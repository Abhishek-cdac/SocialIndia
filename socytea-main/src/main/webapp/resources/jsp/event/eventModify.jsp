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
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body>
<!-- Start Page Loading -->
	<div id="loader-wrapper"><div id="loader"></div><div class="loader-section section-left"></div><div class="loader-section section-right"></div></div>
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
							<h5 class="breadcrumbs-title"><s:text name="Text.Utility.Management.modifyevent" /></h5>
							<ol class="breadcrumbs">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard"/></a></li>
							<li><a href="eventMgmt"><s:text name="Text.Utility.Management"/></a></li>
							<li><a href="eventMgmt"><s:text name="Text.Utility.Management.event"/></a></li>
							<li class="active"><s:text name="Text.Utility.Management.modifyevent"/></li>
						</ol>
					</div>
					</div>
					<!--breadcrumbs end-->
					</div>
				</div>
				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				<form role="form" id="userCreationFormId" action="eventmodifyform" method="post" enctype="multipart/form-data">
										  <div class="col-md-12">
												<div class="row">
											<div class="input-field col s6">
												<label for="eventfuname" class=" control-label"><s:text name="Text.event.function.for"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control title titlevalidate left" name="eventobj.eventfuname" autocomplete="off" id="eventfun" ></s:textfield>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('eventfun');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="eventobj.eventfunctionid" id="title_hidd_id"></s:hidden>
											</div>
											<div class="input-field col s6">
												<label class=" control-label"><s:text name="Text.Utility.Management.eventemp"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control shortvalidate left" name="eventobj.eventfuntempatetext"
												id="eventtemplateid" ></s:textfield>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('eventtemplateid');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="eventobj.eventfuntempateid" id="even_template_hidd_id"></s:hidden>
											</div>
											</div>

											<div class="row">
											<div class="input-field col s6">
											<label for="eventetitle" class=" control-label"><s:text name="Text.title"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control title titlevalidate" name="eventobj.evetitle" autocomplete="off"
												id="eventetitle" ></s:textfield>
											</div>
											<div class="input-field col s6">
											<div id="genter_select" class="row">
											<label for="eventtype" class="active"><s:text name="Event Type"/><span class="mandatory">*</span></label>
											<div class="clear height10px"></div>
											<s:radio id="eventtype" name="eventtype" list="#{ '2' : 'Society Event', '3' : 'Commitee Meeting'}"></s:radio>
											</div></div>
											</div>

										   <div class="row">
											<div class="input-field col s6">
												<label for="facitity" class="control-label"><s:text name="Facility"/></label>
												<s:textfield cssClass="form-control left" name="lvrEvntfacilitytext" id="facitity" autocomplete="off"></s:textfield>
												<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('facitity');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
												<s:hidden name="eventobj.facitityid" id="facitity_hidd_id"></s:hidden>
											</div>
											<div class="input-field col s6" style="">
											<div class="form-group" id="usernamedivid">
												<label for="eventlocation" class="left control-label"><s:text name="Text.Utility.Management.location"/></label>
												<!-- <div class="right pointer tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Store Location" onclick="toShowGmap('eventlocation')" >
												<span class="mdi-maps-place large pink-text" ></span></div> -->
												<s:textfield cssClass="form-control pinCode left" name="eventobj.evelocation" id="eventlocation"></s:textfield>
												<span class="input-group-addon timepickericon tooltipped left" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Location" onclick="toShowGmapwithlatlong('eventlocation','evelatlongid')"><i class="mdi-maps-place pink-text small"></i></span>
												<s:hidden name="eventobj.evelatlong" id="evelatlongid"></s:hidden>
											</div></div>
											</div>

											<div class="row">
											<div class="input-field col s6">
												<label for="evestartdate"><s:text name="Text.Utility.Management.eventstartdate"/><span class="mandatory">*</span></label>
                								<s:textfield id="evestartdate" cssClass="form-control futuredateOfBirth" name="evestartdate" onkeyup="textValidate(this,'20','DT')"/>
											</div>
											<div class="input-field col s6">
												<label for="eveenddate"><s:text name="Text.Utility.Management.eventenddate"/><span class="mandatory">*</span></label>
                								<s:textfield id="eveenddate"  cssClass="form-control futuredateOfBirth" name="eveenddate" onkeyup="textValidate(this,'20','DT')"/>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s6">
											<div class="form-group" id="usernamedivid">
													<div class="input-group bootstrap-timepicker timepicker col s12"></div>
												<label class="control-label left"><s:text name="Text.Utility.Management.eventstarttime"/><span class="mandatory">*</span></label>
           										 <s:textfield id="timepickerstarttime" name="eventobj.evestarttime" cssClass="form-control input-small left"/>
           										 <span id="startclockspanid"  class="input-group-addon timepickericon tooltipped left" data-toggle="modal" data-target="#timepickerstarttime" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Enter Start Time"><i class="mdi-action-query-builder pink-text small"></i></span>

											</div>
											</div>
											<div class="input-field col s6">
											<div class="form-group" id="usernamedivid">
												<div class="input-group bootstrap-timepicker timepicker col s12"></div>
												<label class="control-label left"><s:text name="Text.Utility.Management.eventendtime"/><span class="mandatory">*</span></label>
            									<s:textfield id="timepickerendtime" name="eventobj.eveendtime" cssClass="form-control input-small left"/>
            									<span id="endclockspanid" class="input-group-addon timepickericon tooltipped left" data-toggle="modal" data-target="#timepickerendtime" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Enter End Time"><i class="mdi-action-query-builder pink-text small"></i></span>
											</div>
											</div>
											</div>
											<!-- <div class="row">
											<div class="input-field col s6" style="">
												<label for="eventvideopath" class=" control-label"><s:text name="Text.Utility.Management.eventvideopath"/></label>
												<s:textfield cssClass="form-control pinCode" name="eventobj.evevideopath"
												id="eventvideopath"></s:textfield>
											</div>
											</div> -->
											<div class="row">
											<div class="input-field col s6">
												<label for="eventshortdesc" class=" control-label"><s:text name="Text.Utility.Management.eventshort"/><span class="mandatory">*</span></label>
												<%-- <s:textfield cssClass="form-control shortvalidate" name="eventobj.eveshdesc"
												id="eventshortdesc" ></s:textfield> --%>
												<s:textarea cssClass="materialize-textarea shortvalidate" name="eventobj.eveshdesc" id="eventshortdesc" rows="2" style="resize:none;height:0px"></s:textarea>
											</div>
												<div class="input-field col s6">
												<div id="genter_select" class="row" style="">
											<label for="eventtype" class="active"><s:text name="Event Rsvp"/></label>
											<div class="clear height25px"></div>
											<s:radio id="eventrsvp" name="eventrsvp" list="#{ '1' : 'Yes', '0' : 'No'}"></s:radio>
											</div>
											</div>
											</div>
											<div class="row">
											<div class="input-field col s12">
												<label for="evedesc" class="control-label"><s:text name="Text.Utility.Management.eventdesc"/></label>
												<s:textarea cssClass="materialize-textarea validate" name="eventobj.evedesc" id="evedesc" rows="2" style="resize:none;height:15px"></s:textarea>
											</div>
											</div>

										   <div  class="imgclass eventcreateImgdiv row">
											<div class="input-field col s6" id="preimg" style="display: block;">
												<table align="left" style="width: 200px; margin: auto; ">
													<tbody>
														<tr style="">
														<td style="text-align: center; padding: 5px;">
															<s:if test="#session.eveimg!=null && #session.eveimg!=''">
															<img id="myImg" class="lbrimg" name="eveimg" src="/templogo/event/web/<s:property value="#session.EVENTID"/>/<s:property value="#session.eveimg" />" onclick="toViewlargesizeimg(this.id)"/>
															<div style="clear: both;"></div>
																<div id="imgdivid" style="cursor: pointer;" class="mdi-editor-mode-edit pink-text text-accent-3" onclick="imagechange()"> <span class="glyphicon glyphicon-pencil"></span> Edit </div>
															</s:if><s:else>
																<img class="lbrimg" id="myImg" src="<s:text name='Resource.path'/>/images/defaultevent.jpg"/>
																<div style="clear: both;"></div>
																<div id="imgdivid" style="cursor: pointer;" class="mdi-editor-mode-edit pink-text text-accent-3" onclick="imagechange()"> <span class="glyphicon glyphicon-pencil"></span> Edit </div>
																</s:else>
															</td>

														</tr>
													</tbody>
												</table>
										</div>
									<div class="input-field col s12 imgtbl_stfcrt" style="display:none;">
									<div class="form-group " id="documentfiledivid">
										<div>
							            <label for="documentfile" class="control-label lablenames active">Event Image</label>
										<span class="atag"> (<s:text name="Text.filesize" />, <s:text name="Text.allowformat"/>)</span>
										<div class ="clear height10px"></div>
							             <input type="file" id="documentfile" name="documentfile" data-maxfilesize="<s:text name="upload.file.size" />" data-default-file="/templogo/event/web/<s:property value="#session.EVENTID"/>/<s:property value="#session.eveimg" />" class="mrgnleft15 tooltipster documentfile dropify" style="margin-left: 0px;" title="Choose File" />

										</div>
									</div>
									</div>
											</div>
											<div class="clear height25px"></div>
											<button type="submit" id="userCreateButtonId"  class="<s:text name="button.color.submit"/>" onblur="dateComplate().style.display='none';" ><s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
											<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();"><s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
										</div>
										<div class ="clear height5px"></div>
										<s:hidden name="eveid" id="eventuserid"></s:hidden>
										</form>
				<s:form method="post" id="userCancelForm"></s:form>

						</div>
						</div>
						</div>
						</section>
		</div></div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<jsp:include page="../common/clorbox.jsp"></jsp:include>
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/timepicker_min.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />
		<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css?fcg" type="text/css" rel="stylesheet" media="screen,projection">
	<jsp:include page="../common/googlemap.jsp"></jsp:include>

	<!-- ================================================
    Scripts
    ================================================ -->
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/timepicker_min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
	<script>
$(document).ready(function(){

	$("#startclockspanid").click(function(){ $("#timepickerstarttime").click(); });
	$("#endclockspanid").click(function(){$("#timepickerendtime").click(); });
    /*$('input[type=file]').drop_uploader({
        uploader_text: 'Again Drop files to upload, or',
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

	        $("#userCreateButtonId").click(function () {
	        	var rtnrst = toValidateSTDTENDT();
	        	if (rtnrst) {
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
	        	} else {
	        		return false;
			    }
	       });


    $.ajax({
		type : 'post',
		datatype : 'html',
		url : 'getfunctiondataonload',
		success : function(data) {
			var spli = data.split("!_!");
			eventfunctiondata(spli[1]);
		}
	});
    eventfacilityajax();

});

function toValidateSTDTENDT(){ // Start date end date validation
	var stDte  = $("#evestartdate").val();
	var endDte  = $("#eveenddate").val();
	if (stDte.length>0 && endDte.length >0) {
		var frmDt = new Array(); var toDt = new Array();
		frmDt = stDte.split("-");
        toDt = endDte.split("-");
        var frm = frmDt[2]+"-"+frmDt[1]+"-"+frmDt[0];
        var to_date = toDt[2]+"-"+toDt[1]+"-"+toDt[0];
		var fromdatebj = new Date(frm); //Year-Month-Date
	    var toDateobj = new Date(to_date); //Year-Month-Date
	    if (fromdatebj  <= toDateobj) {
	    	return true;
	    } else {
	    	swal("End date should less then Start date. Change your Start date and End date");
	    	$("#eveenddate").val("");
	    	return false;
		}
	} else {
		return true;
	}
}

function eventfunctiondata(valjson) {
	valjson = valjson.replace(/&quot;/ig, '"');
	var locval = JSON.parse(valjson);
	var objects_cardtype;
	$('#eventfun').typeahead('destroy');
	$('#eventfun').typeahead({
		order : "asc",
		hint : true,
		accent : true,
		offset : true,
		minLength : 0,
		highlight: true,
		showHintOnFocus:true,
		searchOnFocus : true,
		source : function(query, process) {
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
		updater : function(item) {
			$('#title_hidd_id').val(map[item].id);
			$('#eventtemplateid').val("");
			$('#even_template_hidd_id').val('');
			$('#eventtemplateid').typeahead('destroy');

			$('#facitity').val('');
			$('#facitity_hidd_id').val('');
			$('#facitity').typeahead('destroy');
			eventfuntemplateajax(map[item].id);
			return item;
		}

	});
	$('#eventfun').blur(function() {
				if (typeof (objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value) != -1) {
				} else {
					/* $('#eventfun').val('');
					$('#eventfun').focus();
					$('#title_hidd_id').val('');

					$('#eventtemplateid').val('');
					$('#eventtemplateid').focus();
					$('#even_template_hidd_id').val('');

					$('#facitity').val('');
					$('#facitity').focus();
					$('#facitity_hidd_id').val(''); */
				}
	});

}

function eventfuntemplateajax(pFunctionid){
	 $.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getfunctiontemplatedataonload?functionid='+pFunctionid,
			success : function(data) {
				var spli = data.split("!_!");
				eventfuntemplate(spli[1]);
			}
		});
}
function eventfuntemplate(valjson) {
	valjson = valjson.replace(/&quot;/ig, '"');
	var locval = JSON.parse(valjson);
	var objects_cardtype;
	$('#eventtemplateid').typeahead('destroy');
	$('#eventtemplateid').typeahead({
		order : "asc",
		hint : true,
		accent : true,
		offset : true,
		minLength : 0,
		highlight: true,
		showHintOnFocus:true,
		searchOnFocus : true,
		source : function(query, process) {
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
		updater : function(item) {
			$('#even_template_hidd_id').val(map[item].id);
			//$('#facitity').val('');
			$('#facitity_hidd_id').val('');
			$('#facitity').typeahead('destroy');
			eventfacilityajax();
			return item;
		}
	});
	$('#eventtemplateid').blur(function() {
				if (typeof (objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value) != -1) {
				} else {
					/* $('#eventtemplateid').val('');
					$('#eventtemplateid').focus();
					$('#even_template_hidd_id').val('');
					$('#facitity').val('');
					$('#facitity').focus();
					$('#facitity_hidd_id').val(''); */
				}
	});
}


function eventfacilityajax(){
	 $.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getfunctionfacilityload',
			success : function(data) {
				var spli = data.split("!_!");
				eventfacilitytemplate(spli[0]);
			}
		});
}
function eventfacilitytemplate(valjson) {
	valjson = valjson.replace(/&quot;/ig, '"');
	var locval = JSON.parse(valjson);
	var objects_cardtype;
	$('#facitity').typeahead('destroy');
	$('#facitity').typeahead({
		order : "asc",
		hint : true,
		accent : true,
		offset : true,
		minLength : 0,
		highlight: true,
		showHintOnFocus:true,
		searchOnFocus : true,
		source : function(query, process) {
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
		updater : function(item) {
			$('#facitity_hidd_id').val(map[item].id);
			return item;
		}
	});
	$('#facitity').blur(function() {
				if (typeof (objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value) != -1) {
				} else {
					/* $('#facitity').val('');
					$('#facitity').focus();
					$('#facitity_hidd_id').val(''); */
				}
	});
}

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
            resultsFormatter: function(item){ return "<li>" + "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.searchkey + "</div></div></li>" },
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

	 $('#timepickerstarttime').timepicker();
	 $('#timepickerendtime').timepicker();
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
				//var retval=generalformvalidation();

				return true;
			}
		});
	 $("#eventfun").rules("add", {
			required : true,
			messages : {
				required : "<s:text name="Error.customerreg.eventfunction" />",
			}
		});
	 $("#eventtemplateid").rules("add", {
			required : true,
			messages : {
				required : "<s:text name="The event template is required" />",
			}
		});
	  	$("#eventetitle").rules("add", {
			required : true,
			minlength: 2,
			maxlength:20,
			messages : {
				required : "<s:text name="Error.customerreg.eventtitle" />",
			}
		});
	  	$("#evedesc").rules("add", {
		//	required : true,
			minlength: 2,
			maxlength:500,
			messages : {
				required : "<s:text name="Error.customerreg.eventshdesc" />",
			}
		});
 	$("#eventshortdesc").rules("add", {
		required : true,
		minlength: 2,
		maxlength : 150,
		messages : {
			required : "<s:text name="Error.customerreg.eventshdesc" />",
		}
	});
 	$("#evestartdate").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.eventstartname" />",
		}
	});
	$("#eveenddate").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.eventendname" />",
		}
	});
	$("#timepickerstarttime").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.eventstarttime" />",
		}
	});
	$("#timepickerendtime").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Error.customerreg.eventendtime" />",
		}
	});



$("#eventfun").keyup(function(){textValidate(this,'100','NV');});
$("#eventshortdesc").keyup(function(){textValidate(this,'100','NV');});
$("#evedesc").keyup(function(){textValidate(this,'5000','NV');});
//$("#eventvideopath").keyup(function(){textValidate(this,'150','EML');});
});

function dateComplate(){

	$("#evestartdate").rules('add', { greaterThan: "#eveenddate", required : true,
		messages : {
			required : "<s:text name="Start date is not greater than." />",
		} });

	}
 function cancelFunction(){
	$("#userCancelForm").attr("action", "eventMgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
 }
 function imagechange(){
	 $("#documentfile").click();
	 $(".imgtbl_stfcrt").show();
	 $("#preimg").hide();
	 $("#imgdivid").hide();
 }
/*  function submitFunction(){
		$("#userCreationFormId").attr("action", "eventmodifyform");
		$("#userCreationFormId").submit();
 } */
</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</html>