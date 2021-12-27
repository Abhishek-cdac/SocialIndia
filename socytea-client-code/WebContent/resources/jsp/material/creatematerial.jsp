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
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>	
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="Breadcrumb.add.material" /></h5>
								<ol class="breadcrumbs left">
									<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
									<li><a href="materialMgmt"><s:text name="Breadcrumb.utility" /></a></li>
									<li><a href="materialMgmt"><s:text name="Breadcrumb.material" /></a></li>
									<li class="active"><s:text name="Breadcrumb.add.material" /></li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->
				<!--start container-->
				<div class="container">
			<div class="card-panel">
										  <form method="post" id="formValidate"  name="formValidate" action="materialCreateAction">
 													<s:if test="#session.sSoctyId==-1">
 													<div class="row rowmargin12px" >
										 <div class="input-field col s6">
																<label for="township_txt_id"><s:text name="Text.adduser.town.ship.id"></s:text><span class="mandatory">*</span></label>																 
																<s:textfield  id="township_txt_id"  cssClass="form-control typeahead tt-query townshipIdvalidate"  autocomplete="off" />
																<input type="hidden" name="townshipId" id="township_hidd_id"  class="form-control "  />																						
															</div>
                                        
										 <div class="input-field col s6">
																<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /><span class="mandatory">*</span></label>
																<s:textfield  id="societyId_txt_id" name="societyName"  cssClass="typeahead tt-query form-control societyName"  autocomplete="off" />
														<input type="hidden"  name="materialObj.societyId" id="societyId_hidd_id"  class="form-control "  />
																<%-- <s:textfield name="restRegObj.societyName" id="emailid" placeholder="Enter Email-Id" cssClass="form-control emailidvalidate"/> --%>													
															</div>
 															 </div>	
 															 </s:if>
 															<div class="row rowmargin12px" >
										                <div class="input-field col s6">
																<label for="card"><s:text name="Category Name" /><span class="mandatory">*</span></label>
																<s:textfield  name="ID_CARD_TYP_Name" id="card"   cssClass="form-control typeahead tt-query categoryType left" autocomplete="off"/>
																<span class="input-group-addon txtbxdownarowicon left" onclick="openAutocmp('card');"><i class="mdi-navigation-arrow-drop-down tinysmall black-text"></i></span>
																  <input  type="hidden" name="categoryType" id="cardid" class="form-control"/> 
																	 <%-- <s:hidden   id="cardid"  cssClass="form-control"></s:hidden> --%>	
																	
															
													</div>
															<div class="input-field col s6">															
																		<label for="materialname"><s:text name="Material Name" /><span class="mandatory">*</span></label>
																		<s:textfield name="materialObj.materialName"  id="materialname"  cssClass="form-control materialName" />
																	
															</div>
															</div>
 													<div class="row rowmargin12px" >
										                <div class="input-field col s6">
																<label for=totalquty>Total Quantity<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.totalQnty" id="totalquty" onkeyup="textValidate(this,'4','PHNM');" cssClass="form-control totalQnty" />
														
															</div>
															<div class="input-field col s6">
																<label for="usedquty">Used Quantity<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.usedQnty" onkeyup="textValidate(this,'4','PHNM');" id="usedquty" cssClass="form-control usedQnty" />
															
															</div>
 															 </div>
												
														<div class="row rowmargin12px" >
										                <div class="input-field col s6">
																<label for="availablequty">Material Avail. Quantity<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.materialQnty" id="availablequty" onkeyup="textValidate(this,'4','PHNM');" cssClass="form-control materialQnty" />
														
															</div>
															 <div class="input-field col s6">

																<label for="materialprice">Material Price<span class="mandatory">*</span></label>
																<s:textfield name="materialObj.materialPrice"  onkeyup="textValidate(this,'10','DT');" id="materialprice" cssClass="form-control materialPrice"/>
															
															</div>
															
														</div>	
														<div class="row rowmargin12px" >
										               <%--  <div class="input-field col s6">
																 <label for="purchasedate">Purchase Date<span class="mandatory">*</span></label> 															
															     <div class="input-group input-append date" id="datePicker">
															     <s:textfield name="purchaseDate" id="purchasedate"  cssClass="form-control forumdatevalidate" />
                												<!-- <input type="text" class="form-control" name="forumDate"  placeholder="Enter Date Of Birth"/> -->
               											 <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
           													 
															  </div>   															            											        					
															</div> --%>
															
															<div class="input-field col s6">
												 <label for="purchasedate">Purchase Date<span class="mandatory">*</span></label> 																														
												<s:textfield  cssClass="dateOfBirth" name="purchaseDate" id="purchasedate"></s:textfield>
												</div>
														</div>	
														
														<div class="row rowmargin12px" >
								<div class="input-field col s12">
									<label class="active" for="textarea1"><s:text name="Text.material.desc" /></label>
									<textarea id="textarea1" name="materialObj.materialDesc" class="materialize-textarea" style="height: 15px;"></textarea>
								</div>

							</div>																				 		
								<div class="clear height10px"></div>
						          <div>
						                           <button type="submit" id="userCreateButtonId" 
								                   class="<s:text name="button.color.submit"/>">
								                   <s:text name="Text.button.submit" />
								                     <i class="<s:text name="button.icon.submitcard"/>"></i>
							                         </button>
						                           <button type="button" id="userCancelButtonId"
							                         class="<s:text name="button.color.cancel"/>"
							                              onclick="cancelFunction();">
							                       <s:text name="Text.button.cancel" />
							                       <i class="<s:text name="button.icon.replycard"/>"></i>
						                                 </button>
				                                  </div>	
														</form> 
										</div>
	
					<s:form method="post" id="userCancelForm"></s:form>
				</div>
				
				<!--end container-->
			
			</section>

		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>	
</body>
<script type="text/javascript">	
$(document).ready(function(){
	
	$.ajax({
		type : 'post',
		datatype : 'json',
		url : 'getresidentform',
		data : '',
		success : function(data) {
			var arr=data.split("!_!");
			townshipfun(arr[1]);
		}
	});
	
	$.ajax({
		type : 'post',
		datatype : 'json',
		url : 'getmaterialcategory',
		data : '',
		success : function(data) {
			
			var arr=data.split("!_!");
			//$("#multiselectdiv").html(data);
			//statelistload(arr[1]);
			
			materialcategoryfun(arr[1]);
		}
	});
});

$('#formValidate').validate({
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
			//toShowLoadingImgoverlay();
		} 
	});
var pvrLoginusrsoctyid = '<s:property value="#session.sSoctyId"/>';//socytea id not found in session -[Admin login]
if(pvrLoginusrsoctyid<=0){
 $("#township_txt_id").rules("add", {
		required : true,
		messages : {
			required :"<s:text name="Error.customerreg.township.name.length" />",
		}
		
	});
$("#societyId_txt_id").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.customerreg.societyId" />",
	}
	
}); 
}
$("#card").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.customerreg.material.category" />",
	}
	
});
$("#materialname").rules("add", {
	required : true,
	minlength :2,
	maxlength :100,
	messages : {
		required :"<s:text name="Error.customerreg.material.name" />",
	}
	
});
$("#totalquty").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.qnty.total.length" />",
	}
	
});
$("#usedquty").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.qnty.used.length" />",
	}
	
});
$("#availablequty").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.qnty" />",
	}
	
});
$("#materialprice").rules("add", {
	required : true,
	minlength :1,
	maxlength :15,
	messages : {
		required :"<s:text name="Error.customerreg.material.price.length" />",
	}
	
});
$("#purchasedate").rules("add", {
	required : true,
	messages : {
		required :"<s:text name="Error.customerreg.material.purchasedate" />",
	}
	
});
	


 


function materialcategoryfun(ar)
{
	var objects_cardtype;		
	ar=ar.replace(/&quot;/ig,'"');
	var locval = JSON.parse(ar);	
 $('#card').typeahead({
	     	order: "asc",
			hint: true,
				accent: true,
				offset: true,
				minLength : 0,
				highlight: true,
				showHintOnFocus:true,

				searchOnFocus: true, 
	        source: function(query, process) {
	        	objects_cardtype = [];  
	        map = {};		      
	        var data = locval;
	        $.each(data, function(i, object) {
	            map[object.label] = object;
	            objects_cardtype.push(object.label);
	        });
	        process(objects_cardtype);
	       $(".dropdown-menu").css("height", "auto");
	       $(".dropdown-menu").addClass("form-control");
	    },
	    updater: function(item) {
	        $('#cardid').val(map[item].id);
	        return item;
	    }
	});
 $('#card').blur(function(){				
		if (objects_cardtype.indexOf(this.value)!=-1) {			
        }else{            
        	  $('#card').val('');        	         	 
        	  $('#card').focus();
	          $('#cardid').val('');       
          }		
      });
	}


function townshipfun(ar)
{
	var twnmap;
	 var objects_township;
	 ar=ar.replace(/&quot;/ig,'"');		   
	var locval = JSON.parse(ar);	
	$('#township_txt_id').val('');   
	 $('#township_hidd_id').val('');
 $('#township_txt_id').typeahead({
	     	order: "asc",
			hint: true,
				accent: true,
				offset: true,
				searchOnFocus: true, 
	        source: function(query, process) {
	        	objects_township = [];  
	        	twnmap = {};		      
	        var data = locval;
	        $.each(data, function(i, object) {
	        	twnmap[object.label] = object;
	        	objects_township.push(object.label);
	        });
	        process(objects_township);
	       $(".dropdown-menu").css("height", "auto");
	       $(".dropdown-menu").addClass("form-control");
	    },
	    updater: function(item) {	    	  	         
	          $('#township_hidd_id').val(twnmap[item].id);
	        getsociety(twnmap[item].id);
	       return item;
	    }
	});
 $('#township_txt_id').blur(function(){		
	 if (typeof(objects_township) != "undefined" && objects_township.indexOf(this.value)!=-1) {	
        }else{            
        	   $('#township_txt_id').val('');        	         	 
        	  $('#township_txt_id').focus();
	          $('#township_hidd_id').val(''); 
	          $('#societyId_txt_id').val('');        	         	 
		      $('#societyId_hidd_id').val('');
		      $('#societyId_txt_id').focus();
		  $('#societyId_txt_id').typeahead('destroy'); 
          }		
      });
}

function getsociety(townid){
	//var valjson=onchangetownshipId(townid);
	
	var temp="";
	$.ajax({
 		type : 'post',
 		datatype : 'html',
 		url : 'townshipgetsociety',
 		data : 'townshipid=' + townid+"&clfor=autocomp",
 		success : function(data) { 			
 			var spl=data.split("!_!"); 			
 			temp=spl[1]; 	
 			societylistfun(spl[1]);
 			//return temp;
 		}
 	});
}
var objects_society;
function societylistfun(ar)
{
	
	
	//var valjson='<s:property value="locIdStatetyp"/>';
	
	ar=ar.replace(/&quot;/ig,'"');
	var loc_state_val = JSON.parse(ar);	
	$('#societyId_txt_id').val('');        	         	 
    $('#societyId_hidd_id').val('');
    $('#societyId_txt_id').typeahead('destroy');
	$('#societyId_txt_id').typeahead({		 
     	order: "asc",
		hint: true,
		accent: true,
		offset: true,
		searchOnFocus: true, 
        source: function(query, process) {
        	objects_society = [];  
        map = {};		      
        var data = loc_state_val;
        $.each(data, function(i, object) {
            map[object.label] = object;
            objects_society.push(object.label);
        });
        process(objects_society);
       $(".dropdown-menu").css("height", "auto");
       $(".dropdown-menu").addClass("form-control");
    },
    updater: function(item) {
        $('#societyId_hidd_id').val(map[item].id);
        //onchangeCitycodeAgency();
        return item;
    }
	}).on('typeahead:selected', function(e, suggestion, dataSetName) {
        /* Revalidate the state field */
        $('#formValidate').formValidation('removeField', 'societyName');
    }).on('typeahead:closed', function(e) {
        /* Revalidate the state field */
        //$('#residentCreationFormId').formValidation('revalidateField', 'societyName');
    });			
		$('#societyId_txt_id').typeahead('refresh');
		//add dynamical validate - 3
	$('#formValidate').bootstrapValidator('addField', 'societyName', {
            validators: {
                notEmpty: {
                    message: '<s:text name="Error.customerreg.societyId" />'
                }
            }
 });

	$('#societyId_txt_id').typeahead('refresh');	
 $('#societyId_txt_id').blur(function(){				
		if (objects_state.indexOf(this.value)!=-1) {			
        }else{            
        	  $('#societyId_txt_id').val('');        	         	 
        	  $('#societyId_txt_id').focus();
	          $('#societyId_hidd_id').val('');
	          $('#societyId_txt_id').typeahead('destroy');
	     
          }		
      }); 
	}

 function cancelFunction(){
	$("#userCancelForm").attr("action", "materialMgmt");
	$("#userCancelForm").submit();
	$('#userCancelButtonId').addClass('<s:text name="cancel.button.animated.rollout"/>');		
	toShowLoadingImgoverlay();
	$(".card-panel").addClass('<s:text name="cardpanel.button.animated.flipoutx"/>');
}
</script>
</html>
