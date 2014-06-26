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
		<h1>Events</h1>
		
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