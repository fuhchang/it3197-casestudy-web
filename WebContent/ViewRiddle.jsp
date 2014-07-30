<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports">
		<style type="text/css">
			.btn {
				margin-top:2%;
				margin-bottom:3%;
			}
			.glyphicon {
				margin-right: -10%;
			}
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<script>
			$(document).ready(function(){
				$('#status').popover({
					html: true,
					content: '<table class="table">'+
								'<tr><th class="text-center">Points given: ${riddle.riddlePoint}</th></tr>'+
								'<tr style="padding-bottom:0%;"><td>Correctly Answered: ${correctlyAnswered} out of ${answeredList.size()} people</td></tr></table>'
				});
			    setTimeout(function() {
			        $('#rating').modal('show');
			    }, 5000); // milliseconds
			});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<button type="button" id="status" class="btn btn-info col-xs-offset-8 col-xs-4" data-container="body" data-toggle="popover" data-placement="bottom">View Status</button>
		
		<form action="InsertChoiceWebServlet" method="post">
			<c:set var="riddleID" value="${riddle.riddleID}"/>
			
			<div class="form-group">
				<h4><strong>Title:</strong></h4>
				<div class="well">${riddle.riddleTitle}</div>
			</div>
			<div class="form-group">
				<h4><strong>Content:</strong></h4>
				<div class="well" style="text-align:justify">${riddle.riddleContent}</div>
			</div>
			<div class="form-group">
				<h4><strong>Choices:</strong></h4>
				<c:forEach items="${riddleAnsList}" var="riddleAns">
					
					<c:forEach items="${userAnsweredList}" var="userAns">
						<c:if test="${userAns.riddle.riddleID == riddleID}">
							<c:set var="answered" value="true"/>
							
							<c:if test="${riddleAns.riddleAnswerStatus == 'CORRECT'}">
								<c:choose>
									<c:when test="${userAns.riddleAnswer.riddleAnswerID == riddleAns.riddleAnswerID}">
										<button class="btn btn-success form-control" disabled><span class="pull-left glyphicon glyphicon-ok"></span>${riddleAns.riddleAnswer}</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-success form-control" disabled>${riddleAns.riddleAnswer}</button>
									</c:otherwise>
								</c:choose>
							</c:if>
							
							<c:if test="${riddleAns.riddleAnswerStatus == 'WRONG'}">
								<c:choose>
									<c:when test="${userAns.riddleAnswer.riddleAnswerID == riddleAns.riddleAnswerID}">
										<button class="btn btn-danger form-control" disabled><span class="pull-left glyphicon glyphicon-remove"></span>${riddleAns.riddleAnswer}</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-danger form-control" disabled>${riddleAns.riddleAnswer}</button>
									</c:otherwise>
								</c:choose>
							</c:if>
							
							<c:if test="${userAns.answeredRate == 'NULL'}">
								<div id="rating" class="modal fade">
									<div class="modal-dialog modal-sm">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
												<h4 class="modal-title text-center"><strong>How will you rate this riddle?</strong></h4>
											</div>
											<div class="modal-body">
												<div class="row">
													<button class="btn btn-success col-xs-offset-1 col-xs-3" name="rating" value="easy" style="margin-right:3%;"><span class="glyphicon glyphicon-star" style="padding:0%;"></span><br/>Easy</button>
													<button class="btn btn-warning col-xs-3" name="rating" value="medium" style="margin-right:3%;"><span class="glyphicon glyphicon-star" style="padding-right:10%;"></span><span class="glyphicon glyphicon-star"></span><br/>Medium</button> 
													<button class="btn btn-danger col-xs-3" name="rating" value="hard"><span class="glyphicon glyphicon-star" style="padding-right:10%;"></span><span class="glyphicon glyphicon-star" style="padding-right:10%;"></span><span class="glyphicon glyphicon-star"></span><br/>Hard</button>
												</div>
												<br/>
												<div class="row text-right">
													<em>* 1 point will be awarded for participation *</em>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</c:if>
					</c:forEach>
					
					<c:if test="${answered != 'true'}">
						<button type="submit" class="btn btn-default form-control" name="riddleAnswerID" value="${riddleAns.riddleAnswerID}">${riddleAns.riddleAnswer}</button>
					</c:if>
				</c:forEach>
			</div>
			<input type="hidden" name="riddleID" value="${riddle.riddleID}" />
		</form>
	</jsp:attribute>
</t:master>