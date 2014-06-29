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
		<a href="createEventStep1.jsp">
			<button type="submit" id="addHobbies" class="btn btn-primary">
  				<span class="glyphicon glyphicon-plus-sign"></span> Create Event
			</button>
		</a>
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
	</jsp:attribute>
</t:master>