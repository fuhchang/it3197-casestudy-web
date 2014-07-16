package com.example.CommunityOutreach.servlet.cs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.PostManager;
import com.example.CommunityOutreach.model.HobbyPost;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreatePostServlet
 */
@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePostServlet() {
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
		boolean result;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		String grpID = request.getParameter("grpID");
		String postContent = request.getParameter("postContent");
		String postLat = request.getParameter("postLat");
		String postLng = request.getParameter("postLng");
		
		HobbyPost post = new HobbyPost();
		post.setGrpID(Integer.parseInt(grpID));
		System.out.println(grpID);
		post.setContent(postContent);
		if (postLat != "" && postLng != "") {
			post.setLat(Double.parseDouble(postLat));
			post.setLng(Double.parseDouble(postLng));
		}
		PostManager postmanager = new PostManager();
		result = postmanager.createPost(post);

		if (result) {
			JsonObject myObj = new JsonObject();
			myObj.addProperty("success", true);
			myObj.addProperty("message", "Hobby created successfully.");
			out.println(myObj.toString());
		}
	}

}
