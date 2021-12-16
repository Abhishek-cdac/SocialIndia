<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible"
	content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight"
	content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<link
	href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css"
	type="text/css" rel="stylesheet" media="screen,projection" />
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link
	href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css"
	type="text/css" rel="stylesheet" media="screen,projection" />
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
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">
									<s:text name="Create Resident" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="residentmgmt"><s:text
												name="Breadcrumb.manage" /></a></li>
									<li><a href="residentmgmt"><s:text
												name="Breadcrumb.manage.restmgmt" /></a></li>
									<li class="active"><s:text name="Create Resident" /></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<div class="card-panel">
						<form id="residentCreationFormId" name="residentCreationFormId"
							action="residentmgmtadd" method="post"
							enctype="multipart/form-data">
							<ul class="collapsible collapsible-accordion"
								data-collapsible="expandable">
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" /> active"
										style="color: #fff;">
										<i
											class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i>
										<s:text name="Text.profile.detail" />
									</div>
									<div class="collapsible-body padding10px" id="firstdivid">
										<div id="profilehidden" class="padding10px"
											style="display: block;">
											<s:if test="#session.sSoctyId==-1">
												<div class="row">
													<div class="input-field col s6">
														<label for="township_txt_id"><s:text
																name="Text.adduser.town.ship.id"></s:text><span
															class="mandatory">*</span></label>
														<s:textfield id="township_txt_id"
															cssClass="form-control typeahead tt-query townshipIdvalidate left"
															autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left"
															onclick="openAutocmp('township_txt_id');"><i
															class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<input type="hidden" name="townshipId"
															id="township_hidd_id" class="form-control " />
													</div>
													<div class="input-field col s6">
														<label for="societyId_txt_id"><s:text
																name="Text.adduser.societyname" /><span
															class="mandatory">*</span></label>
														<s:textfield id="societyId_txt_id" name="societyName"
															cssClass="typeahead tt-query form-control left"
															autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left"
															onclick="openAutocmp('societyId_txt_id');"><i
															class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<input type="hidden" name="restRegObj.societyName"
															id="societyId_hidd_id" class="form-control " />
													</div>
												</div>
											</s:if>
											<s:else>
												<input type="hidden" name="townshipId" id="township_hidd_id"
													class="form-control "
													value="<s:property value="#session.townshipIdStr"/>" />
												<input type="hidden" name="restRegObj.societyName"
													id="societyId_hidd_id" class="form-control "
													value="<s:property value="#session.sSoctyId" />" />
											</s:else>

											<div class="row">
												<div class="input-field col s2">
													<label for="isdcodeid" class="active"><s:text
															name="Text.customerreg.isd" /><span class="mandatory">*</span></label>
													<s:textfield cssClass="form-control IsdNumber"
														name="restRegObj.isdCode" id="isdcodeid"
														value="%{getText('Text.ISD.value')}"></s:textfield>
													<s:fielderror fieldName="restRegObj.userName" />
												</div>
												<div class="input-field col s4">
													<label for="mobilenoid"><s:text
															name="Text.mobileno" /><span class="mandatory">*</span></label>
													<s:textfield cssClass="form-control registerNumber "
														name="restRegObj.mobileNo" id="mobilenoid"></s:textfield>
													<s:fielderror fieldName="usercreateObj.mobileNo" />
													<div id="mobirst" class="error manualscriptvalidation"
														style="display: none;"></div>
												</div>
												<div class="input-field col s6">
													<label for="emailid"><s:text
															name="Text.customerreg.emailid" /></label>
													<s:textfield name="restRegObj.emailId" id="emailid"
														cssClass="form-control" autocomplete="off" />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="card"><s:text
															name="Text.customerreg.idproof" /></label>
													<s:textfield name="ID_CARD_TYP_Name" id="card"
														cssClass="form-control idproofvalidate left"
														autocomplete="off" />
													<span class="input-group-addon txtbxdownarowicon left"
														onclick="openAutocmp('card');"><i
														class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<input type="hidden" name="restRegObj.idCardType"
														id="cardid" class="form-control" />
												</div>
												<div class="input-field col s6">
													<label for="idproofno"><s:text
															name="Text.customerreg.idproofno" /></label>
													<s:textfield name="restRegObj.idProofNo" id="idproofno"
														cssClass="form-control idproofnovalidate" />
													<div id="card-error-div"
														class="error manualscriptvalidation"
														style="display: none;"></div>
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="firstname"><s:text
															name="Text.customerreg.firstname" /></label>
													<s:textfield name="restRegObj.firstName" id="firstname"
														cssClass="form-control typeahead tt-query" />
												</div>
												<div class="input-field col s6">
													<label for="lastname"><s:text
															name="Text.customerreg.lastname" /></label>
													<s:textfield name="restRegObj.lastName" id="lastname"
														cssClass="form-control" />
												</div>
											</div>
											<div class="row">
												<!-- <div id="input-select">	 -->
												<%-- <div class="input-field col s6">
															<label for="gendertype" class=""><s:text name="Text.gender" /></label>
															<s:select id="gendertype" name="restRegObj.gender_txt"  list="#{'1':'Male','2' : 'Female','3' : 'Other'}"/>
															</div> --%>
												<div class="input-field col s6">
													<label for="gendertyp" class="active"><s:text
															name="Gender" /></label>
													<s:select id="gendertyp"
														cssClass="form-control lastnamevalidate"
														name="restRegObj.gender"
														list="#{'1':'Male','2':'Female','3':'Other'}"></s:select>
													<%-- <s:fielderror fieldName="usercreateObj.lastName"/> --%>
													<!-- </div> -->
												</div>
												<div class="input-field col s6">
													<label for="dob"><s:text name="Text.resident.dob" /></label>
													<s:textfield id="dob" cssClass="dateOfBirth"
														name="restRegObj.dob"></s:textfield>
												</div>
											</div>
											<div class="row">
												<div class="input-field">
													<label for="acpid" class="active"><s:text
															name="Text.accesschannel" /></label>
													<div id="acpid">
														<input type="checkbox" name="restRegObj.accessChannel"
															class="myCheckbox" id="test5" value="2" checked="checked" />
														<label for="test5">Mobile</label> <input type="checkbox"
															name="restRegObj.accessChannel" class="myCheckbox"
															id="test6" value="1" disabled="disabled" /> <label
															for="test6">Web</label> <input type="checkbox"
															name="restRegObj.accessChannel" class="myCheckbox"
															id="test7" value="3" disabled="disabled" /> <label
															for="test7">Both</label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</li>
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" />">
										<i
											class="mdi-communication-business tinysmall white-text text-accent-4"></i>
										<s:text name="Text.block.detail" />
									</div>
									<div class="collapsible-body padding10px" id="seconddivid">
										<!--<div style="clear: both; height:20px;"></div><div id="blockdetdiv" >
			<div class="imgaddplus1" onclick="myFunction2('add');"><i class="<s:text name="button.color.addhideshow"/>" ></i>
			<div class="spacialspace">Block Detail  </div></div>
			<div class="imgaddminus1" onclick="myFunction2('sub');" style="display:none; cursor: pointer;"><i class="<s:text name="button.color.minushideshow"/>" ></i>
			<div class="spacialspace">Block Detail  </div></div>
			</div><div style="clear: both; height:5px;"></div>-->
										<div id="blockhidden" class="padding10px"
											style="display: block;">
											<div class="row">
												<div class="input-field col s6">
													<label for="noofblocks" class="active"><s:text
															name="Text.resident.NoofBlock/Wings" /></label>
													<s:textfield name="restRegObj.noOfBlocks" id="noofblocks"
														value="1" cssClass="form-control" />
												</div>
												<div class="input-field col s5">
													<label for="noofflats" class="active"><s:text
															name="Text.resident.noofflats"></s:text></label>
													<s:textfield name="restRegObj.noofFlats" id="noofflats"
														value="1" cssClass="form-control" />
												</div>

												<div class="input-field col s1">
													<button type="button"
														class="<s:text name="button.color.add"/>" id="addButton">
														<i class="mdi-content-add"></i>
													</button>
												</div>
											</div>
											<div class="row">
												<!-- RPK  
		<div class="input-field col s6">
		<label for="blcksidd" class="active"><s:text name="Text.resident.block" /></label>
		<div id="blcksidd"></div>
		</div><!-- RPK  -->
												<div class="input-field col s6">
													<label for="flatno_2" class="active"><s:text
															name="Text.resident.block" /><span class="mandatory">*</span></label>
													<!--// RPK - WINGSBLOCK  - 1 -->
													<!--<s:textfield name="restRegObj.block1" id="flatno_2" cssClass="form-control blocknamelist"/>-->
													<select name="restRegObj.block1" id="flatno_2"
														class="form-control blocknamelist"><option
															value=''>Select Block/Wings Name</option></select>
													<div id="maintexcelfile-error"
														class="error manualscriptvalidation"
														style="display: none;">Select block/wings name is
														required</div>
												</div>


												<div class="input-field col s6">
													<label for="flatno_1"><s:text
															name="Text.resident.flatno"></s:text><span
														class="mandatory">*</span></label>
													<s:textfield name="restRegObj.flatno_1 " id="flatno_1"
														cssClass="form-control flatnamelist" />
													<div id="maintexcelfile-error1"
														class="error manualscriptvalidation"
														style="display: none;">Flat number is required</div>
												</div>
											</div>
											<div id='TextBoxesGroup'>
												<div id="TextBoxDiv"></div>
											</div>
										</div>
									</div>
								</li>
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" />">
										<i
											class="mdi-action-extension tinysmall white-text text-accent-4"></i>
										<s:text name="Text.other.detail" />
									</div>
									<div class="collapsible-body padding10px" id="thirddivid">
										<!-- <div style="clear: both; height:5px;"></div><div id="othersdetdiv" >
										 <div class="imgaddplus2" onclick="myFunction3('add');"><i class="<s:text name="button.color.addhideshow"/>" ></i>
										 <div class="spacialspace">Others Detail  </div></div>
										  <div class="imgaddminus2" onclick="myFunction3('sub');" style="display:none; cursor: pointer;"><i class="<s:text name="button.color.minushideshow"/>" ></i>
										  <div class="spacialspace">Others Detail </div></div>
		</div><div style="clear: both; height:5px;"></div> -->
										<div id="otherhidden" class="padding10px"
											style="display: block;">
											<div class="row">
												<div class="input-field col s6">
													<label for="address1" id="address1_for"><s:text
															name="Address1" /></label>
													<s:textfield name="restRegObj.address1" id="address1"
														cssClass="form-control " />
												</div>
												<div class="input-field col s6">
													<label for="address2"><s:text name="Address2"></s:text></label>
													<s:textfield name="restRegObj.address2" id="address2"
														cssClass="form-control " />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="country_txt_id" id="country_txt_id_for"><s:text
															name="Menuheader.uam.profile.country" /></label>
													<s:textfield name="Country_id_name" id="country_txt_id"
														cssClass="form-control typeahead tt-query left"
														autocomplete="off" />
													<span class="input-group-addon txtbxdownarowicon left"
														onclick="openAutocmp('country_txt_id');"><i
														class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<input type="hidden" name="restRegObj.countryCode"
														id="country_hidd_id" class="form-control" />
													<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
												</div>
												<div class="input-field col s6">
													<label for="state_txt_id" id="state_txt_id_for"><s:text
															name="Menuheader.uam.profile.state" /></label>
													<s:textfield name="State_id_name" id="state_txt_id"
														cssClass="form-control typeahead tt-query left"
														autocomplete="off" />
													<span class="input-group-addon txtbxdownarowicon left"
														onclick="openAutocmp('state_txt_id');"><i
														class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<input type="hidden" name="restRegObj.stateId"
														id="state_hidd_id" class="form-control" />
													<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="city_txt_id" id="city_txt_id_for"><s:text
															name="Menuheader.uam.profile.city" /></label>
													<s:textfield name="City_id_name" id="city_txt_id"
														cssClass="form-control typeahead tt-query left"
														autocomplete="off" />
													<span class="input-group-addon txtbxdownarowicon left"
														onclick="openAutocmp('city_txt_id');"><i
														class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<input type="hidden" name="restRegObj.cityId"
														id="city_hidd_id" class="form-control" />
													<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
												</div>
												<div class="input-field col s6">
													<label for="pstlcode_txt_id" id="pstlcode_txt_id_for"><s:text
															name="Text.customerreg.pincode" /></label>
													<s:textfield name="restRegObj.ivrBnLBR_PSTL_ID"
														id="pstlcode_txt_id"
														cssClass="form-control typeahead tt-query left"
														autocomplete="off" />
													<%-- 															<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
													<span class="input-group-addon txtbxdownarowicon left"></span>
													<input type="hidden" name="restRegObj.pinCode"
														id="pstlcode_hidd_id" class="form-control" />
													<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="occupation"><s:text
															name="Text.resident.occupation" /></label>
													<s:textfield name="restRegObj.occupation" id="occupation"
														cssClass="form-control " />
												</div>
												<div class="input-field col s6">
													<label for="bloodgrp"><s:text
															name="Text.resident.bloodgrp" /></label>
													<s:textfield name="restRegObj.bloodType" id="bloodgrp"
														cssClass="form-control " />
												</div>
											</div>
										</div>

									</div>
								</li>
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" />">
										<i
											class="mdi-social-people tinysmall white-text text-accent-4"></i>
										Family Detail
									</div>
									<div class="collapsible-body padding10px" id="fourthdivid">
										<div id="familydetail" class="padding10px"
											style="display: block;">
											<div class="row">
												<div class="input-field col s6">
													<label class="active"><s:text
															name="Text.resident.familymember" /></label>
													<s:textfield cssClass="form-control familymembers"
														name="restRegObj.membersInFamily" id="member"
														onkeyup="textValidate(this,'10','PHNM');" value="1"></s:textfield>
													<s:fielderror fieldName="usercreateObj.familymembers" />
												</div>
												<div class="input-field col s1">
													<button type="button" id="addButton1"
														class="<s:text name="button.color.add"/>">
														<i class="mdi-content-add"></i>
													</button>
												</div>
											</div>
											<div class="card padding10px">
												<div class="row">
													<div class="input-field col s6">
														<label for="famName1"><s:text name="Name" /></label>
														<s:textfield name="famName1" id="famName1"
															cssClass="form-control famName" autocomplete="off"
															spellcheck="false" />
														<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
													</div>
													<div class="input-field col s1">
														<label for="isdcodeid1"><s:text
																name="Text.customerreg.isd" /></label>
														<s:textfield cssClass="form-control famIsdNumber"
															name="famisdno1" id="isdcodeid1"
															value="%{getText('Text.ISD.value')}"
															onkeyup="textValidate(this,'4','PHNM');"></s:textfield>

													</div>
													<div class="input-field col s5">
														<label for="mobile11noid"><s:text
																name="Text.mobileno" /></label>
														<s:textfield cssClass="form-control famMobileNo "
															name="famMobileNo1" id="mobile11noid"
															onkeyup="textValidate(this,'10','PHNM');"></s:textfield>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s6">
														<label class="emailid1_1"><s:text
																name="Text.emailid" /></label>
														<s:textfield cssClass="form-control famEmailId"
															name="emailId" id="emailid1_1"
															onkeyup="textValidate(this,'100','EML');"
															onblur="toValiEmail(this.id)"></s:textfield>
													</div>
													<div class="input-field col s3">
														<label class="active"><s:text name="Member Type" /></label>
														<div class="clear height15px"></div>
														<input name="memtypechk" id="memtypechk1"
															class="myCheckbox memtype" value="1" checked="checked"
															type="checkbox"> <label for="memtypechk1">Relation</label>
														<input name="memtypechk" id="memtypechk2"
															class="myCheckbox memtype" value="2" type="checkbox">
														<label for="memtypechk2">Tenent</label>
													</div>
													<div class="input-field col s3">
														<label class="active" for="profileaccess"><s:text
																name="Profile Access" /></label>
														<div class="clear height15px"></div>
														<input name="prfaccesschk" id="prfaccesschk1"
															class="myCheckbox prfaccchk" value="1" type="checkbox">
														<label for="prfaccesschk1">Allow</label> <input
															name="prfaccesschk" id="prfaccesschk2"
															class="myCheckbox prfaccchk" value="0" checked="checked"
															type="checkbox"> <label for="prfaccesschk2">Not
															Allow</label>
													</div>
												</div>
											</div>
											<div id='TextBoxesGroup1'>
												<div id="TextBoxDiv1"></div>
											</div>
										</div>
									</div>
								</li>
							</ul>
							<input type="hidden" name="mySelect" id="Selectblack"
								class="form-control " /> <input type="hidden" name="myselect1"
								id="Selectflat" class="form-control " />
							<s:hidden name="famName" id="famName" class="form-control " />
							<s:hidden name="famMobileNo" id="famMobileNo"
								class="form-control " />
							<s:hidden name="famEmailId" id="famEmailId" class="form-control " />
							<s:hidden name="famisdcode" id="famisdcode" class="form-control " />
							<s:hidden name="fammemtype" id="fammemtypeid"
								class="form-control " />
							<s:hidden name="famprfaccess" id="famprfaccessid"
								class="form-control " />
							<div class="row">
								<div class="input-field col s6">
									<button type="button" id="userCreateButtonId"
										class="<s:text name="button.color.submit"/>">
										<s:text name="Text.button.submit" />
										<i class="<s:text name="button.icon.submitcard"/>"></i>
									</button>
									<button type="button" id="userCancelButtonId"
										class="<s:text name="button.color.cancel"/>"
										onclick="cancelFunction();">
										<s:text name="Text.button.cancel" />
										<i class="<s:text name="button.icon.replycard"/>"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				<!--end container-->
			</section>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	<s:hidden name="sltTwshipidfrmtwnmgmt" id="sltTwshipidfrmtwnmgmt"
		value="%{#session.ivrTwsipid}"></s:hidden>
	<s:hidden name="sltTwshipnamefrmtwnmgmt" id="frmtwnmgmtfrmtwnmgmt"
		value="%{#session.ivrTwsipname}"></s:hidden>
	<s:hidden name="sltsocyidfrmtwnmgmt" id="sltsocyidfrmtwnmgmt"
		value="%{#session.ivrSwityid}"></s:hidden>
	<s:hidden name="sltsocynamefrmtwnmgmt" id="sltsocynamefrmtwnmgmt"
		value="%{#session.ivrSwityname}"></s:hidden>
</body>
<script>
	var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
	$(document)
			.ready(
					function() {

						$("#pstlcode_txt_id").keyup(function() {
							textValidate(this, '10', 'MNM');
						});

						$('#pstlcode_txt_id').blur(
								function() {
									//     	alert($('#pstlcode_txt_id').val());
									$('#pstlcode_hidd_id').val(
											$('#pstlcode_txt_id').val());
								});

						getcntrylist('add');
						var exigndr = $("#gendertyp").html();
						$("#gendertyp").html(
								'<option value="" disabled selected>Choose your gender</option>'
										+ exigndr);
						$("#gendertyp").material_select();
						$('#gendertype').change(function() {
							if ($(this).val() != "") {
								$(this).valid();
							}
						});
						$('.myCheckbox').click(
								function() {
									$(this).siblings('input:checkbox').prop(
											'checked', false);
									$(this).prop('checked', true);
								});
						if (pvrLoginusrsoctyid == "-1"
								|| pvrLoginusrsoctyid == "null"
								|| pvrLoginusrsoctyid == "") {//socytea id not found in session - Admin login */

							$.ajax({
								type : 'post',
								datatype : 'json',
								url : 'getresidentform',
								data : '',
								success : function(data) {
									var arr = data.split("!_!");
									townshipfun(arr[1]);
								}
							});
						} else {
							togetsocietyWingslist(pvrLoginusrsoctyid);// RPK - WINGSBLOCK  - 2  
							onchangetownship();
						}
						$.ajax({
							type : 'post',
							datatype : 'json',
							url : 'getidcardvalue',
							data : '',
							success : function(data) {
								var arr = data.split("!_!");
								idcardlistfun(arr[1]);
							}
						});
						$("#imgdivid").click(function() {
							$("#staffImage").click();
						});
						var counter = 2;
						var flag = true;
						$("#addButton")
								.click(
										function() {
											var no_flats = $("#noofflats")
													.val();
											if (counter > no_flats) {
												swal("Only " + no_flats
														+ " Flat allow");
												flag = false;
											} else {
												flag = true;
											}
											if (flag) {
												var newTextBoxDiv = $(
														document
																.createElement('div'))
														.attr(
																"id",
																'TextBoxDiv'
																		+ counter);
												//newTextBoxDiv.after().html('<div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'"><s:text name="Text.resident.block" /></label><input type="text" name="blockname" id="noofblocks_'+counter+'" class="form-control blocknamelist" /></div></div><div class="input-field col s6" > <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.resident.flatno"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'" maxlength="20" cssClass="form-control flatnamelist" /></div></div></div>');		// text box           
												newTextBoxDiv
														.after()
														.html(
																'<div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'" class="active"><s:text name="Text.resident.block" /></label><select name="blockname" id="noofblocks_'+counter+'" class="form-control blocknamelist" >'
																		+ societyoptiontag
																		+ '</select></div></div><div class="input-field col s6" > <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.resident.flatno"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'" maxlength="20" cssClass="form-control flatnamelist" /></div></div></div>');// select box	// RPK - WINGSBLOCK  - 3  	    
												newTextBoxDiv
														.appendTo("#TextBoxesGroup");
												counter++;
												$('select').material_select();
											}

										});
						$("#noofflats").blur(function() {
							var no_flats = $("#noofflats").val();
							if (no_flats == 0) {
								if (counter > no_flats) {
									var cc = (counter - 1);
									$("#noofflats").val(cc);

								}
							} else {
								if (no_flats < counter) {
									var ii = 0;
									for (ii = counter; ii > no_flats; ii--) {
										$("#TextBoxDiv" + ii).remove();
									}
									counter = (ii + 1);
								} else {
									flag = true;
								}
							}

						});
						if (pvrLoginusrsoctyid == "-1"
								|| pvrLoginusrsoctyid == "null"
								|| pvrLoginusrsoctyid == "") {//socytea id not found in session - Admin login	
							$("#township_txt_id").keyup(function() {
								textValidate(this, '100', 'AI');
							});
							$("#societyId_txt_id").keyup(function() {
								textValidate(this, '100', 'AI');
							});
						}
						$("#mobilenoid").keyup(function() {
							textValidate(this, '10', 'PHNM');
						});
						$("#isdcodeid").keyup(function() {
							textValidate(this, '4', 'PHNM');
						});
						$("#noofflats").keyup(function() {
							textValidate(this, '3', 'PHNM');
						});
						$("#noofblocks").keyup(function() {
							textValidate(this, '3', 'PHNM');
						});
						$("#emailid").keyup(function() {
							textValidate(this, '100', 'EML');
						});
						$("#idproofno").keyup(function() {
							$("#card-error-div").hide();
							textValidate(this, '30', 'EML');
						});
						$("#firstname").keyup(function() {
							textValidate(this, '30', 'OA');
						});
						$("#lastname").keyup(function() {
							textValidate(this, '30', 'OA');
						});
						$(".blocknamelist").keyup(function() {
							textValidate(this, '30', 'EML');
						});
						$(".flatnamelist").keyup(function() {
							textValidate(this, '30', 'EML');
						});
						$("#address1").keyup(function() {
							textValidate(this, '100', 'ADP');
						});
						$("#address2").keyup(function() {
							textValidate(this, '100', 'ADP');
						});
						$("#occupation").keyup(function() {
							textValidate(this, '100', 'ADP');
						});
						$("#member").keyup(function() {
							textValidate(this, '3', 'EML');
						});
						$("#bloodgrp").keyup(function() {
							textValidate(this, '15', 'NV');
						});
						$("#emailid").blur(function() {
							toValiEmail(this.id);
						});
						$("#mobilenoid").blur(function() {
							toValiMobno(this.id);
							toCheckMobexits();
						});

						$('#residentCreationFormId').validate({
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

						if (pvrLoginusrsoctyid == "-1"
								|| pvrLoginusrsoctyid == "null"
								|| pvrLoginusrsoctyid == "") {//socytea id not found in session - Admin login
							$("#township_txt_id")
									.rules(
											"add",
											{
												required : true,
												maxlength : 50,
												messages : {
													required : "<s:text name="Error.customerreg.townshipId"/>"
												}

											});
							$("#societyId_txt_id")
									.rules(
											"add",
											{
												required : true,
												maxlength : 50,
												messages : {
													required : "<s:text name="Error.society.create.empty" />"
												}
											});
						}
						$("#isdcodeid")
								.rules(
										"add",
										{
											required : true,
											minlength : 1,
											maxlength : 4,
											messages : {
												required : "<s:text name="Error.customerreg.isd" />"
											}
										});
						$("#mobilenoid")
								.rules(
										"add",
										{
											required : true,
											number : true,
											minlength : 10,
											maxlength : 10,
											messages : {
												required : "<s:text name="Error.customerreg.mobileno" />"
											}
										});
					});

	function familypurpose() {
		var targets = [];
		var targets1 = [];
		var targets2 = [];
		var targets3 = [];
		var targets4 = [];
		var targets5 = [];
		var targets6 = [];
		var targets7 = [];
		//$.each($(".blocknamelist"), function(){ // old - for text box // RPK - WINGSBLOCK
		//targets.push($(this).val());
		// });
		$("select.blocknamelist")
				.each(
						function() { // RPK - WINGSBLOCK  - 4    	
							var contentPanelId = jQuery(this).attr("id");
							var selecttxt = $(
									"#" + contentPanelId + " option:selected")
									.text();
							var selectval = $(
									"#" + contentPanelId + " option:selected")
									.val();
							if (selectval == "") {
								targets.push("");
							} else {
								targets.push(selecttxt);
							}
						});
		$.each($(".flatnamelist"), function() {
			targets1.push($(this).val());
		});
		$.each($(".famName"), function() {
			var name = $(this).val();
			targets2.push(name);
		});
		$.each($(".famMobileNo"), function() {
			var mob = $(this).val();
			targets3.push(mob);
		});
		$.each($(".famEmailId"), function() {
			var email = $(this).val();
			targets4.push(email);
		});
		$.each($(".famIsdNumber"), function() {
			var isd = $(this).val();
			targets5.push(isd);
		});
		$("input[name='memtypechk']:checked").each(function() {
			var memtyp = $(this).val();
			targets6.push(memtyp);
		});
		$("input[name='prfaccesschk']:checked").each(function() {
			var prfaccess = $(this).val();
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
	}

	$(document)
			.ready(
					function() {

						$("#userCreateButtonId")
								.click(
										function() {

											//start: card validation
											var cardtyp = $("#card").val();
											var cardno = $("#idproofno").val();

											if (cardtyp == "Aadhaar Card") {
												if (isNaN(cardno)) {
													$("#card-error-div")
															.text(
																	"Please enter numbers only.");
													$("#card-error-div").show();
													return false;
												}
												$("#card-error-div").hide();
											} else {
												var letters = /^[0-9a-zA-Z]+$/;
												if (!cardno.match(letters)) {
													$("#card-error-div")
															.text(
																	"Please enter alphanumeric characters only.");
													$("#card-error-div").show();
													return false;
												}
												$("#card-error-div").hide();
											}
											//end: card validation

											familypurpose();

											$("#firstdivid").css("display",
													"block");
											$("#seconddivid").css("display",
													"block");
											$("#thirddivid").css("display",
													"block");
											$("#fourthdivid").css("display",
													"block");
											var blocksel = $("#Selectblack")
													.val();
											var no_flats1 = $("#Selectflat")
													.val();
											var noffltflg = true;
											var nofblkflg = true;
											if (blocksel != "") {
												nofblkflg = true;
												$("#maintexcelfile-error")
														.hide();
											} else {
												$("#maintexcelfile-error")
														.show();
												nofblkflg = false;
											}

											if (no_flats1 != "") {
												noffltflg = true;
												$("#maintexcelfile-error1")
														.hide();
											} else {
												$("#maintexcelfile-error1")
														.show();
												noffltflg = false;
											}
											var mobilnovalidmanu = toCheckMobexits();
											if ((nofblkflg || nofblkflg == "true")
													&& (noffltflg || noffltflg == "true")
													&& (mobilnovalidmanu || mobilnovalidmanu == "true")) {
												$("#residentCreationFormId")
														.attr("action",
																"residentmgmtadd");
												$("#residentCreationFormId")
														.attr("method", "Post");
												$("#residentCreationFormId")
														.attr("enctype",
																"multipart/form-data");
												$("#residentCreationFormId")
														.submit();
											} else {

											}

										});

						var counter1 = 2;
						var flag1 = true;
						$("#addButton1")
								.click(
										function() {
											var member = $("#member").val();
											if (counter1 > member) {
												swal("Only "
														+ member
														+ " Family member allow");
												flag1 = false;
											} else {
												flag1 = true;
											}
											if (flag1) {
												var newTextBoxDiv = $(
														document
																.createElement('div'))
														.attr(
																"id",
																'TextBoxDiv1'
																		+ counter1);
												/*  newTextBoxDiv.after().html('<div class="row" id="appenddiv'+counter1+'"><div class="input-field col s6"><label for="noofblocks_'+counter1+'"><s:text name="Name" /></label><input type="text" name="blockname"  id="noofblocks_'+counter1+'" class="form-control famName" /></div><div class="input-field col s1"><label for="famisd_'+counter1+'" class="active"><s:text name="Text.customerreg.isd" /></label> <s:textfield  name="famisd_'+counter1+'" id="famisd_'+counter1+'" cssClass="form-control famIsdNumber" value="%{getText(\'Text.ISD.value\')}"/></div><div class="input-field col s5" ><label  for="famMobileNo_'+counter1+'"><s:text name="Text.mobileno" /></label><s:textfield name="famMobileNo_'+counter1+'" id="famMobileNo_'+counter1+'" cssClass="form-control famMobileNo" /></div></div><div class="row" id="appenddiv1_'+counter1+'" ><div class="input-field col s6"><label for="emailid1_'+counter1+'"><s:text name="Text.emailid"/></label><s:textfield cssClass="form-control famEmailId" name="emailid1_'+counter1+'" id="emailid1_'+counter1+'" onblur="toValiEmail(\"emailid1_'+counter1+'\");" /></div><div class="col s3"><label>Member Type</label><p><input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="option" value="1" checked/> <label for="option">Relation</label>&nbsp;<input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="option1" value="2" />&nbsp;<label for="option1">Tenent</label></p></div> <div class="col s3"> <label>Profile Access </label><p><input type="checkbox" name="prfaccesschk" id="prfaccesschk1'+counter1+'" class="myCheckbox prfaccchk" value="1" /> <label for="prfaccesschk1'+counter1+'">Allow</label>&nbsp;<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="prfaccesschk'+counter1+'" value="2" checked/>&nbsp;<label for="prfaccesschk'+counter1+'">Not Allow</label></p></div></div><hr style="border-bottom:dotted" />'); */
												newTextBoxDiv
														.after()
														.html(
																'<div class="card padding10px"><div class="row" id="appenddiv'+counter1+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter1+'"><s:text name="Name" /></label><input type="text" name="blockname"   id="noofblocks_'+counter1+'" class="form-control famName" /></div></div><div class="input-field col s2"  > <div class="form-group"  ><label for="famisd_'+counter1+'" class="active"><s:text name="Text.customerreg.isd" /></label> <s:textfield  name="famisd_'
																		+ counter1
																		+ '" id="famisd_'
																		+ counter1
																		+ '"   cssClass="form-control famIsdNumber" value="%{getText(\'Text.ISD.value\')}" onkeyup="textValidate(this,\"4\",\"PHNM\")"/></div></div><div class="input-field col s4" > <div class="form-group" ><label for="famMobileNo_'+counter1+'" ><s:text name="Text.mobileno" /></label><s:textfield name="famMobileNo_'+counter1+'" id="famMobileNo_'+counter1+'"   cssClass="form-control famMobileNo" /></div></div></div><div class="row" id="appenddiv1_'+counter1+'" ><div class="input-field  col s6"><div class="form-group" ><label for="emailid1_'+counter1+'"" class=" control-label"><s:text name="Text.emailid" /></label><s:textfield cssClass="form-control famEmailId" name="emailId" id="emailid1_'
																		+ counter1
																		+ '" onblur="toValiEmail(\"emailid1_'
																		+ counter1
																		+ '\");" /></div> </div>	 <div class="input-field col s3"  style="margin-top:25px"> <label class="active" >Member Type</label><div class="clear height5px"></div><div><input type="checkbox" name="memtypechk" id="member'+counter1+'" class="myCheckbox memtype" value="1" checked/> <label for="member'+counter1+'">Relation</label>&nbsp;<input type="checkbox" name="memtypechk" id="tenent_'+counter1+'" class="myCheckbox memtype" value="2" />&nbsp;<label for="tenent_'+counter1+'">Tenent</label></div></div>    <div class="input-field col s3"  style="margin-top:25px"> <label class="active" for="allow_'+counter1+'">Profile Access</label><div class="clear height5px"></div><div><input type="checkbox" name="prfaccesschk" id="allow_'+counter1+'" class="myCheckbox memtype" value="1" checked/> <label for="allow_'+counter1+'">Allow</label>&nbsp;<input type="checkbox" name="prfaccesschk" id="notallow_'+counter1+'" class="myCheckbox memtype" value="0" />&nbsp;<label for="notallow_'+counter1+'">Not Allow</label></div></div></div></div>');
												newTextBoxDiv
														.appendTo("#TextBoxesGroup1");
												counter1++;
											}
											$('.myCheckbox')
													.click(
															function() {
																$(this)
																		.siblings(
																				'input:checkbox')
																		.prop(
																				'checked',
																				false);
																$(this)
																		.prop(
																				'checked',
																				true);
															});
										});

						$("#member").blur(function() {
							var member = $("#member").val();
							if (member == 0) {
								if (counter1 > member) {
									var cc = (counter1 - 1);
									$("#member").val(cc);
								}
							} else {
								if (member < counter1) {
									var ii = 0;
									for (ii = counter1; ii > member; ii--) {
										$("#TextBoxDiv1" + ii).remove();
									}
									counter1 = (ii + 1);
								} else {
									flag1 = true;
								}
							}
						});
					});

	function townshipfun(ar) {
		var twnmap;
		var objects_township = [];
		ar = ar.replace(/&quot;/ig, '"');
		var locval = JSON.parse(ar);
		$('#township_txt_id').typeahead({
			order : "asc",
			hint : true,
			minLength : 0,
			highlight : true,
			showHintOnFocus : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
				objects_township = [];
				twnmap = {};
				var data = locval;
				$.each(data, function(i, object) {
					twnmap[object.label] = object;
					objects_township.push(object.label);
				});
				process(objects_township);
				$(".dropdown-menu").css("height", "auto");
				$(".dropdown-menu").addClass("form-control");
			},
			updater : function(item) {
				$('#township_hidd_id').val(twnmap[item].id);
				onchangetownship();
				$('#societyId_txt_id').val('');
				getsociety(twnmap[item].id);
				return item;
			}
		});
		$('#township_txt_id').click(
				function() {
					if ($('#township_txt_id').val() != "undefined"
							&& $('#township_txt_id').val()!="") {
						$('#township_txt_id').val('');
						$('#township_txt_id').focus();
						$('#township_hidd_id').val('');
						$('#societyId_txt_id').val('');
						$('#societyId_hidd_id').val('');
						$('#societyId_txt_id').typeahead('destroy');
						$('#societyId_txt_id').focus();
						
					}
					$('#township_txt_id').focus();
				});
	}

	function getsociety(townid) {
		var temp = "";
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'townshipgetsociety',
			data : 'townshipid=' + townid + "&clfor=autocomp",
			success : function(data) {
				var spl = data.split("!_!");
				temp = spl[1];
				societylistfun(spl[1]);
				//return temp;
			}
		});
	}
	var objects_society;
	function societylistfun(ar) {
		ar = ar.replace(/&quot;/ig, '"');
		var loc_state_val = JSON.parse(ar);
		$('#societyId_txt_id').typeahead('destroy');
		$('#societyId_txt_id').typeahead({
			order : "asc",
			hint : true,
			minLength : 0,
			highlight : true,
			showHintOnFocus : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
				objects_society = [];
				map = {};
				var data = loc_state_val;
				$.each(data, function(i, object) {
					map[object.label] = object;
					objects_society.push(object.label);
				});
				process(objects_society);
				$(".dropdown-menu").css("height", "auto");
				$(".dropdown-menu").addClass("form-control");
			},
			updater : function(item) {
				$('#societyId_hidd_id').val(map[item].id);
				togetsocietyWingslist(map[item].id); // RPK - WINGSBLOCK  - 5     
				return item;
			}
		}).on('typeahead:selected', function(e, suggestion, dataSetName) {
		}).on('typeahead:closed', function(e) {
		});

		// $('#societyId_txt_id').typeahead('refresh');	
		$('#societyId_txt_id').click(
				function() {
					if ($('#societyId_txt_id').val() != "undefined"
						&& $('#societyId_txt_id').val()!="") {
						$('#societyId_txt_id').val('');
						$('#societyId_txt_id').focus();
						$('#societyId_hidd_id').val('');
						$('#societyId_txt_id').typeahead('destroy');
					}
				});
	}

	var societyoptiontag = "<option value=''>Select Block/Wings Name</option>";
	function togetsocietyWingslist(pSocietyidd) { // RPK - WINGSBLOCK - 6
		var temp = "";
		$
				.ajax({
					type : 'post',
					datatype : 'html',
					url : 'getwingsblocklist',
					data : 'ivrSocietyid=' + pSocietyidd,
					success : function(data) {
						var spl = data.split("!_!");
						societyoptiontag = spl[1];
						societyoptiontag = societyoptiontag.replace(/&lt;/ig,
								'<');
						societyoptiontag = societyoptiontag.replace(/&gt;/ig,
								'>');
						if (societyoptiontag == "") {
							societyoptiontag = "<option value=''>Select Block/Wings Name</option>";
						} else {
							societyoptiontag = "<option value=''>Select Block/Wings Name</option>"
									+ societyoptiontag;
						}
						$("#flatno_2").html(societyoptiontag);
						$('select').material_select();
					}
				});
	}

	function idcardlistfun(ar) {
		var objects_cardtype;
		ar = ar.replace(/&quot;/ig, '"');
		var locval = JSON.parse(ar);
		$('#card').typeahead({
			order : "asc",
			hint : true,
			minLength : 0,
			highlight : true,
			showHintOnFocus : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
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
			updater : function(item) {
				$('#cardid').val(map[item].id);
				return item;
			}
		});
		$('#card').click(
				function() {
					if ($('#card').val() != "undefined"
						&& $('#card').val()!="") {
						$('#card').val('');
						$('#cardid').val('');
						$('#card').focus();
					} 

				});
	}

	var cntload = false;
	function getcntrylist(check) {
		if (check == "add" && cntload == false) {
			$.ajax({
				type : 'post',
				datatype : 'json',
				url : 'getcountryvalue',
				data : '',
				success : function(data) {
					var arr = data.split("!_!");
					CountrylistFun(arr[1]);
				}
			});
		} else if (check == "sub") {

		}

	}

	function CountrylistFun(ar) {
		cntload = true;
		var objects_country;
		ar = ar.replace(/&quot;/ig, '"');
		var loccutyval = JSON.parse(ar);
		$('#country_txt_id').typeahead('destroy');
		$('#country_txt_id').typeahead({
			order : "asc",
			hint : true,
			minLength : 0,
			highlight : true,
			showHintOnFocus : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
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
			updater : function(item) {
				$('#country_hidd_id').val(map[item].id);
				$('#state_txt_id').val('');
				$('#state_hidd_id').val('');
				$('#state_txt_id').typeahead('destroy');
				$('#city_txt_id').val('');
				$('#city_hidd_id').val('');
				$('#city_txt_id').typeahead('destroy');
				onchangeStatecodeAgency();
				return item;
			}
		});

		$('#country_txt_id').click(
				function() {
					
					if ($('#country_txt_id').val() != "undefined"
						&& $('#country_txt_id').val() != '') {
						$('#country_txt_id').val('');
						$('#country_txt_id').focus();
						$('#country_hidd_id').val('');
						//statelist
						$('#state_txt_id').val('');
						$('#state_hidd_id').val('');
						$('#state_txt_id').typeahead('destroy');
						// citylist
						$('#city_txt_id').val('');
						$('#city_hidd_id').val('');
						$('#city_txt_id').typeahead('destroy');
						cntload=false;
						getcntrylist('add');
						
					
					}
					$('#country_txt_id').focus();
				});
	}
	function onchangeStatecodeAgency() {
		var cntryval = $("#country_hidd_id").val();
		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'countryGetStatevalue',
			data : 'countryidkey=' + cntryval,
			success : function(data) {
				var arr = data.split("!_!");
				statelistload(arr[1]);
			}
		});
	}
	function statelistload(ar) {
		var objects_state;
		ar = ar.replace(/&quot;/ig, '"');
		var loc_state_val = JSON.parse(ar);
		$('#state_txt_id').typeahead('destroy');
		$('#state_txt_id').typeahead({
			order : "asc",
			hint : true,
			minLength : 0,
			highlight : true,
			showHintOnFocus : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
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
			updater : function(item) {
				$('#state_hidd_id').val(map[item].id);
				$('#city_txt_id').val('');
				$('#city_hidd_id').val('');
				$('#city_txt_id').typeahead('destroy');
				//         $('#pstlcode_txt_id').val('');        	         	   	 
				//         $('#pstlcode_hidd_id').val('');
				//         $('#pstlcode_txt_id').typeahead('destroy');
				onchangeCitycodeAgency();
				return item;
			}
		});

		$('#state_txt_id').click(
				function() {
					if ($('#state_hidd_id').val() != "undefined"
							&& $('#state_hidd_id').val() != '') {
						onchangeStatecodeAgency();
						$('#state_txt_id').val('');
						$('#state_txt_id').focus();
						$('#state_hidd_id').val('');
						$('#city_txt_id').val('');
						$('#city_hidd_id').val('');
						$('#city_txt_id').typeahead('destroy');
						$('#state_txt_id').focus();
					}
					
				});
	}
	//City Load
	function onchangeCitycodeAgency() {
		var statval = $("#state_hidd_id").val();
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'agencyGetCityvalue',
			data : 'stateidkey=' + statval,
			success : function(data) {
				var arr = data.split("!_!");
				//$("#state_txt_id").html(data);
				citylistload(arr[1]);
			}
		});
	}

	function citylistload(arr) {
		var objects_city;
		arr = arr.replace(/&quot;/ig, '"');
		var loc_state_val = JSON.parse(arr);
		$('#city_txt_id').typeahead('destroy');
		$('#city_txt_id').typeahead({
			order : "asc",
			hint : true,
			minLength : 0,
			highlight : true,
			showHintOnFocus : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
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
			updater : function(item) {
				$('#city_hidd_id').val(map[item].id);
				// 	        $('#pstlcode_txt_id').val('');    
				// 	        $('#pstlcode_hidd_id').val('');     	         	 
				//       	  	$('#pstlcode_txt_id').typeahead('destroy');
				// 	        onchangePstlcodeAgency();
				return item;
			}
		});

		$('#city_txt_id').click(
				function() {
					
					if ($('#city_hidd_id').val() != "undefined"
						&& $('#city_hidd_id').val() != '') {
						onchangeCitycodeAgency();
						$('#city_txt_id').val('');
						$('#city_txt_id').focus();
						$('#city_hidd_id').val('');
						$('#city_txt_id').focus();
					}
					
				});
	}

	//pin code loading
	function onchangePstlcodeAgency() {
		var cityval = $("#city_hidd_id").val();
		//alert(cityval);
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'agencyGetPstlcodevalue',
			data : 'cityidkey=' + cityval,
			success : function(data) {

				var arr = data.split("!_!");
				//$("#state_txt_id").html(data);
				pstlcodelistload(arr[1]);
			}
		});
	}
	function pstlcodelistload(arr) {
		var objects_pstl;
		arr = arr.replace(/&quot;/ig, '"');
		var loc_pstl_val = JSON.parse(arr);
		$('#pstlcode_txt_id').typeahead('destroy');
		$('#pstlcode_txt_id').typeahead({
			order : "asc",
			hint : true,
			minLength : 0,
			highlight : true,
			showHintOnFocus : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
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
			updater : function(item) {
				$('#pstlcode_hidd_id').val(map[item].id);
				// onchangeCitycodeAgency();
				return item;
			}
		});

		$('#pstlcode_txt_id').blur(
				function() {
					if (typeof (objects_pstl) != "undefined"
							&& objects_pstl.indexOf(this.value) != -1) {
					} else {
						$('#pstlcode_txt_id').val('');
						$('#pstlcode_txt_id').focus();
						$('#pstlcode_hidd_id').val('');

					}
				});
	}

	function myFunction1(check) {
		if (check == "add") {
			$('.imgaddminus').css("display", "inline");
			$('#profilehidden').show();
			$('.imgaddplus').css("display", "none");
		} else if (check == "sub") {
			$('.imgaddminus').css("display", "none");
			$('.imgaddplus').css("display", "inline");
			$('#profilehidden').hide();
		}

	}
	function myFunction2(check) {

		if (check == "add") {
			$('.imgaddminus1').css("display", "inline");
			$('#blockhidden').show();
			$('.imgaddplus1').css("display", "none");
		} else if (check == "sub") {
			$('.imgaddminus1').css("display", "none");
			$('.imgaddplus1').css("display", "inline");
			$('#blockhidden').hide();
		}

	}

	function familydetail(check) {

		if (check == "add") {
			$('.imgaddminus3').css("display", "inline");
			$('#familydetail').show();
			$('.imgaddplus3').css("display", "none");
		} else if (check == "sub") {
			$('.imgaddminus3').css("display", "none");
			$('.imgaddplus3').css("display", "inline");
			$('#familydetail').hide();
		}

	}
	function cancelFunction() {
		$("#userCancelForm").attr("action", "residentmgmt");
		$("#userCancelForm").submit();
		$('#userCancelButtonId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass(
				'<s:text name="cardpanel.button.animated.flipoutx"/>');
	}

	function onchangetownship() {
		var townshipid = $("#township_hidd_id").val();
		//alert(townshipid);
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getTownshipDataRst',
			data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				var lvrAds = data.split("!_!");
				var cntrydtls = lvrAds[1].split("_*_");
				var statedtls = lvrAds[2].split("_*_");
				var citytls = lvrAds[3].split("_*_");
				var pincdtls = lvrAds[4].split("_*_");

				$("#country_txt_id").val(cntrydtls[0]);
				$("#country_hidd_id").val(cntrydtls[1]);
				$("#state_txt_id").val(statedtls[0]);
				$("#state_hidd_id").val(statedtls[1]);
				$("#city_txt_id").val(citytls[0]);
				$("#city_hidd_id").val(citytls[1]);
				$("#pstlcode_txt_id").val(pincdtls[0]);
				// 				$("#pstlcode_hidd_id").val(pincdtls[1]);
				$("#pstlcode_hidd_id").val(pincdtls[0]);
				$("#address1").val(lvrAds[5]);
				$("#pstlcode_txt_id_for").addClass("active");
				$("#city_txt_id_for").addClass("active");
				$("#state_txt_id_for").addClass("active");
				$("#country_txt_id_for").addClass("active");
				$("#address1_for").addClass("active");
				onchangeStatecodeAgency();
				onchangeCitycodeAgency();
				// 				 onchangePstlcodeAgency();
			}
		});
	}
	function toCheckMobexits() { // Mobile no validation
		var lvrSocietyid = $("#societyId_hidd_id").val();
		var lvrMobno = $("#mobilenoid").val();
		var lvrRst = true;
		if (typeof (lvrSocietyid) == "undefined") {
			lvrSocietyid = "";
		}
		if (lvrMobno.trim().length > 0) {
			$
					.ajax({
						type : 'post',
						datatype : 'html',
						url : 'validatersiedentmob',
						async : false,
						data : 'societyID=' + lvrSocietyid + "&ivrMobileno="
								+ lvrMobno,
						success : function(data) {
							var arr = data.split("!_!");
							if (arr[1] == "Exists") {
								$("#mobirst")
										.html(
												"Mobile No. already exist in this society.");
								$("#mobirst").show();
								$("#mobilenoid").focus();
								lvrRst = false;
							} else {
								lvrRst = true;
								$("#mobirst").html("");
								$("#mobirst").hide();
							}

						}

					});
		} else {
			$("#mobirst").html("");
			$("#mobirst").hide();
		}
		return lvrRst;
	}
	$(document)
			.ready(
					function() {
						if (pvrLoginusrsoctyid == "-1"
								|| pvrLoginusrsoctyid == "null"
								|| pvrLoginusrsoctyid == "") {//socytea id not found in session - Admin login
							var lvrShrtTwshipid = '<s:property value="#session.ivrTwsipid"/>';
							var lvrShrtTwshipname = '<s:property value="#session.ivrTwsipname"/>';
							var lvrShrtSocytid = '<s:property value="#session.ivrSwityid"/>';
							var lvrShrtSocytname = '<s:property value="#session.ivrSwityname"/>';
							if ((lvrShrtTwshipid != "null"
									&& lvrShrtTwshipid.length > 0 && lvrShrtTwshipid != "")
									&& (lvrShrtTwshipname != "null"
											&& lvrShrtTwshipname.length > 0 && lvrShrtTwshipname != "")) {
								$('#township_txt_id').val(lvrShrtTwshipname);
								$('#township_hidd_id').val(lvrShrtTwshipid);
								getsociety(lvrShrtTwshipid);
								$('#societyId_txt_id').val(lvrShrtSocytname);
								$('#societyId_hidd_id').val(lvrShrtSocytid);
								onchangetownship();

							} else {
							}
						}
					});
</script>
</html>
