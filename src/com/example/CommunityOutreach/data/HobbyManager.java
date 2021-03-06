package com.example.CommunityOutreach.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.User;

public class HobbyManager {
	private DBController dbController = new DBController();
	
	public boolean createHobby(Hobby hobby, User user){
		int active = 1;
		boolean result = false;
		String sql = "INSERT INTO hobbies_group ";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection conn = dbController.getConnection();
			hobby.setGrpID(0);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, hobby.getGrpID());
			ps.setString(2, hobby.getGrpName());
			ps.setString(3, hobby.getCategory());
			ps.setDouble(4, hobby.getLat());
			ps.setDouble(5, hobby.getLng());
			ps.setString(6, hobby.getPhoto());
			ps.setString(7, hobby.getGrpDesc());
			ps.setString(8, user.getNric());
			ps.setInt(9, 1);
			ps.setString(10, hobby.getHobbyFBPostID());
			ps.executeUpdate();
			result=true;
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
	
	public ArrayList<Hobby> retrieveAllHobby(){
		ArrayList<Hobby> hobbyList = new ArrayList<Hobby>();
		String sql = "SELECT * FROM hobbies_group ORDER BY groupID";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Hobby hobby = new Hobby();
				hobby.setGrpID(rs.getInt("groupID"));
				hobby.setGrpName(rs.getString("groupName"));
				hobby.setCategory(rs.getString("category"));
				hobby.setGrpDesc(rs.getString("description"));
				hobby.setLat(rs.getDouble("Lat"));
				hobby.setLng(rs.getDouble("Lng"));
				hobby.setPhoto(rs.getString("groupimage"));
				hobby.setAdminNric(rs.getString("adminNric"));
				hobby.setActive(rs.getInt("active"));
				hobby.setHobbyFBPostID(rs.getString("hobbyFBPostID"));
				hobbyList.add(hobby);
			}
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
			// TODO Auto-generated catchAl block
			e.printStackTrace();
		}
		return hobbyList;
	}
	
	
	
	public Hobby retrieveHobby(int id){
		String sql = "SELECT * FROM hobbies_group WHERE groupID = ? ORDER BY groupID";
		
		Hobby hobby = new Hobby();
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				hobby.setGrpID(rs.getInt("groupID"));
				hobby.setGrpName(rs.getString("groupName"));
				hobby.setCategory(rs.getString("category"));
				hobby.setGrpDesc(rs.getString("description"));
				hobby.setLat(rs.getDouble("Lat"));
				hobby.setLng(rs.getDouble("Lng"));
				hobby.setAdminNric(rs.getString("adminNric"));
				hobby.setActive(rs.getInt("active"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hobby;
	}
	public boolean updateHobby(Hobby hobby){
		String sql = "UPDATE hobbies_group set groupName = ?, category=?,Lat=?,Lng=?,description=?, active=? where groupID =?";
		boolean result = false;
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hobby.getGrpName());
			ps.setString(2,hobby.getCategory());
			ps.setDouble(3, hobby.getLat());
			ps.setDouble(4, hobby.getLng());
			ps.setString(5, hobby.getGrpDesc());
			ps.setInt(6, 1);
			ps.setInt(7, hobby.getGrpID());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int getLastHobbyID(String nric){
		String sql = "SELECT * FROM hobbies_group WHERE adminNric =  ? ORDER BY groupID DESC";
		Connection conn;
		ArrayList<Hobby> hobbyList = new ArrayList<Hobby>();
		try {
			conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nric);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Hobby hobby = new Hobby();
				hobby.setGrpID(rs.getInt("groupID"));
				hobby.setGrpName(rs.getString("groupName"));
				hobby.setCategory(rs.getString("category"));
				hobby.setGrpDesc(rs.getString("description"));
				hobby.setLat(rs.getDouble("Lat"));
				hobby.setLng(rs.getDouble("Lng"));
				hobby.setAdminNric(rs.getString("adminNric"));
				hobby.setActive(rs.getInt("active"));
				hobbyList.add(hobby);
				
			}
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
		return hobbyList.get(0).getGrpID();
		
	}
	
	public ArrayList<Hobby> retrieveAllHobbyByCategory(String category){
		String sql = "SELECT * FROM hobbies_group WHERE category =  ? ORDER BY groupID DESC";
		Connection conn;
		ArrayList<Hobby> hobbyList = new ArrayList<Hobby>();
		
		try {
			conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Hobby hobby = new Hobby();
				hobby.setGrpID(rs.getInt("groupID"));
				hobby.setGrpName(rs.getString("groupName"));
				hobby.setCategory(rs.getString("category"));
				hobby.setGrpDesc(rs.getString("description"));
				hobby.setLat(rs.getDouble("Lat"));
				hobby.setLng(rs.getDouble("Lng"));
				hobby.setAdminNric(rs.getString("adminNric"));
				hobby.setActive(rs.getInt("active"));
				hobbyList.add(hobby);
			}
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
		return hobbyList;
		
	}
	
	public boolean uploadImage(String path , int id){
		String sql = "UPDATE hobbies_group set groupimage =? where groupID = ?";
		boolean result = false;
		Connection conn;
		try {
			conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, path);
			ps.setInt(2, id);
			ps.executeUpdate();
			result=true;
		} catch (IllegalAccessException | InstantiationException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
}
