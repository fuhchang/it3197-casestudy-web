package com.example.CommunityOutreach.model;

public class RiddleAnswered {
	private Riddle riddleID;
	private User nric;
	private String riddleAns;
	private String riddleStatus;
	
	public RiddleAnswered(){};
	
	public RiddleAnswered(Riddle riddleID, User nric, String riddleAns, String riddleStatus){
		this.riddleID = riddleID;
		this.nric = nric;
		this.riddleAns = riddleAns;
		this.riddleStatus = riddleStatus;
	}
	
	public Riddle getRiddleID() {
		return riddleID;
	}
	public void setRiddleID(Riddle riddleID) {
		this.riddleID = riddleID;
	}
	public User getNric() {
		return nric;
	}
	public void setNric(User nric) {
		this.nric = nric;
	}
	public String getRiddleAns() {
		return riddleAns;
	}
	public void setRiddleAns(String riddleAns) {
		this.riddleAns = riddleAns;
	}
	public String getRiddleStatus() {
		return riddleStatus;
	}
	public void setRiddleStatus(String riddleStatus) {
		this.riddleStatus = riddleStatus;
	};
}

