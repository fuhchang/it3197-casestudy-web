package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.RiddleManager;
import com.example.CommunityOutreach.model.Riddle;
import com.example.CommunityOutreach.model.RiddleAnswer;
import com.example.CommunityOutreach.model.RiddleUserAnswered;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class InsertChoiceServlet
 */
@WebServlet("/InsertChoiceServlet")
public class InsertChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertChoiceServlet() {
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
		
		RiddleManager riddleManager = new RiddleManager();
		int riddleID = Integer.parseInt(request.getParameter("riddleID"));
		String userNRIC = request.getParameter("userNRIC");
		
		if(request.getParameter("rating") == null) {
			int riddleAnswerID = Integer.parseInt(request.getParameter("riddleAnswerID"));
			boolean result = riddleManager.insertChoice(riddleID, riddleAnswerID, userNRIC);
	
			if (result) {
				 /* Calculation for difficulty level */
				 ArrayList<RiddleUserAnswered> answeredList = riddleManager.retrieveAllAnswered(riddleID);
				 ArrayList<RiddleAnswer> riddleAnsList = riddleManager.retrieveRiddleAnswers(riddleID);
				 
				 int correctlyAnswered = 0, easyRate = 0, mediumRate = 0, hardRate = 0, totalRate = 0;
				 for(int i = 0; i < answeredList.size(); i++) {
					 for(int ii = 0; ii < riddleAnsList.size(); ii++) {
						 if(answeredList.get(i).getRiddleAnswer().getRiddleAnswerID() == riddleAnsList.get(ii).getRiddleAnswerID()) {
							 if(riddleAnsList.get(ii).getRiddleAnswerStatus().equals("CORRECT")) {
								 correctlyAnswered++;
							 }
						 }
					 }
					 if(answeredList.get(i).getAnsweredRate().equals("EASY")) {
						 easyRate++;
						 totalRate++;
					 }
					 else if(answeredList.get(i).getAnsweredRate().equals("MEDIUM")) {
						 mediumRate++;
						 totalRate++;
					 }
					 else if(answeredList.get(i).getAnsweredRate().equals("HARD")) {
						 hardRate++;
						 totalRate++;
					 }
				 }
				 
				 double correctAnsPercentage, ratePercentage, finalPercentage;
				 correctAnsPercentage = (correctlyAnswered / answeredList.size()) * 100;
				 ratePercentage = (easyRate * 0.2) + (mediumRate * 0.5) + (hardRate * 0.8) / totalRate;
				 finalPercentage = correctAnsPercentage * ratePercentage;
				 
				 Riddle riddle = riddleManager.retrieveRiddle(riddleID);
				 if(0 < finalPercentage && finalPercentage < 0.33){
					 riddle.setRiddlePoint(5);
				 }
				 else if(0.34 < finalPercentage && finalPercentage < 0.66){
					 riddle.setRiddlePoint(10);
				 }
				 else if(0.67 < finalPercentage && finalPercentage < 1){
					 riddle.setRiddlePoint(20);
				 }
				 /* End of calculation for difficulty level */
				 
				JsonObject myObj = new JsonObject();
				myObj.addProperty("success", true);
				myObj.addProperty("message", "Choice inserted successfully.");
				out.println(myObj.toString());
			}
		}
		else {
			String rating = request.getParameter("rating");
			boolean result = riddleManager.insertRate(riddleID, userNRIC, rating);
	
			if (result) {
				JsonObject myObj = new JsonObject();
				myObj.addProperty("success", true);
				myObj.addProperty("message", "Rating inserted successfully.");
				out.println(myObj.toString());
			}
		}
	}
}
