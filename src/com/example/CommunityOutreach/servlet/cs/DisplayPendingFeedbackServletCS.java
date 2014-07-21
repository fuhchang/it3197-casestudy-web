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
import com.example.CommunityOutreach.model.Article;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DisplayPendingFeedbackServletCS
 */
@WebServlet("/DisplayPendingFeedbackServletCS")
public class DisplayPendingFeedbackServletCS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayPendingFeedbackServletCS() {
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
		List<Article> artList = am.retrieveAllPendingOfficerArticles();
	/*	for(int i=0; i<artList.size();i++){
			artList.get(i).getTitle();
			System.out.println();
			System.out.println(artList.get(i).getTitle());
		}*/
		System.out.println(artList.size());
		request.setAttribute("artList", artList);
		
		
		if((artList.size() == 0) || (artList == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message", "Unable to retrieve articles.");
            out.println(myObj.toString());
        }
        else {
            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
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
