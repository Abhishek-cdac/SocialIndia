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
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
								<h5 class="breadcrumbs-title"><s:text name="Text.createcompany.Management" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="companymgmt"><s:text name="Breadcrumb.manage.cmpymgmt" /></a></li>
							<li class="active"><s:text name="Text.createcompany.Management" /></li>
						</ol>
					</div>
				</div>
				</div>
				</div>
				 <!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
<form  id="companyCreationFormId" name="companyCreationFormId" action="companymgmtadd" method="post" enctype="multipart/form-data" >
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-social-domain tinysmall white-text text-accent-4"></i> <s:text name="Text.cmpny.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">
								<!-- 		  <div class="imgaddplus" onclick="myFunction1('add');" style="display: none;">
								<i class="mdi-content-add-circle tinysmall " style="color: #ff4081; float: left; cursor: pointer;"></i>
								<div class="spacialspace">Profile Detail </div>
							</div> onclick="myFunction1('sub');"-->
					<!-- <div id="">
							<div class="imgaddminus">
								<i class="<s:text name="button.color.minushideshow"></s:text>"></i>
								<span class="spacialspace">Profile Detail </span>
							</div>
										    </div><div style="clear: both; height:5px;"></div>-->
			<div id="profilehidden" style="display:block;">
			<div class="row">
					<div class="input-field col s12 m4 l3">
					<input type="file"  name="staffImage" id="staffImage" class="dropify" />
        			</div>
					<div class="col s12 m8 l9">
									<div class="row rowmargin12px" >
										 <div class="input-field col s6">
											<label for="companyname"><s:text name="Text.company.name"></s:text><span class="mandatory">*</span></label>
											<s:textfield name="cmpyRegObj.companyName" id="companyname"   cssClass="form-control firstnamevalidate"/>
										 </div>

										 <div class="input-field col s6">
										 	<label for="emailid"><s:text name="Text.customerreg.emailid" /><span class="mandatory"></span></label>
										 	<s:textfield name="cmpyRegObj.companyEmail" id="emailid"  cssClass="form-control emailidvalidate"/>
										 </div>

									</div>
 															 <div class="row rowmargin12px">
 															<div class="input-field col s2">
												<label for="isdcodeid"><s:text name="Text.customerreg.isd"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control IsdNumber"  name="cmpyRegObj.isdCode"
												id="isdcodeid" required="required" value="%{getText('Text.ISD.value')}"></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>

											<div class="input-field col s4">
												<label for="mobilenoid" ><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber " name="cmpyRegObj.mobileNo"
												id="mobilenoid" required="required"></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
															<div class="input-field col s6">
																<label for="cmpnyRegno"><s:text name="Text.company.regno" /><span class="mandatory">*</span></label>
																	 <s:textfield  name="cmpyRegObj.cmpnyRegno" id="cmpnyRegno"   cssClass="form-control typeahead tt-query cmpnyRegnovalidate" autocomplete="off"/>
																</div>
													</div>
															<div class="row rowmargin12px">
															<div class="input-field col s6">
  															<label for="keysearch"><s:text name="Text.company.keysearch" /><span class="mandatory"></span></label>
															<s:textfield name="cmpyRegObj.keyforSrch"  id="keysearch"  cssClass="form-control"/>
															</div>

										<div class="input-field col s6">
                                            <label for="genter_select" class="active">Status</label>
                                            <div class="clear height10px"></div>
                                            <div id="genter_select" class="row">
                                              <input name="cmpyRegObj.verifyStatus" id="Verified" value="1" type="radio" >
                                              <label for="Verified">Verified</label>
                                              <input name="cmpyRegObj.verifyStatus" id="Unverified" value="0" type="radio" checked>
                                              <label for="Unverified">Unverified</label>
                                            </div>
                                            <div class="input-field">
                                                <div class="errorTxt8"></div>
                                            </div>
                            			</div>
										</div>
								</div>
						</div>
					</div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />" > <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>
<div class="collapsible-body padding10px" id="seconddivid" >
<!-- <div style="clear: both; height:5px;"></div>
					<div id="othersdetdiv" >
							<div class="imgaddplus1" onclick="myFunction2('add');"><i class="<s:text name="button.color.addhideshow"/>"></i>
							<div class="spacialspace">Other Detail </div>
							</div>
							<div class="imgaddminus1" onclick="myFunction2('sub');" style="display: none; cursor: pointer;">
							<i class="<s:text name="button.color.minushideshow"></s:text>"></i>
							<span class="spacialspace">Other Detail </span>
							</div></div><div style="clear: both; height:5px;"></div> -->
<div id="otherhidden" style="display:block; margin:0px;">
										<div class="row rowmargin12px">
															<div class="input-field col s6">
																		<label for="address1"><s:text name="Address1" /><span class="mandatory"></span></label>
																		<s:textfield name="cmpyRegObj.address1"  id="address1"
																			cssClass="form-control " />
																	</div>
  														 <div class="input-field col s6">
																<label for="address2"><s:text name="Address2"></s:text><span class="mandatory"></span></label>
																<s:textfield name="cmpyRegObj.address2" id="address2" cssClass="form-control " />
															</div>
										</div>

 										<div class="row rowmargin12px">
															<div class="input-field col s6">
												          <label for="country_txt_id"><s:text name="Menuheader.uam.profile.country" /></label>
														<s:textfield name="Country_id_name" id="country_txt_id" cssClass="form-control typeahead tt-query left"  autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="cmpyRegObj.countryId" ID="country_hidd_id" class="form-control"/>
											            </div>
  														  	<div class="input-field col s6">
												          <label for="state_txt_id"><s:text name="Menuheader.uam.profile.state" /></label>
													       <s:textfield name="State_id_name" id="state_txt_id" cssClass="form-control typeahead tt-query left"  autocomplete="off"/>
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<input type="hidden" name="cmpyRegObj.stateId" ID="state_hidd_id" class="form-control"/>
											            </div>
										</div>

 															  <div class="row rowmargin12px">
															<div class="input-field col s6">
												          <label for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /></label>
													       <s:textfield name="City_id_name" id="city_txt_id" cssClass="form-control typeahead tt-query left"  autocomplete="off"/>
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
													<input type="hidden" name="cmpyRegObj.cityId" id="city_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
 															<div class="input-field col s6">
												          <label for="pstlcode_txt_id"><s:text name="Text.customerreg.pincode" /></label>
													        <s:textfield name="cmpyRegObj.ivrBnLBR_PSTL_ID" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
<%-- 																	<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
													<span class="input-group-addon txtbxdownarowicon left" ></span>
													<input type="hidden" name="cmpyRegObj.pstlId" id="pstlcode_hidd_id"  class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
																	</div>
															 <div class="row rowmargin12px">
															<div class="input-field col s12">
												<label for="description"><s:text name="Description" /></label>
												<textarea name="cmpyRegObj.descr" class="materialize-textarea"  ></textarea>
											</div>
										</div>
										 </div>
</div>
</li>
</ul>
					<div class="row clear marginleft5px" style="margin-left: 5px;">
							<button type="button" id="compnyCreateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.submit" />
							<i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>
										</form>
										</div><!-- formclass end -->
										</div>
										<s:form method="post" id="userCancelForm"></s:form>
									</div>
									</section>
								</div>
							</div>

			<jsp:include page="../common/footer.jsp"></jsp:include>
			<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
			<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
</body>
<script>

$(document).ready(function(){

	$("#pstlcode_txt_id").keyup(function(){
		textValidate(this,'10','MNM');
	});
	
	$('#pstlcode_txt_id').blur(function(){
//     	alert($('#pstlcode_txt_id').val());
		$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
	});
	
	myFunction2('add');

	$("#imgdivid").click(function(){
		$("#staffImage").click();
	});
	$("#companyname").keyup(function(){
    	textValidate(this,'30','OA');
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
	$("#address1").keyup(function(){
    	textValidate(this,'100','ADP');
    });
    $("#address2").keyup(function(){
    	textValidate(this,'100','ADP');
    });
    $("#keysearch").keyup(function(){
    	textValidate(this,'200','ADP');
    });
    $("#emailid").blur(function(){
	   	 toValiEmail(this.id);
	 });
	 $("#mobilenoid").blur(function(){
	    	toValiMobno(this.id);
	 });

	 $("#compnyCreateButtonId").click(function(){
		 $("#firstdivid").css("display","block");
		 $("#seconddivid").css("display","block");
		 $('#companyCreationFormId').attr("method","Post");
		 $('#companyCreationFormId').attr("action","companymgmtadd");
		 $('#companyCreationFormId').attr("enctype","multipart/form-data");
		 $('#companyCreationFormId').submit();
	 });
});

$('#companyCreationFormId').validate({
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
$("#companyname").rules("add", {
	required : true,
	minlength :5,
	maxlength :50,
	messages : {
		required : "<s:text name="Error.customerreg.companyname" />",
		//minlength : "Enter at least 5 characters"
	}
});

$("#cmpnyRegno").rules("add", {
	required : true,
	minlength :5,
	maxlength :20,
	messages : {
		required : "<s:text name="Error.customerreg.companyregno" />",
		//minlength : "Enter at least 5 characters"

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

$(document).ready(function(){
	// Basic
    $('.dropify').dropify({
    messages: {
    	"default":"Drag and drop a image here or click",
    	replace:"Drag and drop or image to replace",
    	remove:"Remove",
    	error:"Sorry, this file is too large"
    }
	});
});
var loadFile = function(event) {
	var output = document.getElementById('myImg');
	output.src = URL.createObjectURL(event.target.files[0]);
};

	function CountrylistFun(ar) {
		cntload = true;
		var objects_country;
		ar=ar.replace(/&quot;/ig,'"');
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
	       $(".dropdown-menu").css("height", "auto");
	       $(".dropdown-menu").addClass("form-control");
	    },
	    updater: function(item) {
	        $('#country_hidd_id').val(map[item].id);
	        onchangeStatecodeAgency();
	        return item;
	    }
	});

	 $('#country_txt_id').blur(function(){
			if (typeof(objects_country) != 'undefined' && objects_country.indexOf(this.value)!=-1) {
	        }else{
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
// 		          $('#pstlcode_txt_id').val('');
// 	        	  $('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');
	          }
	      });
	}
function onchangeStatecodeAgency()
{
	var cntryval = $("#country_hidd_id").val();
	// alert(statval);
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
function statelistload(ar) {
	var objects_state;
	ar=ar.replace(/&quot;/ig,'"');
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
        onchangeCitycodeAgency();
        return item;
    }
});

 $('#state_txt_id').blur(function(){
		if (typeof(objects_state) != 'undefined' && objects_state.indexOf(this.value)!=-1) {
        }else{
        	  $('#state_txt_id').val('');
        	  $('#state_txt_id').focus();
	          $('#state_hidd_id').val('');

	         /*  $('#country_txt_id').val('');
        	  $('#country_txt_id').typeahead('destroy');
	          $('#country_hidd_id').val(''); */
	         // citylist
	          $('#city_txt_id').val('');
	          $('#city_hidd_id').val('');
	          $('#city_txt_id').typeahead('destroy');
// 	          $('#pstlcode_txt_id').val('');
//         	  $('#pstlcode_txt_id').typeahead('destroy');
// 	          $('#pstlcode_hidd_id').val('');

          }
      });
	}
	//City Load
	function onchangeCitycodeAgency(){
		var statval = $("#state_hidd_id").val();
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

	 function citylistload(arr) {
		var objects_city;
		arr=arr.replace(/&quot;/ig,'"');
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
// 	        onchangePstlcodeAgency();
	        return item;
	    }
	 });

	 	$('#city_txt_id').blur(function(){
			if (typeof(objects_city) != 'undefined' &&  objects_city.indexOf(this.value)!=-1) {
	        }else{
	        	  $('#city_txt_id').val('');
	        	  $('#city_txt_id').focus();
		          $('#city_hidd_id').val('');

		         /*  $('#state_txt_id').val('');
	        	  $('#state_txt_id').typeahead('destroy');
		          $('#state_hidd_id').val(''); */

// 		          $('#pstlcode_txt_id').val('');
// 	        	  $('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');


	          }
	    });
    }

	//pin code loading
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

	function pstlcodelistload(arr) {
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
	       // onchangeCitycodeAgency();
	        return item;
	    }
	});

	 $('#pstlcode_txt_id').blur(function(){
			if (typeof(objects_pstl) != 'undefined' && objects_pstl.indexOf(this.value)!=-1) {
	        }else{
	        	  $('#pstlcode_txt_id').val('');
	        	  $('#pstlcode_txt_id').focus();
		          $('#pstlcode_hidd_id').val('');
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
 var cntload = false;
function myFunction2(check){
	 if(check=="add" && cntload==false){
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
		 //$('.imgaddminus1').css("display","inline");
		 //$('#otherhidden').show();
		 //$('.imgaddplus1').css("display","none");
	 }else if(check=="sub"){
		// $('.imgaddminus1').css("display","none");
		// $('.imgaddplus1').css("display","inline");
		// $('#otherhidden').hide();
	 }

}

 function cancelFunction(){
	$("#userCancelForm").attr("action", "companymgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>
