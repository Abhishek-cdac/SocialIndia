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
<title><s:text name="Text.Title" /></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />
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
						<h5 class="breadcrumbs-title"><s:text name="Text.ViewCampaign.Management" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="donate"><s:text name="Text.Utility.Management" /></a></li>
							<li><a href="donate"><s:text name="Text.Campaign.Management"></s:text></a></li>
							<li class="active"><s:text name="Text.ViewCampaign.Management" /></li>
							</ol>							
							<div class="right">
								<button id="gobckbtnid" class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button>
							</div>
					</div>
				</div>							
				</div></div>
				<div class="container">				
				<div class="card-panel">
				 <form method="post" id="registerformid" name="registerformid" action="">
										<div class="row margin">
				<div class="left resilaborview col s12 m4 l3">	
							<table class="resilabortbl" align="center">
							<tbody>
							<tr>
							<s:if test="#session.staffProfileImage!=null && #session.staffProfileImage!=''">
								<td><img class="lbrimg hoverable pointer" id="cmpnyid" style="display:block;" src="/externalPath/donate/web/<s:property value="donateId"/>/<s:property value="#session.staffProfileImage"/>" onclick="toViewlargesizeimg(this)"></td>
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
                 									  <div class="input-field col m5"><s:text name="Text.adduser.town.ship.id" /></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="townshipName"/></span></div>																
															</div>
															</div> 
															<div class="input-field col m6">
               											<div class="row">
															  <div class="input-field col m5"><s:text name="Text.adduser.society.id" /></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="societyName"/></span></div>   															            											        				
															</div>
															</div>
								</div>
  																
 								<div class="row margin">
 													<div class="input-field col m6"> 
 															 <div class="row">
 															  <div class="input-field col m5"><s:text name="Institution Name"/></div>
                  											 <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="instituname"/></span></div>
																																 																														
															</div>
															</div>
  														  <div class="input-field col m6">
               											<div class="row">
               											  <div class="input-field col m5"><s:text name="Institution Contact No."/></div>
                  											 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="institucontact"/></span></div>
															</div>
															</div>
  																  
 								 </div>
 								 
 								 	<div class="row margin">
 								 		  <div class="input-field col m10">
               											<div class="row">
               											  <div class="input-field col m3"><s:text name="Institution Address"/></div>
                  											 <div class="input-field col s9 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="instituaddress"/></span></div>
															</div>
															</div>
 								 	</div>
 					</div>
			</div>	
										
										</form>
										</div>
										<div class="clear" style="height: 10px;"></div>
										
										</div>									
										</section>									
										</div>
										<s:form method="post" id="userCancelForm">
										<s:hidden name="flashId" id="flashId" ></s:hidden>
										</s:form>										
									</div>					
			<jsp:include page="../common/footer.jsp"></jsp:include>			
			<jsp:include page="../common/allbasicjs.jsp"></jsp:include>					
			<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script> 
</body>
<script>


 $(document).ready(function(){
		$("#gobckbtnid").click(function(){
			$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
			toShowLoadingImgoverlay();
			$("#userCancelForm").attr("action", "donate");
			$("#userCancelForm").submit();
		});	   
	});
</script>
</html>
