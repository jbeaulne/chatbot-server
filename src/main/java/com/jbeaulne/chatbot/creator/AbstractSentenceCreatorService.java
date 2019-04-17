package com.jbeaulne.chatbot.creator;

import java.util.List;

import com.jbeaulne.chatbot.beans.BaseWord;
import com.jbeaulne.chatbot.beans.Word;
import com.jbeaulne.chatbot.constants.SpecialWords;

public abstract class AbstractSentenceCreatorService implements SentenceCreatorService {
	
	private static final int DEFAULT_MAX = 1024;

	@Override
	public String createSentence(String profile, String keyword) {
		return createSentence(profile, keyword, DEFAULT_MAX);
	}

	@Override
	public String createSentence(String profile, String keyword, int max) {
		StringBuilder sentence = new StringBuilder();
		Word initialWord = getWord(keyword,profile);
		if(initialWord != null) {
			if(!SpecialWords.START_SENTENCE.equalsIgnoreCase(initialWord.getWord())) {
				sentence.append(initialWord.getWord());
				buildBeginningOfSentence(sentence,initialWord,max);
			}
			
			buildEndOfSentence(sentence,initialWord,max);
		}
		return sentence.toString().trim();
	}
	
	protected void buildBeginningOfSentence(StringBuilder sentence, Word word, int max) {
		String nextWord = selectNextWord(word,"wordsBefore");
		if(nextWord == null || sentence.length() + nextWord.length() > max) {
			return;
		}
		if(!SpecialWords.START_SENTENCE.equalsIgnoreCase(nextWord)) {
			Word nextWordObj = getWord(nextWord,word.getProfile());
			String article = selectArticle(nextWordObj);
			sentence.insert(0, nextWordObj.getWord() + " ");
			if(article!=null) {
				sentence.insert(0, article + " ");
			} 
			buildBeginningOfSentence(sentence,nextWordObj,max);
		}
	}
	
	protected void buildEndOfSentence(StringBuilder sentence, Word word, int max) {
		String nextWord = selectNextWord(word,"wordsAfter");
		if(nextWord == null || sentence.length() + nextWord.length() > max) {
			return;
		}
		if(!SpecialWords.END_SENTENCE.equalsIgnoreCase(nextWord)) {
			Word nextWordObj = getWord(nextWord,word.getProfile());
			String article = selectArticle(nextWordObj);
			if(article != null) {
				sentence.append(" " + article);
			}
			sentence.append(" " + nextWordObj.getWord());
			buildEndOfSentence(sentence,nextWordObj,max);
		}
	}
	
	protected List<BaseWord> selectList(Word word, String listName){
		if("wordsBefore".equalsIgnoreCase(listName)) {
			return word.getWordsBefore();
		} else if ("wordsAfter".equalsIgnoreCase(listName)) {
			return word.getWordsAfter();
		} else if ("articles".equalsIgnoreCase(listName)) {
			return word.getArticles();
		} else {
			throw new RuntimeException("List name not valid: " + listName);
		}
	}
	
	protected String selectArticle(Word word) {
		String article = selectNextWord(word,"articles");
		if(SpecialWords.NO_ARTICLE.equalsIgnoreCase(article)) {
			return null;
		} else {
			return article;
		}
	}
	
	protected abstract String selectNextWord(Word word, String listName);
	protected abstract Word getWord(String word, String profile);
}
