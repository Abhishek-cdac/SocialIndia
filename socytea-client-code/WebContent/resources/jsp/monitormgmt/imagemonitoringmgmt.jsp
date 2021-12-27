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
									<s:text name="text.imagemonitoring" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="imagemonitoring"><s:text name="user.monitoring.Headmenu.iptracking" /></a></li>
									<li class="active"><s:text name="text.imagemonitoring" /></li>
								</ol>
								<div class="right"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<jsp:include page="../common/Alert.jsp" flush="true" />					
					<div class="row">
						<div id="table-datatables">							
								<div style="height: 10px;"></div>
								<div class="col s12">
									<table id="example" class="responsive-table display" cellspacing="0" width="100%">
										<thead>
											<tr class="<s:text name="datatable.heading.bgcolor"/>">
												<th><s:text name="Text.id"></s:text></th>
												<th><s:text name="Text.uploadimage"></s:text></th>
												<th><s:text name="text.imageon"></s:text></th>
												<th><s:text name="Text.userdetail"></s:text></th>
												<th><s:text name="Text.action"></s:text></th>
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
	<s:form method="post" id="delgroupform">
	<s:hidden name="useruniqueId" id="uniqUserIdEdit"></s:hidden>
	<s:hidden name="deleteusrid" id="deleteusridEdit"></s:hidden>
    </s:form>
	<!-- END MAIN -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	 <jsp:include page="../common/clorbox.jsp"></jsp:include>
	<script type="text/javascript">
	var oTable;
	$(document).ready(function() {		
		createoTable();			
	}); 
	function createoTable() {		
		oTable = $('#example').DataTable( {
			"jQueryUI" : false,
			"processing" : true,
			"serverSide" : true,
			"displayLength" : 10,
			"serverMethod": "POST",
			"scrollAutoCss": true,						
			"ajaxSource" : "imagemonitoringMgmt",
			"sorting" : [],
			"paginationType" : "full_numbers",
			"columnDefs" : [ {
				"sortable" : false,
				"targets" : [ 0, 1, 2, 3, 4]
			} ],
			"fnDrawCallback": function( oSettings ) {
				 $('.tooltipped').tooltip({delay: 10});
			      //alert( 'DataTables has redrawn the table' );
			    }
				});		
	}	
	
	 function deleteactioncontent(pMastertblid, atttchtblid, tblflag,imgmontable,imgmontid) {		
			var statusval = "";
			statusval = "Block Image";
			swal({
				title : "Are you sure want to " + statusval + "?",
				text : "You will not be able to recover this record!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes",
				closeOnConfirm : false
			}, function(isConfirm) {
				if(isConfirm){
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'deleteimagemonitoringaction',
					data : 'uniqueId=' + imgmontid + '&tableflg=' + tblflag+'&imgmontable=' +imgmontable+'&blockrowid=' +atttchtblid+'&ivrMastertblid = '+pMastertblid,
					success : function(data) {					
						$("#imgmontid_" + imgmontid).attr("onclick", "activeactioncontent('" + pMastertblid + "','" + atttchtblid + "','"+tblflag+"','"+imgmontable+"','"+imgmontid+"')");
						$("#imgmontcnt_lbl_" + imgmontid).html("&nbsp;&nbsp;UnBlock Image");
						swal(statusval + "!", "Record has been " + statusval + ".", "success");
					},
					error : function(d) {						
						swal(statusval + "!", "Record has not been " + statusval + ".", "error");
					}
				});
				} else {					
					$("#imgmontid_" + imgmontid).prop('checked',false).change();
					$("#imgmontcnt_lbl_" + imgmontid).html("&nbsp;&nbsp;Block Image");
				}
			});
		}	
	 function activeactioncontent(pMastertblid, atttchtblid, tblflag,imgmontable,imgmontid) {
		
			var statusval = "";
			statusval = "Unblock Image";
			swal({
				title : "Are you sure want to " + statusval + "?",
				text : "You will be able to see this record!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes",
				closeOnConfirm : false
			}, function(isConfirm) {
				 if(isConfirm){
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'activeimagemonitoringaction',
					data : 'uniqueId=' + imgmontid + '&tableflg=' + tblflag+'&imgmontable=' +imgmontable+'&blockrowid=' +atttchtblid+'&ivrMastertblid = '+pMastertblid,
					success : function(data) {
						$("#imgmontid_" + imgmontid).attr("onclick", "deleteactioncontent('" + pMastertblid + "','" + atttchtblid + "','"+tblflag+"','"+imgmontable+"','"+imgmontid+"')");
						$("#imgmontcnt_lbl_" + imgmontid).html("&nbsp;&nbsp;Block Image");
						swal(statusval + "!", "Record has been " + statusval + ".", "success");
					},
					error : function(d) {
						
						swal(statusval + "!", "Record has not been " + statusval + ".", "error");
					}
				});
				 }else{
					 	$("#imgmontid_" + imgmontid).prop('checked',true).change();
						$("#imgmontcnt_lbl_" + imgmontid).html("&nbsp;&nbsp;UnBlock Image");
					 }
			});
		}
	 function deleteactionUser(id, feedid)
	 {
		 swal({    title: "Are you sure want to Block?",
	            text: "You will not be able to recover this user!",   
	            type: "warning",   
	            showCancelButton: true,   
	            confirmButtonColor: "#DD6B55",   
	            confirmButtonText: "Yes",   
	            closeOnConfirm: false }, 
	            function(isConfirm){ 
		            if(isConfirm){
		            	$.ajax({
							type : 'post',
							datatype : 'html',
							url : 'deactivateusrmonitor',
							data : 'uniqueId=' + id+'&blockrowid='+feedid,
							success : function(data) {
							
								$("#imgmontusr"+feedid).attr("onclick", "activeactionUser('" + id + "','"+feedid+"')");
								$("#imgmontusr_lbl_" + feedid).html("&nbsp;&nbsp;UnBlock User");
								swal("Deactivated!", "User has been Deactivated.", "success"); 
							},
							error : function(d) {
								$("#imgmontusr"+feedid).removeAttr("checked");
								$("#imgmontusr_lbl_" + feedid).html("&nbsp;&nbsp;Block User");
								swal("Deactivated!", "User has been Deactivated.", "error"); 
							}
						});
			            }else{
			            	$("#imgmontusr"+feedid).removeAttr("checked");
							$("#imgmontusr_lbl_" + feedid).html("&nbsp;&nbsp;Block User");
							//swal("Deactivated!", "User has been Deactivated.", "error"); 
				            }
	            	
	            	
	           // swal("Deleted!", "User has been deleted.", "success"); 
	            });
	 }
	 function activeactionUser(id, feedid) {
			
			var statusval = "";
			statusval = "Unblock User";
			swal({
				title : "Are you sure want to " + statusval + "?",
				text : "You will be able to see this record!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes",
				closeOnConfirm : false
			}, function(isConfirm) {
				 if(isConfirm){
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'activeactionuser',
					data : 'uniqueId=' + id + '&blockrowid=' + feedid,
					success : function(data) {
						$("#imgmontusr" + feedid).attr("onclick", "deleteactionUser('" + id + "','"+feedid+"')");
						$("#imgmontusr_lbl_" + feedid).html("  Block User");
						swal("Activated!", "User has been Activated.", "success"); 
					},
					error : function(d) {
						
						swal("Activated!", "User has been Activated.", "error"); 
					}
				});
				 } else {
					 $("#imgmontusr" + feedid).prop('checked',true).change();
						$("#imgmontusr_lbl_" + feedid).html("&nbsp;&nbsp;UnBlock User");
					 }
			});
		}
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body></html>	