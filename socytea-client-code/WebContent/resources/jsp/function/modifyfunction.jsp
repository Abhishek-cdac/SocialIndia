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
<style type="text/css">
	#token-input-invitefriend { width: 100% ! important; }
	.token-input-list-facebook { min-height: 0px ! important;}
</style>
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
								<h5 class="breadcrumbs-title"><s:text name="Function Modify" /></h5>
								<ol class="breadcrumbs">
								<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="function"><s:text name="Breadcrumb.util.configmgmt"/></a></li>
									<li><a href="function"><s:text name="Breadcrumb.config.function"/></a></li>
									<li class="active"><s:text name="Function Modify" /></li>  
						</ol>
					</div>
				</div>
				</div>
				</div>
									
				<div class="container">
				 <div id="jqueryvalidation" class="section">
					<div class="card-panel">
					<form role="form" id="functionCreationFormId" method="post" enctype="multipart/form-data">
							<div class="col m12">				
							<div class="row">
								<div class="input-field col s6">
												
                                    <label for="funname" class=" control-label"><s:text name="Function Name"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control" name="funname" id="funname"  autocomplete="off"></s:textfield>
								</div>
									<div class="input-field col s6">
									<div id="genter_select" class="row">
											
                                               <label for="functiontype" class="active"><s:text name="Function Type"/><span class="mandatory">*</span></label>
											<s:radio id="functiontype" name="functiontype" list="#{'1' : 'Personal Event', '2' : 'Society Event', '3' : 'Commitee Meeting'}" value="functiontype"></s:radio>
											</div>	</div>								
							</div>
							<div class="row">
							<div id="addcloseimg1" class="input-field col s6">
												<div class="left marginleft5px margintop10px"> Add More : </div>
													<div class="left marginleft5px">
														<button id="addButton" class="btn-floating btn-small waves-effect waves-light deep-orange lighten-2 animated zoomIn" type="button" name="submitbtn" value="">
														<i class="mdi-content-add"></i></button></div>
													<div class="left marginleft5px">
														<button id="removeButton2" class="btn-floating btn-small waves-effect waves-light grey darken-3 animated zoomIn" type="button" onclick="removelastSportsDetail();" name="submitbtn" value="">
														<i class="mdi-content-remove"></i></button>
													</div>
								</div>
							</div>
							<s:iterator value="funList" status="count">
 										<div id="TextBoxDiv<s:property value="#count.Index+1"/>" >
 										<div class="row cnt" >
											<div class="input-field col s6">
												<label for="funtext" class=" control-label"><s:text name="Template Text"/><span class="mandatory">*</span></label>
												<s:textfield name="functiontext" value="%{functiontext}" id="functiontext" cssClass="form-control functionname" />
												<s:hidden name="txtid" value="%{txtid}" id="functiontext" cssClass="form-control functiontxtid" />
											</div>
  										</div>	
										</div>
						  </s:iterator>
						<div id='TextBoxesGroup2'><div id="TextBoxDiv2"></div></div>
						</div>
                        <div class ="clear">
						<button  id="userCreateButtonId" class="<s:text name="button.color.submit"/>" ><s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
						</div>
										<s:hidden name="functionid" cssClass="form-control" />	
											<s:hidden name="functionnametxt" id="functiontxt" cssClass="form-control" />	
											<s:hidden name="functiontxttxtid" id="functiontxttxtid" cssClass="form-control" />						
										</form>
										</div>
										<div style="clear: both; height:5px;"></div>
										<s:form method="post" id="userCancelForm"></s:form>
						</div>
						</div>
						</section>		
						</div>
						</div>						
	<jsp:include page="../common/footer.jsp"></jsp:include>		
	<!-- ================================================
    Scripts
    ================================================ -->
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	
<script type="text/javascript">
 var counter = 2;
 $(document).ready(function(){
	$("#addButton").click(function () {
	var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv2' + counter);	               
	newTextBoxDiv.after().html('<div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><label for="noofblocks_'+counter+'"><s:text name="Template Text" /></label><input type="text" name="functionname" id="noofblocks_'+counter+'" class="form-control functionname" /></div></div>');
	newTextBoxDiv.appendTo("#TextBoxesGroup2");		
    counter++;
});

$("#userCreateButtonId").click(function(){	
	functiontext(); 
	var aa = $('#functiontxt').val();
 	$("#functionCreationFormId").attr("action","updateAction"); 
	$("#functionCreationFormId").attr("method","Post");
	$("#functionCreationFormId").attr("enctype","multipart/form-data");
	$("#functionCreationFormId").submit();

});	
});

 $('#functionCreationFormId').validate({
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
$("#funname").rules("add", {
	required : true,
	minlength :5,
	maxlength :50,
	messages : {
		required : "<s:text name="Error.functionname.funname" />",
		//minlength : "Enter at least 5 characters"
	}
});
$("#funtext").rules("add", {
	required : true,
	minlength :5,
	maxlength :200,
	messages : {
		required : "<s:text name="Error.functiontext.funtemp" />",
		//minlength : "Enter at least 5 characters"
	}
});
 
 function removelastSportsDetail(){
    if(counter==1){
         return false;
      }   
   		counter--;
       $("#TextBoxDiv2" + counter).remove();
	}
 
function functiontext(){
	var targets = [];
	   $.each($(".functionname"), function(){
	   			targets.push($(this).val());
	   });
	   $("#functiontxt").val(targets.join("!_!"));
	   
		 var targets1 = [];
		   $.each($(".functiontxtid"), function(){
		   targets1.push($(this).val());
		   });
		   $("#functiontxttxtid").val(targets1.join("!_!"));
	  
}

 function cancelFunction(){
	$("#userCancelForm").attr("action", "function");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}

</script>
</html>											