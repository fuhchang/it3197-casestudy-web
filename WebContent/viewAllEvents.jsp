<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/dataTable/jquery.dataTables_themeroller.css" />
   		<link rel="stylesheet" type="text/css" href="css/dataTable/demo_page.css" />
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	  	<script type="text/javascript" src="js/dataTable/dataTables.js"></script>
   		<script type="text/javascript" src="js/dataTable/numberSort.js"></script>
   		<script>
		$(document).ready(function() {
			$('#eventTable').dataTable({
				
			     "aoColumns": [
			                   { "sType": "natural" },
			                   null,
			                   null,
			                   null,
			                   null,
			               ]
			});
		});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<div class="col-xs-12" style="padding:0;">
			<h3 class="col-xs-8" style="padding:0;">Upcoming Events </h3>
			<div class="col-xs-1 ">
			<button type="submit" id="addEvent" class="btn btn-primary" onClick="location.href='createEventStep1.jsp'">
	  			<span class="glyphicon glyphicon-plus-sign"></span> Create Event
			</button>
			</div>
		</div>
		<br/>
		<br/>
		<table class="table table-bordered" cellspacing="0" width="100%" id="eventTable">
			<thead>
				<tr>
					<td> Event No </td>
					<td> Name </td>
					<td> Description </td>
					<td> From </td>
					<td> To </td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eventArrList}" var="item">
					<tr>
						<td style="width:8%;"><a href="retrieveEvent?id=${item.eventID}">${item.eventID}</a></td>
						<td style="width:10%;">${item.eventName}</td>
						<td style="width:50%;">${item.eventDescription}</td>
						<td style="width:15%;">
						<fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" 
            			value="${item.eventDateTimeFrom}" /></td>
						<td style="width:15%;"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aa" 
            			value="${item.eventDateTimeTo}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br/>
		<br/>
	</jsp:attribute>
</t:master>