package com.jbeaulne.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.jbeaulne.chatbot.processor.ProcessedWord;
import com.jbeaulne.chatbot.processor.SentenceProcessor;

public abstract class AbstractWordBaseService implements WordBaseService {

	@Autowired
	protected SentenceProcessor sentenceProcessor;
	
	@Override
	public void processSentence(String profile, String sentence) {
		sentenceProcessor.setSentence(sentence);
		
		while(sentenceProcessor.hasNext()) {
			ProcessedWord processedWord = sentenceProcessor.getProcessedWord();
			processWord(processedWord,profile);
		}
	}
	
	protected abstract void processWord(ProcessedWord processedWord, String profile);
	
}
