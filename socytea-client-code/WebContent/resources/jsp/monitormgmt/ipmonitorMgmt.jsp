<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><s:text name='Text.project.title' /></title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="<s:text name='meta.contentType' />">
<meta name="viewport" content="<s:text name='meta.viewport' />">
<meta http-equiv="X-UA-Compatible" content="<s:text name='meta.X-UA-Compatible' />">
<meta name="msapplication-tap-highlight" content="<s:text name='meta.msapplication-tap-highlight' />">
<meta name="description" content="<s:text name='meta.description' />">
<meta name="keywords" content="<s:text name='meta.keywords' />">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>
<body>
	<div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
 	</div>
  	<jsp:include page="../common/header.jsp"></jsp:include>
	<div id="main">
		<div class="wrapper">
			<jsp:include page="../common/menuBar.jsp"></jsp:include>
			<section id="content">
				<div id="breadcrumbs-wrapper">
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">
									<s:text name="user.monitoring.iptracking" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="ipTrack"><s:text name="user.monitoring.Headmenu.iptracking" /></a></li>
									<li class="active"><s:text name="user.monitoring.iptracking" /></li>
								</ol>
								<div class="right"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<jsp:include page="../common/Alert.jsp" flush="true" />
					<div class="row">
						<div>
							<form>
								<div class="col s3">
									<div class="form-group">
										<select class="form-control gendervalidate" id="searchfield"
											name="searchfield">
											<option value="">Select Field</option>
											<option value="1">All</option>
											<option value="2">Name</option>
											<option value="3">IP Address</option>
											<option value="4">Access Count</option>
											<!-- <option value="5">Language</option> -->
										</select>
									</div>
								</div>
								<div class="col s3">
									<div class="form-group">
										<s:textfield name="usersearchname" id="usersearchname"
											cssClass="form-control input-sm" placeholder="Search Word" />
									</div>
								</div>
								<div class="col s2">									
										<button id="addaccountbuttonid"
											class="<s:text name="button.color.filter"/>"
											onclick="filter();" data-toggle="modal" data-position="right"
											data-delay="10" data-tooltip="Click to search"
											style="margin-top: 12px;" type="button">
											<i class="mdi-action-search "></i>Filter
										</button>								
								</div>

							</form>
						</div>
					</div>
					<div class="row">
						<div id="table-datatables">							
								<div style="height: 10px;"></div>
								<div class="col s12">
									<table id="example" class="responsive-table display hoverable" cellspacing="0" width="100%">
										<thead>
											<tr class="<s:text name="datatable.heading.bgcolor"/>">
												<th><s:text name="Text.id"></s:text></th>
												<th><s:text name="Text.adduser.username"></s:text></th>
												<th><s:text name="Text.ipaddress"></s:text></th>
												<th><s:text name="Text.Accesscount.accessinfo"></s:text></th>
												<!--  <th><s:text name="Text.Accesscount.accesslanguage"></s:text></th>-->
												<th><s:text name="Text.Access.clientTime"></s:text></th>
												<th><s:text name="Text.Access.GmtTime"></s:text></th>
												<th><s:text name="Text.Access.LastModify"></s:text></th>
											</tr>
										</thead>
									</table>
								</div>						
						</div>

					</div>
					<br>
				<div class="divider"></div>
				</div>
			</section>
		</div>
		<!-- END WRAPPER -->
	</div>
	<!-- END MAIN -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		createoTable();			
	}); 
	function createoTable() {
		oTable = $('#example').DataTable({
			"jQueryUI" : false,
			"processing" : true,
			"serverSide" : true,
			"displayLength" : 10,
			"serverMethod": "POST",				
			"ajaxSource" : "ipAccessMgmt",					
			"sorting" : [],
			"paginationType" : "full_numbers",
			"columnDefs" : [ {
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3, 4,5,6]
					} ],
			"fnDrawCallback": function( oSettings ) {
					$('.tooltipped').tooltip("remove");
					$('.tooltipped').tooltip({delay: 10});
					$('select').material_select();
			}
			});		
	}	
	
	/* function filter(){
		 var searchfield=$('#searchfield').val();
		var usersearchname=$('#usersearchname').val();		
		if (oTable != undefined) {
			oTable.destroy();				
		}else{
			oTable.destroy();		
		} 
	 	oTable = $('#example').DataTable(
				{
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,
					"serverMethod": "POST",							
					"sAjaxSource" :"ipAccessMgmt?searchfield="+ searchfield+"&usersearchname="+usersearchname+"&searchval=1",					
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [{						
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3 , 4,5,6,7]
					}]
				});
	}	 */	
	function filter(){
		 var searchfield=$('#searchfield').val();
		var usersearchname=$('#usersearchname').val();
		if (oTable != undefined) {
			oTable.destroy();
		}else{
			oTable.destroy();
		}
	 	oTable = $('#example').DataTable({
	 				"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,
					"serverMethod": "POST",
					"sAjaxSource" :"ipAccessMgmt?searchfield="+ searchfield+"&usersearchname="+usersearchname+"&searchval=1",
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [{
						"sortable" : false,
						"targets" : [0,1,2,3,4,5,6]
					}],
					"fnDrawCallback": function( oSettings ) {
						$('.tooltipped').tooltip("remove");
						$('.tooltipped').tooltip({delay: 10});
						$('select').material_select();
				}
				});
		
	}

</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body></html>	