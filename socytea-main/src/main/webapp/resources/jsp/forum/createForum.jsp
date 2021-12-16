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
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->


<!-- CORE CSS-->
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
					<div class="container">
							<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="Create.Forum" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li class="active"><a href="forummgmt"><s:text name="Text.Utility.Management" /></a></li>
							<li><a href="forummgmt"><s:text name="Forum" /></a></li>
							<li class="active"><s:text name="Create.Forum" /></li>
						</ol>
					</div>
				</div>
				</div>
			</div>
			<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				  <form method="post" id="registerformid" name="registerformid" action="fourmCreateAction">
					<div class="row">
						<div class="input-field col s6">
							<label class=" control-label"><s:text name="Topic Name" /><span class="mandatory">*</span></label>
								<s:textfield name="forumTopicData.topicsName" id="lastname" cssClass="form-control topicname" />
								</div>
									<div class="input-field col s6">
										<label class=" control-label">Key For Search<span class="mandatory">*</span></label>
												<s:textfield name="forumTopicData.keyForSearch" id="keysearch" cssClass="form-control keysearch" />												
											</div>
											</div>
													<div class="row">
											<div class="input-field col s6">
											 <label  class="control-label">Valid On<span class="mandatory">*</span></label> 														
													<s:textfield id="frmdateid" type="text" cssClass="futuredateOfBirth" name="forumDate" onkeyup="textValidate(this,'20','DT')"/>
											</div>			
											</div>
											<div class="row">	
											<div class="input-field col s12">
													<label for="descriptionid" class=" control-label"><s:text name="Text.desc" /><span class="mandatory">*</span></label>
													<s:textarea cssClass="materialize-textarea descriptionid" name="forumTopicData.topicsDesc"
													id="descriptionid" rows="3"></s:textarea>
											</div>
										</div>
											 <div style="clear: both;margin-left: 0px;">
											 <button type="submit" id="userEditButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
												<button type="button" id="cancelbuttonid" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
											</div>
											<s:hidden name="merchantobj.mrchntId" value="%{merchantobj.mrchntId}"></s:hidden>
										</form>
						</div>
						</div>
						</div>
						</section>
						</div>
						</div>
		<s:form method="post" id="userCancelForm"></s:form>
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<s:hidden name="merchantobj.mrchntId" id="merhcnatId"></s:hidden>
	
	<!-- ================================================
    Scripts
    ================================================ -->
    <link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	    $('input[type=file]').drop_uploader({
	        uploader_text: 'Drop files to upload, or',
	        browse_text: 'Browse',
	        browse_css_class: 'button button-primary',
	        browse_css_selector: 'file_browse',
	        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
	        file_icon: '<i class="pe-7s-file"></i>',
	        time_show_errors: 5,
	        layout: 'thumbnails'
	    });
	    
	    $("#keysearch").keyup(function(){
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
	$("#lastname").rules("add", {
		required : true,
		minlength :5,
		maxlength :400,
		messages : {
			required : "<s:text name="Error.forum.create.empty"/>",
			minlength:"<s:text name="Error.forum.create.name.length"/>",
			maxlength:"<s:text name="Error.forum.create.name.length"/>"
		}
	});
 	$("#keysearch").rules("add", {
		required : true,
		minlength :3,
		maxlength :500,
		messages : {
			required : "<s:text name="Error.forum.create.keysreach.empty"/>",
			minlength:"<s:text name="Error.forum.create.keysreach.length" />",
			maxlength:"<s:text name="Error.forum.create.keysreach.length" />"
		}
	});
 	$("#frmdateid").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.forum.date" />",
		}
	});
	 
 	$("#descriptionid").rules("add", {
		required : true,
		minlength :5,
		maxlength :1000,
		messages : {
			required : "<s:text name="Error.forum.create.desc.empty"/>",
			minlength:"<s:text name="Error.forum.create.name.desc.length" />",
			maxlength:"<s:text name="Error.forum.create.name.desc.length" />"
		}
	});
 	$("#lastname").keyup(function(){textValidate(this,'400','AN');});
 	$("#keysearch").keyup(function(){textValidate(this,'500','ADP');});
	 
	
});
	function cancelFunction(){
		$("#userCancelForm").attr("action", "forummgmt");
		$("#userCancelForm").submit();
		$('#cancelbuttonid').addClass('<s:text name="cancel.button.animated.rollout"/>');		
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
	}
</script>


</html>	