<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<section id="content">
				<!--breadcrumbs start-->
				<div id="breadcrumbs-wrapper">
					<!-- Search for small screen -->
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.committee.detail" /></h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome" /></a></li>
									<li><a href="committeemgmt"><s:text name="Breadcrumb.manage" /></a></li>
									<li><a href="committeemgmt"><s:text name="Breadcrumb.manage.committeemgmt"></s:text></a></li>
									 <li class="active"><s:text name="Breadcrumb.manage.committee.detail" /></li>
								</ol>
								<div class="right" ><button id="gobckbtnid" class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button></div>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
				<div class="card-panel">
				<form method="post" id="registerformid" name="registerformid" 
											action="">
							<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.adduser.town.ship.id" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="townshipname"/></span></div>
                      </div>
                    </div>
                   <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5"><s:text name="Text.adduser.society.id" /></div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="societyname"/></span></div>
  						</div>
                     </div>
							</div>
							
					<%-- 		<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">User Name</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="usercreateObj.userName"/></span></div>
                      </div>
                    </div>
                   <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">User Group </div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="committeeRole"/></span></div>
  						</div>
                     </div>
							</div> --%>
						
							<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.fname" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="usercreateObj.firstName"/></span></div>
                      </div>
                    </div>
                   <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5"><s:text name="Text.lname" /></div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="usercreateObj.lastName"/></span></div>
  						</div>
                     </div>
							</div>
							
							
				<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.emailid" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="usercreateObj.emailId"/></span></div>
                      </div>
                    </div>
                   <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5"><s:text name="Text.mobileno" /></div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="usercreateObj.isdCode"/>-<s:property value="usercreateObj.mobileNo"/></span></div>
  						</div>
                     </div>
							</div>
							<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.gender" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="usercreateObj.gender"/></span></div>
                      </div>
                    </div>
                   <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5"><s:text name="Text.resident.dob" /></div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="usercreateObj.dob"/></span></div>
  						</div>
                     </div>
							</div>
							<div class="row">
										
                			<div class="input-field col m6">
                  				<div class="row">							
								<div class="input-field col m5"><s:text name="Breadcrumb.util.committeerolemgmt" /></div>
						  		<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="committeeRole"/></span></div>
							</div></div>
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.accesschannel" /> </div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="accessChennel"/></span></div>
                      </div>
                    </div> </div>
					</form>
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				<!--end container-->
				</div>
			</section>

		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
    <jsp:include page="../common/allbasicjs.jsp"></jsp:include>
    <jsp:include page="../common/clorbox.jsp"></jsp:include>
</body>
<script>

$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "committeemgmt");
		$("#userCancelForm").submit();
	});
});	
</script>
</html>