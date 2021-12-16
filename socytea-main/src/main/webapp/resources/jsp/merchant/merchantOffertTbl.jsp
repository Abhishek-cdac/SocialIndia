<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!--================================================================================
	Item Name: Materialize - Material Design Admin Template
	Version: 3.1
	Author: GeeksLabs
	Author URL: http://www.themeforest.net/user/geekslabs
================================================================================ -->
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
<!-- CORE CSS-->
<jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>
<body>
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<!-- End Page Loading -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<jsp:include page="../common/menuBar.jsp"></jsp:include>
			<!-- //////////////////////////////////////////////////////////////////////////// -->
			<!-- START CONTENT -->
			<section id="content"> <!--breadcrumbs start-->
			<div id="breadcrumbs-wrapper">
				<!-- Search for small screen -->
            	<jsp:include page="../common/searchexploremob.jsp"></jsp:include>	
				<div class="container">
					<div class="row">
						<div class="col s12 m12 l12">
							<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.merchantoff" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Dashboard" /></a></li>
								<li><a href="merchantMgmtTbl"><s:text name="Breadcrumb.manage" /></a></li>
								<li><a href="merchantMgmtTbl"><s:text name="Text.Merchant.Management" /></a></li>
								<li class="active"><s:text name="Breadcrumb.manage.merchantoff" /></li>
							</ol>
							<div class="right">
							<form method="post" action="createMerchantoffer" style="cursor: pointer;padding: 0 3px 0 0;float:left;">
							<button class="<s:text name="button.color.new.create"/>" data-tooltip="Post New Offer"
								 data-toggle="modal" type="submit" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" >
								<i class="mdi-maps-local-offer left"></i><s:text name="Text.Title.MrchantnewOffer" />
							</button>
							<s:hidden id="mrchntId" name="merchantobj.mrchntId" value="%{merchantobj.mrchntId}"></s:hidden>
								</form>
								<button id="gobckbtnid" class="<s:text name="button.color.cancel"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--breadcrumbs end--> <!--start container-->
			<div class="container"></div>
			<!--end container--> </section>

			<jsp:include page="../common/Alert.jsp" flush="true" />
			<div style="clear: both;"></div>
			<div class="col s12 m4 l3">
				<!-- Search inputs -->
			</div>
			<div id="table-datatables">
				<div class="row">
					<div style="height: 10px;"></div>
					<div class="col s12">
						<table id="example" class="display hoverable" cellspacing="0" width="100%">
							<thead>
<tr class="<s:text name="datatable.heading.bgcolor"/>">
<th><s:text name="Text.table.sno" /></th>
<th><s:text name="Text.Title.MrchantOfferImg" /></th>
<th><s:text name="Text.Title.MrchantOfferName" /></th>
<th><s:text name="Text.Title.BroucherName"/></th>
<th><s:text name="Text.Title.MrchantPrice"/></th>
<th><s:text name="Text.table.action"/></th>
</tr>
</thead>

						</table>
					</div>
				</div>
			</div>
			<br>
			<div class="divider"></div>
<s:form method="post" id="userCancelForm"></s:form>
		</div>
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
	
	<s:form method="post" id="merchantdetail">
				<s:hidden name="merchantUtilobj.merchantUtilId" id="merchantUtilId"></s:hidden>
				<s:hidden name="merchantobj.mrchntId" value="%{merchantobj.mrchntId}"></s:hidden>
			</s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include> 
		<jsp:include page="../common/clorbox.jsp"></jsp:include> 
		<script type="text/javascript">
		var oTable;
		$(document).ready(function() {
			createoTable();
		});
		function createoTable() {
			var merid=$("#mrchntId").val();
			oTable = $('#example')
					.DataTable(
							{
								"jQueryUI" : false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"ajaxSource" : "merchantOfferTbldata?mrchntUniqId="+merid,
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3, 4 ,5]
								} ],
								"fnDrawCallback": function( oSettings ) {
									 $('.tooltipped').tooltip({delay: 10});
								      //alert( 'DataTables has redrawn the table' );
								    }
							});
		} 
		function editMerchantDetail(merutilid){
			$("#merchantUtilId").val(merutilid);
			$("#merchantdetail").attr("action","editMerchantUtilDetail");
			$("#merchantdetail").submit();
		}
		function deleteMerchantDetail(merutilid){
			swal({    title: "Are you sure want to delete?",
	            text: "You will not be able to recover this merchant offer!",   
	            type: "warning",   
	            showCancelButton: true,   
	            confirmButtonColor: "#DD6B55",   
	            confirmButtonText: "Yes",   
	            closeOnConfirm: false }, 
	            function(){
			$("#merchantUtilId").val(merutilid);
			$("#merchantdetail").attr("action","deleteMerchantUtilDetail");
			$("#merchantdetail").submit();
	            });
		}
		$(document).ready(function(){
			$("#gobckbtnid").click(function(){
				$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
				toShowLoadingImgoverlay();
				$("#userCancelForm").attr("action", "merchantMgmtTbl");
				$("#userCancelForm").submit();
			});	   
		});
	</script>
	
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>
