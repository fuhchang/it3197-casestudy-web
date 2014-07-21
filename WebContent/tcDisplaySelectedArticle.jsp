<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
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
	    <script>
	    
	    
	    
	    
	    
	    
	    
	    var geocoder;
		var map;
		var marker;
		//Onload handler to fire off the app.
		 if (navigator.geolocation) {
			    navigator.geolocation.getCurrentPosition(initialize, showError);
			} else { 
			    x.innerHTML = "Geolocation is not supported by this browser.";
			}
	 
		 function showError(error) {
			    switch(error.code) {
			        case error.PERMISSION_DENIED:
			            alert("Unable to get your current location.");
			            document.getElementById('currentLat').value = 0;
						document.getElementById('currentLon').value = 0;
						document.getElementById('currentLat2').value = 0;
						document.getElementById('currentLon2').value = 0;
			            break;
			        case error.POSITION_UNAVAILABLE:
			            alert("Location information is unavailable.");
			            document.getElementById('currentLat').value = 0;
						document.getElementById('currentLon').value = 0;
						document.getElementById('currentLat2').value = 0;
						document.getElementById('currentLon2').value = 0;
			            break;
			        case error.TIMEOUT:
			            alert("The request to get user location timed out.");
			            document.getElementById('currentLat').value = 0;
						document.getElementById('currentLon').value = 0;
						document.getElementById('currentLat2').value = 0;
						document.getElementById('currentLon2').value = 0;
			            break;
			        case error.UNKNOWN_ERROR:
			            alert("An unknown error occurred.");
			            break;
			    }
			}
	 
		/*function initialize(position) {
		  //geocoder = new google.maps.Geocoder();			
		  var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);		 
		 /* map = new google.maps.Map(document.getElementById('mapCanvas'), {		 
		    zoom: 18,		 
		    center: latLng,		 
		    mapTypeId: google.maps.MapTypeId.ROADMAP	 
		  });		 
		  var infowindow = new google.maps.InfoWindow({
			   content:"<p><b>Not the location you want?</b></p>" + "<p>\nChange the location by dragging the marker</p>"
			   });		  
		  marker = new google.maps.Marker({position: latLng, title: 'Location point', map: map,draggable: true});
		  infowindow.open(map,marker);
		  google.maps.event.addListener(marker, 'click', function() {
			   infowindow.open(map,marker);
			   });*/
	 
	 
		 
		 
		 		 
		/*}*/
		
	    
			function initialize(position) {				
			  //var myLatlng = new google.maps.LatLng(1.287778,103.851944);			
			  var myLatlng = new google.maps.LatLng(${dbLat},${dbLon});
			  var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);		 
			  
			  var mapOptions = {
			    zoom: 15,
			    center: myLatlng,
			    mapTypeId: google.maps.MapTypeId.ROADMAP 
			  }
			  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);		
			  var marker = new google.maps.Marker({
			      position: myLatlng,
			      icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
			      map: map			
			  });	
			  
			  var marker1 = new google.maps.Marker({
			      position: latLng,
			      map: map			
			  });	
			  var infowindow = new google.maps.InfoWindow({
				   content:"<p><b>Place of occurrence</b></p>"
				   });			  
			  infowindow.open(map,marker);
			  google.maps.event.addListener(marker, 'click', function() {
				   infowindow.open(map,marker);
				   });
			  
			  var trafficLayer = new google.maps.TrafficLayer();
			  trafficLayer.setMap(map);
			  
			  document.getElementById("currentLat").value=position.coords.latitude;
			  document.getElementById("currentLon").value=position.coords.longitude;
			  
			  
			  var linkCoordinates = [
			                               new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
			                               new google.maps.LatLng(${dbLat},${dbLon}),
			                               
			                             ];
			                             var destinationPath = new google.maps.Polyline({
			                               path: linkCoordinates,
			                               geodesic: true,
			                               strokeColor: '#FF0000',
			                               strokeOpacity: 1.0,
			                               strokeWeight: 2
			                             });

			                             destinationPath.setMap(map);
			}			
			
			
			
			google.maps.event.addDomListener(window, 'load', initialize);			
		</script>
		<br />	 
		<div class="panel panel-info" Style="width:100%;margin:0px auto;">
			<div class="panel-heading">
				<!--  <h1 class="panel-title">Latest News From Around The Neighbourhood</h1>-->
				<h2>${articleTitle}</h2>
				<h4 class="text-warning"><b>Author: ${articleUserName} </b><br/><label>${articleDate }<br/>Category: ${category }</label></h4>	
							
			</div>			
			<div class="panel-body" Style="margin:0px auto;" >
				<p>${articleContent}</p>
				<hr/>			
				<h4>${articleLocation}</h4>
				
				
				
				<form action="ArticleGo" method="post">
					<input type = "text" class="form-control" id="currentLat" style="width:100%;display:none;" name="currentLat" value = "" readonly/>
					<input type = "text" class="form-control" id="currentLon" style="width:100%;display:none;" name="currentLon" value ="" readonly/>
					<input type = "text" class="form-control" id="dbLat" style="width:100%;display:none;" name="dbLat" value = "${dbLat}" readonly/>
					<input type = "text" class="form-control" id="dbLon" style="width:100%;display:none;" name="dbLon" value ="${dbLon }" readonly/>
				<input type="submit" class="btn btn-primary btn-sm" value="Get Directions" id="btn">
				</form>
				
				
				
					<div id="map-canvas" Style="width:100%; height:300px;margin:0px auto; border:1px solid black;"></div>
					
			</div>
			
			
			<form action="TCDisplaySelectedArticle" method="post">
				<!-- <div class="checkbox">
				 &nbsp <label>
				   <input type="checkbox" value="Yes" name="chkBox" style="margin-left:2px;">
				    Is this feedback legitimate?
				  </label>
				</div>-->
				
				&nbsp; &nbsp; &nbsp;Is this article legitimate?
				<select class="btn btn-default dropdown-toggle" style="width:75px;" name="legit">
								  <option>Yes</option>
								  <option>No</option>
				</select>
				<br/>
				
				
				
				<input type = "text" class="form-control" id="idArticle" style="width:100%;display:none;" name="idArticle" value = ${idArticle} readonly/>
				
				
				
				
				<table>
					<tbody>
						<tr>
							<td>
								&nbsp&nbsp&nbsp&nbsp<input type="submit" class="btn btn-primary btn-sm" value="Submit" id="btn"></td>
				
						</form>	
							<form action="PendingArticlesServlet" method="post">
								<td>&nbsp<input type="submit" class="btn btn-primary btn-sm" value="Cancel" id="btn"></td>
						</form>	
			</tr>
					</tbody>
				</table>
			
			
		</div>
		<br/>
		<br/>
	</jsp:attribute>
</t:master>


<script>
	$(document).ready(function() {
		
	});

</script>
