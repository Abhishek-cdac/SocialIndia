<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<sj:head locale="en" jqueryui="true" jquerytheme="pagination"
	customBasepath="resources" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/css/login.css" />
<title>Letspay</title>
</head>

<body>
	<div class="header" style="height: 80px; background: #FFFFFF;">
		<div align="center" id="header"
			style="background-color: rgb(255, 255, 255); height: 85px;">
			<div class="loginbanner contain_log" style="width: 100%;">
				<div
					style="float: left; margin-top: 10px; height: 50px; width: 45%;">
					<label class="hdrlogo"><span class="hdrasslo">LETSPAY</span></label>
				</div>
				<div>
					<img src="resources/images/yourlogo.jpg" alt="logo" class="logorght"
						style="margin-top: 2px; float: right; margin-right: 10px;">
				</div>
			</div>
		</div>
	</div>
	<div style="height: 30px;"></div>
	<div class="box_design"></div>
	<div class="height15"></div>
	<div class="lgoinfont">LOGIN</div>
	<div style="height: 5px;"></div>
	<div class="broderbot"></div>

	<div class="clear" style="height: 10px;"></div>
	<div class="loginwrapper">
		<div class="loginwrap zindex100 animate2 bounceInDown">
			<div class="loginwrapperinner"
				style="background: none; padding: 20px 0 0 0">
				<form id="loginform" method="post">
					<div class="animate4 bounceIn">
						<label class="label_id">User-ID:</label>
						<s:textfield name="userInfo.userName" cssClass="txtInput" id="userId" value=""
							placeholder="Your login name"></s:textfield>
					</div>
					<div class="animate5 bounceIn">
						<label class="label_id">Password:</label>
						<s:password id="passwd" name="userInfo.password" cssClass="txtInput" value=""
							placeholder="Password"></s:password>
					</div>

					<div class="animate6 bounceIn">
						<input type="button" class="btn btn-default btn-block"
							id="userLogin" name="submitform" value="Sign In" title="Login">
						<input type="button" class="btn btn-default btn-block"
							id="usersignup" name="submitform" onclick="signupfun();" value="Sign Up">
					</div>
					<div>
						<a href="#" id="forgotlink"
							onclick="$('#contactEmail').val('');"
							style="float: left; width: 40%; margin-left: 40%; border: none; color: #071c31; margin-top: 10px;"><img
							src="/uambootstrap/resources/images/forgot.png"
							style="width: 18px; display: inline; margin-top: -2px;" />
							Request Password?</a>
					</div>
					<input type="hidden" name="aType" id="aType" value="usrLogin" />

				</form>

				<div style="display: none">
					<div id="forgotpassdiv">
						<div class="modTitle colorbxtitle"
							style="font-size: 13px; font-weight: bold;">Request
							Password</div>
						<div class="coloboxmodContent">
							<!--                                <div id="msgbox"></div>-->
							<form method="post" name="forgotpwd" id="forgotpwd"
								action="index.jsp">
								<table class="tblform">
									<tr>
										<td>
											<div class="label">
												Email<span class="mandatory">*</span>
											</div> <input class="textbox" name="contactEmail" id="contactEmail"
											type="text" />
										</td>
									</tr>
									<tr>
										<td><input type="button" id="resetreq" class="btnsubmit"
											value="Request For New Password" onclick="reqForgorPass();" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>

				<div style="display: none">
					<div id="setpass">
						<div class="modTitle colorbxtitle"
							style="font-size: 13px; font-weight: bold;">Change Password</div>
						<div class="coloboxmodContent"
							style="border-width: 0; border: none;">
							<div class="errordiv" style="display: none; width: 80%;"
								onclick="$('.error_box').html('');$('.errordiv').hide();">
								<div class="error_box"></div>
							</div>
							<form method="post" name="changepwd" id="changepwd">
								<table class="tblform">
									<tr>
										<td>
											<div class="label">
												New Password<span class="mandatory">*</span>
											</div> <input class="textbox" name="newpwdcha" id="newpwdcha"
											type="password" />
										</td>
									</tr>
									<tr>
										<td>
											<div class="label">
												Retype Password<span class="mandatory">*</span>
											</div> <input class="textbox" name="renewpwd" id="renewpwd"
											type="password" onkeydown="enterkey(event);" />
										</td>
									</tr>
									<tr>
										<td><input type="button" id="setnewpwd" class="btnsubmit"
											value="Submit" onclick="reqsetpwd();" /></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							if (parent.location == window.location) {
							} else {
								window.parent.location.href = window.location;
							}

							function replaceAll(find, replace, str) {
								return str.replace(new RegExp(find, 'g'),
										replace);
							}

							$("#userLogin")
									.click(
											function() {
												var user = jQuery.trim($(
														'#userId').val());
												var pass = jQuery.trim($(
														'#passwd').val());

												if (user.length > 0
														&& pass.length > 0) {
													user = user.replace("&",
															"<AMP>", "g");
													pass = pass.replace("&",
															"<AMP>", "g");
													
													$("#loginform").attr("action", "loginform");
													$("#loginform").submit();

													/* var ajxcal = callAjax("view/autocomplete.jsp?type=newloginusr&usrnm="
															+ user
															+ "&pswrd="
															+ pass);
													var ajxsplt = ajxcal
															.split("!_!");
													var stats = ajxsplt[1];
													if (stats == "newuser") {
														$
																.colorbox({
																	inline : true,
																	width : "380px",
																	href : "#setpass",
																	onOpen : function() {
																		$(
																				"#newpwdcha")
																				.focus();
																	}
																});
													}
													//                        else if(stats == "olduser"){
													//                            $("#loginform").attr("action", "/LoginValidate");
													//                            $("#loginform").submit();                            
													//                        }
													else {
														$("#loginform")
																.attr("action",
																		"/LoginValidate");
														$("#loginform")
																.submit();
													} */
												} else {
													$("#errmsg").slideDown(400);
												}
											});
							$("#forgotlink")
									.colorbox(
											{
												inline : true,
												onComplete : function(e) {
													$("#resetreq")
															.click(
																	function(e) {
																		e
																				.stopPropagation();
																		if ($(
																				"#contactEmail")
																				.val() == "") {
																			jAlert("Please enter your Email");
																		} else {
																			$.colorbox
																					.close();
																		}
																	});
												},
												width : "30%",
												href : "#forgotpassdiv"
											});
						});
		function reqForgorPass() {
			//                $.fn.colorbox.close();
			var cmail = $("#contactEmail").val();
			var cuser = $("#contactUser").val();
			if (cmail.length > 0) {
				cmail = cmail.replace("&", "<AMP>", "g");
				//                    var dt = callAjax("/UserAction?actiontype=forgotPass&email="+cmail+"&usr="+cuser);
				var dt = callAjax("/UserAction?actiontype=forgotPass&email="
						+ cmail);
				var x = dt.split("<MPV>");
				jAlert(x[1], "Information");
			}
		}
		function reqsetpwd() {
			$('.error_box').html('');
			$('.errordiv').show();
			$('#newpwdcha').require("New Password is required").minLength(3,
					"New Password allow minimum 3 characters").maxLength(10,
					"New Password allow maximum 10 characters");
			$('#renewpwd').require("Retype password is required").minLength(3,
					"Re-Enter Password allow minimum 3 characters").maxLength(
					10, "Re-Enter Password allow maximum 10 characters");

			if ($('.error_box').html() == "") {
				if (jQuery.trim($('#newpwdcha').val()) != jQuery.trim($(
						'#renewpwd').val())) {
					$('.error_box')
							.append(
									"<br/>&nbsp; - &nbsp;Password doesn't match<br/><br/>");
				} else {
					$('.errordiv').hide();

					var user1 = jQuery.trim($('#userId').val());
					var pass1 = jQuery.trim($('#passwd').val());
					var newpass1 = jQuery.trim($('#newpwdcha').val());

					user1 = user1.replace("&", "<AMP>", "g");
					pass1 = pass1.replace("&", "<AMP>", "g");
					newpass1 = newpass1.replace("&", "<AMP>", "g");

					var ajxcal1 = callAjax("view/autocomplete.jsp?type=newpasschange&usrnm="
							+ user1
							+ "&pswrd="
							+ pass1
							+ "&newretyppas="
							+ newpass1);
					var ajxsplt1 = ajxcal1.split("!_!");
					var getval = ajxsplt1[1];
					if (getval == "success") {
						$('#passwd').val(newpass1);
						$("#loginform").attr("action", "loginform");
						$("#loginform").submit();
					} else if (getval == "failed") {
						jAlert("Please Try Again");
					}
				}
			}
		}
		function frmsubmit(e) {
			if (e.keyCode == 13) {
				var user = jQuery.trim($('#userId').val());
				var pass = jQuery.trim($('#passwd').val());

				if (user.length > 0 && pass.length > 0) {
					$("#formUserLogin").submit();
				} else {
					$("#errmsg").slideDown(400);
				}
			}
		}
	
		$(function() {
			$("form input").keypress(
					function(e) {
						if ((e.which && e.which == 13)
								|| (e.keyCode && e.keyCode == 13)) {
							$('#userLogin').click();
						}
					});
		});
		
		function signupfun(){
			/* $("#usersignup").attr("action", "signupaction");
			$("#usersignup").submit(); */
			$("#loginform").attr("action", "signupaction");
			$("#loginform").submit();
		}
	</script>
	<script>
		function enterkey(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if (keycode == '13') {
				reqsetpwd();
			}
		}
	</script>
	<div class="clear"></div>
	<div style="height: 50px; clear: both;"></div>
	<%@include file="common/footer.jsp"%>
</body>
</html>