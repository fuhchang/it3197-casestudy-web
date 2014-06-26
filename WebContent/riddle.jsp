<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports"></jsp:attribute>
	<jsp:attribute name="jsImports"></jsp:attribute>
	
	<jsp:attribute name="content">
		<h1>Riddle</h1>
		
		<div class="panel-group" id="accordion" style="padding-top:2%">
			<div class="panel panel-default">
				<a data-toggle="collapse" data-parent="#accordion" href="#riddle" style="a:none;color:white;">
					<div class="panel-heading" style="background-color:grey">
						<h3 class="panel-title">
							<strong>Title</strong>
						</h3>
					</div>
				</a>
				<div id="riddle" class="panel-collapse collapse in">
					<div class="panel-body">
						Riddle
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<a data-toggle="collapse" data-parent="#accordion" href="#riddle1" style="a:none;color:white;">
					<div class="panel-heading" style="background-color:grey">
						<h3 class="panel-title">
							<strong>Title</strong>
						</h3>
					</div>
				</a>
				<div id="riddle1" class="panel-collapse collapse in">
					<div class="panel-body">
						Riddle
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</t:master>