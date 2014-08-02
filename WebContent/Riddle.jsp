<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports">
		<script>
			var toggle;
			var notToggled = $('.panel-content').collapse("hide");
			
			$(document).ready(function(){
				toggle = $('.panel-heading').click(function() {
					toggle.not($(this).removeClass('active'));
					$(this).toggleClass('active');
					notToggled.not($(this).next()).slideUp();
					$(this).next().slideToggle("fast");
					
					if($('.panel-title', this).find('span').hasClass('glyphicon-chevron-right')) {
						$('.panel-title', this).find('span').removeClass('glyphicon-chevron-right');
						$('.panel-title', this).find('span').addClass('glyphicon-chevron-down');
					}
					else {
						$('.panel-title', this).find('span').removeClass('glyphicon-chevron-down');
						$('.panel-title', this).find('span').addClass('glyphicon-chevron-right');
					}
				});
			});
			
			function createRiddle() {
				if(confirm("50 points will be used to submit a riddle. Are you sure?")) {
					window.location.href="CreateRiddle.jsp";
				}
			}
			
			function cannotCreateRiddle() {
				alert("You do not have sufficient points. 50 points is required to submit a riddle.\nParticipate in events and hobbies or simply just travel with our web to earn some points.");
			}
			
			function deleteRiddle(riddleID) {
				if(confirm("Are you sure you want to delete?")) {
					window.location.href="DeleteRiddleWebServlet?riddleID="+riddleID;
				}
			};
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<div class="row">
			<h1 class="col-xs-3">Riddle</h1>
				<c:choose>
					<c:when test="${user.points >= 50}">
						<button class="col-xs-offset-2 col-xs-6 btn btn-default" onClick="createRiddle()" style="margin-top:6%;margin-bottom:5%;"><span class="glyphicon glyphicon-plus-sign"></span> Submit your riddle</button>
					</c:when>
					<c:otherwise>
						<button class="col-xs-offset-2 col-xs-6 btn btn-default" onClick="cannotCreateRiddle()" style="margin-top:6%;margin-bottom:5%;"><span class="glyphicon glyphicon-plus-sign"></span> Submit your riddle</button>
					</c:otherwise>
				</c:choose>
		</div>
		
		<div class="panel-group">
			<!-- For each unanswered riddle, a panel to display -->
			<c:forEach items="${riddleUnansweredList}" var="riddleUnanswered">
				<div class="panel panel-default">
					<c:choose>
						<c:when test="${riddleUnanswered.riddlePoint == 5}">
							<div class="panel-heading" style="color:white;background-color:green;">
								<h3 class="panel-title"><strong>${riddleUnanswered.riddleTitle}</strong><span class="pull-right glyphicon glyphicon-chevron-right"></span></h3>
							</div>
						</c:when>
						<c:when test="${riddleUnanswered.riddlePoint == 10}">
							<div class="panel-heading" style="color:white;background-color:orange;">
								<h3 class="panel-title"><strong>${riddleUnanswered.riddleTitle}</strong><span class="pull-right glyphicon glyphicon-chevron-right"></span></h3>
							</div>
						</c:when>
						<c:when test="${riddleUnanswered.riddlePoint == 20}">
							<div class="panel-heading" style="color:white;background-color:red;">
								<h3 class="panel-title"><strong>${riddleUnanswered.riddleTitle}</strong><span class="pull-right glyphicon glyphicon-chevron-right"></span></h3>
							</div>
						</c:when>
					</c:choose>
					<div id="riddle${riddleUnanswered.riddleID}" class="panel-content" onClick="location.href='ViewRiddleWebServlet?riddleID=${riddleUnanswered.riddleID}'">
						<div class="panel-body" style="text-align:justify">${riddleUnanswered.riddleContent}<span class="pull-right glyphicon glyphicon-hand-left"></span></div>
					</div>
				</div>
			</c:forEach>
			
			<!-- For each answered riddle, a panel to display -->
			<c:forEach items="${riddleAnsweredList}" var="riddleAnswered">
				<div class="panel panel-default">
					<div class="panel-heading" style="color:white;background-color:grey;">
						<h3 class="panel-title"><strong>${riddleAnswered.riddleTitle}</strong><span class="pull-right glyphicon glyphicon-chevron-right"></span></h3>
					</div>
					<div id="riddle${riddleAnswered.riddleID}" class="panel-content" onClick="location.href='ViewRiddleWebServlet?riddleID=${riddleAnswered.riddleID}'">
						<div class="panel-body" style="text-align:justify">${riddleAnswered.riddleContent}<span class="pull-right glyphicon glyphicon-hand-left"></span></div>
					</div>
				</div>
			</c:forEach>
			
			<!-- For each user created riddle, a panel to display -->
			<c:forEach items="${riddleList}" var="riddle">
				<c:if test="${riddle.user.nric == user.nric}">
					<div class="panel panel-default">
						<div class="panel-heading" style="color:white;background-color:black;">
							<h3 class="panel-title"><strong>${riddle.riddleTitle}</strong><span class="pull-right glyphicon glyphicon-chevron-right"></span></h3>
						</div>
						<div id="riddle${riddle.riddleID}" class="panel-content" data-toggle="modal" data-target=".modal${riddle.riddleID}">
							<div class="panel-body" style="text-align:justify">${riddle.riddleContent}<span class="pull-right glyphicon glyphicon-hand-left"></span></div>
						</div>
						
						<div class="modal fade modal${riddle.riddleID}">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										<h4 class="modal-title"><strong>${riddle.riddleTitle}</strong></h4>
									</div>
									<div class="modal-body">
										<p style="text-align:justify">${riddle.riddleContent}</p>
										<c:forEach items="${riddleAnsList}" var="riddleAns">
											<c:if test="${riddleAns.riddle.riddleID == riddle.riddleID}">
												<c:choose>
													<c:when test="${riddleAns.riddleAnswerStatus == 'CORRECT'}">
														<button class="btn btn-success form-control" style="margin-top:5%;" disabled>${riddleAns.riddleAnswer}</button>
													</c:when>
													<c:otherwise>
														<button class="btn btn-danger form-control" style="margin-top:5%;" disabled>${riddleAns.riddleAnswer}</button>
													</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
									</div>
									<div class="modal-footer">
										<button class="btn btn-default glyphicon glyphicon-pencil" onClick="location.href='UpdateRiddleWebServlet?riddleID=${riddle.riddleID}'"> Edit</button>
										<button class="btn btn-default glyphicon glyphicon-trash" onClick="deleteRiddle(${riddle.riddleID})"> Delete</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</jsp:attribute>
</t:master>