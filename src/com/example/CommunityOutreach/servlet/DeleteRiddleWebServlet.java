package com.example.CommunityOutreach.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.RiddleManager;

/**
 * Servlet implementation class DeleteRiddleWebServlet
 */
@WebServlet("/DeleteRiddleWebServlet")
public class DeleteRiddleWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRiddleWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RiddleManager riddleManager = new RiddleManager();
		riddleManager.deleteRiddle(Integer.parseInt(request.getParameter("riddleID")));
		riddleManager.deleteRiddleAns(Integer.parseInt(request.getParameter("riddleID")));
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("RetrieveAllRiddleWebServlet");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
