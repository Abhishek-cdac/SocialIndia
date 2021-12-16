<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><s:text name='Text.project.title' /></title>
<meta charset="utf-8">
<meta http-equiv="Content-Type"
	content="<s:text name='meta.contentType' />">
<meta name="viewport" content="<s:text name='meta.viewport' />">
<meta http-equiv="X-UA-Compatible"
	content="<s:text name='meta.X-UA-Compatible' />">
<meta name="msapplication-tap-highlight"
	content="<s:text name='meta.msapplication-tap-highlight' />">
<meta name="description" content="<s:text name='meta.description' />">
<meta name="keywords" content="<s:text name='meta.keywords' />">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link
	href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link
	href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
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
									<s:text name="Society Modify" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text
												name="Breadcrumb.dashboard" /></a></li>
									<li><a href="societymgmt"><s:text
												name="Breadcrumb.manage" /></a></li>
									<li class="active"><a href="societymgmt"><s:text
												name="Breadcrumb.manage.societymgmt" /></a></li>
									<li class="active"><s:text name="Society Modify" /></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<div class="card-panel">
						<form id="societyupdateformid" name="societyupdateformid"
							action="societyUpdateAction" method="post"
							enctype="multipart/form-data">
							<ul class="collapsible collapsible-accordion"
								data-collapsible="expandable">
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" /> active">
										<i
											class="mdi-social-location-city tinysmall white-text text-accent-4"></i>
										Society Detail
									</div>
									<div class="collapsible-body" id="firstdivid">
										<!-- 		<div id="">	 <div class="imgaddplus" onclick="myFunction1('add');" style="display:none;"> <i class="mdi-content-add-circle tinysmall spacialcolor"></i>
					 <div class="spacialspace" onclick="myFunction1('sub');">Society Details : </div> 
                         </div> <div class="imgaddminus" ><i class="mdi-content-remove-circle tinysmall spacialcolor" ></i>
                            <div class="spacialspace">Society Details</div>
							</div>
							<div style="clear: both; height:5px;"></div> -->
										<div id="profilehidden" style="display: block; margin: 15px;">
											<div class="row">
												<div class="input-field col s6">
													<label for="township_txt_id"><s:text
															name="Township Name" /><span class="mandatory">*</span></label>
													<s:textfield id="township_txt_id"
														name="societyMst.townshipName"
														cssClass="form-control  townshipIdvalidate"
														readonly="true" autocomplete="off" />
													<s:hidden value="%{townshipId}" name="townshipId"
														id="township_hidd_id" />
													<s:fielderror fieldName="townshipId" />
												</div>
												<div class="input-field col s2">
													<label for="Isdcode"><s:text
															name="Text.customerreg.isd" /><span class="mandatory">*</span></label>
													<s:textfield cssClass="form-control Isdcode" name="isdCode"
														id="Isdcode" readonly="true"></s:textfield>
													<s:fielderror fieldName="usercreateObj.isdCode" />
												</div>
												<div class="input-field col s4">
													<label for="mobileNo"><s:text
															name="Mobile / Landline / Contact" /><span
														class="mandatory">*</span></label>
													<s:textfield cssClass="form-control mobilevalidate"
														name="societyMst.ivrSmobnum" id="mobileNo" readonly="true"></s:textfield>
													<s:fielderror fieldName="usercreateObj.mobileNo" />
												</div>

											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="society">Society Name<span
														class="mandatory">*</span></label>
													<s:textfield cssClass="form-control societyname"
														name="societyMst.societyName"
														value="%{societyMst.societyName}" id="society"></s:textfield>
													<s:fielderror fieldName="societyObj.societyName" />
												</div>
												<div class="input-field col s6">
													<label for="registerNo">Registration No.<span
														class="mandatory">*</span></label>
													<s:textfield cssClass="form-control registerNo"
														name="societyMst.registerNo" id="registerNo"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="noofmem">No. of Members<span
														class="mandatory">*</span></label>
													<s:textfield cssClass="form-control noofmembers"
														name="societyMst.noOfMembers"
														value="%{societyMst.noOfMembers}" id="noofmem"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>

												<div class="input-field col s6">
													<label for="emailId"><s:text
															name="Text.adduser.emailid" /></label>
													<s:textfield cssClass="form-control" name="emailId"
														value="%{emailId}" id="emailId"></s:textfield>
													<s:fielderror fieldName="usercreateObj.emailId" />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label class="active" for="noOfBlocksWings">No. of
														Blocks/Wings<span class="mandatory">*</span>
													</label>
													<s:textfield cssClass="form-control noOfBlocksWings"
														name="societyMst.noOfBlocksWings" id="noOfBlocksWings"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
												<div class="input-field col s2">
													<label class="control-label"><button type="button"
															class="btn-floating btn-medium waves-effect waves-light teal darken-3 animated infinite zoomIn"
															id="addButton" name="submitbtn">
															<i class="mdi-content-add"></i>
														</button> </label>
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<s:iterator value="societyWingList" status="count">
														<div id="TextBoxDiv<s:property value="#count.Index+1"/>">
															<div class="form-group" id="usernamedivid">
																<label for="wingsName">Block/Wings Name </label>
																<s:textfield cssClass="form-control newwingsname"
																	name="wingsName" value="%{wingsName}" id="wingsName"></s:textfield>
																<s:fielderror fieldName="usercreateObj.userName" />
															</div>
														</div>
													</s:iterator>
													<input type="hidden" id="cnt"
														value="<s:property value="%{societyWingList.size()}"/>">
												</div>
												<%-- <label for="wingsName">Wings/Block Name</label>
													<s:textfield cssClass="form-control flatname" name="wingsName" value="%{wingsName}" id="wingsName"></s:textfield> --%>
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
										Other Detail
									</div>
									<div class="collapsible-body" id="seconddivid">
										<!--  <div id=""> <div style="clear: both; height:5px;"></div>
										                    <div class="imgaddplus1" onclick="myFunction2('add');"><i class="mdi-content-add-circle tinysmall spacialcolor"></i>
                                                             <div class="spacialspace">Other Details </div></div>
										                    <div class="imgaddminus1" onclick="myFunction2('sub');" style="display:none; cursor: pointer;"><i class="mdi-content-remove-circle tinysmall spacialcolor" ></i>
                                                              <div class="spacialspace">Other Details </div></div>										                  
										                  </div><div style="clear: both; height:10px;"></div>-->
										<div id="otherhidden" style="display: block; margin: 15px;">

											<div class="row">
												<div class="input-field col s6">
													<label for="colourCode">Color Code (Hex)</label>
													<s:textfield cssClass="form-control colourCode"
														name="societyMst.colourCode" id="colourCode"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
												<div class="input-field col s6">
													<label for="imprintName">Imprint Name</label>
													<s:textfield cssClass="form-control"
														name="societyMst.imprintName" id="imprintName"></s:textfield>
													<s:fielderror fieldName="usercreateObj.emailId" />
												</div>
											</div>

											<div class="row">
												<div class="input-field col s6">
													<label class="active" for="logoImage">Upload Logo</label>
													<div class="clear height10px"></div>
													<div class="">
														<input type="file" id="logoImage" name="logoImage"
															class="dropify"
															data-default-file="/templogo/society/web/<s:property value="societyMst.societyId"/>/<s:property value="societyMst.logoImage"/>">
													</div>
												</div>
												<div class="input-field col s6">
													<label class="active" for="icoImage">Upload ICO
														Image</label>
													<div class="clear height10px"></div>
													<input type="file" id="icoImage" name="icoImage"
														class="dropify"
														data-default-file="/templogo/society/web/<s:property value="societyMst.societyId"/>/<s:property value="societyMst.icoImage"/>">
												</div>
											</div>
											<div class="row">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m7">
															<b>Address Information : <a target="_black"
																href="https://www.google.co.in/maps/place/<s:property value="societyMst.cityName"/>"
																style="margin-top: 5px;" class="tooltipped"
																data-toggle="modal" data-target="#addnewaccount"
																data-position="bottom"
																data-delay="<s:text name="material.tooltip.delay"/>"
																data-tooltip="Location"><i
																	class="mdi-communication-location-on tinysmall green-text text-darken-1"></i></a></b>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Address</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.address" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">
															<s:text name="Country"></s:text>
														</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.countryName" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">
															<s:text name="State"></s:text>
														</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.stateName" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">
															<s:text name="City"></s:text>
														</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.cityName" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">
															<s:text name="Pin Code"></s:text>
														</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.pinCode" />
															</span>
														</div>
													</div>
													<div style="clear: both; height: 10px;"></div>
												</div>
											</div>
											<div id="towndatadiv"></div>
										</div>
									</div>
								</li>
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" />">
										<i
											class="mdi-action-account-balance tinysmall white-text text-accent-4"></i>
										Account Detail
									</div>
									<div class="collapsible-body" id="thirddivid">
										<!--  <div id="">
										  <div class="imgaddplus2" onclick="myFunction3('add');">  <i class="mdi-content-add-circle tinysmall spacialcolor"></i>
										  <div class="spacialspace">Account Details </div>
                                               </div>
										    <div class="imgaddminus2" onclick="myFunction3('sub');"  style="display:none" ><i class="mdi-content-remove-circle tinysmall spacialcolor" ></i>
                                             <div class="spacialspace">Account Details </div>    
										    </div>
										     
										    </div><div style="clear: both; height:10px;"></div> -->
										<div id="accounthidden" style="display: block; margin: 15px;">
											<div class="row">
												<div class="input-field col s6">
													<label for="bankAccNo">Account No.</label>
													<s:textfield cssClass="form-control registerNumber"
														name="societyAcc.bankAccNo" id="bankAccNo"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
												<div class="input-field col s6">
													<label for="bankAccName">Account Name</label>
													<s:textfield cssClass="form-control registerNumber"
														name="societyAcc.bankAccName" id="bankAccName"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="bankName">Bank Name</label>
													<s:textfield cssClass="form-control registerNumber"
														name="societyAcc.bankName" id="bankName"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
												<div class="input-field col s6">
													<label for="ifscCode">IFSC Code</label>
													<s:textfield cssClass="form-control registerNumber"
														name="societyAcc.ifscCode" id="ifscCode"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
											</div>
											<div class="row">
												<div class="input-field col s6">
													<label for="GSTIN">GSTIN</label>
													<s:textfield cssClass="form-control registerNumber"
														name="societyMst.gstin" id="GSTIN"></s:textfield>
													<s:fielderror fieldName="usercreateObj.GSTIN" />
												</div>
												<div class="input-field col s6">
													<label for="HSN">SAC/HSN</label>
													<s:textfield cssClass="form-control registerNumber"
														name="societyMst.hsn" id="HSN"></s:textfield>
													<s:fielderror fieldName="usercreateObj.userName" />
												</div>
											</div>
										</div>
									</div>
								</li>

								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" />">
										<i
											class="mdi-action-account-balance tinysmall white-text text-accent-4"></i>
										Maintenace Bill Template Detail
									</div>
									<div class="collapsible-body" id="fourthdivid">
										<div id="accounthidden" style="display: block; margin: 15px;">
											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>
													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">
														<tr>
															<td width="20%" align="center"><input
																type="checkbox" name="Monthly_QTLY_maintenances"
																class="myCheckbox prfaccchk"
																id="Monthly_QTLY_maintenances" value="true" checked />
																<label for="Monthly_QTLY_maintenances">Monthly /
																	QTLY maintenances</label></td>
															<td width="20% align="center"><input type="checkbox"
																name="Municipal_tax" class="myCheckbox prfaccchk"
																id="Municipal_tax" value="true" checked /> <label
																for="Municipal_tax">Municipal tax</label></td>
															<td width="20% align="center"><input type="checkbox"
																name="Water_charge" class="myCheckbox prfaccchk"
																id="Water_charge" value="true" checked /> <label
																for="Water_charge">Water Charge</label></td>
															<td width="20% align="center"><input type="checkbox"
																name="Federation_charges" class="myCheckbox prfaccchk"
																id="Federation_charges" value="true" checked /> <label
																for="Federation_charges">Federation charges</label></td>
															<td width="20% align="center"><input type="checkbox"
																name="Repair_fund" class="myCheckbox prfaccchk"
																id="Repair_fund" value="true" checked /> <label
																for="Repair_fund">Repair Fund</label></td>
														</tr>
													</table>
												</div>
											</div>
											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>
													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">
														<tr>
															<td width="20%" align="center"><input
																type="checkbox" name="Sinking_fund"
																class="myCheckbox prfaccchk" id="Sinking_fund"
																value="true" checked /> <label for="Sinking_fund">Sinking
																	Fund</label></td>
															<td width="20%" align="center"><input
																type="checkbox" name="Major_repair_funds"
																class="myCheckbox prfaccchk" id="Major_repair_funds"
																value="true" checked /> <label for="Major_repair_funds">Major
																	Repair Funds </label></td>
															<td width="20%" align="center"><input
																type="checkbox" name="Non_Occupancy_charges"
																class="myCheckbox prfaccchk" id="Non_Occupancy_charges"
																value="true" checked /> <label
																for="Non_Occupancy_charges">Non Occupancy
																	scharges </label></td>
															<td width="20%" align="center"><input
																type="checkbox" name="Play_zone_fees"
																class="myCheckbox prfaccchk" id="Play_zone_fees"
																value="true" checked /> <label for="Play_zone_fees">Play
																	Zone Fees</label></td>
															<td width="20%" align="center"><input
																type="checkbox" name="Penalties"
																class="myCheckbox prfaccchk" id="Penalties" value="true"
																checked /> <label for="Penalties">Penalties</label></td>
														</tr>
													</table>
												</div>
											</div>


											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>
													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">
														<tr>
															<td width="20%"><input type="checkbox"
																name="Interest" class="myCheckbox prfaccchk"
																id="Interest" value="true" checked /> <label
																for="Interest">Interest</label></td>
															<td width="20%"><input type="checkbox"
																name="Late_fees" class="myCheckbox prfaccchk"
																id="Late_fees" value="true" checked /> <label
																for="Late_fees">Late fees</label></td>
															<td width="20%"><input type="checkbox"
																name="Insurance_cost" class="myCheckbox prfaccchk"
																id="Insurance_cost" value="true" checked /> <label
																for="Insurance_cost">Insurance Cost</label></td>
															<td width="20%"><input type="checkbox"
																name="Car_parking_1" class="myCheckbox prfaccchk"
																id="Car_parking_1" value="true" checked /> <label
																for="Car_parking_1">Car Parking 1</label></td>
															<td width="20%"><input type="checkbox"
																name="Car_parking_2" class="myCheckbox prfaccchk"
																id="Car_parking_2" value="true" checked /> <label
																for="Car_parking_2">Car Parking 2</label></td>
														</tr>
													</table>

												</div>
											</div>



											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>
													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">
														<tr>
															<td width="20%"><input type="checkbox"
																name="Two_wheeler_1" class="myCheckbox prfaccchk"
																id="Two_wheeler_1" value="true" checked /> <label
																for="Two_wheeler_1">Two Wheeler 1</label></td>
															<td width="20%"><input type="checkbox"
																name="Two_wheeler_2" class="myCheckbox prfaccchk"
																id="Two_wheeler_2" value="true" checked /> <label
																for="Two_wheeler_2">Two Wheeler 2</label></td>
															<td width="20%"><input type="checkbox"
																name="Sundry_adjustment" class="myCheckbox prfaccchk"
																id="Sundry_adjustment" value="true" checked /> <label
																for="Sundry_adjustment">Sundry Adjustment</label></td>
															<td width="20%"><input type="checkbox"
																name="Property_tax" class="myCheckbox prfaccchk"
																id="Property_tax" value="true" checked /> <label
																for="Property_tax">Property Tax</label></td>
															<td width="20%"><input type="checkbox"
																name="Excess_Payments" class="myCheckbox prfaccchk"
																id="Excess_Payments" value="true" checked /> <label
																for="Excess_Payments">Excess Payments</label></td>
														</tr>
													</table>


												</div>
											</div>


											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>
													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">
														<tr>
															<td width="20%"><input type="checkbox"
																name="Club_House" class="myCheckbox prfaccchk"
																id="Club_House" value="true" checked /> <label
																for="Club_House">Club House</label></td>
															<td width="20%"><input type="checkbox"
																name="Arrears" class="myCheckbox prfaccchk" id="Arrears"
																value="true" checked /> <label for="Arrears">Arrears</label>
															</td>
															<td width="20%"><input type="checkbox"
																name="Previous_Dues" class="myCheckbox prfaccchk"
																id="Previous_Dues" value="true" checked /> <label
																for="Previous_Dues">Previous Dues</label></td>
															<td width="20%"><input type="checkbox"
																name="APP_Subscription_Fee" class="myCheckbox prfaccchk"
																id="APP_Subscription_Fee" value="true" checked /> <label
																for="APP_Subscription_Fee">APP Subscription Fee</label>
															</td>
															<td width="20%"><input type="checkbox"
																name="Total_Payable" class="myCheckbox prfaccchk"
																id="Total_Payable" value="true" checked /> <label
																for="Total_Payable">Total Payable</label></td>
														</tr>
													</table>


												</div>
											</div>


											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>
													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">
														<tr>
															<td width="20%"><input type="checkbox"
																name="Amount_in_Words" class="myCheckbox prfaccchk"
																id="Amount_in_Words" value="true" checked disabled /> <label
																for="Amount_in_Words">Amount In Words</label></td>
															<td width="20%"><input type="checkbox"
																name="Bill_No" class="myCheckbox prfaccchk" id="Bill_No"
																value="true" checked disabled /> <label for="Bill_No">Bill
																	No</label></td>
															<td width="20%"><input type="checkbox"
																name="Flat_No" class="myCheckbox prfaccchk" id="Flat_No"
																value="true" checked disabled /> <label for="Flat_No">Flat
																	No</label></td>
															<td width="20%"><input type="checkbox"
																name="Flat_Area" class="myCheckbox prfaccchk"
																id="Flat_Area" value="true" checked disabled /> <label
																for="Flat_Area">Flat Area</label></td>
															<td width="20%"><input type="checkbox" name="Notes"
																class="myCheckbox prfaccchk" id="Notes" value="true"
																checked disabled /> <label for="Notes">Notes</label></td>
														</tr>
													</table>
												</div>
											</div>


											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>

													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">

														<tr>
															<td width="20%"><input type="checkbox" name="RSRVD1"
																class="myCheckbox prfaccchk" id="RSRVD1" value="true"
																checked /> <label for="RSRVD1">RSRVD1</label></td>
															<td width="20%"><input type="checkbox" name="RSRVD2"
																class="myCheckbox prfaccchk" id="RSRVD2" value="true"
																checked /> <label for="RSRVD2">RSRVD2</label></td>
															<td width="20%"><input type="checkbox" name="RSRVD3"
																class="myCheckbox prfaccchk" id="RSRVD3" value="true"
																checked /> <label for="RSRVD3">RSRVD3</label></td>
															<td width="20%"><input type="checkbox" name="RSRVD4"
																class="myCheckbox prfaccchk" id="RSRVD4" value="true"
																checked /> <label for="RSRVD4">RSRVD4</label></td>
															<td width="20%"><input type="checkbox" name="RSRVD5"
																class="myCheckbox prfaccchk" id="RSRVD5" value="true"
																checked /> <label for="RSRVD5">RSRVD5</label></td>
														</tr>
													</table>
												</div>
											</div>


											<div class="row">
												<div class="input-field col s12">
													<div class="clear height15px"></div>


													<table cellpadding="0" cellspacing="0" width="100%"
														border="1" height="20px">
														<tr>
															<td width="20%"><input type="checkbox" name="RSRVD6"
																class="myCheckbox prfaccchk" id="RSRVD6" value="true"
																checked /> <label for="RSRVD6">RSRVD6</label></td>
															<td width="20%"><input type="checkbox" name="RSRVD7"
																class="myCheckbox prfaccchk" id="RSRVD7" value="true"
																checked /> <label for="RSRVD7">RSRVD7</label></td>
															<td width="20%"><input type="checkbox" name="RSRVD8"
																class="myCheckbox prfaccchk" id="RSRVD8" value="true"
																checked /> <label for="RSRVD8">RSRVD8</label></td>
															<td width="20%"><input type="checkbox" name="RSRVD9"
																class="myCheckbox prfaccchk" id="RSRVD9" value="true"
																checked /> <label for="RSRVD9">RSRVD9</label></td>
															<td width="20%"><input type="checkbox"
																name="RSRVD10" class="myCheckbox prfaccchk" id="RSRVD10"
																value="true" checked /> <label for="RSRVD10">RSRVD10</label>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</div>
									</div>
								</li>


							</ul>
							<s:hidden value="%{societyMst.societyId}"
								name="societyMst.societyId"></s:hidden>
							<%-- <s:hidden name="flatnames" id="Selectflat"></s:hidden> --%>
							<s:hidden name="flatnames" id="newnewwingsname"></s:hidden>
							<div class="row">
								<div class="input-field col s6">
									<button type="button" id="userCreateButtonId"
										class="<s:text name="button.color.submit"/>">
										<s:text name="Text.button.update" />
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
				</div>
			</section>
		</div>
		<s:form method="post" id="userCancelForm"></s:form>

	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>

	<!-- dropify -->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>

</body>
<script type="text/javascript">
	var counter = 1;
	var itcnt = $("#cnt").val();
	if (itcnt >= 1) {

		counter = itcnt;
		counter++;
	}

	var flag = true;
	$("#addButton")
			.click(
					function() {
						var no_flats = $("#noOfBlocksWings").val();
						if (counter > no_flats) {
							swal("Only " + no_flats + " Wings Allow.");
							flag = false;
						} else {
							flag = true;
						}
						if (flag) {
							var newTextBoxDiv = $(document.createElement('div'))
									.attr("id", 'TextBoxDiv' + counter);
							newTextBoxDiv
									.after()
									.html(
											' <div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><label for="flatname'+counter+'"><s:text name="Text.society.block" /></label><s:textfield name="restRegObj" id="flatname'+counter+'" cssClass="form-control newwingsname"  /></div></div><div class="input-field col s6"></div>');
							newTextBoxDiv.appendTo("#TextBoxesGroup");
							counter++;
						}
					});

	$("#noOfBlocksWings").blur(function() {
		var no_flats = $("#noOfBlocksWings").val();
		if (no_flats == 0) {
			if (counter > no_flats) {
				var cc = (counter - 1);
				$("#noOfBlocksWings").val(cc);
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

	$(document)
			.ready(
					function() {
						// Basic
						$('.dropify')
								.dropify(
										{
											messages : {
												"default" : "Drag and drop a image here or click",
												replace : "Drag and drop or image to replace",
												remove : "Remove",
												error : "Sorry, this file is too large"
											}
										});

						// Translated
						$('.dropify-fr')
								.dropify(
										{
											messages : {
												// default: 'Glissez-d�posez un fichier ici ou cliquez',
												replace : 'Glissez-d�posez un fichier ou cliquez pour remplacer',
												remove : 'Supprimer',
												error : 'D�sol�, le fichier trop volumineux'
											}
										});

						// Used events
						var drEvent = $('.dropify-event').dropify();

						drEvent.on('dropify.beforeClear', function(event,
								element) {
							return confirm("Do you really want to delete \""
									+ element.filename + "\" ?");
						});

						drEvent.on('dropify.afterClear', function(event,
								element) {
							alert('File deleted');
						});
						/*$.ajax({
							type : 'post',
							datatype : 'html',
							url : 'gettownshipOnload',
							success : function(data) {
								var spli = data.split("!_!");
								townshipdata(spli[1]);				
							}
						});*/

						$('#societyupdateformid').validate(
								{
									errorElement : 'div',
									errorPlacement : function(error, element) {

										var placement = $(element)
												.data('error');
										if (placement) {
											$(placement).append(error);
										} else {
											error.insertAfter(element);
										}
									},
									submitHandler : function(form) {
										var targets = [];
										$.each($(".newwingsname"), function() {
											targets.push($(this).val());
											$("#newnewwingsname").val(
													targets.join("!_!"));
										});
										toShowLoadingImgoverlay();
										return true;
									}
								});
						$("#township_txt_id")
								.rules(
										"add",
										{
											required : true,
											maxlength : 50,
											messages : {
												required : "<s:text name="Error.customerreg.townshipId" />",
											}

										});

						$("#society")
								.rules(
										"add",
										{
											required : true,
											maxlength : 50,
											messages : {
												required : "<s:text name="Error.society.create.empty" />",
											}
										});

						$("#Isdcode")
								.rules(
										"add",
										{
											required : true,
											minlength : 1,
											maxlength : 4,
											messages : {
												required : "<s:text name="Error.customerreg.isd" />",
											}
										});
						$("#mobileNo")
								.rules(
										"add",
										{
											required : true,
											number : true,
											minlength : 8,
											maxlength : 15,
											messages : {
												required : "<s:text name="Error.customerreg.mobileno" />",
											}
										});
						$("#noofmem")
								.rules(
										"add",
										{
											required : true,
											number : true,
											minlength : 1,
											maxlength : 5,
											messages : {
												required : "<s:text name="Error.society.noof.member" />",
											}
										});

						$("#registerNo")
								.rules(
										"add",
										{
											required : true,
											minlength : 2,
											maxlength : 20,
											messages : {
												required : "<s:text name="Error.society.noof.register.no" />",
											}
										});
						$("#noOfBlocksWings")
								.rules(
										"add",
										{
											required : true,
											number : true,
											minlength : 1,
											maxlength : 4,
											messages : {
												required : "<s:text name="Error.society.noof.blocks.wings" />",
											}
										});
						$("#wingsName")
								.rules(
										"add",
										{
											required : true,
											messages : {
												required : "<s:text name="The block/wings name is required" />",
											}

										})
						$("#township_txt_id").keyup(function() {
							textValidate(this, '100', 'AI');
						});
						$("#society").keyup(function() {
							textValidate(this, '100', 'AI');
						});

						$("#noofmem").keyup(function() {
							textValidate(this, '5', 'MNM');
						});
						$("#registerNo").keyup(function() {
							textValidate(this, '20', 'REGNM');
						});
						$("#noOfBlocksWings").keyup(function() {
							textValidate(this, '4', 'MNM');
						});
						$("#bankAccNo").keyup(function() {
							textValidate(this, '16', 'MNM');
						});
						$("#bankAccName").keyup(function() {
							textValidate(this, '100', 'OA');
						});
						$("#bankName").keyup(function() {
							textValidate(this, '100', 'OA');
						});
						$("#emailId").blur(function() {
							toValiEmail(this.id);
						});

						$("#userCreateButtonId").click(
								function() {
									$("#firstdivid").css("display", "block");
									$("#seconddivid").css("display", "block");
									$("#thirddivid").css("display", "block");
									$("#societyupdateformid").attr("action",
											"societyUpdateAction");
									$("#societyupdateformid").attr("method",
											"Post");
									$("#societyupdateformid").attr("enctype",
											"multipart/form-data");
									$("#societyupdateformid").submit();
								});

					});
	function onchangetownship() {
		var townshipid = $("#township_hidd_id").val();
		//alert(townshipid);
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getTownshipData',
			data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				$("#towndatadiv").html(data);
			}
		});
	}
	function cancelFunction() {
		$("#userCancelForm").attr("action", "societymgmt");
		$("#userCancelForm").submit();
		$('#userCancelButtonId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass(
				'<s:text name="cardpanel.button.animated.flipoutx"/>');
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
			$('#otherhidden').show();
			$('.imgaddplus1').css("display", "none");
		} else if (check == "sub") {
			$('.imgaddminus1').css("display", "none");
			$('.imgaddplus1').css("display", "inline");
			$('#otherhidden').hide();
		}

	}
	function myFunction3(check) {
		if (check == "add") {
			$('.imgaddminus2').css("display", "inline");
			$('#accounthidden').show();
			$('.imgaddplus2').css("display", "none");
		} else if (check == "sub") {
			$('.imgaddminus2').css("display", "none");
			$('.imgaddplus2').css("display", "inline");
			$('#accounthidden').hide();
		}

	}
</script>
</body>
</html>