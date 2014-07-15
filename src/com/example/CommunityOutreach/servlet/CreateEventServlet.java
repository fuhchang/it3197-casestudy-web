package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.User;
import com.example.CommunityOutreach.util.Settings;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateEventServlet
 */
@WebServlet("/createEvent")
public class CreateEventServlet extends HttpServlet implements Settings{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
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
        
        //int eventID = Integer.parseInt(request.getParameter("eventID"));
        //Real Values
        String eventName = request.getParameter("eventName");
        String eventCategory = request.getParameter("eventCategory");
        String eventDescription = request.getParameter("eventDescription");
        String eventType = request.getParameter("eventType");
        String occurence = request.getParameter("occurence");
        String eventDateTimeFrom = request.getParameter("eventDateTimeFrom");
        String eventDateTimeTo = request.getParameter("eventDateTimeTo");
        String eventLocation = request.getParameter("eventLocation");
        int noOfParticipantsAllowed = 0;
        if(request.getParameter("noOfParticipants") != null){
        	try{
        		noOfParticipantsAllowed = Integer.parseInt(request.getParameter("noOfParticipants"));
        	}
        	catch(Exception e){
        		noOfParticipantsAllowed = 0;
        	}
        }
        Date dateTimeFrom = null;
        Date dateTimeTo = null;
        try {
        	System.out.println(request.getParameter("web"));
        	if(request.getParameter("web").equals("true")){
        		Calendar cFrom = Calendar.getInstance();
        		dateTimeFrom = webSqlDateTimeFormatter.parse(eventDateTimeFrom);
        		cFrom.setTime(dateTimeFrom);
        		cFrom.set(Calendar.SECOND, 0);
        		dateTimeFrom = cFrom.getTime();
        		
        		Calendar cTo = Calendar.getInstance();
        		dateTimeTo = webSqlDateTimeFormatter.parse(eventDateTimeTo);
        		cTo.set(Calendar.SECOND, 0);
        		cTo.setTime(dateTimeTo);
        		dateTimeTo = cTo.getTime();
        	}
        	else{
        		dateTimeFrom = sqlDateTimeFormatter.parse(eventDateTimeFrom);
        		dateTimeTo = sqlDateTimeFormatter.parse(eventDateTimeTo);
        	}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Testing Values
        //System.out.println("Event No: " + eventID);
		String eventAdminNRIC = "S9512233X";
		
		EventManager eventManager = new EventManager();
		UserManager userManager = new UserManager();
        Event event = new Event(0,eventAdminNRIC,eventName,eventCategory,eventDescription,eventType,dateTimeFrom,dateTimeTo,occurence,eventLocation,noOfParticipantsAllowed,1);
        User user = userManager.retrieveUser(eventAdminNRIC);
        if(user == null){
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","There is no record of such user. Unable to create event.");
            out.println(myObj.toString());
            return;
        }
        else{
	        if(user.getActive() == 0){
	    		JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message","There is no record of such user. Unable to create event.");
	            out.println(myObj.toString());
	            return;
	        }
        }
        
        boolean isEventCreated = false;
        try{
        	isEventCreated = eventManager.createEvent(event);
        	if(!isEventCreated){
        		JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to create event successfully.");
                out.println(myObj.toString());
        	}
        	else{
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.addProperty("message","Event created successfully.");
                out.println(myObj.toString());
        	}
        }
        catch(Exception ex){
        	ex.printStackTrace();
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to create event successfully.");
            out.println(myObj.toString());
        }
        
        if(request.getParameter("web").equals("true")){
        	RequestDispatcher rd = request.getRequestDispatcher("event.jsp");
        	rd.forward(request, response);
		}
	}

}
