<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<style type="text/css">
#generaltbl tr td {border: none;width: 50%;
}
</style>
<div class="container">
<div id="jqueryvalidation" class="section">
<div class="card-panel">
<s:if test="documentmng.docSubFolder==1">
	<s:if test="maintanencefee.dataUploadFrom==3">
		<div id="default-tabs-justified-general" class="tab-pane active">
			<div id="generaltbl" style="border: medium none;">

				<div class="row">
					<div class="form-group" id="documenttypedivid">
						<div class="input-field col m5" > <b><s:text name="Text.image"/></b></div> 
						<div class="col-md-1 control-label"> : <img
							id="showmobimage" style="cursor: pointer;" alt=""
							src="<s:property value="documentmng.mobileImagePath"/>"
							onclick="toViewlargesizeimgwithsrc(this.id,'<s:property value="documentmng.fullFilePath"/>')">
							</div>
					</div>
				</div>
				<div class="row">
						<div class="input-field col m5"> <b><s:text name="Text.file"/></b>
						</div> <div class="col-md-1 control-label"> :
							<s:a href="" onclick="submitgivenform('%{documentmng.downloadImagePath}');"
								cssStyle="color:black;"><div class="saveicon" id="documenttypedivid"
							style="height: 28px; cursor: pointer;"></div></s:a>
							</div>
				</div>
				<div class="row">
					<div class="input-field col s6">
						<div class="form-group" id="docShareIddivid">
							<label class="control-label"><s:text name="Text.resident"/></label>
							<s:textfield name="viewshareusernames" id="viewshareusernames"
								cssClass="form-control typeahead tt-query viewshareusernames"
								 autocomplete="off" />
							<input type="hidden" id="viewshareusernames_hidd" class="form-control" /> 
							<div class="clear height10px"></div>
							<div class="help-block error" style="display: none;" data-bv-validator="notEmpty" data-bv-for="viewshareusernames" data-bv-result="INVALID"><s:text name="Error.residentname.require"/></div>
						</div>
					</div>
					<div class="input-field col s6">
					<button type="button" onclick="addAdditionalmaintenancebyimage();"
						class="<s:text name="button.color.submit"/>">
						  <s:text name="Send" /><i class="mdi-content-send right"></i>
					</button>
					</div>
				</div>
				<div style="clear: both;">
				
				</div>
				<form method="post" id="addAdditionalImagesharename"
					action="addAdditionalImagesharenames">
					<s:hidden name="documentMng.documentId"
						value="%{documentmng.documentId}"></s:hidden>
					<s:hidden name="documentMng.docShareId" id="addsharenames" value=""></s:hidden>
				</form>
			</div>
		</div>
	</s:if>
	<s:else>
	<form method="post" id="addAdditionalFormsharenames" action="addAdditionalFormsharename">
		<div class="row">
			<div class="input-field col m6">
					<div class="input-field col m5"> <s:text name="Text.billdate"/></div> 
					<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.billDate"/> </span>
			</div>
			</div>
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.serviceCharges"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.serviceCharge"/> </span>
				</div>
			</div>
			</div>
			</div>
					<div class="row">
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.municipalTax"/>
										</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.municipalTax"/> </span>
				</div>
			</div>
			</div>
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.penalti.others"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.penalty"/> </span>
				</div>
			</div>
			</div>
			</div>
					<div class="row">
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.water.charges"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.waterCharge"/> </span>
				</div>
			</div>
			</div>
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.nonoccupancy.charges"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.nonOccupancyCharge"/> </span>
				</div>
			</div>
				</div>
				</div>
					<div class="row">
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.federation.culture"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.culturalCharge"/> </span>
				</div>
			</div>
			</div>
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.srink.fund"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.sinkingFund"/> </span>
				</div>
			</div>
			</div>
			</div>
					<div class="row">
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.repair.fund"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.repairFund"/> </span>
				</div>
			</div>
			</div>
			
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5"> <s:text name="Text.insurance.charges"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.insuranceCharges"/> </span>
				</div>
			</div>
			</div>
			</div>
					<div class="row">
			<div class="input-field col m6">
				<div class="form-group">
					<div class="input-field col m5" > <s:text name="Text.playZones"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.playZoneCharge"/> </span>
				</div>
			</div>
			</div>
			
			<div class="input-field col m6">
					<div class="input-field col m5"> <s:text name="Text.majorRepair.Fund"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.majorRepairFund"/> </span>
				</div>
			</div>
			</div>
			
					<div class="row">
			<div class="input-field col m6">
					<div class="input-field col m5"> <s:text name="Text.interest"/>
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="maintanencefee.interest"/> </span>
				</div>
			</div>
			
			<div class="input-field col m6">
					<div class="input-field col m5"> Total
					</div> <div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="total"/> </span>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="input-field col m6">
					<div class="input-field col m5"> <s:text name="Text.file"/>
					</div> <div class="input-field col m7"> :
							<s:a href="" onclick="submitgivenform('%{documentmng.downloadImagePath}');"
								cssStyle="color:black;"><i class="mdi-content-save tinysmall teal-text text-accent-4" id="documenttypedivid"
							style="cursor: pointer;"></i></s:a>
							</div>
			</div>
						<%-- <div class="input-field col m5"> <b><s:text name="Text.file"/></b>
						</div> <div class="col-md-1 control-label"> :
							<s:a href="" onclick="submitgivenform('%{documentmng.downloadImagePath}');"
								cssStyle="color:black;"><div class="saveicon" id="documenttypedivid"
							style="height: 28px; cursor: pointer;"></div></s:a>
							</div> --%>
				</div>
			<%-- <div class="row">
				<div class="input-field col m12">
					<div class="input-field col m9">
						<div class="form-group" id="docShareIddivid">
							<label for="viewshareusernames" class="control-label active"><s:text name="Text.resident"/></label>
							<div class="clear height10px"></div>
							<s:textfield name="viewshareusernames" id="viewshareusernames"
								cssClass="form-control typeahead tt-query viewshareusernames"
								 autocomplete="off" />
							<input type="hidden" id="viewshareusernames_hidd"
								class="form-control" /> <div class="clear height10px"></div><div class="help-block error"
								style="display: none;" data-bv-validator="notEmpty"
								data-bv-for="viewshareusernames" data-bv-result="INVALID"><s:text name="Error.residentname.require"></s:text></div>
						</div>
					</div>
						<div class="col-sm-3" style="margin-top:26px; margin-left:0px; float:left;padding: 0;">
						 <button type="button" id="userCreateButtonId" onclick="addAdditionalmaintenancebyform();" style="float:right;"
						class="<s:text name="button.color.submit"/>">
						  <s:text name="Send" /><i class="mdi-content-send right"></i>
					</button>
			</div>
				
			</div>
			</div> --%>
			<s:hidden name="documentMng.documentId" value="%{documentmng.documentId}"></s:hidden>
			<s:hidden name="documentMng.docShareId" id="formviewsharenames" value=""></s:hidden>
	</form>
	</s:else>
	
</s:if>

<s:else>

											 <form method="post" id="addAdditionalGeneralsharenames" action="addAdditionalGeneralsharename">
											 
										                      	 <div class="row">
                                                                  <div class="input-field col m6"> 
                                                                     <div class="row">
                                                                    <div class="input-field col m5"><s:text name="Text.DocType"></s:text></div>
                                                                    <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="documentmng.docTypName"/></span></div>
                                                                      </div>
                                                                   </div></div>
                                                                   <div class="row">
                                                                 <div class="input-field col m6">          
                                                                <div class="row">
                                                                     <div class="input-field col m5"><s:text name="Text.emailNotification"></s:text>  </div>
                                                                 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="documentmng.emailStatusdet"/></span></div>
                                                                           </div>
                                                                     </div>
							                                     </div>
															<div class="row">
  																  <div class="input-field col m6"> 
                                                                            <div class="row">
                                                                    <div class="input-field col m5" ><s:text name="Text.file"></s:text></div>
                                                                    <div class="input-field col m7"><span class="<s:text name="view.fontvalue.color" />">:
                                                                    
                                                                  	<s:a  id="documenttypedivid" href="#" onclick="submitgivenform('%{documentmng.downloadImagePath}');"
								cssStyle="color:black;height: 28px; cursor: pointer;"><i class="mdi-content-save tinysmall teal-text text-accent-4"></i></s:a>												
															</span></div>
                                                           </div></div>
															</div>
													
													<div class="row">
  																  <div class="input-field col m12"> 
                                                                            <div class="row">
                                                                    <div class="input-field col " style="width: 20.7%;"><s:text name="Text.Utility.Management.eventshort"></s:text></div>
                                                                    <div class="input-field col m9"><span class="<s:text name="view.fontvalue.color" />">:
                                                                  	<s:property value="documentmng.subject"/>											
															</span></div>
                                                           </div></div>
															</div>
															
															
														  <div class="row">
                                                                 <div class="input-field col m12">          
                                                                <div class="row">
                                                                     <div class="input-field col " style="width: 20.7%;"><s:text name="Text.desc"></s:text> </div>
                                                                 <div class="input-field col m9">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="documentmng.descr"/></span></div>
                                                                           </div>
                                                                     </div>
							                                     </div>
							          	 			<div class="row">
                                                                 <div class="input-field col m12">          
                                                                <div class="row">
                                                                     <div class="input-field col " style="width: 20.7%;"><s:text name="Text.resident.members.others"></s:text> </div>
                                                                 <div class="input-field col m9">: <s:textfield name="viewshareusernames" id="viewshareusernames"
								cssClass="form-control typeahead tt-query viewshareusernames"
								autocomplete="off" />
							<input type="hidden" id="viewshareusernames_hidd"
								class="form-control" /> <div class="clear height10px"></div><div class="help-block error"
								style="display: none;" data-bv-validator="notEmpty"
								data-bv-for="viewshareusernames" data-bv-result="INVALID">User name is required</div></div>
                                                                          
                                                                   </div> </div>
							                                     </div>
							                                     	<div class="row">
                                                                 <div class="input-field col m12">          
                                                                <div class="row">
                                                                     <div class="input-field col " style="width: 20.7%;"> </div>
                                                                 <div class="input-field col m9">
                                                                 <button type="button" onclick="addAdditionalGeneralbyform();" style="float:right;"
						class="<s:text name="button.color.submit"/>">
						  <s:text name="Send" /><i class="<s:text name="button.icon.submitcard"/>"></i>
					</button>				</div>
                                                                           </div>
                                                                     </div>
							                                     </div>
							                                     
										 <div style="float:left;height:340px; display:none;" id="verticalline"></div> 
										 <s:hidden name="documentMng.documentId" value="%{documentmng.documentId}"></s:hidden>
			<s:hidden name="documentMng.docShareId" id="formGeneralsharenames" value=""></s:hidden>
			<s:hidden name="sdocShareUsrId" value="%{sdocShareUsrId}"></s:hidden>
                                         </form>
					<s:form method="post" id="userCancelForm"></s:form>							
</s:else>
</div></div></div>



<form id="cancelform"></form>
<form id="downloadFormid" method="post" action="downloadfiles">
						<s:hidden id="downloadFormval" name="fileName" value=""></s:hidden>
						<s:hidden id="downloadFormvalpath" name="fileNamePath" value=""></s:hidden>
						</form>

<link type="text/css" rel="stylesheet"
	href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />



<!-- Required JS Files -->
<script type="text/javascript"
	src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script>
<script>
	$(document).ready(function() {
		toLoadAutoComplate();
	});

	function toLoadAutoComplate() {
		toremoveAllFB();
		$.ajax({
					type : 'post',
					datatype : 'json',
					url : 'getuserdetail',
					data : '',
					success : function(data) {
						data = data.replace(/&quot;/ig, '"');
						data = data.replace(/%27/ig, "'");						
						var loc_state_val = JSON.parse(data);
						$("#viewshareusernames")
								.tokenInput(
										loc_state_val,
										{
											propertyToSearch : "searchkey",
											resultsFormatter: function(item){ return "<li>" + "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.fName +" "+item.lName+" "+item.flatNo+ "<br><div  >" + item.mobilNum + "</div></div></div></li>" },
											tokenFormatter : function(item) {
												return "<li><p id='"+item.searchvalue+"'>"
														+ item.searchkey
														+ "</p></li>"
											},
											theme : "facebook"
										});

					 	$(".token-input-dropdown-facebook").css("z-index","260000");
						$("ul .token-input-list-facebook").css("width", "462px");
						$("div.token-input-dropdown-facebook").css("width","462px");
						$("ul.token-input-list-facebook").css("width", "100%");
						$("ul.token-input-list-facebook").css("min-height","0px");
						$("ul.token-input-list-facebook").css("max-height","100px");
						$("ul.token-input-list-facebook").css("height", "auto");
						$("ul.token-input-list-facebook").css("overflow-y","auto");
						$("ul.token-input-list-facebook").css(" border-color","#C4C4C4"); 
					}
				});

	}
	 function toremoveAllFB(){	
			$(".token-input-token-facebook").each(function(x){// new autocomplete
		        $(this).remove();
		    });
			 $('.token-input-dropdown-facebook').remove();
			 $('.token-input-list-facebook').remove();
		}
	 
	function additionaltogetId() {
		var idname = "";
		$(".token-input-token-facebook p").each(function(x) {// new autocomplete
			idname += $(this).attr("id") + ",";
		});
		return idname;
	}
	function addAdditionalmaintenancebyimage() {
		var idnam = additionaltogetId();
		$("#addsharenames").val(idnam);
		if (idnam.length > 0) {
			$("#docShareIddivid").removeClass("has-error");
			$("#docShareIddivid .help-block").hide();
			// $("#addAdditionalImagesharename").attr("action","addAdditionalImagesharenames");
			$("#addAdditionalImagesharename").submit();
		} else {
			$("#docShareIddivid").addClass("has-error");
			$("#docShareIddivid .help-block").show();
			return false;
		}

	}
	function cancelFunction() {
		$("#cancelform").attr("action", "utititymgmt");
		$("#cancelform").submit();
	}
function addAdditionalmaintenancebyform(){
	var idnam = additionaltogetId();
	$("#formviewsharenames").val(idnam);
	if (idnam.length > 0) {
		$("#docShareIddivid").removeClass("has-error");
		$("#docShareIddivid .help-block").hide();
		// $("#addAdditionalImagesharename").attr("action","addAdditionalImagesharenames");
		$("#addAdditionalFormsharenames").submit();
	} else {
		$("#docShareIddivid").addClass("has-error");
		$("#docShareIddivid .help-block").show();
		return false;
	}
}
function addAdditionalGeneralbyform(){
	var idnam = additionaltogetId();
	$("#formGeneralsharenames").val(idnam);
	if (idnam.length > 0) {
		$("#docShareIddivid").removeClass("has-error");
		$("#docShareIddivid .help-block").hide();
		$("#addAdditionalGeneralsharenames").submit();
	} else {
		$("#docShareIddivid").addClass("has-error");
		$("#docShareIddivid .help-block").show();
		return false;
	}
}
function submitgivenform(subval){

	$("#downloadFormval").val(subval);
	$("#downloadFormid").submit();
	
}
	
</script>