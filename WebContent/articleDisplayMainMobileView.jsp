<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/dataTable/jquery.dataTables_themeroller.css" />
   		<link rel="stylesheet" type="text/css" href="css/dataTable/demo_page.css" />
   		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.custom.css" />
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	  	<script src="js/displayArticle.js"></script>
	  	<script type="text/javascript" language="javascript" src="js/dataTable/dataTables.js"></script>
   		<script type="text/javascript" language="javascript" src="js/dataTable/numberSort.js"></script>
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
			            document.getElementById('currentLat').value = 0;
						document.getElementById('currentLon').value = 0;
						document.getElementById('currentLat2').value = 0;
						document.getElementById('currentLon2').value = 0;
			            break;
			        case error.POSITION_UNAVAILABLE:
			            alert("Location information is unavailable.");
			            document.getElementById('currentLat').value = 0;
						document.getElementById('currentLon').value = 0;
						document.getElementById('currentLat2').value = 0;
						document.getElementById('currentLon2').value = 0;
			            break;
			        case error.TIMEOUT:
			            alert("The request to get user location timed out.");
			            document.getElementById('currentLat').value = 0;
						document.getElementById('currentLon').value = 0;
						document.getElementById('currentLat2').value = 0;
						document.getElementById('currentLon2').value = 0;
			            break;
			        case error.UNKNOWN_ERROR:
			            alert("An unknown error occurred.");
			            break;
			    }
			}
	 
		function initialize(position) {
		  //geocoder = new google.maps.Geocoder();			
		  var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);		 
		 /* map = new google.maps.Map(document.getElementById('mapCanvas'), {		 
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
			   });*/
	 
		  // Update current position info.		 
		  updateMarkerPosition(latLng);		 
		 
		 		 
		}
		
		
		function updateMarkerPosition(latLng) {
			// document.getElementById('info').innerHTML = [ latLng.lat(),latLng.lng()].join(', ');
			document.getElementById('currentLat').value = latLng.lat();
			document.getElementById('currentLon').value = latLng.lng();
			document.getElementById('currentLat2').value = latLng.lat();
			document.getElementById('currentLon2').value = latLng.lng();
			
			//document.getElementById('test').value = [latLng.lat(),latLng.lng()].join(', ');		 
			}
		</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<form action="DisplayArticleMainServlet" method="post">
	<br />
	 
	 <div class="panel panel-info" Style="width:100%;margin:0px auto;">
		<div class="panel-heading">
			<h2>Latest News</h2>
			
		</div>
		
		
		
		
		
		
		
		
		<div class="panel-body" Style="width:100%; " >
		
				
		
		
				<button type="submit" class="pull-right btn btn-primary btn-sm"value="Refresh" id="btn" style="margin-left:5px;">Refresh</button>
				
				<a class=" pull-right btn btn-primary btn-sm" onclick="location.href='articleSubmission.jsp'">Submit Article</a>
				<br/>
				
				<input type = "text" class="form-control" id="currentLat2" style="width:100%;display:none;" name="currentLat2"/>
				<input type = "text" class="form-control" id="currentLon2" style="width:100%;display:none;" name="currentLon2"/>
				
				
				</form>
				
				
				
				<hr/>
				<form action="DisplayArticleMainServlet2" method="post">
				<p style="color:#088A4B"><b>[Enter 0 to retrieve all articles]</b></p>
				<div>
    				<div class="input-group">
      					<span class="input-group-addon">Distance (km): </span>
      					<INPUT TYPE="NUMBER" MIN="0" MAX="20" STEP="1" VALUE="${selectedDist}" class="form-control" name="distSelected" id="distSelected">
      						<span class="input-group-btn">
        						<button type="submit" class="btn btn-default" value="Go" id="btnGO">Go</button>
     						 </span>
   						 </div>
  				</div>
  				
  				
  				<div class="input-group" style="margin-top:10px;">
		  					<span class="input-group-addon">Latitude: </span>
		 					<input type = "text" class="form-control" id="currentLat" style="width:100%;" name="currentLat" readonly/>
				</div>
				<div class="input-group" style="margin-top:10px;">
		  					<span class="input-group-addon">Longitude: </span>
		 					<input type = "text" class="form-control" id="currentLon" style="width:100%;" name="currentLon" readonly/>
				</div>
  				
				<!-- <input type = "text" class="form-control" id="test" style="width:100%;;" name="test"/>-->
				
				
  				</form>
				<hr/>
				
				<a type="submit" class="pull-right" href="DisplayArticleMainServlet3?currentLat=${currentLat}&currentLon=${currentLon}&distSelected=${selectedDist}" id="showArtLoc" name="article-content-id"><h4><u><b>View Article Locations</b></u></h4></a>
				<br id="brrr"/>
							
					<!--<table class="table table-striped">-->
					<table class="table table-striped" width="100%" border="1" id="articleTable">	
						<thead>
							<tr>
								<th>Articles:</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${artList}" var="item">
									<tr>
									 	
										<td>
											<h4><p class="text-warning"><b><u>${item.title}</u></b></p></h4>
											
											<label style="color:#B40431;">Posted By: ${item.articleUser}</label>
											<br/>
											${item.articleDate}
											<br/>
											${item.location }
											<br/>
											Distance: ${item.dist}km
										
											<br/>
											<a type="submit" href="DisplaySelectedArticle?id=${item.articleID }" id="" name="article-content-id"><u>Read More</u></a>
											
										</td>
										
										
											
									</tr>
								</c:forEach>
						
						
						</tbody>		
				 </table>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	</jsp:attribute>
</t:master>

<script>
	$(document).ready(function() {
		//$('#articleTable').dataTable();
		var selectedDistance = document.getElementById("distSelected").value;
		//alert(hi);
		if(selectedDistance==0){
			//document.getElementById("test").value="Shit";
			document.getElementById("showArtLoc").style.display="none";
			document.getElementById("brrr").style.display="none";
		}
		
		var locationAvailable = document.getElementById("currentLat").value;
		
		if(locationAvailable==0){
			//document.getElementById("btnGO").disabled=true;
		}
		else if(locationAvailable!=0){
			//document.getElementById("btnGO").disabled=false;
		}
		
	});
	
	
	
	

</script>