package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.HobbyMembers;

public class HobbyMembersManager {
	private DBController dbController = new DBController();
	
	public ArrayList<HobbyMembers> retrieveAllHobbyMember(String nric){
		ArrayList<HobbyMembers> MembersList = new ArrayList<HobbyMembers>();

		String sql = "SELECT * FROM hobbies_group_members WHERE userNRIC = ?";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nric);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				HobbyMembers member = new HobbyMembers();
				member.setGroupID(rs.getInt("groupID"));
				MembersList.add(member);
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
		return MembersList;
	}
	
	public boolean createHobbyMember(HobbyMembers member){
		String sql ="INSERT INTO hobbies_group_members(groupMemberID, groupID, userNRIC, dateTimeJoined,active, MemberRole)";
		sql += "VALUES(?,?,?,?,?,?)";
		boolean result = false;
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setInt(2, member.getGroupID());
			ps.setString(3, member.getUserNRIC());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ps.setString(4, dateFormat.format(date));
			ps.setInt(5, member.getActive());
			ps.setString(6, member.getRole());
			
			ps.execute();
			result = true;
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
}
