package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

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
import com.example.CommunityOutreach.util.Settings;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateEventParticipantServlet
 */
@WebServlet("/createEventParticipant")
public class CreateEventParticipantServlet extends HttpServlet implements Settings{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventParticipantServlet() {
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
        
        String userNRIC = request.getParameter("userNRIC");
        
		int eventID = 0;
        if(request.getParameter("eventID") != null){
        	try{
        		eventID = Integer.parseInt(request.getParameter("eventID"));
        	}
        	catch(Exception e){
        		eventID = 0;
        	}
        }
		
		String eventDateTimeFrom = request.getParameter("eventDateTimeFrom");
		Date dateTimeFrom = null;
		try {
			dateTimeFrom = sqlDateTimeFormatter.parse(eventDateTimeFrom);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        UserManager userManager = new UserManager();
		EventParticipantsManager eventParticipantsManager = new EventParticipantsManager();
		EventManager eventManager = new EventManager();
        EventParticipants eventParticipant = new EventParticipants(eventID,userNRIC,dateTimeFrom,0);
        User checkUser = userManager.retrieveUser(userNRIC);
        Event checkEvent = eventManager.retrieveEvent(eventID);
        EventParticipants checkEventParticipants= eventParticipantsManager.retrieveEventParticipant(eventID,userNRIC);
        
        if((checkUser == null) || (userNRIC == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","There is no record of such user.");
            out.println(myObj.toString());
            return;
        }
        if(checkUser.getActive() == 0){
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","This user has already been obsoleted.");
            out.println(myObj.toString());
            return;
        }
        if((checkEvent == null) || (checkEvent.getEventID() == 0)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","There is no record of such event.");
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
        if((checkEventParticipants != null)){
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","This user has already join the event.");
            out.println(myObj.toString());
            return;
        }
        boolean isEventParticipantCreated = false;
        try{
        	isEventParticipantCreated = eventParticipantsManager.createEventParticipant(eventParticipant);
        	if(!isEventParticipantCreated){
        		JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to create event participant successfully.");
                out.println(myObj.toString());
        	}
        	else{
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.addProperty("message","Event participant created successfully.");
                out.println(myObj.toString());
        	}
        }
        catch(Exception ex){
        	ex.printStackTrace();
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to create event participant successfully.");
            out.println(myObj.toString());
        }
	}

}
