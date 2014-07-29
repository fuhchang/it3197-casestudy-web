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
		<div class="col-xs-12">
		<h1 class="col-xs-7">Hobby</h1>
		<div class="col-xs-1 ">
		<a href="createHobby.jsp">
		<button type="submit" id="addHobbies" class="btn btn-defaul" data-toggle="modal" data-target="#">
  			<span class="glyphicon glyphicon-plus-sign"></span> Create Hobby
		</button>
		</a>
		</div>
		</div>
		<ul class="nav nav-tabs text-center" id="tabs">
			<li class="col-xs-6"><a href="#joined" data-toggle="tab">Joined</a></li>
			<li class="col-xs-6"><a href="#all" data-toggle="tab">All</a></li>
		</ul>
		
		<div class="tab-content">
			<div class="tab-pane" id="joined">
					<table class="table table-striped">
			<tbody>
			<c:forEach items="${joinList}" var="joinItem">
				<tr class="col-sm-4">
				
					<td>
					<a href="ViewJoinGrpServlet?id=${joinItem.grpID}">
					<div class="col-sm-4 portfolio-item">
                    <img class="img-responsive" src="${joinItem.photo}" id="img">
					 <h3 name="gName">${joinItem.grpName}</h3>
					<textarea name="summernote" id="summernote" cols="27" rows="7"
											readonly>${joinItem.grpDesc}</textarea>
	
            </div>
            </a>
            </td>
				</tr>
				<tr>
					<td>
						<br>
					</td>
				</tr>
				</c:forEach>	
			</tbody>
			</table>
			</div>
			<div class="tab-pane" id="all">
					<table class="table table-striped" id="grpTable2"> 
					
			<tbody>
			<c:forEach items="${hobbyList}" var="item">
				<tr class="col-sm-4">
					
					<td>
					<a href="ViewGroupServlet?id=${item.grpID}&request=0">
					<div class="col-sm-4 portfolio-item">
                    <img class="img-responsive" src="http://placehold.it/250x100" id="img">
					 <h3 name="gName">${item.grpName}</h3>
					<textarea name="summernote" id="summernote" cols="27" rows="7"
											readonly>${item.grpDesc}</textarea>
	
            </div>
            </a>
            </td>
				</tr>
				<tr>
					<td>
						<br>
					</td>
				</tr>
				</c:forEach>	
			</tbody>
			</table>
			</div>
		</div>

	</jsp:attribute>
</t:master>
