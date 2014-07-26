package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.HobbyManager;
import com.example.CommunityOutreach.data.HobbyMembersManager;
import com.example.CommunityOutreach.model.Hobby;
import com.example.CommunityOutreach.model.HobbyMembers;

/**
 * Servlet implementation class RetrieveAllHobbyServlet
 */
@WebServlet("/RetrieveAllHobbyServlet")
public class RetrieveAllHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveAllHobbyServlet() {
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
		
		HobbyManager hm = new HobbyManager();
		ArrayList<Hobby> hobbyList = hm.retrieveAllHobby();
		
		HobbyMembersManager hmManager = new HobbyMembersManager();
		
		ArrayList<HobbyMembers> memberList = hmManager.retrieveAllHobbyMember(userName);
		
		ArrayList<Hobby> finalList = new ArrayList<Hobby>();
		if(memberList.size() > 0){
			int b = 0;
			for(int i=0; i< memberList.size(); i++){
				
				for(int a=0; a< hobbyList.size(); a++){
					
					String mID = Integer.toString(memberList.get(i).getGroupID());
					String hID = Integer.toString(hobbyList.get(a).getGrpID());
					System.out.println(mID + " " + hID);
					if (mID.equals(hID)) {
						finalList.add(hobbyList.get(a));
					} 
				}
			}
			
			request.setAttribute("joinList", finalList);
		}
		
		for(int i=0; i<finalList.size();i++){
			for(int a=0; a<hobbyList.size(); a++){
				String same = Integer.toString(finalList.get(i).getGrpID());
				String hID = Integer.toString(hobbyList.get(a).getGrpID());
				if(same.equals(hID)){
					hobbyList.remove(a);
					break;
				}
			}
		}
		
		if(hobbyList.size() > 0){
			request.setAttribute("hobbyList", hobbyList);		
			}
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hobby.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		HobbyManager hm = new HobbyManager();
		ArrayList<Hobby> hobbyList = hm.retrieveAllHobby();
		
		HobbyMembersManager hmManager = new HobbyMembersManager();
		
		ArrayList<HobbyMembers> memberList = hmManager.retrieveAllHobbyMember(userName);
		
		ArrayList<Hobby> finalList = new ArrayList<Hobby>();
		if(memberList.size() > 0){
			int b = 0;
			for(int i=0; i< memberList.size(); i++){
				
				for(int a=0; a< hobbyList.size(); a++){
					
					String mID = Integer.toString(memberList.get(i).getGroupID());
					String hID = Integer.toString(hobbyList.get(a).getGrpID());
					System.out.println(mID + " " + hID);
					if (mID.equals(hID)) {
						finalList.add(hobbyList.get(a));
					} 
				}
			}
			
			request.setAttribute("joinList", finalList);
		}
		
		for(int i=0; i<finalList.size();i++){
			for(int a=0; a<hobbyList.size(); a++){
				String same = Integer.toString(finalList.get(i).getGrpID());
				String hID = Integer.toString(hobbyList.get(a).getGrpID());
				if(same.equals(hID)){
					hobbyList.remove(a);
					break;
				}
			}
		}
		
		if(hobbyList.size() > 0){
			request.setAttribute("hobbyList", hobbyList);		
			}
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hobby.jsp");
		requestDispatcher.forward(request, response);
	}

}
