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
									<s:text name="Breadcrumb.util.bookingmgmt" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.utility" /></a></li>
									<li class="active"><s:text name="Breadcrumb.util.bookingmgmt" /></li>
								</ol>
								<%-- <div class="right">								
								 <button style="width:275px;" id="createGrpbtn" type="button"  onclick="createNewcateg();" class=" <s:text name="button.color.new.create"/> tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Add New Category"><i class="<s:text name="button.icon.addcard"/>"> </i><s:text name="button.icon.addcatgry.label"/></button>								
								</div> --%>
								
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
													<th>Name</th>
													<th><s:text name="Text.table.bookingmobno"></s:text></th>
													<th><s:text name="Text.facilityname"></s:text></th>
													<th><s:text name="Text.table.bookingplace"></s:text></th>
													<th><s:text name="Text.table.bookingstarttime"></s:text></th>
													<th><s:text name="Text.table.bookingendtime"></s:text></th>
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
	<s:form method="post" id="createbookingid" name="createbookingid">
		<s:hidden name="iVOCategoryname" id="iVOmerchantCategoryname"></s:hidden>
		<s:hidden name="iVObookingid" id="iVObookingid"></s:hidden>
		<%--  <s:hidden name="committeecomments" id="iVOcommitteecomments" class="form-control " /> --%>
	</s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<script type="text/javascript">

	 function createmrchntcate(){
		$("#createbookingid").attr("action", "createmrchbookingtype");
		$("#createbookingid").submit();
	}
		var oTable;
		$(document).ready(function() {
			createoTable();
			$("#comment").keyup(function(){
				alert("msss");
				textValidate(this,'100','AI');
			});
		});
		function createoTable() {
			oTable = $('#example').DataTable({
								"jQueryUI" : false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"serverMethod" : "POST",
								"scrollAutoCss" : true,
								"ajaxSource" : "bookingmanagedatatable",
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3,4,5,6 ]
								} ],
								"drawCallback" : function(oSettings) {
									$('.tooltipped').tooltip("remove");
									$('.tooltipped').tooltip({delay : 10});
									tohideLoadingImgoverlay();
								}
							});
		}
		
		
		function viewbookingaction(id)
		{
			$("#iVObookingid").val(id);
			$("#createbookingid").attr("action","bookingmstview");
			$("#createbookingid").submit();
		}
		
   function approvalbookingaction(id) {
		var statusval = "";
		statusval = "Approve";
		swal({
			title : "Comments",   
			 text: "<textarea id='comment' class='form-controll' maxlength='100'></textarea>",
			 html: true,
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Enter Comments"
		}, function(inputValue) {
			if (inputValue === false)
				return false;
			if (inputValue === "") {
				swal.showInputError("Enter Comments");
				return false;
			}
			 var val = document.getElementById('comment').value;
			$("#iVOcommitteecomments").val(val);
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'bookingapprovalaction',
				data : 'uniqueId=' + id+'&committeecomments='+val ,
				success : function(data) {
					tohideLoadingImgoverlay();
					
					$("#bookingappr_" + id).hide();
					$("#idcardiddecl_" + id).hide();
				
					$("#statusid_" + id).show();
					$("#statusid_" + id).html("Approved");
					swal(statusval + "!", " booking has been " + statusval + ".", "success");
				},
				error : function(d) {
					tohideLoadingImgoverlay();
					swal(statusval + "!", " booking has not been " + statusval + ".", "error");
				}
			});
		});
	}
	function declinebookingaction(id) {
		var statusval = "";
		statusval = "Decline";
		swal({
			title : "Comments",   
			 text: "<textarea id='comment' class='form-controll' maxlength='100'></textarea>",
			 html: true,
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Enter Comments"
		}, function(inputValue) {
			if (inputValue === false)
				return false;
			if (inputValue === "") {
				swal.showInputError("Enter Comments");
				return false;
			}
			 var val = document.getElementById('comment').value;
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'bookingdeclineaction',
				data : 'uniqueId=' + id+'&committeecomments='+val ,
				success : function(data) {
					tohideLoadingImgoverlay();
					$("#bookingappr_" + id).hide();
					$("#idcardiddecl_" + id).hide();
					$("#statusid_" + id).show();
					$("#statusid_" + id).html("Declined");
					swal(statusval + "!", " booking has been " + statusval + ".", "success");
				},
				error : function(d) {
					tohideLoadingImgoverlay();
					swal(statusval + "!", " booking has not been " + statusval + ".", "error");
				}
			});
		});
	}
	$(document).ready(function() {
		
		$("#comment").keyup(function(){
			alert("msss");
			textValidate(this,'100','AI');
		});
	});
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>

