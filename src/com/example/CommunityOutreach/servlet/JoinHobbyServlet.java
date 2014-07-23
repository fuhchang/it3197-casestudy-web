package com.example.CommunityOutreach.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyMembersManager;
import com.example.CommunityOutreach.model.HobbyMembers;

/**
 * Servlet implementation class JoinHobbyServlet
 */
@WebServlet("/JoinHobbyServlet")
public class JoinHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinHobbyServlet() {
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
		 
		 String id = request.getParameter("id");
		 HobbyMembers hm = new HobbyMembers();
		 hm.setGroupID(Integer.parseInt(id));
		 hm.setRole("members");
		 hm.setUserNRIC(userName);
		 hm.setActive(1);
		 
		 HobbyMembersManager hmManager = new HobbyMembersManager();
		 boolean result = hmManager.createHobbyMember(hm);
		 System.out.println(result);
		 
		 RequestDispatcher requestDispatcher = request.getRequestDispatcher("RetrieveAllHobbyServlet");
		 requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
