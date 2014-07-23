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
		<h2>Select Category </h2>
		</div>
		
		
	
			
					<table class="table">
			<tbody>
			
				<tr>
					<td>
					<a href="SearchHobbyForRequest?id=1">
					<button class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true">Dance<i class="glyphicon glyphicon-play pull-right"></i></button>
					</a>
           			 </td>
				</tr>
				<tr>
					<td>
					<a href="SearchHobbyForRequest?id=2">
						<button class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true">Cooking<i class="glyphicon glyphicon-play pull-right"></i></button>
						</a>
					</td>
				</tr>
				<tr>
					<td>
					<a href="SearchHobbyForRequest?id=3">
						<button class="btn btn-default btn-block" data-dismiss="modal" aria-hidden="true">Gardeing<i class="glyphicon glyphicon-play pull-right"></i></button>
						</a>
					</td>
				</tr>
			</tbody>
			</table>
			
			
			
		</div>

	</jsp:attribute>
</t:master>
