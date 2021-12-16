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
				<div class="header-search-wrapper grey hide-on-large-only">
					<i class="mdi-action-search active"></i> <input type="text"
						name="Search" class="header-search-input z-depth-2"
						placeholder="Explore Materialize">
				</div>
				<div class="container">
					<div class="row">
						<div class="col s12 m12 l12">
							<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.util.disputetomercht" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Dashboard" /></a></li>
								<li><a href="#"><s:text name="Breadcrumb.disputemgmt.disputemgmt" /></a></li>
								<li class="active"><s:text name="Breadcrumb.util.disputetomercht" /></li>

							</ol>
				<s:if test="#session.GROUPCODE==5 ||#session.GROUPCODE.equalsIgnoreCase('5')" >
				<!-- DOubt Dont delete this Start -->
					<!-- <div class="right">
                      <a href="tomerchantcmpltcreationaction"><button class="<s:text name="button.color.new.create"/> " data-tooltip=" Create New Compliant" data-delay="10" data-position="bottom" style="width: 228px;" type="button" data-tooltip-id="bf9615fd-0551-45fd-61bb-34647624e5d6"><i class="mdi-social-domain  left"> </i><s:text name="text.newcomplaint"/></button></a>
                	</div>	 -->
                	<!-- DOubt Dont delete this End -->
                </s:if>
						</div>

					</div>

				</div>

			</div>
			<!--breadcrumbs end--> <!--start container-->
			<div class="container"></div>
			<!--end container--> </section>

			<jsp:include page="../common/Alert.jsp" flush="true" />
			<div style="clear: both;"></div>

			<div id="table-datatables" class="padding10px">
				<div class="row">
					<div style="height: 10px;"></div>
					<div class="col s12">
						<table id="example" class="responsive-table display hoverable" cellspacing="0" width="100%">
							<thead>
									<tr class="<s:text name="datatable.heading.bgcolor"/>">
												    <th><s:text name="S.No."></s:text></th>
													<th><s:text name="Text.adduser.townshipname"></s:text> </th>
													<th><s:text name="Text.adduser.society.id"></s:text> </th>
													<th><s:text name="text.residentname"></s:text></th>
													<th><s:text name="text.merchantname"></s:text></th>
													<th><s:text name="text.issue.on"></s:text></th>
													<th><s:text name="Text.table.action"></s:text></th>
												</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<br>
			<div class="divider"></div>

		</div>
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
	<s:hidden id="fromdate_hidd" value=""></s:hidden>
	<s:hidden id="todate_hidd" value=""></s:hidden>
	<s:form method="post" id="delgroupform">
					<s:hidden name="closereasonfield" id="closereasonid"></s:hidden>
					<s:hidden name="emailcommentfield" id="emailcommentid"></s:hidden>
					<s:hidden name="tomerchantidfield" id="tomerchantidEdit"></s:hidden>
					<s:hidden name="disputeuseridfield" id="disputeuseridEdit"></s:hidden>
					<s:hidden name="uniqueId" id="deleteusridEdit"></s:hidden>
			</s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
			createoTable();
		});
		function createoTable() {
			var fromdate = $("#dateFromid").val();
			var todate = $("#dateToid").val();
			oTable = $('#example')
					.DataTable(
							{
								"jQueryUI" : false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"ajaxSource" : "disputeToMerchantTbldata",
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3, 4, 5]
								} ],
								"drawCallback": function( oSettings ) {
									$('.tooltipped').tooltip("remove");
									 $('.tooltipped').tooltip({delay: 10});
								    }
							});
		}

function closecompliant(id){

			swal({   title: "Close Reason",
		        //text: "Create New Group",
		        type: "input",   showCancelButton: true,
		        closeOnConfirm: false,

		        animation: "slide-from-top",
		        inputValue: "" ,
		        inputPlaceholder: "Enter the reason" },
		        function(inputValue){

		            if (inputValue === false) return false;
		            if (inputValue === "") {
		            swal.showInputError("Enter the reason");
		            return false
		            }
		            $("#closereasonid").val(inputValue);
		            $("#deleteusridEdit").val(id);
					$("#delgroupform").attr("action", "closetomerchantcmpltaction");
					$("#delgroupform").submit();
		      // swal("Success", "Reason updated Successfully ", "success");
		   });
		}
		function actionDelete(id)
		{
		swal({    title: "Are you sure?",
            text: "You will not be able to recover this user!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes",
            closeOnConfirm: false },
            function(){
            	$("#deleteusridEdit").val(id);

				$("#delgroupform").attr("action", "deletetomerchantcmpltaction");
				$("#delgroupform").submit();
           // swal("Deleted!", "User has been deleted.", "success");
            });
		}
function compliantsendemail(id,toid,disputeid){

			swal({   title: "Comments",
		        //text: "Create New Group",
		        type: "input",   showCancelButton: true,
		        closeOnConfirm: false,

		        animation: "slide-from-top",
		        inputValue: "" ,
		        inputPlaceholder: "Enter the comment" },
		        function(inputValue){

		            if (inputValue === false) return false;
		            if (inputValue === "") {
		            swal.showInputError("Enter the comment");
		            return false
		            }
		            $("#emailcommentid").val(inputValue);
		            $("#deleteusridEdit").val(id);
		            $("#tomerchantidEdit").val(toid);
		            $("#disputeuseridEdit").val(disputeid);
					$("#delgroupform").attr("action", "sendmailtomerchantcmpltaction");
					$("#delgroupform").submit();
		      // swal("Success", "Reason updated Successfully ", "success");
		   });
		}
function sendsmstoadmin(id,toid,disputeid){
	 swal({   title: "Comments",
        //text: "Create New Group",
        type: "input",   showCancelButton: true,
        closeOnConfirm: false,

        animation: "slide-from-top",
        inputValue: "" ,
        inputPlaceholder: "Enter the comment" },
        function(inputValue){

            if (inputValue === false) return false;
            if (inputValue === "") {
            swal.showInputError("Enter the comment");
            return false
            }
            $("#emailcommentid").val(inputValue);
            $("#deleteusridEdit").val(id);
            $("#tomerchantidEdit").val(toid);
            $("#disputeuseridEdit").val(disputeid);
			$("#delgroupform").attr("action", "sendsmstomerchantcmpltaction");
			$("#delgroupform").submit();
      // swal("Success", "Reason updated Successfully ", "success");
   });

}
	</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/custom-script.js"></script>

</body>
</html>
