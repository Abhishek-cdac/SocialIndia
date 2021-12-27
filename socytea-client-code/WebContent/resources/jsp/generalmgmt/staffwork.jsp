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
  <link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
            <div class="header-search-wrapper grey hide-on-large-only">
                <i class="mdi-action-search active"></i>
                <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore Materialize">
            </div>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Staff Work"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="staffmanagement"><s:text name="Breadcrumb.manage" /></a></li>
					<li><a href="staffmanagement"><s:text name="Breadcrumb.manage.staffmgmt" /></a></li>
                    <li class="active"><s:text name="Staff Work" /></li>
                   </ol>                              						
              </div>
            </div>
          </div>
        </div>
       	<div class="container">
       	<div class="card-panel">
			<form method="post" id="registerformid" name="registerformid" action="WorkalertAction">
 													
 					<div class="row">
  						 <div class="input-field col s6">
						    <div class="input-field col m5"><s:text name="Staff Name"></s:text></div>
							<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.staffName"/></span>																 																														
							</div>
						</div>
  						<div class="input-field col s6">
							<div class="input-field col m5"><s:text name="Text.mobileno" /></div>
							<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="staffRegObj.staffMobno"/></span>
							</div>
						</div>
 				    </div>
 				    <div class="clear height10px"></div>
 						<div class="row">
  														  <div class="col-sm-5">
  														  <div class="input-field col s6">
  														  <div class="input-field col s12">
																 <label for="frmdate">From Date<span class="mandatory">*</span></label> 																
															  <!--  <input type="date" name="workStartDateStr" id="frmdate" class="datepicker">  -->
															   <s:textfield  id="frmdate" cssClass="datepicker" name="workStartDateStr"></s:textfield>
															</div>
															</div>
														</div>
  														<div class="col-sm-5">
  														  <div class="input-field col s6">
  														  <div class="input-field col s12">
																 <label for="todate">To Date<span class="mandatory">*</span></label> 																
														<!-- <input type="date" name="workEndDateStr" id="todate" class="datepicker">  -->
														<s:textfield  id="todate" cssClass="datepicker" name="workEndDateStr"></s:textfield>
															</div>
															</div>
															</div>
					</div>
															<div class="row">
															  <div class="input-field col s6">
															  <div class="input-field col s12">
																<label for="Weeklyoffday" class="active">Weekly Off</label>
														<s:select id="Weeklyoffday" cssClass="form-control weekoffvalidate" headerKey="" headerValue="Select Week Off"
                          							list="#{'1':'Sunday','2':'Monday','3':'Tuesday','4':'Wednesday','5':'Thursday','6':'Friday','7':'Saturday' }" name="Weeklyoff_day">																	
                									</s:select>
														</div>
														</div>
														</div>
 					<div class="row">
					<div class="input-field col s12">
					 <div class="input-field col s12">
					<label for="workdetails">Work</label>
					<s:textarea  name="workdetails" id="workdetails" value="%{workdetails}" cssClass="materialize-textarea form-group workdetailsvalidate"></s:textarea>
					</div></div>														
					</div>
														<s:hidden name="staffRegObj.staffID" id="uniqStaffIdEdit"></s:hidden>	
				<div class="row"><div class="input-field col s6">
				<div class="input-field col s12">
						<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
											</div></div>
					</div>	
														</form>
											</div>
											<s:hidden name="staffRegObj.staffID" id="uniqStaffIdEdit"></s:hidden>																						 
							
					</div>
					</section>
									<s:form method="post" id="userCancelForm"></s:form>
					
				</div>
				</div>
				
				<!--end container-->
			
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 

	
</body>
 <script type="text/javascript">
 $(document).ready(function(){
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
 
 $("#frmdate").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.fromdate" />",	
		}
	});
 $("#todate").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.todate" />",	
		}
	});
 $("#Weeklyoffday").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.weeklyoff" />",			
		}
	});
});
	function cancelFunction(){
		$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');				
		$("#userCancelForm").attr("action", "successeditstaff");
		$("#userCancelForm").submit();
	}										
</script> 	 
</body>
</html>




