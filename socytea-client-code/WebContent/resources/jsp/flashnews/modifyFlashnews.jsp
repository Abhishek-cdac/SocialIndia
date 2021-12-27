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
<title><s:text name="Text.Title"/></title>
<!-- CORE CSS-->
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
								<h5 class="breadcrumbs-title"><s:text name="Text.Utility.Management.createflashnews" /></h5>
								<ol class="breadcrumbs">
							<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="flashnews"><s:text name="Text.Utility.Management" /></a></li>
							<li><a href="flashnews"><s:text name="Text.Utility.Management.flashnews" /></a></li>
							<li class="active"><s:text name="Text.Utility.Management.createflashnews" /></li>	
						</ol>
					</div>
				</div>
				</div>
				</div>
				<jsp:include page="../common/Alert.jsp" flush="true" />
				<!--breadcrumbs end-->				
				<!--start container-->
				<div class="container">				
				<div id="jqueryvalidation" class="section">
				 <div class="card-panel">				 
				 <form role="form" id="flashnewform"  method="post" action="" enctype="multipart/form-data">
                             <div class="col m12">
                             <div id="profilehidden" style="display:block; margin:0 px;">
							<div class="row">
							<div class="col s12 m4 l3">	
							 <input type="file" id="flashnewimage"  name="flashnewimage" class="dropify" data-default-file="/templogo/flashnews/web/<s:property value="flashId"/>/<s:property value="#session.profileimage" />" />											     		    										
         					</div>
             				 <div class="col s12 m8 l9">												
										<div class="row"> 
                             <s:if test="(#session.sSoctyId==null || #session.sSoctyId =='' || #session.sSoctyId =='-1')">
                              	<div class="row">                                
									<div class="input-field col s6">
									<label for="township_txt_id" class="control-label"><s:text name="Text.adduser.town.ship.id" /><span class="mandatory">*</span></label>
									<s:textfield id="township_txt_id" name="townshipName"   cssClass="form-control typeahead tt-query townshipIdvalidate left"  autocomplete="off" />
									<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('township_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>   
									<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control "  />
									</div>
									<div class="input-field col s6">
									<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /><span class="mandatory">*</span></label>
									<s:textfield name="societyName" id="societyId_txt_id" cssClass="typeahead tt-query  form-control left"  autocomplete="off" spellcheck="false" />
									<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('societyId_txt_id');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
									<s:hidden id="societyId_hidd_id" name="societyId" cssClass="form-control"/>
									</div>								  
								 </div>  
							</s:if>	 
							<div class="row">
											<div class="input-field col s6">
											<label for="exdate"><s:text name="Expiry date"/><span class="mandatory">*</span></label>                 							
                							<s:textfield id="exdate" type="text" cssClass="futuredateOfBirth" name="exdate" onkeyup="textValidate(this,'20','DT')"/>
											</div>
											<div class="input-field col s6">
											<div>
											<div class="input-group bootstrap-timepicker timepicker col s12"> </div>
											<label for="timepickerstarttime" class="control-label"><s:text name="Text.label.Expirytime"/><span class="mandatory">*</span></label>
            								<s:textfield id="timepickerstarttime" type="text" name="extime" class="form-control input-small evestarttime left"/>
            								<span id="startclockspanid" class="input-group-addon timepickericon tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Enter Expiry Time"><i class="mdi-action-query-builder pink-text small"></i></span>
       										
											</div>
											</div>
									 </div> 
							 <div class="row">
								<div class="input-field col s12">
											<div class="form-group" id="usernamedivid">
												<label for="title" class="control-label">Flash News Title</label>
												<s:textarea readonly="true" cssClass="materialize-textarea validate" name="title" id="title" rows="2" style="resize:none;height: 50px;"></s:textarea>
											</div>
											</div>
									 </div> 
									 </div>
									 </div>
									  <div class="row">
								<div class="input-field col s12">
											<div class="form-group" id="usernamedivid">
												<label for="evedesc" class="control-label"><s:text name="Text.Utility.Management.flashnewcontent"/></label>
												<s:textarea readonly="true" cssClass="materialize-textarea validate" name="flashnewcontent" id="flashnewcontent" rows="2" style="resize:none;height: 225px;"></s:textarea>
											</div>
											</div>
									 </div> 
									 <div class="row">
									 <div class="input-field col s6">
									 <button type="button" id="userEditButtonId" onclick="createFlashnews();" class="<s:text name="button.color.submit"/>"  >RE-BOOK<i class="<s:text name="button.icon.submitcard"/>"></i></button>
								<button type="button" id="cancelbuttonid" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>  
									 </div>	
									 </div>	
									 <s:hidden  name="flashId" id="flashId"></s:hidden>
									 
									 </div>
									 </div>
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
	<jsp:include page="../common/googlemap.jsp"></jsp:include>
	
	<!-- ================================================
    Scripts
    ================================================ -->
    <link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />		
	<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css?fcg" type="text/css" rel="stylesheet" media="screen,projection">
	<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/timepicker_min.css?sa" />
    <link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css?saa" />
    
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/timepicker_min.js?dfd"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script>
	
<script type="text/javascript">
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
$(document).ready(function(){
	$("#startclockspanid").click(function(){ $("#timepickerstarttime").click(); });	
	$('#timepickerstarttime').timepicker();
	 if (pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login 
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'gettownshipOnload',
			//data : 'uniqTownShipIdEdit=' + townshipid,
			success : function(data) {
				var spli=data.split("!_!");
				townshipdata(spli[1]);
			}
		}); 
		
	 }
	 $('.dropify').dropify({
	        messages: {
	        	"default":"Drag and drop a file here or click",
	        	replace:"Drag and drop or file to replace",
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

$('#flashnewform').validate({
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
if (pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid=="") {
 	$("#township_txt_id").rules("add", {
		required : true,
		messages : {
			required :"<s:text name="Error.customerreg.township.name.length" />",
		}		
	});
	$("#societyId_txt_id").rules("add", {
		required : true,
		messages : {
			required :"<s:text name="Error.customerreg.societyId" />",
		}	
	}); 
	
	$("#flashnewcontent").rules("add", {
		required : true,
		messages : {
			required :"<s:text name="Error.customerreg.flashnews" />",
		}	
	});
	$("#exdate").rules("add", {
		required : true,
		messages : {
			required :"<s:text name="Error.customerreg.expirydate" />",
		}	
	});
}


function townshipdata (valjson) {
	valjson=valjson.replace(/&quot;/ig,'"');
	var locval = JSON.parse(valjson);	
	var objects_cardtype;
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
		if (objects_cardtype != undefined && objects_cardtype.indexOf(this.value)!=-1) {			
  }else{            
  	  $('#township_txt_id').val('');        	         	 
  	  $('#township_txt_id').focus();
	          $('#township_hidd_id').val('');	          
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

function getsociety_auto(tempdata){
	tempdata=tempdata.replace(/&quot;/ig,'"');
	var locval = JSON.parse(tempdata);	
	var map_scty;
	 $('#societyId_txt_id').val('');        	         	 		   
  
$('#societyId_txt_id').typeahead('refresh');
    $('#societyId_txt_id').typeahead('destroy');
	$('#societyId_txt_id').typeahead({
     		order: "asc",
			hint: true,
			accent: true,
			offset: true,
			minLength : 0,
			highlight: true,
			showHintOnFocus:true,
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
		
}
function createFlashnews() {
// 		$("#flashnewform").attr("action","updateflashnews");
		$("#flashnewform").attr("action","createflashnewsmgmt");
		$("#flashnewform").submit();
}
	function cancelFunction() {
		$("#userCancelForm").attr("action", "flashnews");
		$("#userCancelForm").submit();
		$('#cancelbuttonid').addClass('<s:text name="cancel.button.animated.rollout"/>');		
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
	}

	
</script>
 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</html>											