<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
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
								<h5 class="breadcrumbs-title">
									<s:text name="Breadcrumb.rightsmanage" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="loginform"><s:text name="Dashboard" /></a></li>
									<li><a href="usermgmt"><s:text name="UAM" /></a></li>
									<li class="active"><s:text name="Breadcrumb.rightsmanage" /></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<jsp:include page="../common/Alert.jsp" flush="true" />

				<!--start container-->
				<div class="container">
					<div class="card-panel">

						<s:form role="form" id="createRightsId" name="createRightsId"
							method="post">
							<s:set name="nextMenuType" value="M"></s:set>


							<div class="form-group">
								<s:select headerKey="" headerValue="---Select Group---"
									list="#session.groupMasterList" listKey="groupCodeId"
									listValue="groupName" cssClass="form-control assignRightsGroup"
									name="groupCode" id="grpname" value="%{groupmastlist}"
									onchange="getselectedvalues();"></s:select>
								<s:fielderror fieldName="rightsMast.groupCode.groupCode" />
							</div>
							<ul class="">
								<s:iterator value="#request.MENUTYPE" status="userStatus">
									<s:if test="menuType.equalsIgnoreCase('M')">
										<s:if test="rootDesc.equalsIgnoreCase('000001_')"></s:if>
										<s:else>
							</ul>
							</li>

							</s:else>
							<div id='p<s:property value="rootDesc"/>'
								style="cursor: pointer; width: 17px; display: none;"
								onclick="hidelist(1,'<s:property value="rootDesc"/>');">
								<img class="left" width="16" height="17" alt="Logout"
									src="resources/images/icon_plus.gif">
							</div>
							<div id='m<s:property value="rootDesc"/>'
								style="cursor: pointer; width: 17px;"
								onclick="hidelist(0,'<s:property value="rootDesc"/>');">
								<img class="left" width="16" height="17" alt=""
									src="resources/images/icon_minus.gif">
							</div>
							<div style="clear: both;"></div>


							<li id='r<s:property value="rootDesc"/>'
								style="margin-top: -15px; list-style: none;"><input
								type="checkbox" id="ck<s:property value="rootDesc"/>"
								class="filled-in" value="<s:property value="menuId"/>"
								onclick="checkmainrights(this);" name="rights"> <%-- <s:checkbox
										class="checkbox" style="margin-left:35px;margin-right:15px;"
										id="ck%{rootDesc}" name="rights"
										onclick="checkmainrights(this);" fieldValue="%{menuId}"
										value=""></s:checkbox>  --%> <label
								for="ck<s:property value="rootDesc"/>"
								style="margin-left: 25px;"> <s:property value="menuDesc" /></label>

								<ul>
									</s:if>
									<s:else>
										<li id='<s:property value="rootDesc"/>'
											style="list-style: none;"><input type="checkbox"
											id="ck<s:property value="rootDesc"/>"
											value="<s:property value="menuId"/>"
											onclick="checksubrights(this);" name="rights"
											class="filled-in" /> <%-- 		<s:checkbox
													cssClass="checkbox"
													style="margin-left:65px;margin-right:15px;" name="rights"
													id="ck%{rootDesc}" onclick="checksubrights(this);" value=""
													fieldValue="%{menuId}"></s:checkbox> --%> <label
											for="ck<s:property value="rootDesc"/>"
											style="margin-left: 75px;"> <s:property
													value="menuDesc" /></label></li>

									</s:else>


									<div style="clear: both; height: 5px;"></div>
									</s:iterator>

								</ul></li>
							<div class="padding10px"></div>
							<div class="row" style="margin-left: 41px;">
								<button type="button" id="userCreateButtonId"
									onclick="createRights();"
									class="<s:text name="button.color.submit"/>">
									<s:text name="Set" />
									<i class="<s:text name="button.icon.submitcard"/>"></i>
								</button>
								<button type="button" id=""
									class=" <s:text name="button.color.cancel"/>" onclick="home();">
									<s:text name="Text.button.cancel" />
									<i class="<s:text name="button.icon.replycard"/>"></i>
								</button>
							</div>



						</s:form>

						<s:form method="post" id="rightsHomeFormid"></s:form>
					</div>
				</div>
				<!--end container-->
			</section>










		</div>
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>

</body>
<script type="text/javascript">
	function createRights() {
		if ($('#grpname').val() != "") {
			if (checkCbx() == "True") {
				swal("Information", "Please Select Any One Group Name.");
				swal({
					title : "Confirmation",
					text : "Do you want to Confirm to Assign rights?",
					type : "info",
					showCancelButton : true,
					closeOnConfirm : false,
					showLoaderOnConfirm : true,
				}, function() {
					document.createRightsId.action = "createRights";
					document.createRightsId.submit();
				});
			} else {
				swal("Information",
						"<s:text name="Text.grprights.selectrights"/>");
			}
		} else {
			swal("Information", "Please Select Any One Group Name.");
		}

	}

	/*Show entries on click hide*/
	function checkCbx() {
		var cbxlen = document.createRightsId.rights.length;
		var flag = false;
		for ( var i = 0; i < cbxlen; i++) {
			if (document.createRightsId.rights[i].checked) {
				flag = true;
			}
		}
		if (flag) {

			return "True";
		} else {

			return "False";
		}
	}
	function checkmainrights(th) {
		if ($(th).is(':checked')) {
			var mid = th.id;
			mid = mid.replace("ck", "");
			$("#" + mid + " ul li").each(function() {
				var thiid = $(this).attr("id");
				$("#ck" + thiid).prop('checked', true);

			});
		} else {
			var mid = th.id;
			mid = mid.replace("ck", "");
			$("#" + mid + " ul li").each(function() {
				var thiid = $(this).attr("id");
				$("#ck" + thiid).prop('checked', false);

			});
		}
	}
	function checksubrights(th) {
		if ($(th).is(':checked')) {
			var id = th.id;
			var mid = id.split("_");
			if ($("#" + mid[0] + "_").is(':checked')) {
			} else {
				$("#" + mid[0] + "_").prop('checked', true);
			}
		} else {
			var mid = th.id;
			mid = mid.replace("ck", "");
			var mmin = mid.split("_");
			mmin = mmin[0] + "_";
			var inc = 1;
			$("#" + mmin + " ul li").each(function() {
				var thiid = $(this).attr("id");
				if ($("#ck" + thiid).is(':checked')) {
					inc = 2;
				}
			});
			if (inc == 1) {
				$("#ck" + mmin).prop('checked', false);
			}
		}
	}
	function getselectedvalues() {
		var aa = $("#grpname").val();
		var grpnm = $("#grpname option:selected").text();
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : "menurightsadmin",
			data : 'menuid=' + aa,
			success : function(data) {
				var pp = $.trim(data);
				var arr = pp.split("||");
				$('input:checkbox[name=rights]').prop('checked', false);
				for ( var i = 0; i < arr.length; i++) {
					$("#ck" + arr[i]).prop('checked', true);
				}

			}

		});

	}
	function hidelist(show, id) {
		if (show == 0) {
			$("#r" + id + ">ul").hide();
			$("#m" + id).hide();
			$("#p" + id).show();
		} else {
			$("#r" + id + ">ul").show();
			$("#m" + id).show();
			$("#p" + id).hide();
		}
	}

	function home() {
		$("#rightsHomeFormid").attr("action", "Homeform");
		$("#rightsHomeFormid").submit();
	}
</script>

</html>