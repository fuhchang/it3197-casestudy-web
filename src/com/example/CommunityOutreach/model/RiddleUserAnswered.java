package com.example.CommunityOutreach.model;

public class RiddleUserAnswered {
	private int riddleUserAnsweredID;
	private Riddle riddle;
	private RiddleAnswer riddleAnswer;
	private User user;
	private String answeredRate;
	
	public RiddleUserAnswered(){}
	
	public RiddleUserAnswered(int riddleUserAnsweredID, Riddle riddle, RiddleAnswer riddleAnswer, User user, String answeredRate){
		this.riddleUserAnsweredID = riddleUserAnsweredID;
		this.riddle = riddle;
		this.riddleAnswer = riddleAnswer;
		this.user = user;
		this.answeredRate = answeredRate;
	}

	public int getRiddleUserAnsweredID() {
		return riddleUserAnsweredID;
	}
	public void setRiddleUserAnsweredID(int riddleUserAnsweredID) {
		this.riddleUserAnsweredID = riddleUserAnsweredID;
	}
	public Riddle getRiddle() {
		return riddle;
	}
	public void setRiddle(Riddle riddle) {
		this.riddle = riddle;
	}
	public RiddleAnswer getRiddleAnswer() {
		return riddleAnswer;
	}
	public void setRiddleAnswer(RiddleAnswer riddleAnswer) {
		this.riddleAnswer = riddleAnswer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAnsweredRate() {
		return answeredRate;
	}
	public void setAnsweredRate(String answeredRate) {
		this.answeredRate = answeredRate;
	}
}
