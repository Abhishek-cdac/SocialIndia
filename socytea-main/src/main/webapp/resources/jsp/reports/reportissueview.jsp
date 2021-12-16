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
<title><s:text name="Text.Title"/></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />
<style>
#token-input-labornames {
	width: 100% ! important;
}
</style>
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
					<div class="header-search-wrapper grey hide-on-large-only">
						<i class="mdi-action-search active"></i> <input type="text"
							name="Search" class="header-search-input z-depth-2"
							placeholder="Explore Materialize">
					</div>
					<div class="container">
					<div class="row">
							<div class="col s12 m12 l12">
						<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.util.reportissueview" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="reportissuetbl"><s:text name="Breadcrumb.utility" /></a></li>
								<li><a href="reportissuetbl"><s:text name="Text.report.reportissue" /></a></li>
							<li class="active"><s:text name="Breadcrumb.util.reportissueview" /></li>
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
				<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> Report Issue Details</div>

<div class="collapsible-body padding10px" id="firstdivid">

										<div class="row">
											<div class="input-field col s6">
											<div class="row">
											
											 
  														  <div class="input-field col m5" > Name </div> <div class="input-field col m7"> : 
  														  <s:if test="(reportissueObj.userId!='' && reportissueObj.userId!='null' && reportissueObj.userId!='-1')">
  														    <a href="viewresidentdetails?deleteresidentid=<s:property value="reportissueObj.userId"/>" target="_blank" data-position="bottom" data-delay="10" data-tooltip="Click to View" class="pointer tooltipped" data-tooltip-id="afe82cc5-50ea-f1ba-0b91-472358326b9b">
  														  	<s:property value="reportissueObj.name" /></a>
  														  </s:if>
  														  <s:else>
  														  <span class="<s:text name="view.fontvalue.color" />"> <s:property value="reportissueObj.name" /></span>
  														  </s:else>
  														 	
  														  </div>	
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
												  
													<div class="input-field col m5"><s:text name="Text.customerreg.emailid"></s:text></div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="reportissueObj.emailId" /></span></div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
												<div class="input-field col m5" ><s:text name="Text.customerreg.mobileno"></s:text></div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="reportissueObj.mobileNo"/></span></div>
													 
												</div>
											</div>
											<div class="input-field col s6">
												<!-- <div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text name="Text.report.reportto"></s:text></div>
													<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="reportissueObj.reportTo"/></span></div>
												</div> -->
											</div>
										</div>
										
										<div class="row">
											<div class="input-field col m12">
												<div id="divfirstname" class="row">
													<div class="input-field col " style="width: 20.7%;"><s:text name="Text.desc"></s:text></div> <div
														class="input-field col m9"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="reportissueObj.descr"/></span></div> 
												</div>
											</div>
											
										</div>
										
										</div>
										</li>
										
										<s:if test="reportissueObj.status == 1">
										<li>
										<div class="collapsible-body padding10px" id="seconddivid" style="display:block;">
										<div class="row">
										<div class="input-field col s6">
												<div class="row">
						<div class="input-field col s12" >
						<button type="button" id="userCreateButtonId" onclick="solveactioncontent('<s:property value="reportissueObj.repId"/>','<s:property value="reportissueObj.mobileNo"/>');" class="<s:text name="button.color.submit"/>">
						SOLVE <i class="<s:text name="button.icon.submitcard"/>"></i></button>
						
					</div>	
					</div>
											</div>
											</div>
											</div>
										</li>
										</s:if>
										<s:else>
										<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="tooltipped mdi-action-verified-user tinysmall white-text text-lighten-2"></i> Issue Status</div>                    
<div class="collapsible-body padding10px" id="seconddivid" style="display:block;">

<div class="row">
							<s:if test="reportissueObj.status == 0">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text name="Text.status"></s:text></div>
														<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> Solved</span></div>
												</div>
											</div>
											</s:if> 
											<s:elseif test="reportissueObj.status == 2">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text name="Text.status"></s:text></div>
														<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> Declined</span></div>
												</div>
											</div>
											</s:elseif>
											<s:else>
											<div class="input-field col s6">
												<div class="row">
						<div class="input-field col s12" >
						<button type="button" id="userCreateButtonId" onclick="solveactioncontent('<s:property value="reportissueObj.repId"/>','<s:property value="reportissueObj.mobileNo"/>');" class="<s:text name="button.color.submit"/>">
						SOLVE <i class="<s:text name="button.icon.submitcard"/>"></i></button>
						
					</div>	
					</div>
											</div>
											</s:else>
										</div>
										
							</div>
										
											</li>
										</s:else>
										
										
												</ul>
										<div class="clear" style="height: 5px;"></div>
										
												</div>
												<div class="clear" style="height: 10px;"></div>
									
										</div>
										</div>
										</section>									
										</div>
										<s:form method="post" id="userCancelForm">
										<s:hidden name="reportissueObj.bookingId" id="bookinguniqid" ></s:hidden>
										<s:hidden name="uniqueId" id="bookuniqid" ></s:hidden>
										 <s:hidden name="committeecomments" id="iVOcommitteecomments" class="form-control " />
										</s:form>										
									</div>					
			<jsp:include page="../common/footer.jsp"></jsp:include>
			<jsp:include page="../common/clorbox.jsp"></jsp:include>
			<jsp:include page="../common/allbasicjs.jsp"></jsp:include>		
			<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script> 
</body>
<script>
$(document).ready(function(){	
	var approvests='<s:property value="reportissueObj.bookingStatus"/>';
	if(approvests== 0)
		{
		
		$("#firstdivid").css("border","0px");
		}
	else
		{
		
		}
});


$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "reportissuetbl");
		$("#userCancelForm").submit();
	});	   
});

 function solveactioncontent(id,mobno) {		
	var statusval = "";
	statusval = "solve";
	swal({
		title : "Are you sure want to " + statusval + "?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes",
		closeOnConfirm : false
	}, function(){ 
				 $("#userCancelForm").attr("action", "solvereportissueaction?uniqueId=" + id +"&mobno="+ mobno);
					$("#userCancelForm").submit();
	
		});
		
}	

</script>
</html>
