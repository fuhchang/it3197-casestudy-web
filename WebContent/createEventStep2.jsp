<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/datetimepicker.css" />
		<link rel="stylesheet" type="text/css"
			href="css/validation/screen.css" />
		<link rel="stylesheet" type="text/css"
			href="css/validation/cmxform.css" />
		<style>
.floatLeftText {
	float: left;
}

#currentForm {
	margin-top: 25px;
}
</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script type="text/javascript" src="js/moment.js"></script>
		<script type="text/javascript" src="js/datetimepicker.js"></script>
		<script src="js/validation/form-validate.js"></script>
		<script src="js/validation/additional-methods.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				jQuery.validator.addMethod("checkTodayStartDate", function(value, element) {
					//Format the date
					var dateFromString = $("#eventDateTimeFrom").val().substr(0,11);
					var dateFrom = new Date(dateFromString);
					
					var today = new Date();
					var m = today.getMonth();
					var d = today.getDate();
					var y = today.getYear() + 1900;
					var todayDate = new Date(y,m,d);
					if(dateFrom - todayDate > 0){
						return true;
					}
					else{
						return false;
					}
				}, "Please select another starting date after today.");
				jQuery.validator.addMethod("checkTodayEndingDate", function(value, element) {
					//Format the date
					var dateToString = $("#eventDateTimeTo").val().substr(0,11);
					var dateTo = new Date(dateToString);
					
					var today = new Date();
					var m = today.getMonth();
					var d = today.getDate();
					var y = today.getYear() + 1900;
					var todayDate = new Date(y,m,d);
					if(dateTo - todayDate > 0){
						return true;
					}
					else{
						return false;
					}
				}, "Please select another end date after today.");
				jQuery.validator.addMethod("checkDate", function(value, element) {
					//Format the date
					var dateFromString = $("#eventDateTimeFrom").val().substr(0,11);
					var dateToString = $("#eventDateTimeTo").val().substr(0,11);
					var dateFrom = new Date(dateFromString);
					var dateTo = new Date(dateToString);
					if(dateFrom - dateTo <= 0){
						return true;
					}
					else{
						return false;
					}
				}, "Please select another date after starting date.");
				jQuery.validator.addMethod("checkSimilarDate", function(value, element) {
					//Format the date
					var dateFromString = $("#eventDateTimeFrom").val().substr(0,11);
					var dateToString = $("#eventDateTimeTo").val().substr(0,11);
					var dateFrom = new Date(dateFromString);
					var dateTo = new Date(dateToString);
					if(dateFrom - dateTo == 0){
						return true;
					}
					else{
						return false;
					}
				}, "Please select a date similar to the starting date.");
				jQuery.validator.addMethod("checkTime", function(value, element) {
					//Format the date
					var dateFromString = $("#eventDateTimeFrom").val();
					var dateToString = $("#eventDateTimeTo").val();
					var dateFrom = new Date(dateFromString);
					var dateTo = new Date(dateToString);
					if(dateFrom - dateTo <= -1800000){
						return true;
					}
					else{
						return false;	
					}
				}, "Please select a ending time 30 min before the starting time.");
				jQuery.validator.addMethod("checkBigEventTime", function(value, element) {
					//Format the date
					var dateFromString = $("#eventDateTimeFrom").val();
					var dateToString = $("#eventDateTimeTo").val();
					var dateFrom = new Date(dateFromString);
					var dateTo = new Date(dateToString);
					var differenceInDay = Math.floor((dateFrom-dateTo)/-86400000);
					var days = -86400000 * differenceInDay;
					var compare = (dateFrom-dateTo) - days;
					if(compare <= -1800000){
						return true;
					}
					else{
						return false;	
					}
				}, "Please select a ending time 30 min before the starting time.");
				$('#eventDateTimeFrom').datetimepicker({
					pick12HourFormat: true,
				});
		        $('#eventDateTimeTo').datetimepicker({
		           	pick12HourFormat: true,
		        });
		        $("#eventDateTimeFrom").on("dp.change",function (e) {
		        	var day = new Date(e.date);
			        var d = moment(day).subtract('days', 1);
		    	    $('#eventDateTimeTo').data("DateTimePicker").setMinDate(d);
		        });
		        $("#eventDateTimeTo").on("dp.change",function (e) {
		        	var day = new Date(e.date);
			        var d = moment(day).subtract('days', 1);
		        	$('#eventDateTimeFrom').data("DateTimePicker").setMaxDate(d);
		       	});
		     	$("#submitBtn").click(function(e){
		          	$('#createEventStep2Form').validate();
		        });
		        $('#createEventStep2Form').validate({
			            rules: {
			            	eventDateTimeFrom: {
			                    required: true,
			                    checkTodayStartDate: "",
			                },
			                eventDateTimeTo: {
			                    required: true,
			                    checkDate: "",
			                    checkTodayEndingDate: "",
			                    checkBigEventTime: ""
			                }
			            },
			            highlight: function(element) {
			                $(element).closest('.form-group').addClass('has-error');
			            },
			            unhighlight: function(element) {
			                $(element).closest('.form-group').removeClass('has-error');
			            },
			            errorElement: 'span',
			            errorClass: 'help-block',
			            errorPlacement: function(error, element) {
			                if(element.parent('.input-group').length) {
			                    error.insertAfter(element.parent());
			                } else {
			                    error.insertAfter(element);
			                }
			            }
		 	   });
		       $("#magicBtn").click(function(){
		    	   var dateStart = "08/28/2014 8:30 PM";
			   	   var dateEnd = "08/28/2014 5:30 PM";
			   	   $("#eventDateTimeFrom").val(dateStart);
			   	   $("#eventDateTimeTo").val(dateEnd);
			   });
			});
		</script>
	</jsp:attribute>

	<jsp:attribute name="content">
		<button type="button" id="magicBtn" class="btn btn-default"> Magic Button (for presentation purposes)</button>
		<form method="post" id="createEventStep2Form" action="createEvent">
			<div id="currentForm" class="form-horizontal" role="form">
				<div class="panel panel-primary">
				<div class="panel-heading">
					<input type="hidden" name="web" value="true" />
					<input type="hidden" name="eventName" id="eventName"
							value="<c:out value="${param.eventName}" />" />
					<input type="hidden" name="eventCategory" id="eventCategory"
							value="<c:out value="${param.eventCategory}" />" />
					<input type="hidden" name="eventDescription" id="eventDescription"
							value="<c:out value="${param.eventDescription}" />" />
					<input type="hidden" id="noOfParticipants" id="noOfParticipants"
							name="noOfParticipants"
							value="<c:out value="${param.noOfParticipants}" />" />
					<input type="hidden" name="locationName" id="locationName" value="<c:out value="${param.locationName}" />"/>
					<input type="hidden" name="locationAddress" id="locationAddress" value="<c:out value="${param.eventLocation}" />"/>
					<input type="hidden" name="locationHyperLink" id="locationHyperLink" value="<c:out value="${param.locationHyperLink}" />"/>
					<input type="hidden" name="lat" value="<c:out value="${param.lat}" />" />
					<input type="hidden" name="lng" value="<c:out value="${param.lng}" />" />
					<input type="hidden" id="help" name="requestHelp" value="<c:out value="${param.help}" />" />
					Create Event
					<br />
					Step 2: Please fill in the event schedule
				</div>
					<div class="panel-body">
						<div class="form-group">
							<span class="floatLeftText"><label
								class="col-xs-12 control-label">From</label></span>
							<br />
							<br />
                    		<input class="form-control" id="eventDateTimeFrom"
								name="eventDateTimeFrom" type="text" readonly />
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label
								class="col-xs-12 control-label">To</label></span>
							<br />
							<br />
                    		<input class="form-control" id="eventDateTimeTo"
								name="eventDateTimeTo" type="text" readonly />
						</div>
						<div class="form-group" id="occurence">
							<span class="floatLeftText"><label
								class="col-xs-12 control-label">Repeat the event</label></span>
							<select class="form-control" id="occurence" name="occurence">
								<option> Once </option>
								<option> Daily </option>
								<option> Weekly </option>
								<option> Monthly </option>
								<option> Annually </option>
							</select>
						</div>
						<div class="form-group">
							<a href="createEventStep1.jsp"><button type="button"
									id="cancelBtn" class="btn btn-default"> Previous </button></a>
							<input type="submit" name="submit" id="submitBtn"
								value="Create Event" class="btn btn-default" />
						</div>
					</div>
				</div>
			</div>
		</form>
		
			
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			        <h4 class="modal-title" id="myModalLabel">Request from Hobby Groups</h4>
			      </div>
			      <div class="modal-body">
			        Do you want to request help from hooby groups?
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" id="no" onclick="notRequestingHelp()">No</button>
			        <button type="button" class="btn btn-primary" id="yes" onclick="requestingHelp()">Yes</button>
			      </div>
			    </div>
			  </div>
			</div>
	</jsp:attribute>
</t:master>