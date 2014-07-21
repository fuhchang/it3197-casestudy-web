package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ArticleUpdateFeedbackStatusServlet
 */
@WebServlet("/ArticleUpdateFeedbackStatusServlet")
public class ArticleUpdateFeedbackStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleUpdateFeedbackStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		
		// TODO Auto-generated method stub
        int articleID = Integer.parseInt(request.getParameter("articleIDToUpdate"));
		
		ArticleManager am = new ArticleManager();
		//Article a = new Article(0, title, content, currentTime, category, location, userNRIC, active,approved,dbLat, dbLon);
		//Article a = new Article();
		//a.setArticleID(articleID);
		
		 boolean articleUpdatedCheck = false;
	        try{
	        	articleUpdatedCheck = am.confirmFeedbackArticle(articleID, request.getParameter("articleStatus"));
	        	if(!articleUpdatedCheck){
	        		JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", false);
	                myObj.addProperty("message","Unable to update article successfully.");
	                out.println(myObj.toString());
	        	}
	        	else{
	                JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", true);
	                myObj.addProperty("message","Article updated successfully.");
	                out.println(myObj.toString());
	        	}
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	    		JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message","Unable to update article successfully.");
	            out.println(myObj.toString());
	        }
	}

}
