package com.jbeaulne.chatbot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WordNotFoundException extends RuntimeException {

	public WordNotFoundException() {
		super("Word not found");
	}
}
