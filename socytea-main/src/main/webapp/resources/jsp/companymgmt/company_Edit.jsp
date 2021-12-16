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
<!-- For Windows Phone -->
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
								<h5 class="breadcrumbs-title"><s:text name="Text.breadcrumb.companymodify" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="companymgmt"><s:text name="Breadcrumb.manage" /></a></li>
							<li><a href="companymgmt"><s:text name="Breadcrumb.manage.cmpymgmt" /></a></li>
							<li class="active"><s:text name="Text.breadcrumb.companymodify" /></li>
						</ol>
					</div>
				</div>
				</div>
				</div>
				 <!--start container-->
	<div class="container">
	<div id="jqueryvalidation" class="section">
	<div class="card-panel">
<form  id="CompanymodifyFormId" name="CompanymodifyFormId" action="companyEditSubmitaction" method="post"  enctype="multipart/form-data" >
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-social-domain tinysmall white-text text-accent-4"></i> <s:text name="Text.cmpny.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">
								<!-- 		  <div class="imgaddplus" onclick="myFunction1('add');" style="display: none;">
								<i class="mdi-content-add-circle tinysmall "
									style="color: #ff4081; float: left; cursor: pointer;"></i>
								<div class="spacialspace">Profile Detail :</div>
							</div>  onclick="myFunction1('sub');"-->
							<!-- <div id=""><div class="imgaddminus"><i class="<s:text name="button.color.minushideshow"></s:text>"></i>
							<span class="spacialspace">Profile Detail</span>
							</div></div><div style="clear: both; height:5px;"></div> -->

						<div id="profilehidden" style="display:block; margin:15px;">
							<div class="row">
							<div class="col s12 m4 l3">
										    <input type="file" id="staffImage"  name="staffImage" class="dropify" data-default-file="/templogo/company/web/<s:property value="cmpyRegObj.companyId"/>/<s:property value="#session.staffProfileImage" />" />
         					</div>
             				 <div class="col s12 m8 l9">
											<div class="row rowmargin12px" >
										 <div class="input-field col s6">
																<label for="companyname"><s:text name="Text.name"></s:text><span class="mandatory">*</span></label>
																<s:textfield name="cmpyRegObj.companyName" id="companyname"  cssClass="form-control firstnamevalidate" readonly="readonly"/>
															</div>

  																	 <div class="input-field col s6">
																<label for="emailid"><s:text name="Text.customerreg.emailid" /><span class="mandatory"></span></label>
																<s:textfield name="cmpyRegObj.companyEmail" id="emailid"  cssClass="form-control emailidvalidate" readonly="readonly"/>
															</div>
															</div>

 										<div class="row rowmargin12px" >
										 <div class="input-field col s2">
												<label for="isdcodeid"><s:text name="Text.customerreg.isd"></s:text><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control IsdNumber"  name="cmpyRegObj.isdCode"
												id="isdcodeid" readonly="true"></s:textfield>
												<s:fielderror fieldName="usercreateObj.ivrBnLBR_ISD_CODE"/>
											</div>
											<div class="input-field col s4">
												<label for="mobilenoid"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber " name="cmpyRegObj.mobileNo"
												id="mobilenoid"  readonly="true"></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>



										 <div class="input-field col s6">
												<label for="cmpnyRegno"><s:text name="Text.company.regno" /><span class="mandatory">*</span></label>
												<s:textfield  name="cmpyRegObj.cmpnyRegno" id="cmpnyRegno" readonly="true" cssClass="form-control typeahead tt-query cmpnyRegnovalidate" autocomplete="off"/>
										</div>
										</div>
										<div class="row rowmargin12px" >
																 <div class="input-field col s6">
																		<label for="keysearchid"><s:text name="Text.company.keysearch" /></label>
																		<s:textfield name="cmpyRegObj.keyforSrch"  id="keysearchid"  cssClass="form-control keyvalidate" />
																	</div>

															 <div class="input-field col s6">
																<label for="genter_select" class="active"><s:text name="Status"></s:text><span class="mandatory"></span></label>
																<div class="clear height10px"></div>
																 <div id="genter_select" class="row">
                                                                 <s:radio name="cmpyRegObj.verifyStatus" id="gender" list="#{'1':'Verified','0':'Unverified'}" />
                                                                 </div>
															</div>
										</div></div></div>
				</div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />" onclick="myFunction2('add');"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>
<div class="collapsible-body padding10px" id="seconddivid" >
<!-- <div style="clear: both; height:5px;"></div>
							<div id=""><div class="imgaddplus1" onclick="myFunction2('add');"><i class="<s:text name="button.color.addhideshow"/>"></i>
								<div class="spacialspace">Other Detail </div>
							</div>
							<div class="imgaddminus1" onclick="myFunction2('sub');"
								style="display: none; cursor: pointer;">
								<i class="<s:text name="button.color.minushideshow"></s:text>"></i>
								<span class="spacialspace">Other Detail </span>
							</div></div><div style="clear: both; height:5px;"></div> -->
							<div id="otherhidden" style="display:block; margin:15px;">
													     <div class="row rowmargin12px" >
																 <div class="input-field col s6">
																		<label for="address1"><s:text name="Address1" /><span class="mandatory"></span></label>
																		<s:textfield name="cmpyRegObj.address1"  id="address1"
																			cssClass="form-control " />
																	</div>

  														  <div class="input-field col s6">
																<label for="address2"><s:text name="Address2"></s:text><span class="mandatory"></span></label>
																<s:textfield name="cmpyRegObj.address2" id="address2"  cssClass="form-control " />
															</div>
  																</div>
 															  <div class="row rowmargin12px" >
																 <div class="input-field col s6">
												          <label for="country_txt_id"><s:text name="Menuheader.uam.profile.country" /></label>
														<s:textfield name="cmpyRegObj.countryname" id="country_txt_id" cssClass="form-control typeahead tt-query left"  autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="cmpyRegObj.countryId" id="country_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
  														   <div class="input-field col s6">
												          <label for="state_txt_id"><s:text name="Menuheader.uam.profile.state" /></label>
													       <s:textfield name="cmpyRegObj.statename" id="state_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="cmpyRegObj.stateId" id="state_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
											            </div>
 															 <div class="row rowmargin12px" >
																 <div class="input-field col s6">
												          <label for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /></label>
													       <s:textfield name="cmpyRegObj.cityname" id="city_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="cmpyRegObj.cityId" id="city_hidd_id" class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
 															  <div class="input-field col s6">
												          <label for="pstlcode_txt_id"><s:text name="Text.customerreg.pincode" /></label>
													        <s:textfield name="cmpyRegObj.pincodename" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left"  autocomplete="off"/>
<%-- 														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
<span class="input-group-addon txtbxdownarowicon left" ></span>
												<s:hidden name="cmpyRegObj.pstlId" id="pstlcode_hidd_id"  class="form-control"/>
												          <s:fielderror fieldName="usercreateObj.groupId.groupCode"/>
											            </div>
											            </div>

														 <div class="row rowmargin12px" >
																 <div class="input-field col s12">
												<label><s:text name="Description" /><span class="mandatory"></span></label>
												<s:textarea cssClass="materialize-textarea" name="cmpyRegObj.descr" />
												<s:fielderror fieldName="passObj.currentpwd" />
											</div>
										</div>
										</div>
</div>
</li>
</ul>

									<input type="hidden" name="mySelect"id="mySelect" class="form-control " />
									<s:hidden name="companyuniqueId" id="deletelcompanyidEdit"></s:hidden>
									<s:hidden name="deletecompanyid" id="deletelcompanyidEdit"></s:hidden>
									<div class="row" style="clear: both;margin-left:5px;">
									<button type="button" id="cmpnyUpdateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.update" />
							<i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
									</div>

										</form>
										</div>
										</div>
										<s:form method="post" id="userCancelForm"></s:form>
										</div>
										</section>
									</div>
</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
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

$('#CompanymodifyFormId').validate({
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



$(document).ready(function(){
	$("#imgdivid").click(function(){
		$("#staffImage").click();
	});
	 onchangeStatecodeAgency();
	 onchangeCitycodeAgency();
// 	 onchangePstlcodeAgency();
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
	    $("#emailid").blur(function(){
	   	 toValiEmail(this.id);
	    });
	    $("#keysearch").keyup(function(){
	    	textValidate(this,'200','ADP');
	    });
		$("#cmpnyUpdateButtonId").click(function(){
			 $("#firstdivid").css("display","block");
			 $("#seconddivid").css("display","block");
			 $('#CompanymodifyFormId').attr("method","Post");
			 $('#CompanymodifyFormId').attr("action","companyEditSubmitaction");
			 $('#CompanymodifyFormId').attr("enctype","multipart/form-data");
			 $('#CompanymodifyFormId').submit();
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
			ar=ar.replace(/%27/ig,"'");
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
		        $('#state_txt_id').val('');
		          $('#state_hidd_id').val('');
		          $('#state_txt_id').typeahead('destroy');
		          $('#city_txt_id').val('');
		          $('#city_hidd_id').val('');
		          $('#city_txt_id').typeahead('destroy');
// 		          $('#pstlcode_txt_id').val('');
// 	      	  $('#pstlcode_txt_id').typeahead('destroy');
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
// 		          $('#pstlcode_txt_id').val('');
// 	      	  	  $('#pstlcode_txt_id').typeahead('destroy');
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

// 			          $('#pstlcode_txt_id').val('');
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
			        	 $('#city_txt_id').focus();
				          $('#city_hidd_id').val('');

// 				          $('#pstlcode_txt_id').val('');
// 				          $('#pstlcode_txt_id').typeahead('destroy');
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
					searchOnFocus: true,
					minLength : 0,
					highlight: true,
					showHintOnFocus:true,
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
			// selectexistingcat();
			// $('.imgaddminus1').css("display","inline");
		// $('#otherhidden').show();
		 //$('.imgaddplus1').css("display","none");
	}  else if(check=="sub"){
		 //$('.imgaddminus1').css("display","none");
		// $('.imgaddplus1').css("display","inline");
		// $('#otherhidden').hide();
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

 function cancelFunction(){
	$("#userCancelForm").attr("action", "companymgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>
