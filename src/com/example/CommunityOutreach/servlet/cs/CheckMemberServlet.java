package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyMembersManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.HobbyMembers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CheckMemberServlet
 */
@WebServlet("/CheckMemberServlet")
public class CheckMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckMemberServlet() {
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
        
        String userNric = request.getParameter("userName");
        int grpID = Integer.parseInt(request.getParameter("grpID"));
        
        HobbyMembersManager hmm = new HobbyMembersManager();
        HobbyMembers member = hmm.CheckMember(userNric, grpID);
        
        if(member != null){
        	 Gson gson = new Gson();
             JsonObject myObj = new JsonObject();
             myObj.addProperty("success", true);
         	JsonElement userObj;
         	JsonArray userArr = new JsonArray();
         	userObj = gson.toJsonTree(member);
         	userArr.add(userObj);
         	myObj.add("memberCheck", userArr);
             out.println(myObj.toString());
             out.close();
        }
		
	}

}
