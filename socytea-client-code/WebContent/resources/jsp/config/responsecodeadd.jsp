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
                <h5 class="breadcrumbs-title"><s:text name="Text.add.responsecdmsg"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="responsecodetbl"><s:text name="Breadcrumb.util.configmgmt"/></a></li>
									<li><a href="responsecodetbl"><s:text name="Breadcrumb.config.respnsemgmt"/></a></li>  
									<li class="active"><s:text name="Text.add.responsecdmsg"/></li>  
                   </ol>                              						
              </div>
              
            </div>
            
          </div>
          
        </div>
       	<div class="container">
       	<div class="card-panel">
										<form  id="ResponseCreationFormId" name="ResponseCreationFormId" action="createresponse" method="post" >
										    <div id="">										 
										    </div><div style="clear: both; height:5px;"></div>
										    
										   
           									
 											<div class="row">	
											<div class="col s12">
											<div class="row">
  														   <div class="input-field col s12">
                                                       <label for="descriptionid">Response Message<span class="mandatory">*</span></label>
                                                    <div class="clear height25px"></div>
                                                          <s:textfield cssClass="materialize-textarea descriptionid" id="descriptionid" rows="3" cols="" name="iVOresponsename"></s:textfield>
                                                   </div>		 
 											</div>	
											</div>
										</div>
						<div class="row">	
								  
                                           <div class="input-field">	
											<label for="pidd" class="active">Status</label>	
											    <div id="pidd">										
												<input type="checkbox" name="statustypechk" class="myCheckbox statustype" id="stsid" value="00" checked/> <label for="stsid">Success</label> 
												<input type="checkbox" name="statustypechk" class="myCheckbox statustype" id="errid" value="01"/> <label for="errid">Error</label> 												
											</div></div>	   
											</div>
															<div style="clear: both; height:30px;"></div>																															
				<input type="hidden" name="mySelect" id="mySelect" class="form-control " />
						<div class="row">
						<div class="input-field col s6">
						<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>	
					</div>	
					 <s:hidden name="statustype" id="statustypeid" class="form-control " />							
				</form>
				<s:form method="post" id="userCancelForm"></s:form>
									 </div>
				</div>
			</section>

		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 
</body>
<script>
$(document).ready(function(){
	  // Basic
	   $('.myCheckbox').click(function() {
		    $(this).siblings('input:checkbox').prop('checked', false);
		    $(this).prop('checked', true);
		});	 
        // Used events
   
    $("#userCreateButtonId").click(function(){
				var targets = [];								  
				   $("input[name='statustypechk']:checked").each(function(){
					   var memtyp=$(this).val();					 
					   targets.push(memtyp);
					   });				
				   $("#statustypeid").val(targets);
				   
			 });	   	
});

$('#ResponseCreationFormId').validate({
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
	$("#descriptionid").rules("add", {
		required : true,
		minlength :5,
		maxlength :50,
		messages : {
			required : "<s:text name="Error.response.resmsg" />",
			//minlength : "Enter at least 5 characters"
		}
	});

 function cancelFunction(){
	$("#userCancelForm").attr("action", "responsecodetbl");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>
