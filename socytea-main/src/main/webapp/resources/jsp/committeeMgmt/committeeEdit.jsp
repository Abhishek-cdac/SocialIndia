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
								<h5 class="breadcrumbs-title"><s:text name="Committee Modify" /></h5>
								<ol class="breadcrumbs">
								<li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome" /></a></li>
								<li><a href="committeemgmt"><s:text name="Breadcrumb.manage" /></a></li>
								<li><a href="committeemgmt"><s:text name="Committee Management" /></a></li>
								<li  class="active"><s:text name="Committee Modify" /></li>
						</ol>
					</div>
				</div>
				</div></div>
				 <!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
	<form role="form" id="userCreationFormId" action="committeeUpdateAction"  method="post" >
			<s:if test="#session.sSoctyId==-1">
									<div class="row rowmargin12px">
											  <div class="input-field col s6">
												<label for="township_txt_id"><s:text name="Text.adduser.town.ship.id" /><span class="mandatory">*</span></label>
												<s:textfield  id="township_txt_id" name="usercreateObj.townshipName"  cssClass="form-control typeahead tt-query townshipIdvalidate" readonly="true" autocomplete="off" />
												<s:hidden  name="townshipId" id="township_hidd_id"  class="form-control "  />
												<s:fielderror fieldName="townshipId"/>
											</div>
										<!-- <div class="row rowmargin12px"> -->
											  <div class="input-field col s6">
												<label for="societyId_txt_id"><s:text name="Text.adduser.society.id" /><span class="mandatory">*</span></label>
												<s:textfield  id="societyId_txt_id" name="usercreateObj.societyName"  cssClass="form-control typeahead tt-query societyIdvalidate" readonly="true" autocomplete="off" onkeyup="societycheck();"/>
														<s:hidden  name="societyId" id="societyId_hidd_id"  class="form-control "  />
													<s:fielderror fieldName="usercreateObj.societyId"/>
											</div>
											<!-- </div>	 -->
											</div>
			</s:if>
											  <div class="row rowmargin12px">
											   <div class="input-field col s1">
												<label for="isdcodeid" class="active"><s:text name="Text.Form.IsdCode"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control Isdcode " name="usercreateObj.isdCode" readonly="true" id="isdcodeid" value="%{getText('Text.ISD.value')}"></s:textfield>
												<s:fielderror fieldName="usercreateObj.isdCode"/>
											</div>
											  <div class="input-field col s5">
												<label class="mobilevalidate"><s:text name="Text.adduser.mobnumber" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control mobilevalidate" readonly="true" name="usercreateObj.mobileNo" id="mobilevalidate" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.mobileNo"/>
											 </div>

														  <div class="input-field col s6">
																<label  for="committee_txt_id"><s:text name="Committee Role" /><span class="mandatory">*</span></label>
														<s:textfield  id="committee_txt_id" name="usercreateObj.committeeRole"  cssClass="form-control typeahead tt-query committeevalidate left" autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('committee_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<s:hidden name="committeeid"  id="committee_hidd_id"  class="form-control "  />
															</div>
															</div>
															  <div class="row rowmargin12px">
														 <div class="input-field col s6">
												<label for="emailid"><s:text name="Text.adduser.emailid" /></label>
												<s:textfield cssClass="form-control "  name="usercreateObj.emailId" id="emailid" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.emailId"/>
											 </div>

											 <div class="input-field col s6">
										<div class="form-group" id="usernamedivid">
												<label for="firstname"><s:text name="Text.adduser.fname" /></label>
												<s:textfield cssClass="form-control firstnamevalidate" name="usercreateObj.firstName" id="firstname" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.firstName"/>
											</div>
											</div>
											</div>
											 <div class="row rowmargin12px">
											 <div class="input-field col s6">
												<label class="lastname"><s:text name="Text.adduser.lname" /></label>
												<s:textfield cssClass="form-control lastnamevalidate" name="usercreateObj.lastName" id="lastname"></s:textfield>
												<s:fielderror fieldName="usercreateObj.lastName"/>
											 </div>
											 <!--  <div id="input-select"> -->
											 <div class="input-field col s6">
												<label for="gendertyp" class="active"><s:text name="Gender" /></label>
												<s:select id="gendertyp" cssClass="form-control lastnamevalidate" name="usercreateObj.gender" value="%{usercreateObj.gender}" list="#{'1':'Male','2':'Female','3':'Other'}" ></s:select>
												<s:fielderror fieldName="usercreateObj.lastName"/>
											 <!-- </div> -->
											 </div> </div>
										<div class="row rowmargin12px" >
										  <div class="input-field col s6">
										  <label for="dateid">Date Of Birth</label>
										  <s:textfield  id="dateid" cssClass="dateOfBirth" name="usercreateObj.dob"></s:textfield>
										  </div>
								</div>
											<div class="row rowmargin12px" >
											<div class="input-field">
											<label for="accessChannel" class="active"><s:text name="Access Channel" /><span class="mandatory"></span></label>

                                            <s:checkboxlist list="#{'2':'Mobile','1':'Web','3':'Both'}"
                                             name="usercreateObj.accessChannel" id="accessChannel" cssClass="myCheckbox" /></div>
											</div>
											 <s:hidden name="usercreateObj.userId" value="%{usercreateObj.userId}"></s:hidden>
											 <div style="clear: both; height: 25px;"></div>
											 <div class="clear marginleft25px">
										<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.update" />
							<i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
										</div>
										</form>

										<s:form method="post" id="userCancelForm"></s:form>

									</div>
								</div>
						</div>
			<!-- END Body Container -->
	</section>
</div>
</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
</body>
<script type="text/javascript">
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
$(document).ready(function(){
	 var exigndr = $("#gendertyp").html();
	 $("#gendertyp").html('<option value="" disabled>Choose your gender</option>'  + exigndr);
	 $("#gendertyp").material_select();
    $('input[type="checkbox"]').click(function(){
        if($(this).prop("checked") == true){
            $(this).siblings('input:checkbox').prop('checked', false);
        } else if($(this).prop("checked") == false){
        	 $(this).prop('checked', true);
        }
    });

if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login
	    var test=$("#societyId_hidd_id").val();
		getsocietyonload(test);
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'gettownshipOnload',
			success : function(data) {
				var spli=data.split("!_!");
				townshipdata(spli[1]);
			}
		});
}
		$.ajax({
			type : 'post',
			datatype : 'html',
		    url : 'getCommitteeOnload',
			success : function(data) {
				var spli=data.split("!_!");
				committeedata(spli[1]);
			}
		});

	// Keyup Validation
if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login
	    $("#township_txt_id").keyup(function(){
			textValidate(this,'100','AI');
		});
		$("#societyId_txt_id").keyup(function(){
			textValidate(this,'100','AI');
		});
}

		$("#committee_txt_id").keyup(function(){
			textValidate(this,'100','AI');
		});
	 	$("#firstname").keyup(function(){
			textValidate(this,'100','OA');
		});
	 	$("#lastname").keyup(function(){
			textValidate(this,'100','OA');
		});
	 	$("#emailid").keyup(function(){
			textValidate(this,'100','EML');
		});
	 	$("#emailid").blur(function(){
		 toValiEmail(this.id);
	 	});

		// Submit Validation
	 	$('#userCreationFormId').validate({
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
if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login
		$("#township_txt_id").rules("add", {
			required : true,
			messages : {
				required : "<s:text name="Error.customerreg.townshipId" />"
			}
		});

		$("#societyId_txt_id").rules("add", {
			required : true,
			messages : {
				required : "<s:text name="Error.customerreg.societyId" />"
			}
		});
}
		$("#committee_txt_id").rules("add", {
			required : true,
			messages : {
				required : "<s:text name="Error.customerreg.committeerole" />"
			}
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
		 $('#township_txt_id').blur(function(){
			if (typeof(objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
	        }else{
	          }
	     });
}

function committeedata(valjsoncommittee){
    var objects_committee;
	var committeemap;
	valjsoncommittee=valjsoncommittee.replace(/&quot;/ig,'"');
	var locvalcommittee = JSON.parse(valjsoncommittee);
		$('#committee_txt_id').typeahead({

			order : "asc",
			hint : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
			source : function(query, process) {
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
			updater : function(item) {
				$('#committee_hidd_id').val(committeemap[item].id);
				return item;
			}
		});
		$('#committee_txt_id').blur(function() {
			if (typeof(objects_committee) != "undefined" && objects_committee.indexOf(this.value) != -1) {
			} else {
			}
		});
	}

	function getsocietyonload(townid) {
		var temp = "";
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'townshipgetsociety',
			data : 'townshipid=' + townid + "&clfor=autocomp",
			success : function(data) {
				var spl = data.split("!_!");
				temp = spl[1];
				getsociety_autoonload(spl[1]);
			}
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
				getsociety_auto(spl[1]);
			}
		});
	}

	function getsociety_autoonload(tempdata) {
		var objects_scty;
		tempdata = tempdata.replace(/&quot;/ig, '"');
		var locval = JSON.parse(tempdata);
		var map_scty;
		$('#societyId_txt_id').typeahead('destroy');
		$('#societyId_txt_id').typeahead({
			order : "asc",
			hint : true,
			accent : true,
			offset : true,
			searchOnFocus : true,
			source : function(query, process) {
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
			updater : function(item) {
				$('#societyId_hidd_id').val(map_scty[item].id);
				return item;
			}
		});
		$('#societyId_txt_id').typeahead('refresh');
		$('#societyId_txt_id').blur(function() {if (typeof (objects_scty) != "undefined" && objects_scty.indexOf(this.value) != -1) {} else {} });
	}

		function getsociety_auto(tempdata) {
			tempdata = tempdata.replace(/&quot;/ig, '"');
			var locval = JSON.parse(tempdata);
			var map_scty;
			$('#societyId_txt_id').val('');
			// $('#societyId_hidd_id').val('');
			$('#societyId_txt_id').typeahead('destroy');
			$('#societyId_txt_id').typeahead({
				order : "asc",
				hint : true,
				accent : true,
				offset : true,
				searchOnFocus : true,
				source : function(query, process) {
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
				updater : function(item) {
					$('#societyId_hidd_id').val(map_scty[item].id);
					return item;
				}
			});
			$('#societyId_txt_id').typeahead('refresh');
			$('#societyId_txt_id').blur(
					function() {
						if (typeof (objects_scty) != "undefined" && objects_scty.indexOf(this.value) != -1) {
						} else {
							$('#societyId_txt_id').val('');
							$('#societyId_txt_id').focus();
							$('#societyId_hidd_id').val('');
							$('#societyId_txt_id').typeahead('destroy');
						}
					});

		}

		function cancelFunction() {
			$("#userCancelForm").attr("action", "committeemgmt");
			$("#userCancelForm").submit();
			$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
			toShowLoadingImgoverlay();
			$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
		}
		function onchangetownship() {
			var townshipid = $("#township_hidd_id").val();
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
	   function societycheck(){
			var stateid=$("#township_txt_id").val();
			if(stateid=="" && stateid!=null){
		          $('#societyId_txt_id').val('');
			}
		}
</script>
</html>
