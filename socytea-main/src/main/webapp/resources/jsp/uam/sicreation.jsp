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
<jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>
<body>
<!-- Start Page Loading -->
	<div id="loader-wrapper" class="loadmanual">
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
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.sicreation" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Dashboard"/></a></li>
							<li class="active"><a href="usermgmt"><s:text name="Breadcrumb.uam"></s:text></a></li>
							<li class="active"><a href="usermgmt"><s:text name="Breadcrumb.usermanage"/></a></li>
							<li class="active"><s:text name="Breadcrumb.sicreation" /></li>
						</ol>
					</div>
				</div>
				</div>
				</div>
				 <div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
										<form  id="userCreationFormId" action="usersicreation" method="post" enctype="multipart/form-data">

									<div class="row">
										<div class="input-field col s2">
												<label for="isdcodeid"><s:text name="Text.Form.IsdCode"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control isdNo" value="%{getText('Text.ISD.value')}" name="usercreateObj.isdCode"
												id="isdcodeid" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
											<div class="input-field col s4">
												<label for="mobilenoid"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control mobilevalidate " name="usercreateObj.mobileNo"
												id="mobilenoid"></s:textfield>
												<s:fielderror fieldName="usercreateObj.mobileNo"/>
											</div>
											<div class="input-field col s6">
												<label for="emailid"><s:text name="Text.emailid" /></label>
												<s:textfield cssClass="form-control " name="usercreateObj.emailId"
												id="emailid" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.emailId"/>
											</div>


									</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="card"><s:text name="Text.customerreg.idproof"/></label>
												<s:textfield name="idcardName" id="card" cssClass="form-control typeahead tt-query left "  autocomplete="off" spellcheck="false"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden id="cardid" name="usercreateObj.idCardType" class="form-control" />
												<s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											</div>
												<div class="input-field col s6">
												<label for="idproofno"><s:text name="Text.customerreg.idproofno"/></label>
												<s:textfield cssClass="form-control  " name="usercreateObj.idProofNo"
												id="idproofno"></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
												<div id="card-error-div" class="error manualscriptvalidation" style="display:none;"></div>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="firstname"><s:text name="Text.fname" /></label>
												<s:textfield cssClass="form-control registerNumber" name="usercreateObj.firstName"
												id="firstname" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
												<div class="input-field col s6">
												<label for="lastname"><s:text name="Text.lname" /></label>
												<s:textfield cssClass="form-control registerNumber" name="usercreateObj.lastName"
												id="lastname" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
											</div>
											<div class="row">
										<%-- 	<div id="input-select">
												<div class="input-field col s6">
												<label for="gender" class="control-label"><s:text name="Text.gender" /></label>
												 <s:select cssClass="form-control gendervalidate" id="gender" list="#{'1':'Male','2':'Female','3':'Other' }" name="usercreateObj.gender" />
											</div>
											</div>	 --%>
											<!-- 	<div id="input-select">			 -->	<!-- 	</div> -->
												<div class="input-field col s6">
												<label for="gender" class="active"><s:text name="Text.gender" /></label>
												 <s:select cssClass="form-control gendervalidate" id="gender" list="#{'1':'Male','2':'Female','3':'Other' }" name="usercreateObj.gender" />

											</div>
												<div class="input-field col s6">
													<label for="dateid"><s:text name="Text.resident.dob"/></label>
													<s:textfield  id="dateid" cssClass="dateOfBirth" name="usercreateObj.dob"></s:textfield>
															</div>
															</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="address1"><s:text name="uam.profile.address1"/></label>
												<s:textfield cssClass="form-control address" name="usercreateObj.address1"
												id="address1" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.address"/>
											</div>
											<div class="input-field col s6">
												<label for="address2"><s:text name="uam.profile.address2"/></label>
												<s:textfield cssClass="form-control addresses" name="usercreateObj.address2"
												id="address2"  ></s:textfield>
												<s:fielderror fieldName="usercreateObj.addresses"/>
											</div>
											</div>
										<div class="row">
											<div class="input-field col s6">
												<label for="country_txt_id"><s:text name="Menuheader.uam.profile.country" /><span class="mandatory"></span></label>
												<s:textfield name="countryName" id="country_txt_id" cssClass="form-control typeahead tt-query country left"  autocomplete="off" spellcheck="false"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="usercreateObj.countryCode" id="country_hidd_id" class="form-control"/>
												<s:fielderror fieldName="usercreateObj.groupId.country"/>
											</div>
										<div class="input-field col s6">
												<label for="state_txt_id"  ><s:text name="Menuheader.uam.profile.state" /><span class="mandatory"></span></label>
												<s:textfield name="stateName" id="state_txt_id" cssClass="form-control typeahead tt-query state left"  autocomplete="off" spellcheck="false" onkeyup="statecheck();"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="usercreateObj.stateId" id="state_hidd_id" class="form-control"/>
												<s:fielderror fieldName="usercreateObj.state"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /><span class="mandatory"></span></label>
												<s:textfield name="cityName" id="city_txt_id" cssClass="form-control typeahead tt-query city left" autocomplete="off" spellcheck="false" onkeyup="citycheck();"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="usercreateObj.cityId" id="city_hidd_id" class="form-control"/>
												<s:fielderror fieldName="usercreateObj.city"/>
											</div>
												<div class="input-field col s6">
												<label for="pstlcode_txt_id"><s:text name="Menuheader.uam.profile.pincode" /></label>
												<s:textfield name="usercreateObj.postalid" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query pincode left"  autocomplete="off" spellcheck="false" onkeyup="pstlcodecheck();"/>
<%-- 												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
<span class="input-group-addon txtbxdownarowicon left" ></span>
<!-- 												<input type="hidden" id="pstlcode_hidd_id" name="usercreateObj.postalid" class="form-control"/> -->
												<s:fielderror fieldName="usercreateObj.pincode"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
												<label for="occupation"><s:text name="uam.profile.occupation"/></label>
												<s:textfield cssClass="form-control occupation" name="usercreateObj.occupation"
												id="occupation"  ></s:textfield>
												<s:fielderror fieldName="usercreateObj.occupation"/>
											</div>
											<div class="input-field col s6">
												<%--<label for="member"><s:text name="Text.resident.familymember"/></label>
												<s:textfield cssClass="form-control familymembers" name="usercreateObj.membersInFamily"
												id="member"  ></s:textfield>
												<s:fielderror fieldName="usercreateObj.familymembers"/> --%>
												<label for="bloodgrp"><s:text name="Text.resident.bloodgrp"/></label>
											<s:textfield name="usercreateObj.bloodType" id="bloodgrp" cssClass="form-control typeahead tt-query bloodgroup"  autocomplete="off" spellcheck="false"/>
												<input type="hidden" id="bloodgroupid" class="form-control"/>
												<s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field">
											<label class="active" for="accessChannel"><s:text name="Text.accesschannel" /><span class="mandatory">*</span></label>
											 <p>
											 <s:checkboxlist list="#{'1':'Web','2':'Mobile','3':'Both'}"
                                             name="accessChannel" id="accessChannel" cssClass="myCheckbox" value="1"/>
											</p>
                                            </div>
                                            </div>
											 <div class="clear height25px"></div>
											 <div class="row">
											 <div class="col s12">
											<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.submit" />
							<i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
										</div></div>
										</form>
										<s:form method="post" id="userCancelForm"></s:form>

									</div>
								</div>
							</div>
							</section>
					   </div>
				</div>

		<jsp:include page="../common/footer.jsp"></jsp:include>
		<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
</body>
<script>
 $(document).ready(function() {
	 
	 $("#pstlcode_txt_id").keyup(function(){
			textValidate(this,'10','MNM');
		});
	 
		$('#pstlcode_txt_id').blur(function(){
//	     	alert($('#pstlcode_txt_id').val());
			$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
		});
	 
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
		});
	 
	 var exigndr = $("#gender").html();
	 $("#gender").html('<option value="" disabled selected>Choose your gender</option>'  + exigndr);
	 $("#gender").material_select();
	     $('input[type="checkbox"]').click(function(){
	         if($(this).prop("checked") == true){
	             $(this).siblings('input:checkbox').prop('checked', false);

	         } else if($(this).prop("checked") == false){
	        	 $(this).prop('checked', true);

	         }
	     });
	 	$("#mobilenoid").keyup(function(){
			textValidate(this,'10','PHNM');
		});
		$("#isdcodeid").keyup(function(){
			textValidate(this,'4','PHNM');
		});
		$("#emailid").keyup(function(){
			textValidate(this,'100','EML');
		});
		 $("#idproofno").keyup(function(){
			 $("#card-error-div").hide();
		    	textValidate(this,'21','EML');
		 });
		  $("#firstname").keyup(function(){
		    	textValidate(this,'30','OA');
		  });
		    $("#lastname").keyup(function(){
		    	textValidate(this,'30','OA');
		    });
		    $("#blocknamelist").keyup(function(){
		    	textValidate(this,'30','PHNM');
		    });
		    $("#flatnamelist").keyup(function(){
		    	textValidate(this,'30','EML');
		    });
		    $("#address1").keyup(function(){
		    	textValidate(this,'100','ADP');
		    });
		    $("#address2").keyup(function(){
		    	textValidate(this,'100','ADP');
		    });
		    $("#occupation").keyup(function(){
		    	textValidate(this,'50','OA');
		    });
		    $("#member").keyup(function(){
		    	textValidate(this,'3','PHNM');
		    });
		    $("#bloodgrp").keyup(function(){
		    	textValidate(this,'10','NV');
		    });

		    $("#emailid").blur(function(){
		    	toValiEmail(this.id);
			});
		    $("#mobilenoid").blur(function(){
		    	toValiMobno(this.id);
		    });

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

		   	$("#isdcodeid").rules("add", {
		   		required : true,
		   		minlength :2,
		   		messages : {
		   			required : "<s:text name="Error.customerreg.ISDno.mobileno" />",
		   		}
		   	});

		   	$("#mobilenoid").rules("add", {
		   		required : true,
		   		minlength :10,
		   		maxlength :10,
		   		messages : {
		   			required : "<s:text name="Error.customerreg.mobileno.length" />",
		   			//minlength : "Enter at least 5 characters"
		   		}
		   	});
});


 function cancelFunction(){
	$("#userCancelForm").attr("action", "usermgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
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
	        	  $('#township_txt_id').val('');
	        	  $('#township_txt_id').focus();
		          $('#township_hidd_id').val('');
	          }
	      });

	}
	$(document).ready(function(){
	 /*	var test=$("#societyId_hidd_id").val();
	 	getsocietyonload(test);
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'gettownshipOnload',
			//data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				var spli=data.split("!_!");
				townshipdata(spli[1]);
			}
		});*/
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getCommitteeOnload',
			//data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				var spli=data.split("!_!");
				committeedata(spli[1]);
			}
		});


	});
	 function committeedata(valjsoncommittee){
	 var objects_committee;
		var committeemap;
		valjsoncommittee=valjsoncommittee.replace(/&quot;/ig,'"');
		var locvalcommittee = JSON.parse(valjsoncommittee);
		$('#committee_txt_id').typeahead({
		     	order: "asc",
				hint: true,
					accent: true,
					offset: true,
					searchOnFocus: true,
		        source: function(query, process) {
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
		    updater: function(item) {
		        $('#committee_hidd_id').val(committeemap[item].id);
		       return item;
		    }
		});
	$('#committee_txt_id').blur(function(){
			if (typeof(objects_committee) != "undefined" &&  objects_committee.indexOf(this.value)!=-1) {
	     }else{

	       }
	   });
	 }

	 function getsocietyonload(townid){
		 var temp="";
			$.ajax({
		 		type : 'post',
		 		datatype : 'html',
		 		url : 'townshipgetsociety',
		 		data : 'townshipid=' + townid+"&clfor=autocomp",
		 		success : function(data) {
		 			var spl=data.split("!_!");
		 			temp=spl[1];
		 			getsociety_autoonload(spl[1]);
		 			//return temp;
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


	var objects_scty;
	function getsociety_autoonload(tempdata){
		tempdata=tempdata.replace(/&quot;/ig,'"');
		var locval = JSON.parse(tempdata);
		var map_scty;
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

	function getsociety_auto(tempdata){
		tempdata=tempdata.replace(/&quot;/ig,'"');
		var locval = JSON.parse(tempdata);
		var map_scty;
		$('#societyId_txt_id').val('');
       // $('#userCreationFormId').bootstrapValidator('removeField', 'societyName'); //remove dynamical validate - 1
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
	}).on('typeahead:selected', function(e, suggestion, dataSetName) {/* Revalidate the state field */
    }).on('typeahead:closed', function(e) { /* Revalidate the state field */
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
		/*function societyIdvalidate(){
			var statval = $("#societyId_txt_id").val();
			if(statval!="" && statval.length>0){
				//$("#societid").removeClass("has-error");
				//$("#societid").addClass("has-success");
				//$("#societid .help-block").attr("data-bv-result","societyId_txt_id");
				//$("#societid .help-block").hide();
			}else{
				$("#societid").removeClass("has-success");
				$("#societid").addClass("has-error");
				$("#societid .help-block").attr("data-bv-result","INVALID");
				$("#societid .help-block").show();
			}
		}*/
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
					url : 'categorytype',
					data : '',
					success : function(data) {
						var arr=data.split("!_!");
						categorylistFun(arr[1]);
					}
				});

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

		});

	 function categorylistFun(catData){
		 	var objects_categorytype;
		 	var map_cat;
		 	catData=catData.replace(/&quot;/ig,'"');
		 	catData=catData.replace(/%27/ig,"'");
		 	var locval = JSON.parse(catData);
		 	$('#category').typeahead('destroy');
		  	$('#category').typeahead({
		 	     		order: "asc",
		 				hint: true,
		 				accent: true,
		 				offset: true,
		 				searchOnFocus: true,
		 	        	source: function(query, process) {

		 	        		objects_categorytype = [];
		 	        		map_cat = {};
		 	        		var data = locval;
		 	        $.each(data, function(i, object) {
		 	        	map_cat[object.label] = object;
		 	        	objects_categorytype.push(object.label);
		 	        });
		 	        process(objects_categorytype);
		 	       $(".dropdown-menu").css("height", "auto");
		 	       $(".dropdown-menu").addClass("form-control");
		 	    },
		 	    updater: function(item) {
		 	        $('#categoryid').val(map_cat[item].id);
		 	        return item;
		 	    }
		 	});
		  $('#category').blur(function(){
		 		if (typeof(objects_categorytype) != "undefined" && objects_categorytype.indexOf(this.value)!=-1) {
		         }else{
		         	  $('#category').val('');
		         	  $('#category').focus();
		 	          $('#categoryid').val('');
		           }
		       });
		 	}
	 function idcardlistfun(ar){
	 	var objects_cardtype;
	 	ar=ar.replace(/&quot;/ig,'"');
	 	ar=ar.replace(/%27/ig,"'");
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
	  $('#card').blur(function(){
	 		if (typeof(objects_cardtype) != "undefined" &&  objects_cardtype.indexOf(this.value)!=-1) {
	         }else{
	         	  $('#card').val('');
	         	  $('#card').focus();
	 	          $('#cardid').val('');
	           }
	       });
	 	}
		function CountrylistFun(ar){
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
// 		          $('#pstlcode_txt_id').val('');
// 	    	  		$('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');

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
// 			          $('#pstlcode_txt_id').val('');
// 		        	  $('#pstlcode_txt_id').typeahead('destroy');
// 			          $('#pstlcode_hidd_id').val('');
		          }
		      });
		}
		function onchangeStatecodeAgency()
		{
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
// 		          $('#pstlcode_txt_id').val('');
// 	    	  	  $('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');

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
// 			          $('#pstlcode_txt_id').val('');
// 		        	  $('#pstlcode_txt_id').typeahead('destroy');
// 			          $('#pstlcode_hidd_id').val('');

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
			});
			}
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
					searchOnFocus: true,
					minLength : 0,
					highlight: true,
					showHintOnFocus:true,
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
// 			        $('#pstlcode_txt_id').val('');
// 			          $('#pstlcode_txt_id').typeahead('destroy');
// 			          $('#pstlcode_hidd_id').val('');

// 			        onchangePstlcodeAgency();
			        return item;
			    }
			});

			 $('#city_txt_id').blur(function(){
					if (typeof(objects_city) != "undefined" && objects_city.indexOf(this.value)!=-1) {
			        }else{
			        	$('#city_txt_id').val('');
				          $('#city_hidd_id').val('');
				          $('#city_txt_id').typeahead('destroy');
// 			        	  $('#pstlcode_txt_id').typeahead('destroy');
// 				          $('#pstlcode_hidd_id').val('');


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
				});
			}
		}

			function pstlcodelistload(arr)
			{
			var objects_pstl;
				arr=arr.replace(/&quot;/ig,'"');
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

			function statecheck(){
				var countryid=$("#country_hidd_id").val();
				if(countryid=="" && countryid!=null){
					$('#state_txt_id').val('');
			          $('#city_txt_id').val('');
// 			          $('#pstlcode_txt_id').val('');
				}
			}
			function citycheck(){
				var stateid=$("#state_hidd_id").val();
				if(stateid=="" && stateid!=null){
			          $('#city_txt_id').val('');
// 			          $('#pstlcode_txt_id').val('');
				}
			}
			function pstlcodecheck(){
// 				var pstlcode=$("#city_hidd_id").val();
// 				if(pstlcode=="" && pstlcode!=null){
// 			          $('#pstlcode_txt_id').val('');
// 				}
			}
</script>
</html>
