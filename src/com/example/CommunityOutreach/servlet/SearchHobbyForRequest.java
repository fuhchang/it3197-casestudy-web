package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.requestHobbyManager;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.RequestHobby;

/**
 * Servlet implementation class SearchHobbyForRequest
 */
@WebServlet("/SearchHobbyForRequest")
public class SearchHobbyForRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchHobbyForRequest() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		HobbyManager hm = new HobbyManager();
		ArrayList<Hobby> hobbyList;
		if (id == 1) {
			hobbyList = hm.retrieveAllHobbyByCategory("Dance");
			request.setAttribute("hobbyList", hobbyList);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			requestDispatcher.forward(request, response);
		} else if (id == 2) {
			hobbyList = hm.retrieveAllHobbyByCategory("Cooking");
			request.setAttribute("hobbyList", hobbyList);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			requestDispatcher.forward(request, response);
		} else if (id == 3) {
			hobbyList = hm.retrieveAllHobbyByCategory("Gardening");
			request.setAttribute("hobbyList", hobbyList);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("cateID"));
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng"));
		System.out.println(id);
		System.out.println(lat);
		System.out.println(lng);
		HobbyManager hm = new HobbyManager();
		ArrayList<Hobby> hobbyList;
		
		if (id == 1) {
			
			hobbyList = hm.retrieveAllHobbyByCategory("Dance");
			ArrayList<Hobby> danceList = new ArrayList<Hobby>();
			ArrayList<RequestHobby> reqList = new ArrayList<RequestHobby>();
			requestHobbyManager rhm = new requestHobbyManager();
			
			for (int i = 0; i < hobbyList.size(); i++) {
				double result = getDistanceFromLatLongInKm(lat, lng, hobbyList.get(i).getLat(), hobbyList.get(i).getLat());
				if (result < 2000) {
					 danceList.add(hobbyList.get(i));
					 reqList.addAll(rhm.retrieveAllRequest(hobbyList.get(i).getGrpID()));
				}
				
			}
			 request.setAttribute("status", reqList);
			 request.setAttribute("hobbyList", danceList); 
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			 requestDispatcher.forward(request, response);
			 
		} else if (id == 2) {

			hobbyList = hm.retrieveAllHobbyByCategory("Cooking");
			ArrayList<Hobby> CookList = new ArrayList<Hobby>();
			for (int i = 0; i < hobbyList.size(); i++) {
				double result = getDistanceFromLatLongInKm(lat, lng, hobbyList.get(i).getLat(), hobbyList.get(i).getLat());
				if (result < 2000) {
					CookList.add(hobbyList.get(i));
				}
				
			}
			 request.setAttribute("hobbyList", CookList); RequestDispatcher
			 requestDispatcher =
			 request.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			 requestDispatcher.forward(request, response);
			 
		} else if (id == 3) {

			hobbyList = hm.retrieveAllHobbyByCategory("Gardening");
			ArrayList<Hobby> GardenList = new ArrayList<Hobby>();
			for (int i = 0; i < hobbyList.size(); i++) {
				double result = getDistanceFromLatLongInKm(lat, lng, hobbyList.get(i).getLat(), hobbyList.get(i).getLat());
				if (result < 2000) {
					 GardenList.add(hobbyList.get(i));
				}
				
			}
			 request.setAttribute("hobbyList",  GardenList); RequestDispatcher
			 requestDispatcher =
			 request.getRequestDispatcher("/ViewAvaliableHobbyHelp.jsp");
			 requestDispatcher.forward(request, response);
			 
		}
		

	}

	private double getDistanceFromLatLongInKm(double lat1, double lng1,
			double lat2, double lng2) {
		int km = 1000;
		double dLat = deg2rad(lat2 - lat1);
		double dLon = deg2rad(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.sin(dLon / 2) * Math.sin(dLon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = km * c;// distance in km
		return d;

	}

	private double deg2rad(double deg) {
		return deg * (Math.PI / 180);
	}

}
