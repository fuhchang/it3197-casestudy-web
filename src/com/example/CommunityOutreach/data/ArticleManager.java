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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
	
	/***Display Approved Article with category "News Around The Neighbourhood"***/
	public ArrayList<Article> retrieveAllApprovedArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Approved' AND category = 'News Around The Neighbourhood' ORDER BY dateTime DESC";
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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
	
	
	/***Display Pending Article with category "News Around The Neighbourhood"***/
	public ArrayList<Article> retrieveAllPendingLatestArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category = 'News Around The Neighbourhood' ORDER BY dateTime DESC";
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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
	
	/***Display Confirmed Article with category "Feedback"***/
	public ArrayList<Article> retrieveAllConfirmedFeedbackArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Confirmed' AND category = 'Feedback' ORDER BY dateTime DESC";
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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
	
	
	/***Display Pending Article with category "Feedback"***/
	public ArrayList<Article> retrieveAllPendingFeedbackArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category = 'Feedback' ORDER BY dateTime DESC";
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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
	
	/***Display Pending Article with category "Location Usage"***/
	public ArrayList<Article> retrieveAllPendingLocationUsageArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category = 'Location Usage' ORDER BY dateTime DESC";
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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
	
	
	/***Display Confirmed Article with category "Location Usage"***/
	public ArrayList<Article> retrieveAllConfirmedLocationArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Confirmed' AND category = 'Location Usage' ORDER BY dateTime DESC";
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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
	
	
	
	/***Display Pending Article with category "Feedback" AND "Location Usage"***/
	public ArrayList<Article> retrieveAllPendingOfficerArticles() {
		String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category <> 'News Around The Neighbourhood' ORDER BY dateTime DESC";
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
				//System.out.println("Article Date: " + articleSubmittedDate);
				
				article.setArticleDate(articleSubmittedDate);
				//article.setDateTime();
				article.setCategory(rs.getString("category"));
				article.setLocation(rs.getString("location"));
				article.setUserNRIC(rs.getString("userNRIC"));
				article.setActive(rs.getInt("active"));
				article.setApproved(rs.getString("status"));
				article.setDbLat(rs.getDouble("lat"));
				article.setDbLon(rs.getDouble("lng"));
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
				article.setDbLon(rs.getDouble("lng"));
				
				
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
				" userNRIC = ? , active = ? , status = ? , lat = ?, lng = ? WHERE articleID = ? ";
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
	
	
	public boolean confirmFeedbackArticle(int articleID, String status) {
		String sql = "UPDATE articles SET status = '"+ status +"' WHERE articleID = " + articleID;
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
	
	 public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
		 double earthRadius = 6371; //kilometers
		    double dLat = Math.toRadians(lat2-lat1);
		    double dLng = Math.toRadians(lng2-lng1);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    float dist = (float) (earthRadius * c);

		    return dist;
		    }
	 
	 

		
		
		
		
	/*	double earthRadius = 6371; //kilometers
	    double dLat = Math.toRadians(1.36991135967923-1.3871626);
	    double dLng = Math.toRadians(103.85165950555734-103.89699930000006);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(1.3871626)) * Math.cos(Math.toRadians(1.36991135967923)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (earthRadius * c);
	    System.out.println();
	    System.out.println();
	    System.out.println(dist);
	    System.out.println();
	    System.out.println();
	    //1.3871626, 103.89699930000006
	  	//1.36991135967923, 103.85165950555734
		*/
		
	 
	 /***Display Approved Article with category "News Around The Neighbourhood" within specified distance***/
		public ArrayList<Article> retrieveAllApprovedArticlesWithinDistance(double currentLat, double currentLon, int selectedDist) {
			String sql = "SELECT * FROM articles WHERE status = 'Approved' AND category = 'News Around The Neighbourhood' ORDER BY dateTime DESC";
			ArrayList<Article> articlesArrList = new ArrayList<Article>();
			try {
				Connection conn = dbController.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					
					
					
					double earthRadius = 6371; //kilometers
				    double dLat = Math.toRadians(rs.getDouble("lat")-currentLat);
				    double dLng = Math.toRadians(rs.getDouble("lng")-currentLon);
				    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				               Math.cos(Math.toRadians(currentLat)) * Math.cos(Math.toRadians(rs.getDouble("lat"))) *
				               Math.sin(dLng/2) * Math.sin(dLng/2);
				    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				    double dist = (double) (earthRadius * c);
					
				    double roundedDist = Math.floor(dist * 1000) / 1000.0;
					
				    System.out.println();
				    System.out.println(rs.getString("title"));
				    System.out.println(dist);
				    System.out.println();
				    System.out.println();
					
					
					
					
					Article article = new Article();
					article.setArticleID(rs.getInt("articleID"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					
					Date articleDate = rs.getTimestamp("dateTime");
					DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
					String articleSubmittedDate = df.format(articleDate);
					// Print what date is today!
					//System.out.println("Article Date: " + articleSubmittedDate);
					
					article.setArticleDate(articleSubmittedDate);
					//article.setDateTime();
					article.setCategory(rs.getString("category"));
					article.setLocation(rs.getString("location"));
					article.setUserNRIC(rs.getString("userNRIC"));
					article.setActive(rs.getInt("active"));
					article.setApproved(rs.getString("status"));
					article.setDbLat(rs.getDouble("lat"));
					article.setDbLon(rs.getDouble("lng"));
					
					
					
					if(currentLat == 0 && currentLon==0){
						article.setDist("-");
						articlesArrList.add(article);
					}
					else if(selectedDist==0){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					else if(dist<=selectedDist){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					
					
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
	 
		
		/***Display Approved Article with category "Feedback" AND "Location Usage" within specified distance***/
		public ArrayList<Article> retrieveAllPendingArticlesWithinDistance(double currentLat, double currentLon, int selectedDist) {
			String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category <> 'News Around The Neighbourhood' ORDER BY dateTime DESC";
			ArrayList<Article> articlesArrList = new ArrayList<Article>();
			try {
				Connection conn = dbController.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					
					
					
					double earthRadius = 6371; //kilometers
				    double dLat = Math.toRadians(rs.getDouble("lat")-currentLat);
				    double dLng = Math.toRadians(rs.getDouble("lng")-currentLon);
				    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				               Math.cos(Math.toRadians(currentLat)) * Math.cos(Math.toRadians(rs.getDouble("lat"))) *
				               Math.sin(dLng/2) * Math.sin(dLng/2);
				    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				    double dist = (double) (earthRadius * c);
					
				    double roundedDist = Math.floor(dist * 1000) / 1000.0;
					
				    System.out.println();
				    System.out.println(rs.getString("title"));
				    System.out.println(dist);
				    System.out.println();
				    System.out.println();
					
					
					
					
					Article article = new Article();
					article.setArticleID(rs.getInt("articleID"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					
					Date articleDate = rs.getTimestamp("dateTime");
					DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
					String articleSubmittedDate = df.format(articleDate);
					// Print what date is today!
					//System.out.println("Article Date: " + articleSubmittedDate);
					
					article.setArticleDate(articleSubmittedDate);
					//article.setDateTime();
					article.setCategory(rs.getString("category"));
					article.setLocation(rs.getString("location"));
					article.setUserNRIC(rs.getString("userNRIC"));
					article.setActive(rs.getInt("active"));
					article.setApproved(rs.getString("status"));
					article.setDbLat(rs.getDouble("lat"));
					article.setDbLon(rs.getDouble("lng"));
					
					
					
					if(currentLat == 0 && currentLon==0){
						article.setDist("-");
						articlesArrList.add(article);
					}
					else if(selectedDist==0){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					else if(dist<=selectedDist){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					
					
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
		
		
		
		/***Display Approved Article with category "Feedback" within specified distance***/
		public ArrayList<Article> retrieveAllPendingFeedbackArticlesWithinDistance(double currentLat, double currentLon, int selectedDist) {
			String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category = 'Feedback' ORDER BY dateTime DESC";
			ArrayList<Article> articlesArrList = new ArrayList<Article>();
			try {
				Connection conn = dbController.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					
					
					
					double earthRadius = 6371; //kilometers
				    double dLat = Math.toRadians(rs.getDouble("lat")-currentLat);
				    double dLng = Math.toRadians(rs.getDouble("lng")-currentLon);
				    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				               Math.cos(Math.toRadians(currentLat)) * Math.cos(Math.toRadians(rs.getDouble("lat"))) *
				               Math.sin(dLng/2) * Math.sin(dLng/2);
				    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				    double dist = (double) (earthRadius * c);
					
				    double roundedDist = Math.floor(dist * 1000) / 1000.0;
					
				    System.out.println();
				    System.out.println(rs.getString("title"));
				    System.out.println(dist);
				    System.out.println();
				    System.out.println();
					
					
					
					
					Article article = new Article();
					article.setArticleID(rs.getInt("articleID"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					
					Date articleDate = rs.getTimestamp("dateTime");
					DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
					String articleSubmittedDate = df.format(articleDate);
					// Print what date is today!
					//System.out.println("Article Date: " + articleSubmittedDate);
					
					article.setArticleDate(articleSubmittedDate);
					//article.setDateTime();
					article.setCategory(rs.getString("category"));
					article.setLocation(rs.getString("location"));
					article.setUserNRIC(rs.getString("userNRIC"));
					article.setActive(rs.getInt("active"));
					article.setApproved(rs.getString("status"));
					article.setDbLat(rs.getDouble("lat"));
					article.setDbLon(rs.getDouble("lng"));
					
					
					
					if(currentLat == 0 && currentLon==0){
						article.setDist("-");
						articlesArrList.add(article);
					}
					else if(selectedDist==0){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					else if(dist<=selectedDist){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					
					
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
		
		
		/***Display Approved Article with category "Feedback" within specified distance***/
		public ArrayList<Article> retrieveAllPendingLocationArticlesWithinDistance(double currentLat, double currentLon, int selectedDist) {
			String sql = "SELECT * FROM articles WHERE status = 'Pending' AND category = 'Location Usage' ORDER BY dateTime DESC";
			ArrayList<Article> articlesArrList = new ArrayList<Article>();
			try {
				Connection conn = dbController.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					
					
					
					double earthRadius = 6371; //kilometers
				    double dLat = Math.toRadians(rs.getDouble("lat")-currentLat);
				    double dLng = Math.toRadians(rs.getDouble("lng")-currentLon);
				    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				               Math.cos(Math.toRadians(currentLat)) * Math.cos(Math.toRadians(rs.getDouble("lat"))) *
				               Math.sin(dLng/2) * Math.sin(dLng/2);
				    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				    double dist = (double) (earthRadius * c);
					
				    double roundedDist = Math.floor(dist * 1000) / 1000.0;
					
				    System.out.println();
				    System.out.println(rs.getString("title"));
				    System.out.println(dist);
				    System.out.println();
				    System.out.println();
					
					
					
					
					Article article = new Article();
					article.setArticleID(rs.getInt("articleID"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					
					Date articleDate = rs.getTimestamp("dateTime");
					DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
					String articleSubmittedDate = df.format(articleDate);
					// Print what date is today!
					//System.out.println("Article Date: " + articleSubmittedDate);
					
					article.setArticleDate(articleSubmittedDate);
					//article.setDateTime();
					article.setCategory(rs.getString("category"));
					article.setLocation(rs.getString("location"));
					article.setUserNRIC(rs.getString("userNRIC"));
					article.setActive(rs.getInt("active"));
					article.setApproved(rs.getString("status"));
					article.setDbLat(rs.getDouble("lat"));
					article.setDbLon(rs.getDouble("lng"));
					
					
					
					if(currentLat == 0 && currentLon==0){
						article.setDist("-");
						articlesArrList.add(article);
					}
					else if(selectedDist==0){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					else if(dist<=selectedDist){
						article.setDist(Double.toString(roundedDist));
						article.setDistToSort(roundedDist);
						articlesArrList.add(article);
					}
					
					
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
		
		
		
		
}
