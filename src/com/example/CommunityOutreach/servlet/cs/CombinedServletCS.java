package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.model.Article;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.Hobby;
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
		List<Event> eventList = em.retrieveAllEvents();
		
		HobbyManager hm = new HobbyManager();
		List<Hobby> hobbyList = hm.retrieveAllHobby();
		
		request.setAttribute("artList", artList);
		request.setAttribute("eventList", eventList);
		request.setAttribute("hobbyList", hobbyList);
		
		
		if((artList.size() == 0) || (artList == null)||(eventList.size() == 0) || (eventList == null)||(hobbyList.size() == 0) || (hobbyList == null)){
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
            JsonArray eventArray = new JsonArray();
            for(int i = 0; i< eventList.size();i++){
            	eventObj = gson.toJsonTree(eventList.get(i));
            	eventArray.add(eventObj);
            	myObj.add("eventList", eventArray);
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
            out.println(myObj.toString());
            
        }
        out.close();
	}

}
