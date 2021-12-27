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
<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
								<h5 class="breadcrumbs-title"><s:text name="Text.Tender.view" /></h5>
								<ol class="breadcrumbs left">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li class="active"><a href="tendermgmt"><s:text name="Text.Utility.Management" /></a></li>
							<li><a href="tendermgmt"><s:text name="Text.Tender" /></a></li>
								<li class="active"><s:text name="Text.Tender.view" /></li>
								</ol>
								<div class="right">
					<button id="gobckbtnid" class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button>
				</div>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
					<div class="card-panel">
											 <form action="" name="formValidate" id="formValidate" method="post">
											   <s:if test="#session.GROUPCODE ==1 || #session.GROUPCODE==2">
											                  <div class="row">
                                                                  <div class="input-field col m6"> 
                                                                            <div class="row">
                                                                    <div class="input-field col m5">Township Name</div>
                                                                    <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="townshipname"/></span></div>
                                                                      </div>
                                                                   </div></div><div style="height:10px;" class="clear"></div>
                                                                   <div class="row">
                                                                  <div class="input-field col m6"> 
                                                                            <div class="row">
                                                                    <div class="input-field col m5">Society Name</div>
                                                                    <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="tenderobj.societyname"/></span></div>
                                                                      </div>
                                                                   </div></div><div style="height:10px;" class="clear"></div>
											                       </s:if>
										                      	 <div class="row">
                                                                  <div class="input-field col m6"> 
                                                                            <div class="row">
                                                                    <div class="input-field col m5"><s:text name="Text.tendername"/></div>
                                                                    <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="tenderobj.tenderName"/></span></div>
                                                                      </div>
                                                                   </div></div><div style="height:10px;" class="clear"></div>
                                                                        <div class="row">
                                                                 <div class="input-field col m6">          
                                                                <div class="row">
                                                                     <div class="input-field col m5"><s:text name="Text.tenderdate"/> </div>
                                                                 <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="tenderobj.tenderDate"/></span></div>
                                                                           </div>
                                                                     </div>
							                                     </div><div style="height:10px;" class="clear"></div>											 											
															<div class="row">
  														 <div class="input-field col m6"> 
                                                            <div class="row">
                                                             <div class="input-field col m5">Tender File </div>
                                                             <div class="input-field col m7">
                                                             <div style="float: left"> :</div>
											                 <div style="float: left">                                                                   												
															 <s:iterator value="tenderdocList">
																<s:if test="(documentType.equalsIgnoreCase('png') || documentType.equalsIgnoreCase('jpg') || documentType.equalsIgnoreCase('png')||documentType.equalsIgnoreCase('gif')||documentType.equalsIgnoreCase('jpeg')||documentType.equalsIgnoreCase('tiff'))" >
																	<div class="hoverable left"><img class="tndrimg hoverable" id="imgTENDERid" src="/templogo/tender/mobile/<s:property value="#session.tenderSessID"/>/<s:property value="documentName"/>" onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/tender/web/<s:property value="#session.tenderSessID"/>/<s:property value="documentName"/>')"></div>
																</s:if>
																<s:else>															
																	<div class="hoverable left"><a class="pointer tooltipped " data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Download" id="documenttypedivid" href="<s:property value="filedownloadurl"/>" ><i class="hoverable tinysmall  mdi-file-file-download  pink-text text-accent-3"></i></a></div>																		
																</s:else>
															</s:iterator>															
															</div></div>
                                                           </div></div>
															</div>
														  <div style="height:5px;" class="clear"></div>
														  <div class="row">
                                                                 <div class="input-field col m12">          
                                                                <div class="row">
                                                                     <div class="input-field col " style="width: 20.7%;"><s:text name="Tender Detail"/> </div>
                                                                 <div class="input-field col m9">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="tenderobj.tenderDetails"/></span></div>
                                                                           </div>
                                                                     </div>
							                                     </div>
																
										 <div style="float:left;height:340px; display:none;" id="verticalline"></div> 
                                         </form>
					<s:form method="post" id="userCancelForm"></s:form>
					<form id="downloadFormid" method="post" action="downloadfilestender">
						<s:hidden id="downloadFormval" name="fileName" value=""></s:hidden>
						<s:hidden id="downloadFormvalpath" name="fileNamePath" value=""></s:hidden>
						</form>
				</div>
				<!--end container-->
				</div>
			</section>

		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/clorbox.jsp"></jsp:include>
    <jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
    
    </body>
			
<script>
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "tendermgmt");
		$("#userCancelForm").submit();
	});	   
});


</script></html>
