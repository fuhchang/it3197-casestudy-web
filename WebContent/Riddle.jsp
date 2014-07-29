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
			<button type="submit" class="col-xs-offset-2 col-xs-6 btn btn-default" onClick="location.href='CreateRiddle.jsp'" style="margin-top:6%;margin-bottom:5%;"><span class="glyphicon glyphicon-plus-sign"></span> Submit your riddle</button>
		</div>
		
		<div class="panel-group">
			<!-- For each riddle, a panel to display -->
			<c:forEach items="${riddleList}" var="riddle">
				<div class="panel panel-default">
					<c:choose>
						<c:when test="${riddle.user.nric == user.nric}">
							<div class="panel-heading" style="color:white;background-color:gray;">
								<h3 class="panel-title"><strong>${riddle.riddleTitle}</strong><span class="pull-right glyphicon glyphicon-chevron-right"></span></h3>
							</div>
							<div id="riddle${riddle.riddleID}" class="panel-content" data-toggle="modal" data-target=".modal${riddle.riddleID}">
								<div class="panel-body" style="text-align:justify">${riddle.riddleContent}<span class="pull-right glyphicon glyphicon-hand-left"></span></div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="panel-heading" style="color:white;background-color:black;">
								<h3 class="panel-title"><strong>${riddle.riddleTitle}</strong><span class="pull-right glyphicon glyphicon-chevron-right"></span></h3>
							</div>
							<div id="riddle${riddle.riddleID}" class="panel-content" onClick="location.href='ViewRiddleWebServlet?riddleID=${riddle.riddleID}'">
								<div class="panel-body" style="text-align:justify">${riddle.riddleContent}<span class="pull-right glyphicon glyphicon-hand-left"></span></div>
							</div>
						</c:otherwise>
					</c:choose>
					
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
			</c:forEach>
		</div>
	</jsp:attribute>
</t:master>