<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><s:text name='Text.project.title' /></title>
<style type="text/css">
#userCreationFormId ul li{
	list-style: none;
	color: #D90F0F;	
	font-size: 11px;
	margin-left: -40px;
    margin-top: 0px;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<jsp:include page="../common/header.jsp"></jsp:include>

		<div id="right-column">
			<div class="right-column-content">
				<div class="row">
					<div class="col-xs-12">
						<ol class="breadcrumb">
							<li><a href="breadcrumbdashboard"><s:text name="Home" /></a></li>
							<li><a href="breadcrumbdashboard"><s:text name="Staff Mgmt" /></a></li>
							<li class="active"><s:text name="Staff Create" /></li>
						</ol>
					</div>
				</div>
				 <div class="row">
					<div class="col-md-6">
						<h1>
							<span aria-hidden="true" class="icon icon-grid-big"></span> <span
								class="main-text"><s:text name="Staff Create" /></span>
						</h1>
					</div>
					<div class="col-md-12">
							<div class="block">
								<div class="block-heading">
									<div class="main-text h2">
								<%-- 		<s:text name="Menuheader.uam.profile" /> --%>
									</div>
								</div>
								
								<div class="block-content-outer">
									<div class="block-content-inner">
										<form method="post" id="registerformid" name="registerformid" action="createdstaff">
									   
 													<div class="row">
  														  <%-- <div class="col-sm-5">
  														  <div class="form-group" id="divfirstname">
																<label><s:text name="Text.id"></s:text><span class="mandatory">*</span></label>																 
																<s:textfield name="staffRegObj.staffID" id="firstname" value="0001" cssClass="form-control firstnamevalidate" /> 															
															</div>
															</div> --%>
															
  																  <div class="col-sm-5"> 
  																  <div class="form-group" id="divlastname">
																<label><s:text name="Text.name" /><span class="mandatory">*</span></label>
																<s:textfield name="staffRegObj.staffName" id="lastname" value="" cssClass="form-control lastnamevalidate" />
															</div>
															</div>
															<div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.emailid" /><span class="mandatory">*</span></label>
																<s:textfield name="staffRegObj.staffEmail" id="emailid" value="" cssClass="form-control emailidvalidate" />
															</div>
															</div>
 															 </div><br> 
														<div class="row">
  														 
  														 <div class="col-sm-5"> 
  																  <div class="form-group">
																		<label><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
																		<s:textfield name="staffRegObj.staffMobno" id="mobileno" value=""
																			cssClass="form-control mobilevalidate" />
																	</div>
																	</div>
																	<div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.gender" /><span class="mandatory">*</span></label>
																<s:select id="countryId" cssClass="form-control gendervalidate"
																	headerKey="" headerValue="Select"
																	list="#{'1':'Male','2':'Female','3':'Other' }" 
																	name="staffRegObj.staffGender" value="1">																	
																</s:select>
															</div>
															</div>
														</div><br> 
														
														
														
														
														<div class="row">
  														  <div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.customerreg.idproof" /><span class="mandatory">*</span></label>
																<s:select id="idproof" cssClass="form-control idproofvalidate"
																	headerKey="" headerValue="Select"
																	list="#{'1':'Voter ID','2':'Driving Linces','3':'AAdhar Card','4':'Pan Card','5':'Others' }" 
																	name="staffRegObj.staffIdcardtype" value="1">
																	
																</s:select>
															</div>
															</div>
  																  <div class="col-sm-5"> 		
  																  <div class="form-group">															
																		<label><s:text name="Text.customerreg.idproofno." /><span class="mandatory">*</span></label>
																		<s:textfield name="staffRegObj.staffIdcardno" id="idProofNo" value="" cssClass="form-control idproofnovalidate" />
																	</div>
																	</div>
														</div><br>
														
														
														<div class="row">
  														  
  																 <div class="col-sm-5"> 
  																  <div class="form-group">
																		<label><s:text name="Text.address" /><span class="mandatory">*</span></label>
																		<s:textfield name="staffRegObj.staffAddr" id="mobileno" value="9874563210"
																			cssClass="form-control mobilevalidate" />
																	</div>
																	</div>
																	<div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.country" /><span class="mandatory">*</span></label>
																<%-- <s:select id="idproof" cssClass="form-control idproofvalidate"
																	headerKey="" headerValue="Select"
																	list="#{'1':'India','2':'USA','3':'England','4':'Paris'}" 
																	name="staffRegObj.staffCountry" value="1">
																	
																</s:select> --%>
																<s:select id="countryid"   cssClass="form-control idproofvalidate"
														headerKey="" headerValue="Select"
														list="#session.countryList" listKey="countryId"
														listValue="countryName" name="staffRegObj.staffCountry"
														value="%{staffRegObj.countryId}"
														onchange="onchangeCountrycodeAgency();">
													</s:select>
															</div>
															</div>
														</div> <br> 
														
														
														 <div class="row">
  														  
  																  <div class="col-sm-5"> 		
  																	  <div class="form-group">
																<label><s:text name="Text.state" /><span class="mandatory">*</span></label>
																<%-- <s:select id="statediv" cssClass="form-control idproofvalidate"
																	headerKey="" headerValue="Select"
																	list="#{'1':'Tamil nadu','2':'kerala','3':'Andhra Pradesh','4':'Gujarat'}" 
																	name="staffRegObj.staffState" value="1">
																	
																</s:select> --%>
																<s:select name="staffRegObj.staffState"  listKey="stateId" id="statediv" cssClass="form-control idproofvalidate"
														 list="# {'':'Select '} " onchange="onchangeStatecodeAgency();">
													</s:select>
															</div>
																	</div>
															<div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.city" /><span class="mandatory">*</span></label>
																<%-- <s:select id="idproof" cssClass="form-control idproofvalidate"
																	headerKey="" headerValue="Select"
																	list="#{'1':'Chennai','2':'Villupuram','3':'Tiruvannamalai','4':'Namakkal'}" 
																	name="staffRegObj.staffCity" value="1">
																	
																</s:select> --%>
																<s:select name="staffRegObj.staffCity" id="citydiv"  cssClass="form-control idproofvalidate"
															 list="# {'':'Select '} ">
														</s:select>
															</div>
															</div>		
																	
														</div><br> 
												 		
														<button type="submit" class="btn btn-primary btn-sm" id="submitbtn" name="submitbtn"
													value="submit" ><s:text name="Text.button.submit" /></button>
														<input type="hidden" name="alert" value="Created staff"/>
														</form> 
										
										<s:form method="post" id="userCancelForm"></s:form>
										
									</div>
								</div>
							</div>
					   </div>
				</div> 

			</div>

			<!-- START Footer Container -->
			<jsp:include page="../common/footer.jsp"></jsp:include>
			<!-- END Footer Container -->
		</div>
		<!-- END Right Column -->

	</div>
	
	<%-- <link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/bootstrap/bootstrap.min.css" rel="stylesheet"> --%>
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.structure.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.theme.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/mCustomScrollbar/jquery.mCustomScrollbar.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/icheck/all.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/fonts/metrize-icons/styles-metrize-icons.css" rel="stylesheet">

	<!-- Optional CSS Files -->
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/bootstrapValidator.min.css" rel="stylesheet" />
	<!-- add CSS files here -->

	<!-- More Required CSS Files -->
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/styles-core.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/styles-core-responsive.css" rel="stylesheet" />

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<s:text name='Resource.path'/>/assets/js/required/misc/ie10-viewport-bug-workaround.js"></script>
	
	<!-- Required JS Files -->
	<%-- <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.min.js"></script> 
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/bootstrap/bootstrap.min.js"></script>--%>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery.easing.1.3-min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery.mCustomScrollbar.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/misc/jquery.mousewheel-3.0.6.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/misc/retina.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/icheck.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/misc/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/circloid-functions.js"></script>

	<!-- Optional JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/ckeditor/adapters/jquery.js"></script> <!-- This jQuery Adapter is REQUIRED for CKEditor to function properly -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrapValidator.min.js"></script>
	<!-- <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrapValidator-language/languagecode_COUNTRYCODE.js"></script> -->
	<!-- add optional JS plugin files here -->

	<!-- REQUIRED: User Editable JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/script.js"></script>
	<!-- add additional User Editable files here -->

	<!-- Demo JS Files -->
	<%-- <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/demo-files/form-validation.js"></script> --%>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/formValidation.min.js" ></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script>
	
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/typeahead/typeahead.css" rel="stylesheet" />
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/typeahead/typeahead.bundle.js"></script>

</body>
<script>
function onchangeCountrycodeAgency() {
	
	var cntryval = $("#countryid").val();
	$.ajax({
		type : 'post',
		datatype : 'html',
		url : 'countryGetStatevalue',
		data : 'countryidkey=' + cntryval,
		success : function(data) {
			$("#statediv").html(data);
		}
	});
}
function onchangeStatecodeAgency(){
	var statval = $("#statediv").val();
	 alert(statval);
	$.ajax({
		type : 'post',
		datatype : 'html',
		url : 'agencyGetCityvalue',
		data : 'stateidkey=' + statval,
		success : function(data){
			$("#citydiv").html(data);
		}
	});
}
 function cancelFunction(){
	$("#userCancelForm").attr("action", "staffmgmt");
	$("#userCancelForm").submit();
}
</script>
</html>
