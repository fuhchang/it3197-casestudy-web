package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class TCDisplaySelectedArticle
 */
@WebServlet("/TCDisplaySelectedArticle")
public class TCDisplaySelectedArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TCDisplaySelectedArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int articleID=Integer.parseInt(request.getParameter("id"));
		ArticleManager am = new ArticleManager();
		Article articleDetail = am.retrieveArticle(articleID);
		
		response.getWriter().print(articleDetail.getTitle());
		int idArticle = articleDetail.getArticleID();
		String title = articleDetail.getTitle();
		String user = articleDetail.getUserNRIC();
		String content = articleDetail.getContent();
		String location = articleDetail.getLocation();
		Date articleDate = articleDetail.getDateTime();
		DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
		String articleSubmittedDate = df.format(articleDate);
		// Print what date is today!
		System.out.println("Article Date: " + articleSubmittedDate);
		String articleUserName = articleDetail.getArticleUser();
		double dbLat = articleDetail.getDbLat();
		double dbLon = articleDetail.getDbLon();
		
		System.out.println(title);
		request.setAttribute("idArticle", idArticle);
		request.setAttribute("articleTitle", title);
		request.setAttribute("articleUserName", articleUserName);
		request.setAttribute("articleContent", content);
		request.setAttribute("articleLocation", location);
		request.setAttribute("articleDate", articleSubmittedDate);
		request.setAttribute("dbLat", dbLat);
		request.setAttribute("dbLon", dbLon);
		RequestDispatcher rd = request.getRequestDispatcher("tcDisplaySelectedArticle.jsp");
		rd.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String legit = request.getParameter("legit");
		
		System.out.println(legit);
		
		//int articleID=Integer.parseInt(request.getParameter("id"));
		
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		//System.out.println(request.getParameter("idArticle"));
		
		ArticleManager am = new ArticleManager();
		Article articleDetail = am.retrieveArticle(idArticle);
		
		String title = articleDetail.getTitle();
		String content = articleDetail.getContent();
		Date articleDate = articleDetail.getDateTime();
		String category = articleDetail.getCategory();
		String location = articleDetail.getLocation();
		String userNRIC = articleDetail.getUserNRIC();
		int active = 1;
		double dbLat = articleDetail.getDbLat();
		double dbLon = articleDetail.getDbLon();		
		
		if(legit.equals("Yes")){
			String approved= "Confirmed";
			
			 Article a = new Article(idArticle, title, content, articleDate,category, location, userNRIC, active,approved,dbLat, dbLon);
		
		 	boolean isArticleEdited = false;
	        try{
	        	isArticleEdited = am.editArticle(a);
	        	if(!isArticleEdited){
	        		JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", false);
	                myObj.addProperty("message","Unable to edit article successfully.");
	                System.out.println(myObj.toString());
	        	}
	        	else{
	                JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", true);
	                myObj.addProperty("message","Article edited successfully.");
	                System.out.println(myObj.toString());
	        	}
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	    		JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message","Unable to edit article successfully.");
	            System.out.println(myObj.toString());
	        }
	        
	        RequestDispatcher rd = request.getRequestDispatcher("PendingArticlesServlet");
	        rd.forward(request,response);
		}
		else if(legit.equals("No")){
			String approved= "Discard";
			
			 Article a = new Article(idArticle, title, content, articleDate,category, location, userNRIC, active,approved,dbLat, dbLon);
		
		 	boolean isArticleEdited = false;
	        try{
	        	isArticleEdited = am.editArticle(a);
	        	if(!isArticleEdited){
	        		JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", false);
	                myObj.addProperty("message","Unable to edit article successfully.");
	                System.out.println(myObj.toString());
	        	}
	        	else{
	                JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", true);
	                myObj.addProperty("message","Article edited successfully.");
	                System.out.println(myObj.toString());
	        	}
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	    		JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message","Unable to edit article successfully.");
	            System.out.println(myObj.toString());
	        }
	        
	        RequestDispatcher rd = request.getRequestDispatcher("PendingArticlesServlet");
	        rd.forward(request,response);
		}
		
		
		
		
		
		
		
		
		
	}

}
