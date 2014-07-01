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
		 <h1></h1>
		
		<div class="jumbotron">
			<div class="row">
			  <div class="col-sm-6 col-md-4">
			    <div class="thumbnail">
			      <img id="icon" src="resources/wewantyou.jpg">
			      <div class="caption">
			        <p>Have an article to share? Complain to make? Request to give? You came to the right page!</p>
			        
			         <p><a href="#" class="btn btn-primary" onclick="location.href='articleSubmission.jsp'" role="button">Click Here To Start</a>
			      </div>
			    </div>
			  </div>
			</div>
			
		</div>
	
	</jsp:attribute>
</t:master>