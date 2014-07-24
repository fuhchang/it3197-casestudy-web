<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports">
		<script>
		var riddleList = ${riddleList};
		var riddleAnsList = ${riddleAnsList};
		$(document).ready(function(){
		});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<h1>Riddle</h1>
		
		<button type="submit" class="col-xs-offset-7 col-xs-5 btn btn-default" onClick="location.href='createRiddle.jsp'"><span class="glyphicon glyphicon-plus-sign"></span> Create Riddle</button>
		
		<div class="panel-group" id="accordion">
			<c:forEach items="${riddleList}" var="riddle">
				<div class="panel panel-default">
					<a data-toggle="collapse" data-parent="#accordion" href="#riddle${riddle.riddleID}" style="color:white;text-decoration:none;">
						<div class="panel-heading" style="background-color:grey;">
							<h3 class="panel-title"><strong>${riddle.riddleTitle}</strong><span class="glyphicon glyphicon-chevron-right pull-right"></span></h3>
						</div>
					</a>
					
					<div style="height:1px;background-color:grey"></div>
					
					<div id="riddle${riddle.riddleID}" class="panel-collapse collapse in" data-toggle="modal" data-target=".modal${riddle.riddleID}">
						<div class="panel-body">${riddle.riddleContent}</div>
					</div>
					
					<div class="modal fade modal${riddle.riddleID}">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title"><strong>${riddle.riddleTitle}</strong></h4>
								</div>
								<div class="modal-body">
									<p>${riddle.riddleContent}</p>
									<c:forEach items="${riddleAnsList}" var="riddleAns">
										<c:if test="${riddleAns.riddle.riddleID == riddle.riddleID}">
											<button class="btn btn-default form-control" style="margin-top:5%;">${riddleAns.riddleAnswer}</button>
										</c:if>
									</c:forEach>
								</div>
								<div class="modal-footer">
									<button class="btn btn-default glyphicon glyphicon-pencil"> Edit</button>
									<button class="btn btn-default glyphicon glyphicon-trash"> Delete</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</jsp:attribute>
</t:master>