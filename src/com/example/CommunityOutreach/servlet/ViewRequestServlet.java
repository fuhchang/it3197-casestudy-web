package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.requestHobbyManager;
import com.example.CommunityOutreach.model.RequestHobby;

/**
 * Servlet implementation class ViewRequestServlet
 */
@WebServlet("/ViewRequestServlet")
public class ViewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int grpID = Integer.parseInt(request.getParameter("grpID"));
		
		requestHobbyManager rhm = new requestHobbyManager();
		ArrayList<RequestHobby> reqList = rhm.retrieveAllRequest(grpID);
		ArrayList<String> statusList = new ArrayList<String>();
		for(int i=0; i< reqList.size(); i++){
			if( reqList.get(i).getRequestStatus().equals("pending")){
				statusList.add("submit");
	
			}else{
				statusList.add("hidden");
				
			}
		}
		request.setAttribute("statusList", statusList);
		request.setAttribute("reqList", reqList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewRequest.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			int grpID = Integer.parseInt(request.getParameter("grpID"));
		
		requestHobbyManager rhm = new requestHobbyManager();
		ArrayList<RequestHobby> reqList = rhm.retrieveAllRequest(grpID);
		ArrayList<String> statusList = new ArrayList<String>();
		for(int i=0; i< reqList.size(); i++){
			if(reqList.get(i).equals("pending")){
				statusList.add("submit");
			}else{
				statusList.add("hidden");
			}
		}
		request.setAttribute("statusList", statusList);
		request.setAttribute("reqList", reqList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewRequest.jsp");
		requestDispatcher.forward(request, response);
	}

}
