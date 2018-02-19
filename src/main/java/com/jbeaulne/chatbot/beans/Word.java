package com.jbeaulne.chatbot.beans;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wordbase")
@CompoundIndexes({
	@CompoundIndex(name="word_index",def="{'word':1,'profile':1}")
})
public class Word extends BaseWord {
	
	@Id
	private String id;

	private String profile;
	
	private List<BaseWord> wordsBefore;
	private List<BaseWord> wordsAfter;
	private List<BaseWord> articles;
	
	public Word() {}
	
	public Word(String word, int count, String profile) {
		super(word,count);
		setProfile(profile);
	}
	
	public Word(String word, int count, String profile, List<BaseWord> wordsBefore, List<BaseWord> wordsAfter, List<BaseWord> articles) {
		super(word,count);
		setProfile(profile);
		setWordsBefore(wordsBefore);
		setWordsAfter(wordsAfter);
		setArticles(articles);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<BaseWord> getWordsBefore() {
		return wordsBefore;
	}
	public void setWordsBefore(List<BaseWord> wordsBefore) {
		this.wordsBefore = wordsBefore;
	}
	public List<BaseWord> getWordsAfter() {
		return wordsAfter;
	}
	public void setWordsAfter(List<BaseWord> wordsAfter) {
		this.wordsAfter = wordsAfter;
	}

	public List<BaseWord> getArticles() {
		return articles;
	}

	public void setArticles(List<BaseWord> articles) {
		this.articles = articles;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
