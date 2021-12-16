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
					
					<div class="container">
					
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="text.createcomplaint" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
						<li class="active"><s:text name="Dispute Management" /></li>
								<li><a href="tolabortbl"><s:text name="Breadcrumb.util.disputetolabor" /></a></li>
								<li class="active"><s:text name="text.createcomplaint" /></li>
						</ol>
					</div>
				</div>
				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
										<form  id="cmpltCreationFormId" action="tolaborcmpltcreationactionform" method="post" enctype="multipart/form-data">
								
									
											<div class="row">
											<div class="input-field col s6">
												<label for="cardid"><s:text name="Text.complainttitle"/><span class="mandatory">*</span></label>
												<s:textfield name="disputemerchantobj.disputeTitle" id="complainttitle" cssClass="form-control typeahead tt-query "  autocomplete="off" spellcheck="false"/>
												
												<s:fielderror fieldName="disputemerchantobj.groupId.groupCode"/>
											</div>
												
											</div>
											<div class="row">
											<div class="input-field col s12">
												<label for="firstname"><s:text name="text.desccomplaint" /><span class="mandatory">*</span></label>
												
												<s:textarea cssClass="materialize-textarea" id="desccomplaint" name="disputemerchantobj.disputeDesc"></s:textarea>
												<s:fielderror fieldName="disputemerchantobj.userName"/>
											</div>	
												
											</div>	
											
											
										
											
											
											 <div style="clear: both;">
											<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.submit" />
							<i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button> 
										</div>
										</form>
										<s:form method="post" id="userCancelForm"></s:form>
										
									</div>
								</div>
							</div>
						</div>
						</div>
						</section>
						</div>
						</div>
						
			
		
		<jsp:include page="../common/footer.jsp"></jsp:include>
	 <jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 
	
</body>
<script>
$('#cmpltCreationFormId').validate({
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
		} 
	});
	
	$("#complainttitle").rules("add", {
		required : true,
		minlength :2,
		messages : {
			required : "<s:text name="Error.regdisputetomercht.cmplttitle" />",
		}
	});
	
	$("#desccomplaint").rules("add", {
		required : true,
		minlength :5,
		
		messages : {
			required : "<s:text name="Error.regdisputetomercht.cmpltdesctitle" />",
			//minlength : "Enter at least 5 characters"
		}
	});
 function cancelFunction(){
	$("#userCancelForm").attr("action", "tolabortbl");
	$("#userCancelForm").submit();
}
 
 
			
</script>

</html>
 