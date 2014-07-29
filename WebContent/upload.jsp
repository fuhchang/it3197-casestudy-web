<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script src="https://maps.googleapis.com/maps/api/js"></script>
		<script type="text/javascript"
			src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<style>
#map_canvas {
	width: 300px;
	height: 300px;
	background-color: #CCC;
}
</style>
		<script type="text/javascript">
			

			function imageSelector() {
				var path;
				$("#imgSelector").On('Click', function() {
					path = $("#imgSelector").text();
					alert(path);
				});
			}

			function readURL(input) {

				if (input.files && input.files[0]) {
					var reader = new FileReader();
						
					reader.onload = function(e) {
						$('#blah').attr('src', e.target.result);
					}

					reader.readAsDataURL(input.files[0]);
					
				}
			}

			$("#imgInp").change(function() {
				readURL(this);
			});
		</script>
	
		<style>
#mapCanvas {
	width: 100%;
	height: 300px;
}
</style>
	</jsp:attribute>

	<jsp:attribute name="content">
	<br>	
			<form action="UploadServlet" method="post" enctype="multipart/form-data">
			
			<div class="panel-heading">
				<h2>Upload Group Image Form</h2>
			</div>
				<div class="panel-body">
				<div class="input-group">
					<input type="hidden" name="grpID"value="${grpID}"/>
					<input type='file' id="imgInp" name="imgFile"/>
   					 <img id="blah" src="#" alt="your image" height="100" width="100"/>
   					<br /> <input type="submit" value="Upload" />
				</div>
				</div>
		</form>
		
		
	</jsp:attribute>
</t:master>