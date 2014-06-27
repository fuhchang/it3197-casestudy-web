package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.User;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class EditEventServlet
 */
@WebServlet("/editEvent")
public class EditEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventServlet() {
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
        //Real Values
        /*String name = request.getParameter("name");
        String password = request.getParameter("password");
        String contactNo = request.getParameter("contactNo");
        String address = request.getParameter("address");
        String email = request.getParameter("email");*/
        
        //Testing Values
		String eventAdminNRIC = "S9512233X";
		String eventName = "Tx";
		String eventCategory = "Tx";
		String eventDescription = "Tx";
		String eventType = "Tx";
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,27);
		calendar.set(Calendar.MONTH,6);
		calendar.set(Calendar.YEAR,2014);
		calendar.set(Calendar.HOUR_OF_DAY,9);
		calendar.set(Calendar.MINUTE,00);
		calendar.set(Calendar.SECOND, 00);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.DAY_OF_MONTH,27);
		calendar2.set(Calendar.MONTH,6);
		calendar2.set(Calendar.YEAR,2014);
		calendar2.set(Calendar.HOUR_OF_DAY,13);
		calendar2.set(Calendar.MINUTE,00);
		calendar2.set(Calendar.SECOND, 00);
		
		String occurence = "Xy";
		String eventLocation = "Xy";
		int noOfParticipantsAllowed = 0;
        
        EventManager eventManager = new EventManager();
        Event event = new Event(eventID,eventAdminNRIC,eventName,eventCategory,eventDescription,eventType,calendar.getTime(),calendar2.getTime(),occurence,eventLocation,noOfParticipantsAllowed,1);
        Event checkEvent = eventManager.retrieveEvent(eventID);
        if((checkEvent == null) || (eventID == 0)){
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
        
        boolean isEventEdited = false;
        try{
        	isEventEdited = eventManager.editEvent(event);
        	if(!isEventEdited){
        		JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to edit event successfully.");
                out.println(myObj.toString());
        	}
        	else{
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.addProperty("message","Event edited successfully.");
                out.println(myObj.toString());
        	}
        }
        catch(Exception ex){
        	ex.printStackTrace();
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to edit event successfully.");
            out.println(myObj.toString());
        }
	}

}
