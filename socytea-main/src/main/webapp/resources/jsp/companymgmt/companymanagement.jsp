<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<!--================================================================================
	Item Name: Materialize - Material Design Admin Template
	Version: 3.1
	Author: GeeksLabs
	Author URL: http://www.themeforest.net/user/geekslabs
================================================================================ -->
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
                <h5 class="breadcrumbs-title"><s:text name="Text.breadcrumb.companymenu" /></h5>
                <ol class="breadcrumbs left">
                   <li><a href="loginform"><s:text name="Breadcrumb.dashboard"/></a></li>
					<li><a href="companymgmt"><s:text name="Breadcrumb.manage" /></a></li>
                    <li class="active"><s:text name="Text.breadcrumb.companymenu" /></li>             
                   </ol>
                  <div class="right">
                      <a href="createNewcompanyform"><button type="button" style="width: 235px;" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Create New Company"><i class="mdi-social-domain left"> </i>Create Company</button></a>
                </div></div></div></div> </div>
        <!--breadcrumbs end-->
        <!--start container-->
        <div class="container">
        
        </div>
        <!--end container-->
      </section>
     
     <jsp:include page="../common/Alert.jsp" flush="true" />
     <div style="clear: both;"></div>            
            <div id="table-datatables" class="padding10px">              
              <div class="row">
               <div style="height: 10px;"></div>
               <div class="col s12">
                <table id="example" class="responsive-table display hoverable" >
                   <thead>
													<tr class="<s:text name="datatable.heading.bgcolor"/>">
												    	 <th><s:text name="Text.id"></s:text></th>
												  	 <th><s:text name="Text.image"></s:text></th>
													<th><s:text name="Text.company.name"></s:text></th>
													<th><s:text name="Text.company.regno"></s:text></th>
													<th><s:text name="Text.mobileno"></s:text></th>
													<th><s:text name="Text.company.verifysts"></s:text></th>
													<th><s:text name="Text.action"></s:text></th>
												</tr>
											</thead>
                  </table>
                </div>
              </div>
            </div> 
            <br>
            <div class="divider"></div> 
            <!--DataTables example Row grouping-->      
    </div>
    <!-- END WRAPPER -->
  </div>
  <!-- END MAIN -->
	<s:form method="post" id="delgroupform">
					<s:hidden name="laboruniqueId" id="uniqlaborIdEdit"></s:hidden>
					<s:hidden name="laborName_hidd_txt" id="laborName_hidd_id"></s:hidden>
					<s:hidden name="deletecompanyid" id="deletelcompanyidEdit"></s:hidden>
					<s:hidden name="deletelaborserviceid" id="deletelaborserv_id"></s:hidden>
			</s:form>

			
			<s:form method="post" name="lockunlockaccountformid" id="lockunlockaccountformid"  >
				 <s:hidden id="lockuniqidHidn" name="custAccontDetailObj.uniqId" value="%{custAccontDetailObj.uniqId}"></s:hidden>
				 <s:hidden id="lockunlockflgHidn" name="custAccontDetailObj.isLocked" value="%{custAccontDetailObj.isLocked}"></s:hidden>
			</s:form>
			
			<s:hidden id="validateEnterValHidn" name="validateEnterValHidn" value="%{validateEnterVal}"></s:hidden>
			<s:hidden id="validateAccntfrmtHidn" name="validateAccntfrmtHidn" value="%{validateAccntfrmt}"></s:hidden>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include> 
	<jsp:include page="../common/clorbox.jsp"></jsp:include>
    
</body>
 <script type="text/javascript">
        /*Show entries on click hide*/
        var oTable;
        $(document).ready(function() {
			createoTable();						
		});
		function createoTable() {
			oTable = $('#example').DataTable(
							{
								"jQueryUI" : false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"serverMethod": "POST",
								"scrollAutoCss": true,			
								"ajaxSource" : "companymgmtdatatable",
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3 , 4,5,6]
								} ],
								"fnDrawCallback": function( oSettings ) {
									$('.tooltipped').tooltip("remove");
									 $('.tooltipped').tooltip({delay: 10});
								      //alert( 'DataTables has redrawn the table' );
								    }														
							});
			
		}
        $(document).ready(function(){
            $(".dropdown-content.select-dropdown li").on( "click", function() {
                var that = this;
                setTimeout(function(){
                if($(that).parent().hasClass('active')){
                        $(that).parent().removeClass('active');
                        $(that).parent().hide();
                }
                },100);
            });
        });
        
       /*  function deletecompanyaction(id){
    		
    		BootstrapDialog.confirm('Are you sure want to delete?', function(result) {
    			if (result){
    				$("#deletelcompanyidEdit").val(id);
    				$("#delgroupform").attr("action", "deletecompany");
    				$("#delgroupform").submit();
    			 } 
    		});
    	} */
        
        function deletecompanyaction(id) {
    	
    		swal({    title: "Are you sure want to delete?",
    			text: "You will not be able to recover this Company Detail !",  
                type: "warning",   
                showCancelButton: true,   
                confirmButtonColor: "#DD6B55",   
                confirmButtonText: "Yes",   
                closeOnConfirm: false }, 
                function(){ 
                	$("#deletelcompanyidEdit").val(id);
    				$("#delgroupform").attr("action", "deletecompany");
    				$("#delgroupform").submit();
               // swal("Deleted!", "User has been deleted.", "success"); 
                });
    		}
        
        
    	function editcompany(id) {
    		$("#deletelcompanyidEdit").val(id);
    		$("#delgroupform").attr("action", "editcompanyform");
    		$("#delgroupform").submit();
    	}
    	function viewcompany(id) {
    		$("#deletelcompanyidEdit").val(id);
    		
    		$("#delgroupform").attr("action", "viewcompanyform");
    		$("#delgroupform").submit();
    	}
    	function labourfeedback(id,labname) {
    		$("#uniqlaborIdEdit").val(id);
    		$("#laborName_hidd_id").val(labname);
    		$("#delgroupform").attr("action", "labourfeedbackform");
    		$("#delgroupform").submit();
    	}
    		
    	function staffcreation()
    	{
    		$("#delgroupform").attr("action", "createstaffform");
    		$("#delgroupform").submit();
    	}
    </script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>	
</html>