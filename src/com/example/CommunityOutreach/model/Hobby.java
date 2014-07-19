package com.example.CommunityOutreach.model;

import java.io.InputStream;

import com.mysql.jdbc.Blob;

public class Hobby {
	private int grpID;
	private String grpName;
	private String category;
	private String location;
	private double Lat;
	private double Lng;
	private String grpDesc;
	private int active;
	private byte[] photo;
	private String adminNric;
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
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	/**
	 * @return the grpDesc
	 */
	public String getGrpDesc() {
		return grpDesc;
	}
	/**
	 * @param grpDesc the grpDesc to set
	 */
	public void setGrpDesc(String grpDesc) {
		this.grpDesc = grpDesc;
	}
	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	/**
	 * @return the lng
	 */
	public double getLng() {
		return Lng;
	}
	/**
	 * @param lng the lng to set
	 */
	public void setLng(double lng) {
		Lng = lng;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return Lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		Lat = lat;
	}
	/**
	 * @return the adminNric
	 */
	public String getAdminNric() {
		return adminNric;
	}
	/**
	 * @param adminNric the adminNric to set
	 */
	public void setAdminNric(String adminNric) {
		this.adminNric = adminNric;
	}
	
	
}
