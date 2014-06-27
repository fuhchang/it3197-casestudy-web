package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.EventParticipants;

/**
 * This is the data access manager for Event Participants
 * @author Lee Zhuo Xun
 *
 */
public class EventParticipantsManager {
	private DBController dbController = new DBController();
	
	/**
	 * This method is to create event participants into database
	 * 
	 * @param eventParticipants
	 * @return boolean
	 */
	public boolean createEventParticipant(EventParticipants eventParticipant) {
		String sql = "INSERT INTO event_participants ";
		sql += "VALUES( ? , ? , ? , ? )";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, eventParticipant.getEventID());
			ps.setString(2, eventParticipant.getUserNRIC());
			Timestamp timestampJoined = new Timestamp(eventParticipant.getDateTimeJoined().getTime());
			ps.setTimestamp(3, timestampJoined);
			ps.setInt(4, eventParticipant.getCheckIn());

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
	 * This method is to retrieve all event participants from the database.
	 * 
	 * @return ArrayList<EventParticipants>
	 */
	public ArrayList<EventParticipants> retrieveAllEventParticipants() {
		String sql = "SELECT * FROM event_participants";
		ArrayList<EventParticipants> eventParticipantsArrList = new ArrayList<EventParticipants>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EventParticipants eventParticipant = new EventParticipants();
				eventParticipant.setEventID(rs.getInt("eventID"));
				eventParticipant.setUserNRIC(rs.getString("userNRIC"));
				eventParticipant.setDateTimeJoined(rs.getTimestamp("dateTimeJoined"));
				eventParticipant.setCheckIn(rs.getInt("checkIn"));
				eventParticipantsArrList.add(eventParticipant);
			}
			conn.close();
			return eventParticipantsArrList;
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
	public EventParticipants retrieveEventParticipant(int eventID) {
		String sql = "SELECT * FROM event_participants WHERE eventID = " + eventID ;
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			EventParticipants eventParticipant = new EventParticipants();
			if (rs.next()) {
				eventParticipant.setEventID(rs.getInt("eventID"));
				eventParticipant.setUserNRIC(rs.getString("userNRIC"));
				eventParticipant.setDateTimeJoined(rs.getTimestamp("dateTimeJoined"));
				eventParticipant.setCheckIn(rs.getInt("checkIn"));
			} else {
				return null;
			}
			conn.close();
			return eventParticipant;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to retrieve a event based on user's nric
	 * @param userNRIC
	 * @return Event
	 */
	public EventParticipants retrieveEventParticipant(String userNRIC) {
		String sql = "SELECT * FROM event_participants WHERE userNRIC = '" + userNRIC + "'";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			EventParticipants eventParticipant = new EventParticipants();
			if (rs.next()) {
				eventParticipant.setEventID(rs.getInt("eventID"));
				eventParticipant.setUserNRIC(rs.getString("userNRIC"));
				eventParticipant.setDateTimeJoined(rs.getTimestamp("dateTimeJoined"));
				eventParticipant.setCheckIn(rs.getInt("checkIn"));
			} else {
				return null;
			}
			conn.close();
			return eventParticipant;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to retrieve a event based on user's nric and event id
	 * @param eventID
	 * @param userNRIC
	 * @return EventParticipants
	 */
	public EventParticipants retrieveEventParticipant(int eventID, String userNRIC) {
		String sql = "SELECT * FROM event_participants WHERE userNRIC = '" + userNRIC + "' AND eventID = " + eventID;
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			EventParticipants eventParticipant = new EventParticipants();
			if (rs.next()) {
				eventParticipant.setEventID(rs.getInt("eventID"));
				eventParticipant.setUserNRIC(rs.getString("userNRIC"));
				eventParticipant.setDateTimeJoined(rs.getTimestamp("dateTimeJoined"));
				eventParticipant.setCheckIn(rs.getInt("checkIn"));
			} else {
				return null;
			}
			conn.close();
			return eventParticipant;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to edit event participant into the database
	 * @param eventParticipant
	 * @return boolean
	 */
	public boolean editEventParticipant(EventParticipants eventParticipant) {
		String sql = "UPDATE event_participants ";
		sql += "SET dateTimeJoined = ? , checkIn = ? WHERE eventID = ? AND userNRIC = ? ";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);

			Timestamp timestampJoined = new Timestamp(eventParticipant.getDateTimeJoined().getTime());
			ps.setTimestamp(1, timestampJoined);
			ps.setInt(2, eventParticipant.getCheckIn());
			ps.setInt(3, eventParticipant.getEventID());
			ps.setString(4, eventParticipant.getUserNRIC());
			
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
	 * This method is to delete event participant in the database
	 * @param eventID
	 * @param userNRIC
	 * @return boolean
	 */
	public boolean deleteEventParticipants(int eventID, String userNRIC) {
		String sql = "DELETE FROM event_participants WHERE eventID = " + eventID + " AND userNRIC = '" + userNRIC + "'";
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
	
	public static void main(String args[]){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,30);
		calendar.set(Calendar.MONTH,6);
		calendar.set(Calendar.YEAR,2014);
		calendar.set(Calendar.HOUR_OF_DAY,9);
		calendar.set(Calendar.MINUTE,10);
		calendar.set(Calendar.SECOND, 00);
		
		//EventParticipants e = new EventParticipants(7,"S9523803C",calendar.getTime(),1);
		//createEventParticipant(e);
		
		//System.out.println(retrieveAllEventParticipants().get(0).getUserNRIC());
		//System.out.println(retrieveEventParticipant(7).getUserNRIC());
		//System.out.println(retrieveEventParticipant("S9523803C").getEventID());
		//editEventParticipant(e);
		//deleteEventParticipants(7,"S9523803C");
	}
}
