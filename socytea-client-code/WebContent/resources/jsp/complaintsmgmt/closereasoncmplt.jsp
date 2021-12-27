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
                <h5 class="breadcrumbs-title"><s:text name="Text.close.complaint" /></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									
									<li><a href="complaintsmgmt"><s:text name="Text.Utility.Management"></s:text></a></li>
							<li><a href="complaintsmgmt"><s:text name="Breadcrumb.manage.complaintmgmt" /></a></li>
									<li class="active"><s:text name="Text.close.complaint" /></li>  
                   </ol>                              						
              </div>
              
            </div>
            
          </div>
          
        </div>
       	<div class="container">
       	<div class="card-panel">
										<form  id="ReasonCreationFormId" name="ReasonCreationFormId" action="Reasonaction" method="post" >
										    <div id="">										 
										    </div><div style="clear: both; height:5px;"></div>
										    
										   
           									
 											<div class="row">	
											<div class="col s12">
											<div class="row">
  														   <div class="input-field col s12">
																<label for="name">Reason</label>																 
																<textarea class="materialize-textarea descriptionid" id="descriptionid" rows="3" cols="" name="closereasonval"></textarea>													
															</div>		 
 											</div>	
											</div>
										</div>
						<div class="row">	
								  
                                           <div class="input-field">	
												<label class="active" for="cmplstsdivid">Status</label>
												<div id="cmplstsdivid">
													<input type="checkbox" name="statustypechk" class="myCheckbox statustype" id="stsid" value="3" checked/> <label for="stsid">Resolve</label> 
													<input type="checkbox" name="statustypechk" class="myCheckbox statustype" id="errid" value="2"/> <label for="errid">Declined</label>
												</div>												
												 												
											</div>   
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
					 <s:hidden name="cmpltRegObj.complaintsId" id="hiddencmpltid" />					
				</form>
				<s:form method="post" id="userCancelForm"></s:form>
									 </div>
				</div>
			</section>

		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>	
</body>
<script>
$(document).ready(function(){
	  // Basic
	   $('.myCheckbox').click(function() {
		    $(this).siblings('input:checkbox').prop('checked', false);
		    $(this).prop('checked', true);
		});	 
    
    // Translated
  /*  $('.dropify-fr').dropify({
        messages: {
           // default: 'Glissez-déposez un fichier ici ou cliquez',
            replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
            remove:  'Supprimer',
            error:   'Désolé, le fichier trop volumineux'
        }
    });*/

    // Used events
   
    $("#userCreateButtonId").click(function(){
				var targets = [];
				
				  
				   $("input[name='statustypechk']:checked").each(function(){
					   var memtyp=$(this).val();
					 
					   targets.push(memtyp);
					   });
				   toShowLoadingImgoverlay();
				   $("#statustypeid").val(targets);
				   
			 }); 
	 
   	
	$("#name").rules("add", {
   		required : true,
   		minlength : 3,
		maxlength : 50,
   		messages : {
   			required :"Reason is required",
   		}
   	});
   

   
});
$(document).ready(function(){
	$("#imgdivid").click(function(){
		$("#staffImage").click();
	});
	
	    
});


 function cancelFunction(){
	$("#userCancelForm").attr("action", "complaintsmgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>
