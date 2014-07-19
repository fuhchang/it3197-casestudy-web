package com.example.CommunityOutreach.model;

import java.util.Date;

public class HobbyMembers {
	private int groupID;
	private String userNRIC;
	private Date dateTimeJoined;
	private int active;
	private String role;
	
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public String getUserNRIC() {
		return userNRIC;
	}
	public void setUserNRIC(String userNRIC) {
		this.userNRIC = userNRIC;
	}
	public Date getDateTimeJoined() {
		return dateTimeJoined;
	}
	public void setDateTimeJoined(Date dateTimeJoined) {
		this.dateTimeJoined = dateTimeJoined;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
}
