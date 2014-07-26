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
			var map;
			
			var mapProp = {
				center : new google.maps.LatLng(<c:out value="${event.lat}" />, <c:out value="${event.lng}" />),
				
				zoom : 15,
				minZoom : 15,
				maxZoom : 15,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

			var newMarker = new google.maps.Marker({
				position : new google.maps.LatLng(parseFloat(<c:out value="${event.lat}" />), parseFloat(<c:out value="${event.lng}" />)),
				map : map
			});
			google.maps.event.addListener(newMarker, 'click',function() {
				map.setZoom(15);
			});
			new google.maps.event.trigger(newMarker, 'click' );

			
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