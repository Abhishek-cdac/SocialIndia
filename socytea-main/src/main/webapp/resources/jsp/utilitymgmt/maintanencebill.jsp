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
<title><s:text name="Text.Title"/></title>
<!-- CORE CSS-->
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
								<h5 class="breadcrumbs-title"><s:text name="Maintenance Bill" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="utititymgmt"><s:text name="Text.Utility.Management"></s:text></a></li>
							<li class="active"><s:text name="Maintenance Bill" /></li>
							<%-- <li class="active"><s:text name="Text.createdocument.Management" /></li> --%>
						</ol>
					</div>
				</div>
				</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				  <div class="card-panel">				 
					<div class="col s12 m8 l9">
						<div class="row">
									<!--  <div class="col s12">
                                  <ul id="default-tabs-justified" class="tabs tab-demo z-depth-1">
											<li class="tab col s3"><a  class="white-text cyan darken-1 waves-effect waves-light active" href="#default-tabs-justified-general" onclick="toLoadAutoComplate();">General Document</a></li>
											<li class="tab col s3"><a class="white-text pink accent-2 waves-effect waves-light" href="#default-tabs-justified-maintence" onclick="loadMaintenancediv()">Maintenance Form</a></li>
										</ul>
										</div> -->
										<!--<div class="col s12">
										
									 	<div id="default-tabs-justified-general" class="col s12 cyan lighten-5"> -->
										
										<%-- <form role="form" id="generaldocform"  method="post" action="documentsuccess" enctype="multipart/form-data">
											<div id="generaltbl" style="border: medium none; ">
											<div class="row">
										 <div class="input-field col s6">
										<label for="documenttype" class="control-label">Document Type<span class="mandatory">*</span></label>	
												<s:textfield name="documenttype" id="documenttype" cssClass="form-control typeahead tt-query documenttype" autocomplete="off" />
														<input type="hidden" name="documentMng.docTypId" id="documenttype_hidd" class="form-control"/>
									                 </div>
									        <div class="input-field col s6">
										<label for="shotdesc" class=" control-label">Short Description<span class="mandatory">*</span></label>
												<s:textfield name="documentMng.subject" id="shotdesc"  cssClass="form-control custom-control subject" rows="3" style="resize:none"></s:textfield>  
									</div>
									</div>
						<div class="row ">
										<div class="input-field col s6">								
										<label for="emailStatus" class="active"><s:text name="Email Notification"></s:text><span class="mandatory"></span></label>																 															
										<div class="clear height10px"></div>
										<div id="genter_select" class="row">
                                       <s:radio id="emailStatus" name="documentMng.emailStatus" value="1" list="#{'1':'Yes','2':'No'}"></s:radio>
                                      </div>
																		
										</div>
										 <div class="input-field col s6">
										<label for="usernames" id="token-input-usernames_id" class="">Resident</label>
										<s:textfield name="usernames" id="usernames"  cssClass="form-control" autocomplete="off" style="width:100%;"/>
										<input type="hidden"  id="usernames_hidd" class="form-control"/> 
										<s:hidden name="documentMng.docShareId" id="sharenames" value=""></s:hidden>
										 </div>
						</div>
						<div class="row"> 
							<div class="input-field col s12">
												<label for="descfile" class="control-label">Description</label>
												<textarea name="documentMng.descr" id="descfile" class="materialize-textarea" rows="3" style="resize:none"></textarea>   
							</div>
						</div>
									          <div class="row">									 
									     <div class="col-md-12" style="margin: 0 13px;">
										<div colspan="2"  id="documentfiledivid">
												<label for="documentfile" class="control-label lablenames active" style="color:#26a69a;">File<span class="mandatory">*</span></label><span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformatfiles"/>)</span>
													<input type="file" id="documentfile" name="documentfile" data-maxfilesize="<s:text name="upload.file.size" />"
							class="mrgnleft15 tooltipster documentfile" style="margin-left: 0px;" title="Choose File" multiple/>
							 <div class="clear height10px"></div>
							<div id="generalDocfile-error" class="error manualscriptvalidation" style="display: none;">Document file is required</div>
										</div>
										</div>
                                     </div></div>
                                     <div class="clear height10px"></div>
                                     <div style="clear: both; ">
 <button type="submit" id="GeneralDocCreateButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
<button type="button" id="GeneralDocCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
												<div style="clear: both;height:10px;"></div>
										</div>
										
											</form>
											</div>  
											 <div id="default-tabs-justified-maintence" class="col s12 pink lighten-5"> 
											<div style="margin-top: 10px;">--%>
											<!--  <a style="cursor:pointer;" onclick="excel('Excelcbk');"><input type="checkbox" id="Excelcbk" class="myCheckbox"  value="Excel" style="margin-left:15px;" checked/><label style="margin-left:3px; cursor:pointer;"><b>Excel Upload</b></label></a>
											<a style="cursor:pointer;" onclick="main('Maintencecbk');"><input id="Maintencecbk" type="checkbox" class="myCheckbox" value="Maintence" disabled="disabled"/><label  style="margin-left:3px; cursor:pointer;"><b>Maintence Form</b></label></a>
                                                  <a style="cursor:pointer;" onclick="image('Imagecbk');"><input type="checkbox" id="Imagecbk" class="myCheckbox" value="Image" style="margin-left:15px;" disabled="disabled"/><label  style="margin-left:3px; cursor:pointer;"><b>Image Upload</b></label></a>
                                                      </div> -->
                                                <!--   <div style="clear: both; height:30px;"></div>
	<div id="maintenceformdiv">
	<form role="form" id="maintenanceFormcreation" method="post" >
	<div class="row">
	<div class="input-field col s6">
	<label id="fbactive" for="residentnames" class=""><s:text name="Text.resident"/><span class="mandatory">*</span></label>	
	<s:textfield name="residentnames" id="residentnames" cssClass="form-control typeahead tt-query residentnames" autocomplete="off" />
	<input type="hidden"  id="residentnames_hidd" class="form-control"/> 
	<s:hidden name="documentMng.docShareId" id="mtsharenames" value=""></s:hidden>
	<div id="residentnames_hidd-error" class="error manualscriptvalidation" style="display: none;top:0rem;">Please select resident</div>
											</div> 
											<div class="input-field col s6">
												<label for="billDatedp" class="control-label">Bill Date<span class="mandatory">*</span>	</label>
                												<s:textfield name="maintanencefee.billDate" id="billDatedp" cssClass="futuredateOfBirth" onkeyup="textValidate(this,'20','DT')" />
                												<div id="billDatedp-error" class="error manualscriptvalidation" style="display: none;">Bill date is required</div> 
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="servicecharges" class="control-label">Service Charges</label>
													<s:textfield name="maintanencefee.serviceCharge" id="servicecharges" onkeyup="textValidate(this,'50','NM')" cssClass="form-control typeahead tt-query serviceCharge addnumbers" onblur="addMethod();"/>
											</div>
											<div class="input-field col s6">
												<label for="municipaltaxes" class="control-label">Municipal Taxes</label>
													<s:textfield name="maintanencefee.municipalTax" id="municipaltaxes"  onkeyup="textValidate(this,'50','NM')" cssClass="form-control typeahead tt-query municipalTax addnumbers" onblur="addMethod();"/>
																	<input type="hidden" ID="municipaltaxesid" class="form-control"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="penalties" class="control-label">Penalties/Others</label>
													<s:textfield name="maintanencefee.penalty" id="penalties" onkeyup="textValidate(this,'50','NM')" value="" cssClass="form-control typeahead tt-query penalty addnumbers" onblur="addMethod();"/>
											</div>
											<div class="input-field col s6">
												<label for="watercharges" class="control-label">Water Charges</label>
													<s:textfield name="maintanencefee.waterCharge" id="watercharges" onkeyup="textValidate(this,'50','NM')" value=""  cssClass="form-control typeahead tt-query waterCharge addnumbers" onblur="addMethod();"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
											<div class="form-group" id="groupnamedivid">
												<label for="nonoccupationcharge" class="control-label">Non Occupancy Charge</label>
													<s:textfield name="maintanencefee.nonOccupancyCharge" onkeyup="textValidate(this,'50','NM')" id="nonoccupationcharge" value="" cssClass="form-control typeahead tt-query nonOccupancyCharge addnumbers" onblur="addMethod();"/>
											</div>
											</div>
											<div class="input-field col s6">
												<label for="federationculture" class="control-label">Federation/Cultural</label>
													<s:textfield name="maintanencefee.culturalCharge" onkeyup="textValidate(this,'50','NM')" id="federationculture" value="" cssClass="form-control typeahead tt-query culturalCharge addnumbers" onblur="addMethod();"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="sinkingfund" class="control-label">Sinking Fund</label>
													<s:textfield name="maintanencefee.sinkingFund" onkeyup="textValidate(this,'50','NM')" id="sinkingfund" value=""  cssClass="form-control typeahead tt-query addnumbers" onblur="addMethod();"/>
																	<input type="hidden" ID="sinkingfundid" class="form-control"/>
											</div>
											<div class="input-field col s6">
												<label for="repairfund" class="control-label">Repair Fund</label>
													<s:textfield name="maintanencefee.repairFund" onkeyup="textValidate(this,'50','NM')" id="repairfund" value="" cssClass="form-control typeahead tt-query addnumbers" onblur="addMethod();"/>
																	<input type="hidden" id="repairfundid" class="form-control"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="insurancecharges" class="control-label">Insurance Charges</label>
													<s:textfield name="maintanencefee.insuranceCharges" onkeyup="textValidate(this,'50','NM')" id="insurancecharges" value="" cssClass="form-control typeahead tt-query addnumbers" onblur="addMethod();"/>
																	<input type="hidden" id="insurancechargesid" class="form-control"/>
											</div>
											<div class="input-field col s6">
												<label for="playzone" class="control-label">Play Zones</label>
													<s:textfield name="maintanencefee.playZoneCharge" onkeyup="textValidate(this,'50','NM')" id="playzone" value="" cssClass="form-control typeahead tt-query addnumbers" onblur="addMethod();"/>
																	<input type="hidden" id="playzoneid" class="form-control"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="majorrepairfund" class="control-label">Major Repair Fund</label>
													<s:textfield name="maintanencefee.majorRepairFund" onkeyup="textValidate(this,'50','NM')" id="majorrepairfund" value="" cssClass="form-control typeahead tt-query addnumbers" onblur="addMethod();"/>
																	<input type="hidden" ID="majorrepairfundid" class="form-control"/>
											</div>
											<div class="input-field col s6">
												<label for="interest" class="control-label">Interest</label>
													<s:textfield name="maintanencefee.interest" onkeyup="textValidate(this,'50','NM')" id="interest" value="" cssClass="form-control typeahead tt-query addnumbers" onblur="addMethod();"/>
																	<input type="hidden" ID="interestid" class="form-control"/>
											</div>
											</div>
												<div class="row">
											<div class="input-field col s6">
												<label for="total" class="control-label">Total</label>
												<s:textfield name="total" id="total" value="0" cssClass="form-control typeahead tt-query" readonly="true"/>
												<input type="hidden" id="totalid" class="form-control"/>
											</div>
										             <div class="input-field col s6">
																<label for="emailStatuss" class="active"><s:text name="Email Notification"></s:text><span class="mandatory"></span></label>																 															
																<div id="genter_select" class="row">
                                                                   <s:radio id="emailStatuss" name="documentMng.emailStatus" value="1" list="#{'1':'Yes','2':'No'}"></s:radio>
																</div>
										             </div></div><div class="clear height10px"></div><div class="clear height10px"></div>
											
											<div class="clear height10px"></div>
				<button type="button" id="userCreateButtonId" onclick="createmaintenancebyform();" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
				<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
												<div class="clear height10px"></div>
										
                                           </form>
                                           </div>
           <div id="imageformdiv" style="display: none;">
           <form role="form" id="maintenanceImageform"  method="post" enctype="multipart/form-data">
           <div class="row">
           <div class="input-field col s6">
										<div class="form-group" id="divfirstname">
																<label  for="emailStatusss" class="active"><s:text name="Email Notification"></s:text><span class="mandatory"></span></label>																 															
																<div id="genter_select" class="row">
                                                               <s:radio id="emailStatusss" name="documentMng.emailStatus" value="1" list="#{'1':'Yes','2':'No'}"></s:radio>
                                                            </div>
                               </div></div>
           </div>
           <div class="row"> <div class="input-field col s12"></div></div>
           <div class="row">
           <div class="input-field col s12">
		   <label id="imagefor" for="imgusernames" class="input-field active">Resident<span class="mandatory">*</span></label>	
			<s:textfield name="imgusernames" id="imgusernames"  cssClass="form-control typeahead tt-query usernames"  autocomplete="off" />
			<input type="hidden"  id="imgusernames_hidd" class="form-control"/>
			<s:hidden name="documentMng.docShareId" id="imgsharenames" value=""></s:hidden>
			<div class="clear height10px"></div>
		    <div id="imgusernames-error" class="error manualscriptvalidation" style="display: none;">Please Select Resident</div>
			</div>
			</div>
			<div class="row"> <div class="input-field col s12"></div></div>
			<div class="row">			             
            <div class="input-field col s12">
					<div class="form-group" id="maintimagefiledivid">
										<div colspan="2">
												<label for="maintimagefile" class="control-label active" style="color:#26a69a;">Upload Image<span class="mandatory">*</span></label>
													<input type="file" id="maintimagefile" name="maintimagefile" data-maxfilesize="<s:text name="upload.file.size" />"
							class="mrgnleft15 tooltipster maintimagefile" style="margin-left: 0px;" title="Choose File" max-uploads = 6 accept="image/*"/>
							<div id="maintimagefile-error" class="error manualscriptvalidation" style="display: none;">Image file is required</div>
										</div>
					</div>
			</div> </div>
                                           	<div style="clear: both;">
                                           	<button type="button" id="userEditButtonId" onclick="createmaintenancebyimage();" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
												<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
												<div style="clear: both;height:10px;"></div>
										</div>
                                           </form>
                                           </div>-->
                                           <div id="excelformdiv">
                                           <form role="form" id="maintenanceExcelform"  method="post" action="" enctype="multipart/form-data">
                                                    	<s:if test="(#session.sSoctyId==null || #session.sSoctyId =='' || #session.sSoctyId =='-1')">
                                                   	 <div class="col-md-12">
									<div class="input-field col s6">
									<label for="township_txt_id"><s:text name="Text.adduser.town.ship.id" /></label>
									<s:textfield id="township_txt_id" name="townshipName" data-error=".errorTxt1"  cssClass="form-control typeahead tt-query townshipIdvalidate"  autocomplete="off" />
									<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control "  />
									</div>
									<div class="input-field col s6">
									<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /></label>
									<s:textfield name="societyName" id="societyId_txt_id" cssClass="typeahead tt-query  form-control"  autocomplete="off" spellcheck="false" />
									<s:hidden id="societyId_hidd_id" name="societyId" cssClass="form-control"/>
									</div>
									  </div>   
									</s:if>	  
                                           	     <div class="col-md-12" style="padding: 0;">
											<div class="form-group" id="maintexcelfiledivid">
										<div colspan="2">
												<label for="maintexcelfile" class=" control-label lablenames active"  style="color:#26a69a;"><s:text name="Text.file"/><span class="mandatory">*</span></label>
												
							<a onclick="submitgivenform('<s:text name="ApplicationAbsolutePath"/><s:text name="Proj.path"/><s:text name="WebContent"/><s:text name="Resource.path"/>/jsp/sampleexcel/samplexlsxformat.xlsx');" href="" style="color:red"></a>
							<a class="atag" href="maintnxlxdwnld">[Sample Excel]</a>
								<div style="clear: both;">	<div style="clear: both;">
										<div style="clear: both;">	<div style="clear: both;">	<div style="clear: both;">	<div style="clear: both;">	  
							<input type="file" id="maintexcelfile"  name="maintexcelfile" data-maxfilesize="<s:text name="upload.file.size" />"
							class="mrgnleft15 tooltipster maintexcelfile" style="margin-left: 0px;" title="Choose File" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
							<div id="maintexcelfile-error" class="error manualscriptvalidation" style="display: none;">Document file is required</div>
										</div>
										</div>
										</div>
										
                                           	<div style="clear: both;">
                         <button type="button" id="userEditButtonId" onclick="createmaintenancebyexcel();" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
												<div style="clear: both;height:10px;"></div>
										</div>										
                                           </form>                                                                                     
                                           </div>                                           
											</div>
										</div>									
		<s:form method="post" id="userCancelForm"></s:form>
		 <form id="downloadFormid" method="post" action="downloadfiles">
		<s:hidden id="downloadFormval" name="fileName" value=""></s:hidden>
						</form>									
								</div>
						</div>
						</div>
						</section>
						</div></div>																
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css?sds" />
	<jsp:include page="../common/googlemap.jsp"></jsp:include>
	
	<!-- ================================================
    Scripts
    ================================================ -->
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?5454"></script>

<script type="text/javascript">
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
$(document).ready(function(){
	
	
	loadMaintenancediv();
	toLoadAutoComplate();	
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getdocumenttype',
			data : '',
			success : function(data) {
				var arr=data.split("!_!");	
				CountrylistFun(arr[1]);
				
			}
		}); 
	 if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login */
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
		
	 } 
/* 	$("#generaldocform").click(function(){
		 var idnam=togetId();
		 //alert(idnam);
		 $("#sharenames").val(idnam);
	}); */
/* 	$('.myCheckbox').click(function() {
		   $(this).siblings('input:checkbox').prop('checked', false);
		   $(this).prop('checked', true);
		}); */
	
		$('input[type=file]').drop_uploader({
	        uploader_text: 'Drop files, image to upload, or',
	        browse_text: 'Browse',
	        browse_css_class: 'button button-primary',
	        browse_css_selector: 'file_browse',
	        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
	        file_icon: '<i class="pe-7s-file"></i>',
	        time_show_errors: 5,
	        layout: 'thumbnails'
	    });  

		
}); 
function townshipdata(valjson){
	valjson=valjson.replace(/&quot;/ig,'"');
	var locval = JSON.parse(valjson);	
	var objects_cardtype;
 $('#township_txt_id').typeahead({
	     	order: "asc",
			hint: true,
				accent: true,
				offset: true,
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


}
 function getsociety(townid){
		//var valjson=onchangetownshipId(townid);
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
 
 function getsociety_auto(tempdata){
		tempdata=tempdata.replace(/&quot;/ig,'"');
		var locval = JSON.parse(tempdata);	
		var map_scty;
		 $('#societyId_txt_id').val('');        	         	 		   
	  
	$('#societyId_txt_id').typeahead('refresh');
	    $('#societyId_txt_id').typeahead('destroy');
		$('#societyId_txt_id').typeahead({
	     		order: "asc",
				hint: true,
				accent: true,
				offset: true,
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
	        return item;
	    }
	  //add dynamical validate - 2
	});	
		 $('#township_txt_id').blur(function(){				
				if (objects_cardtype.indexOf(this.value)!=-1) {					
		        }else{            
		        	  $('#township_txt_id').val('');        	         	 
		        	  $('#township_txt_id').focus();
			          $('#township_hidd_id').val('');
		          }		
		      });	
			
		
		$('#societyId_txt_id').typeahead('refresh');

		
$('#societyId_txt_id').blur(function(){				
	if (typeof(objects_scty) != "undefined" && objects_scty.indexOf(this.value)!=-1) {	
     }else{            
     	  $('#societyId_txt_id').val('');        	         	 
     	  $('#societyId_txt_id').focus();
	      $('#societyId_hidd_id').val('');
	      $('#societyId_txt_id').typeahead('destroy');
       }		
   });		
}
	function addMethod() {
		var tot = 0;
		var targets = [];
		$.each($(".addnumbers"), function() {
			targets.push($(this).val());
		});
		$("#totalid").val(targets.join(","));

		var idval = document.getElementById('totalid').value;
		var sumsplit = idval.split(',');
		for (var i = 0; i < sumsplit.length; i++) {
			if(sumsplit[i]!=''){
			tot = parseFloat(tot) + parseFloat(sumsplit[i]);
			$("#total").val('');
			$("#total").val(tot);
			}else{
			}
		}
	}

	function generalformvalidation() {
		var $fileUpload = $("#generaldocform input[type='file']");
		if (parseInt($fileUpload.get(0).files.length) > '<s:text name="max.doc.fileupload.size" />') {
			$("#generalDocfile-error").html(
					'<s:text name="Error.document.upload.size" />');
			$("#generalDocfile-error").show();
			return false;
		}

		var fileval = $("#generaldocform input[name=documentfile]").val();
		if (fileval == "") {
			$("#generalDocfile-error").html(
					'<s:text name="Error.documentfile.required" />');
			$("#generalDocfile-error").show();
			return false;
		} else {
			$("#generalDocfile-error").hide();
		}
		toShowLoadingImgoverlay();
		return true;
	}

	function cancelFunction() {
		$("#userCancelForm").attr("action", "utititymgmt");
		$("#userCancelForm").submit();
		$('#GeneralDocCancelButtonId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		$('#userCancelButtonId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass(
				'<s:text name="cardpanel.button.animated.flipoutx"/>');
	}

	function CountrylistFun(ar) {
		var objects_country;
		ar = ar.replace(/&quot;/ig, '"');
		ar = ar.replace(/%27/ig, "'");
		var loccutyval = JSON.parse(ar);
		$('#documenttype').typeahead({
			order : "asc",
			hint : true,
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
				$('#documenttype_hidd').val(map[item].id);
				return item;
			}
		});
	}
	function toLoadAutoComplate() {
		// toShowLoadingImgoverlay();
		toremoveAllFB();
		$
				.ajax({
					type : 'post',
					datatype : 'json',
					url : 'getuserdetail',
					data : '',
					success : function(data) {
						data = data.replace(/&quot;/ig, '"');
						data = data.replace(/%27/ig, "'");
						var loc_state_val = JSON.parse(data);
						$("#usernames")
								.tokenInput(
										loc_state_val,
										{
											propertyToSearch : "searchkey",
											resultsFormatter : function(item) {
												return "<li>"
														+ "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' class='circle' />"
														+ "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>"
														+ item.fName
														+ " "
														+ item.lName
														+ " "
														+ item.flatNo
														+ "<br><div  >"
														+ item.mobilNum
														+ "</div></div></div></li>"
											},
											tokenFormatter : function(item) {
												return "<li><p id='"+item.searchvalue+"'>"
														+ item.fName
														+ " "
														+ item.lName
														+ " "
														+ item.flatNo
														+ " <br/>  "
														+ item.mobilNum
														+ " </p></li>"
											},
											theme : "facebook"
										});
						$(".token-input-dropdown-facebook").css("z-index",
								"260000");
						$("#generaltbl ul.token-input-list-facebook").css(
								"width", "100%");
						$("div.token-input-dropdown-facebook").css(
								"width",
								$('#generaltbl ul.token-input-list-facebook')
										.width());
						/* $("#token-input-usernames").css("width",$('#generaltbl ul.token-input-list-facebook').width()); */
						$("#token-input-usernames_id").css(
								"width",
								$('#generaltbl ul.token-input-list-facebook')
										.width());
						$("#generaltbl ul.token-input-list-facebook").css(
								"width",
								$('#generaltbl ul.token-input-list-facebook')
										.width());
						$("#generaltbl ul.token-input-list-facebook").css(
								"min-height", "60px");
						$("#generaltbl ul.token-input-list-facebook").css(
								"max-height", "120px");
						$("#generaltbl ul.token-input-list-facebook").css(
								"height", "auto");
						$("#generaltbl ul.token-input-list-facebook").css(
								"overflow-y", "auto");
						$("#generaltbl ul.token-input-list-facebook").css(
								"border-color", "#C4C4C4");
						$("#generaltbl ul.token-input-list-facebook").addClass(
								"form-control");
						$("#generaltbl ul.token-input-list-facebook").css(
								"margin-top", "30px");
						$("#token-input-usernames_id").addClass("active");
						tohideLoadingImgoverlay();
					},
					error : function(data) {
						tohideLoadingImgoverlay();
					}
				});
		$("#token-input-usernames_id").addClass("active");
	}
	function toremoveAllFB() {
		$(".token-input-token-facebook").each(function(x) {// new autocomplete
			$(this).remove();
		});
		$(".token-input-dropdown-facebook").each(function(x) {
			$(this).remove();
		});
		$('.token-input-dropdown-facebook').remove();
		$('.token-input-list-facebook').remove();
	}
	function togetId() {
		var idname = "";
		$(".token-input-token-facebook p").each(function(x) {// new autocomplete
			idname += $(this).attr("id") + ",";
		});
		return idname;
	}

	function generaldocumentclick() {
		var idnam = togetId();
		//alert(idnam);
		$("#sharenames").val(idnam);
		$("#generaldocform").attr("action", "documentsuccess");
		$("#generaldocform").submit();
	}
	function mttogetId() {
		var idname = "";
		$("#maintenanceFormcreation .token-input-token-facebook p").each(
				function(x) {// new autocomplete
					idname += $(this).attr("id") + ",";
				});
		return idname;
	}

	function createmaintenancebyform() {
		var idnam = mttogetId();
		$("#mtsharenames").val(idnam);
		var dpval = $("#billDatedp").val();
		var flg = 1;
		if (idnam == "") {
			$("#residentnames_hidd-error")
					.html(
							'<s:text name="Error.maintenance.residentnames.require" />');
			$("#residentnames_hidd-error").show();
			flg = 0;
		} else {
			$("#residentnames_hidd-error").hide();
		}

		if (dpval == "") {
			$("#billDatedp-error").html(
					'<s:text name="Error.maintenance.billdate.require" />');
			$("#billDatedp-error").show();
			flg = 0;
		} else {
			$("#billDatedp-error").hide();

		}
		if (flg == 1) {
			toShowLoadingImgoverlay();
			$("#maintenanceFormcreation").attr("action","maintanencedocumentformsubmit");
			$("#maintenanceFormcreation").submit();
		} else {
			return false;
		}
	}

	/* function main(th) {
		toShowLoadingImgoverlay();
		$("#maintenceformdiv").show();
		$("#imageformdiv").hide();
		$("#excelformdiv").hide();
		$(".myCheckbox").prop('checked', false);
		$("#" + th).prop('checked', true);
		loadMaintenancediv();

	}
	function image(th) {
		toShowLoadingImgoverlay();
		$("#imageformdiv").show();
		$("#excelformdiv").hide();
		$("#maintenceformdiv").hide();
		$(".myCheckbox").prop('checked', false);
		$("#" + th).prop('checked', true);
		loadMaintenancediv();
	}
	function excel(th) {
		toShowLoadingImgoverlay();
		$("#excelformdiv").show();
		$("#imageformdiv").hide();
		$("#maintenceformdiv").hide();
		$(".myCheckbox").prop('checked', false);
		$("#" + th).prop('checked', true);
		loadMaintenancediv();
		tohideLoadingImgoverlay();

	} */
	function createmaintenancebyexcel() {

		var val = $(".maintexcelfile").val();
		if (val.length > 0) {
			$("#dialogoverlay_img").show();
			$("#uploadimg_clorbx").show();
			$("#maintenanceExcelform").attr("action","maintanencedocumentexcelsubmit");
			$("#maintenanceExcelform").submit();
		} else {
			$("#maintexcelfile-error").show();
		}
	}
	function imgtogetId() {
		var idname = "";
		$("#maintenanceImageform .token-input-token-facebook p").each(
				function(x) {// new autocomplete
					idname += $(this).attr("id") + ",";
				});
		return idname;
	}
	function createmaintenancebyimage() {
		$("#maintimagefiledivid").removeClass("has-error");
		$("#maintimagefiledivid .help-block").hide();
		$("#imgusernamesdivid").removeClass("has-error");
		$("#imgusernamesdivid .help-block").hide();
		var idnam = imgtogetId();
		$("#imgsharenames").val(idnam);

		var val = $(".maintimagefile").val();
		if (val.length > 0 && idnam.length > 0) {
			$("#dialogoverlay_img").show();
			$("#uploadimg_clorbx").show();
			$("#maintenanceImageform").attr("action",
					"maintanencedocumentimagesubmit");
			$("#maintenanceImageform").submit();
		} else {
			if (val.length == 0) {
				$("#maintimagefile-error").show();
			}
			if (idnam.length == 0) {
				$("#imgusernames-error").show();

			}
		}
	}
	function loadMaintenancediv() {
		toremoveAllFB();
		if ($("#Maintencecbk").is(':checked')) {
			loadMaintenanceByform();
		} else if ($("#Imagecbk").is(':checked')) {
			loadMaintenanceByImage();
		} else {

		}
	}
	function loadMaintenanceByform() {
		toremoveAllFB();
		$
				.ajax({
					type : 'post',
					datatype : 'json',
					url : 'getuserdetail',
					data : '',
					success : function(data) {
						data = data.replace(/&quot;/ig, '"');
						data = data.replace(/%27/ig, "'");
						var loc_state_val = JSON.parse(data);
						$("#residentnames")
								.tokenInput(
										loc_state_val,
										{
											propertyToSearch : "searchkey",
											resultsFormatter : function(item) {
												return "<li>"
														+ "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />"
														+ "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>"
														+ item.fName
														+ " "
														+ item.lName
														+ " "
														+ item.flatNo
														+ "<br><div  >"
														+ item.mobilNum
														+ "</div></div></div></li>"
											},
											//tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
											tokenFormatter : function(item) {
												return "<li><p id='"+item.searchvalue+"'>"
														+ item.fName
														+ " "
														+ item.lName
														+ " "
														+ item.flatNo
														+ " <br/>  "
														+ item.mobilNum
														+ " </p></li>"
											},
											theme : "facebook"
										});

						$(".token-input-dropdown-facebook").css("z-index",
								"260000");
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css("width", "100%");
						$("div.token-input-dropdown-facebook")
								.css(
										"width",
										$(
												'#maintenceformdiv ul.token-input-list-facebook')
												.width());
						$("#token-input-residentnames")
								.css(
										"width",
										$(
												'#maintenceformdiv ul.token-input-list-facebook')
												.width());
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css(
										"width",
										$(
												'#maintenceformdiv ul.token-input-list-facebook')
												.width());
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css("min-height", "60px");
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css("max-height", "120px");
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css("height", "auto");
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css("overflow-y", "auto");
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css("border-color", "#C4C4C4");
						$("#maintenceformdiv ul.token-input-list-facebook")
								.addClass("form-control");
						$("#maintenceformdiv ul.token-input-list-facebook")
								.css("margin-top", "30px");
						$("#fbactive").addClass("active");
						tohideLoadingImgoverlay();
					},
					error : function(data) {
						tohideLoadingImgoverlay();
					}
				});
	}

	function loadMaintenanceByImage() {
		toremoveAllFB();
		$
				.ajax({
					type : 'post',
					datatype : 'json',
					url : 'getuserdetail',
					data : '',
					success : function(data) {
						data = data.replace(/&quot;/ig, '"');
						data = data.replace(/%27/ig, "'");
						var loc_state_val = JSON.parse(data);
						$("#imgusernames")
								.tokenInput(
										loc_state_val,
										{
											propertyToSearch : "searchkey",
											resultsFormatter : function(item) {
												return "<li>"
														+ "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />"
														+ "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>"
														+ item.fName
														+ " "
														+ item.lName
														+ " "
														+ item.flatNo
														+ "<br><div  >"
														+ item.mobilNum
														+ "</div></div></div></li>"
											},
											// tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
											tokenFormatter : function(item) {
												return "<li><p id='"+item.searchvalue+"'>"
														+ item.fName
														+ " "
														+ item.lName
														+ " "
														+ item.flatNo
														+ " <br/>  "
														+ item.mobilNum
														+ " </p></li>"
											},
											theme : "facebook"
										});

						$(".token-input-dropdown-facebook").css("z-index",
								"260000");
						$("#imageformdiv ul.token-input-list-facebook").css(
								"width", "100%");
						$("div.token-input-dropdown-facebook").css("width",
								"100%");
						$("#token-input-imgusernames").css(
								"width",
								$('#imageformdiv ul.token-input-list-facebook')
										.width());
						$("#imageformdiv ul.token-input-list-facebook").css(
								"width",
								$('#imageformdiv ul.token-input-list-facebook')
										.width());
						$("#imageformdiv ul.token-input-list-facebook").css(
								"min-height", "60px");
						$("#imageformdiv ul.token-input-list-facebook").css(
								"max-height", "120px");
						$("#imageformdiv ul.token-input-list-facebook").css(
								"height", "auto");
						$("#imageformdiv ul.token-input-list-facebook").css(
								"overflow-y", "auto");
						$("#imageformdiv ul.token-input-list-facebook").css(
								"border-color", "#C4C4C4");
						$("#imageformdiv ul.token-input-list-facebook")
								.addClass("form-control");
						$("#imageformdiv ul.token-input-list-facebook").css(
								"margin-top", "30px");
						$("#imagefor").addClass("active");
						tohideLoadingImgoverlay();
					},
					error : function(data) {
						tohideLoadingImgoverlay();
					}
				});
		$("#imagefor").addClass("active");
	}
	function submitgivenform(subval) {
		$("#downloadFormval").val(subval);
		$("#downloadFormid").submit();

	}
</script>
 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>


</html>											