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
   		<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/tags/markerclustererplus/2.0.12/src/markerclusterer_packed.js" />
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
	    
	   

		/*    if (navigator.geolocation) {
		        navigator.geolocation.getCurrentPosition(initialize);
		    } else { 
		        x.innerHTML = "Geolocation is not supported by this browser.";
		    }
	    */
			//function initialize(position) {
				
	    	function initialize(){
	    	
			// alert("Latitude: " + position.coords.latitude + "<br>Longitude: " + position.coords.longitude);
			  var myLatlng = new google.maps.LatLng(1.3667, 103.8);			
			  var mapOptions = {
			    zoom: 11,
			    center: myLatlng,
			    mapTypeId: google.maps.MapTypeId.ROADMAP 
			  }
			  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);		
			  
			  //var me = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
			  
			  
			  //var myMarker1 = new google.maps.Marker({position: me, icon: 'http://maps.google.com/mapfiles/ms/icons/red-pushpin.png',map: map });
				//var myMarker1 = new google.maps.Marker({position: me, icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',map: map });
			//var myMarker1 = new google.maps.Marker({position: me, icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',map: map });
			  
			/*  <c:forEach items="${artList}" var="item">
			  		var testing = new google.maps.LatLng(${item.dbLat},${item.dbLon});
			  		var marker = new google.maps.Marker({
					      position: testing,
					      title: '${item.title}',
					      icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
					      map: map			
					  });
			  		 
			  	</c:forEach>*/
			  	
			  	
			  	var markers = [];
				  <c:forEach items="${artList}" var="item">
				  	  var latLng = new google.maps.LatLng(${item.dbLat},${item.dbLon});
				  	  var marker = new google.maps.Marker({'position': latLng});
				  	  markers.push(marker);
				  	</c:forEach>
				  	var markerCluster = new MarkerClusterer(map, markers);
			  	
			  	
			  	
			  	var infowindow = new google.maps.InfoWindow({
				   content:"<p><b>You are here</b></p>"
				});		
			  	
				infowindow.open(map,myMarker1);
				google.maps.event.addListener(myMarker1, 'click', function() {
					infowindow.open(map,myMarker1);
				});
			  	
				
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
	 
	<div class="panel panel-info" Style="width:850px;margin:0px auto;">
		<div class="panel-heading">
			<!--  <h1 class="panel-title">Latest News From Around The Neighbourhood</h1>-->
			<h1>Confirmed Location Requests</h1>
			
		</div>
		
		<ul class="nav nav-tabs" role="tablist" style="margin-left:5px;">
			
		  <li><a href="DesktopLatestArticle">Latest News</a></li>
		  <li><a href="DesktopFeedbackArticle">Feedbacks</a></li>
		  <li class="active"><a href="DesktopLocationArticle">Location Requests</a></li>
		  <li><a href="DesktopDeleteLatestArticle">Remove Articles From Main Page</a></li>
		</ul>
		
		<br/>
		
		
		<div class="panel-body" Style="width:800px;margin:0px auto; border:1px solid black;" >
		<form action="DesktopLocationArticle" method="post">
		<button type="submit" class="pull-right btn btn-primary btn-sm"value="Refresh" id="btn" style="margin-left:5px;">Refresh</button>
		
		<!--  <a class=" pull-right btn btn-primary btn-sm" onclick="location.href='articleSubmission.jsp'">Submit Article</a>-->
		
		<div Style="width:100%; height:300px;margin:0px auto;" >
				<p style="font-size:200%">Article Locations:</p>
					<div id="map-canvas"></div>
		</div>	
		<hr/>
		
		
		<br/>
		<hr/>			
			<!--<table class="table table-striped">-->
			<table class="table table-striped" cellspacing="0" width="100%" id="articleTable">	
			
					<thead>
						<tr>
							<th></th>
							<th>ID:</th>
							<th>Article:</th>
							<th>Category:</th>
							<th>Status:</th>
							<th></th>
						</tr>					
					</thead>	
					<tbody>
						<c:forEach items="${artList}" var="item">
								<tr>
								 	<td width="3%"><span class="glyphicon glyphicon-globe" style="margin-top:150%;"></span></td>
								 	<td>${item.articleID }</td>
									<td>
										<b><u>${item.title}</u></b>
										<br/>
										Posted By: ${item.articleUser}
										<br/>
										${item.articleDate}	
									</td>
									<td>${item.category }</td>
									<td>${item.approved }</td>
									<td>
										<a type="submit" href="DesktopLocationSelectedArticle?id=${item.articleID }" id="" name="article-content-id"><u>Read</u></a>
									</td>	
								</tr>
							</c:forEach>
					
					
					</tbody>		
			
			   </table>
			   	<br/>
				<br/>
		
		</div>
		<br/>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		</form>
	</jsp:attribute>
</t:master>

<script>
	$(document).ready(function() {
		$('#articleTable').dataTable();
	} );

</script>