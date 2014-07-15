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
 * Servlet implementation class RetrieveAllHobbyServlet
 */
@WebServlet("/RetrieveAllHobbyServlet")
public class RetrieveAllHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAllHobbyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HobbyManager hm = new HobbyManager();
		ArrayList<Hobby> hobbyList = hm.retrieveAllHobby();
		
		for(int i=0; i<hobbyList.size();i++){
			System.out.println(hobbyList.get(i).getGrpName());
			System.out.println(hobbyList.get(i).getGrpDesc());
		}
		if(hobbyList.size() > 0){
			request.setAttribute("hobbyList", hobbyList);		
			}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hobby.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HobbyManager hm = new HobbyManager();
		ArrayList<Hobby> hobbyList = hm.retrieveAllHobby();
		
		for(int i=0; i<hobbyList.size();i++){
			System.out.println(hobbyList.get(i).getGrpName());
		}
		if(hobbyList.size() > 0){
			request.setAttribute("hobbyList", hobbyList);		
			}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hobby.jsp");
		requestDispatcher.forward(request, response);
	}

}
