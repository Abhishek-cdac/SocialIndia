<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><s:text name='Text.project.title' /></title>

<style type="text/css">
#addaccountformid ul li{
	list-style: none;
	color: #D90F0F;	
	font-size: 11px;
	margin-left: -40px;
    margin-top: 0px;
}
#modifyaccountformid ul li{
	list-style: none;
	color: #D90F0F;	
	font-size: 11px;
	margin-left: -40px;
    margin-top: 0px;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<!-- START Header Container -->
		<jsp:include page="../common/header.jsp"></jsp:include>
		<!-- START Left Column -->
		<!-- END Left Column -->

		<!-- START Right Column -->
		<div id="right-column">
			<div class="right-column-content">

				<div class="row">
					<div class="col-xs-12">
						<ol class="breadcrumb">
							<li><a href="#"><s:text	name="Labour Management" /></a></li>
							<li class="active"><s:text name="Labour Management" /></li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h1>
							<span aria-hidden="true" class="icon icon-dollar"></span> <span
								class="main-text"><s:text
									name="Labour Management" /></span>
						</h1>
					</div>
					<div class="col-md-6"></div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="block">
							
							<!-- START Widget Hybrid: Task List -->
							<div class="block-content-outer">
								
								<jsp:include page="../common/Alert.jsp" flush="true" />
								
								<div class="block-content-inner">
									<div class="table-responsive">
									<div id="hidealert" class="alert alert-success" onclick="hidealerty()">
    								<strong>SuccessFully!</strong> update your profile
  									</div>
  									<script type="text/javascript">
  									function hidealerty(){
  									$('#hidealert').hide();	
  									}
  									</script>
										<div class="text-right" style="margin: 10px;">
											<a href="addcommittee"><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">Committee Add</button></a>
										</div>

										<table id="example"
											class="table table-striped table-bordered dt-responsive nowrap"
											cellspacing="0" width="100%">
											<thead>
												<tr>
													<th>Name</th>
													<th>Email_ID</th>
													<th>Phone No</th>
													<th>DOB</th>
													<th>Country</th>
													<th>Action</th>
												</tr>
											</thead>
											<!-- <tbody></tbody>		 -->																				
												<tr>
													<td>Testj</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="committedit"><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>	
													<tr>
													<td>Test2</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>
													<tr>
													<td>Test3</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>
													<tr>
													<td>Test4</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>
													<tr>
													<td>Test5</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>	
													<tr>
													<td>Test6</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>	
													<tr>
													<td>Test7</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>
													<tr>
													<td>Test8</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>
													<tr>
													<td>Test9</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>	
													<tr>
													<td>Test10</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>
											<tr>
													<td>Test11</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>	
													<tr>
													<td>Test12</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>
													<tr>
													<td>Test13</td>
													<td>Test@gmail.com</td>
													<td>9874563210</td>
													<td>10-06-1991</td>
													<td>India</td>
													<td><a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;View&nbsp;&nbsp;</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=""><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addnewaccount">&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</button></a></td>
												</tr>																				
										</table>

									</div>
								</div>
							</div>
							<!-- END Widget Hybrid: Task List -->
						</div>
					</div>

				</div>
			</div>

			<s:form method="post" name="deleteaccountformid" id="deleteaccountformid"  >
				 <s:hidden id="deluniqidHidn" name="custAccontDetailObj.uniqId" value="%{custAccontDetailObj.uniqId}"></s:hidden>
			</s:form>
			
			<s:form method="post" name="lockunlockaccountformid" id="lockunlockaccountformid"  >
				 <s:hidden id="lockuniqidHidn" name="custAccontDetailObj.uniqId" value="%{custAccontDetailObj.uniqId}"></s:hidden>
				 <s:hidden id="lockunlockflgHidn" name="custAccontDetailObj.isLocked" value="%{custAccontDetailObj.isLocked}"></s:hidden>
			</s:form>
			
			<s:hidden id="validateEnterValHidn" name="validateEnterValHidn" value="%{validateEnterVal}"></s:hidden>
			<s:hidden id="validateAccntfrmtHidn" name="validateAccntfrmtHidn" value="%{validateAccntfrmt}"></s:hidden>
			

			<jsp:include page="../common/footer.jsp"></jsp:include>

		</div>

	</div>
	</div>

	<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/assets/css/required/mCustomScrollbar/jquery.mCustomScrollbar.min.css" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/icheck/all.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/datatables/css/dataTables.bootstrap.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/datatables/css/dataTables.tableTools.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/datatables/css/dataTables.responsive.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/demo-files/tables-datatables.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/css/bootstrap-dialog.min.css" rel="stylesheet" />
	

	<!-- Required JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery.mCustomScrollbar.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/icheck.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrapValidator.min.js"></script>
	<!-- Optional JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/datatables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/datatables/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/datatables/js/dataTables.tableTools.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/datatables/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/demo-files/tables-datatables.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap-dialog.min.js"></script>
	
	
	<script type="text/javascript">
		 $(document)
				.ready(
						function() {
							createoTable();
							
							
							

						});  
		
		function createoTable() {
			var userName = "";
			var UserId = "";
			var groupCode = "";
			var mobileNumber = "";
			var primaryMailId = "";
			oTable = $('#example')
					.dataTable(
							{
						 		"bJQueryUI" : true,
								/* "bProcessing" : true,
								"bServerSide" : true,  */
								"iDisplayLength" : 10,
								/* "sAjaxSource" : "<s:text name='Proj.path'/>/customeraccounttable?emailId="
										+ primaryMailId + "",
								"aaSorting" : [], */
								/* "sPaginationType" : "full_numbers", */
								"aoColumnDefs" : [ {
									"bSortable" : false,
									"aTargets" : [ 0, 1, 2, 3 ]
								} ]
							});
		}
						</script>
						</body>
						</html>

	