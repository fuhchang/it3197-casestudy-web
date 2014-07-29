package com.example.CommunityOutreach.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.RiddleManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Riddle;
import com.example.CommunityOutreach.model.RiddleAnswer;
import com.example.CommunityOutreach.model.User;

/**
 * Servlet implementation class InsertChoiceWebServlet
 */
@WebServlet("/InsertChoiceWebServlet")
public class InsertChoiceWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertChoiceWebServlet() {
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
		
		response.setContentType("text/html");
		String nric = null;
		Cookie[] cookies = request.getCookies();
		 if(cookies != null){
	        for(Cookie cookie : cookies){
	            if(cookie.getName().equals("userLogin")){
	                nric = cookie.getValue().toString();
	                break;
	            }
	        }
		 }

		 UserManager userManager = new UserManager();
		 User user = userManager.retrieveUser(nric);
		 
		 RiddleManager riddleManager = new RiddleManager();
		 
		 if(request.getParameter("riddleAnswerID") != null) {
			 int riddleID = Integer.parseInt(request.getParameter("riddleID"));
			 int riddleAnswerID = Integer.parseInt(request.getParameter("riddleAnswerID"));
			 
			 Riddle riddle = riddleManager.retrieveRiddle(riddleID);
			 
			 boolean result = riddleManager.insertChoice(riddleID, riddleAnswerID, nric);
			 
			 if(result) {
				 RiddleAnswer riddleAns = riddleManager.retrieveRiddleAnswer(riddleAnswerID);
				 
				 if(riddleAns.getRiddleAnswerStatus().equals("CORRECT"))
					 userManager.updatePoints(nric, user.getPoints()+riddle.getRiddlePoint());
			 }
		 }
		 else if(request.getParameter("rating") != null) {
			 boolean result = riddleManager.insertRate(nric, request.getParameter("rating").toUpperCase());
			 
			 if(result) {
				 userManager.updatePoints(nric, user.getPoints()+1);
			 }
		 }
			
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewRiddleWebServlet");
		requestDispatcher.forward(request, response);
	}

}
