package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DisplayArticleMainServlet
 */
@WebServlet("/DisplayArticleMainServlet")
public class DisplayArticleMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayArticleMainServlet() {
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
		ArticleManager am = new ArticleManager();
		List<Article> artList = am.retrieveAllApprovedArticles();
	/*	for(int i=0; i<artList.size();i++){
			artList.get(i).getTitle();
			System.out.println();
			System.out.println(artList.get(i).getTitle());
		}*/
		
		request.setAttribute("artList", artList);
		
		RequestDispatcher rd = request.getRequestDispatcher("articleDisplayMain.jsp");
		rd.forward(request,response);
		
	//	HttpSession session = request.getSession(true);
	//	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
	//	ArrayList<Article> articlesArray=new ArticleManager().retrieveAllArticles();
	//	session.setAttribute("articlesArray", gson.toJson(articlesArray));
		//System.out.println("HIIII" + articlesArray.size());
		//RequestDispatcher rd = request.getRequestDispatcher("DisplayPacking.jsp");
		//rd.forward(request,response);
		
	/*	PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        ArticleManager articleManager = new ArticleManager();
        ArrayList<Article> articleArrList = articleManager.retrieveAllArticles();
        
        System.out.println(articleArrList.size());
 
        if((articleArrList.size() == 0) || (articleArrList == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message", "Unable to retrieve users.");
            out.println(myObj.toString());
        }
        else {
            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
        //    myObj.addProperty("success", true);
        	JsonElement articlesObj;
        	JsonArray articlesArray = new JsonArray();
            for(int i=0;i<articleArrList.size();i++){
            	articlesObj = gson.toJsonTree(articleArrList.get(i));
            	articlesArray.add(articlesObj);
            	myObj.add("articlesInfo", articlesArray);
            }
            out.println(myObj.toString());
       //     System.out.println((articlesArray.size()));
            session.setAttribute("articlesArray", articlesArray);
        }
        
        
        out.close();*/
        
     //   RequestDispatcher rd = request.getRequestDispatcher("articleDisplayMain.jsp");
	//	rd.forward(request,response);
	}

}
