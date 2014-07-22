package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.PostManager;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.HobbyPost;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ViewGroupServlet
 */
@WebServlet("/ViewGroupServlet")
public class ViewGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		HobbyManager hobbyManager = new HobbyManager();
		Hobby hobby = hobbyManager.retrieveHobby(id);
		request.setAttribute("id", id);
		request.setAttribute("GrpName", hobby.getGrpName());
		request.setAttribute("category", hobby.getCategory());
		request.setAttribute("GrpDesc", hobby.getGrpDesc());
		PostManager pm = new PostManager();
		ArrayList<HobbyPost> postList = pm.retrievePost(id);
		
		request.setAttribute("postList", postList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewHobbyGroup.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		HobbyManager hobbyManager = new HobbyManager();
		Hobby hobby = hobbyManager.retrieveHobby(id);
		request.setAttribute("id", id);
		request.setAttribute("GrpName", hobby.getGrpName());
		request.setAttribute("category", hobby.getCategory());
		request.setAttribute("GrpDesc", hobby.getGrpDesc());
		PostManager pm = new PostManager();
		ArrayList<HobbyPost> postList = pm.retrievePost(id);
		
		request.setAttribute("postList", postList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewHobbyGroup.jsp");
		requestDispatcher.forward(request, response);
	}

}
