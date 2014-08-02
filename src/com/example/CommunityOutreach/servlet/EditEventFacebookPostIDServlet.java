package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.model.Event;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class EditEventFacebookPostIDServlet
 */
@WebServlet("/editEventFBPostID")
public class EditEventFacebookPostIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventFacebookPostIDServlet() {
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
        
        EventManager eventManager = new EventManager();
        Event event = new Event();
        event.setEventID(Integer.parseInt(request.getParameter("eventID")));
        event.setEventFBPostID(Integer.parseInt(request.getParameter("eventFBPostID")));
        boolean editEventFBPostID = eventManager.editEventFBPostID(event);
    	if(editEventFBPostID){
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Event Facebook Post ID edited event successfully.");
            out.println(myObj.toString());
    	}
    	else{
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
            myObj.addProperty("message","Unable to edit Event Facebook Post ID");
            out.println(myObj.toString());
    	}
	}

}
