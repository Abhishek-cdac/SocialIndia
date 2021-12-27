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
<!--dropify-->
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
								<h5 class="breadcrumbs-title">Post eNews</h5>
								<ol class="breadcrumbs left">
								<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
									<li class="active"><a href="eNewsmgmt"><s:text name="Text.Utility.Management" /></a></li>
							<li><a href="eNewsmgmt"><s:text name="eNews" /></a></li>
									<li class="active">Post eNews</li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
			<div class="card-panel">
										<form role="form" id="eNewform"  method="post" enctype="multipart/form-data" action="eNewssubmitaction" >
											 <div class="row rowmargin12px" >
										                <div class="input-field col s6">
																<label for="eNewtitle"><s:text name="Text.title"/><span class="mandatory">*</span></label>															
																 															
															<s:textfield cssClass="form-control emailvalidate" name="eNewsobj.ivrBnTITLE" id="eNewtitle" autocomplete="off"></s:textfield>
															</div>								
											
											<div class="input-field col s6">
												<label class=" control-label" for="complaintsTitle"><s:text name="Text.Utility.Management.eventshort" /></label>
												<s:textfield cssClass="form-control subjectvalidate" name="eNewsobj.ivrBnSHRTDESCRIPTION" id="complaintsTitle"  autocomplete="off"></s:textfield>
												</div>	
											</div>
									
										             <div class="row rowmargin12px" >
										                <div class="input-field col s12">
												<label class=" control-label" for="complaintsDesc"><s:text name="Text.desc" /></label>
												<textarea id="complaintsDesc" name="eNewsobj.ivrBnDESCRIPTION" autocomplete="off" class=" materialize-textarea form-control custom-control" style="height: 10px;"></textarea>   
											
									         </div>
									         </div>									        
									          
                            <div class="row">
								<div class="input-field col s12">
									<label class="active"><s:text name="File" /></label><span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformatfiles"/>)</span>
									<input type="file" id="documentfile" name="documentfile" data-maxfilesize="<s:text name="upload.file.size" />"
							class="mrgnleft15 tooltipster documentfile" style="margin-left: 0px;" title="Choose File" accept="image/*" multiple/>
							   <div id="documentfile-error" class="error manualscriptvalidation" style="display: none;">Actual Price Is Required</div>
								</div>

							</div>	
								
                                    <div style="clear: both; height: 10px;"></div>
						          <div style="margin-left: 0px;">
						                           <button type="submit" id="userCreateButtonId" 
								                   class="<s:text name="button.color.submit"/>">
								                   <s:text name="Text.button.submit" />
								                     <i class="<s:text name="button.icon.submitcard"/>"></i>
							                         </button>
						                           <button type="button" id="userCancelButtonId"
							                         class="<s:text name="button.color.cancel"/>"
							                              onclick="cancelFunction();">
							                       <s:text name="Text.button.cancel" />
							                       <i class="<s:text name="button.icon.replycard"/>"></i>
						                                 </button>
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
<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>

</body>
	
<script type="text/javascript">

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
	
	$("#userCreateButtonId").click(function () {
	  	 var $fileUpload = $("#eNewform input[type='file']");
	  	var files = $('#eNewform :input[type=file]').get(0).files;
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
    
  
     	
	$('#eNewform').validate({
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
		
	$("#eNewtitle").rules("add", {
			required : true,
			minlength:5,
		     maxlength:100,				
			messages : {
				required :"<s:text name="Error.reg.title" />",
			}
			
		});
     	
});


$("#complaintsTitle").keyup(function(){
	textValidate(this,'500','EML');
});

$("#shrtdesc").keyup(function(){
	textValidate(this,'100','EML');
});




 function cancelFunction(){
	$("#userCancelForm").attr("action", "eNewsmgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
 
</script>
</html>