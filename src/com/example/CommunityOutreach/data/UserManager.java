package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.User;
import com.example.CommunityOutreach.model.UserLocation;

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
		sql += "VALUES( ? , ? , ? , ? , ? , ? , ? , ?, ?)";
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
			ps.setInt(9, user.getPoints());
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
				user.setPoints(rs.getInt("points"));
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
				user.setPoints(rs.getInt("points"));
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
	 * This method is to retrieve a user based on user's nric and password
	 * @param nric
	 * @return User
	 */
	public User retrieveUser(String nric, String password) {
		String sql = "SELECT * FROM user WHERE nric = ? AND password = ?";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, nric);
			ps.setString(2, password);
			
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
				user.setPoints(rs.getInt("points"));
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
	
	public User retrieveUserByName(String name) {
		String sql = "SELECT * FROM user WHERE name=?";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, name);
			
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
				user.setPoints(rs.getInt("points"));
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
				user.setPoints(rs.getInt("points"));
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
	
	public boolean deleteUser(String nric) {
		String sql = "DELETE FROM user WHERE nric = '" + nric + "'";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.executeUpdate();
			
			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/* SHERRY */
	public boolean updatePoints(String nric, int points) {
		String sql = "UPDATE user set points = ? WHERE nric = ?";
		boolean result = false;
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, points);
			ps.setString(2, nric);
			
			System.out.println(ps);
			ps.executeUpdate();
			
			conn.setAutoCommit(true);
			conn.close();
			result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertUserLocation(String userNRIC, double lat, double lng) {
		boolean result = false;
		
		String sql = "INSERT INTO user_location ";
		sql += "VALUES(?, ?, ?, ?, ?)";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, 0);
			ps.setString(2, userNRIC);
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ps.setString(3, df.format(date));
			ps.setDouble(4, lat);
			ps.setDouble(5, lng);
			
			ps.executeUpdate();
			result = true;
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<UserLocation> retrieveAllUserLocation() {
		ArrayList<UserLocation> locationList = new ArrayList<UserLocation>();
		
		String sql = "SELECT * FROM user_location";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				UserLocation userLocation = new UserLocation();
				userLocation.setLocationID(rs.getInt("locationID"));
				userLocation.setUser(new User(rs.getString("userNRIC")));
				userLocation.setDateTime(rs.getTimestamp("dateTime"));
				userLocation.setLat(rs.getDouble("lat"));
				userLocation.setLng(rs.getDouble("lng"));
				locationList.add(userLocation);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locationList;
	}
}
