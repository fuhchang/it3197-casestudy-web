<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="cssImports" fragment="true" %>
<%@ attribute name="jsImports" fragment="true" %>
<%@ attribute name="content" fragment="true" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<meta name="viewport" content="initial-scale = 1.0,maximum-scale = 1.0" />
		<title>Community Outreach</title>
		
		<!-- CSS Import -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/master.css"/>
		<jsp:invoke fragment="cssImports"/>
	</head>
	
	<body class="container">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="pull-left navbar-toggle" data-toggle="collapse" data-target="#nav-content">
       					<span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.jsp"><img src="resources/community.png" height="25px" width="25px"> Community Outreach</a>
				</div>
				
				<div class="collapse navbar-collapse" id="nav-content">
					<ul class="nav navbar-nav">
						<li><a href="event.jsp">Events</a></li>
						<li><a href="article.jsp">Articles</a></li>
						<li><a href="hobby.jsp">Hobby</a></li>
						<li><a href="riddle.jsp">Riddle</a></li>
						<li><a href="profile.jsp">Profile</a></li>
						<li><a href="setting.jsp">Setting</a></li>
						
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="DesktopLatestArticle">Town Council - Articles</a></li>
					</ul>
				</div>
			</div>
		</nav>
		
		<!-- Content Structure -->
		<div class="content container">
			<jsp:invoke fragment="content"/>
		</div>
	
		<!-- JS Import -->
		<script src="plugins/jquery-2.x.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/master.js"></script>
		<jsp:invoke fragment="jsImports"/>
	</body>
</html>