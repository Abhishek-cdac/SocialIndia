<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="<s:text name="meta.contentType"/>">
<meta name="viewmin_gst_amt" content="<s:text name="meta.viewmin_gst_amt"/>">
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
									<s:text name="GST Info" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text	name="Breadcrumb.gst" /></a></li>
<%-- 									<li><a href="#"><s:text	name="Breadcrumb.gst.createmin_amt" /></a></li> --%>
								</ol>
							</div>
						</div>
					</div>
				</div>
				 
				<div class="container">
				<jsp:include page="../common/Alert.jsp" flush="true" />
					<div class="card-panel">
						<form method="post" id="registerformid" name="registerformid"
							action="managegst" enctype="multipart/form-data">
							<div class="row">
								<div class="col s12 m8 l9">
									<div class="row">
										<div class="input-field col s6">
											<label for="gst"><s:text name="Text.gst"></s:text><span
												class="mandatory">*</span></label>
											<s:textfield name="manageGST.gstNum" id="gst_num"
												cssClass="form-control registerNumber"
												value="%{#session.gstNum}">
											</s:textfield>
											<s:fielderror fieldName="gst.gst_num" />
										</div>
										<div class="input-field col s6">
											<label for="min_gst_amt"><s:text name="Text.gstminamt"></s:text><span
												class="mandatory">*</span></label>
											<s:textfield cssClass="form-control registerNumber"
												name="manageGST.minGstAmt" id="min_gst_amt"
												value="%{#session.minGstAmt}"></s:textfield>
											<s:fielderror fieldName="gst.min_gst_amt" />
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6">
											<label for="min_amt"><s:text
													name="Text.gstamt" /><span class="mandatory">*</span></label>
											<s:textfield cssClass="form-control registerNumber "
												name="manageGST.minAmt" id="min_amt"
												value="%{#session.minAmt}"></s:textfield>
											<s:fielderror fieldName="gst.min_amt" />
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
				
				$("#gst_num")
						.rules(
								"add",
								{
									required : true,
									minlength : 1,
									maxlength : 45,
									messages : {
										required : "<s:text name="Error.gst.num" />",
									}

								});

				$("#min_gst_amt")
						.rules(
								"add",
								{
									required : true,
									number : true,
									minlength : 1,
									maxlength : 45,
									messages : {
										required : "<s:text name="Error.gst.min_amt" />",
									}
								});
				$("#min_amt")
						.rules(
								"add",
								{
									required : true,
									minlength : 1,
									maxlength : 45,
									messages : {
										required : "<s:text name="Error.gst.amt" />",
									}
								});
		});	
	</script>
</html>	
