<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
<<<<<<< OURS
		<link rel="stylesheet" type="text/css" href="css/datetimepicker.css" />
		<link rel="stylesheet" type="text/css" href="css/validation/screen.css" />
		<link rel="stylesheet" type="text/css" href="css/validation/cmxform.css" />
=======
		<link rel="stylesheet" href="css/DateTimePicker.css" />
>>>>>>> THEIRS
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
<<<<<<< OURS
		<script type="text/javascript" src="js/moment.js"></script>
		<script type="text/javascript" src="js/datetimepicker.js"></script>
		<script src="js/validation/form-validate.js"></script>
		<script src="js/validation/additional-methods.js"></script>
=======
		<script type="text/javascript" src="js/DateTimePicker.js" charset="UTF-8"></script>
>>>>>>> THEIRS
		<script type="text/javascript">
			$(document).ready(function(){
<<<<<<< OURS
				var eventType = '<c:out value="${param.noOfParticipants}" />';
				alert(eventType);
				jQuery.validator.addMethod("checkDate", function(value, element) {
					//Format the date
					var dateFromString = $("#eventDateTimeFrom").val().substr(0,11);
					var dateToString = $("#eventDateTimeTo").val().substr(0,11);
					var dateFrom = new Date(dateFromString);
					var dateTo = new Date(dateToString);
					if(dateFrom - dateTo < 0){
						return true;
					}
					else{
						return false;
					}
				}, "Please select another date after today.");
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
					alert(dateFrom-dateTo);
					var differenceInDay = Math.floor((dateFrom-dateTo)/-86400000); 
					alert(differenceInDay);
					var days = -86400000 * differenceInDay;
					var compare = (dateFrom-dateTo) - days;
					if(compare <= -1800000){
						return true;
					}
					else{
						return false;	
					}
				}, "Please select a ending time 30 min before the starting time.");
				if(eventType == "Big Event"){
					$('#eventDateTimeFrom').datetimepicker({
						pick12HourFormat: true,
					});
		            $('#eventDateTimeTo').datetimepicker({
		            	pick12HourFormat: true,
		            });
		            $("#eventDateTimeFrom").on("dp.change",function (e) {
		               $('#eventDateTimeTo').data("DateTimePicker").setMinDate(e.date);
		            });
		            $("#eventDateTimeTo").on("dp.change",function (e) {
		               $('#eventDateTimeFrom').data("DateTimePicker").setMaxDate(e.date);
		            });
		            $("#submitBtn").click(function(e){
		            	$('#createEventStep2Form').validate();
		            });
		            $('#createEventStep2Form').validate({
			            rules: {
			            	eventDateTimeFrom: {
			                    required: true,
			                },
			                eventDateTimeTo: {
			                    required: true,
			                    checkDate: "",
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
				}
				else{
					$('#eventDateTimeFrom').datetimepicker({
						pick12HourFormat: true,
					});
		            $('#eventDateTimeTo').datetimepicker({
		            	pick12HourFormat: true,
		            });
		            $("#form-group").eq(2).hide();
		            $("#submitBtn").click(function(e){
		            	$('#createEventStep2Form').validate();
		            });
		            $('#createEventStep2Form').validate({
			            rules: {
			            	eventDateTimeFrom: {
			                    required: true,
			                },
			                eventDateTimeTo: {
			                    required: true,
			                    checkSimilarDate: "",
			                    checkTime: ""
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
				}
				
=======
				$("#dtBox").DateTimePicker({
					titleContentDateTime: "Date/Time",
					setButtonContent: "Set date/time",
					clearButtonContent: "Reset"
				});
				$("#submitBtn").click(function(e){
					var eventDateTimeForm = $(".form-control").eq(0).val();
					var eventDateTimeTo = $(".form-control").eq(1).val();
					if(eventDateTimeForm == ""){
		        		$(".form-group").eq(0).html("<span class='floatLeftText'><label class='col-xs-12 control-label'>From</label></span><br/><br/><input class='startDateTime1 form-control' type='text' data-field='datetime' data-format='dd-MM-yyyy hh:mm:ss AA' data-startend='start' data-startendelem='.endDateTime1' readonly /> <div id='dtBox'></div>").addClass("has-error");
		        		e.preventDefault();
		        	}
					if(eventDateTimeTo == ""){
		        		$(".form-group").eq(1).html("<span class='floatLeftText'><label class='col-xs-12 control-label'>To</label></span><br/><br/><input class='endDateTime1 form-control' type='text' data-field='datetime' data-format='dd-MM-yyyy hh:mm:ss AA'  data-startend='end' data-startendelem='.startDateTime1' readonly />").addClass("has-error");
		        		e.preventDefault();
		        	}
				});
>>>>>>> THEIRS
			});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
<<<<<<< OURS
		<form method="post" id="createEventStep2Form" action="createEvent" >
=======
		<form method="post" action="createEventStep2.jsp">
>>>>>>> THEIRS
			<div id="currentForm" class="form-horizontal" role="form">
				<div class="panel panel-primary">
				<div class="panel-heading">
					Create Event
					<br/>
					Step 2: Please fill in the event schedule
				</div>
					<div class="panel-body">
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">From</label></span>
							<br/>
							<br/>
<<<<<<< OURS
                    		<input class="form-control" id="eventDateTimeFrom" name="eventDateTimeFrom" type="text" readonly/>
=======
							<input class="startDateTime1 form-control" type="text" data-field="datetime" data-format="dd-MM-yyyy hh:mm:ss AA" data-startend="start" data-startendelem=".endDateTime1" readonly /> 
							<div id="dtBox"></div>
>>>>>>> THEIRS
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">To</label></span>
							<br/>
							<br/>
<<<<<<< OURS
                    		<input class="form-control" id="eventDateTimeTo"  name="eventDateTimeTo" type="text" readonly/>
=======
							<input class="endDateTime1 form-control" type="text" data-field="datetime" data-format="dd-MM-yyyy hh:mm:ss AA"  data-startend="end" data-startendelem=".startDateTime1" readonly />
>>>>>>> THEIRS
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Repeat the event</label></span>
							<select class="form-control" id="gType">
								<option> Once </option>
								<option> Daily </option>
								<option> Weekly </option>
								<option> Monthly </option>
								<option> Annually </option>
							</select>
						</div>
						<div class="form-group">
<<<<<<< OURS
							<a href="createEventStep1.jsp"><button type="button" id="cancelBtn" class="btn btn-default"> Previous </button></a>
							<input type="submit" name="submit" id="submitBtn" value="Create Event" class="btn btn-default"/>
=======
							<a href="createEventStep1.jsp"><button type="submit" class="btn btn-default"> Previous </button></a>
							<input type="Submit" name="submit" id="submitBtn" value="Create Event" class="btn btn-default"/>
>>>>>>> THEIRS
						</div>
					</div>
				</div>
			</div>
		</form>
	</jsp:attribute>
</t:master>
