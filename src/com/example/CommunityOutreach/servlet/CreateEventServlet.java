package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.data.EventLocationDetailManager;
import com.example.CommunityOutreach.data.EventManager;
import com.example.CommunityOutreach.data.EventParticipantsManager;
import com.example.CommunityOutreach.data.UserManager;
import com.example.CommunityOutreach.model.Article;
import com.example.CommunityOutreach.model.Event;
import com.example.CommunityOutreach.model.EventLocationDetail;
import com.example.CommunityOutreach.model.EventParticipants;
import com.example.CommunityOutreach.model.User;
import com.example.CommunityOutreach.util.Settings;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class CreateEventServlet
 */
@WebServlet("/createEvent")
public class CreateEventServlet extends HttpServlet implements Settings{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
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
        
        //int eventID = Integer.parseInt(request.getParameter("eventID"));
        //Real Values
        String eventName = request.getParameter("eventName");
        String eventCategory = request.getParameter("eventCategory");
        String eventDescription = request.getParameter("eventDescription");
        String occurence = request.getParameter("occurence");
        String eventDateTimeFrom = request.getParameter("eventDateTimeFrom");
        String eventDateTimeTo = request.getParameter("eventDateTimeTo");
        int noOfParticipantsAllowed = 0;
        if(request.getParameter("noOfParticipants") != null){
        	try{
        		noOfParticipantsAllowed = Integer.parseInt(request.getParameter("noOfParticipants"));
        	}
        	catch(Exception e){
        		noOfParticipantsAllowed = 0;
        	}
        }
        Date dateTimeFrom = null;
        Date dateTimeTo = null;
        String eventAdminNRIC = "";
        try {
        	System.out.println(request.getParameter("web"));
        	if(request.getParameter("web").equals("true")){
        		Calendar cFrom = Calendar.getInstance();
        		dateTimeFrom = webSqlDateTimeFormatter.parse(eventDateTimeFrom);
        		cFrom.setTime(dateTimeFrom);
        		cFrom.set(Calendar.SECOND, 0);
        		dateTimeFrom = cFrom.getTime();
        		
        		Calendar cTo = Calendar.getInstance();
        		dateTimeTo = webSqlDateTimeFormatter.parse(eventDateTimeTo);
        		cTo.set(Calendar.SECOND, 0);
        		cTo.setTime(dateTimeTo);
        		dateTimeTo = cTo.getTime();
        		
        		eventAdminNRIC = this.getCookieValue(request);
        	}
        	else{
        		dateTimeFrom = sqlDateTimeFormatter.parse(eventDateTimeFrom);
        		dateTimeTo = sqlDateTimeFormatter.parse(eventDateTimeTo);
        		eventAdminNRIC = request.getParameter("eventAdminNRIC");
        	}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Testing Values
        //System.out.println("Event No: " + eventID);
		//Event Location

        String eventLocationName = request.getParameter("locationName");
        String eventLocationAddress = request.getParameter("locationAddress");
        String eventLocationHyperLink = request.getParameter("locationHyperLink");

        double lat = 0.00;
		double lng = 0.00;
        if((request.getParameter("lat") != null) && (request.getParameter("lng") != null)){
        	try{
        		lat = Double.parseDouble(request.getParameter("lat"));
        		lng = Double.parseDouble(request.getParameter("lng"));
        	}
        	catch(Exception e){
        		lat = 0.00;
        		lng = 0.00;
        	}
        }
        
		EventManager eventManager = new EventManager();
		UserManager userManager = new UserManager();
		EventLocationDetailManager eventLocationDetailManager = new EventLocationDetailManager();
        Event event = new Event(0,eventAdminNRIC,eventName,eventCategory,eventDescription,dateTimeFrom,dateTimeTo,occurence,noOfParticipantsAllowed,1);
        User user = userManager.retrieveUser(eventAdminNRIC);
        EventParticipantsManager eventParticipantsManager = new EventParticipantsManager();
        
        if(user == null){
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","There is no record of such user. Unable to create event.");
            out.println(myObj.toString());
            return;
        }
        else{
	        if(user.getActive() == 0){
	    		JsonObject myObj = new JsonObject();
	            myObj.addProperty("success", false);
	            myObj.addProperty("message","There is no record of such user. Unable to create event.");
	            out.println(myObj.toString());
	            return;
	        }
        }
        
        boolean isEventCreated = false;
        try{
        	isEventCreated = eventManager.createEvent(event);
        	if(!isEventCreated){
        		JsonObject myObj = new JsonObject();
                myObj.addProperty("success", false);
                myObj.addProperty("message","Unable to create event successfully.");
                out.println(myObj.toString());
        	}
        	else{
        		ArrayList<Event> eventArrList = eventManager.retrieveAllEvents();
        		int eventID = eventArrList.get(eventArrList.size()-1).getEventID();
        		Calendar todayDate = Calendar.getInstance();
        		EventParticipants eventParticipants = new EventParticipants(eventID,eventAdminNRIC,todayDate.getTime(),0);
        		boolean isEventParticipantsCreated = eventParticipantsManager.createEventParticipant(eventParticipants);
        		boolean addedPoints = userManager.updatePoints(eventAdminNRIC, (user.getPoints() + pointsForCreatingEvent));
        		EventLocationDetail eventLocationDetails = new EventLocationDetail(0,eventID,eventLocationName,eventLocationAddress,eventLocationHyperLink,lat,lng);
        		boolean addedLocation = eventLocationDetailManager.createEventLocationDetails(eventLocationDetails);
        		ArticleManager articleManager = new ArticleManager();
        		
        		DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy HH:mm a");
        		Calendar cal = Calendar.getInstance();
        		String now = dateFormat.format(cal.getTime());
        		
        		
        		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy HH:mm a");

                Date currentTime = null;
        		try {
        			currentTime = simpleDateFormat.parse(now);
        		} catch (ParseException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        		}
        		
        		Article article = new Article(0,event.getEventName(),event.getEventDescription(),currentTime,"News Around The Neighbourhood",eventLocationDetails.getEventLocationAddress(),eventAdminNRIC,1,"Pending",eventLocationDetails.getEventLocationLat(),eventLocationDetails.getEventLocationLng());
        		article.setArticleFBPostID(request.getParameter("eventFBPostID"));
        		boolean articleCreated = articleManager.createArticle(article);
        		
        		Event eventFB = new Event();
        		eventFB.setEventID(eventID);
        		eventFB.setEventFBPostID(request.getParameter("eventFBPostID"));
        		
        		boolean editEventFBPostID = eventManager.editEventFBPostID(eventFB);
        		
        		if((isEventParticipantsCreated) && (addedPoints) && (addedLocation) && (articleCreated) && (editEventFBPostID)){
        	        if(request.getParameter("web").equals("true")){
        	        	if(request.getParameter("requestHelp").equals("on")){
            	        	response.sendRedirect("hobbyCategory.jsp");
        	        	}
        	        	else{
            	    		response.sendRedirect("retrieveAllEvents?web=true");
        	        	}
        			}
        	        else{
        	        	JsonObject myObj = new JsonObject();
        				myObj.addProperty("success", true);
        				myObj.addProperty("message","Event created successfully.");
        				out.println(myObj.toString());
        	        }
        		}
        		else{
        			JsonObject myObj = new JsonObject();
        			myObj.addProperty("success", false);
        			myObj.addProperty("message","Unable to create event successfully.");
        			out.println(myObj.toString());
        			return;
        		}
        	}
        }
        catch(Exception ex){
        	ex.printStackTrace();
    		JsonObject myObj = new JsonObject();
            myObj.addProperty("success", false);
            myObj.addProperty("message","Unable to create event successfully.");
            out.println(myObj.toString());
            return;
        }
	}

    public String getCookieValue(HttpServletRequest req){
    	String cName = "userLogin",dValue = "";
    	Cookie[] c1= req.getCookies();
    	if (c1 != null) {
    		for(int i=0; i<c1.length; i++) {
    			Cookie c = c1[i];
    			if (cName.equals(c.getName())) {
    				return(c.getValue());
    			}
    		}
    	}
    	return(dValue);
   }
}
