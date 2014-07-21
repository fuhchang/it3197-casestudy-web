package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
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
 * Servlet implementation class DisplayArticleMainServlet2
 */
@WebServlet("/DisplayArticleMainServlet2")
public class DisplayArticleMainServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayArticleMainServlet2() {
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
        int distSelected;
        double currentLat;
        double currentLon;
        
        if(request.getParameter("distSelected").equals("")||request.getParameter("currentLat").equals("")||request.getParameter("currentLon").equals("")){
	       // RequestDispatcher rd = request.getRequestDispatcher("articleDisplayMainMobileView.jsp");
			//rd.forward(request,response);
        	distSelected=0;
        	currentLat=0;
        	currentLon=0;
        }
        else{
        	distSelected = Integer.parseInt(request.getParameter("distSelected"));
        	currentLat = Double.parseDouble(request.getParameter("currentLat"));
			currentLon = Double.parseDouble(request.getParameter("currentLon"));
        }
        
        
				
				//int distSelected = Integer.parseInt(request.getParameter("distSelected"));
				
				System.out.println(currentLat);
				System.out.println(currentLon);
				System.out.println(distSelected);
				
				
				ArticleManager am = new ArticleManager();
				List<Article> artList = am.retrieveAllApprovedArticlesWithinDistance(currentLat, currentLon, distSelected);
				
				
				if(0<distSelected){
					Collections.sort(artList,new DistAscComparator());
				}
				
				request.setAttribute("artList", artList);
				
				
				if(currentLat==0){
					request.setAttribute("selectedDist", 0);
				}
				else{
				
					request.setAttribute("selectedDist", distSelected);
				}
				
				request.setAttribute("currentLat", currentLat);
				request.setAttribute("currentLon",currentLon);
				
		        RequestDispatcher rd = request.getRequestDispatcher("articleDisplayMainMobileView.jsp");
				rd.forward(request,response);
		
        
		
	}
	
	
	/*
	 * sort distance in ascending order
	 */
	private class DistAscComparator implements Comparator<Article> {

		@Override
		public int compare(Article arg0, Article arg1) {
			// TODO Auto-generated method stub
			return Double.compare(arg0.getDistToSort(), arg1.getDistToSort());
		}

	}

}
