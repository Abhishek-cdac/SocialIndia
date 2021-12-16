<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible"
	content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight"
	content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<link
	href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link
	href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/Alert.jsp" flush="true" />
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
								<h5 class="breadcrumbs-title">
									<s:text name="Database Info" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome"/></a></li>
									<li><a href="#"><s:text name="Breadcrumb.societymgmt"/></a></li>
									<li><a href="#"><s:text	name="Breadcrumb.biometric.createdatabase" /></a></li>
								</ol>
							</div>
						</div>
					
					
					</div>
				
				</div>
				 
				<div class="container">
					<div class="card-panel">
						<form method="post" id="registerformid" name="registerformid"
							action="biometricdatabase" enctype="multipart/form-data">
							<div class="row">
								<div class="col s12 m8 l9">
									<div class="row">
										<div class="input-field col s6">
											<label for="hostname"><s:text name="Text.hostname"></s:text><span
												class="mandatory">*</span></label>
											<s:textfield name="biodatabaseinfo.bioHostname" id="hostname"
												cssClass="form-control registerNumber"
												value="%{#session.biohostname}">
											</s:textfield>
											<s:fielderror fieldName="biodatabaseinfo.bioHostname" />
										</div>
										<div class="input-field col s6">
											<label for="port"><s:text name="Text.portnumber"></s:text><span
												class="mandatory">*</span></label>
											<s:textfield cssClass="form-control registerNumber"
												name="biodatabaseinfo.bioPort" id="port"
												value="%{#session.bioport}"></s:textfield>
											<s:fielderror fieldName="biodatabaseinfo.bioPort" />
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6">
											<label for="database"><s:text
													name="Text.databasename" /><span class="mandatory">*</span></label>
											<s:textfield cssClass="form-control registerNumber "
												name="biodatabaseinfo.bioDatabase" id="database"
												value="%{#session.biodatabase}"></s:textfield>
											<s:fielderror fieldName="biodatabaseinfo.bioDatabase" />
										</div>
										<div class="input-field col s6">
											<label for="username"><s:text
													name="Text.databaseusername" /><span class="mandatory">*</span></label>
											<s:textfield cssClass="form-control registerNumber "
												name="biodatabaseinfo.bioUsername" id="username"
												value="%{#session.biousername}"></s:textfield>
											<s:fielderror fieldName="biodatabaseinfo.bioUsername" />
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6">
											<label for="password"><s:text name="Text.password" /><span class="mandatory">*</span></label>
											<s:password name="biodatabaseinfo.bioPassword" id="password"
												cssClass="form-control registerNumber"
												value="%{#session.biopassword}" showPassword="true"></s:password>
											<s:fielderror fieldName="biodatabaseinfo.bioPassword" />
										</div>
									</div>
								</div>

								<div class="input-field col s6">
									<button type="submit" id="userCreateButtonId"
										class="<s:text name="button.color.submit"/>">
										<s:text name="Text.button.submit" />
										<i class="<s:text name="button.icon.submitcard"/>"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>

				<!--end container-->
         
			</section>

		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	</body>
	
	<script type="text/javascript">
	$(document)
	.ready(
			function() {
				$('#registerformid').validate({
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
					}
				});
				
				$("#hostname")
						.rules(
								"add",
								{
									required : true,
									minlength : 11,
									maxlength : 15,
									messages : {
										required : "<s:text name="Error.biometric.name" />",
									}

								});

				$("#port")
						.rules(
								"add",
								{
									required : true,
									number : true,
									minlength : 2,
									maxlength : 6,
									messages : {
										required : "<s:text name="Error.biometric.portnumber" />",
									}
								});
				$("#database")
						.rules(
								"add",
								{
									required : true,
									minlength : 2,
									maxlength : 45,
									messages : {
										required : "<s:text name="Error.biometric.databasename" />",
									}
								});
				$("#username")
				.rules(
						"add",
						{
							required : true,
							minlength : 2,
							maxlength : 45,
							messages : {
								required : "<s:text name="Error.biometric.username" />",
							}
						});
				$("#password")
				.rules(
						"add",
						{
							required : true,
							minlength : 2,
							maxlength : 45,
							messages : {
								required : "<s:text name="Error.biometric.password" />",
							}
						});
		});	
	</script>
</html>	
