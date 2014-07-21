package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.RiddleManager;
import com.example.CommunityOutreach.model.RiddleAnswer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class RetrieveAllRiddleAnswerServlet
 */
@WebServlet("/RetrieveAllRiddleAnswerServlet")
public class RetrieveAllRiddleAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAllRiddleAnswerServlet() {
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
        RiddleAnswer[] riddleAnsList = riddleManager.retrieveRiddleAnswers(Integer.parseInt(request.getParameter("riddleID")));

        if(riddleAnsList.length == 0 || riddleAnsList == null) {
        	JsonObject myObj = new JsonObject();
        	myObj.addProperty("success", false);
        	myObj.addProperty("message", "Unable to retrieve riddle answers.");
        	out.println(myObj.toString());
        } else {
        	Gson gson = new Gson();
        	JsonObject myObj = new JsonObject();
        	myObj.addProperty("success", true);
        	
        	JsonElement obj;
        	JsonArray jsonArr = new JsonArray();
        	
        	for(int i = 0; i < riddleAnsList.length; i++) {
        		obj = gson.toJsonTree(riddleAnsList[i]);
        		jsonArr.add(obj);
        		myObj.add("riddleAnsList", jsonArr);
        	}
        	out.println(myObj.toString());
        }
        out.close();
	}

}
