<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	</jsp:attribute>
	<jsp:attribute name="content">	
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
	    <meta charset="utf-8">	    
	    <style>
	      html, body, #map-canvas {
	        height: 100%;
	        margin: 0px;
	        padding: 0px
	      }
	    </style>
	    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
	    <script>
			function initialize() {				
			  //var myLatlng = new google.maps.LatLng(1.287778,103.851944);			
			  var myLatlng = new google.maps.LatLng(${dbLat},${dbLon});
			  var mapOptions = {
			    zoom: 18,
			    center: myLatlng,
			    mapTypeId: google.maps.MapTypeId.ROADMAP 
			  }
			  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);		
			  var marker = new google.maps.Marker({
			      position: myLatlng,
			      map: map			
			  });		  
			  var infowindow = new google.maps.InfoWindow({
				   content:"<p><b>Place of occurrence</b></p>"
				   });			  
			  infowindow.open(map,marker);
			  google.maps.event.addListener(marker, 'click', function() {
				   infowindow.open(map,marker);
				   });
			}			
			google.maps.event.addDomListener(window, 'load', initialize);			
		</script>
		<br />	 
		<div class="panel panel-info" Style="width:500px;margin:0px auto;">
			<div class="panel-heading">
				<!--  <h1 class="panel-title">Latest News From Around The Neighbourhood</h1>-->
				<h2>${articleTitle}</h2>
				<h4 class="text-warning"><b>Author: ${articleUserName} </b><label class="pull-right">${articleDate }</label></h4>				
			</div>			
			<div class="panel-body" Style="margin:0px auto;" >
				<p>${articleContent}</p>
				<hr/>			
				<h4>${articleLocation}</h4>
				<div class="panel-body" Style="width:100%; height:300px;margin:0px auto; border:1px solid black;" >
					<div id="map-canvas"></div>
				</div>	
			</div>
			<form action="DesktopLatestSelectedArticle" method="post">
				<!-- <div class="checkbox">
				 &nbsp <label>
				   <input type="checkbox" value="Yes" name="chkBox" style="margin-left:2px;">
				    Is this feedback legitimate?
				  </label>
				</div>-->
				
				
			<!--  	&nbsp&nbsp&nbsp&nbsp<select class="btn btn-default dropdown-toggle" style="width:300px;" name="legit">
								  <option>Post to Main Page</option>
								  <option>Discard</option>
				</select>-->
				
				<input type = "text" class="form-control" id="idArticle" style="width:100%;display:none;" name="idArticle" value = "${idArticle}"/>
				
				<table>
					<tbody>
						<tr>
							<td>&nbsp&nbsp&nbsp&nbsp<a type="submit"  class="btn btn-primary btn-sm" href="ArticleResponse?id=${idArticle}&decision=Approved" id="" name="article-content-id">Accept and Inform Applicant</a></td>
							<td>&nbsp<a type="submit"  class="btn btn-primary btn-sm" href="ArticleResponse?id=${idArticle}&decision=Discard" id="" name="article-content-id">Reject and Inform Applicant</a></td>
						
							</form>	
							<form action="DesktopLocationArticle" method="post">
								<td>&nbsp<input type="submit" class="btn btn-primary btn-sm" value="Back" id="btn"></td>
							</form>
						</tr>
					</tbody>
				</table>
		</div>
		<br/>
	</jsp:attribute>
</t:master>


<script>
	$(document).ready(function() {
		
	});

</script>
