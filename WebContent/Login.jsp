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
			margin-top:45px;
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
		
		<form action="LoginServlet" method="post">
			<div class="panel panel-info">
			<div class="panel-heading" align="middle">
			<img id="icon" src="resources/logo.png" height="100" width="100" >
				<h2>Welcome to Community OutReach</h2>
			</div>
				<div class="panel-body">
				<div class="input-group">
		  					<span class="input-group-addon">UserName: </span>
		 					<input type="text" class="form-control" placeholder="Please enter your user name"
							name="user" value="S9911332E">
							
						</div>
						<br/>
						<div class="input-group">
		  					<span class="input-group-addon">Password: </span>
		 					<input type="password" class="form-control" placeholder="Please enter your password"
							name="pwd" value="123123">
						</div>
				
				<div>
    				
  				</div>
  				
  				<br />
  				   &nbsp<input type="submit" class="btn btn-primary btn-sm"
						value="Login" id="submitBtn">	
			</div>
		</div>
		</form>
		
		<!-- JS import -->
		<script src="plugins/jquery-2.x.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>