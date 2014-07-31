package com.example.CommunityOutreach.servlet;

import java.io.IOException;

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
 * Servlet implementation class CreateRiddleWebServlet
 */
@WebServlet("/CreateRiddleWebServlet")
public class CreateRiddleWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRiddleWebServlet() {
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
		 
		 Riddle riddle = new Riddle();
		 riddle.setUser(new User(nric));
		 riddle.setRiddleTitle(request.getParameter("title"));
		 riddle.setRiddleContent(request.getParameter("content"));
		 riddle.setRiddleStatus("ACTIVE");
		 riddle.setRiddlePoint(10);
		 
		 RiddleManager riddleManager = new RiddleManager();
		 riddleManager.createRiddle(riddle);
		 
		 for (int i = 0; i < 4; i++) {
			 RiddleAnswer riddleAns = new RiddleAnswer();
			 riddleAns.setRiddleAnswer(request.getParameter("choice"+(i+1)));
			 
			 if(Integer.parseInt(request.getParameter("choices")) == i)
				 riddleAns.setRiddleAnswerStatus("CORRECT");
			 else
				 riddleAns.setRiddleAnswerStatus("WRONG");
			 
			 riddleManager.createRiddleAns(riddleAns);
		 }
		 
		 UserManager userManager = new UserManager();
		 User user = userManager.retrieveUser(nric);
		 userManager.updatePoints(nric, user.getPoints()-50);
		 
		 response.sendRedirect("RetrieveAllRiddleWebServlet");
	}

}
