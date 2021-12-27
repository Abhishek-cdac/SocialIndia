<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<sj:head locale="en" jqueryui="true" jquerytheme="pagination"
	customBasepath="/resources" />
<title>SMS Config</title>
<style>
.ui-datepicker-trigger {
	display: none;
}
</style>
</head>

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
				<div class="row">
					<div class="col-xs-12">
						<ol class="breadcrumb">
							<li><a href="index.html"
								tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/index.html">Home</a>
							</li>
							<li class="active"><a href="#">Configuration</a></li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h1>
							<span aria-hidden="true" class="icon icon-grid-big"></span> <span
								class="main-text"> SMS </span>
						</h1>
					</div>
					<div class="col-md-6"></div>
				</div>

				<div class="row">
					<div class="col-md-8">

						<div class="row">
							<div class="col-md-6">
								<!-- START Widget Hybrid: Task List -->

								<jsp:include page="../common/Alert.jsp" flush="true" />
								<div class="errordiv" style="display: none; margin-top: 10px;"
									onclick="$('.error_box').html('');$('.errordiv').hide();">
									<div class="error_box"></div>
								</div>

								<form method="post" name="formSmsconfig" id="formSmsconfig" >
									<table class="tblform">
										<tr>
											<td>
												<div>
													HTTP Url<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.HttpUrl" id="httpurl"
													cssClass="inp-form" value="%{smsconfObj.HttpUrl}"
													cssStyle="width:200px;" />
											</td>
											<td>
												<div>User Name</div> <s:textfield
													name="smsconfObj.UserName" id="usernam" cssClass="inp-form"
													value="%{smsconfObj.UserName}" cssStyle="width:200px;" />
											</td>
										</tr>
										<tr>
											<td>
												<div>
													Password<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.password" id="passwrd"
													cssClass="inp-form" value="%{smsconfObj.password}"
													cssStyle="width:200px;" />
											</td>
											<td>
												<div>
													System Id<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.systemid" id="sysid"
													cssClass="inp-form" value="%{smsconfObj.systemid}"
													cssStyle="width:200px;" />
											</td>
										</tr>
										<tr>
											<td>
												<div>
													Sender<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.SenderName" id="sendernam"
													cssClass="inp-form" value="%{smsconfObj.SenderName}"
													cssStyle="width:200px;" />
											</td>
											<td>
												<div>
													CDMA Sender<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.CdmaSender" id="cdmasender"
													cssClass="inp-form" value="%{smsconfObj.CdmaSender}"
													cssStyle="width:200px;" />
											</td>
										</tr>
										<tr>
											<td>
												<div>
													Provider Name<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.ProviderName"
													id="providerNam" cssClass="inp-form"
													value="%{smsconfObj.ProviderName}" cssStyle="width:200px;" />
											</td>
											<td>
												<div>
													Server IP<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.serverip" id="servrip"
													cssClass="inp-form" value="%{smsconfObj.serverip}"
													cssStyle="width:200px;" />
											</td>
										</tr>
										<tr>
											<td>
												<div>
													Port No<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.port" id="portno"
													cssClass="inp-form" value="%{smsconfObj.port}"
													cssStyle="width:200px;" />
											</td>
											<td>
												<div>
													Config Type<span class="mandatory">*</span>
												</div> <s:textfield name="smsconfObj.configtype" id="configtyp"
													cssClass="inp-form" value="%{smsconfObj.configtype}"
													cssStyle="width:200px;" />
											</td>
										</tr>
									</table>

									<input type="button" id="cnfgUpdate" name="cnfgUpdate"
										class="btnsubmit" value="Configure" onclick="validate();"
										style="margin: 0 0 0 15px" /><input
										type="hidden" name="actiontype" id="actiontype"
										value="smtpconfiguratuion" />
									<div style="height: 7px"></div>
								</form>



								<!-- END Widget Hybrid: Task List -->
							</div>
						</div>

					</div>

				</div>
			</div>

			<!-- START Footer Container -->
			<jsp:include page="../common/footer.jsp"></jsp:include>
			<!-- END Footer Container -->

		</div>
		<!-- END Right Column -->

	</div>
	<!-- END Body Container -->
	</div>
	<!-- /.container -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- Required JS Files -->
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery-1.11.1.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/jquery-1.11.1.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery-ui.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/bootstrap.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.easing.1.3-min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/jquery.easing.1.3-min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.mCustomScrollbar.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/jquery.mCustomScrollbar.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.mousewheel-3.0.6.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/misc/jquery.mousewheel-3.0.6.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/retina.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/misc/retina.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/icheck.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/icheck.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.ui.touch-punch.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/misc/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/circloid-functions.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/required/circloid-functions.js"></script>

	<!-- Optional JS Files -->
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/circloid-functions-optional.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/circloid-functions-optional.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.vmap.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/jqvmap/jquery.vmap.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.vmap.world.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/jqvmap/maps/jquery.vmap.world.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.vmap.sampledata.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/jqvmap/data/jquery.vmap.sampledata.js"></script>
	<!-- JQVMap Sample Data -->
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.JUMlib.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.JUMlib.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.resize.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.resize.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.tooltip.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.tooltip.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.pie.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.pie.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.stack.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.stack.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.time.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.time.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.flot.orderBars.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/flot/jquery.flot.orderBars.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/jquery.easypiechart.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/moment.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/misc/moment.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/fullcalendar.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/fullcalendar/fullcalendar.min.js"></script>
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/bootstrap-datetimepicker.min.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/optional/bootstrap-datetimepicker.min.js"></script>
	<!-- add optional JS plugin files here -->

	<!-- REQUIRED: User Editable JS Files -->
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/script.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/script.js"></script>
	<!-- add additional User Editable files here -->

	<!-- Demo JS Files -->
	<script type="text/javascript"
		src="/uambootstrap/resources/js/bootstrap/index.js"
		tppabs="http://livedemo.base5builder.com/circloid_html/type_1/templates/blue/assets/js/demo-files/index.js"></script>
	<!-- Start of StatCounter Code for Default Guide -->
	
	<link rel="stylesheet" type="text/css" href="/uambootstrap/resources/css/jquery.validity.css" />

	<script type="text/javascript" src="/uambootstrap/resources/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="/uambootstrap/resources/js/jQuery.validity.js"></script>
	
	<script type="text/javascript">
		var sc_project = 10227118;
		var sc_invisible = 1;
		var sc_security = "b8dc3e52";
		var scJsHost = (("https:" == document.location.protocol) ? "https://secure."
				: "http://www.");
		document
				.write("<sc"+"ript type='text/javascript' src='" +
scJsHost+
"statcounter.com/counter/counter.js'></"+"script>");
	</script>
	<noscript>
		<div class="statcounter">
			<a title="site stats"
				href="javascript:if(confirm(%27http://statcounter.com/  \n\nThis file was not retrieved by Teleport Pro, because it is addressed on a domain or path outside the boundaries set for its Starting Address.  \n\nDo you want to open it from the server?%27))window.location=%27http://statcounter.com/%27"
				tppabs="http://statcounter.com/" target="_blank"><img
				class="statcounter" src="index.htm.gif"
				tppabs="http://c.statcounter.com/10227118/0/b8dc3e52/1/"
				alt="site stats"></a>
		</div>
	</noscript>
	<!-- End of StatCounter Code for Default Guide -->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})
				(
						window,
						document,
						'script',
						'/uambootstrap/resources/js/bootstrap/analytics.js'/*tpa=http://www.google-analytics.com/analytics.js*/,
						'ga');

		ga('create', 'UA-41035904-5', 'auto');
		ga('send', 'pageview');
	</script>
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})
				(
						window,
						document,
						'script',
						'/uambootstrap/resources/js/bootstrap/analytics.js'/*tpa=http://www.google-analytics.com/analytics.js*/,
						'ga');

		ga('create', 'UA-41035904-5', 'auto');
		ga('send', 'pageview');
	</script>
	
	<script type="text/javascript">
		function validate() {
			$('.error_box').html('');
			$('.errordiv').show();	
			
			$('#httpurl').require("HTTP Url is required");
			$('#usernam').require("User Name is required");
			$('#passwrd').require("Password is required");
			$('#sysid').require("System Id is required");
			$('#sendernam').require("Sender Name is required");
			$('#cdmasender').require("CDMA Sender Name is required");
			$('#providerNam').require("Provider Name is required");
			$('#servrip').require("Server IP is required");
			$('#portno').require("Port number is required").match("number",
					"Please enter valid port number");
			$('#configtyp').require("Config type is required");
			/* $("#fromidCfg").maxLength(50,
					"Sender Email should not exceeed max. of 50 characters"); */
			
			if ($('.error_box').html() == "") {
				$('.errordiv').hide();
				alert("test");
				$("#formSmsconfig").attr("action", "smsConfigUpdate");
				$("#formSmsconfig").submit();
			}
		}
	</script>
</body>
</html>
