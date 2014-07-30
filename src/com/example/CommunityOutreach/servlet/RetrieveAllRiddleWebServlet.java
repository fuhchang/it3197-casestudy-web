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
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Riddle;
import com.example.CommunityOutreach.model.RiddleAnswer;
import com.example.CommunityOutreach.model.RiddleUserAnswered;
import com.example.CommunityOutreach.model.User;

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
		 
		UserManager userManager = new UserManager();
		User user = userManager.retrieveUser(nric);
		request.setAttribute("user", user);
		
		RiddleManager riddleManager = new RiddleManager();
		
		ArrayList<Riddle> riddleList = riddleManager.retrieveAllRiddle();
		request.setAttribute("riddleList", riddleList);
		
		ArrayList<RiddleAnswer> riddleAnsList = riddleManager.retrieveAllRiddleAnswers();
		request.setAttribute("riddleAnsList", riddleAnsList);
		
		ArrayList<RiddleUserAnswered> userAnsweredList = riddleManager.retrieveAllUserAnswered(nric);
		request.setAttribute("userAnsweredList", userAnsweredList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Riddle.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
