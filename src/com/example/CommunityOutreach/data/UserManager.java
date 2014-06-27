package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.User;

/**
 * This is the data access manager for User
 * @author Lee Zhuo Xun
 *
 */
public class UserManager {
	private DBController dbController = new DBController();

	/**
	 * This method is to create user into database
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean createUser(User user) {
		String sql = "INSERT INTO user ";
		sql += "VALUES( ? , ? , ? , ? , ? , ? , ? , ?)";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getNric());
			ps.setString(2, user.getName());
			ps.setString(3, user.getType());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getContactNo());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getEmail());
			ps.setInt(8, user.getActive());

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
	 * This method is to retrieve all users from the database.
	 * 
	 * @return ArrayList<User>
	 */
	public ArrayList<User> retrieveAllUsers() {
		String sql = "SELECT * FROM user";
		ArrayList<User> userArrList = new ArrayList<User>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setNric(rs.getString("nric"));
				user.setName(rs.getString("name"));
				user.setType(rs.getString("type"));
				user.setPassword(rs.getString("password"));
				user.setContactNo(rs.getString("contactNo"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getInt("active"));
				userArrList.add(user);
			}
			conn.close();
			return userArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to retrieve all active users from the database.
	 * 
	 * @return ArrayList<User>
	 */
	public ArrayList<User> retrieveAllActiveUsers() {
		String sql = "SELECT * FROM user WHERE active = 1";
		ArrayList<User> userArrList = new ArrayList<User>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setNric(rs.getString("nric"));
				user.setName(rs.getString("name"));
				user.setType(rs.getString("type"));
				user.setPassword(rs.getString("password"));
				user.setContactNo(rs.getString("contactNo"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getInt("active"));
				userArrList.add(user);
			}
			conn.close();
			return userArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is to retrieve a user based on user's nric
	 * @param nric
	 * @return User
	 */
	public User retrieveUser(String nric) {
		String sql = "SELECT * FROM user WHERE nric = '" + nric + "'";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			if (rs.next()) {
				user.setNric(rs.getString("nric"));
				user.setName(rs.getString("name"));
				user.setType(rs.getString("type"));
				user.setPassword(rs.getString("password"));
				user.setContactNo(rs.getString("contactNo"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getInt("active"));
			} else {
				return null;
			}
			conn.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to edit user into the database
	 * @param user
	 * @return boolean
	 */
	public boolean editUser(User user) {
		String sql = "UPDATE user ";
		sql += "SET name = ? , password = ? , contactNo = ? , address = ? , email = ? WHERE nric = ? ";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getContactNo());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getNric());
			
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
	 * This method is to obsolete user
	 * @param userID
	 * @return boolean
	 */
	public boolean obsoleteUser(String nric) {
		String sql = "UPDATE user SET active = 0 WHERE nric = '" + nric + "'";
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
