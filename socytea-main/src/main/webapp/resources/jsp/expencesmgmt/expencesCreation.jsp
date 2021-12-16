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
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.util.Management.createexpences"/></h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
									<li><a href="expencesmgmt"><s:text name="Text.Utility.Management" /></a></li>
									<li><a href="expencesmgmt"><s:text name="Breadcrumb.util.expencesmgmt"/></a></li>
									<li class="active"><s:text name="Breadcrumb.util.Management.createexpences"/></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
<!--start container-->
<div class="container">
	<div class="card-panel">
	<form method="post" id="registerformid" name="registerformid" enctype="multipart/form-data" action="expencesCreateAction">
		<div class="row">  														
  		<div class="input-field col s12"> 
  			<div class="input-field col s6" id="divlastname">
				<label for="expences"><s:text name="Text.expences" /><span class="mandatory">*</span></label>
				<s:textfield name="expenceobj.expenceFor" id="expences" cssClass="form-control topicname expencesvalidate"/>
			</div>		
			<div class="input-field col s6">  				
				<label for="noproducts"><s:text name="Text.no.prodcut" /><span class="mandatory">*</span></label> 																														    
				<s:textfield name="expenceobj.noOfProduct" id="noproducts" cssClass="form-control topicname productvalidate"/>  																	            											        				
			</div>
		</div>
		</div>		
			
		<div class="row">  														
  		<div class="input-field col s12"> 
  			<div class="input-field col s6" id="divlastname">
				 <label for="amount"><s:text name="Text.amount" /><span class="mandatory">*</span></label> 															
				<s:textfield name="expenceobj.productPerAmt" id="amount" cssClass="form-control topicname amountvalidate"/>
			</div>		
			<div class="input-field col s6">  				
				<label for="totalamt"><s:text name="Text.totalamt" /><span class="mandatory">*</span></label>
				<s:textfield name="expenceobj.expenceTotAmt" id="totalamt" cssClass="form-control topicname totalamtvalidate"/>  																	            											        				
			</div>
		</div>
		</div>
		
		<div class="row">  														
  		<div class="input-field col s12"> 
  			<div class="input-field col s12" id="divlastname">
				<label for="expensdescid"><s:text name="Text.desc" /></label>
			   <textarea name="expenceobj.descr" class="materialize-textarea form-control custom-control tenderdescvalidate" id="expensdescid" rows="3" style="resize:none"></textarea>	
			</div>					
		</div>
		</div>		
			  <div style="clear: both; height: 10px;"></div>
		<div class="row"> 
		<div class="input-field col s12"> 
  			<div class="input-field col s12" id="divlastname">
				<button type="submit" id="submitbtn" name="submitbtn" class="<s:text name="button.color.submit"/>">
					<s:text name="Text.button.submit" /> <i class="<s:text name="button.icon.submitcard"/>"></i>
				</button>
				<button type="button" id="cancelbuttonid" class="<s:text name="button.color.cancel"/>"  onclick="cancelFunction();">
					 <s:text name="Text.button.cancel" /> <i class="<s:text name="button.icon.replycard"/>"></i>
				 </button>
		    </div>
		 </div></div>
					
	</form>
	<s:form method="post" id="userCancelForm"></s:form>		
	</div>
</div>
</section>
</div>
</div>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
</body>
<script type="text/javascript">
$(document).ready(function() {
$("#expences").keyup(function(){textValidate(this,'100','NV');});
$("#noproducts").keyup(function(){textValidate(this,'11','OI');});
$("#amount").keyup(function(){textValidate(this,'11','ECHO');});	 		
$("#totalamt").keyup(function(){textValidate(this,'11','ECHO');});	

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
		} 
	});


$("#expences").rules("add", {
		required : true,
		minlength:2,
		maxlength:100,
		messages : {
			required :"<s:text name="Error.customerreg.expences" />",
		}

	});
$("#noproducts").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.expencesreg.product" />",
	}
	
});
$("#amount").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.expencesreg.amount" />",
	}
	
});
$("#totalamt").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.expencesreg.totalamt" />",
	}
	
});
});

$("#noproducts").blur(function(){
	var counter=2;
	var ss=$("#noproducts").val();
	if(ss==0){
		 if(ss<counter){
			 var ii=1;
			 $("#noproducts").val(ii);
		 }else{
			 flag =true;
		 }
	}else{
		
	}
});

$("#amount").blur(function(){
	var counter=2;
	var ss=$("#amount").val();
	if(ss==0){
		 if(ss<counter){
			 var ii=1;
			 $("#amount").val(ii);
		 }else{
			 flag =true;
		 }
	}else{
		
	}
});
$("#totalamt").blur(function(){
	var counter=2;
	var ss=$("#totalamt").val();
	if(ss==0){
		 if(ss<counter){
			 var ii=1;
			 $("#totalamt").val(ii);
		 }else{
			 flag =true;
		 }
	}else{
		
	}
});

function togetId(){
	 var idname="";
     $(".token-input-token-facebook p").each(function(x){// new autocomplete
         idname+=$(this).attr("id")+",";           
     });
     return idname;
}
 function cancelFunction(){
	
	$("#userCancelForm").attr("action", "expencesmgmt");
	$("#userCancelForm").submit();
	$('#cancelbuttonid').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>  
</html>

	
