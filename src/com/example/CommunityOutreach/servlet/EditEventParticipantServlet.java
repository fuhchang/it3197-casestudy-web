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
import com.example.CommunityOutreach.data.EventParticipantsManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.EventParticipants;
import com.example.CommunityOutreach.model.User;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class EditEventParticipantServlet
 */
@WebServlet("/editEventParticipant")
public class EditEventParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventParticipantServlet() {
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
        //Real Values
        /*String name = request.getParameter("name");
        String password = request.getParameter("password");
        String contactNo = request.getParameter("contactNo");
        String address = request.getParameter("address");
        String email = request.getParameter("email");*/
        
        //Testing Values
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,27);
		calendar.set(Calendar.MONTH,6);
		calendar.set(Calendar.YEAR,2014);
		calendar.set(Calendar.HOUR_OF_DAY,9);
		calendar.set(Calendar.MINUTE,00);
		calendar.set(Calendar.SECOND, 00);
        
		UserManager userManager = new UserManager();
		EventParticipantsManager eventParticipantsManager = new EventParticipantsManager();
        EventManager eventManager = new EventManager();
        Event checkEvent = eventManager.retrieveEvent(eventID);
        User checkUser = userManager.retrieveUser(userNRIC);
        EventParticipants eventParticipants = new EventParticipants(eventID,userNRIC,calendar.getTime(),1);
        EventParticipants checkEventParticipant = eventParticipantsManager.retrieveEventParticipant(eventID, userNRIC);
        
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
        if(checkEventParticipant == null){
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","This is no record of such event participants.");
            out.println(myObj.toString());
            return;
        }
        
        boolean isEventParticipantsEdited = false;
        try{
        	isEventParticipantsEdited = eventParticipantsManager.editEventParticipant(eventParticipants);
        	if(!isEventParticipantsEdited){
        		JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to edit event participants successfully.");
                out.println(myObj.toString());
        	}
        	else{
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.addProperty("message","Event participants edited successfully.");
                out.println(myObj.toString());
        	}
        }
        catch(Exception ex){
        	ex.printStackTrace();
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to edit event participants successfully.");
            out.println(myObj.toString());
        }
	}

}
