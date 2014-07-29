package com.example.CommunityOutreach.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.PostManager;

/**
 * Servlet implementation class uploadPostImageServlet
 */
@WebServlet("/uploadPostImageServlet")
public class uploadPostImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SAVE_DIR = "uploadFiles";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadPostImageServlet() {
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
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		String test = request.getParameter("test123");
		System.out.println(test);
		int grpID = Integer.parseInt(request.getParameter("groupID"));
		int postID = Integer.parseInt(request.getParameter("groupID"));
		
		System.out.println("id is " + grpID + "_" +postID);
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		PostManager pm = new PostManager();
		String fileName = null;
		for (Part part : request.getParts()) {
			fileName = grpID + "_" +postID + ".jpg";
			System.out.println(savePath + File.separator + fileName);
			part.write(savePath + File.separator + fileName);
			
			System.out.println(savePath + File.separator + fileName);
		}
		System.out.println("/CommunityOutreach/" + SAVE_DIR + "/" + fileName);
		pm.uploadImage("/CommunityOutreach/" + SAVE_DIR + "/" + fileName, grpID);
		
		request.setAttribute("message", "Upload has been done successfully!");
		getServletContext().getRequestDispatcher("/RetrieveAllHobbyServlet")
				.forward(request, response);
	}

}
