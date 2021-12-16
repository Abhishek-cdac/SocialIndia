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
								<h5 class="breadcrumbs-title"><s:text name="Skill Modify" /></h5>
								<ol class="breadcrumbs">
								<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.util.configmgmt"/></a></li>
                                   <li><a href="skillmgmt"><s:text name="Text.skillnamemgmt"/></a></li>
									<li class="active"><s:text name="Skill Modify" /></li>  
						</ol>
					</div>
				</div>
				</div>
				</div>
				<div class="container">
				 <div id="jqueryvalidation" class="section">
						<div class="card-panel">
							<form role="form" id="skillUpdateFormId" method="post"
								enctype="multipart/form-data">
								<div class="col m12">
									<div class="row">
										<div class="input-field col s6">
											<label for="category_txt_id"><s:text
													name="Select Category" /></label>
											<s:textfield name="category_id_name" id="category_txt_id"
												cssClass="form-control typeahead tt-query" readonly="true"
												autocomplete="off" />
											<s:hidden name="category_hidd_id" id="category_hidd_id"
												class="form-control" />
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6">
											<label for="skillname" class="control-label"><s:text
													name="Skill Name" /><span class="mandatory">*</span></label>
											<s:textfield cssClass="form-control skillnames"
												name="skillname" id="skillname" autocomplete="off"></s:textfield>
										</div>
									</div></div>
									<div class="clear">
										<button type="submit" id="userCreateButtonId"
											class="<s:text name="button.color.submit"/>">
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
									<s:hidden name="skillId" id="skillId"></s:hidden>
							</form>
						</div>
						<div style="clear: both; height: 5px;"></div>

						<s:form method="post" id="userCancelForm"></s:form>

					</div>
						</div>						
						</section>
						</div>
						</div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<!-- ================================================
    Scripts
    ================================================ -->
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
<script>
/* $(document).ready(function(){
$.ajax({
	type : 'post',
	datatype : 'json',
	url : 'getCategoryvalue',
	data : '',
	success : function(data) {
		var arr=data.split("!_!");
		CategorylistFun(arr[1]);
	}
});
});
function CategorylistFun(ar) {
	var objects_category;		
    ar=ar.replace(/&quot;/ig,'"');		     
    ar=ar.replace(/%27/ig,"'");
    ar=ar.replace(/&amp;/ig,'&');	
	var loccutyval = JSON.parse(ar);
	$('#category_txt_id').typeahead({		 
 	order: "asc",
	hint: true,
	accent: true,
	offset: true,
	searchOnFocus: true, 
    source: function(query, process) {
    objects_category = [];  
    map = {};		      
    var data = loccutyval;
    $.each(data, function(i, object) {
        map[object.label] = object;
        objects_category.push(object.label);
    });
    process(objects_category);
   $(".dropdown-menu").css("height", "auto");
   $(".dropdown-menu").addClass("form-control");
},
updater: function(item) {
		$('#category_hidd_id').val(map[item].id);
		$('#skills_txt_id').typeahead('destroy');
		return item;
	}
});

$('#category_txt_id').blur(
		function() {
			if (typeof (objects_category) != "undefined" && objects_category.indexOf(this.value) != -1) {
			} else {
				$('#category_txt_id').val('');
				$('#category_txt_id').focus();
				$('#category_hidd_id').val('');
				$('#skills_txt_id').typeahead('destroy');
			}
		});
}
 */
$(document).ready(function(){
	
	$('#skillUpdateFormId').validate({
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
	$("#skillname").rules("add", {
		required : true,
		minlength :2,
		maxlength :50,
		messages : {
			required : "<s:text name="Error.skilnam.skillname" />",
			//minlength : "Enter at least 5 characters"
		}
	});

	
	$("#userCreateButtonId").click(function(){
	 	 $("#skillUpdateFormId").attr("action","skillUpdate"); 
		$("#skillUpdateFormId").attr("method","Post");
		$("#skillUpdateFormId").attr("enctype","multipart/form-data");
		$("#skillUpdateFormId").submit();

	});	
});	
 function cancelFunction(){
	$("#userCancelForm").attr("action", "skillmgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}

</script>
 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>


</html>											