<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports"></jsp:attribute>
	
	<jsp:attribute name="content">
		<h1>Update Riddle</h1>
		
		<form action="UpdateRiddleWebServlet" method="post">
			<div class="form-group">
				<label for="title">Title: </label>
				<input type="text" class="form-control" name="title" value="${riddle.riddleTitle}" placeholder="Enter title">
			</div>
			<div class="form-group">
			  <label for="content">Content: </label>
			  <textarea rows="2" class="form-control" name="content" placeholder="Enter content">${riddle.riddleContent}</textarea>
			</div>
			<div class="form-group">
				<label for="choices">Choices: </label>
				
				<c:forEach items="${riddleAnsList}" var="riddleAns" varStatus="theCount">
					<div class="radio">
						<c:choose>
							<c:when test="${riddleAns.riddleAnswerStatus == 'CORRECT'}">
								<input type="radio" name="choices" value="${theCount.index}" checked>
							</c:when>
							<c:otherwise>
																<!-- theCount.index is 0, 1, 2, 3 -->
								<input type="radio" name="choices" value="${theCount.index}">
							</c:otherwise>
						</c:choose>
													<!-- theCount.count is 1, 2, 3, 4 -->
						<input class="form-control" name="choice${theCount.count}" type="text" value="${riddleAns.riddleAnswer}">
					</div>
				<input type="hidden" name="riddleAnsID${theCount.index}" value="${riddleAns.riddleAnswerID}" />
				</c:forEach>
			</div>
			<input type="hidden" name="riddleID" value="${riddle.riddleID}" />
			<button type="submit" class="btn btn-default col-xs-offset-9 col-xs-3">Update</button>
		</form>
	</jsp:attribute>
</t:master>