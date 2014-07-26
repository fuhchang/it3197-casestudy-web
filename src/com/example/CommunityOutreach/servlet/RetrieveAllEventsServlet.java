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

import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Event;
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
            ArrayList<Event> eventArrList = eventManager.retrieveAllEvents();

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
	        	JsonElement eventObj;
	        	JsonArray eventArray = new JsonArray();
	            for(int i=0;i<eventArrList.size();i++){
	            	eventObj = gson.toJsonTree(eventArrList.get(i));
	            	eventArray.add(eventObj);
	            	myObj.add("eventInfo", eventArray);
	            }
	            out.println(myObj.toString());
	        }

		}
	}
}
