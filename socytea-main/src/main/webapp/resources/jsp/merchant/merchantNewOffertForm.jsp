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
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
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
								<h5 class="breadcrumbs-title"><s:text name="Text.Title.MrchantnewOffer" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="merchantMgmtTbl"><s:text name="Breadcrumb.manage" /></a></li>
							<li><a href="merchantMgmtTbl"><s:text name="Text.Merchant.Management" /></a></li>
							<li><a href="AddOfferForm"><s:text name="Breadcrumb.manage.merchantoff" /></a></li>
							<li class="active"><s:text name="Text.Title.MrchantnewOffer" /></li>
						</ol>
					</div>
					</div>
				</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<jsp:include page="../common/Alert.jsp" flush="true" />
				<div class="container">				
				<div id="jqueryvalidation" class="section">
				<div class="card-panel">				
				<form  id="merchantOfferCreationFormId" name="merchantOfferCreationFormId" action="addNewMerchantOffer" method="post" enctype="multipart/form-data">

										    <div class="row">
											<div class="input-field col s6">
													<label for="offerNameid" class=" control-label"><s:text name="Text.Title.MrchantOfferName" /><span class="mandatory">*</span></label>
														<s:textfield cssClass="form-control offerNameid" name="merchantutilObj.offerName" id="offerNameid"></s:textfield>	
											</div>
													<div class="input-field col s6">
													<label for="broucherNameid" class=" control-label"><s:text name="Text.Title.BroucherName" /><span class="mandatory">*</span></label>
														<s:textfield cssClass="form-control broucherNameid" name="merchantutilObj.broucherName" id="broucherNameid"></s:textfield>	
											</div>
											</div>
													<div class="row">
											<div class="input-field col s6">
													<label for="actualPrice" class="control-label"><s:text name="Text.Title.MrchantActulPrice"/><span class="mandatory">*</span></label>
													<s:textfield name="merchantutilObj.actualPrice" id="actualPrice" cssClass="form-control typeahead tt-query actualPriceId" autocomplete="off" />
											</div>			
											<div class="input-field col s6">
													<label for="offerPriceid" class=" control-label"><s:text name="Text.Title.MrchantOfferPrice" /><span class="mandatory">*</span></label>
													<s:textfield cssClass="form-control offerPriceid" name="merchantutilObj.offerPrice" id="offerPriceid"></s:textfield>
											</div>
											</div>
											<div class="row">			
											<div class="input-field col s6">
													<label for="offerValidFromid" class=" control-label"><s:text name="Text.Title.MrchantOffervalidfrom" /></label>
                									<s:textfield name="merchantutilObj.offerValidFrom" id="offerValidFromid" cssClass="futuredateOfBirth" onkeyup="textValidate(this,'20','DT')" /> 
											</div>
											<div class="input-field col s6">
													<label for="offerValidToid"><s:text name="Text.Title.MrchantOffervalidto" /></label>
                									<s:textfield name="merchantutilObj.offerValidTo" id="offerValidToid" cssClass="futuredateOfBirth" onkeyup="textValidate(this,'20','DT')" /> 
											</div></div>
									
											<div class="row">	
											<div class="input-field col s12">
													<label for="descriptionid" class=" control-label"><s:text name="Text.desc" /></label>
													<s:textarea cssClass="materialize-textarea descriptionid" name="merchantutilObj.description" id="descriptionid" rows="3"></s:textarea>
											</div>
										</div>
										<div class="row">	
											<div class="input-field col s12">
													<label class="active">Offer Images <span class="mandatory">*</span> </label><span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformat"/>)</span>
													<input type="file" id="offerfile" name="offerfile" data-maxfilesize="<s:text name="upload.file.size" />" class="mrgnleft15 tooltipster offerfileid" style="margin-left: 0px;" title="Choose File" accept="image/*, <s:text name="Text.image.extensions"/>" multiple/>
													<div id="offerfile-error" class="errors manualscriptvalidation" style="display: none;"></div>
											</div>
										</div>									
											 <div style="clear: both;margin-left: 5px;">
											    <button type="button" id="userEditButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
												<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
											</div>
											<s:hidden name="merchantobj.mrchntId" value="%{merchantobj.mrchntId}"></s:hidden>											
											<input type="hidden" name="MAX_FILE_SIZE" id="MAX_FILE_SIZE"  value="%{getText('upload.file.size')}" />	
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
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js?well"></script>
	<script type="text/javascript">
$(document).ready(function(){
    $('input[type=file]').drop_uploader({
        uploader_text: 'Drag and drop a image here, or ',
        browse_text: 'Browse',
        browse_css_class: 'button button-primary',
        browse_css_selector: 'file_browse',
        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
        file_icon: '<i class="pe-7s-file"></i>',
//        acceptedFiles: ".png, .jpg",
        time_show_errors: 5,
        layout: 'thumbnails'
    }); 
    $("#offerNameid").keyup(function(){
    	textValidate(this,'50','OA');
    });
    $("#actualPrice").keyup(function() {
		textValidate(this, '9', 'NM');
	});
    $("#offerPriceid").keyup(function() {
		textValidate(this, '9', 'NM');
	});
    $("#broucherNameid").keyup(function(){
    	textValidate(this,'50','AN');
    });
	
        
    $('#merchantOfferCreationFormId').validate({        	            
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
				 var rt = tovalifilesize();
				  if(rt){	
					toShowLoadingImgoverlay();
					return true;
				  } else {
					return false;
				 }
				//toShowLoadingImgoverlay();
			} 
		});
   
    			
	 $("#offerNameid").rules("add", {
			required : true,
			minlength:2,
			maxlength:50,
			messages : {
				required : "<s:text name="Text.Merchant.OfferName.Require"/>",
			}
		});
	 $("#actualPrice").rules("add", {
			required : true,
			minlength: 1,
			maxlength:9,
			messages : {
				required : "<s:text name="Text.Merchant.ActualPrice.Require"/>",
			}
		});
	 $("#broucherNameid").rules("add", {
			required : true,
			minlength: 2,
			maxlength: 50,
			messages : {
				required : "<s:text name="Text.Merchant.BroucherName.Require"/>",
			}
		});
	 $("#offerPriceid").rules("add", {
			required : true,
			minlength: 1,
			maxlength: 9,
			messages : {
				required : "<s:text name="Text.Merchant.OfferPrice.Require"/>",
			}
		});
	 
	  $("#userEditButtonId").click(function () {
		  		tovalifilesize();
				$("#merchantOfferCreationFormId").attr("action", "addNewMerchantOffer");
				$("#merchantOfferCreationFormId").attr("method", "post");
				$("#merchantOfferCreationFormId").attr("enctype", "multipart/form-data");
				$("#merchantOfferCreationFormId").submit();
		 		   		   
		});
	
});

function tovalifilesize(){		
   	var fileUpload = $("#merchantOfferCreationFormId input[type='file']");   	
   	var files = $('#merchantOfferCreationFormId :input[type=file]').get(0).files;
   	var totsize = 0;
   	var maxsize=5*1024*1024;
   	for (i = 0; i < files.length; i++) {
   		totsize=totsize+files[i].size;
   	}
   	if(totsize==0){
   		$("#offerfile-error").html('<s:text name="Text.Merchant.catlogimage.require" />');
   		$("#offerfile-error").show();
   		return false;
	} else{
		if(totsize>maxsize){
	   	    $("#offerfile-error").html('<s:text name="Text.filesize" />');
	  		$("#offerfile-error").show();
	        return false;
	   	} else{
	   	 return true;
		   	}
	}
}
 function cancelFunction(){
	$("#userCancelForm").attr("action", "AddOfferForm");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>	