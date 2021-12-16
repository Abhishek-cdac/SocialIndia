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
								<h5 class="breadcrumbs-title"><s:text name="Edit.Forum" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="forummgmt"><s:text name="Breadcrumb.utility" /></a></li>
							<li><a href="forummgmt"><s:text name="Forum" /></a></li>	
							<li class="active"><s:text name="Edit.Forum" /></li>
						</ol>
					</div>
				</div>
				</div></div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				<form method="post" id="registerformid" name="registerformid" action="fourmUpdateAction">
 													<div class="row">  														
  																  <div class="input-field col s6"> 
																<label for="topicName"><s:text name="Topic Name" /><span class="mandatory">*</span></label>
																<s:textfield name="forumTopicData.topicsName" id="topicName" cssClass="form-control topicname"/>
															</div>
															 <div class="input-field col s6"> 
																<label for="keyForSearch">Key For Search<span class="mandatory">*</span></label>
																<s:textfield name="forumTopicData.keyForSearch" id="keyForSearch" cssClass="form-control keysearch"/>
															</div>
 															 </div>
												
														<div class="row">
														 <div class="input-field col s6">
																 <label for="validOn">Valid On<span class="mandatory">*</span></label> 																														     
															     <s:textfield id="validOn" type="text" cssClass="futuredateOfBirth" name="forumTopicData.fvalidDate" onkeyup="textValidate(this,'20','DT')"/>
															</div>
														</div>
														<div class="row">
											<div class="input-field col s12">
											<div class="form-group" id="groupnamedivid">
												<label for="description" class="control-label">Description<span class="mandatory">*</span></label>
											<s:textarea name="forumTopicData.topicsDesc" id="description" cssClass="materialize-textarea description" style="resize:none"></s:textarea>      												
											</div> 
											</div>
														</div>		
														<button type="submit" id="userEditButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
														<button type="button" id="cancelbuttonid" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>																						 		
													<s:hidden name="forumTopicData.topicsId" value="%{forumTopicData.topicsId}"></s:hidden>
														</form>
				
				</div>
					</div>
						</div>						
						</section>
						</div>
						</div>									
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<s:form method="post" id="userCancelForm"></s:form>
	
	<!-- ================================================
    Scripts
    ================================================ -->
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<script type="text/javascript">

$(document).ready(function() {
	
	$("#keyForSearch").keyup(function(){
	    textValidate(this,'200','ADP');
	 });
 
$('#registerformid').validate({
	 errorElement : 'div',
		 errorPlacement : function(error, element) {
			var placement = $(element).data('error');
			if (placement) {
				$(placement).append(error);
			} else {
				error.insertAfter(element);
			}
		},
		submitHandler : function(form) {
			toShowLoadingImgoverlay();
			return true;
			//toShowLoadingImgoverlay();
		} 
	});
$("#topicName").rules("add", {
	required : true,
	minlength: 5,
	maxlength: 400,
	messages : {
		required : "<s:text name="Error.forum.create.empty"/>",
		minlength:"<s:text name="Error.forum.create.name.length"/>",
		maxlength:"<s:text name="Error.forum.create.name.length"/>"
	}
});
	$("#validOn").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.forum.date"/>",
		}
	});
$("#keyForSearch").rules("add", {
	required : true,
	minlength: 3,
	maxlength: 500,
	messages : {
		required : "<s:text name="Error.forum.create.keysreach.empty"/>",
		minlength:"<s:text name="Error.forum.create.keysreach.length" />",
		maxlength:"<s:text name="Error.forum.create.keysreach.length" />"
	}
});
$("#description").rules("add", {
	required : true,
	minlength: 5,
	maxlength: 2000,
	messages : {
		required : "<s:text name="Error.forum.create.desc.empty"/>",
		minlength:"<s:text name="Error.forum.create.name.desc.length" />",
		maxlength:"<s:text name="Error.forum.create.name.desc.length" />"
	}
});
     $("#topicName").keyup(function(){textValidate(this,'400','OA');});
	 $("#keyForSearch").keyup(function(){textValidate(this,'500','ADP');});    

});

 function cancelFunction(){
	 $('#cancelbuttonid').addClass('<s:text name="cancel.button.animated.rollout"/>');		
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
		$("#userCancelForm").attr("action", "forummgmt");
		$("#userCancelForm").submit();
	
}
</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</html>	