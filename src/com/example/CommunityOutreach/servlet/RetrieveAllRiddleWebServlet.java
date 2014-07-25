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

/**
 * Servlet implementation class RetrieveAllRiddleWebServlet
 */
@WebServlet("/RetrieveAllRiddleWebServlet")
public class RetrieveAllRiddleWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAllRiddleWebServlet() {
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
		 request.setAttribute("userNRIC", nric);
		
		ArrayList<Riddle> riddleList = new RiddleManager().retrieveAllRiddle();
		request.setAttribute("riddleList", riddleList);
		
		ArrayList<RiddleAnswer> riddleAnsList = new RiddleManager().retrieveAllRiddleAnswers();
		request.setAttribute("riddleAnsList", riddleAnsList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Riddle.jsp");
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
		 request.setAttribute("userNRIC", nric);
		
		ArrayList<Riddle> riddleList = new RiddleManager().retrieveAllRiddle();
		request.setAttribute("riddleList", riddleList);
		
		ArrayList<RiddleAnswer> riddleAnsList = new RiddleManager().retrieveAllRiddleAnswers();
		request.setAttribute("riddleAnsList", riddleAnsList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Riddle.jsp");
		requestDispatcher.forward(request, response);
	}

}
