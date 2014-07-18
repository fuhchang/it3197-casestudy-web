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
 * Servlet implementation class CreateRiddleServlet
 */
@WebServlet("/CreateRiddleServlet")
public class CreateRiddleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateRiddleServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		// Riddle
		Riddle riddle = new Riddle();
		riddle.setUser(user);
		riddle.setRiddleTitle(request.getParameter("riddleTitle"));
		riddle.setRiddleContent(request.getParameter("riddleContent"));
		riddle.setRiddleStatus(request.getParameter("riddleStatus"));
		riddle.setRiddlePoint(Integer.parseInt(request.getParameter("riddlePoint")));
		
		// Answer
		RiddleAnswer riddleAns = new RiddleAnswer();
		riddleAns.setUser(user);
		riddleAns.setRiddleAnswer(request.getParameter("riddleAnswer"));
		riddleAns.setRiddleAnswerStatus(request.getParameter("riddleAnswerStatus"));
		
		RiddleManager riddleManager = new RiddleManager();
		boolean riddleResult = riddleManager.createRiddle(riddle);
		boolean answerResult = riddleManager.createCorrectRiddleAns(riddleAns);

		if (riddleResult && answerResult) {
			JsonObject myObj = new JsonObject();
			myObj.addProperty("success", true);
			myObj.addProperty("message", "Riddle created successfully.");
			out.println(myObj.toString());
		}
	}

}
