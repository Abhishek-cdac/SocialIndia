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
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
							<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="text.create.complaint" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="complaintsmgmt"><s:text name="Text.Utility.Management"></s:text></a></li>
							<li><a href="complaintsmgmt"><s:text name="Breadcrumb.manage.complaintmgmt" /></a></li>
							<li class="active"><s:text name="text.create.complaint" /></li>
						</ol>
					</div>
				</div>
				</div>
				</div>
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				<form  role="form" id="generalcmpltform"  name="generalcmpltform" enctype="multipart/form-data">

										    <div class="row">
											<div class="input-field col s6">
													<label for="offerNameid" class=" control-label"><s:text name="Text.adduser.emailid" /><span class="mandatory">*</span></label>
														<s:textfield cssClass="form-control emailvalidate" name="cmpltRegObj.complaintsOthersEmail" id="complaintsOthersEmail" ></s:textfield>	
											</div>
													<div class="input-field col s6">
													<label for="broucherNameid" class=" control-label"><s:text name="Text.complaint.subject" /></label>
														<s:textfield cssClass="form-control subjectvalidate" name="cmpltRegObj.complaintsTitle" 
												id="complaintsTitle"  ></s:textfield>
											</div>
											</div>
													<div class="row">
											<div class="input-field col s6">
													<label for="actualPrice" class="control-label"><s:text name="Text.complaintshtdesc"/></label>
													<s:textfield  id="shrtdesc" cssClass="form-control " name="cmpltRegObj.shrtdesc"  >	</s:textfield>
											</div>			
											
											</div>
											
									
											<div class="row">	
											<div class="input-field col s12">
													<label for="descriptionid" class=" control-label"><s:text name="Text.desc" /></label>
													<s:textarea cssClass="materialize-textarea descriptionid" name="cmpltRegObj.complaintsDesc"
													id="descriptionid" rows="3"></s:textarea>
											</div>
										</div>
										<div class="row">	
											<div class="input-field col s12">
										<div colspan="2" id="documentfiledivid">
										<label class="control-label lablenames active" for="documentfile" >Upload Images</label>
										<div class="clear height10px"></div>
										<input type="file" id="documentfile" name="documentfile" data-maxfilesize="<s:text name="upload.file.size" />" 
							class="mrgnleft15 tooltipster documentfileid" title="Choose File" accept="image/*" multiple/>
							<div id="documentfile-error" class="error manualscriptvalidation" style="display: none;">Actual Price Is Required</div>
										</div>
											</div>
										</div>
											 <div style="clear: both;margin-left: 0px;">
											 <button type="button" id="userEditButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
												<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
											</div>
											<!-- <s:hidden name="merchantobj.mrchntId" value="%{merchantobj.mrchntId}"></s:hidden>-->
											<s:hidden name="testblockid" value="1"></s:hidden>
											
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
    $('#generalcmpltform').validate({
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
	 $("#complaintsOthersEmail").rules("add", {
			required : true,
			messages : {
				required : "<s:text name="Error.customerreg.emailid"/>",
			}
		});
	 
	 $("#complaintsTitle").keyup(function() {
			textValidate(this, '150', 'OA');
				});
	 $("#complaintsOthersEmail").keyup(function() {
			textValidate(this, '100', 'EML');
		});		
	/*  $("#complaintsOthersEmail").blur(function(){			
			var rr = validateEmail($("#complaintsOthersEmail").val());	
			if(!rr){					 				
				swal("<s:text name="emailfrmt.err.title"/>", "<s:text name="email.frmt.err.msg"/>", "error");
				$("#complaintsOthersEmail").val('');
				$("#complaintsOthersEmail").focus();
			}				
		}); */
		$("#complaintsOthersEmail").blur(function(){toValiEmail(this.id);});
	  $("#userEditButtonId").click(function () {
		   	 var $fileUpload = $("#generalcmpltform input[type='file']");
		   	var files = $('#generalcmpltform :input[type=file]').get(0).files;
		   	var totsize=0;
		   	var maxsize=5*1024*1024;
		   	for (i = 0; i < files.length; i++) {
		   		totsize=totsize+files[i].size;
		   		
		   	}
		   	if(totsize>maxsize){
		   	 $("#documentfile-error").html('<s:text name="Text.filesize" />');
		  		$("#documentfile-error").show();
		        return false;
		   	} else {
		   		$("#generalcmpltform").attr("method", "post");
				$("#generalcmpltform").attr("action", "complaintsubmitaction");
				$("#generalcmpltform").submit();
		   	}
		   	
		});
	  
	
});

	function createcomplainFunction(){
		
	}
	function cancelFunction(){
		$("#userCancelForm").attr("action", "complaintsmgmt");
		$("#userCancelForm").submit();
		$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
	}
</script>


</html>	