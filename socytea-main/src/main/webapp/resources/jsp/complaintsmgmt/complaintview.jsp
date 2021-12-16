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
<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css" />
<style>
#token-input-labornames
{
width: 100% ! important;
}
</style>
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
						<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.cmpltdetail" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="complaintsmgmt"><s:text name="Breadcrumb.utility" /></a></li>
								<li><a href="complaintsmgmt"><s:text name="Breadcrumb.manage.complaintmgmt" /></a></li>
							<li class="active"><s:text name="Breadcrumb.manage.cmpltdetail" /></li>
							</ol>
							
							<div class="right">
							<button id="gobckbtnid" class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">Go Back
                        <i class="<s:text name="button.icon.replycard"/>"></i>
                      </button>
							</div>
					</div>
				</div>						
				</div></div>
				<div class="container">
				<div id="alertmsgsuccess" class="card success" style="display: none;width: 98%;margin-left: 1%;" onclick="hideFunction();">
				<div class="card-content white-text">
                        <p><i class="mdi-navigation-check"></i> Complaint Shared Successfully.</p>
                      </div></div>	
								<div id="alertmsgerror" class="card error" style="display: none;width: 98%;margin-left: 1%;" onclick="hideFunction();">
  									<div class="card-content white-text">
                        <p><i class="mdi-navigation-check"></i> Shared Error.</p>
                      </div>	
								</div>	
				<div class="card-panel">
										<div class="row">
											<div class="input-field col s6">
											<div class="row">
											 <s:if test="cmpltRegObj.grpname.equalsIgnoreCase('Resident')">
											      <div class="input-field col m5" ><s:text name="Text.name" /></div> 
											      <div class="input-field col m7 breakword"> : 
  														  <a target="_blank" href="viewresidentdetails?deleteresidentid=<s:property value="cmpltfrmusrid"/>">
  														 <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpltRegObj.complaintName" /> (<s:property value="cmpltRegObj.grpname" />) </span>
  												  </a></div>
  											</s:if>
  											<s:elseif test="cmpltRegObj.grpname.equalsIgnoreCase('Committee')">
  												<div class="input-field col m5" ><s:text name="Text.name" /></div> 
  												<div class="input-field col m7 breakword"> :  <a target="_blank" href="viewresidentdetails?deleteresidentid=<s:property value="cmpltfrmusrid"/>">
  												<span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpltRegObj.complaintName" /> (<s:property value="cmpltRegObj.grpname" />) </span>
  												</a></div>
  											 </s:elseif>
											<s:else>
												<div class="input-field col m5" ><s:text name="Text.name" /></div> 
  												<div class="input-field col m7 breakword"> :  <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpltRegObj.complaintName" /></span></div>															
											</s:else>	
													
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
												  	<!--<s:if test="cmpltRegObj.cmpltfromgrpname.equalsIgnoreCase('Resident')">
													<div class="input-field col m5"><s:text name="Text.complaintto" /></div> 
													<div class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> Committee</span></div> 
													</s:if>
													<s:else>
													<div class="input-field col m5"><s:text name="Text.complaintto" /></div> 
													<div class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpltRegObj.complaintsOthersEmail"/></span></div> 
													</s:else> -->
													<div class="input-field col m5"><s:text name="Text.complaintto" /></div> 
													<div class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="cmpltRegObj.cmpltfromgrpname"/></span></div> 
												</div>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5"><s:text
															name="Text.title" /></div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpltRegObj.complaintsTitle"/></span></div> 
												</div>
											</div>
											<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" >Date</div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="cmpltRegObj.entryDatetime"/></span></div>
												</div>
											</div>
										</div>
					<div class="row">
							<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" ><s:text name="text.complantimage" /> </div> 
												<div class="input-field col m7 breakword">
												<div class="left"> :&nbsp;</div>	
												<div class="left">														
													<s:iterator value="Imglist">														
														<img class="tndrimg hoverable margin tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Click to view" id="imgid" src="/templogo/complaint/mobile/<s:property value="cmpltRegObj.complaintsId"/>/<s:property value="ivrBnATTACH_NAME"/>" onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/complaint/web/<s:property value="cmpltRegObj.complaintsId"/>/<s:property value="ivrBnATTACH_NAME"/>')">															
													</s:iterator>
											    </div>
												</div>
												</div>
							</div>	
							<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" >Resolve Date</div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="cmpltRegObj.ModifyDatetime"/></span></div>
												</div>
											</div>			
				</div>
			<div class="row">
				<div class="input-field col s6">
					<div id="divfirstname" class="row">
					<div class="input-field col m5" style="font-weight: normal;" ><s:text name="text.video" /></div> 					
					<div class="input-field col m7 breakword">
												<div class="left"> :&nbsp;</div>	
												<div class="left">														
													<s:iterator value="videofileatch">														
														 <img class="tndrimg hoverable margin tooltipped" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Click to view" id="imgid" src="/templogo/complaint/thumbimage/<s:property value="cmpltRegObj.complaintsId"/>/<s:property value="thumbImage"/>" onclick="toViewlargesizeVideowithsrc(this.id,'/templogo/complaint/videos/<s:property value="cmpltRegObj.complaintsId"/>/<s:property value="ivrBnATTACH_NAME"/>')">																											
														<!-- <embed id="emdid" class="tndrimg hoverable margin" width="100" height="100" src="/templogo/complaint/mobile/<s:property value="cmpltRegObj.complaintsId"/>/<s:property value="ivrBnATTACH_NAME"/>" onclick="toViewlargesizeVideowithsrc(this.id,'/templogo/complaint/web/<s:property value="cmpltRegObj.complaintsId"/>/<s:property value="ivrBnATTACH_NAME"/>')">-->													
													</s:iterator>																									
													<!-- <img class="tndrimg hoverable margin" id="vido01s" src="https://www.youtube.com/embed/XGSy3_Czz8k?autoplay=1" onclick="toViewlargesizeVideowithsrc(this.id,'https://www.youtube.com/embed/XGSy3_Czz8k?autoplay=1')"><embed id="emdid" class="tndrimg hoverable margin" width="100" height="100" src="/assets_tutorials/media/Shaun-the-Sheep-The-Movie-Official-Trailer.ogg" onclick="toViewlargesizeVideowithsrc(this.id,'/assets_tutorials/media/Shaun-the-Sheep-The-Movie-Official-Trailer.ogg>')"> -->
											    </div>
												</div>
					
					<!-- <div class="input-field col m7"> : <a class="" target="_blank" href="#" >	<span class="<s:text name="view.fontvalue.color" />"></span> </a></div> -->
					</div>
				</div>	
				<div class="input-field col s6">
												<div id="divfirstname" class="row">
													<div class="input-field col m5" >Status</div> <div
														class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="statusflgstr"/></span></div>
												</div>
											</div>									
			</div>
			<div class="row">
				<div class="input-field col s12">
					<div id="divfirstname" class="row">
						<div class="input-field col m5" style="width: 20.7%;font-weight: normal;"><s:text name="Text.complaintshtdesc" /></div> 
						<div class="input-field col m7 breakword"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property value="cmpltRegObj.shrtdesc"/></span></div>
					</div>
				</div>																						
			</div>
									<div class="row">
									<div class="input-field col s12">
										<div id="divfirstname" class="row">
											<div class="input-field col m12"  style="font-weight: normal;"><s:text name="Text.desc" /></div>											
											</div>
										</div>
									</div>
									
									<div class="row">
									<div class="input-field col s12">
										<div id="divfirstname" class="row">											
											<div class="col m12 breakword"><span class="<s:text name="view.fontvalue.color" />"><s:property value="cmpltRegObj.complaintsDesc"/></span></div>
											</div>
										</div>
									</div>
									
				<s:if test="cmpltRegObj.cmpltfromgrpname.equalsIgnoreCase('Resident')">
				<div class="row">
					<div class="input-field col s12">
					<div id="divfirstname" class="row">
					<div class="input-field col m2" ><s:text name="text.forwardto" /></div>
					<div class="input-field col m7">  
						<s:textfield name="labornames" id="labornames"  cssClass="form-control typeahead tt-query labornames"  autocomplete="off" />
					</div>		
					<div class="input-field col m3"><button  value="Close" name="submitbtn" id="submitbtn" class="left <s:text name="button.color.submit"/>" type="button" onclick="Laborsendemail();">Send<i class="<s:text name="button.icon.submitcard"/>"></i></button>																									
								<input type="hidden"  id="labornames_hidd" class="form-control"/>																		
								<s:hidden name="documentMng.docShareId" id="sharenames" value=""></s:hidden>								
					</div>									
					</div>
					</div>
				</div>
				</s:if>
										
					<div class="clear height5px"></div>										
					</div>
												<div class="clear" style="height: 10px;"></div>
										
										</div>										
										</section>									
										</div>
										<s:form method="post" id="userCancelForm">
										<s:hidden name="cmpltRegObj.complaintsId" id="cmpltuniqid" ></s:hidden>
										</s:form>										
									</div>					
			<jsp:include page="../common/footer.jsp"></jsp:include>
			<jsp:include page="../common/clorbox.jsp"></jsp:include>
			<jsp:include page="../common/allbasicjs.jsp"></jsp:include>		
			<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script> 
</body>
<script>
$(document).ready(function(){	
	 toLoadAutoComplate();
});

function toLoadAutoComplate(){
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getlabordetail',
			data : '',
			success : function(data) {
			if(data!=""){
			data=data.replace(/&quot;/ig,'"');
			data=data.replace(/%27/ig,"'");
			var loc_state_val = JSON.parse(data);
			$("#labornames").tokenInput(loc_state_val, {
              propertyToSearch: "searchkey",
              resultsFormatter: function(item){ return "<li>" + "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.firstName + "<br><div> " +item.labmobno +"</div></div></li>" },
              tokenFormatter: function(item) { return "<li> <p id='"+item.searchvalue+"'>" + item.firstName + "<br>" +item.labmobno + "</p> </li>"},
              theme: "facebook"});  

			// resultsFormatter: function(item){ return "<li style='height:51px'>" + "<img class='grptypimage' src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.fName +" "+item.lName+" "+item.flatNo+ "<br><div>" + item.mobilNum + "</div></div></div></li>" },
	            //tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
	           // theme: "facebook"}); 		

			/* $("#residentnames").tokenInput(loc_state_val, {
	                propertyToSearch: "searchkey",
	                resultsFormatter: function(item){ return "<li>" + "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.searchkey + "</div></div></li>" },
	                tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
	                theme: "facebook"});  
				$("#imgusernames").tokenInput(loc_state_val, {
	                propertyToSearch: "searchkey",
	                resultsFormatter: function(item){ return "<li>" + "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.searchkey + "</div></div></li>" },
	                tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
	                theme: "facebook"});  */

         	 $(".token-input-dropdown-facebook").css("z-index","260000");
	         $("ul.token-input-list-facebook").css("width","100%");
	         $("div.token-input-dropdown-facebook").css("width",$('ul.token-input-list-facebook').width());	
	         $("ul.token-input-list-facebook").css("min-height","0px");       		
			 $("ul.token-input-list-facebook").css("max-height","162px"); 
			 $("ul.token-input-list-facebook").css("height","auto");  
			 $("ul.token-input-list-facebook").css("overflow-y","auto");
			 $("ul.token-input-list-facebook").css("border-color","#C4C4C4");
			}
			}
		}); 
	 
}
function togetId(){
	 var idname="";
   $(".token-input-token-facebook p").each(function(x){// new autocomplete
       idname+=$(this).attr("id")+",";           
   });
   return idname;
}
function Laborsendemail(){
	var idnam=togetId();
	var cmpltid=$("#cmpltuniqid").val();
	if(idnam!=''){		
		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'invitecmpltaction',
			data : 'cmpltShareids='+idnam+"&cmpltid="+cmpltid,
			success : function(data) {
				var str=data;
			if((str.trim())=='0'){
				$('#alertmsgsuccess').show();
				
			}else if((str.trim())=='1'){
				$('#alertmsgerror').show();	
			}else{
				
			}
			
			}
		});
	}else{
		$("#userCreationFormId").attr("action","eventCreationform");
		$("#userCreationFormId").submit();
	}
}
function hideFunction(){
	$('#alertmsgsuccess').hide();
	$('#alertmsgerror').hide();	
}
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "complaintsmgmt");
		$("#userCancelForm").submit();
	});	   
});
</script>
</html>
