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
<style type="text/css">
	#token-input-invitefriend { width: 100% ! important; }
	.token-input-list-facebook { min-height: 0px ! important;}
</style>
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
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.reusegatepass" /></h5>
								<ol class="breadcrumbs">
							 <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
				<li><a href="Issuegatepassmgmttbl"><s:text name="Breadcrumb.manage.gatepass"/></a></li>
                    <li> <a href="Issuegatepassmgmttbl"><s:text name="Breadcrumb.manage.issuegatepass" /></a></li>
                     <li class="active"><s:text name="Breadcrumb.manage.reusegatepass" /></li>
						</ol>
					</div>
				</div>
				</div>
				</div>
				<div class="container">
				 <div id="jqueryvalidation" class="section">
					<div class="card-panel">
					<form  role="form" id="gatepassreuseFormId" action="gatepasscreation" method="post" enctype="multipart/form-data">

									<div class="row">
									<div class="input-field col s6">
												<label for="visitorname"><s:text name="Text.visitorname" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control  " name="gatepassObj.visitorName"
												id="visitorname" onkeyup="textValidate(this,'100','OA')"></s:textfield>

											</div>
										<div class="input-field col s2">
												<label for="isdcodeid"><s:text name="Text.Form.IsdCode"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control isdNo" value="%{getText('Text.ISD.value')}" name="isdCode"
												id="isdcodeid" ></s:textfield>
												<s:fielderror fieldName="gatepassObj.userName"/>
											</div>
											<div class="input-field col s4">
												<label for="mobilenoid"><s:text name="Text.visitormobno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control mobilevalidate " name="gatepassObj.mobileNo" onkeyup="textValidate(this,'15','PHNM')"
												id="mobilenoid"></s:textfield>
												<s:fielderror fieldName="gatepassObj.mobileNo"/>
											</div>



									</div>
									<div class="row">
											<div class="input-field col s6">
											<label for="issuedate"><s:text name="Text.datevisit"/></label>
                								<s:textfield id="issuedate" type="text" cssClass="futuredateOfBirth" name="gatepassObj.issueDate" onkeyup="textValidate(this,'20','DT')"/>

											</div>
											<div class="input-field col s6 passtypeone">
											<div>
											<div class="input-group bootstrap-timepicker timepicker col s12 "> </div>
											<label for="timepickerstarttime" class=" control-label"><s:text name="Text.time"/></label>
            								<input id="timepickerstarttime" type="text" name="gatepassObj.issueTime" class="form-control input-small evestarttime left"/>
            								<span id="startclockspanid" class="input-group-addon timepickericon tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Enter Start Time"><i class="mdi-action-query-builder pink-text small"></i></span>

											</div>
											</div>
											<div class="input-field col s6 passtypetwo">
											<label for="passexpirydate"><s:text name="Text.expirydate"/><span class="mandatory passtypetwo" style="display:none;"></span></label>
                								<s:textfield id="passexpirydate" type="text" cssClass="futuredateOfBirth" name="gatepassObj.expiryDate" />

											</div>
											</div>

											<div class="row ">

											<div class="input-field col s6 ">
										<div id="genter_select" class="row">
										<label for="passtype" class="active">Pass Type<span class="mandatory">*</span></label>
											<div class="clear height10px"></div>
											<s:radio id="passtype" name="passType" list="#{'1':'Visitor','2':'Skilled Help'}" ></s:radio>
										</div>
									</div>
											<div class="input-field col s6 passtypeone">
												<label for="firstname"><s:text name="Text.accompanies" /></label>
												<s:textfield cssClass="form-control registerNumber" name="gatepassObj.noOfAccompanies"
												id="firstname" onkeyup="textValidate(this,'5','PHNM')" ></s:textfield>
												<s:fielderror fieldName="gatepassObj.userName"/>
											</div>

									<div class="input-field col s6 passtypetwo">
												<label for="card"><s:text name="Text.customerreg.idproof"/><span class="mandatory passtypetwo" style="display:none;"></span></label>
												<s:textfield name="idcardName" id="card" cssClass="typeahead tt-query"  autocomplete="off" spellcheck="false"/>
												<s:hidden id="cardid" name="gatepassObj.idcardtype" class="form-control" />

											</div>
									</div>

								<div class="row  passtypetwo">

												<div class="input-field col s6">
												<label for="idproofno"><s:text name="Text.customerreg.idproofno"/><span class="mandatory passtypetwo" style="display:none;"></span></label>
												<s:textfield cssClass="form-control  " name="gatepassObj.idCardNumber"
												id="idproofno" onkeyup="textValidate(this,'100','EML')"></s:textfield>

											</div>
											<div class="input-field col s6">
												<label for="skillid"><s:text name="Text.skillname" /><span class="mandatory passtypetwo" style="display:none;"></span></label>
												  <s:textfield name="skillName" id="skills_txt_id" cssClass="form-control typeahead tt-query" autocomplete="off" />
														<s:hidden name="gatepassObj.skillid" id="skills_hidd_id" class="form-control"/>
											</div>
								</div>

											<div class="row passtypetwo">


											<div class="input-field col s6">
												<label for="emailid"><s:text name="Text.customerreg.emailid"/></label>
												<s:textfield cssClass="form-control " name="gatepassObj.email"
												id="emailid" ></s:textfield>

											</div>
									<div class="input-field col s6">
													<label for="dateid"><s:text name="Text.resident.dob"/></label>
													<s:textfield  id="dateid" cssClass="dateOfBirth" name="gatepassObj.dateOfBirth"></s:textfield>
															</div>

											</div>

									<div class="row passtypetwo">

											</div>
											<div class="row passtypeone">
											<div class="input-field col s6">
												<label for="address1"><s:text name="Text.vehicleno"/></label>
												<s:textfield cssClass="form-control address" name="gatepassObj.vehicleNumber"
												id="address1"   onkeyup="textValidate(this,'50','EML')"></s:textfield>
												<s:fielderror fieldName="gatepassObj.address"/>
											</div>

                                            </div>
											<div class="row">
											<div class="input-field ">
												<label for="address1"><s:text name="Text.desc"/></label>
												<s:textarea cssClass="materialize-textarea validate" name="gatepassObj.description" id="evedesc" rows="2" style="resize:none;height:15px"></s:textarea>
											</div>

											</div>
											 <div class="clear height25px"></div>
											 <div class="row">
									     <div class="input-field col s12" >
										<div colspan="2"  id="documentfiledivid">
												<label for="documentfile" class="control-label lablenames active">Visitor Image</label>
												<span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformat"/>)</span>
												<div class ="clear height10px"></div>
												 <input type="file" id="documentfile" name="documentfile" data-maxfilesize="<s:text name="upload.file.size" />" data-default-file="/templogo/gatepass/web/<s:property value="#session.GATEPASSID"/>/<s:property value="#session.visitorimg" />" class="mrgnleft15 tooltipster documentfile dropify" style="margin-left: 0px;" title="Choose File" />
							<div id="documentfile-error" class="error manualscriptvalidation" style="display: none;">Document File Is Required</div>
										</div>
										</div>
                                     </div>
                                     <div class ="clear height25px"></div>
											 <div class="row">
											 <div class="col s12">
											<button type="submit" id="gatepassbuttonid" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.submit" />
							<i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class=" <s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
										</div></div>
										<s:hidden name="gatepassObj.passId"  />
										</form>

										<s:form method="post" id="userCancelForm"></s:form>
	</div>
						</div>
						</div>
						</div>
						</section>
						</div>
						</div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
		<%-- <jsp:include page="../common/googlemap.jsp"></jsp:include> --%>
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/timepicker_min.css" />
 <link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css?fcg" type="text/css" rel="stylesheet" media="screen,projection">
 <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
 <script type="text/javascript" src="<s:text name='Resource.path'/>/js/timepicker_min.js"></script>


<script>
var passtypeval ='<s:property value='passType'/>';

$(document).ready(function(){
	if (passtypeval==2) {
		$(".passtypetwo").show();
		$(".passtypeone").hide();
	} else {
		$(".passtypetwo").hide();
		$(".passtypeone").show();
	}
$("input[name=passType]").change(function(){
	passtypeval=$(this).val();
	if (passtypeval==2) {
		$(".passtypetwo").show();
		$('.passtypeone').hide();
	} else {
		$(".passtypetwo").hide();
		$(".passtypeone").show();
	}

});
	$("#startclockspanid").click(function(){ $("#timepickerstarttime").click(); });
	$("#endclockspanid").click(function(){$("#timepickerendtime").click(); });
    /*$('input[type=file]').drop_uploader({
        uploader_text: 'Drop files or image to upload, or',
        browse_text: 'Browse',
        browse_css_class: 'button button-primary',
        browse_css_selector: 'file_browse',
        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
        file_icon: '<i class="pe-7s-file"></i>',
        time_show_errors: 5,
        layout: 'thumbnails'
    });*/
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

	        $("#gatepassbuttonid").click(function () {

	        	if(passtypeval==1){
	        		/*alert("ddd==passtypeval"+passtypeval);  */
	        		$("#visitorname").rules("add", {
	         			required : true,
	         			messages : {
	         				required : "<s:text name="Error.visitorreg.gatepass" />",
	         			}
	         		});
	         	  	$("#mobilenoid").rules("add", {
	         			required : true,
	         			minlength: 10,
	         			maxlength:10,
	         			messages : {
	         				required : "<s:text name="Error.customerreg.mobileno" />",
	         			}
	         		});

	         	 	/*$("#issuedate").rules("add", {
	         			required : true,
	         			messages : {
	         				required : "<s:text name="Error.customerreg.eventstartname" />",
	         			}
	         		}); */
	         	 	$('#passexpirydate').rules('remove');
	         	 	$('#idproofno').rules('remove');
	         	 	$('#card').rules('remove');
	         	 	$('#skillid').rules('remove');

	        	}
	        	else{
	        		/* alert("else==passtypeval"+passtypeval); */

	        		/*alert("ddd==passtypeval"+passtypeval);  */
	        		$("#visitorname").rules("add", {
	         			required : true,
	         			messages : {
	         				required : "<s:text name="Error.visitorreg.gatepass" />",
	         			}
	         		});
	         	  	$("#mobilenoid").rules("add", {
	         			required : true,
	         			minlength: 10,
	         			maxlength:10,
	         			messages : {
	         				required : "<s:text name="Error.customerreg.mobileno" />",
	         			}
	         		});

	         	 /*	$("#issuedate").rules("add", {
	         			required : true,
	         			messages : {
	         				required : "<s:text name="Error.customerreg.eventstartname" />",
	         			}
	         		});
	         	 	$("#card").rules("add", {
	         			required : true,
	         			messages : {
	         				required : "<s:text name="Error.customerreg.cardname" />",
	         			}
	         		});
	         	  	$("#idproofno").rules("add", {
	         			required : true,
	         			minlength: 9,
	         			maxlength:19,
	         			messages : {
	         				required : "<s:text name="Error.customerreg.cardnum.length" />",
	         			}
	         		});
	         	  	$("#skills_txt_id").rules("add", {
	         			required : true,
	         			messages : {
	         				required : "<s:text name="Error.visitorreg.skill" />",
	         			}
	         		});
	        		$("#passexpirydate").rules("add", {
	         			required : true,
	         			messages : {
	         				required : "<s:text name="Error.customerreg.expirydate" />",
	         			}
	         		});*/
	        	}

	         	 var $fileUpload = $("#formValidate input[type='file']");
	         	var files = $('#formValidate :input[type=file]').get(0).files;
	         	var totsize=0;
	         	var maxsize=5*1024*1024;
	         	for (i = 0; i < files.length; i++)
	         	{
	         		totsize=totsize+files[i].size;

	         	}
	         	if(totsize>maxsize){
	         	 $("#documentfile-error").html('<s:text name="Text.filesize" />');
	        		$("#documentfile-error").show();
	              return false;
	         	}

	       });
	        $("#emailid").blur(function(){
     		    toValiEmail(this.id);
     		});
});

$(document).ready(function() {
	 $('#timepickerstarttime').timepicker();
	 $('#timepickerendtime').timepicker();
 	 $('#gatepassreuseFormId').validate({
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
				//var retval=generalformvalidation();
				toShowLoadingImgoverlay();
				return true;
				//
			}

		});


 	$(document).ready(function(){
 		$.ajax({
 			type : 'post',
 			datatype : 'json',
 			url : 'getidcardvalue',
 			data : '',
 			success : function(data) {
 				var arr=data.split("!_!");
 				//$("#multiselectdiv").html(data);
 				//statelistload(arr[1]);
 				idcardlistfun(arr[1]);
 			}
 		});
 		skillsload();
 	});
 	function idcardlistfun(ar) {
 		var objects_cardtype;
 		ar=ar.replace(/&quot;/ig,'"');
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
 			if (typeof (objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
 	        }else{
 	        	  $('#card').val('');
 	        	  $('#card').focus();
 		          $('#cardid').val('');
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

 	function SkillslistFun(ar) {
		var objects_skills;
	    ar=ar.replace(/&quot;/ig,'"');
	    ar=ar.replace(/%27/ig,"'");
	    ar=ar.replace(/&amp;/ig,'&');
		var loccutyval = JSON.parse(ar);
		$('#skills_txt_id').typeahead({
     	order: "asc",
		hint: true,
		accent: true,
		offset: true,
		searchOnFocus: true,
        source: function(query, process) {
        objects_skills = [];
        map = {};
        var data = loccutyval;
        $.each(data, function(i, object) {
            map[object.label] = object;
            objects_skills.push(object.label);
        });
        process(objects_skills);
       $(".dropdown-menu").css("height", "auto");
       $(".dropdown-menu").addClass("form-control");
    },
    updater: function(item) {

			$('#skills_hidd_id').val(map[item].id);
		/* 	$('#state_txt_id').val('');
			$('#state_hidd_id').val('');
			$('#state_txt_id').typeahead('destroy');
			$('#city_txt_id').val('');
			$('#city_hidd_id').val('');
			$('#city_txt_id').typeahead('destroy');
			$('#pstlcode_txt_id').val('');
			$('#pstlcode_txt_id').typeahead('destroy');
			$('#pstlcode_hidd_id').val('');
			onchangeStatecodeAgency();*/
			return item;
		}
	});

	$('#skills_txt_id').blur(
			function() {
				if (typeof (objects_skills) != "undefined" && objects_skills.indexOf(this.value) != -1) {
				} else {
					$('#skills_txt_id').val('');
					$('#skills_txt_id').focus();
					$('#skills_hidd_id').val('');
					//statelist
					/* $('#state_txt_id').val('');
					$('#state_hidd_id').val('');
					$('#state_txt_id').typeahead('destroy'); */
					// citylist

				}
			});
}

 	function skillsload() {
 		var gval ="gate_pass";
		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getSkillsvalue',
			data : 'categoryidkey=' + gval,
			success : function(data) {
				//alert(data)
				var arr=data.split("!_!");
				SkillslistFun(arr[1]);
			}
		});
		}
$("#evedesc").keyup(function(){textValidate(this,'5000','NV');});
//$("#eventvideopath").keyup(function(){textValidate(this,'150','EML');});
});


 function cancelFunction(){
	$("#userCancelForm").attr("action", "Issuegatepassmgmttbl");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>


</html>