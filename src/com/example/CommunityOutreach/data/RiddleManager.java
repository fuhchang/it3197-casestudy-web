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
import com.example.CommunityOutreach.model.RiddleUserAnswered;
import com.example.CommunityOutreach.model.User;

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
		sql += "VALUES(?, ?, ?, ?)";
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
	
	public boolean insertChoice(int riddleID, int riddleAnswerID, String userNRIC) {
		boolean result = false;
		
		String sql = "INSERT INTO riddle_user_answered ";
		sql += "VALUES(?, ?, ?, ?, ?)";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, 0);
			ps.setInt(2, riddleID);
			ps.setInt(3, riddleAnswerID);
			ps.setString(4, userNRIC);
			ps.setString(5, "NULL");
			
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
	
	public boolean insertRate(int riddleID, String userNRIC, String rate) {
		boolean result = false;

		String sql = "UPDATE riddle_user_answered set answeredRate = ? WHERE (riddleID = ? AND userNRIC = ?)";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, rate);
			ps.setInt(2, riddleID);
			ps.setString(3, userNRIC);
			
			ps.executeUpdate();
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
	
	public ArrayList<RiddleUserAnswered> retrieveAllUserAnswered(String userNRIC) {
		ArrayList<RiddleUserAnswered> userAnswerList = new ArrayList<RiddleUserAnswered>();
		
		String sql = "SELECT * FROM riddle_user_answered WHERE userNRIC = ?";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userNRIC);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RiddleUserAnswered userAnswer = new RiddleUserAnswered();
				userAnswer.setRiddleUserAnsweredID(rs.getInt("answeredID"));
				userAnswer.setRiddle(new Riddle(rs.getInt("riddleID")));
				userAnswer.setRiddleAnswer(new RiddleAnswer(rs.getInt("riddleAnswerID")));
				userAnswer.setUser(new User(userNRIC));
				userAnswer.setAnsweredRate(rs.getString("answeredRate"));
				userAnswerList.add(userAnswer);
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
		return userAnswerList;
	}
	
	public ArrayList<RiddleUserAnswered> retrieveAllAnswered(int riddleID) {
		ArrayList<RiddleUserAnswered> userAnswerList = new ArrayList<RiddleUserAnswered>();
		
		String sql = "SELECT * FROM riddle_user_answered WHERE riddleID = ?";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, riddleID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RiddleUserAnswered userAnswer = new RiddleUserAnswered();
				userAnswer.setRiddleUserAnsweredID(rs.getInt("answeredID"));
				userAnswer.setRiddle(new Riddle(riddleID));
				userAnswer.setRiddleAnswer(new RiddleAnswer(rs.getInt("riddleAnswerID")));
				userAnswer.setUser(new User(rs.getString("userNRIC")));
				userAnswer.setAnsweredRate(rs.getString("answeredRate"));
				userAnswerList.add(userAnswer);
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
		return userAnswerList;
	}
	
	public Riddle retrieveRiddle(int riddleID) {
		Riddle riddle = new Riddle();
		
		String sql = "SELECT * FROM riddle WHERE riddleID = ?";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, riddleID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				riddle.setRiddleID(riddleID);
				riddle.setRiddleTitle(rs.getString("riddleTitle"));
				riddle.setRiddleContent(rs.getString("riddleContent"));
				riddle.setRiddleStatus(rs.getString("riddleStatus"));
				riddle.setRiddlePoint(rs.getInt("riddlePoint"));
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
		
		return riddle;
	}
	
	public ArrayList<RiddleAnswer> retrieveRiddleAnswers(int riddleID) {
		ArrayList<RiddleAnswer> riddleAnsList = new ArrayList<RiddleAnswer>();
		
		String sql = "SELECT * FROM riddle_answer WHERE riddleID = ?";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, riddleID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RiddleAnswer riddleAns = new RiddleAnswer();
				riddleAns.setRiddleAnswerID(rs.getInt("riddleAnswerID"));
				riddleAns.setRiddle(new Riddle(riddleID));
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
	
	public RiddleAnswer retrieveRiddleAnswer(int riddleAnswerID) {
		RiddleAnswer riddleAns = new RiddleAnswer();
		
		String sql = "SELECT * FROM riddle_answer WHERE riddleAnswerID = ?";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, riddleAnswerID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				riddleAns.setRiddleAnswerID(riddleAnswerID);
				riddleAns.setRiddle(new Riddle(rs.getInt("riddleID")));
				riddleAns.setRiddleAnswer(rs.getString("riddleAnswer"));
				riddleAns.setRiddleAnswerStatus(rs.getString("riddleAnswerStatus"));
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
		
		return riddleAns;
	}

	public boolean updateRiddle(Riddle riddle){
		String sql = "UPDATE riddle set userNRIC = ?, riddleTitle = ?, riddleContent = ?, riddleStatus = ?, riddlePoint = ? WHERE riddleID = ?";
		boolean result = false;
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, riddle.getUser().getNric());
			ps.setString(2, riddle.getRiddleTitle());
			ps.setString(3, riddle.getRiddleContent());
			ps.setString(4, riddle.getRiddleStatus());
			ps.setInt(5, riddle.getRiddlePoint());
			ps.setInt(6, riddle.getRiddleID());
			
			ps.executeUpdate();
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
	
	public boolean updateRiddleAns(Riddle riddle, RiddleAnswer answer) {
		boolean result = false;
		
		String sql = "UPDATE riddle_answer set riddleID = ?, riddleAnswer = ?, riddleAnswerStatus = ? WHERE riddleAnswerID = ?";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, riddle.getRiddleID());
			ps.setString(2, answer.getRiddleAnswer());
			ps.setString(3, answer.getRiddleAnswerStatus());
			ps.setInt(4, answer.getRiddleAnswerID());
			
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

	public boolean deleteRiddle(int riddleID){
		boolean result = false;
		
		String sql = "DELETE FROM riddle WHERE riddleID = ?";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, riddleID);
			
			ps.executeUpdate();
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

	public boolean deleteRiddleAns(int riddleID){
		boolean result = false;
		
		String sql = "DELETE FROM riddle_answer WHERE riddleID = ?";
		
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, riddleID);
			
			ps.executeUpdate();
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
}
