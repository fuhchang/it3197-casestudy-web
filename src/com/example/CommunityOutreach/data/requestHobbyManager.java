package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.RequestHobby;

public class requestHobbyManager {
	private DBController dbController = new DBController();
	
	public boolean createRequest(RequestHobby rh){
		String sql = "INSERT INTO request_hobby ";
		sql += "VALUES(?,?,?,?,?,?)";
		boolean result = false;
		
		try{
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setInt(2, 1);
			ps.setInt(3, rh.getHobbyID());
			ps.setString(4, rh.getRequestStatus());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			ps.setString(5, dateFormat.format(rh.getRequestDateStart()));
			ps.setString(6, dateFormat.format(rh.getRequestDateEnd()));
			ps.executeUpdate();
			result = true;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<RequestHobby> retrieveAllRequest(int id){
		ArrayList<RequestHobby> reqList = new ArrayList<RequestHobby>();
		String sql = "SELECT * FROM request_hobby where hobbyID = ?";
		
		Connection conn;
		try {
			conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RequestHobby rh = new RequestHobby();
				rh.setHobbyID(id);
				rh.setRequestID(rs.getInt("requestID"));
				rh.setRequestStatus(rs.getString("requestStatus"));
				rh.setRequestDateStart(rs.getDate("requestDateStart"));
				rh.setRequestDateEnd(rs.getDate("requestDateEnd"));
				reqList.add(rh);
			}
		} catch (IllegalAccessException | InstantiationException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reqList;
	}
	
	public boolean AccpetRequest(int id){
		boolean result = false;
		String sql = "UPDATE request_hobby set requestStatus=? WHERE requestID=?";
		Connection conn;
		try {
			conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "accept");
			ps.setInt(2, id);
			ps.executeUpdate();
			result = true;
		} catch (IllegalAccessException | InstantiationException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
