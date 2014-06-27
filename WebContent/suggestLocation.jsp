<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:master>
	<jsp:attribute name="cssImports">
		<!-- Import CSS here -->
		<!-- DataTable -->
   		<link rel="stylesheet" type="text/css" href="css/dataTable/jquery.dataTables_themeroller.css" />
   		<link rel="stylesheet" type="text/css" href="css/dataTable/demo_page.css" />
   		<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.4.custom.css" />
   		
		<style>
			table {
				margin-top: 80px;
			}
		</style>
	</jsp:attribute>
	<jsp:attribute name="jsImports">
		<!-- Import JS here -->
		<script type='text/JavaScript' src='js/oneMap.js'></script>
   		<!-- DataTable -->
   		<script type="text/javascript" language="javascript" src="js/dataTable/dataTables.js"></script>
   		<script type="text/javascript" language="javascript" src="js/dataTable/numberSort.js"></script>
   		<script type="text/JavaScript">
	   		var oTable;
	   		$(document).ready(function(){
	   			oTable = $('#example').dataTable();
		   		var OneMap = new GetOneMap('divMain','SM'); 
				$("#overlayTheme").click(function(){
					var themeName = $("#txtTheme").val();
					$.ajax({
						url: "http://www.onemap.sg/API/services.svc/mashupData?token=xkg8VRu6Ol+gMH+SUamkRIEB7fKzhwMvfMo/2U8UJcFhdvR4yN1GutmUIA3A6r3LDhot215OVVkZvNRzjl28TNUZgYFSswOi&themeName="+themeName,
						type: "GET",
						dataType: "jsonp",
				        success: function(data){
				         	var size = data.SrchResults[0].FeatCount;
					   		var searchResults = new Array();
				           	for(var i=1;i<size;i++){
				           		var name = data.SrchResults[i].NAME.toString();
				           		var postalCode = data.SrchResults[i].ADDRESSPOSTALCODE.toString();
				           		var streetName = data.SrchResults[i].ADDRESSSTREETNAME.toString();
				           		var hyperlink = data.SrchResults[i].HYPERLINK.toString();
				           		var xy = data.SrchResults[i].XY.toString();
				           		var iconName = data.SrchResults[i].ICON_NAME.toString();
				           		oTable.fnAddData([
									name,
									postalCode,
				           		    streetName,
				           		    hyperlink,
				           		    xy,
				           		 	iconName
								]);
				           		//alert(data.SrchResults[1].Name);
				           	}
							
				           	//Name, address postal code, address street name, HYPERLINK, XY, ICON_NAME
				        },
				        error: function(req, status, ex) {
				  	      alert(req[0] + status + ex);
				        }
					});
				});
	   		});
		</script>
	</jsp:attribute>

	<jsp:attribute name="content">
		<table>
			<tr>
				<td colspan="3">
		       		Mashup data
		       	</td>
		  	</tr>
		  	<tr>
		   		<td>Enter theme name: (hotels)</td>
				<td><input type="text" id="txtTheme" value="Museum"style="width: 250px" /></td>
		  	</tr>  
		  	<tr>
		   		<td id="txt"></td>
		   		<td><input type="button" value="MashUp Theme" id="overlayTheme" /></td>
		  	</tr>
		</table>
		<br />
		<div id="divMain" style='width: 800px; height: 530px;'></div>
		<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
		  	<thead>
				<tr>
		         	<th>Name</th>
		            <th>Postal Code</th>
		            <th>Street Name</th>
		            <th>Hyperlink</th>
		            <th>X/Y Coordinates</th>
		            <th>Icon Name</th>
		      	 </tr>
		    </thead>
			<tbody></tbody>
	        <tfoot>
				<tr>
		         	<th>Name</th>
		            <th>Postal Code</th>
		            <th>Street Name</th>
		            <th>Hyperlink</th>
		            <th>X/Y Coordinates</th>
		            <th>Icon Name</th>
		      	 </tr>
	        </tfoot>
	    </table>
	</jsp:attribute>
</t:master>