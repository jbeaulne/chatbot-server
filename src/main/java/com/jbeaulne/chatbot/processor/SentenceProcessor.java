package com.jbeaulne.chatbot.processor;

public interface SentenceProcessor {
	
	public boolean hasNext();
	public ProcessedWord getProcessedWord();
	
	public String getSentence();
	public void setSentence(String sentence);
}
