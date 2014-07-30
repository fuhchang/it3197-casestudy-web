package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.PostManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.HobbyPost;
import com.example.CommunityOutreach.model.User;

/**
 * Servlet implementation class CreatePostWebServlet
 */
@WebServlet("/CreatePostWebServlet")
public class CreatePostWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePostWebServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
			String title = request.getParameter("gtitle");
			String grpDesc = request.getParameter("gDesc");
			String coordinates = request.getParameter("coordinates");
	
			HobbyPost post = new HobbyPost();
			post.setGrpID(Integer.parseInt(id));
			post.setContent(grpDesc);
			post.setPostTitle(title);
			post.setNric(userName);
			StringTokenizer st = new StringTokenizer(coordinates, ",");
			double Lat;
			double Lng;
			String[] temp = new String[2];
			int i = 0;
			while (st.hasMoreElements()) {
				temp[i] = (String) st.nextElement();
				i++;
			}

			Lat = Double.parseDouble(temp[0]);
			Lng = Double.parseDouble(temp[1]);

			post.setLat(Lat);
			post.setLng(Lng);
			
			PostManager pm = new PostManager();
			boolean result = pm.createPost(post);
			if(result){
			UserManager um = new UserManager();
			User user = um.retrieveUser(userName);
			um.updatePoints(user.getNric(), user.getPoints() + 50);
			request.setAttribute("id", id);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewJoinGrpServlet");
			requestDispatcher.forward(request, response);
			}
		}

	}


