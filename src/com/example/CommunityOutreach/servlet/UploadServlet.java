package com.example.CommunityOutreach.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.CommunityOutreach.data.HobbyManager;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static String SAVE_DIR = "uploadFiles";
	private static String SAVE_DIR = "uploadFiles";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
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
		
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		int grpID = Integer.parseInt(request.getParameter("grpID"));
		System.out.println("id is " + grpID);
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		HobbyManager hm = new HobbyManager();
		String fileName = null;
		for (Part part : request.getParts()) {
			fileName = grpID + ".jpg";
			System.out.println(savePath + File.separator + fileName);
			part.write(savePath + File.separator + fileName);
			
			System.out.println(savePath + File.separator + fileName);
		}
		System.out.println("/CommunityOutreach/" + SAVE_DIR + "/" + fileName);
		hm.uploadImage("/CommunityOutreach/" + SAVE_DIR + "/" + fileName, grpID);
		request.setAttribute("message", "Upload has been done successfully!");
		getServletContext().getRequestDispatcher("/RetrieveAllHobbyServlet")
				.forward(request, response);
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
