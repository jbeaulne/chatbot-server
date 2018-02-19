package com.jbeaulne.chatbot.utils;

import java.util.ArrayList;
import java.util.List;

import com.jbeaulne.chatbot.beans.BaseWord;
import com.jbeaulne.chatbot.beans.Word;
import com.jbeaulne.chatbot.processor.ProcessedWord;

public class WordUtils {

	public static Word createNewWord(ProcessedWord w, String profile) {
		List<BaseWord> articleList = new ArrayList<BaseWord>();
		List<BaseWord> beforeList = new ArrayList<BaseWord>();
		List<BaseWord> afterList = new ArrayList<BaseWord>();
		
		if(w.getArticle() != null) {
			articleList.add(new BaseWord(w.getArticle(),1));
		}
		
		if(w.getWordBefore() != null) {
			beforeList.add(new BaseWord(w.getWordBefore(),1));
		}
		
		if(w.getWordAfter() != null) {
			afterList.add(new BaseWord(w.getWordAfter(),1));
		}
		
		return new Word(w.getWord(),1,profile,beforeList,afterList,articleList);
	}
}
