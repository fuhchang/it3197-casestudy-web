<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports">
		<script src="https://maps.googleapis.com/maps/api/js"></script>
		<script>
			var allPoints = new Array;
			var additionalPt;
			var temp = 0;
			
			$(document).ready(function(){
				additionalPt = document.getElementById("additionalPt");
				additionalPt.innerHTML = temp;
				geolocateUser();
			});
			
			function geolocateUser() {
				// If the browser supports the Geolocation API
				if (navigator.geolocation)
				{
				  var positionOptions = {
						  enableHighAccuracy: true,
						  timeout: 120000 // 2 minute
				  };
				  //navigator.geolocation.getCurrentPosition(geolocationSuccess, geolocationError, positionOptions);
				  navigator.geolocation.watchPosition(geolocationSuccess, geolocationError, positionOptions);
				}
				else
				  document.getElementById("error").innerHTML += "Your browser doesn't support the Geolocation API";
			}
			 
			function geolocationSuccess(position) {
				var oldUserLatLng = userLatLng;
				var userLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
				
				// Write the formatted address
			    writeAddressName(userLatLng);
				
			    var mapOptions = {
			    		zoom : 16,
			    		center : userLatLng,
			    		mapTypeId : google.maps.MapTypeId.ROADMAP
			    };
			    
			    // Draw the map
				var map = new google.maps.Map(document.getElementById("map"), mapOptions);
			    
				// Place the marker
				new google.maps.Marker({
					map: map,
					position: userLatLng
				});
				
				if(!oldUserLatLng.equals(userLatLng)) {
					allPoints.push(userLatLng);
					temp++;
					additionalPt.innerHTML = temp;
				}
				
				/*var coordinates = [new google.maps.LatLng(oldUserLatLng), new google.maps.LatLng(userLatLng)];
				var path = new google.maps.Polyline({
				    path: coordinates,
				    geodesic: true,
				    strokeColor: '#FF0000',
				    strokeOpacity: 1.0,
				    strokeWeight: 2
				});
				path.setMap(map);*/
				
				/*if (position.coords.accuracy <= 50) {
					navigator.geolocation.clearWatch(watchID);
				}*/
			}
			
			function writeAddressName(latLng) {
			    var geocoder = new google.maps.Geocoder();
			    geocoder.geocode({"location": latLng}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK)
					document.getElementById("address").innerHTML = results[0].formatted_address;
				else
				    document.getElementById("error").innerHTML += "Unable to retrieve your address" + "<br />";
				});
			}
			 
			function geolocationError(positionError) {
				document.getElementById("error").innerHTML += "Error: " + positionError.message + "<br />";
			}
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<h1>Profile</h1>
		
		<div class="panel panel-default">
		  <div class="panel-body">
		    <p>Name: ${user.name}</p>
		    <p>Points: ${user.points} (+<span id="additionalPt"></span>)
		    	<button type="submit" class="btn btn-info pull-right" value="addPoints"><span class="glyphicon glyphicon-plus-sign"></span> Add Points</button>
		    </p>
		  </div>
		</div>
		
		<div id="map" style="width:100%;height:40%"></div>
		<br />
	    <p><b>Address</b>:<br><span id="address"></span></p>
	    <p id="error"></p>
	</jsp:attribute>
</t:master>