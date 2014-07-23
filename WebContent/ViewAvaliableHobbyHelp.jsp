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
		<h1>Request</h1>
		</div>
		<div class="panel panel-info">
		<table class="table table-striped" id="grpTable2"> 
			<thead>
				<tr>
					<th>Group Name</th>
					<th>View group</th>
					<th>Make Request</th>
				</tr>
			</thead>		
			<tbody>
			<c:forEach items="${hobbyList}" var="item">
			<a href="ViewGroupServlet?id=${item.grpID}&request='1'">
				<tr >
					<td class="col-xs-4">
						${item.grpName}
            		</td>
            		<td class="col-xs-4">
            		<a href="ViewGroupServlet?id=${item.grpID}&request=1">
            			<button type="submit" id="addHobbies" class="btn btn-defaul" data-toggle="modal" data-target="#">
            				View
            			</button>
            			</a>
            		</td>
            		<td class="col-xs-4">
						<a href="href="ViewGroupServlet?id=${item.grpID}&request='1'">
					<button type="submit" id="addHobbies" class="btn btn-defaul" data-toggle="modal" data-target="#">
  						Request
					</button>
					</a>
            		</td>
				</tr>
				</a>
				</c:forEach>	
			</tbody>
			</table>
		</div>
	</jsp:attribute>
</t:master>
