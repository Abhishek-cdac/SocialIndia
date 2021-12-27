<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Calendar"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<%-- <sj:head locale="en" jqueryui="true" jquerytheme="pagination"
	customBasepath="resources" /> --%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name='Text.project.title' /></title>

<style type="text/css">
#registerformid ul li{
	list-style: none;
	color: #D90F0F;	
	font-size: 11px;
	margin-left: -40px;
    margin-top: 0px;
}
</style>

</head>

<body>

	<!-- Top content -->
	<div class="top-content">
<%-- 
		<jsp:include page="common/Alert.jsp" flush="true" /> --%>

		<div class="inner-bg" style="background-color: #04285c"> 
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-4 text">
						<h1>
							<strong><s:text name='Text.project.title' /></strong>
							Registration Form
						</h1>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-10 col-sm-offset-2 form-box">

						<div class="col-md-10 col-sm-10">
							<div class="block">
								<div class="block-heading">
									<div class="main-text h2">Registration</div>
									<div class="block-controls">
										<span aria-hidden="true"
											class="icon icon-cross icon-size-medium block-control-remove"></span>
										<span aria-hidden="true"
											class="icon icon-arrow-down icon-size-medium block-control-collapse"></span>
									</div>
									<div style="clear: both;"></div>
									<div class="errordiv" style="display: none; margin-top: 10px;"
										onclick="$('.error_box').html('');$('.errordiv').hide();">
										<div class="error_box"></div>
									</div>
								</div>

								<div class="block-content-outer">
									<div class="block-content-inner">
									   <form method="post" id="registerformid" name="registerformid" action="signinpageverify">
 													<div class="row">
  														  <div class="col-sm-5">
  														  <div class="form-group" id="divfirstname">
																<label><s:text name="Text.adduser.username"></s:text><span class="mandatory">*</span></label>																 
																<s:textfield name="custRegObj.userName" id="firstname"  cssClass="form-control usernamevalidate" /> 															
															<s:fielderror fieldName="custRegObj.userName"></s:fielderror>
															</div>
															</div>
															<div class="col-sm-5"> 
  																  <div class="form-group">
																		<label><s:text name="Text.customerreg.mobileno" /><span class="mandatory">*</span></label>
																		<s:textfield name="custRegObj.mobileNo" id="mobileno"
																			cssClass="form-control mobilevalidate" />
																			<s:fielderror fieldName="custRegObj.mobileNo"></s:fielderror>
																	</div>
																	</div>
																	 </div><br>
  																 <%--  <div class="col-sm-5"> 
  																  <div class="form-group" id="divlastname">
																<label><s:text name="Text.customerreg.firstname" /><span class="mandatory">*</span></label>
																<s:textfield name="custRegObj.firstName" id="lastname"
																	cssClass="form-control firstnamevalidate" />
																
															</div>
															</div> --%>
 															
													<%-- <div class="row">
  														  <div class="col-sm-5"> 
  																  <div class="form-group" id="divlastname">
																<label><s:text name="Text.customerreg.lastname" /><span class="mandatory">*</span></label>
																<s:textfield name="custRegObj.lastName" id="lastname"
																	cssClass="form-control lastnamevalidate" />
															</div>
															</div>
												   <div class="col-sm-5">
  														  <div class="form-group" >
																 <label><s:text name="Text.customerreg.dob" /><span class="mandatory">*</span></label> 
															     <div class="input-group input-append date" id="datePicker">
                												<input type="text" class="form-control" name="date" />
               											 <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
           													 </div>
															     															            											        					
															</div>
															</div>
														</div><br> --%>
														<div class="row">
  														  <div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.customerreg.emailid" /><span class="mandatory">*</span></label>
																<s:textfield name="custRegObj.emailId" id="emailid"
																	cssClass="form-control emailidvalidate" />
																	<s:fielderror fieldName="custRegObj.emailId"></s:fielderror>
															</div>
															</div>
  																  <div class="col-sm-5"> 
  																  <div class="form-group">
																<label><s:text name="Text.customerreg.group.type" /><span class="mandatory">*</span></label>
																<s:select id="groupcode" cssClass="form-control groupvalidate"
																	headerKey="" headerValue="Select Group"
																	list="#{'1':'Admin','2':'Committee' }"  value="%{custRegObj.groupCode}"
																	name="custRegObj.groupCode" onchange="testfun();">																	
																</s:select>
															</div>
															</div>
														</div><br>
														<div class="row">
														<div class="col-sm-5" id="hideselect" style="display: none;">
										<div class="form-group" id="usernamedivid" >
												<label class=" control-label"><s:text name="Text.adduser.town.ship.id" /><span class="mandatory">*</span></label>
												<s:select id="townshipid"   cssClass="form-control townshipIdvalidate"
														headerKey="" headerValue="Select"
															list="#session.TOWNSHIPLIST" listKey="townshipId"
														listValue="townshipName" name="townshipId"
														value="%{townshipId}"
														onchange="onchangetownshipId();">
													</s:select>
												<s:fielderror fieldName="townshipId"/>
											</div> 
											</div>
											
										<div class="col-sm-5" id="hidetown" style="display: none;">
										<div class="form-group" id="usernamedivid">
												<label class=" control-label"><s:text name="Text.adduser.society.id" /><span class="mandatory">*</span></label>
																<s:select name="societyId"  listKey="stateId" id="statediv" cssClass="form-control societyIdvalidate"
														 list="# {'':'Select '} " >
												</s:select>
													<s:fielderror fieldName="usercreateObj.societyId"/>
											</div> 
											</div>	</div>
													 <%--	<div class="row">
  														  <div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.customerreg.gender" /><span class="mandatory">*</span></label>
																<s:select id="countryId" cssClass="form-control gendervalidate"
																	headerKey="" headerValue="Select"
																	list="#{'1':'Male','2':'Female','3':'Other' }" 
																	name="custRegObj.gender">																	
																</s:select>
															</div>
															</div> 
  																  <div class="col-sm-5"> 
  																  <div class="form-group">
																		<label><s:text name="Text.customerreg.mobileno" /><span class="mandatory">*</span></label>
																		<s:textfield name="custRegObj.mobileNo" id="mobileno"
																			cssClass="form-control mobilevalidate" />
																			<s:fielderror fieldName="custRegObj.mobileNo"></s:fielderror>
																	</div>
																	</div>
														</div>--%><br>
												 		<div class="row">
  														  <div class="col-sm-5">
  														  <div class="form-group">
																<label><s:text name="Text.customerreg.idproof" /><span class="mandatory">*</span></label>
																<s:select id="idproof" cssClass="form-control idproofvalidate"
																	headerKey="" headerValue="Select"
																	list="#{'1':'Voter ID','2':'Driving Linces','3':'AAdhar Card','4':'Pan Card','5':'Others' }" 
																	name="custRegObj.idCardType">
																	
																</s:select>
															</div>
															</div>
  																  <div class="col-sm-5"> 		
  																  <div class="form-group">															
																		<label><s:text name="Text.customerreg.idproofno" /><span class="mandatory">*</span></label>
																		<s:textfield name="custRegObj.idProofNo" id="mobileno" cssClass="form-control idproofnovalidate" />
																	</div>
																	</div>
																	
														</div><br> 
														<%-- <div class="row">
  														 <div class="col-sm-5">
											<div class="form-group" id="userpassdivid">
												<label class=" control-label"><s:text name="Menuheader.uam.profile.address" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control address" name="custRegObj.address1" id="passwdTextId" placeholder=""></s:textfield>
												<s:fielderror fieldName="usercreateObj.address1"/>
											 </div>
											 </div>
															<div class="col-sm-5">
												<div class="form-group" id="userpassdivid">
													<label class=" control-label"><s:text name="Menuheader.uam.profile.Blood.group" /><span class="mandatory">*</span></label>
													<s:textfield cssClass="form-control bloodGroup" name="custRegObj.bloodType" id="passwdTextId" placeholder=" Group"></s:textfield>
													<s:fielderror fieldName="usercreateObj.bloodType"/>
												 </div> 
												 </div></div> --%><br><br> 
														<button type="submit" class="btn btn-primary btn-sm" id="submitbtn" name="submitbtn"
													value="submit" ><s:text name="Text.button.submit" /></button>
													<s:a type="submit" cssClass="btn btn-primary btn-sm" href="loginprocess" id="submitbtn" name="submitbtn"
													value="cancel" ><s:text name="cancel" /></s:a>
														</form> 
														
											</div><br><br>
												
											</div>    										
																										
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
<%@include file="common/footer.jsp"%>
  	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/bootstrap/bootstrap.min.css" rel="stylesheet">  	 	
 	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/datepicker.css" rel="stylesheet" /> 
 	<link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/ccc/dist/css/bootstrapValidator.css"> 	
 	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/bootstrapValidator.min.css" rel="stylesheet" /> 
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/styles-core.css" rel="stylesheet" /> 	
	
	<!-- Required JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-1.11.1.min.js"></script>
 	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/formValidation.min.js" ></script>
	 <script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script> 	 		 	
	  <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/bootstrap/bootstrap.min.js"></script> 
	  <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrap-datepicker.js"></script>
	 <%--  <script type="text/javascript" src="<s:text name='Resource.path'/>/ccc/dist/js/bootstrapValidator.js"></script> --%>
	 	
	
<script type="text/javascript">

 $(document).ready(function() {
 	    $('#datePicker')
        .datepicker({
            format: 'mm/dd/yyyy',
            autoclose:true
        })
        .on('changeDate', function(e) {        	
            $('#registerformid').formValidation('revalidateField', 'date');
        });  
 }); 
					$('#registerformid').bootstrapValidator({
										message : 'This value is not valid',
										
										feedbackIcons : {
											valid : '<s:text name="Text.feedback.ok" />',
											invalid : '<s:text name="Text.feedback.remove" />',
											validating : '<s:text name="Text.feedback.refresh" />'
										},
										 button: {
								            selector: '#submitbtn',
								            disabled: 'disabled'
								        }, 
								         fields : {
								        	 usernamevalidate : {
													selector : '.usernamevalidate',
													validators : {
														notEmpty : {
															message : '<s:text name="Error.customerreg.username" />'
														},
														stringLength : {
															min : 5,
															max : 20,
															message : '<s:text name="Error.customerreg.username.length" />'
														},
														regexp : {
															regexp : /^[A-Za-z-0-9]+$/,
															message : '<s:text name="Error.customerreg.usernamename.validate" />'
														}
													}
												},
											firstnamevalidate : {
												selector : '.firstnamevalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.firstname" />'
													},
													stringLength : {
														min : 5,
														max : 20,
														message : '<s:text name="Error.customerreg.firstname.length" />'
													},
													regexp : {
														regexp : /^[A-Za-z]+$/,
														message : '<s:text name="Error.customerreg.firstname.validate" />'
													}
												}
											},
											societyIdvalidate:{
												selector : '.societyIdvalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.societyId" />'
													}
												}
											},	
											townshipIdvalidate:{
												selector : '.townshipIdvalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.townshipId" />'
													}
												}
											},	
											 lastnamevalidate : {
												selector : '.lastnamevalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.lastname" />'
													},
													stringLength : {
														min : 1,
														max : 20,
														message : '<s:text name="Error.customerreg.lastname.length" />'
													},
													regexp : {
														regexp : /^[A-Za-z]+$/,
														message : '<s:text name="Error.customerreg.lastname.validate" />'
													}
												}
											}, 
											  date : {
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.dob"/>'
													},
													date : {
														format : 'MM/DD/YYYY',
														message : '<s:text name="Error.customerreg.dob.invalid"/>'
													}
												}
											}
											, 
											
											bloodGroup: {
					                        	 selector: '.bloodGroup',
					                             validators: {
					                                 notEmpty: {
					                                     message: '<s:text name="Error.profile.bloodgroup" />'
					                                 },
					                                 regexp: {
					                                	    regexp: /^(A|B|AB|O|a|b|ab|o)[+-]$/,
					                                	    message: 'Invalid blood type'
					                                	},
					                             }
					                             
					                         },
											emailidvalidate : {
												selector : '.emailidvalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.emailid" />'
													},
													stringLength : {
														min : 8,
														max : 70,
														message : '<s:text name="Error.customerreg.emailid.length" />'
													},
													emailAddress : {
														message : '<s:text name="Error.customerreg.emailid.validate" />'
													}
												}
											},
											 address: {
							                  	 selector: '.address',
							                       validators: {
							                           notEmpty: {
							                               message: '<s:text name="Error.profile.address" />'
							                           },
							                          
							                       }
							                   },
											groupvalidate:{
												selector : '.groupvalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.group" />'
													}
												}
											},	
											
											gendervalidate : {
												selector : '.gendervalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.gender" />'
													}
												}
											},
											mobilevalidate : {
												selector : '.mobilevalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.mobileno" />'
													},
													stringLength : {
														min : 5,
														max : 15,
														message : '<s:text name="Error.customerreg.mobileno.length" />'
													},
													regexp : {
														regexp : /^[0-9]+$/,
														message : '<s:text name="Error.customerreg.mobileno.validate" />'
													}

												}
											},
											idproofvalidate : {
												selector : '.idproofvalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.idproof" />'
													}
												}
											},
											idproofnovalidate : {
												selector : '.idproofnovalidate',
												validators : {
													notEmpty : {
														message : '<s:text name="Error.customerreg.idproofno" />'
													},
													stringLength : {
														min : 4,
														max : 15,
														message : '<s:text name="Error.customerreg.idproofno.length" />'
													},
													regexp : {
														regexp : /^[A-Za-z0-9]+$/,
														message : '<s:text name="Error.customerreg.mobileno.validate" />'
													}
												}
											}, 

										} 

									});							
				
</script>
<script type="text/javascript">
function onchangetownshipId() {
 	var townshipid = $("#townshipid").val();
 	//alert(townshipid);
 	$.ajax({
 		type : 'post',
 		datatype : 'html',
 		url : 'townshipgetsociety',
 		data : 'townshipid=' + townshipid,
 		success : function(data) {
 			$("#statediv").html(data);
 		}
 	});
 }
function testfun(){
	var groupcode=$("#groupcode").val();
	if(groupcode>1){
		$("#hideselect").show();
		$("#hidetown").show();
	}else{
		$("#hideselect").hide();
		$("#hidetown").hide();
	}
	
}
</script>
</body>
</html>