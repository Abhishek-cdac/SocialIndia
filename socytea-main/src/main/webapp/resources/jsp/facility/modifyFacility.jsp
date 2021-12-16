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
                <h5 class="breadcrumbs-title"><s:text name="Text.modify.facility"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
					<li><a href="facilitymgmt"><s:text name="Breadcrumb.util.configmgmt" /></a></li>
					<li><a href="facilitymgmt"><s:text name="Text.facility" /></a></li>
                    <li class="active"><s:text name="Text.modify.facility" /></li>
                   </ol>
              </div>
            </div>
          </div>
        </div>
       	<div class="container">
<div class="card-panel">
	<form id="updatefacilityFormId" name="updatefacilityFormId" action="updatefacility" method="post" enctype="multipart/form-data"  >
		<div class="row">
			<div class="col s12 m4 l3">
				<input type="file" name="facImage" id="facImage" class="dropify" data-default-file="/templogo/facility/web/<s:property value="facilityId"/>/<s:property value="#session.facilityImage" />" />
        	</div>
        <div class="col s12 m8 l9">
         <s:if test="#session.sSoctyId==-1">
		<div class="row">
  			<div class="input-field col s6">
				<label for="township_txt_id"><s:text name="Text.adduser.town.ship.id"></s:text><span class="mandatory">*</span></label>
				<s:textfield  id="township_txt_id" name="townshipName" cssClass="form-control typeahead tt-query townshipIdvalidate" readonly="true" autocomplete="off" />
				<s:hidden id="township_hidd_id" name="townshipId" class="form-control"/>
			</div>
  			<div class="input-field col s6">
				<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /><span class="mandatory">*</span></label>
				<s:textfield  id="societyId_txt_id" name="societyName"  cssClass="typeahead tt-query form-control" readonly="true" autocomplete="off" />
				<s:hidden  name="societyId" id="societyId_hidd_id"  class="form-control "  />
			</div>
 		</div>
 	</s:if>
 		<div class="row">
			<div class="input-field col s6">
				<label for="facname"><s:text name="Text.facilityname" /><span class="mandatory">*</span></label>
				<s:textfield name="facname" id="facname" cssClass="form-control facname" autocomplete="off"/>
			</div>
			<div class="input-field col s6">
				<label for="place"><s:text name="Text.facilityPlace" /><span class="mandatory">*</span></label>
				<s:textfield name="place" id="place"  cssClass="form-control place" />
			</div>
		</div>
		</div>
          <div class="clear height10px"></div>
        <div class="row margin" >
			<div class="input-field col s12">
				<label for="facdesc" class="control-label"><s:text name="Text.describtion"/></label>
				<s:textarea cssClass="materialize-textarea validate" name="facdesc" id="facdesc" rows="2" style="resize:none;height:15px"></s:textarea>
			</div>
		</div>
		        <div class="row" style="margin-left:10px;" >
				<button type="button" id="createfacility" class="<s:text name="button.color.submit"/>" onclick="updatefacilitymethod();">
				<s:text name="Text.button.update" /><i class="<s:text name="button.icon.submitcard"/>"></i></button>
				<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="cancelFunction();">
				<s:text name="Text.button.cancel" /><i class="<s:text name="button.icon.replycard"/>"></i></button>
			</div>

		</div>
		<s:hidden name="facilityId" id="facilityId"/>
	</form>
</div>
</div>
</section>
</div>
<s:form method="post" id="userCancelForm"></s:form>
</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
</body>
<script>
function updatefacilitymethod(){
	$('#updatefacilityFormId').attr("Method","Post");
	$('#updatefacilityFormId').attr("action","updatefacility");
	$('#updatefacilityFormId').attr("enctype","multipart/form-data");
	$('#updatefacilityFormId').submit();
}
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
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

    if(pvrLoginusrsoctyid=="-1" || pvrLoginusrsoctyid=="null" || pvrLoginusrsoctyid==""){//socytea id not found in session - Admin login */
 /*   	 $.ajax({
    		type : 'post',
    		datatype : 'json',
    		url : 'getresidentform',
    		data : '',
    		success : function(data) {
    			var arr=data.split("!_!");
    			townshipfun(arr[1]);
    		}
    	}); */
    	}


});
$('#updatefacilityFormId').validate({
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
$("#township_txt_id").rules("add", {
	required : true,
	messages : {
		required : "<s:text name="The township name is required" />",
	}
});
$("#societyId_txt_id").rules("add", {
	required : true,
	messages : {
		required : "<s:text name="The society name is required" />",
	}
});
$("#facname").rules("add", {
	required : true,
	minlength :2,
	maxlength :50,
		messages : {
			required : "<s:text name="The facility name is required" />",
		}
	});
	$("#place").rules("add", {
	required : true,
	minlength :2,
	maxlength :50,
		messages : {
			required : "<s:text name="The place name is required" />",

		}
	});
	$("#facdesc").rules("add", {
	minlength :2,
	maxlength :400,
		messages : {
		}
	});


function townshipfun(ar) {
	var twnmap;
	var objects_township = [];
	ar=ar.replace(/&quot;/ig,'"');
	var locval = JSON.parse(ar);
	$('#township_txt_id').val('');
	$('#township_hidd_id').val('');
    $('#township_txt_id').typeahead({
	     	order: "asc",
			hint: true,
			accent: true,
			offset: true,
			searchOnFocus: true,
	        source: function(query, process) {
	        	objects_township = [];
	        	twnmap = {};
	        var data = locval;
	        $.each(data, function(i, object) {
	        	twnmap[object.label] = object;
	        	objects_township.push(object.label);
	        });
	        process(objects_township);
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
	 if (typeof(objects_township) != "undefined" && objects_township.indexOf(this.value)!=-1) {
        }else{
        	  $('#township_txt_id').val('');
        	  $('#township_txt_id').focus();
	          $('#township_hidd_id').val('');
	          $('#societyId_txt_id').val('');
		      $('#societyId_hidd_id').val('');
		      $('#societyId_txt_id').focus();
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
 			societylistfun(spl[1]);
 			//return temp;
 		}
 	});
}
var objects_society;
function societylistfun(ar) {
	ar=ar.replace(/&quot;/ig,'"');
	var loc_state_val = JSON.parse(ar);
	$('#societyId_txt_id').val('');
    $('#societyId_hidd_id').val('');
    $('#societyId_txt_id').typeahead('destroy');
	$('#societyId_txt_id').typeahead({
     	order: "asc",
		hint: true,
		accent: true,
		offset: true,
		searchOnFocus: true,
        source: function(query, process) {
        	objects_society = [];
        map = {};
        var data = loc_state_val;
        $.each(data, function(i, object) {
            map[object.label] = object;
            objects_society.push(object.label);
        });
        process(objects_society);
       $(".dropdown-menu").css("height", "auto");
       $(".dropdown-menu").addClass("form-control");
    },
    updater: function(item) {
        $('#societyId_hidd_id').val(map[item].id);
        return item;
    }
	}).on('typeahead:selected', function(e, suggestion, dataSetName) {
    }).on('typeahead:closed', function(e) {
    });
		$('#societyId_txt_id').typeahead('refresh');
		//add dynamical validate - 3
	    $('#societyId_txt_id').typeahead('refresh');
        $('#societyId_txt_id').blur(function(){
			if (typeof(objects_society) != "undefined" && objects_society.indexOf(this.value)!=-1) {
        	}else{
        	  $('#societyId_txt_id').val('');
        	  $('#societyId_txt_id').focus();
	          $('#societyId_hidd_id').val('');
	          $('#societyId_txt_id').typeahead('destroy');
          	}
      	});
}
	function cancelFunction() {
		$("#userCancelForm").attr("action", "facilitymgmt");
		$("#userCancelForm").submit();
		$('#userCancelButtonId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass(
				'<s:text name="cardpanel.button.animated.flipoutx"/>');
	}

</script>
</html>
