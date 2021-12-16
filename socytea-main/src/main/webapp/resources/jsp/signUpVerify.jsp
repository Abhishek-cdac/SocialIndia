<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<sj:head locale="en" jqueryui="true" jquerytheme="pagination" customBasepath="resources" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name='Text.project.title' /></title>

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">



<title>Letspay</title>

</head>

<body>
	<div class="top-content">

		<div class="inner-bg" style="background-color: #04285c;">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-4 text">
						<h1>
							<strong><s:text name='Text.project.title' /></strong> Activation Form
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
					<jsp:include page="common/Alert.jsp" flush="true" />
						<div class="form-top">
							<div class="form-top-left">
								<h3>Activation Key Form</h3>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" id="posthome" method="post" action="posthome">
								<div class="form-group">
									<label><s:text name="Text.customerreg.activation.key" /><span class="mandatory">*</span></label>
									<s:password name="activationKey" id="userId"
										cssClass="form-control loginidvalidate" value=""
										placeholder="Activation Key"></s:password>
								</div>
								<button type="submit" class="btn btn-primary btn-sm"
									id="userLogin" name="submitform" style="width: 49.5%;"
									title="Login"><s:text name="Text.login.signin" /></button>
								<s:a cssClass="btn btn-primary btn-sm" href="loginprocess"
									id="usersignup" name="submitform" style="width: 49.5%;"
									title="Login" ><s:text name="Text.login.signup.cancel" />
									</s:a>
									<s:hidden name="custRegObj.userName" ></s:hidden>
									<%-- <s:hidden name="custRegObj.lastName" ></s:hidden>
									<s:hidden name="custRegObj.firstName" ></s:hidden> 
									<s:hidden name="date" ></s:hidden>--%>
									<s:hidden name="custRegObj.emailId" ></s:hidden>
									<s:hidden name="custRegObj.groupCode" ></s:hidden>
									<%-- <s:hidden name="custRegObj.gender" ></s:hidden> --%>
									<s:hidden name="custRegObj.mobileNo" ></s:hidden>
									<s:hidden name="custRegObj.idCardType" ></s:hidden>
									<s:hidden name="custRegObj.idProofNo" ></s:hidden>
									<s:hidden name="custRegObj.groupUniqId" ></s:hidden>
									<%-- <s:hidden name="custRegObj.bloodType" ></s:hidden>
									<s:hidden name="custRegObj.address1" ></s:hidden> --%>
									</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- <div class="clear" style="height: 10px;"></div> -->


	<!-- CSS -->
	<link rel="stylesheet" type="text/css"
		href="<s:text name='Resource.path'/>/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css"
		href="<s:text name='Resource.path'/>/css/bootstrap/styles-core.css">
	<%-- <link rel="stylesheet" type="text/css" href="<s:text name='Resource.path'/>/css/jquery.validity.css" /> --%>
	<link rel="stylesheet" type="text/css"
		href="<s:text name='Resource.path'/>/css/jquery.alerts.css" />
	<link rel="stylesheet" type="text/css"
		href="<s:text name='Resource.path'/>/ccc/dist/css/bootstrapValidator.css">
	<link rel="stylesheet" type="text/css"
		href="<s:text name='Resource.path'/>/css/bootstrap/style.css">

	<!-- Javascript -->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/formValidation.min.js"></script>
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script>

	<style>
.inner-bg {
	padding: 2px 0 20px;
}
</style>

	<%@include file="common/footer.jsp"%>

	
</body>
</html>