package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.data.EventLocationDetailManager;
import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.EventParticipantsManager;
import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.RiddleManager;
import com.example.CommunityOutreach.model.Article;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.EventLocationDetail;
import com.example.CommunityOutreach.model.EventParticipants;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.Riddle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CombinedServletCS
 */
@WebServlet("/CombinedServletCS")
public class CombinedServletCS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CombinedServletCS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
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
        
        
        
        ArticleManager am = new ArticleManager();
		List<Article> artList = am.getLatestArticle();
		
		
		EventManager em = new EventManager();
		List<Event> eventList = em.retrieveAllLatestEvents();
		
		EventLocationDetailManager elm = new EventLocationDetailManager();
		EventParticipantsManager epm = new EventParticipantsManager();
		List<EventLocationDetail> eventLocationList = new ArrayList<EventLocationDetail>();
		List<ArrayList<EventParticipants>> eventParticipantsList = new ArrayList<ArrayList<EventParticipants>>();
		for(int i=0;i<eventList.size();i++){
			eventLocationList.add(elm.retrieveEventLocationDetails(eventList.get(i).getEventID()));			
			eventParticipantsList.add(epm.retrieveEventParticipant(eventList.get(i).getEventID()));
		}
		
		HobbyManager hm = new HobbyManager();
		List<Hobby> hobbyList = hm.retrieveAllHobby();
		
		RiddleManager rm = new RiddleManager();
		List<Riddle> riddleList = rm.retrieveAllRiddle();
		
		request.setAttribute("artList", artList);
		request.setAttribute("eventList", eventList);
		request.setAttribute("eventLocationList", eventLocationList);
		request.setAttribute("eventParticipantsList", eventParticipantsList);
		request.setAttribute("hobbyList", hobbyList);
		request.setAttribute("riddleList", riddleList);
		
		if((artList.size() == 0) || (artList == null) || (eventList.size() == 0) || (eventList == null)  || (eventLocationList.size() == 0) || (eventLocationList == null)  || (eventParticipantsList.size() == 0) || (eventParticipantsList == null) ||(hobbyList.size() == 0) || (hobbyList == null)||(riddleList.size() == 0) || (riddleList == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message", "Unable to retrieve.");
            out.println(myObj.toString());
        }
        else {
            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
        	
            JsonElement eventObj;
            JsonElement eventLocationObj;
            JsonElement eventParticipantsObj;
            JsonArray eventArray = new JsonArray();
            JsonArray eventLocationArray = new JsonArray();
            JsonArray eventParticipantsArray = new JsonArray();
            for(int i = 0; i< eventList.size();i++){
            	eventObj = gson.toJsonTree(eventList.get(i));
            	eventArray.add(eventObj);
            	myObj.add("eventList", eventArray);
            }
            
            for(int i = 0; i< eventLocationList.size();i++){
            	eventLocationObj = gson.toJsonTree(eventLocationList.get(i));
            	eventLocationArray.add(eventLocationObj);
            	myObj.add("eventLocationList", eventLocationArray);
            }
            
            for(int i = 0; i< eventParticipantsList.size();i++){
            	eventParticipantsObj = gson.toJsonTree(eventParticipantsList.get(i));
            	eventParticipantsArray.add(eventParticipantsObj);
            	myObj.add("eventParticipantsList", eventParticipantsArray);
            }
            
            
            JsonElement hobbyObj;
            JsonArray hobbyArray = new JsonArray();
            for(int i = 0; i< hobbyList.size();i++){
            	hobbyObj = gson.toJsonTree(hobbyList.get(i));
            	hobbyArray.add(hobbyObj);
            	myObj.add("hobbyList", hobbyArray);
            }
            
            JsonElement articleObj;
        	JsonArray articleArray = new JsonArray();
            for(int i=0;i<artList.size();i++){
            	articleObj = gson.toJsonTree(artList.get(i));
            	articleArray.add(articleObj);
            	myObj.add("artList", articleArray);
            }
            
            JsonElement riddleObj;
        	JsonArray riddleArray = new JsonArray();
            for(int i=0;i<riddleList.size();i++){
            	riddleObj = gson.toJsonTree(riddleList.get(i));
            	riddleArray.add(riddleObj);
            	myObj.add("riddleList", riddleArray);
            }
            
            out.println(myObj.toString());
            
        }
        out.close();
	}

}
