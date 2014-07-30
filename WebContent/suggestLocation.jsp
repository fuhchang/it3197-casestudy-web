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
				margin-top: 1%;
				margin-left: 1%;
			}
			#legend {
			    background: white;
			 	padding: 2%;
			 	display:block;
			}
			a{
				text-align:center;
			}
			#selectLocation{
				margin-top:-0.2%;
				margin-left:80%;
			}
		</style>
		<!-- Import JS here -->
		<script src="plugins/jquery-2.x.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/master.js"></script>
		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false&libraries=geometry"></script>
		<script src="js/oneMap.js"></script>
   		<script type="text/javascript" src="js/suggestLocation.js"></script>
   		<script type="text/javascript" src="js/spin.js"></script>
   		<script type="text/javascript" src="js/ladda.js"></script>
   		<script type="text/javascript" src="js/markerCluster.js"></script>
	</head>
	<body>
		<div id="selectLocation">
			<button class="btn btn-primary selectLocation"> 
			 	<span class="glyphicon glyphicon-plus-sign"></span> Select Location
			</button>
		</div>
		<div class="row panel panel-primary" style="margin-left:1%;margin-right:1%;margin-top:1%;">
			<div class="panel-heading">
				Themes & Options
				<span id='down' class="glyphicon glyphicon-chevron-down" style='float:right;'></span>
				<span id='up' class="glyphicon glyphicon-chevron-up" style='float:right;'></span>
			</div>
			<div class="panel-body">
			  	<div class="col-xs-6 col-md-8">
					<p>Theme(s): </p>
					<div id="themeAvailable" class="list-group">
						<div id="arts">
							<a class='list-group-item' id='MONUMENTS' style='width:50%;'>Monuments</a>
							<a class='list-group-item' id='NATIONALPARKS' style='width:50%'>Parks</a>
							<a class='list-group-item' id='TOURISM' style='width:50%'>Tourism</a>
						</div>
						<div id="education">
							<a class='list-group-item' id='COMMUNITYCLUBS' style='width:50%;'>Community Clubs</a>
							<a class='list-group-item' id='HERITAGESITES' style='width:50%'>Heritage Sites</a>
							<a class='list-group-item' id='LIBRARIES' style='width:50%'>Libraries</a>
						</div>
						<div id="family">
							<a class='list-group-item' id='Eldercare'>Elder Care Services</a>
							<a class='list-group-item' id='Family'>Family Services </a>
							<a class='list-group-item' id='VoluntaryWelfareOrgs'>Voluntary Welfare Organizations</a>
						</div>
						<div id="health">
							<a class='list-group-item' id='EXERCISEFACILITIES'>Exercise Facilities</a>
							<a class='list-group-item' id='REGISTERED_PHARMACY'>Retail Pharmacy Locations </a>
							<a class='list-group-item' id='RelaxSG'>Relax@SG</a>
						</div>
					</div>
				</div>
			  	<div class="col-xs-6 col-md-4">
					<p> Options: </p>
					<div>
						<a class='list-group-item suggestLocation'> Suggest Location </a>
						<a class='list-group-item deselectAllThemes'> Deselect All Theme(s) </a>
						<a class='list-group-item refreshMap'> Refresh Map </a>
					</div>
			  	</div>
			</div>
		</div>
		<div id="googleMap" style="width:98%;height:380px;"></div>
		<div id="legend">
		  Legend:
		  <!-- Arts -->
		  <div id="forLegend" class='MONUMENTS' style="margin-bottom:5px;margin-top:5px;"><img class="MONUMENTS"/><span class="MONUMENTS" style="font-size:14px;margin-left:5px;">Monuments</span></div>
		  <div id="forLegend" class='NATIONALPARKS' style="margin-bottom:5px;margin-top:5px;"><img class="NATIONALPARKS"/><span class="NATIONALPARKS" style="font-size:14px;margin-left:5px;">Parks</span></div>
		  <div id="forLegend" class='TOURISM' style="margin-bottom:5px;margin-top:5px;"><img class="TOURISM"/><span class="TOURISM" style="font-size:14px;margin-left:5px;">Tourism</span></div>
		  <!-- Education -->
		  <div id="forLegend" class='COMMUNITYCLUBS' style="margin-bottom:5px;margin-top:5px;"><img class="COMMUNITYCLUBS"/><span class="COMMUNITYCLUBS" style="font-size:14px;margin-left:5px;">Community Clubs</span></div>
		  <div id="forLegend" class='HERITAGESITES' style="margin-bottom:5px;margin-top:5px;"><img class="HERITAGESITES"/><span class="HERITAGESITES" style="font-size:14px;margin-left:5px;">Heritage Sites</span></div>
		  <div id="forLegend" class='LIBRARIES' style="margin-bottom:5px;margin-top:5px;"><img class="LIBRARIES"/><span class="LIBRARIES" style="font-size:14px;margin-left:5px;">Libraries</span></div>
		  <!-- Family -->
		  <div id="forLegend" class='Eldercare' style="margin-bottom:5px;margin-top:5px;"><img class="Eldercare"/><span class="Eldercare" style="font-size:14px;margin-left:5px;">Elder Care Services</span></div>
		  <div id="forLegend" class='Family' style="margin-bottom:5px;margin-top:5px;"><img class="Family"/><span class="Family" style="font-size:14px;margin-left:5px;">Family Services</span></div>
		  <div id="forLegend" class='VoluntaryWelfareOrgs' style="margin-bottom:5px;margin-top:5px;"><img class="VoluntaryWelfareOrgs"/><span class="VoluntaryWelfareOrgs" style="font-size:14px;margin-left:5px;">Voluntary Welfare Organizations</span></div>
		  <!-- Health -->
		  <div id="forLegend" class='EXERCISEFACILITIES' style="margin-bottom:5px;margin-top:5px;"><img class="EXERCISEFACILITIES"/><span class="EXERCISEFACILITIES" style="font-size:14px;margin-left:5px;">Exercise Facilities</span></div>
		  <div id="forLegend" class='REGISTERED_PHARMACY' style="margin-bottom:5px;margin-top:5px;"><img class="REGISTERED_PHARMACY"/><span class="REGISTERED_PHARMACY" style="font-size:14px;margin-left:5px;">Retail Pharmacy Locations </span></div>
		  <div id="forLegend" class='RelaxSG' style="margin-bottom:5px;margin-top:5px;"><img class="RelaxSG"/><span class="RelaxSG" style="font-size:14px;margin-left:5px;">Relax@SG</span></div>
		</div>
		<br/>
		<div class="row" style="margin-left:1%;margin-right:0%;margin-bottom:1%;">
		  	<div class="col-xs-12" style='padding-left:0;padding-right:1%;'>
		  		<table class="table table-bordered selectLocationTable">
		  			<tr><td colspan="4" style="background-color:#428bca;color:#fff;"><h4> Information </h4></td></tr>
		  			<tr><td colspan="4" class='selectedName'></td></tr>
		  			<tr><td class='selectedlocation' colspan='4'></td></tr>
		  			<tr><td colspan='4'><a class="hyperL"><span class='selectedHyperlink'></span></a></td></tr>
		  			<tr><td>Latitude: </td><td class='selectedLatitude'></td><td>Longtitude: </td><td class='selectedLongtitude'></td></tr>
		  		</table>
			</div>
			<br/>
		</div>
	</body>
</html>		