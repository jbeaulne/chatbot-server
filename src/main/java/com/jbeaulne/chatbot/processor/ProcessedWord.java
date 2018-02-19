package com.jbeaulne.chatbot.processor;

import com.jbeaulne.chatbot.constants.SpecialWords;

public class ProcessedWord {

	private String wordBefore;
	private String word;
	private String wordAfter;
	private String article = SpecialWords.NO_ARTICLE;
	
	public ProcessedWord() {}
	
	public ProcessedWord(String word, String wordBefore, String wordAfter) {
		this(word,wordBefore,wordAfter,null);
	}
	
	public ProcessedWord(String word, String wordBefore, String wordAfter, String article) {
		setWord(word);
		setWordBefore(wordBefore);
		setWordAfter(wordAfter);
		setArticle(article);
	}
	
	public String getWordBefore() {
		return wordBefore;
	}
	public void setWordBefore(String wordBefore) {
		this.wordBefore = wordBefore;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getWordAfter() {
		return wordAfter;
	}
	public void setWordAfter(String wordAfter) {
		this.wordAfter = wordAfter;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
}
