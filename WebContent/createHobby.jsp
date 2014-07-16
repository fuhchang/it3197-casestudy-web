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
		<script type="text/javascript">
			var geocoder;
			var map;
			var marker;
			//Onload handler to fire off the app.

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
				map = new google.maps.Map(document.getElementById('mapCanvas'),
						{
							zoom : 18,
							center : latLng,
							mapTypeId : google.maps.MapTypeId.ROADMAP
						});
				var infowindow = new google.maps.InfoWindow(
						{
							content : "<h2>Selected Your Group office</h2> <p>Not the location you want? Change the location by dragging the marker</p>"
						});
				marker = new google.maps.Marker({
					position : latLng,
					title : 'Location point',
					map : map,
					draggable : true
				});
				infowindow.open(map, marker);
				google.maps.event.addListener(marker, 'click', function() {
					infowindow.open(map, marker);
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
				geocoder
						.geocode(
								{
									latLng : pos
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
				document.getElementById('info').value = [ latLng.lat(),
						latLng.lng() ].join(', ');
				//document.getElementById('test').value = [latLng.lat(),latLng.lng()].join(', ');		 
			}

			function updateMarkerAddress(str) {
				// document.getElementById('address').innerHTML = str;

				document.getElementById('selectedLoc').value = str;
			}

			function codeAddress() {
				var address = document.getElementById("inputTextAddress").value;
				geocoder.geocode({
					'address' : address
				}, function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						//marker.setMap(null);	
						map.setCenter(results[0].geometry.location);
						var latitude = results[0].geometry.location.lat();
						;
						var longitude = results[0].geometry.location.lng();
						marker.setPosition(new google.maps.LatLng(latitude,
								longitude));
						latLng = new google.maps.LatLng(latitude, longitude);
						geocodePosition(latLng);
						//document.getElementById('info').innerHTML = latitude + ", " + longitude;
						document.getElementById('info').value = latitude + ", "
								+ longitude;
						//			document.getElementById('test').value=latitude + ", " + longitude;

						// Add dragging event listeners.					 
						google.maps.event.addListener(marker, 'dragstart',
								function() {
									// updateMarkerAddress('Dragging...');					 
								});
						google.maps.event.addListener(marker, 'drag',
								function() {
									// updateMarkerStatus('Dragging...');					 
									updateMarkerPosition(marker.getPosition());
								});
						google.maps.event.addListener(marker, 'dragend',
								function() {
									// updateMarkerStatus('Drag ended');					 
									geocodePosition(marker.getPosition());
								});
					} else {
						alert("Invalid address entered.");
					}
				});
			}

			function imageSelector() {
				var path;
				$("#imgSelector").On('Click', function() {
					path = $("#imgSelector").text();
					alert(path);
				});
			}

			function readURL(input) {

				if (input.files && input.files[0]) {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('#blah').attr('src', e.target.result);
					}

					reader.readAsDataURL(input.files[0]);
				}
			}

			$("#imgInp").change(function() {
				readURL(this);
			});
		</script>
	
		<style>
#mapCanvas {
	width: 100%;
	height: 300px;
}
</style>
	</jsp:attribute>

	<jsp:attribute name="content">
	<br>	
			<form action="CreateHobbyWebServlet" method="post">
			<div class="panel panel-info">
			<div class="panel-heading">
				<h2>Create Group Form</h2>
			</div>
				<div class="panel-body">
				<div class="input-group">
		  					<span class="input-group-addon">Title: </span>
		 					<input type="text" class="form-control" placeholder=""
							name="gtitle">
						</div>
				<br />
				
				<select class="btn btn-default dropdown-toggle"
						style="width: 100%; height: 35px;" name="gType">
								  <option>Select a category</option>
								  
								  <option>item1</option>
        						  <option>item2</option>
                                  <option>item3</option>
							</select>
				<br />
				<br />
				<textarea cols="50" placeholder="Enter your Description here"
						style="width: 100%; height: 350px;" name="gDesc"></textarea>
				<br />
				<br />
				<div class="input-group">
					<input type='file' id="imgInp" name="imgFile"/>
   					 <img id="blah" src="#" alt="your image" height="42" width="42"/>
				</div>
				<div>
    				<div class="input-group">
      					<!--  <input type="text" class="form-control">-->
      					<input type="text" class="form-control" id="inputTextAddress"
								title="Address to Geocode" placeholder="Enter location here" />
      						<span class="input-group-btn">
        						<!--  <button class="btn btn-default" type="button">Go!</button>-->
        						<input type="button" class="btn btn-default"
								value="Search" onclick="codeAddress();" id="inputButtonGeocode"
								title="Search"></input>
     						 </span>
   						 </div>
  				</div>
  				
  				<br />
  				<!-- map -->
  				<div id="mapCanvas"></div>
  				<!-- current location -->
  				 <b>selected location:</b>
  				 <input type="text" class="form-control" id="info"
						style="width: 100%" name="coordinates" readonly />
  				 <!-- selected position -->
  				 <b>Selected matching address:</b>
  				  <input type="text" class="form-control" placeholder=""
						name="gLoc" id="selectedLoc" style="width: 100%;" readonly>
  				  <!-- submit -->
  				  <br />
  				   &nbsp<input type="submit" class="btn btn-primary btn-sm"
						value="Submit" id="submitBtn">	
			</div>
		</div>
		</form>
	
	</jsp:attribute>
</t:master>