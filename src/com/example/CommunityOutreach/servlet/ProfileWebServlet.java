package com.example.CommunityOutreach.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.User;

/**
 * Servlet implementation class ProfileWebServlet
 */
@WebServlet("/ProfileWebServlet")
public class ProfileWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		String nric = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("userLogin")){
					nric = cookie.getValue().toString();
					break;
	            }
	        }
		}
		 
		UserManager userManager = new UserManager();
		User user = userManager.retrieveUser(nric);
		request.setAttribute("user", user);

		request.setAttribute("additionalPt", 0);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Profile.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		String nric = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("userLogin")){
					nric = cookie.getValue().toString();
					break;
	            }
	        }
		}
		 
		UserManager userManager = new UserManager();
		User user = userManager.retrieveUser(nric);
		request.setAttribute("user", user);
		
		if(request.getParameter("additionalPt") != null && !request.getParameter("additionalPt").equals("")){
			userManager.updatePoints(nric, user.getPoints()+Integer.parseInt(request.getParameter("additionalPt")));
		}
		
		response.sendRedirect("ProfileWebServlet");
	}

}
