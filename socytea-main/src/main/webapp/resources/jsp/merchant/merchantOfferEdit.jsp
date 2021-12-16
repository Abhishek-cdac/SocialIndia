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
								<h5 class="breadcrumbs-title"><s:text name="Text.Title.Merchantmodify.offer" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="merchantMgmtTbl"><s:text name="Breadcrumb.manage" /></a></li>
							<li><a href="merchantMgmtTbl"><s:text name="Text.Merchant.Management" /></a></li>
							<li><a href="AddOfferForm"><s:text name="Breadcrumb.manage.merchantoff" /></a></li>
							<li class="active"><s:text name="Text.Title.Merchantmodify.offer" /></li>
						</ol>
					</div>
				</div>
				</div>
								</div>
				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				<form  id="merchantOfferCreationFormId" name="merchantOfferCreationFormId" action="editNewMerchantOffer" method="post" enctype="multipart/form-data">
										    <div class="row">
											<div class="input-field col s6">
													<label for="offerNameid" class=" control-label"><s:text name="Text.Title.MrchantOfferName" /><span class="mandatory">*</span></label>
														<s:textfield cssClass="form-control offerNameid" name="merchantUtilobj.offerName"
													id="offerNameid" value="%{merchantUtilobj.offerName}"></s:textfield>	
											</div>
													<div class="input-field col s6">
													<label for="broucherNameid" class=" control-label"><s:text name="Text.Title.BroucherName" /><span class="mandatory">*</span></label>
														<s:textfield cssClass="form-control broucherNameid" name="merchantUtilobj.broucherName"
													id="broucherNameid" value="%{merchantUtilobj.broucherName}"></s:textfield>	
											</div>
											</div>
													<div class="row">
											<div class="input-field col s6">
													<label for="actualPrice" class="control-label"><s:text name="Text.Title.MrchantActulPrice"/><span class="mandatory">*</span></label>
													<s:textfield name="merchantUtilobj.actualPrice" id="actualPrice" cssClass="form-control typeahead tt-query actualPriceId" value="%{merchantUtilobj.actualPrice}" autocomplete="off" />
											</div>			
											<div class="input-field col s6">
													<label for="offerPriceid" class=" control-label"><s:text name="Text.Title.MrchantOfferPrice" /><span class="mandatory">*</span></label>
													<s:textfield cssClass="form-control offerPriceid" name="merchantUtilobj.offerPrice"
													id="offerPriceid" value="%{merchantUtilobj.offerPrice}"></s:textfield>
											</div>
											</div>
											<div class="row">			
											<div class="input-field col s6">
													<label for="offerValidFromid"><s:text name="Text.Title.MrchantOffervalidfrom" /></label>
                												<s:textfield name="merchantUtilobj.offerValidFrom" id="offerValidFromid" value="%{merchantUtilobj.offerValidFrom}" cssClass="futuredateOfBirth" onkeyup="textValidate(this,'20','DT')" /> 
											</div>
											<div class="input-field col s6">
												<div class="form-group" id="mobileNodivid">
													<label  for="offerValidToid"><s:text name="Text.Title.MrchantOffervalidto" /></label>
                												<s:textfield name="merchantUtilobj.offerValidTo" id="offerValidToid" value="%{merchantUtilobj.offerValidTo}" cssClass="futuredateOfBirth" onkeyup="textValidate(this,'20','DT')" /> 
												</div>
											</div></div>
									
											<div class="row">
											<div class="input-field">
											<label class="active" for="dlimgid">Delete Old Images</label>
											<div id="dlimgid">								
											 <div class="left pointer"  onclick="deleteimage('deleteimage');">
											 <input id="deleteimage" type="checkbox" class="myCheckbox"   value="yes"/><label  for="deleteimage">Yes</label></div>
                                             <div class="left pointer" onclick="deleteimage('addnewimage');">
                                             <input type="checkbox" id="addnewimage" class="myCheckbox"  value="no" checked /><label for="addnewimage">No</label></div>
											</div>			
											</div>
											</div>
											<div class="clear height10px"></div>
											<div class="clear height10px"></div>
											<div class="row">	
											<div class="input-field col s12">
												<div class="form-group" id="mrchFnamedivid">
													<label for="descriptionid" class=" control-label"><s:text name="Text.desc" /></label>
													<s:textarea cssClass="materialize-textarea" name="merchantUtilobj.description"
													id="descriptionid" rows="3"></s:textarea>
												</div>
											</div>
											
										</div>
										<div class="row">	
											<div class="input-field col s12" >
											<div class="form-group" id="offerfiledivid">
										<div id="offerfiledivid">
												<label class="lablenames active" for="offerfile">Offer Images</label><span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformat"/>)</span>
												<input type="file" id="offerfile" name="offerfile" data-maxfilesize="<s:text name="upload.file.size" />" class="mrgnleft15 tooltipster offerfile" style="margin-left: 0px;" title="Choose File" accept="image/*, <s:text name="Text.image.extensions"/>" multiple/>
												<div id="offerfile-error" class="errors manualscriptvalidation" style="display: none;"></div>
										</div>
										</div>
											</div>
										</div>
										
											 <div style="clear: both;margin-left: 5px;">
											 <button type="submit" id="userEditButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
												<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
											</div>
											<s:hidden name="merchantobj.mrchntId" value="%{merchantUtilobj.mrchntId}"></s:hidden>
											<s:hidden name="merchantUtilobj.merchantUtilId" value="%{merchantUtilobj.merchantUtilId}"></s:hidden>
											<s:hidden name="isdeleteImage" id="isdeleteImage" value="no"></s:hidden>
										</form>
				
				
						</div>
						</div>
						</div>
						</section>
						</div>
						</div>								
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<s:form method="post" id="userCancelForm"></s:form>
	 <link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
	<!-- ================================================
    Scripts
    ================================================ -->
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<jsp:include page="../common/googlemap.jsp"></jsp:include>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	$('.myCheckbox').click(function() {
		   $(this).siblings('input:checkbox').prop('checked', false);
		   $(this).prop('checked', true);
		});
    $('input[type=file]').drop_uploader({
        uploader_text: 'Drag and drop a image here, or ',
        browse_text: 'Browse',
        browse_css_class: 'button button-primary',
        browse_css_selector: 'file_browse',
        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
        file_icon: '<i class="pe-7s-file"></i>',
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
				toShowLoadingImgoverlay();
				return true;
				//toShowLoadingImgoverlay();
			} 
		});
	 $("#offerNameid").rules("add", {
			required : true,
			minlength: 2,
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
		   	 var $fileUpload = $("#merchantOfferCreationFormId input[type='file']");
		   	var files = $('#merchantOfferCreationFormId :input[type=file]').get(0).files;
		   	var totsize=0;
		   	var maxsize=5*1024*1024;
		   	for (i = 0; i < files.length; i++)
		   	{
		   		totsize=totsize+files[i].size;
		   		
		   	}
		   	if(totsize>maxsize){
		   	 $("#offerfile-error").html('<s:text name="Text.filesize" />');
		  		$("#offerfile-error").show();
		        return false;
		   	}
		   	
		});
	  
});
function deleteimage(th){
	   $(".myCheckbox").prop('checked', false);
	   $("#"+th).prop('checked', true);
	   var isdel=$("#"+th).val();
	   $("#isdeleteImage").val(isdel);
	 }
 function cancelFunction(){
	$("#userCancelForm").attr("action", "AddOfferForm");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>


</html>	