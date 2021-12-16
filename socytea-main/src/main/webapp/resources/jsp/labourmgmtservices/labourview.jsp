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
<div class="container"> <div class="row"> <div class="col s12 m12 l12">		
		<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.labordetail" /></h5>
		<ol class="breadcrumbs left">
		<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
		<li><a href="labourmgmt"><s:text name="Breadcrumb.manage" /></a></li>								
 		<li ><a href="labourmgmt"><s:text name="Breadcrumb.manage.labormgmt" /></a></li>
		<li class="active"><s:text name="Breadcrumb.manage.labordetail" /></li>
		</ol>
		<div class="right"><button id="gobckbtnid" type="button" class="<s:text name="button.color.cancel" />"  data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back"><i class="<s:text name="button.icon.replycard"/>"></i>Go Back</button>
        </div> 
</div> </div> </div>
</div>	
<!--breadcrumbs end-->
<!--start container-->
<div class="container">
<div id="jqueryvalidation" class="section">
<div class="card-panel">	

<!-- <div class="input-field col s12"> <div class="input-field col s5"><b class="">Profile Detail</b></div></div> <div class="clear height10px"> </div> -->
<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> <s:text name="Text.profile.detail" /></div>
<div class="collapsible-body padding10px" id="firstdivid">		
<div class="row margin">
<div class="input-field left resilaborview col s3">
<div class="row"><div class=" col s12">
	<table class="resilabortbl" align="center">
		<tbody>
		<tr>
		<s:if test="#session.staffProfileImage!=null && #session.staffProfileImage!=''">
			<td><img class="lbrimg pointer hoverable" id="laborimgid" style="display: block;" src="/templogo/labor/mobile/<s:property value="#session.laborSessID"/>/<s:property value="#session.staffProfileImage"/>" onclick="toViewlargesizeimgwithsrc(this,'/templogo/labor/web/<s:property value="#session.laborSessID"/>/<s:property value="#session.staffProfileImage"/>')"></td>
		</s:if>
		<s:else>
			<td><img class="lbrimg pointer hoverable" src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" id="laborimgid" style="width:100%;" onclick="toViewlargesizeimg(this)"></td>
		</s:else>
			</tr>
		</tbody>
	</table>
	</div></div>									
</div>
<div class="input-field col s9">
	<div class="row">	
  		<div class="col s6">
  			<div class="row form-group " >
			<div class="input-field col s5"><s:text name="Text.name" /></div>
			<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnLBR_NAME"/></span></div>														 																														
			</div>
		</div>
   		<div class="col s6">
  		<div class="row form-group breakword">
		<div class="input-field col s5"><s:text name="Text.rating" /></div>			
		<s:if test="labRegObj.ivrBnLBR_RATING<=0">
			<div class="input-field col s7" style="margin-top:-10px;">
			: <i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<div style="margin-left: 16px;"><span class="<s:text name="view.fontvalue.color" />">Not Rating</span></div>
			</div>
		</s:if>
		<s:elseif test="labRegObj.ivrBnLBR_RATING>=1 && labRegObj.ivrBnLBR_RATING<2">
			<div class="input-field col s7 " style="margin-top:-10px;">
			: <i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<div style="margin-left: 16px;"><span class="<s:text name="view.fontvalue.color" />">Very Bad</span></div></div>
		</s:elseif>
		<s:elseif test="labRegObj.ivrBnLBR_RATING>=2 && labRegObj.ivrBnLBR_RATING<3">
			<div class="input-field col s7 " style="margin-top:-10px;">
			: <i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>			  
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<div style="margin-left: 16px;"><span class="<s:text name="view.fontvalue.color" />">Bad</span></div></div>
		</s:elseif>
		<s:elseif test="labRegObj.ivrBnLBR_RATING>=3 && labRegObj.ivrBnLBR_RATING<4">
			<div class="input-field col s7 ">
			:<i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			 <i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<div style="margin-left: 16px;"><span class="<s:text name="view.fontvalue.color" />">Not Good</span></div></div>
		</s:elseif>
		<s:elseif test="labRegObj.ivrBnLBR_RATING>=4 && labRegObj.ivrBnLBR_RATING<5">
			<div class="input-field col s7 " style="margin-top:-10px;">:<i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<div style="margin-left: 16px;"><span class="<s:text name="view.fontvalue.color" />">Good</span></div></div>
		</s:elseif>
		<s:elseif test="labRegObj.ivrBnLBR_RATING>=5">
			<div class="input-field col s7 " style="margin-top:-10px;">
			: <i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i><i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			  <i style="color: #ff528d" class="<s:text name="button.color.ratting"/>"></i>
			<div style="margin-left: 16px;"><span class="<s:text name="view.fontvalue.color" />">Excellent</span></div></div>
		</s:elseif>
		<s:else>
			<div class="input-field col m7 " style="margin-top:-10px;">:<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i><i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
			<i style="color: #dad5da" class="<s:text name="button.color.ratting"/>"></i>
		<div style="margin-left: 16px;"><span class="<s:text name="view.fontvalue.color" />">Not Rating</span></div></div>
		</s:else>						
		</div>														 																														
		</div>
	</div>
	
	<div class="row">
	<div class="col s6">
  			<div class="row form-group" >
  				<div class="input-field col s5"><s:text name="Text.emailid" /></div>
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnLBR_EMAIL"/></span></div>
			</div>
		</div>
		<div class="col s6">
  			<div class="row form-group">
  				<div class="input-field col s5"><s:text name="Text.mobileno"/></div>
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="labRegObj.ivrBnLBR_ISD_CODE"/>-<s:property value="labRegObj.ivrBnLBR_PH_NO"/> </span></div>
			</div>
		</div>			
	</div>
	<div class="row">
		<div class="input-field col m6">
		<div class="row form-group" >
			<div class="input-field col m5"><s:text name="Text.Companyname"/></div>
			<div class="input-field col m7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="laborcompanyname"/></span></div>
			</div></div>
		<div class="input-field col s6">
  			
  			<div class="row form-group " >
  				<div class="input-field col s5"><s:text name="Text.labor.experience" /></div>
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnLBR_EXPERIENCE"/> Year</span></div>
			</div>
			
		</div>			
	</div>
	<div class="row">
  		<div class="input-field  col s6">
  			<div class="row form-group" >
			<div class="input-field col s5"><s:text name="Text.labor.cost" /></div>
			<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnLBR_COST"/> </span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group " >
  				<div class="input-field col s5"><s:text name="Text.labor.costper" /></div>
  				
  				  <s:if test="labRegObj.ivrBnLBR_COSTPER==0 || labRegObj.ivrBnLBR_COSTPER.equalsIgnoreCase('0')">
					<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />">Hour</span></div>
				 </s:if>
  				
  				<s:elseif test="labRegObj.ivrBnLBR_COSTPER==1 || labRegObj.ivrBnLBR_COSTPER.equalsIgnoreCase('1')">
  				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />">Daily</span></div>											
				</s:elseif>
				<s:elseif test="labRegObj.ivrBnLBR_COSTPER==2 || labRegObj.ivrBnLBR_COSTPER.equalsIgnoreCase('2')">
				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />">Monthly</span></div>						
				</s:elseif>
				<s:else>
				<div class="input-field col s7">: <span class="<s:text name="view.fontvalue.color" />"></span></div>
				</s:else>	  				  				
			</div>
		</div>
</div>
	
 <div class="row">
        <div class="input-field col s6">
  			<div class="row form-group">
  				<div class="input-field col s5"><s:text name="Text.status" /></div>
  				<s:if test="labRegObj.ivrBnVERIFIED_STATUS==0 || labRegObj.ivrBnVERIFIED_STATUS.equalsIgnoreCase('0')">
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />">Unverified</span></div>
  				</s:if>
				<s:else>
				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />">Verified</span></div>
  				</s:else>  				  			
			</div>
		</div></div>
	<div class="clear height5px"></div>
	<div class="row">
  				
   		<div class="input-field col s6">
  			<div class="row form-group ">
  				<div class="input-field col s5"></div>
  				<div class="input-field col s7"> </div>
			</div>
		</div>				
	</div>
	
</div>
</div>
</div>
</li>
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-action-extension tinysmall white-text text-accent-4"></i> <s:text name="Text.other.detail" /></div>                    
<div class="collapsible-body padding10px" id="seconddivid" >
<!-- <div class="input-field col s12"> <div class="input-field col s5"> <b class="">Other Details</b></div> </div> -->	
<div class="row margin">
  		<div class="input-field  col s6">
  			<div class="row form-group" >
			<div class="input-field col s5"><s:text name="Text.customerreg.idproof" /></div>
			<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="labRegObj.ivrBnID_CARD_TYPNAME"/> </span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group" >
  				<div class="input-field col s5"><s:text name="Text.customerreg.idproofno" /></div>
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnID_CARD_NO"/></span></div>
			</div>
		</div>
</div>


<div class="row margin">
  		<div class="input-field  col s6">
  			<div class="row form-group " >
			<div class="input-field col s5"><s:text name="Text.address" /></div>
			<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnLBR_ADD_1"/>, <s:property value="labRegObj.ivrBnLBR_ADD_2"/> </span></div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group" >
  				<div class="input-field col s5"><s:text name="Text.city" /></div>
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.lbrcityName"/></span></div>
			</div>
		</div>
</div>
<div class="row margin">
		
		<div class="input-field  col s6">
  			<div class="row form-group" >
			<div class="input-field col s5"><s:text name="Text.state"/></div>
			<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.lbrstateName"/></span> </div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group" >
  				<div class="input-field col s5"><s:text name="Text.country" /></div>
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.lbrcntryName"/></span></div>
			</div>
		</div>
</div>
<div class="row margin">
		
		<div class="input-field  col s6">
  			<div class="row form-group " >
			<div class="input-field col s5"><s:text name="Text.customerreg.pincode" /></div>
			<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.lbrpincode"/></span> </div>														 																														
			</div>
  		</div>
   		<div class="input-field col s6">
  			<div class="row form-group " >
  				<div class="input-field col s5"><s:text name="Work Type" /></div>
  				<div class="input-field col s7 breakword">: <span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.wrktypname"/></span></div>
			</div>
		</div>
</div>   

<div class="row margin">
	<div class="input-field col s12">
		<div class="row form-group " >
			<div class="input-field col s3"><s:text name="Text.lbrcate.cateskills" /> </div>			
		</div>		
	</div>
</div>
<s:iterator value="CatskillsList"  >
<div class="row margin">
<div class="input-field col s12">
	<div class="row form-group " >
		<div class="input-field col s12 breakword"><span class="<s:text name="view.fontvalue.color" />"> <s:property value="skillslistname"/> - <s:property value="cat_skillslistname"/></span></div>
	</div>
	</div>
</div>
</s:iterator>
<div class="row margin">
	<div class="input-field col s12">
		<div class="row form-group breakword" >
			<div class="input-field col s3"><s:text name="Text.key.for.search" /> </div>			
		</div>		
	</div>
</div>
<div class="row margin">
<div class="input-field col s12">
	<div class="row " >
		<div class="col m12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnKEY_FOR_SEARCH"/></span></div>
	</div>
	</div>
</div>
 
                      <div class="row margin">
                             <div class="input-field col s12">
		                   <div class="row form-group " >
			                    <div class="input-field col s3"><s:text name="Issue List" /></div>			
		                              </div>		
	                              </div></div>
										<div class="row margin">
											<div class="input-field col s12">
											<div class="row form-group " >
													<div class="input-field col s12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="issuetext" />
														</span>
													</div></div>
												</div>
											</div>
										
<div class="row margin">
	<div class="input-field col s12">
		<div class="row form-group breakword" >
			<div class="input-field col s3"><s:text name="Text.describtion" /> </div>			
		</div>		
	</div>
</div>
<div class="row margin">
<div class="input-field col s12">
	<div class="row" >
		<div class="col m12 breakword "><span class="<s:text name="view.fontvalue.color" />"><s:property value="labRegObj.ivrBnLBR_DESCP"/></span></div>
	</div>
	</div>
</div>	
</div>
</li>
</ul>

</div></div></div>
<s:form method="post" id="userCancelForm"></s:form>
</section>
</div>
</div>		
<jsp:include page="../common/footer.jsp"></jsp:include>
<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
<jsp:include page="../common/clorbox.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "labourmgmt");
		$("#userCancelForm").submit();
	});	   
});
</script>
</body>
</html>
