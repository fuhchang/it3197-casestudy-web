package com.example.CommunityOutreach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.CommunityOutreach.controller.DBController;
import com.example.CommunityOutreach.model.Article;
import com.example.CommunityOutreach.model.User;

/**
 * This is the data access manager for Article
 * @author Lee Zhuo Xun
 *
 */
public class ArticleManager {
	private static DBController dbController = new DBController();
	
	/**
	 * This method is to create article into database
	 * 
	 * @param article
	 * @return boolean
	 */
	public boolean createArticle(Article article) {
		String sql = "INSERT INTO articles ";
		sql += "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? )";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, article.getArticleID());
			ps.setString(2, article.getTitle());
			ps.setString(3, article.getContent());
			Timestamp timestamp = new Timestamp(article.getDateTime().getTime());
			ps.setTimestamp(4, timestamp);
			ps.setString(5, article.getCategory());
			ps.setString(6, article.getLocation());
			ps.setString(7, article.getUserNRIC());
			ps.setInt(8, article.getActive());
			ps.setString(9, article.getApproved());
			ps.setDouble(10, article.getDbLat());
			ps.setDouble(11, article.getDbLon());

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
	 * This method is to retrieve all articles from the database.
	 * 
	 * @return ArrayList<Article>
	 */
	public ArrayList<Article> retrieveAllArticles() {
		String sql = "SELECT * FROM articles";
		ArrayList<Article> articlesArrList = new ArrayList<Article>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setArticleID(rs.getInt("articleID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				
				Date articleDate = rs.getTimestamp("dateTime");
				DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
				String articleSubmittedDate = df.format(articleDate);
				// Print what date is today!
				System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("long"));
				articlesArrList.add(article);
					
				UserManager um = new UserManager();
				User UserDetail = um.retrieveUser(rs.getString("userNRIC"));
				article.setArticleUser(UserDetail.getName());		
			}
			conn.close();
			return articlesArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/***Display Approved Article with category "News Around The Neighbourhood***/
	public ArrayList<Article> retrieveAllApprovedArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Approved' AND category = 'News Around The Neighbourhood'";
		ArrayList<Article> articlesArrList = new ArrayList<Article>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setArticleID(rs.getInt("articleID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				
				Date articleDate = rs.getTimestamp("dateTime");
				DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
				String articleSubmittedDate = df.format(articleDate);
				// Print what date is today!
				System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("long"));
				articlesArrList.add(article);
					
				UserManager um = new UserManager();
				User UserDetail = um.retrieveUser(rs.getString("userNRIC"));
				article.setArticleUser(UserDetail.getName());		
			}
			conn.close();
			return articlesArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	/***Display Pending Article with category "Feedback***/
	public ArrayList<Article> retrieveAllPendingFeedbackArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category = 'Feedback'";
		ArrayList<Article> articlesArrList = new ArrayList<Article>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setArticleID(rs.getInt("articleID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				
				Date articleDate = rs.getTimestamp("dateTime");
				DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
				String articleSubmittedDate = df.format(articleDate);
				// Print what date is today!
				System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("long"));
				articlesArrList.add(article);
					
				UserManager um = new UserManager();
				User UserDetail = um.retrieveUser(rs.getString("userNRIC"));
				article.setArticleUser(UserDetail.getName());		
			}
			conn.close();
			return articlesArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/***Display Pending Article with category "Location Usage***/
	public ArrayList<Article> retrieveAllPendingLocationUsageArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category = 'Location Usage'";
		ArrayList<Article> articlesArrList = new ArrayList<Article>();
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setArticleID(rs.getInt("articleID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				
				Date articleDate = rs.getTimestamp("dateTime");
				DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
				String articleSubmittedDate = df.format(articleDate);
				// Print what date is today!
				System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("long"));
				articlesArrList.add(article);
					
				UserManager um = new UserManager();
				User UserDetail = um.retrieveUser(rs.getString("userNRIC"));
				article.setArticleUser(UserDetail.getName());		
			}
			conn.close();
			return articlesArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	/**
	 * This method is to retrieve a article based on articleID
	 * @param articleID
	 * @return Article
	 */
	public Article retrieveArticle(int articleID) {
		String sql = "SELECT * FROM articles WHERE articleID = " + articleID ;
		try {
			Connection conn = dbController.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			Article article = new Article();
			if (rs.next()) {
				article.setArticleID(rs.getInt("articleID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setDateTime(rs.getTimestamp("dateTime"));
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("long"));
				
				
				UserManager um = new UserManager();
				User UserDetail = um.retrieveUser(rs.getString("userNRIC"));
				article.setArticleUser(UserDetail.getName());
				
			} else {
				return null;
			}
			conn.close();
			return article;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is to edit article into the database
	 * @param article
	 * @return boolean
	 */
	public boolean editArticle(Article article) {
		String sql = "UPDATE articles ";
		sql += "SET title = ? , content = ? , dateTime = ? , category = ? , location = ? ," +
				" userNRIC = ? , active = ? , status = ? , lat = ?, long = ? WHERE articleID = ? ";
		try {
			Connection conn = dbController.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, article.getTitle());
			ps.setString(2, article.getContent());
			Timestamp timestamp = new Timestamp(article.getDateTime().getTime());
			ps.setTimestamp(3, timestamp);
			ps.setString(4, article.getCategory());
			ps.setString(5, article.getLocation());
			ps.setString(6, article.getUserNRIC());
			ps.setInt(7, article.getActive());
			ps.setString(8, article.getApproved());
			ps.setDouble(9, article.getDbLat());
			ps.setDouble(10, article.getDbLon());
			ps.setInt(11, article.getArticleID());
			
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
	 * This method is to obsolete article
	 * @param articleID
	 * @return boolean
	 */
	public boolean obsoleteArticle(int articleID) {
		String sql = "UPDATE articles SET status = 'Inactive' WHERE articleID = " + articleID;
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
	
	public static void main(String args[]){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,30);
		calendar.set(Calendar.MONTH,6);
		calendar.set(Calendar.YEAR,2014);
		calendar.set(Calendar.HOUR_OF_DAY,9);
		calendar.set(Calendar.MINUTE,20);
		calendar.set(Calendar.SECOND, 00);
		//Article article = new Article(1,"Xy","Xy",calendar.getTime(),"X","X","S9523803C",1,0);
		/*createArticle(article);*/
		//System.out.println(editArticle(article));
		//obsoleteArticle(1);
	}
}
