package com.jbeaulne.chatbot.service;

import com.jbeaulne.chatbot.beans.Word;

public interface WordBaseService {
	public Word findByWordAndProfile(String word, String profile);
	public void processSentence(String profile, String sentence);
	public void deleteProfile(String profile);
}
