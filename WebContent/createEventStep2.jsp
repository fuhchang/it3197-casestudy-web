<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" href="css/datetimepicker.css" />
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
		<script type="text/javascript" src="js/datetimepicker.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/bootstrapValidator.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('#eventDateTimeFrom').datetimepicker({
					format: 'M d, Y h:m:s A',
				});
				$('#eventDateTimeTo').datetimepicker({
					format: 'M d, Y h:m:s A',
				});
				$("#createEventStep2Form").bootstrapValidator({
		        	submitButtons: null,
		        	live: 'enabled',
		        	excluded: ':disabled',
				});
			});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<form method="post" id="createEventStep2Form" action="createEvent">
			<div id="currentForm" class="form-horizontal" role="form">
				<div class="panel panel-primary">
				<div class="panel-heading">
					<input type="hidden" name="web" value="true" />
					<input type="hidden" name="eventType" id="eventType" value="<c:out value="${param.eventType}" />"/>
					<input type="hidden" name="eventName" id="eventName" value="<c:out value="${param.eventName}" />"/>
					<input type="hidden" name="eventCategory" id="eventCategory" value="<c:out value="${param.eventCategory}" />"/>
					<input type="hidden" name="eventDescription" id="eventDescription" value="<c:out value="${param.eventDescription}" />"/>
					<input type="hidden" name="eventLocation" id="eventLocation" value="<c:out value="${param.eventLocation}" />"/>
					<input type="hidden" id="noOfParticipants" id="noOfParticipants" name="noOfParticipants" value="<c:out value="${param.noOfParticipants}" />"/>
					Create Event
					<br/>
					Step 2: Please fill in the event schedule
				</div>
					<div class="panel-body">
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">From</label></span>
							<br/>
							<br/>
							<input class="form-control" id="eventDateTimeFrom" name="eventDateTimeFrom" type="text" readonly/>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">To</label></span>
							<br/>
							<br/>
							<input class="form-control" id="eventDateTimeTo"  name="eventDateTimeTo" type="text" readonly />
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Repeat the event</label></span>
							<select class="form-control" id="occurence" name="occurence">
								<option> Once </option>
								<option> Daily </option>
								<option> Weekly </option>
								<option> Monthly </option>
								<option> Annually </option>
							</select>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-default" formaction="createEventStep1.jsp"> Previous </button>
							<input type="Submit" name="submit" id="submitBtn" value="Create Event" class="btn btn-default"/>
						</div>
					</div>
				</div>
			</div>
		</form>
	</jsp:attribute>
</t:master>