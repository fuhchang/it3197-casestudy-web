package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;

/**
 * Servlet implementation class ArticleSubmissionServlet
 */
@WebServlet("/ArticleSubmissionServlet")
public class ArticleSubmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleSubmissionServlet() {
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
		String title = request.getParameter("title");
		String category = (request.getParameter("category"));
		String content = request.getParameter("content");
	//	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//	Date date = new Date();
	//	System.out.println(date);
		Date currentTime = null;
		System.out.println(request.getParameter("coordinates"));
		String location=request.getParameter("coordinates");
		String userNRIC = "S9512233X";
		int active = 1;
		int approved=0;
		
		
		ArticleManager am = new ArticleManager();
	/*	Article a = new Article(0, title, content, currentTime,category, location, userNRIC, active,approved);
		
		 boolean articleCreatedCheck = false;
	        try{
	        	articleCreatedCheck = am.createArticle(a);
	        	if(!articleCreatedCheck){
	        		JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", false);
	                myObj.addProperty("message","Unable to create event successfully.");
	                System.out.println(myObj.toString());
	        	}
	        	else{
	                JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", true);
	                myObj.addProperty("message","Event created successfully.");
	                System.out.println(myObj.toString());
	        	}
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	    		JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message","Unable to create event successfully.");
	            System.out.println(myObj.toString());
	        }*/
	}

}
