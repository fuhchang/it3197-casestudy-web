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
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports"></jsp:attribute>
	
	<jsp:attribute name="content">
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