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
								<h5 class="breadcrumbs-title"><s:text name="text.currentprocess" /></h5>
								<ol class="breadcrumbs left">
									<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="systemtrack"><s:text name="Monitoring" /></a></li>
								<li class="active"><s:text name="text.currentprocess" /></li>
								</ol>
					<div class="right">
						<button class="<s:text name="button.color.refresh"/>" type="button" name="rfsbutonid" id="rfsbutonid">Refresh
                        <i class="<s:text name="crt.button.icon.refresh"/>"></i>
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
				<div id="sysdetlid" name="sysdetlid">																			
				
				</div>
					</div>
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
			</section>
		</div>
	</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>		
</body>
<script>
 $(document).ready(function() {	
	 togetMemorydetails(); 
	 $("#rfsbutonid").click(function(){
		 togetMemorydetails();
	 });
	 
	
});
 function togetMemorydetails(){
	 toShowLoadingImgoverlay();
		$.ajax({
		type : 'post',
		datatype : 'json',
		url : 'GetJavaHeapMemory',
		data : '',
		success : function(data) {
			$("#sysdetlid").html(data);	
			tohideLoadingImgoverlay();		
		},
		error : function(data){
			tohideLoadingImgoverlay();
			}
	});  	
	 }
 </script>
</html>