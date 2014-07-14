<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/bootstrapValidator.css" />
		<style>
			.floatLeftText{
				float:left;
			}
			#currentForm{
				margin-top:25px;
			}
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script type="text/javascript" src="https://www.dropbox.com/static/api/1/dropins.js" id="dropboxjs" data-app-key="cqvf3nim3klslqb"></script>
		<script type="text/javascript" src="js/bootstrapValidator.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				
				$('#uploadPosterButtons').css("margin-top","-50px");
				$('#selected-image').hide();
				$('#poster').hide();
		        var button = Dropbox.createChooseButton({
		            success: function(files) {
		            	$('#uploadPosterButtons').css("margin-top","0px");
		            	$('#selected-image').attr("src",files[0].link).show();
		            	$('#poster').show();
		            },
		            linkType: 'direct',
		            extensions: ['.jpg', '.jpeg', '.png'],
		        });
				$("#suggestLocation").click(function(e) {
					e.preventDefault();
					window.open("suggestLocation.jsp", '','width=800px,height=500,resizable=no');
					window.focus();
				});
		        $('#container').append(button);
		        $('#createEventStep1Form').bootstrapValidator({
		        	submitButtons: null,
		        	live: 'enabled',
		        	excluded: ':disabled',
		            feedbackIcons: {
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		        	fields: {
		        		eventName: {
		        			trigger: 'keyup',
		        			message: 'Event name is not valid',
		        			validators: {
		        				notEmpty: {
		        					message: 'Please enter a name for the event'
		        				}
		        			},
		        		},
		        		eventDescription: {
		        			trigger: 'keyup',
		        			message: 'Event description is not valid',
		        			validators: {
		        				notEmpty: {
		        					message: 'Please enter a description for the event'
		        				}
		        			},
		        		},
			        	eventLocation: {
			        		trigger: 'keyup',
			        		onStatus: function(e, data) {
		                        $('#createEventStep1Form').validate();
		                    },
			        		message: 'Event location is not valid',
			        		validators: {
			                    callback: {
			                        message: 'Please enter a location for the event',
			                        callback: function(value, validator) {
			                        	if(value.length > 0){
				                            return true;	
			                        	}
			                        	else{
			                        		return false;
			                        	}
			                        }
			                    }
			        		},
			        	}
					},        		
		        });
			});
		</script>
	</jsp:attribute>
	<jsp:attribute name="content">
		<form method="post" id="createEventStep1Form" action="createEventStep2.jsp">
			<div id="currentForm" class="form-horizontal" role="form">
				<div class="panel panel-primary">
				<div class="panel-heading">
					<input id='eventType' type="hidden" name="eventType" value="<c:out value="${param.typeOfEvent}" />" />
					Create Event
					<br/>
					Step 1: Please fill in the event details
				</div>
					<div class="panel-body">
						<div class="form-group">
							<span class="floatLeftText"><label id="poster" class="col-xs-12 control-label">Poster</label></span>
							<br/>
							<br/>
							<input type="image" id="selected-image" name="image_url" width="400" height="200"/>
							<table id="uploadPosterButtons">
								<tr>
									<td><div id="container"></div></td>
									<td style="padding-left:25px;"></td>
									<td><input type="file" name="excelPathName" id="uploadBtnExcel" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/></td>
								</tr>
							</table>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label" for="eventName"> Name</label></span>
							<input class="form-control" type="text" name="eventName" id="eventName" placeholder="Enter event name"/>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Category</label></span>
							<select class="form-control" id="gType" name="eventCategory">
								<option> Arts </option>
								<option> Education</option>
								<option> Family </option>
								<option> Health </option>
							</select>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Description</label></span>
							<textarea class="form-control" name="eventDescription" rows="3" placeholder="Enter event description"></textarea>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Location</label></span>
							<textarea id="location" class="form-control" name="eventLocation" rows="3" placeholder="Enter event location"></textarea>
							<br/>
							<button type="submit" class="btn btn-default" id='suggestLocation' formaction="#"  data-bind="jqueryui: 'button'"> Suggest Location </button>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">No of participants</label></span>
							<select class="form-control" id="gType" name="noOfParticipants">
								<option> 0 - 99 </option>
								<option> 100 - 499 </option>
								<option> 500 - 999 </option>
								<option> 1000 - 9999 </option>
								<option> 10000 - 99999 </option>
							</select>
						</div>
						<div class="form-group">
							<a href="event.jsp"><button type="button" id="cancelBtn" class="btn btn-default"> Cancel </button></a>
							<input type="submit" name="submit" id="submitBtn" value="Next" class="btn btn-default"/>
						</div>
					</div>
				</div>
			</div>
		</form>
	</jsp:attribute>
</t:master>