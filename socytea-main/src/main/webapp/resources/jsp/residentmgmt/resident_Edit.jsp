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

 <!-- // RPK - WINGSBLOCK - 1 Don't move down page this script need here otherwise not working-->
 <script type="text/javascript">	<!-- // Select / Combobox - Block/Wings name Start-->
 var societyoptiontag ="<option value=''>Select Block/Wings Name</option>";
 function togetsocietyWingslist (pSocietyidd){ // RPK - WINGSBLOCK - 2
 	var temp="";
 	$.ajax({
  		type : 'post',
  		datatype : 'html',
  		url : 'getwingsblocklist',
  		data : 'ivrSocietyid=' + pSocietyidd,
  		async: false, // don't remove this line
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
 			//alert(societyoptiontag);
  			//$("#flatno_2").html(societyoptiontag);
  			//$('select').material_select();
  		}
  	});
 }
 var societyidd ="<s:property value="societyID"/>";
 togetsocietyWingslist(societyidd);// RPK - WINGSBLOCK - 2

 function togetwingsblckname(lvrSbxCntid_Redit, lvrExistwingname) {	 // RPK - WINGSBLOCK - 3
 		$("#"+lvrSbxCntid_Redit).html(societyoptiontag);
 		$("#"+lvrSbxCntid_Redit+">option").each(function(){
 	 	//	alert($(this).text().toLowerCase() + " == "+lvrExistwingname.toLowerCase());
 	        if ($(this).text().toLowerCase() == lvrExistwingname.toLowerCase()) {
 	            $(this).attr('selected', 'selected');
 	        };
 	    });
 }
 </script>
 <!-- // Select / Combobox - Block/Wings name End-->

 <div id="main">
    <div class="wrapper">
     <jsp:include page="../common/menuBar.jsp"></jsp:include>
      <section id="content">
        <div id="breadcrumbs-wrapper">
            <jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Text.member.modify"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="residentmgmt"><s:text name="Breadcrumb.manage" /></a></li>
					<li><a href="residentmgmt"><s:text name="Breadcrumb.manage.restmgmt" /></a></li>
                    <li class="active"><s:text name="Text.member.modify" /></li>
                   </ol>
              </div>
            </div>
          </div>
        </div>
<div class="container">
<div class="card-panel">
<form  id="residentEditSubmit" name="residentEditSubmit" action="residentEditSubmitaction" method="post" enctype="multipart/form-data" >
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">
<!--  <div id=""> <%-- <div class="imgaddplus" onclick="myFunction1('add');" style="display:none;"> <i class="<s:text name="button.color.addhideshow"/>" ></i>
<div class="spacialspace">Profile Detail : </div> </div>  onclick="myFunction1('sub');"--%>
<div class="imgaddminus" ><i class="<s:text name="button.color.minushideshow"/>" ></i>
<div class="spacialspace"><s:text name="Text.profile.detail" /> </div> </div>
</div><div style="clear: both; height:5px;"></div> -->
<div id="profilehidden" class="padding10px" style="display:block;">
	<s:if test="#session.sSoctyId==-1">
		<div class="row">
  														  <div class="input-field col s6">
																<label for="township_txt_id" class="active"><s:text name="Text.adduser.town.ship.id"></s:text><span class="mandatory">*</span></label>
																	<s:textfield   id="township_txt_id" name="restRegObj.townshipName" cssClass="form-control typeahead tt-query townshipIdvalidate" readonly="true" autocomplete="off" />
																<s:hidden name="townshipID" id="township_hidd_id"  class="form-control "  />
															</div>
  																 <div class="input-field col s6">
																<label for="societyId_txt_id" class="active"><s:text name="Text.adduser.societyname" /><span class="mandatory">*</span></label>
																<s:textfield  id="societyId_txt_id" name="restRegObj.societyName"  cssClass="typeahead tt-query form-control" readonly="true" autocomplete="off" />
																<input type="hidden"  name="societyID" id="societyId_hidd_id"  class="form-control "  />
															</div>
 		</div>
 	</s:if>
 										<div class="row">
 															<div class="input-field col s1">
												<label for="isdcodeid" class="active"><s:text name="Text.customerreg.isd" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control IsdNumber"  name="restRegObj.isdCode" id="isdcodeid" readonly="true"></s:textfield>
												<s:fielderror fieldName="restRegObj.userName"/>
											</div>
											<div class="input-field col s5">
												<label for="mobilenoid" class="active"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber " name="restRegObj.mobileNo" id="mobilenoid" readonly="true"></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
												<div class="input-field col s6">
														<label for="emailid"><s:text name="Text.customerreg.emailid" /></label>
														 <s:textfield  name="restRegObj.emailId" id="emailid" cssClass="form-control typeahead tt-query" autocomplete="off"/>
													</div>
										</div>
										<div class="row">
															<div class="input-field col s6">
																<label for="card"><s:text name="Text.customerreg.idproof" /></label>
																<s:textfield name="restRegObj.idCardTypeName" id="card"  cssClass="form-control typeahead tt-query idproofvalidate left" autocomplete="off"/>
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i id="card_arrow" class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
																<s:hidden name="restRegObj.idCardType" id="cardid" cssClass="form-control"/>
																	</div>
															<div class="input-field col s6">
															<label for="idproofno"><s:text name="Text.customerreg.idproofno" /></label>
															<s:textfield name="restRegObj.idProofNo" id="idproofno"  cssClass="form-control idproofnovalidate" />
															<div id="card-error-div" class="error manualscriptvalidation" style="display:none;"></div>
															</div>
										</div>
										<div class="row">
															<div class="input-field col s6">
															<label for="firstname"><s:text name="Text.customerreg.firstname"/></label>
															<s:textfield name="restRegObj.firstName" id="firstname" cssClass="form-control typeahead tt-query"/>
															</div>
															<div class="input-field col s6">
															<label for="lastname"><s:text name="Text.customerreg.lastname"/></label>
															<s:textfield name="restRegObj.lastName" id="lastname"  cssClass="form-control"/>
															 </div>
										</div>
										<div class="row">
													<!-- <div id="input-select">	 -->
												<div class="input-field col s6">
												<label for="gendertyp" class="active"><s:text name="Gender" /></label>
												<s:select id="gendertyp" cssClass="form-control lastnamevalidate" name="restRegObj.gender" value="%{restRegObj.gender}" list="#{'1':'Male','2':'Female','3':'Other'}" ></s:select>
											    </div>
												<div class="input-field col s6">
												<label for="dob"><s:text name="Text.resident.dob"/></label>
												<s:textfield  id="dob" cssClass="dateOfBirth" name="restRegObj.dob"></s:textfield>
												</div>
										</div>
										<div class="row">
												<div class="input-field ">
												<label for="acpid" class="active">Access Channel<span class="mandatory"></span></label>
												<div id="acpid">
											 	<s:checkboxlist list="#{'2':'Mobile','1':'Web','3':'Both'}" name="restRegObj.accessChannel" id="accesschannelcbxid" cssClass="myCheckbox" />
												</div></div>
										</div>
				</div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active"> <i class="mdi-communication-business tinysmall white-text text-accent-4"></i> <s:text name="Text.block.detail" /></div>
<div class="collapsible-body padding10px" id="seconddivid">
<!-- <div style="clear: both; height:20px;"></div> <div id="blockdetdiv" >
<div class="imgaddplus1" onclick="myFunction2('add');"><i class="<s:text name="button.color.addhideshow"/>" ></i>
<div class="spacialspace" style="font-weight: bold;">Block Detail </div></div>
<div class="imgaddminus1" onclick="myFunction2('sub');" style="display:none; cursor: pointer;"><i class="<s:text name="button.color.minushideshow"/>" ></i>
<div class="spacialspace">Block Detail </div></div>
</div><div style="clear: both; height:5px;"></div>  -->
<div id="blockhidden" class="padding10px" style="display:block;">
							<div class="row">
													     <div class="input-field col s6">
																		<label for="noofblocks"><s:text name="Text.resident.NoofBlock/Wings" /></label>
																		<s:textfield name="restRegObj.noOfBlocks" maxlength="3" id="noofblocks"  cssClass="form-control" />
																	</div>
  														 <div class="input-field col s5">
																<label for="noofflats"><s:text name="Text.resident.noofflats"></s:text></label>
																<s:textfield name="restRegObj.noofFlats" id="noofflats" cssClass="form-control" />
															</div>
  																   <div class="input-field col s1">
  																<button type="button" class="<s:text name="button.color.add"/>" id="addButton" ><i class="mdi-content-add"></i></button>
  																</div>
 							</div>
 						<s:iterator value="FlatList" status="flatcount">
							<div id="TextBoxDiv1<s:property value="#flatcount.Index+1"/>" >
							<div class="row cnt" >
								<div class="input-field col s6">
									<label for="noofblocks1<s:property value="#flatcount.Index+1"/>" class="active"><s:text name="Text.resident.block" /><span class="mandatory">*</span></label>
									<!-- // Text box - Block/Wings name -->
									<!--  <s:textfield name="block1"  id="noofblocks1%{#flatcount.Index+1}"  value="%{wingsname}" cssClass="form-control blocknamelist" /> -->
									<!-- // Select / Combobox - Block/Wings name -->
									<%-- <select name="block1" id="noofblocks1<s:property value="#flatcount.Index+1"/>" class="form-control blocknamelist"><option value=''>Select Block/Wings Name</option></select> --%>
									<select name="block1" id="noofblocks1<s:property value="#flatcount.Index+1"/>" class="form-control blocknamelist"><option value=''>Select Block/Wings Name</option></select>
									<div id="maintexcelfile-error_<s:property value="#flatcount.Index+1"/>" class="error manualscriptvalidation" style="display:none;">Select block/wings name is required</div>
									<script type="text/javascript">// RPK - WINGSBLOCK - 4
									var tempblcknamelst = "noofblocks1<s:property value="#flatcount.Index+1"/>";
									var existingwingsname = "<s:property value="wingsname"/>";
									togetwingsblckname(tempblcknamelst,existingwingsname);
									</script>
								</div>
  								<div class="input-field col s6">
									<label for="flatn11o_1<s:property value="#flatcount.Index+1"/>" ><s:text name="Text.resident.flatno"/><span class="mandatory">*</span></label>
									<s:textfield name="flatno_1" id="flatn11o_1%{#flatcount.Index+1}" value="%{flatno}"  cssClass="form-control flatnamelist" />
									<div id="maintexcelfile-error1_<s:property value="#flatcount.Index+1"/>" class="error manualscriptvalidation" style="display:none;">Flat number is required</div>
								</div>
						    </div>
						    </div>
						 </s:iterator>
						 <s:if test="(FlatList.size()<=0 || FlatList=='' || FlatList=='null')">
						 <div id="TextBoxDiv1_0" >
							<div class="row cnt" >
								<div class="input-field col s6">
									<label for="noofblocks1_0" class="active"><s:text name="Text.resident.block" /><span class="mandatory">*</span></label>
									<!-- // Text box - Block/Wings name -->
									<!--  <s:textfield name="block1"  id="noofblocks1%{#flatcount.Index+1}"  value="%{wingsname}" cssClass="form-control blocknamelist" /> -->
									<!-- // Select / Combobox - Block/Wings name -->
									<select name="block1" id="noofblocks1_0" class="form-control blocknamelist"><option value=''>Select Block/Wings Name</option></select>
									<div id="maintexcelfile-error_1" class="error manualscriptvalidation" style="display:none;">Select block/wings name is required</div>
									<script type="text/javascript">// RPK - WINGSBLOCK - 4
									var tempblcknamelst = "noofblocks1_0";
									var existingwingsname = "";
									togetwingsblckname(tempblcknamelst,existingwingsname);
									</script>
								</div>
  								<div class="input-field col s6">
									<label for="flatn11o_1_0" ><s:text name="Text.resident.flatno"/><span class="mandatory">*</span></label>
									<s:textfield name="flatno_1" id="flatn11o_1_0" value=""  cssClass="form-control flatnamelist" />
									<div id="maintexcelfile-error1_1" class="error manualscriptvalidation" style="display:none;">Flat number is required</div>
								</div>
						    </div>
						    </div>
						    <input type="hidden" id="cnt" value="2">
						 </s:if>
						 <s:else>
						  <input type="hidden" id="cnt" value="<s:property value="%{FlatList.size()}"/>">
						 </s:else>


							<div id='TextBoxesGroup'><div id="TextBoxDiv"></div></div>
	</div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>
<div class="collapsible-body padding10px" id="thirddivid" >
<!--<div style="clear: both; height:5px;"></div>
<div id="othersdetdiv" >
<div class="imgaddplus2" onclick="myFunction3('add');"><i class="<s:text name="button.color.addhideshow"/>" ></i>
<div class="spacialspace">Others Detail </div></div>
<div class="imgaddminus2" onclick="myFunction3('sub');" style="display:none; cursor: pointer;"><i class="<s:text name="button.color.minushideshow"/>" ></i>
<div class="spacialspace">Others Detail </div></div>
</div><div style="clear: both; height:5px;"></div> -->
<div id="otherhidden" class="padding10px" style="display:block;">
											<div class="row">
													     <div class="input-field col s6">
																		<label for="address1"><s:text name="Address1" /></label>
																		<s:textfield name="restRegObj.address1" id="address1" cssClass="form-control " />
																	</div>
  														 <div class="input-field col s6">
																<label for="address2"><s:text name="Address2"></s:text></label>
																<s:textfield name="restRegObj.address2" id="address2" cssClass="form-control " />
															</div>
															</div>
 															 <div class="row">
 															   <div class="input-field col s6">
												          <label for="country_txt_id"><s:text name="Menuheader.uam.profile.country" /></label>
														<s:textfield name="restRegObj.countryname" id="country_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off" />
															<!-- <input type="hidden" name="restRegObj.countryCode" id="country_hidd_id" class="form-control"/> -->
												          <span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="restRegObj.countryCode" id="country_hidd_id" class="form-control " />
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
												      </div>
  														   <div class="input-field col s6">
												          <label for="state_txt_id"><s:text name="Menuheader.uam.profile.state" /></label>
													       <s:textfield name="restRegObj.statename" id="state_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
															<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="restRegObj.stateId" id="state_hidd_id" class="form-control " />
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
 															 </div>
 															 <div class="row">
 															 <div class="input-field col s6">
												          <label for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /></label>
													       <s:textfield name="restRegObj.cityname" id="city_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
															<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="restRegObj.cityId" id="city_hidd_id" class="form-control " />
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
 															  <div class="input-field col s6">
												          <label for="pstlcode_txt_id"><s:text name="Text.customerreg.pincode" /></label>
													        <s:textfield name="restRegObj.pinCode" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
<%-- 															<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
<span class="input-group-addon txtbxdownarowicon left" ></span>
<%-- 												<s:hidden name="restRegObj.pinCode" id="pstlcode_hidd_id" class="form-control " /> --%>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>

																	</div>
																	<div class="row">
													    <div class="input-field col s6">
																		<label for="occupation"><s:text name="Text.resident.occupation" /></label>
																		<s:textfield name="restRegObj.occupation" id="occupation"
																			cssClass="form-control " />
																	</div>
																	 <div class="input-field col s6">
																		<label for="bloodgrp"><s:text name="Text.resident.bloodgrp" /></label>
																		<s:textfield name="restRegObj.bloodType" id="bloodgrp"
																			cssClass="form-control " />
																	</div>
 													</div>
	</div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> "> <i class="mdi-social-people tinysmall white-text text-accent-4"></i> Family Detail</div>
<div class="collapsible-body padding10px" id="fourthdivid">
<!-- <div id="blockdetdiv" >
<div class="imgaddplus3" onclick="familydetail('add');"><i class="<s:text name="button.color.addhideshow"/>" ></i>
<div class="spacialspace">Family Detail </div></div>
<div class="imgaddminus3" onclick="familydetail('sub');" style="display:none; cursor: pointer;"><i class="<s:text name="button.color.minushideshow"/>" ></i>
<div class="spacialspace">Family Detail </div></div>
</div><div style="clear: both; height:5px;"></div> -->
<div id="familydetail" class="padding10px" style="display:block;">
															<div class="row">
										<div class="input-field col s6">
												<label class="active"><s:text name="Text.resident.familymember"/></label>
												<s:textfield cssClass="form-control familymembers" name="restRegObj.membersInFamily"
												id="member"></s:textfield>
												<s:fielderror fieldName="usercreateObj.familymembers"/>
											</div>
											 	<div class="input-field col s1">
  												<button type="button" class="<s:text name="button.color.add"/>" id="addButton1"><i class="mdi-content-add"></i></button>
  											</div>
											</div>
										<s:iterator value="userFamilyList" status="famcount">

											<div id="TextBoxDiv1<s:property value="#famcount.Index+1"/>">
											<div class="card padding10px">
											<div class="row">
											 <div class="input-field col s6">
												<label class="control-label"><s:text name="Name"/></label>
												<input type="hidden" name="fmbrunid_hidd" id="fmbrunid_hidd" class="fmbruid" value="<s:property value="%{fmbruniqueid}"/>"/>
												<s:textfield name="famName1" value="%{fmbrName}" id="" cssClass="form-control famName" autocomplete="off" spellcheck="false"/>
												<s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											</div>
											 <div class="input-field col s1">
												<label class=" control-label"><s:text name="Text.customerreg.isd" /></label>
												<s:textfield cssClass="form-control famIsdNumber"  value="%{fmbrISD}" name="famisdno1"  onkeyup="textValidate(this,'4','PHNM')"
												id="isdcodeid"></s:textfield>

											</div>
												 <div class="input-field col s5">
												<label class=" control-label"><s:text name="Text.mobileno" /></label>
												<s:textfield cssClass="form-control famMobileNo " value="%{fmbrPhNo}" name="famMobileNo1"   id="mobilenoid" onkeyup="textValidate(this,'10','PHNM');"></s:textfield>
											</div>
											</div>
												<div class="row">
											 <div class="input-field col s6">
												<label class=" control-label"><s:text name="Text.emailid" /></label>
												<s:textfield cssClass="form-control famEmailId" value="%{fmbrEmail}" name="emailId"
												id="emailid%{#famcount.Index+1}" onkeyup="textValidate(this,'100','EML');" onblur="toValiEmail(this.id)"></s:textfield>
											</div>
											<div class="input-field col s3">
											<label class="active" for="membertype<s:property value="#famcount.Index+1"/>">Member Type</label>
											<div class="clear height15px"></div>
											<div id="membertype<s:property value="#famcount.Index+1"/>">
											<s:if test="fmbrMtype==1">
											<input type="checkbox" name="fmbrMtype" class="myCheckbox" id="Relation<s:property value="#famcount.Index+1" />" value="1" checked/> <label for="Relation<s:property value="#famcount.Index+1" />">Relation</label>
											<input type="checkbox" name="fmbrMtype" class="myCheckbox" id="Tenent<s:property value="#famcount.Index+1" />" value="2"/> <label for="Tenent<s:property value="#famcount.Index+1" />">Tenent</label>
											</s:if>
											<s:else>
											<input type="checkbox" name="fmbrMtype" class="myCheckbox" id="Relation<s:property value="#famcount.Index+1" />" value="1" /> <label for="Relation<s:property value="#famcount.Index+1" />">Relation</label>
											<input type="checkbox" name="fmbrMtype" class="myCheckbox" id="Tenent<s:property value="#famcount.Index+1" />" checked value="2"/> <label for="Tenent<s:property value="#famcount.Index+1" />">Tenent</label>
											</s:else>
											</div>

											</div>
											<div class="input-field col s3">
											<label for="profilaccss<s:property value="#famcount.Index+1" />" class="active">Profile Access <span class="mandatory"></span></label>
                                          <div class="clear height15px"></div>
                                           <div id="profilaccss<s:property value="#famcount.Index+1" />">
                                             <s:if test="fmbrPrfAccess==1">
											<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="Allow<s:property value="#famcount.Index+1" />" value="1" checked/>
											<label for="Allow<s:property value="#famcount.Index+1" />">Allow</label>
											<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="notAllow<s:property value="#famcount.Index+1" />" value="0"/>
											<label for="notAllow<s:property value="#famcount.Index+1" />">Not Allow</label>
											</s:if>
											<s:else>
											<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="Allow<s:property value="#famcount.Index+1" />" value="1" />
											<label for="Allow<s:property value="#famcount.Index+1" />">Allow</label>
											<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="notAllow<s:property value="#famcount.Index+1" />" value="0" checked/>
											<label for="notAllow<s:property value="#famcount.Index+1" />">Not Allow</label>
											</s:else>
											</div>
											</div>
											</div>
											<!-- <div class="col s12 l12"><div class="divider2px cyan darken-2"></div></div> -->
											<div class="clear height10px"></div>
											</div>
											</div>
											</s:iterator><div id='TextBoxesGroup1'> <div id="TextBoxDiv1"></div>	</div>
											  <input type="hidden" id="familycnt" value="<s:property value="%{userFamilyList.size()}"/>">
												<div id='TextBoxesGroup1' > <div id="TextBoxDiv1"></div>	</div>
</div>
</div>
</li>
</ul>
												<!-- <div style="clear: both; height:5px;"></div> <div style="clear: both;margin-left: 15px;"> -->
											  <input type="hidden" name="mySelect" id="Selectblack" class="form-control " />
											  <input type="hidden" name="myselect1" id="Selectflat" class="form-control " />
											  <s:hidden name="famName" id="famName" class="form-control " />
											  <s:hidden name="famMobileNo" id="famMobileNo" class="form-control " />
											  <s:hidden name="famEmailId" id="famEmailId" class="form-control " />
											  <s:hidden name="famisdcode" id="famisdcode" class="form-control " />
											  <s:hidden name="fmbrMtype_str" id="fammemtypeid" class="form-control " />
											  <s:hidden name="famprfaccess_str" id="famprfaccessid" class="form-control " />
											  <s:hidden name="deleteresidentid" id="deleteresidentidEdit"></s:hidden>
											  <s:hidden name="fmemberuniqueid" id="fmemberuniqueid_hidden" /> <!-- // family member unique id -->
					<div class="row">
						<div class="input-field col s6">
						<button type="button" id="userCreateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
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
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
</body>
<script>
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
$('input[type="checkbox"]').click(function() {
    $(this).siblings('input:checkbox').prop('checked', false);
    $(this).prop('checked', true);
});


$(document).ready(function(){
	
	$("#pstlcode_txt_id").keyup(function(){
		textValidate(this,'10','MNM');
	});
	
	$('#pstlcode_txt_id').blur(function(){
//     	alert($('#pstlcode_txt_id').val());
		$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
	});
	
	 $("#restRegObj\\.accessChannel-2").attr("disabled","disabled");
	 $("#restRegObj\\.accessChannel-3").attr("disabled","disabled");

	 var exigndr = $("#gendertyp").html();
	 $("#gendertyp").html('<option value="" disabled>Choose your gender</option>'  + exigndr);
	 $("#gendertyp").material_select();
	$("#imgdivid").click(function(){
		$("#staffImage").click();
	});
	 var member=$("#member").val();
	 if(member==0) {
		 $("#TextBoxDiv11").hide();
	 }
	 myFunction3("add");
	 onchangeStatecodeAgency();
	 onchangeCitycodeAgency();
// 	 onchangePstlcodeAgency();

	var counter = 1;
	 var itcnt = $("#cnt").val();
	  if(itcnt>=1){

	 	counter=itcnt;
	 	counter++;
	 }
	 var flag = true;
	 $("#addButton").click(function () {
		 var no_flats=$("#noofflats").val();
		 if(counter>no_flats){
			 swal("Only "+no_flats+" Flat allow");
	            flag =false;
		}else{
			 flag =true;
		}
		if(flag){
			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv' + counter);
			//newTextBoxDiv.after().html(' <div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label><s:text name="Text.resident.block" /></label><input type="text" name="blockname" id="noofblocks_'+counter+'" class="form-control blocknamelist" /></div></div><div class="input-field col s6" > <div class="form-group" ><label><s:text name="Text.resident.flatno"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'" maxlength="20" cssClass="form-control flatnamelist" /></div></div></div>'); // Text box don't delete
			newTextBoxDiv.after().html(' <div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'" class="active"><s:text name="Text.resident.block" /></label><select name="blockname" id="noofblocks_'+counter+'" class="form-control blocknamelist" >'+societyoptiontag+'</select></div></div><div class="input-field col s6" > <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.resident.flatno"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'" maxlength="20" cssClass="form-control flatnamelist" /></div></div></div>');// RPK - WINGSBLOCK  - 5
		    newTextBoxDiv.appendTo("#TextBoxesGroup");
			counter++;
			$('select').material_select();// RPK - WINGSBLOCK  - 6
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
if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login
		    $("#township_txt_id").keyup(function(){
				textValidate(this,'100','AI');
			});
			$("#societyId_txt_id").keyup(function(){
				textValidate(this,'100','AI');
			});
}
	 $("#mobilenoid").keyup(function(){
			textValidate(this,'15','PHNM');
		});
		$("#isdcodeid").keyup(function(){
			textValidate(this,'4','PHNM');
		});
		$("#emailid").keyup(function(){
			textValidate(this,'100','EML');
		});
		$("#noofblocks").keyup(function(){
			textValidate(this,'3','PHNM');
		});
		$("#noofflats").keyup(function(){
			textValidate(this,'3','PHNM');
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
		    $(".blocknamelist").keyup(function(){
		    	textValidate(this,'30','EML');
		    });
		    $(".flatnamelist").keyup(function(){
		    	textValidate(this,'30','EML');
		    });
		    $("#address1").keyup(function(){
		    	textValidate(this,'100','DOC');
		    });
		    $("#address2").keyup(function(){
		    	textValidate(this,'100','DOC');
		    });
		    $("#occupation").keyup(function(){
		    	textValidate(this,'100','DOC');
		    });
		    $("#member").keyup(function(){
		    	textValidate(this,'3','NM');

		    });
		    $("#bloodgrp").keyup(function(){
		    	textValidate(this,'15','NV');
		    });
		    $("#emailid").blur(function(){
			   	 toValiEmail(this.id);
			    });

		  });


var loadFile = function(event) {
	var output = document.getElementById('myImg');
	output.src = URL.createObjectURL(event.target.files[0]);
};
var counter1 = 1;

var flag1 = true;
$(document).ready(function(){
	var famcnt=$("#familycnt").val();
	if(famcnt>=1){
		counter1=famcnt;
		counter1++;
	}
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
		var targets8 =[];
		  /* $.each($(".blocknamelist"), function(){// Text box - Block/Wings name
		   targets.push($(this).val());
		   });*/
		   $("select.blocknamelist").each(function(){ // RPK - WINGSBLOCK  - 7
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
			   } */
			   targets2.push(name);
			   });
		   $.each($(".famMobileNo"), function(){
			   var mob=$(this).val();
			   /* if(mob==''){
				   mob="NaN";
			   } */
			   targets3.push(mob);
			   });
		   $.each($(".famEmailId"), function(){
			   var email=$(this).val();
			  /*  if(email==''){
				   email="NaN";
			   } */
			   targets4.push(email);
			   });
		   $.each($(".famIsdNumber"), function(){
			   var isd=$(this).val();

			   targets5.push(isd);
			   });
		   $("input[name='fmbrMtype']:checked").each(function(){
			   var memtyp=$(this).val();
			   var teee=memtyp.toString();
			   targets6.push(teee);
		   });
		   $("input[name='prfaccesschk']:checked").each(function(){
			   var prfaccess=$(this).val();
			   targets7.push(prfaccess);
		  });
		   $.each($(".fmbruid"), function(){// family member unique id
			   	var unquid=$(this).val();
			   	targets8.push(unquid);
			});
		   $("#Selectblack").val(targets.join("!_!"));
		   $("#Selectflat").val(targets1.join("!_!"));
		   $("#famName").val(targets2.join("!_!"));
		   $("#famMobileNo").val(targets3.join("!_!"));
		   $("#famEmailId").val(targets4.join("!_!"));
		   $("#famisdcode").val(targets5.join("!_!"));

		   var tttjon=targets6.join("!_!");
		   $("#fammemtypeid").val(tttjon);
		   $("#famprfaccessid").val(targets7.join("!_!"));
		   $("#fmemberuniqueid_hidden").val(targets8.join("!_!"));// family member unique id

		   $("#firstdivid").css("display","block");
			$("#seconddivid").css("display","block");
			$("#thirddivid").css("display","block");
			$("#fourthdivid").css("display","block");

			/* $("#residentEditSubmit").attr("action","residentEditSubmitaction");
			$("#residentEditSubmit").attr("method","Post");
			$("#residentEditSubmit").attr("enctype","multipart/form-data");
			$("#residentEditSubmit").submit(); */
			var blocksel=$("#Selectblack").val();
			var no_flats1=$("#Selectflat").val();
			var noffltflg = true;
			var nofblkflg = true;
			if (blocksel!="") {
				nofblkflg = true;
				$("#maintexcelfile-error_1").hide();
			} else {
				 $("#maintexcelfile-error_1").show();
				nofblkflg = false;
			}

			if (no_flats1!="") {
				 noffltflg = true;
				 $("#maintexcelfile-error1_1").hide();
			} else {
				$("#maintexcelfile-error1_1").show();
				 noffltflg = false;
			}
			if((nofblkflg || nofblkflg=="true") && (noffltflg || noffltflg=="true")){
				$("#residentEditSubmit").attr("action","residentEditSubmitaction");
				$("#residentEditSubmit").attr("method","Post");
				$("#residentEditSubmit").attr("enctype","multipart/form-data");
				$("#residentEditSubmit").submit();
			 }else{


			 }

	 });

	$("#addButton1").click(function () {
		 var member=$("#member").val();
		 if(counter1>member){
			 swal("Only "+member+" Family member allow");
	           flag1 =false;
		}else{
			 flag1 =true;
		}
		if(flag1){
			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv1' + counter1);
		 	newTextBoxDiv.after().html('<div class="card padding10px"><div class="row" id="appenddiv'+counter1+'"><div class="input-field col s6"><label for="noofblocks_'+counter1+'"><s:text name="Name" /></label><input type="text" name="blockname"  id="noofblocks_'+counter1+'" class="form-control famName" /></div><div class="input-field col s1"><label class="active" for="famisd_'+counter1+'"><s:text name="Text.customerreg.isd" /></label> <s:textfield  name="famisd_'+counter1+'" id="famisd_'+counter1+'" cssClass="form-control famIsdNumber" value="%{getText(\'Text.ISD.value\')}" onkeyup="textValidate(this,\"4\",\"PHNM\")"/></div><div class="input-field col s5" ><label  for="famMobileNo_'+counter1+'"><s:text name="Text.mobileno" /></label><s:textfield name="famMobileNo_'+counter1+'" id="famMobileNo_'+counter1+'" cssClass="form-control famMobileNo"/></div></div><div class="row" id="appenddiv1_'+counter1+'" ><div class="input-field col s6"><label for="emailid1_'+counter1+'"><s:text name="Text.emailid" /></label><s:textfield cssClass="form-control famEmailId" name="emailId" id="emailid1_'+counter1+'" onblur="toValiEmail(\"emailid1_'+counter1+'\");" /></div><div class="input-field col s3"><label class="active" for="membertype'+counter1+'">Member Type</label><div class="clear height15px"></div><div id="membertype'+counter1+'"><input type="checkbox" name="fmbrMtype" class="myCheckbox memtype" id="member'+counter1+'" value="1" checked/> <label for="member'+counter1+'">Relation</label>&nbsp;<input type="checkbox" name="fmbrMtype" class="myCheckbox memtype" id="tenent_'+counter1+'" value="2" />&nbsp;<label for="tenent_'+counter1+'">Tenent</label></div></div> <div class="input-field col s3"> <label class="active" for="profilaccss'+counter1+'">Profile Access </label><div class="clear height15px"></div><div id="profilaccss'+counter1+'"><input type="checkbox" name="prfaccesschk" id="allow_'+counter1+'" class="myCheckbox prfaccchk" value="1" /> <label for="allow_'+counter1+'">Allow</label>&nbsp;<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="notallow_'+counter1+'" value="2" checked/>&nbsp;<label for="notallow_'+counter1+'">Not Allow</label></div></div></div></div>');
		/*newTextBoxDiv.after().html('<hr style="border-bottom:dotted" /><div class="row" id="appenddiv'+counter1+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter1+'"><s:text name="Name" /></label><input type="text" name="blockname"   id="noofblocks_'+counter1+'" class="form-control famName" /></div></div><div class="input-field col s2"  > <div class="form-group"  ><label for="famisd_'+counter1+'"><s:text name="Text.customerreg.isd" /></label> <s:textfield  name="famisd_'+counter1+'" id="famisd_'+counter1+'"   cssClass="form-control famIsdNumber" /></div></div><div class="input-field col s4" > <div class="form-group" ><label for="famMobileNo_'+counter1+'" ><s:text name="Text.mobileno" /></label><s:textfield name="famMobileNo_'+counter1+'" id="famMobileNo_'+counter1+'"   cssClass="form-control famMobileNo" /></div></div></div><div class="row" id="appenddiv1_'+counter1+'" ><div class="input-field  col s6"><div class="form-group" ><label for="emailid1_'+counter1+'"" class=" control-label"><s:text name="Text.emailid" /></label><s:textfield cssClass="form-control famEmailId" name="emailId" id="emailid1_'+counter1+'" /></div> </div>	 <div class="input-field col s3"  style="margin-top:25px"> <label class="active" for="member'+counter1+'"">Member Type</label><div><input type="checkbox" name="fmbrMtype" id="member'+counter1+'" class="myCheckbox memtype" value="1" checked/> <label for="option">Relation</label>&nbsp;<input type="checkbox" name="fmbrMtype" id="tenent_'+counter1+'" class="myCheckbox memtype" value="2" />&nbsp;<label for="tenent_'+counter1+'">Tenent</label></div></div>    <div class="input-field col s3"  style="margin-top:25px"> <label class="active" for="allow_'+counter1+'">Profile Access</label><div><input type="checkbox" name="prfaccesschk" id="allow_'+counter1+'" class="myCheckbox memtype" value="1" checked/> <label for="notallow_'+counter1+'">Allow</label>&nbsp;<input type="checkbox" name="prfaccesschk" id="notallow_'+counter1+'" class="myCheckbox memtype" value="0" />&nbsp;<label for="tenent_'+counter1+'">Not Allow</label></div></div></div>');*/
		newTextBoxDiv.appendTo("#TextBoxesGroup1");
			counter1++;
		}
		$('input[type="checkbox"]').click(function() {
		    $(this).siblings('input:checkbox').prop('checked', false);
		    $(this).prop('checked', true);
		});

	});
	$("#member").blur(function(){
		 var member = $("#member").val();
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
});
$(document).ready(function(){
    $('input[type="checkbox"]').click(function(){
        if($(this).prop("checked") == true){
            $(this).siblings('input:checkbox').prop('checked', false);
        }
        else if($(this).prop("checked") == false){
        }
        });
});
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
});

	function idcardlistfun(ar) {
		var objects_cardtype;
		ar=ar.replace(/&quot;/ig,'"');
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
	 $('#card').click(function(){
			if ($('#card').val() != "undefined"
				&& $('#card').val() != '') {
				 $('#card').val('');
	        	  $('#card_arrow').click();
		          $('#cardid').val('');
	        }
	      });
		}


	function CountrylistFun(ar){
		cntload = true;
		var objects_country;
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
	    },
	    updater: function(item) {
	    	$('#country_hidd_id').val(map[item].id);
	        $('#state_txt_id').val('');
	          $('#state_hidd_id').val('');
	          $('#state_txt_id').typeahead('destroy');
	          $('#city_txt_id').val('');
	          $('#city_hidd_id').val('');
	          $('#city_txt_id').typeahead('destroy');
// 	          $('#pstlcode_txt_id').val('');
//       	      $('#pstlcode_txt_id').typeahead('destroy');
// 	          $('#pstlcode_hidd_id').val('');
	        onchangeStatecodeAgency();
	        return item;
	    }
	});

		$('#country_txt_id').click(
				function() {
					
					if ($('#country_txt_id').val() != "undefined"
						&& $('#country_txt_id').val() != '') {
						
						cntload=false;
						myFunction3('add');
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
						
					}
					
				});
	}
function onchangeStatecodeAgency(){
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
function statelistload(ar){
	var objects_state;
	ar=ar.replace(/&quot;/ig,'"');
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
//          $('#pstlcode_txt_id').val('');
//  	  	  $('#pstlcode_txt_id').typeahead('destroy');
//          $('#pstlcode_hidd_id').val('');
        onchangeCitycodeAgency();
        return item;
    }
});

	$('#state_txt_id').click(
			function() {
				if ($('#state_hidd_id').val() != "undefined"
						&& $('#state_hidd_id').val() != '') {
					onchangeStatecodeAgency();
				}
				$('#state_txt_id').val('');
				$('#state_txt_id').focus();
				$('#state_hidd_id').val('');
				$('#city_txt_id').val('');
				$('#city_hidd_id').val('');
				$('#city_txt_id').typeahead('destroy');
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
	function citylistload(arr){
	 var objects_city;
		arr=arr.replace(/&quot;/ig,'"');
		var loc_state_val = JSON.parse(arr);
		$('#city_txt_id').typeahead({
	     	order: "asc",
			hint: true,
			accent: true,
			offset: true,
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
			searchOnFocus: true,
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
// 	          $('#pstlcode_txt_id').val('');
// 	          $('#pstlcode_txt_id').typeahead('destroy');
// 	          $('#pstlcode_hidd_id').val('');
// 	        onchangePstlcodeAgency();
	        return item;
	    }
	});

		$('#city_txt_id').click(
				function() {
					
					if ($('#city_hidd_id').val() != "undefined"
						&& $('#city_hidd_id').val() != '') {
						onchangeCitycodeAgency();
					}
					$('#city_txt_id').val('');
					$('#city_txt_id').focus();
					$('#city_hidd_id').val('');
				});
	}
	function onchangePstlcodeAgency(){
		var cityval = $("#city_hidd_id").val();
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
	function pstlcodelistload(arr){
	var objects_pstl;
		arr=arr.replace(/&quot;/ig,'"');
		var loc_pstl_val = JSON.parse(arr);
		$('#pstlcode_txt_id').typeahead({
	     	order: "asc",
			hint: true,
			accent: true,
			offset: true,
			searchOnFocus: true,
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
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
	        	  $('#pstlcode_txt_id').val('');
	        	  $('#pstlcode_txt_id').focus();
		          $('#pstlcode_hidd_id').val('');
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
var cntload = false;
function myFunction3(check){
	 if(check=="add" && cntload == false){
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
		// $('.imgaddminus2').css("display","inline");
		// $('#otherhidden').show();
		// $('.imgaddplus2').css("display","none");
	 }else if(check=="sub"){
		// $('.imgaddminus2').css("display","none");
		// $('.imgaddplus2').css("display","inline");
		// $('#otherhidden').hide();
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
 function cancelFunction(){
	$("#userCancelForm").attr("action", "residentmgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}

</script>
</html>
