<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	</jsp:attribute>
	
	<jsp:attribute name="content">
	
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
 
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
 
	<script type="text/javascript">
 
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
			           // alert("You must allow to be able to use this service.");
			            //document.getElementById("info").style.visibility="hidden";		
			          //   marker.setPosition(
			          //          new google.maps.LatLng(1.287778, 103.851944
			          //          		)
			          //      );
			            break;
			        case error.POSITION_UNAVAILABLE:
			            alert("Location information is unavailable.");
			            break;
			        case error.TIMEOUT:
			            alert("The request to get user location timed out.");
			            break;
			        case error.UNKNOWN_ERROR:
			            alert("An unknown error occurred.");
			            break;
			    }
			}
	 
		function initialize(position) {
		  geocoder = new google.maps.Geocoder();			
		  var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);		 
		  map = new google.maps.Map(document.getElementById('mapCanvas'), {		 
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
			   });
	 
		  // Update current position info.		 
		  updateMarkerPosition(latLng);		 
		  geocodePosition(latLng);
		 
		  // Add dragging event listeners.		 
		  google.maps.event.addListener(marker, 'dragstart', function() {		 
		   // updateMarkerAddress('Dragging...');		 
		  });
		 		 		 
		  google.maps.event.addListener(marker, 'drag', function() {		 
		   // updateMarkerStatus('Dragging...');		 
		    updateMarkerPosition(marker.getPosition());		 
		  });
		 				 
		  google.maps.event.addListener(marker, 'dragend', function() {		 
		   // updateMarkerStatus('Drag ended');	 
		    geocodePosition(marker.getPosition());		    		 
		  });		 
		}
		
		function geocodePosition(pos) {		 
			geocoder.geocode({
				latLng: pos
			}, 	 
			function(responses) {		 
			    if (responses && responses.length > 0) {			 
			      updateMarkerAddress(responses[0].formatted_address);			 
			    } else {	 
			      updateMarkerAddress('Cannot determine address at this location.');			 
			    }			 
			  });			 
			}
		 
			function updateMarkerStatus(str) {		 
			//  document.getElementById('markerStatus').innerHTML = str;			 
			}
	 
			function updateMarkerPosition(latLng) {
			// document.getElementById('info').innerHTML = [ latLng.lat(),latLng.lng()].join(', ');
			document.getElementById('info').value = [ latLng.lat(),latLng.lng()].join(', ');
			document.getElementById('storingLat').value = latLng.lat();
			document.getElementById('storingLon').value = latLng.lng();
			
			//document.getElementById('test').value = [latLng.lat(),latLng.lng()].join(', ');		 
			}
			  
			function updateMarkerAddress(str) {			 
			// document.getElementById('address').innerHTML = str;
			  document.getElementById('testing').value = str;
			}	
			
			function codeAddress(){		
				var address= document.getElementById("inputTextAddress").value;
				geocoder.geocode( { 'address': address}, function(results, status) {
				      if (status == google.maps.GeocoderStatus.OK) {
				    	  //marker.setMap(null);	
				        map.setCenter(results[0].geometry.location);				        
				        var latitude = results[0].geometry.location.lat();;
				        var longitude = results[0].geometry.location.lng();
				        marker.setPosition(
			                    new google.maps.LatLng(latitude, longitude
			                    		)
			                );		
				        latLng = new google.maps.LatLng(latitude, longitude);
				        geocodePosition(latLng);			 
							 //document.getElementById('info').innerHTML = latitude + ", " + longitude;
							document.getElementById('info').value = latitude + ", " + longitude;
				//			document.getElementById('test').value=latitude + ", " + longitude;
							document.getElementById('storingLat').value = latitude;
							document.getElementById('storingLon').value = longitude; 					 
							  // Add dragging event listeners.					 
							  google.maps.event.addListener(marker, 'dragstart', function() {			 
							   // updateMarkerAddress('Dragging...');					 
							  });
							  google.maps.event.addListener(marker, 'drag', function() {				 
							   // updateMarkerStatus('Dragging...');					 
							    updateMarkerPosition(marker.getPosition());					 
							  });				  					 
							  google.maps.event.addListener(marker, 'dragend', function() {					 
							   // updateMarkerStatus('Drag ended');					 
							    geocodePosition(marker.getPosition());					 
							  });
				      } else {
				        alert("Invalid address entered.");
				      }
				    });
			}	 
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			google.maps.event.addDomListener(window, 'load', initialize1);
			
			
			
			
			
			
			function initialize1() {
					
				  geocoder = new google.maps.Geocoder();			
				  var latLng = new google.maps.LatLng(1.287778, 103.851944);		 
				  map = new google.maps.Map(document.getElementById('mapCanvas'), {		 
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
					   });
			 
				  // Update current position info.		 
				  updateMarkerPosition(latLng);		 
				  geocodePosition(latLng);
				 
				  // Add dragging event listeners.		 
				  google.maps.event.addListener(marker, 'dragstart', function() {		 
				   // updateMarkerAddress('Dragging...');		 
				  });
				 		 		 
				  google.maps.event.addListener(marker, 'drag', function() {		 
				   // updateMarkerStatus('Dragging...');		 
				    updateMarkerPosition(marker.getPosition());		 
				  });
				 				 
				  google.maps.event.addListener(marker, 'dragend', function() {		 
				   // updateMarkerStatus('Drag ended');	 
				    geocodePosition(marker.getPosition());		    		 
				  });		 
				}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		</script>
	
		<style>
		  #mapCanvas {	 
		    width: 100%;		 
		    height: 300px;	 
		  }
 		 </style>
	
	<!--  
	<script src="http://www.google.com/jsapi?key=ABQIAAAAlJFc1lrstqhgTl3ZYo38bBQcfCcww1WgMTxEFsdaTsnOXOVOUhTplLhHcmgnaY0u87hQyd-n-kiOqQ"></script>
<script>
	(function () {
		google.load("maps", "2");
		google.setOnLoadCallback(function () {
			// Create map
			var map = new google.maps.Map2(document.getElementById("map")),
				markerText = "<h2>You are here</h2><p>Nice with geolocation, ain't it?</p>",
				markOutLocation = function (lat, long) {
					var latLong = new google.maps.LatLng(lat, long),
						marker = new google.maps.Marker(latLong);
					map.setCenter(latLong, 13);
					map.addOverlay(marker);
					marker.openInfoWindow(markerText);
					google.maps.Event.addListener(marker, "click", function () {
						marker.openInfoWindow(markerText);
					});
				};
				map.setUIToDefault();

			// Check for geolocation support	
			if (navigator.geolocation) {
				// Get current position
				navigator.geolocation.getCurrentPosition(function (position) {
						// Success!
						markOutLocation(position.coords.latitude, position.coords.longitude);
					}, 
					function () {
						// Gelocation fallback: Defaults to Stockholm, Sweden
						markerText = "<p>Please accept geolocation for me to be able to find you. <br>I've put you in Stockholm for now.</p>";
						markOutLocation(59.3325215, 18.0643818);
					}
				);
			}
			else {
				// No geolocation fallback: Defaults to Eeaster Island, Chile
				markerText = "<p>No location support. Try Easter Island for now. :-)</p>";
				markOutLocation(-27.121192, -109.366424);
			}
		});	
	})();
</script>-->


	
 	<form action="ArticleSubmissionServlet" method="post">
		 <h1></h1>
	
				
				
				<div class="panel panel-info">
					<div class="panel-heading">
						<h1 class="panel-title">Submit Article</h1>
					</div>
					<div class="panel-body">
					
						<div class="input-group">
		  					<span class="input-group-addon">Title: </span>
		 					<input type="text" class="form-control" placeholder="" name="title">
						</div>
				
				
						<br/>
				
						
		 			
				
							<select class="btn btn-default dropdown-toggle" style="width:100%; height:35px;" name="category">
								  <option>Select a category</option>
								  
								  <option>Feedback</option>
        						  <option>News Around The Neighbourhood</option>
                                  <option>Location Usage</option>
							</select>
						
						
						<br/>
						<br/>
						
						
							<textarea cols="50" placeholder="Place your content here..." style="width:100%; height:350px;" name="content"></textarea>
						
						
						<br/>
						<br/>
					
				<!-- 	<div id="map" style="width:100%;height:380px;"></div>-->
				
					
			<!--  	<div class="col-lg-6">-->
				<div>
    				<div class="input-group">
      					<!--  <input type="text" class="form-control">-->
      					<input type = "text" class="form-control" id="inputTextAddress" title="Address to Geocode" placeholder="Enter location here"/>
      						<span class="input-group-btn">
        						<!--  <button class="btn btn-default" type="button">Go!</button>-->
        						<input type="button" class="btn btn-default" value="Search" onclick="codeAddress();" id="inputButtonGeocode"  title="Search"></input>
     						 </span>
   						 </div><!-- /input-group -->
  				</div>
				
				
				
				
				
				
				
				
				
				
				  
				        
				  
					
					<br/>
			 		<div id="mapCanvas"></div>
 
				    <b>Current position:</b>	
				    <input type = "text" class="form-control" id="info" style="width:100%" name="coordinates" readonly/>
				    		 
				 <!--     <div id="info" name="coordinates"></div>-->			 
				    <b>Closest matching address:</b>	 
				<!--    <div id="address"></div>		  
				    <input type="text" class="form-control" placeholder="" name="title" id="test" style="width:100%;" readonly>	-->
				    <input type="text" class="form-control" placeholder="" name="address" id="testing" style="width:100%;" readonly>	
				    <br/>
				 <!--     <b>Location: </b>-->		    
				  		    
				 <!--     <div><input type = "text" id="inputCoordinates" style="width:200px" readonly/></div>-->				
					
					<input type = "text" class="form-control" id="storingLat" style="width:100%; display:none;" name="storingLat" readonly/>
					<input type = "text" class="form-control" id="storingLon" style="width:100%; display:none;" name="storingLon" readonly/>
					 &nbsp<input type="submit" class="btn btn-primary btn-sm" value="Submit" id="submitBtn">
					
					<form action="DisplayArticleMainServlet" method="post">
						<input type="submit" class="btn btn-primary btn-sm" value="Back" id="btn">
					</form>	
					</div>
				</div>
				
					   		
		 </form>	
	</jsp:attribute>
</t:master>


<script>
	$(document).ready(function() {
		//document.getElementById('storingLat').style.visibility = 'hidden';
		//document.getElementById('storingLon').style.visibility = 'hidden';
	});

</script>