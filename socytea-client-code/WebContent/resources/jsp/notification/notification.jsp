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
                <h5 class="breadcrumbs-title"><s:text name="Text.notification"/></h5>
                <ol class="breadcrumbs left">
                   <li><a href="loginform"><s:text name="Dashboard"/></a></li>
                    <li class="active"><s:text name="Text.notification"/></li>            
                   </ol>                                           					
              </div>              
            </div> </div>
            </div>   
             <!--breadcrumbs end-->         
            <!--start container-->
        <div class="container">
    	<jsp:include page="../common/Alert.jsp" flush="true" />
     <div style="clear: both;"></div>
   <!--   <div class="card-panel"> -->
	 <div id="email-list" class="col s6 m2 l4 card-panel z-depth-1">     
            <div id="table-datatables">              
              <div class="row">                   
                <div class="col s12 ">
                  <table id="data-table-simple" class="responsive-table display" style="border:0px" >
                    <thead style="border:0px">                    
                    <tr>
                    <th style="border:0px;" class="col s2"></th>                    
                    </tr>                  
					</thead> 
                  </table>
                 <div class="clear height25px"></div>
                </div>
              </div>
            </div> 
            </div>
           <!--  </div> -->
          </div>
        <!--end container-->
          
      </section>     
            <br>
            <div class="divider"></div> 
            <!--DataTables example Row grouping-->     
    </div>
    <!-- END WRAPPER -->
  </div>
  <!-- END MAIN -->
	<s:form method="post" id="delgroupform">
					<s:hidden name="useruniqueId" id="uniqUserIdEdit"></s:hidden>
					<s:hidden name="uniqueId" id="deleteusridEdit"></s:hidden>
			</s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>        
</body>
 <script type="text/javascript">
        /*Show entries on click hide*/
        
        $(document).ready(function() {
        	 $(".collapsible.collapsible-accordion> li").removeClass("active");
        	 $(".collapsible.collapsible-accordion> li> a").removeClass("active");
        	 $(".collapsible.collapsible-accordion> li").removeClass("activeMenu");
        	 $(".collapsible-body> ul> li").removeClass("active");
        	 $(".collapsible-body").hide();
        	 $("#menu001").removeClass("active");
        	  $("#menu002").addClass("active");
				createoTable();						
		});
		function createoTable() {
			oTable = $('#data-table-simple').DataTable({
								"jQueryUI" : false,
								"lengthChange": false,
								"bFilter": false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"ajaxSource" : "notificationdatatable",						
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0]
								} ],
								"drawCallback": function( oSettings ) {
									$('.tooltipped').tooltip("remove");
									 $('.tooltipped').tooltip({delay: 10});
									 $("#data-table-simple tr").removeClass("odd");
									$("#data-table-simple tr").addClass("even");
									$("#data-table-simple_info").css("padding-left","10px"); 
								    }
							});
	
			
		}
		function viewaction(id,tablename) {
			$("#deleteusridEdit").val(id);
			$("#delgroupform").attr("action", "viewNotificationAction");
			$("#delgroupform").submit();
		}
	function deleteaction(id) {
		swal({    title: "Are you sure want to delete?",
            text: "You will not be able to recover this notification!",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Yes",   
            closeOnConfirm: false }, 
            function(){ 
            	$("#deleteusridEdit").val(id);
				$("#delgroupform").attr("action", "deletenotificationaction");
				$("#delgroupform").submit();
           // swal("Deleted!", "User has been deleted.", "success"); 
            });
		}
		function home(){
					$("#releaseHisFormid").attr("action", "homeusercreation");
					$("#releaseHisFormid").submit();
		}
		
		
	function societychange(){
		$("#slectfield").val('');
		$("#usersearchname").val('');
	}function slectfieldtype(){
		$("#usersearchname").val('');
	}					
    </script>
 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</html>