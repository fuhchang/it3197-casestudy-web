package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;

/**
 * Servlet implementation class DisplayArticleMainServlet3
 */
@WebServlet("/DisplayArticleMainServlet3")
public class DisplayArticleMainServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayArticleMainServlet3() {
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
		
        //String l = request.getParameter("currentLat");
        
		double currentLat = Double.parseDouble(request.getParameter("currentLat"));
		double currentLon = Double.parseDouble(request.getParameter("currentLon"));
		int distSelected = Integer.parseInt(request.getParameter("distSelected"));
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(currentLat);
		System.out.println(currentLon);
		System.out.println(distSelected);
		
		
		ArticleManager am = new ArticleManager();
		List<Article> artList = am.retrieveAllApprovedArticlesWithinDistance(currentLat, currentLon, distSelected);
		
		request.setAttribute("currentLat", currentLat);
		request.setAttribute("currentLon", currentLon);
		request.setAttribute("distSelected", distSelected);
		
		request.setAttribute("artList", artList);
		
        RequestDispatcher rd = request.getRequestDispatcher("articleDisplayMainStats.jsp");
		rd.forward(request,response);
		
	}

}
