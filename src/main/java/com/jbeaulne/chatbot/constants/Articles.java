package com.jbeaulne.chatbot.constants;

import java.util.HashSet;
import java.util.Set;

public enum Articles {
	THE("the"),A("a"),AN("an");
	
	private String word;
	
	private static Set<String> wordSet;
	
	private Articles(String word) {
		setWord(word);
	}

	public String getWord() {
		return word;
	}

	private void setWord(String word) {
		this.word = word;
		getWordSet().add(word);
	}
	
	public static Set<String> getWordSet(){
		if(wordSet == null) {
			wordSet = new HashSet<String>();
		}
		return wordSet;
	}
}
