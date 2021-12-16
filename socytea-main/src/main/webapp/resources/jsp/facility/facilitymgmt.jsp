<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible" content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight" content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title"/></title>
  <jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>
<body>
<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<jsp:include page="../common/menuBar.jsp"></jsp:include>
			<section id="content">
			<div id="breadcrumbs-wrapper">
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">
									<s:text name="Text.facility" />							
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.util.configmgmt"/></a></li>
									<li class="active"><s:text name="Text.facility" /></li>  
								</ol>
								<div class="right">								
								 <button id="createGrpbtn" type="button"  onclick="createfacility()" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Add New Facility"><i class="<s:text name="button.icon.addcard"/>"> </i><s:text name="Text.newfacility"/></button>								
								</div>
							</div>
						</div>
					</div>
			</div>
					<div class="container">
					<jsp:include page="../common/Alert.jsp" flush="true" />
	 				<div class="clear height5px" style="clear: both;"></div>
					<div class="row">
						<div class="col s12 m12 l12">
							<div id="table-datatables">
									<table id="example" class="responsive-table display hoverable" cellspacing="0" width="100%">
										<thead>
											<tr class="<s:text name="datatable.heading.bgcolor"/>">
												<th><s:text name="Text.table.sno"></s:text></th>
												<th><s:text name="Text.facilityimage"></s:text></th>
												<th><s:text name="Text.facilityname"></s:text></th>
												<th><s:text name="Text.adduser.town.ship.id"></s:text></th>
												<th><s:text name="Text.adduser.society.id"></s:text></th>
												<th><s:text name="Text.facilityPlace"></s:text></th>
												<th><s:text name="Text.action"></s:text></th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
						</div>
					</div>
				</div>			
			</section>
		</div>
		<!-- END WRAPPER -->
	</div>
	<!-- END MAIN -->
	<s:form method="post" id="editfunction" name="editfunction">
	<s:hidden name="facilityId" id="facilityid"></s:hidden>
	<s:hidden name="activeKey" id="facilityKey"></s:hidden>
	</s:form>
	<s:form method="post" id="faciltyForm"></s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<jsp:include page="../common/clorbox.jsp"></jsp:include>
<script type="text/javascript">	
	var oTable;
	$(document).ready(function() {
		createoTable();
	});
	 function createfacility(){
		$("#faciltyForm").attr("action", "createfacility");
		$("#faciltyForm").submit();
	}	 
		 
	function createoTable() {
		oTable = $('#example').DataTable({
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,
					"serverMethod" : "POST",
					"scrollAutoCss" : true,
					"ajaxSource" :  "facilitymgmttbl",
					"sorting" : [],
					"paginationType" : "full_numbers",
							"columnDefs" : [ {
								"sortable" : false,
								"targets" : [ 0, 1, 2, 3 ]
							} ],
					"fnDrawCallback" : function(oSettings) {
								$('.tooltipped').tooltip("remove");
								$('.tooltipped').tooltip({delay : 10});
								tohideLoadingImgoverlay();
							}
					});
	}

	 function editfactionaction(id,status,facilit){
		 var statusval = "";
		 statusval = "active";
		 if( status!="0"){
			  $("#facilityid").val(id);
			  $("#facilityKey").val(facilit);
				$("#editfunction").attr("action","editfacility");
				$("#editfunction").submit();
		 }else{
			 swal("Deactive !", "Facility has not been " + statusval + ".", "error");
		 }
	}
	 	function deleteaction(id, status) {	
		var statusval = "";
		statusval = "deactivate";
		swal({
			title : "Are you sure want to " + statusval + "?",
			text : "You will not be able to see  this Facility!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes",
			closeOnConfirm : false
		}, function() {
			toShowLoadingImgoverlay();
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'deactivefacility',
				data : 'facilityId=' + id + '&status=0',
				success : function(data) {
					tohideLoadingImgoverlay();
					$("#idcardid_" + id).attr("onclick", "activeaction('" + id + "','"+status+"')");
					$("#ideditid_" + id).attr("onclick", "editfactionaction('" + id + "','0')");
					$("#idcardi_deacticon_" + id).show();
					$("#idcardi_activeicon_" + id).hide();						
					$("#statusval_" + id).html("Deactive");
					swal("Deactivate!", "Facility has been " + statusval + ".", "success");
				},
				error : function(d) {
					tohideLoadingImgoverlay();
					swal("Deactivate!", "Facility has not been " + statusval + ".", "error");
				}
			});
		});
	}
	 	 function activeaction(id, status) {
		var statusval = "";
		statusval = "active";
		swal({
			title : "Are you sure want to " + statusval + "?",
			text : "You will be able to see this facility!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes",
			closeOnConfirm : false
		}, function() {
			toShowLoadingImgoverlay();
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'deactivefacility',
				data : 'facilityId=' + id + '&status=1',
				success : function(data) {
					tohideLoadingImgoverlay();
					$("#categoryid_" + id).attr("onclick", "deleteaction('" + id + "','"+status+"')");
					$("#ideditid_" + id).attr("onclick", "editfactionaction('" + id + "','1')");
					$("#idcardi_deacticon_" + id).hide();
					$("#idcardi_activeicon_" + id).show();
					$("#statusval_" + id).html("Active");
					swal("Active!", "Facility has been " + statusval + ".", "success");
				},
				error : function(d) {
					tohideLoadingImgoverlay();
					swal("Active!", "Facility has not been " + statusval + ".", "error");
				}
			});
		});
	} 
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>