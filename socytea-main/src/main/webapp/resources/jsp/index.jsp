<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.Locale" import="java.util.Date" import="java.util.TimeZone" 
import="java.text.SimpleDateFormat" 
import="java.util.Map" import="com.opensymphony.xwork2.ActionSupport" 
import="com.opensymphony.xwork2.ActionContext" 
import="com.letspay.utils.LogOut" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<% 
String cookName = "custloginid";
String passName = "password";
String lgnid = "";
String pass = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		if (cookies[i].getName().equals(cookName)) {
			lgnid = cookies[i].getValue();
			break;
		}}
}
if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		if (cookies[i].getName().equals(passName)) {
			pass = cookies[i].getValue();
			break;
		}
	}
}

Map sessionMap = ActionContext.getContext().getSession();
String IPACCESS_STATUS = String.valueOf(sessionMap.get("IPACCESS_STATUSFLG"));
String ip = "";
String clintHost ="";
int clientIP =0; 
String localdt = "";
Locale locale = null;	
String protocol = "";
String method = "";
String countryName = "";
String language = "";
if(IPACCESS_STATUS!=null && !IPACCESS_STATUS.equalsIgnoreCase("0")){
	 sessionMap.put("IPACCESS_STATUSFLG","0");
	 ip = request.getHeader("X-Forwarded-For");
	 clintHost = request.getServerName();
	 clientIP = request.getServerPort();
	 localdt = request.getParameter("dd");
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
	 locale = request.getLocale();	
	 protocol = request.getProtocol();
	 method = request.getMethod();
	 countryName = locale.getCountry();
	 language = locale.getLanguage();
    //AccessInfo access=new AccessInfo();
	
	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//Date date1 = new Date();
	//dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	// String entryTime= dateFormat.format(new Date());
	// String accessinfo=access.accesscontrolip(ip,clintHost,clientIP,language,protocol,method,countryName,serverTime,gmtTime,entryTime);	
}else{
	System.out.println("---- statusCode -----refersh--------"+IPACCESS_STATUS);	
}
String lvrSeeeionrights = String.valueOf(sessionMap.get("RIGHTSLST"));
String lvrSessoinfind = "NEW SESSION";
if(lvrSeeeionrights!=null && !lvrSeeeionrights.equalsIgnoreCase("null") && !lvrSeeeionrights.equalsIgnoreCase("")){
	lvrSessoinfind ="EXISTING SESSION";
}else{
	lvrSessoinfind ="NEW SESSION";
}
System.out.println("---- statusCode -----refersh--------"+IPACCESS_STATUS);	
%>




<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible" content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight" content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="common/basiccss.jsp"></jsp:include>
<title>Login Page | Socytea</title>
<script>
window.history.forward();
function noBack() { window.history.forward(); }
var lvexsession='<%=lvrSessoinfind%>';
if(lvexsession=="EXISTING SESSION"){
	window.location.href="Homeform";
}
</script>
  <!-- Favicons-->
  <link rel="icon" href="<s:text name='Resource.path'/>/images/social/48.ico" sizes="32x32">
  <!-- Favicons-->
  <link rel="apple-touch-icon-precomposed" href="<s:text name='Resource.path'/>/images/social/48.ico">
  <!-- For iPhone -->
  <meta name="msapplication-TileColor" content="#00bcd4">
  <meta name="msapplication-TileImage" content="<s:text name='Resource.path'/>/images/social/48.ico">
  <!-- For Windows Phone -->
<style type="text/css">
.societyerror{ font-size: 1rem; color: #ff4081;left: 0rem;top: -1rem; position: relative;transform: translateY(0%);} </style>
  <!-- CORE CSS-->
  
  <link href="<s:text name='Resource.path'/>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/css/style.css?d33fgd" type="text/css" rel="stylesheet" media="screen,projection">
    <!-- Custome CSS-->    
    <link href="<s:text name='Resource.path'/>/css/custom/custom.css?gfdg" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/css/layouts/page-center.css" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="<s:text name='Resource.path'/>/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  
   <link href="<s:text name='Resource.path'/>/js/plugins/sweetalert/dist/sweetalert.css" type="text/css" rel="stylesheet" media="screen,projection">
   <!-- jQuery Library -->
  <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-1.11.2.min.js"></script>
  <!--animate css-->
  <link href="<s:text name='Resource.path'/>/js/plugins/animate-css/animate.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body class="<s:text name="full.theme.bgolor"/>">
  <!-- Start Page Loading<jsp:include page="common/Alert.jsp" flush="true" /> -->
  <div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
  <!-- End Page Loading -->
 <jsp:include page="common/Alert.jsp" flush="true" />
  <div id="login-page" class="row">
    <div class="col s12 z-depth-4 card-panel">
      <form role="form" id="loginform" class="loginform" method="post" action="postlogin">
      	<s:hidden id="resetLogin" name="resetLogin"/>
        <div class="row ">
          <div class="input-field col s12 center">
            <img src="<s:text name='Resource.path'/>/images/social/social-india-final-logo.png" alt="" class="rounded responsive-img valign profile-image-login">
            <p class="center login-form-text">SOCYTEA</p>
          </div>
        </div>
				<div class="row margin" id="emailmobrowid">
					<div class="input-field col s12">
						<i class="mdi-social-person-outline prefix"></i>
						<s:textfield name="userInfo.userName" id="userId"></s:textfield>
						<label for="userId">Mobile No. / Email <span
							class="mandatory">*</span></label>
					</div>
				</div>
                    
				  <div class="row" id="societyselectbox" style="display: none">
					<div id="input-select">
						<div class="input-field col s12" id="societylistid">
							<label for="societyId" class="" style="margin-left: 45px; color: #26a69a;"><s:text
									name="Text.select.society" /><span class="mandatory">*</span></label> <i
								class="mdi-social-location-city prefix" id="cityid"></i>
							<div id="selectSocietyId"></div>
							<div id="socyteamsg" class="societyerror"></div>
						</div>
					</div>
				</div> 
				     <!--  <div class="clear height25px"></div> -->
				<%-- <div class="row" id="societyselectbox" style="display: none">
					 <div id="input-select"> -->
						<div class="input-field col s12" id="societylistid">
							<label for="selectSocietyId" class="active" style="margin-left: 45px;"><s:text name="Text.select.society" /><span class="mandatory">*</span></label> <i
								class="mdi-social-location-city prefix" id="cityid"></i>
							<div id="selectSocietyId"></div>
							<div id="socyteamsg" class="societyerror"></div>
						</div>
					 </div> 
				</div> --%> 

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-lock-outline prefix"></i>
						<s:password id="passwd" name="userInfo.password"></s:password>
						<label for="passwd">Password<span class="mandatory">*</span></label>
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col s12 m12 l12  login-text">
						<input type="checkbox" name="remember" id="remember-me" class="remember-me" value="yes"/> <label
							for="remember-me">Remember me</label>
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col s12">
						<button type="submit"
							class="btn waves-effect animated infinite waves-light col s12"
							id="userLogin" name="submitform" title="Login">
							<s:text name="Text.login.signin" />
						</button>
						<!-- <a href="index.html" class="btn waves-effect waves-light col s12">Login</a> -->
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<p class="left medium-small">
							<a href="#" onclick="forgotpassword();">Forgot password ?</a>
						</p>
					</div>
				</div>

			</form>
    </div>
  </div>
  <!-- ================================================
    Scripts
    ================================================ -->

  
  <!--materialize js-->
  <script type="text/javascript" src="<s:text name='Resource.path'/>/js/materialize.js"></script>
  <!--prism-->
  <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/prism/prism.js"></script>
  <!--scrollbar-->
  <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>

      <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
    <!-- validation -->
		<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-validation/jquery.validate.min.js"></script>
		    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-validation/additional-methods.min.js"></script>

<!--sweetalert -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sweetalert/dist/sweetalert.min.js"></script>  
    
</body>
<script type="text/javascript">

document.getElementById("resetLogin").value = "false";

if('<%=lgnid%>'!=""){
    $('#userId').val('<%=lgnid%>');
	}

if('<%=pass%>'!=""){
    $('#passwd').val('<%=pass%>');
	}
	if('<%=lgnid%>'!="" && '<%=pass%>'!=""){		
		$(".remember-me").attr('checked',true)
	}

function forgotpassword(){
	swal({   title: "Forgot Password?",   
        type: "input",   showCancelButton: true,   
        closeOnConfirm: false,   
        animation: "slide-from-top",   
        inputPlaceholder: "Enter your email / mobile no." }, 
        function(inputValue){   
            if (inputValue === false) return false; 
            if (inputValue === "") {     
                swal.showInputError("Enter your email / mobile no.");     
                	return false;  
                } 
            $.ajax({
				type : 'post',
				datatype : 'html',
				url : 'forgetPassword',
				data : "actiontype=actiontype&emailrMobile="+inputValue,
				success : function(data) {	
            	if (data.trim()=="0") {     
            	 	swal("Success", "Password has sent successfully" , "success");
               	} else {
                	/*  swal("Information", "Invalid Email id or Mobile No!." , "Information");  */
                	swal("Information!", "Invalid Email id or Mobile No!.", "error");
                } 
				}
            }); 
      
   });
}

function societycheckerror(){
	var aa = $("#societyId option:selected").val();
	if(aa.length>0){
		$("#socyteamsg").hide();
		$("#cityid").addClass("active");
	}else{
		$("#cityid").removeClass("active");
		$("#socyteamsg").html("The society is required");
	}
}
$('#loginform').validate({
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
			var aa = $("#societyId option:selected").val();
			if(aa.length==0){
			/* 	$("#societyerror").show(); */
			$("#socyteamsg").html("The society is required");
				return false;
			}else{
				$("#socyteamsg").hide();
			}
			toShowLoadingImgoverlay();
			return true;
			//toShowLoadingImgoverlay();
		} 
	});
	$("#userId").rules("add", {
		required : true,
		messages : {
			required : "<s:text name="Text.login.mobandemailfield" />",
			//minlength : "Enter at least 5 characters"
		}
	});
	
	$("#passwd").rules("add", {
		required : true,
		messages : {
			required : "The password is required",
			//minlength : "Enter at least 5 characters"
			
		}
	});
	
$(document).ready(function(){
	var locIPACCESS_STATUS ='<%=IPACCESS_STATUS%>';		
	if(locIPACCESS_STATUS!="0"){
		var dt_clint = new Date();       
        var dft = dt_clint.getFullYear()+"-"+(dt_clint.getMonth()+1) +"-"+ dt_clint.getDate()+ " " +dt_clint.getHours()+":"+dt_clint.getMinutes()+":"+dt_clint.getSeconds();               
        //$("#clienttime_hidd").val(dt_clint.getTime());
        //$("#clientdate_hidd").val(dft);                      
        var ipadr ='<%=ip%>';
        var clintHost ='<%=clintHost%>';
        var clientip ='<%=clientIP%>';
        var languagee ='<%=language%>';
        var protocol ='<%=protocol%>';
        var methodname ='<%=method%>';
        var countryName ='<%=countryName%>';
        $.ajax({
			type : 'post',				
			url : '<s:text name='Resource.path'/>/jsp/monitormgmt/iptrackajax.jsp',
			data : "clientdatetime=" + dft +"&ip="+ ipadr+ "&clintHost="+ clintHost+
			       "&clientip="+clientip+"&languagee="+languagee+"&protocol="+protocol+"&methodname="+methodname+"&countryname="+countryName,
			success : function(data) {					
			},error:function(data){
				//alert("ssserr : "+data);
			}
		});
	}

    $("#userId").blur(function(){
    var userId=$("#userId").val();    
	if (userId.length > 0) {
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'loginCheckShowSociety',
					data : "userName=" + userId,
					success : function(data) {
						/* if (data.length == 2) {
							$("#societylistid").hide();
							$("#emailmobrowid").addClass("margin");
						} else {
							$("#societylistid").show();

							$("#emailmobrowid").removeClass("margin");
						} */
						if(data.trim().length>0){
							$("#selectSocietyId").html(data);
						$('select').material_select();
						$("#societyselectbox").show();
						$("#societyId").rules("add", {
							required : true
						});
						}

					},
					error : function() {
						$("#selectSocietyId").html("");
						$("#societyselectbox").hide();
						$("#emailmobrowid").addClass("margin");
					}
				});
			
	
	} else {
		$("#selectSocietyId").html("");
		$("#societyselectbox").hide();
		$("#emailmobrowid").addClass("margin");
	}
		});

	});

</script>
</html>