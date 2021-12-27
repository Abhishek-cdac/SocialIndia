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
								<h5 class="breadcrumbs-title"><s:text name="Text.breadcrumb.companydetails" /></h5>
								<ol class="breadcrumbs left">
									<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
									<li><a href="companymgmt"><s:text name="Breadcrumb.manage" /></a></li>
									<li><a href="companymgmt"><s:text name="Breadcrumb.manage.cmpymgmt" /></a></li>
									<li class="active"><s:text name="Text.breadcrumb.companydetails" /></li>
								</ol>
								<div class="right">
								 <button id="gobckbtnid" type="button" class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back"><i class="<s:text name="button.icon.replycard"/>"></i>Go Back</button>
				</div>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
				<div class="card-panel">
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-social-domain tinysmall white-text text-accent-4"></i> <s:text name="Text.cmpny.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">				
			<div class="row margin">
				<div class="left resilaborview col s12 m4 l3">	
							<table class="resilabortbl" align="center">
							<tbody>
							<tr>
							<s:if test="#session.staffProfileImage!=null && #session.staffProfileImage!=''">
								<td><img class="lbrimg hoverable pointer" id="cmpnyid" style="display:block;" src="/templogo/company/web/<s:property value="cmpyRegObj.companyId"/>/<s:property value="#session.staffProfileImage"/>" onclick="toViewlargesizeimg(this)"></td>
							</s:if><s:else>
								<td><img class="lbrimg hoverable pointer" id="cmpnyid" style="display:block;" src="resources/images/amazon.jpg"></td>
							</s:else>
								</tr>
							</tbody>
							</table>			
           		</div>
            
				<div class="col s12 m8 l9">
								<div class="row margin">
 													<div class="input-field col m6">
                 									 <div class="row">
                 									  <div class="input-field col m5"><s:text name="Text.company.name" /></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.companyName"/></span></div>																
															</div>
															</div> 
															<div class="input-field col m6">
               											<div class="row">
																 <%-- <label class="col-md-4 control-label"><s:text name="Text.company.regno" /> </label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.cmpnyRegno"/></b> --%>													
															  <div class="input-field col m5"><s:text name="Text.company.regno" /></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.cmpnyRegno"/></span></div>   															            											        				
															</div>
															</div>
								</div>
  																
 								<div class="row margin">
 													<div class="input-field col m6"> 
 															 <div class="row">
 															  <div class="input-field col m5"><s:text name="Text.emailid"/></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.companyEmail"/></span></div>
  														 <%--  <label class="col-md-4 control-label"><s:text name="Text.emailid" /></label><label class="col-md-2 control-label"> :</label> <b> <s:property value="cmpyRegObj.companyEmail"/></b> --%>
																																 																														
															</div>
															</div>
  														  <div class="input-field col m6">
               											<div class="row">
               											  <div class="input-field col m5"><s:text name="Text.mobileno"/></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.isdCode"/>-<s:property value="cmpyRegObj.mobileNo"/></span></div>
															<%-- 	<label class="col-md-4 control-label"><s:text name="Text.mobileno"></s:text></label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.mobileNo"/></b> --%>
															</div>
															</div>
  																  
 								 </div>
 								 
 								 	<div class="row margin">
 								 		<div class="input-field col m6">
               											<div class="row">
               											<div class="input-field col m5"><s:text name="Status"/></div>
                  											 <div class="input-field col m7">: 
															
																 <span class="<s:text name="view.fontvalue.color" />">
																 <s:if test="cmpyRegObj.verifyStatus.equalsIgnoreCase('0')">
																 Unverified
																</s:if> 
																<s:else>
																Verified
																</s:else>
																  </span>
																  </div>
															</div>
										</div>
 								 	</div>
 					</div>
			</div>	
		</div>
		</li>
		<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>                    
<div class="collapsible-body padding10px" id="seconddivid" >
<div class="row margin">		
							<div class="col s12">	
													 <div class="row">
 													<div class="input-field col m6"> 
 													 <div class="row">
																<%-- <label class="col-md-4 control-label"><s:text name="uam.profile.address1" /> </label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.address1"/></b> --%>
														 <div class="input-field col m5"><s:text name="uam.profile.address1"/></div>
                  											 <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" /> "><s:property value="cmpyRegObj.address1"/></span></div>	
															</div>
															</div>
															<div class="input-field col m6">
               											<div class="row">
               											  <div class="input-field col m5"><s:text name="uam.profile.address2"/></div>
                  											 <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" /> "><s:property value="cmpyRegObj.address2"/></span></div>
																<%-- <label class="col-md-4 control-label"><s:text name="uam.profile.address2" /></label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.address2"/></b> --%>
																</div>
															</div>
  																  
														</div>
														<div class="row">
 													<div class="input-field col m6"> 
 													 <div class="row">
 													 <div class="input-field col m5"><s:text name="Text.country"/></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.countryname"/></span></div>
																<%-- <label class="col-md-4 control-label"><s:text name="Text.country" /></label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.countryname"/> </b> --%>																
															</div>
															</div>
														
  														<div class="input-field col m6">
               											<div class="row">
               											<div class="input-field col m5"><s:text name="Text.state"/></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.statename"/></span></div>
																<%-- <label class="col-md-4 control-label"><s:text name="Text.state" /></label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.statename"/> </b> --%>																
															</div>
															</div>
  																  
														</div>
														<div class="row">
 													<div class="input-field col m6"> 
 													 <div class="row">
 													 <div class="input-field col m5"><s:text name="Text.city"/></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.cityname"/></span></div>
															<%-- 	<label class="col-md-4 control-label"><s:text name="Text.city" /></label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.cityname"/> </b> --%>
															</div>
															</div>
  														 
  																 <div class="input-field col m6">
               											<div class="row">
               											<div class="input-field col m5"><s:text name="Text.customerreg.pincode"/></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.pincodename"/></span></div>
																	<%-- 	<label class="col-md-4 control-label"><s:text name="Text.customerreg.pincode" /></label><label class="col-md-2 control-label"> :</label><b>  <s:property value="cmpyRegObj.pincodename"/></b> --%>
																	</div>
																	</div>
														</div>
											<div class="row">
 												<div class="input-field col m12"> 
 													 <div class="row">
 													  <div class="input-field col m5"><s:text name="Key For Search"/></div>
                  																										
													</div>
												</div>
											</div>
											<div class="row">
 												<div class="input-field col m12"> 
 													 <div class="row">
 													   <div class="col m12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.keyforSrch"/></span></div>															
													</div>
												</div>
											</div>
											<div class="row">  														
 													<div class="input-field col m12"> 
 													 <div class="row">
 														<div class="input-field col m5"><s:text name="Description"/> </div>                  											 
													</div>													
													</div>
											</div>
											<div class="row">  														
 													<div class="input-field col m12"> 
													<div class="row">
														<div class="col m12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpyRegObj.descr"/></span></div>
													</div>
													</div>
											</div>
							</div>
				</div>
</div>
</li>
</ul>			
			
				</div>
															<div style="clear: both; height:5px;"></div>								
								</div>
								<s:form method="post" id="userCancelForm"></s:form>
								</section>
									</div>
								</div>
				
<s:form method="post" id="userCancelForm"></s:form>
				<jsp:include page="../common/footer.jsp"></jsp:include>
			<jsp:include page="../common/allbasicjs.jsp"></jsp:include> 
			  <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	<jsp:include page="../common/clorbox.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "companymgmt");
		$("#userCancelForm").submit();
	});	   
});

</script>
</html>
