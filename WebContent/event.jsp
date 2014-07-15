<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<div class="col-xs-12">
		<h1 class="col-xs-9">Events</h1>
		<div class="col-xs-1 ">
		<button type="submit" id="addHobbies" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
  			<span class="glyphicon glyphicon-plus-sign"></span> Create Event
		</button>
		</div>
		</div>
		<ul class="nav nav-tabs text-center" id="tabs">
			<li class="col-xs-6"><a href="#joined" data-toggle="tab">Joined</a></li>
			<li class="col-xs-6"><a href="#all" data-toggle="tab">All</a></li>
		</ul>

		<div class="tab-content">
			<div class="tab-pane" id="joined">
				<h3>Joined</h3>
			</div>
			<div class="tab-pane" id="all">
				<h3>All</h3>
			</div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">Select type of event</h4>
		      </div>
		      <div class="modal-body">
		        Please select a type of event
		        <br/>
		        <br/>
		      	<form method="post" action="createEventStep1.jsp">
			  		<input type="submit" class="btn btn-default" name="typeOfEvent" value="Big Event" />
		        	<input type="submit" class="btn btn-default" name="typeOfEvent" value="Small Event" />
		        </form>
		      </div>
		    </div>
		  </div>
		</div>
	</jsp:attribute>
</t:master>