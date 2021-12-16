<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<style type="text/css">
.fileUpload {position: relative;overflow: hidden;margin-top: 10px;margin-left: -15px;}
.fileUpload input.upload {position: absolute;top: 0;right: 0;margin: 0;padding: 0;font-size: 20px;cursor: pointer;opacity: 0;filter: alpha(opacity = 0);}
#generaltbl tr td {border: none;width: 50%;}
</style>  
<div class="container">
<div id="jqueryvalidation" class="section">
<div class="card-panel">
<s:if test="documentmng.docSubFolder==2">
	<div id="default-tabs-justified-general" class="tab-pane active">
	<form role="form" id="generaldocform" method="post"
		action="editdocumentaction" enctype="multipart/form-data">
		<div id="generaltbl" style="border: medium none;">
		 <div class="col-md-12">
		 <div class="row">
											<div class="input-field col s6">
												<label for="eventetitle" class=" control-label"><s:text name="Text.DocType"/><span class="mandatory">*</span></label>
												<s:textfield name="documenttype" id="documenttype" cssClass="form-control typeahead tt-query documenttype left" autocomplete="off" value="%{documentmng.docTypName}"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('documenttype');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
						<s:hidden name="documentMng.docTypId"  id="documenttype_hidd" class="form-control"  value="%{documentmng.docTypId}"/>
											</div>
											<div class="input-field col s6">
												<label for="eventshortdesc" class=" control-label"><s:text name="Text.Utility.Management.eventshort"/><span class="mandatory">*</span></label>
												<s:textfield name="documentMng.subject"
							cssClass="form-control custom-control subject" rows="3"
							style="resize:none" value="%{documentmng.subject}"></s:textfield>
											</div>
																						
											</div>	
					<div class="row">
						<div class="input-field col s6">
								<div style="margin-top:20px;"> </div>
								<label for="emailNotification" class="active"><s:text name="Email Notification"/><span class="mandatory">*</span></label>
								<div class="clear height10px"></div>
								<div id="genter_select" class="row">
                                 <s:radio name="documentMng.emailStatus" id="emailStatus" value="%{documentmng.emailStatus}" list="#{'1':'Yes','2':'No'}" ></s:radio>
                                     </div>
						</div>
						<div class="input-field col s6">
							<label for="edusernames"id="editresident" class="control-label active"><s:text name="Text.resident" /></label>
							<s:textfield name="usernames" id="edusernames" cssClass="form-control typeahead tt-query" autocomplete="off" />
							<input type="hidden" id="edusernames_hidd" class="form-control" />
							<s:hidden name="documentMng.docShareId" id="edsharenames" value=""></s:hidden>
						</div>																															
					</div>	
					<div class="clear height10px"></div>
					<div class="row">
					<div class="input-field col s12">
						<label for="generalDocDesciption" id="generaldoc" ><s:text name="Text.desc"/></label>
						<s:textarea name="documentMng.descr" id="generalDocDesciption" cssClass="materialize-textarea validate" style="resize: none" value="%{documentmng.descr}"></s:textarea>
					</div>								
					</div>	
					<div class="row">
								<div class="input-field col s12">
									<label for="documentfile" class="active"><s:text name="File" /></label><span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformatfiles"/>)</span>
									<input type="file" id="documentfile" name="documentfile" data-maxfilesize="<s:text name="upload.file.size" />"
							class="mrgnleft15 tooltipster documentfile" style="margin-left: 0px;" title="Choose File" accept="image/*"/>
							   <div id="documentfile-error" class="error manualscriptvalidation" style="display: none;">Actual price is required</div>
								</div>

							</div>	
		 </div>

		</div>
		<div style="clear: both;">
		<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.update" /><i class="mdi-content-send right"></i></button>
						<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="mdi-content-reply left"></i></button> 
			<s:hidden name="documentMng.documentId" value="%{documentmng.documentId}"></s:hidden>
		</div>
	</form>
</div>
</s:if>
<s:elseif test="maintanencefee.dataUploadFrom==3">
<div id="maintenanceformimageeditdiv">
<form role="form" id="maintenanceImageformedit" action="maintenanceImageformupdateedit"  method="post" enctype="multipart/form-data">
                                            <div class="row">
										 <div class="input-field col m6">
										<div class="form-group" id="imgusernamesdivid">
										
										<label class="control-label"><s:text name="Text.resident"/></label>	
										<s:if test="dcShare.documentShareFName!=null && dcShare.documentShareFName.length()>0">
										<s:textfield disabled="true" cssClass="form-control typeahead tt-query" value="%{dcShare.documentShareFName}"></s:textfield>
										</s:if>
										<s:elseif test="dcShare.documentShareLName!=null && dcShare.documentShareLName.length()>0">
										<s:textfield disabled="true" cssClass="form-control typeahead tt-query" value="%{dcShare.documentShareLName}"></s:textfield>
										</s:elseif>
										<s:elseif test="dcShare.documentShareEmail!=null && dcShare.documentShareEmail.length()>0">
										<s:textfield disabled="true" cssClass="form-control typeahead tt-query" value="%{dcShare.documentShareEmail}"></s:textfield>
										</s:elseif>
										             </div>
										             </div>
										             <div class="input-field col m6">
										<div class="form-group" id="divfirstname">
																<label for="emailStatus" class="active" ><s:text name="Email Notification"></s:text><span class="mandatory"></span></label>																 															
																<div id="genter_select" class="row">
                                                      <s:radio name="documentMng.emailStatus" id="emailStatus" value="%{documentmng.emailStatus}" list="#{'1':'Yes','2':'No'}" ></s:radio>
                                                       </div>
																
																														
															</div>
										</div>
										             </div>
										             
          <div class="input-field col s12">
			<div class="form-group" id="maintimagefiledivid">
										
												<label for="maintimagefile" class="control-label active"><s:text name="Text.image.upload"/></label>
													<input type="file" id="maintimagefile" name="maintimagefile" data-maxfilesize="<s:text name="upload.file.size" />"
							class="mrgnleft15 tooltipster maintimagefile" style="margin-left: 0px;" title="Choose File" accept="image/*"/>
							<small class="help-block" style="display: none;" data-bv-validator="notEmpty" data-bv-for="maintimagefile" data-bv-result="INVALID">Please upload a file</small>										
		</div>
		</div>
                                           	<div style="clear: both;">
											<button type="submit"  class="btn btn-primary"><s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
											<button type="button" id="geneditcancelbtn" class="btn btn-primary" onclick="cancelFunction();"><s:text name="Text.button.cancel" /> <i class="<s:text name="button.icon.replycard"/>"></i></button> 
											
											<s:hidden name="maintanencefee.maintenanceId" value="%{maintanencefee.maintenanceId}"></s:hidden>
										<s:hidden name="documentMng.documentId" value="%{documentmng.documentId}"></s:hidden>
										</div>
                                           </form>

</div>
</s:elseif>

<s:else >
<div id="maintenceformdiv">
<form role="form" id="maintenanceFormedit" method="post" action="maintanencedocumentformedit">
											<div class="row">
										 <div class="input-field col m6">
											<div class="form-group" id="residentnamesdivid">
										<label  class="control-label"><s:text name="Text.resident"/><span class="mandatory">*</span></label>
												
										<s:if test="dcShare.documentShareFName!=null && dcShare.documentShareFName.length()>0">
											<s:textfield disabled="" cssClass="form-control typeahead tt-query" value="%{dcShare.documentShareFName}" readonly="true"></s:textfield>
										</s:if>
										<s:elseif test="dcShare.documentShareLName!=null && dcShare.documentShareLName.length()>0">
											<s:textfield disabled="true" cssClass="form-control typeahead tt-query" value="%{dcShare.documentShareLName}" readonly="true"></s:textfield>
										</s:elseif>
										<s:elseif test="dcShare.documentShareEmail!=null && dcShare.documentShareEmail.length()>0">
											<s:textfield disabled="true" cssClass="form-control typeahead tt-query" value="%{dcShare.documentShareEmail}" readonly="true"></s:textfield>
										</s:elseif>
											</div>
											</div>
											<div class="input-field col m6">
												<label for="billDatedp" class="control-label"><s:text name="Text.billdate"/><span class="mandatory">*</span></label><!-- futuredateOfBirth -->
                								<s:textfield name="maintanencefee.billDate" id="billDatedp" cssClass="" value="%{maintanencefee.billDate}" onkeyup="textValidate(this,'20','DT')" readonly="true"/> 
											</div>
											</div>
											<div class="row">
											<div class="input-field col m6">
												<label for="servicecharges" class="control-label"><s:text name="Text.serviceCharges"/></label>
													<s:textfield name="maintanencefee.serviceCharge" id="servicecharges" value="%{maintanencefee.serviceCharge}" onkeyup="textValidate(this,'50','NM')"  onkeypress="addMethodss();" cssClass="form-control typeahead tt-query serviceCharge addnumbers" />
											</div>
											<div class="input-field col m6">
												<label for="municipaltaxes" class="control-label"><s:text name="Text.municipalTax"/></label>
													<s:textfield name="maintanencefee.municipalTax" id="municipaltaxes" value="%{maintanencefee.municipalTax}"  onkeyup="textValidate(this,'50','NM')" onblur="addMethod();"  cssClass="form-control typeahead tt-query municipalTax addnumbers" />
																	<input type="hidden" ID="municipaltaxesid" class="form-control"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col m6">
												<label for="penalties" class="control-label"><s:text name="Text.penalti.others"/></label>
													<s:textfield name="maintanencefee.penalty" id="penalties" onkeyup="textValidate(this,'50','NM')" value="%{maintanencefee.penalty}" onblur="addMethod();" cssClass="form-control typeahead tt-query penalty addnumbers" />
											</div>
											<div class="input-field col m6">
												<label for="watercharges" class="control-label"><s:text name="Text.water.charges"/></label>
													<s:textfield name="maintanencefee.waterCharge" id="watercharges" onkeyup="textValidate(this,'50','NM')" value="%{maintanencefee.waterCharge}" onblur="addMethod();" cssClass="form-control typeahead tt-query waterCharge addnumbers" />
											</div>
											</div>
											<div class="row">
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="nonoccupationcharge" class="control-label"><s:text name="Text.nonoccupancy.charges"/></label>
													<s:textfield name="maintanencefee.nonOccupancyCharge" onkeyup="textValidate(this,'50','NM')" id="nonoccupationcharge" value="%{maintanencefee.nonOccupancyCharge}" onblur="addMethod();" cssClass="form-control typeahead tt-query nonOccupancyCharge addnumbers" />
											</div>
											</div>
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="federationculture" class="control-label"><s:text name="Text.federation.culture"/></label>
													<s:textfield name="maintanencefee.culturalCharge" onkeyup="textValidate(this,'50','NM')" id="federationculture" value="%{maintanencefee.culturalCharge}" onblur="addMethod();"  cssClass="form-control typeahead tt-query culturalCharge addnumbers" />
											</div>
											</div>
											</div>
											<div class="row">
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="sinkingfund" class="control-label"><s:text name="Text.srink.fund"/></label>
													<s:textfield name="maintanencefee.sinkingFund" onkeyup="textValidate(this,'50','NM')" id="sinkingfund" value="%{maintanencefee.sinkingFund}" onblur="addMethod();" cssClass="form-control typeahead tt-query addnumbers" />
																	<input type="hidden" ID="sinkingfundid" class="form-control"/>
											</div>
											</div>
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="repairfund" class="control-label"><s:text name="Text.repair.fund"/></label>
													<s:textfield name="maintanencefee.repairFund" onkeyup="textValidate(this,'50','NM')" id="repairfund" value="%{maintanencefee.repairFund}" onblur="addMethod();" cssClass="form-control typeahead tt-query addnumbers" />
																	<input type="hidden" id="repairfundid" class="form-control"/>
											</div>
											</div>
											</div>
											<div class="row">
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="insurancecharges" class="control-label"><s:text name="Text.insurance.charges"/></label>
													<s:textfield name="maintanencefee.insuranceCharges" onkeyup="textValidate(this,'50','NM')" id="insurancecharges" value="%{maintanencefee.insuranceCharges}" onblur="addMethod();" cssClass="form-control typeahead tt-query addnumbers" />
																	<input type="hidden" id="insurancechargesid" class="form-control"/>
											</div>
											</div>
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="playzone" class="control-label"><s:text name="Text.playZones"/></label>
													<s:textfield name="maintanencefee.playZoneCharge" onkeyup="textValidate(this,'50','NM')" id="playzone" value="%{maintanencefee.playZoneCharge}" onblur="addMethod();" cssClass="form-control typeahead tt-query addnumbers"/>
													<input type="hidden" id="playzoneid" class="form-control"/>
											</div>
											</div>
											</div>
											<div class="row">
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="majorrepairfund" class="control-label"><s:text name="Text.majorRepair.Fund"/></label>
													<s:textfield name="maintanencefee.majorRepairFund" onkeyup="textValidate(this,'50','NM')" id="majorrepairfund" value="%{maintanencefee.majorRepairFund}" onblur="addMethod();" cssClass="form-control typeahead tt-query addnumbers" />
																	<input type="hidden" ID="majorrepairfundid" class="form-control"/>
											</div>
											</div>
											<div class="input-field col m6">
											<div class="form-group" id="groupnamedivid">
												<label for="interest" class="control-label"><s:text name="Text.interest"/></label>
													<s:textfield name="maintanencefee.interest" onkeyup="textValidate(this,'50','NM')" id="interest" value="%{maintanencefee.interest}" onblur="addMethod();" cssClass="form-control typeahead tt-query addnumbers" />
																	<input type="hidden" ID="interestid" class="form-control"/>
											</div>
											</div>
											</div>
													 <div class="row">
							<div class="input-field col s6">
								<label for="total" class="control-label">Total</label>
								<s:textfield name="total" id="total" readonly="true" cssClass="form-control typeahead tt-query" />
								<input type="hidden" id="totalid" class="form-control"/>
							</div>
							<div class="input-field col m6">
										<div class="form-group" id="divfirstname">
																<label for="emailStatus" class="active"><s:text name="Email Notification"></s:text><span class="mandatory"></span></label>																 															
																<div id="genter_select" class="row">
                                                           <s:radio name="documentMng.emailStatus" id="emailStatus" value="%{documentmng.emailStatus}" list="#{'1':'Yes','2':'No'}" ></s:radio>
                                                          </div>
															</div>
										</div>
										             </div>
											
											<div class="row margin">
											<button type="submit" id="maintenanceEditsubmitId" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.update" /> <i class="<s:text name="button.icon.submitcard"/>"></i></button>
											<button type="button" id="maintenanceEditcancelId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
										</div>
										<s:hidden name="maintanencefee.maintenanceId" value="%{maintanencefee.maintenanceId}"></s:hidden>
										<s:hidden name="documentMng.documentId" value="%{documentmng.documentId}"></s:hidden>
     </form>
     </div>	
</s:else>

</div>
</div>
</div>
	<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
	<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
	<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />

	<form id="cancelFunctionFormEditdoc"></form>	
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?24324342"></script>
<script type="text/javascript">
$(document).ready(function(){			
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getdocumenttype',
			data : '',
			success : function(data) {
				var arr=data.split("!_!");					
				edCountrylistFun(arr[1]);
			}
		}); 
	 edtoLoadAutoComplate();
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
	    $("#maintenanceEditsubmitId").click(function () {
	    	
		  	 var $fileUpload = $("#generaldocform input[type='file']");
		  	var files = $('#generaldocform :input[type=file]').get(0).files;
		  	var totsize=0;
		  	var maxsize=5*1024*1024;
		  	for (i = 0; i < files.length; i++) {
		  		totsize=totsize+files[i].size;
		  		
		  	}
		  	if(totsize>maxsize){
		  	 $("#documentfile-error").html('<s:text name="Text.filesize" />');
		 		$("#documentfile-error").show();
		       return false;
		  	}
		  	
		});
		$('#generaldocform').validate({
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
		 		  var idnam=togetId();
					 $("#edsharenames").val(idnam);
					 toShowLoadingImgoverlay();
				return true;
				//toShowLoadingImgoverlay();
			}
		});
	 	$("#documentMng_subject").rules("add", {
	 		required : true,
			minlength:2,
			maxlength:150,
			messages : {
				required : "<s:text name="Error.documenttype.shrt.des.required"/>",
			}
		});
	 	$("#documenttype").rules("add", {
			required : true,
			messages : {
				required : "Document Type Is Required",
			}
		});
	 	
		
	 	
		$('#maintenanceFormedit').validate({
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
				var idnam=mtedittogetId();
	     		 $("#mteditsharenames").val(idnam);
	     		toShowLoadingImgoverlay();
				return true;
				//toShowLoadingImgoverlay();
			}
		});
		$("#billDatedp").rules("add", {
			required : true,
			messages : {
				required : "<s:text name="Error.forum.date" />",
			}
		});
		
		
		
		$('#maintenanceImageformupdateedit').validate({
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
				 var idnam=mtimgedittogetId();
	     		 $("#imgsharenames").val(idnam);
	     		toShowLoadingImgoverlay();
				return true;
				//toShowLoadingImgoverlay();
			}
		});
		
});

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
			tot = parseFloat(tot) + parseFloat(sumsplit[i]);
			$("#total").val('');
			$("#total").val(tot);
		}
	}

	function edCountrylistFun(ar) {
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
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
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
		 $('#documenttype').blur(function(){				
				if (objects_country != undefined && objects_country.indexOf(this.value)!=-1) {			
		        }else{            
		        	  $('#documenttype').val('');        	         	 
		        	  $('#documenttype').focus();
			          $('#documenttype_hidd').val('');	          
		          }		
		      });
	}

	function edtoLoadAutoComplate() {
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

						$("#edusernames")
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
											tokenFormatter : function(item) {
												return "<li><p id='"+item.searchvalue+"'>"
														+ item.fName
														+ " "
														+ item.lName
														+ " "
														+ item.flatNo
														+ " <br/>  "
														+ item.mobilNum
														+ "</p></li>"
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
						$("#editresident").addClass("active");
					}
				});

	}
	function toremoveAllFB() {
		$("#shareid .token-input-token-facebook").each(function(x) {// new autocomplete
			$(this).remove();
		});
		$('.token-input-dropdown-facebook').remove();
		$('#shareid .token-input-list-facebook').remove();
	}
	function togetId() {
		toShowLoadingImgoverlay();
		var idname = "";
		$("#generaldocform .token-input-token-facebook p").each(function(x) {// new autocomplete
			idname += $(this).attr("id") + ",";
		});
		return idname;
	}

	function mtedittogetId() {
		toShowLoadingImgoverlay();
		var idname = "";
		$("#maintenanceFormedit .token-input-token-facebook p").each(
				function(x) {// new autocomplete
					idname += $(this).attr("id") + ",";
				});
		return idname;
	}
	function mtimgedittogetId() {
		toShowLoadingImgoverlay();
		var idname = "";
		$("#maintenanceImageformedit .token-input-token-facebook p").each(
				function(x) {// new autocomplete
					idname += $(this).attr("id") + ",";
				});
		return idname;
	}
	function cancelFunction() {
		$('#maintenanceEditcancelId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		$('#geneditcancelbtn').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');

		toShowLoadingImgoverlay();
		$(".card-panel").addClass(
				'<s:text name="cardpanel.button.animated.flipoutx"/>');
		$("#cancelFunctionFormEditdoc").attr("action", "utititymgmt");
		$("#cancelFunctionFormEditdoc").submit();

	}
</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>