package com.example.CommunityOutreach.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArticleGo
 */
@WebServlet("/ArticleGo")
public class ArticleGo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleGo() {
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
		
		String yourLat = request.getParameter("currentLat");
		String yourLon = request.getParameter("currentLon");
		
		String artLat = request.getParameter("dbLat");
		String artLon = request.getParameter("dbLon");
		
		double currentLat = Double.parseDouble(yourLat);
		double currentLon = Double.parseDouble(yourLon);
		double dbLat = Double.parseDouble(artLat);
		double dbLon = Double.parseDouble(artLon);

		String link = "http://maps.google.com/maps?saddr=" + currentLat + "," + currentLon + "&daddr=" + dbLat +"," + dbLon;
		response.sendRedirect(link);
	}

}
