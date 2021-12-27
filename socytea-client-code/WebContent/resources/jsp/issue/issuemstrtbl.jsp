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
									<s:text name="Issue List" />							
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.util.configmgmt"/></a></li>
									<li class="active"><s:text name="Issue List" /></li>  
								</ol>
								<div class="right">								
								 <button id="createGrpbtn" type="button"  onclick="createfunction()" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Add New Issue"><i class="<s:text name="button.icon.addcard"/>"> </i><s:text name="Add New Issue"/></button>								
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
												<th><s:text name="Issue List"></s:text></th>
												<th><s:text name="Text.status"></s:text></th>
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
	<s:form method="post" id="editissue" name="editissue">
		<s:hidden name="editissueid" id="editissueid"></s:hidden>
		<s:hidden name="issuelist" id="issuelist"></s:hidden>
		<s:hidden name="updateflg" id="updateflg"></s:hidden>
	</s:form>
	<s:form method="post" id="CreateissueForm" name="CreateissueForm">
	<s:hidden name="createissuelist" id="issuelistcreate"></s:hidden>
	
	</s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
<script type="text/javascript">	
var oTable;
		$(document).ready(function() {
			createoTable();
		});
			function createfunction() {
				swal({
					title : "Add New Issue",
					// text: "Write something interesting:",   
					type : "input",
					showCancelButton : true,
					closeOnConfirm : false,
					animation : "slide-from-top",
					inputPlaceholder : "Enter New Issue"
				}, function(inputValue) {
					if (inputValue === false)
						return false;
					if (inputValue === "") {
						swal.showInputError("Enter New Issue");
						return false;
					}
					 if(inputValue.length  >=5 && inputValue.length  <= 50){
		                 	var letters = /^[0-9a-zA-Z\ ]+$/;  
		                 	if(inputValue.match(letters)){
		                 		$("#issuelistcreate").val(inputValue);
		                 		$("#CreateissueForm").attr("action", "createissue");
		                 		$("#CreateissueForm").submit();
		                 	}else {
		                 		swal.showInputError(" New issue list should not allow special character.");     
		                         return false;
		                 	}
		                 } else {
		                 	swal.showInputError(" New issue list minimum 2 to maximum 50 characters.");     
		                     return false;
		                 }
				
				});
			}
	 
		 
	function createoTable() {
		oTable = $('#example').DataTable({
							"jQueryUI" : false,
							"processing" : true,
							"serverSide" : true,
							"displayLength" : 10,
							"serverMethod" : "POST",
							"scrollAutoCss" : true,
							"ajaxSource" : "issuemgmtbl",
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

	 function editfunctionaction(id,status,issuetxt){
		 var statusval = "";
		 statusval = "active";
		 if( status!="0"){
			 var update="issuelistupdate";
			 swal({   title: "Edit Issue  list",   
	                type: "input",   showCancelButton: true,
	                closeOnConfirm: false,   
	                animation: "slide-from-top", 
	                inputValue: ""+issuetxt , 
	                inputPlaceholder: "Issue List" }, 
	                function(inputValue){   
	                    if (inputValue === false) return false;      
	                    if (inputValue === "") {     
	                    	swal.showInputError("Enter your issue list");     
	                    	return false;  
	                    } 
	                    if(inputValue.length  >=5 && inputValue.length  <= 50){
	                    	var letters = /^[0-9a-zA-Z\ ]+$/;  
	                    	if(inputValue.match(letters)){
	                    		$("#issuelist").val(inputValue);
	                    		$("#editissueid").val(id);
	                    		$("#updateflg").val(update);
	    						$("#editissue").attr("action", "modifylisttxt");
	    						$("#editissue").submit();
	              				// swal("Success", "Group updated Successfully ", "success"); 
	                    	}else {
	                    		swal.showInputError("Issue list should not allow special character.");     
	                            return false;
	                    	}
	                    } else {
	                    	swal.showInputError("Issue list minimum 5 to maximum 50 characters.");     
	                        return false;
	                    }
	           });
		 }else{
			 swal("Deactive!", "Issue has not been " + statusval + ".", "error");
		 }
	}
	 	function deleteaction(id, status,val) {	
		var statusval = "";
		statusval = "deactivate";
		 var update="activestatus";
		swal({
			title : "Are you sure want to " + statusval + "?",
			text : "You will not be able to see this issue list name!",
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
				url : 'modifylisttxt',
				data : 'editissueid=' + id + '&issuelist=0&updateflg='+update,
				success : function(data) {
					tohideLoadingImgoverlay();
					$("#idcardid_" + id).attr("onclick", "activeaction('" + id + "','"+status+"','"+val+"')");
					$("#ideditid_" + id).attr("onclick", "editfunctionaction('" + id + "','0','"+val+"')");
					$("#idcardi_deacticon_" + id).show();
					$("#idcardi_activeicon_" + id).hide();						
					$("#statusval_" + id).html("Deactive");
					swal("Deactivate!", "Issue list name has been " + statusval + ".", "success");
				},
				error : function(d) {
					tohideLoadingImgoverlay();
					swal("Deactivate!", "Issue list name has not been " + statusval + ".", "error");
				}
			});
		});
	}
	 	 function activeaction(id, status,val) {
	 		 
		var statusval = "";
		statusval = "active";
		 var update="activestatus";
		swal({
			title : "Are you sure want to " + statusval + "?",
			text : "You will be able to see this issue list!",
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
				url : 'modifylisttxt',
				data : 'editissueid=' + id + '&issuelist=1&updateflg='+update,
				success : function(data) {
					tohideLoadingImgoverlay();
					$("#categoryid_" + id).attr("onclick", "deleteaction('" + id + "','"+status+"','"+val+"')");
					$("#ideditid_" + id).attr("onclick", "editfunctionaction('" + id + "','1','"+val+"')");
					$("#idcardi_deacticon_" + id).hide();
					$("#idcardi_activeicon_" + id).show();
					$("#statusval_" + id).html("Active");
					swal("Active!", "Issue list name has been " + statusval + ".", "success");
				},
				error : function(d) {
					tohideLoadingImgoverlay();
					swal("Active!", "Issue list name has not been " + statusval + ".", "error");
				}
			});
		});
	} 
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>