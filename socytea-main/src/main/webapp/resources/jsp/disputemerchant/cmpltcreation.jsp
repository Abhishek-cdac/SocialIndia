<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <style type="text/css">
  .input-field div.error{
    position: relative;
    top: -1rem;
    left: 0rem;
    font-size: 0.8rem;
    color:#FF4081;
    -webkit-transform: translateY(0%);
    -ms-transform: translateY(0%);
    -o-transform: translateY(0%);
    transform: translateY(0%);
  }
  .input-field label.active{
      width:100%;
  }
  .left-alert input[type=text] + label:after, 
  .left-alert input[type=password] + label:after, 
  .left-alert input[type=email] + label:after, 
  .left-alert input[type=url] + label:after, 
  .left-alert input[type=time] + label:after,
  .left-alert input[type=date] + label:after, 
  .left-alert input[type=datetime-local] + label:after, 
  .left-alert input[type=tel] + label:after, 
  .left-alert input[type=number] + label:after, 
  .left-alert input[type=search] + label:after, 
  .left-alert textarea.materialize-textarea + label:after{
      left:0px;
  }
  .right-alert input[type=text] + label:after, 
  .right-alert input[type=password] + label:after, 
  .right-alert input[type=email] + label:after, 
  .right-alert input[type=url] + label:after, 
  .right-alert input[type=time] + label:after,
  .right-alert input[type=date] + label:after, 
  .right-alert input[type=datetime-local] + label:after, 
  .right-alert input[type=tel] + label:after, 
  .right-alert input[type=number] + label:after, 
  .right-alert input[type=search] + label:after, 
  .right-alert textarea.materialize-textarea + label:after{
      right:70px;
  }
  </style> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description" content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
<meta name="keywords" content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
<title><s:text name="Text.Title"/></title>
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<!-- For Windows Phone -->


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
								<li><a href="tomerchanttbl"><s:text name="Breadcrumb.util.disputetomercht" /></a></li>
								<li class="active"><s:text name="text.createcomplaint" /></li>
						</ol>
					</div>
				</div>
				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
										<form  id="cmpltCreationFormId" action="tomerchantcmpltcreationactionform" method="post" enctype="multipart/form-data">
								
									
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
	$("#userCancelForm").attr("action", "tomerchanttbl");
	$("#userCancelForm").submit();
}
 
 
			
</script>

</html>
 