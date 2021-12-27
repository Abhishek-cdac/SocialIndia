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
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>
<body>
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<jsp:include page="../common/menuBar.jsp"></jsp:include>
	<section id="content">
				<!--breadcrumbs start-->
			<div id="breadcrumbs-wrapper">
					<!-- Search for small screen -->
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					 <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.residentdetails"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
				<li><a href="residentmgmt"><s:text name="Breadcrumb.manage" /></a></li>
				<li><a href="residentmgmt"><s:text name="Breadcrumb.manage.restmgmt" /></a></li>
                    <li class="active"><s:text name="Breadcrumb.manage.residentdetails" /></li>
                   </ol>
                  <div class="right">
                      <a href="residentmgmt"><button id="gobckbtnid"  class="<s:text name="button.color.cancel" />"  type="button"  data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back"><i class="<s:text name="button.icon.replycard"/>"></i>Go Back</button></a>
                </div>                               						
              </div>
            </div>
          </div>
	</div> <!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
					<div class="card-panel">
					<form id="residentCreationFormId" name="residentCreationFormId" action="societymanage" method="post" enctype="multipart/form-data">
					<!-- <div class="row"> <div class="input-field col m5"> <b><s:text name="Text.profile.detail" /></b></div></div> -->	
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">		
			<div class="row">
			<div class="col s12 m12 l12"> 
               <div class="input-field col m6">    
                 <div class="row">            
                  <div class="input-field col m5"><s:text name="Text.adduser.town.ship.id" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="restRegObj.townshipName"/></span></div> 
                   </div>                  
              </div>
               <div class="input-field col m6">   
                    <div class="row">         
                        <div class="input-field col m5"><s:text name="Text.adduser.society.id" /></div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.societyName"/></span></div>  					
                     </div>
               </div>
             </div>
			</div>
			<div class="row"><div class="col s12 m12 l12"> 
                <div class="input-field col m6">
                 <div class="row">
                  <div class="input-field col m5"><s:text name="uam.profile.fname" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.firstName"/></span></div>
                   </div>
                    </div>
                       <div class="input-field col m6">
                        <div class="row">
                  <div class="input-field col m5"><s:text name="uam.profile.lname" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.lastName"/></span></div>
                   </div>
                    </div>
			</div></div>
							
           <div class="row"><div class="col s12 m12 l12"> 
                         <div class="input-field col m6">
                          <div class="row">
                        <div class="input-field col m5"><s:text name="Text.customerreg.emailid" /></div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.emailId"/></span></div>
  						</div>
  						</div>
                <div class="input-field col m6">
                 <div class="row">
                  <div class="input-field col m5"><s:text name="Text.customerreg.mobileno" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.isdCode"/>-<s:property value="restRegObj.mobileNo"/></span></div>
                    </div>
                    </div> 
          </div></div> 
          <div class="row"><div class="col s12 m12 l12"> 
                      <div class="input-field col m6">
                       <div class="row">
                  <div class="input-field col m5"><s:text name="Text.customerreg.idproof" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.idCardTypeName"/></span></div>
                   
                   </div> </div>
                <div class="input-field col m6">
                 <div class="row">
                  <div class="input-field col m5"><s:text name="Text.customerreg.idproofno" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.idProofNo"/></span></div>
                    </div>
                    </div>
          </div></div>
       <div class="row"><div class="col s12 m12 l12"> 
                    
                    <div class="input-field col m6">
                     <div class="row">
                  <div class="input-field col m5"><s:text name="Text.customerreg.gender" /></div>
                  <s:if test="restRegObj.gender==1 || restRegObj.gender.equalsIgnoreCase('1')">
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Male</span></div>
                   </s:if> 
				<s:elseif  test="restRegObj.gender==2 || restRegObj.gender.equalsIgnoreCase('2')">
				<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Female</span></div>
				</s:elseif>
				<s:elseif  test="restRegObj.gender==3 || restRegObj.gender.equalsIgnoreCase('3')">
				<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Other</span></div>
				</s:elseif>
				<s:else>
				<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"></span></div>
				</s:else>
                      </div></div>

                      
                      
                <div class="input-field col m6">
                 <div class="row">
                  <div class="input-field col m5"><s:text name="Text.customerreg.dob" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="restRegObj.dob"/></span></div>
                    </div></div>
       </div></div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active"> <i class="mdi-communication-business tinysmall white-text text-accent-4"></i> <s:text name="Text.block.detail" /></div>                    
<div class="collapsible-body padding10px" id="seconddivid">
	<!--  	<div class="row input-field col s12"><div class="input-field col m5"> <b>Block Detail  </b></div></div>-->
    <div class="row">
    	<div class="col s12 m12 l12"> 
              <div class="input-field col m6" >
               <div class="row">
                  <div class="input-field col m5"><s:text name="Text.resident.NoofBlock/Wings" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.noOfBlocks"/></span></div>
                      </div></div>
                        <div class="input-field col m6">
                         <div class="row">
                  <div class="input-field col m5"><s:text name="Text.resident.noofflats" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.noofFlats"/></span></div>
                    </div>
    </div></div>
    </div>                    
    <s:if test="restRegObj.noofFlats.equalsIgnoreCase('0')">
 	</s:if>
 	<s:else>
 		<s:iterator value="FlatList"> 		
          <div class="row"><div class="col s12 m12 l12">
                          <div class="input-field col m6">
                          <div class="row">
                  <div class="input-field col m5"><s:text name="Text.resident.block" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="wingsname"/></span></div>
                     </div> </div>
                <div class="input-field col m6">
                <div class="row">
                  <div class="input-field col m5"><s:text name="Text.resident.flatno" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="flatno"/></span></div>
                    </div></div>
          </div></div>
       </s:iterator> 
 	</s:else>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>                    
<div class="collapsible-body padding10px" id="thirddivid" >
	<!--  <div class="row input-field col s12 "><div class="input-field col m5"> <b>Others Detail  </b></div></div>-->
     <div class="row"><div class="col s12 m12 l12">
                           <div class="input-field col m6">
                           <div class="row">
                  <div class="input-field col m5"><s:text name="uam.profile.address1" /></div>
                   <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.address1"/></span></div>
                   </div> </div>
                          <div class="input-field col m6">
                          <div class="row">
                  <div class="input-field col m5"><s:text name="uam.profile.address2" /></div>
                   <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.address2"/></span></div>
                      </div></div>
      </div></div>
      <div class="row"><div class="col s12 m12 l12">
                       <div class="input-field col m6">
                       <div class="row">
                  <div class="input-field col m5"><s:text name="Menuheader.uam.profile.Blood.group" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.bloodType"/></span></div>
                     </div> </div>
                <div class="input-field col m6">
                <div class="row">
                  <div class="input-field col m5"><s:text name="Text.country" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.countryname"/></span></div>
                   </div> </div>
      </div></div>
                    
      <div class="row"><div class="col s12 m12 l12">
                          <div class="input-field col m6">
                          <div class="row">
                  <div class="input-field col m5"><s:text name="Text.state" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.statename"/></span></div>
                     </div> </div>
                <div class="input-field col m6">
                <div class="row">
                  <div class="input-field col m5"><s:text name="Text.city" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.cityname"/></span></div>
                    </div></div>
      </div></div>
      <div class="row"><div class="col s12 m12 l12">
                          <div class="input-field col m6">
                          <div class="row">
                  <div class="input-field col m5"><s:text name="Text.pincode" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="restRegObj.pinCode"/></span></div>
                      </div></div>
                <div class="input-field col m6">
                <div class="row">
                  <div class="input-field col m5"><s:text name="Text.resident.occupation" /></div>
                   <div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="restRegObj.occupation"/></span></div>
                   </div> </div>
      </div></div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active"> <i class="mdi-social-people tinysmall white-text text-accent-4"></i> <s:text name="Text.family.detail" /></div>                    
<div class="collapsible-body padding10px" id="fourthdivid">
<!--    <div class="row input-field col s12"><div class="input-field col m5"> <b>Family Detail  </b></div></div> -->
<div class="row"><div class="col s12 m12 l12">
                          <div class="input-field col m6">
                          <div class="row">
                  <div class="input-field col m5"><s:text name="Text.resident.familymember" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="restRegObj.membersInFamily"/></span></div>
                      </div></div>
      </div></div>
      <div class="row input-field col s12"></div>
      <s:iterator value="userFamilyList">
      <div class="card white darken-5">
         <div class="row"><div class="col s12 m12 l12">
                          <div class="col m6">
                           <div class="row">
                  <div class="input-field col m5"><s:text name="Name"/></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="fmbrName"/></span></div>
                      </div></div>
                       <div class="col m6">
                        <div class="row">
                  <div class="input-field col m5"><s:text name="Text.mobileno" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="fmbrISD"/>-<s:property value="fmbrPhNo"/></span></div>
                   </div> </div>
         </div></div>
         <div class="row"><div class="col s12 m12 l12">
                  <div class="col m6">
                           <div class="row">
                  <div class="input-field col m5"><s:text name="Text.emailid" /></div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="fmbrEmail"/></span></div>
                    </div> </div>
                      <s:if test="fmbrMtype.equalsIgnoreCase('1')">
                         <div class="col m6">
                          <div class="row">
                  <div class="input-field col m5">Member Type</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Relation </span></div>
                     </div> </div>
                      </s:if>
                      <s:else>
                       <div class="col m6">
                        <div class="row" >
                  <div class="input-field col m5">Member Type</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Tenent</span></div>
                    </div></div>
                    </s:else>
  		</div></div>
        <div class="row"><div class="col s12 m12 l12">
                      <s:if test="fmbrPrfAccess.equalsIgnoreCase('1')">
                         <div class="col m6">
                          <div class="row">
                  <div class="input-field col m5">Profile Access</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Allow </span></div>
                     </div> </div>
                      </s:if>
                      <s:else>
                       <div class="col m6">
                        <div class="row">
                  <div class="input-field col m5">Profile Access</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">Not Allow</span></div>
                    </div></div>
                    </s:else>
        </div></div>
         <div class="row input-field col s12"></div>
        </div>       
     </s:iterator>
</div>
</li>
</ul>				
		</form>			
		<s:form method="post" id="userCancelForm"></s:form>
		</div>
		<!--end container-->
		</div>
		</section>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include><jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
</body>
<script>
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
	});	   
});
</script>
</html>

