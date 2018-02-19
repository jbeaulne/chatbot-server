package com.jbeaulne.chatbot.service.mongo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.jbeaulne.chatbot.beans.BaseWord;
import com.jbeaulne.chatbot.beans.Word;
import com.jbeaulne.chatbot.beans.WordSum;
import com.jbeaulne.chatbot.exceptions.WordNotFoundException;
import com.jbeaulne.chatbot.mogodb.repository.WordBaseRepository;
import com.jbeaulne.chatbot.processor.ProcessedWord;
import com.jbeaulne.chatbot.service.AbstractWordBaseService;
import com.jbeaulne.chatbot.service.WordBaseService;
import com.jbeaulne.chatbot.utils.WordUtils;

public class WordBaseMongoService extends AbstractWordBaseService implements WordBaseService {
	
	@Autowired
	private WordBaseRepository repository;
	
	@Autowired
	private MongoTemplate template;
	
	@Override
	public Word findByWordAndProfile(String word, String profile) {
		Word wordObj = repository.findByWordAndProfile(word, profile);
		if(wordObj == null) {
			throw new WordNotFoundException();
		}
		return wordObj;
	}
	
	@Override
	public void deleteProfile(String profile) {
		repository.removeByProfile(profile);
	}
	
	@Override
	protected void processWord(ProcessedWord processedWord, String profile) {
		Word word = repository.findIDByWordAndProfile(processedWord.getWord(),profile);
		
		if(word == null) {
			//insert word
			word = WordUtils.createNewWord(processedWord, profile);
			repository.insert(word);
		} else {
			String id = word.getId();
			
			increaseWordCount(id);
			increaseListCount(id,processedWord.getWordBefore(),"wordsBefore");
			increaseListCount(id,processedWord.getWordAfter(),"wordsAfter");
			increaseListCount(id,processedWord.getArticle(),"articles");
		}
		
	}
	
	protected void increaseWordCount(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		Update update = new Update();
		update.inc("count", 1);
		template.findAndModify(query, update, Word.class);
	}
	
	protected void increaseListCount(String id, String word, String listName) {
		String queryString = listName + ".word";
		String updateString = listName + ".$.count";
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		query.addCriteria(Criteria.where(queryString).is(word));
		
		Update update = new Update();
		update.inc(updateString, 1);
		Word wordObj = template.findAndModify(query, update, Word.class);
		
		if(wordObj == null) {
			query = new Query();
			query.addCriteria(Criteria.where("_id").is(id));
			
			update = new Update();
			update.push(listName, new BaseWord(word,1));
			template.findAndModify(query, update, Word.class);
		}
	}

	
}
