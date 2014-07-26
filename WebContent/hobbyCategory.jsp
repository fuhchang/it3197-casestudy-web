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
		$(document).ready(function(){
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
										alert(responses[0].formatted_address);
									} else {
										alert('Cannot determine address at this location.');
									}
								});
			}
			/*
			
				$('.btnDance').click(function(){
					var mge = "1";
					mge += ",";
					mge += longitude;
					mge += ",";
					mge += latitude;
					sendData(mge);
				});
			});
			function sendData(mge){
				
				 $.ajax({
			          type: "POST",
			          url: "SearchHobbyForRequest",
			          data: { message : mge},
			          success : function(data){
			              alert(data);
			          }
			        });
			}
			*/
		</Script>
	</jsp:attribute>

	<jsp:attribute name="content">
		<div class="col-xs-12">
		<h2>Select Category </h2>
		</div>
					<table class="table">
			<tbody>
			
				<tr>
					<td>
					
					<button type ="submit" class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true" id="btnDance" onclick="location.href='SearchHobbyForRequest?id=1'">Dance<i class="glyphicon glyphicon-play pull-right"></i></button>
					
           			 </td>
				</tr>
				<tr>
					<td>
					
						<button class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true" id="btnCooking" onclick="location.href='SearchHobbyForRequest?id=2'">Cooking<i class="glyphicon glyphicon-play pull-right"></i></button>
						
						
					</td>
				</tr>
				<tr>
					<td>
					
						<button class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true" id="btnGarden" onclick="location.href='SearchHobbyForRequest?id=3'">Gardeing<i class="glyphicon glyphicon-play pull-right"></i></button>
						
						
					</td>
				</tr>
			</tbody>
			</table>
			
			
			
		</div>

	</jsp:attribute>
</t:master>
