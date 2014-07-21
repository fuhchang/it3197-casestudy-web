<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/dataTable/jquery.dataTables_themeroller.css" />
   		<link rel="stylesheet" type="text/css" href="css/dataTable/demo_page.css" />
   		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.custom.css" />
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	  	<script src="js/displayArticle.js"></script>
	  	<script type="text/javascript" language="javascript" src="js/dataTable/dataTables.js"></script>
   		<script type="text/javascript" language="javascript" src="js/dataTable/numberSort.js"></script>
	</jsp:attribute>
	<jsp:attribute name="content">
	
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
	    <meta charset="utf-8">	    
	    <style>
	      html, body, #map-canvas {
	        height: 100%;
	        margin: 0px;
	        padding: 0px
	      }
	    </style>
	    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
		<script src="http://maps.google.com/maps/api/js?sensor=true&libraries=geometry"></script>
	    <script>
	    
			
	    	function initialize(){
			  var myLatlng = new google.maps.LatLng(1.3667, 103.8);			
			  var mapOptions = {
			    zoom: 10,
			    center: myLatlng,
			    mapTypeId: google.maps.MapTypeId.ROADMAP 
			  }
			  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);		
			  
			  var me = new google.maps.LatLng(${currentLat}, ${currentLon});
			  
			
			//var myMarker1 = new google.maps.Marker({position: me, icon: 'http://maps.google.com/mapfiles/ms/icons/red-pushpin.png',map: map });
				//var myMarker1 = new google.maps.Marker({position: me, icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',map: map });
			
				var myMarker1 = new google.maps.Marker({position: me, icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',map: map });
			  
			  <c:forEach items="${artList}" var="item">
			  		var testing = new google.maps.LatLng(${item.dbLat},${item.dbLon});
			  		var marker = new google.maps.Marker({
					      position: testing,
					      title: '${item.title}',
					      icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
					      map: map			
					  });
			  		 
			  	</c:forEach>
			  	
			  	
			  	
			  	var infowindow = new google.maps.InfoWindow({
				   content:"<p><b>You are here</b></p>"
				});		
			  	
				infowindow.open(map,myMarker1);
				google.maps.event.addListener(myMarker1, 'click', function() {
					infowindow.open(map,myMarker1);
				});
			  	
				
				var circle = {
				          strokeColor: "blue",
				          strokeOpacity: 0.8,
				          strokeWeight: 2,
				          fillColor: "blue",
				          fillOpacity: 0.35,
				          map: map,
				          center: new google.maps.LatLng(${currentLat}, ${currentLon}),
				          radius: ${distSelected}*1000
				        };
				        cityCircle = new google.maps.Circle(circle);
				
				
				
			/*
				  var flightPlanCoordinates = [
				                               new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
				                               new google.maps.LatLng(1.3871626, 103.89699930000006),
				                               
				                             ];
				                             var flightPath = new google.maps.Polyline({
				                               path: flightPlanCoordinates,
				                               geodesic: true,
				                               strokeColor: '#FF0000',
				                               strokeOpacity: 1.0,
				                               strokeWeight: 2
				                             });

				                             flightPath.setMap(map);

				 var trafficLayer = new google.maps.TrafficLayer();
				  trafficLayer.setMap(map);*/
				
			  
			 	/*var from = new google.maps.LatLng(1.3871626, 103.89699930000006);
			  	var to   = new google.maps.LatLng(1.36991135967923, 103.85165950555734);
			  	var dist = google.maps.geometry.spherical.computeDistanceBetween(from, to);
			  	alert(dist/1000 + " km");*/
			 
			}			
			google.maps.event.addDomListener(window, 'load', initialize);			
		</script>
	
	
	
	
	<br />
	 
	
		
		
		<div Style="width:100%; height:400px;margin:0px auto;" >
					<form action="DisplayArticleMainServlet" method="post">
					
					<input type = "text" class="form-control" id="currentLat" style="width:100%;display:none;" name="currentLat" value="${currentLat }"/>
					<input type = "text" class="form-control" id="currentLon" style="width:100%;display:none;" name="currentLon" value="${currentLat}"/>
					<input type = "text" class="form-control" id="distSelected" style="width:100%;display:none;" name="distSelected" value="${distSelected}"/>
					
					
						<input type="submit" class="btn btn-primary btn-sm" value="Back" id="btn">
					</form>
					
					<div id="map-canvas" style="border:1px solid black"></div>
					<br/>
		</div>	
		
	
	
	
	
	
	
	
	
	
	
	
	
		
	</jsp:attribute>
</t:master>

<script>
	$(document).ready(function() {
		//$('#articleTable').dataTable();
	} );

</script>