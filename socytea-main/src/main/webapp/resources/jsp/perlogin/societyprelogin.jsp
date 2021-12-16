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
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->

 <!-- CORE CSS-->
  <link href="<s:text name='Resource.path'/>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/css/style.css?acxx" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="<s:text name='Resource.path'/>/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="<s:text name='Resource.path'/>/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/sweetalert/dist/sweetalert.css" type="text/css" rel="stylesheet" media="screen,projection">	
</head>
<style>
	#token-input-invitefriend {
	width: 100% ! important;	
	}	
	.filled-in[type="checkbox"]:disabled:checked + label::after {
    background-color:#ff4081;
    border-color: #ff4081;
}

.filled-in[type="checkbox"]:disabled:not(:checked) + label::after {
    background-color: #fff;
    border: 2px solid;
}
	</style>
<body>
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
	 <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav class="navbar-color teal">
                <div class="nav-wrapper">
                    <ul class="left">    
                     <li style=" margin-left: 10px;margin-top: 6px;width: 40px;"> <img src="<s:text name='Resource.path'/>/images/social/logo.png" alt="" class="circle responsive-img valign profile-image"></li>                  
                      <li><h1 class="logo-wrapper"><a href="loginform" class="brand-logo darken-1">SOCYTEA</a> <span class="logo-text"></span></h1></li>
                    </ul>
                     
                </div>
            </nav>
        </div>
        <!-- end header nav-->
  </header>

	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START MAIN -->
	<div id="">
    <!-- START WRAPPER -->
    <div class="wrapper">
      <!-- //////////////////////////////////////////////////////////////////////////// -->
		
      <!-- START CONTENT -->
      <section id="content">
        
        <!--breadcrumbs start-->
        <div id="breadcrumbs-wrapper">
            <!-- Search for small screen -->
            
         
        </div>              
        <div class="container">
				<div class="card-panel">							
						
					<div style="clear: both; height: 20px; "></div>
            
					
					
					<form>
					<jsp:include page="../common/Alert.jsp" flush="true" />
				
					</form>
                   </div>



					<div style="clear: both; height: 20px; "></div>
				</div>
        <!--end container-->
      </section>
      <!-- END CONTENT -->

      <!-- //////////////////////////////////////////////////////////////////////////// -->
      <!-- START RIGHT SIDEBAR NAV-->
       <!-- START CONTENT -->
           
            <!-- END CONTENT -->
      
      <!-- LEFT RIGHT SIDEBAR NAV-->

    </div>
    <!-- END WRAPPER -->

  </div>
  <!-- END MAIN -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<jsp:include page="../common/clorbox.jsp"></jsp:include>
 <!-- ================================================
    Scripts
    ================================================ -->
    
   
     <!-- jQuery Library -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-1.11.2.min.js"></script>    
    <!--materialize js-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/materialize.js"></script>
    <!--scrollbar-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    

    <!-- chartist -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.js"></script>   

    <!-- chartjs -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartjs/chart.min.js"></script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartjs/chart-script.js"></script>

     <!-- sparkline -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sparkline/sparkline-script.js"></script> 
    <script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/sweetalert/dist/sweetalert.min.js"></script>

    
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script> 
	
	
</body>

</html>