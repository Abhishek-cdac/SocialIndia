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
						<h5 class="breadcrumbs-title"><s:text name="Text.Utility.feedback.Management.detail" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Dashboard" /></a></li>
								<li><a href="feedbackMgmt"><s:text name="Text.Utility.Management"/></a></li>
							<li><a href="feedbackMgmt"><s:text name="Text.Utility.feedback.Management"/></a></li>
							<li class="active"><s:text name="Text.Utility.feedback.Management.detail"></s:text></li>
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
										<div class="row">
											<div class="input-field col s6">
											<div class="row">																					 
  												<div class="input-field col m5" ><s:text name="Text.name" /></div> <div class="input-field col m7"> :   													
  												<span class="<s:text name="view.fontvalue.color" />"><s:property value="feedback.dbkby_fname"/> </span>
  												</div>
											</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">												 
													<div class="input-field col m5"><s:text
															name="Text.Utility.feedback.Management.FeebbackTo" /></div> <div
														class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="feedback.feedbacktoname"/>[<s:property value="feedback.feed_group"/>]</span></div> 
												
												</div>
											</div>
										</div>
				<div class="row">
				<div class="input-field col s6">
					<div id="divfirstname" class="row">
							<div class="input-field col m5"><s:text name="Text.Utility.feedback.Management.EntryDate" /></div> 
							<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="feedbackdeate"/></span></div> 
					</div>
				</div>
				<div class="input-field col s6">
				<div id="divfirstname" class="row">
						<div class="input-field col m5"><s:text name="Text.Utility.feedback.Rating.Management" /></div>  
						<s:if test="feedback.feedbackrating==0">
																<div class="input-field col m7 "><div>:</div><div style="margin: -33px 0px 0px;"><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
																<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i></div>
																<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Not Rating</span></div></div>
						</s:if> 
						<s:elseif test="feedback.feedbackrating==1">
																<div class="input-field col m7 "><div>:</div><div style="margin: -33px 0px 0px;"><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
																<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i></div>
																<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Very Bad</span></div></div>
					    </s:elseif>
						<s:elseif test="feedback.feedbackrating==2">
								<div class="input-field col m7 "><div>:</div><div style="margin: -33px 0px 0px;"><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
								<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i></div>
								<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Bad</span></div></div>
						</s:elseif>
						<s:elseif test="feedback.feedbackrating==3">
								<div class="input-field col m7 "><div>:</div><div style="margin: -33px 0px 0px;"><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
								<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i></div>
								<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Not Good</span></div></div>
						</s:elseif>
						<s:elseif test="feedback.feedbackrating==4">
																	<div class="input-field col m7 "><div>:</div><div style="margin: -33px 0px 0px;"><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
																<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i></div>
														<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Good</span></div></div>
						</s:elseif>
						<s:elseif test="feedback.feedbackrating==5">
																	<div class="input-field col m7 "><div>:</div><div style="margin: -33px 0px 0px;"><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
																<i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i></div>
														<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Excellent</span></div></div>
						</s:elseif>
						<s:else>
																<div class="input-field col m7 "><div>:</div><div style="margin: -33px 0px 0px;"><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
																<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i></div>
														<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Not Rating</span></div></div>
						</s:else>
												</div>
			</div>
										</div>
										
										<div class="row">
											<div class="input-field col s12">
												<div id="divfirstname" class="row">
													<div class="input-field col m2"><s:text name="Text.Utility.feedback.Management" /> : </div> 													
												</div>
											</div>	
										</div>
										<div class="row">
											<div class="col s12">
												<div id="divfirstname" class="row">												
													<div class="input-field col m9"><span class="<s:text name="view.fontvalue.color" />"><s:property value="feedback.feedbacktext"/></span></div>
												</div>
											</div>	
										</div>
										
										<div class="clear" style="height: 5px;"></div>
										</form>
										</div>
										<div class="clear" style="height: 10px;"></div>
										
										</div>									
										</section>									
										</div>
										<s:form method="post" id="userCancelForm">
										<s:hidden name="cmpltRegObj.complaintsId" id="cmpltuniqid" ></s:hidden>
										</s:form>										
									</div>					
			<jsp:include page="../common/footer.jsp"></jsp:include>			
			<jsp:include page="../common/allbasicjs.jsp"></jsp:include>					
			<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script> 
</body>
<script>

 function cancelFunction(){
	$("#userCancelForm").attr("action", "feedbackMgmt");
	$("#userCancelForm").submit();
}
 $(document).ready(function(){
		$("#gobckbtnid").click(function(){
			$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
			toShowLoadingImgoverlay();
			$("#userCancelForm").attr("action", "feedbackMgmt");
			$("#userCancelForm").submit();
		});	   
	});
</script>
</html>
