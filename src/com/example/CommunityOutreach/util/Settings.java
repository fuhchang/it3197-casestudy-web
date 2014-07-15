package com.example.CommunityOutreach.util;

import java.text.SimpleDateFormat;

public interface Settings {
	final static String DB_USER_NAME = "root";
	final static String DB_PASSWORD = "mysql";
	final static String DB_IP_ADDRESS = "localhost";
	final static String DB_PORT = "3306";
	final static String DB_DATABASE_NAME = "community_outreach";
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm a");
	SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy h:mm:ss a");
	//E.g. Jul 27, 2014 9:00:00 AM
	SimpleDateFormat sqlDateTimeFormatter = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
	SimpleDateFormat webSqlDateTimeFormatter = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
}

