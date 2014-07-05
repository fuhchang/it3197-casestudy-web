package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Hobby;

public class HobbyManager {
	private DBController dbController = new DBController();
	
	public boolean createHobby(Hobby hobby){
		int active = 1;
		boolean result = false;
		String sql = "INSERT INTO hobbies_group ";
		sql += "VALUES(?,?,?,?,?,?)";
		try {
			Connection conn = dbController.getConnection();
			hobby.setGrpID(0);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, hobby.getGrpID());
			ps.setString(2, hobby.getGrpName());
			ps.setString(3, hobby.getCategory());
			ps.setString(4, hobby.getLocation());
			ps.setString(5, hobby.getDesc());
			ps.setInt(6, active);
			
			ps.executeUpdate();
			
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
}
