 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
	<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<!-- For Windows Phone -->
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
					
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="sms.config" /></h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.util.configmgmt" /></a></li>
									<li class="active"><s:text name="sms.config" /></li>
								</ol>
							</div>
						</div>
					</div></div>
				<!--breadcrumbs end-->


				<!--start container-->
				<div class="container">
				 <div id="jqueryvalidation" class="section">
				<div class="card-panel">
				<div id="card-alert" class="card success" style="width: 98%;margin-left: 1%">
                              <div class="card-content white-text" id="emailupdate"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                    Sms Configuration Change Started.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                    <div class="card-content white-text" id="emailengstart"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                      Sms engine has started.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                    <div class="card-content white-text" id="emailengstop"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                      Sms engine has stop.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                    <div class="card-content white-text" id="error"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                     Error.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                   </div>
						<div style="clear: both; height: 5px;"></div>
										<form  id="userCreationFormId" action="smsUpdated" method="post">																	
											<div class="row">	
											 <div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.httpurl"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber" name="httpurl" 
												id="httpurl" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>	
											<div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.httploginid" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber" name="httploginid" 
												id="httploginid" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
											</div>
											</div>												
											<div class="row">	
											 <div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.httppass"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control address" name="httppass" 
												id="httphost"></s:textfield>
												<s:fielderror fieldName="usercreateObj.address"/>
											</div>	
											<div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.CDMAName"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control addresses" name="cdmaname" 
												id="cdmaname" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.addresses"/>
											</div>	
											</div>
												<div class="row">	
											 <div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.SenderName"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control address" name="senderName" 
												id="senderName"></s:textfield>
												<s:fielderror fieldName="usercreateObj.address"/>
											</div>	
											<div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.ProviderName"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control addresses" name="providerName" 
												id="providerName" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.addresses"/>
											</div>	
											</div>
											<div class="row">	
											 <div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.FetchRow"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control address" name="fetchRow" 
												id="fetchRow" readonly="true"></s:textfield>
												<s:fielderror fieldName="usercreateObj.address"/>
											</div>	
											<div class="input-field col s6">
												<label class=" control-label"><s:text name="sms.config.BulkFetch"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control addresses" name="bulkFetch" 
												id="bulkFetch" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.addresses"/> 
											</div>	
											</div>
										
												 </form>
										 <div style="clear: both; height: 10px;"></div>
						          <div style="margin-left: 0px;">
						                           <button type="submit" id="userCreateButtonId" 
								                   class="<s:text name="button.color.update"/>" onclick="emailupdatefunction()">
								                   <s:text name="Text.button.modify" />
								                      <i class="<s:text name="button.icon.replycard"/>"></i>
							                         </button>
						                           <button type="submit" id="userCancelButtonId"
							                         class="<s:text name="button.color.emailstart"/>"
							                              onclick="smsengstartfunction()">
							                       <s:text name="Text.button.startengine" />
							                        <i class="<s:text name="button.icon.submitcard"/>"></i>
							                      
						                                 </button>
						                                  <button type="submit" id="userCreateButtonId" 
								                   class="<s:text name="button.color.emailstop"/>" onclick="smsengstopfunction()">
								                   <s:text name="Text.button.stopengine" />
								                     <i class="<s:text name="button.icon.submitcard"/>"></i>
							                         </button>							                         				                  
				                                  </div> <div style="clear: both; height: 30px;"></div>
										<s:form method="post" id="userCancelForm"></s:form>
										
									</div>
								</div>
						</div>											
						</section>
						</div></div>										
		<jsp:include page="../common/footer.jsp"></jsp:include>
	    <jsp:include page="../common/allbasicjs.jsp"></jsp:include>	 
	    </body>
<script>
function emailupdatefunction() {
	 toShowLoadingImgoverlay();
	 var httpurl=$('#httpurl').val();
	 if(httpurl!=null && httpurl!="undefined"){
		 httpurl=httpurl;
	 }else{
		 httpurl="";
	 }
	 var httploginid=$('#httploginid').val();
	 if(httploginid!=null && httploginid!="undefined"){
		 httploginid=httploginid;
	 }else{
		 httploginid="";
	 }
	 var httppass=$('#httppass').val();
	 if(httppass!=null && httppass!="undefined"){
		 httppass=httppass;
	 }else{
		 httppass="";
	 }
	 var cdmaname=$('#cdmaname').val();
	 if(cdmaname!=null && cdmaname!="undefined"){
		 cdmaname=cdmaname;
	 }else{
		 cdmaname="";
	 }
	 var senderName=$('#senderName').val();
	 if(senderName!=null && senderName!="undefined"){
		 senderName=senderName;
	 }else{
		 senderName="";
	 }
	 var providerName=$('#providerName').val();
	 if(providerName!=null && providerName!="undefined"){
		 providerName=providerName;
	 }else{
		 providerName="";
	 }
	 var fetchRow=$('#fetchRow').val();
	 var bulkFetch=$('#bulkFetch').val();
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'smsUpdated',
			data : "httpurl= "+httpurl+"&httploginid="+httploginid+"&httppass="+httppass+"&cdmaname="+cdmaname+"&senderName="+senderName+"&providerName="+providerName+"&fetchRow="+fetchRow+"&bulkFetch="+bulkFetch+"",
			success : function(data) {
			var alt=data.split("!_!");
			if(alt[1]=='0'){
				 tohideLoadingImgoverlay(); 
				$('#alertdiv2').show();
				$('#emailupdate').show();
				$('#emailengstart').hide();
				$('#emailengstop').hide();
			}else{
				tohideLoadingImgoverlay();
				$('#alertdiv2').show();
				$('#error').show();
				$('#emailupdate').hide();
				$('#emailengstart').hide();
				$('#emailengstop').hide();
			}
			}
		}); 
} 

 function smsengstartfunction() {
	 toShowLoadingImgoverlay();
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'smsEnginStart',
			data : "",
			success : function(data) {
			var alt=data.split("!_!");
			if(alt[1]=='0'){
				tohideLoadingImgoverlay()
				$('#alertdiv2').show();
				$('#emailengstart').show();
				$('#emailengstop').hide();
				$('#emailupdate').hide();
			}else{
				tohideLoadingImgoverlay()
				$('#emailengstart').hide();
				$('#emailengstop').hide();
				$('#emailupdate').hide();
				$('#alertdiv2').show();
				$('#error').show();
			}
			}
		}); 
	}
 function smsengstopfunction() {
	 toShowLoadingImgoverlay();
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'smsEnginStop',
			data : "",
			success : function(data) {
			var alt=data.split("!_!");
			if(alt[1]=='0'){
				tohideLoadingImgoverlay()
				$('#alertdiv2').show();
				$('#emailengstart').hide();
				$('#emailengstop').show();
				$('#emailupdate').hide();
			}else{
				tohideLoadingImgoverlay()
				$('#emailengstart').hide();
				$('#emailengstop').hide();
				$('#emailupdate').hide();
				$('#alertdiv2').show();
				$('#error').show();
			}
			}
		}); 
	}
 
 $('#userCreationFormId').validate({
   	 errorElement : 'div',
   		 errorPlacement : function(error, element) {
   			var placement = $(element).data('error');
   			if (placement) {
   				$(placement).append(error);
   			} else {
   				error.insertAfter(element);
   			}
   		},
   		submitHandler : function(form) {
   			return true;
   			//toShowLoadingImgoverlay();
   		} 
   	});
   	$("#httpurl").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
   	$("#httploginid").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
   	$("#httphost").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
   	$("#cdmaname").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
   	$("#senderName").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
   	$("#providerName").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
	$("#fetchRow").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
   	$("#bulkFetch").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.value.title" />",
   		}
   		
   	});
	function home(){
		$("#userCancelForm").attr("action", "posthomepage");
		$("#userCancelForm").submit();
}

</script>
</html>
 
  