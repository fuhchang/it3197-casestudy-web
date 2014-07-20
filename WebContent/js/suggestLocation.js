$(document).ready(function() {
					var mapProp = {
						center : new google.maps.LatLng(1.3450, 103.8250),
						zoom : 11,
						minZoom : 11,
						maxZoom : 20,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};
					var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
					map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(document.getElementById('legend'));					
					var markers = [];
					var themeNames= [];
					var result = [];
					var temp = [];
					var iconName = "";
					var category = window.location.href.slice(window.location.href.indexOf('=') + 1);
					var markerClusterer = null;
					
					refreshMap();
					$("#family").hide();
					$("#forLegend .Eldercare").hide();
					$("#forLegend span .Eldercare").hide();
					$("#forLegend .Family").hide();
					$("#forLegend span .Family").hide();
					$("#forLegend .VoluntaryWelfareOrgs").hide();
					$("#forLegend span .VoluntaryWelfareOrgs").hide();
					$("#forLegend .suggestLocation").hide();
					$("#forLegend span .suggestLocation").hide();
					$("#legend").hide();
					$(".panel-body").hide();
					$("#down").show();
					$("#up").hide();
					
					if((category == "Family") || (category == "Family#")){
						$("#family").show();
					}

					var x = "";
					var y = "";
					 // Try HTML5 geolocation
					if(navigator.geolocation) {
						navigator.geolocation.getCurrentPosition(showPosition);
					}

					
					function showPosition(position) {
					    y = position.coords.latitude;
					    x = position.coords.longitude;

						alert(y + "," + x);
					}
					
					$("#Eldercare").click(function(e){
						displayTheme("Eldercare");
					});
					
					$("#Family").click(function(e){
						displayTheme("Family");
					});
					
					$("#VoluntaryWelfareOrgs").click(function(e){
						displayTheme("VoluntaryWelfareOrgs");
					});
					
					$(".deselectAllThemes").click(function(){
						if(themeNames.length <= 0){
							alert("Please select a theme.");
							return;
						}
						else{
							$("#legend").hide();
							$("#forLegend .suggestLocation").show();
							$("#forLegend span .suggestLocation").show();
							if((category == "Family") || (category == "Family#")){
								$("#Eldercare").removeClass("active disabled");
								$("#forLegend .Eldercare").hide();
								$("#forLegend span .Eldercare").hide();
								$("#Family").removeClass("active disabled");
								$("#forLegend .Family").hide();
								$("#forLegend span .Family").hide();
								$("#VoluntaryWelfareOrgs").removeClass("active disabled");
								$("#forLegend .VoluntaryWelfareOrgs").hide();
								$("#forLegend span .VoluntaryWelfareOrgs").hide();
								markerClusterer.clearMarkers();
								for(var i = 0;i<markers.length;i++){
									markers[i].setVisible(false);
								}
								markers = [];
								result = [];
								themeNames = [];
								temp = [];
							}
						}
					});
					
					$("#down").click(function(){
						$(".panel-body").slideDown();
						$("#down").hide();
						$("#up").show();
					});
					
					$("#up").click(function(){
						$(".panel-body").slideUp();
						$("#down").show();
						$("#up").hide();
					});
					var suggestMarker = null;
					$(".suggestLocation").click(function() {
						if(themeNames.length <= 0){
							alert("Please select a theme.");
							return;
						}
						else{
							$("#legend").show();
							$("#forLegend .suggestLocation").show();
							$("#forLegend span .suggestLocation").show();
							for(var i=0;i<themeNames.length;i++){
								displayTheme(themeNames[i]);
							}
							if(suggestMarker != null){
								suggestMarker.setVisible(false);
							}
							var closest = find_closest_marker(y,x);
							
						    var coord = result[closest][4];
						    
							var x = coord.substr(0, coord.indexOf(","));
							var y = coord.substr(coord.indexOf(",") + 1, coord.length);
							
							/*var name = result[closest][0];
							for(var i=0;i<result.length;i++){
								if(result[i][0] == name){
									markers[i].setVisible(false);
								}
							}*/
							
							suggestMarker = new google.maps.Marker({
						        position : new google.maps.LatLng(y,x),
						        map: map,
						        icon: "images/suggest.gif",
						    });
							var name = result[closest][0];
							var address = result[closest][2] + "\n"
									+ result[closest][1];
							var hyperLink = result[closest][3];
							
							google.maps.event.addListener(suggestMarker, 'click',function() {
								$(".selectedlocation").html(address);
								$(".selectedName").html(name);
								$(".selectedHyperlink").html(hyperLink);
								$(".btn").attr("disabled",false);
							});
							markers[closest].setVisible(false);
						    markers.splice(closest,1,suggestMarker);

							refreshMap();
						}
					});
					
					$(".refreshMap").click(function(){
						if((category == "Family") || (category == "Family#")){
							$("#Eldercare").removeClass("active disabled");
							$("#forLegend .Eldercare").hide();
							$("#forLegend span .Eldercare").hide();
							$("#Family").removeClass("active disabled");
							$("#forLegend .Family").hide();
							$("#forLegend span .Family").hide();
							$("#VoluntaryWelfareOrgs").removeClass("active disabled");
							$("#forLegend .VoluntaryWelfareOrgs").hide();
							$("#forLegend span .VoluntaryWelfareOrgs").hide();
							markerClusterer.clearMarkers();
							for(var i = 0;i<markers.length;i++){
								markers[i].setVisible(false);
							}
							markers = [];
							result = [];
							temp = [];
						}
						for(var i=0;i<themeNames.length;i++){
							displayTheme(themeNames[i]);
						}
					});
					
					$(".selectLocation").click(function() {
						var location = $(".selectedlocation").html();
						$(window.opener.document).find("#location").html("");
						$(window.opener.document).find("#location").html("<textarea class='form-control floatLeftText' name='eventLocation' rows='3' placeholder='Enter event location'>"+location+"</textarea>");
						window.close();
					});
					
					//Self-declared functions
					
					function find_closest_marker( lat1, lon1 ) {    
					    var pi = Math.PI;
					    var R = 6371; //equatorial radius
					    var distances = [];
					    var closest = -1;

					    for(var i=0;i<markers.length; i++ ) {  
					        var lat2 = markers[i].position.lat();
					        var lon2 = markers[i].position.lng();

					        var chLat = lat2-lat1;
					        var chLon = lon2-lon1;

					        var dLat = chLat*(pi/180);
					        var dLon = chLon*(pi/180);

					        var rLat1 = lat1*(pi/180);
					        var rLat2 = lat2*(pi/180);

					        var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(rLat1) * Math.cos(rLat2); 
					        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
					        var d = R * c;
					        distances[i] = d;
					        if ( closest == -1 || d < distances[closest] ) {
					            closest = i;
					        }
					    }
					    // (debug) The closest marker is:
					    return closest;
					}
					
					// To display theme based on the available themes selected.
					function displayTheme(themeName){
						if(!$("#" + themeName).hasClass("disabled")){
							var l = Ladda.create(document.querySelector('#' + themeName));
							l.start();
							$(".btn").attr("disabled","disabled");
							//$(".list-group-item").addClass("disabled");
							$("#legend").show();
							$("#" + themeName).addClass("active disabled");
							$("#forLegend ." + themeName).show();
							$("#forLegend span ." + themeName).show();
							var mashUpData = new MashupData();
							mashUpData.token = "xkg8VRu6Ol+gMH+SUamkRIEB7fKzhwMvfMo/2U8UJcFhdvR4yN1GutmUIA3A6r3LDhot215OVVkZvNRzjl28TNUZgYFSswOi";
							mashUpData.themeName = themeName;
							mashUpData.GetMashupData(getResult);
							l.stop();
						}
					}
					
					//To retrieve the results based on the theme from One Map and convert coordinates
					function getResult(mashupResults,themeName){
						var results = mashupResults.results;
						var initial = result.length;
						for (var i = 0; i < results.length; i++) {
							var name = mashupResults.results[i].NAME.toString();
							var postalCode = mashupResults.results[i].ADDRESSPOSTALCODE.toString();
							var streetName = mashupResults.results[i].ADDRESSSTREETNAME.toString();
							var hyperLink = mashupResults.results[i].HYPERLINK.toString();
							var xy = mashupResults.results[i].XY.toString();
							var icon_name = mashupResults.results[i].ICON_NAME.toString();
							iconName = "http://www.onemap.sg/icons/" + themeName + "/" + icon_name;
							result.push([name, postalCode, streetName, hyperLink, xy, iconName ]);
							temp.push([name, postalCode, streetName, hyperLink, xy, iconName ]);
						}
						$("." + themeName).attr("src",iconName);
						themeNames.push(themeName);
						for(var i=initial;i<result.length;i++){
							GetCords(i,result[i][4]);
						};
					}
					
					//To convert coordinates from SVY21 to WGS84 for Google Maps
					function GetCords(i, xy) {
						var inXYList = xy;
						var CoordConvertorObj = new CoordConvertor();
						CoordConvertorObj.ConvertCoordinate(i,inXYList, 3414, 4326, showVals);
					}
					
					//This is to display the coordinates, set the info window details.
					function showVals(index,outXY) {
						// X = Longtitude, Y = Latitude
						var x = outXY.substr(0, outXY.indexOf(","));
						var y = outXY.substr(outXY.indexOf(",") + 1, outXY.length);
						result[index][4] = x + "," + y;
						var image = iconName;
						var name = result[index][0];
						var hyperLink = result[index][3];
						var address = result[index][2] + "\n"
								+ result[index][1];
						createMarker(index, y, x, image, name, address, hyperLink);
					}
					
					//To create a marker into the map
					function createMarker(index, lat, lon, image, name, address, hyperLink) {
						var newmarker = new google.maps.Marker({
							position : new google.maps.LatLng(lat, lon),
							map : map,
							icon : image
						});

						google.maps.event.addListener(newmarker, 'click',function() {
							$(".selectedlocation").html(address);
							$(".selectedName").html(name);
							$(".selectedHyperlink").html(hyperLink);
							$(".btn").attr("disabled",false);
						});
						markers.push(newmarker);

						refreshMap();
					}
					
					//To refresh the map
					function refreshMap(){
						if(markerClusterer){
							markerClusterer.clearMarkers();
						}
						markerClusterer = new MarkerClusterer(map, markers,{
							maxZoom:15,
						});
						temp = [];
						$(".list-group-item").removeClass("disabled");
						for(var i = 0;i<themeNames.length;i++){
							$("#" + themeNames[i]).addClass("disabled");
						}
					}

});