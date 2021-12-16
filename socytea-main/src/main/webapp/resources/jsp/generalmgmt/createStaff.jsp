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
          <!-- Search for small screen -->
          <jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Create Staff"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="staffmanagement"><s:text name="Breadcrumb.manage" /></a></li>
					<li><a href="staffmanagement"><s:text name="Breadcrumb.manage.staffmgmt" /></a></li>
                    <li class="active"><s:text name="Text.create.staff" /></li>

                   </ol>
              </div>
            </div>
          </div>
        </div>
       	<div class="container">
       	<div class="card-panel">
 <form method="post" id="registerformid" name="registerformid" action="createdstaff" enctype="multipart/form-data">
 		<div class="row">
									  <div class="col s12 m4 l3">
										    <input type="file" name="staffImage" id="staffImage" class="dropify" data-default-file="" />
           									 </div>
           									 <div class="col s12 m8 l9">
									<div class="row">
       									 <div class="input-field col s6">
                								<label for="staffName"><s:text name="Text.name"></s:text><span class="mandatory">*</span></label>
                									<s:textfield name="staffRegObj.staffName" id="staffName" cssClass="form-control firstnamevalidate"/>
        								</div>
        								 <div class="input-field col s1">
                								<label for="isdcodeid"><s:text name="Text.Form.IsdCde"></s:text><span class="mandatory">*</span></label>
                									<s:textfield cssClass="form-control IsdNumber"  name="staffRegObj.ISDcode" id="isdcodeid" value="%{getText('Text.ISD.value')}"></s:textfield>
                									<s:fielderror fieldName="usercreateObj.userName"/>
        								</div>
        								 <div class="input-field col s5">
                								<label for="mobilenoid"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
                									<s:textfield cssClass="form-control registerNumber " name="staffRegObj.staffMobno" id="mobilenoid"  ></s:textfield>
                								    <s:fielderror fieldName="staffRegObj.staffMobno"/>
        								</div>
    								</div>
    								<div class="row">
        								 <div class="input-field col s6">
                								<label for="card"><s:text name="Text.customerreg.idproof" /></label>
             										<s:textfield  name="idcatypetxt" id="card" cssClass="form-control typeahead tt-query idproofvalidate left" autocomplete="off"/>
			 									    <span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input  type="hidden" name="ID_CARD_TYP" id="cardid" class="form-control"/>
        								</div>
        								 <div class="input-field col s6">
                								<label for="idproofno"><s:text name="Text.customerreg.idproofno" /></label>
                									<s:textfield name="staffRegObj.staffIdcardno" id="idproofno" cssClass="form-control idproofnovalidate" />
                									<div id="card-error-div" class="error manualscriptvalidation" style="display:none;"></div>
        								</div>
    								</div>
    								<div class="row">
       		 							 <div class="input-field col s6">
                								<label for="companyname"><s:text name="Text.Companyname" /><span class="mandatory">*</span></label>
             										<s:textfield  name="staffRegObj.staffcompanyname" id="companyname" cssClass="form-control typeahead tt-query companynamevalidate left" autocomplete="off"/>
			 											<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('companyname');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input  type="hidden" name="staffRegObj.staffcompany_hidd_txt" id="companyname_hidd_id" class="form-control"/>
        								</div>
        								 <div class="input-field col s6">
                								<label for="emailid"><s:text name="Text.customerreg.emailid" /></label>
                								<s:textfield name="staffRegObj.staffEmail" id="emailid" cssClass="form-control emailidvalidate"/>
        								</div>
    								</div>
    								</div>
    								 <div class="input-field col s12">
    								<div class="row">
    								 <div class="input-field col s6">
                								<label for="gender" class="active"><s:text name="Text.customerreg.gender" /><span class="mandatory"></span></label>
                									<s:select id="gender" cssClass="form-control gendervalidate"
                          							list="#{'1':'Male','2':'Female','3':'Other' }" name="staffRegObj.staffGender">
                									</s:select>
        								</div>
        								 <div class="input-field col s6">
                								<label for="category">Category<span class="mandatory"></span></label>
             										<s:textfield  name="Categoryname" id="category" cssClass="form-control typeahead tt-query category" autocomplete="off"/>
             										<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('category');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
			 										<input  type="hidden" name="locIdCategoryid" id="categoryid" class="form-control"/>
        								</div>
    								</div>
    								<div class="row">
    								 <div class="input-field col s6">
                								<label for="workinghour"><s:text name="Work Hours" /><span class="mandatory"></span></label>
                										<s:textfield name="staffRegObj.Workinghours" id="workinghour" cssClass="form-control mobilevalidate"/>
        								</div>
        								 <div class="input-field col s6">
                								<label for="address2"><s:text name="Address"></s:text><span class="mandatory"></span></label>
                									<s:textfield name="staffRegObj.staffAddr" id="address2" cssClass="form-control addressvalidate"/>
        								</div>
    								</div>
    								<div class="row">
    								 <div class="input-field col s6">
                								<label for="country_txt_id"><s:text name="Menuheader.uam.profile.country" /></label>
            										<s:textfield name="staffCountryid" id="country_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off" />
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('country_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="Country_id" id="country_hidd_id" class="form-control"/>
            								</div>
       	 								 <div class="input-field col s6">
                								<label for="state_txt_id"><s:text name="Menuheader.uam.profile.state" /></label>
                 									<s:textfield name="staffStateid" id="state_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('state_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="State_id" id="state_hidd_id" class="form-control"/>
            								</div>
        								</div>
    								<div class="row">
    								 <div class="input-field col s6">
                								<label for="city_txt_id"><s:text name="Menuheader.uam.profile.city" /></label>
             										<s:textfield name="staffCityid" id="city_txt_id" cssClass="form-control typeahead tt-query left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('city_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden" name="City_id" id="city_hidd_id" class="form-control"/>
        								</div>
        								 <div class="input-field col s6">
                								<label for="pstlcode_txt_id"><s:text name="Text.customerreg.pincode" /></label>
              										<s:textfield name="pstlIdname" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query left"  autocomplete="off"/>
<%-- 														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('pstlcode_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> --%>
												<span class="input-group-addon txtbxdownarowicon left" ></span>
												<input type="hidden" name="pstlcode_id" id="pstlcode_hidd_id" class="form-control"/>
        								</div>
    									</div> <s:hidden name="StaffuniqueId" id="uniqStaffIdEdit"></s:hidden>
 									</div>
 									<div class="input-field col s6">
						<button type="submit" id="userCreateButtonId" class="<s:text name="button.color.submit"/>">
						<s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
						<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>
								</div>
							</form>
							<s:form method="post" id="userCancelForm"></s:form>
					 </div>
				</div>

				<!--end container-->

			</section>

		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
</body>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#pstlcode_txt_id").keyup(function(){
		textValidate(this,'10','MNM');
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
});
	$(document).ready(function(){
		
		$('#pstlcode_txt_id').blur(function(){
//	     	alert($('#pstlcode_txt_id').val());
			$('#pstlcode_hidd_id').val($('#pstlcode_txt_id').val());
		});
		
		 var exigndr = $("#gender").html();
		 $("#gender").html('<option value="" disabled selected>Choose your gender</option>'  + exigndr);
		 $("#gender").material_select();
		 $("#staffName").keyup(function(){
		    	textValidate(this,'30','OA');
		 });
		$("#imgdivid").click(function(){
			$("#staffImage").click();
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
		    	textValidate(this,'30','EML');
		    });
		    $("#firstname").keyup(function(){
		    	textValidate(this,'30','OA');
		    });
		    $("#companyname").keyup(function(){
		    	textValidate(this,'100','OA');
		    });
		    $("#address2").keyup(function(){
		    	textValidate(this,'200','ADP');
		    });

		    $("#workinghour").keyup(function(){
		    	textValidate(this,'3','PHNM');
		    });
		    $("#mobilenoid").blur(function(){
		    	toValiMobno(this.id);
		 	});
		    $("#emailid").blur(function(){
			   	 toValiEmail(this.id);
			 });
		    $('#registerformid').validate({
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
		       	$("#staffName").rules("add", {
		       		required : true,
		       		minlength : 2,
					maxlength : 50,
		       		messages : {
		       			required :"<s:text name="Error.staffreg.name" />",
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
		    	$("#isdcodeid").rules("add", {
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
		       		required : "<s:text name="Error.customerreg.mobileno" />",
		       		}
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
		//ajax loading
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
	 				minLength : 0,
	 	 			highlight: true,
	 	 			showHintOnFocus:true,
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
 		if (typeof(objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
         }else{
         	  $('#card').val('');
         	  $('#card').focus();
 	          $('#cardid').val('');
           }
       });
 	}
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
//     	  		$('#pstlcode_txt_id').typeahead('destroy');
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
// 	          $('#pstlcode_txt_id').val('');
//     	  	  $('#pstlcode_txt_id').typeahead('destroy');
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
		        	/*   $('#city_txt_id').val('');
		        	 // $('#city_txt_id').focus();
			          $('#city_hidd_id').val(''); */
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
		        	  /* $('#pstlcode_txt_id').val('');
		        	  $('#pstlcode_txt_id').focus();
			          $('#pstlcode_hidd_id').val(''); */
		          }
		      });
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
 function cancelFunction(){
	$("#userCancelForm").attr("action", "staffmgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>