$(document).ready(function() {
					var zoom;
					if(detectmob()){
						zoom = 10;
					}
					else{
						zoom = 11;
					}
					var mapProp = {
						center : new google.maps.LatLng(1.3450, 103.8250),
						
						zoom : zoom,
						minZoom : zoom,
						maxZoom : 18,
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
					var prev_window = false;
					
					var suggestMarker = null;

					var x;
					var y;
					
					refreshMap();
					//Arts
					$("#arts").hide();
					$("#forLegend .MONUMENTS").hide();
					$("#forLegend span .MONUMENTS").hide();
					$("#forLegend .NATIONALPARKS").hide();
					$("#forLegend span .NATIONALPARKS").hide();
					$("#forLegend .TOURISM").hide();
					$("#forLegend span .TOURISM").hide();
					//Education
					$("#education").hide();
					$("#forLegend .LIBRARIES").hide();
					$("#forLegend span .LIBRARIES").hide();
					$("#forLegend .COMMUNITYCLUBS").hide();
					$("#forLegend span .COMMUNITYCLUBS").hide();
					$("#forLegend .HERITAGESITES").hide();
					$("#forLegend span .HERITAGESITES").hide();
					//Family
					$("#family").hide();
					$("#forLegend .Eldercare").hide();
					$("#forLegend span .Eldercare").hide();
					$("#forLegend .Family").hide();
					$("#forLegend span .Family").hide();
					$("#forLegend .VoluntaryWelfareOrgs").hide();
					$("#forLegend span .VoluntaryWelfareOrgs").hide();
					//Health
					$("#health").hide();
					$("#forLegend .EXERCISEFACILITIES").hide();
					$("#forLegend span .EXERCISEFACILITIES").hide();
					$("#forLegend .REGISTERED_PHARMACY").hide();
					$("#forLegend span .REGISTERED_PHARMACY").hide();
					$("#forLegend .RelaxSG").hide();
					$("#forLegend span .RelaxSG").hide();
					/**/
					$("#legend").hide();
					$(".panel-body").hide();
					$("#down").show();
					$("#up").hide();
					$(".selectLocationTable").hide();
					$(".btn").attr("disabled","disabled");

					if((category == "Arts") || (category == "Arts#")){
						$("#arts").show();
					}
					else if((category == "Education") || (category == "Education#")){
						$("#education").show();
					}
					else if((category == "Family") || (category == "Family#")){
						$("#family").show();
					}
					else if((category == "Health") || (category == "Health#")){
						$("#health").show();
					}

					 // Try HTML5 geolocation
					if(navigator.geolocation) {
						navigator.geolocation.watchPosition(showPosition);
					}
					//Arts
					$("#MONUMENTS").click(function(e){
						displayTheme("MONUMENTS");
					});
					$("#NATIONALPARKS").click(function(e){
						displayTheme("NATIONALPARKS");
					});
					$("#TOURISM").click(function(e){
						displayTheme("TOURISM");
					});
					//Education
					$("#HERITAGESITES").click(function(e){
						displayTheme("HERITAGESITES");
					});
					$("#LIBRARIES").click(function(e){
						displayTheme("LIBRARIES");
					});
					$("#COMMUNITYCLUBS").click(function(e){
						displayTheme("COMMUNITYCLUBS");
					});
					//Family
					$("#Eldercare").click(function(e){
						displayTheme("Eldercare");
					});
					
					$("#Family").click(function(e){
						displayTheme("Family");
					});
					
					$("#VoluntaryWelfareOrgs").click(function(e){
						displayTheme("VoluntaryWelfareOrgs");
					});
					//Health
					$("#EXERCISEFACILITIES").click(function(e){
						displayTheme("EXERCISEFACILITIES");
					});
					$("#REGISTERED_PHARMACY").click(function(e){
						displayTheme("REGISTERED_PHARMACY");
					});
					$("#RelaxSG").click(function(e){
						displayTheme("RelaxSG");
					});
					
					$(".deselectAllThemes").click(function(){
						if(themeNames.length <= 0){
							alert("Please select a theme.");
							return;
						}
						else{
							$("#legend").hide();
							if((category == "Arts") || (category == "Arts#")){
								$("#MONUMENTS").removeClass("active disabled");
								$("#forLegend .MONUMENTS").hide();
								$("#forLegend span .MONUMENTS").hide();
								$("#NATIONALPARKS").removeClass("active disabled");
								$("#forLegend .NATIONALPARKS").hide();
								$("#forLegend span .NATIONALPARKS").hide();
								$("#TOURISM").removeClass("active disabled");
								$("#forLegend .TOURISM").hide();
								$("#forLegend span .TOURISM").hide();
								$(".selectLocationTable").hide();
								$(".btn").attr("disabled","disabled");
							}
							else if((category == "Education") || (category == "Education#")){
								$("#LIBRARIES").removeClass("active disabled");
								$("#forLegend .LIBRARIES").hide();
								$("#forLegend span .LIBRARIES").hide();
								$("#COMMUNITYCLUBS").removeClass("active disabled");
								$("#forLegend .COMMUNITYCLUBS").hide();
								$("#forLegend span .COMMUNITYCLUBS").hide();
								$("#HERITAGESITES").removeClass("active disabled");
								$("#forLegend .HERITAGESITES").hide();
								$("#forLegend span .HERITAGESITES").hide();
								$(".selectLocationTable").hide();
								$(".btn").attr("disabled","disabled");
							}
							else if((category == "Family") || (category == "Family#")){
								$("#Eldercare").removeClass("active disabled");
								$("#forLegend .Eldercare").hide();
								$("#forLegend span .Eldercare").hide();
								$("#Family").removeClass("active disabled");
								$("#forLegend .Family").hide();
								$("#forLegend span .Family").hide();
								$("#VoluntaryWelfareOrgs").removeClass("active disabled");
								$("#forLegend .VoluntaryWelfareOrgs").hide();
								$("#forLegend span .VoluntaryWelfareOrgs").hide();
								$(".selectLocationTable").hide();
								$(".btn").attr("disabled","disabled");
							}
							else if((category == "Health") || (category == "Health#")){
								$("#REGISTERED_PHARMACY").removeClass("active disabled");
								$("#forLegend .REGISTERED_PHARMACY").hide();
								$("#forLegend span .REGISTERED_PHARMACY").hide();
								$("#RelaxSG").removeClass("active disabled");
								$("#forLegend .RelaxSG").hide();
								$("#forLegend span .RelaxSG").hide();
								$("#EXERCISEFACILITIES").removeClass("active disabled");
								$("#forLegend .EXERCISEFACILITIES").hide();
								$("#forLegend span .EXERCISEFACILITIES").hide();
								$(".selectLocationTable").hide();
								$(".btn").attr("disabled","disabled");
							}
							markerClusterer.clearMarkers();
							for(var i = 0;i<markers.length;i++){
								markers[i].setVisible(false);
							}
							markers = [];
							result = [];
							themeNames = [];
							temp = [];
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
					
					$(".suggestLocation").click(function() {
						if(themeNames.length <= 0){
							alert("Please select a theme.");
							return;
						}
						else{
							$("#legend").show();
							for(var i=0;i<themeNames.length;i++){
								displayTheme(themeNames[i]);
							}
							
							//y,x
							
							var closest = find_closest_marker(y,x);
						    var coord = result[closest][4];
						    
							var suggestedX = coord.substr(0, coord.indexOf(","));
							var suggestedY = coord.substr(coord.indexOf(",") + 1, coord.length);
							
							suggestMarker = markers[closest];
							var name = result[closest][0];
							var address = result[closest][2] + "\n"
									+ result[closest][1];
							
							var hyperL = "";
							if((result[closest][3] != null) || (result[closest][3] == "") || (result[closest][3] != undefined)){
								hyperL = 'For more information: <a href='+result[closest][3]+'>'+result[closest][3]+'</a>';
							}

						    var html = '<div id="content">'+
						      '<div id="siteNotice">'+
						      '</div>'+
						      '<h4 id="firstHeading" class="firstHeading">'+name+'</h1>'+
						      '<div id="bodyContent">'+
						      hyperL +
						      '</div>'+
						      '</div>';
						    
						    suggestMarker['infowindow'] = new google.maps.InfoWindow({
									content : html
						    });
							google.maps.event.addListener(suggestMarker, 'click',function() {
								if (!prev_window) {
								} else {
									prev_window.close();
								}
								this['infowindow'].open(map, this);
								prev_window = this['infowindow'];
								$(".selectedlocation").html(address);
								$(".selectedName").html(name);
								$(".selectedHyperlink").html(result[closest][3]);
								$(".selectedLatitude").html(suggestedY);
								$(".selectedLongtitude").html(suggestedX);
								$(".hyperL").attr("href",result[closest][3]);
								$(".btn").attr("disabled",false);
								$(".selectLocationTable").show();
								map.setZoom(15);
								map.panTo(new google.maps.LatLng(suggestedY, suggestedX));
							});
							refreshMap();
							new google.maps.event.trigger( suggestMarker, 'click' );
						}
					});
					
					$(".refreshMap").click(function(){
						if((category == "Arts") || (category == "Arts#")){
							$("#MONUMENTS").removeClass("active disabled");
							$("#forLegend .MONUMENTS").hide();
							$("#forLegend span .MONUMENTS").hide();
							$("#NATIONALPARKS").removeClass("active disabled");
							$("#forLegend .NATIONALPARKS").hide();
							$("#forLegend span .NATIONALPARKS").hide();
							$("#TOURISM").removeClass("active disabled");
							$("#forLegend .TOURISM").hide();
							$("#forLegend span .TOURISM").hide();
						}
						else if((category == "Education") || (category == "Education#")){
							$("#LIBRARIES").removeClass("active disabled");
							$("#forLegend .LIBRARIES").hide();
							$("#forLegend span .LIBRARIES").hide();
							$("#COMMUNITYCLUBS").removeClass("active disabled");
							$("#forLegend .COMMUNITYCLUBS").hide();
							$("#forLegend span .COMMUNITYCLUBS").hide();
							$("#HERITAGESITES").removeClass("active disabled");
							$("#forLegend .HERITAGESITES").hide();
							$("#forLegend span .HERITAGESITES").hide();
						}
						else if((category == "Family") || (category == "Family#")){
							$("#Eldercare").removeClass("active disabled");
							$("#forLegend .Eldercare").hide();
							$("#forLegend span .Eldercare").hide();
							$("#Family").removeClass("active disabled");
							$("#forLegend .Family").hide();
							$("#forLegend span .Family").hide();
							$("#VoluntaryWelfareOrgs").removeClass("active disabled");
							$("#forLegend .VoluntaryWelfareOrgs").hide();
							$("#forLegend span .VoluntaryWelfareOrgs").hide();
						}
						else if((category == "Health") || (category == "Health#")){
							$("#REGISTERED_PHARMACY").removeClass("active disabled");
							$("#forLegend .REGISTERED_PHARMACY").hide();
							$("#forLegend span .REGISTERED_PHARMACY").hide();
							$("#RelaxSG").removeClass("active disabled");
							$("#forLegend .RelaxSG").hide();
							$("#forLegend span .RelaxSG").hide();
							$("#EXERCISEFACILITIES").removeClass("active disabled");
							$("#forLegend .EXERCISEFACILITIES").hide();
							$("#forLegend span .EXERCISEFACILITIES").hide();
						}

						markerClusterer.clearMarkers();
						for(var i = 0;i<markers.length;i++){
							markers[i].setVisible(false);
						}
						
						markers = [];
						result = [];
						temp = [];
						for(var i=0;i<themeNames.length;i++){
							displayTheme(themeNames[i]);
						}
					});
					
					$(".selectLocation").click(function() {
						var name = $(".selectedName").html();
						var hyperlink = $(".selectedHyperlink").html();
						var location = $(".selectedlocation").html();
						var lat = $(".selectedLatitude").html();
						var lng = $(".selectedLongtitude").html(); 
						$(window.opener.document).find("#location").html("");
						$(window.opener.document).find("#lat").attr("value",lat);
						$(window.opener.document).find("#lng").attr("value",lng);
						$(window.opener.document).find("#locationName").attr("value",name);
						$(window.opener.document).find("#locationHyperLink").attr("value",hyperlink);
						$(window.opener.document).find("#location").html("<textarea class='form-control floatLeftText' name='eventLocation' rows='3' placeholder='Enter event location'>"+location+"</textarea>");
						window.close();
					});
					
					//Self-declared functions
					//show position based on the current location
					function showPosition(position) {
						y = position.coords.latitude;
						x = position.coords.longitude;
					}
					
					//Detect whether the devices is a desktop web or mobile web
					function detectmob() { 
						 if( navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPod/i) || navigator.userAgent.match(/BlackBerry/i) || navigator.userAgent.match(/Windows Phone/i)){
						    return true;
						 }
						 else {
							 return false;
						 }
					}
					
					//Find the closest marker to the current location
					function find_closest_marker(lat1,lon1) {
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

					        var a = Math.sin(dLat/2) * Math.sin(dLat/2) + 
					                    Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(rLat1) * Math.cos(rLat2); 
					        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
					        var d = R * c;

					        distances[i] = d;
					        if ( closest == -1 || d < distances[closest] ) {
					            closest = i;
					        }
					    }
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
							var name = mashupResults.results[i].NAME;
							var postalCode = mashupResults.results[i].ADDRESSPOSTALCODE;
							var streetName = mashupResults.results[i].ADDRESSSTREETNAME;
							var hyperLink = mashupResults.results[i].HYPERLINK;
							var xy = mashupResults.results[i].XY;
							var icon_name = mashupResults.results[i].ICON_NAME;
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
						
						var hyperL = "";
						
						if((hyperLink != null) || (hyperLink == "") || (hyperLink != undefined)){
							hyperL =  'For more information: <a href='+hyperLink+'>'+hyperLink+'</a>'
					    };
						
					    var html = '<div id="content">'+
					      '<div id="siteNotice">'+
					      '</div>'+
					      '<h4 id="firstHeading" class="firstHeading">'+name+'</h1>'+
					      '<div id="bodyContent">'+
					      hyperL +
					      '</div>'+
					      '</div>';

						  newmarker['infowindow'] = new google.maps.InfoWindow({
							content : html
						  });

						  google.maps.event.addListener(newmarker, 'click',function() {
							if (!prev_window) {
							} else {
								prev_window.close();
							}
							this['infowindow'].open(map, this);
							prev_window = this['infowindow'];
							$(".selectedlocation").html(address);
							$(".selectedName").html(name);
							$(".selectedHyperlink").html(hyperLink);
							$(".selectedLatitude").html(lat);
							$(".selectedLongtitude").html(lon);
							$(".hyperL").attr("href",hyperLink);
							$(".btn").attr("disabled",false);
							$(".selectLocationTable").show();
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
							maxZoom:14,
						});
						temp = [];
						$(".list-group-item").removeClass("disabled");
						for(var i = 0;i<themeNames.length;i++){
							$("#" + themeNames[i]).addClass("disabled");
						}
					}

});