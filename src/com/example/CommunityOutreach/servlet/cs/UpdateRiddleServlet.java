package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.RiddleManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Riddle;
import com.example.CommunityOutreach.model.RiddleAnswer;
import com.example.CommunityOutreach.model.User;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UpdateRiddleServlet
 */
@WebServlet("/UpdateRiddleServlet")
public class UpdateRiddleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRiddleServlet() {
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
		
		String userNRIC = request.getParameter("userNRIC");
		UserManager userManager = new UserManager();
		User user = userManager.retrieveUser(userNRIC);

		RiddleManager riddleManager = new RiddleManager();
		
		// Riddle
		Riddle riddle = new Riddle();
		riddle.setRiddleID(Integer.parseInt(request.getParameter("riddleID")));
		riddle.setUser(user);
		riddle.setRiddleTitle(request.getParameter("riddleTitle"));
		riddle.setRiddleContent(request.getParameter("riddleContent"));
		riddle.setRiddleStatus(request.getParameter("riddleStatus"));
		riddle.setRiddlePoint(Integer.parseInt(request.getParameter("riddlePoint")));
		boolean riddleResult = riddleManager.updateRiddle(riddle);
		
		// Answer
		boolean answerResult = false;
		for(int i = 0; i < 4; i++) {
			RiddleAnswer riddleAns = new RiddleAnswer();
			riddleAns.setRiddleAnswerID(Integer.parseInt(request.getParameter("riddleAnswerID"+i)));
			riddleAns.setRiddleAnswer(request.getParameter("riddleAnswer"+i));
			riddleAns.setRiddleAnswerStatus(request.getParameter("riddleAnswerStatus"+i));
			answerResult = riddleManager.updateRiddleAns(riddle, riddleAns);
		}

		if (riddleResult && answerResult) {
			JsonObject myObj = new JsonObject();
			myObj.addProperty("success", true);
			myObj.addProperty("message", "Riddle updated successfully.");
			out.println(myObj.toString());
		}
	}

}
