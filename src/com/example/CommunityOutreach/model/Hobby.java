package com.example.CommunityOutreach.model;

public class Hobby {
	private int grpID;
	private String grpName;
	private String category;
	private String location;
	private String Desc;
	private int active;
	public int getGrpID() {
		return grpID;
	}
	public void setGrpID(int grpID) {
		this.grpID = grpID;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
}
