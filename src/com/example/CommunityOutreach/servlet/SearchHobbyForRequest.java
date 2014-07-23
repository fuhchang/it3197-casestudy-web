package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.model.Hobby;

/**
 * Servlet implementation class SearchHobbyForRequest
 */
@WebServlet("/SearchHobbyForRequest")
public class SearchHobbyForRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchHobbyForRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		HobbyManager hm = new HobbyManager();
		ArrayList<Hobby> hobbyList;
		if(id == 1){
			hobbyList = hm.retrieveAllHobbyByCategory("Dance");
			request.setAttribute("hobbyList", hobbyList);
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			 requestDispatcher.forward(request, response);
		}else if(id ==2){
			hobbyList = hm.retrieveAllHobbyByCategory("Cooking");
			request.setAttribute("hobbyList", hobbyList);
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			 requestDispatcher.forward(request, response);
		}else if(id ==3){
			hobbyList = hm.retrieveAllHobbyByCategory("Gardening");
			request.setAttribute("hobbyList", hobbyList);
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			 requestDispatcher.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
