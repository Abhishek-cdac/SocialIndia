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
  <link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
  <link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css?gh" type="text/css" rel="stylesheet" media="screen,projection">
	<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css?hg" />
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
								<h5 class="breadcrumbs-title">Post Tender</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li class="active"><a href="tendermgmt"><s:text name="Text.Utility.Management" /></a></li>
							<li><a href="tendermgmt"><s:text name="Tender" /></a></li>
									<li class="active">Post Tender</li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
			<div class="card-panel">
									<form method="post" id="formValidate" name="formValidate" enctype="multipart/form-data" action="tenderCreateAction">
 													<div class="row rowmargin12px" >
										                <div class="input-field col s6">
																<label for="tendername"><s:text name="Text.tendername" /><span class="mandatory">*</span></label>
																<s:textfield name="tenderobj.tenderName" id="tendername" cssClass="form-control topicname titlevalidate"/>
															
															</div>
														 
															<div class="input-field col s6">
												 <label for=datePicker>Tender Date<span class="mandatory">*</span></label> 																														
												<s:textfield  cssClass="futuredateOfBirth" name="tenderobj.tenderDate" id="datePicker"></s:textfield>
												</div>
															</div>
														
							<div class="row rowmargin12px" >
								<div class="input-field col s12">
									<label class="active" for="textarea1"><s:text name="Text.tenderdail" /><span class="mandatory">*</span></label>
									<textarea id="textarea1" name="tenderobj.tenderDetails" class="materialize-textarea" style="height: 10px;"></textarea>
								</div>

							</div>
											
                                <div class="row">
								<div class="input-field col s12">
									<label class="active"><s:text name="File" /></label><span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformatfiles"/>)</span>
									<input type="file" id="documentfile" name="documentfile" data-maxfilesize="<s:text name="upload.file.size" />"
							class="mrgnleft15 tooltipster documentfile" style="margin-left: 0px;" title="Choose File" accept="image/*, .csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel, .txt, .doc, .docs, .pdf" multiple/>
							<div id="documentfile-error" class="error manualscriptvalidation" style="display: none;">Actual Price Is Required</div>
								</div>

							</div>																				 		
								<div class="clear height10px"></div>
						          <div>
						                           <button type="submit" id="userCreateButtonId"  class="<s:text name="button.color.submit"/>"> <s:text name="Text.button.submit" />  <i class="<s:text name="button.icon.submitcard"/>"></i> </button>
						                           <button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							                       <s:text name="Text.button.cancel" /> <i class="<s:text name="button.icon.replycard"/>"></i> </button>
				                                  </div>	
													</form>
														</div>
	
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				
				<!--end container-->
			
			</section>

		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
  <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>

</body>
 
<script>
$(document).ready(function(){
	 $('input[type=file]').drop_uploader({
	        uploader_text: 'Drop files or image to upload, or',
	        browse_text: 'Browse',
	        browse_css_class: 'button button-primary',
	        browse_css_selector: 'file_browse',
	        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
	        file_icon: '<i class="pe-7s-file"></i>',
	        time_show_errors: 5,
	        layout: 'thumbnails'
    });
});
$(document).ready(function() {
$("#eventetitle").keyup(function(){textValidate(this,'100','NV');});
$("#eventshortdesc").keyup(function(){textValidate(this,'100','NV');});
$("#evedesc").keyup(function(){textValidate(this,'5000','NV');});	 		
$("#eventvideopath").keyup(function(){textValidate(this,'150','EML');});	
}); 

$("#lastname").keyup(function(){
	textValidate(this,'250','EML');
});

$("#userCreateButtonId").click(function () {
  	 var $fileUpload = $("#formValidate input[type='file']");
  	var files = $('#formValidate :input[type=file]').get(0).files;
  	var totsize=0;
  	var maxsize=5*1024*1024;
  	for (i = 0; i < files.length; i++)
  	{
  		totsize=totsize+files[i].size;
  		
  	}
  	if(totsize>maxsize){
  	 $("#documentfile-error").html('<s:text name="Text.filesize" />');
 		$("#documentfile-error").show();
       return false;
  	}
  	
});
$('#formValidate').validate({
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
	
$("#tendername").rules("add", {
		required : true,
		minlength :2,
		maxlength : 250,
		messages : {
			required :"<s:text name="Error.customerreg.tendertitle" />",
		}
		
	});
$("#textarea1").rules("add", {
	required : true,
	minlength :2,
	messages : {
		required :"<s:text name="Error.customerreg.tenderdesc" />",
	}
	
});
$("#datePicker").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.customerreg.tenderdate" />",
	}
	
});
function togetId(){
	 var idname="";
     $(".token-input-token-facebook p").each(function(x){// new autocomplete
         idname+=$(this).attr("id")+",";           
     });
     return idname;
}
function userdetailFun(){
	var idnam=togetId();
	var idval= $('.documentfile').val;
	if(idnam!=''){
	$("#inviteid").val(idnam);
	}else{
	}
}
function toremoveFBAuto(){
    $(".token-input-token-facebook").each(function(x){// new autocomplete
        $(this).remove();
    });
}
 function cancelFunction(){
	$("#userCancelForm").attr("action", "tendermgmt");
	$("#userCancelForm").submit();
}
</script>               
</html>
