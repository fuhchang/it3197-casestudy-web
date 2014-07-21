package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Article;
import com.example.CommunityOutreach.model.User;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ArticleResponse
 */
@WebServlet("/ArticleResponse")
public class ArticleResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int articleID=Integer.parseInt(request.getParameter("id"));
		String decision = request.getParameter("decision");
		
		ArticleManager am = new ArticleManager();
		Article articleDetail = am.retrieveArticle(articleID);
		
	//	response.getWriter().print(articleDetail.getTitle());
		String title = articleDetail.getTitle();
		String user = articleDetail.getUserNRIC();
		String content = articleDetail.getContent();
		String location = articleDetail.getLocation();
		Date articleDate = articleDetail.getDateTime();
		DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
		String articleSubmittedDate = df.format(articleDate);
		// Print what date is today!
	//	System.out.println("Article Date: " + articleSubmittedDate);
		String articleUserName = articleDetail.getArticleUser();
		double dbLat = articleDetail.getDbLat();
		double dbLon = articleDetail.getDbLon();
		
		
		
		UserManager um = new UserManager();
		User UserDetail = um.retrieveUser(user);
		String articleUserPhone = UserDetail.getContactNo();
		String articleUserEmail = UserDetail.getEmail();
		//article.setArticleUser(UserDetail.getName());	
		
		String result="";
		
		if(decision.equals("Approved")){
		
			result="approved";
		
		}
		else if(decision.equals("Discard")){
			result = "rejected as the location is unavailable for booking";
		}
		
		
		
		
		String msg = "Hi " + articleUserName + ", \n\nThe request you sent on " + articleSubmittedDate 
				+ " to use " + location + " has been " + result + ".\n\nThank you.\nNanyang Polytechnic Town Council \nContact Number: 65551242 \nEmail: NYPTC@mail.com " +
						"\n\nQuote: \"" + content + "\"";
		
		request.setAttribute("decision", decision);
		
		//System.out.println(title);
		request.setAttribute("idArticle", articleID);
		request.setAttribute("articleTitle", "RE: " + title);
		request.setAttribute("articleUserName", articleUserName);
		request.setAttribute("msg",msg);
		request.setAttribute("articleContent", content);
		request.setAttribute("articleLocation", location);
		request.setAttribute("articleDate", articleSubmittedDate);
		request.setAttribute("dbLat", dbLat);
		request.setAttribute("dbLon", dbLon);
		request.setAttribute("articleUserEmail", articleUserEmail);
		RequestDispatcher rd = request.getRequestDispatcher("articleReponse.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		String decision = request.getParameter("decision");
		System.out.println(decision);
		
		ArticleEMain email = ArticleEMain.getInstance();
		
		if(email.send("IT3161GeoVizAssignment@gmail.com" , "123841N@mymail.nyp.edu.sg", subject, message)==true){
			System.out.println("Successfully sent!");
					int idArticle = Integer.parseInt(request.getParameter("idArticle"));
					
					
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

					String approved= decision;
					
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
					
					RequestDispatcher rd = request.getRequestDispatcher("DesktopLocationArticle");
					rd.forward(request,response);
	
		}
		else{
			
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setHeader("Access-Control-Max-Age", "86400");
	        
			out.println("An error has occurred, not sent! (School network cannot send)"); 
			System.out.println("An error has occurred, not sent! (School network cannot send)");
		}
		
	}

}
