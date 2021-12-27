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
                <h5 class="breadcrumbs-title"><s:text name="Text.labourmodify.Management"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="labourmgmt"><s:text name="Breadcrumb.manage" /></a></li>
					<li><a href="labourmgmt"><s:text name="Breadcrumb.manage.labormgmt" /></a></li>
                    <li class="active"><s:text name="Text.labourmodify.Management" /></li>
                   </ol>
              </div>
            </div>
          </div>
        </div>
 <div class="container">
 <div class="card-panel">
<form  id="laborformid" name="laborformid">
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">
										<%--     <div class="imgaddplus" onclick="myFunction1('add');" style="display:none;">  <i class="<s:text name="button.color.addhideshow"/>" ></i>
										     <div class="spacialspace">Profile Detail : </div> </div>  onclick="myFunction1('sub');"--%>
										  <!-- <div id=""> <div class="imgaddminus" ><i class="<s:text name="button.color.minushideshow"/>" ></i>
										    <div class="spacialspace">Profile Detail </div> </div>
										    </div><div style="clear: both; height:5px;"></div> -->
		<div id="profilehidden" style="display:block;">
			<div class="row">
									  <div class="col s12 m4 l3">
										   <input type="file" name="staffImage" id="staffImage" class="dropify" data-default-file="/templogo/labor/web/<s:property value="#session.laborSessID"/>/<s:property value="#session.staffProfileImage" />" />
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
												<s:textfield cssClass="form-control IsdNumber"  name="labRegObj.ivrBnLBR_ISD_CODE"
												id="isdno"></s:textfield>
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
												<s:hidden type="hidden" name="laborcompanyid" id="companyname_hidd_id" class="form-control"/>
            								</div>
														</div>
															<div class="row">
															 <div class="input-field col s6">
																<label for="card"><s:text name="Text.customerreg.idproof" /></label>
																<s:textfield  name="labRegObj.ivrBnID_CARD_TYPNAME" id="card" cssClass="form-control typeahead tt-query idproofvalidate left" autocomplete="off"/>
			 													<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="labRegObj.ivrBnID_CARD_TYP" id="cardid" class="form-control"></s:hidden>
														</div>
															 <div class="input-field col s6">
																		<label for="idproofno"><s:text name="Text.customerreg.idproofno" /></label>
																		<s:textfield name="labRegObj.ivrBnID_CARD_NO" id="idproofno"  cssClass="form-control idproofnovalidate" />
																		<div id="card-error-div" class="error manualscriptvalidation" style="display:none;"></div>
															</div>
													</div></div></div></div>
</div></li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />" onclick="myFunction2('add');"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>
<div class="collapsible-body padding10px" id="seconddivid" >
<!-- <div style="clear: both; height:5px;"></div><div id="othersdetdiv" >
<div class="imgaddplus1" onclick="myFunction2('add');"> <i class="<s:text name="button.color.addhideshow"/>" ></i>
<div class="spacialspace">Others Detail </div></div>
<div class="imgaddminus1" onclick="myFunction2('sub');" style="display:none; cursor: pointer;"><i class="<s:text name="button.color.minushideshow"/>" ></i>
<div class="spacialspace">Others Detail </div></div></div><div style="clear: both; height:5px;"></div> -->
<div id="otherhidden" style="display:block;  margin:15px;">
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
												         <s:textfield name="labRegObj.lbrcntryName" id="country_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off" />
															<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="labRegObj.ivrBnLBR_COUNTRY_ID" id="country_hidd_id" class="form-control"/>
											            </div>
  														   <div class="input-field col s6">
												          <label for="state_txt_id"><s:text name="Menuheader.uam.profile.state" /></label>
												           <s:textfield name="labRegObj.lbrstateName" id="state_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="labRegObj.ivrBnLBR_STATE_ID" id="state_hidd_id" class="form-control"/>
											            </div>
											            </div>
 															 <div class="row">
 															<div class="input-field col s6">
												          <label for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /></label>
												        <s:textfield name="labRegObj.lbrcityName" id="city_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="labRegObj.ivrBnLBR_CITY_ID" id="city_hidd_id" class="form-control"/>
											            </div>
 															   <div class="input-field col s6">
												          <label for="pstlcode_txt_id"><s:text name="Text.customerreg.pincode" /></label>
												         <s:textfield name="labRegObj.lbrpincode" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
<%-- 														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
<span class="input-group-addon txtbxdownarowicon left" ></span>
												<s:hidden name="labRegObj.ivrBnLBR_PSTL_ID" id="pstlcode_hidd_id" class="form-control"/>
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
											            </div>
											            </div>
											            <div class="row">
														 <div class="input-field col s6">
																<label for="cost"><s:text name="Text.labor.cost"></s:text></label>
																<s:textfield name="labRegObj.ivrBnLBR_COST" id="cost" cssClass="form-control " />
															</div>
															<div class="input-field col s6">
																<label for="experience"><s:text name="Experience" /> (Years)</label>
																<s:textfield name="labRegObj.ivrBnLBR_EXPERIENCE" id="experience" cssClass="form-control "/>
															</div>
											            </div>
													<div class="row">
															<%-- <div class="input-field col s6">
															  <label class="active"><s:text name="Text.labor.category"></s:text></label>
															  <div class="clear height5px"></div>
													  		<select multiple id="mySelectID" class="selectpicker" data-selected-text-format="values" > </select>
															</div> --%>
															<div class="input-field col s6">
 															 <div class="form-group">
 															 <div id="genter_select" class="row">
												          <label for="costper" class="active"><s:text name="Text.labor.costper" /></label>
												          <div class="clear height15px"></div>
												          <div>
												       	<s:radio id= "costper" name="labRegObj.ivrBnLBR_COSTPER" list="#{'0':'Hour','1':'Daily','2':'Monthly'}" />
												       </div>
											            </div> </div>
											            </div>
															 <div class="input-field col s6">
															 	<div class="form-group">
															 	<div id="genter_select" class="row">
																	<label for="ststusid" class="active"><s:text name="Status"></s:text></label>
																	<div class="clear height15px"></div>
																	<div>
																	<s:radio name="option" list="#{'1':'Verified','0':'Unverified'}" value="0" id="ststusid"/>
                                                                  	</div> </div>
                                                                </div>
                                                              </div>
													</div><div class="clear height10px"></div>

													<div class="clear height25px"></div>
													<div id="skillhidden" style="display:none; margin:10px 0px 0px 25px;">
		</div>
		<div class="row">

															 <div class="input-field col s6">
															  	<%-- <label class="active"><s:text name="Text.labor.category"></s:text></label>
															  	<div class="clear height5px"></div>
													  			<select  id="mySelectID" class="selectpicker" data-selected-text-format="values" > </select>   --%>
													  			<label for="category_txt_id"><s:text name="Text.labor.category" /><span class="mandatory">*</span></label>
														<s:textfield name="category_id_name" id="category_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('category_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<s:hidden name="categoryidkeyv" id="category_hidd_id" class="form-control"/>
														<div class="clear"></div>
					                               <div id="lbrcatgerrdivid" class="error manualscriptvalidation" style="display:none;">Labor category is required</div>	
															</div>
															<div class="input-field col s5">

															  	<label for="skills_txt_id">Skills</label>
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

		<s:if test ="CatskillsList == 0">
			<div class="row" id="categoryskillsadddiv" style="display: none;">
				<div class="input-field col s12">
					<label >Category - Skills</label>
					<s:hidden name="categoryskill_hidd_id" id="categoryskill_hidd_id" class="form-control"/>
				</div>
			</div>
		</s:if>
		<s:else>
													<div class="row" id="categoryskillsadddiv" >
														<div class="input-field col s12">
																<label >Category - Skills</label>
																<s:hidden name="categoryskill_hidd_id" id="categoryskill_hidd_id" class="form-control"/>
															</div>
											            </div>
											            <div class="row" >
											            <div class="input-field col s12"></div>
											            </div>
											            <div class="clear height25px"></div>
		</s:else>

		<s:iterator value="CatskillsList" status="catskillscount">
											<div id="TextBoxDiv1<s:property value="#catskillscount.Index+1"/>" style="margin:5px;" >
											 <div class="row cnt" >
											 <div style="margin: 5px; width: 50%; float: left;cursor:pointer;" id="appenddiv<s:property value="#catskillscount.Index+1"/>" class="row">
											 <s:property value="skillslistname"/> - <s:property value="cat_skillslistname"/></div>
											 <div class="divactionicon" id="<s:property value="#catskillscount.Index+1"/>" onclick="removeskill(this.id);"><i class="tooltipped mdi-action-highlight-remove tinysmall grey-text text-darken-3" data-position="bottom" data-delay="10" data-tooltip="Close"></i></div>
											<%--  <div onclick="removeskill(this.id);" id="<s:property value="#catskillscount.Index+1"/>">X</div> --%>
											 <input type="hidden" value="<s:property value="skillslistids"/>_<s:property value="cat_skillslistids"/>" name="categoryidname" class="categoryidval" id="<s:property value="#catskillscount.Index+1"/>_hidden">

											</div>
											</div>
		</s:iterator>
											<div id="categoryskillsadd" > <div id="TextBoxDiv"></div></div>
											<input type="hidden" id="cnt" value="<s:property value="%{CatskillsList.size()}"/>">

											<div class="clear height25px"></div>

										<div class="row"><div class="input-field">
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
		<input type="hidden" name="offerissuetxt" id="checkboxval" class="form-control" />
		<div class="clear height10px"></div>
		<div class="row rowmargin12px">
			<div class="input-field col s12">
				<label for="description"><s:text name="Description" /></label>
				<s:textarea name="labRegObj.ivrBnLBR_DESCP" rows="2" cssClass="materialize-textarea"  />
			</div>
		</div>

										</div>
</div></li>
</ul>
						<input type="hidden" name="mySelect" id="mySelect" class="form-control " />
						<s:hidden name="LaboruniqueId" id="uniqlaborIdEdit"></s:hidden>
						<s:hidden name="groupflg" id="groupflg" value="labor"></s:hidden>
						<s:hidden name="deletelaborid" id="deletelaboridEdit"></s:hidden>
						 <s:hidden  name="categoryskillidval" id="categoryskillid" />
						<div class="row">
						<div class="input-field col s12">
						<button type="button" id="laborupdateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>
					</div>
	</form><s:form method="post" id="userCancelForm"></s:form>
				</div>
				</div>
			</section>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
</body>
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


var flag = true;
$(document).ready(function() {
	
	$("#pstlcode_txt_id").keyup(function(){
		textValidate(this,'10','MNM');
	});
	
	$('#pstlcode_txt_id').blur(function(){
//     	alert($('#pstlcode_txt_id').val());
		$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
	});
	
	var mrchntId=$("#uniqlaborIdEdit").val();
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
    	$('#laborformid').validate({
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
       		messages : {
       		required :"<s:text name="Error.staffreg.companyname" />",
       		}
       	});
    	
    });
    $(document).ready(function(){
    	$("#imgdivid").click(function(){
    		$("#staffImage").click();
    	});
    	 onchangeStatecodeAgency();
    	 onchangeCitycodeAgency();
//     	 onchangePstlcodeAgency();
    	 		$("#firstname").keyup(function(){
    			textValidate(this,'30','OA');
    			});
    	 		$("#name").keyup(function(){
    		    	textValidate(this,'50','OA');
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
    		    $("#emailid").blur(function(){
    			    toValiEmail(this.id);
    			});
    		    $("#cost").keyup(function(){
    				textValidate(this,'9','MNM');
    			});
    		    $("#experience").keyup(function(){
    				textValidate(this,'2','NM');
    	   	 	});
    		    $(".companyname").keyup(function(){
    		    	textValidate(this,'100','OA');
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
    				searchOnFocus: true,
    				minLength : 0,
    				highlight: true,
    				showHintOnFocus:true,
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
    				/* 	$('#state_txt_id').val('');
    					$('#state_hidd_id').val('');
    					$('#state_txt_id').typeahead('destroy');
    					$('#city_txt_id').val('');
    					$('#city_hidd_id').val('');
    					$('#city_txt_id').typeahead('destroy');
    					$('#pstlcode_txt_id').val('');
    					$('#pstlcode_txt_id').typeahead('destroy');
    					$('#pstlcode_hidd_id').val('');
    					onchangeStatecodeAgency();*/
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

    	function idcardlistfun(ar){
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
    			if (objects_cardtype != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
    	        }else{
    	        	  $('#card').val('');
    	        	  $('#card').focus();
    		          $('#cardid').val('');
    	          }
    	      });
    		}


    	function worktypelistfun(){
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
    		/*var locval = [{"id":"1","label":"CONTRACT BASED"},{"id":"2","label":"DAILY WAGES"},{"id":"3","label":"MONTHLY WAGES"},{"id":"4","label":"HOUR BASED"}];*/
    	 $('#worktype').typeahead({

    		     	order: "asc",
    				hint: true,
    				accent: true,
    				offset: true,
    				searchOnFocus: true,
    				minLength : 0,
    				highlight: true,
    				showHintOnFocus:true,
    		        source: function(query, process) {
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
    		    updater: function(item) {
    		        $('#worktype_hidd_id').val(map[item].id);
    		        return item;
    		    }
    		});
    	 $('#worktype').blur(function(){
    			if (objects_worktype != "undefined" && objects_worktype.indexOf(this.value)!=-1) {
    	        }else{
    	        	  $('#worktype').val('');
    	        	  $('#worktype').focus();
    		          $('#worktype_hidd_id').val('');
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
//     		          $('#pstlcode_txt_id').val('');
//     	      	      $('#pstlcode_txt_id').typeahead('destroy');
//     		          $('#pstlcode_hidd_id').val('');
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
//     			          $('#pstlcode_txt_id').val('');
//     		        	  $('#pstlcode_txt_id').typeahead('destroy');
//     			          $('#pstlcode_hidd_id').val('');
    		          }
    		      });
    		}
    		function onchangeStatecodeAgency()
    		{
    			//alert("bbb::: "+$("#country_hidd_id").val());
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
    		function statelistload(ar)
    		{
    			var objects_state;
    			ar=ar.replace(/&quot;/ig,'"');
    			ar=ar.replace(/%27/ig,"'");
    			var loc_state_val = JSON.parse(ar);
    			$('#state_txt_id').typeahead({
    		     	order: "asc",
    				hint: true,
    				accent: true,
    				offset: true,
    				minLength : 0,
    				highlight: true,
    				showHintOnFocus:true,
    				searchOnFocus: true,
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
//     		          $('#pstlcode_txt_id').val('');
//     	      	  	  $('#pstlcode_txt_id').typeahead('destroy');
//     		          $('#pstlcode_hidd_id').val('');

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
//     			          $('#pstlcode_txt_id').val('');
//     		        	  $('#pstlcode_txt_id').typeahead('destroy');
//     			          $('#pstlcode_hidd_id').val('');

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
    			});}
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

//     			          $('#pstlcode_txt_id').val('');
//     			          $('#pstlcode_txt_id').typeahead('destroy');
//     			          $('#pstlcode_hidd_id').val('');
//     			        onchangePstlcodeAgency();
    			        return item;
    			    }
    				});
    			 $('#city_txt_id').blur(function(){
    					if (typeof(objects_city) != "undefined" && objects_city.indexOf(this.value)!=-1) {
    			        }else{
    			        	 $('#city_txt_id').val('');
    			        	 $('#city_txt_id').focus();
    				          $('#city_hidd_id').val('');

//     				          $('#pstlcode_txt_id').val('');
//     				          $('#pstlcode_txt_id').typeahead('destroy');
//     				          $('#pstlcode_hidd_id').val('');
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
    				});}
    			}
    			function pstlcodelistload(arr)
    			{
    			var objects_pstl;
    				arr=arr.replace(/&quot;/ig,'"');
    				arr=arr.replace(/%27/ig,"'");
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
   /*  function skillsload()
    {

    	$.ajax({
    		type : 'post',
    		datatype : 'json',
    		url : 'getSkillvalue',
    		data : '',
    		success : function(data) {

    			var arr=data.split("!_!");
    			//$("#multiselectdiv").html(data);
    			//statelistload(arr[1]);
    			multiselectfun(arr[1]);
    		}
    	});
    	} */
    function multiselectfun(ar)
    {

    	//var jsval = '[{"NAMEVAR":"Some Data 1","CODE":"1"},{"NAMEVAR":"Some Data 2","CODE":"2"}]';
    	var jsval = ar;
    	jsval=jsval.replace(/&quot;/ig,'"');
    	jsval=jsval.replace(/&amp;/ig,'&');
    	var jsonData = $.parseJSON(jsval);
    	var selectdiv = $('#mySelectID');
    	var existcat_label="<s:property value="labRegObj.skillslistname"/>";
    	var existcat_idds="<s:property value="labRegObj.skillslistids"/>";
    	var existcat_idd=existcat_idds.split(", ");
    	selectdiv.append("<option value='' disabled>Choose your option</option>");
    	//$("#mySelectID").material_select('refresh');
    	$(jsonData).each(function (index, o) {
    		var flgg=false;
    		 var optionfield = "";
    		for(var i=0;i<existcat_idd.length;i++){
    			if(o.id==existcat_idd[i]){
    				 optionfield = $("<option/>").attr("value", o.id).text(o.label).attr("checked","checked");
    				 flgg=true;
    				break;
    			}else{

    			}
    		}
    		if(flgg){

    		}else{
    			optionfield=$("<option/>").attr("value", o.id).text(o.label);
    		}

    		selectdiv.append(optionfield);
    	});
    	$("#mySelectID").material_select();
    	existcat_label=existcat_label.replace(/&quot;/ig,'"');
    	existcat_label=existcat_label.replace(/&amp;/ig,'&');
    	$(".selectpicker input.select-dropdown").val(existcat_label);
    	$("#mySelect").val(existcat_idds);
    }



    var skillflg=true;
    function myFunction2 (check) {
    	 if(check=="add"){
    		 //$('.imgaddminus1').css("display","inline"); $('#otherhidden').show(); $('.imgaddplus1').css("display","none");
    		 if(skillflg){
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
    			 skillsload();
    			 worktypelistfun();
    			// selectexistingcat();
    			 skillflg=false;
    		 }
    	 }else if(check=="sub"){
    		 //$('.imgaddminus1').css("display","none");
    		 //$('.imgaddplus1').css("display","inline");
    		 //$('#otherhidden').hide();
    	 }

    }

    function myFunction3(check){
    	 if(check=="add"){
    		 $('.imgaddminus2').css("display","inline");
    		 $('#skillhidden').show();
    		 $('.imgaddplus2').css("display","none");
    	 }else if(check=="sub"){
    		 $('.imgaddminus2').css("display","none");
    		 $('.imgaddplus2').css("display","inline");
    		 $('#skillhidden').hide();
    	 }

    }
    function selectexistingcat() {
    	var existcat_label="<s:property value="labRegObj.skillslistname"/>";
    	$('#divfirstname button').html("");
    	$('#divfirstname button').html("<span class='filter-option pull-left'>"+existcat_label+"</span>");
    }
     function cancelFunction(){
    	$("#userCancelForm").attr("action", "labourmgmt");
    	$("#userCancelForm").submit();
    	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
    	toShowLoadingImgoverlay();
    	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
    }
     function categoryskillpurpose(){
   	  var targets = [];
   	  	$.each($(".categoryidval"), function(){targets.push($(this).val());
   		   });     
        if(targets.length>0){
   	  		$("#categoryskillid").val(targets.join("!_!"));
   	 		return true;
   		}else{
   	 		$("#categoryskillid").val(targets.join("!_!"));
   			return false;
   		}
   }
     $(document).ready(function(){
    	 $("#laborupdateButtonId").click(function(){
    		 
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
 				 	$('#laborformid').attr("Method","Post");
 		    		$('#laborformid').attr("action","labourEditSubmitaction");
 		    		$('#laborformid').attr("enctype","multipart/form-data");
 		    		$('#laborformid').submit();
 			  }else{
 				  
 			  }
 		});

 		var counter =  parseInt('<s:property value="%{CatskillsList.size()}"/>') + 1;
 		  $("#addButton1").click(function(){
 			 if($("#category_txt_id").val() !="" && $("#skills_txt_id").val() !=""){
 		    	$("#categoryskillsadddiv").show();
 			 }
 				var cat_id=$("#category_hidd_id").val();
 				var skill_id=$("#skills_hidd_id").val();
 				var categoryname =$("#category_txt_id").val();
 				var skillsname =$("#skills_txt_id").val();

 				if($('#'+cat_id+'_'+skill_id+'').length){
					swal("Skill Already Selected");
		    	//  Id is there

		    	}
		    	else{
		    		if($("#skills_hidd_id").val() =="") {
		    			swal("Select any one labor category and skills");
		    			} else{
		    			var newTextBoxDiv = $(document.createElement('div')).attr("id", 'TextBoxDiv1' + counter);
		 				   /*  newTextBoxDiv.after().html('<div class="row" id="appenddiv'+counter1+'"><div class="input-field col s6"><label for="noofblocks_'+counter1+'"><s:text name="Name" /></label><input type="text" name="blockname"  id="noofblocks_'+counter1+'" class="form-control famName" /></div><div class="input-field col s1"><label for="famisd_'+counter1+'" class="active"><s:text name="Text.customerreg.isd" /></label> <s:textfield  name="famisd_'+counter1+'" id="famisd_'+counter1+'" cssClass="form-control famIsdNumber" value="%{getText(\'Text.ISD.value\')}"/></div><div class="input-field col s5" ><label  for="famMobileNo_'+counter1+'"><s:text name="Text.mobileno" /></label><s:textfield name="famMobileNo_'+counter1+'" id="famMobileNo_'+counter1+'" cssClass="form-control famMobileNo" /></div></div><div class="row" id="appenddiv1_'+counter1+'" ><div class="input-field col s6"><label for="emailid1_'+counter1+'"><s:text name="Text.emailid"/></label><s:textfield cssClass="form-control famEmailId" name="emailid1_'+counter1+'" id="emailid1_'+counter1+'" onblur="toValiEmail(\"emailid1_'+counter1+'\");" /></div><div class="col s3"><label>Member Type</label><p><input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="option" value="1" checked/> <label for="option">Relation</label>&nbsp;<input type="checkbox" name="memtypechk" class="myCheckbox memtype" id="option1" value="2" />&nbsp;<label for="option1">Tenent</label></p></div> <div class="col s3"> <label>Profile Access </label><p><input type="checkbox" name="prfaccesschk" id="prfaccesschk1'+counter1+'" class="myCheckbox prfaccchk" value="1" /> <label for="prfaccesschk1'+counter1+'">Allow</label>&nbsp;<input type="checkbox" name="prfaccesschk" class="myCheckbox prfaccchk" id="prfaccesschk'+counter1+'" value="2" checked/>&nbsp;<label for="prfaccesschk'+counter1+'">Not Allow</label></p></div></div><hr style="border-bottom:dotted" />'); */
		 				    newTextBoxDiv.after().html('<div class="row cnt"><div class="row" id="appenddiv'+counter+'" style="margin: 5px; width: 50%; float: left;cursor:pointer;">'+categoryname+' - '+skillsname+'</div><div class="divactionicon" id="'+counter+'" onclick="removeskill(this.id);"><i class="tooltipped mdi-action-highlight-remove tinysmall grey-text text-darken-3" data-position="bottom" data-delay="10" data-tooltip="Close"></i></div><input type="hidden" id="'+counter+'_hidden" class="categoryidval" name="categoryidname" value="'+cat_id+'_'+skill_id+'"/></div>');

		 				     newTextBoxDiv.appendTo("#categoryskillsadd");
		 				    $('#TextBoxDiv1'+counter+'').css("margin","5px");
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
    function removeskill(id){
 		$('#appenddiv'+id+'').remove();
 		$('#'+id+'').remove();
 		$('#'+id+'_hidden').remove();
 	}

     function companylistfun(arr){
			var objects_companyname;

			arr=arr.replace(/&quot;/ig,'"');
			arr=arr.replace(/%27/ig,"'");
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
