<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script src="https://maps.googleapis.com/maps/api/js"></script>
		<script type="text/javascript"
			src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<style>
#map_canvas {
	width: 300px;
	height: 300px;
	background-color: #CCC;
}
</style>
		<script>
			var lat;
			var lng;
			var address;
			var geocoder = new google.maps.Geocoder();
			var infowindow = new google.maps.InfoWindow();
			$(document).ready(function() {
				getLocation();
				$("#getLocation").on('click', function() {
					
					getReverseGeocodingData(lat, lng);
					$("#test").append(lat + " " + lng);
				});
			});
			function getLocation() {
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(showPosition);
				} else {
					$("#locationla").append(
							"Geolocation is not supported by this browser.");
				}
			}

			function showPosition(position) {
				$("#locationla")
						.append("latitude: " + position.coords.latitude);
				$("#locationlo").append(
						"longitude: " + position.coords.longitude);
				lg = position.coords.longitude;
				la = position.coords.latitude;
				initialize();
				google.maps.event.addDomListener(window, 'load', initialize);
			}

			function initialize() {
				var map_canvas = document.getElementById('map_canvas');
				var map_options = {

					center : new google.maps.LatLng(la, lg),
					zoom : 16,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};
				var map = new google.maps.Map(map_canvas, map_options);
				var marker = new google.maps.Marker({
					position : new google.maps.LatLng(la, lg),
					map : map,
					title : 'your location',
					draggable : true

				});

				google.maps.event.addListener(marker, 'dragend', function(
						marker) {
					var latLng = marker.latLng;
					lat = latLng.lat();
					lng = latLng.lng();
				});
			}

			function getReverseGeocodingData(lat, lng) {
				var latlng = new google.maps.LatLng(lat, lng);
				// This is making the Geocode request
				var geocoder = new google.maps.Geocoder();
				geocoder.geocode({
					'latLng' : latlng
				}, function(results, status) {
					if (status !== google.maps.GeocoderStatus.OK) {
						alert(status);
					}
					// This is checking to see if the Geoeode Status is OK before proceeding
					if (status == google.maps.GeocoderStatus.OK) {
						console.log(results);
						var address = (results[0].formatted_address);
						alert(address);
					}
				});
			}
		</script>
	</jsp:attribute>

	<jsp:attribute name="content">
	<br>
		<div class="form-horizontal" role="form">
			<div class="panel panel-primary">
			<div class="panel-heading">
				Create Group Form
			</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="col-xs-3 control-label">Group Name</label>
						<input class="form-control" type="text" name="gName" />
					</div>
					<div class="form-group">
						<label class="col-xs-6 control-label">Group Type</label>
						<select class="form-control" id="gType">
							<option>item1</option>
							<option>item2</option>
							<option>item3</option>
						</select>
					</div>
					<div class="form-group">
						<label class="col-xs-5 control-label">Group Description</label>
						<textarea class="form-control" rows="3"></textarea>
					</div>
					<div class="form-group">
						 <button type="button" class="btn btn-default" id="getLocation">Select Location</button>
					</div>
					<div class="form-group">
						<div id="map_canvas"></div>
					</div>
					<div class="form-group">
					<label class="col-xs-6 control-label" id="test">muhaha</label>
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</t:master>