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
<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="<s:text name='Resource.path'/>/css/custom/custom.css" type="text/css" rel="stylesheet">
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
                <h5 class="breadcrumbs-title"><s:text name="Text.createlabour.Management"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="labourmgmt"><s:text name="Breadcrumb.manage" /></a></li>
					<li><a href="labourmgmt"><s:text name="Breadcrumb.manage.labormgmt" /></a></li>
                    <li class="active"><s:text name="Text.createlabour.Management" /></li>
                   </ol>
              </div>
            </div>
          </div>
        </div>
       	<div class="container">
       	<div class="card-panel">
<form  id="labourCreationFormId" name="labourCreationFormId"  action="labourmgmtadd" method="post" enctype="multipart/form-data"  >
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">
										  <%-- <div class="imgaddplus" onclick="myFunction1('add');" style="display:none;"> <i class="<s:text name="button.color.addhideshow"/>" ></i>
										  <div class="spacialspace">Profile Detail : </div> </div> onclick="myFunction1('sub');" --%>
										   <!--  <div id=""> <div class="imgaddminus" > <i class="<s:text name="button.color.minushideshow"/>" ></i>
										    <div class="spacialspace">Profile Detail </div> </div>
										    </div><div style="clear: both; height:5px;"></div> -->

			<div id="profilehidden" style="display:block; margin:15px;">
					<div class="row">
									  <div class="col s12 m4 l3">
										    <input type="file" name="staffImage" id="staffImage" class="dropify" data-default-file="" />
           									 </div>
           									 <div class="col s12 m8 l9">
												<div class="row">
  														   <div class="input-field col s6">
																<label for="name"><s:text name="Text.name"></s:text><span class="mandatory"></span></label>
																<s:textfield name="labRegObj.ivrBnLBR_NAME" id="name" cssClass="form-control firstnamevalidate"/>
															</div>
  															 <div class="input-field col s6">
																<label for="emailid"><s:text name="Text.customerreg.emailid" /></label>
																<s:textfield name="labRegObj.ivrBnLBR_EMAIL" id="emailid" cssClass="form-control emailidvalidate"/>
															</div>
 															 </div>

 															 <div class="row">
 															 <div class="input-field col s1">
												<label for="isdno"><s:text name="Text.Form.IsdCode"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control IsdNumber"  name="labRegObj.ivrBnLBR_ISD_CODE"  id="isdno" value="%{getText('Text.ISD.value')}"></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
											 <div class="input-field col s5">
												<label for="mobilenoid"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber " name="labRegObj.ivrBnLBR_PH_NO"
												id="mobilenoid"></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
											       <div class="input-field col s6">
                								<label for="companyname"><s:text name="Text.Companyname" /><span class="mandatory">*</span></label>
             										<s:textfield  name="laborcompanyname" id="companyname" cssClass="form-control typeahead tt-query companynamevalidate left" autocomplete="off"/>
			 										<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('companyname');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<input  type="hidden" name="laborcompanyid" id="companyname_hidd_id" class="form-control"/>
        								</div></div>
											               <div class="row">
														 <div class="input-field col s6">
																<label for="card"><s:text name="Text.customerreg.idproof" /></label>
																	 <s:textfield  name="ID_CARD_TYP_Name" id="card" cssClass="form-control typeahead tt-query idproofvalidate left" autocomplete="off"/>
																     <span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<input  type="hidden" name="ID_CARD_TYP" id="cardid" class="form-control"/>
																	</div>


															 <div class="input-field col s6">
																		<label for="idproofno"><s:text name="Text.customerreg.idproofno" /></label>
																		<s:textfield name="labRegObj.ivrBnID_CARD_NO" id="idproofno"  cssClass="form-control idproofnovalidate" />
																		<div id="card-error-div" class="error manualscriptvalidation" style="display:none;"></div>
															</div></div>

															</div></div></div>
	</div>
	</li>
	<li>
	<div class="collapsible-header <s:text name="collapsible.header.color" />" > <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>
	<div class="collapsible-body padding10px" id="seconddivid" >
	<!--<div style="clear: both; height:5px;"></div> <div id="othersdetdiv" >
	<div class="imgaddplus1" onclick="myFunction2('add');"> <i class="<s:text name="button.color.addhideshow"/>" ></i>
	<div class="spacialspace">Others Detail </div></div>
	<div class="imgaddminus1" onclick="myFunction2('sub');" style="display:none; cursor: pointer;"><i class="<s:text name="button.color.minushideshow"/>" ></i>
	<div class="spacialspace">Others Detail </div></div> </div><div style="clear: both; height:5px;"></div> -->
	<div id="otherhidden" style="display:block; margin:15px;">
													      <div class="row">
													       <div class="input-field col s6">
																		<label for="address1"><s:text name="uam.profile.address1" /><span class="mandatory"></span></label>
																		<s:textfield name="labRegObj.ivrBnLBR_ADD_1" id="address1"
																			cssClass="form-control " />
																	</div>
  														  <div class="input-field col s6">
																<label for="address2"><s:text name="uam.profile.address2"></s:text><span class="mandatory"></span></label>
																<s:textfield name="labRegObj.ivrBnLBR_ADD_2" id="address2" cssClass="form-control " />
															</div>
 															 </div>
 															 <div class="row">
 															  <div class="input-field col s6">
												          <label for="country_txt_id"><s:text name="Menuheader.uam.profile.country" /></label>
														<s:textfield name="Country_id_name" id="country_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="Country_id" id="country_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
  														   <div class="input-field col s6">
												          <label for="state_txt_id"><s:text name="Menuheader.uam.profile.state" /></label>
													       <s:textfield name="State_id_name" id="state_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="State_id" id="state_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
											            </div>
 															 <div class="row">
 															 <div class="input-field col s6">
												          <label for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /></label>
													       <s:textfield name="City_id_name" id="city_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="City_id" id="city_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
 															   <div class="input-field col s6">
												          <label for="pstlcode_txt_id"><s:text name="Text.customerreg.pincode" /></label>
													        <s:textfield name="pstlcode_txt" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
<%-- 																	<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
<span class="input-group-addon txtbxdownarowicon left" ></span>
												<input type="hidden" id="pstlcode_hidd_id" name="labRegObj.ivrBnLBR_PSTL_ID" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
											            </div>
														<div class="row">
														 <div class="input-field col s6">
																<label for="keyforsearch"><s:text name="Text.company.keysearch"></s:text><span class="mandatory"></span></label>
																<s:textfield name="labRegObj.ivrBnKEY_FOR_SEARCH" id="keyforsearch" cssClass="form-control " />
															</div>
 															   <div class="input-field col s6">
												          <label for="worktype"><s:text name="Work Type" /></label>
												        <s:textfield name="labRegObj.wrktypname" id="worktype" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('worktype');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="labRegObj.wrktypId" id="worktype_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
											            </div>
											          <div class="row">
														 <div class="input-field col s6">
																<label for="cost"><s:text name="Cost"></s:text></label>
																<s:textfield name="labRegObj.ivrBnLBR_COST" id="cost" cssClass="form-control " />
														</div>
														 <div class="input-field col s6">
																<label for="experience"><s:text name="Experience" /> (Years)</label>
																<s:textfield name="labRegObj.ivrBnLBR_EXPERIENCE" id="experience" cssClass="form-control "/>
															</div>
											          </div>
											            	<div class="clear height10px"></div>

											<div class="clear" style="height:5px;"></div>
											<div class="row">

															<div class="input-field col s6">
 															 <div class="form-group">
                                            <div id="genter_select" class="row">
												          		<label for="costper" class="active"><s:text name="Cost Per" /></label>
												         		<div class="clear height15px"></div>
												          		<div>
												       				<s:radio name="labRegObj.ivrBnLBR_COSTPER" list="#{'0':'Hour','1':'Daily','2':'Monthly'}" value="0"/>
												       			</div>
											            	</div> </div>
											            </div>
															 <div class="input-field col s6">
															 <div id="genter_select" class="row">
																<label for="ststusid" class="active"><s:text name="Status"></s:text></label>
																<div class="clear height15px"></div>
																<s:radio name="option" list="#{'1':'Verified','0':'Unverified'}" value="0" id="ststusid"/>
                                                                   <!--  <input id="option" type="radio" name="labRegObj.ivrBnLBR_VERIFIED_STATUS" value="1">
                                                                    <label for="option">Verified</label>
                                                                    <input id="option1" type="radio" name="labRegObj.ivrBnLBR_VERIFIED_STATUS" value="0">
                                                                    <label for="option1">Unverified</label>  -->
															</div></div>
													</div>
													<div class="clear height25px"></div>

		<div class="row">

			<div id="skillhidden" style="display:none; margin:10px 0px 0px 25px;">
			</div>
			<div class="row padding10px">
			<div class="input-field col s6">
					<%-- <label class="active"><s:text name="Text.labor.category"></s:text></label><div class="clear height5px"></div>
						<select  id="mySelectID" class="selectpicker" data-selected-text-format="values" > </select>   --%>
					<label for="category_txt_id"><s:text name="Text.labor.category" /><span class="mandatory">*</span></label>
					<s:textfield name="category_id_name" id="category_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off" />
					<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('category_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
					<s:hidden name="categoryidkeyv" id="category_hidd_id" class="form-control"/>
					<div class="clear"></div>
					<div id="lbrcatgerrdivid" class="error manualscriptvalidation" style="display:none;">Labor category is required</div>	
			</div>
			<div class="input-field col s5">
					<label for="skills_txt_id">Skills<span class="mandatory">*</span></label>
					<s:textfield name="skills_id_name" id="skills_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off" />
					<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('skills_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
					<input type="hidden" name="skills_id" id="skills_hidd_id" class="form-control"/>
					<div class="clear"></div>
					  <div id="lbrsierrdivid" class="error manualscriptvalidation" style="display:none;">Labor skill is required</div>	
			</div>
			<div class="input-field col s1">
  					<button type="button" id="addButton1" class="<s:text name="button.color.add"/>"><i class="mdi-content-add"></i></button>
  			</div>
			</div>
			<div class="row padding10px" id="categoryskillsadddiv" style="display: none;">
				<div class="input-field col s12">
						<%-- <label for="categoryskillsadd"><s:text name="Text.company.keysearch"></s:text><span class="mandatory"></span></label> --%>
						<div id="categoryskillsadd">
						<div id="TextBoxDiv1"><div class="" style="color: #9e9e9e !important;"> <label class="active"> Category - Skills </label></div> </div>
						</div>
						<s:hidden name="categoryskill_hidd_id" id="categoryskill_hidd_id" class="form-control"/>
				</div>

			</div>

													</div>


													 <div class="row">
                                         <div class="input-field">
										<label for="tblid" class="active">Issue Type</label>
										  <div class="clear height15px"></div>
											<div id="tblid" class="scrollclass"></div>
					<!-- <table id="tblid" >
						<tr style="height: 50px;">
							<td style="width: 30%;"><div>
									<input id="address" class="myCheckbox" type="checkbox" name="issueFeatures[]"
										value="address is wrong"> <label for="address">address is wrong</label>
								</div></td>
							<td style="width: 40%;"><div >
									<input id="wrgMobile" class="myCheckbox" type="checkbox" name="issueFeatures[]"
										value="Wrong Mobile No"> <label for="wrgMobile">Wrong Mobile No</label>
								</div></td>

						</tr>
						<tr>
							<td  style="width: 30%;"><div>
									<input id="mapwrg" class="myCheckbox" type="checkbox" name="issueFeatures[]"
										value="Map Location is Wrong"> <label for="mapwrg">Map Location is Wrong</label>
								</div></td>
							<td><input id="wrgname" class="myCheckbox" type="checkbox" name="issueFeatures[]"
								value="Name is Wrong"> <label for="wrgname">Name is Wrong</label></td>
						</tr>
					</table> -->
			</div>

		</div>
		<div  class="row"><div class="input-field col s12"></div></div>
		<div class="row">
				<div class="input-field col s6">
					<label for="issuetxt"><s:text name="Issue Text" /></label>
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
		</div>
								<div class="clear height25px"></div>

										 <div class="row padding10px">
															<div class="input-field col s12">
												<label for="description"><s:text name="Description" /></label>
												<textarea name="labRegObj.ivrBnLBR_DESCP" rows="2" class="materialize-textarea"  ></textarea>
												<s:fielderror fieldName="passObj.currentpwd" />
											</div>
										</div>


		</div>

	</li>
	</ul>
						<input type="hidden" name="mySelect" id="mySelect" class="form-control " />
						<div class="row">
						<div class="input-field col s6">
						<button type="button" id="loborCreateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>
					</div>
					 <s:hidden  name="categoryskillidval" id="categoryskillid" />
					 	<input type="hidden" name="offercheck" id="issuetxtval" class="form-control" />
		<input type="hidden" name="offertxt" id="checkboxval" class="form-control" />
		</form>
		<s:form method="post" id="userCancelForm"></s:form>
		</div>
				</div>
			</section>
		</div></div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
</body>
<script>
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

		/*    var rescheck="";
			  var l=0;
			  $("input[name='issuelistbox']").each( function () {
				  if($(this). prop("checked") == true){
					  if(l==0){
						  rescheck=rescheck+$(this).val();
					  }else{
						  rescheck=rescheck+"!_!"+$(this).val();
					  }
					  }else{
						 /*  if(l==0){
							  rescheck=rescheck+$(this).val()+"=0";
							  }else{
								  rescheck=rescheck+"!_!"+$(this).val()+"=0";
							  }

					  }
				  l++;});
			  $("#checkboxval").val(rescheck);  */

	}
var counter = 2;
var flag = true;

$(document).ready(function(){
	
	$("#pstlcode_txt_id").keyup(function(){
		textValidate(this,'10','MNM');
	});
	
	$('#pstlcode_txt_id').blur(function(){
//     	alert($('#pstlcode_txt_id').val());
		$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
	});
	
	$.ajax({
		type : 'post',
		datatype : 'html',
		url : 'getIssuevalue',
		data : "activeflg=ajaxload",
		success : function(data) {
			var retval=data.trim();
			$("#tblid").html(retval);
			$( "<div class='clear height5px'></div>" ).insertBefore( $("input[name='issuelistbox']" ) );

		}
 });


	// Basic
	myFunction2('add')

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
    	$('#labourCreationFormId').validate({
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
    				  var selectedCountry = $("#mySelectID").val();
    				  $("#mySelect").val(selectedCountry);
    				  toShowLoadingImgoverlay();
    				  return true;
    			}
    		});

	$("#isdno").rules("add", {
   		required : true,
   		minlength : 1,
		maxlength : 4,
   		messages : {
   			required :"<s:text name="Error.customerreg.isd" />",
   		}
   	});
   	$("#mobilenoid").rules("add", {
   		required : true,
   		number : true,
   		minlength : 10,
		maxlength : 10,
   		messages : {
   			required : "<s:text name="Error.customerreg.mobileno.length" />",
   		}
   	});
	 $("#companyname").rules("add", {
   		required : true,
   		minlength : 2,
		maxlength : 150,
   		messages : {
   		required :"<s:text name="Error.staffreg.companyname" />",
   		}
   	});
	
   		$("#name").keyup(function(){
    		textValidate(this,'50','OA');
    	});
		$("#imgdivid").click(function(){
			$("#staffImage").click();
		});
		$("#firstname").keyup(function(){
			textValidate(this,'30','OA');
		});
		$("#emailid").keyup(function(){
			textValidate(this,'100','EML');
		});
		$("#mobilenoid").keyup(function(){
			textValidate(this,'10','PHNM');
		});
		$("#isdno").keyup(function(){
			textValidate(this,'4','PHNM');
		});
	    $("#idproofno").keyup(function(){
	    	$("#card-error-div").hide();
	    	textValidate(this,'30','EML');
	    });
	    $("#lastname").keyup(function(){
	    	textValidate(this,'30','OA');
	    });
	    $("#address1").keyup(function(){
	    	textValidate(this,'100','ADP');
	    });
	    $("#address2").keyup(function(){
	    	textValidate(this,'100','ADP');
	    });
	    $("#mobilenoid").blur(function(){
	    	toValiMobno(this.id);
	 	});
	    $("#emailid").blur(function(){
		    toValiEmail(this.id);
		});
		 $("#cost").keyup(function(){
			textValidate(this,'9','MNM');
		});
		 $("#experience").keyup(function(){
				textValidate(this,'2','NM');
	    });
		  $("#companyname").keyup(function(){
		    	textValidate(this,'100','OA');
		    });
/*


	    $("#loborCreateButtonId").click(function(){
	    	$("#firstdivid").css("display","block");
			$("#seconddivid").css("display","block");
	    	$('#labourCreationFormId').attr("Method","Post");
	    	$('#labourCreationFormId').attr("action","labourmgmtadd");
	    	$('#labourCreationFormId').attr("enctype","multipart/form-data");
	    	$('#labourCreationFormId').submit();
		});
	     */

});

function categoryskillpurpose(){
	  var targets = [];	
	  $.each($(".categoryidval"), function(){
		targets.push($(this).val());
	  });
     if(targets.length > 0){
    	 
	  	$("#categoryskillid").val(targets.join("!_!"));
	 	return true;
	}else{
	 	$("#categoryskillid").val(targets.join("!_!"));
		return false;
	}
}
var loadFile = function(event) {
	var output = document.getElementById('myImg');
	output.src = URL.createObjectURL(event.target.files[0]);
};

$(document).ready(function(){
	$.ajax({
		type : 'post',
		datatype : 'json',
		url : 'getidcardvalue',
		data : '',
		success : function(data) {
			var arr=data.split("!_!");
			//$("#multiselectdiv").html(data);
			//statelistload(arr[1]);
			idcardlistfun(arr[1]);
		}
	});

	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getcompanylistvalue',
			data : '',
			success : function(data) {
				var arr=data.split("!_!");
				companylistfun(arr[1]);
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
				searchOnFocus: true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
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
		if (typeof (objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
        }else{
        	  $('#card').val('');
        	  $('#card').focus();
	          $('#cardid').val('');
	          /*statelist
	          $('#txtSearch_1').val('');
	          $('#hiddenInputElement_1').val('');
	          $('#txtSearch_1').typeahead('destroy');
	          citylist
	          $('#txtSearch_2').val('');
	          $('#hiddenInputElement_2').val('');
	          $('#txtSearch_2').typeahead('destroy'); */
          }
      });
	}

function categoryload() {
	$.ajax({
		type : 'post',
		datatype : 'json',
		url : 'getCategoryvalue',
		data : '',
		success : function(data) {
			var arr=data.split("!_!");
			CategorylistFun(arr[1]);
		}
	});
	}

	function multiselectfun(ar) {
		var jsval = ar;
		jsval=jsval.replace(/&quot;/ig,'"');
		jsval=jsval.replace(/&amp;/ig,'&');
		var jsonData = $.parseJSON(jsval);
		var selectdiv = $('#mySelectID');
		selectdiv.append("<option value='' disabled selected>Choose your option</option>");
		$(jsonData).each(function (index, o) {
		    var optionfield = $("<option/>").attr("value", o.id).text(o.label);
		    selectdiv.append(optionfield);
		});
		$("#mySelectID").material_select();
	}


	function CategorylistFun(ar) {
		var objects_category;
	    ar=ar.replace(/&quot;/ig,'"');
	    ar=ar.replace(/%27/ig,"'");
	    ar=ar.replace(/&amp;/ig,'&');
		var loccutyval = JSON.parse(ar);
		$('#category_txt_id').typeahead({
     	order: "asc",
		hint: true,
		accent: true,
		offset: true,
		minLength : 0,
		highlight: true,
		showHintOnFocus:true,
		searchOnFocus: true,
        source: function(query, process) {
        objects_category = [];
        map = {};
        var data = loccutyval;
        $.each(data, function(i, object) {
            map[object.label] = object;
            objects_category.push(object.label);
        });
        process(objects_category);
       $(".dropdown-menu").css("height", "auto");
       $(".dropdown-menu").addClass("form-control");
    },
    updater: function(item) {

			$('#category_hidd_id').val(map[item].id);
			$('#skills_txt_id').val('');
			$('#skills_hidd_id').val('');
			$('#skills_txt_id').typeahead('destroy');
			skillsload();
			return item;
		}
	});

	$('#category_txt_id').blur(
			function() {
				if (typeof (objects_category) != "undefined" && objects_category.indexOf(this.value) != -1) {
				} else {
					$('#category_txt_id').val('');
					$('#category_txt_id').focus();
					$('#category_hidd_id').val('');
					//skilllist
					$('#skills_txt_id').val('');
					$('#skills_hidd_id').val('');
					$('#skills_txt_id').typeahead('destroy');
					// citylist

				}
			});
}

	function SkillslistFun(ar) {
		var objects_skills;
	    ar=ar.replace(/&quot;/ig,'"');
	    ar=ar.replace(/%27/ig,"'");
	    ar=ar.replace(/&amp;/ig,'&');
		var loccutyval = JSON.parse(ar);
		$('#skills_txt_id').typeahead({
     	order: "asc",
		hint: true,
		accent: true,
		offset: true,
		minLength : 0,
		highlight: true,
		showHintOnFocus:true,
		searchOnFocus: true,
        source: function(query, process) {
        objects_skills = [];
        map = {};
        var data = loccutyval;
        $.each(data, function(i, object) {
            map[object.label] = object;
            objects_skills.push(object.label);
        });
        process(objects_skills);
       $(".dropdown-menu").css("height", "auto");
       $(".dropdown-menu").addClass("form-control");
    },
    updater: function(item) {

			$('#skills_hidd_id').val(map[item].id);
			return item;
		}
	});

	$('#skills_txt_id').blur(
			function() {
				if (typeof (objects_skills) != "undefined" && objects_skills.indexOf(this.value) != -1) {
				} else {
					$('#skills_txt_id').val('');
					$('#skills_txt_id').focus();
					$('#skills_hidd_id').val('');
					//statelist
					/* $('#state_txt_id').val('');
					$('#state_hidd_id').val('');
					$('#state_txt_id').typeahead('destroy'); */
					// citylist

				}
			});
}


	function skillsload() {

		var cntryval = $("#category_hidd_id").val();

		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getSkillsvalue',
			data : 'categoryidkey=' + cntryval,
			success : function(data) {
				//alert(data)
				var arr=data.split("!_!");
				SkillslistFun(arr[1]);
			}
		});

		}
	function CountrylistFun(ar) {
			var objects_country;
		    ar=ar.replace(/&quot;/ig,'"');
		    ar=ar.replace(/%27/ig,"'");
			var loccutyval = JSON.parse(ar);
			$('#country_txt_id').typeahead({
	     	order: "asc",
			hint: true,
			accent: true,
			offset: true,
			searchOnFocus: true,
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
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
// 				$('#pstlcode_txt_id').val('');
// 				$('#pstlcode_txt_id').typeahead('destroy');
// 				$('#pstlcode_hidd_id').val('');
				onchangeStatecodeAgency();
				return item;
			}
		});

		$('#country_txt_id').blur(
				function() {
					if (typeof (objects_country) != "undefined" && objects_country.indexOf(this.value) != -1) {
					} else {
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
// 						$('#pstlcode_txt_id').val('');
// 						$('#pstlcode_txt_id').typeahead('destroy');
// 						$('#pstlcode_hidd_id').val('');
					}
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
				//alert(data)
				var arr = data.split("!_!");
				//$("#state_txt_id").html(data);
				statelistload(arr[1]);
			}
		});
	}
	function statelistload(ar) {
		var objects_state;
		ar = ar.replace(/&quot;/ig, '"');
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

				$('#city_txt_id').val('');
				$('#city_hidd_id').val('');
				$('#city_txt_id').typeahead('destroy');
// 				$('#pstlcode_txt_id').val('');
// 				$('#pstlcode_txt_id').typeahead('destroy');
// 				$('#pstlcode_hidd_id').val('');
				onchangeCitycodeAgency();
				return item;
			}
		});

		$('#state_txt_id').blur(
				function() {
					if (typeof (objects_state) != "undefined" && objects_state.indexOf(this.value) != -1) {
					} else {
						$('#state_txt_id').val('');
						$('#state_txt_id').focus();
						$('#state_hidd_id').val('');

						// citylist
						$('#city_txt_id').val('');
						$('#city_hidd_id').val('');
						$('#city_txt_id').typeahead('destroy');
// 						$('#pstlcode_txt_id').val('');
// 						$('#pstlcode_txt_id').typeahead('destroy');
// 						$('#pstlcode_hidd_id').val('');

					}
				});
	}
	//City Load
	function onchangeCitycodeAgency() {
		var statval = $("#state_hidd_id").val();
		// alert(statval);
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
		//var valjson='<s:property value="locIdStatetyp"/>';
		arr = arr.replace(/&quot;/ig, '"');
		var loc_state_val = JSON.parse(arr);
		$('#city_txt_id').typeahead({
			order : "asc",
			hint : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
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
// 				$('#pstlcode_txt_id').val('');
// 				$('#pstlcode_txt_id').typeahead('destroy');
// 				$('#pstlcode_hidd_id').val('');

// 				onchangePstlcodeAgency();
				return item;
			}
		});

		$('#city_txt_id').blur(
				function() {
					if (typeof (objects_city) != "undefined" && objects_city.indexOf(this.value) != -1) {
					} else {
						$('#city_txt_id').val('');
						$('#city_txt_id').focus();
						$('#city_hidd_id').val('');

						/*  $('#state_txt_id').val('');
						 $('#state_txt_id').typeahead('destroy');
						 $('#state_hidd_id').val(''); */

// 						$('#pstlcode_txt_id').val('');
// 						$('#pstlcode_txt_id').typeahead('destroy');
// 						$('#pstlcode_hidd_id').val('');
					}
				});
	}

	//pin code loading

	function onchangePstlcodeAgency() {
		var cityval = $("#city_hidd_id").val();
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
				// onchangeCitycodeAgency();
				return item;
			}
		});

		$('#pstlcode_txt_id').blur(
				function() {
					if (typeof (objects_pstl) != "undefined" && objects_pstl.indexOf(this.value) != -1) {
					} else {
						$('#pstlcode_txt_id').val('');
						$('#pstlcode_txt_id').focus();
					}
				});
	}

	function worktypelistfun() {
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'wrktyprajaxlst',
			data : '',
			success : function(data) {
				var arr = data.split("!_!");
				worktypelistfunauto(arr[1]);
			}
		});
	}

	function worktypelistfunauto(arr) {
		arr = arr.replace(/&quot;/ig, '"');
		var locval = JSON.parse(arr);
		var objects_worktype;
	/*	var locval = [ {
			"id" : "1",
			"label" : "CONTRACT BASED"
		}, {
			"id" : "2",
			"label" : "DAILY WAGES"
		}, {
			"id" : "3",
			"label" : "MONTHLY WAGES"
		}, {
			"id" : "4",
			"label" : "HOUR BASED"
		} ];*/
		$('#worktype').typeahead({
			order : "asc",
			hint : true,
			accent : true,
			offset : true,
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
			searchOnFocus : true,
			source : function(query, process) {
				objects_worktype = [];
				map = {};
				var data = locval;
				$.each(data, function(i, object) {
					map[object.label] = object;
					objects_worktype.push(object.label);
				});
				process(objects_worktype);
				$(".dropdown-menu").css("height", "auto");
				$(".dropdown-menu").addClass("form-control");
			},
			updater : function(item) {
				$('#worktype_hidd_id').val(map[item].id);
				return item;
			}
		});
		$('#worktype').blur(
				function() {
					if (typeof (objects_worktype) != "undefined" && objects_worktype.indexOf(this.value) != -1) {
					} else {
						$('#worktype').val('');
						$('#worktype').focus();
						$('#worktype_hidd_id').val('');
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
	var skillflg = true;
	function myFunction2(check) {
		if (check == "add") {
			if (skillflg) {
				$.ajax({
					type : 'post',
					datatype : 'json',
					url : 'getcountryvalue',
					data : '',
					success : function(data) {

						var arr = data.split("!_!");
						//$("#state_txt_id").html(data);
						CountrylistFun(arr[1]);
					}
				});
				categoryload();
				worktypelistfun();
				skillflg = false;
			}

			// $('.imgaddminus1').css("display","inline"); $('#otherhidden').show(); $('.imgaddplus1').css("display","none");
		} else if (check == "sub") {
			// $('.imgaddminus1').css("display","none"); $('.imgaddplus1').css("display","inline");  $('#otherhidden').hide();
		}
	}

	function cancelFunction() {
		$("#userCancelForm").attr("action", "labourmgmt");
		$("#userCancelForm").submit();
		$('#userCancelButtonId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass(
				'<s:text name="cardpanel.button.animated.flipoutx"/>');
	}

	$(document).ready(function(){
		
		
		$("#loborCreateButtonId").click(function(){
			
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
			
			
			 var ctegoyskill=$("#categoryskillid").val();
	 			//alert(ctegoyskill);
	 			var cteg=true;
	 			if(ctegoyskill!=""){
	 				cteg = true;
	 				$("#lbrcatgerrdivid").hide();
	 			}else{
	 				$("#lbrcatgerrdivid").show();
	 				cteg = false;
	 			}
	 			if(ctegoyskill!=""){
	 				cteg = true;
	 				$("#lbrsierrdivid").hide();
	 			}else{
	 				$("#lbrsierrdivid").show();
	 				cteg = false;
	 			}
	 			
	 			var categoryskill = categoryskillpurpose();
	 			functiontext();
	 			$("#firstdivid").css("display","block");
	 			$("#seconddivid").css("display","block");
	 			  if((categoryskill || categoryskill=="true")){		 				   			
	 				 	$('#labourCreationFormId').attr("Method","Post");
	 		    		$('#labourCreationFormId').attr("action","labourmgmtadd");
	 		    		$('#labourCreationFormId').attr("enctype","multipart/form-data");
	 		    		$('#labourCreationFormId').submit();	 	    	  	 	    	
	 			  }else{
	 				  
	 			  }
	 		});
		
		var counter = 1;
		  $("#addButton1").click(function(){

			 if($("#category_txt_id").val() !="" && $("#skills_txt_id").val() !=""){
		    	$("#categoryskillsadddiv").show();
			 }

				var cat_id=$("#category_hidd_id").val();
				var skill_id=$("#skills_hidd_id").val();


		    	if($('#'+cat_id+'_'+skill_id+'').length){
					swal("Skill Already Selected");
		    	//  Id is there

		    	}
		    	else{
		    		if($("#skills_hidd_id").val() =="")
		    			{
		    			swal("Select any one labor category and skills");
		    			}
		    		else{
		    			var categoryname =$("#category_txt_id").val();
						var skillsname =$("#skills_txt_id").val();
						var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv1' + counter);
						   /*  newTextBoxDiv.after().html('<div class="row" id="appenddiv'+counter1+'"><div class="input-field col s6"><label for="noofblocks_'+counter1+'"><s:text name="Name" /></label><input type="text" name="blockname"  id="noofblocks_'+counter1+'" class="form-control famName" /></div><div class="input-field col s1"><label for="famisd_'+counter1+'" class="active"><s:text name="Text.customerreg.isd" /></label> <s:textfield  name="famisd_'+counter1+'" id="famisd_'+counter1+'" cssClass="form-control famIsdNumber" value="%{getText(\'Text.ISD.value\')}"/></div><div class="input-field col s5" ><label  for="famMobileNo_'+counter1+'"><s:text name="Text.mobileno" /></label><s:textfield name="famMobileNo_'+counter1+'" id="famMobileNo_'+counter1+'" cssClass="form-control famMobileNo" /></div></div><div class="row" id="appenddiv1_'+counter1+'" ><div class="input-field col s6"><label for="emailid1_'+counter1+'"><s:text name="Text.emailid"/></label><s:textfield cssClass="form-control famEmailId" name="emailid1_'+counter1+'" id="emailid1_'+counter1+'" onblur="toValiEmail(\"emailid1_'+counter1+'\");" /></div><div class="col s3"><label>Member Type</label><p><input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="option" value="1" checked/> <label for="option">Relation</label>&nbsp;<input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="option1" value="2" />&nbsp;<label for="option1">Tenent</label></p></div> <div class="col s3"> <label>Profile Access </label><p><input type="checkbox" name="prfaccesschk" id="prfaccesschk1'+counter1+'" class="myCheckbox prfaccchk" value="1" /> <label for="prfaccesschk1'+counter1+'">Allow</label>&nbsp;<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="prfaccesschk'+counter1+'" value="2" checked/>&nbsp;<label for="prfaccesschk'+counter1+'">Not Allow</label></p></div></div><hr style="border-bottom:dotted" />'); */
						    newTextBoxDiv.after().html('<div class="row" id="appenddiv'+counter+'" style="margin: 10px 0 0 0px; width: 50%; float: left;cursor:pointer;">'+categoryname+' - '+skillsname+'</div><div class="divactionicon" id="'+counter+'" onclick="removeskill(this.id);"><i class="tooltipped mdi-action-highlight-remove tinysmall red-text text-darken-3" data-position="bottom" data-delay="10" data-tooltip="Close"></i></div><input type="hidden" id="'+cat_id+'_'+skill_id+'" class="categoryidval" name="categoryidname" value="'+cat_id+'_'+skill_id+'"/> <div class="clear height5px"></div>');

						    newTextBoxDiv.appendTo("#categoryskillsadd");
							counter++;
							$("#category_txt_id").val("");
							$("#skills_txt_id").val("");
							$("#lbrcatgerrdivid").hide();
							$("#lbrsierrdivid").hide();
		    		}

		    	//Id is NOT there
		    	}



		    });

	});
	function removeskill(id)
	{

		$('#appenddiv'+id+'').remove();
		$('#'+id+'').remove();
	}

	function companylistfun(arr){
		var objects_companyname;
		arr=arr.replace(/&quot;/ig,'"');
		var locval = JSON.parse(arr);

	 $('#companyname').typeahead({

		     	order: "asc",
				hint: true,
					accent: true,
					offset: true,
					minLength : 0,
					highlight: true,
					showHintOnFocus:true,
					searchOnFocus: true,
		        source: function(query, process) {
		        	objects_companyname = [];
		        map = {};
		        var data = locval;
		        $.each(data, function(i, object) {
		            map[object.label] = object;
		            objects_companyname.push(object.label);
		        });
		        process(objects_companyname);
		       $(".dropdown-menu").css("height", "auto");
		       $(".dropdown-menu").addClass("form-control");
		    },
		    updater: function(item) {
		        $('#companyname_hidd_id').val(map[item].id);
		        return item;
		    }
		});
	 $('#companyname').blur(function(){
			if (objects_companyname != undefined && objects_companyname.indexOf(this.value)!=-1) {
	        }else{
	        	  $('#companyname').val('');
	        	  $('#companyname').focus();
		          $('#companyname_hidd_id').val('');
	          }
	      });
		}
</script>
</html>
