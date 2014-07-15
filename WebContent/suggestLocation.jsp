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
		<!-- Import CSS here -->
		<style>
			#googleMap {
				margin-top: 20px;
				margin-left: 20px;
			}
			#legend {
			    background: white;
			 	padding: 10px;
			}
		</style>
		<!-- Import JS here -->
		<script src="plugins/jquery-2.x.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/master.js"></script>
		<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
		<script src="js/oneMap.js"></script>
   		<script type="text/JavaScript">
	   		$(document).ready(function(){
	   			var mapProp = {
	   				center:new google.maps.LatLng(1.3450,103.8250), 
	   				zoom:11,
	   				minZoom:11,
	   			  	mapTypeId:google.maps.MapTypeId.ROADMAP
				};
	   			var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
	   			
				var marker = [];
				var result = [];
				var output = [];
				
				$.ajax({
					url: "http://www.onemap.sg/API/services.svc/mashupData?token=xkg8VRu6Ol+gMH+SUamkRIEB7fKzhwMvfMo/2U8UJcFhdvR4yN1GutmUIA3A6r3LDhot215OVVkZvNRzjl28TNUZgYFSswOi&themeName=Museum",
					type: "GET",
					dataType: "jsonp",
				    success: function(data){
				        var size = data.SrchResults[0].FeatCount;
				        for(var i=1;i<size;i++){
				        	var name = data.SrchResults[i].NAME.toString();
				           	var postalCode = data.SrchResults[i].ADDRESSPOSTALCODE.toString();
				           	var streetName = data.SrchResults[i].ADDRESSSTREETNAME.toString();
				           	var hyperLink = data.SrchResults[i].HYPERLINK.toString();
				           	var xy = data.SrchResults[i].XY.toString();
				           	var iconName = data.SrchResults[i].ICON_NAME.toString();
				           	result.push([name,postalCode,streetName,hyperLink,xy,iconName]);
					        GetCords(i-1,xy);

				       	}
						//Name, address postal code, address street name, HYPERLINK, XY, ICON_NAME
				  	},
				   	error: function(req, status, ex) {
				  		alert(req[0] + status + ex);
				    }
				});
	   			function GetCords(i,xy) {
					var inXYList=xy;
					var CoordConvertorObj= new CoordConvertor();
					CoordConvertorObj.ConvertCoordinate(inXYList,3414,4326,showVals);
				}
				function showVals(outXY){
					//X = Longtitude, Y = Latitude
		           	var x = outXY.substr(0,outXY.indexOf(","));
		           	var y = outXY.substr(outXY.indexOf(",") + 1, outXY.length);
					output.push(outXY);
					var index = output.length;

					var image = 'images/mr.gif';
					var html = "<h5>"+result[index-1][0]+"</h5>"
					  + "<p>"+result[index-1][2]+"</p>"
					  + "<p style='margin-top:-10px;'>"+result[index-1][1]+"</p>"
					  + "<p> For more information: <a href='" + result[index-1][3] + "'>" + result[index-1][3] + "</a></p>";
					 var address = result[index-1][2] + "\n" + result[index-1][1];
					createMarker(y,x,html,image,address);
				}

				var prev_window = false;
				function createMarker(lat, lon, html, image,address) {
				    var newmarker = new google.maps.Marker({
				        position: new google.maps.LatLng(lat, lon),
				        map: map,
				        icon:image
				    });

				  	newmarker['infowindow'] = new google.maps.InfoWindow({
				   	    content: html
				  	});
				  	
				    google.maps.event.addListener(newmarker, 'click', function() {
				    	if(!prev_window){
				    	}
				    	else{
						  	prev_window.close();
				    	}
					  	this['infowindow'].open(map, this);
			        	prev_window = this['infowindow'];
			        	$(".selectedlocation").html(address);
				    });
				    marker.push(newmarker);
				}
				
				map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(document.getElementById('legend'));

				
				$(".selectLocation").click(function(){
					var location = $(".selectedlocation").html();
					$(window.opener.document).find("#location").html(location);
					window.close();
				});
				
	   		});
		</script>
	</head>
	<body>
		<div id="googleMap" style="width:95%;height:380px;"></div>
		<div id="legend">
		  Legend:
		</div>
		<div style="margin-left:30px;">
			<p class="selectedlocation"></p>
			<button class="selectLocation"> Select Location</button>
		</div> 
	</body>
</html>