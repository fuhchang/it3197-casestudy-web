package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.PostManager;
import com.example.CommunityOutreach.model.HobbyPost;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GetPostListServlet
 */
@WebServlet("/GetPostListServlet")
public class GetPostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostListServlet() {
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
        
        String getID = request.getParameter("id");
      
        int id = Integer.parseInt(getID);
        PostManager pm = new PostManager();
        ArrayList<HobbyPost> postList = pm.retrievePost(id);
        
        if(postList.size() == 0 || postList == null){
        	 JsonObject myObj = new JsonObject();
             myObj.addProperty("success", false);
             myObj.addProperty("message", "Unable to retrieve any post.");
             out.println(myObj.toString());
        }else{
        	Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
        	JsonElement eventObj;
        	JsonArray postArray = new JsonArray();
            for(int i=0;i< postList.size();i++){
            	eventObj = gson.toJsonTree(postList.get(i));
            	postArray.add(eventObj);
            	myObj.add("postList", postArray);
            }
            out.println(myObj.toString());
        }
        out.close();
	}
	
}
