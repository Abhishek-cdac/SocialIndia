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
								<h5 class="breadcrumbs-title"><s:text name="email.config" /></h5>
								<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.util.configmgmt" /></a></li>
									<li class="active"><s:text name="email.config" /></li>
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
                                     Email configuration changed and email engine started.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                    <div class="card-content white-text" id="emailengstart"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                      Email engine has started.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                    <div class="card-content white-text" id="emailengstop"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                      Email engine has stop.
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
										<form  id="userCreationFormId" action="emailconfupdate" method="post" enctype="multipart/form-data">																	
											<div class="row">	
											 <div class="input-field col s6">
												<label for="email" class=" control-label"><s:text name="Text.customerreg.emailid"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber" name="email" 
												id="email" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
										
											</div>	
											 <div class="input-field col s6">
												<label for="passwd" class=" control-label"><s:text name="Text.login.password" /><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control registerNumber" name="passwd" 
												id="passwd" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.userName"/>
										
											</div>
											</div>												
											<div class="row">	
											 <div class="input-field col s6">
												<label for="host" class=" control-label"><s:text name="email.config.hostname"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control address" name="host" 
												id="host" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.address"/>

											</div>	
											 <div class="input-field col s6">
												<label for="port" class=" control-label"><s:text name="email.config.portno"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control addresses" name="port" 
												id="port"  ></s:textfield>
												<s:fielderror fieldName="usercreateObj.addresses"/>
										
											</div>	
											</div>
												<div class="row">	
											 <div class="input-field col s6">
												<label for="proto" class=" control-label"><s:text name="email.config.Protocol"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control address" name="proto" 
												id="proto" ></s:textfield>
												<s:fielderror fieldName="usercreateObj.address"/>											
											</div>	
											 <div class="input-field col s6">
												<label for="nofet" class=" control-label"><s:text name="email.config.fetch"/><span class="mandatory">*</span></label>
												<s:textfield cssClass="form-control addresses" name="nofet" 
												id="nofet"  ></s:textfield>
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
							                             onclick="emailengstartfunction()">
							                       <s:text name="Text.button.startengine" />
							                        <i class="<s:text name="button.icon.submitcard"/>"></i>
							                      
						                                 </button>
						                                  <button type="submit" id="userCreateButtonId" 
								                   class="<s:text name="button.color.emailstop"/>" onclick="emailengstopfunction()">
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
	 var email=$('#email').val();
	 var passwd=$('#passwd').val();
	 var host=$('#host').val();
	 var port=$('#port').val();
	 var proto=$('#proto').val();
	 var nofet=$('#nofet').val();
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'emailconfupdate',
			data : "email= "+email+"&passwd="+passwd+"&host="+host+"&port="+port+"&proto="+proto+"&nofet="+nofet+"",
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
 function emailengstartfunction() {
	 toShowLoadingImgoverlay();
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'emailEnginStart',
			data : "",
			success : function(data) {
			var alt=data.split("!_!");
			if(alt[1]=='0'){
				tohideLoadingImgoverlay();
				$('#alertdiv2').show();
				$('#emailengstart').show();
				$('#emailengstop').hide();
				$('#emailupdate').hide();
			}else{
				tohideLoadingImgoverlay();
				$('#alertdiv2').show();
				$('#error').show();
				$('#emailengstart').hide();
				$('#emailengstop').hide();
				$('#emailupdate').hide();
			}
			}
		}); 
	}
 function emailengstopfunction() {
	 toShowLoadingImgoverlay();
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'emailEnginStop',
			data : "",
			success : function(data) {
			var alt=data.split("!_!");
			if(alt[1]=='0'){
				tohideLoadingImgoverlay();
				$('#alertdiv2').show();
				$('#emailengstop').show();
				$('#emailengstart').hide();
				$('#emailupdate').hide();
			}else{
				tohideLoadingImgoverlay();
				$('#alertdiv2').show();
				$('#error').show();
				$('#emailengstart').hide();
				$('#emailengstop').hide();
				$('#emailupdate').hide();
			}
			}
		}); 
	}
 function home(){
		$("#userCancelForm").attr("action", "posthomepage");
		$("#userCancelForm").submit();
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
   			toShowLoadingImgoverlay();
   			return true;   			
   		} 
   	});
   	$("#email").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.email.title" />",
   		}
   		
   	});
   	$("#passwd").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.pass.title" />",
   		}
   		
   	});
   	$("#host").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.host.title" />",
   		}
   		
   	});
   	$("#port").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.port.title" />",
   		}
   		
   	});
   	$("#proto").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.proto.title" />",
   		}
   		
   	});
   	$("#nofet").rules("add", {
   		required : true,
   		messages : {
   			required :"<s:text name="Error.nofet.title" />",
   		}
   		
   	});
   	$(document).ready(function() {
   		$("#email").blur(function(){			
   			toValiEmail(this.id);				
   		});
   		});
   		   	
</script>
</html>
