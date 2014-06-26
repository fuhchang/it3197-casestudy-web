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
		<h1>Articles</h1>
		
		<div class="jumbotron">
			<h2>Brief Intro</h2>
		</div>
		
		<ul class="nav nav-tabs text-center" id="tabs">
			<li class="col-xs-6"><a href="#latest" data-toggle="tab">Latest</a></li>
			<li class="col-xs-6"><a href="#upcoming" data-toggle="tab">Upcoming</a></li>
		</ul>
		
		<div class="tab-content">
			<div class="tab-pane" id="latest">
				<h3>Latest</h3>
			</div>
			<div class="tab-pane" id="upcoming">
				<h3>Upcoming</h3>
			</div>
		</div>
	</jsp:attribute>
</t:master>