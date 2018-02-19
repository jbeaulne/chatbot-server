package com.jbeaulne.chatbot.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbeaulne.chatbot.beans.SentenceMessage;
import com.jbeaulne.chatbot.beans.Word;
import com.jbeaulne.chatbot.constants.SpecialWords;
import com.jbeaulne.chatbot.creator.SentenceCreatorService;
import com.jbeaulne.chatbot.service.WordBaseService;

@RestController
@RequestMapping("/chatbot/{profile}")
public class ChatbotController {

	@Autowired
	private WordBaseService service;
	
	@Autowired
	private SentenceCreatorService creator;
	
	@RequestMapping("/{word}")
	public Word getWord(@PathVariable("profile") String profile, @PathVariable("word") String word) {
		return service.findByWordAndProfile(word, profile);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/processSentence")
	public ResponseEntity<String> processSentence(@PathVariable("profile") String profile, @RequestBody SentenceMessage message){
		service.processSentence(profile, message.getSentence());
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/")
	public ResponseEntity<String> deleteProfile(@PathVariable("profile") String profile){
		service.deleteProfile(profile);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping("/createSentence")
	public SentenceMessage createSentence(@PathVariable("profile") String profile, 
			@RequestParam(name="keyword",defaultValue=SpecialWords.START_SENTENCE) String keyword,
			@RequestParam(name="max",required=false) String maxStr) {
		SentenceMessage sentence = new SentenceMessage();
		if(maxStr != null && StringUtils.isNumeric(maxStr)) {
			int max = Integer.parseInt(maxStr);
			creator.setMax(max);
		}
		sentence.setSentence(creator.createSentence(profile, keyword));
		return sentence;
	}
} 
