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
								<h5 class="breadcrumbs-title"><s:text name="View.Forum" /></h5>
								<ol class="breadcrumbs left">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="forummgmt"><s:text name="Breadcrumb.utility" /></a></li>
							<li><a href="forummgmt"><s:text name="Forum" /></a></li>								
							<li class="active"><s:text name="View.Forum" /></li>
						</ol>
							<div class="right">
							<button id="gobckbtnid" class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action" onclick="closeFunction();">Go Back<i class="<s:text name="button.icon.replycard"/>"></i></button>
							</div>
					</div>
				</div>
				</div>
				</div>
				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				<div class="col-md-12">
								<div class="section">
									<div id="topicdiv" class="block-heading" style="display: none;">

										<h4 class="header"> Topic : <span id="topics"></span> </h4>
										<div class="col s1"></div>
										<div class="col s11">
											<div id="moreminhide">
												( <span id="descminshow"></span><a id="moredesc"
													onclick="morefunc();" style="cursor: pointer;"><span
													id="hidemore" style="color: lightseagreen;">...More</span></a>)
											</div>
											<div id="fulldesc">
												( <span id="topicsdesc"></span><a id="lessdesc"
													onclick="morelessfunc();" style="cursor: pointer;"><span
													id="hideless" style="color: lightseagreen;">...Less</span></a>)
											</div>
										</div>
										<div class="text-right" style=""></div>
									</div>
								</div>
								<div class="block-heading">
								<div class="card">
				<div class="row">
				<div class="input-field col s12">
					<div class="input-field col s3">
								<div class="input-fieldcol col s4">
									<s:if test="#session.userProfileImage!=null && #session.userProfileImage!=''">
										<img id="cruntusrimgid" class="hoverable circle responsive-img valign profile-image pointer"
																height="96" name="userProfileImage"
																onclick="toViewlargesizeimgwithsrc(this,'/templogo/profile/web/<s:property value="#session.USERID"/>/<s:property value="#session.userProfileImage"/>');"
																src="/templogo/profile/mobile/<s:property value="#session.USERID"/>/<s:property value="#session.userProfileImage"/>">
									</s:if>
									<s:else>
									<img class="hoverable circle responsive-img valign profile-image" alt="" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" onclick="toViewlargesizeimg(this)">
									</s:else>
								</div>
								<div class="clear height5px"></div>
								<s:if test="#session.FirstName!=null && #session.FirstName.length()>0 ">
									<div> Name : <span> <s:property value="#session.FirstName" /> </span></div>
								</s:if> 
								<s:elseif test="#session.LastName!=null && #session.LastName.length()>0 "> 
									<div> Name : <span> <s:property value="#session.LastName" /> </span></div>
								</s:elseif> 
								<s:else> 
									<div> Name : <span> <s:property value="#session.MobileNo" />  </span></div>
								</s:else>
			</div>												
			<div class="input-field col s9">
					<form  method="post" id="postcomment">
						<div class="row">						
							<div class="input-field col s8">
								<label for="comments" id="commentslabelid" class="control-label">Post Your Commands</label>
								<textarea class="materialize-textarea description " rows="6" style="resize: none" id="comments" ></textarea>
							</div>
													
							<div class="input-field col s1" style="margin-top:20px;width: 200px;">
								<button type="button" class="<s:text name="button.color.submit"/>" id="submitbtn" name="submitbtn" value="submit" onclick="postcommentaction();">
								<s:text name="Post"/><i class="<s:text name="button.icon.submitcard"/> right"></i>
							</button>
							</div>
						<s:hidden value="%{topicsId}" id="topicsId" name="topicsId"> </s:hidden>
					
						</div>
					</form>
			</div>
				
		</div>
		</div></div>
												
		<div id="forumcomments"></div>											
				</div>
								</div>
	</div>
	</div></div></section></div></div>						
<jsp:include page="../common/footer.jsp"></jsp:include>
<s:form method="post" id="userCancelForm"></s:form>
<jsp:include page="../common/clorbox.jsp"></jsp:include>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
<script>
$(document).ready(function() {
	//toShowLoadingImgoverlay();
	var id = $("#topicsId").val();
	$.ajax({
		type : 'post',
		datatype : 'json',
		url : 'forumViewAction',
		data : "topicsId=" + id,
		success : function(data) {
			$("#forumcomments").append(data);
			//alert("=len==="+$("#topicsDescLen").val())
			//alert("=topicsDescMin==="+$("#topicsDescMin").val())
			$("#topicdiv").show();
			$("#topics").html($("#topicname").val());
			$("#descminshow").html($("#topicsDescMin").val());
			$("#topicsdesc").html($("#desc").val());
			if($("#topicsDescLen").val()>150){
				$("#fulldesc").hide();
			}else{
				$("#fulldesc").show();
				$("#hideless").hide();
				$("#moreminhide").hide();
			}
			
			
			if ($("#firstname").val() != null && $("#firstname").val().length >0) {
				$("#fname").html($("#firstname").val());
			}
			else if ($("#lastname").val() != null && $("#lastname").val().length >0) {
				$("#lname").html($("#lastname").val());
			}else{
				$("#mobileNo").html($("#userMobileNo").val());
			}
			tohideLoadingImgoverlay();
			
		},
		error : function(data) {
			//tohideLoadingImgoverlay();
		}
	});
	

});

	
	function postcommentaction(){
		var comm=$("#comments").val();
		var topicsId=$("#topicsId").val();
		if(comm!='' && comm.length>=3){
			comm = comm.replace("\n", "<br/>", 'g');
			toShowLoadingImgoverlay();
		$.ajax({
			type : 'post',
			datatype : '',
			url : 'postCommentAction',
			data : "postComment=" + comm+"&topicsId="+topicsId,
			success : function(data) {
				reloadcomments();
				$("#customdivid").focus();
				//window.location.hash = '#customdivid';
				var scrollPos =  $("#customdivid").offset().top-30;
				 $(window).scrollTop(scrollPos);$("#commentslabelid").removeClass("active");
				tohideLoadingImgoverlay();
			},
			error : function(data) {
				tohideLoadingImgoverlay();
			}
		});
		}else{
			swal('Please enter minimum 3 characters');
		}
		/* }else{
			 $("#commentsvalidate").addClass("has-error");
		      $("#commentsvalidate .help-block").html('<s:text name="Text.select.society" />');
				$("#commentsvalidate .help-block").show();
		} */
	}
	
	function reloadcomments(){		
		var id = $("#topicsId").val();
		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'forumViewAction',
			data : "topicsId=" + id,
			success : function(data) {

				$("#forumcomments").html(data);
				$("#comments").val('');
				$("#topics").html($("#topicname").val());
				if ($("#firstname").val() != '' && $("#lastname").val() == '') {
					$("#fname").html($("#firstname").val());
				} else if ($("#lastname").val() != '' && $("#firstname").val() == '') {
					$("#lname").html($("#lastname").val());
				} else{
					$("#mobileNo").html($("#userMobileNo").val());
				}
				tohideLoadingImgoverlay();
			},
			error : function(data) {
				tohideLoadingImgoverlay();
			}
		});
	}
	function morefunc(){
		$("#moreminhide").hide();
		$("#fulldesc").show();
	}
	
	function morelessfunc(){
		$("#moreminhide").show();
		$("#fulldesc").hide();
	}
	
	function closeFunction() {
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "forumMgmt");
		$("#userCancelForm").submit();
	}
</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
     <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>


</html>	