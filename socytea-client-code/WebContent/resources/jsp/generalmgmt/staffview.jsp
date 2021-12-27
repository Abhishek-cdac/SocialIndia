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
<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
                <h5 class="breadcrumbs-title"><s:text name="Text.staff.details" /></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home"/></a></li>
				<li><a href="staffmanagement"><s:text name="Breadcrumb.manage" /></a></li>
				<li><a href="staffmanagement"><s:text name="Breadcrumb.manage.staffmgmt" /></a></li>
                    <li class="active"><s:text name="Text.staff.details" /></li>
             
                   </ol>
                  <div class="right">
                      <button id="gobckbtnid" type="button" class=" <s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back"><i class="<s:text name="button.icon.replycard"/>"></i>Go Back</button>
                </div>                               						
              </div>
            </div>
      </div></div>
		<div class="container">
		<div class="card-panel">
		<form method="post" id="registerformid" name="registerformid" action="" >
								
								<div class="row">
									<div class="left resilaborview col s12 m4 l3">	
									<!-- <input type="file" id="documentfile" name="profileImage" disabled="disabled" class="dropify" data-default-file="/templogo/staff/web/<s:property value="#session.staffSessID"/>/<s:property value="#session.staffProfileImage"/>" /> -->			    
           							<table class="resilabortbl" align="center">
									<tbody>
									<tr>
           							<s:if test="#session.staffProfileImage!=null && #session.staffProfileImage!='' && #session.staffProfileImage!='null'">
           							<td><img class="lbrimg pointer hoverable" id="laborimgid" style="display: block;" src="/templogo/staff/mobile/<s:property value="#session.staffSessID"/>/<s:property value="#session.staffProfileImage"/>" onclick="toViewlargesizeimgwithsrc(this,'/templogo/staff/web/<s:property value="#session.staffSessID"/>/<s:property value="#session.staffProfileImage"/>')"></td>
           							</s:if>
									<s:else>
           							<td><img class="lbrimg pointer hoverable" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" id="laborimgid" style="width:100%;" onclick="toViewlargesizeimg(this)"></td>
           							</s:else>
           							</tr>
									</tbody>
									</table>	
           							</div> 
           							<div class="col s12 m8 l9">	
 													<div class="row">
  														  <div class="col m6">
																 <div class="col m5"><s:text name="Text.name"></s:text></div>
																<div class="col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.staffName"/></span></div>																 																														
														</div>
														<div class="col m6">
																 <div class="col m5"><s:text name="Text.mobileno" /> </div>
																<div class="col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.ISDcode"/>-<s:property value="staffRegObj.staffMobno"/></span>
															</div>  																
 														</div></div>
 															 <div class="row">
  														 <div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.customerreg.idproof" /> </div>
																 <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffIdcardtypname"/></span></div>												
															</div>														
														<div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.customerreg.idproofno" /> </div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="staffRegObj.staffIdcardno"/></span></div>
															</div>
														</div>	
														<div class="row">
														<div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.Companyname"/></div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="staffRegObj.staffcompanyname"/></span></div>
															</div>
														<div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.emailid" /></div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="staffRegObj.staffEmail"/></span>
															</div>
															</div> 
														</div>
													<div class="row">
  														  <div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.gender" /></div>
																 <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.staffGender"/></span>													
															</div>
															</div>
  														  <div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Category" /></div>
																 <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffcategoryname"/></span>													
															</div>
															</div>  																  
														</div>
														
											</div>
											</div>
											<div class="row">		
											<div class="col s12 m12 l12">			
														<div class="row">
															<div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Work Hours" /></div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.Workinghours"/></span>
															</div>
															</div>
  															 <div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.address" /></div>
																		<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.staffAddr"/></span>
																	</div>
																	</div>	  
														</div>
														
														<div class="row">
  														<div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.country" /></div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="staffCountry"/></span>
																</div>
															</div>
  																  <div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.state" /></div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffState"/> </span>																
															</div>
															</div>
														</div>
														<div class="row">
  														  <div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.city" /></div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffCity"/> </span>
															</div>
															</div>
  															<div class="input-field col m6">
																 <div class="input-field col m5"><s:text name="Text.customerreg.pincode" /></div>
																<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffpincode"/> </span>
															</div>
															</div>	 
														</div>															
															</div>	
															</div>						
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
    		<!-- dropify
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script> -->
<script type="text/javascript">
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "successeditstaff");
		$("#userCancelForm").submit();
	});	   
});
</script>
</body>
</html>
