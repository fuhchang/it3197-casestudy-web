<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports"></jsp:attribute>
	
	<jsp:attribute name="content">
		<h1>Submit Riddle</h1>
		
		<form action="CreateRiddleWebServlet" method="post">
			<div class="form-group">
				<label for="title">Title: </label>
				<input type="text" class="form-control" name="title" id="title" placeholder="Enter title">
			</div>
			<div class="form-group">
			  <label for="content">Content: </label>
			  <textarea rows="2" class="form-control" name="content" id="content" placeholder="Enter content"></textarea>
			</div>
			<div class="form-group">
				<label for="choices">Choices: </label>
				
				<div class="input-group">
					<span class="input-group-addon">
				    	<input type="radio" name="choices" value="0" checked>
			    	</span>
				    <input class="form-control" name="choice1" type="text" id="choice1" placeholder="Enter choice">
				</div>
				<br/>
				<div class="input-group">
					<span class="input-group-addon">
				    	<input type="radio" name="choices" value="1">
			    	</span>
				    <input class="form-control" name="choice2" type="text" id="choice2" placeholder="Enter choice">
				</div>
				<br/>
				<div class="input-group">
					<span class="input-group-addon">
				    	<input type="radio" name="choices" value="2">
			    	</span>
				    <input class="form-control" name="choice3" type="text" id="choice3" placeholder="Enter choice">
				</div>
				<br/>
				<div class="input-group">
					<span class="input-group-addon">
				    	<input type="radio" name="choices" value="3">
				    </span>
				    <input class="form-control" name="choice4" type="text" id="choice4" placeholder="Enter choice">
				</div>
			</div>
			<button type="submit" class="btn btn-default col-xs-offset-9 col-xs-3">Submit</button>
		</form>
	</jsp:attribute>
</t:master>