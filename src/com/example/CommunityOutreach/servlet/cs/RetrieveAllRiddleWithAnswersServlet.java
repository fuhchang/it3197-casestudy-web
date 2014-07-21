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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class RetrieveAllRiddleWithAnswersServlet
 */
@WebServlet("/RetrieveAllRiddleWithAnswersServlet")
public class RetrieveAllRiddleWithAnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAllRiddleWithAnswersServlet() {
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
        ArrayList<Riddle> riddleList = riddleManager.retrieveAllRiddle();
    	ArrayList<RiddleAnswer> riddleAnsList = new ArrayList<RiddleAnswer>();
    	
        for(int i = 0; i < riddleList.size(); i++) {
        	//RiddleAnswer[] riddleAnsArr = riddleManager.retrieveRiddleAnswers(riddleList.get(i));
        	for(int ii = 0; ii < 4; ii++) {
        		//riddleAnsList.add(riddleAnsArr[ii]);
        	}
        }
        
        if(riddleList.size() == 0 || riddleList == null) {
        	JsonObject myObj = new JsonObject();
        	myObj.addProperty("success", false);
        	myObj.addProperty("message", "Unable to retrieve riddle.");
        	out.println(myObj.toString());
        } else {
        	Gson gson = new Gson();
        	JsonObject myObj = new JsonObject();
        	myObj.addProperty("success", true);
        	
        	JsonElement obj;
        	JsonArray jsonArr = new JsonArray();
        	
        	for(int i = 0; i < riddleList.size(); i++) {
        		obj = gson.toJsonTree(riddleList.get(i));
        		jsonArr.add(obj);
        		myObj.add("list", jsonArr);
        	}
        	for(int i = 0; i < riddleAnsList.size(); i++) {
        		obj = gson.toJsonTree(riddleAnsList.get(i));
        		jsonArr.add(obj);
        		myObj.add("list", jsonArr);
        	}
        	out.println(myObj.toString());
        	
        	/*JsonElement riddleObj;
        	JsonArray riddleArray = new JsonArray();
        	
        	for(int i = 0; i < riddleList.size(); i++) {
        		riddleObj = gson.toJsonTree(riddleList.get(i));
        		riddleArray.add(riddleObj);
        		myObj.add("riddleList", riddleArray);
        	}
        	out.println(myObj.toString());
        	
        	JsonElement riddleAnsObj;
        	JsonArray riddleAnsArray = new JsonArray();
        	
        	for(int i = 0; i < riddleAnsList.size(); i++) {
        		riddleAnsObj = gson.toJsonTree(riddleAnsList.get(i));
        		riddleAnsArray.add(riddleAnsObj);
        		myObj.add("riddleAnsList", riddleAnsArray);
        	}
        	out.println(myObj.toString());*/
        }
        out.close();
	}

}
