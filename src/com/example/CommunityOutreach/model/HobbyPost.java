package com.example.CommunityOutreach.model;

import java.util.Date;

public class HobbyPost {
	private int postID;
	private Date datetime;
	private String content;
	private Double Lat;
	private Double Lng;
	private int grpID;
	private String nric;
	private String postTitle;
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getLat() {
		return Lat;
	}
	public void setLat(Double lat) {
		Lat = lat;
	}
	public Double getLng() {
		return Lng;
	}
	public void setLng(Double lng) {
		Lng = lng;
	}
	/**
	 * @return the grpID
	 */
	public int getGrpID() {
		return grpID;
	}
	/**
	 * @param grpID the grpID to set
	 */
	public void setGrpID(int grpID) {
		this.grpID = grpID;
	}
	/**
	 * @return the nric
	 */
	public String getNric() {
		return nric;
	}
	/**
	 * @param nric the nric to set
	 */
	public void setNric(String nric) {
		this.nric = nric;
	}
	/**
	 * @return the postTitle
	 */
	public String getPostTitle() {
		return postTitle;
	}
	/**
	 * @param postTitle the postTitle to set
	 */
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	
	
	
}
