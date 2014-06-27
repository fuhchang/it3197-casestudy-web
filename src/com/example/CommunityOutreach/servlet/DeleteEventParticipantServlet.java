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
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.EventParticipants;
import com.example.CommunityOutreach.model.User;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DeleteEventParticipantServlet
 */
@WebServlet("/deleteEventParticipant")
public class DeleteEventParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEventParticipantServlet() {
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
        System.out.println("Event No: " + eventID);
        String userNRIC = request.getParameter("userNRIC");

        UserManager userManager = new UserManager();
        User checkUser = userManager.retrieveUser(userNRIC);
        EventManager eventManager = new EventManager();
        Event checkEvent = eventManager.retrieveEvent(eventID);
        EventParticipantsManager eventParticipantsManager = new EventParticipantsManager();
        EventParticipants checkEventParticipant = eventParticipantsManager.retrieveEventParticipant(eventID, userNRIC);
        
        if((checkUser == null) || (userNRIC == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","There is no record of such user.");
            out.println(myObj.toString());
            return;
        }
        if((checkEvent == null) || (eventID == 0)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","There is no record of such event.");
            out.println(myObj.toString());
            return;
        }
        if(checkEventParticipant == null){
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","This is no record of such event participants.");
            out.println(myObj.toString());
            return;
        }
        
        if(checkEvent.getActive() == 0){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","This event has already been obsoleted.");
            out.println(myObj.toString());
            return;
        }
        else if(checkUser.getActive() == 0){
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","This user has already been obsoleted.");
            out.println(myObj.toString());
            return;
        }
        else{
	        boolean isEventObsoleted = false;
	        try{
	        	isEventObsoleted = eventManager.obsoleteEvent(eventID);
	        	if(!isEventObsoleted){
	        		JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", false);
	                myObj.addProperty("message","Unable to delete event participant successfully.");
	                out.println(myObj.toString());
	        	}
	        	else{
	                JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", true);
	                myObj.addProperty("message","Event participant deleted successfully.");
	                out.println(myObj.toString());
	        	}
	        }
	        catch(Exception ex){
	        	ex.printStackTrace();
	    		JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message","Unable to delete event participant successfully.");
	            out.println(myObj.toString());
	        }
        }
	}

}
