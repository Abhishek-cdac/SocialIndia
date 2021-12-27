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
<jsp:include page="common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<jsp:include page="common/basiccss.jsp"></jsp:include>
<link
	href="<s:text name='Resource.path'/>/js/plugins/dropify/css/dropify.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
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
	<jsp:include page="common/header.jsp"></jsp:include>
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<jsp:include page="common/menuBar.jsp"></jsp:include>
			<!-- //////////////////////////////////////////////////////////////////////////// -->
			<jsp:include page="common/Alert.jsp" flush="true" />
			<!-- START CONTENT -->
			<section id="content">
				<!--breadcrumbs start-->
				<div id="breadcrumbs-wrapper">
					<!-- Search for small screen -->
					<jsp:include page="common/searchexploremob.jsp"></jsp:include>
					<div class="container">

						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">
									<s:text name="Breadcrumb.changepassword" />
								</h5>
								<ol class="breadcrumbs">
									<li><a href="loginform"><s:text
												name="Breadcrumb.dashboard" /></a></li>
									<li class="active"><s:text
											name="Breadcrumb.changepassword" /></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
					<div class="card-panel">
						<div style="margin-left: 20px;" class="form-check">
										<input type="checkbox" class="form-check-input"
											id="materialUnchecked" onclick="showPwd()"> <label
											class="form-check-label" for="materialUnchecked">Show
											Password</label>
									</div>
						<!-- <form role="form" id=changepwdForm action="changePasswordAction" method="post" > -->
						<form role="form" id=changepwdForm action="socpswdhm"
							method="post">
							<div class="row" style="margin-left: 12px;">
								<div class="input-field col s6">
									<label for="usernameTextId" class=" control-label"><s:text
											name="change.password.user.id" /><span class="mandatory">*</span></label>
									<s:password cssClass="form-control oldpwdvalid"
										name="custRegObj.userName" id="usernameTextId"
										autocomplete="off" value=""></s:password>
									<s:fielderror fieldName="custRegObj.userName" />
								</div>
							</div>
							<div class="row" style="margin-left: 12px;">
								<div class="input-field col s6">
									<label for="passwdTextId" class=" control-label"><s:text
											name="change.password.new.user.id" /><span class="mandatory">*</span></label>
									<s:password cssClass="form-control newpwdvalid"
										name="custRegObj.password" id="passwdTextId"
										autocomplete="off" value=""></s:password>
									<div id="password_strength"></div>
									<s:fielderror fieldName="custRegObj.password" />
								</div>
							</div>

							<div class="row" style="margin-left: 12px;">
								<div class="input-field col s6">
									<label for="currentTextId" class=" control-label"><s:text
											name="change.password.new.retype.id" /><span
										class="mandatory">*</span></label>
									<s:password cssClass="form-control retypepwdvalid"
										name="retypepassword" id="currentTextId" autocomplete="off"
										value=""></s:password>
									<s:fielderror fieldName="custRegObj.userName" />
								</div>
							</div>
							<div style="clear: both; height: 5px;">
							</div>


							<!-- <div style="clear: both;margin-left: 15px;"> -->
							<div class="row" style="margin-left: 12px;">
									<button type="submit" id="userCreateButtonId"
										style="margin-left: 23px;"
										class="<s:text name="button.color.submit"/>">
										<s:text name="Breadcrumb.changepassword" />
										<i class="<s:text name="button.icon.submitcard"/>"></i>
									</button>
									<button type="button" id="userCancelButtonId"
										class="<s:text name="button.color.cancel"/>"
										onclick="cancelFunction();">
										<s:text name="Text.button.cancel" />
										<i class="<s:text name="button.icon.replycard"/>"></i>
									</button>
							</div>
						</form>
						<s:form method="post" id="userCancelForm"></s:form>
					</div>
				</div>
			</section>
		</div>
		<!--end container-->
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
	<jsp:include page="./common/allbasicjs.jsp"></jsp:include>
	<!-- dropify -->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/dropify/js/dropify.min.js"></script>
</body>
<script>
	$("#userCreateButtonId")
			.click(
					function() {
						if (!isStrongPwd($("#passwdTextId").val())) {
							$("#password_strength")
									.html(
											"The password must have atleast 8 chars with one uppercase letter, one lower case letter, one digit and one of special characters.");
							$("#password_strength").css("color", "red");
							return false;
						}
					});

	function showPwd() {
		var x = document.getElementById("usernameTextId");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}

		var y = document.getElementById("passwdTextId");
		if (y.type === "password") {
			y.type = "text";
		} else {
			y.type = "password";
		}

		var z = document.getElementById("currentTextId");
		if (z.type === "password") {
			z.type = "text";
		} else {
			z.type = "password";
		}
	}

	function isStrongPwd(password) {

		var uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		var lowercase = "abcdefghijklmnopqrstuvwxyz";

		var digits = "0123456789";

		var splChars = "!@#$%&*()";

		var ucaseFlag = contains(password, uppercase);

		var lcaseFlag = contains(password, lowercase);

		var digitsFlag = contains(password, digits);

		var splCharsFlag = contains(password, splChars);

		if (password.length >= 8 && ucaseFlag && lcaseFlag && digitsFlag
				&& splCharsFlag)
			return true;
		else
			return false;

	}

	function contains(password, allowedChars) {

		for (i = 0; i < password.length; i++) {

			var char = password.charAt(i);

			if (allowedChars.indexOf(char) >= 0) {
				return true;
			}

		}

		return false;
	}

	$(function() {
		$("#passwdTextId").bind("keyup", function() {
			//TextBox left blank.
			if ($(this).val().length == 0) {
				$("#password_strength").html("");
				return;
			}

			//Regular Expressions.
			var regex = new Array();
			regex.push("[A-Z]"); //Uppercase Alphabet.
			regex.push("[a-z]"); //Lowercase Alphabet.
			regex.push("[0-9]"); //Digit.
			regex.push("[$@$!%*#?&]"); //Special Character.

			var passed = 0;

			//Validate for each Regular Expression.
			for ( var i = 0; i < regex.length; i++) {
				if (new RegExp(regex[i]).test($(this).val())) {
					passed++;
				}
			}

			//Validate for length of Password.
			if (passed > 2 && $(this).val().length > 8) {
				passed++;
			}

			//Display status.
			var color = "";
			var strength = "";
			switch (passed) {
			case 0:
			case 1:
				strength = "Weak";
				color = "red";
				break;
			case 2:
				strength = "Good";
				color = "darkorange";
				break;
			case 3:
			case 4:
				strength = "Strong";
				color = "green";
				break;
			case 5:
				strength = "Very Strong";
				color = "darkgreen";
				break;
			}
			$("#password_strength").html(strength);
			$("#password_strength").css("color", color);
		});
	});

	$('#changepwdForm').validate({
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

	$("#usernameTextId").rules("add", {
		required : true,
		minlength : 3,
		maxlength : 15,
		messages : {
			required : "<s:text name="Error.changepassword.oldpassword" />",
		}

	});
	$("#passwdTextId").rules("add", {
		required : true,
		minlength : 3,
		maxlength : 15,
		messages : {
			required : "<s:text name="Error.changepassword.newpassword" />",
		}

	});

	$("#currentTextId").rules("add", {
		required : true,
		minlength : 3,
		maxlength : 15,
		equalTo : "#passwdTextId",
		messages : {
			required : "<s:text name="Error.changepassword.retypepassword" />",
		}
	});
	function cancelFunction() {
		$('#userCancelButtonId').addClass(
				'<s:text name="cancel.button.animated.rollout"/>');
		toShowLoadingImgoverlay();
		$(".card-panel").addClass(
				'<s:text name="cardpanel.button.animated.flipoutx"/>');
		$("#userCancelForm").attr("action", "posthomepage");
		$("#userCancelForm").submit();
	}
</script>
</html>
