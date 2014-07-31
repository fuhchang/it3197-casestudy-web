<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports">
		<script src="https://maps.googleapis.com/maps/api/js"></script>
		<script>
			var temp = 0;
			var oldPosition;
			var currentPosition;
			var positionArr = new Array();
			var markersArr = new Array();
			
			$(document).ready(function() {
				additionalPt = document.getElementById("additionalPt");
				hiddenAdditionalPt = document.getElementById("hiddenAdditionalPt");
				
				//additionalPt.innerHTML = temp;
				//hiddentAddtionalPt.value = addtionalPt.innerHTML;
				additionalPt.innerHTML = 50; // For presentation purpose, else remove this line
				hiddenAdditionalPt.value = 50; // For presentation purpose, else remove this line
				
				//navigator.geolocation.watchPosition(function(position) {
				navigator.geolocation.getCurrentPosition(function(position) {
					/*
					if(oldPosition == null)
						oldPosition = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
					else
						oldPosition = currentPosition;
					currentPosition = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);*/
					var currentPosition = new google.maps.LatLng(1.379348, 103.849876); // For presentation purpose, else remove this line
					
					// Write the formatted address
				    writeAddressName(currentPosition);
					
					// Draw the map
					var map = new google.maps.Map(document.getElementById("map"), {
						zoom: 15,
						center: currentPosition,
						mapTypeId: google.maps.MapTypeId.ROADMAP
					});
					
					positionArr.push(currentPosition);
					/* For presentation purpose, remove these lines below */
					positionArr.push(new google.maps.LatLng(1.378043, 103.84576190000007));
					positionArr.push(new google.maps.LatLng(1.3806469, 103.84651409999992));
					positionArr.push(new google.maps.LatLng(1.381905, 103.84481800000003));
					/* For presentation purpose, remove these lines above */
					
					//if(!oldPosition.equals(currentPosition)){
						//markersArr.setMap(null);
						
						var marker;
						var i = 0;
						do {
							// Starting point marker
							if(markersArr[0] == null){
								marker = new google.maps.Marker({
									map: map,
									icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
									position: positionArr[i]
								});
							}
							// End point marker
							else if((i+1) == positionArr.length) {
								marker = new google.maps.Marker({
									map: map,
									icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
									position: positionArr[i]
								});
							}
							// In between marker
							else{
								marker = new google.maps.Marker({
									map: map,
									icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
									position: positionArr[i]
								});
							}
							markersArr.push(marker);
							i++;
						} while (i < positionArr.length);
						
						for(var index = 0; index < markersArr.length; index++){
							markersArr[index].setMap(map);
						}
						
						var path = new google.maps.Polyline({
						    path: positionArr,
						    geodesic: true,
						    strokeColor: '#FF0000',
						    strokeOpacity: 1.0,
						    strokeWeight: 2
						});
						path.setMap(map);
						
						//temp++;
					//}
					
				}, geolocationError, {enableHighAccuracy: true});
			
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
			});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<h1>Profile</h1>
		
		<form action="ProfileWebServlet" method="post">
			<div class="panel panel-default">
			  <div class="panel-body">
			    <p>Name: ${user.name}</p>
			    <p>Points: ${user.points} (+<span id="additionalPt">${additionalPt}</span>)
					<input type="hidden" id="hiddenAdditionalPt" name="additionalPt" value=""/>
			    	<button class="btn btn-info pull-right" value="addPoints"><span class="glyphicon glyphicon-plus-sign"></span> Add Points</button>
			    </p>
			  </div>
			</div>
		</form>
		
		<div id="map" style="width:100%;height:40%"></div>
		<br />
	    <p><b>Address</b>:<br><span id="address"></span></p>
	    <p id="error"></p>
	</jsp:attribute>
</t:master>