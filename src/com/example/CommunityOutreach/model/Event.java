package com.example.CommunityOutreach.model;

import java.util.Date;

public class Event{
	private int eventID;
	private String eventAdminNRIC;
	private String eventName;
	private String eventCategory;
	private String eventDescription;
	private String eventType;
	private Date eventDateTimeFrom;
	private Date eventDateTimeTo;
	private String occurence;
	private String eventLocation;
	private int noOfParticipantsAllowed;
	private int active;
	private double lat;
	private double lng;
	
	/**
	 * Event's default constructor
	 */
	public Event(){}

	/**
	 * This constructor is to retrieve events in mobile
	 * @param eventID
	 * @param eventAdminNRIC
	 * @param eventName
	 * @param eventCategory
	 * @param eventDescription
	 * @param eventType
	 * @param eventDateTimeFrom
	 * @param eventDateTimeTo
	 * @param occurence
	 * @param eventLocation
	 * @param noOfParticipantsAllowed
	 * @param active
	 */
	public Event(int eventID, String eventAdminNRIC, String eventName,String eventCategory, String eventDescription, String eventType,Date eventDateTimeFrom, Date eventDateTimeTo, String occurence,String eventLocation, int noOfParticipantsAllowed, int active) {
		// TODO Auto-generated constructor stub
		this.eventID = eventID;
		this.eventAdminNRIC = eventAdminNRIC;
		this.eventName = eventName;
		this.eventCategory = eventCategory;
		this.eventDescription = eventDescription;
		this.eventType = eventType;
		this.eventDateTimeFrom = eventDateTimeFrom;
		this.eventDateTimeTo = eventDateTimeTo;
		this.occurence = occurence;
		this.eventLocation = eventLocation;
		this.noOfParticipantsAllowed = noOfParticipantsAllowed;
		this.active = active;
	}
	
	/**
	 * This constructor is to retrieve events in web
	 * @param eventID
	 * @param eventAdminNRIC
	 * @param eventName
	 * @param eventCategory
	 * @param eventDescription
	 * @param eventType
	 * @param eventDateTimeFrom
	 * @param eventDateTimeTo
	 * @param occurence
	 * @param eventLocation
	 * @param noOfParticipantsAllowed
	 * @param active
	 * @param lat
	 * @param lng
	 */
	public Event(int eventID, String eventAdminNRIC, String eventName,String eventCategory, String eventDescription, String eventType,Date eventDateTimeFrom, Date eventDateTimeTo, String occurence,String eventLocation, int noOfParticipantsAllowed, int active, double lat, double lng) {
		super();
		this.eventID = eventID;
		this.eventAdminNRIC = eventAdminNRIC;
		this.eventName = eventName;
		this.eventCategory = eventCategory;
		this.eventDescription = eventDescription;
		this.eventType = eventType;
		this.eventDateTimeFrom = eventDateTimeFrom;
		this.eventDateTimeTo = eventDateTimeTo;
		this.occurence = occurence;
		this.eventLocation = eventLocation;
		this.noOfParticipantsAllowed = noOfParticipantsAllowed;
		this.active = active;
		this.lat = lat;
		this.lng = lng;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventAdminNRIC() {
		return eventAdminNRIC;
	}

	public void setEventAdminNRIC(String eventAdminNRIC) {
		this.eventAdminNRIC = eventAdminNRIC;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getEventDateTimeFrom() {
		return eventDateTimeFrom;
	}

	public void setEventDateTimeFrom(Date eventDateTimeFrom) {
		this.eventDateTimeFrom = eventDateTimeFrom;
	}

	public Date getEventDateTimeTo() {
		return eventDateTimeTo;
	}

	public void setEventDateTimeTo(Date eventDateTimeTo) {
		this.eventDateTimeTo = eventDateTimeTo;
	}

	public String getOccurence() {
		return occurence;
	}

	public void setOccurence(String occurence) {
		this.occurence = occurence;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public int getNoOfParticipantsAllowed() {
		return noOfParticipantsAllowed;
	}

	public void setNoOfParticipantsAllowed(int noOfParticipantsAllowed) {
		this.noOfParticipantsAllowed = noOfParticipantsAllowed;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
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
