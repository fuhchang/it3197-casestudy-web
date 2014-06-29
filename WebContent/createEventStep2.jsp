<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" href="css/DateTimePicker.css" />
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
		<script type="text/javascript" src="js/DateTimePicker.js" charset="UTF-8"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#dtBox").DateTimePicker({
					titleContentDateTime: "Date/Time",
					setButtonContent: "Set date/time",
					clearButtonContent: "Reset"
				});
			});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
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
						<input class="startDateTime1 form-control" type="text" data-field="datetime" data-format="dd-MM-yyyy hh:mm:ss AA" data-startend="start" data-startendelem=".endDateTime1" readonly /> 
						<div id="dtBox"></div>
					</div>
					<div class="form-group">
						<span class="floatLeftText"><label class="col-xs-12 control-label">To</label></span>
						<br/>
						<br/>
						<input class="endDateTime1 form-control" type="text" data-field="datetime" data-format="dd-MM-yyyy hh:mm:ss AA"  data-startend="end" data-startendelem=".startDateTime1" readonly >
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
						<a href="createEventStep1.jsp"><button type="submit" class="btn btn-default"> Previous </button></a>
						<a href="event.jsp"><button type="submit" class="btn btn-default"> Create Event </button></a>
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</t:master>