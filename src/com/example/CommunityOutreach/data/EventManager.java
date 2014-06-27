package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Event;

/**
 * This is the data access manager for Event
 * @author Lee Zhuo Xun
 *
 */
public class EventManager {
	private DBController dbController = new DBController();
	
	/**
	 * This method is to create event into database
	 * 
	 * @param event
	 * @return boolean
	 */
	public boolean createEvent(Event event) {
		String sql = "INSERT INTO event ";
		sql += "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, event.getEventID());
			ps.setString(2, event.getEventAdminNRIC());
			ps.setString(3, event.getEventName());
			ps.setString(4, event.getEventCategory());
			ps.setString(5, event.getEventDescription());
			ps.setString(6, event.getEventType());
			Timestamp timestampFrom = new Timestamp(event.getEventDateTimeFrom().getTime());
			Timestamp timestampTo = new Timestamp(event.getEventDateTimeTo().getTime());
			ps.setTimestamp(7, timestampFrom);
			ps.setTimestamp(8, timestampTo);
			ps.setString(9, event.getOccurence());
			ps.setString(10, event.getEventLocation());
			ps.setInt(11, event.getNoOfParticipantsAllowed());
			ps.setInt(12, event.getActive());

			System.out.println(ps);
			ps.executeUpdate();

			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is to retrieve all events from the database.
	 * 
	 * @return ArrayList<Event>
	 */
	public ArrayList<Event> retrieveAllEvents() {
		String sql = "SELECT * FROM event";
		ArrayList<Event> eventArrList = new ArrayList<Event>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setEventID(rs.getInt("eventID"));
				event.setEventAdminNRIC(rs.getString("eventAdminNRIC"));
				event.setEventName(rs.getString("eventName"));
				event.setEventCategory(rs.getString("eventCategory"));
				event.setEventDescription(rs.getString("eventDescription"));
				event.setEventType(rs.getString("eventType"));
				event.setEventDateTimeFrom(rs.getTimestamp("eventDateTimeFrom"));
				event.setEventDateTimeTo(rs.getTimestamp("eventDateTimeTo"));
				event.setOccurence(rs.getString("occurence"));
				event.setEventLocation(rs.getString("eventLocation"));
				event.setNoOfParticipantsAllowed(rs.getInt("noOfParticipantsAllowed"));
				event.setActive(rs.getInt("active"));
				eventArrList.add(event);
			}
			conn.close();
			return eventArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to retrieve a event based on eventID
	 * @param eventID
	 * @return Event
	 */
	public Event retrieveEvent(int eventID) {
		String sql = "SELECT * FROM event WHERE eventID = " + eventID ;
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			Event event = new Event();
			if (rs.next()) {
				event.setEventID(rs.getInt("eventID"));
				event.setEventAdminNRIC(rs.getString("eventAdminNRIC"));
				event.setEventName(rs.getString("eventName"));
				event.setEventCategory(rs.getString("eventCategory"));
				event.setEventDescription(rs.getString("eventDescription"));
				event.setEventType(rs.getString("eventType"));
				event.setEventDateTimeFrom(rs.getTimestamp("eventDateTimeFrom"));
				event.setEventDateTimeTo(rs.getTimestamp("eventDateTimeTo"));
				event.setOccurence(rs.getString("occurence"));
				event.setEventLocation(rs.getString("eventLocation"));
				event.setNoOfParticipantsAllowed(rs.getInt("noOfParticipantsAllowed"));
				event.setActive(rs.getInt("active"));
			} else {
				return null;
			}
			conn.close();
			return event;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to edit event into the database
	 * @param event
	 * @return boolean
	 */
	public boolean editEvent(Event event) {
		String sql = "UPDATE event ";
		sql += "SET eventAdminNRIC = ? , eventName = ? , eventCategory = ? , eventDescription = ? , eventType = ? ," +
				" eventDateTimeFrom = ? , eventDateTimeTo = ? , occurence = ? , eventLocation = ? , noOfParticipantsAllowed = ? WHERE eventID = ? ";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, event.getEventAdminNRIC());
			ps.setString(2, event.getEventName());
			ps.setString(3, event.getEventCategory());
			ps.setString(4, event.getEventDescription());
			ps.setString(5, event.getEventType());
			Timestamp timestampFrom = new Timestamp(event.getEventDateTimeFrom().getTime());
			Timestamp timestampTo = new Timestamp(event.getEventDateTimeTo().getTime());
			ps.setTimestamp(6, timestampFrom);
			ps.setTimestamp(7, timestampTo);
			ps.setString(8, event.getOccurence());
			ps.setString(9, event.getEventLocation());
			ps.setInt(10, event.getNoOfParticipantsAllowed());
			ps.setInt(11, event.getEventID());
			
			System.out.println(ps);
			ps.executeUpdate();
			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is to obsolete event
	 * @param eventID
	 * @return boolean
	 */
	public boolean obsoleteEvent(int eventID) {
		String sql = "UPDATE event SET active = 0 WHERE eventID = " + eventID;
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			System.out.println(ps1);
			ps1.executeUpdate();
			
			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
