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
import com.example.CommunityOutreach.model.RiddleUserAnswered;

/**
 * Servlet implementation class ViewRiddleWebServlet
 */
@WebServlet("/ViewRiddleWebServlet")
public class ViewRiddleWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRiddleWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		int riddleID = Integer.parseInt(request.getParameter("riddleID"));
		RiddleManager riddleManager = new RiddleManager();
		
		Riddle riddle = riddleManager.retrieveRiddle(riddleID);
		request.setAttribute("riddle", riddle);
		
		ArrayList<RiddleAnswer> riddleAnsList = riddleManager.retrieveRiddleAnswers(riddleID);		
		request.setAttribute("riddleAnsList", riddleAnsList);
		
		ArrayList<RiddleUserAnswered> userAnsweredList = riddleManager.retrieveAllUserAnswered(nric);
		request.setAttribute("userAnsweredList", userAnsweredList);
		
		ArrayList<RiddleUserAnswered> answeredList = riddleManager.retrieveAllAnswered(riddleID);
		request.setAttribute("answeredList", answeredList);
		
		int correctlyAnswered = 0;
		for(int i = 0; i < answeredList.size(); i++) {
			for(int ii = 0; ii < riddleAnsList.size(); ii++) {
				if(answeredList.get(i).getRiddleAnswer().getRiddleAnswerID() == riddleAnsList.get(ii).getRiddleAnswerID()) {
					if(riddleAnsList.get(ii).getRiddleAnswerStatus().equals("CORRECT"))
						correctlyAnswered++;
				}
			}
		}
		request.setAttribute("correctlyAnswered", correctlyAnswered);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewRiddle.jsp");
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

		int riddleID = Integer.parseInt(request.getParameter("riddleID"));
		RiddleManager riddleManager = new RiddleManager();
		
		Riddle riddle = riddleManager.retrieveRiddle(riddleID);
		request.setAttribute("riddle", riddle);
		
		ArrayList<RiddleAnswer> riddleAnsList = riddleManager.retrieveRiddleAnswers(riddleID);		
		request.setAttribute("riddleAnsList", riddleAnsList);
		
		ArrayList<RiddleUserAnswered> userAnsweredList = riddleManager.retrieveAllUserAnswered(nric);
		request.setAttribute("userAnsweredList", userAnsweredList);
		
		ArrayList<RiddleUserAnswered> answeredList = riddleManager.retrieveAllAnswered(riddleID);
		request.setAttribute("answeredList", answeredList);
		
		int correctlyAnswered = 0;
		for(int i = 0; i < answeredList.size(); i++) {
			for(int ii = 0; ii < riddleAnsList.size(); ii++) {
				if(answeredList.get(i).getRiddleAnswer().getRiddleAnswerID() == riddleAnsList.get(ii).getRiddleAnswerID()) {
					if(riddleAnsList.get(ii).getRiddleAnswerStatus().equals("CORRECT"))
						correctlyAnswered++;
				}
			}
		}
		request.setAttribute("correctlyAnswered", correctlyAnswered);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewRiddle.jsp");
		requestDispatcher.forward(request, response);
	}

}
