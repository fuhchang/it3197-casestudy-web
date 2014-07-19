package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.HobbyMembersManager;
import com.example.CommunityOutreach.model.HobbyMembers;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class JoinHobbyGrpServlet
 */
@WebServlet("/JoinHobbyGrpServlet")
public class JoinHobbyGrpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinHobbyGrpServlet() {
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
		
		String grpID = request.getParameter("grpID");
		String userNric = request.getParameter("userNric");
		HobbyMembers member = new HobbyMembers();
		member.setGroupID(Integer.parseInt(grpID));
		member.setUserNRIC(userNric);
		member.setRole("member");
		member.setActive(1);
		HobbyMembersManager manager = new HobbyMembersManager();
		
		boolean result = manager.createHobbyMember(member);
		if (result == true) {
			JsonObject myObj = new JsonObject();
			myObj.addProperty("success", true);
			myObj.addProperty("message", "Hobby created successfully.");
			out.println(myObj.toString());
		}
	}

}
