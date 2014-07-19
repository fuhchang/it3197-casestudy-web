package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.model.Hobby;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UpdateHobbyServlet
 */
@WebServlet("/UpdateHobbyServlet")
public class UpdateHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHobbyServlet() {
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
		String title = request.getParameter("gtitle");
		String category = request.getParameter("grpType");
		String grpDesc = request.getParameter("gDesc");
		String Lat = request.getParameter("gLat");
		String Lng = request.getParameter("gLng");
		
		Hobby hobby = new Hobby();
		hobby.setGrpID(Integer.parseInt(grpID));
		hobby.setGrpName(title);
		hobby.setCategory(category);
		if(Lat != "" && Lng !=""){
		hobby.setLat(Double.parseDouble(Lat));
		hobby.setLng(Double.parseDouble(Lng));
		}
		hobby.setGrpDesc(grpDesc);
		HobbyManager hobbyManager =  new HobbyManager();
		boolean result = hobbyManager.updateHobby(hobby);

		if (result) {
			JsonObject myObj = new JsonObject();
			myObj.addProperty("success", true);
			myObj.addProperty("message", "Hobby created successfully.");
			out.println(myObj.toString());
		}
	}

}
