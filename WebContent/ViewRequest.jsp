<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" href="css/datepicker.css">
        <link rel="stylesheet" href="css/bootstrap.css">
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
  		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
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
					<th>Event Name</th>
					<th>Request</th>
					<th>status</th>
					<th>View Date</th>
				</tr>
			</thead>		
			<tbody>
			<c:forEach items="${reqList}" var="item">
			
				<tr>
					<td class="col-xs-4">
						${item.requestID}
            		</td>
            		<td class="col-xs-4">
            			<a href="accpetRequestServlet?id=${item.requestID}&grpID=${item.hobbyID}">
            			<button type="${statusList}" id="btnRequest" class="btn btn-defaul" data-toggle="modal" data-target="#">
            				Accept
            			</button>
            			</a>
            		</td>
            		<td class="col-xs-4">
					${item.requestStatus}
            		</td>
            		
            		<td>
            		<button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">
							View
						</button>
            		</td>
				</tr>
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h3>Event Name</h3>
							</div>
							<div class="modal-body">
								<div class='col-sm-6'>
								
            <div class="form-group">
            
                <p>Start Date: ${item.requestDateStart}</p>
                <br>
             	<br>
             	<br>
             	<p>End Date: ${item.requestDateEnd}</p>
            </div>
        </div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
					    </div>
					</div>
				</div>
				
				</c:forEach>	
			</tbody>
			</table>
			
		</div>
		
	</jsp:attribute>
</t:master>
