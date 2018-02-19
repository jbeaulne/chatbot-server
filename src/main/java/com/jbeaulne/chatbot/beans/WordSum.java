package com.jbeaulne.chatbot.beans;

import org.springframework.data.annotation.Id;

public class WordSum {

	@Id
	private String Id;
	private int sum;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
}
