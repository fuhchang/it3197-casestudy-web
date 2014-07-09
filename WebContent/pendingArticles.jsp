<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<link rel="stylesheet" type="text/css" href="css/dataTable/jquery.dataTables_themeroller.css" />
   		<link rel="stylesheet" type="text/css" href="css/dataTable/demo_page.css" />
   		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.custom.css" />
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script src="js/displayArticle.js"></script>
	  	<script type="text/javascript" language="javascript" src="js/dataTable/dataTables.js"></script>
   		<script type="text/javascript" language="javascript" src="js/dataTable/numberSort.js"></script>
		<Script type="text/javascript">
		</Script>
	</jsp:attribute>

	<jsp:attribute name="content">
	
	
		<br/>
		<div class="panel panel-info" Style="width:850px;margin:0px auto;">
			<div class="panel-heading">
				<h2>Pending Feedback(s)</h2>
			</div>	
			<br/>	
			<div class="panel-body" Style="width:800px;margin:0px auto; border:1px solid black;" >
			<!-- 		<ul class="nav nav-tabs text-center" id="tabs" style="margin-top:-20px;">
						<li class="col-xs-6"><a href="#feedback" data-toggle="tab">Feedbacks</a></li>
						<li class="col-xs-6"><a href="#loc" data-toggle="tab">Location Requests</a></li>
					</ul>-->
			
					<form action="PendingArticlesServlet" method="post">
					<!--  	<div class="tab-content">
							<div class="tab-pane" id="feedback">-->
								<button type="submit" class="pull-right btn btn-primary btn-sm"value="Refresh" id="btn" style="margin-left:5px;">Refresh</button>
								<table class="table table-striped" cellspacing="0" width="100%" id="feedbackTable">				
									<thead>
										<tr>
											<th></th>
											<th>Date Published:</th>
											<th>Article Title:</th>	
											<th>Author:</th>
											<th>Status:</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="feedbackTable">
										<c:forEach items="${fbList}" var="feedback">
											<tr>
												<td>
													<ul><li></li></ul>
												</td>
													
												<td>
													${feedback.articleDate}
												</td>
												<td>
													${feedback.title}
												</td>
													
												<td>
													${feedback.articleUser}
												</td>
												<td>
													${feedback.approved}
												</td>
												<td>
													<a type="submit" href="TCDisplaySelectedArticle?id=${feedback.articleID }" id="" name="article-content-id"><u>Read</u></a>
												</td>	
											</tr>
										</c:forEach>
							   		 </tbody>
							   </table>
				<!-- 			</div>-->
							
							
							
							
							
							
						<!--  	<div class="tab-pane" id="loc">
								<button type="submit" class="pull-right btn btn-primary btn-sm"value="Refresh" id="btn" style="margin-left:5px;">Refresh</button>
								<table class="table table-striped" cellspacing="0" width="100%" id="locTable">				
									<thead>
										<tr>
											<th></th>
											<th>Date Published:</th>
											<th>Article Title:</th>	
											<th>Author:</th>
											<th>Status:</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="locTable">
										<c:forEach items="${luList}" var="loc">
											<tr>
												<td>
													<ul><li></li></ul>
												</td>
													
												<td>
													${loc.articleDate}
												</td>
												<td>
													${loc.title}
												</td>
													
												<td>
													${loc.articleUser}
												</td>
												
												<td>
													${loc.approved}
												</td>
												
												<td>
													<a type="submit" href="TCDisplaySelectedArticle?id=${loc.articleID }" id="" name="article-content-id"><u>Read</u></a>
												</td>	
											</tr>
										</c:forEach>
							   		 </tbody>
							   </table>
							</div>
							
							
						</div>-->
					</form>		
			</div>
			<br/>
		</div>
		
	</jsp:attribute>
</t:master>


<script>
	$(document).ready(function() {
		$('#feedbackTable').dataTable();
		$('#locTable').dataTable();
	} );

</script>