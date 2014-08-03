package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.requestHobbyManager;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.RequestHobby;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateRequestServlet
 */
@WebServlet("/CreateRequestServlet")
public class CreateRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequestServlet() {
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
		boolean result;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		
		int eventID = Integer.parseInt(request.getParameter("eventID"));
		int hobbyID = Integer.parseInt(request.getParameter("hobbyID"));
		String startDate = request.getParameter("dateStart");
		String endDate = request.getParameter("endDate");
		String groupname = request.getParameter("groupname");
		RequestHobby rh = new RequestHobby();
		rh.setEventID(eventID);
		rh.setHobbyID(hobbyID);
		rh.setGroupname(groupname);
		rh.setRequestStatus("pending");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			rh.setRequestDateStart(dateFormat.parse(startDate));
			rh.setRequestDateEnd(dateFormat.parse(endDate));
			requestHobbyManager rhm = new requestHobbyManager();
			result = rhm.createRequest(rh);
			
			if (result) {
				JsonObject myObj = new JsonObject();
				myObj.addProperty("success", true);
				myObj.addProperty("message", "request has been submitted.");
				out.println(myObj.toString());
			}
			out.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
