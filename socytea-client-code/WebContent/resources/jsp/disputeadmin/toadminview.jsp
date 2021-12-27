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
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.disputemgmt.toadminview" /></h5>
								<ol class="breadcrumbs left">
								<li><a href="Homeform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="tosocialadmintbl"><s:text name="Dispute Management" /></a></li>								
								<li class="active"><s:text name="Breadcrumb.disputemgmt.toadminview" /></li>
								</ol>
								<div class="right">
					<button id="gobckbtnid" class="tooltipped <s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button>
				</div>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
				<div class="card-panel">

				<form id="residentCreationFormId" name="residentCreationFormId" action="complaints" method="post" enctype="multipart/form-data">
							<div class="row">
              <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.adduser.townshipname" /> </div>
                   <div class="input-field col m7"> :  <span class="<s:text name="view.fontvalue.color" />"><s:property value="disputemerchantobj.twnshpname"/></span></div>
                      </div>
                    </div>
                   <div class="input-field col m6">          
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.adduser.society.id"></s:text></div>
                   <div class="input-field col m7"> :  <span class="<s:text name="view.fontvalue.color" />"><s:property value="disputemerchantobj.societyname"/></span></div>
                      </div>
                      </div>
							</div>
							<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="text.residentname"></s:text> </div>
                   <div class="input-field col m7"> :   <span class="<s:text name="view.fontvalue.color" />"><s:property value="disputemerchantobj.residentname"/>(Resident)</span></div>
                      </div>
                    </div>
                   <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5"><s:text name="text.adminname"></s:text></div>
						  <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="disputemerchantobj.merchantname"/></span></div>
  						</div>
                     </div>
							</div>
							<div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="text.disputemgmt.AssignToSocialIndia"></s:text></div>
                  <div class="input-field col m7"> : <span onclick="compliantsendemail(<s:property value="disputemerchantobj.disputeId"/>,<s:property value="disputemerchantobj.disputeT0Id"/>,<s:property value="disp_entbyresidentid"/>);" style="margin-left: 11px;cursor: pointer;" class="tooltipped" id="gobckbtnid" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Email"><i title=""  class="<s:text name="button.color.disputeemail" />"></i></span>
                   <span onclick="sendsmstoadmin(<s:property value="disputemerchantobj.disputeId"/>,<s:property value="disputemerchantobj.disputeT0Id"/>,<s:property value="disp_entbyresidentid"/>);" style="margin-left: 11px;cursor: pointer;" class="tooltipped" id="gobckbtnid" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="SMS"><i title=""  class="<s:text name="button.color.disputesms" />"></i></span>
                 </div>
                      </div>
                    </div>
					</div>
				
                   	<div class="row">
                <div class="input-field col s12">
                  <div class="row">
                  <div class="input-field col m12"><s:text name="Text.desc"></s:text></div>
                      </div>
                    </div>
                   </div>
                    	<div class="row">
                <div class="input-field col s12">
                  <div class="row">
                   <div class="col m12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="disputemerchantobj.disputeDesc"/></span></div>
                   </div></div></div>
					</form>
					</div>
					<s:form method="post" id="userCancelForm"></s:form>
					<s:form method="post" id="delgroupform">
					<s:hidden name="closereasonfield" id="closereasonid"></s:hidden>
					<s:hidden name="emailcommentfield" id="emailcommentid"></s:hidden>
					<s:hidden name="uniqueId" id="deleteusridEdit"></s:hidden>
			</s:form>
				</div>
			</section>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>	
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>		

<jsp:include page="../common/clorbox.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		$("#gobckbtnid").click(function() {
			$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
			toShowLoadingImgoverlay();
			$("#userCancelForm").attr("action", "tosocialadmintbl");
			$("#userCancelForm").submit();
		});	   
	});
	
	function compliantsendemail(id){
		
		swal({   title: "Comments",   
	        //text: "Create New Group",   
	        type: "input",   showCancelButton: true,
	        closeOnConfirm: false,   
	        
	        animation: "slide-from-top", 
	        inputValue: "" , 
	        inputPlaceholder: "Enter the comment" }, 
	        function(inputValue){  
	        	
	            if (inputValue === false) return false;      
	            if (inputValue === "") {     
	            swal.showInputError("Enter the comment");     
	            return false; 
	            } 

	            if(inputValue.length  >=5 && inputValue.length  <= 50){
           		 $("#emailcommentid").val(inputValue);
    	            $("#deleteusridEdit").val(id);
    				$("#delgroupform").attr("action", "sendmailcmpltaction");
    				$("#delgroupform").submit();
           } else {
           	swal.showInputError("Comments minimum 5 to maximum 50 characters.");
               return false;
           }
				           
	      // swal("Success", "Reason updated Successfully ", "success"); 
	   });
	}
function sendsmstoadmin(id){
 swal({   title: "Comments",   
    //text: "Create New Group",   
    type: "input",   showCancelButton: true,
    closeOnConfirm: false,   
    
    animation: "slide-from-top", 
    inputValue: "" , 
    inputPlaceholder: "Enter the comment" }, 
    function(inputValue){  
    	
        if (inputValue === false) return false;      
        if (inputValue === "") {     
        swal.showInputError("Enter the comment");     
        return false ;  
        } 
        if(inputValue.length  >=5 && inputValue.length  <= 50){
       	 $("#emailcommentid").val(inputValue);
            $("#deleteusridEdit").val(id);
    		$("#delgroupform").attr("action", "sendsmscmpltaction");
    		$("#delgroupform").submit();
  } else {
  	swal.showInputError("Comments minimum 5 to maximum 50 characters.");
      return false;
  }		         
}); 
}
</script>
</body>
</html>