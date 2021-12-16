<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%String msg=request.getParameter("alert");%>
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
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
							<h5 class="breadcrumbs-title"><s:text name="Text.report.labor" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Dashboard" /></a></li>
								<li><a href="#"><s:text name="Text.Report.Management" /></a></li>
								<li class="active"><s:text name="Text.report.labor" /></li>

							</ol>
							<div class="right" id="xlspdfdownload">
							<a
							onclick="getencriptedData('<s:text name="socialindia.labourReportXLS"/>');">
							<button id="addaccountbuttonid" class="<s:text name="button.color.new.create"/>"
								style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Export As XLS">
										<i class="mdi-file-cloud-download left"> </i>XLS
									</button>
						</a>
								<a
									onclick="getencriptedData('<s:text name="socialindia.labourReportPDF"/>');">
									<button id="addaccountbuttonid"
										class="<s:text name="button.color.new.create1"/>"
										style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Export As PDF">
										<i class="mdi-file-cloud-download left"> </i>PDF
									</button>
								</a>							
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--breadcrumbs end--> <!--start container-->
			<iframe id="my_iframe" style="display:none;"></iframe>
			<div class="container"></div>
			<!--end container--> </section>
			<jsp:include page="../common/Alert.jsp" flush="true" />
			<s:hidden id="societyId_session" value="%{#session.sSoctyIdStr}"></s:hidden>
			<div style="clear: both;"></div>
			<div class="col s12 m4 l3">
				<div class="row" style="margin-left: 12px;">
					<div class="input-field col s2">
						<!-- If have any search filter -->
						<label for="dateFromid">From Date [dd-mm-yyyy]</label>
						<s:textfield name="dateFrom" id="dateFromid" cssClass="dateOfBirth"
							onkeyup="textValidate(this,'20','DT')" />
					</div>
					<div class="input-field col s2">
						<label for="dateToid">To Date [dd-mm-yyyy]</label>
						<s:textfield name="dateTo" id="dateToid" cssClass="dateOfBirth"
							onkeyup="textValidate(this,'20','DT')" />
					</div>
					<div class="input-field col s2">
						<button id="addaccountbuttonid"
							class="<s:text name="button.color.filter"/>"
							onclick="loadDataTableByFilter();" data-toggle="modal"
							data-position="right" data-delay="10"
							data-tooltip="Click to search" style="margin-top: 12px;"
							type="button">
							<i class="mdi-action-search "></i>Filter
						</button>
					</div>


				</div>
			</div>
			<div id="table-datatables" class="padding10px">
				<div class="row">
					<div style="height: 10px;"></div>
					<div class="col s12">
						<table id="example" class="responsive-table display hoverable">
							<thead>
									<tr class="<s:text name="datatable.heading.bgcolor"/>">
												    <th><s:text name="Text.id"></s:text></th>
													<th><s:text name="Text.name"></s:text></th>
													<th><s:text name="Text.company.name"></s:text></th>
													<th><s:text name="Text.mobileno"></s:text></th>
													<th><s:text name="Text.emailid"></s:text></th>
													<th><s:text name="Work Type"></s:text></th>
													<th><s:text name="Menuheader.uam.profile.address"></s:text></th>
													<th><s:text name="Text.entryDate"></s:text></th>
													<!-- <th><s:text name="Text.modifyDate"></s:text></th>-->
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
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<%-- <% msg=""; %> --%>
	<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		createoTable();}); 
	function createoTable() {	
		var fromdate = $("#dateFromid").val();
		var todate = $("#dateToid").val();
		oTable = $('#example').DataTable({
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,
					"serverMethod": "POST",
					"scrollAutoCss": true,									    
					"ajaxSource" : "labourmgmtReportTbldata?fromdate="
						+ fromdate + "&todate=" + todate,
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [ {
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3, 4,5,6,7]
					} ],
					"drawCallback": function( oSettings ) {
						if(oSettings._iRecordsTotal==0)
						{
						$("#xlspdfdownload").hide();
						}
					else
					{
						$("#xlspdfdownload").show();
					}
						$('.tooltipped').tooltip("remove");
						 $('.tooltipped').tooltip({delay: 10});
					    }
				});
		$(window).bind('resize', function () {
		    oTable.fnAdjustColumnSizing();
		  } );
		tohideLoadingImgoverlay();
	}
	function loadDataTableByFilter() {
		var fromdate = $("#dateFromid").val(); // dd-MM-yyyy
		var todate = $("#dateToid").val(); // dd-MM-yyyy
		if(fromdate!='' && todate!=''){
			var frmDt = new Array(); var toDt = new Array();
			frmDt = fromdate.split("-");
            toDt = todate.split("-");
            var frm = frmDt[2]+"-"+frmDt[1]+"-"+frmDt[0];
            var to_date = toDt[2]+"-"+toDt[1]+"-"+toDt[0];
			var fromdatebj = new Date(frm); //Year-Month-Date
		    var toDateobj = new Date(to_date); //Year-Month-Date	
		    if (fromdatebj  <= toDateobj) {			    	  			    	   
		    	   //alert("To date is greather then From Date.");	
		    	oTable.destroy();
				$("#fromdate_hidd").val(fromdate);
				$("#todate_hidd").val(todate);
				oTable = $('#example').DataTable({
									"jQueryUI" : false,
									"processing" : true,
									"serverSide" : true,
									"displayLength" : 10,
									"serverMethod": "POST",
									"scrollAutoCss": true,									    
									"ajaxSource" : "labourmgmtReportTbldata?fromdate="+ fromdate + "&todate=" + todate,
									"sorting" : [],
									"paginationType" : "full_numbers",
									"columnDefs" : [ {
										"sortable" : false,
										"targets" : [ 0, 1, 2, 3, 4,5,6,7]
									} ],
									"drawCallback": function( oSettings ) {
										if(oSettings._iRecordsTotal==0) {
											$("#xlspdfdownload").hide();
										} else {
											$("#xlspdfdownload").show();
										}
										 $('.tooltipped').tooltip("remove");
										 $('.tooltipped').tooltip({delay: 10});
										 $('select').material_select();
									 }
								});
		    } else {
		    	swal("From date should less then To date. Change your from date and to date"); 
			}
		} else {
			if(fromdate=='' && todate==''){
				 swal("Select From Date And To Date");	
			}else if(fromdate==''){
				 swal("Please Select From Date");	
			}else if(todate==''){
				 swal("Please Select To Date");	
			}
			oTable.destroy();
			createoTable();
		}
	}
	function getencriptedData(url){
		//toShowLoadingImgoverlay();
		var fromdate = $("#fromdate_hidd").val();
		var todate = $("#todate_hidd").val();
		var sessionSociety = $("#societyId_session").val();
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getencryptedData',
			data : 'fromdate=' + fromdate+'&todate='+todate+"&crntsocietyid="+sessionSociety,
			success : function(data) {
				url=url+"?"+data;
				document.getElementById('my_iframe').src = url;
				//window.location.href = url;
				$('#my_iframe').ready(function(){ });

			},
			error : function(d) {
				//tohideLoadingImgoverlay();
			}
		});
		
	}
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body></html>