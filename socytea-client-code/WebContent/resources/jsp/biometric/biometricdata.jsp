<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<!--================================================================================
	Item Name: Materialize - Material Design Admin Template
	Version: 3.1
	Author: GeeksLabs
	Author URL: http://www.themeforest.net/user/geekslabs
================================================================================ -->

<head>
<meta http-equiv="Content-Type"
	content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible"
	content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight"
	content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
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
			<section id="content">
				<!--breadcrumbs start-->
				<div id="breadcrumbs-wrapper">
					<!-- Search for small screen -->
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="Biometric User Info" /></h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome"/></a></li>
									<li><a href="#"><s:text name="Breadcrumb.societymgmt"/></a></li>
									<li class="active"><s:text name="Breadcrumb.biometric.usrinfo" /></li>
								</ol>
							</div>
						</div>
			            <div class="row">
			              <div class="col s12 m12 l12">
			                <!-- <button type="button">Society Management</button> -->
			                <a href="_societymgmt_"><button type="button">Demographic Data</button></a>
			                <a href="biometricdatabasemenu"><button type="button">Biometric Database</button></a>
			                <a href="biometricdatamenu"><button type="button" class="btn">Biometric Data</button></a>
                			<a href="feescharges"><button type="button">Fees and Charges</button></a>
			              </div>
			            </div>

					</div>

				</div>
				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">

					<jsp:include page="../common/Alert.jsp" flush="true" />
					<div style="clear: both;"></div>

					<div class="col s12 m4 l3">

						<div class="row">
							<div class="input-field col s2">
								<label for="slectfield"><s:text
										name="Text.selectfield.set" /></label>
								<s:textfield name="labRegObj.wrktypname" id="slectfield"
									cssClass="form-control typeahead tt-query" autocomplete="off"
									spellcheck="false" />
								<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id"
									class="form-control" />
							</div>
							<div class="input-field col s2">
								<label for="usersearchname"><s:text name="text.search" /></label>
								<s:textfield name="usersearchname" id="usersearchname"
									cssClass="form-control typeahead tt-query" autocomplete="off"
									spellcheck="false" />
							</div>

							<div class="input-field col s2">
								<button data-target="#addnewaccount" style="margin-top: 12px;"
									data-toggle="modal" data-position="right" onclick="filter();"
									data-delay="10" data-tooltip="Click to search"
									class="<s:text name="button.color.filter"/>"
									id="addaccountbuttonid" type="button" onclick="filter();">
									<i class="mdi-action-search "></i>Filter
								</button>
							</div>

						</div>
					</div>

					<div id="table-datatables">

						<div class="row">
							<div style="height: 10px;"></div>
							<div class="col s12">
								<table id="example" class="responsive-table display hoverable">
									<thead>
										<tr class="<s:text name="datatable.heading.bgcolor"/>">
											<th><s:text name="Text.biometricusrtbl.id" /></th>
<%-- 											<th><s:text name="Text.biometricusrtbl.societyid" /></th> --%>
											<th><s:text name="Text.biometricusrtbl.residentid"></s:text></th>
											<th><s:text name="Text.biometricusrtbl.residentname"></s:text></th>
											<th><s:text name="Text.biometricusrtbl.mobileno"></s:text></th>
											<th><s:text name="Text.biometricusrtbl.email"></s:text></th>
											<th><s:text name="Text.biometricusrtbl.recordstatus" /></th>
											<th><s:text name="Text.biometricusrtbl.location" /></th>
											<th><s:text name="Text.biometricusrtbl.issendpushnotification" /></th>
											<th><s:text name="Text.biometricusrtbl.devicelogid" /></th>
											<th><s:text name="Text.biometricusrtbl.deviceid" /></th>
											<th><s:text name="Text.biometricusrtbl.userid" /></th>
											<th><s:text name="Text.biometricusrtbl.logdate" /></th>
											<th><s:text name="Text.biometricusrtbl.statuscode" /></th>
											<th><s:text name="Text.biometricusrtbl.duration" /></th>
											<th><s:text name="Text.biometricusrtbl.verificationmode" /></th>
											
											
									   </tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
					<br>
					<div class="divider"></div>
					<!--DataTables example Row grouping-->
				</div>
				<!--end container-->
			</section>
		</div>
		<!-- END WRAPPER -->
	</div>
	<!-- END MAIN -->

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
</body>
<script type="text/javascript">
	/*Show entries on click hide*/
	var oTable;
	$(document).ready(function() {
		createoTable();
		selectfieldfun();
	});

	function selectfieldfun() {
		var objects_slectfield;
		var locval = [ {
			"id" : "1",
			"label" : "All"
		}, {
			"id" : "2",
			"label" : "Email"
		}, {
			"id" : "3",
			"label" : "Mobile No."
		}, {
			"id" : "4",
			"label" : "Name"
		} ];

		$('#slectfield').typeahead({

			order : "asc",
			hint : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
				objects_slectfield = [];
				map = {};
				var data = locval;
				$.each(data, function(i, object) {
					map[object.label] = object;
					objects_slectfield.push(object.label);
				});
				process(objects_slectfield);
				$(".dropdown-menu").css("height", "auto");
				$(".dropdown-menu").addClass("form-control");
			},
			updater : function(item) {
				$('#slectfield_hidd_id').val(map[item].id);
				return item;
			}
		});

		$('#slectfield').blur(
				function() {
					if (objects_slectfield != undefined
							&& objects_slectfield.indexOf(this.value) != -1) {
					} else {
						$('#slectfield').val('');
						$('#slectfield').focus();
						$('#slectfield_hidd_id').val('');
					}
				});
	}

	function createoTable() {
		oTable = $('#example').DataTable({
			"jQueryUI" : false,
			"processing" : true,
			"serverSide" : true,
			"displayLength" : 15,
			"ajaxSource" : "bioUserdatatable",
			"sorting" : [],
			"paginationType" : "full_numbers",
			"columnDefs" : [ {
				"sortable" : false,
				"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ]
			} ],
			"fnDrawCallback" : function(oSettings) {
				$('.tooltipped').tooltip("remove");
				$('.tooltipped').tooltip({
					delay : 8
				});
				$('select').material_select();
			}
		});

	}

	$(document).ready(function() {
		$(".dropdown-content.select-dropdown li").on("click", function() {
			var that = this;
			setTimeout(function() {
				if ($(that).parent().hasClass('active')) {
					$(that).parent().removeClass('active');
					$(that).parent().hide();
				}
			}, 100);
		});
	});

	function filter() {
		var slectfield = $('#slectfield_hidd_id').val();
		var searchname = $('#usersearchname').val();

		oTable.destroy();
		oTable = $('#example').DataTable(
				{
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 15,
					"ajaxSource" : "bioUserdatatable?slectfield=" + slectfield
							+ "&searchname=" + searchname + "&searchval=1",
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [ {
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7,8, 9, 10, 11, 12, 13, 14]
					} ],
					"fnDrawCallback" : function(oSettings) {
						$('.tooltipped').tooltip("remove");
						$('.tooltipped').tooltip({
							delay : 8
						});
						$('select').material_select();
					}
				});

	}

	function slectfieldtype() {
		$("#usersearchname").val('');
	}

	$("#slectfield").change(function() {
		$('#usersearchname').val('');
	});
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript"
	src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</html>