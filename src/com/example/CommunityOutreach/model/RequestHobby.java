package com.example.CommunityOutreach.model;

import java.util.Date;

public class RequestHobby {

	private int requestID;
	private int eventID;
	private int hobbyID;
	private String requestStatus;
	private Date requestDateStart;
	private Date requestDateEnd;
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public int getHobbyID() {
		return hobbyID;
	}
	public void setHobbyID(int hobbyID) {
		this.hobbyID = hobbyID;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	/**
	 * @return the requestDateStart
	 */
	public Date getRequestDateStart() {
		return requestDateStart;
	}
	/**
	 * @param requestDateStart the requestDateStart to set
	 */
	public void setRequestDateStart(Date requestDateStart) {
		this.requestDateStart = requestDateStart;
	}
	/**
	 * @return the requestDateEnd
	 */
	public Date getRequestDateEnd() {
		return requestDateEnd;
	}
	/**
	 * @param requestDateEnd the requestDateEnd to set
	 */
	public void setRequestDateEnd(Date requestDateEnd) {
		this.requestDateEnd = requestDateEnd;
	}
	
	
	
}
