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
								<h5 class="breadcrumbs-title">User Management</h5>
								<ol class="breadcrumbs left">
									<li><a href="loginform"><s:text
												name="Breadcrumb.dashboard" /></a></li>
									<li><a href="usermgmt"><s:text name="UAM" /></a></li>
									<li class="active">User Management</li>
								</ol>
								<div class="right">
									<a href="uamusercreateform"><button type="button"
											style="width: 200px;"
											class=" <s:text name="button.color.new.create"/>"
											data-toggle="modal" data-target="#addnewaccount"
											data-position="bottom"
											data-delay="<s:text name="material.tooltip.delay"/>"
											data-tooltip="Create New User">
											<i class="mdi-social-person-add left"> </i>New User
										</button></a> <a href="uamsiusercreateform"><button type="button"
											style="width: 200px"
											class=" <s:text name="button.color.new.create1"/>"
											data-toggle="modal" data-target="#addnewaccount"
											data-position="bottom"
											data-delay="<s:text name="material.tooltip.delay"/>"
											data-tooltip="Create SI User">
											<i class="mdi-social-person-add left"> </i> SI User
										</button></a>
								</div>
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
								<label for="township_txt_id"><s:text
										name="Text.adduser.town.ship.id" /></label>
								<s:textfield id="township_txt_id"
									name="usercreateObj.townshipName" data-error=".errorTxt1"
									cssClass="form-control typeahead tt-query townshipIdvalidate"
									autocomplete="off" />
								<s:hidden name="townshipid" id="township_hidd_id"
									cssClass="form-control " />
							</div>
							<div class="input-field col s2">
								<label for="societyId_txt_id"><s:text
										name="Text.adduser.societyname" /></label>
								<s:textfield name="societyName" id="societyId_txt_id"
									cssClass="typeahead tt-query  form-control" autocomplete="off"
									spellcheck="false" />
								<s:hidden id="societyId_hidd_id" name="societyId"
									cssClass="form-control" />
							</div>
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
							<%--<div class="input-field col s2">
		<s:select style="width:180px;" id="townshipid" cssClass="form-control townshipIdvalidate" headerValue="Select Township" list="#session.TOWNSHIPLIST" listKey="townshipId" listValue="townshipName" name="townshipmaster.townshipId" value="%{townshipmaster.townshipId}" onchange="onchangetownshipId();">
		</s:select>    
		<s:fielderror fieldName="restRegObj.userName" />
								</div>
								<div class="input-field col s2">
									<s:select style="width:180px;" name="societyId" listKey="stateId" id="statediv" cssClass="form-control societyIdvalidate" list="# {'':'Select Society '} " headerValue="Select Society"  onchange="societychange();">
									</s:select>	
								</div>
								<div class="input-field col s2">
									<select style="width:180px;" class="form-control gendervalidate" id="slectfield" name="selectfield" onchange="slectfieldtype();">
                                         <option value="">Select Field</option>
                                              <option value="1">All</option>
                                               <option value="2">Email</option>
                                                  <option value="3">Mobile No.</option>
                                                       <option value="4">Name</option>
                                                       <option value="5">Group Type</option>
                                                      </select>   
								</div>

							<div class="input-field col s2">
									<label><s:text name="Search" /></label>

									<s:textfield name="restRegObj.emailId" id="usersearchname"
										cssClass="form-control typeahead tt-query cmpnyRegnovalidate"
										autocomplete="off" />
								</div> --%>
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
											<th><s:text name="Text.id" /></th>
											<th><s:text name="Text.name" /></th>
											<th><s:text name="Text.Form.MobileNo"></s:text></th>
											<th><s:text name="Text.Form.EmailId"></s:text></th>
											<th><s:text name="Table.recnhead.township"></s:text></th>
											<th><s:text name="Table.recnhead.society"></s:text></th>
											<th><s:text name="Text.adduser.groupname" /></th>
											<th><s:text name="Text.accesschannel" /></th>
											<th><s:text name="Text.action"></s:text></th>
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
	<s:form method="post" id="delgroupform">
		<s:hidden name="useruniqueId" id="uniqUserIdEdit"></s:hidden>
		<s:hidden name="deleteusrid" id="deleteusridEdit"></s:hidden>
	</s:form>
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
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'gettownshipOnload',
			//data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				var spli = data.split("!_!");
				townshipdata(spli[1]);
			}
		});
	});
	function townshipdata(valjson) {
		valjson = valjson.replace(/&quot;/ig, '"');
		var locval = JSON.parse(valjson);
		var objects_cardtype;
		$('#township_txt_id').typeahead({
			order : "asc",
			hint : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
				objects_cardtype = [];
				map = {};
				var data = locval;
				$.each(data, function(i, object) {
					map[object.label] = object;
					objects_cardtype.push(object.label);
				});
				process(objects_cardtype);
				$(".dropdown-menu").css("height", "auto");
				$(".dropdown-menu").addClass("form-control");
			},
			updater : function(item) {
				$('#township_hidd_id').val(map[item].id);
				getsociety(map[item].id);
				return item;
			}

		});
		$('#township_txt_id').blur(function() {
			if (objects_cardtype.indexOf(this.value) != -1) {
			} else {
				$('#township_txt_id').val('');
				$('#township_txt_id').focus();
				$('#township_hidd_id').val('');
			}
		});

	}
	function getsociety(townid) {
		//var valjson=onchangetownshipId(townid);
		var temp = "";
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'townshipgetsociety',
			data : 'townshipid=' + townid + "&clfor=autocomp",
			success : function(data) {
				var spl = data.split("!_!");
				temp = spl[1];
				getsociety_auto(spl[1]);
				//return temp;
			}
		});
	}

	function getsociety_auto(tempdata) {
		tempdata = tempdata.replace(/&quot;/ig, '"');
		var locval = JSON.parse(tempdata);
		var map_scty;
		$('#societyId_txt_id').val('');

		$('#societyId_txt_id').typeahead('refresh');
		$('#societyId_txt_id').typeahead('destroy');
		$('#societyId_txt_id').typeahead({
			order : "asc",
			hint : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
				objects_scty = [];
				map_scty = {};
				var data = locval;
				$.each(data, function(i, object) {
					map_scty[object.label] = object;
					objects_scty.push(object.label);
				});
				process(objects_scty);
				$(".dropdown-menu").css("height", "auto");
				$(".dropdown-menu").addClass("form-control");
			},
			updater : function(item) {
				$('#societyId_hidd_id').val(map_scty[item].id);
				return item;
			}
		//add dynamical validate - 2
		});
		$('#societyId_txt_id').typeahead('refresh');

		$('#societyId_txt_id').blur(
				function() {
					if (typeof (objects_scty) != "undefined"
							&& objects_scty.indexOf(this.value) != -1) {
					} else {
						$('#societyId_txt_id').val('');
						$('#societyId_txt_id').focus();
						$('#societyId_hidd_id').val('');
						$('#societyId_txt_id').typeahead('destroy');
					}
				});
	}

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
		}, {
			"id" : "5",
			"label" : "Group Type"
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
				//objects_slectfield.push("");
				$.each(data, function(i, object) {
					console.log("itemssss : ["+object.label+"]");
					map[object.label] = object;
					objects_slectfield.push(object.label);
				});
				process(objects_slectfield);
				$(".dropdown-menu").css("height", "auto");
				$(".dropdown-menu").addClass("form-control");
			},
			updater : function(item) {
				console.log("ites : ["+item+"]");
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
						//$('#slectfield').focus();
						$('#slectfield_hidd_id').val('');
					}
				});
	}

	function createoTable() {
		oTable = $('#example').DataTable({
			"jQueryUI" : false,
			"processing" : true,
			"serverSide" : true,
			"displayLength" : 10,
			"ajaxSource" : "addmerchantUserdatatable",
			"sorting" : [],
			"paginationType" : "full_numbers",
			"columnDefs" : [ {
				"sortable" : false,
				"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7, 8 ]
			} ],
			"fnDrawCallback" : function(oSettings) {
				$('.tooltipped').tooltip("remove");
				$('.tooltipped').tooltip({
					delay : 10
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

	function onchangetownshipId() {
		var townshipid = $("#township_hidd_id").val();
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'townshipgetsociety',
			data : 'townshipid=' + townshipid,
			success : function(data) {
				$("#societyId_hidd_id").html(data);
				$("#slectfield_hidd_id").val('');
				$("#usersearchname").val('');
				//$("#societyId_hidd_id").addClass('initialized');
				$('#societyId_hidd_id').material_select();
			},
			error : function(d) {
			}
		});
	}
	function edituser(id) {
		$("#uniqUserIdEdit").val(id);
		$("#delgroupform").attr("action", "edituserform");
		$("#delgroupform").submit();
	}
	function deleteuser(id) {
		swal({
			title : "Are you sure want to delete?",
			text : "You will not be able to recover this user!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes",
			closeOnConfirm : false
		}, function() {
			$("#deleteusridEdit").val(id);
			$("#delgroupform").attr("action", "deleteuseraction");
			$("#delgroupform").submit();
			// swal("Deleted!", "User has been deleted.", "success"); 
		});
	}
	function home() {
		$("#releaseHisFormid").attr("action", "homeusercreation");
		$("#releaseHisFormid").submit();
	}

	function filter() {
		var township = $('#township_hidd_id').val();
		var society = $('#societyId_hidd_id').val();
		var slectfield = $('#slectfield_hidd_id').val();
		var searchname = $('#usersearchname').val();

		oTable.destroy();
		oTable = $('#example').DataTable(
				{
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,
					"ajaxSource" : "addmerchantUserdatatable?townshipid="
							+ township + "&society=" + society + "&slectfield="
							+ slectfield + "&searchname=" + searchname
							+ "&searchval=1",
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [ {
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7, 8 ]
					} ],
					"fnDrawCallback" : function(oSettings) {
						$('.tooltipped').tooltip("remove");
						$('.tooltipped').tooltip({
							delay : 10
						});
						$('select').material_select();
					}
				});

	}

	function societychange() {
		$("#slectfield_hidd_id").val('');
		$("#usersearchname").val('');
	}
	function slectfieldtype() {
		$("#usersearchname").val('');
	}

	function editGroup(thisobjj) {
		toShowLoadingImgoverlay();
		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'groupType',
			data : 'uniqueId=' + thisobjj,
			success : function(data) {
				var arr = data.split("!_!");
				$("#selectboxdiv" + thisobjj).html(arr);
				var unquid = thisobjj;
				$("#lableblockdivid_" + unquid).hide();
				$("#editblockdivid_" + unquid).show();
				$('select').material_select();
				tohideLoadingImgoverlay();

			},
			error : function(d) {
				tohideLoadingImgoverlay();
			}
		});
		// tohideLoadingImgoverlay();
	}

	function saveEditGrptyp(id) {
		toShowLoadingImgoverlay();
		var selectval = document.getElementById('groupdiv').value;
		var selectvalue = $('#groupdiv option:selected').text();
		if (selectvalue == 'Resident') {
			$('#access_' + id).html("Mobile");
			$.ajax({
				type : 'post',
				datatype : 'json',
				url : 'groupTypeUpdate',
				data : 'selectval=' + selectval + '&selectId=' + id
						+ '&flg=mobile',
				success : function(data) {
					$("#labelGrpTyid_" + id).html(selectvalue);
					$("#editblockdivid_" + id).hide();
					$("#lableblockdivid_" + id).show();
					$("#access_" + id).html("Mobile");
					tohideLoadingImgoverlay();
					oTable.destroy();
					createoTable();
				},
				error : function(d) {
					tohideLoadingImgoverlay();
				}
			});
		} else {
			$('#access_' + id).html("both");
			$.ajax({
				type : 'post',
				datatype : 'json',
				url : 'groupTypeUpdate',
				data : 'selectval=' + selectval + '&selectId=' + id
						+ '&flg=both',
				success : function(data) {
					$("#labelGrpTyid_" + id).html(selectvalue);
					$("#editblockdivid_" + id).hide();
					$("#lableblockdivid_" + id).show();
					$("#usid" + id).html("Mobile");
					tohideLoadingImgoverlay();
					oTable.destroy();
					createoTable();
				},
				error : function(d) {
					tohideLoadingImgoverlay();
				}
			});
		}

	}

	function calcelEditGrptyp(id) {
		toShowLoadingImgoverlay();
		var unquid = id;
		$("#editblockdivid_" + unquid).hide();
		$("#lableblockdivid_" + unquid).show();
		tohideLoadingImgoverlay();
	}
	$("#township_txt_id").change(function() {
		$('#societyId_txt_id').val('');
		$('#societyId_hidd_id').val('');
		$('#slectfield').val('');
		$('#slectfield_hidd_id').val('');
		$('#usersearchname').val('');
	});

	$("#societyId_txt_id").change(function() {
		$('#slectfield').val('');
		$('#slectfield_hidd_id').val('');
		$('#usersearchname').val('');
	});

	$("#slectfield").change(function() {
		$('#usersearchname').val('');
	});
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript"
	src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</html>