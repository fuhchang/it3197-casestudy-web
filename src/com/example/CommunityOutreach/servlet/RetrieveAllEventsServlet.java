package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.EventLocationDetailManager;
import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.EventParticipantsManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.EventLocationDetail;
import com.example.CommunityOutreach.model.EventParticipants;
import com.example.CommunityOutreach.model.User;
import com.example.CommunityOutreach.util.Settings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class RetrieveAllEventsServlet
 */
@WebServlet("/retrieveAllEvents")
public class RetrieveAllEventsServlet extends HttpServlet implements Settings{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAllEventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        if(request.getParameter("web").equals("true")){
	        EventManager eventManager = new EventManager();
	        ArrayList<Event> eventArrList = eventManager.retrieveAllActiveEvents();
	        
	        for(int i=0;i<eventArrList.size();i++){
	        	String eventDateTimeFrom = displayFormatter.format(eventArrList.get(i).getEventDateTimeFrom());
	        	String eventDateTimeTo = displayFormatter.format(eventArrList.get(i).getEventDateTimeTo());
	        	try {
					eventArrList.get(i).setEventDateTimeFrom(displayFormatter.parse(eventDateTimeFrom));
	            	eventArrList.get(i).setEventDateTimeTo(displayFormatter.parse(eventDateTimeTo));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        UserManager um = new UserManager();
	        boolean userDeleted = um.deleteUser("S9523803C");
	        System.out.println(userDeleted);
			request.setAttribute("eventArrList", eventArrList);
	    	RequestDispatcher rd = request.getRequestDispatcher("viewAllEvents.jsp");
	    	rd.forward(request,response);
        }
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
        
        if(!request.getParameter("web").equals("true")){

            EventManager eventManager = new EventManager();
            EventLocationDetailManager eventLocationDetailsManager = new EventLocationDetailManager();
            EventParticipantsManager eventParticipantsManager = new EventParticipantsManager();
            ArrayList<Event> eventArrList = eventManager.retrieveAllEventsSorted();
            ArrayList<EventLocationDetail> eventLocationDetailArrList = new ArrayList<EventLocationDetail>();
            ArrayList<EventParticipants> eventParticipantsArrList = eventParticipantsManager.retrieveEventParticipant(request.getParameter("userNRIC"));
            for(int i=0;i<eventArrList.size();i++){
            	if(eventArrList.get(i).getActive() == 1){
            		eventLocationDetailArrList.add(eventLocationDetailsManager.retrieveEventLocationDetails(eventArrList.get(i).getEventID()));
            	}
            }
            
    		request.setAttribute("eventArrList", eventArrList);
	        if((eventArrList.size() == 0) || (eventArrList == null)){
	            JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message", "Unable to retrieve users.");
	            out.println(myObj.toString());
	        }
	        else {
	            Gson gson = new Gson();
	            JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", true);
	        	JsonElement eventObj, eventLocationObj, eventParticipantsObj;
	        	JsonArray eventArray = new JsonArray();
	        	JsonArray eventLocationArray = new JsonArray();
	        	JsonArray eventParticipantsArray = new JsonArray();
	            for(int i=0;i<eventArrList.size();i++){
	            	eventObj = gson.toJsonTree(eventArrList.get(i));
	            	eventArray.add(eventObj);
	            	myObj.add("eventInfo", eventArray);
	            }
	            for(int i=0;i<eventLocationDetailArrList.size();i++){
	            	eventLocationObj = gson.toJsonTree(eventLocationDetailArrList.get(i));
	            	eventLocationArray.add(eventLocationObj);
	            	myObj.add("eventLocationInfo", eventLocationArray);
	            }
	            for(int i=0;i<eventParticipantsArrList.size();i++){
	            	eventParticipantsObj = gson.toJsonTree(eventParticipantsArrList.get(i));
	            	eventParticipantsArray.add(eventParticipantsObj);
	            	myObj.add("eventParticipantsInfo", eventParticipantsArray);
	            }
	            out.println(myObj.toString());
	        }

		}
	}
}
