package com.example.CommunityOutreach.model;

import java.util.Date;

public class UserLocation {
	private int locationID;
	private User user;
	private Date dateTime;
	private double lat;
	private double lng;
	
	// Constructor
	public UserLocation() {}
	
	public UserLocation(int locationID, User user, Date dateTime, double lat, double lng) {
		this.locationID = locationID;
		this.user = user;
		this.dateTime = dateTime;
		this.lat = lat;
		this.lng = lng;
	}
	
	// Getter and Setter
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
}
