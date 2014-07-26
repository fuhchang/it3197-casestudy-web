<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false&libraries=geometry"></script>
		<script>
		$(document).ready(function(){
			var zoom;
			var geocoder = new google.maps.Geocoder();
			var location = "<c:out value="${event.eventLocation}" />";
			alert(location);
			if(detectmob()){
				zoom = 10;		
			}
			else{
				zoom = 11;
			}
				
			showAddress(location);
			
			var mapProp = {
				center : new google.maps.LatLng(1.3450, 103.8250),
				
				zoom : zoom,
				minZoom : zoom,
				maxZoom : 18,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
			
			//Detect whether the devices is a desktop web or mobile web
			function detectmob() { 
				 if( navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPod/i) || navigator.userAgent.match(/BlackBerry/i) || navigator.userAgent.match(/Windows Phone/i)){
				    return true;
				 }
				 else {
					 return false;
				 }
			}

			function showAddress(address) {
				geocoder.geocode( { 'address': address}, function(results, status) {
				      if (status == google.maps.GeocoderStatus.OK) {
				        map.setCenter(results[0].geometry.location);
				        map.setZoom(15);
				        var marker = new google.maps.Marker({
				            map: map,
				            position: results[0].geometry.location
				        });
				      } else {
				        alert("Geocode was not successful for the following reason: " + status);
				      }
				});
			}
		});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<div id=".col-xs-6 .col-md-4" style="margin-left:2%;">
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
			<div id="googleMap" style="width:98%;height:380px;"></div>
		</div>
	</jsp:attribute>
</t:master>