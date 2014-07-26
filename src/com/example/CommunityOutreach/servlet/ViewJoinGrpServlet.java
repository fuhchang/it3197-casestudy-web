package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.PostManager;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.HobbyPost;

/**
 * Servlet implementation class ViewJoinGrpServlet
 */
@WebServlet("/ViewJoinGrpServlet")
public class ViewJoinGrpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewJoinGrpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String userName = null;
		Cookie[] cookies = request.getCookies();
		 if(cookies != null){
		        for(Cookie cookie : cookies){
		            if(cookie.getName().equals("userLogin")){
		                userName = cookie.getValue().toString();
		                break;
		            }
		        }
		 }
		 
		
		int id = Integer.parseInt(request.getParameter("id"));
		HobbyManager hobbyManager = new HobbyManager();
		Hobby hobby = hobbyManager.retrieveHobby(id);
		if(hobby.getAdminNric().equals(userName)){
			request.setAttribute("type", "button");
		}else{
			request.setAttribute("type", "hidden");
		}
		request.setAttribute("id", id);
		request.setAttribute("GrpName", hobby.getGrpName());
		request.setAttribute("category", hobby.getCategory());
		request.setAttribute("GrpDesc", hobby.getGrpDesc());
		PostManager pm = new PostManager();
		ArrayList<HobbyPost> postList = pm.retrievePost(id);
		request.setAttribute("postList", postList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewJoinHobbyGroup.jsp");
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewJoinHobbyGroup.jsp");
		requestDispatcher.forward(request, response);
	}

}
