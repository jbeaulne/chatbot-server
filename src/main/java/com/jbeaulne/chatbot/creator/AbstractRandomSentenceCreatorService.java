package com.jbeaulne.chatbot.creator;

import java.util.List;
import java.util.Random;

import com.jbeaulne.chatbot.beans.BaseWord;
import com.jbeaulne.chatbot.beans.Word;

public abstract class AbstractRandomSentenceCreatorService extends AbstractSentenceCreatorService {

	private Random rng = new Random();

	@Override
	protected String selectNextWord(Word word, String listName) {
		List<BaseWord> wordList = selectList(word,listName);
		int sum = getWordSum(word,listName);
		int selection = rng.nextInt(sum);
		int count = 0;
		int i = 0;
		
		while(count < selection) {
			count += wordList.get(i).getCount();
			i++;
		}
		BaseWord selectedWord = wordList.get(Math.max(0,i-1));
		return selectedWord.getWord();
	}
	
	protected abstract int getWordSum(Word word, String listName);

}
