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
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.HobbyPost;

public class PostManager {
	private DBController dbController = new DBController();
	
	public boolean createPost(HobbyPost post){
		int active = 1;
		boolean result = false;
		String sql = "INSERT INTO post ";
		sql += "VALUES(?,?,?,?,?,?,?,?)";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ps.setInt(1, 0);
			ps.setString(2,	dateFormat.format(date));
			ps.setString(3, post.getContent());
			ps.setDouble(4, post.getLat());
			ps.setDouble(5, post.getLng());
			ps.setInt(6, post.getGrpID());
			ps.setString(7, post.getNric());
			ps.setString(8, post.getPostTitle());
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
	
	public ArrayList<HobbyPost> retrievePost(int id){
		ArrayList<HobbyPost> postList = new ArrayList<HobbyPost>();
		String sql = "select * from post where groupID = ? ORDER BY postID DESC";
		Connection conn;
		try {
			conn = dbController.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				HobbyPost post = new HobbyPost();
				post.setPostID(rs.getInt("postID"));
				post.setGrpID(rs.getInt("groupID"));
				post.setLat(rs.getDouble("Lat"));
				post.setLng(rs.getDouble("Lng"));
				post.setContent(rs.getString("content"));
				post.setDatetime(rs.getDate("dateTime"));
				post.setNric(rs.getString("userNRIC"));
				post.setPostTitle(rs.getString("postTitle"));
				postList.add(post);
			}
		} catch (IllegalAccessException | InstantiationException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return postList;
		
	}
	
	public boolean delPost(int id){
		String sql = "DELETE FROM post where postID = ?";
		Connection conn;
		boolean result = false;
		
		try {
			conn = dbController.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
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
	
	public boolean updatePost(HobbyPost post){
		boolean result = false;
		String sql = "UPDATE post set dateTime= ?, postTitle = ?, content =? ,Lat =?, Lng =? WHERE postID=?";
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ps.setString(1,	dateFormat.format(date));
			ps.setString(2, post.getPostTitle());
			ps.setString(3, post.getContent());
			ps.setDouble(4, post.getLat());
			ps.setDouble(5, post.getLng());
			ps.setInt(6, post.getPostID());
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
	
}
