package com.example.CommunityOutreach.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.example.CommunityOutreach.model.Hobby;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String title = request.getParameter("gtitle");
		String category = request.getParameter("gType");
		String grpDesc = request.getParameter("gDesc");
		String loc = request.getParameter("gLoc");
		
		Hobby hobby = new Hobby();
		hobby.setGrpName(title);
		hobby.setCategory(category);
		hobby.setLocation(loc);
		hobby.setGrpDesc(grpDesc);
		HobbyManager hobbyManager = new HobbyManager();
		hobbyManager.createHobby(hobby);
		
	}

}
