package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class getUserServlet
 */
@WebServlet("/GetUserServlet")
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //ArrayList<User> userList = new ArrayList<User>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserServlet() {
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
        
        UserManager userManager = new UserManager();
        System.out.println(request.getParameter("userNRIC"));
        System.out.println(request.getParameter("password"));
        User user = userManager.retrieveUser(request.getParameter("userNRIC"), request.getParameter("password"));
        
       /*
        if(user.equals("")) {
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message", "Unable to retrieve User.");
            out.println(myObj.toString());
        }else{
        	Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
        	JsonElement userObj;
        	JsonArray userArr = new JsonArray();
        	userObj = gson.toJsonTree(user);
        	userArr.add(userObj);
        	myObj.add("user", userArr);
            out.println(myObj.toString());
        }
        */
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        myObj.addProperty("success", true);
    	JsonElement userObj;
    	JsonArray userArr = new JsonArray();
    	userObj = gson.toJsonTree(user);
    	userArr.add(userObj);
    	myObj.add("user", userArr);
        out.println(myObj.toString());
        out.close();
        
        /*UserManager getUser = new UserManager();
        userList = getUser.retrieveAllUsers();
        
        if(userList.size() ==0){
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message", "Unable to retrieve Hobby.");
            out.println(myObj.toString());
        }else{
        	Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
        	JsonElement eventObj;
        	JsonArray userArray = new JsonArray();
            for(int i=0;i<userList.size();i++){
            	eventObj = gson.toJsonTree(userList.get(i));
            	userArray.add(eventObj);
            	myObj.add("userList", userArray);
            }
            out.println(myObj.toString());
        }
        out.close();*/
	}

}
