<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->

<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />
</head>
<style>#token-input-invitefriend{width: 100% ! important;}</style>
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
							<div class=" col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.viewgatepass" /></h5>
								<ol class="breadcrumbs left">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="Issuegatepassmgmttbl"><s:text name="Breadcrumb.manage.gatepass"/></a></li>
                    <li><a href="Issuegatepassmgmttbl"><s:text name="Breadcrumb.manage.issuegatepass" /></a></li>
                     <li class="active"><s:text name="Breadcrumb.manage.viewgatepass" /></li>
						</ol>
						<div class="right">
						
						
									<button id="addaccountbuttonid" onclick="printData('<s:property value="gatepassObj.gatepassNo"/>')"
										class="<s:text name="button.color.new.create1"/>"
										style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Print">
										<i class="mdi-file-cloud-download left"> </i>Print
									</button>
								
								<button id="gobckbtnid"
											class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">
											Go Back <i class="<s:text name="button.icon.replycard"/>"></i>
										</button>
								</div>
					</div>

							</div>
						</div>
					</div>
				<div class="container">
					<div class="card-panel">
					<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
					<li>
					<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> Visitor Detail</div>
						<div class="collapsible-body padding10px" id="firstdivid">
						<div class="row margin">
<div class="left resilaborview col s3">
<table class="resilabortbl" align="center">
		<tbody>
		<tr>
		<s:if test="#session.visitorimg!=null && #session.visitorimg!=''">
			<td><img class="lbrimg pointer hoverable" id="laborimgid" style="display: block;" src="/templogo/gatepass/mobile/<s:property value="#session.GATEPASSID"/>/<s:property value="#session.visitorimg"/>" onclick="toViewlargesizeimgwithsrc(this,'/templogo/gatepass/web/<s:property value="#session.GATEPASSID"/>/<s:property value="#session.visitorimg"/>')"></td>
		</s:if>
		<s:else>
			<td><img class="lbrimg pointer hoverable" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" id="laborimgid" style="width:100%;" onclick="toViewlargesizeimg(this)"></td>
		</s:else>
			</tr>
		</tbody>
	</table>
	</div>
	<div class="col s9">
							<div class="row">
								<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5"><s:text name="Text.visitorname" /> </div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="gatepassObj.visitorName" /></span>
										</div>
									</div>
								</div>
							<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5">Gatepass No.</div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="gatepassObj.gatepassNo" /></span>
										</div>
									</div>
								</div>

							</div>
							<div class="row">

								<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5"> <s:text name="Text.visitormobno" /> </div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="gatepassObj.mobileNo" /></span>
										</div>
									</div>
								</div>

								<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5">
										<s:text name="Text.datevisit"/>
										</div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"><s:property value="Visited_date" /></span>
										</div>
									</div>
								</div>
							</div>
							<s:if test="passType.equalsIgnoreCase('1')">
							<div class="row">
								<div class="input-field col s6">
									<div class="form-group" id="divage">
										<div class="input-field col m5">
											<s:text name="Text.time"/>
										</div>
										<div class="input-field col m7">:
										<span class="<s:text name="view.fontvalue.color" />"> <s:property value="Visited_time" /></span>
										</div>
									</div>
								</div>
								<div class="input-field col s6">
								<div class="form-group" id="divage">
									<div class="input-field col m5"><s:text name="Text.expirydate"/></div>
									<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />">
									<s:if test="passType.equalsIgnoreCase('1')">
									<s:property value="Visited_expiry_date" /> Days
									</s:if>
									<s:else>
									<s:property value="Visited_expiry_date" />
									</s:else>
									</span>
											</div>
								</div>
								</div>
								</div>
								<div class="row">
								<div class="input-field col s6">
							<div class="form-group" id="divage">
								<div class="input-field col m5">Pass Type</div>
								<div class="input-field col m7"> :
								<s:if test="passType.equalsIgnoreCase('1')">
								<span class="<s:text name="view.fontvalue.color" />">
								Visitor
								</span>
								</s:if>
								<s:else>
								<span class="<s:text name="view.fontvalue.color" />">Skilled Help</span>
								</s:else>
										</div>
							</div>
						</div>
								</div>
							</s:if>	<s:else>
								<div class="row">
								<div class="input-field col s6">
								<div class="form-group" id="divage">
									<div class="input-field col m5"><s:text name="Text.expirydate"/></div>
									<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />">
									<s:if test="passType.equalsIgnoreCase('1')">
									<s:property value="Visited_expiry_date" /> Days
									</s:if>
									<s:else>
									<s:property value="Visited_expiry_date" />
									</s:else>
									</span>
											</div>
								</div>
								</div>
								<div class="input-field col s6">
							<div class="form-group" id="divage">
								<div class="input-field col m5">Pass Type</div>
								<div class="input-field col m7"> :
								<s:if test="passType.equalsIgnoreCase('1')">
								<span class="<s:text name="view.fontvalue.color" />">
								Visitor
									</span>
								</s:if>
									<s:else>
									<span class="<s:text name="view.fontvalue.color" />">Skilled Help</span>
									</s:else>
										</div>

							</div>
						</div>


					</div>
							</s:else>



					</div>
					</div>
</div>
</li>
					<!-- tab-1 -->
					<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>
<div class="collapsible-body padding10px" id="seconddivid" >
<!-- <div class="input-field col s12"> <div class="input-field col s5"> <b class="">Other Details</b></div> </div> -->
<div class="row margin">
					<s:if test="passType.equalsIgnoreCase('1')">
			<div class="row">
			<div class="input-field col s6">
							<div class="form-group" id="divage">
								<div class="input-field col m5">Location</div>
								<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />">
								<s:if test="!Block_Name.equalsIgnoreCase('') ">
									BLOCK - <s:property value="Block_Name" />,
									<s:if test="!Flat_Name.equalsIgnoreCase('') ">
									DOOR - <s:property value="Flat_Name" />
									</s:if>
								</s:if>
								<s:else>
								</s:else>
								</span>
										</div>

							</div>
						</div>
						<div class="input-field col s6">
										<div class="input-field col m5"><s:text name="Text.accompanies" /></div>
										<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">
										<s:property value="gatepassObj.noOfAccompanies" /></span>
							</div>
						</div>
			</div>
			<div class="row">
						<div class="input-field col s6">
										<div class="input-field col m5"><s:text name="Text.vehicleno"/></div>
										<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">
										<s:property value="gatepassObj.vehicleNumber" /></span>
							</div>
						</div>
				</div>
			<div class="row">
						<div class="input-field col s12">
								<div class="input-field col" style="width: 20.7%"><s:text name="Text.desc"/></div>
								<div class="input-field col m9 breakword">
								: <span class="<s:text name="view.fontvalue.color" />"><s:property value="gatepassObj.description" />
									</span>
								</div>
						</div>

					</div>
					</s:if>
					<s:else>
					<div class="row">
						<div class="input-field col s6">
							<div class="form-group" id="divage">
								<div class="input-field col m5"><s:text name="Text.customerreg.idproof"/></div>
								<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property
										value="idcardName" /></span>
										</div>
							</div>
						</div>
						<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5">
										<s:text name="Text.customerreg.idproofno"/>
										</div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"><s:property value="gatepassObj.idCardNumber" /></span>
										</div>
									</div>
						</div>
					</div>
					<div class="row">

						<div class="input-field col s6">
								<div class="input-field col m5"><s:text name="Text.skillname" /></div>
								<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property
										value="skillName" /></span>
										</div>
						</div>
						<div class="input-field col s6">
										<div class="input-field col m5"><s:text name="Text.customerreg.emailid"/></div>
										<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">
										<s:property value="gatepassObj.email" /></span>
							</div>
						</div>
					</div>
					<div class="row">

						<div class="input-field col s6">
										<div class="input-field col m5"><s:text name="Text.resident.dob"/></div>
										<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">
										<s:property value="gatepassObj.dateOfBirth" /></span>
							</div>
						</div>
				</div>
				<div class="row">
						<div class="input-field col s12">
								<div class="input-field col" style="width: 20.7%"><s:text name="Text.desc"/></div>
								<div class="input-field col m9 breakword">
								: <span class="<s:text name="view.fontvalue.color" />"><s:property value="gatepassObj.description" />
									</span>
								</div>
						</div>
						</div>
					</s:else>
				</div>
					</div>
					</li>
					</ul>
					</div>
					</div>



</div>

		</div>
		</section>
	</div>
	<s:form method="post" id="userCancelForm"></s:form>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
<jsp:include page="../common/clorbox.jsp"></jsp:include>

</body>
<script>
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "Issuegatepassmgmttbl");
		$("#userCancelForm").submit();
	});
});

function printData(id) {
	$.ajax({
		type : 'post',
		datatype : 'html',
		url : 'gatepassprintData',
		data : "uniqueGatepassno=" + id,
		success : function(data) {
		/* 	document.write(data);
			window.print(); */
			var spli = data.split("!_!");
			if(spli[1]=="success"){
			 var mywindow = window.open('', 'my div', 'height=600,width=800,toolbar=0,scrollbars=0,status=0');
		        mywindow.document.write('<html><head><title></title>');
		        /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
		        mywindow.document.write('</head><body ><div id="header" style="background-color:White;"></div>');
		        mywindow.document.write(spli[0]);
		        mywindow.document.write('<div id="footer" style="background-color:White;"></div></body></html>');
		        mywindow.print();
		        mywindow.close();
			}else{
				BootstrapDialog.alert('The society does not have root user'); 
			}
		}, error:function(dd){}
	});
}
</script>
</html>
