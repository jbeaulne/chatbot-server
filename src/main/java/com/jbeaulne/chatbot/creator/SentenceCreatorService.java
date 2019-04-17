package com.jbeaulne.chatbot.creator;

public interface SentenceCreatorService {
	public String createSentence(String profile, String keyword);
	public String createSentence(String profile, String keyword, int max);
}
