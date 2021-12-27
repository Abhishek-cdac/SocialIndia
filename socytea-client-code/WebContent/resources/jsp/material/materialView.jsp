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
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.material.view" /></h5>
								<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="materialMgmt"><s:text name="Breadcrumb.utility" /></a></li>
								<li><a href="materialMgmt"><s:text name="Breadcrumb.material" /></a></li>
								<li class="active"><s:text name="Breadcrumb.material.view" /></li>
								</ol>								
				<div class="right">
						<button id="gobckbtnid" type="button" class="<s:text name="button.color.cancel"/>"  data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back"><i class="<s:text name="button.icon.replycard"/>"></i>Go Back</button>
				</div>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">
					<div class="card-panel">
					 <form id="residentCreationFormId" name="residentCreationFormId" action="societymanage" method="post" enctype="multipart/form-data">
                             
                             <s:if test="#session.GROUPCODE ==1 || #session.GROUPCODE==2">
                             
                            
							<div class="row">
								<div class="input-field col m6">
									<div class="row">
										<div class="input-field col m5">Township Name</div>
										<div class="input-field col m7">
											:<span class="<s:text name="view.fontvalue.color" />">
												<s:property value="townshipname" />
											</span>
										</div>
									</div>
								</div>
								<div class="input-field col m6">
									<div class="row">
										<div class="input-field col m5">Society Name</div>
										<div class="input-field col m7">
											:<span class="<s:text name="view.fontvalue.color" />">
												<s:property value="materialMst.societyName" />
											</span>
										</div>
									</div>
								</div>
							</div>
                              </s:if>
							<div class="row">
							<div class="input-field col m6">
									<div class="row">
										<div class="input-field col m5">Category Name</div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />">
												<s:property value="materialMst.categoryName" />
											</span>
										</div>
									</div>
								</div>
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">Material Name</div>
                   <div class="input-field col m7">:<span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.materialName"/></span></div>
                      </div>
                    </div>
                   
							</div>
							      
							      <div class="row">
							      <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">Total Quantity</div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.totalQnty"/></span></div>
  						</div>
                     </div>
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">Used Quantity</div>
                   <div class="input-field col m7">:<span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.usedQnty"/></span></div>
                      </div>
                    </div>
                   
							</div>
						
						 <div class="row">
						 <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">Material Avail. Quantity</div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.materialQnty"/></span></div>
  						</div>
                     </div>
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">Quantity Price</div>
                   <div class="input-field col m7">:<span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.materialPrice"/></span></div>
                      </div>
                    </div>
                   
							</div>
					 <%-- <div class="row">
                <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5"><s:text name="Text.material.desc" /></div>
                   <div class="input-field col m7 breakword">:<span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.materialDesc"/></span></div>
                      </div>
                    </div>
                    </div> --%>
                    <div class="row">
                    <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">Purchase Date</div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.purDate"/></span></div>
  						</div>
                     </div></div>
                     <div class="row">
                <div class="input-field col ">
                  <div class="row">
                  <div class="input-field col m10">Material Description</div>
                   <div class="input-field col breakword"><span class="<s:text name="view.fontvalue.color" />"> <s:property value="materialMst.materialDesc"/></span></div>
                      </div>
                    </div>
                    </div>
                    
                    </form>
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				<!--end container-->
				</div>
			</section>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
<script type="text/javascript">
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "materialMgmt");
		$("#userCancelForm").submit();
	
	});	   
});
</script>
</body>
</html>
