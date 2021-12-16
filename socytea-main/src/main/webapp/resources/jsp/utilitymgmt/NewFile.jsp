<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><s:text name='Text.project.title' /></title>
<style type="text/css">
.fileUpload {position: relative;overflow: hidden;margin-top: 10px;margin-left: -15px;}
.fileUpload input.upload {position: absolute;top: 0;right: 0;margin: 0;padding: 0;font-size: 20px;cursor: pointer;opacity: 0;filter: alpha(opacity=0);}
#generaltbl tr td{border: none;width: 50%;}
</style>
</head>
<body>
	<div class="container-fluid">
		<!-- START Header Container -->
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div id="right-column">
			<div class="right-column-content">
				<div class="row">
					<div class="col-xs-12">
						<ol class="breadcrumb">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard"/></a></li>
							<li><a href="eventMgmt"><s:text name="Text.Utility.Management"/></a></li>
							<li><a href="eventMgmt"><s:text name="Text.Utility.Management.event"/></a></li>
							<li class="active"><s:text name="Text.Utility.Management.Details"/></li>
						</ol>
					</div>
				</div>
				 <div class="row">
					<div class="col-md-6">
						<h1>
							<span aria-hidden="true" class="icon icon-map"></span> <span class="main-text"><s:text name="Text.Utility.Management.Details"/></span>
						</h1>
					</div>
					<div class="col-md-12">
							<div class="block">
								<div class="block-heading">									
									<div id="alertmsgsuccess" class="alert alert-success" style="display: none;" onclick="hideFunction();">
  									<strong>Event Shared Successfully.</strong>
								</div>
								<div id="alertmsgerror" class="alert alert-success" style="display: none;" onclick="hideFunction();">
  									<strong>Shared Error.</strong>
								</div>
									<div class="text-right"><a href="eventMgmt"><button type="submit" class="btn btn-primary btn-sm" id="submitbtn" name="submitbtn"
										value="Close" onclick="eventMgmtFun();">Goto Back</button></a></div>
								</div>

								<div class="block-content-outer">
									<div class="block-content-inner">
											 <form method="post" id="registerformid" name="registerformid" action="">
									         <div class="row">
  																 <div class="col-sm-5">
  														  <div class="form-group" >
															   <label class="col-md-4 control-label"><b>Title</b></label><label class="col-md-1 control-label"> :</label><b><s:property value="eventobj.evetitle"/></b>
															</div>
 															 </div>
 															 
															<div class="col-sm-5">
  																  <div class="form-group" id="divage">
															<label class="col-md-4 control-label"><b>Event Image</b></label><label class="col-md-1 control-label"> :</label><label style="margin:-7px;margin-left:2px"><s:if test="#session.eveimg!=null && #session.eveimg!=''">
															<td><img id="imgeventid" style="width : 40px;max-width: 40px; max-height: 40px; display: block;"
																src="/templogo/event/mobile/<s:property value="#session.EVENTID"/>/<s:property value="#session.eveimg"/>" onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/event/web/<s:property value="#session.EVENTID"/>/<s:property value="#session.eveimg"/>')";></td>
														</s:if><!-- <img id="myImg" class="left pointer" width="30px" height="27px" style="margin: -3px 5px 0px 0px;" src="resources/images/icon/debina.jpg"> --> </label>
                                                           
																</div>
															</div>																										
											</div>
											<div class="row">
															 <div class="col-sm-5">
  																  <div class="form-group" id="divage">
															<label class="col-md-4 control-label"><b>Start Date</b></label><label class="col-md-1 control-label"> :</label><b> <s:property value="eventobj.evestartdate"/></b>

																</div>
															</div>
															 <div class="col-sm-5">
  																  <div class="form-group" id="divage">
															<label class="col-md-4 control-label"><b>End Date</b></label><label class="col-md-1 control-label"> :</label><b><s:property value="eventobj.eveenddate"/></b>

																</div>
															</div>
										  </div>
										<div class="row">
															 <div class="col-sm-5">
  																  <div class="form-group" id="divage">
															<label class="col-md-4 control-label"><b>Start Time</b></label><label class="col-md-1 control-label"> :</label><b> <s:property value="eventobj.evestarttime"/></b>

																</div>
															</div>
															<div class="col-sm-5">
  																  <div class="form-group" id="divage">
															<label class="col-md-4 control-label"><b>End Time</b></label><label class="col-md-1 control-label"> :</label><b> <s:property value="eventobj.eveendtime"/></b>

																</div>
															</div>
										</div>
										<div class="row" >															
															<div class="col-sm-5">
  														  <div class="form-group" >
															  <label class="col-md-4 control-label"><b>Video Path</b></label><label class="col-md-1 control-label"> :</label> <a target="_blank" href="<s:property value="eventobj.evevideopath"/>"><label><b class="videohover"><s:property value="eventobj.evevideopath"/></b></label></a>
                                                           </div>
															</div>
															<div class="col-sm-5">
  																  <div class="form-group" id="divage">
															<label class="col-md-4 control-label"><b>Event Location</b></label><label class="col-md-1 control-label"> :</label><b> <s:property value="eventobj.evelocation"/></b>

																</div>
															</div>
									  </div>

									  <div class="row">
														   <div class="col-md-5"  style="float:left; margin-left:15px;">
  																  <div class="form-group" id="divage">
															<label ><b>Short Description</b></label>
															<p > <s:property value="eventobj.eveshdesc"/></p>

																</div>
															</div>
														  
										</div>
										<div class="clear height5px"></div>
										<div class="row">
															 <div class="col-md-6" style="float:left; margin-left:15px;">
  														  <div class="form-group">
												<label><b>Description</b></label>
												<p><s:property value="eventobj.evedesc"/></p>
											</div>
										</div>
										<div  class="col-md-4" style="display:block;" id="shareid">
 										<div class="form-group" >
										<label class=" control-label"><b><s:text name="Text.Utility.Management.modifyeventResident"/></b></label>
													<s:textfield name="eventobj.inviteName" id="invitefriend" cssClass="form-control typeahead tt-query" />
													<s:hidden name="eventobj.inviteid" id="inviteid"></s:hidden>									
										</div>
										</div>
										<div class="col-sm-1" style ="float:left;margin-top:58px; margin-left:0px; float:left;">
											<button type="button" class="btn btn-primary btn-sm" id="submitbtn" name="submitbtn"
										 style="float:right;" onclick="userdetailFun()"> Send>> </button>
                                       </div>
									</div>
									</form>
								</div>
								<div id="verticalline" style="float:left;height:340px; display:none;"></div>									
								</div><div class="clear height5px"></div>
								<s:form method="post" id="userCancelForm"></s:form>
									</div></div></div>
									</div></div>
			</div>
			<!-- START Footer Container -->
			<jsp:include page="../common/footer.jsp"></jsp:include>
			<!-- END Footer Container -->		
		<!-- END Right Column -->
	    <jsp:include page="../common/clorbox.jsp"></jsp:include>
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/assets/css/optional/bootstrapValidator.min.css" />	
  		<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/datepicker.css" rel="stylesheet" /> 	
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />

	<!-- Required JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/formValidation.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/bootstrap/bootstraptypehead.min.js?ytty"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
</body>
<script>
$(document).ready(function(){
    $('input[type=file]').drop_uploader({
        uploader_text: 'Again Drop files to upload, or',
        browse_text: 'Browse',
        browse_css_class: 'button button-primary',
        browse_css_selector: 'file_browse',
        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
        file_icon: '<i class="pe-7s-file"></i>',
        time_show_errors: 5,
        layout: 'thumbnails'
    });
});
function toLoadAutoComplate(){
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getuserfbdetail',
			data : '',
			success : function(data) {
				data=data.replace(/&quot;/ig,'"');
				data=data.replace(/%27/ig,"'");
				var loc_state_val = JSON.parse(data);
				$("#invitefriend").tokenInput(loc_state_val, {
            	propertyToSearch: "searchkey",
            	 resultsFormatter: function(item){ return "<li style='height:51px'>" + "<img class='grptypimage' src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.fName +" "+item.lName+" "+item.flatNo+ "<br><div>" + item.mobilNum + "</div></div></div></li>" },
		            tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
		            theme: "facebook"}); 				
				$(".token-input-dropdown-facebook").css("z-index","260000");
	        	//$("ul.token-input-list-facebook").css("max-width","465px");
	        	$("ul.token-input-list-facebook").css("width","100%");
	        	//$("div.token-input-dropdown-facebook").css("max-width","465px");
	        	$("div.token-input-dropdown-facebook").css("width",$('ul.token-input-list-facebook').width());	        	        	
	       		$("ul.token-input-list-facebook").css("min-height","100px");       		
				$("ul.token-input-list-facebook").css("max-height","162px"); 
				$("ul.token-input-list-facebook").css("height","auto");  
				$("ul.token-input-list-facebook").css("overflow-y","auto");
				$("ul.token-input-list-facebook").css("border-color","#C4C4C4"); 
				$("ul.token-input-list-facebook").addClass("form-control");         
			}
		}); 
}
$(document).ready(function() {
	 toLoadAutoComplate();
	 $('#datePicker')
     .datepicker({format: 'dd-mm-yyyy',autoclose:true})
     .on('changeDate', function(e) {        	
         $('#userCreationFormId').formValidation('revalidateField', 'date');
     }); 	 
	 $('#datePickerenddate')
     .datepicker({format: 'dd-mm-yyyy',autoclose:true})
     .on('changeDate', function(e) {        	
         $('#userCreationFormId').formValidation('revalidateField', 'date');
     }); 
	 
});
 
function togetId(){var idname="";
     $(".token-input-token-facebook p").each(function(x){// new autocomplete
         idname+=$(this).attr("id")+",";           
     });
     return idname;
}
function userdetailFun(){
	var idnam=togetId();
	if(idnam!=''){
		 toShowLoadingImgoverlay();
		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'inviteeventaction',
			data : 'eventShareids='+idnam,
			success : function(data) {
				var str=data;
				toremoveFBAuto();
             $("#invitefriend").val(""); 
			if((str.trim())=='0'){
				$('#alertmsgsuccess').show();
				
			}else if((str.trim())=='1'){
				$('#alertmsgerror').show();	
			}else{
				
			}
			tohideLoadingImgoverlay()	
			}
		});
	}else{
		$("#userCreationFormId").attr("action","eventCreationform");
		$("#userCreationFormId").submit();
	}
}
function toremoveFBAuto(){
    $(".token-input-token-facebook").each(function(x){// new autocomplete
        $(this).remove();
    });
}
function hideFunction(){$('#alertmsgsuccess').hide();$('#alertmsgerror').hide();}
function eventMgmtFun(){
	 toShowLoadingImgoverlay();
	$("#registerformid").attr("action","MgmtFun");
	$("#registerformid").submit();	
}</script>

</html>
