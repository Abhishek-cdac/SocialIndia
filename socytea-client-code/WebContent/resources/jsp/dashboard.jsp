<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.Locale" import="java.util.Map" import="com.opensymphony.xwork2.ActionSupport" import="com.opensymphony.xwork2.ActionContext" import="com.socialindia.accessInfo.AccessInfo" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<%
String ip = request.getHeader("X-Forwarded-For");

if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip = request.getHeader("Proxy-Client-IP");
}
if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip = request.getHeader("WL-Proxy-Client-IP");
}
if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip = request.getHeader("HTTP_CLIENT_IP");
}
if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
}
if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    ip = request.getRemoteAddr();
}
Map sessionMap = ActionContext.getContext().getSession();
String userid=String.valueOf(sessionMap.get("USERID"));
AccessInfo access=new AccessInfo();
String aa=access.accessUniqueId(ip,userid);
%>
<!--================================================================================
	Item Name: Materialize - Material Design Admin Template
	Version: 3.1
	Author: GeeksLabs
	Author URL: http://www.themeforest.net/user/geekslabs
================================================================================ -->
 <head>
<meta http-equiv="Content-Type" content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible" content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight" content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<title><s:text name="Text.Title" /></title>

  <!-- CORE CSS-->
  <link href="<s:text name='Resource.path'/>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->
  <link href="<s:text name='Resource.path'/>/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <%-- <link href="<s:text name='Resource.path'/>/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection"> --%>
  <link href="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/jvectormap/jquery-jvectormap.css" type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="common/icodisplyfile.jsp"></jsp:include>
</head>
<body onload="noBack()">
  <!-- Start Page Loading -->
  <div id="loader-wrapper">
      <div id="loader"></div>
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
  <!-- End Page Loading -->
  <!-- //////////////////////////////////////////////////////////////////////////// -->
  <jsp:include page="common/header.jsp"></jsp:include>
  <!-- //////////////////////////////////////////////////////////////////////////// -->
  <!-- START MAIN -->
  <div id="main">
    <!-- START WRAPPER -->
    <div class="wrapper">
     <jsp:include page="common/menuBar.jsp"></jsp:include>
      <!-- //////////////////////////////////////////////////////////////////////////// -->
	  <jsp:include page="common/Alert.jsp" flush="true" />
	  <div id="card-alert" class="card success" style="width: 98%;margin-left: 1%">
                              <div class="card-content white-text" id="dbquery"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                     App Database query file changed.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                   </div>
      <!-- START CONTENT -->
      <section id="content">

        <!--breadcrumbs start-->
        <div id="breadcrumbs-wrapper">
            <!-- Search for small screen -->
            <jsp:include page="common/searchexploremob.jsp"></jsp:include>
        </div>
        <!--breadcrumbs end-->

        <!--start container-->
        <div class="container">
           <section id="content">
                <!--start container-->
                    <!--chart dashboard start-->
                    <div id="chart-dashboard">
                        <div class="row">
                            <div class="col s12">
                                <div class="card">
                                    <div class="card-move-up waves-effect waves-block waves-light">
                                        <div class="move-up cyan darken-1">
                                            <div>
                                                <span class="chart-title white-text">Township</span>
                                               <div class="chart-revenue cyan darken-2 white-text">
                                                    <p class="chart-revenue-total"><div class="orange lighten-1 left" id="orngediv" style="height:10px;width:10px; margin:4px 3px 0 0;"></div> <div id="orngediv1" class="white-text text-darken-4 left "  style="font-size:13px;">Resident</div></p>
                                                    <p class="chart-revenue-per" > <div class="pink lighten-1 left" id="pinkdiv"  style="height:10px;width:10px;margin:4px 3px 0 0;"></div> <div id="pinkdiv1"  class="white-text text-darken-4 left"  style="font-size:13px;" >Society</div></p>
                                                </div>
                                              <!--    <div class="switch chart-revenue-switch right">
                                                    <label class="cyan-text text-lighten-5">
                                                      Month
                                                      <input type="checkbox">
                                                      <span class="lever"></span> Year
                                                    </label>
                                                </div> -->
                                            </div>
                                            <div class="trending-line-chart-wrapper">
                                                <canvas id="trending-line-chart" height="70"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-content">
                                        <a class="btn-floating btn-move-up waves-effect waves-light darken-2 right hoverable tooltipped" data-position="left" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="View Chart Details"><i class="mdi-content-add activator"></i></a>
                                    </div>

                                    <div class="card-reveal">
                                        <span class="card-title grey-text text-darken-4">Township Details <i class="mdi-navigation-close right"></i></span>
                                        <table class="responsive-table" id="grpTblid">
                                            <thead>
                                                <tr>
                                                    <th data-field="id">S.No.</th>
                                                    <th data-field="month">Township Name</th>
                                                    <th data-field="item-sold">No. Of Society</th>
                                                    <th data-field="item-price">No. Of Resident</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>Township1</td>
                                                    <td>200</td>
                                                    <td>100</td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                   <td>Township2</td>
                                                    <td>500</td>
                                                    <td>100</td>
                                                </tr>
                                                <tr>
                                                    <td>3</td>
                                                    <td>Township3</td>
                                                    <td>100</td>
                                                    <td>50</td>
                                                </tr>
                                                <tr>
                                                    <td>4</td>
                                                     <td>Township4</td>
                                                    <td>1000</td>
                                                    <td>800</td>
                                                </tr>
                                                <tr>
                                                    <td>5</td>
                                                    <td>Township5</td>
                                                    <td>2000</td>
                                                    <td>1400</td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>


                                </div>
                            </div>


                        </div>
                    </div>
                    <!--chart dashboard end-->

                    <!-- //////////////////////////////////////////////////////////////////////////// -->

                    <!--card stats start-->
                    <div id="card-stats">
                        <div class="row">
                            <div class="col s12 m6 l3 hoverable">
                                <div class="card">
                                    <div class="card-content  green white-text">
                                        <p class="card-stats-title"><i class="mdi-file-file-download "></i> No. Of Apps Download</p>
                                        <h4 class="card-stats-number" id="noappDownload">0</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i>  <span class="green-text text-lighten-5"></span>
                                        </p>
                                    </div>
                                    <div class="card-action  green darken-2">
                                      <!--   <div id="clients-bar" class="center-align"></div> -->
                                    </div>
                                </div>
                            </div>

                            <div class="col s12 m6 l3 hoverable">
                                <div class="card">
                                    <a href="signonfailurereport" data-position="top" data-delay="10" data-tooltip="Click to View Failed Users" class="tooltipped"><div class="card-content  red white-text">
                                        <p class="card-stats-title"><i class="mdi-social-person "></i>Failed Users</p>
                                        <h4 class="card-stats-number" id="failedlogon"></h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> <span class="blue-grey-text text-lighten-5"></span>
                                        </p>
                                    </div></a>
                                    <div class="card-action  red darken-2">
                                      <!--   <div id="profit-tristate" class="center-align"></div> -->
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3 hoverable">
                                <div class="card">
                                    <a href="usermgmt" data-position="top" data-delay="10" data-tooltip="Click to View Reisdent" class="tooltipped"><div class="card-content  green white-text">
                                        <p class="card-stats-title"><i class="mdi-social-person "></i> Active Members</p>
                                        <h4 class="card-stats-number" id="activeuser"></h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> <span class="blue-grey-text text-lighten-5"></span>
                                        </p>
                                    </div></a>
                                    <div class="card-action  green darken-2">
                                      <!--   <div id="profit-tristate" class="center-align"></div> -->
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3 hoverable">
                                <div class="card">
                                   <a href="complaintsmgmt" data-position="top" data-delay="10" data-tooltip="Click to View Complaints" class="tooltipped"> <div class="card-content purple white-text">
                                        <p class="card-stats-title"><i class="mdi-content-content-paste "></i>User Compliants</p>
                                        <h4 class="card-stats-number" id="usercomplaints"></h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i>  <span class="purple-text text-lighten-5"></span>
                                        </p>
                                    </div></a>
                                    <div class="card-action purple darken-2">
                                      <!--   <div id="sales-compositebar" class="center-align"></div> -->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col s12 m6 l3 hoverable">
                                <div class="card">
                                    <div class="card-content  cyan white-text">
                                        <p class="card-stats-title"><i class="mdi-navigation-apps "></i> App Usage</p>
                                        <h4 class="card-stats-number" id="appuse">0</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i>  <span class="green-text text-lighten-5"></span>
                                        </p>
                                    </div>
                                    <div class="card-action  cyan darken-2">
                                       <!--  <div id="project-line-1" class="center-align"></div> -->
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3 hoverable">
                                <div class="card">
                                    <div class="card-content indigo lighten-1 white-text">
                                        <p class="card-stats-title"><i class="mdi-editor-insert-drive-file"></i> Status Health</p>
                                        <h4 class="card-stats-number" id="statusHealth">0</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-down"></i>  <span class="deep-purple-text text-lighten-5"></span>
                                        </p>
                                    </div>
                                    <div class="card-action  indigo darken-2">
                                       <!--  <div id="project-line-2" class="center-align"></div> -->
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3 hoverable">
                                <a href="labourmgmt" data-position="top" data-delay="10" data-tooltip="Click to View Labor's" class="tooltipped"><div class="card">
                                    <div class="card-content teal white-text">
                                        <p class="card-stats-title"><i class="mdi-social-group "></i>No. Of Labor's Sign Up</p>
                                        <h4 class="card-stats-number" id="lbrCount"></h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i>  <span class="blue-grey-text text-lighten-5"></span>
                                        </p>
                                    </div>
                                 <!--    <div class="card-action teal darken-2">
										<div id="project-line-2" c
										lass="center-align">
											<canvas style="display: inline-block; width: 280px; height: 30px; vertical-align: top;" width="280" height="30"></canvas>
												</div>
										</div> -->
                                    <div class="card-action teal darken-2">
                                        <div id="sales-bar-2" class="center-align"></div>
                                    </div>
                                </div></a>
                            </div>
                            <div class="col s12 m6 l3 hoverable">
                                <a href="merchantMgmtTbl" data-position="top" data-delay="10" data-tooltip="Click to View Merchant's" class="tooltipped"> <div class="card">
                                    <div class="card-content  green white-text">
                                        <p class="card-stats-title"><i class="mdi-social-person"></i>No. Of Merchant Sign Up</p>
                                        <h4 class="card-stats-number" id="mctcount"></h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i>  <span class="purple-text text-lighten-5"></span>
                                        </p>
                                    </div>
                                    <div class="card-action  green darken-2">
                                       <!--  <div id="project-line-4" class="center-align"></div> -->
                                    </div></a>
                                </div>
                            </div>
                            <!--  <div class="row"> -->
                             <div class="col s12 m6 l3 hoverable">
                             <div data-position="top" data-delay="10" data-tooltip="Cyberplat Wallet Balance" class="tooltipped"> 
                                <div class="card">
                                    <div class="card-content purple white-text">
                                        <p class="card-stats-title"><i class="mdi-action-account-balance tinysmall"></i>Cyberplat Wallet Balance</p>
                                        <h4 class="card-stats-number" id="cyberplate"></h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i>  <span class="purple-text text-lighten-5"></span>
                                        </p>
                                    </div>
                                    <div class="card-action purple darken-2">
                                       <!--  <div id="project-line-4" class="center-align"></div> -->
                                    </div>
                                </div></div></div>
                                <div class="col s12 m6 l3 hoverable">
                                  <div data-position="top" data-delay="10" data-tooltip="App Database File" class="tooltipped" data-tooltip-id="c5e50eb3-5a1c-90c1-2b6f-a7e47a91d95d"> 
                                <div class="card pointer" id="appdpqueries">
                                    <div class="card-content purple white-text">
                                        <p class="card-stats-title">To Create App Database File</p>
                                        <h4 class="card-stats-number" id=""></h4>
                                       <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i>  <span class="purple-text text-lighten-5"></span>
                                        </p>
                                      <div class="clear height5px"></div>
                                       
                                    </div>
                                    <div class="card-action purple darken-2">
                                       <!--  <div id="project-line-4" class="center-align"></div> -->
                                    </div>
                                </div></div></div>
                                
                            <!-- </div> -->
                        </div>
                        <!--work collections start-->
                    <div id="work-collections">
                        <div class="row">
                            <div class="col s12 m6 l6">
                                
                                <ul id="projects-collection" class="collection">
                                    <li class="collection-item avatar">
                                        <i class="mdi-file-folder circle light-blue darken-2"></i>
                                        <span class="collection-header">Socytea</span>
                                        <p>Details Count</p>
                                        <a href="#" class="secondary-content"><i class="mdi-action-grade"></i></a>
                                    </li>
                                    <li class="collection-item pointer" id="dbrdtownshiplistid">
                                        <div class="row">
                                            <div class="col s6">
                                              <p class="collections-title">Township</p>

                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat cyan" id="townshipcount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-1"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer" id="dbrdsocytealistid">
                                        <div class="row">
                                            <div class="col s6">
                                                 <p class="collections-title">Society</p>
                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat grey darken-3" id="societycount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-2"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer" id="dbrdcommteelistid">
                                        <div class="row">
                                            <div class="col s6">
                                                <p class="collections-title">Committee</p>

                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat teal" id="committeecount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-3"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer" id="dbrdresidntlistid">
                                        <div class="row">
                                            <div class="col s6">
                                                 <p class="collections-title">Resident</p>

                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat teal" id="residentcount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-4"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer" id="dbrdcmpylistid">
                                        <div class="row">
                                            <div class="col s6">
                                                 <p class="collections-title">Company</p>
                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat teal" id="companycount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-5"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer" id="dbrdstafflistid">
                                        <div class="row">
                                            <div class="col s6">
                                                 <p class="collections-title">Staff</p>

                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat teal" id="staffcount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-6"></div>
                                            </div>
                                        </div>
                                    </li>
                                     <li class="collection-item pointer" id="dbrdlaborlistid">
                                        <div class="row">
                                            <div class="col s6">
                                                 <p class="collections-title">Labor</p>

                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat teal" id="laborcount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-7"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer" id="dbrdmerchantlistid">
                                        <div class="row">
                                            <div class="col s6">
                                                 <p class="collections-title">Merchant</p>

                                            </div>
                                            <div class="col s3">
                                                <span class="task-cat teal" id="merchantcount"></span>
                                            </div>
                                            <div class="col s3">
                                                <div id="project-line-8"></div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="col s12 m12 l6">
                                 
                                
                                <ul id="issues-collection" class="collection">
                                    <li class="collection-item avatar">
                                        <i class="mdi-action-receipt  circle green darken-2"></i>
                                        <span class="collection-header">Reports</span>
                                       <!--  <p>Assigned to you</p> -->
                                        <a href="#" class="secondary-content"><i class="mdi-action-grade"></i></a>
                                    </li>
                                    <li class="collection-item pointer " id="dbrdmerchantreplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title"> Merchant</p>
                                                <p class="collections-content">Merchant Report</p>
                                            </div>
                                            <%-- <div class="col s2">
                                                <span class="task-cat pink accent-2">P1</span>
                                            </div> --%>
                                            <div class="col s3">
                                                <div class="progress">
                                                     <div class="determinate" style="width: 70%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer"  id="dbrdstaffreplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title">Staff</p>
                                                <p class="collections-content">Staff Report </p>
                                            </div>

                                            <div class="col s3">
                                                <div class="progress">
                                                    <div class="determinate" style="width: 40%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer"  id="dbrdresidntreplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title">Resident </p>
                                                <p class="collections-content">Resident Report </p>
                                            </div>

                                            <div class="col s3">
                                                <div class="progress">
                                                    <div class="determinate" style="width: 95%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer"  id="dbrdlaborreplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title">Labor</p>
                                                <p class="collections-content">Labor Report</p>
                                            </div>

                                            <div class="col s3">
                                                <div class="progress">
                                                     <div class="determinate" style="width: 10%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer"  id="dbrdsocyteareplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title">Society</p>
                                                <p class="collections-content">Society Report</p>
                                            </div>

                                            <div class="col s3">
                                                <div class="progress">
                                                     <div class="determinate" style="width: 10%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer"  id="dbrdtownshipreplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title">Township </p>
                                                <p class="collections-content">Township Report</p>
                                            </div>

                                            <div class="col s3">
                                                <div class="progress">
                                                     <div class="determinate" style="width: 10%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer"  id="dbrdauditreplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title">Audit Trail </p>
                                                <p class="collections-content">Audit Trail Report</p>
                                            </div>

                                            <div class="col s3">
                                                <div class="progress">
                                                     <div class="determinate" style="width: 10%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="collection-item pointer"  id="dbrdcomitteereplistid">
                                        <div class="row">
                                            <div class="col s7">
                                                <p class="collections-title">Committee </p>
                                                <p class="collections-content">Committee Report</p>
                                            </div>

                                            <div class="col s3">
                                                <div class="progress">
                                                     <div class="determinate" style="width: 10%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--work collections end-->
                        </section>
                    </div>
                <!--end container-->
            </section>
          <!-- Floating Action Button -->
            <!-- <div class="fixed-action-btn" style="bottom: 50px; right: 19px;">
                <a class="btn-floating btn-large">
                  <i class="mdi-action-stars"></i>
                </a>
                <ul>
                  <li><a href="css-helpers.html" class="btn-floating red"><i class="large mdi-communication-live-help"></i></a></li>
                  <li><a href="app-widget.html" class="btn-floating yellow darken-1"><i class="large mdi-device-now-widgets"></i></a></li>
                  <li><a href="app-calendar.html" class="btn-floating green"><i class="large mdi-editor-insert-invitation"></i></a></li>
                  <li><a href="app-email.html" class="btn-floating blue"><i class="large mdi-communication-email"></i></a></li>
                </ul>
            </div> -->
            <!-- Floating Action Button -->

        <!--end container-->

      <!-- END CONTENT -->
    </div>
    <!-- END WRAPPER -->
  </div>
  <!-- END MAIN -->
  <div id="chartValuesdiv" style="display:none;"></div>



  <!-- //////////////////////////////////////////////////////////////////////////// -->
   <!-- START FOOTER -->
  <footer class="page-footer">

        <div class="container">
            <div class="row section">
                <div class="col l6 s12">
                    <h5 class="white-text">Mumbai Market</h5>
                    <p class="grey-text text-lighten-4">India map</p>
                    <div id="world-map-markers"></div>
                </div>
                <div class="col l4 offset-l2 s12">
                    <h5 class="white-text">Sales by India</h5>
                    <p class="grey-text text-lighten-4"> polar chart to show sales by india.</p>
                    <div id="polar-chart-holder">
                        <canvas id="polar-chart-country" width="200"></canvas>
                    </div>
                </div>
            </div>
        </div>

         <s:if test="((#session.sSoctyId!=null && #session.sSoctyId !='' && #session.sSoctyId!=null && #session.sSoctyId!='-1') && (#session.sSoctyColorcode!=null && #session.sSoctyColorcode!='' && #session.sSoctyColorcode!='null'))">
			 <div class="footer-copyright" style="background-color:<s:property value="#session.sSoctyColorcode"/>">
		</s:if>
		<s:elseif test="((#session.townshipId!=null && #session.townshipId!='null' && #session.townshipId!='-1' && #session.townshipId!=-1) && (#session.townshipcolourcode!=null && #session.townshipcolourcode!=''))">
			 <div class="footer-copyright" style="background-color:<s:property value="#session.townshipcolourcode"/>">
		</s:elseif>
		<s:else>
	  		<div class="footer-copyright <s:text name="full.theme.bgolor"/>">
		</s:else>
            <div class="container">
      <%--   <span>Copyright © 2016 <a class="grey-text text-lighten-4" href="#" >Social India</a> All rights reserved.</span>
        <span class="right"> Design and Developed by <a class="grey-text text-lighten-4" href="#">Peninlog Technologies</a></span> --%>
         <span> <a href="#modal3" class="modal-trigger grey-text text-lighten-4">Terms of Use</a>&nbsp;&nbsp; | &nbsp;&nbsp;</span>
		<span> <a href="#modal2" class="modal-trigger grey-text text-lighten-4">Privacy Policy </a>&nbsp;&nbsp; | &nbsp;&nbsp;</span>
		<span> <a href="#modal4" class="modal-trigger grey-text text-lighten-4"> Copyright &copy; 2016 Social India</a> All rights reserved.</span>
 		<span class="right"> Design and Developed by <a class="grey-text text-lighten-4" href="#">Peninlog Technologies</a></span>
        </div>
    </footer>
    <jsp:include page="common/footercontent.jsp"></jsp:include>
    <script type="text/javascript">
	$( document ).ready(function() {
		 $(".collapsible.collapsible-accordion> li").removeClass("active");
    	 $(".collapsible.collapsible-accordion> li> a").removeClass("active");
    	 $(".collapsible.collapsible-accordion> li").removeClass("activeMenu");
    	 $(".collapsible-body> ul> li").removeClass("active");
    	 $(".collapsible-body").hide();
    	  $("#menu001").addClass("active");
    	  $("#menu002").removeClass("active");

	 	$.ajax({
	 		type : 'post',
	 		datatype : 'html',
	 		url : 'dashbordSelect',
	 		data : 'servicecode=SI24000' ,
	 		success : function(data) {
	 			var str=data.split('!_!');
	 			$("#activeuser").html(str[0]);
	 			$("#usercomplaints").html(str[1]);
	 			$("#lbrCount").html(str[2]);
	 			$("#mctcount").html(str[3]);
	 			$("#noappDownload").html(str[4]);
	 			$("#failedlogon").html(str[5]);
	 			$("#appuse").html(str[6]);
	 			$("#statusHealth").html(str[7] + " MB");
	 		},
	 	});
	 	var cyberplatesd='<s:property value="#session.cyberplatesd"/>';
	 	var cyberplatap='<s:property value="#session.cyberplatap"/>';
	 	var cyberplatop='<s:property value="#session.cyberplatop"/>';
	/*  var url22='<s:text name="url.cyberplat.wallet"/>?SD='+cyberplatesd+'&AP='+cyberplatap+'&OP='+cyberplatop; */
	 var url='<s:text name="url.cyberplat.wallet"/>';
	 if(cyberplatesd!='null' && cyberplatap!=null && cyberplatop!=null){		 
		 $.ajax({
		 		//datatype : 'text',
		 		 dateType: "json",
		 		type : 'get',
		 		data : 'sdval='+cyberplatesd+'&apval='+cyberplatap+'&opval='+cyberplatop+'&urldata='+url,
		 		async: false,
		 		url : 'cyberplatedata',
		 		success : function(data) {
		 		$("#cyberplate").html(data.trim());
		 		},error: function(data) {
		 		}
		 	});
	 }else{
		 $("#cyberplate").html("0");
	 }
	 function addDbquery(){
		 var appdpurl='<s:text name="url.appdbquery.wallet"/>';
			 $.ajax({
			 		dateType: "json",
			 		type : 'get',
			 		url : 'appdbquery',
			 		success : function(data) {			 		
			 		$('#dbquery').show();
			 		},error: function(data) {
			 		}
			 	});
	 }
 
	 $("#appdpqueries").click(function(){
		 addDbquery();
		}); 

	 	$("#dbrdtownshiplistid").click(function(){
			window.location.href="townshipmgmt";
		});

	 	$("#dbrdsocytealistid").click(function(){
			window.location.href="societymgmt";
		});

	 	$("#dbrdcommteelistid").click(function(){
			window.location.href="committeemgmt";
		});

	 	$("#dbrdresidntlistid").click(function(){
			window.location.href="residentmgmt";
		});
	 	$("#dbrdcmpylistid").click(function(){
			window.location.href="companymgmt";
		});
	 	$("#dbrdstafflistid").click(function(){
			window.location.href="staffmgmt";
		});
	 	$("#dbrdlaborlistid").click(function(){
			window.location.href="labourmgmt";
		});
	 	$("#dbrdmerchantlistid").click(function(){
			window.location.href="merchantMgmtTbl";
		});



	 	$("#dbrdtownshipreplistid").click(function(){
			window.location.href="townshipReportTbl";
		});

	 	$("#dbrdsocyteareplistid").click(function(){
			window.location.href="societyReportTbl";
		});

	 	$("#dbrdcomitteereplistid").click(function(){
			window.location.href="committeeReportTbl";
		});

	 	$("#dbrdresidntreplistid").click(function(){
			window.location.href="residentReportTbl";
		});
	 	$("#dbrdauditreplistid").click(function(){
			window.location.href="auditTrialReportTbl";
		});
	 	$("#dbrdstaffreplistid").click(function(){
			window.location.href="stafReportTbl";
		});
	 	$("#dbrdlaborreplistid").click(function(){
			window.location.href="labourReportTbl";
		});
	 	$("#dbrdmerchantreplistid").click(function(){
			window.location.href="merchantReportTbl";
		});
	 });
	</script>
    <!-- END FOOTER -->

    <!--materialize js-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/materialize.js"></script>
    <!--scrollbar-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>

    <!-- chartist -->
 	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.js"></script>


    <!-- chartjs -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartjs/chart.min.js"></script>

      <script>
    $(document).ready(function(){
     	$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'getdashboardChartData',
			success : function(data) {
				$("#chartValuesdiv").html(data);
				var linedata;
				var ivrFlgtwon=$("#ivrFlgtwon").val();
				var grpTblidvr=$("#ivrGrpTbl").val();
				if(grpTblidvr!="No Data Found"){
					$("#grpTblid").html(grpTblidvr);

				}else{

				}
				if(ivrFlgtwon=="TOWN"){
					$("#chart-dashboard").show();
					$("#pinkdiv").show();
					$("#pinkdiv1").show();
					var docty=$("#townshipDetail").val();
			    	var docval=$("#societyDetail").val();
			    	var docval1=$("#flatDetail").val();
			    	var societyDetail=docty.split("!_!");
			    	var data=docval.split("!_!");
			    	var data1=docval1.split("!_!");
			    	var trendingLineChart = document.getElementById("trending-line-chart").getContext("2d");
				    linedata = {
					labels : societyDetail,
					datasets : [
					{
						label: "First dataset",
						fillColor : "rgba(128, 222, 234, 0.6)",
						strokeColor : "#ffffff",
						pointColor : "#ff4081",
						pointStrokeColor : "#ffffff",
						pointHighlightFill : "#ffffff",
						pointHighlightStroke : "#ffffff",
						data: data
					},
					{
						label: "Second dataset",
						fillColor : "rgba(128, 222, 234, 0.3)",
						strokeColor : "#80deea",
						pointColor : "#ff9800",
						pointStrokeColor : "#80deea",
						pointHighlightFill : "#80deea",
						pointHighlightStroke : "#80deea",
						data: data1
					}
					]
				  };
			    } else if(ivrFlgtwon=="SOCY"){
			    	 $("#chart-dashboard").show();
				    $("#pinkdiv").hide();
				    $("#pinkdiv1").hide();
			    	var docty=$("#townshipDetail").val();
			    	var docval=$("#societyDetail").val();
			    	var societyDetail=docty.split("!_!");
			    	var data=docval.split("!_!");
			    	var trendingLineChart = document.getElementById("trending-line-chart").getContext("2d");
				    linedata = {
				    labels : societyDetail,
				    datasets : [
					{
						label: "First dataset",
						fillColor : "rgba(128, 222, 234, 0.6)",
						strokeColor : "#ffffff",
						pointColor : "#ff9800",
						pointStrokeColor : "#ffffff",
						pointHighlightFill : "#ffffff",
						pointHighlightStroke : "#ffffff",
						data: data
					}
				]
				};
			}else{
				 $("#chart-dashboard").hide();
				}
		window.trendingLineChart = new Chart(trendingLineChart).Line(linedata, {
			scaleShowGridLines : true,///Boolean - Whether grid lines are shown across the chart
			scaleGridLineColor : "rgba(255,255,255,0.4)",//String - Colour of the grid lines
			scaleGridLineWidth : 1,//Number - Width of the grid lines
			scaleShowHorizontalLines: true,//Boolean - Whether to show horizontal lines (except X axis)
			scaleShowVerticalLines: false,//Boolean - Whether to show vertical lines (except Y axis)
			bezierCurve : true,//Boolean - Whether the line is curved between points
			bezierCurveTension : 0.4,//Number - Tension of the bezier curve between points
			pointDot : true,//Boolean - Whether to show a dot for each point
			pointDotRadius : 5,//Number - Radius of each point dot in pixels
			pointDotStrokeWidth : 2,//Number - Pixel width of point dot stroke
			pointHitDetectionRadius : 20,//Number - amount extra to add to the radius to cater for hit detection outside the drawn point
			datasetStroke : true,//Boolean - Whether to show a stroke for datasets
			datasetStrokeWidth : 3,//Number - Pixel width of dataset stroke
			datasetFill : true,//Boolean - Whether to fill the dataset with a colour
			animationSteps: 15,// Number - Number of animation steps
			animationEasing: "easeOutQuart",// String - Animation easing effect
			tooltipTitleFontFamily: "'Roboto','Helvetica Neue', 'Helvetica', 'Arial', sans-serif",// String - Tooltip title font declaration for the scale label
			scaleFontSize: 12,// Number - Scale label font size in pixels
			scaleFontStyle: "bold",// String - Scale label font weight style
			scaleFontColor: "#fff",// String - Scale label font colour
			tooltipEvents: ["mousemove", "touchstart", "touchmove"],// Array - Array of string names to attach tooltip events
			tooltipFillColor: "rgba(255,255,255,0.8)",// String - Tooltip background colour
			tooltipTitleFontFamily: "'Roboto','Helvetica Neue', 'Helvetica', 'Arial', sans-serif",// String - Tooltip title font declaration for the scale label
			tooltipFontSize: 12,// Number - Tooltip label font size in pixels
			tooltipFontColor: "#000",// String - Tooltip label font colour
			tooltipTitleFontFamily: "'Roboto','Helvetica Neue', 'Helvetica', 'Arial', sans-serif",// String - Tooltip title font declaration for the scale label
			tooltipTitleFontSize: 14,// Number - Tooltip title font size in pixels
			tooltipTitleFontStyle: "bold",// String - Tooltip title font weight style
			tooltipTitleFontColor: "#009688",// String - Tooltip title font colour
			tooltipYPadding: 8,// Number - pixel width of padding around tooltip text
			tooltipXPadding: 16,// Number - pixel width of padding around tooltip text
			tooltipCaretSize: 10,// Number - Size of the caret on the tooltip
			tooltipCornerRadius: 6,// Number - Pixel radius of the tooltip border
			tooltipXOffset: 10,// Number - Pixel offset from point x to tooltip edge
			responsive: true
			});
		 //trendingLineChart.update();
			}
		});

    });
    </script>
    <script type="text/javascript">
    window.history.forward();
    function noBack() {window.history.forward(); }
    $(document).ready(function() {
    	initSessionMonitor();

    		 $.ajax({
    		type : 'post',
    		datatype : 'json',
    		url : 'getcountlistall',
    		data : '',
    		success : function(data) {
    			var ar =data.split("!_!");
	 			$("#townshipcount").html(ar[1]);
	 			$("#societycount").html(ar[4]);
	 			$("#residentcount").html(ar[3]);
	 			$("#committeecount").html(ar[2]);
				$("#companycount").html(ar[5]);
				$("#staffcount").html(ar[6]);
	 			$("#laborcount").html(ar[7]);
				$("#merchantcount").html(ar[8]);
    		}
    	});


    });
</script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartjs/chart-script.js?rrdfdf"></script>
     <!-- sparkline -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sparkline/sparkline-script.js"></script>


    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>

      <!-- google map api -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAAZnaZBXLqNBRXjd-82km_NO7GUItyKek"></script>
    <!--google map-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/google-map/google-map-script.js"></script>


    <!--jvectormap-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jvectormap/vectormap-script.js"></script>


</body>

</html>