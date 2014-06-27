package com.example.CommunityOutreach.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.CommunityOutreach.util.Settings;

/**
 * This is the controller among the database
 * @author Lee Zhuo Xun
 *
 */
public class DBController implements Settings{
	Connection con;
	
	/**
	 * This method is to get connection between data access and database
	 * @return Connection
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws IllegalAccessException,InstantiationException, ClassNotFoundException, SQLException {
		// Instantiate a new instance of the driver
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		String url = "jdbc:mysql://"+DB_IP_ADDRESS+":"+DB_PORT+"/"+DB_DATABASE_NAME;
		try { 
			//Establish connection between the database and JDBC
			con =  DriverManager.getConnection(url,DB_USER_NAME,DB_PASSWORD);
			System.out.println("Successfully connected to " + url+ "."); 
		} 
		catch (java.sql.SQLException e) { 
			System.out.println("Connection failed ->"+ url); 
			System.out.println(e);
			e.printStackTrace();
		}
		return con; 
	}
}
