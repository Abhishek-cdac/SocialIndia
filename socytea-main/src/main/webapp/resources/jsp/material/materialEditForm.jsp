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
<title><s:text name="Text.Title" /></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.material.modify"/></h5>
								<ol class="breadcrumbs left">
									<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
									<li><a href="materialMgmt"><s:text name="Breadcrumb.utility" /></a></li>
									<li><a href="materialMgmt"><s:text name="Breadcrumb.material" /></a></li>
									<li class="active"><s:text name="Breadcrumb.material.modify" /></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
			<div class="card-panel">
										  <form method="post" id="formValidate" name="formValidate" action="materialUpdateAction"> 													 													
 															<div class="row rowmargin12px" >
										 <div class="input-field col s6">
																<label for=card><s:text name="Category Name" /><span class="mandatory">*</span></label>
																<s:textfield  name="materialObj.categoryName" id="card"  cssClass="form-control typeahead tt-query categoryType left" autocomplete="off"/>
																 <!--  <input  type="hidden" name="categoryType" id="cardid" class="form-control"/> --> 
																 <span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span> 
																  <s:hidden  name="materialObj.materialCategoryId" value="%{materialObj.materialCategoryId}" id="cardid" class="form-control"></s:hidden>
																	 <%-- <s:hidden   id="cardid"  cssClass="form-control"></s:hidden> --%>	
																	
															
													</div>
															 <div class="input-field col s6">														
																		<label for=materialname><s:text name="Material Name" /><span class="mandatory">*</span></label>
																		<s:textfield name="materialObj.materialName"  id="materialname"  cssClass="form-control materialName" />
																	
															</div>
															</div>
 													<div class="row rowmargin12px" >
										 <div class="input-field col s6">
																<label for=totalquty>Total Quantity<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.totalQnty" id="totalquty" onkeyup="textValidate(this,'4','PHNM');" cssClass="form-control totalQnty" />
															
															</div>
															 <div class="input-field col s6">
																<label for=usedquty>Used Quantity<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.usedQnty" onkeyup="textValidate(this,'4','PHNM');" id="usedquty" cssClass="form-control usedQnty" />
															
															</div>
 															 </div>
												
														<div class="row rowmargin12px" >
										 <div class="input-field col s6">
																<label for=availablequty>Material Avail. Quantity<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.materialQnty" id="availablequty" onkeyup="textValidate(this,'4','PHNM');" cssClass="form-control materialQnty" />
															
															</div>
															 <div class="input-field col s6">
																<label for=materialprice>Material Price<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.materialPrice"  onkeyup="textValidate(this,'10','DT');" id="materialprice" cssClass="form-control materialPrice" />
															
															</div>
															
														</div>	
														<div class="row rowmargin12px" >
										<%--  <div class="input-field col s6">
																 <label>Purchase Date<span class="mandatory">*</span></label> 															
															     <div class="input-group input-append date" id="datePicker">
															     <s:textfield name="materialObj.purDate" id="frmdateid"  placeholder="Choose Purchase Date" cssClass="form-control forumdatevalidate" />
                												<!-- <input type="text" class="form-control" name="forumDate"  placeholder="Enter Date Of Birth"/> -->
               											 <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
           													
															  </div>   															            											        					
															</div> --%>
															<div class="input-field col s6">
												 <label for="purchasedate">Purchase Date<span class="mandatory">*</span></label> 																														
												<s:textfield  cssClass="dateOfBirth" name="materialObj.purDate" id="purchasedate"></s:textfield>
												</div>
														</div>	
														<%-- <div class="row rowmargin12px" >
										 <div class="input-field col s6">
																<label><s:text name="Material Desc" /></label>
																<s:textarea name="materialObj.materialDesc" value="%{materialObj.materialDesc}"  class="form-control custom-control description"  style="resize:none"/>
															<s:textarea name="materialObj.materialDesc" cssClass="form-control custom-control description" style="resize:none"></s:textarea>
															
															</div> 
														
														</div>	 --%>
														<div class="row rowmargin12px" >
								<div class="input-field col s12">
									<label class="active" for="textarea1"><s:text name="Text.material.desc" /></label>
											<s:textarea cssClass="materialize-textarea" name="materialObj.materialDesc" />
									<%-- <s:textarea id="textarea1" name="materialObj.materialDesc" class="materialize-textarea" ></s:textarea> --%>
								</div>

							</div>		
														<s:hidden name="materialObj.materialId" value="%{materialObj.materialId}"></s:hidden>	
																																	 		
							<div class="clear height10px"></div>
							<div>
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
						</form> 
										</div>
	
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				
				<!--end container-->
			</section>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
</body>


<script type="text/javascript">
$('#formValidate').validate({
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
	
$("#card").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.customerreg.material.category" />",
	}
	
});
$("#materialname").rules("add", {
	required : true,
	minlength :2,
	maxlength :100,
	messages : {
		required :"<s:text name="Error.customerreg.material.name" />",
	}
	
});
$("#totalquty").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.qnty.total.length" />",
	}
	
});
$("#usedquty").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.qnty.used.length" />",
	}
	
});
$("#availablequty").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.qnty" />",
	}
	
});
$("#materialprice").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.price.length" />",
	}
	
});
$("#purchasedate").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.customerreg.material.purchasedate" />",
	}
	
});

  	
$(document).ready(function(){
	
	
	
	$.ajax({
		type : 'post',
		datatype : 'json',
		url : 'getmaterialcategory',
		data : '',
		success : function(data) {
			
			var arr=data.split("!_!");
			//$("#multiselectdiv").html(data);
			//statelistload(arr[1]);
			materialcategoryfun(arr[1]);
		}
	});
});

function materialcategoryfun(ar)
{
	var objects_cardtype;		
	ar=ar.replace(/&quot;/ig,'"');
	var locval = JSON.parse(ar);	
 $('#card').typeahead({
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
	        $('#cardid').val(map[item].id);
	        return item;
	    }
	});
 $('#card').blur(function(){				
		if (objects_cardtype.indexOf(this.value)!=-1) {			
        }else{            
        	  $('#card').val('');        	         	 
        	  $('#card').focus();
	          $('#cardid').val('');       
          }		
      });
	}



 function cancelFunction(){
	$("#userCancelForm").attr("action", "materialMgmt");
	$("#userCancelForm").submit();
}
</script>
</html>
