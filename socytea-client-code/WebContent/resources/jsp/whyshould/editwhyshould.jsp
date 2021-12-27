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
<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body>
 <div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
 </div>
  <jsp:include page="../common/header.jsp"></jsp:include>
 <div id="main">
    <div class="wrapper">
     <jsp:include page="../common/menuBar.jsp"></jsp:include>
      <section id="content">
        <div id="breadcrumbs-wrapper">
            <!-- Search for small screen -->
            		<jsp:include page="../common/searchexploremob.jsp"></jsp:include>	
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Why Should I Update Modify"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="whyshould"><s:text name="Breadcrumb.util.configmgmt" /></a></li>
					<li><a href="whyshould"><s:text name="Why Should I Update" /></a></li>
                    <li class="active"><s:text name="Why Should I Update Modify" /></li>             
                   </ol>                              						
              </div>              
            </div>            
          </div>          
        </div>
       	<div class="container">
<div class="card-panel">
	<form id="whyshoudedit" name="whyshoudedit" action="" method="post" enctype="multipart/form-data"  >
		  <div class="row">
			<div class="input-field col s6">
				<label for="whyshtdes" class="control-label"><s:text name="Why Should"/><span class="mandatory">*</span></label>
				<s:textfield name="whyshtdes" id="whyshtdes" cssClass="form-control whyshtdes" autocomplete="off"/>
			</div>
		</div>
        <div class="row">
			<div class="input-field col s12">
				<label for="whydes" class="control-label"><s:text name="Text.describtion"/><span class="mandatory">*</span></label>
				<s:textarea cssClass="materialize-textarea whydes" name="whydes" id="whydes" rows="2" style="resize:none;height:50px"></s:textarea>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
				<button type="button" id="createfacility" class="<s:text name="button.color.submit"/>" onclick="createwhymethod();">
				<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
				<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelwhy();">
				<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
			</div>	
			</div>
			<s:hidden name="whyshouldId" id="whyshouldId"></s:hidden>
	</form>
</div>
</div>
</section>
</div>
<s:form method="post" id="userCancelForm"></s:form>
</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>	
</body>
<script>

$('#whyshoudedit').validate({
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
	
$("#whyshtdes").rules("add", {
		required : true,
		minlength:2,
		maxlength:50,
		messages : {
			required :"<s:text name="Error.profile.whyshould.name" />",
		}
		
	});
$("#whydes").rules("add", {
	required : true,
	minlength:2,
	maxlength:500,
	messages : {
		required :"<s:text name="Error.profile.whyshould.desc" />",
	}
	
});
 function createwhymethod(){
	$('#whyshoudedit').attr("Method","Post");
	$('#whyshoudedit').attr("action","whyshoudmodify");
	$('#whyshoudedit').attr("enctype","multipart/form-data");
	$('#whyshoudedit').submit();
}
	
	 function cancelwhy() {
		$("#userCancelForm").attr("action", "whyshould");
		$("#userCancelForm").submit();
		$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
	}
	
</script>
</html>
