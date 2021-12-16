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
						<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.residentdetails" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="#"><s:text name="Menuheader.uam.profile" /></a></li>								
								<li class="active"><s:text name="Breadcrumb.manage.residentdetails" /></li>

							</ol>
							<div class="right">
							</div>
					</div>
				</div>							
				</div></div>
<div class="container" >
<div id="jqueryvalidation" class="section">
<div class="card-panel">

<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">
<!-- <div class="input-field col s12"> <div class="input-field col s5"><b class="">Basic Details : </b></div> </div>	 <div class="clear height10px"> </div> -->
<div class="row"  style="margin:10px">
<div class="left resilaborview col s3">
	<table class="resilabortbl" align="center">
		<tbody>
		<tr>
		<s:if test="restRegObj.userImage!=null && restRegObj.userImage!='' && restRegObj.userImage!='null'">
			<td><img id="rsdimgid" class="lbrimg hoverable pointer" style="display: block;" src='/templogo/profile/mobile/<s:property value="restRegObj.userId"/>/<s:property value="restRegObj.userImage"/>' onclick="toViewlargesizeimgwithsrc(this,'/templogo/profile/web/<s:property value="restRegObj.userId"/>/<s:property value="restRegObj.userImage"/>')"></td>
		</s:if>
		<s:else>
			<td><img class="lbrimg hoverable pointer" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" id="defaulidd" style="width:100%;"  onclick="toViewlargesizeimg(this)"></td>
		</s:else>
			</tr>
		</tbody>
	</table>										
</div>
<div class="col s9">		
		<div class="row"><div class="col s12 m12 l12">
		<div class="col s6">
  			<div class="row form-group breakword" id="divfirstname">
			<div class="input-field col s5"><s:text name="Text.adduser.town.ship.id" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.townshipName"/></span></div>														 																														
			</div>
		</div>
			
			<div class="col s6">
  			<div class="row form-group breakword">
  				<div class="input-field col s5"><s:text name="Text.adduser.society.id" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.societyName"/> </span></div>
			</div>
		</div>														 
 		</div></div>
 		
 		<div class="row"><div class="col s12 m12 l12">
	<div class="col s6 input-field">
  			<div class="row form-group breakword" id="divfirstname">
  				<div class="input-field col s5"><s:text name="uam.profile.fname" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.firstName"/></span></div>
			</div>
		</div>
		<div class="col s6 input-field">
  			<div class="row form-group breakword">
  				<div class="input-field col s5"><s:text name="uam.profile.lname" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.lastName"/> </span></div>
			</div>
		</div>			
	</div></div>
	<div class="row"><div class="col s12 m12 l12">
	<div class="col s6 input-field">
  			<div class="row form-group breakword" id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.customerreg.idproof" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="restRegObj.idCardTypeName"/></span></div>
			</div>
		</div>
		<div class="col s6 input-field">
  			<div class="row form-group breakword">
  				<div class="input-field col s5"><s:text name="Text.customerreg.idproofno" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.idProofNo"/></span></div>
			</div>
		</div></div>			
	</div>
	<div class="col s6 input-field">
	 <div class="row">
                  <div class="input-field col m5"><s:text name="Text.mobileno" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.IsdCode"/>-<s:property value="restRegObj.MobileNo"/></span></div>
                   </div></div>
	</div></div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-communication-business tinysmall white-text text-accent-4"></i> <s:text name="Text.block.detail" /></div>                    
<div class="collapsible-body padding10px" id="seconddivid">
<!-- <div class="input-field col s12"><div class="input-field col s5"> <b class="">Block Details : </b></div> </div> -->							 
	<div class="row"><div class="col s12 m12 l12">
  		<div class="input-field  col s6">
  			<div class="row form-group breakword" id="divfirstname">
			<div class="input-field col s5"><s:text name="Text.resident.NoofBlock/Wings" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="restRegObj.noOfBlocks"/> </span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group breakword" id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.resident.noofflats" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.noofFlats"/></span></div>
			</div>
		</div>
</div></div>

<s:if test="restRegObj.noofFlats.equalsIgnoreCase('0')">
 						</s:if>
 						<s:else>
 						<s:iterator value="FlatList">
 						<div class="row"><div class="col s12 m12 l12">
  		<div class="input-field  col s6">
  			<div class="row form-group " id="divfirstname">
			<div class="input-field col s5"><s:text name="Text.resident.block" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="wingsname"/></span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group breakword" id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.resident.flatno" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="flatno"/></span></div>
			</div>
		</div>
			</div></div>
 			</s:iterator> 
 				</s:else>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>                    
<div class="collapsible-body padding10px" id="thirddivid">
						
<!-- <div class="clear height10px" ></div><div class="input-field col s12"><div class="input-field col s5"> <b class="">Other Details : </b></div></div> -->		
<div class="row"><div class="col s12 m12 l12">
  		<div class="input-field  col s6">
  			<div class="row form-group breakword" id="divfirstname">
			<div class="input-field col s5"><s:text name="Text.customerreg.gender" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.gender_txt"/></span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group breakword" id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.customerreg.dob" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.dob"/></span></div>
			</div>
		</div>
</div></div>

		<div class="row"><div class="col s12 m12 l12">
  		<div class="input-field  col s6">
  			<div class="row form-group breakword" id="divfirstname">
			<div class="input-field col s5"><s:text name="uam.profile.address1" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.address1"/></span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group breakword" id="divfirstname">
  				<div class="input-field col s5"><s:text name="uam.profile.address2" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.address2"/></span></div>
			</div>
		</div>
</div></div>

<div class="row"><div class="col s12 m12 l12">
  		<div class="input-field  col s6">
  			<div class="row form-group " id="divfirstname">
			<div class="input-field col s5"><s:text name="Menuheader.uam.profile.Blood.group" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.bloodType"/></span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group " id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.country" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.countryname"/></span></div>
			</div>
		</div>
</div></div>

<div class="row"><div class="col s12 m12 l12">
  		<div class="input-field  col s6">
  			<div class="row form-group " id="divfirstname">
			<div class="input-field col s5"><s:text name="Text.state" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.statename"/></span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group " id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.city" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.cityname"/></span></div>
			</div>
		</div>
</div></div>

<div class="row"><div class="col s12 m12 l12">
  		<div class="input-field  col s6">
  			<div class="row form-group " id="divfirstname">
			<div class="input-field col s5"><s:text name="Text.pincode" /></div>
			<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.pstlcodename"/></span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group " id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.resident.occupation" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.occupation"/></span></div>
			</div>
		</div>
</div></div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-social-people tinysmall white-text text-accent-4"></i> Family Detail</div>                    
<div class="collapsible-body padding10px" id="fourthdivid">
<!-- <div class="clear height10px" ></div><div class="input-field col s12"><div class="input-field col s5"> <b class="">Family Details : </b></div></div> -->		                  
        <div class="row"><div class="col s12 m12 l12">
        <div class="input-field col s6">
  			<div class="row form-group breakword" id="divfirstname">
  				<div class="input-field col s5"><s:text name="Text.resident.familymember" /></div>
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.membersInFamily"/></span></div>
			</div>
		</div>
  	</div>	
   		
</div>       	
   		  <s:iterator value="userFamilyList">
   		   <div class="card white darken-5">
                    <div class="row" ><div class="col s12 m12 l12">
                          <div class="input-field col m6">
                           <div class="row">
                  <div class="input-field col m5"><s:text name="Name"/></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="fmbrName"/></span></div>
                      </div></div>
                       <div class="input-field col m6">
                        <div class="row">
                  <div class="input-field col m5"><s:text name="Text.mobileno" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="fmbrISD"/>-<s:property value="fmbrPhNo"/></span></div>
                   </div> </div>
                    </div></div>
                        <div class="row" >
                        <div class="col s12 m12 l12">
                          <div class="input-field col m6">
                           <div class="row">
                  <div class="input-field col m5"><s:text name="Text.emailid" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="fmbrEmail"/></span></div>
                    </div> </div>
                      <s:if test="fmbrMtype.equalsIgnoreCase('1')">
                         <div class="input-field col m6">
                          <div class="row">
                  <div class="input-field col m5">Member Type</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Relation </span></div>
                     </div> </div>
                      </s:if>
                      <s:else>
                       <div class="input-field col m6">
                        <div class="row" >
                  <div class="input-field col m5">Member Type</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Tenent</span></div>
                    </div></div>
                    </s:else>
                    </div></div>
                         <div class="row" ><div class="col s12 m12 l12">
                      <s:if test="fmbrPrfAccess.equalsIgnoreCase('1')">
                         <div class="input-field col m6">
                          <div class="row">
                  <div class="input-field col m5">Profile Access</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Allow </span></div>
                     </div> </div>
                      </s:if>
                      <s:else>
                       <div class="input-field col m6">
                        <div class="row">
                  <div class="input-field col m5">Profile Access</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Not Allow</span></div>
                    </div></div>
                    </s:else>
                    </div></div>
                    </div>
               </s:iterator>
</div>
</li>
</ul>
</div>                                                              										 															 																																								
</div>
<div class="clear height5px"></div>
</div>										
</section>	
</div>
</div>	
<s:form method="post" id="userCancelForm"></s:form>																						
<jsp:include page="../common/footer.jsp"></jsp:include>
<jsp:include page="../common/clorbox.jsp"></jsp:include>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
</body>
</html>