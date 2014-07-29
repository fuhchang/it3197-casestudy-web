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
		<link rel="stylesheet"
			href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
  		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
		<Script type="text/javascript">
			$(function() {
				$("#datepicker").datepicker({
					showOn : "button",
					buttonImage : "images/calendar.png",
					buttonImageOnly : true
				});
				$("#datepicker1").datepicker({
					showOn : "button",
					buttonImage : "images/calendar.png",
					buttonImageOnly : true
				});
			});

			function sendData() {
				var mge = $('#datepicker').val();
				mge += ",";
				mge += $('#datepicker1').val();
				mge += ",";
				mge += $('#id').val();
				mge += ",";
				$.ajax({
					type : "POST",
					url : "RequestHobbyHelpServlet",
					data : {
						message : mge
					},
					success : function(data) {
						alert('success' + data);
					}
				});

			}
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
				<tr>
					<td class="col-xs-4">
						${item.grpName}
            		</td>
            		<td class="col-xs-4">
            		<a href="ViewGroupServlet?id=${item.grpID}&request=1">
            			<button type="submit" id="addHobbies"
											class="btn btn-defaul" data-toggle="modal" data-target="#">
            				View
            			</button>
            			</a>
            		</td>
            		<td class="col-xs-4">
					<button class="btn btn-default btn-sm" data-toggle="modal"
										data-target="#myModal">
							request
						</button>
            		</td>
				</tr>
				</a>
				
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span>
											<spansm>
										</button>
								<h3>${item.grpName}</h3>
							</div>
							<div class="modal-body">
								<div class='col-sm-6'>
								
            <div class="form-group">
            <input type="hidden" id="id" value="${item.grpID}">
                <p>Start Date: <input type="text" id="datepicker" name="date1" readonly>
												</p>
                <br>
             	To
             	<br>
             	<p>End Date: <input type="text" id="datepicker1"
														name="date2" readonly>
												</p>
            </div>
        </div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary" id="requestHobby"
											onclick="sendData()">Request</button>
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
