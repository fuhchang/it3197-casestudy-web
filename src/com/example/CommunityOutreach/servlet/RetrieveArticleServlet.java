package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.model.Article;
import com.example.CommunityOutreach.model.Event;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class RetrieveArticleServlet
 */
@WebServlet("/retrieveArticle")
public class RetrieveArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveArticleServlet() {
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
        
        int articleID;
        if((request.getParameter("articleID") == null) || (request.getParameter("articleID").equals(""))){
        	articleID = 0;
        }
        else{
        	articleID = Integer.parseInt(request.getParameter("articleID"));
        }
        System.out.println("Article No: " + articleID);
 
        ArticleManager articleManager = new ArticleManager();
        Article article = articleManager.retrieveArticle(articleID);
 
        if((articleID == 0) || (article == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to retrieve this article.");
            out.println(myObj.toString());
        }
        else {
            Gson gson = new Gson(); 
            JsonElement articleObj = gson.toJsonTree(article);
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", true);
            myObj.add("articleInfo", articleObj);
            out.println(myObj.toString());
        }
        out.close();
	}

}
