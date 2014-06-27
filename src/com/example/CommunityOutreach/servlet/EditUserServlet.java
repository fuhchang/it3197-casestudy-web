package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.User;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
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
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
        String nric = request.getParameter("nric");
        //Real Values
        /*String name = request.getParameter("name");
        String password = request.getParameter("password");
        String contactNo = request.getParameter("contactNo");
        String address = request.getParameter("address");
        String email = request.getParameter("email");*/
        
        //Testing Values
        System.out.println("NRIC: " + nric);
		String name = "Jim";
		String password = "234";
		String contactNo = "91230495";
		String address = "test";
		String email = "test";
        
        UserManager userManager = new UserManager();
        User user = new User(nric,name,null,password,contactNo,address,email,1,0);
        
        User checkUser = userManager.retrieveUser(nric);
        if((checkUser == null) || (nric == null)){
            JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","There is no record of such user.");
            out.println(myObj.toString());
            return;
        }
        if(checkUser.getActive() == 0){
        	JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","This user has already been obsoleted.");
            out.println(myObj.toString());
            return;
        }
        
        boolean isUserEdited = false;
        try{
        	isUserEdited = userManager.editUser(user);
        	if(!isUserEdited){
        		JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to edit user successfully.");
                out.println(myObj.toString());
        	}
        	else{
                JsonObject myObj = new JsonObject();
                myObj.addProperty("success", true);
                myObj.addProperty("message","User edited successfully.");
                out.println(myObj.toString());
        	}
        }
        catch(Exception ex){
        	ex.printStackTrace();
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to edit user successfully.");
            out.println(myObj.toString());
        }
	}

}
