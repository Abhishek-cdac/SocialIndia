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
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.usercreation" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li class="active"><a href="usermgmt"><s:text name="Breadcrumb.uam"></s:text></a></li>
							<li class="active"><a href="usermgmt"><s:text name="Breadcrumb.usermanage"/></a></li>
							<li class="active"><s:text name="Breadcrumb.usercreation" /></li>
						</ol>
					</div>
				</div></div></div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
				<div id="jqueryvalidation" class="section">
				<div class="card-panel">
<form role="form" class="formValidate" id="formValidate" action="usercreationForm" method="post" >
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header teal white-text active" > <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> Profile Detail</div>
<div class="collapsible-body" id="firstdivid">

							<!--<div class="imgaddminus" >
								<i class="mdi-content-remove-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i><span class="spacialspace"> Profile Detail </span>
							</div>-->
							<div style="clear: both; height:5px;"></div>
								<div id="profilehidden" style="display:block;margin:15px 15px 15px 15px;">

										<div class="row rowmargin12px" >
										 <div class="input-field col s6">
										<div class="form-group" id="usernamedivid">
												<label for="township_txt_id" class=" control-label"><s:text name="Text.adduser.town.ship.id" /><span class="mandatory">*</span></label>
												<s:textfield id="township_txt_id" name="usercreateObj.townshipName" data-error=".errorTxt1"  cssClass="form-control typeahead tt-query townshipIdvalidate left"  autocomplete="off" />
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('township_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control"  />
												 <div class="errorTxt1"></div>
											</div>
											</div>
								    <div class="input-field col s6">
  									 <div class="form-group" id="societid">
									<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /><span class="mandatory">*</span></label>
									<s:textfield name="societyName" id="societyId_txt_id" cssClass="typeahead tt-query  form-control left"  autocomplete="off" spellcheck="false" onchange="societyIdvalidate()" />
									<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('societyId_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>

									<s:hidden id="societyId_hidd_id" name="societyId" cssClass="form-control"/>
									</div>
									</div>
									</div>
									<div class="row rowmargin12px">

										<div class="input-field col s2">
												<div class="form-group" id="usernamedivid">
												<label for="isdcodeid" class=" control-label"><s:text name="Text.Form.IsdCode"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control isdNo" value="%{getText('Text.ISD.value')}" name="usercreateObj.isdCode"
												id="isdcodeid" ></s:textfield>
											</div>
											</div>
													<div class="input-field col s4">
												<div class="form-group" id="usernamedivid">
												<label for="mobilenoid" class=" control-label"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control mobilevalidate " name="usercreateObj.mobileNo"  id="mobilenoid" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.mobileNo"/>
											</div>
											</div>
										<div class="input-field col s6">
										<div class="form-group" id="usernamedivid">
												<label for="emailid" class=" control-label"><s:text name="Text.emailid" /></label>
												<s:textfield cssClass="form-control " name="usercreateObj.emailId"
												id="emailid" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.emailId"/>
											</div>
											</div>
											</div>
											<div class="row rowmargin12px">
											<div class="input-field col s6">
											<div class="form-group" id="groupnamedivid">
												<label for="card" class="control-label"><s:text name="Text.customerreg.idproof"/></label>
												<s:textfield name="idcardName" id="card" cssClass="form-control typeahead tt-query left"  autocomplete="off" spellcheck="false"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden id="cardid" name="usercreateObj.idCardType" cssClass="form-control" />
												<s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											</div>
											</div>
											<div class="input-field col s6">
												<div class="form-group" id="usernamedivid">
												<label for="idproofno" class=" control-label"><s:text name="Text.customerreg.idproofno"/></label>
												<s:textfield cssClass="form-control  " name="usercreateObj.idProofNo"
												id="idproofno" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
												<div id="card-error-div" class="error manualscriptvalidation" style="display:none;"></div>
											</div>
											</div>
											</div>
											<div class="row rowmargin12px">
											<div class="input-field col s6">
												<label for="firstname" class=" control-label"><s:text name="Text.fname" /></label>
												<s:textfield cssClass="form-control registerNumber" name="usercreateObj.firstName"
												id="firstname" ></s:textfield>
											</div>
											<div class="input-field col s6">
											<div class="form-group" id="usernamedivid">
												<label for="lastname" class=" control-label"><s:text name="Text.lname" /></label>
												<s:textfield cssClass="form-control registerNumber" name="usercreateObj.lastName"
												id="lastname" ></s:textfield>
											</div>
											</div>
											</div>
											<div class="row rowmargin12px">
											<div class="input-field col s6">
												<label for="gender" class="active"><s:text name="Text.gender" /></label>
												 <s:select cssClass="form-control gendervalidate" id="gender" list="#{'1':'Male','2':'Female','3':'Other' }" name="usercreateObj.gender" />

											</div>
												<div class="input-field col s6">
												<label for="dateid"><s:text name="Text.resident.dob"/></label>
												<s:textfield  id="dateid" cssClass="dateOfBirth" name="usercreateObj.dob"></s:textfield>
												</div>
												</div>

											<%--  <label for="checkbox1" class="active"><s:text name="Text.accesschannel" /><span class="mandatory"></span></label> --%>
											<%-- <div class="input-field col s6">
											<label class="active" for="accessChannel"><s:text name="Text.accesschannel" /><span class="mandatory">*</span></label>
											 <p style="margin-left: -10px;">
											 <s:checkboxlist list="#{'1':'Mobile','2':'Web','3':'Both'}"
                                             name="accessChannel" id="accessChannel" cssClass="myCheckbox" value="3"/>
											</p>
                                            </div> --%>
                                       <div class="row rowmargin12px">
										<div class="input-field col s6">
											<label for="pidd" class="active">Access Channel</label>
											<div id="pidd">
											<input type="checkbox" name="accessChannel" class="myCheckbox" id="test5" value="2"/> <label for="test5">Mobile</label>
											<input type="checkbox" name="accessChannel" class="myCheckbox" id="test6" value="1"/> <label for="test6">Web</label>
											<input type="checkbox" name="accessChannel" class="myCheckbox" id="test7" value="3" checked="checked" /> <label for="test7">Both</label>
											</div>
										</div>
										<div class="input-field col s6">
											<div class="form-group" id="usernamedivid">
												<label for="numoflogins" class=" control-label"><s:text name="Text.numoflogins" /></label>
												<s:textfield cssClass="form-control registerNumber" name="usercreateObj.numOfLogins"
												id="numoflogins" ></s:textfield>
											</div>
											</div>
											</div>
                                            </div>
        </div>
      </li>
       <li>
       <div class="collapsible-header teal white-text"> <i class="mdi-communication-business tinysmall white-text text-accent-4"></i> Block Detail</div>
       <div class="collapsible-body" id="seconddivid">
                    <!--  <div class="clear height25px"></div>
						<div id="blockdetdiv" >
						<div class="imgaddplus1" onclick="myFunction2('add');">
								<i class="mdi-content-add-circle tinysmall " style="color: #ff4081; cursor: pointer; float: left;cursor: pointer;"></i>
								<div class="spacialspace">Block Detail </div>
							</div>

							<div class="imgaddminus1" onclick="myFunction2('sub');"
								style="display: none; cursor: pointer;">
								<i class="mdi-content-remove-circle tinysmall"
									style="color: #ff4081; float: left;"></i><span class="spacialspace">
									Block Detail </span>
							</div>
						</div> -->
                    <div id="blockhidden" style="display:block;margin:15px 15px 15px 15px; ">

											<div class="row rowmargin12px">
													      <div class="input-field col s6">
																		<label for="noofblocks"><s:text name="Text.resident.NoofBlock/Wings" /></label>
																		<s:textfield name="usercreateObj.noOfBlocks" onkeyup="textValidate(this,'3','OI');" value="1"   maxlength="3" id="noofblocks"
																			cssClass="form-control " />
																	</div>
  														 <div class="input-field col s4">
																<label for="noofflats"><s:text name="Text.resident.noofflats"></s:text></label>
																<s:textfield name="usercreateObj.noofFlats" id="noofflats"  onkeyup="textValidate(this,'3','OI');" value="1"   maxlength="3" cssClass="form-control " />
															</div>
															<div class="input-field col s2">
												<label  class=""><button type="button"
											class="<s:text name="button.color.add"/>" id="addButton"
											name="button" value=""><i class="mdi-content-add"></i></button> </label>
											</div>
 															 </div>
 							<div class="row rowmargin12px">
							<div class="input-field col s6">
								<%--  <div class="input-field col s6">
								<label for="blockname"><s:text name="Text.resident.block" /></label>
								<s:textfield name="restRegObj.block1"  onkeyup="textValidate(this,'30','AI');"  id="blockname"  cssClass="form-control blocknamelist" />
								</div> --%>
								<label for="bloclnameidd" class="active"><s:text name="Text.resident.block" /><span class="mandatory">*</span></label>
								<!--// RPK - WINGSBLOCK  - 1 -->
								<!--<s:textfield name="restRegObj.block1"  onkeyup="textValidate(this,'30','AI');"  id="blockname"  cssClass="form-control blocknamelist" />-->
								<select name="restRegObj.block1" id="bloclnameidd" class="form-control blocknamelist"><option value=''>Select Block/Wings Name</option></select>
								<div id="maintexcelfile-error" class="error manualscriptvalidation" style="display:none;">Select block/wings name is required</div>
							</div>
  							<div class="input-field col s6">
								<label for="flatno_1"><s:text name="Text.resident.flatno"></s:text><span class="mandatory">*</span></label>
								<s:textfield name="restRegObj.flatno_1 " id="flatno_1"   cssClass="form-control flatnamelist" />
								<div id="maintexcelfile-error1" class="error manualscriptvalidation" style="display:none;">Flat number is required</div>
							</div>

						</div>
											<div id='TextBoxesGroup'>
														<div id="TextBoxDiv"></div>	</div>
											</div>

       </div>
       </li>
       <li>
       <div class="collapsible-header teal white-text"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> Other Detail</div>
       <div class="collapsible-body" id="thirddivid">
       <!--  <div style="clear: both; height:5px;"></div><div style="clear: both; height:5px;"></div>
										<div id="othersdetdiv" >
										<div class="imgaddplus2" onclick="myFunction3('add');">
								<i class="mdi-content-add-circle tinysmall "
									style="color: #ff4081; float: left; cursor: pointer;"></i>
								<div class="spacialspace">Others
									Detail </div>
							</div>
							<div class="imgaddminus2" onclick="myFunction3('sub');"
								style="display: none;">
								<i class="mdi-content-remove-circle tinysmall"
									style="color: #ff4081; float: left;cursor: pointer;"></i><span class="spacialspace">
									Others Detail </span>
							</div></div><div style="clear: both; height:5px;"></div> -->
       <div id="otherhidden" style="display:block;margin:15px 15px 15px 15px; ">
											<div class="row rowmargin12px">
											  <div class="input-field col s6">
												<label for="address1" class=" control-label"><s:text name="uam.profile.address1"/></label>
												<s:textfield cssClass="form-control address" name="usercreateObj.address1"
												id="address1" onkeyup="textValidate(this,'100','DOC');"></s:textfield>
												<s:fielderror fieldName="usercreateObj.address"/>
											</div>
											  <div class="input-field col s6">
												<label for="address2" class=" control-label"><s:text name="uam.profile.address2"/></label>
												<s:textfield cssClass="form-control addresses" name="usercreateObj.address2"
												id="address2"  onkeyup="textValidate(this,'100','DOC');" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.addresses"/>
											</div>
											</div>
										<div class="row rowmargin12px">
											  <div class="input-field col s6">
												<label for="country_txt_id" class="control-label"><s:text name="Menuheader.uam.profile.country" /><span class="mandatory"></span></label>
												<s:textfield name="countryName" id="country_txt_id" cssClass="form-control typeahead tt-query country left"  autocomplete="off" spellcheck="false"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="usercreateObj.countryCode" id="country_hidd_id" class="form-control"/>
												<s:fielderror fieldName="usercreateObj.groupId.country"/>
											</div>
											 <div class="input-field col s6">
												<label for="state_txt_id" class=" control-label" style="width:100%;" ><s:text name="Menuheader.uam.profile.state" /><span class="mandatory"></span></label>
												<s:textfield name="stateName" id="state_txt_id" cssClass="form-control typeahead tt-query state left"  autocomplete="off" spellcheck="false" onkeyup="statecheck();"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="usercreateObj.stateId" id="state_hidd_id" class="form-control"/>
												<s:fielderror fieldName="usercreateObj.state"/>
											</div>
											</div>
											<div class="row rowmargin12px">
											  <div class="input-field col s6">
												<label for="city_txt_id" class="control-label"><s:text name="Menuheader.uam.profile.city" /><span class="mandatory"></span></label>
												<s:textfield name="cityName" id="city_txt_id" cssClass="form-control typeahead tt-query city left" autocomplete="off" spellcheck="false" onkeyup="citycheck();"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="usercreateObj.cityId" id="city_hidd_id" class="form-control"/>
												<s:fielderror fieldName="usercreateObj.city"/>
											</div>
												 <div class="input-field col s6">
												<label for="pstlcode_txt_id" class=" control-label"><s:text name="Menuheader.uam.profile.pincode" /></label>
												<s:textfield name="usercreateObj.postalid" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query pincode left"  autocomplete="off" spellcheck="false" onkeyup="pstlcodecheck();"/>
<%-- 												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
<span class="input-group-addon txtbxdownarowicon left" ></span>
<!-- 												<input type="hidden" id="pstlcode_hidd_id" name="usercreateObj.postalid" class="form-control"/> -->
												<s:fielderror fieldName="usercreateObj.pincode"/>
											</div>
											</div>

											<div class="row rowmargin12px">
											 <div class="input-field col s6">
												<label for="occupation" class=" control-label"><s:text name="uam.profile.occupation"/></label>
												<s:textfield cssClass="form-control occupation" name="usercreateObj.occupation"
												id="occupation"  ></s:textfield>
												<s:fielderror fieldName="usercreateObj.occupation"/>
											</div>

											 <div class="input-field col s6">
												<label for="bloodgrp" class="control-label"><s:text name="Text.resident.bloodgrp"/></label>
											<s:textfield name="usercreateObj.bloodType" id="bloodgrp" cssClass="form-control typeahead tt-query bloodgroup"
											 autocomplete="off" spellcheck="false"/>
												<s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											</div>
											</div>
		</div>
       </div>
       </li>
       <li>
       <div class="collapsible-header teal white-text"> <i class="mdi-social-people tinysmall white-text text-accent-4"></i> Family Detail</div>
       <div class="collapsible-body" id="fourthdivid">
       <!-- <div id="blockdetdiv" >
												<div class="imgaddplus3" onclick="familydetail('add');">
								<i class="mdi-content-add-circle tinysmall "
									style="color: #ff4081; float: left; cursor: pointer;"></i>
								<div class="spacialspace">Family
									Detail </div>
							</div>
							<div class="imgaddminus3" onclick="familydetail('sub');"
								style="display: none; cursor: pointer;">
								<i class="mdi-content-remove-circle tinysmall"
									style="color: #ff4081; float: left;cursor: pointer;"></i><span class="spacialspace">
									Family Detail </span>
							</div></div><div style="clear: both; height:5px;"></div> -->
       <div id="familydetail" style="display:block;margin:15px 15px 15px 15px; ">
															<div class="row">
															<div class="input-field col s4">
												<label for="member" class=" control-label"><s:text name="Text.resident.familymember"/></label>
												<s:textfield cssClass="form-control familymembers" name="usercreateObj.membersInFamily"
												id="member" value="1"  ></s:textfield>
												<s:fielderror fieldName="usercreateObj.familymembers"/>
											</div>
											<div class="input-field col s2">
  															<label  class="control-label"><button type="button"
												class="<s:text name="button.color.add"/>" id="addButton1"
												name="submitbtn" value=""><i class="mdi-content-add"></i></button> </label>
  																</div></div>
											<div class="card padding10px">
											<div class="row">
											 <div class="input-field col s6">
												<label for="famname1" class="control-label"><s:text name="Name"/></label>
											<s:textfield name="famName1" id="famname1" cssClass="form-control famName"
											 autocomplete="off" spellcheck="false" onkeyup="textValidate(this,'30','OA');"/>
												<s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											</div>

											 <div class="input-field col s2">
												<label for="isdcodeid1" class=" control-label"><s:text name="Text.customerreg.isd" /></label>
												<s:textfield cssClass="form-control famIsdNumber"  value="%{getText(\'Text.ISD.value\')}"  name="famisdno1"
												id="isdcodeid1"  ></s:textfield>

											</div>
												 <div class="input-field col s4">
												<label for="mobilenoid1" class=" control-label"><s:text name="Text.mobileno" /></label>
												<s:textfield cssClass="form-control famMobileNo " name="famMobileNo1"
												id="mobilenoid1" onkeyup="textValidate(this,'10','PHNM');" ></s:textfield>
											</div>
											</div>
												<div class="row">
											 <div class="input-field col s6">
												<label class=" control-label"><s:text name="Text.emailid" /></label>
												<s:textfield cssClass="form-control famEmailId" name="emailId"
												id="emailid1_1" onkeyup="textValidate(this,'100','EML');"  onblur="toValiEmail(this.id)"></s:textfield>
											</div>

                                           <div class="input-field col s3">
											<label class="active">Member Type</label>
											<div class="clear height15px"></div>
											<input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="Relation" value="1" checked/> <label for="Relation">Relation</label>
											<input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="Tenent" value="2"/> <label for="Tenent">Tenent</label>
											</div>
                                            <div class="input-field col s3">
											<label class="active">Profile Access</label>
											<div class="clear height15px"></div>
											<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="Allow" value="1" />
											<label for="Allow">Allow</label>
											<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="notAllow" value="0" checked/>
											<label for="notAllow">Not Allow</label>
											</div>
											</div></div>
											<div id='TextBoxesGroup1'> <div id="TextBoxDiv1"></div></div>
											</div>
       </div>
       </li></ul>
                 <s:hidden name="blockNameList" id="Selectblack" class="form-control " />
				<s:hidden name="flatNameList" id="Selectflat" class="form-control " />
				<s:hidden name="famName" id="famName" class="form-control " />
											  <s:hidden name="famMobileNo" id="famMobileNo" class="form-control " />
											   <s:hidden name="famEmailId" id="famEmailId" class="form-control " />
											    <s:hidden name="famisdcode" id="famisdcode" class="form-control " />
											    <s:hidden name="fammemtype_user" id="fammemtypeid" class="form-control " />
											    <s:hidden name="famprfaccess_user" id="famprfaccessid" class="form-control " />
									  <div style="clear: both; height:15px;"></div>
									    <div class="clear marginleft25px" style="margin-left: 5px;">
				<button type="button" id="userCreateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.submit" />
				<i class="<s:text name="button.icon.submitcard"/>"></i></button>
				<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
										</div>
										</form>
										<s:form method="post" id="userCancelForm"></s:form>

									</div>
								</div>
								</div>
								</section>

						</div></div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
	    <jsp:include page="../common/allbasicjs.jsp"></jsp:include>
</body>
<script>
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
 $(document).ready(function() {
	 
		$('#pstlcode_txt_id').blur(function(){
//	     	alert($('#pstlcode_txt_id').val());
			$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
		});
		
	 var exigndr = $("#gender").html();
	 $("#gender").html('<option value="" disabled selected>Choose your gender</option>'  + exigndr);
	 $("#gender").material_select();
	 $('.myCheckbox').click(function() {
		    $(this).siblings('input:checkbox').prop('checked', false);
		    $(this).prop('checked', true);
		});
	 var counter = 2;
	 var flag = true;
	 $("#addButton").click(function () {
		 var no_flats=$("#noofflats").val();
		 if(counter>no_flats){
			 swal("Only " + no_flats + " Flat No. Allow");
	        flag = false;
		}else{
			 flag =true;
		}
		if(flag){
			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv' + counter);
		    //newTextBoxDiv.after().html(' <div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'"><s:text name="Text.resident.block" /></label><input type="text" name="blockname" onkeyup="textValidate(this,\'30\',\'AI\');"  id="noofblocks_'+counter+'" class="form-control blocknamelist" /></div></div><div class="input-field col s6" > <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.resident.flatno"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'"  maxlength="20" cssClass="form-control flatnamelist" /></div></div></div>');//// text box
		    newTextBoxDiv.after().html(' <div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'" class="active"><s:text name="Text.resident.block" /></label><select name="blockname" id="noofblocks_'+counter+'" class="form-control blocknamelist" >'+societyoptiontag+'</select></div></div><div class="input-field col s6" > <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.resident.flatno"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'"  maxlength="20" cssClass="form-control flatnamelist" /></div></div></div>');// select box	// RPK - WINGSBLOCK  - 3
		    newTextBoxDiv.appendTo("#TextBoxesGroup");
			counter++;$('select').material_select();
		}

	});

	 $("#noofflats").blur(function(){
		 var no_flats=$("#noofflats").val();
		 if(no_flats==0){
			 if(counter>no_flats){
				 var cc = (counter - 1);
				 $("#noofflats").val(cc);

			 }
		 }else{
		 if(no_flats<counter){
			 var ii=0;
			 for(ii=counter;ii>no_flats;ii--){
				 $("#TextBoxDiv"+ii).remove();
			 }
			 counter=(ii+1);
		 }else{
			 flag =true;
		 }
		 }
	 });
			$("#userCreateButtonId").click(function(){
				
				//start: card validation
				var cardtyp = $("#card").val();
				var cardno = $("#idproofno").val();
				
				if(cardtyp == "Aadhaar Card"){
					if(isNaN(cardno)){
						$("#card-error-div").text("Please enter numbers only.");
						$("#card-error-div").show();
						return false;
					}
					$("#card-error-div").hide();
				}
				else{
					var letters = /^[0-9a-zA-Z]+$/; 
					if(!cardno.match(letters)){
						$("#card-error-div").text("Please enter alphanumeric characters only.");
						$("#card-error-div").show();
						return false;
					}	
					$("#card-error-div").hide();
				}
				//end: card validation
				
				var targets = [];
				var targets1 =[];
				var targets2 =[];
				var targets3 =[];
				var targets4 =[];
				var targets5 =[];
				var targets6 =[];
				var targets7 =[];
				   /*$.each($(".blocknamelist"), function(){
				   targets.push($(this).val());
				   });*/
				   $("select.blocknamelist").each(function(){ // RPK - WINGSBLOCK  - 4
					   var contentPanelId = jQuery(this).attr("id");
					   var selecttxt =  $("#"+contentPanelId+" option:selected").text();
					   var selectval =  $("#"+contentPanelId+" option:selected").val();
						if(selectval==""){
							targets.push("");
						} else {
							targets.push(selecttxt);
						}
				   });
				   $.each($(".flatnamelist"), function(){
					   targets1.push($(this).val());
					   });
				   $.each($(".famName"), function(){
					   var name=$(this).val();
					   /*  if(name==''){
						   name="NaN";
					   }  */
					   targets2.push(name);
					   });
				   $.each($(".famMobileNo"), function(){
					   var mob=$(this).val();
					   /*  if(mob==''){
						   mob="NaN";
					   }  */
					   targets3.push(mob);
					   });
				   $.each($(".famEmailId"), function(){
					   var email=$(this).val();
					  /*  if(email==''){
						   email="NaN";
					   }  */
					   targets4.push(email);
					   });
				   $.each($(".famIsdNumber"), function(){
					   var isd=$(this).val();
					   /* if(isd==''){
						   isd="NaN";
					   }  */
					   targets5.push(isd);
					   });
				   $("input[name='memtypechk']:checked").each(function(){
					   var memtyp=$(this).val();
					   targets6.push(memtyp);
					   });
				   $("input[name='prfaccesschk']:checked").each(function(){
					   var prfaccess=$(this).val();
					   targets7.push(prfaccess);
					   });
				   $("#Selectblack").val(targets.join("!_!"));
				   $("#Selectflat").val(targets1.join("!_!"));
				   $("#famName").val(targets2.join("!_!"));
				   $("#famMobileNo").val(targets3.join("!_!"));
				   $("#famEmailId").val(targets4.join("!_!"));
				   $("#famisdcode").val(targets5.join("!_!"));
				   $("#fammemtypeid").val(targets6.join("!_!"));
				   $("#famprfaccessid").val(targets7.join("!_!"));


					$("#firstdivid").css("display","block");
					$("#seconddivid").css("display","block");
					$("#thirddivid").css("display","block");
					$("#fourthdivid").css("display","block");
					var blocksel=$("#Selectblack").val();
					var no_flats1=$("#Selectflat").val();
					var noffltflg = true;
					var nofblkflg = true;
					if (blocksel!="") {
						nofblkflg = true;
						$("#maintexcelfile-error").hide();
					} else {
						 $("#maintexcelfile-error").show();
						nofblkflg = false;
					}

					if (no_flats1!="") {
						 noffltflg = true;
						 $("#maintexcelfile-error1").hide();
					} else {
						$("#maintexcelfile-error1").show();
						 noffltflg = false;
					}

					 if((nofblkflg || nofblkflg=="true") && (noffltflg || noffltflg=="true")){
						$("#formValidate").attr("action","usercreationForm");
						$("#formValidate").attr("method","Post");
						$("#formValidate").submit();
					 }else{

					 }
			 });



	 var counter1 = 2;
	 var flag1 = true;
	 $("#addButton1").click(function () {
		 var member=$("#member").val();
		 if(counter1>member){
			 swal("Only " + member
						+ " Family No. Allow");
	            flag1 =false;
		}else{
			 flag1 =true;
		}
		if(flag1){
			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv1' + counter1);
			 newTextBoxDiv.after().html('<div class="card padding10px"><div class="row" id="appenddiv'+counter1+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter1+'"><s:text name="Name" /></label><input type="text" name="blockname" onkeyup="textValidate(this,\"30\",\"OA\");"  id="noofblocks_'+counter1+'" class="form-control famName" /></div></div><div class="input-field col s2"  > <div class="form-group"  ><label for="famisd_'+counter1+'" class="active"><s:text name="Text.customerreg.isd" /></label> <s:textfield  name="famisd_'+counter1+'" id="famisd_'+counter1+'"   cssClass="form-control famIsdNumber" value="%{getText(\'Text.ISD.value\')}" onkeyup="textValidate(this,\"4\",\"PHNM\")"/></div></div><div class="input-field col s4" > <div class="form-group" ><label for="famMobileNo_'+counter1+'" ><s:text name="Text.mobileno" /></label><input type="text" name="famMobileNo_'+counter1+'" id="famMobileNo_'+counter1+'"   class="form-control famMobileNo" onkeyup="textValidate(this,\'15\',\'PHNM\');" /></div></div></div><div class="row" id="appenddiv1_'+counter1+'" ><div class="input-field  col s6"><div class="form-group" ><label for="emailid1_'+counter1+'"" class=" control-label"><s:text name="Text.emailid" /></label><s:textfield cssClass="form-control famEmailId" name="emailId" id="emailid1_'+counter1+'" onblur="toValiEmail(\"emailid1_'+counter1+'\");" /></div> </div>	 <div class="input-field col s3"  style="margin-top:25px"> <label class="active" >Member Type</label><div class="clear height5px"></div><div><input type="checkbox" name="memtypechk" id="member'+counter1+'" class="myCheckbox memtype" value="1" checked/> <label for="member'+counter1+'">Relation</label>&nbsp;<input type="checkbox" name="memtypechk" id="tenent_'+counter1+'" class="myCheckbox memtype" value="2" />&nbsp;<label for="tenent_'+counter1+'">Tenent</label></div></div>    <div class="input-field col s3"  style="margin-top:25px"> <label class="active" for="allow_'+counter1+'">Profile Access</label><div class="clear height5px"></div><div><input type="checkbox" name="prfaccesschk" id="allow_'+counter1+'" class="myCheckbox memtype" value="1" checked/> <label for="allow_'+counter1+'">Allow</label>&nbsp;<input type="checkbox" name="prfaccesschk" id="notallow_'+counter1+'" class="myCheckbox memtype" value="0" />&nbsp;<label for="notallow_'+counter1+'">Not Allow</label></div></div></div></div>');
			 newTextBoxDiv.appendTo("#TextBoxesGroup1");
			counter1++;
		}
		$('.myCheckbox').click(function() {
		    $(this).siblings('input:checkbox').prop('checked', false);
		    $(this).prop('checked', true);
		});
	});

	 $("#member").blur(function(){
		 var member=$("#member").val();
		 if(member==0){
			 if(counter1>member){
				 var cc = (counter1 - 1);
				 $("#member").val(cc);
			}

		}else{
		 if(member<counter1){
			 var ii=0;
			 for(ii=counter1;ii>member;ii--){
				 $("#TextBoxDiv1"+ii).remove();
			 }
			 counter1=(ii+1);
		 }else{
			 flag1 =true;
		 }
		}
	});

	 	$("#mobilenoid").keyup(function(){
		    textValidate(this,'10','PHNM');
		});
		$("#isdcodeid").keyup(function(){
			textValidate(this,'4','PHNM');
		});
		$("#emailid").keyup(function(){
			textValidate(this,'100','EML');
		});
		    $("#idproofno").keyup(function(){
		    	$("#card-error-div").hide();
		    	textValidate(this,'30','EML');
		    });
		    $("#firstname").keyup(function(){
		    	textValidate(this,'30','OA');
		    });
		    $("#lastname").keyup(function(){
		    	textValidate(this,'30','OA');
		    });
		    $("#noOfFlats").keyup(function(){
		    	textValidate(this,'30','EML');
		    });
		    $("#flatnamelist").keyup(function(){
		    	textValidate(this,'30','EML');
		    });
		    $("#address1").keyup(function(){
		    	textValidate(this,'100','ADP');
		    });
		    $("#address2").keyup(function(){
		    	textValidate(this,'100','ADP');
		    });
		    $("#occupation").keyup(function(){
		    	textValidate(this,'100','OA');
		    });
		    $("#member").keyup(function(){
		    	textValidate(this,'3','PHNM');
		    });
		    $("#pstlcode_txt_id").keyup(function(){
		    	textValidate(this,'10','PHNM');
		    });
		     $("#bloodgrp").keyup(function(){
		    	textValidate(this,'15','NV');
		    });
		    $("#mobilenoid").blur(function(){
		    	toValiMobno(this.id);
		    });
		    $("#emailid").blur(function(){
		    	toValiEmail(this.id);
			});
		  /*   $(".blocknamelist").keyup(function(){
		    	alert("ss");
		    	textValidate(this,'30','AI');
		    }); */
});
 $(document).ready(function(){
       $('input[type="checkbox"]').click(function(){
         if($(this).prop("checked") == true){
             $(this).siblings('input:checkbox').prop('checked', false);
         }
         else if($(this).prop("checked") == false){

         }
         });

 	$('#formValidate').validate({
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
	$("#township_txt_id").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.townshipId" />",
			//minlength : "Enter at least 5 characters"
		}
	});

	$("#societyId_txt_id").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.societyId" />",
			//minlength : "Enter at least 5 characters"

		}
	});
	$("#isdcodeid").rules("add", {
		required : true,
		minlength :2,
		messages : {
			required : "<s:text name="Error.customerreg.ISDno.mobileno" />",
		}
	});

	$("#mobilenoid").rules("add", {
		required : true,
		minlength :10,
		maxlength :10,
		messages : {
			required : "<s:text name="Error.customerreg.mobileno.length" />",
			//minlength : "Enter at least 5 characters"
		}
	});
 });
 function cancelFunction(){
	$("#userCancelForm").attr("action", "usermgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}

 function onchangetownshipId() {
 	var townshipid = $("#townshipid").val();
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

 function townshipdata(valjson){
		valjson=valjson.replace(/&quot;/ig,'"');
		var locval = JSON.parse(valjson);
		var objects_cardtype;
	 $('#township_txt_id').typeahead({
		     	order: "asc",
				hint: true,
					accent: true,
					offset: true,
					minLength : 0,
					highlight: true,
					showHintOnFocus:true,
					searchOnFocus: true,
		        source: function(query, process) {
		        	objects_cardtype = [];
		        map = {};
		        var data = locval;
		        $.each(data, function(i, object) {
		            map[object.label] = object;
		            objects_cardtype.push(object.label);
		        });
		        process(objects_cardtype);
		       $(".dropdown-menu").css("height", "auto");
		       $(".dropdown-menu").addClass("form-control");
		    },
		    updater: function(item) {
		        $('#township_hidd_id').val(map[item].id);
		        getsociety(map[item].id);
		        return item;
		    }

		});
	 $('#township_txt_id').blur(function(){
			if (typeof(objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
	        }else{
	        	  //$('#township_txt_id').val('');
	        	 // $('#township_txt_id').focus();
		          //$('#township_hidd_id').val('');
	          }
	      });

	}
$(document).ready(function(){
	
	$("#pstlcode_txt_id").keyup(function(){
		textValidate(this,'10','MNM');
	});
	
	 	var test=$("#societyId_hidd_id").val();
	 	getsocietyonload(test)
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'gettownshipOnload',
			//data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				var spli=data.split("!_!");
				townshipdata(spli[1]);
			}
		});
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getCommitteeOnload',
			//data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				var spli=data.split("!_!");
				committeedata(spli[1]);
			}
		});


	});
	 function committeedata(valjsoncommittee){
	 var objects_committee;
		var committeemap;
		valjsoncommittee=valjsoncommittee.replace(/&quot;/ig,'"');
		var locvalcommittee = JSON.parse(valjsoncommittee);
		$('#committee_txt_id').typeahead({
		     	order: "asc",
				hint: true,
					accent: true,
					offset: true,
					searchOnFocus: true,
		        source: function(query, process) {
		        	objects_committee = [];
		        	committeemap = {};
		        var data = locvalcommittee;
		        $.each(data, function(i, object) {
		        	committeemap[object.label] = object;
		        	objects_committee.push(object.label);
		        });
		        process(objects_committee);
		       $(".dropdown-menu").css("height", "auto");
		       $(".dropdown-menu").addClass("form-control");
		    },
		    updater: function(item) {
		          $('#committee_hidd_id').val(committeemap[item].id);
		       return item;
		    }
		});
	$('#committee_txt_id').blur(function(){
			if (typeof(objects_committee) != "undefined" &&  objects_committee.indexOf(this.value)!=-1) {
	     }else{
	     	 // $('#committee_txt_id').val('');
	     	//  $('#committee_txt_id').focus();
		        //  $('#committee_hidd_id').val('');
	       }
	   });
	 }

	 function getsocietyonload(townid){
		 if(townid.trim().length>0){
			 var temp="";
				$.ajax({
			 		type : 'post',
			 		datatype : 'html',
			 		url : 'townshipgetsociety',
			 		data : 'townshipid=' + townid+"&clfor=autocomp",
			 		success : function(data) {
			 			var spl=data.split("!_!");
			 			temp=spl[1];
			 			getsociety_autoonload(spl[1]);
			 		}
			 	});
		 }
	 }

	 function getsociety(townid){
		var temp="";
		$.ajax({
	 		type : 'post',
	 		datatype : 'html',
	 		url : 'townshipgetsociety',
	 		data : 'townshipid=' + townid+"&clfor=autocomp",
	 		success : function(data) {
	 			var spl=data.split("!_!");
	 			temp=spl[1];
	 			getsociety_auto(spl[1]);
	 			//return temp;
	 		}
	 	});
	}
	var objects_scty;
	function getsociety_autoonload(tempdata){
		tempdata=tempdata.replace(/&quot;/ig,'"');
		var locval = JSON.parse(tempdata);
		var map_scty;
	    $('#societyId_txt_id').typeahead('destroy');
		$('#societyId_txt_id').typeahead({
	     		order: "asc",
				hint: true,
				accent: true,
				offset: true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
				searchOnFocus: true,
	        	source: function(query, process) {
	        	objects_scty = [];
	        	map_scty = {};
	        	var data = locval;
	        	$.each(data, function(i, object) {
	        		map_scty[object.label] = object;
	        		objects_scty.push(object.label);
	        	});
	        	process(objects_scty);
	       		$(".dropdown-menu").css("height", "auto");
	       		$(".dropdown-menu").addClass("form-control");
	    },
	    updater: function(item) {
	        $('#societyId_hidd_id').val(map_scty[item].id);
	        togetsocietyWingslist (map_scty[item].id); // RPK - WINGSBLOCK  - 5
	        return item;
	    }
	});
	$('#societyId_txt_id').typeahead('refresh');
	$('#societyId_txt_id').blur(function(){
	if (typeof(objects_scty) != "undefined" && objects_scty.indexOf(this.value)!=-1) {
	}else{
	 	 //$('#societyId_txt_id').val('');
	 	 // $('#societyId_txt_id').focus();
	     // $('#societyId_hidd_id').val('');
	    //  $('#societyId_txt_id').typeahead('destroy');
	   }
	});
	}
	function getsociety_auto(tempdata){
		tempdata=tempdata.replace(/&quot;/ig,'"');
		var locval = JSON.parse(tempdata);
		var map_scty;
		$('#societyId_txt_id').val('');
	    $('#societyId_txt_id').typeahead('destroy');
		$('#societyId_txt_id').typeahead({
	     		order: "asc",
				hint: true,
				accent: true,
				offset: true,
				searchOnFocus: true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
	        	source: function(query, process) {
	        	objects_scty = [];
	        	map_scty = {};
	        	var data = locval;
	        	$.each(data, function(i, object) {
	        		map_scty[object.label] = object;
	        		objects_scty.push(object.label);
	        	});
	        	process(objects_scty);
	       		$(".dropdown-menu").css("height", "auto");
	       		$(".dropdown-menu").addClass("form-control");
	    },
	    updater: function(item) {
	        $('#societyId_hidd_id').val(map_scty[item].id);
	        togetsocietyWingslist (map_scty[item].id); // RPK - WINGSBLOCK  - 5
	        return item;
	    }
	  //add dynamical validate - 2
	}).on('typeahead:selected', function(e, suggestion, dataSetName) {

    }).on('typeahead:closed', function(e) {

    });
		$('#societyId_txt_id').typeahead('refresh');
		//add dynamical validate - 3

	$('#societyId_txt_id').blur(function(){
		if (typeof(objects_scty) != "undefined" && objects_scty.indexOf(this.value)!=-1) {
     	}else{
     	 // $('#societyId_txt_id').val('');
     	  //$('#societyId_txt_id').focus();
	     // $('#societyId_hidd_id').val('');
	      //$('#societyId_txt_id').typeahead('destroy');
       }
   		});
	}
	function societyIdvalidate(){
			var statval = $("#societyId_txt_id").val();
			if(statval!="" && statval.length>0){
				$("#societid").removeClass("has-error");
				$("#societid").addClass("has-success");
				$("#societid .help-block").attr("data-bv-result","societyId_txt_id");
				$("#societid .help-block").hide();
			}else{
				$("#societid").removeClass("has-success");
				$("#societid").addClass("has-error");
				$("#societid .help-block").attr("data-bv-result","INVALID");
				$("#societid .help-block").show();
			}
	}

	var societyoptiontag ="<option value=''>Select Block/Wings Name</option>";
	function togetsocietyWingslist (pSocietyidd){ // RPK - WINGSBLOCK - 6
		var temp="";
		$.ajax({
	 		type : 'post',
	 		datatype : 'html',
	 		url : 'getwingsblocklist',
	 		data : 'ivrSocietyid=' + pSocietyidd,
	 		success : function(data) {
	 			var spl=data.split("!_!");
	 			societyoptiontag = spl[1];
	 			societyoptiontag=societyoptiontag.replace(/&lt;/ig,'<');
	 			societyoptiontag=societyoptiontag.replace(/&gt;/ig,'>');
	 			if(societyoptiontag==""){
	 				societyoptiontag= "<option value=''>Select Block/Wings Name</option>";
	 			} else {
	 				societyoptiontag = "<option value=''>Select Block/Wings Name</option>"+societyoptiontag;
				}
	 			$("#bloclnameidd").html(societyoptiontag);
	 			$('select').material_select();
	 		}
	 	});
	}


		$(document).ready(function(){
			$.ajax({
				type : 'post',
				datatype : 'json',
				url : 'getidcardvalue',
				data : '',
				success : function(data) {
					var arr=data.split("!_!");
					idcardlistfun(arr[1]);
				}
			});

			 $.ajax({
					type : 'post',
					datatype : 'json',
					url : 'categorytype',
					data : '',
					success : function(data) {
						var arr=data.split("!_!");
						categorylistFun(arr[1]);
					}
				});

			 $.ajax({
					type : 'post',
					datatype : 'json',
					url : 'getcountryvalue',
					data : '',
					success : function(data) {
						var arr=data.split("!_!");
						CountrylistFun(arr[1]);
					}
				});

		});

	 function categorylistFun(catData){
		 	var objects_categorytype;
		 	var map_cat;
		 	catData=catData.replace(/&quot;/ig,'"');
		 	catData=catData.replace(/%27/ig,"'");
		 	var locval = JSON.parse(catData);
		 	$('#category').typeahead('destroy');
		  	$('#category').typeahead({
		 	     		order: "asc",
		 				hint: true,
		 				accent: true,
		 				offset: true,
		 				searchOnFocus: true,
		 	        	source: function(query, process) {

		 	        		objects_categorytype = [];
		 	        		map_cat = {};
		 	        		var data = locval;
		 	        $.each(data, function(i, object) {
		 	        	map_cat[object.label] = object;
		 	        	objects_categorytype.push(object.label);
		 	        });
		 	        process(objects_categorytype);
		 	       $(".dropdown-menu").css("height", "auto");
		 	       $(".dropdown-menu").addClass("form-control");
		 	    },
		 	    updater: function(item) {
		 	        $('#categoryid').val(map_cat[item].id);
		 	        return item;
		 	    }
		 	});
		  $('#category').blur(function(){
		 		if (typeof(objects_categorytype) != "undefined" && objects_categorytype.indexOf(this.value)!=-1) {
		         }else{
		         	  $('#category').val('');
		         	  $('#category').focus();
		 	          $('#categoryid').val('');
		           }
		       });
		 	}
	 function idcardlistfun(ar){
	 	var objects_cardtype;
	 	ar=ar.replace(/&quot;/ig,'"');
	 	ar=ar.replace(/%27/ig,"'");
	 	var locval = JSON.parse(ar);

	  $('#card').typeahead({

	 	     	order: "asc",
	 			hint: true,
	 				accent: true,
	 				offset: true,
	 				minLength : 0,
	 				highlight: true,
	 				showHintOnFocus:true,
	 				searchOnFocus: true,
	 	        source: function(query, process) {

	 	        	objects_cardtype = [];
	 	        map = {};
	 	        var data = locval;
	 	        $.each(data, function(i, object) {
	 	            map[object.label] = object;
	 	            objects_cardtype.push(object.label);
	 	        });
	 	        process(objects_cardtype);
	 	       $(".dropdown-menu").css("height", "auto");
	 	       $(".dropdown-menu").addClass("form-control");
	 	    },
	 	    updater: function(item) {
	 	        $('#cardid').val(map[item].id);
	 	        return item;
	 	    }
	 	});
	  $('#card').blur(function(){
	 		if (typeof(objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
	         }else{
	         	  $('#card').val('');
	         	  $('#card').focus();
	 	          $('#cardid').val('');
	           }
	       });
	 	}
	 var objects_country;
		function CountrylistFun(ar) {

			     ar=ar.replace(/&quot;/ig,'"');
			     ar=ar.replace(/%27/ig,"'");
			var loccutyval = JSON.parse(ar);
			$('#country_txt_id').typeahead({
		     	order: "asc",
				hint: true,
				accent: true,
				offset: true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
				searchOnFocus: true,
		        source: function(query, process) {
		        objects_country = [];
		        map = {};
		        var data = loccutyval;
		        $.each(data, function(i, object) {
		            map[object.label] = object;
		            objects_country.push(object.label);
		        });
		        process(objects_country);
		       $(".dropdown-menu").css("height", "auto");
		       $(".dropdown-menu").addClass("form-control");
		    },
		    updater: function(item) {
		        $('#country_hidd_id').val(map[item].id);

		        $('#state_txt_id').val('');
		          $('#state_hidd_id').val('');
		          $('#state_txt_id').typeahead('destroy');
		          $('#city_txt_id').val('');
		          $('#city_hidd_id').val('');
		          $('#city_txt_id').typeahead('destroy');
// 		          $('#pstlcode_txt_id').val('');
// 	    	  		$('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');

		        onchangeStatecodeAgency();
		        return item;
		    }
		});

		 $('#country_txt_id').blur(function(){
				if (typeof(objects_country) != "undefined" && objects_country.indexOf(this.value)!=-1) {
		        }else{
		        	  $('#country_txt_id').val('');
		        	  $('#country_txt_id').focus();
			          $('#country_hidd_id').val('');
			          $('#state_txt_id').val('');
			          $('#state_hidd_id').val('');
			          $('#state_txt_id').typeahead('destroy');
			          $('#city_txt_id').val('');
			          $('#city_hidd_id').val('');
			          $('#city_txt_id').typeahead('destroy');
// 			          $('#pstlcode_txt_id').val('');
// 		        	  $('#pstlcode_txt_id').typeahead('destroy');
// 			          $('#pstlcode_hidd_id').val('');
		          }
		      });
		}
		function onchangeStatecodeAgency()
		{
			var cntryval = $("#country_hidd_id").val();
			if(cntryval!="" && cntryval!="null" && cntryval!="0" && typeof(cntryval) != "undefined"){
			$.ajax({
				type : 'post',
				datatype : 'json',
				url : 'countryGetStatevalue',
				data : 'countryidkey=' + cntryval,
				success : function(data) {
					var arr=data.split("!_!");
					statelistload(arr[1]);
				}
			});
		}
		}
		var objects_state;
		function statelistload(ar){

			ar=ar.replace(/&quot;/ig,'"');
			ar=ar.replace(/%27/ig,"'");
			var loc_state_val = JSON.parse(ar);
			$('#state_txt_id').typeahead({
		     	order: "asc",
				hint: true,
				accent: true,
				offset: true,
				searchOnFocus: true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
		        source: function(query, process) {
		        objects_state = [];
		        map = {};
		        var data = loc_state_val;
		        $.each(data, function(i, object) {
		            map[object.label] = object;
		            objects_state.push(object.label);
		        });
		        process(objects_state);
		       $(".dropdown-menu").css("height", "auto");
		       $(".dropdown-menu").addClass("form-control");
		    },
		    updater: function(item) {
		        $('#state_hidd_id').val(map[item].id);

		        $('#city_txt_id').val('');
		          $('#city_hidd_id').val('');
		          $('#city_txt_id').typeahead('destroy');
// 		          $('#pstlcode_txt_id').val('');
// 	    	  	  $('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');

		        onchangeCitycodeAgency();
		        return item;
		    }
		});

		 $('#state_txt_id').blur(function(){
				if (typeof(objects_state) != "undefined" && objects_state.indexOf(this.value)!=-1) {
		        }else{
		        	  $('#state_txt_id').val('');
		        	  $('#state_txt_id').focus();
			          $('#state_hidd_id').val('');

			          $('#city_txt_id').val('');
			          $('#city_hidd_id').val('');
			          $('#city_txt_id').typeahead('destroy');
// 			          $('#pstlcode_txt_id').val('');
// 		        	  $('#pstlcode_txt_id').typeahead('destroy');
// 			          $('#pstlcode_hidd_id').val('');

		          }
		      });
			}
			//City Load
			function onchangeCitycodeAgency(){
			var statval = $("#state_hidd_id").val();
			if(statval!="" && statval!="null" && statval!="0" && typeof(statval) != "undefined"){
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'agencyGetCityvalue',
				data : 'stateidkey=' + statval,
				success : function(data){
					var arr=data.split("!_!");
					citylistload(arr[1]);
				}
			});
			}
			}

			function citylistload(arr)
			{
			var objects_city;
				arr=arr.replace(/&quot;/ig,'"');
				arr=arr.replace(/%27/ig,"'");
				var loc_state_val = JSON.parse(arr);
				$('#city_txt_id').typeahead({
			     	order: "asc",
					hint: true,
					accent: true,
					offset: true,
					searchOnFocus: true,
					minLength : 0,
					highlight: true,
					showHintOnFocus:true,
			        source: function(query, process) {
			        objects_city = [];
			        map = {};
			        var data = loc_state_val;
			        $.each(data, function(i, object) {
			            map[object.label] = object;
			            objects_city.push(object.label);
			        });
			        process(objects_city);
			       $(".dropdown-menu").css("height", "auto");
			       $(".dropdown-menu").addClass("form-control");
			    },
			    updater: function(item) {
			        $('#city_hidd_id').val(map[item].id);
// 			        $('#pstlcode_txt_id').val('');
// 			          $('#pstlcode_txt_id').typeahead('destroy');
// 			          $('#pstlcode_hidd_id').val('');

// 			        onchangePstlcodeAgency();
			        return item;
			    }
			});

			 $('#city_txt_id').blur(function(){
					if (typeof(objects_city) != "undefined" && objects_city.indexOf(this.value)!=-1) {
			        }else{
			        	   $('#city_txt_id').val('');
			        	  $('#city_txt_id').focus();
				          $('#city_hidd_id').val('');
// 			        	  $('#pstlcode_txt_id').typeahead('destroy');
// 				          $('#pstlcode_hidd_id').val('');


			          }
			      });
				}
			//pin code loading

			function onchangePstlcodeAgency(){
				var cityval = $("#city_hidd_id").val();
				if(cityval!="" && cityval!="null" && cityval!="0" && typeof(cityval) != "undefined"){
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'agencyGetPstlcodevalue',
					data : 'cityidkey=' + cityval,
					success : function(data){
						var arr=data.split("!_!");
						pstlcodelistload(arr[1]);
					}
				});
			}
		}

			function pstlcodelistload(arr)
			{
			var objects_pstl;
				arr=arr.replace(/&quot;/ig,'"');
				var loc_pstl_val = JSON.parse(arr);
				$('#pstlcode_txt_id').typeahead({
			     	order: "asc",
					hint: true,
					accent: true,
					offset: true,
					minLength : 0,
					highlight: true,
					showHintOnFocus:true,
					searchOnFocus: true,
			        source: function(query, process) {
			        objects_pstl = [];
			        map = {};
			        var data = loc_pstl_val;
			        $.each(data, function(i, object) {
			            map[object.label] = object;
			            objects_pstl.push(object.label);
			        });
			        process(objects_pstl);
			       $(".dropdown-menu").css("height", "auto");
			       $(".dropdown-menu").addClass("form-control");
			    },
			    updater: function(item) {
			        $('#pstlcode_hidd_id').val(map[item].id);
			        return item;
			    }
			});

			 $('#pstlcode_txt_id').blur(function(){
					if (typeof(objects_pstl) != "undefined" && objects_pstl.indexOf(this.value)!=-1) {
			        }else{
			        	  /* $('#pstlcode_txt_id').val('');
			        	  $('#pstlcode_txt_id').focus();
				          $('#pstlcode_hidd_id').val(''); */
			          }
			      });
				}

			function myFunction1(check){
				 if(check=="add"){
					 $('.imgaddminus').css("display","inline");
					 $('#profilehidden').show();
					 $('.imgaddplus').css("display","none");
				 }else if(check=="sub"){
					 $('.imgaddminus').css("display","none");
					 $('.imgaddplus').css("display","inline");
					 $('#profilehidden').hide();
				 }

			}


			function myFunction2(check){

				 if(check=="add"){
					 $('.imgaddminus1').css("display","inline");
					 $('#blockhidden').show();
					 $('.imgaddplus1').css("display","none");
				 }else if(check=="sub"){
					 $('.imgaddminus1').css("display","none");
					 $('.imgaddplus1').css("display","inline");
					 $('#blockhidden').hide();
				 }

			}
			function myFunction3(check){

				 if(check=="add"){

						 $.ajax({
								type : 'post',
								datatype : 'json',
								url : 'getcountryvalue',
								data : '',
								success : function(data) {

									var arr=data.split("!_!");
									//$("#state_txt_id").html(data);
									CountrylistFun(arr[1]);
								}
							});



					 $('.imgaddminus2').css("display","inline");
					 $('#otherhidden').show();
					 $('.imgaddplus2').css("display","none");
				 }else if(check=="sub"){
					 $('.imgaddminus2').css("display","none");
					 $('.imgaddplus2').css("display","inline");
					 $('#otherhidden').hide();
				 }

			}
			function familydetail(check){
				 if(check=="add"){
					 $('.imgaddminus3').css("display","inline");
					 $('#familydetail').show();
					 $('.imgaddplus3').css("display","none");
				 }else if(check=="sub"){
					 $('.imgaddminus3').css("display","none");
					 $('.imgaddplus3').css("display","inline");
					 $('#familydetail').hide();
				 }

			}
			function statecheck(){
				var countryid=$("#country_hidd_id").val();
				if(countryid=="" && countryid!=null){
					$('#state_txt_id').val('');
			          $('#city_txt_id').val('');
// 			          $('#pstlcode_txt_id').val('');
				}
			}
			function citycheck(){
				var stateid=$("#state_hidd_id").val();
				if(stateid=="" && stateid!=null){
			          $('#city_txt_id').val('');
// 			          $('#pstlcode_txt_id').val('');
				}
			}
			function pstlcodecheck(){
// 				var pstlcode=$("#city_hidd_id").val();
// 				if(pstlcode=="" && pstlcode!=null){
// 			          $('#pstlcode_txt_id').val('');
// 				}
			}
</script>
</html>