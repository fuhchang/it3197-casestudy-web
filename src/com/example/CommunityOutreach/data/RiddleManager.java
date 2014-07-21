package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Riddle;
import com.example.CommunityOutreach.model.RiddleAnswer;

public class RiddleManager {
	private DBController dbController = new DBController();
	ResultSet rs = null;
	int generatedID;
	
	public boolean createRiddle(Riddle riddle) {
		boolean result = false;
		
		String sql = "INSERT INTO riddle ";
		sql += "VALUES(?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, 0);
			ps.setString(2, riddle.getUser().getNric());
			ps.setString(3, riddle.getRiddleTitle());
			ps.setString(4, riddle.getRiddleContent());
			ps.setString(5, riddle.getRiddleStatus());
			ps.setInt(6, riddle.getRiddlePoint());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while(rs.next()){
				generatedID += rs.getInt(1);
			}
			rs.close();
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
	
	public boolean createRiddleAns(RiddleAnswer answer) {
		boolean result = false;
		
		String sql = "INSERT INTO riddle_answer ";
		sql += "VALUES(?, ?, ?, ?, ?)";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, 0);
			ps.setInt(2, generatedID);
			ps.setString(3, answer.getRiddleAnswer());
			ps.setString(4, answer.getRiddleAnswerStatus());
			
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
	
	public ArrayList<Riddle> retrieveAllRiddle() {
		ArrayList<Riddle> riddleList = new ArrayList<Riddle>();
		
		String sql = "SELECT * FROM riddle";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Riddle riddle = new Riddle();
				riddle.setRiddleID(rs.getInt("riddleID"));
				UserManager userManager = new UserManager();
				riddle.setUser(userManager.retrieveUser(rs.getString("userNRIC")));
				riddle.setRiddleTitle(rs.getString("riddleTitle"));
				riddle.setRiddleContent(rs.getString("riddleContent"));
				riddle.setRiddleStatus(rs.getString("riddleStatus"));
				riddle.setRiddlePoint(rs.getInt("riddlePoint"));
				riddleList.add(riddle);
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
		return riddleList;
	}
	
	public ArrayList<RiddleAnswer> retrieveAllRiddleAnswers() {
		ArrayList<RiddleAnswer> riddleAnsList = new ArrayList<RiddleAnswer>();
		
		String sql = "SELECT * FROM riddle_answer";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RiddleAnswer riddleAns = new RiddleAnswer();
				riddleAns.setRiddleAnswerID(rs.getInt("riddleAnswerID"));
				riddleAns.setRiddle(new Riddle(rs.getInt("riddleID")));
				riddleAns.setRiddleAnswer(rs.getString("riddleAnswer"));
				riddleAns.setRiddleAnswerStatus(rs.getString("riddleAnswerStatus"));
				riddleAnsList.add(riddleAns);
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
		return riddleAnsList;
	}
	
	public RiddleAnswer[] retrieveRiddleAnswers(int riddleID) {
		RiddleAnswer[] riddleAnswers = new RiddleAnswer[4];
		int i = 0;
		
		String sql = "SELECT * FROM riddle_answer WHERE riddleID = ?";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, riddleID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RiddleAnswer riddleAns = new RiddleAnswer();
				riddleAns.setRiddleAnswerID(rs.getInt("riddleAnswerID"));
				riddleAns.setRiddleAnswer(rs.getString("riddleAnswer"));
				riddleAns.setRiddleAnswerStatus(rs.getString("riddleAnswerStatus"));
				riddleAnswers[i] = riddleAns;
				i++;
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
		return riddleAnswers;
	}
}
