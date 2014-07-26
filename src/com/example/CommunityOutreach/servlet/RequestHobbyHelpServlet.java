package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.requestHobbyManager;
import com.example.CommunityOutreach.model.RequestHobby;

/**
 * Servlet implementation class RequestHobbyHelpServlet
 */
@WebServlet("/RequestHobbyHelpServlet")
public class RequestHobbyHelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestHobbyHelpServlet() {
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
		
		try {
			String message = request.getParameter("message");
			StringTokenizer st2 = new StringTokenizer( message, ",");
			ArrayList<String> reqList = new ArrayList<String>();
			while (st2.hasMoreElements()) {
				reqList.add((String) st2.nextElement());
			}
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
			RequestHobby rh = new RequestHobby();
			rh.setRequestDateStart(df.parse(reqList.get(0)));
			rh.setRequestDateEnd(df.parse(reqList.get(1)));
			rh.setHobbyID(Integer.parseInt(reqList.get(2)));
			rh.setEventID(0);
			rh.setRequestID(0);
			rh.setRequestStatus("pending");
			requestHobbyManager requestManager = new requestHobbyManager();
			requestManager.createRequest(rh);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
