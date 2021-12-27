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
<jsp:include page="common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<jsp:include page="common/basiccss.jsp"></jsp:include>
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
		<jsp:include page="common/header.jsp"></jsp:include>
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<jsp:include page="common/menuBar.jsp"></jsp:include>
			<!-- //////////////////////////////////////////////////////////////////////////// -->
			<!-- START CONTENT -->
			<section id="content">
				<!--breadcrumbs start-->
				<div id="breadcrumbs-wrapper">
					 <!-- Search for small screen -->
            		<jsp:include page="common/searchexploremob.jsp"></jsp:include>
					<div class="container">
					
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">My Profile</h5>
								<ol class="breadcrumbs">
									<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<!-- 	<li><a href="userreg">UAM</a></li> -->
									<li class="active">My Profile</li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
				<div class="card-panel">
				<div id="basic-collections" class="section">
				<form id="profileformid" name="profileformid" enctype="multipart/form-data">
						<div class="row">
						  <!-- <div class="left" style="width: 20%;margin-top: 10px;"> -->
						  <div class="col s12 m4 l3">
                  <input type="file" id="documentfile" name="profileImage" class="dropify" data-default-file="/templogo/profile/web/<s:property value="#session.USERID"/>/<s:property value="#session.userProfileImage"/>" />                 
											
            </div>
           <!--  <div class="left" style="width: 80%;"> -->
           
					<div class="col s12 m8 l9">	
								
											<div id="">

								<div class="imgaddplus" onclick="myFunction1('add');"
									style="display: none;">
									<i class="mdi-content-add-circle tinysmall "
										style="color: #ff4081; float: left; cursor: pointer;"></i>
									<div class="spacialspace">Profile Detail </div>
								</div>

							</div>
							
							      <div style="clear: both; height: 5px; width: 5px;"></div>
							       
							       	<div id="profilehidden" style="display: block;">
											
												<div class="row"  style="margin-left: 12px;">
									              <div class="input-field col s6">
															<label for="firstname"><s:text name="uam.profile.fname"></s:text></label>
															<s:textfield name="custRegObj.firstName" id="firstname"
																cssClass="form-control firstnamevalidate" />
														</div>
													
													<div class="input-field col s6">
															<label for="lastname"><s:text name="uam.profile.lname" /></label>
															<s:textfield name="custRegObj.lastName" id="lastname" cssClass="form-control emailidvalidate" />
														
													</div>
												</div>
													<div class="row"  style="margin-left: 12px;">
									              <div class="input-field col s6">
															<label for="card" ><s:text name="Text.customerreg.idproof" /></label>
															<s:textfield  name="idcardtypename" id="card" cssClass="form-control typeahead tt-query idproofvalidate" autocomplete="off"/>
			 											<s:hidden name="idCardType" value="%{cardtype}" id="cardid" class="form-control"/>
															
													</div>
													<div class="input-field col s6">
															<label  for="cardnum"><s:text name="Text.customerreg.idproofno" /></label>
															<s:textfield name="custRegObj.idProofNo" id="cardnum"
																cssClass="form-control idproofnovalidate" />
																<div id="card-error-div" class="error manualscriptvalidation" style="display:none;"></div>
													</div>
												</div>
												
												<!-- <div id="otherhidden" style="margin: 10px 0px 0px 25px;"> -->
												<div class="row"  style="margin-left: 12px;">
									              <div class="input-field col s6">
															<label for="address1"><s:text name="uam.profile.address1"></s:text></label>
															<s:textfield name="custRegObj.address1" id="address1" cssClass="form-control firstnamevalidate" />
														</div>
													 <div class="input-field col s6">
															<label for="address2"><s:text name="uam.profile.address2" /></label>
															<s:textfield name="custRegObj.address2" id="address2"
																cssClass="form-control emailidvalidate" />
														</div>
												</div></div>
												<div id="otherhidden" style="margin: 10px 0px 0px 25px;">
											<div class="row">
											  <div class="input-field col s6">
															<label  for="country_txt_id" class="control-label"><s:text
																	name="Menuheader.uam.profile.country" /></label>
															<s:textfield name="countryName" id="country_txt_id" cssClass="form-control typeahead tt-query"  autocomplete="off" />
														<s:hidden name="countryCode" id="country_hidd_id" class="form-control"/>														
															<s:fielderror fieldName="usercreateObj.groupId.groupCode" />

													</div>

													  <div class="input-field col s6">
															<label  for="state_txt_id" class="control-label"><s:text
																	name="Menuheader.uam.profile.state" /></label>
														<s:textfield name="stateName" id="state_txt_id" cssClass="form-control typeahead tt-query"  autocomplete="off"/>
														<s:hidden name="stateCode" id="state_hidd_id" class="form-control"/>														
															<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
													
													</div>

												</div>
												<div class="row">
											  <div class="input-field col s6">
													
															<label  for="city_hidd_id" class="control-label"><s:text
																	name="Menuheader.uam.profile.city" /></label>
																	<s:textfield name="cityName" id="city_txt_id" cssClass="form-control typeahead tt-query"  autocomplete="off"/>
														<s:hidden name="cityCode" id="city_hidd_id" class="form-control"/>  														
															<s:fielderror fieldName="usercreateObj.groupId.groupCode" />
														</div>
													
													  <div class="input-field col s6">
															<label  for="pstlcode_txt_id" class="control-label"><s:text
																	name="Text.customerreg.pincode" /></label>
																	<s:textfield name="pincodeNo" id="pstlcode_txt_id" cssClass="form-control typeahead tt-query"  autocomplete="off"/>
														<s:hidden name="postalCode" id="pstlcode_hidd_id" class="form-control"/>														
													
												</div>
												</div>
												<div class="row">		
												<div class="input-field col s6">
											 <label class="active" for="accessMedia"><s:text name="Text.accesschannel" /><span class="mandatory"></span></label>	
											 <p style="margin-left: -10px;">										
                                              <s:checkboxlist list="#{'1':'Web','2':'Mobile','3':'Both'}" name="custRegObj.accessMedia" id="accessMedia" cssClass="myCheckbox"/>
                                              </p>
											</div></div> 
											</div> 
											<s:hidden name="custRegObj.userId" value="%{custRegObj.userId}"> </s:hidden>
										 <div style="clear: both; height: 25px;"></div>
							<button type="submit" id="userCreateButtonId" style="margin-left: 24px;" class="<s:text name="button.color.submit"/>">
								<s:text name="Text.button.update" />
								<i class="<s:text name="button.icon.submitcard"/>"></i>
							</button>
							
							<button type="button" id="userCancelButtonId"
								class="<s:text name="button.color.cancel"/>"
								onclick="cancelFunction();">
								<s:text name="Text.button.cancel" />
								<i class="<s:text name="button.icon.replycard"/>"></i>
							</button>
					</div>
					</div>
					</form>
					</div></div>
					<div class="clear"></div>
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				</section>
				<!--end container-->
				</div>
		</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
	<jsp:include page="./common/allbasicjs.jsp"></jsp:include>	 
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>	
</body>
<script type="text/javascript">
function imagechange(){
	 $(".dropify").click();
	 $(".imgtbl_stfcrt").show();
	 $("#preimg").hide();
	 $("#imgdivid").hide();
}

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
		$("#userCreateButtonId").click(function(){
			
			//start: card validation
			var cardtyp = $("#card").val();
			var cardno = $("#cardnum").val();
			
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
			
			toShowLoadingImgoverlay();
			$("#profileformid").attr("method","Post");
			$("#profileformid").attr("action","profileUpdate");
		});


		
        $('input[type="checkbox"]').click(function(){
            if($(this).prop("checked") == true){
                $(this).siblings('input:checkbox').prop('checked', false);
            }
            else if($(this).prop("checked") == false){
            	 $(this).prop('checked', true);
            }
            });
  
	$("#firstname").keyup(function(){
    	textValidate(this,'30','OA');
    });
	
	$("#cardnum").keyup(function(){
		$("#card-error-div").hide();
    });
	
    $("#lastname").keyup(function(){
    	textValidate(this,'30','OA');
    });
    $("#idproofno").keyup(function(){
    	textValidate(this,'30','DOC');
    });
    $("#address1").keyup(function(){
    	textValidate(this,'100','DOC');
    });
    $("#address2").keyup(function(){
    	textValidate(this,'100','DOC');
    });
	$("#imgdivid").click(function(){		
		$("#profileImage").click();
	});
	
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
			url : 'getcountryvalue',
			data : '',
			success : function(data) {
				var arr=data.split("!_!");					
				CountrylistFun(arr[1]);
			}
		}); 
	 onchangeStatecodeAgency();	
	 onchangeCitycodeAgency();
	 onchangePstlcodeAgency();
	 
});

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
          $('#pstlcode_txt_id').val('');        	         	 
  	  $('#pstlcode_txt_id').typeahead('destroy');
          $('#pstlcode_hidd_id').val('');
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
	          $('#pstlcode_txt_id').val('');        	         	 
        	  $('#pstlcode_txt_id').typeahead('destroy');
	          $('#pstlcode_hidd_id').val('');
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
function statelistload(ar){
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
          $('#pstlcode_txt_id').val('');        	         	 
  	  	  $('#pstlcode_txt_id').typeahead('destroy');
          $('#pstlcode_hidd_id').val('');
        
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
	          $('#pstlcode_txt_id').val('');        	         	 
        	  $('#pstlcode_txt_id').typeahead('destroy');
	          $('#pstlcode_hidd_id').val('');
	              
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
	          
	          $('#pstlcode_txt_id').val('');       	         	 
	          $('#pstlcode_txt_id').typeahead('destroy');
	          $('#pstlcode_hidd_id').val('');
	        onchangePstlcodeAgency();
	        return item;
	    }
		}); 
	 $('#city_txt_id').blur(function(){				 
			if (typeof(objects_city) != "undefined" && objects_city.indexOf(this.value)!=-1) {						
	        }else{            		      			         
	        	 $('#city_txt_id').val('');
	        	 $('#city_txt_id').focus(); 
		          $('#city_hidd_id').val('');
		          
		          $('#pstlcode_txt_id').val('');       	         	 
		          $('#pstlcode_txt_id').typeahead('destroy');
		          $('#pstlcode_hidd_id').val('');
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
	
	
	
	
var loadFile = function(event) {
	var output = document.getElementById('myImg');
	output.src = URL.createObjectURL(event.target.files[0]);
};
	function cancelFunction() {
		$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
		$("#userCancelForm").attr("action", "posthomepage");
		$("#userCancelForm").submit();
	}
</script>
</html>
