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
								<h5 class="breadcrumbs-title"><s:text name="Maintenance Bill" /></h5>
								<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="utititymgmt"><s:text name="Text.Utility.Management"></s:text></a></li>
							<li class="active"><s:text name="Maintenance Bill" /></li>
							<%-- <li class="active"><s:text name="Text.createdocument.Management" /></li> --%>
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
				 <form role="form" id="maintenanceExcelform"  method="post" action="" enctype="multipart/form-data">
                             <div class="col m12">
                             <s:if test="(#session.sSoctyId==null || #session.sSoctyId =='' || #session.sSoctyId =='-1')">
                              	<div class="row">                                
									<div class="input-field col s6">
									<label for="township_txt_id" class="control-label"><s:text name="Text.adduser.town.ship.id" /><span class="mandatory">*</span></label>
									<s:textfield id="township_txt_id" name="townshipName"   cssClass="form-control typeahead tt-query townshipIdvalidate"  autocomplete="off" />
									<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control "  />
									</div>
									<div class="input-field col s6">
									<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /><span class="mandatory">*</span></label>
									<s:textfield name="societyName" id="societyId_txt_id" cssClass="typeahead tt-query  form-control"  autocomplete="off" spellcheck="false" />
									<s:hidden id="societyId_hidd_id" name="societyId" cssClass="form-control"/>
									</div>
									
								</div>  
							</s:if>	 
							 <div class="row">
								<div class="input-field col s12" id="excelformdiv">                               														
								<!-- <label for="maintexcelfile" class=" control-label lablenames active"  style="color:#26a69a;"><s:text name="Text.file"/><span class="mandatory">*</span></label> -->
								<label for="maintexcelfile" class="control-label lablenames active"><s:text name="Text.file"/><span class="mandatory">*</span></label>
								<div class="clear height10px"></div>
								<!--<a onclick="submitgivenform('<s:text name="ApplicationAbsolutePath"/><s:text name="Proj.path"/><s:text name="WebContent"/><s:text name="Resource.path"/>/jsp/sampleexcel/samplexlsxformat.xlsx');" href="" style="color:red"></a>-->
								<!-- <a class="atag" href="maintnxlxdwnld">[Sample Doc]</a> -->
								<input type="file" id="maintexcelfile"  name="maintexcelfile" data-maxfilesize="<s:text name="upload.file.size" />"
								class="mrgnleft15 maintexcelfile dropify" style="margin-left: 0px;" />
										<div class ="clear height10px"></div>	
								<div id="maintexcelfile-error" class="error manualscriptvalidation" style="display: none;">Maintenance Document File Need.</div>														
								<div class ="clear height25px"></div>	
                         		<button type="button" id="userEditButtonId" onclick="createmaintenancebyexcel();" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.submit" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
								<!--<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>  -->
								<!--  <button type="button" id="userEditButtonId" onclick="createmaintenancebyexcel();" class="<s:text name="button.color.submit"/>"  ><s:text name="Text.button.downloadtemplate" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>  -->
								<div style="clear: both;height:10px;"></div>
								</div>	
									 </div> 	
									 </div>	
									 
                                </form>                                                                                                                                                             
								</div>
								</div>									
								<s:form method="post" id="userCancelForm"></s:form>
		 						<form id="downloadFormid" method="post" action="downloadfiles">
								<s:hidden id="downloadFormval" name="fileName" value=""></s:hidden>								
								</form>	
								<% 
								Integer sid = (Integer)session.getAttribute("sSoctyId");
								String path = "/usr/share/tomcat/siexternals/mntnc_bill/" + sid + "/"+ sid +".xlsx";
								%>
								<s:form id="downloadFormid" method="post" action="downloadfiles">
									<input id="downloadFormval" name="fileName" type="hidden" value="<%=path%>"/>
								    <button type="submit" id="userEditButtonId" class="<s:text name="button.color.submit"/>"  ><s:text name="Download Template" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>						
								</s:form>	
								
								
								</div>																		
											
							</section>
						</div>
					</div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />		
		<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css?fcg" type="text/css" rel="stylesheet" media="screen,projection">
	<jsp:include page="../common/googlemap.jsp"></jsp:include>
	
	<!-- ================================================
    Scripts
    ================================================ -->
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
<script type="text/javascript">
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
$(document).ready(function(){
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
/*
		$('input[type=file]').drop_uploader({
	        uploader_text: 'Drop files, image to upload, or',
	        browse_text: 'Browse',
	        browse_css_class: 'button button-primary',
	        browse_css_selector: 'file_browse',
	        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
	        file_icon: '<i class="pe-7s-file"></i>',
	        time_show_errors: 5,
	        layout: 'thumbnails'
	    });  
	    */
}); 

$('#maintenanceExcelform').validate({
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
});	
		
}


	function addMethod() {
		var tot = 0;
		var targets = [];
		$.each($(".addnumbers"), function() {
			targets.push($(this).val());
		});
		$("#totalid").val(targets.join(","));

		var idval = document.getElementById('totalid').value;
		var sumsplit = idval.split(',');
		for (var i = 0; i < sumsplit.length; i++) {
			if(sumsplit[i]!=''){
				tot = parseFloat(tot) + parseFloat(sumsplit[i]);
				$("#total").val('');
				$("#total").val(tot);
			}else{
			}
		}
	}

	function cancelFunction() {
		$("#userCancelForm").attr("action", "utititymgmt");
		$("#userCancelForm").submit();
		$('#GeneralDocCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
		$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
	}


	


	function createmaintenancebyexcel() {
		var val = $("#maintexcelfile").val();
		if (val.length > 0) {			
			$("#maintenanceExcelform").attr("action","billdocumentfilesubmit");
			$("#maintenanceExcelform").submit();
		} else {
			$("#maintexcelfile-error").show();
		}
	}
	
</script>
 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>


</html>											