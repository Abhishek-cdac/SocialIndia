<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description" content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
<meta name="keywords" content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
<title><s:text name="Text.Title" /></title>
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />
<style>
#token-input-labornames
{ width: 100% ! important; }
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
					<div class="header-search-wrapper grey hide-on-large-only">
						<i class="mdi-action-search active"></i> <input type="text"
							name="Search" class="header-search-input z-depth-2"
							placeholder="Explore Materialize">
					</div>
					<div class="container">
					<div class="row">
							<div class="col s12 m12 l12">
						<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.util.bookingmgmt" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="bookingmgmttbl"><s:text name="Breadcrumb.utility" /></a></li>
								<li><a href="bookingmgmttbl"><s:text name="Breadcrumb.util.bookingmgmt" /></a></li>
							<li class="active"><s:text name="Breadcrumb.manage.bookingdetail" /></li>
							</ol>
							
							<div class="right">
							<button id="gobckbtnid" class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button>
							</div>
					</div>
				</div>						
				</div></div>
				<div class="container">
				
				<div class="card-panel">
				<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> Booking Details</div>

<div class="collapsible-body padding10px" id="firstdivid">

										<div class="row">
											<div class="input-field col s6">
											<div class="row">
											
											 
  														  <div class="input-field col m5" >Booking Resident Name</div> <div class="input-field col m7"> : 
  														  <a href="viewresidentdetails?deleteresidentid=<s:property value="bookingobj.bookedusrid"/>" target="_blank" data-position="bottom" data-delay="10" data-tooltip="Click to View" class="pointer tooltipped" data-tooltip-id="afe82cc5-50ea-f1ba-0b91-472358326b9b">
  														  <s:property value="bookingobj.bookedBy" /></a>
  														  </div>	
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
												  
													<div class="input-field col m5"><s:text name="Text.title"></s:text></div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.facTitle" /></span></div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
												<div class="input-field col m5" ><s:text name="Text.table.bookingplace"></s:text></div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.place"/></span></div>
													 
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text name="Text.customerreg.mobileno"></s:text></div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.contactNo"/></span></div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text name="Text.table.bookingstarttime"></s:text></div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.startTime"/></span></div> 
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text name="Text.table.bookingendtime"></s:text></div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.endTime"/></span></div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5">Facility Name</div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.title"/></span></div> 
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" >Facility Place</div> <div
														class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.facplace"/></span></div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="input-field col m12">
												<div id="divfirstname" class="row">
													<div class="input-field col " style="width: 20.7%;"><s:text name="Text.desc"></s:text></div> <div
														class="input-field col m9"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.description"/></span></div> 
												</div>
											</div>
											
										</div>
										<div class="row">
											<div class="input-field col m12">
												<div id="divfirstname" class="row">
													<div class="input-field col " style="width: 20.7%;">Facility Description</div> <div
														class="input-field col m9"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.facdesc"/></span></div> 
												</div>
											</div>
											
										</div>
										</div>
										</li>
										
										<s:if test="bookingobj.bookingStatus == 0">
										<li>
										<div class="collapsible-body padding10px" id="seconddivid" style="display:block;">
										<div class="row">
										<div class="input-field col s6">
												<div class="row">
						<div class="input-field col s12" >
						<button type="button" id="userCreateButtonId" onclick="ApprovalFunction();" class="<s:text name="button.color.submit"/>">
						APPROVE <i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="declineFunction();">
						DECLINE<i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>	
					</div>
											</div>
											</div>
											</div>
										</li>
										</s:if>
										<s:else>
										<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="tooltipped mdi-action-verified-user tinysmall white-text text-lighten-2"></i> Approve Status</div>                    
<div class="collapsible-body padding10px" id="seconddivid" style="display:block;">

<div class="row">
							<s:if test="bookingobj.bookingStatus == 1">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text name="Text.table.bookingstatus"></s:text></div>
														<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> Approved</span></div>
												</div>
											</div>
											</s:if> 
											<s:elseif test="bookingobj.bookingStatus == 2">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text name="Text.table.bookingstatus"></s:text></div>
														<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> Declined</span></div>
												</div>
											</div>
											</s:elseif>
											<s:else>
											<div class="input-field col s6">
												<div class="row">
						<div class="input-field col s12" >
						<button type="button" id="userCreateButtonId" onclick="ApprovalFunction();" class="<s:text name="button.color.submit"/>">
						APPROVE <i class="<s:text name="button.icon.submitcard"/>"></i></button>
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="declineFunction();">
						DECLINE<i class="<s:text name="button.icon.replycard"/>"></i></button>
					</div>	
					</div>
											</div>
											</s:else>
										</div>
										<s:if test="bookingobj.bookingStatus == 1 || bookingobj.bookingStatus == 2">
										<div class="row">
											<div class="input-field col m12">
												<div id="divfirstname" class="row">
													<div class="input-field col " style="width: 20.7%;">Comments</div> <div
														class="input-field col m9"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="bookingobj.commiteecomment"/></span></div> 
												</div>
											</div>
											
										</div>
										</s:if>
							</div>
										
											</li>
										</s:else>
										
										
												</ul>
										<div class="clear" style="height: 5px;"></div>										
												</div>
												<div class="clear" style="height: 10px;"></div>
													</div>						
										</section>									
										</div>
										<s:form method="post" id="declineapprovalform">
											<s:hidden name="bookingobj.bookingId" id="bookinguniqid" ></s:hidden>
											<s:hidden name="uniqueId" id="bookuniqid" ></s:hidden>
											 <s:hidden name="committeecomments" id="iVOcommitteecomments" class="form-control " />
										</s:form>	
										<s:form method="post" id="userCancelForm">
											
										</s:form>										
									</div>					
			<jsp:include page="../common/footer.jsp"></jsp:include>
			<jsp:include page="../common/clorbox.jsp"></jsp:include>
			<jsp:include page="../common/allbasicjs.jsp"></jsp:include>		
			<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script> 
</body>
<script>
$(document).ready(function(){	
	var approvests='<s:property value="bookingobj.bookingStatus"/>';
	if(approvests== 0) { $("#firstdivid").css("border","0px");
		} else { }
});


$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "bookingmgmttbl");
		$("#userCancelForm").submit();
	});	   
});

function declineFunction()
{
	swal({
		title : "Comments",   
		 text: "<textarea id='comment' class='form-controll' maxlength='100'></textarea>",
		 html: true,
		showCancelButton : true,
		closeOnConfirm : false,
		animation : "slide-from-top",
		inputPlaceholder : "Enter Comments"
	}, function(inputValue) {
		if (inputValue === false)
			return false;
		if (inputValue === "") {
			swal.showInputError("Enter Comments");
			return false;
		}
		 var val = document.getElementById('comment').value;
		$("#iVOcommitteecomments").val(val);
	    $("#declineapprovalform").attr("action", "bookingdeclineaction");
		$("#declineapprovalform").submit();
});
}
function ApprovalFunction()
{
	swal({
		title : "Comments",   
		 text: "<textarea id='comment' class='form-controll' maxlength='100'></textarea>",
		 html: true,
		showCancelButton : true,
		closeOnConfirm : false,
		animation : "slide-from-top",
		inputPlaceholder : "Enter Comments"
	}, function(inputValue) {
		if (inputValue === false)
			return false;
		if (inputValue === "") {
			swal.showInputError("Enter Comments");
			return false;
		}
		 var val = document.getElementById('comment').value;
		$("#iVOcommitteecomments").val(val);
	 $("#declineapprovalform").attr("action", "bookingapprovalaction");
		$("#declineapprovalform").submit();
	});
}

</script>
</html>
