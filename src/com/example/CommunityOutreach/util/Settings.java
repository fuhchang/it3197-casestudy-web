package com.example.CommunityOutreach.util;

import java.text.SimpleDateFormat;

public interface Settings {
	//Remote Credentials
	//final static String DB_USER_NAME = "adminSBD83lE";
	//final static String DB_PASSWORD = "UIbVeqzmKeIZ";
	
	//Local Database
	final static String DB_USER_NAME = "root";
	final static String DB_PASSWORD = "mysql";
	
	final static String DB_IP_ADDRESS = "127.0.0.1";
	final static String DB_PORT = "3306";
	
	//Remote Database
	//final static String DB_DATABASE_NAME = "communityoutreach";
	
	//Local Database
	final static String DB_DATABASE_NAME = "community_outreach";
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm a");
	SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy h:mm:ss a");
	//E.g. Jul 27, 2014 9:00:00 AM
	SimpleDateFormat sqlDateTimeFormatter = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
	SimpleDateFormat webSqlDateTimeFormatter = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
	SimpleDateFormat displayFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

	int pointsForCreatingEvent = 600;
	int pointsForJoiningEvent = 300;
}

