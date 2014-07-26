package com.example.CommunityOutreach.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.requestHobbyManager;

/**
 * Servlet implementation class accpetRequestServlet
 */
@WebServlet("/accpetRequestServlet")
public class accpetRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accpetRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int requestID = Integer.parseInt(request.getParameter("id"));
		int grpID = Integer.parseInt(request.getParameter("grpID"));
		requestHobbyManager rhm =  new requestHobbyManager();
		rhm.AccpetRequest(requestID);
		request.setAttribute("grpID", grpID);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewRequestServlet");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
