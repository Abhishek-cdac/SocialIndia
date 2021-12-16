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
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->

 <!-- CORE CSS-->
  <link href="<s:text name='Resource.path'/>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/css/style.css?acxx" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="<s:text name='Resource.path'/>/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="<s:text name='Resource.path'/>/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<s:text name='Resource.path'/>/js/plugins/sweetalert/dist/sweetalert.css" type="text/css" rel="stylesheet" media="screen,projection">	
</head>
<style>
	#token-input-invitefriend {
	width: 100% ! important;	
	}	
	.filled-in[type="checkbox"]:disabled:checked + label::after {
    background-color:#ff4081;
    border-color: #ff4081;
}

.filled-in[type="checkbox"]:disabled:not(:checked) + label::after {
    background-color: #fff;
    border: 2px solid;
}
	</style>
<body>
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
	 <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
            <nav class="navbar-color teal">
                <div class="nav-wrapper">
                    <ul class="left">    
                     <li style=" margin-left: 10px;margin-top: 6px;width: 40px;"> <img src="<s:text name='Resource.path'/>/images/social/logo.png" alt="" class="circle responsive-img valign profile-image"></li>                  
                      <li><h1 class="logo-wrapper"><a href="loginform" class="brand-logo darken-1">SOCYTEA</a> <span class="logo-text"></span></h1></li>
                    </ul>
                     
                </div>
            </nav>
        </div>
        <!-- end header nav-->
  </header>

	<!-- //////////////////////////////////////////////////////////////////////////// -->
	<!-- START MAIN -->
	<div id="">
    <!-- START WRAPPER -->
    <div class="wrapper">
      <!-- //////////////////////////////////////////////////////////////////////////// -->
		<jsp:include page="../common/Alert.jsp" flush="true" />
      <!-- START CONTENT -->
      <section id="content">
        
        <!--breadcrumbs start-->
        <div id="breadcrumbs-wrapper">
            <!-- Search for small screen -->
            
         
        </div>              
        <div class="container">
				<div class="card-panel">							
						<form id="residentorderFormId" name="residentorderFormId"  method="post" enctype="multipart/form-data">
						<ul class="collapsible collapsible-accordion" data-collapsible="expandable">
<li>
<div class="collapsible-header <s:text name="collapsible.header.color" /> active" style="color:#fff;"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> CUSTOMERS DETAILS</div>
<div class="collapsible-body padding10px" id="firstdivid">
																 
						   	
			 	<div  style="display:block; margin:10px 0px 0px 25px;" >
							<div class="row">
							<div>
							<span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentname"/></span>   <span class="<s:text name="view.fontvalue.color"/>"><s:property value="residentlastname"/></span>
							</div>
							<div>
							<s:iterator value="FlatList" status="famcount">
							<s:if test="!flatnumber.equalsIgnoreCase('')">
							<span class="<s:text name="view.fontvalue.color"/>"> <s:property value="flatnumber"/></span>,
							</s:if>
							<s:else>
							</s:else>
							<s:if test="!blackname.equalsIgnoreCase('')">
							<span class="<s:text name="view.fontvalue.color"/>"> <s:property value="blackname"/></span>,
							</s:if>
							<s:else>
							</s:else>
							</s:iterator>
							
							
							<s:if test="!residentadd1.equalsIgnoreCase('')">
							<span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentadd1"/></span>,
							</s:if>
							<s:else>
							</s:else>
							<s:if test="!residentcity.equalsIgnoreCase('')">
							<span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentcity"/></span>,
							</s:if>
							<s:else>
							</s:else>
							
							 <s:if test="!residentcountry.equalsIgnoreCase('')">
							 <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentcountry"/></span>.
							</s:if>
							<s:else>
							</s:else>
							
							 </div>
							 <s:if test="!residentpincode.equalsIgnoreCase('')">
							<div>
							 Pincode -<span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentpincode"/></span>
							</div>
							</s:if>
							 <s:if test="!residentmobno.equalsIgnoreCase('')">
							<div>
							 Contact -<span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentmobno"/></span>
							</div>
							</s:if>
                
                   
                  
							</div>
							<%-- <div class="row">
							 
                <div class="input-field col m6">  
                  <div class="row">
                  <div class="input-field col m5">Block/Wings Name</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentblockname"/></span></div>
                      </div>
                    </div>
                    <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">Flat Number</div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentflatno"/></span></div>
  						</div>
                     </div>
                   
							</div> --%>
							<%-- <div class="row">
                          
                     <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">Address1</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentadd1"/></span></div>
                      </div>
                    </div>
                     <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">Address2</div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentadd2"/></span></div>
  						</div>
                     </div>
                    
							</div> --%>
							
							<%-- <div class="row">
                
                  
                     <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">City</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentcity"/></span></div>
                      </div>
                    </div>
                    
                    <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">State</div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentstate"/></span></div>
  						</div>
                     </div>
							</div> --%>
							<%-- <div class="row">
			    
                     <div class="input-field col m6">
                  <div class="row">
                  <div class="input-field col m5">Country</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentcountry"/></span></div>
                      </div>
                    </div>
                    
                    <div class="input-field col m6">
               <div class="row">
                        <div class="input-field col m5">Pincode</div>
						  <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> <s:property value="residentpincode"/></span></div>
  						</div>
                     </div>
							</div> --%>
							</div>
							<div style="clear: both; height: 20px; "></div>
							                           
							
							<div style="clear: both; "></div>
							
		      <%--  <div id="">

							<div class="imgaddplus1" onclick="myFunction2('add');"
								style="display: none;">
								<i class="mdi-content-add-circle tinysmall "
									style="color: #ff4081; float: left; cursor: pointer;"></i>
								<div class="spacialspace">Order
									Detail :</div>
							</div>

							<div class="imgaddminus1" onclick="myFunction2('sub');">
								<i class="mdi-content-remove-circle tinysmall"
									style="color: #ff4081; float: left; cursor: pointer;"></i><span
									class="spacialspace">
									Order Detail :</span>
							</div>

						</div><div style="clear: both; height:5px;"></div> --%>
						</div>
						</li>
						<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-maps-local-grocery-store tinysmall white-text text-accent-4"></i> Order Details</div>                    
<div class="collapsible-body padding10px" id="seconddivid">
															<div id="orderhidden" style="display:block; margin:10px 0px 0px 25px;">
															
															
														<%-- 	 <s:if test="restRegObj.noofFlats.equalsIgnoreCase('0')">
 	</s:if>
 	<s:else> --%>
 	<table id="example" class=" display" cellspacing="0" width="100%">
										<thead style="border: 0px;">
											<tr >
												<th>ITEMS</th>
												<th> ORDER QTY </th>
												<th>SUPPLY QTY</th>
												<th>ACTION</th>
											</tr>
										</thead>
										<s:iterator value="OrderList" status="famcount">
										<tr role="row" class="orderrow"><td><span class="<s:text name="view.fontvalue.color"/>"><s:property value="itemName"/></span></td>
										<td><span class="<s:text name="view.fontvalue.color"/>"><s:property value="orderQty"/></span>
										 </td>
										 <td>  <s:if test="Prctacceptstatus.equalsIgnoreCase('0')">
                                                        <s:textfield cssClass="form-control " name="supplyquality" value="%{supplyQty}"
												id="member" style="border-style: groove; text-align: center;border-color: #bbbbbb; border-radius: 0px; border-width: 1px; width: 30px ! important; height: 26px;"></s:textfield>
                                                        
                                                         </s:if>
                                                         <s:elseif  test="Prctacceptstatus.equalsIgnoreCase('1') || Prctacceptstatus.equalsIgnoreCase('2')">
                                                         <span class="<s:text name="view.fontvalue.color"/>"><s:property value="supplyQty"/></span>
                                                         </s:elseif>
                                                        
                                                         <%--  <s:elseif  test="Prctacceptstatus.equalsIgnoreCase('1')">
                                                         <s:textfield cssClass="form-control " name="supplyquality" value="%{supplyQty}"
												id="member" style="border-style: groove; text-align: center;border-color: #bbbbbb; border-radius: 0px; border-width: 1px; width: 30px ! important; height: 26px;"></s:textfield>
                                                        
                                                         </s:elseif> --%>
										 </td>
										 <div class="input-field col m7" style="display:none;">
                                                        : <s:textfield cssClass="form-control " name="commentid" value="%{commentid}" 
												id="member1" style="display:none;border-style: groove; text-align: center;border-color: #bbbbbb; border-radius: 0px; border-width: 1px; width: 30px ! important; height: 26px;"></s:textfield>
                                                        
                                                    </div>
										 <td>
									
											
											 <s:if test="itemstatus==1 && Prctacceptstatus.equalsIgnoreCase('0')">
										 <p style="padding: 0px;"> 
										 	
										 <input type="checkbox" name="prfaccesschk"  id="Allow<s:property value="#famcount.Index+1" />" class="filled-in" value="1" checked>
										 <label id="Allow<s:property value="#famcount.Index+1" />" for="Allow<s:property value="#famcount.Index+1" />"> </label>
										 </p>
										 <%-- <p><input type="checkbox" name="prfaccesschk" id="notAllow<s:property value="#famcount.Index+1" />" class="filled-in" value="0" >
										 <label id="notAllow<s:property value="#famcount.Index+1" />" for="notAllow<s:property value="#famcount.Index+1" />"> &nbsp;&nbsp;Not Accept</label></p> --%>
										 </s:if>
										 <s:elseif test="itemstatus==1 && Prctacceptstatus.equalsIgnoreCase('1')">
										  <input type="checkbox" name="prfaccesschk"  id="Allow<s:property value="#famcount.Index+1" />" class="filled-in" value="1" disabled="disabled" checked>
										  <label id="Allow<s:property value="#famcount.Index+1" />" for="Allow<s:property value="#famcount.Index+1" />" > </label>
										<%-- <span class="<s:text name="view.fontvalue.color"/>"> &nbsp;&nbsp;Accept </span> --%>
										 </s:elseif>
										 <s:elseif test="itemstatus==1 && Prctacceptstatus.equalsIgnoreCase('2')">
										 <input type="checkbox" name="prfaccesschk"  id="Allow<s:property value="#famcount.Index+1" />" class="filled-in" value="1" disabled="disabled" checked>
										  <label id="Allow<s:property value="#famcount.Index+1" />" for="Allow<s:property value="#famcount.Index+1" />"> </label>
									<%-- 	<span class="<s:text name="view.fontvalue.color"/>"> &nbsp;&nbsp;Accept </span> --%>
										 </s:elseif>
										  <s:elseif test="itemstatus==0 && Prctacceptstatus.equalsIgnoreCase('0')">
										 <p style="padding: 2px;"> 
										 	
										 <p style="padding: 0px;"><input type="checkbox" name="prfaccesschk" id="notAllow<s:property value="#famcount.Index+1" />" class="filled-in" value="0">
									 <label id="notAllow<s:property value="#famcount.Index+1" />" for="notAllow<s:property value="#famcount.Index+1" />">  </label>
										 </p>
										
										 </s:elseif>
										 <s:elseif test="itemstatus==0 && Prctacceptstatus.equalsIgnoreCase('1')">
									<p style="padding: 0px;"><input type="checkbox" name="prfaccesschk" id="notAllow<s:property value="#famcount.Index+1" />" disabled="disabled" class="filled-in" value="0">
									 <label id="notAllow<s:property value="#famcount.Index+1" />" for="notAllow<s:property value="#famcount.Index+1" />">  </label>
										 </s:elseif>
										 <s:elseif test="itemstatus==0 && Prctacceptstatus.equalsIgnoreCase('2')">
										<p style="padding: 0px;"><input type="checkbox" name="prfaccesschk" id="notAllow<s:property value="#famcount.Index+1" />" disabled="disabled" 	class="filled-in" value="0">
									 <label id="notAllow<s:property value="#famcount.Index+1" />" for="notAllow<s:property value="#famcount.Index+1" />">  </label>
										 </s:elseif>
										 </td></tr>
										 </s:iterator>
									</table>
											 
											</div><!-- others-->
											</div>
										</li> 
				<li>
<div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-action-face-unlock tinysmall white-text text-accent-4"></i> RESIDENT COMMENTS </div>                    
<div class="collapsible-body padding10px" id="thirddivid">	
					<div class="row"  style="margin-left:12px;">
												 
												 <div class="col m12" style="margin: 0px 0px 0px 0px;"> <span class="<s:text name="view.fontvalue.color"/>"><s:property value="residentcomments"/></span>
												</div>
											</div>	 
											 </div>
</li>
<li>
											 <div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-action-store tinysmall white-text text-accent-4"></i> MERCHANT COMMENTS </div>  
											 <div class="collapsible-body padding10px" id="fourdivid">	
					
			<s:if test="Prctacceptstatus.equalsIgnoreCase('0')">
			<div class="row"  style="margin-left:12px;">
												 <textarea class="materialize-textarea validate" name="supplycomments" id="evedesc" rows="2" style="resize:none;height:100px"></textarea>
												 <%-- <div class="col m12" style="margin: 0px 0px 0px 0px;"> <span class="<s:text name="view.fontvalue.color"/>"><s:property value="mrchcomments"/></span>
												</div> --%>
											</div>	 
											 <div class="input-field col m4">
                                                       <div class="row">
						<div class="input-field col s12" >
						
						<button type="button" id="userCancelButtonId" class="<s:text name="button.color.cancel"/>" onclick="declineFunction();">
						Decline<i class="<s:text name="button.icon.replycard"/>"></i></button>
						<button type="button" id="userCreateButtonId" onclick="AcceptOrder();" class="<s:text name="button.color.submit"/>">
						Accept<i class="<s:text name="button.icon.submitcard"/>"></i></button>
					</div>	
					</div></div>			
											 </s:if>
											 <s:elseif test="Prctacceptstatus.equalsIgnoreCase('1')">
											 <div class="row"  style="margin-left:12px;">
												
												 <div class="col m12" style="margin: 0px 0px 0px 0px;"> <span class="<s:text name="view.fontvalue.color"/>"><s:property value="mrchcomments"/></span>
												</div>
											</div>	 
											 
											 
									<div style="clear: both; height: 20px; "></div>
										
											 </s:elseif>
											<s:elseif test="Prctacceptstatus.equalsIgnoreCase('2')">
											<div class="row"  style="margin-left:12px;">
												
												 <div class="col m12" style="margin: 0px 0px 0px 0px;"> <span class="<s:text name="view.fontvalue.color"/>"><s:property value="mrchcomments"/></span>
												</div>
											</div>	 
											 
									<div style="clear: both; height: 20px; "></div>
										
											 </s:elseif>
											 </div>
											 </li>
											  <s:if test="Prctacceptstatus.equalsIgnoreCase('1')">
											 <li>
											 <div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-content-content-paste tinysmall white-text text-accent-4"></i> ORDER STATUS </div>
											  <div class="collapsible-body padding10px" id="fifthdivid">
											  <div class="row" style="margin-left:12px;">
			    
                     <div class="input-field col m12">
                  <div class="row" >
                  
                  <div class="input-field col m2"  >Status</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> Accept </span></div>
                      </div>
                    </div>
                    
                    
							</div>	
											  </div>
											 </li>
											 </s:if>
											 <s:elseif test="Prctacceptstatus.equalsIgnoreCase('2')">
											 <li>
											 <div class="collapsible-header <s:text name="collapsible.header.color" />"> <i class="mdi-content-content-paste tinysmall white-text text-accent-4"></i> ORDER STATUS </div>
											  <div class="collapsible-body padding10px" id="fifthdivid">
											  <div class="row" style="margin-left:12px;">
			    
                     <div class="input-field col m12">
                  <div class="row" >
                  
                  <div class="input-field col m2" >Status</div>
                   <div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color"/>"> Cancel </span></div>
                      </div>
                    </div>
                    
                    
							</div>	
											  </div>
											 </li>
											 </s:elseif>
											 </ul>
				 <s:hidden name="productorderid" ></s:hidden>
				 <s:hidden name="acceptstatus_str" id="acceptstatusid" class="form-control " />
				 <s:hidden name="commentid_str" id="commentidval"  class="form-control " />
				 <s:hidden name="supplyquality_str" id="supplyqualityid" class="form-control " />
				 <%--  <s:hidden name="supplycomments" id="iVOmrchcomments" class="form-control " /> --%>
					</form>
					<div style="clear: both; height: 20px; "></div>
            
					</div>
					
					<form id="userCancelForm" name="labourview" action="/socialIN/labourview" method="post">
				
					</form>




					<div style="clear: both; height: 20px; "></div>
				</div>
        <!--end container-->
      </section>
      <!-- END CONTENT -->

      <!-- //////////////////////////////////////////////////////////////////////////// -->
      <!-- START RIGHT SIDEBAR NAV-->
       <!-- START CONTENT -->
           
            <!-- END CONTENT -->
      
      <!-- LEFT RIGHT SIDEBAR NAV-->

    </div>
    <!-- END WRAPPER -->

  </div>
  <!-- END MAIN -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<jsp:include page="../common/clorbox.jsp"></jsp:include>
 <!-- ================================================
    Scripts
    ================================================ -->
    
   
     <!-- jQuery Library -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-1.11.2.min.js"></script>    
    <!--materialize js-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/materialize.js"></script>
    <!--scrollbar-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    

    <!-- chartist -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.js"></script>   

    <!-- chartjs -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartjs/chart.min.js"></script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/chartjs/chart-script.js"></script>

     <!-- sparkline -->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/sparkline/sparkline-script.js"></script> 
    <script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins/sweetalert/dist/sweetalert.min.js"></script>

    
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script> 
	<script type="text/javascript">
	
	$('input[type="checkbox"]').click(function() {
	
	   // $(this).siblings('input:checkbox').prop('checked', false).change();
		var ischecked= $(this).is(':checked');
		
                    if(!ischecked)
                    	{
                    	$(this).val("0");
                      //  $("label[for='" + this.id + "']").text("Not Accept")
                    	}
                    else
                    	{
                    	$(this).val("1");
               		// $("label[for='" + this.id + "']").text("Accept");
                    	}
                       
	     //$(this).prop('id').val("0");
	});
	
	function AcceptOrder() {
		swal({    title: "Are you sure want to Accept?",
         text: "You will not be able to recover this order detail!",   
         type: "warning",   
         showCancelButton: true,   
         confirmButtonColor: "#DD6B55",   
         confirmButtonText: "Yes",   
         closeOnConfirm: false }, 
         function(){ 
         
         	$("#residentorderFormId").attr("action", "mrchprdtorderaction");
			$("#residentorderFormId").submit();
        // swal("Deleted!", "User has been deleted.", "success"); 
         });
		}
	/* function AcceptOrder() {
		
		swal({
			title : "Merchant Comments",   
			 text: "<textarea id='text' class='form-controll'></textarea>",
			 html: true,
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top",
			inputPlaceholder : "Enter Comments"
		}, function(inputValue) {
			if (inputValue === false)
				return false;
			if (inputValue === "") {
				swal.showInputError("Enter Comments");
				return false;
			}
			 var val = document.getElementById('text').value;
			$("#iVOmrchcomments").val(val);
			$("#residentorderFormId").attr("action", "mrchprdtorderaction");
			$("#residentorderFormId").submit();
			//swal("Success", "New Group Created Successfully" , "success"); 
		});
	} */
	/* $('input[type="checkbox"]').click(function() {
	    $(this).siblings('input:checkbox').prop('checked', false);
	    $(this).prop('checked', true);
	}); */
 function myFunction2(check){
	    
 	 if(check=="add"){
 		 $('.imgaddminus1').css("display","inline");
 		 $('#orderhidden').show();
 		 $('.imgaddplus1').css("display","none");
 	 }else if(check=="sub"){
 		 $('.imgaddminus1').css("display","none");
 		 $('.imgaddplus1').css("display","inline");
 		 $('#orderhidden').hide();
 	 } 
 	
 }
 $(document).ready(function(){
	 $("#firstdivid").css("display","block");
		$("#seconddivid").css("display","block");
		$("#thirddivid").css("display","block");
		$("#fourdivid").css("display","block");
		$("#fifthdivid").css("display","block");
 $("#userCreateButtonId").click(function(){
		var targets = [];
		var targets1 = [];
		 var targets2 = [];
		   $("input[name='prfaccesschk']").each(function(){
			   var prfaccess=$(this).val();
			   targets.push(prfaccess);
			   });
		    $("input[name='supplyquality']").each(function(){
			   var supqty=$(this).val();
			   targets1.push(supqty);
			   });
		   $("input[name='commentid']").each(function(){
			   var cmtid=$(this).val();
			   targets2.push(cmtid);
			   });
		   $("#acceptstatusid").val(targets.join("!_!"));
		   $("#supplyqualityid").val(targets1.join("!_!"));
		    $("#commentidval").val(targets2.join("!_!"));
		 
	 });
	 });
 function declineFunction()
 {
	 $("#residentorderFormId").attr("action", "declineorderaction");
		$("#residentorderFormId").submit();
 }
 </script>
</body>

</html>