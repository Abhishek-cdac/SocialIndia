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
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/timepicker_min.css" />
<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
           <!--  <div class="header-search-wrapper grey hide-on-large-only">
                <i class="mdi-action-search active"></i>
                <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore Materialize">
            </div> -->
              <!-- Search for small screen -->
           <jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Township Modify"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome" /></a></li>
					<li><a href="townshipmgmt"><s:text name="Breadcrumb.manage" /></a></li>
					<li><a href="townshipmgmt"><s:text name="Breadcrumb.manage.townshipmgmt" /></a></li>
                    <li class="active"><s:text name="Township Modify" /></li>
                   </ol>
              </div>
            </div>
          </div>
        </div>
       	<div class="container">
       	<div class="card-panel">
       	<form id="residentCreationFormId" name="residentCreationFormId"	action="townshipEditUpdate" method="post" enctype="multipart/form-data">
							<div class="row" >
								<div class="input-field col s6">
									<label class="active" for="township_txt_id"><s:text name="Text.adduser.townshipname"></s:text><span class="mandatory">*</span></label>
									<s:textfield id="township_txt_id" name="townShipMst.townshipName" cssClass="form-control typeahead tt-query townshipIdvalidate" autocomplete="off" />
									<s:fielderror fieldName="townshipObj.townshipName" />
								</div>
								<div class="input-field col s6">
									<label class="active" for="builderName">Builder Name<span class="mandatory">*</span></label>
									<s:textfield name="townShipMst.builderName" id="builderName" cssClass="form-control typeahead tt-query townshipIdvalidate" autocomplete="off" />
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<s:textfield name="townShipMst.noOfSociety" id="nosoc" cssClass="form-control typeahead tt-query townshipIdvalidate"/>
									<label class="active" for="nosoc">No. Of Society<span class="mandatory">*</span></label>
								</div>
								<div class="input-field col s6">
									<s:textfield name="townShipMst.noOfFlats" id="noofflat" cssClass="form-control typeahead tt-query townshipIdvalidate"/>
									<label class="active" for="noofflat">No. Of Flats<span class="mandatory">*</span></label>
								</div>
							</div>
							<div class="row">
							<div class="input-field col s2">
									<label class="active" for="IsdNumber" ><s:text name="Text.customerreg.isd" /><span class="mandatory">*</span></label>
									<s:textfield name="townShipMst.isdCode" id="IsdNumber" cssClass="form-control typeahead tt-query townshipIdvalidate"></s:textfield>
								</div>
								<div class="input-field col s4">
									<label class="active" for="mnono" class=" control-label"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
									<s:textfield name="townShipMst.mobileNo" id="mnono" cssClass="form-control typeahead tt-query townshipIdvalidate"></s:textfield>
								</div>
								<div class="input-field col s6">
									<s:textfield name="townShipMst.address" id="address" cssClass="form-control typeahead tt-query townshipIdvalidate"/>
									<label class="active" for="address">Address</label>
								</div>

							</div>

							<div class="row">
								<div class="input-field col s6">
									<label class="active" for="country_txt_id"><s:text	name="Menuheader.uam.profile.country" /><span class="mandatory">*</span></label>
									<%-- <s:textfield name="staffCountryid" id="country_txt_id" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false" />
									<input type="hidden" name="countryId" id="country_hidd_id" class="form-control" /> --%>
									<s:textfield name="townShipMst.countryFullName"  id="country_txt_id" cssClass="form-control typeahead tt-query country left"  autocomplete="off" />
									<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
									<s:hidden name="countryCode" value="%{countryMst.countryId}" id="country_hidd_id" class="form-control"/>
								</div>
								<div class="input-field col s6">
									<label class="active" for="state_txt_id"><s:text name="Menuheader.uam.profile.state" /><span class="mandatory">*</span></label>
									<s:textfield  name="townShipMst.stateFullName" id="state_txt_id" cssClass="form-control statevalidate left" autocomplete="off" onkeyup="statecheck();"/>
									<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
									<s:hidden  name="stateCode" value="%{stateData.stateId}" id="state_hidd_id" class="form-control "/>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<label class="active" for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /><span class="mandatory">*</span></label>
									<s:textfield  name="townShipMst.cityFullName" id="city_txt_id" cssClass="form-control city left" autocomplete="off" onkeyup="citycheck();"/>
									<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
									<s:hidden name="cityCode" value="%{cityData.cityId}" id="city_hidd_id" class="form-control"/>

								</div>
								<div class="input-field col s6">
									<label for="pstlcode_txt_id"><s:text name="Menuheader.uam.profile.pincode" /><span class="mandatory">*</span></label>
									<s:textfield name="townShipMst.pinFullCode" id="pstlcode_txt_id" cssClass="form-control pincode left" autocomplete="off" onkeyup="pstlcodecheck();"/>
<%-- 									<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
									<span class="input-group-addon txtbxdownarowicon left" ></span>
									<s:hidden name="postalCode"  value="%{postalData.pstlId}" id="pstlcode_hidd_id" class="form-control"/>

								</div>
							</div>
							<div class="row">
							   <div class="input-field col s6">
									<label class="active" for="emailid"><s:text name="Text.customerreg.emailid" /></label>
									<s:textfield name="townShipMst.emailId" id="emailid" cssClass="form-control" autocomplete="off" />
								</div>
								<div class="input-field col s6">
									<!-- <s:textfield name="townShipMst.landMark" id="lanmardTxtid"/>
									<label class="active" for="lanmardTxtid">Land Mark <div class="mdi-maps-place" onclick="toShowGmap('lanmardTxtid')"></div></label>
									<s:fielderror fieldName="usercreateObj.groupId.groupCode" />-->
									<div class="form-group" id="usernamedivid">
									<label for="lanmardTxtid" class="left control-label">Landmark</label>
									<s:textfield cssClass="form-control pinCode left" name="townShipMst.landMark" id="lanmardTxtid"></s:textfield>
									<span class="input-group-addon timepickericon tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Location" onclick="toShowGmap('lanmardTxtid')"><i class="mdi-maps-place pink-text small"></i></span>
									<!-- <div class="mdi-maps-place" onclick="toShowGmap('lanmardTxtid')"><span target="_black" title="Location" class="mapicon"></span></div><span></span></a></label> -->
									<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
								</div>

								</div>

							</div>
							<div class="row">
								<div class="input-field col s6">
									<s:textfield name="townShipMst.townshipcolourcode" id="colorcode" />
									<label class="active" for="colorcode">Color Code (Hex)</label>
								</div>
								<div class="input-field col s6">
									<s:textfield name="townShipMst.imprintName" id="Imprint"/>
									<label class="active" for="Imprint">Imprint Name</label>
								</div></div>
								<div class="row">
									<div class="input-field col s6">
								<label class="active" for="logoImage">Upload Logo</label> <div class="clear height10px"></div>
                  <div class="">
                  <input type="file" id="logoImage" name="logoImage" class="dropify" data-default-file="/templogo/township/web/<s:property value="townShipMst.townshipId"/>/<s:property value="townShipMst.townshiplogoname"/>">
                  </div>
              </div>
              	<div class="input-field col s6">
				<label class="active" for="icoImage">Upload ICO Image</label> <div class="clear height10px"></div>
                  <div class="">
                  <input type="file" id="icoImage" name="icoImage" class="dropify" data-default-file="/templogo/township/web/<s:property value="townShipMst.townshipId"/>/<s:property value="townShipMst.townshipiconame"/>">
                  </div>
					</div>
						</div>
						<s:hidden value="%{townShipMst.townshipId}" name="townShipMst.townshipId"></s:hidden>
						<div style="clear: both; height: 10px;"></div>
						<div class="row">
						<div class="input-field col s6">
						<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>" onclick="valsetFunction();">
						<s:text name="Text.button.update"/><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>
					</div>
					</form>
       </div>

					<s:form method="post" id="userCancelForm"></s:form>
				</div>

				<!--end container-->

			</section>

		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/googlemap.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>

    <script type="text/javascript">
   function loadFile(event) {
	  $('#userimgdivid').hide();
	  $('#userimgdivid1').hide();
	  $('#userimgchange').show();
	  $("#logoImage").click();
    };
    function loadFileIco(event) {
    	$('#icoImage').val('');
    	  $('#usericoimg').hide();
    	  $('#usericoimg1').hide();
    	  $('#usericoimgchange').show();
    	  $("#icoImage").click();

    };
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
    	
        $('#pstlcode_txt_id').blur(function(){
//         	alert($('#pstlcode_txt_id').val());
			$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
		});

        // Used events
        var drEvent = $('.dropify-event').dropify();

        drEvent.on('dropify.beforeClear', function(event, element){
            return confirm("Do you really want to delete \"" + element.filename + "\" ?");
        });

        drEvent.on('dropify.afterClear', function(event, element){
            alert('File deleted');
        });

        $('#residentCreationFormId').validate({
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
       	$("#township_txt_id").rules("add", {
       		required : true,
       		minlength :5,
       		maxlength :50,
       		messages : {
       			required :"<s:text name="Error.customerreg.townshipId"/>",
       		}

       	});

        	$("#builderName").rules("add", {
       		required : true,
       		minlength :5,
       		maxlength :50,
       		messages : {
       			required :"<s:text name="Error.townshipmgmt.builder.name" />",
       		}
       	});
       	$("#nosoc").rules("add", {
       		required : true,
       		number : true,
       		minlength : 1,
			maxlength : 4,
       		messages : {
       			required :"<s:text name="Error.townshipmgmt.noof.society" />",
       		}
       	});

       	$("#noofflat").rules("add", {
       		required : true,
       		number : true,
       		minlength : 1,
			maxlength : 4,
       		messages : {
       			required : "<s:text name="Error.townshipmgmt.noof.flats" />",
       		}
       	});
    	$("#IsdNumber").rules("add", {
       		required : true,
       		minlength : 1,
			maxlength : 4,
       		messages : {
       			required :"<s:text name="Error.customerreg.isd" />",
       		}
       	});
       	$("#mnono").rules("add", {
       		required : true,
       		number : true,
       		minlength : 8,
			maxlength : 15,
       		messages : {
       			required : "<s:text name="Error.customerreg.mobileno" />",
       		}
       	});
      	$("#country_txt_id").rules("add", {
       		required : true,
       		messages : {
       			required : "<s:text name="Error.customerreg.countryid"/>",
       		}
       	});
      	$("#state_txt_id").rules("add", {
       		required : true,
       		messages : {
       			required : "<s:text name="Error.customerreg.stateid"/>",
       		}
       	});
      	$("#city_txt_id").rules("add", {
       		required : true,
       		messages : {
       			required : "<s:text name="Error.customerreg.cityid" />",
       		}
       	});
      	$("#pstlcode_txt_id").rules("add", {
       		required : true,
       		messages : {
       			required : "<s:text name="Error.customerreg.pincode" />",
       		}
       	});

    });

    $(document).ready(function(){
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
		 $("#township_txt_id").keyup(function(){
				textValidate(this,'100','AI');
			});
		 $("#builderName").keyup(function(){
				textValidate(this,'100','AI');
			});
		 $("#nosoc").keyup(function(){
				textValidate(this,'4','MNM');
			});
		 $("#noofflat").keyup(function(){
				textValidate(this,'4','MNM');
			});
		 $("#IsdNumber").keyup(function(){
				textValidate(this,'4','MNM');
			});
		 $("#mnono").keyup(function(){
				textValidate(this,'15','MNM');
			});
		 $("#address").keyup(function(){
				textValidate(this,'200','ADP');
			});
		 $("#country_txt_id").keyup(function(){
				textValidate(this,'100','OA');
			});
		 $("#state_txt_id").keyup(function(){
				textValidate(this,'100','OA');
			});
		 $("#city_txt_id").keyup(function(){
				textValidate(this,'100','OA');
			});
		 $("#pstlcode_txt_id").keyup(function(){
				textValidate(this,'10','MNM');
			});
		 $("#emailid").keyup(function(){
				textValidate(this,'100','EML');
			});
		 $("#emailid").blur(function(){
			 toValiEmail(this.id);
		 });

	});
	function CountrylistFun(ar)
	{
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
// 	          $('#pstlcode_txt_id').val('');
// 	  		$('#pstlcode_txt_id').typeahead('destroy');
// 	          $('#pstlcode_hidd_id').val('');

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
// 		          $('#pstlcode_txt_id').val('');
// 	        	  $('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');
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
// 	          $('#pstlcode_txt_id').val('');
// 	  	  $('#pstlcode_txt_id').typeahead('destroy');
// 	          $('#pstlcode_hidd_id').val('');

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
// 		          $('#pstlcode_txt_id').val('');
// 	        	  $('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');

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
// 		        $('#pstlcode_txt_id').val('');
// 		          $('#pstlcode_txt_id').typeahead('destroy');
// 		          $('#pstlcode_hidd_id').val('');

// 		        onchangePstlcodeAgency();
		        return item;
		    }
		});

		 $('#city_txt_id').blur(function(){
				if (typeof(objects_city) != "undefined" && objects_city.indexOf(this.value)!=-1) {
		        }else{
		        	   $('#city_txt_id').val('');
		        	  $('#city_txt_id').focus();
			          $('#city_hidd_id').val('');
// 		        	  $('#pstlcode_txt_id').typeahead('destroy');
// 			          $('#pstlcode_hidd_id').val('');

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
// 		        	   $('#pstlcode_txt_id').val('');
// 		        	  $('#pstlcode_txt_id').focus();
// 			          $('#pstlcode_hidd_id').val('');
		          }
		      });
			}
		function cancelFunction(){
			$("#userCancelForm").attr("action", "townshipmgmt");
			$("#userCancelForm").submit();
			$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
			toShowLoadingImgoverlay();
			$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
		}

		function statecheck(){
			var countryid=$("#country_hidd_id").val();
			if(countryid=="" && countryid!=null){
				$('#state_txt_id').val('');
		          $('#city_txt_id').val('');
// 		          $('#pstlcode_txt_id').val('');
			}
		}
		function citycheck(){
			var stateid=$("#state_hidd_id").val();
			if(stateid=="" && stateid!=null){
		          $('#city_txt_id').val('');
// 		          $('#pstlcode_txt_id').val('');
			}
		}
		function pstlcodecheck(){
			var pstlcode=$("#city_hidd_id").val();
			if(pstlcode=="" && pstlcode!=null){
// 		          $('#pstlcode_txt_id').val('');
			}
		}
    </script>
</body>
</html>
