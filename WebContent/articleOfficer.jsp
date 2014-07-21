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
	 
	 <div class="panel panel-info" Style="width:100%;margin:0px auto;">
		<div class="panel-heading">
			<h2>Pending articles</h2>
			
		</div>
		
		<div class="panel-body" Style="width:100%; " >
		
		
		
		
		<button type="submit" class="pull-right btn btn-primary btn-sm"value="Refresh" id="btn" style="margin-left:5px;">Refresh</button>
		
		<a class=" pull-right btn btn-primary btn-sm" onclick="location.href='articleSubmission.jsp'">Submit Article</a>
		<br/>
		
		<br/>
		
		
		
		
					
			<!--<table class="table table-striped">-->
			<table class="table table-striped" width="100%" border="1" id="articleTable">	
				<thead>
					<tr>
						<th>Articles:</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${artList}" var="item">
							<tr>
							 	
								<td>
									<h4><p class="text-warning"><b><u>${item.title}</u></b></p></h4>
									
									<label style="color:#B40431;">Posted By: ${item.articleUser}</label>
									<br/>
									${item.articleDate}
									<br/>
									${item.location }
									
								
									<br/>
									<a type="submit" href="DisplaySelectedArticle?id=${item.articleID }" id="" name="article-content-id"><u>Read More</u></a>
									
								</td>
								
								
									
							</tr>
						</c:forEach>
				
				
				</tbody>		
			   </table>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		</form>
	</jsp:attribute>
</t:master>

<script>
	$(document).ready(function() {
		//$('#articleTable').dataTable();
	} );

</script>