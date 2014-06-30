<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<style>
			.floatLeftText{
				float:left;
			}
			#currentForm{
				margin-top:25px;
			}
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script type="text/javascript" src="https://www.dropbox.com/static/api/1/dropins.js" id="dropboxjs" data-app-key="cqvf3nim3klslqb"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('#selected-image').hide();
		        var button = Dropbox.createChooseButton({
		            success: function(files) {
		            	$('#selected-image').attr("src",files[0].link).show();
		            },
		            linkType: 'direct',
		            extensions: ['.jpg', '.jpeg', '.png'],
		        });
		        document.getElementById('container').appendChild(button);
		        $("#submitBtn").click(function(e){
		        	var eventName = $(".form-control").eq(0).val();
		        	var description = $(".form-control").eq(2).val();
		        	var location = $(".form-control").eq(3).val();
		        	var noOfParticipants = $(".form-control").eq(4).val();
		        	if(eventName == ""){
		        		$(".form-group").eq(1).html("<span class='floatLeftText'><label class='col-xs-12 control-label' for='inputError1'> Name</label></span><input class='form-control' type='text' name='eventName' id='inputError1' placeholder='Enter event name'/>").addClass("has-error");
		        		e.preventDefault();
		        	}
		        	if(description == ""){
		        		$(".form-group").eq(3).html("<span class='floatLeftText'><label class='col-xs-12 control-label' for='inputError1'>Description</label></span><textarea class='form-control' rows='3' placeholder='Enter event description' id='inputError1'></textarea>").addClass("has-error");
		        		e.preventDefault();
		        	}
		        	if(location == ""){
		        		$(".form-group").eq(4).html("<span class='floatLeftText'><label class='col-xs-12 control-label'>Location</label></span><textarea class='form-control' rows='3' placeholder='Enter event location'></textarea>").addClass("has-error");
		        		e.preventDefault();
		        	}
		        	/*if((noOfParticipants == "") || (noOfParticipants == Nan)){
		        		$(".form-group").eq(5).html("<span class='floatLeftText'><label class='col-xs-12 control-label'>No. of Participants </label></span><input class='form-control' type='number' name='gName' placeholder='Enter number of participants (0-999999)' min='0' max='999999'/>").addClass("has-error");
		        		e.preventDefault();
		        	}*/
		        });
			});
		</script>
	</jsp:attribute>
	
	<jsp:attribute name="content">
		<form method="post" action="createEventStep2.jsp">
			<div id="currentForm" class="form-horizontal" role="form">
				<div class="panel panel-primary">
				<div class="panel-heading">
					Create Event
					<br/>
					Step 1: Please fill in the event details
				</div>
					<div class="panel-body">
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Poster</label></span>
							<br/>
							<br/>
							<input type="image" id="selected-image" name="image_url" width="400" height="200"/>
							<div id="container"></div>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label"> Name</label></span>
							<input class="form-control" type="text" name="eventName" placeholder="Enter event name"/>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Category</label></span>
							<select class="form-control" id="gType">
								<option> Arts </option>
								<option> Education</option>
								<option> Family </option>
								<option> Health </option>
							</select>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Description</label></span>
							<textarea class="form-control" rows="3" placeholder="Enter event description"></textarea>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">Location</label></span>
							<textarea class="form-control" rows="3" placeholder="Enter event location"></textarea>
							<br/>
							<a href="suggestLocation.jsp"><button type="submit" class="btn btn-default"> Suggest Location </button></a>
						</div>
						<div class="form-group">
							<span class="floatLeftText"><label class="col-xs-12 control-label">No. of Participants </label></span>
							<input class="form-control" type="number" name="gName" placeholder="Enter number of participants (0-999999)" min="0" max="999999"/>
						</div>
						<div class="form-group">
							<a href="event.jsp"><button type="submit" class="btn btn-default"> Cancel </button></a>
							<input type="Submit" name="submit" id="submitBtn" value="Next" class="btn btn-default"/>
						</div>
					</div>
				</div>
			</div>
		</form>
	</jsp:attribute>
</t:master>