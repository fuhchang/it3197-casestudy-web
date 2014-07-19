package com.example.CommunityOutreach.servlet.cs;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.HobbyMembersManager;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.HobbyMembers;
import com.example.CommunityOutreach.model.User;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateHobbyServlet
 */
@WebServlet("/CreateHobbyServlet")
public class CreateHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateHobbyServlet() {
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
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		String title = request.getParameter("gtitle");
		String category = request.getParameter("gType");
		String grpDesc = request.getParameter("gDesc");
		String Lat = request.getParameter("gLat");
		String Lng = request.getParameter("gLng");
		String nric = request.getParameter("nric");
		Hobby hobby = new Hobby();
		User user = new User();
		hobby.setGrpName(title);
		hobby.setCategory(category);
		user.setNric(nric);
		if (Lat != "" && Lng != "") {
			hobby.setLat(Double.parseDouble(Lat));
			hobby.setLng(Double.parseDouble(Lng));
		}
		hobby.setGrpDesc(grpDesc);
		HobbyManager hobbyManager = new HobbyManager();
		boolean result = hobbyManager.createHobby(hobby, user);
		if (result) {
			int grpID = hobbyManager.getLastHobbyID(nric);
			HobbyMembersManager memberManager = new HobbyMembersManager();

			HobbyMembers members = new HobbyMembers();
			members.setUserNRIC(nric);
			members.setActive(1);
			members.setRole("admin");
			members.setGroupID(grpID);
			boolean resultMember = memberManager.createHobbyMember(members);
			if (result == true && resultMember == true) {
				JsonObject myObj = new JsonObject();
				myObj.addProperty("success", true);
				myObj.addProperty("message", "Hobby created successfully.");
				out.println(myObj.toString());
			}
		}

	}

}
