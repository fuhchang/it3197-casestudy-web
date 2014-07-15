package com.example.CommunityOutreach.model;

import java.io.InputStream;

import com.mysql.jdbc.Blob;

public class Hobby {
	private int grpID;
	private String grpName;
	private String category;
	private String location;
	private String grpDesc;
	private int active;
	private byte[] photo;
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
	
	
}
