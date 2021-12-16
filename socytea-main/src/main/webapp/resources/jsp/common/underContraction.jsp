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
<title><s:text name="Text.Title" /></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>
<body>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<jsp:include page="../common/menuBar.jsp"></jsp:include>
			<section id="content">
				<div id="breadcrumbs-wrapper">
					<div class="header-search-wrapper grey hide-on-large-only">
						<i class="mdi-action-search active"></i> <input type="text"
							name="Search" class="header-search-input z-depth-2"
							placeholder="Explore Materialize">
					</div>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">
									<!--<s:text name="user.monitoring.iptracking" />-->
									Under Construction
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<!-- <li><a href="ipTrack"><s:text name="user.monitoring.Headmenu.iptracking" /></a></li>
									<li class="active"><s:text name="user.monitoring.iptracking" /></li>  -->
								</ol>
								<div class="right"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="container">
					<jsp:include page="../common/Alert.jsp" flush="true" />
					<div id="jqueryvalidation" class="section">
						<div class="card-panel">
							<div class="row">
								<div class="col s12 m12 l12">
								<img style="" class="left" width="200px" height="200px" alt="Under Construction" src="<s:text name='Resource.path'/>/images/Under-construction.png">
								<div style="font-size:20px; font-weight : 600;float:left;margin-top:80px;"> Progress...</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- END WRAPPER -->
	</div>
	<!-- END MAIN -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 
</body>
</html>
<%--






<body>
	<div class="container-fluid">
		<!-- START Header Container -->
		<jsp:include page="../common/header.jsp"></jsp:include>
		<!-- END Header Container -->

		<!-- START Body Container -->
		<!-- <div id="body-container"> -->

		<!-- START Left Column -->
		<!-- END Left Column -->

		<!-- START Right Column -->
		<div id="right-column">
			<div class="right-column-content">
			<div class="col-md-12">
			<div class="block">
				<div class="block-content-outer">
				<div class="block-content-inner">			
				<div class="col-md-12" style="text-align: center;">
					<img style="" class="left" width="200px" height="200px" alt="Under Construction" src="resources/images/Under-construction.png">
				<div style="font-size:20px; font-weight : 600;float:left;margin-top:80px;"> Progress...</div>
				</div>			 		
			 	</div><div class="clear height5px;"></div>
				</div>
				
			</div>
			</div>
			<div class="col-md-12">
			</div>
			</div>
			</div>
			<!-- START Footer Container -->
			<jsp:include page="../common/footer.jsp"></jsp:include>
			<!-- END Footer Container -->
		</div>
		<!-- END Right Column -->

	<!-- END Body Container -->
	
	
	<!-- OTP Start Colorbox -->
	 <!-- <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" id="otpColorbox"></div>  -->
	<!-- OTP End Colorbox -->

	<!-- /.container -->

	<!-- Placed at the end of the document so the pages load faster -->
<!-- Required CSS Files -->
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.structure.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.theme.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/mCustomScrollbar/jquery.mCustomScrollbar.min.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/icheck/all.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/fonts/metrize-icons/styles-metrize-icons.css" rel="stylesheet">

	<!-- Optional CSS Files -->
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/bootstrapValidator.min.css" rel="stylesheet" />
	<!-- add CSS files here -->

	<!-- More Required CSS Files -->
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/styles-core.css" rel="stylesheet" />
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/styles-core-responsive.css" rel="stylesheet" />

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<s:text name='Resource.path'/>/assets/js/required/misc/ie10-viewport-bug-workaround.js"></script>
	
	<!-- Required JS Files -->	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.min.js"></script> 	
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery.easing.1.3-min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery.mCustomScrollbar.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/misc/jquery.mousewheel-3.0.6.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/misc/retina.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/icheck.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/misc/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/circloid-functions.js"></script>

	<!-- Optional JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/ckeditor/adapters/jquery.js"></script> <!-- This jQuery Adapter is REQUIRED for CKEditor to function properly -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrapValidator.min.js"></script>
	<!-- <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/bootstrapValidator-language/languagecode_COUNTRYCODE.js"></script> -->
	<!-- add optional JS plugin files here -->

	<!-- REQUIRED: User Editable JS Files -->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/script.js"></script>
	<!-- add additional User Editable files here -->

	<!-- Demo JS Files -->

	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/formValidation.min.js" ></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script>
	
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/typeahead/typeahead.css" rel="stylesheet" />
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/typeahead/typeahead.bundle.js"></script>

</body> --%>

</html>
