package com.jbeaulne.chatbot.creator.mongo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.jbeaulne.chatbot.beans.Word;
import com.jbeaulne.chatbot.beans.WordSum;
import com.jbeaulne.chatbot.creator.AbstractRandomSentenceCreatorService;
import com.jbeaulne.chatbot.mogodb.repository.WordBaseRepository;

public class RandomMongoSentenceCreatorService extends AbstractRandomSentenceCreatorService {

	@Autowired
	private WordBaseRepository repository;
	
	@Autowired
	private MongoTemplate template;
	
	@Override
	protected int getWordSum(Word word, String listName) {
		Aggregation agg = newAggregation(
		        match(Criteria.where("_id").is(word.getId())),
		        unwind(listName),        
		        group("id").sum(listName+".count").as("sum")
		    );
		
		AggregationResults<WordSum> results = template.aggregate(agg, Word.class, WordSum.class);
		return results.getMappedResults().get(0).getSum();
	}

	@Override
	protected Word getWord(String word, String profile) {
		return repository.findByWordAndProfile(word, profile);
	}

}
