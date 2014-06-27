package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.EventParticipantsManager;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.EventParticipants;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class RetrieveEventParticipantServlet
 */
@WebServlet("/retrieveEventParticipant")
public class RetrieveEventParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveEventParticipantServlet() {
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
        
        int eventID;
        if((request.getParameter("eventID") == null) || (request.getParameter("eventID").equals(""))){
        	eventID = 0;
        }
        else{
        	eventID = Integer.parseInt(request.getParameter("eventID"));
        }
        String userNRIC = request.getParameter("userNRIC");
        System.out.println("Event No: " + eventID);
        
        EventParticipantsManager eventParticipantsManager = new EventParticipantsManager();
        EventParticipants checkEventParticipants;
        EventParticipants checkEventParticipantsEvent;
        EventParticipants checkEventParticipantsNRIC;
        if((eventID != 0) && (userNRIC != null) && (!userNRIC.equals(""))){
        	checkEventParticipants = eventParticipantsManager.retrieveEventParticipant(eventID, userNRIC);
            if((eventID == 0) || (userNRIC == null) || ((userNRIC.equals(""))) || (checkEventParticipants == null)){
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to retrieve this event participants.");
                out.println(myObj.toString());
            }
            else {
                Gson gson = new Gson(); 
                JsonElement eventParticipantsObj = gson.toJsonTree(checkEventParticipants);
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.add("eventParticipantsInfo", eventParticipantsObj);
                out.println(myObj.toString());
            }
        }
        else if(eventID != 0){
        	checkEventParticipantsEvent = eventParticipantsManager.retrieveEventParticipant(eventID);
            if((eventID == 0) || (checkEventParticipantsEvent == null)){
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to retrieve this event.");
                out.println(myObj.toString());
            }
            else {
                Gson gson = new Gson(); 
                JsonElement eventParticipantsObj = gson.toJsonTree(checkEventParticipantsEvent);
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.add("eventParticipantsInfo", eventParticipantsObj);
                out.println(myObj.toString());
            }
        }
        else if((userNRIC != null) && (!userNRIC.equals(""))){
        	checkEventParticipantsNRIC = eventParticipantsManager.retrieveEventParticipant(userNRIC);
            if((userNRIC == null) || ((userNRIC.equals(""))) || (checkEventParticipantsNRIC == null)){
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to retrieve this user.");
                out.println(myObj.toString());
            }
            else {
                Gson gson = new Gson(); 
                JsonElement eventParticipantsObj = gson.toJsonTree(checkEventParticipantsNRIC);
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.add("eventParticipantsInfo", eventParticipantsObj);
                out.println(myObj.toString());
            }
        }
        else{
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to retrieve this event participants.");
            out.println(myObj.toString());
        }
        out.close();
	}

}
