<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0" />
		<title>Community Outreach</title>
		
		<!-- CSS Import -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/master.css" />
		<link rel="stylesheet" type="text/css" href="css/bootstrapValidator.css" />
		<link rel="stylesheet" type="text/css" href="css/ladda.css" />
		<!-- Import CSS here -->
		<style>
			#googleMap {
				margin-top: 20px;
				margin-left: 25px;
			}
			#legend {
			    background: white;
			 	padding: 15px;
			 	display:block;
			}
			a{
				width:65%;
				text-align:center;
			}
		</style>
		<!-- Import JS here -->
		<script src="plugins/jquery-2.x.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/master.js"></script>
		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
		<script src="js/oneMap.js"></script>
   		<script type="text/javascript" src="js/suggestLocation.js"></script>
   		<script type="text/javascript" src="js/spin.js"></script>
   		<script type="text/javascript" src="js/ladda.js"></script>
   		<script type="text/javascript" src="js/markerCluster.js"></script>
	</head>
	<body>
		<div id="googleMap" style="width:95%;height:380px;"></div>
		<div id="legend">
		  Legend:
		  <div id="forLegend" class='suggestLocation' style="margin-bottom:5px;margin-top:5px;"><img src='images/suggest.gif' class="suggestLocation" /><span class="suggestLocation" style="font-size:14px;margin-left:5px;">Recommended Location</span></div>
		  <div id="forLegend" class='Eldercare' style="margin-bottom:5px;margin-top:5px;"><img class="Eldercare"/><span class="Eldercare" style="font-size:14px;margin-left:5px;">Elder Care Services</span></div>
		  <div id="forLegend" class='Family' style="margin-bottom:5px;margin-top:5px;"><img class="Family"/><span class="Family" style="font-size:14px;margin-left:5px;">Family Services</span></div>
		  <div id="forLegend" class='VoluntaryWelfareOrgs' style="margin-bottom:5px;margin-top:5px;"><img class="VoluntaryWelfareOrgs"/><span class="VoluntaryWelfareOrgs" style="font-size:14px;margin-left:5px;">Voluntary Welfare Organizations</span></div>
		</div>
		<div class="row panel panel-primary" style="margin-left:2.5%;margin-right:2.5%;margin-top:1%;">
			<div class="panel-heading">
				Themes & Options
				<span id='down' class="glyphicon glyphicon-chevron-down" style='float:right;'></span>
				<span id='up' class="glyphicon glyphicon-chevron-up" style='float:right;'></span>
			</div>
			<div class="panel-body">
			  	<div class="col-xs-6">
					<p>Theme(s): </p>
					<div id="themeAvailable" class="list-group">
						<div id="education">
							<!-- <a class='list-group-item' id='Eldercare' style='width:50%'>Elder Care Services</a>
							<a class='list-group-item' id='Family' style='width:50%;'>Family Services </a>
							<a class='list-group-item' id='VoluntaryWelfareOrgs' style='width:50%'>Voluntary Welfare Organizations</a> -->
						</div>
						<div id="family">
							<a class='list-group-item' id='Eldercare'>Elder Care Services</a>
							<a class='list-group-item' id='Family'>Family Services </a>
							<a class='list-group-item' id='VoluntaryWelfareOrgs'>Voluntary Welfare Organizations</a>
						</div>
					</div>
				</div>
			  	<div class="col-xs-6">
					<p> Options: </p>
					<div>
						<a class='list-group-item suggestLocation'> Suggest Location </a>
						<a class='list-group-item deselectAllThemes'> Deselect All Theme(s) </a>
						<a class='list-group-item refreshMap'> Refresh Map </a>
					</div>
			  	</div>
			</div>
		</div>
		<div class="row" style="margin-left:2.5%;">
		  	<div class="col-xs-4" style='padding-left:0;'>
		  		<br/>
		  		<br/>
		  		<table>
		  			<tr><td class='selectedName'></td></tr>
		  			<tr><td class='selectedlocation'></td></tr>
		  			<tr><td class='selectedHyperlink'></td></tr>
		  		</table>
				<button class="btn btn-default selectLocation"> Select Location</button>
			</div>
			<br/>
		</div>
	</body>
</html>