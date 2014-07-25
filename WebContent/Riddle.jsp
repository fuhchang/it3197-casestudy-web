<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports">
		<script>
			$('.panel-collapse').collapse("hide");
			
			function deleteRiddle(riddleID) {
				if(confirm("Are you sure you want to delete?")) {
					window.location.href="DeleteRiddleWebServlet?riddleID="+riddleID;
				}
			}
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<h1>Riddle</h1>
		
		<button type="submit" class="col-xs-offset-7 col-xs-5 btn btn-default" onClick="location.href='CreateRiddle.jsp'"><span class="glyphicon glyphicon-plus-sign"></span> Create Riddle</button>
		
		<div class="panel-group" id="accordion">
			<!-- For each riddle, a panel to display -->
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
					
					<!-- For each riddle, a modal to display answers -->
					<div class="modal fade modal${riddle.riddleID}">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									<h4 class="modal-title"><strong>${riddle.riddleTitle}</strong></h4>
								</div>
								<div class="modal-body">
									<p>${riddle.riddleContent}</p>
									<!-- For each riddle get the four choices -->
									<c:forEach items="${riddleAnsList}" var="riddleAns">
										<!--  Only display the choices for the selected riddle -->
										<c:if test="${riddleAns.riddle.riddleID == riddle.riddleID}">
											<c:choose>
												<!-- If riddle belongs to logged in user, he cannot answer -->
												<c:when test="${riddle.user.nric == userNRIC}">
													<button class="btn btn-default form-control" style="margin-top:5%;" disabled>${riddleAns.riddleAnswer}</button>
												</c:when>
												<!-- If riddle does not belongs to logged in user, he can answer -->
												<c:otherwise>
													<button class="btn btn-default form-control" style="margin-top:5%;">${riddleAns.riddleAnswer}</button>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
								</div>
								<c:if test="${riddle.user.nric == userNRIC}">
									<div class="modal-footer">
										<button class="btn btn-default glyphicon glyphicon-pencil" onClick="location.href='UpdateRiddleWebServlet?riddleID=${riddle.riddleID}'"> Edit</button>
										<button class="btn btn-default glyphicon glyphicon-trash" onClick="deleteRiddle(${riddle.riddleID})"> Delete</button>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</jsp:attribute>
</t:master>