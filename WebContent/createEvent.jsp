<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<style>
			.floatLeftText{
				float:left;
			}
			#currentForm{
				margin-top:25px;
			}
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<div id="currentForm" class="form-horizontal" role="form">
			<div class="panel panel-primary">
			<div class="panel-heading">
				Create Event
			</div>
				<div class="panel-body">
					<div class="form-group">
						<span class="floatLeftText"><label class="col-xs-12 control-label">Event Name</label></span>
						<input class="form-control" type="text" name="gName" />
					</div>
					<div class="form-group">
						<span class="floatLeftText"><label class="col-xs-12 control-label">Category</label></span>
						<select class="form-control" id="gType">
							<option> Arts </option>
							<option> Education</option>
							<option> Family </option>
							<option> Health </option>
						</select>
					</div>
					<div class="form-group">
						<span class="floatLeftText"><label class="col-xs-12 control-label">Description</label></span>
						<textarea class="form-control" rows="3"></textarea>
					</div>
					<div class="form-group">
						<a href="suggestLocation.jsp"><button type="submit" class="btn btn-default"> Suggest Location </button></a>
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</t:master>