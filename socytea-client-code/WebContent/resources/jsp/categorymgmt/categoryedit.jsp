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
                <h5 class="breadcrumbs-title"><s:text name="Category Modify"/></h5>
                <ol class="breadcrumbs left">
                    
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="category"><s:text name="Breadcrumb.util.configmgmt"/></a></li>
									<li><a href="category"><s:text name="Breadcrumb.util.Categorymgmt" /></a></li>  
									<li class="active"><s:text name="Category Modify" /></li>               
                   </ol>                              						
              </div>
              
            </div>
            
          </div>
          
        </div>
       	<div class="container">
       	<div class="card-panel">
										<form  id="userCreationFormId" name="labourEditSubmit" action="categoryEditSubmitaction" method="post"  enctype="multipart/form-data" >
										
		<div id="profilehidden" style="display:block;">
			<div class="row">									
           		<div class="col s12">									
					<div class="row">
  									<div class="input-field col s12">
										<label for="name"><s:text name="Text.add.category"/><span class="mandatory">*</span></label>																 
										<s:textfield name="categoryobj.iVOCATEGORY_NAME" id="name" cssClass="form-control firstnamevalidate"/> 															
									</div>
  																
 					</div>
 					<div class="row">	
						<div class="input-field col s12">
							<div id="documentfiledivid">
								<label class="active"><s:text name="Text.category.image"/></label>
								<div class="clear height10px"></div>				
								<input type="file" name="staffImage" id="staffImage" class="dropify" data-default-file="/templogo/category/web/<s:property value="#session.categorySessID"/>/<s:property value="#session.staffProfileImage" />"/>	
							</div>
						</div>
					</div>
			</div>
									
			</div>
		</div>

				<s:hidden name="categoryobj.iVOCATEGORY_ID" id="deletelaboridEdit"></s:hidden>			
												
			<div class="row">
						<div class="input-field col s6">
						<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>	
			</div>
									</form>	
										<s:form method="post" id="userCancelForm"></s:form>
									 </div></div>
				
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
    $('.dropify').dropify({
        messages: {
        	"default":"Drag and drop a image here or click",
        	replace:"Drag and drop or image to replace",
        	remove:"Remove",
        	error:"Sorry, this file is too large"
        }
    });
    
    // Used events
    var drEvent = $('.dropify-event').dropify();

    drEvent.on('dropify.beforeClear', function(event, element){
        return confirm("Do you really want to delete \"" + element.filename + "\" ?");
    });

    drEvent.on('dropify.afterClear', function(event, element){
        alert('File deleted');
    });
    $('#userCreationFormId').validate({
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
	
$("#name").rules("add", {
		required : true,
		minlength : 2,
	    maxlength : 50,
		messages : {
			required :"Category is required",
		}
	});
	
});
$(document).ready(function(){
	$("#imgdivid").click(function(){
		$("#staffImage").click();
	});
	
	
			
});
	
	
var loadFile = function(event) {
	var output = document.getElementById('myImg');
	output.src = URL.createObjectURL(event.target.files[0]);
};
	
	





 function cancelFunction(){
	$("#userCancelForm").attr("action", "category");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>
