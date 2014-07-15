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
		<h1 class="col-xs-9">Hobby</h1>
		<div class="col-xs-3 ">
		<button type="submit" id="addHobbies" class="btn btn-primary">
  		<a href="createHobby.jsp"><span
						class="glyphicon glyphicon-plus-sign"></span></a>
		</button>
		</div>
		</div>
		<ul class="nav nav-tabs text-center" id="tabs">
			<li class="col-xs-6"><a href="#joined" data-toggle="tab">Joined</a></li>
			<li class="col-xs-6"><a href="#all" data-toggle="tab">All</a></li>
		</ul>

		<div class="tab-content">
			<div class="tab-pane" id="joined">
					<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>a</td>
					<td>b</td>
					<td>c</td>
				</tr>
			</tbody>
			</table>
			</div>
			<div class="tab-pane" id="all">
					<table class="table table-striped" id="grpTable2"> 
					
			<tbody>
			<c:forEach items="${hobbyList}" var="item">
				<tr class="col-sm-4">
				
					<td>
					<a href="ViewGroupServlet?id=${item.grpID}">
					<div class="col-sm-4 portfolio-item">
                    <img class="img-responsive" src="http://placehold.it/250x100" id="img">
					 <h3 name="gName">${item.grpName}</a>
                		</h3>
					<textarea name="summernote" id="summernote" cols="27" rows="7"
											readonly>${item.grpDesc}</textarea>
	
            </div>
            </a>
            </td>
				</tr>
				</c:forEach>	
			</tbody>
			</table>
			</div>
		</div>

	</jsp:attribute>
</t:master>
