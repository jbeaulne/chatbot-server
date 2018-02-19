package com.jbeaulne.chatbot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jbeaulne.chatbot.creator.SentenceCreatorService;
import com.jbeaulne.chatbot.creator.mongo.RandomMongoSentenceCreatorService;
import com.jbeaulne.chatbot.processor.SentenceProcessor;
import com.jbeaulne.chatbot.processor.SentenceProcessorImpl;
import com.jbeaulne.chatbot.service.WordBaseService;
import com.jbeaulne.chatbot.service.mongo.WordBaseMongoService;

@Configuration
public class ChatbotConfiguration {

	@Bean
	public WordBaseService getService() {
		return new WordBaseMongoService();
	}
	
	@Bean
	public SentenceProcessor getSentenceProcessor() {
		return new SentenceProcessorImpl();
	}
	
	@Bean
	public SentenceCreatorService getSentenceCreator() {
		return new RandomMongoSentenceCreatorService();
	}
}
