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
<title><s:text name="Text.Title"/></title>
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
						<h5 class="breadcrumbs-title"><s:text name="Text.merchantdetail.Management" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Dashboard" /></a></li>
								<li><a href="merchantMgmtTbl"><s:text name="Breadcrumb.manage" /></a></li>
								<li><a href="merchantMgmtTbl"><s:text name="Text.Merchant.Management" /></a></li>
								<li class="active"><s:text name="Text.merchantdetail.Management" /></li>

							</ol>
							<div class="right">
							<button id="gobckbtnid" class="tooltipped <s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button>
							</div>
					</div>
				</div>						
				</div></div>
<div class="container">
<div class="card-panel">
		<!-- <div><div class="input-field col m5"> <b>Profile Detail</b></div></div> -->
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">								
		
		<div class="row padding10px">
		<div class="left resilaborview col s3">
			<table class="resilabortbl" align="center">
			<tbody>
			<tr>
			<s:if test="#session.merchantProfileImage!=null && #session.merchantProfileImage!=''">
				<td><img class="lbrimg pointer hoverable" id="laborimgid" style="display: block;" src="/templogo/Merchant/mobile/<s:property value="#session.MarchantSessID"/>/<s:property value="#session.merchantProfileImage"/>" onclick="toViewlargesizeimgwithsrc(this,src='/templogo/Merchant/mobile/<s:property value="#session.MarchantSessID"/>/<s:property value="#session.merchantProfileImage"/>')"></td>
			</s:if>
			<s:else>
				<td><img class="lbrimg pointer hoverable" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" id="laborimgid" style="width:100%;" onclick="toViewlargesizeimg(this)"></td>
			</s:else>
			</tr>
			</tbody>
		</table>										
		</div>
	<div class="col s9">
	<div class="row margin">
  		<div class="col s6">
  			<div class="row form-group " id="divfirstname">
				<div class="col s5" ><s:text name="Text.Form.MerchantName" /></div> 
				<div class="col s7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="merchantobj.mrchntName" /> </span></div>													 																														
			</div>
		</div>
   		<div class="col s6">
  		<div class="row form-group">
		<div class="col s5"><s:text name="Text.merchant.category" /></div> 
		<div class="col s7 breakword"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property value="merchantobj.MerchantCategoryName" /></span></div> 
								
		</div>														 																														
		</div>
	</div>
	<div class="clear height10px"></div>
	<div class="row margin">
	<div class="col s6">
  			<div class="row form-group" id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.emailid" /></div> 
  				<div class="input-field col s7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="merchantobj.mrchntEmail" /> </span></div> 
			</div>
		</div>
		<div class="col s6">
  			<div class="row form-group ">
  				<div class="input-field col s5"><s:text name="Text.mobileno" /></div> 
  				<div class="input-field col s7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="merchantobj.mrchntIsdCode" />-<s:property value="merchantobj.mrchntPhNo" /> </span></div>
			</div>
		</div>			
	</div>
	
	<div class="row margin">
  		<div class="input-field  col s6">
  			<div class="row form-group " id="divfirstname">
			<div class="input-field col s5" ><s:text name="Text.Title.Merchantshopname" /></div>
			 <div class="input-field col s7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="merchantobj.shopName" /> </span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group " id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.store.location" /></div>
  				<div class="input-field col s7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="merchantobj.storeLocation" /></span></div>	  				
			</div>
		</div>
</div>
	<div class="row margin"><div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text name="Text.store.opentime" /></div> 
													<div class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
															value="merchantobj.storeOpentime" /> </span></div>
												</div>
							</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text
															name="Text.store.closetime" /></div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property
															value="merchantobj.storeClosetime" />
													</span></div>
												</div>
											</div>
										</div>	
</div>
</div>
</div></li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>                    
<div class="collapsible-body padding10px" id="seconddivid" >
<!-- <div> <div class="input-field col m5"> <b><s:text name="Text.other.detail" /> </b> </div> </div> <div class="clear height5px"></div>-->
		<div class="padding10px">
		<div class="row margin ">
		<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text
															name="Text.address" />1</div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
															value="merchantobj.mrchntAdd1" /> </span></div>
												</div>
					</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text
															name="Text.address" />2</div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property
															value="merchantobj.mrchntAdd2" />
													</span></div>
												</div>
											</div>
										</div>
										<div class="row margin">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text
															name="Menuheader.uam.profile.country" /></div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
															value="merchantobj.countryName" /> </span></div>
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text
															name="Menuheader.uam.profile.state" /></div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property
															value="merchantobj.stateName" />
													</span></div>
												</div>
											</div>
										</div>
										<div class="row margin">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text
															name="Menuheader.uam.profile.city" /></div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="merchantobj.cityName" /> </span></div>
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text
															name="Text.customerreg.pincode" /></div> <div
<%-- 														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="merchantobj.pstlcodeDesc" /> --%>
															class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="merchantobj.pstlcode" />
													</span></div>
												</div>
											</div>
										</div>
										<div class="row margin">
										<div class="input-field col s12">
												<div class="row">
													<div class="input-field col m3"><s:text name="Text.website" /> : </div> 
													<div class="input-field col m9 breakword"><span class="<s:text name="view.fontvalue.color" />"> <s:property value="merchantobj.website" /> </span></div>
												</div>
											</div>
										</div>
										
<div class="row margin">
<div class="input-field col s12">
<div id="divfirstname" class="row">
<div class="input-field col m5"><s:text name="Text.key.for.search" /></div> 
</div></div>
</div>
<div class="row margin">
<div class="input-field col s12">
<div id="divfirstname" class="row">
<div class="input-field col m12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="merchantobj.keyForSearch" /> </span></div>
</div></div>
</div>
<div class="row margin">
	<div class="input-field col s12">
		<div id="divfirstname" class="row">
		<div class="input-field col m12" ><s:text name="Description" /></div>
		</div>
	</div>																					
</div>
<div class="row margin">
	<div class="input-field col s12">
		<div id="divfirstname" class="row">
		 <div class="input-field col m12 breakword"> <span class="<s:text name="view.fontvalue.color" />"><s:property value="merchantobj.merchDescription" /> </span></div>
		</div>
	</div>																					
</div>
    <div class="row margin">
	<div class="input-field col s12">
		<div id="divfirstname" class="row">
		<div class="input-field col m12"><s:text name="Issue List" /> </div> 
		</div>
	</div>																					
</div>
	<div class="row margin">
										<div class="input-field col s12">
													<div id="divfirstname" class="row">
													<div class="input-field col m12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="issuetext" /> </span></div>
												</div>
										</div> 
										<%--  <s:iterator value="issList" status="count">
 												<div id="TextBoxDiv<s:property value="#count.Index+1"/>" >
 											<div class="row">
													<div class="input-field col m3"><s:text name="Issue List" />  </div> 
													<div class="input-field col m9 breakword"><span class="<s:text name="view.fontvalue.color" />"> :<s:property value="%{issueDesc}" /> </span></div>
												
  											</div>	 
											</div>
											</s:iterator> --%>
</div>
</div>
</li>
	<s:if test="merchantobj.merchantCategoryId==1">
		<li id="featuersliid" style="display: block;">
		<div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-av-album tinysmall white-text text-accent-4'></i> <s:text name="Text.sports.detail" /> </div>
		<div class="collapsible-body padding10px" id="thirdivid">
        <!-- <div class="clear height10px"></div> <div> <div class="input-field col m5"> <b>Sports Detail </b> </div> </div> <div class="clear height5px"></div> -->
				<div class="padding10px">
				<s:iterator value="merchantStockobjList" status="famcount">
												<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5"><s:text
																	name="Text.merchant.sports" /></div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="typeName" /> </span></div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5"><s:text
																	name="Text.merchant.quantity" /></div> <div
																class="input-field col m7"> :  <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="quantity" /> </span></div>
														</div>
													</div>
												</div>
			</s:iterator></div>
			</div></li>
		</s:if>
		<s:elseif test="merchantobj.merchantCategoryId==2">
		<li id="featuersliid" style="display: block;">
		<div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-maps-local-hospital tinysmall white-text text-accent-4'></i> <s:text name="Text.pharmacy.detail" /></div>
		<div class="collapsible-body padding10px" id="thirdivid">
		<!-- <div class="clear height10px"></div> <div class="input-field col m5"> <b>Pharmacy Detail </b> </div> <div class="clear height5px"></div> -->
			<div class="padding10px">
			<s:iterator value="merchantStockobjList" status="famcount">
											<div  class="borderradius">
												<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" >Medicine</div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="typeName" /> </span></div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5">Power</div>  <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="power" /> </span></div>
														</div>
													</div>
												</div>
													<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" >Company</div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="companyName" /> </span></div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5"><s:text
																	name="Text.merchant.quantity" /></div>  <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="quantity" /> </span></div>
														</div>
													</div>
												</div>
												</div>
			<div class="clear" style="height: 10px;"></div>
			</s:iterator></div>
			</div></li>
			</s:elseif>
			<s:elseif test="merchantobj.merchantCategoryId==3">
			<li id="featuersliid" style="display: block;">
		    <div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-social-whatshot tinysmall white-text text-accent-4'></i> <s:text name="Text.jewellery.detail" /></div>
		    <div class="collapsible-body padding10px" id="thirdivid">
					<!-- <div class="clear height10px"></div> <div class="input-field col m5"> <b>Jewellery Detail </b> </div> <div class="clear height5px"></div> -->
					<div class="padding10px">
					<s:iterator value="merchantStockobjList" status="famcount">
										<div  class="borderradius">
													<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" ><s:text name="Text.merchant.jewellery" /></div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="typeName" /> </span></div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5"><s:text
																	name="Text.merchant.quantity" /></div> <div
																class="input-field col m7"> :  <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="quantity" /> </span></div>
														</div>
													</div>
												</div>
												</div>
												<div class="clear" style="height: 10px;"></div>
			</s:iterator></div>
			</div></li>
		</s:elseif>
		<s:elseif test="merchantobj.merchantCategoryId==4">
		<li id="featuersliid" style="display: block;">
		<div class="collapsible-header <s:text name="collapsible.header.color" /> active"  style="color:#fff;" id="thirdivtitid"><i class='mdi-maps-local-restaurant tinysmall white-text text-accent-4'></i> <s:text name="Text.restaurant.detail" /></div>
		<div class="collapsible-body padding10px" id="thirdivid">
			<!-- <div class="clear height10px"></div> <div class="input-field col m5"> <b>Restaurant Detail </b> </div> <div class="clear height5px"></div> -->
			<div  class="borderradius padding10px">
													<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" >Cuisines</div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.cuisines" /> </span></div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5">Break Fast</div>  <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.breakFastavl" /> </span></div>
														</div>
													</div>
												</div>
												<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" >Lunch</div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.lunchavl" /> </span></div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5">Dinner</div> <div
																class="input-field col m7"> :  <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.dinneravl" /> </span></div>
														</div>
													</div>
												</div>
													<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" >Indoor</div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.indooravl" /> </span>
																	</div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5">Delivery</div>  <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.deliveryavl" /> </span>
																	</div>
														</div>
													</div>
												</div>
													<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" >Take-Away</div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.takeAwayavl" /> </span>
																	</div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5">Cafe</div>  <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.cafeavl" /> </span>
																	</div>
														</div>
													</div>
												</div>
												<div class="row margin">
													<div class="input-field col s6">
														<div class="row">
															<div class="input-field col m5" >Luxury Dining</div> <div
																class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.luxuryDiningavl" /> </span>
																	</div>
														</div>
													</div>
													<div class="input-field col s6">
														<div class="row" id="divemailId">
															<div class="input-field col m5">Nightlife</div> <div
																class="input-field col m7"> :  <span class="<s:text name="view.fontvalue.color" />"><s:property
																	value="merchantcatdetobj.nightlifeavl" /> </span>
																	</div>
														</div>
													</div>
												</div>
		</div><div class="clear" style="height: 10px;"></div>
		</div></li>
</s:elseif>
</ul>
</div>
</div>
</section>									
</div>
<s:hidden name="merchantid" ></s:hidden>
<s:form method="post" id="userCancelForm"></s:form>
</div>					
<jsp:include page="../common/footer.jsp"></jsp:include>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
<jsp:include page="../common/clorbox.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
	});	   
});

$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "merchantMgmtTbl");
		$("#userCancelForm").submit();
	});	   
});
</script></body></html>
