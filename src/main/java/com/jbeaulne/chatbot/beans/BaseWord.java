package com.jbeaulne.chatbot.beans;

public class BaseWord {
	
	private String word;
	private int count;
	
	public BaseWord() {}
	
	public BaseWord(String word, int count){
		setWord(word);
		setCount(count);
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
