<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<style>
        #control {
	        background: #fff;
	        padding: 0.5%;
	        font-size: 14px;
	        font-family: Arial;
	        border: 1px solid #ccc;
	        box-shadow: 0 2px 2px rgba(33, 33, 33, 0.4);
	        display: none;
      	}
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false&libraries=geometry"></script>
		<script>
		$(document).ready(function(){
			var map;
			var directionsDisplay = new google.maps.DirectionsRenderer();
			var directionsService = new google.maps.DirectionsService();
			var trafficLayer = new google.maps.TrafficLayer();
			var transitLayer = new google.maps.TransitLayer();
			var x;
			var y;	
			
			var mapProp = {
				center : new google.maps.LatLng(<c:out value="${event.lat}" />, <c:out value="${event.lng}" />),
				
				zoom : 15,
				minZoom : 11,
				maxZoom : 18,
				mapTypeId : google.maps.MapTypeId.ROADMAP,
			};
			map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

			var control = document.getElementById('control');
			control.style.display = 'block';
			map.controls[google.maps.ControlPosition.TOP_CENTER].push(control);

			directionsDisplay.setMap(map);
			
			 // Try HTML5 geolocation
			if(navigator.geolocation) {
				navigator.geolocation.watchPosition(showPosition);
			}

			function showPosition(position) {
				y = position.coords.latitude;
				x = position.coords.longitude;
			}
			
			var locationPosition = new google.maps.LatLng(parseFloat(<c:out value="${event.lat}" />), parseFloat(<c:out value="${event.lng}" />))
			var locationMarker = new google.maps.Marker({
				position : locationPosition,
				map : map
			});
			
			google.maps.event.addListener(locationMarker, 'click',function() {
				map.setZoom(15);
			});
			new google.maps.event.trigger(locationMarker, 'click' );

			var end = parseFloat(<c:out value="${event.lat}" />) + ", " + parseFloat(<c:out value="${event.lng}" />);

			$("#information").click(function(){
				transitLayer.setMap(null);
				directionsDisplay.setMap(null);
				directionsDisplay.setPanel(null);
				locationMarker = new google.maps.Marker({
					position : locationPosition,
					map : map
				});
				
				google.maps.event.addListener(locationMarker, 'click',function() {
					map.setZoom(15);
				});

				new google.maps.event.trigger(locationMarker, 'click' );
			});
			
			$("#DRIVING").click(function(){
				directionsDisplay.setMap(map);
				getDirections("DRIVING");
				transitLayer.setMap(null);
			});
			$("#TRANSIT").click(function(){
				directionsDisplay.setMap(map);
				getDirections("TRANSIT");
				transitLayer.setMap(map);
			});

			$('#control #traffic').change(function () {
				var checked = $(this).is(":checked");
				if(!checked){
					trafficLayer.setMap(null);
					return;
				}
				else{
					trafficLayer.setMap(map);
					return;
				}
			});


			function getDirections(by){
				locationMarker.setVisible(false);
				map.setZoom(11);
				directionsDisplay.setPanel(document.getElementById('directions-panel'));
				var request = {
			 		origin: y + ", " + x,
					destination: end,
				    travelMode: google.maps.TravelMode[by],
				    provideRouteAlternatives: true,
				};
				directionsService.route(request, function(response, status) {
					if (status == google.maps.DirectionsStatus.OK) {
						directionsDisplay.setDirections(response);
					}
				});
			}

		});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<div id=".col-xs-6 .col-md-4" style="padding-bottom: 2%;">
			<h6>Event No: <c:out value="${event.eventID}" /></h6>
			<img/>
			<h2><c:out value="${event.eventName}" /></h2>
			<table>
				<tr>
					<td>Category:</td>
				</tr>
				<tr>
					<td><c:out value="${event.eventCategory}" /></td>
				</tr>
				<tr>
					<td>Description:</td>
				</tr>
				<tr>
					<td><c:out value="${event.eventDescription}" /></td>
				</tr>
				<tr>
					<td>From:</td>
				</tr>
				<tr>
					<td><c:out value="${event.eventDateTimeFrom}" /></td>
				</tr>
				<tr>
					<td>To:</td>
				</tr>
				<tr>
					<td><c:out value="${event.eventDateTimeTo}" /></td>
				</tr>
				<tr>
					<td>Occurs:</td>
				</tr>
				<tr>
					<td><c:out value="${event.occurence}" /></td>
				</tr>
				<tr>
					<td>No of participants allowed:</td>
				</tr>
				<tr>
					<td><c:out value="${event.noOfParticipantsAllowed}" /></td>
				</tr>
			</table>
			<br/>
			
			<div id="googleMap" style="width:100%;height:380px;margin-right:0;"></div>
			
			
			<ul class="nav nav-tabs" role="tablist" id="myTab">
			  <li class="active"><a href="#informations" role="tab" data-toggle="tab" id="information">Information</a></li>
			  <li class="dropdown">
			    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
			      Navigate Me<span class="caret"></span>
			    </a>
			    <ul class="dropdown-menu" role="menu">
			  		<li><a href="#navigate" role="tab" data-toggle="tab" id="DRIVING">By Car</a></li>
			  		<li><a href="#navigate" role="tab" data-toggle="tab" id="TRANSIT">By Transit</a></li>
			    </ul>
			  </li>
			</ul>
			<br/>
			<div class="tab-content">
				<div class="tab-pane active" id="informations">
					<p><c:out value="${event.eventLocation}" /></p>
				</div>
  				<div class="tab-pane" id="navigate">
    				<div id="directions-panel"></div>
  				</div>
			</div>
			<div id="control">
  				Show traffic:
  				<input type="checkbox" id="traffic" name="traffic" value="traffic"/>
  			</div>
		</div>
		<br/>
		<br/>
	</jsp:attribute>
</t:master>