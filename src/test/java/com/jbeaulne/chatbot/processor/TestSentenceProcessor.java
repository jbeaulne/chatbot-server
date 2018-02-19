package com.jbeaulne.chatbot.processor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jbeaulne.chatbot.constants.SpecialWords;
import com.jbeaulne.chatbot.processor.SentenceProcessor;
import com.jbeaulne.chatbot.processor.SentenceProcessorImpl;

public class TestSentenceProcessor {

	@Test
	public void test() {
		String sentence = "Crooked Hilary Clinton is a very terrible person and the worst!";
		
		SentenceProcessor processor = new SentenceProcessorImpl(sentence);
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertNull(processor.getProcessedWord().getWordBefore());
		assertEquals(processor.getProcessedWord().getWord(),SpecialWords.START_SENTENCE);
		assertEquals(processor.getProcessedWord().getWordAfter(),"crooked");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),SpecialWords.START_SENTENCE);
		assertEquals(processor.getProcessedWord().getWord(),"crooked");
		assertEquals(processor.getProcessedWord().getWordAfter(),"hilary");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),"crooked");
		assertEquals(processor.getProcessedWord().getWord(),"hilary");
		assertEquals(processor.getProcessedWord().getWordAfter(),"clinton");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),"hilary");
		assertEquals(processor.getProcessedWord().getWord(),"clinton");
		assertEquals(processor.getProcessedWord().getWordAfter(),"is");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),"clinton");
		assertEquals(processor.getProcessedWord().getWord(),"is");
		assertEquals(processor.getProcessedWord().getWordAfter(),"very");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),"a");
		assertEquals(processor.getProcessedWord().getWordBefore(),"is");
		assertEquals(processor.getProcessedWord().getWord(),"very");
		assertEquals(processor.getProcessedWord().getWordAfter(),"terrible");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),"very");
		assertEquals(processor.getProcessedWord().getWord(),"terrible");
		assertEquals(processor.getProcessedWord().getWordAfter(),"person");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),"terrible");
		assertEquals(processor.getProcessedWord().getWord(),"person");
		assertEquals(processor.getProcessedWord().getWordAfter(),"and");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),"person");
		assertEquals(processor.getProcessedWord().getWord(),"and");
		assertEquals(processor.getProcessedWord().getWordAfter(),"worst");
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),"the");
		assertEquals(processor.getProcessedWord().getWordBefore(),"and");
		assertEquals(processor.getProcessedWord().getWord(),"worst");
		assertEquals(processor.getProcessedWord().getWordAfter(),SpecialWords.END_SENTENCE);
		
		assertTrue(processor.hasNext());
		assertEquals(processor.getProcessedWord().getArticle(),SpecialWords.NO_ARTICLE);
		assertEquals(processor.getProcessedWord().getWordBefore(),"worst");
		assertEquals(processor.getProcessedWord().getWord(),SpecialWords.END_SENTENCE);
		assertNull(processor.getProcessedWord().getWordAfter());
		
		assertFalse(processor.hasNext());
	}

}
