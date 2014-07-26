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
		function editPost(){
			
		}
		</Script>
	</jsp:attribute>

	<jsp:attribute name="content">
	
	<div>
	
<div class="text-center">
    <h1>${GrpName}</h1>
    <p>${GrpDesc}</p>
  </div>
		<br/>
		<br/>
		<div class="input-group">
		<a href="ViewRequestServlet?grpID=${id}">
		 <input type="${type}" class="form-control" placeholder="Write you thought" id="posting" value="View Request"readonly>
		</a>
		</div>
		<br>
  <div class="input-group">
 
		<span class="input-group-addon">Post: </span>
		 <a href="createPost.jsp?grpID=${id}">
		 <input type="text" class="form-control" placeholder="Write you thought" id="posting" readonly>
		</a>
		</div>
	<br/>
	
  <div>
		<table class="table table-striped col-xs-12" id="grpTable2"> 	
			<tbody>
			<c:forEach items="${postList}" var="item">
				<tr>
					<td>
						
							<div class="portfolio-item">
							<a href="EditPost.jsp?id=${item.postID}&grpPost=${item.postTitle}&postContent=${item.content}&grpid=${item.grpID}">
									<button class="btn glyphicon glyphicon-pencil" style="float: right"'>Edit</button>
									</a>
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
