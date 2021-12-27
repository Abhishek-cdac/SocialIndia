<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title><s:text name="Text.Title" /></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>
<body>
<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div><jsp:include page="../common/header.jsp"></jsp:include>
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
									<s:text name="Breadcrumb.util.committeerolemgmt" />					
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.util.configmgmt"/></a></li>
									<li class="active"><s:text name="Breadcrumb.util.committeerolemgmt" /></li>  
								</ol>
								<div class="right">								
								 <button style="width:275px;" id="createGrpbtn" type="button"  onclick="createrole();" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Add New Role"><i class="<s:text name="button.icon.addcard"/>"> </i><s:text name="button.label.addrole"/></button>								
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
													<th><s:text name="Breadcrumb.util.committeerolemgmt"></s:text></th>
													<th><s:text name="Text.status"></s:text></th>
													<th>Action</th>
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
	<s:form method="post" id="createNewcommitteeid" name="createNewcommitteeid">
		<s:hidden name="iVOcommitteename" id="iVOcommitteename"></s:hidden>
	</s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<script type="text/javascript">
	function createrole() {
		swal({
			title : "<s:text name="text.create.committeerole" />",   
			type : "input",
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Enter New Role"
		}, function(inputValue) {
			if (inputValue === false){return false;}
			if (inputValue === "") {
				swal.showInputError("Enter New Role");
				return false;
			}
			if(inputValue.length  >=2 && inputValue.length  < 50){
             	var letters = /^[0-9a-zA-Z\ ]+$/;  
             	if(inputValue.match(letters)){
             		$("#iVOcommitteename").val(inputValue);
        			$("#createNewcommitteeid").attr("action", "createcommitteeroletype");
        			$("#createNewcommitteeid").submit();       				
             	}else {
             		swal.showInputError("Role should not allow special character.");     
                     return false;
             	}
             } else {
             	swal.showInputError("Role minimum 2 to maximum 50 characters.");     
                 return false;
             }

		});
	}
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
								"serverMethod" : "POST",
								"scrollAutoCss" : true,
								"ajaxSource" : "committeerolemanagedatatable",
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3 ]
								} ],
								"drawCallback" : function(oSettings) {
									$('.tooltipped').tooltip("remove");
									$('.tooltipped').tooltip({delay : 10});
									tohideLoadingImgoverlay();
								}
							});
		}
		
   function deleteaction(id, status) {
		var statusval = "";
		statusval = "deactivate";
		swal({
			title : "Are you sure want to " + statusval + "?",
			text : "You will not be able to see this role!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes",
			closeOnConfirm : false
		}, function() {			
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'deleteactioncommitteerole',
				data : 'uniqueId=' + id + '&statusflg=' + status,
				success : function(data) {
					$("#committid_" + id).attr("onclick", "activeaction('" + id + "','Active')");
					$("#committid_deacticon_" + id).show();
					$("#committid_activeicon_" + id).hide();						
					$("#statusval_" + id).html("Deactive");
					swal("Deactivate!", "Role has been " + statusval + ".", "success");
				},
				error : function(d) {					
					swal("Deactivate!", "Role has not been " + statusval + ".", "error");
				}
			});
		});
	}
	function activeaction(id, status) {
		var statusval = "";
		statusval = "active";
		swal({
			title : "Are you sure want to " + statusval + "?",
			text : "You will be able to see this role!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes",
			closeOnConfirm : false
		}, function() {			
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'activeactioncommitteerole',
				data : 'uniqueId=' + id + '&statusflg=' + status,
				success : function(data) {					
					$("#committid_" + id).attr("onclick", "deleteaction('" + id + "','Deactive')");
					$("#committid_deacticon_" + id).hide();
					$("#committid_activeicon_" + id).show();
					$("#statusval_" + id).html("Active");
					swal("Active!", "Role has been " + statusval + ".", "success");
				},
				error : function(d) {					
					swal("Active!", "Role has not been " + statusval + ".", "error");
				}
			});
		});
	}
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>	