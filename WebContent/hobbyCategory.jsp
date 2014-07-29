<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script src="http://maps.google.com/maps/api/js?sensor=false"></script>

		<!-- Include jQuery -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
		<Script type="text/javascript">
		
		 var longitude;
		 var latitude;
		 
		 google.maps.event.addDomListener(window, 'load', initialize);

			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(initialize, showError);
			} else {
				x.innerHTML = "Geolocation is not supported by this browser.";
			}
		
			function showError(error) {
				switch (error.code) {
				case error.PERMISSION_DENIED:
					alert("User denied the request for Geolocation.");
					alert("You must allow to be able to use this service.");
					document.getElementById("info").style.visibility = "hidden";
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
				var latLng = new google.maps.LatLng(position.coords.latitude,
						position.coords.longitude);
				$("#lat").val(position.coords.latitude);
				$("#lng").val(position.coords.longitude);
				$("#lat1").val(position.coords.latitude);
				$("#lng1").val(position.coords.longitude);
				$("#lat2").val(position.coords.latitude);
				$("#lng2").val(position.coords.longitude);
				longitude = position.coords.longitude;
				latitude = position.coords.latitude;
				//alert(latitude);
				//alert(longitude);
				geocodePosition(latLng);
				
			
			}
			
			function geocodePosition(pos) {
				geocoder
						.geocode(
								{
									latLng : pos
								},
								function(responses) {
									if (responses && responses.length > 0) {
										var loc =  document.getElementById("myLoc");
										loc.value = "All hobby group is within 1km radius of " + responses[0].formatted_address;
									} else {
										alert('Cannot determine address at this location.');
									}
								});
			}
			
			
			
			
		</Script>
	</jsp:attribute>

	<jsp:attribute name="content">
		<div class="col-xs-12">
		<h2>Select Category </h2>
		</div>
		<div class="panel panel-info">
			
		</div>
					<table class="table">
			<tbody>
			<tr>
				<td>
				<textarea cols="50" style="width: 100%; height: 80px;" name="myLoc" id="myLoc" readonly></textarea>
				</td>
			</tr>
			
				<tr>
					<td>
					<form action="SearchHobbyForRequest" method="post">
					<input type="hidden" name="cateID" value="1"/>
					<input type="hidden" name="lat" id="lat" />
					<input type="hidden" name="lng" id="lng"/>
					<button type ="submit" class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true" id="btnD" >Dance<i class="glyphicon glyphicon-play pull-right"></i></button>
					</form>
           			 </td>
				</tr>
				<tr>
					<td>
						<form action="SearchHobbyForRequest" method="post">
					<input type="hidden" name="cateID" value="2"/>
					<input type="hidden" name="lat" id="lat1" />
					<input type="hidden" name="lng" id="lng1"/>
						<button class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true" id="btnCooking" onclick="location.href='SearchHobbyForRequest?id=2'">Cooking<i class="glyphicon glyphicon-play pull-right"></i></button>
						</form>
						
					</td>
				</tr>
				<tr>
					<td>
					<form action="SearchHobbyForRequest" method="post">
					<input type="hidden" name="cateID" value="3"/>
					<input type="hidden" name="lat" id="lat2" />
					<input type="hidden" name="lng" id="lng2"/>
						<button class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true" id="btnGarden" onclick="location.href='SearchHobbyForRequest?id=3'">Gardening<i class="glyphicon glyphicon-play pull-right"></i></button>
						</form>
						
					</td>
				</tr>
			</tbody>
			</table>
			
			
			
		</div>

	</jsp:attribute>
</t:master>
