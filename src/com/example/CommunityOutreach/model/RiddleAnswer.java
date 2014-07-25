package com.example.CommunityOutreach.model;

public class RiddleAnswer {
	private int riddleAnswerID;
	private Riddle riddle;
	private String riddleAnswer;
	private String riddleAnswerStatus;
	
	public RiddleAnswer(){}
	
	public RiddleAnswer(int riddleAnswerID, Riddle riddle, String riddleAnswer, String riddleAnswerStatus){
		this.riddleAnswerID = riddleAnswerID;
		this.riddle = riddle;
		this.riddleAnswer = riddleAnswer;
		this.riddleAnswerStatus = riddleAnswerStatus;
	}
	
	public RiddleAnswer(int riddleAnswerID) {
		this.riddleAnswerID = riddleAnswerID;
	}

	public int getRiddleAnswerID() {
		return riddleAnswerID;
	}
	public void setRiddleAnswerID(int riddleAnswerID) {
		this.riddleAnswerID = riddleAnswerID;
	}
	public Riddle getRiddle() {
		return riddle;
	}
	public void setRiddle(Riddle riddle) {
		this.riddle = riddle;
	}
	public String getRiddleAnswer() {
		return riddleAnswer;
	}
	public void setRiddleAnswer(String riddleAnswer) {
		this.riddleAnswer = riddleAnswer;
	}
	public String getRiddleAnswerStatus() {
		return riddleAnswerStatus;
	}
	public void setRiddleAnswerStatus(String riddleAnswerStatus) {
		this.riddleAnswerStatus = riddleAnswerStatus;
	}
}