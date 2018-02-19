package com.jbeaulne.chatbot.processor;

import com.jbeaulne.chatbot.constants.Articles;
import com.jbeaulne.chatbot.constants.SpecialWords;

public class SentenceProcessorImpl implements SentenceProcessor {

	private static final String SPLIT = " ";
	private static final String FILTER_REGEX = "[^a-zA-Z0-9 ]";
	private static final String EMPTY_STRING = "";
	
	private String sentence;
	private ProcessedWord processedWord;
	
	protected String[] sentenceArray;
	protected int curIndex;
	protected boolean postProcessed;
	
	public SentenceProcessorImpl() {}
	
	public SentenceProcessorImpl(String sentence) {
		setSentence(sentence);
	}
	
	@Override
	public boolean hasNext() {
		if(sentenceArray == null) {
			return false;
		}
		
		ProcessedWord processedWord = new ProcessedWord();
		
		if(curIndex >= sentenceArray.length && postProcessed) {
			return false;
		} else if (curIndex >= sentenceArray.length) {
			processedWord.setWord(SpecialWords.END_SENTENCE);
			setWordBefore(processedWord,curIndex);
			postProcessed = true;
		} else if(curIndex < 0) {
			processedWord.setWord(SpecialWords.START_SENTENCE);
			setWordAfter(processedWord,curIndex);
			curIndex++;
		} else {
			setWordBefore(processedWord,curIndex);
			setWord(processedWord);
			setWordAfter(processedWord,curIndex);
			curIndex++;
		}
		setProcessedWord(processedWord);
		return true;
	}
	
	protected void setWordBefore(ProcessedWord processedWord, int index) {
		int checkIndex = index - 1;
		
		if(checkIndex < 0) {
			processedWord.setWordBefore(SpecialWords.START_SENTENCE);
		} else {
			String beforeWord = sentenceArray[checkIndex].trim();
			if(Articles.getWordSet().contains(beforeWord)) {
				setWordBefore(processedWord,checkIndex);
			} else {
				processedWord.setWordBefore(beforeWord);
			}
		}
	}
	
	protected void setWord(ProcessedWord processedWord) {
		String word = sentenceArray[curIndex].trim();
		if(Articles.getWordSet().contains(word)) {
			curIndex++;
			processedWord.setArticle(word);
			setWord(processedWord);
		} else {
			processedWord.setWord(word);
		}
	}
	
	protected void setWordAfter(ProcessedWord processedWord, int index) {
		int checkIndex = index+1;
		if(checkIndex >= sentenceArray.length) {
			processedWord.setWordAfter(SpecialWords.END_SENTENCE);
		} else {
			String afterWord = sentenceArray[checkIndex].trim();
			if(Articles.getWordSet().contains(afterWord)) {
				setWordAfter(processedWord,checkIndex);
			} else {
				processedWord.setWordAfter(afterWord);
			}
		}
	}
	
	protected void setProcessedWord(ProcessedWord processedWord) {
		this.processedWord = processedWord;
	}

	@Override
	public ProcessedWord getProcessedWord() {
		return processedWord;
	}

	@Override
	public String getSentence() {
		return sentence;
	}

	@Override
	public void setSentence(String sentence) {
		this.sentence = sentence.toLowerCase();
		sentenceArray = this.sentence.trim().replaceAll(FILTER_REGEX, EMPTY_STRING).split(SPLIT);
		curIndex = -1;
		postProcessed = false;
	}
	

}
