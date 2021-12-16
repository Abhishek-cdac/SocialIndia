<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><s:text name='Text.project.title' /></title>
<style type="text/css">
#userCreationFormId ul li{
	list-style: none;
	color: #D90F0F;	
	font-size: 11px;
	margin-left: -40px;
    margin-top: 0px;
}.fileUpload {
    position: relative;
    overflow: hidden;
       margin-top: 10px; 
     margin-left: -15px;
}
.fileUpload input.upload {
    position: absolute;
    top: 0;
    right: 0;
    margin: 0;
    padding: 0;
    font-size: 20px;
    cursor: pointer;
    opacity: 0;
    filter: alpha(opacity=0);
}
</style>
</head>

<body>
	<div class="container-fluid">
		<!-- START Header Container -->
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div id="right-column">
			<div class="right-column-content">
				<div class="row">
					<div class="col-xs-12">
						<ol class="breadcrumb">
						<li><a href="posthomepage"><s:text
										name="Text.Home" /></a></li>
							<li class="active"><a href="townshipmgmt"><s:text
										name="Breadcrumb.manage" /></a></li>
							<li class="active">Township Detail</li>
						</ol>
					</div>
				</div>
				 <div class="row">
					<div class="col-md-6">
						<h1>
							<span aria-hidden="true" class="icon icon-settings"></span> <span
								class="main-text">Township Detail</span>
						</h1>
					</div>
					<div class="col-md-12">
							<div class="block">
								<div class="block-heading">
									<!-- <div class="main-text h2">
										Township View
									</div> -->
									<div class="text-right" style="margin-bottom: 10px;margin-right:1px;"><a href="townshipmgmt"><button type="submit" class="btn btn-primary btn-sm" id="submitbtn" name="submitbtn"
										value="Close" >Go To Back</button></a></div>
								</div>
								
								<div class="block-content-outer">
									<div class="block-content-inner">
										<form method="post" id="registerformid" name="registerformid" 
											action="">
											<div class="row">
												<div class="col-sm-5">
													<div class="form-group" id="divfirstname">
														<label label class="col-md-4 control-label"><s:text
																name="Township Name" /></label><label
															class="col-md-1 control-label"> :</label><b><s:property value="townShipMst.townshipName"/></b>

													</div>
												</div>
												
												<div class="col-sm-5">
													<div class="form-group" id="divlastname">
														<label label class="col-md-4 control-label">Builder Name
															</label><label class="col-md-1 control-label"> :</label><b>
															<s:property value="townShipMst.builderName"/></b>
													</div>
												</div>
											</div>
											<div class="row">
											<div class="col-sm-5">
													<div class="form-group" id="divlastname">
														<label label class="col-md-4 control-label">No. Of
															Society</label><label class="col-md-1 control-label"> :</label><b>
															<s:property value="townShipMst.noOfSociety"/></b>
													</div>
												</div>
												<div class="col-sm-5">
													<div class="form-group" id="divfirstname">
														<label label class="col-md-4 control-label">No. of
															Flats</label><label class="col-md-1 control-label"> :</label><b>
															<s:property value="townShipMst.noOfFlats"/></b>

													</div>
												</div>
												
											</div>
											<div class="row">
												<div class="col-sm-5">
													<div class="form-group" id="divfirstname">
														<label label class="col-md-4 control-label">Country</label><label
															class="col-md-1 control-label"> :</label><b> <s:property value="townShipMst.countryName"/></b>

													</div>
												</div>
												<div class="col-sm-5">
													<div class="form-group" id="divlastname">
														<label label class="col-md-4 control-label"><s:text
																name="Text.state"></s:text></label><label
															class="col-md-1 control-label"> :</label><b> 
															<s:property value="townShipMst.stateName"/></b>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-5">
													<div class="form-group">
														<label label class="col-md-4 control-label"><s:text
																name="Text.city"></s:text></label><label
															class="col-md-1 control-label"> :</label><b>
															 <s:property value="townShipMst.cityName"/></b>

													</div>
												</div>
												<div class="col-sm-5">
													<div class="form-group">
														<label label class="col-md-4 control-label"><s:text
																name="Menuheader.uam.profile.pincode"></s:text></label><label
															class="col-md-1 control-label"> :</label><b>
															 <s:property value="townShipMst.pinCode"/></b>

													</div>
												</div>
											</div>
											<div class="row">
											
											<div class="col-sm-5">
													<div class="form-group">
														<label label class="col-md-4 control-label">Land Mark
															</label><label class="col-md-1 control-label">:
															</label><b> <s:property value="townShipMst.landMark"/></b>

													</div>
												</div>
												
											<div class="col-sm-5">
													<div class="form-group" id="divlastname">

														<label label class="col-md-4 control-label">Activation
															Key</label><label class="col-md-1 control-label"> :</label><b>
															<s:property value="townShipMst.activationKey"/></b>
													</div>
												</div>
											</div>
											
											<div class="row">
												<div class="col-sm-5">
													<div class="form-group">
														<label label class="col-md-4 control-label">Mobile No.
															</label><label class="col-md-1 control-label">:
															</label><b> <s:property value="townShipMst.mobileNo"/></b>

													</div>
												</div>
											<div class="col-sm-5">
													<div class="form-group" id="divlastname">

														<label label class="col-md-4 control-label">Email
															</label><label class="col-md-1 control-label"> :</label><b>
															<s:property value="townShipMst.emailId"/></b>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-5">
													<div class="form-group">
														<label label class="col-md-4 control-label">Color
															Code (Hex)</label><label class="col-md-1 control-label">
															:</label><b> <s:property value="townShipMst.townshipcolourcode"/></b>

													</div>
												</div>
											<div class="col-sm-5">
													<div class="form-group" id="divlastname">

														<label label class="col-md-4 control-label">Imprint Name
															</label><label class="col-md-1 control-label"> :</label><b>
															<s:property value="townShipMst.imprintName"/></b>
													</div>
												</div>
											</div>
											
											<br>
											<div class="row">
												<div class="col-sm-5">
													<div class="form-group">
														<label label class="col-md-4 control-label"
															style="margin-top: 20px;">Logo</label><label
															class="col-md-1 control-label" style="margin-top: 20px;">
															:</label><b>
															<s:if test="townShipMst.townshiplogoname!=null && townShipMst.townshiplogoname!=''">
															 <img
															src="/templogo/township/web/<s:property value="townShipMst.townshipId"/>/<s:property value="townShipMst.townshiplogoname"/>"
															style="max-width: 236px;margin:7px; min-width: 120px; max-height: 75px; min-height: 50px;" width="17%" /></b>
															</s:if><s:else> 
															<img id="outputico"
																class="list-thumbnail"
																src="resources/images/social/profile-default-male.png"  alt="profile-pic-4"
																style="max-width: 236px;margin:7px; min-width: 120px; max-height: 75px; min-height: 50px;margin-top: -3px" />
															 </s:else>
													</div>
												</div>
												<div class="col-sm-5">
													<div class="form-group">
														<label label class="col-md-4 control-label"
															style="margin-top: 20px;">ICO Logo</label><label
															class="col-md-1 control-label" style="margin-top: 20px;">
															:</label><b>
															<s:if test="townShipMst.townshipiconame!=null && townShipMst.townshipiconame!=''">
															 <img
															src="/templogo/township/web/<s:property value="townShipMst.townshipId"/>/<s:property value="townShipMst.townshipiconame"/>"
															style="max-width: 236px;margin:7px; min-width: 120px; max-height: 75px; min-height: 50px;" width="8%" /></b>
															</s:if><s:else> 
															<img id="outputico"
														class="list-thumbnail"
														src="resources/images/social/profile-default-male.png"  alt="profile-pic-4"
														style="max-width: 236px;margin:7px; min-width: 120px; max-height: 75px; min-height: 50px;margin-top: -3px" />
															 </s:else>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-5">
													<div class="form-group"></div>
												</div>
											</div>

										</form>

										<s:form method="post" id="userCancelForm"></s:form>
										
									</div>
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
	
	
	<!-- OTP Start Colorbox -->
	 <!-- <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" id="otpColorbox"></div>  -->
	<!-- OTP End Colorbox -->

	<!-- /.container -->

	<!-- Placed at the end of the document so the pages load faster -->
<!-- Required CSS Files -->
	<%-- <link type="text/css" href="<s:text name='Resource.path'/>/assets/css/required/bootstrap/bootstrap.min.css" rel="stylesheet"> --%>
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
	<%-- <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-ui-1.11.0.custom/jquery-ui.min.js"></script> 
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/bootstrap/bootstrap.min.js"></script>--%>
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
	<%-- <script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/demo-files/form-validation.js"></script> --%>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/formValidation.min.js" ></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/bootstrap.min-1.js"></script>
	
	<link type="text/css" href="<s:text name='Resource.path'/>/assets/css/optional/typeahead/typeahead.css" rel="stylesheet" />
	<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/optional/typeahead/typeahead.bundle.js"></script>

</body>
</html>
