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
            <!-- Search for small screen -->
			<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Staff Salary"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="staffmanagement"><s:text name="Breadcrumb.manage" /></a></li>
					<li><a href="staffmanagement"><s:text name="Breadcrumb.manage.staffmgmt" /></a></li>
                    <li class="active"><s:text name="Staff Salary" /></li>
                   </ol>                              						
              </div>
            </div>
          </div>
        </div>
       	<div class="container">
       	<div class="card-panel">
			<form method="post" id="registerformid" name="registerformid" action="SalaryCreateUpdateAction">
 			<div class="row">
  				<div class="input-field col s6">
				<div class="input-field col m5"><s:text name="Text.name"></s:text></div>
				<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.staffName"/></span>														 																														
				</div></div>
  				<div class="input-field col s6">
				<div class="input-field col m5"><s:text name="Text.emailid" /></div>
				<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="staffRegObj.staffEmail"/></span>
				</div>
 				</div>
 				</div>
													<div class="row">
  														  <div class="input-field col s6">
																 <div class="input-field col m5"><s:text name="Text.gender" /> </div>
																 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.staffGender"/></span>													
															  </div>   															            											        				
															</div>
															<div class="input-field col s6">
																<div class="input-field col m5"><s:text name="Text.mobileno" /> </div>
																 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="staffRegObj.staffMobno"/></span></div>
															
															</div>
  																  
														</div>
														<div class="clear height10px"></div>
														<div class="row">
  														  <div class="input-field col s6">
  														   <div class="input-field col s12">
																<label for="salaryid"><s:text name="Salary" /><span class="mandatory">*</span></label>
																<s:textfield name="staffSalary_str" id="salaryid" cssClass="form-control salaryvalidate" />
															</div>	</div>															
  																  <div class="input-field col s6" style="margin-left:0px;">
																<div class="input-field col s12"><label for="wagesid"><s:text name="Extra wages" /><span class="mandatory">*</span></label>
																<s:textfield name="extraWages" id="wagesid" cssClass="form-control salarywagevalidate" />
															</div></div>
														</div>
			
					<s:hidden name="staffRegObj.staffID" id="uniqStaffIdEdit"></s:hidden>																																			
					<div class="row"><div class="input-field col s6"><div class="input-field col s12">
						<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div></div>
					</div>
					</form>
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				</div>
				
				<!--end container-->
			
			</section>

		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	
</body>
	
<script type="text/javascript">
$(document).ready(function(){

	$("#salaryid").keyup(function(){
		textValidate(this,'10','MNM');	
	});
	$("#wagesid").keyup(function(){
		textValidate(this,'10','MNM');	
	});
	
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
  	$("#salaryid").rules("add", {
  		required : true,
  		messages : {
  			required :"<s:text name="Error.staffreg.salary" />",
  		}
  		
  	});

   	$("#wagesid").rules("add", {
  		required : true,
  		messages : {
  		required :"<s:text name="Error.staffreg.salarywages" />",       			
  		}
  	});
});
function cancelFunction(){
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');

	$("#userCancelForm").attr("action", "successeditstaff");
	$("#userCancelForm").submit();
}	</script>

</html>
