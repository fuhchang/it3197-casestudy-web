package com.example.CommunityOutreach.servlet;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
@WebServlet("/CreateHobbyWebServlet")
public class CreateHobbyWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateHobbyWebServlet() {
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
		//String imgString = request.getParameter("imgFile");
		//System.out.println(imgString);
		String title = request.getParameter("gtitle");
		String category = request.getParameter("gType");
		String grpDesc = request.getParameter("gDesc");
		String loc = request.getParameter("coordinates");
		System.out.println(loc);
		StringTokenizer st = new StringTokenizer(loc, ",");
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
		/*
		File file = new File("/storage/emulated/0/DCIM/Camera/20140717_111856.jpg");
		int len = (int) file.length();
		byte[] buf = new byte[len];
		FileInputStream fis = new FileInputStream(file);
		fis.read(buf);
		*/
		Hobby hobby = new Hobby();
		User user = new User();
		user.setNric(userName);
		hobby.setGrpName(title);
		hobby.setCategory(category);
		hobby.setLat(Lat);
		hobby.setLng(Lng);
		/*
		String relativeWebPath ="C:/Users/fuhchang/Desktop/uploadFile";
		String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);
		*/
		hobby.setGrpDesc(grpDesc); 
		HobbyManager hobbyManager = new HobbyManager();
		boolean result = hobbyManager.createHobby(hobby, user);
		if(result){
		int id = hobbyManager.getLastHobbyID(user.getNric());
		HobbyMembers hm = new HobbyMembers();
		hm.setGroupID(id);
		hm.setRole("admin");
		hm.setUserNRIC(user.getNric());
		hm.setActive(1);
		HobbyMembersManager MemberManager = new HobbyMembersManager();
		boolean resultM = MemberManager.createHobbyMember(hm);
		System.out.println(resultM);
		System.out.println(result);
		request.setAttribute("grpID", id);
		RequestDispatcher rd = request.getRequestDispatcher("upload.jsp");
    	rd.forward(request, response);
		}
		
		
	}

}
