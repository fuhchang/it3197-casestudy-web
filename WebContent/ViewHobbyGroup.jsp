<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<Script type="text/javascript">
		
		</Script>
	</jsp:attribute>

	<jsp:attribute name="content">
	
	<div>
	
<div class="text-center">
    <h1>${GrpName}</h1>
    <p>${GrpDesc}</p>
  </div>
  <div class="col-xs-7">
	</div>
	<div class="col-xs-5" style="display:${hidden}">
	<a href="JoinHobbyServlet?id=${id}">
		<button type="submit" id="addHobbies" class="btn btn-defaul" data-toggle="modal" data-target="#">
  			<span class="glyphicon glyphicon-plus-sign"></span> Join Hobby
		</button>
		</a>
		</div>
		<br/>
		<br/>
	<br/>
	
  <div>
		<table class="table table-striped col-xs-12" id="grpTable2"> 	
			<tbody>
			<c:forEach items="${postList}" var="item">
				<tr>
					<td>
						
							<div class="portfolio-item">
								 <h2 name="gName" class="panel-heading text-left">${item.postTitle}</h2>
								<textarea name="summernote" rows="5" cols="30" readonly>${item.content}</textarea>
					 		</div>
            			
            		</td>
				</tr>
				<tr>
				<td>
				<div class="portfolio-item">
				</div>
				</td>
				</tr>
			</tbody>
			</c:forEach>
			</table>
			
	</div>
	</div>
	</jsp:attribute>
</t:master>
