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
								<h5 class="breadcrumbs-title"><s:text name="Create Committee" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome" /></a></li>
							<li class="active"><a href="committeemgmt"><s:text name="Breadcrumb.manage" /></a></li>
							<li class="active"><a href="committeemgmt"><s:text name="Committee Management" /></a></li>
							<li class="active"><s:text name="Create Committee" /></li>
						</ol>
					</div>
					</div>
				</div>
				</div>
				 <!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				 <jsp:include page="../common/Alert.jsp" flush="true" />
				<form role="form" id="userCreationFormId"  method="post" >
				<s:if test="#session.sSoctyId==-1">
					<div class="row rowmargin12px" >
						 <div class="input-field col s6">
												<label for="township_txt_id"><s:text name="Text.adduser.town.ship.id" /><span class="mandatory">*</span></label>
											<s:textfield  id="township_txt_id"  cssClass="form-control typeahead tt-query townshipIdvalidate left"  autocomplete="off" />
											<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('township_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
											<input type="hidden" name="townshipId" ID="township_hidd_id"  class="form-control "  />
											<div class="left"><s:fielderror fieldName="townshipId" cssClass="left"/></div>
											</div>
										 <div class="input-field col s6">
												<label for="societyId_txt_id"><s:text name="Text.adduser.society.id" /><span class="mandatory">*</span></label>
												<s:textfield  id="societyId_txt_id" name="societyName"  cssClass="typeahead tt-query form-control left"  autocomplete="off" onkeyup="societycheck();"/>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('societyId_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<input type="hidden"  name="societyId" ID="societyId_hidd_id"  class="form-control "  />
													<s:fielderror fieldName="usercreateObj.societyId"/>
												</div>
					</div>
			 </s:if>
				<div class="row rowmargin12px" >
															<div class="input-field col s6">
															<label  for="committee_txt_id"><s:text name="Breadcrumb.util.committeerolemgmt" /><span class="mandatory">*</span></label>
														<s:textfield  id="committee_txt_id" name="usercreateObj.committeeRole"  cssClass="form-control typeahead tt-query committeevalidate left" autocomplete="off"/>
														<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('committee_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
														<s:hidden name="committeeRole"  id="committee_hidd_id"  class="form-control "  />
															</div>
															 <div class="input-field col s1">
												<label for="isdcodeid" class="active"><s:text name="Text.Form.IsdCode"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control Isdcode " name="usercreateObj.isdCode" id="isdcodeid" value="%{getText('Text.ISD.value')}"></s:textfield>
												<s:fielderror fieldName="usercreateObj.isdCode"/>
											</div>
											 <div class="input-field col s5">
												<label for="mobilenoid"><s:text name="Text.mobileno" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control mobilevalidate " name="usercreateObj.mobileNo" id="mobilenoid"  ></s:textfield>
												<s:fielderror fieldName="usercreateObj.mobileNo"/>
												<div id="mobirst" class="error manualscriptvalidation" style="display:none;"></div>
											</div>
				</div>
								<div class="row rowmargin12px" >

											 <div class="input-field col s6">
												<label for="emailid"><s:text name="Text.adduser.emailid" /></label>
												<s:textfield cssClass="form-control " name="usercreateObj.emailId" id="emailid"></s:textfield>
												<s:fielderror fieldName="usercreateObj.emailId"/>
											 </div>
											 <div class="input-field col s6">
												<label for="firstname"><s:text name="Text.adduser.fname" /></label>
												<s:textfield cssClass="form-control firstnamevalidate" name="usercreateObj.firstName" id="firstname" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.firstName"/>
											</div>
								</div>


								<div class="row rowmargin12px" >
											 <div class="input-field col s6">
												<label for="lastname"><s:text name="Text.adduser.lname" /></label>
												<s:textfield cssClass="form-control lastnamevalidate" name="usercreateObj.lastName" id="lastname" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.lastName"/>
											 </div>
											 <div class="input-field col s6">
												<label for="gendertyp" class="active"><s:text name="Gender" /></label>
												<s:select id="gendertyp" cssClass="form-control lastnamevalidate" name="usercreateObj.gender" list="#{'1':'Male','2':'Female','3':'Other'}" ></s:select>
												<s:fielderror fieldName="usercreateObj.lastName"/>
								</div>
								</div>
								<div class="row rowmargin12px" >
										  <div class="input-field col s6">
										  <label for="dateid">Date Of Birth</label>
										  <s:textfield  id="dateid" cssClass="dateOfBirth" name="usercreateObj.dob"></s:textfield>
										  </div>
								</div>
								<div class="row rowmargin12px" >
										  <div class="input-field">
										   <label class="active" for="acpid"><s:text name="Access Channel" /></label>
                                           <p id="acpid">
											<input type="checkbox" id="checkbox1" name="accessMedia" class="myCheckbox" value="2"/>
											<label for="checkbox1">Mobile</label>
											<input type="checkbox" id="checkbox2" name="accessMedia" class="myCheckbox" value="1" />
											<label for="checkbox2">Web</label>
                                            <input type="checkbox" id="checkbox3" name="accessMedia" class="myCheckbox" value="3" checked/>
                                            <label for="checkbox3">Both</label>
                                           </p>
											</div>
										</div>
											<div style="clear: both; height: 25px;"></div>
											  <div class="clear marginleft25px">
			<button type="button" id="userCreateButtonId" class="<s:text name="button.color.submit"/>"><s:text name="Text.button.submit" /> <i class="<s:text name="button.icon.submitcard"/>"></i></button>
			<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
							<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
										</div>
										</form>
										<s:form method="post" id="userCancelForm"></s:form>
									</div>
								</div>
							</div>
</section>
</div>
</div>
	<!-- END Body Container -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- this field used for while user click shortcut icon in townshipmgmt page -->
<s:hidden name="sltTwshipidfrmtwnmgmt" id="sltTwshipidfrmtwnmgmt" value="%{#session.ivrTwsipid}"></s:hidden>
<s:hidden name="sltTwshipnamefrmtwnmgmt" id="frmtwnmgmtfrmtwnmgmt" value="%{#session.ivrTwsipname}"></s:hidden>
<s:hidden name="sltsocyidfrmtwnmgmt" id="sltsocyidfrmtwnmgmt" value="%{#session.ivrSwityid}"></s:hidden>
<s:hidden name="sltsocynamefrmtwnmgmt" id="sltsocynamefrmtwnmgmt" value="%{#session.ivrSwityname}"></s:hidden>
</body>
<script type="text/javascript">
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
$(document).ready(function(){
	 var exigndr = $("#gendertyp").html();
	 $("#gendertyp").html('<option value="" disabled selected>Choose your gender</option>'  + exigndr);
	 $("#gendertyp").material_select();
	 $('.myCheckbox').click(function() {
	    $(this).siblings('input:checkbox').prop('checked', false);
	    $(this).prop('checked', true);
	});
	// - Submit
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
				required : "<s:text name="Error.customerreg.townshipId" />",
				//minlength : "Enter at least 5 characters"
				}
			});

			$("#societyId_txt_id").rules("add", {
				required : true,
				messages : {
					required : "<s:text name="Error.customerreg.societyId" />",
				//minlength : "Enter at least 5 characters"

				}
			});
	 }

			$("#committee_txt_id").rules("add", {
				required : true,
				messages : {
					required : "<s:text name="Error.customerreg.committeerole" />",
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
				required : "<s:text name="Error.customerreg.mobileno" />",
			}
		});

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
	 	$("#isdcodeid").keyup(function(){
			textValidate(this,'3','MNM');
		});
	 	$("#mobilenoid").keyup(function(){
			textValidate(this,'10','MNM');
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
	 	$("#mobilenoid").blur(function(){
	    	toValiMobno(this.id);
	    	toCheckMobexits();
	 	});	 		

	if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login
		$.ajax({
			type : 'post',
			datatype : 'html',
			async : 'true',
			url : 'gettownshipOnload',
			success : function(data) {
				var spli=data.split("!_!");
				toloadtownship(spli[1]);
			}
		});
	}else{

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

	$("#userCreateButtonId").click(function(){
		var mobilnovalidmanu = toCheckMobexits();
		 if(typeof(mobilnovalidmanu) != "undefined" && (mobilnovalidmanu || mobilnovalidmanu=="true")){
			    $("#userCreationFormId").attr("action","committeeCreation");
				$("#userCreationFormId").attr("method","Post");
				$("#userCreationFormId").attr("enctype","multipart/form-data");
				$("#userCreationFormId").submit();
		 } else {
			 $("#mobirst").html("Mobile No. already exist in this society.");
				$("#mobirst").show();
				$("#mobilenoid").focus();
		}
	});
});



function toloadtownship(valjson){
	var objects_cardtype;
	var twnmap;
	valjson=valjson.replace(/&quot;/ig,'"');
	var locval = JSON.parse(valjson);
	//$('#township_txt_id').val('');
	//$('#township_hidd_id').val('');
	$('#township_txt_id').typeahead({
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
	        twnmap = {};
	        var data = locval;
	        $.each(data, function(i, object) {
	        	twnmap[object.label] = object;
	            objects_cardtype.push(object.label);
	        });
	        process(objects_cardtype);
	       $(".dropdown-menu").css("height", "auto");
	       $(".dropdown-menu").addClass("form-control");
	    },
	    updater: function(item) {
	        $('#township_hidd_id').val(twnmap[item].id);
	        getsociety(twnmap[item].id);
	       return item;
	    }
	});
 	$('#township_txt_id').blur(function(){
	 if (typeof(objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {
        }else{
        	  $('#township_txt_id').val('');
        	  $('#township_txt_id').focus();
	          $('#township_hidd_id').val('');
	          $('#societyId_txt_id').val('');
		      $('#societyId_hidd_id').val('');
		      $('#societyId_txt_id').typeahead('destroy');
          }
      });
}

function getsociety(townid){
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
 		}
 	});
}

	function getsociety_auto(tempdata){
		var objects_scty;
		tempdata=tempdata.replace(/&quot;/ig,'"');
		var locval = JSON.parse(tempdata);
		var map_scty;
		//$('#societyId_txt_id').val('');
	   // $('#societyId_hidd_id').val('');
	    $('#societyId_txt_id').typeahead('destroy');
		$('#societyId_txt_id').typeahead({
	     		order: "asc",
				hint: true,
				accent: true,
				offset: true,
				searchOnFocus: true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,
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
		$('#societyId_txt_id').blur(function(){
		if (typeof(objects_scty) != "undefined" && objects_scty.indexOf(this.value)!=-1) {
     	}else{
     	  $('#societyId_txt_id').val('');
     	  $('#societyId_txt_id').focus();
	      $('#societyId_hidd_id').val('');
       }
   	});

}

	function committeedata(valjsoncommittee){
		var objects_committee;
		var committeemap;
		valjsoncommittee=valjsoncommittee.replace(/&quot;/ig,'"');
		var locvalcommittee = JSON.parse(valjsoncommittee);
		 $('#committee_txt_id').typeahead('destroy');
		$('#committee_txt_id').val('');
		$('#committee_hidd_id').val('');
		$('#committee_txt_id').typeahead({
		     		order: "asc",
					hint: true,
					accent: true,
					offset: true,
					searchOnFocus: true,
					minLength : 0,
					highlight: true,
					showHintOnFocus:true,
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
			if (typeof(objects_committee) != "undefined" && objects_committee.indexOf(this.value)!=-1) {
	     	}else{
	     		 $('#committee_txt_id').val('');
	       	     $('#committee_txt_id').focus();
		         $('#committee_hidd_id').val('');
	     	}
	   	});
		}
 	function cancelFunction(){
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
 function toCheckMobexits() {
		var lvrSocietyid = $("#societyId_hidd_id").val();
		var lvrMobno = $("#mobilenoid").val();
		var lvrRst=true;
		if(typeof(lvrSocietyid) == "undefined"){
			lvrSocietyid ="";
		}
		if (lvrMobno.trim().length>0) {
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'validatersiedentmob',
				async: false,
				data : 'societyID=' + lvrSocietyid+"&ivrMobileno="+lvrMobno,
				success : function(data){
				var arr=data.split("!_!");
				if(arr[1]=="Exists"){
					$("#mobirst").html("Mobile No. already exist in this society.");
					$("#mobirst").show();
					$("#mobilenoid").focus();
					 lvrRst=false;
					 
				} else {
					 lvrRst=true;
					 $("#mobirst").html("");
				 	 $("#mobirst").hide();
				 	
				}
			}
			});
			 return lvrRst;
	 	} else {
	 		 lvrRst=true;
	 		$("#mobirst").html("");
	 		$("#mobirst").hide();
	 		return lvrRst;
	 	}		 	
	}
 $(document).ready(function(){
 if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login
	 	var lvrShrtTwshipid = '<s:property value="#session.ivrTwsipid"/>';
	 	var lvrShrtTwshipname = '<s:property value="#session.ivrTwsipname"/>';
	 	var lvrShrtSocytid = '<s:property value="#session.ivrSwityid"/>';
	 	var lvrShrtSocytname = '<s:property value="#session.ivrSwityname"/>';
	 	if((lvrShrtTwshipid!="null" && lvrShrtTwshipid.length>0 && lvrShrtTwshipid!="") && (lvrShrtTwshipname!="null" && lvrShrtTwshipname.length>0 && lvrShrtTwshipname!="")){		 	
		 	$('#township_txt_id').val(lvrShrtTwshipname);
	         $('#township_hidd_id').val(lvrShrtTwshipid);
		 	getsociety(lvrShrtTwshipid);
		 	$('#societyId_txt_id').val(lvrShrtSocytname);
		 	$('#societyId_hidd_id').val(lvrShrtSocytid);
		 
	 	} else {
	 	}
	}
 });
</script>
<style>

</style>
</html>
