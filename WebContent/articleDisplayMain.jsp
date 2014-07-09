<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/dataTable/jquery.dataTables_themeroller.css" />
   		<link rel="stylesheet" type="text/css" href="css/dataTable/demo_page.css" />
   		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.custom.css" />
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	  	<script src="js/displayArticle.js"></script>
	  	<script type="text/javascript" language="javascript" src="js/dataTable/dataTables.js"></script>
   		<script type="text/javascript" language="javascript" src="js/dataTable/numberSort.js"></script>
	</jsp:attribute>
	<jsp:attribute name="content">
	
	<form action="DisplayArticleMainServlet" method="post">
	<br />
	 
	<div class="panel panel-info" Style="width:850px;margin:0px auto;">
		<div class="panel-heading">
			<!--  <h1 class="panel-title">Latest News From Around The Neighbourhood</h1>-->
			<h1>Latest News From Around The Neighbourhood</h1>
			
		</div>
		
		<br/>
		
		
		<div class="panel-body" Style="width:800px;margin:0px auto; border:1px solid black;" >
		
		<button type="submit" class="pull-right btn btn-primary btn-sm"value="Refresh" id="btn" style="margin-left:5px;">Refresh</button>
		
		<a class=" pull-right btn btn-primary btn-sm" onclick="location.href='articleSubmission.jsp'">Submit Article</a>
		
		<br/>
		<hr/>			
			<!--<table class="table table-striped">-->
			<table class="table table-striped" cellspacing="0" width="100%" id="articleTable">				
					<thead>
						<tr>
							<th></th>
							<th>Date Published:</th>
							<th>Article Title:</th>	
							<th>Author:</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="articleTable">
						<c:forEach items="${artList}" var="item">
							<tr>
								<td>
									<ul><li></li></ul>
								</td>
									
								<td>
									${item.articleDate}
								</td>
								<td>
									${item.title}
								</td>
									
								<td>
									${item.articleUser}
								</td>
								<td>
									<a type="submit" href="DisplaySelectedArticle?id=${item.articleID }" id="" name="article-content-id"><u>Read</u></a>
								</td>	
							</tr>
						</c:forEach>
			   		 </tbody>
			   </table>
			   	<br/>
				<br/>
		
		</div>
		<br/>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		</form>
	</jsp:attribute>
</t:master>

<script>
	$(document).ready(function() {
		$('#articleTable').dataTable();
	} );

</script>