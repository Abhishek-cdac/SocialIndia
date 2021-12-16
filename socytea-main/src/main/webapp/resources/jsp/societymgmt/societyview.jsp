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
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>
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
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<jsp:include page="../common/menuBar.jsp"></jsp:include>
			<!-- //////////////////////////////////////////////////////////////////////////// -->
			<!-- START CONTENT -->
			<section id="content">
				<!--breadcrumbs start-->
				<div id="breadcrumbs-wrapper">
					<!-- Search for small screen -->
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">
									<s:text name="Society Detail" />
								</h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text
												name="Breadcrumb.dashboardhome" /></a></li>
									<li><a href="societymgmt"><s:text
												name="Breadcrumb.manage" /></a></li>
									<li><a href="societymgmt"><s:text
												name="Breadcrumb.manage.societymgmt" /></a></li>
									<li class="active"><s:text name="Society Detail" /></li>
								</ol>
								<div class="right">
									<a href="societymgmt"><button type="button" id="gobckbtnid"
											class="<s:text name="button.color.cancel" />"
											data-toggle="modal" data-target="#addnewaccount"
											data-position="bottom"
											data-delay="<s:text name="material.tooltip.delay"/>"
											data-tooltip="Go Back">
											<i class="<s:text name="button.icon.replycard"/>"></i>Go Back
										</button></a>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="container">
					<div class="card-panel">
						<form id="residentCreationFormId" name="residentCreationFormId"
							action="societymanage" method="post"
							enctype="multipart/form-data">
							<!-- <div class="row"><div class="input-field col m5"> <b>Society Details </b></div> </div> -->
							<ul class="collapsible collapsible-accordion"
								data-collapsible="expandable">
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" /> active">
										<i
											class="mdi-social-location-city tinysmall white-text text-accent-4"></i>
										Society Detail
									</div>
									<div class="collapsible-body" id="firstdivid">
										<div class="padding10px">
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Township Name</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.townshipName" />
															</span>
														</div>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Society Name</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.societyName" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Registration No.</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.registerNo" />
															</span>
														</div>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Activation Key</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.activationKey" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Email</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="emailId" />
															</span>
														</div>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Mobile No.</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="isdcode" />-<s:property value="mobileNo" /></span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</li>

								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" /> active">
										<i
											class="mdi-communication-business tinysmall white-text text-accent-4"></i>
										Block Detail
									</div>
									<div class="collapsible-body" id="seconddivid">
										<div class="padding10px">
											<!-- <div class="row input-field col s12"><div class="input-field col m5"> <b>Block Detail  </b></div></div> -->

											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">No. of Block/Wings</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.noOfBlocksWings" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<s:iterator value="societyWingList">
												<div class="row" style="margin-left: 12px;">
													<div class="input-field col m6">
														<div class="row">
															<div class="input-field col m5">Block/Wing Name</div>
															<div class="input-field col m7">
																: <span class="<s:text name="view.fontvalue.color" />">
																	<s:property value="wingsName" />
																</span>
															</div>
														</div>
													</div>
												</div>
											</s:iterator>
											<div class="row">
												<div class="input-field col m6">
													<!-- <div class="row">
                  <div class="input-field col m5">Block/Wing Name</div>
                   <div class="input-field col m7">: Block2</div>
                      </div> -->
												</div>
											</div>

										</div>
									</div>
								</li>
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" /> active">
										<i
											class="mdi-action-extension tinysmall white-text text-accent-4"></i>
										Other Detail
									</div>
									<div class="collapsible-body" id="thirddivid">
										<div class="padding10px">
											<!-- <div class="row"> <div class="input-field col m5"> <b>Other Details </b></div> </div> -->
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Imprint Name</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.imprintName" />
															</span>
														</div>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Color Code</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.colourCode" />
															</span>
														</div>
													</div>

												</div>
											</div>
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">

														<s:if
															test="((societyMst.logoImage!='' && societyMst.logoImage!='null'))">
															<div class="input-field col m5">
																<div style="padding-top: 30px;">Logo</div>
															</div>
															<div class="input-field col m7">
																<div style="padding-top: 30px;" class="left">:</div>
																<span style="margin-left: 2px;"
																	class="left hoverable tooltipped"
																	data-position="bottom"
																	data-delay="<s:text name="material.tooltip.delay"/>"
																	data-tooltip="Click to view">&nbsp;<img
																	onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/society/web/<s:property value="societyMst.societyId"/>/<s:property value="societyMst.logoImage"/>');"
																	src="/templogo/society/mobile/<s:property value="societyMst.societyId"/>/<s:property value="societyMst.logoImage"/>"
																	class="webimagedatable" name="" id="imgeLogo"></span>
															</div>
														</s:if>
														<s:else>
															<div class="input-field col m5">Logo</div>
															<div class="input-field col m7">:</div>
														</s:else>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">

														<s:if
															test="((societyMst.icoImage!='' && societyMst.icoImage!='null'))">
															<div class="input-field col m5">
																<div style="padding-top: 30px;">Ico</div>
															</div>
															<div class="input-field col m7">
																<div style="padding-top: 30px;" class="left">:</div>
																<span style="margin-left: 2px;"
																	class="left hoverable tooltipped"
																	data-position="bottom"
																	data-delay="<s:text name="material.tooltip.delay"/>"
																	data-tooltip="Click to view">&nbsp;<img
																	onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/society/web/<s:property value="societyMst.societyId"/>/<s:property value="societyMst.icoImage"/>');"
																	src="/templogo/society/web/<s:property value="societyMst.societyId"/>/<s:property value="societyMst.icoImage"/>"
																	class="webimagedatable" name="" id="imgeLogo"></span>
															</div>
														</s:if>
														<s:else>
															<div class="input-field col m5">ICO</div>
															<div class="input-field col m7">:</div>
														</s:else>
													</div>

												</div>
											</div>
										</div>
									</div>
								</li>
								<li>
									<div
										class="collapsible-header <s:text name="collapsible.header.color" /> active">
										<i
											class="mdi-action-account-balance tinysmall white-text text-accent-4"></i>
										Account Detail
									</div>
									<div class="collapsible-body" id="fourthdivid">
										<div class="padding10px">
											<!-- <div class="row" > <div class="input-field col m5"> <b>Account Details</b></div> </div> -->
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Account No.</div>
														<div class="input-field col m7">
															: <span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyAcc.bankAccNo" />
															</span>
														</div>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Account Name</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyAcc.bankAccName" />
															</span>
														</div>
													</div>

												</div>
											</div>
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">Bank Name</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyAcc.bankName" />
															</span>
														</div>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">IFSC Code</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyAcc.ifscCode" />
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="row" style="margin-left: 12px;">
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">GSTIN</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.gstin" />
															</span>
														</div>
													</div>
												</div>
												<div class="input-field col m6">
													<div class="row">
														<div class="input-field col m5">SAC/HSN</div>
														<div class="input-field col m7">
															:<span class="<s:text name="view.fontvalue.color" />">
																<s:property value="societyMst.hsn" />
															</span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</li>
							</ul>
							<input type="hidden" name="alert" value="User profile created">
						</form>
						<s:form method="post" id="userCancelForm"></s:form>
					</div>
					<!--end container-->
				</div>
			</section>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<jsp:include page="../common/clorbox.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#gobckbtnid").click(
						function() {
							$('#gobckbtnid').addClass(
									'<s:text name="goback.button.animated"/>');
						});
			});
</script>
</html>

