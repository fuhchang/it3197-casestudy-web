<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script src="js/displayArticle.js"></script>
	</jsp:attribute>
	<jsp:attribute name="content">
	
	
<br/>
	 
	<div class="panel panel-info">
		<div class="panel-heading">
			<!--  <h1 class="panel-title">Latest News From Around The Neighbourhood</h1>-->
			
			<table class="table table-striped">
						<thead>
						<tr>
							<td colspan="5">
								<h1>Latest News From Around The Neighbourhood</h1>
							</td>
				  			
							
						</tr>
						<tr >
							<th>Article Title:</th>
							<th>Date Published:</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="packingTable">
			
		
			   		 </tbody>
			   		 </table>
				&nbsp<input type="submit" class="btn btn-primary btn-sm" value="Print List" id="btn">
		</div>
		<div class="panel-body">
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</jsp:attribute>
</t:master>