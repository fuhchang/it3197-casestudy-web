package com.example.CommunityOutreach.model;

import java.util.Date;

public class Article {
	private int articleID;
	private String title;
	private String content;
	private Date dateTime;
	private String category;
	private String location;
	private String userNRIC;
	private int active;
	private int approved;
	
	/**
	 * Article's default constructor
	 */
	public Article(){}

	/**
	 * This constructor is to retrieve article
	 * @param articleID
	 * @param title
	 * @param content
	 * @param dateTime
	 * @param category
	 * @param location
	 * @param userNRIC
	 * @param active
	 * @param approved
	 */
	public Article(int articleID, String title, String content, Date dateTime,String category, String location, String userNRIC, int active,int approved) {
		super();
		this.articleID = articleID;
		this.title = title;
		this.content = content;
		this.dateTime = dateTime;
		this.category = category;
		this.location = location;
		this.userNRIC = userNRIC;
		this.active = active;
		this.approved = approved;
	}

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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

	public String getUserNRIC() {
		return userNRIC;
	}

	public void setUserNRIC(String userNRIC) {
		this.userNRIC = userNRIC;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}
}