<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0" />
		<title>Community Outreach</title>
		
		<!-- CSS import -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
		
		<style>
		html, body
		{
			height:100%;
			width:100%;
		}
		.container
		{
			width:100%;
			margin-left:0;
			margin-right:0;
			text-align:center;
		}
		.glyphicon
		{
			font-size:50px;
		}
		#icon
		{
			margin-top:5%;
		}
		</style>
	</head>
	
	<body class="container">
		<img id="icon" src="resources/community.png">
	 	<h1>Community Outreach</h1>

		<div class="row marketing">
			<form action="retrieveAllEvents" method="GET">
				<button class="btn btn-default col-xs-6">
					<span class="glyphicon glyphicon-calendar"></span>
					<h4>Events</h4>
				</button>
				<input type="hidden" name="web" value="true" />
			</form>
			<button class="btn btn-default col-xs-6" onclick="location.href='article.jsp'">
				<span class="glyphicon glyphicon-list-alt"></span>
				<h4>Articles</h4>
			</button>
			<button class="btn btn-default col-xs-6" onclick="location.href='RetrieveAllHobbyServlet'">
				<span class="glyphicon glyphicon-heart"></span>
				<h4>Hobby</h4>
			</button>
			<button class="btn btn-default col-xs-6" onclick="location.href='RetrieveAllRiddleWebServlet'">
				<span class="glyphicon glyphicon-book"></span>
				<h4>Riddle</h4>
			</button>
			<button class="btn btn-default col-xs-6" onclick="location.href='profile.jsp'">
				<span class="glyphicon glyphicon-user"></span>
				<h4>Profile</h4>
			</button>
			<button class="btn btn-default col-xs-6" onclick="location.href='setting.jsp'">
				<span class="glyphicon glyphicon-cog"></span>
				<h4>Settings</h4>
			</button>
		</div>
		
		<!-- JS import -->
		<script src="plugins/jquery-2.x.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>