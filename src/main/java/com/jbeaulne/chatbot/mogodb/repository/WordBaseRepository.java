package com.jbeaulne.chatbot.mogodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jbeaulne.chatbot.beans.Word;

public interface WordBaseRepository extends MongoRepository<Word, String> {

	public Word findByWordAndProfile(String word, String profile);
	
	@Query(value="{ 'word' : ?0, 'profile':?1 }", fields="{ '_id':1}")
	public Word findIDByWordAndProfile(String word, String profile);
	
	public void removeByProfile(String profile);
}
