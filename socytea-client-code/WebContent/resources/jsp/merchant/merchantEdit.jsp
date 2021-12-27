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
<title><s:text name="Text.Title"/></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="<s:text name='Resource.path'/>/css/custom/custom.css" type="text/css" rel="stylesheet">
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
								<h5 class="breadcrumbs-title"><s:text name="Text.merchantmody.Management" /></h5>
								<ol class="breadcrumbs">
								<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="merchantMgmtTbl"><s:text name="Breadcrumb.manage" /></a></li>
								<li><a href="merchantMgmtTbl"><s:text name="Breadcrumb.util.merchantmgmt" /></a></li>
								<li class="active"><s:text name="Text.merchantmody.Management" /></li>
								</ol>
					</div> </div> </div> </div>
				<!--breadcrumbs end-->
				<!--start container-->
<div class="container">
<div id="jqueryvalidation" class="section">
<div class="card-panel">
<form id="merchantEditFormId" name="merchantEditFormId" action="editMerchant" method="post" enctype="multipart/form-data" >
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">
								<%-- 	<div class="imgaddplus" onclick="showProfileDiv('add');" style="display: none;"> <i class="mdi-content-add-circle tinysmall " style="color: #ff4081; float: left; cursor: pointer;"></i> <div class="spacialspace" style="font-weight: bold;"> <s:text name="Text.profile.detail" /> : </div> </div>   onclick="showProfileDiv('sub');--%>
								<!-- <div><div class="imgaddminus""><i class="<s:text name="button.color.minushideshow"/>" ></i>
								<div class="spacialspace"><s:text name="Text.profile.detail" />
								</div></div></div> <div style="clear: both; height: 5px;"></div> -->

								<div id="profilehidden" style="display: block; margin: 15px;">
											<div class="row">
										    <div class="col s12 m4 l3">
										     <input type="file" name="MrchImage" id="MrchImage" class="dropify" data-default-file="/templogo/Merchant/web/<s:property value="#session.MarchantSessID"/>/<s:property value="#session.merchantProfileImage" />" onclick="toViewlargesizeimg(this.id);" />
           									 </div>
           									 <div class="col s12 m8 l9">
												<div class="row">
  														   <div class="input-field col s6">
  														   <label for="mrchntNameId" class=" control-label"><s:text name="Text.Form.MerchantName" /><span class="mandatory">*</span></label>
														<s:textfield cssClass="form-control mrchntNameId" name="merchantobj.mrchntName"
													id="mrchntNameId" value="%{merchantobj.mrchntName}"></s:textfield>
															</div>
  															 <div class="input-field col s6">
																<label for="merchantCategoryId" class="control-label"><s:text name="Text.merchant.category"/><span class="mandatory">*</span></label>
														<s:textfield name="merchntobjName" id="merchantCategoryId"
																cssClass="form-control typeahead tt-query merchantCategoryId left"
																autocomplete="off"
																value="%{merchantobj.MerchantCategoryName}" />
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('merchantCategoryId');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
															<s:hidden type="hidden" name="merchantobj.merchantCategoryId" id="merchantCategoryId_hidd" class="form-control" value="%{merchantobj.merchantCategoryId}" />
															</div>
 															 </div>

 															 <div class="row">
 															 <div class="input-field col s6">

																	<label for="mrchEmailId"><s:text name="Text.emailid" /><span class="mandatory">*</span></label>
															<s:textfield cssClass="form-control merchantemailid"
																name="merchantobj.mrchntEmail" id="mrchEmailId"
																value="%{merchantobj.mrchntEmail}"></s:textfield>
																	</div>
 															 <div class="input-field col s1">
												<label for="idsCodedivid" class=" control-label"><s:text
																	name="Text.isdcode" /><span class="mandatory">*</span></label>
															<s:textfield cssClass="form-control idsCodeid "
																name="merchantobj.mrchntIsdCode" id="idsCodedivid"
																value="%{merchantobj.mrchntIsdCode}"></s:textfield>
											</div>
											 <div class="input-field col s5">
												<label for="mobileNoid" class=" control-label"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
													<s:textfield cssClass="form-control mobileNoid "
																name="merchantobj.mrchntPhNo" id="mobileNoid"
																value="%{merchantobj.mrchntPhNo}"></s:textfield>
											</div>

															</div>
															<div class="row">
															 <div class="input-field col s6">
																		<label for="shopNameid" class=" control-label"><s:text name="Text.Title.Merchantshopname" /><span
																class="mandatory">*</span></label>
															<s:textfield cssClass="form-control shopNameid"
																name="merchantobj.shopName" id="shopNameid"
																value="%{merchantobj.shopName}"></s:textfield>
															</div>
															 <div class="input-field col s6">
																<label for="storeLocationid" class="left control-label active"><s:text name="Text.store.location"/></label>
									<s:textfield cssClass="form-control pinCode left" name="merchantobj.storeLocation"  id="storeLocationid"></s:textfield>
									<span class="input-group-addon timepickericon tooltipped left" style="position:absolute;margin: 5px 0 0 -23px;"  data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Location" onclick="toShowGmap('storeLocationid')"><i class="mdi-maps-place pink-text small"></i></span>
															</div>
															</div></div>
												</div>

								<div class="row">
													<div class="input-field col s6">
													<div class="input-group bootstrap-timepicker timepicker col s12"></div>
													<label for="storeopentime" class=" control-label"><s:text name="Text.store.opentime" /></label>
													<input id="storeopentime" type="text" name="merchantobj.storeOpentime" value='<s:property value="merchantobj.storeOpentime"/>' class="form-control input-small evestarttime left" />
													<span id="startclockspanid" class="input-group-addon timepickericon tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Store Open Time"><i class="mdi-action-query-builder pink-text small"></i></span>
													</div>
													<div class="input-field col s6">
													<div class="input-group bootstrap-timepicker timepicker col s12"></div>
													<label for="storeclosetime" class=" control-label"><s:text name="Text.store.closetime" /></label>
													<input id="storeclosetime" type="text" name="merchantobj.storeClosetime" value='<s:property value="merchantobj.storeClosetime"/>' class="form-control input-small evestarttime left"/>
													<span id="endclockspanid" class="input-group-addon timepickericon tooltipped"  data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Store Close Time"><i class="mdi-action-query-builder pink-text small"></i></span>
													</div>
							</div>
												<div class="row">
													<div class="input-field col s6">
															<label for="mrchFnameTextId" class=" control-label"><s:text
																	name="Text.key.for.search" /></label>
															<s:textfield cssClass="form-control registerNumber"
																name="merchantobj.keyForSearch" id="mrchFnameTextId"
																value="%{merchantobj.keyForSearch}"></s:textfield>
													</div>
													<div class="input-field col s6"></div>
												</div>
				</div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" onclick="showotherDiv('add');"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>
<div class="collapsible-body padding10px" id="seconddivid" >
<!-- old finish --><!-- <div style="clear: both; height: 0px;"></div>
<div id=""> <div class="imgaddplus3" onclick="showotherDiv('add');">
<i class="<s:text name="button.color.addhideshow"/>"></i><span class="spacialspace"> <s:text name="Text.other.detail" /></span>
</div>
<div class="imgaddminus3" onclick="showotherDiv('sub');" style="cursor: pointer; display: none;">
<i class="<s:text name="button.color.minushideshow"/>"></i><span class="spacialspace"><s:text name="Text.other.detail" />
</span></div></div><div class="clear" style="height: 5px;"></div>-->
<div id="otherhidden3" style="display: block; margin: 15px;">
	<div class="row">
													<div class="input-field col s6">
															<label for="mrchntAdd1" class=" control-label"><s:text
																	name="Text.address" />1</label>
															<s:textfield cssClass="form-control pinCode"
																name="merchantobj.mrchntAdd1" id="mrchntAdd1"
																value="%{merchantobj.mrchntAdd1}"></s:textfield>
													</div>
													<div class="input-field col s6">
															<label for="mrchntAdd2" class=" control-label"><s:text name="Text.address" />2</label>
															<s:textfield cssClass="form-control pinCode" name="merchantobj.mrchntAdd2" id="mrchntAdd1" value="%{merchantobj.mrchntAdd2}"></s:textfield>
													</div>
												</div>
												<div class="row">
													<div class="input-field col s6">
															<label for="country_hidd_id" class="control-label"><s:text
																	name="Menuheader.uam.profile.country" /></label>
															<s:textfield id="country_txt_id"
																cssClass="form-control typeahead tt-query left"
																value="%{merchantobj.countryName}" autocomplete="off"
																spellcheck="false" />
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
																<s:hidden type="hidden" name="merchantobj.countryId"
																value="%{merchantobj.countryId}" id="country_hidd_id"
																class="form-control" />
													</div>
													<div class="input-field col s6">
															<label for="state_hidd_id" class=" control-label" style="width: 100%;"><s:text
																	name="Menuheader.uam.profile.state" /></label>
															<s:textfield id="state_txt_id"
																cssClass="form-control typeahead tt-query left"
																value="%{merchantobj.stateName}" autocomplete="off"
																spellcheck="false" />
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<s:hidden type="hidden" name="merchantobj.stateId"
																value="%{merchantobj.stateId}" id="state_hidd_id"
																class="form-control " />
													</div>
												</div>
												<div class="row">
													<div class="input-field col s6">
															<label for="city_hidd_id" class="control-label"><s:text
																	name="Menuheader.uam.profile.city" /></label>
															<s:textfield name="" id="city_txt_id"
																cssClass="form-control typeahead tt-query left"
																value="%{merchantobj.cityName}" autocomplete="off"
																spellcheck="false" />
															<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<s:hidden type="hidden" name="merchantobj.cityId"
																value="%{merchantobj.cityId}" id="city_hidd_id"
																class="form-control" />
													</div>

													<div class="input-field col s6">
															<label for="pstlcode_hidd_id" class="control-label"><s:text name="Text.customerreg.pincode" /></label>
<%-- 															<s:textfield name="" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left" value="%{merchantobj.pstlcodeDesc}" autocomplete="off" spellcheck="false" /> --%>
																<s:textfield name="" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left" value="%{merchantobj.pstlcode}" autocomplete="off" spellcheck="false" />
<%-- 															<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
														<span class="input-group-addon txtbxdownarowicon left" ></span>
														<s:hidden type="hidden" name="merchantobj.pstlcode" id="pstlcode_hidd_id" value="%{merchantobj.pstlcode}" class="form-control" />
													</div>
												</div>
												<div class="row">

													<div class="input-field col s6">
															<label for="merchwebsite" class="control-label"><s:text
																	name="Text.website" /></label>
															<s:textfield name="merchantobj.website" id="merchwebsite"
																cssClass="form-control typeahead tt-query"
																value="%{merchantobj.website}" autocomplete="off"
																spellcheck="false" />
													</div>
												</div>
												<div class="row">
													<div class="input-field col s12">
															<label for="merchDescription"><s:text name="Description" /><span
																class="mandatory"></span></label>
															<textarea name="merchantobj.merchDescription" id="merchDescription"
																class="materialize-textarea validate"><s:property
																	value="%{merchantobj.merchDescription}" /> </textarea>
													</div>
												</div>
											 <div class="row">
                                         <div class="input-field">
                                         <label for="tblid" class="active">Issue Type</label>
										<div class="clear height15px"></div>
											<div id="tblid" class="scrollclass"></div>
											</div></div>

			  <s:iterator value="issList" status="count">
 												<div id="TextBoxDiv<s:property value="#count.Index+1"/>" >
 											<%-- 	<div class="row cnt" >
												<div class="input-field col s6">
												<label for="issueDesc"><s:text name="Issue Text" /></label> --%>
											<%-- 	<s:textfield name="issueDesc" value="%{issueDesc}" id="issueDesc" cssClass="form-control issueCheckbox" /> --%>
												<s:hidden name="issueid" value="%{issueid}" id="issueid" cssClass="form-control issueid" />
												<!-- </div>
  											</div>	 -->
											</div>
											</s:iterator>
	<div  class="row"><div class="input-field col s12"></div></div>
		<div class="row">
				<div class="input-field col s6">
					<label for="issuetxt"><s:text name="Other Issue" /></label>
					<s:textfield name="issuetxt" id="issuetxt" cssClass="form-control issueCheckbox" />
				</div>
					<div id="addcloseimg1" class="input-field col s6">
			<div class="left marginleft5px margintop10px"> Add More : </div>
			<div class="left marginleft5px">
			<button id="addButtonissue" class="btn-floating btn-small waves-effect waves-light deep-orange lighten-2 animated zoomIn" type="button" name="submitbtn" value="">
			<i class="mdi-content-add"></i></button></div>
			<div class="left marginleft5px">
			<button id="removeButtonissue" class="btn-floating btn-small waves-effect waves-light grey darken-3 animated zoomIn" type="button" onclick="removeissue();" name="submitbtn" value="">
			<i class="mdi-content-remove"></i></button>
			</div>
			</div>

		</div>

		<div id='TextBoxesGroupissue'><div id="TextBoxDivissue"></div></div>

		<input type="hidden" name="offercheck" id="issuetxtval" class="form-control" />
		<input type="hidden" name="offertxt" id="checkboxval" class="form-control" />

											</div>

</div>
</li>

<s:if test="merchantobj.merchantCategoryId==1">
	<li id="featuersliid" style="display: block;">
	<div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-av-album tinysmall white-text text-accent-4'></i> <s:text name="Text.sports.detail" /> </div>
	<div class="collapsible-body padding10px" id="thirdivid">
	<div id="merchanttypeformfields">
				<!-- <div id="" style="float: left">
					<div class="imgaddplus7" onclick="showSportsDiv('add');" style="">
					<i class="mdi-content-add-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
					<div class="spacialspace" style="font-weight: bold;">Sports Detail :</div>
					</div><div class="imgaddminus7" onclick="showSportsDiv('sub');" style="cursor: pointer; display: none;">
					<i class="mdi-content-remove-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
					<div class="spacialspace" style="font-weight: bold;">Sports Detail :</div> </div> </div> -->
					<div class="col s12 moreadddivclass" style="float: left; display: block;" id="addcloseimg1">
					<div class="left marginleft5px margintop10px"> Add More : </div>
															<div class="left marginleft5px">
															<button type="button" onclick="addMoreSportsDetail();" class="<s:text name="button.color.addfield"/>" id="addButton2"
												name="submitbtn" value=""><i class="mdi-content-add"></i></button>
															</div>
															<div class="left marginleft5px">
															<button type="button" onclick="removelastSportsDetail();" class="<s:text name="button.color.removefield"/>" id="removeButton2"
															name="submitbtn" value=""><i class="mdi-content-remove"></i></button>
															</div>
															<!-- <div class="left" style="width: 30px;">
																<img width="20px" height="20px" onclick="removelastSportsDetail();" id="removeButton2" class="pointer" style="" src="resources/images/icon/closebtn.png">
															</div> -->
						</div>
						<div style="clear: both; height: 5px;"></div>
						<div id="sportshidden7" style="display: block; margin: 15px;">
							<s:iterator value="merchantStockobjList" status="famcount">
															<s:if test="#famcount.Index+1>=1">
																<div class="row">
																	<div class="input-field col s6">
																			<label for="typeName<s:property value="%{#famcount.Index+1}"/>" class="control-label"><s:text
																					name="Text.merchant.sports" /></label>
																			<s:textfield id="typeName%{#famcount.Index+1}"
																				cssClass="form-control typeahead tt-query typeNameArray"
																				autocomplete="off" spellcheck="false"
																				value="%{typeName}" />
																	</div>

																	<div class="input-field col s6">
																			<label for="quantity<s:property value="#famcount.Index+1"/>" class="control-label"><s:text
																					name="Text.merchant.quantity" /></label>
																			<s:textfield name="mrchStockobj.quantity" id="quantity%{#famcount.Index+1}"
																				cssClass="form-control typeahead tt-query quantityArray"
																				 autocomplete="off" spellcheck="false" value="%{quantity}" />
																	</div>
																</div>
																<div class="clear" style="height: 10px;"></div>
															</s:if>
															<s:else>
																<div id="TextBoxDiv2<s:property value="#famcount.Index"/>">
																	<div id="appenddiv<s:property value="#famcount.Index"/>" class="row">
																		<div class="input-field col s6">
																				<label for="sports<s:property value="#famcount.Index+1"/>" class="control-label"><s:text
																						name="Text.merchant.sports" /></label>
																				<s:textfield
																					cssClass="form-control typeahead tt-query typeNameArray"
																					id="sports%{#famcount.Index+1}"
																					autocomplete="off" spellcheck="false"
																					value="%{typeName}" />
																		</div>

																		<div class="input-field col s6">
																				<label for="quantity<s:property value="#famcount.Index+1"/>" class="control-label"><s:text
																						name="Text.merchant.quantity" /></label>
																				<s:textfield name="mrchStockobj.quantity"
																					cssClass="form-control typeahead tt-query quantityArray"
																					id="quantity%{#famcount.Index+1}" autocomplete="off"
																					spellcheck="false" value="%{quantity}" />
																		</div>
																	</div>
																	<div class="clear" style="height: 10px;"></div>
																</div>
															</s:else>
							</s:iterator>

			<div class="row" id="nosportsdivid" style="display:none;">
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="sports" class="control-label"><s:text name="Text.merchant.sports" /></label>
					<s:textfield  id="sports" cssClass="form-control typeahead tt-query typeNameArray" autocomplete="off" spellcheck="false" />
				</div>
			</div>

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="quantity" class="control-label"><s:text name="Text.merchant.quantity" /></label>
					<s:textfield name="mrchStockobj.quantity" id="quantity" cssClass="form-control typeahead tt-query quantityArray" autocomplete="off" spellcheck="false" />
				</div>
			</div>
		</div>

							<div id='TextBoxesGroup2'> <div id="TextBoxDiv2"></div>	</div>
							<s:hidden id="sportsCounterval" value="%{merchantStockobjList.size()}"></s:hidden>
							<s:hidden name="typeNameArray" id="typeNameArrayid"></s:hidden>
							<s:hidden name="quantityArray" id="quantityArrayid"></s:hidden>
					</div>
			 </div></div></li>
	 </s:if>
	 <s:elseif test="merchantobj.merchantCategoryId==2">
			<li id="featuersliid" style="display: block;">
			<div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-maps-local-hospital tinysmall white-text text-accent-4'></i> <s:text name="Text.pharmacy.detail" /></div>
			<div class="collapsible-body padding10px" id="thirdivid">
			<div id="merchanttypeformfields">
			 	<!-- <div id=""> <div class="imgaddplus8" onclick="showPharmacyDiv('add');" style="">
				<i class="mdi-content-add-circle tinysmall " style="color: #ff4081; float: left; cursor: pointer;"></i>
				<div class="spacialspace" style="font-weight: bold;">Pharmacy Detail </div> </div>
				<div class="imgaddminus8" onclick="showPharmacyDiv('sub');" style="cursor: pointer; display: none;">
				<i class="mdi-content-remove-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
				<div class="spacialspace" style="font-weight: bold;">Pharmacy Detail </div></div></div> -->
				<div class="col s12 moreadddivclass" style="float: left; display: block;" id="addcloseimg2">
				<div style="" id="usernamedivid" class="form-group">
				<div class="left marginleft5px margintop10px"> Add More : </div>
					<div class="left marginleft5px">
					<button type="button" onclick="addMorePharmacyDetail();" class="<s:text name="button.color.addfield"/>" id="addButton1" name="submitbtn" value=""><i class="mdi-content-add"></i></button>
					</div>
					<div class="left marginleft5px" style="width: 30px;">
						<button type="button" onclick="removelastPharmacyDetail();" class="<s:text name="button.color.removefield"/>" id="removeButton1" name="submitbtn" value=""><i class="mdi-content-remove"></i></button>
					</div>
				</div>
				</div> <div class="clear" style="height: 5px;"></div>
				<div id="pharmacyhidden8" style="display: block; margin: 15px;">
					<s:iterator value="merchantStockobjList" status="famcount">
														<s:if test="#famcount.Index+1>=1">
															<div class="borderradius">
																<div class="row">
																	<div class="input-field col s6">
																			<label for="typeName<s:property value="#famcount.Index+1"/>" class="control-label">Medicine</label>
																			<s:textfield name="mrchStockobj.typeName" id="typeName%{#famcount.Index+1}"
																				cssClass="form-control typeahead tt-query typeNameArray"
																				 autocomplete="off"
																				spellcheck="false" value="%{typeName}" />
																	</div>
																	<div class="input-field col s6">
																			<label for="power<s:property value="#famcount.Index+1"/>" class="control-label">Power</label>
																			<s:textfield name="mrchStockobj.power" id="power%{#famcount.Index+1}"
																				cssClass="form-control typeahead tt-query powerArray" autocomplete="off"
																				spellcheck="false" value="%{power}" />
																	</div>
																</div>
																<div class="row">
																	<div class="input-field col s6">
																		<div for="companyName<s:property value="#famcount.Index+1"/>" class="form-group" id="divemailId">
																			<label class="control-label">Company</label>
																			<s:textfield name="mrchStockobj.companyName" id="companyName%{#famcount.Index+1}"
																				cssClass="form-control typeahead tt-query companyNameArray" autocomplete="off"
																				spellcheck="false" value="%{companyName}" />
																		</div>
																	</div>

																	<div class="input-field col s6">
																			<label for="quantity<s:property value="#famcount.Index+1"/>" class="control-label">Quantity</label>
																			<s:textfield name="mrchStockobj.quantity" id="quantity%{#famcount.Index+1}"
																				cssClass="form-control typeahead tt-query quantityArray" autocomplete="off"
																				spellcheck="false" value="%{quantity}" />
																	</div>
																</div>
															</div>
															<div class="clear" style="height: 10px;"></div>

														</s:if>
														<s:else>
															<div id="TextBoxDiv<s:property value="#famcount.Index"/>">
																<div id="appenddiv<s:property value="#famcount.Index"/>">
																	<div class="borderradius">
																		<div class="row">
																			<div class="input-field col s6">
																					<label for="typeName<s:property value="#famcount.Index+1"/>"  class="control-label">Medicine</label>
																					<s:textfield name="mrchStockobj.typeName" id="typeName%{#famcount.Index+1}"
																						cssClass="form-control typeahead tt-query typeNameArray"
																						autocomplete="off" spellcheck="false"
																						value="%{typeName}" />
																			</div>
																			<div class="input-field col s6">
																					<label for="power<s:property value="#famcount.Index+1"/>" class="control-label">Power</label>
																					<s:textfield name="mrchStockobj.power" id="power%{#famcount.Index+1}" cssClass="form-control typeahead tt-query powerArray" autocomplete="off" spellcheck="false" value="%{power}" />
																			</div>
																		</div>
																		<div class="row">
																			<div class="input-field col s6">
																					<label for="companyName<s:property value="#famcount.Index+1"/>" class="control-label">Company</label>
																					<s:textfield name="mrchStockobj.companyName" id="companyName%{#famcount.Index+1}" cssClass="form-control typeahead tt-query companyNameArray" autocomplete="off" spellcheck="false"
																						value="%{companyName}" />
																			</div>

																			<div class="input-field col s6">
																					<label for="quantity<s:property value="#famcount.Index+1"/>" class="control-label">Quantity</label>
																					<s:textfield name="mrchStockobj.quantity" id="quantity%{#famcount.Index+1}"
																						cssClass="form-control typeahead tt-query quantityArray" autocomplete="off"
																						spellcheck="false" value="%{quantity}" />
																			</div>
																		</div>
																	</div>
																</div>
																<div class="clear" style="height: 10px;"></div>
															</div>
														</s:else>
			</s:iterator>

	<div  class="borderradius" id="nosportsdivid" style="display:none;">
		<div class="row">
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="typeName" class="control-label">Medicine</label>
					<s:textfield name="mrchStockobj.typeName" id="typeName"
						cssClass="form-control typeahead tt-query typeNameArray"
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="power" class="control-label">Power</label>
					<s:textfield name="mrchStockobj.power" id="power"
						cssClass="form-control typeahead tt-query powerArray"
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>
		</div>
		<div class="row">

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="company" class="control-label">Company</label>
					<s:textfield name="mrchStockobj.companyName" id="company" cssClass="form-control typeahead tt-query companyNameArray"
						autocomplete="off" spellcheck="false" />
				</div>
			</div>

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="quantity" class="control-label">Quantity</label>
					<s:textfield name="mrchStockobj.quantity" id="quantity" cssClass="form-control typeahead tt-query quantityArray"
						autocomplete="off" spellcheck="false" />
				</div>
			</div>
		</div>
		</div>
			<div id='TextBoxesGroup'></div> <div id="TextBoxDiv"></div>
			<s:hidden id="sportsCounterval" value="%{merchantStockobjList.size()}"></s:hidden>
			<s:hidden name="typeNameArray" id="typeNameArrayid"></s:hidden>
			<s:hidden name="powerArray" id="powerArrayid"></s:hidden>
			<s:hidden name="companyNameArray" id="companyNameArrayid"></s:hidden>
			<s:hidden name="quantityArray" id="quantityArrayid"></s:hidden>
		</div>
		</div></div></li>
	</s:elseif>
	<s:elseif test="merchantobj.merchantCategoryId==3">
		<li id="featuersliid" style="display: block;">
		<div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-social-whatshot tinysmall white-text text-accent-4'></i> <s:text name="Text.jewellery.detail" /></div>
		<div class="collapsible-body padding10px" id="thirdivid">
		<div id="merchanttypeformfields">
		<!-- <div id=""><div class="imgaddplus9" onclick="showjwelleryDiv('add');" style="">
											<i class="mdi-content-add-circle tinysmall " style="color: #ff4081; float: left; cursor: pointer;"></i>
											<div class="spacialspace" style="font-weight: bold;">Jewellery Detail </div>
											</div>
											<div class="imgaddminus9" onclick="showjwelleryDiv('sub');" style="cursor: pointer; display: none;">
		<i class="mdi-content-remove-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
		<div class="spacialspace" style="font-weight: bold;">Jewellery Detail </div>
		</div></div> -->
		<div class="col s12 moreadddivclass" style="float: left; display: block;" id="addcloseimg3">
		<div style="" id="usernamedivid" class="form-group">
		<div class="left marginleft5px margintop10px"> Add More : </div>
		<div class="left marginleft5px">
		<button type="button" onclick="addMoreJewellaryDetail();" class="<s:text name="button.color.addfield"/>" id="addButton" name="submitbtn" value=""><i class="mdi-content-add"></i></button>
		</div>
		<div class="left marginleft5px">
		<button type="button" onclick="removelastjwellaryDetail();" class="<s:text name="button.color.removefield"/>" id="removeButton" name="submitbtn" value=""><i class="mdi-content-remove"></i></button>
		</div></div></div><div style="clear: both; height: 5px;"></div>
		<div id="jewelleryhidden9" style="display: block; margin: 15px;">
					<s:iterator value="merchantStockobjList" status="famcount">
												<s:if test="#famcount.Index+1>=1">
													<div class="borderradius">
														<div class="row">
															<div class="input-field col s6">
																	<label for="typeName<s:property value="#famcount.Index+1"/>" class="control-label"><s:text name="Text.merchant.jewellery" /></label>
																	<s:textfield name="mrchStockobj.typeName" id="typeName%{#famcount.Index+1}"
																		cssClass="form-control typeahead tt-query typeNameArray"
																		value="%{typeName}"
																		autocomplete="off" spellcheck="false" />
															</div>

															<div class="input-field col s6">
																	<label for="quantity<s:property value="#famcount.Index+1"/>" class="control-label"><s:text
																			name="Text.merchant.quantity" /></label>
																	<s:textfield name="mrchStockobj.quantity" id="quantity%{#famcount.Index+1}"
																		cssClass="form-control typeahead tt-query quantityArray"
																		value="%{quantity}" autocomplete="off" spellcheck="false" />
															</div>
														</div>
													</div>
													<div class="clear" style="height: 10px;"></div>

												</s:if>
												<s:else>
													<div id="TextBoxDiv1<s:property value="#famcount.Index"/>">
														<div class="borderradius">
															<div id="appenddiv<s:property value="#famcount.Index"/>"
																class="row">
																<div class="input-field col s6">
																		<label for="typeName<s:property value="#famcount.Index+1"/>" class="control-label"><s:text
																				name="Text.merchant.jewellery" /></label>
																		<s:textfield name="mrchStockobj.quantity" id="typeName%{#famcount.Index+1}"
																			cssClass="form-control typeahead tt-query typeNameArray"
																			value="%{typeName}"
																			autocomplete="off" spellcheck="false" />
																</div>

																<div class="input-field col s6">
																		<label  for="quantity<s:property value="#famcount.Index+1"/>" class="control-label"><s:text
																				name="Text.merchant.quantity" /></label>
																		<s:textfield name="mrchStockobj.quantity" id="quantity%{#famcount.Index+1}"
																			cssClass="form-control typeahead tt-query quantityArray"
																			value="%{quantity}" autocomplete="off" spellcheck="false" />
																</div>
															</div>
														</div>
														<div class="clear" style="height: 10px;"></div>
														</div>
												</s:else>
			</s:iterator>

			<div class="row" id="nosportsdivid" style="display:none;">
			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="jwelltype" class="control-label"><s:text
							name="Text.merchant.jewellery" /></label>
					<s:textfield name="mrchStockobj.typeName" id="jwelltype"
						cssClass="form-control typeahead tt-query typeNameArray" value=""
						autocomplete="off"
						spellcheck="false" />
				</div>
			</div>

			<div class="input-field col s6">
				<div class="form-group" id="divemailId">
					<label for="jwellquantity" class="control-label"><s:text
							name="Text.merchant.quantity" /></label>
					<s:textfield name="mrchStockobj.quantity" id="jwellquantity"
						cssClass="form-control typeahead tt-query quantityArray" value=""
						autocomplete="off" spellcheck="false" />
				</div>
			</div>
		</div>

			<div id='TextBoxesGroup1'> <div id="TextBoxDiv1"></div>	</div>
			<s:hidden id="sportsCounterval" value="%{merchantStockobjList.size()}"></s:hidden>
			<s:hidden name="typeNameArray" id="typeNameArrayid"></s:hidden>
			<s:hidden name="quantityArray" id="quantityArrayid"></s:hidden>
			</div></div></div></li>

		</s:elseif>
		<s:elseif test="merchantobj.merchantCategoryId==4">
		<li id="featuersliid" style="display: block;">
		<div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-maps-local-restaurant tinysmall white-text text-accent-4'></i> <s:text name="Text.restaurant.detail" /></div>
		<div class="collapsible-body padding10px" id="thirdivid">
		<div id="merchanttypeformfields">
		 <!-- <div id=""> <div class="imgaddplus9" onclick="showRestaurantDiv('add');" style="">
			<i class="mdi-content-add-circle tinysmall " style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace" style="font-weight: bold;">Restaurant Detail </div> </div>
			<div class="imgaddminus9" onclick="showRestaurantDiv('sub');" style="cursor: pointer; display: none;">
			<i class="mdi-content-remove-circle tinysmall" style="color: #ff4081; float: left; cursor: pointer;"></i>
			<div class="spacialspace" style="font-weight: bold;">Restaurant Detail </div> </div> </div> <div style="clear: both; height: 5px;"></div>-->
			<div id="restauranthidden9" style="display: block; margin: 15px;">
										<div class="borderradius">
											<div class="row">
												<div class="input-field col s6">
														<label for="Cuisines" class="control-label">Cuisines</label>
														<s:textfield name="mrchStockobj.cuisines" id="Cuisines"
															cssClass="form-control typeahead tt-query" autocomplete="off"
															spellcheck="false" value="%{merchantcatdetobj.cuisines}" />
												</div>
												<div class="col s6">
													<div class="form-group">
														<label class=" control-label">Features</label>
														<table>
															<tr>
																<td style="width: 30%;"><div>
																		<s:if test="merchantcatdetobj.breakFast==1">
																			<input class="restaurentCheckbox" type="checkbox" id="breakFast"
																				name="restaruntFeatures[]" value="breakFast"
																				checked="checked">
																		</s:if>
																		<s:else>
																			<input class="restaurentCheckbox" type="checkbox" id="breakFast"
																				name="restaruntFeatures[]" value="breakFast">
																		</s:else>
																		<label for="breakFast" for="option">Break Fast</label>
																	</div></td>
																<td style="width: 40%;"><div >

																		<s:if test="merchantcatdetobj.lunch==1">
																			<input class="restaurentCheckbox" type="checkbox"
																				name="restaruntFeatures[]" value="lunch" id="lunch"
																				checked="checked">
																		</s:if>
																		<s:else>
																			<input class="restaurentCheckbox" type="checkbox" id="lunch"
																				name="restaruntFeatures[]" value="lunch">
																		</s:else>
																		<label for="lunch">Lunch</label>
																	</div></td>
																<td  style="width: 30%;"><div>
																		<s:if test="merchantcatdetobj.dinner==1">
																			<input class="restaurentCheckbox" type="checkbox"
																				name="restaruntFeatures[]" value="dinner" id="dinner"
																				checked="checked">
																		</s:if>
																		<s:else>
																			<input class="restaurentCheckbox" type="checkbox" id="dinner"
																				name="restaruntFeatures[]" value="dinner">
																		</s:else>
																		<label for="dinner">Dinner</label>
															</div></td>
															</tr>
															<tr>
															<td><div>
																		<s:if test="merchantcatdetobj.indoor==1">
																			<input class="restaurentCheckbox" type="checkbox"
																				name="restaruntFeatures[]" value="indoor" id="indoor"
																				checked="checked">
																		</s:if>
																		<s:else>
																			<input class="restaurentCheckbox" type="checkbox" id="indoor"
																				name="restaruntFeatures[]" value="indoor">
																		</s:else>
																		<label for="indoor">Indoor</label>
																	</div></td>
																<td style="height: 50px;"><s:if
																		test="merchantcatdetobj.delivery==1">
																		<input class="myCheckbox" type="checkbox" id="delivery"
																			name="restaruntFeatures[]" value="delivery"
																			checked="checked">
																	</s:if> <s:else>
																		<input class="myCheckbox" type="checkbox" id="delivery"
																			name="restaruntFeatures[]" value="delivery">
																	</s:else> <label for="delivery">Delivery</label></td>
																<td><s:if test="merchantcatdetobj.takeAway==1">
																		<input class="restaurentCheckbox" type="checkbox" id="takeAway"
																			name="restaruntFeatures[]" value="takeAway"
																			checked="checked">
																	</s:if> <s:else>
																		<input class="restaurentCheckbox" type="checkbox" id="takeAway"
																			name="restaruntFeatures[]" value="takeAway">
																	</s:else> <label for="takeAway">Take-Away</label></td>

															</tr>
															<tr>
															<td><s:if test="merchantcatdetobj.cafe==1">
																		<input class="restaurentCheckbox" type="checkbox" id="cafe"
																			name="restaruntFeatures[]" value="cafe"
																			checked="checked">
																	</s:if> <s:else>
																		<input class="restaurentCheckbox" type="checkbox" id="cafe"
																			name="restaruntFeatures[]" value="cafe">
																	</s:else> <label for="cafe">Cafe</label></td>
																<td><s:if test="merchantcatdetobj.luxuryDining==1">
																		<input class="restaurentCheckbox" type="checkbox" id="luxuryDining"
																			name="restaruntFeatures[]" value="luxuryDining"
																			checked="checked">
																	</s:if> <s:else>
																		<input class="restaurentCheckbox" type="checkbox" id="luxuryDining"
																			name="restaruntFeatures[]" value="luxuryDining">
																	</s:else> <label for="luxuryDining">Luxury Dining</label></td>
																<td style="height: 50px;"><s:if
																		test="merchantcatdetobj.nightlife==1">
																		<input class="restaurentCheckbox" type="checkbox" id="nightlife"
																			name="restaruntFeatures[]" value="nightlife"
																			checked="checked">
																	</s:if> <s:else>
																		<input class="restaurentCheckbox" type="checkbox" id="nightlife"
																			name="restaruntFeatures[]" value="nightlife">
																</s:else> <label for="nightlife">Nightlife</label></td>
															</tr>
														</table>
														</div>
														</div>
														</div>
														</div>
			</div>
			<input id="restaruntFeatureDetail" type="hidden" value="" name="restaruntFeatureDetail">
			<div class="clear" style="height: 10px;"></div>

		</div></div></li>
		</s:elseif>
</ul>
      							<div style="clear: both;margin-left: 5px;">
								<button type="button" id="userEditButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
								<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
								</div>
<s:hidden name="merchantobj.mrchntId" id="mrchntId" value="%{merchantobj.mrchntId}"></s:hidden>
</form></div> </div> </div>	</section> </div> </div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<s:form method="post" id="merchantdetail"></s:form>
		<s:hidden name="groupflg" id="groupflg" value="merchant"></s:hidden>
		<s:form method="post" id="userCancelForm"></s:form>
	<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/timepicker_min.css" />
    <jsp:include page="../common/googlemap.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/timepicker_min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	<script type="text/javascript">
	var countissue =2;
	$("#addButtonissue").click(function () {
			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDivissue' + countissue);
		    newTextBoxDiv.after().html('<div class="row" id="appenddiv'+countissue+'"><div class="input-field col s6"><label for="noofblocks_'+countissue+'"><s:text name="Other Issue" /></label><input type="text" name="issuetxt_'+countissue+'" id="noofblocks_'+countissue+'" class="form-control issueCheckbox"/></div></div>');
		     newTextBoxDiv.appendTo("#TextBoxesGroupissue");
		     countissue++;
	});
	 function removeissue(){
	    if(countissue==1){
	         return false;
	      }
	    countissue--;
	       $("#TextBoxDivissue" + countissue).remove();
	}
	 function functiontext(){
			var targetiss = [];
			   $.each($(".issueCheckbox"), function(){
				targetiss.push($(this).val());
			   });
			   $("#issuetxtval").val(targetiss.join(","));
			   var targetiss1 = [];
			   $.each($(".issueid"), function(){
				targetiss1.push($(this).val());
			   });
			   $("#checkboxval").val(targetiss1.join("!_!"));
	 }

	 /*  function functiontext(){
			var targetiss = [];
			   $.each($(".issueCheckbox"), function(){
				targetiss.push($(this).val());
			   });
			   $("#issuetxtval").val(targetiss.join("!_!"));

			   var rescheck="";
				  var l=0;
				  $("input[name='issueFeatures[]']").each( function () {
					  if($(this). prop("checked") == true){
						  if(l==0){
							  rescheck=rescheck+$(this).val();
						  }else{
							  rescheck=rescheck+"!_!"+$(this).val();
						  }
						  }else{
							  if(l==0){
								  rescheck=rescheck+$(this).val()+"=0";
								  }else{
									  rescheck=rescheck+"!_!"+$(this).val()+"=0";
								  }

						  }
					  l++;
				  });
				  $("#checkboxval").val(rescheck);

		}
	 */

	var counter = $("#sportsCounterval").val();
	var flag = true;
	$(document).ready(function() {
		
		$("#pstlcode_txt_id").keyup(function(){
			textValidate(this,'10','MNM');
		});
		
		var mrchntId=$("#mrchntId").val();
		var laborflg=$("#groupflg").val();
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getIssuevalcmp',
			data : "mrchntId="+mrchntId+"&groupflg="+laborflg,
			success : function(data) {
				var retval=data.trim();
				$("#tblid").html(retval);
				$( "<div class='clear height5px'></div>" ).insertBefore( $("input[name='issuelistbox']" ) );
				/* $( "<div class='clear height5px'></div>" ).insertAfter( $( ".myCheckboxs" ) );*/
			}
		});
		
        $('#pstlcode_txt_id').blur(function(){
//         	alert($('#pstlcode_txt_id').val());
			$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
		});

		$("#startclockspanid").click(function(){ $("#storeopentime").click(); });
		$("#endclockspanid").click(function(){$("#storeclosetime").click(); });
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

			if(counter=="" || counter =="0" || counter =="-1" || counter == 0 || counter <= 0){
					$("#nosportsdivid").show();
					$(".moreadddivclass").show();counter=1;
			} else{
				$("#nosportsdivid").html("");
			}
			$("#storeopentime").timepicker();
			$("#storeclosetime").timepicker();

			$.ajax({
					type : 'post',
								datatype : 'json',
								url : 'getMerchantCategory',
								data : '',
								success : function(data) {
									var arr = data.split("!_!");
									loadMerchantCategory(arr[1]);
								}
							});
							 $('#merchantEditFormId').validate({
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
										//toShowLoadingImgoverlay();
									}
								});
							 $("#mrchntNameId").rules("add", {
									required : true,
									minlength: 2,
									maxlength:50,
									messages : {
										required : "<s:text name="Error.merchant.name.require"/>",
										//minlength : "Enter at least 5 characters"
									}
								});
								$("#merchantCategoryId").rules("add", {
									required : true,
									messages : {
										required : "<s:text name="Error.merchant.category.require"/>",
										//minlength : "Enter at least 5 characters"
									}
								});
								$("#mrchEmailId").rules("add", {
									required : true,
									 email: true,
									messages : {
										required : "<s:text name="Error.email.valid"/>",
									}
								});
								$("#idsCodedivid").rules("add", {
									required : true,
									minlength: 1,
									messages : {
										required : "<s:text name="Error.isdcode.require"/>",
									}
								});
								$("#mobileNoid").rules("add", {
									required : true,
									minlength: 10,
									maxlength: 10,
									messages : {
										required : "<s:text name="Error.mobileno.require"/>",
									}
								});
								$("#shopNameid").rules("add", {
									required : true,
									minlength: 2,
									maxlength:50,
									messages : {
										required : "<s:text name="Error.shopname.require"/>",
									}
								});
								$("#imgdivid").click(function(){
						    		$("#MrchImage").click();
						    	});
								 $("#mobileNoid").keyup(function() {
										textValidate(this, '10', 'PHNM');
									});
								 $("#idsCodedivid").keyup(function() {
										textValidate(this, '4', 'PHNM');
									});
								 $("#mrchEmailId").keyup(function() {
										textValidate(this, '100', 'EML');
									});
								 $("#mrchEmailId").blur(function(){toValiEmail(this.id);});
								 $("#mobileNoid").blur(function(){toValiMobno(this.id);});
								 $("#mrchntAdd1").keyup(function(){
									textValidate(this,'100','ADP');
								});
								$("#mrchntAdd2").keyup(function(){
									textValidate(this,'100','ADP');
								});
								 $("#userEditButtonId").click(function(){
									 var categoryval=$("#merchantCategoryId_hidd").val();
									  if(categoryval==1){
										  var typeNameArray = [];
										  var quantityArray=[];
									  $.each($(".typeNameArray"), function(){
										  typeNameArray.push($(this).val());
										   });
									  $.each($(".quantityArray"), function(){
										  quantityArray.push($(this).val());
										   });
									  $("#typeNameArrayid").val(typeNameArray.join("!_!"));
									  $("#quantityArrayid").val(quantityArray.join("!_!"));
									  }else if(categoryval==2){
										  var typeNameArray = [];
										  var quantityArray=[];
										  var companyNameArray=[];
										  var powerArray=[];
									  $.each($(".typeNameArray"), function(){
										  typeNameArray.push($(this).val());
										   });
									  $.each($(".companyNameArray"), function(){
										  companyNameArray.push($(this).val());
										   });
									  $.each($(".powerArray"), function(){
										  powerArray.push($(this).val());
										   });
									  $.each($(".quantityArray"), function(){
										  quantityArray.push($(this).val());
										   });
									  $("#typeNameArrayid").val(typeNameArray.join("!_!"));
									  $("#quantityArrayid").val(quantityArray.join("!_!"));
									  $("#companyNameArrayid").val(companyNameArray.join("!_!"));
									  $("#powerArrayid").val(powerArray.join("!_!"));
									  }else if(categoryval==3){
										  var typeNameArray = [];
										  var quantityArray=[];
									  $.each($(".typeNameArray"), function(){
										  typeNameArray.push($(this).val());
										   });
									  $.each($(".quantityArray"), function(){
										  quantityArray.push($(this).val());
										   });

									  $("#typeNameArrayid").val(typeNameArray.join("!_!"));
									  $("#quantityArrayid").val(quantityArray.join("!_!"));
									  }
									  var restprop="";
									  var l=0;
									  $("input[name='restaruntFeatures[]']").each( function () {
										  if($(this). prop("checked") == true){
											  if(l==0){
											  restprop=restprop+$(this).val()+"=1";
											  }else{
												  restprop=restprop+"!_!"+$(this).val()+"=1";
											  }
											  }else{
												  if(l==0){
													  restprop=restprop+$(this).val()+"=0";
													  }else{
														  restprop=restprop+"!_!"+$(this).val()+"=0";
													  }

											  }
										  l++;
									  });
									  $("#restaruntFeatureDetail").val(restprop);
									  functiontext();
									  var aa=$("#checkboxval").val();
									    $("#firstdivid").css("display","block");
										$("#seconddivid").css("display","block");
										$("#thirdivid").css("display","block");
								    	$('#merchantEditFormId').attr("Method","Post");
								    	$('#merchantEditFormId').attr("action","editMerchant");
								    	$('#merchantEditFormId').attr("enctype","multipart/form-data");
								    	$('#merchantEditFormId').submit();


								 });

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
							 onchangeStatecodeAgency();
							 onchangeCitycodeAgency();
							 onchangePstlcodeAgency();

						});
		function loadMerchantCategory(ar) {
			var objects_country;
			ar = ar.replace(/&quot;/ig, '"');
			ar = ar.replace(/%27/ig, "'");
			var loccutyval = JSON.parse(ar);
			$('#merchantCategoryId').typeahead({
				order : "asc",
				hint : true,
				accent : true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
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
					$('#merchantCategoryId_hidd').val(map[item].id);
					getdivforMerchantCategory();
					return item;
				}
			});
			 $('#merchantCategoryId').blur(function(){
					if (objects_country != undefined && objects_country.indexOf(this.value)!=-1) {
			        }else{
			        	  $('#merchantCategoryId').val('');
			        	  $('#merchantCategoryId').focus();
				          $('#merchantCategoryId_hidd').val('');
			          }
			      });
		}
		function showotherDiv(check) {
			if (check == "add") {
				$('.imgaddminus3').css("display", "inline");
				$('#otherhidden3').show();
				$('.imgaddplus3').css("display", "none");
			} else if (check == "sub") {
				$('.imgaddminus3').css("display", "none");
				$('.imgaddplus3').css("display", "inline");
				$('#otherhidden3').hide();
			}
		}

		function getdivforMerchantCategory() {
			var cntryval = $("#merchantCategoryId_hidd").val();
			$.ajax({
				type : 'post',
				datatype : 'json',
				url : 'getMerchantCategoryform',
				data : 'merchantCategory=' + cntryval,
				success : function(data) {
					if(cntryval==1 || cntryval=="1"){
						$("#thirdivtitid").html("<i class='mdi-av-album tinysmall white-text text-accent-4'></i> <s:text name="Text.sports.detail" />");
						$("#featuersliid").show();
						$("#merchanttypeformfields").html(data);
				} else if(cntryval==2 || cntryval=="2"){
						$("#thirdivtitid").html("<i class='mdi-maps-local-hospital tinysmall white-text text-accent-4'></i> <s:text name="Text.pharmacy.detail" />");
						$("#featuersliid").show();
						$("#merchanttypeformfields").html(data);
				} else if(cntryval==2 || cntryval=="3"){
						$("#thirdivtitid").html("<i class='mdi-social-whatshot tinysmall white-text text-accent-4'></i> <s:text name="Text.jewellery.detail" />");
						$("#featuersliid").show();
						$("#merchanttypeformfields").html(data);
				} else if(cntryval==2 || cntryval=="4"){
						$("#thirdivtitid").html("<i class='mdi-maps-local-restaurant tinysmall white-text text-accent-4'></i> <s:text name="Text.restaurant.detail" />");
						$("#featuersliid").show();
						$("#merchanttypeformfields").html(data);
				} else {
					$("#featuersliid").hide();
				}

					//counter = 2;
				}
			});
		}
		function showSportsDiv(check) {

			if (check == "add") {
				$('.imgaddminus7').css("display", "inline");
				$('#sportshidden7').show();
				$('#addcloseimg1').show();
				$('.imgaddplus7').css("display", "none");
			} else if (check == "sub") {
				$('.imgaddminus7').css("display", "none");
				$('.imgaddplus7').css("display", "inline");
				$('#sportshidden7').hide();
				$('#addcloseimg1').hide();
			}

		}
		function addMoreSportsDetail() {
			flag = true;
			if (flag) {
				var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv2' + counter);
				newTextBoxDiv.after() .html('<div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'"><s:text name="Text.merchant.sports" /></label><input type="text" name="blockname"  id="noofblocks_'+counter+'" class="form-control typeNameArray " /></div></div><div class="input-field col s6" style=""> <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.merchant.quantity"></s:text></label><s:textfield  id="flatnum_'+counter+'" maxlength="20" cssClass="form-control quantityArray" /></div></div></div>');
				newTextBoxDiv.appendTo("#TextBoxesGroup2");
				counter++;
			}
		}
		function removelastSportsDetail() {
			if (counter == 1) {
				/*  alert("No more textbox to remove"); */
				return false;
			}

			counter--;

			$("#TextBoxDiv2" + counter).remove();

		}
		function showPharmacyDiv(check) {

			if (check == "add") {
				$('.imgaddminus8').css("display", "inline");
				$('#pharmacyhidden8').show();
				$('#addcloseimg2').show();
				$('.imgaddplus8').css("display", "none");
			} else if (check == "sub") {
				$('.imgaddminus8').css("display", "none");
				$('.imgaddplus8').css("display", "inline");
				$('#pharmacyhidden8').hide();
				$('#addcloseimg2').hide();
			}

		}
		function showjwelleryDiv(check) {
			if (check == "add") {
				$('.imgaddminus9').css("display", "inline");
				$('#jewelleryhidden9').show();
				$('#addcloseimg3').show();
				$('.imgaddplus9').css("display", "none");
			} else if (check == "sub") {
				$('.imgaddminus9').css("display", "none");
				$('.imgaddplus9').css("display", "inline");
				$('#jewelleryhidden9').hide();
				$('#addcloseimg3').hide();
			}
		}
		function addMoreJewellaryDetail() {


				var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv1' + counter);
				newTextBoxDiv.after().html(' <div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'"><s:text name="Text.merchant.jewellery" /></label><input type="text" name="blockname" id="noofblocks_'+counter+'" class="form-control typeNameArray" /></div></div><div class="input-field col s6" style=""> <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.merchant.quantity"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'" maxlength="20" cssClass="form-control quantityArray" /></div></div></div>');

				newTextBoxDiv.appendTo("#TextBoxesGroup1");
				counter++;

		}
		function removelastjwellaryDetail() {
			if (counter == 1) {
				/*  alert("No more textbox to remove"); */
				return false;
			}
			counter--;

			$("#TextBoxDiv1" + counter).remove();
		}
		function showPharmacyDiv(check) {
			if (check == "add") {
				$('.imgaddminus8').css("display", "inline");
				$('#pharmacyhidden8').show();
				$('.imgaddplus8').css("display", "none");
				$('#addcloseimg2').show();
			} else if (check == "sub") {
				$('.imgaddminus8').css("display", "none");
				$('.imgaddplus8').css("display", "inline");
				$('#pharmacyhidden8').hide();
				$('#addcloseimg2').hide();
			}
		}
		function showProfileDiv(check) {
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
		function addMorePharmacyDetail() {
			var no_flats = $("#noofflats").val();
			if (counter > no_flats) {
				alert("Only " + no_flats + " Flat No. Allow");
				flag = false;
			} else {
				flag = true;
			}
			if (flag) {
				var newTextBoxDiv = $(document.createElement('div')).attr("id",
						'TextBoxDiv' + counter);
				newTextBoxDiv
						.after()
						.html(
								' <div class="borderradius"><div class="row"  id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'"><s:text name="Text.merchant.medicine" /></label><input type="text" name="blockname" id="noofblocks_'+counter+'" class="form-control typeNameArray" /></div></div><div class="input-field col s6" style=""> <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.merchant.power"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'" maxlength="20" cssClass="form-control powerArray" /></div></div></div><div class="row" id="appenddiv'+counter+'"><div class="input-field col s6"><div class="form-group"><label for="noofblocks_'+counter+'"><s:text name="Text.merchant.company" /></label><input type="text" name="blockname" id="noofblocks_'+counter+'" class="form-control companyNameArray" /></div></div><div class="input-field col s6" style=""> <div class="form-group" ><label for="flatnum_'+counter+'"><s:text name="Text.merchant.quantity"></s:text></label><s:textfield name="restRegObj.flatnum_'+counter+'" id="flatnum_'+counter+'" maxlength="20" cssClass="form-control quantityArray" /></div></div></div></div><div class="clear" style="height:10px;"></div>');

				newTextBoxDiv.appendTo("#TextBoxesGroup");
				counter++;
			}
		}
		function removelastPharmacyDetail() {
			if (counter == 1) {
				/*  alert("No more textbox to remove"); */
				return false;
			}

			counter--;

			$("#TextBoxDiv" + counter).remove();
		}
		function showRestaurantDiv(check) {
			if (check == "add") {
				$('.imgaddminus9').css("display", "inline");
				$('#restauranthidden9').show();
				$('.imgaddplus9').css("display", "none");

			} else if (check == "sub") {
				$('.imgaddminus9').css("display", "none");
				$('.imgaddplus9').css("display", "inline");
				$('#restauranthidden9').hide();

			}
		}

		function CountrylistFun(ar) {
			var objects_country;
			ar = ar.replace(/&quot;/ig, '"');
			ar = ar.replace(/%27/ig, "'");
			var loccutyval = JSON.parse(ar);
			$('#country_txt_id').typeahead({
				order : "asc",
				hint : true,
				accent : true,
				offset : true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
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
// 					$('#pstlcode_txt_id').val('');
// 					$('#pstlcode_txt_id').typeahead('destroy');
// 					$('#pstlcode_hidd_id').val('');

					onchangeStatecodeAgency();
					return item;
				}
			});

			$('#country_txt_id').blur(
					function() {
						if (typeof (objects_country) != "undefined"
								&& objects_country.indexOf(this.value) != -1) {
						} else {
							$('#country_txt_id').val('');
							$('#country_txt_id').focus();
							$('#country_hidd_id').val('');
							$('#state_txt_id').val('');
							$('#state_hidd_id').val('');
							$('#state_txt_id').typeahead('destroy');
							$('#city_txt_id').val('');
							$('#city_hidd_id').val('');
							$('#city_txt_id').typeahead('destroy');
// 							$('#pstlcode_txt_id').val('');
// 							$('#pstlcode_txt_id').typeahead('destroy');
// 							$('#pstlcode_hidd_id').val('');
						}
					});
		}

		function onchangeStatecodeAgency() {
			var cntryval = $("#country_hidd_id").val();
			if (cntryval != "" && cntryval != "null" && cntryval != "0"
					&& typeof (cntryval) != "undefined") {
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
		}

		function statelistload(ar) {
			var objects_state;
			ar = ar.replace(/&quot;/ig, '"');
			ar = ar.replace(/%27/ig, "'");
			var loc_state_val = JSON.parse(ar);
			$('#state_txt_id').typeahead({
				order : "asc",
				hint : true,
				accent : true,
				offset : true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
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
					/*  if(item!=null){
						   $( "#stateId" ).removeClass( "has-error" ).addClass( "form-group has-success" );
						  // $("#stateId").addClass( "form-group has-success" );
					   }else{
						   alert("fgfg");
						   $( "#stateId" ).removeClass( "has-success" ).addClass( "form-group has-error" );
					   }  */
					/*  $("#state_txt_id").keyup(function(){
						if($('#state_txt_id').val()==null){
							$( "#stateId" ).removeClass( "has-success" ).addClass( "form-group has-error" );
					    	  // $("#stateId").addClass( "form-group has-success" );
					       }
					});  */

					$('#city_txt_id').val('');
					$('#city_hidd_id').val('');
					$('#city_txt_id').typeahead('destroy');
// 					$('#pstlcode_txt_id').val('');
// 					$('#pstlcode_txt_id').typeahead('destroy');
// 					$('#pstlcode_hidd_id').val('');

					onchangeCitycodeAgency();
					return item;
				}
			});

			$('#state_txt_id').blur(
					function() {
						if (typeof (objects_state) != "undefined"
								&& objects_state.indexOf(this.value) != -1) {
						} else {
							$('#state_txt_id').val('');
							$('#state_txt_id').focus();
							$('#state_hidd_id').val('');

							$('#city_txt_id').val('');
							$('#city_hidd_id').val('');
							$('#city_txt_id').typeahead('destroy');
// 							$('#pstlcode_txt_id').val('');
// 							$('#pstlcode_txt_id').typeahead('destroy');
// 							$('#pstlcode_hidd_id').val('');

						}
					});
		}
		//City Load
		function onchangeCitycodeAgency() {
			var statval = $("#state_hidd_id").val();
			if (statval != "" && statval != "null" && statval != "0"
					&& typeof (statval) != "undefined") {
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'agencyGetCityvalue',
					data : 'stateidkey=' + statval,
					success : function(data) {
						var arr = data.split("!_!");
						citylistload(arr[1]);
					}
				});
			}
		}

		function citylistload(arr) {
			var objects_city;
			arr = arr.replace(/&quot;/ig, '"');
			arr = arr.replace(/%27/ig, "'");
			var loc_state_val = JSON.parse(arr);
			$('#city_txt_id').typeahead({
				order : "asc",
				hint : true,
				accent : true,
				offset : true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
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
// 					$('#pstlcode_txt_id').val('');
// 					$('#pstlcode_txt_id').typeahead('destroy');
// 					$('#pstlcode_hidd_id').val('');

// 					onchangePstlcodeAgency();
					return item;
				}
			});

			$('#city_txt_id').blur(
					function() {
						if (typeof (objects_city) != "undefined"
								&& objects_city.indexOf(this.value) != -1) {
						} else {
							/*   $('#city_txt_id').val('');
							 // $('#city_txt_id').focus();
							  $('#city_hidd_id').val(''); */
// 							$('#pstlcode_txt_id').typeahead('destroy');
// 							$('#pstlcode_hidd_id').val('');

						}
					});
		}
		function onchangePstlcodeAgency() {
			var cityval = $("#city_hidd_id").val();
			if (cityval != "" && cityval != "null" && cityval != "0"
					&& typeof (cityval) != "undefined") {
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'agencyGetPstlcodevalue',
					data : 'cityidkey=' + cityval,
					success : function(data) {
						var arr = data.split("!_!");
						pstlcodelistload(arr[1]);
					}
				});
			}
		}

		function pstlcodelistload(arr) {
			var objects_pstl;
			arr = arr.replace(/&quot;/ig, '"');
			var loc_pstl_val = JSON.parse(arr);
			$('#pstlcode_txt_id').typeahead({
				order : "asc",
				hint : true,
				accent : true,
				offset : true,
				searchOnFocus : true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
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
					return item;
				}
			});

			$('#pstlcode_txt_id').blur(
					function() {
						if (typeof (objects_pstl) != "undefined"
								&& objects_pstl.indexOf(this.value) != -1) {
						} else {
							/* $('#pstlcode_txt_id').val('');
							$('#pstlcode_txt_id').focus();
							$('#pstlcode_hidd_id').val(''); */
						}
					});
		}

		function cancelFunction() {
			$("#userCancelForm").attr("action", "merchantMgmtTbl");
			$("#userCancelForm").submit();
			$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	    	toShowLoadingImgoverlay();
	    	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
		}
	</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>


</html>