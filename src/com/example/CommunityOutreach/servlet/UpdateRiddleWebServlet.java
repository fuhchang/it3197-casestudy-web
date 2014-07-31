package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.RiddleManager;
import com.example.CommunityOutreach.model.Riddle;
import com.example.CommunityOutreach.model.RiddleAnswer;
import com.example.CommunityOutreach.model.User;

/**
 * Servlet implementation class UpdateRiddleWebServlet
 */
@WebServlet("/UpdateRiddleWebServlet")
public class UpdateRiddleWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRiddleWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Riddle riddle = new RiddleManager().retrieveRiddle(Integer.parseInt(request.getParameter("riddleID")));
		request.setAttribute("riddle", riddle);
		
		ArrayList<RiddleAnswer> riddleAnsList = new RiddleManager().retrieveRiddleAnswers(Integer.parseInt(request.getParameter("riddleID")));
		request.setAttribute("riddleAnsList", riddleAnsList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/UpdateRiddle.jsp");
		requestDispatcher.forward(request, response);
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
		 
		 Riddle riddle = new Riddle();
		 riddle.setRiddleID(Integer.parseInt(request.getParameter("riddleID")));
		 riddle.setUser(new User(nric));
		 riddle.setRiddleTitle(request.getParameter("title"));
		 riddle.setRiddleContent(request.getParameter("content"));
		 
		 RiddleManager riddleManager = new RiddleManager();
		 riddleManager.updateRiddle(riddle);
		 
		 for (int i = 0; i < 4; i++) {
			 RiddleAnswer riddleAns = new RiddleAnswer();
			 riddleAns.setRiddleAnswerID(Integer.parseInt(request.getParameter("riddleAnsID"+i)));
			 riddleAns.setRiddleAnswer(request.getParameter("choice"+(i+1)));
			 
			 if(Integer.parseInt(request.getParameter("choices")) == i)
				 riddleAns.setRiddleAnswerStatus("CORRECT");
			 else
				 riddleAns.setRiddleAnswerStatus("WRONG");
			 
			 riddleManager.updateRiddleAns(riddle, riddleAns);
		 }
		 
		 response.sendRedirect("RetrieveAllRiddleWebServlet");
	}

}
