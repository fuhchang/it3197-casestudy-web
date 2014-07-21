package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ArticleDeleteApprovedArticles
 */
@WebServlet("/ArticleDeleteApprovedArticles")
public class ArticleDeleteApprovedArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleDeleteApprovedArticles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 int articleID = Integer.parseInt(request.getParameter("id"));
			
			ArticleManager am = new ArticleManager();
			//Article a = new Article(0, title, content, currentTime, category, location, userNRIC, active,approved,dbLat, dbLon);
			//Article a = new Article();
			//a.setArticleID(articleID);
			
			 boolean articleUpdatedCheck = false;
		        try{
		        	articleUpdatedCheck = am.confirmFeedbackArticle(articleID, request.getParameter("decision"));
		        	if(!articleUpdatedCheck){
		        		JsonObject myObj = new JsonObject();
		                myObj.addProperty("success", false);
		                myObj.addProperty("message","Unable to update article successfully.");
		                System.out.println(myObj.toString());
		        	}
		        	else{
		                JsonObject myObj = new JsonObject();
		                myObj.addProperty("success", true);
		                myObj.addProperty("message","Article updated successfully.");
		                System.out.println(myObj.toString());
		        	}
		        }
		        catch(Exception ex){
		        	ex.printStackTrace();
		    		JsonObject myObj = new JsonObject();
		            myObj.addProperty("success", false);
		            myObj.addProperty("message","Unable to update article successfully.");
		            System.out.println(myObj.toString());
		        }
		        
		        RequestDispatcher rd = request.getRequestDispatcher("DesktopDeleteLatestArticle");
				rd.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
