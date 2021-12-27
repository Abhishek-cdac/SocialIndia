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
   <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
                <h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.laborfeedbck" /></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
				<li><a href="labourmgmt"><s:text name="Breadcrumb.manage" /></a></li>
				<li><a href="labourmgmt"><s:text name="Breadcrumb.manage.labormgmt" /></a></li>
                    <li class="active"><s:text name="Breadcrumb.manage.laborfeedbck"/></li>
                   </ol>
                  <div class="right">
                    <button type="button" id="gobckbtnid" class="<s:text name="button.color.cancel" />"" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back"><i class="<s:text name="button.icon.replycard"/>"></i>Go Back</button>
                </div>                               						
              </div>
              
            </div>
            
          </div>
				</div>
<div class="container">
<div class="card-panel">
<div class="input-field col m12">
<div class="input-field col m5">
<h5>Feedback for <span class="bold bold teal-text text-darken-2"><s:property value="laborName_hidd_txt"/></span></h5>
</div>
</div>					
<s:if test="FeedbackList.size()==0">
 	<div class="input-field col m12">
	<div class="input-field col m5">No Feedback For <s:property value="laborName_hidd_txt"/></div>
	</div>
</s:if> 
<s:else>
<s:iterator value="FeedbackList">
<div class="card teal lighten-5 padding10px">
<div class="row" >
<!-- Image Display Start  -->
	<s:if test="ivrFEEDBK_PRO_IMG!=null && ivrFEEDBK_PRO_IMG!='' && ivrFEEDBK_PRO_MobIMG != null && ivrFEEDBK_PRO_MobIMG!=''">
	<div class="col s1">	
		<div class="">
		<img class="circle responsive-img valign profile-image pointer margintop10px" src="<s:property value="ivrFEEDBK_PRO_MobIMG"/>"   onclick="toViewlargesizeimgwithsrc(this.id,'<s:property value="ivrFEEDBK_PRO_IMG"/>');" id="memberprofimg_<s:property value="ivrBnFEEDBK_ID"/>"/>
		</div> 
	</div>
	</s:if>
	<s:else>
	<div class="col s1">	
		<div class="">
		<img class="circle responsive-img valign profile-image pointer margintop10px" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png"   onclick="toViewlargesizeimgwithsrc(this.id,'<s:text name="Resource.path"/>/images/social/profile-default-male.png');" id="memberprofimg_<s:property value="ivrBnFEEDBK_ID"/>"/>
		</div> 
	</div>
	</s:else>
<!-- Image Display End-->

<div class="col s11">	
	<div class="col m4">
		<div class="row">
		<div class="col m12">
	  		<div class="col m2">Name </div>
	  		<div class="col m1">:</div>
	  		<div class="col m9"><span class="<s:text name="view.fontvalue.color" />">  <s:property value="ivrFEEDBK_FNAME"/></span></div>
		</div>
		</div>
		<div class="row">
		<div class="col m12">
			<div class="input-field col m2">Date </div>
	  		<div class="input-field col m1">:</div>
		  	<div class="input-field col m9"><span class="<s:text name="view.fontvalue.color" />"><s:property value="ivrFEEDBK_DATE"/></span></div>
		</div>
		</div>
		<div class="row">
		<div class="col m12">
		  	<div class="input-field col m2">Rating </div>
	  		<div class="input-field col m1">:</div>
		  <s:if test="ivrBnRATING==0">
			<div class="col s9" style="margin-top:-10px;">
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Not Rating</span></div>
			</div>
		</s:if>
		  	<s:elseif test="ivrBnRATING==1">
			<div class="col s9 " style="margin-top:-10px;">
			: <i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Very Bad</span></div></div>
		</s:elseif>
		<s:elseif test="ivrBnRATING==2">
			<div class="col s9 ">
			: <i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>			  
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Bad</span></div></div>
		</s:elseif>
		<s:elseif test="ivrBnRATING==3">
			<div class="col s9 ">
			<i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			 <i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Not Good</span></div></div>
		</s:elseif>
		<s:elseif test="ivrBnRATING==4">
			<div class="col s9 " ><i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Good</span></div></div>
		</s:elseif>
		<s:elseif test="ivrBnRATING==5">
			<div class="col s9 " >
			<i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			  <i style="color: #ff528d" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Excellent</span></div></div>
		</s:elseif>
		<s:else>
			<div class="col m9 " ><i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting.tinysmll"/>"></i>
		<div style="margin-left: 10px;"><span class="<s:text name="view.fontvalue.color" />">Not Rating</span></div></div>
		</s:else>
		  	
		  	<!-- <div class="input-field col m8"><span class="<s:text name="view.fontvalue.color" />"><s:property value="ivrBnRATING"/></span></div> -->
		</div>
		</div>
	</div>
	<div class="col m8">
		<div class="row">
		<div class="col m12">
		<div class="input-field col m12"><p><s:property value="ivrBnFEEDBK_TXT" /></p></div>
		</div>
	</div>
</div>
</div>	
</div>
</div>	
</s:iterator>
</s:else>
</div>
<s:form method="post" id="userCancelForm">
<s:hidden name="LaboruniqueId" id="uniqlaborIdEdit"></s:hidden>
<s:hidden name="laborName_hidd_txt" id="laborName_hidd_id"></s:hidden>
</s:form>
				</div>
				<!--end container-->				
			</section>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
<jsp:include page="../common/clorbox.jsp"></jsp:include>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
</body>
</body>
<script>
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "labourmgmt");
		$("#userCancelForm").submit();
	});	   
});
 function closeFunction(){
	$("#usercloseForm").attr("action", "fourmaction");
	$("#usercloseForm").submit();
}
</script>
</html>
