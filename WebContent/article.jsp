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
		
		
			  <div class="col-sm-6 col-md-4">
			    <div class="thumbnail">
			      <img id="icon" src="resources/wewantyou.jpg">
			      <div class="caption">
			        <p><b>Have an article to share? Feedback to give? Request to make? You came to the right place!</b></p>			        
			          <p><a href="#" class="btn btn-primary" onclick="location.href='DisplayArticleMainServlet'" role="button">Click Here To Continue</a></p>
			        <!-- <p><a href="#" class="btn btn-primary" onclick="location.href='http://maps.google.com/maps?saddr=1.3792222033267854, 103.84977746969753&daddr=1.3728403867740486,103.84756732946926+to:1.3748461023871494, 103.8455932236343'" role="button">Click Here To Continue</a></p>
			        -->
			         
			      </div>
			    </div>
			  </div>
			
	</jsp:attribute>
</t:master>