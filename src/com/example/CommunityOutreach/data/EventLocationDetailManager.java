package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.EventLocationDetail;

public class EventLocationDetailManager {
	private DBController dbController = new DBController();
	
	/**
	 * This method is to create event location details into database
	 * 
	 * @param eventLocationDetails
	 * @return boolean
	 */
	public boolean createEventLocationDetails(EventLocationDetail eventLocationDetails) {
		String sql = "INSERT INTO event_location_details ";
		sql += "VALUES( ? , ? , ? , ? , ? , ? , ? )";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, eventLocationDetails.getEventLocationID());
			ps.setInt(2, eventLocationDetails.getEventID());
			ps.setString(3, eventLocationDetails.getEventLocationName());
			ps.setString(4, eventLocationDetails.getEventLocationAddress());
			ps.setString(5, eventLocationDetails.getEventLocationHyperLink());
			ps.setDouble(6, eventLocationDetails.getEventLocationLat());
			ps.setDouble(7, eventLocationDetails.getEventLocationLng());
			
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
	 * This method is to retrieve a event location details based on eventID
	 * @param eventID
	 * @return EventLocationDetail
	 */
	public EventLocationDetail retrieveEventLocationDetails(int eventID) {
		String sql = "SELECT * FROM event_location_details WHERE eventID = " + eventID ;
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			EventLocationDetail eventLocationDetails = new EventLocationDetail();
			if (rs.next()) {
				eventLocationDetails.setEventLocationID(rs.getInt("eventLocationID"));
				eventLocationDetails.setEventID(rs.getInt("eventID"));
				eventLocationDetails.setEventLocationName(rs.getString("eventLocationName"));
				eventLocationDetails.setEventLocationAddress(rs.getString("eventLocationAddress"));
				eventLocationDetails.setEventLocationHyperLink(rs.getString("eventLocationHyperLink"));
				eventLocationDetails.setEventLocationLat(rs.getDouble("eventLocationLat"));
				eventLocationDetails.setEventLocationLng(rs.getDouble("eventLocationLng"));
			} else {
				return null;
			}
			conn.close();
			return eventLocationDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to edit event location details into the database
	 * @param event
	 * @return boolean
	 */
	public boolean editEventLocationDetails(EventLocationDetail eventLocationDetails) {
		String sql = "UPDATE event_location_details ";
		sql += "SET eventLocationName = ? , eventLocationAddress = ? , eventLocationHyperLink = ? " +
				" eventLocationLat = ? , eventLocationLng = ? WHERE eventID = ? ";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, eventLocationDetails.getEventLocationName());
			ps.setString(2, eventLocationDetails.getEventLocationAddress());
			ps.setString(3, eventLocationDetails.getEventLocationHyperLink());
			ps.setDouble(4, eventLocationDetails.getEventLocationLat());
			ps.setDouble(6, eventLocationDetails.getEventLocationLng());
			ps.setInt(7, eventLocationDetails.getEventID());
			
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
}
