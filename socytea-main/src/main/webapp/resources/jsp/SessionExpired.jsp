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
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="common/basiccss.jsp"></jsp:include>
<link media="screen,projection" rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/css/layouts/page-center.css">
</head>
<body class="cyan">
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<div id="error-page">

    <div class="row">
      <div class="col s12">
        <div class="browser-window">
          <div class="top-bar">
            <div class="circles">
              <div id="close-circle" class="circle"></div>
              <div id="minimize-circle" class="circle"></div>
              <div id="maximize-circle" class="circle"></div>
            </div>
          </div>
          <div class="content">
            <div class="row">
              <div id="site-layout-example-top" class="col s12">
                <p class="flat-text-logo center white-text caption-uppercase">Please Login Again</p>
              </div>
              <div id="site-layout-example-right" class="col s12 m12 l12">
              <ul>
<br>
<li>
For security reasons, we have disabled Back, Forward and Refresh tabs of the browser.<br> Also, the session will expire automatically, if the browser window is idle for a long time.
</li>
<li>
If the problem persists, please try again after clearing the Temporary Files from your web browser.
</li>
<li style="text-align: center;" >
<a  href="loginprocess" class="pink-text"><u>Click here to go to Login Page</u> </a>
</li>
</ul>
                <div class="row center">
                  <h1 class="text-long-shadow col s12"></h1>
                 
                </div>
                <div class="row center">
               
                  <p class="center s12"><button onclick="goBack()" class="btn waves-effect waves-light">Back</button> <a href="index.html" class="btn waves-effect waves-light">Homepage</a>
                    <p>
                    </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>



	<!-- ================================================
    Scripts
    ================================================ -->
	<!-- jQuery Library -->
	 <script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/jquery-1.11.2.min.js"></script> 
	<!--materialize js-->
	<!--angularjs-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/materialize.js"></script>
	<!--prism-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/prism/prism.js"></script>
	<!--scrollbar-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- data-tables -->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/data-tables/data-tables-script.js"></script>
	<!-- chartist -->
<%-- 	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.js"></script> --%>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->

</body>
<script type="text/javascript">
    function goBack() {
      window.history.back();
    }
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
</html>