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
<jsp:include page="../common/header.jsp"></jsp:include>
  <div id="main">
    <div class="wrapper">
     <jsp:include page="../common/menuBar.jsp"></jsp:include>
      <section id="content">
        <div id="breadcrumbs-wrapper">
			<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Breadcrumb.groupmanage" /></h5>
                <ol class="breadcrumbs left">
                  <li><a href="loginform"><s:text name="Breadcrumb.dashboard"/></a></li>
							<li><a href="#"><s:text name="Breadcrumb.uam"/></a></li>
							<li class="active"><s:text name="Breadcrumb.groupmanage" /></li>
                   </ol>
                  <div class="right">
                      <button type="button" style="width: 200px;" onclick="createGrp();" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Create New Group"><i class="mdi-social-group-add left"> </i>New Group</button>
                </div>                               						
              </div>
            </div>
          </div>
        </div>
        <div class="container">
        </div>
      </section>
     
    	<jsp:include page="../common/Alert.jsp" flush="true" />
     <div style="clear: both;"></div>
            <div id="table-datatables" class="padding10px" >
              <div class="row">
                    <div style="height: 10px;"></div>
                <div class="col s12">
                  <table id="example" class="responsive-table display hoverable">
                   <thead>
					<tr class="<s:text name="datatable.heading.bgcolor"/>">
					<th><s:text name="Text.id" /></th>
					<th><s:text name="Text.adduser.groupname" /></th>
					<th><s:text name="Text.Form.NoOfUsers" /></th>
					<th><s:text name="Text.status" /></th>
					<th><s:text name="Text.datetimeformat" /></th>
					<th><s:text name="Text.action" /></th>
					</tr>
				  </thead>
                  </table>
                </div>
              </div>
            </div> 
            <div class="divider"></div> 
    </div>
  </div>
	<s:form method="post" id="deletegroupForm" >
		<s:hidden name="deletegroupid" id="deletegroupid"></s:hidden>
	</s:form>
	
		<s:form method="post" id="createGrpForm" >
		<s:hidden name="NewgroupName" id="NewgroupName"></s:hidden>
	</s:form>
	
	<s:form method="post" id="updateGrpForm" >
		<s:hidden name="groupmang.groupName" id="EditGroupName"></s:hidden>
		<s:hidden name="hiddengroupid" id="hiddengroupid"></s:hidden>
	</s:form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>  
    
</body>
 <script type="text/javascript">
        /*Show entries on click hide*/
        var oTable;
        $(document).ready(function() {
			createoTable();						
		});
		function createoTable() {
			
			oTable = $('#example').DataTable({
				"jQueryUI" : false,
				"processing" : true,
				"serverSide" : true,
				"displayLength" : 10,
				"ajaxSource" : "groupmanagedatatable",						
				"sorting" : [],
				"paginationType" : "full_numbers",
				"columnDefs" : [ {
					"sortable" : false,
					"targets" : [ 0, 1, 2, 3, 4,5]
				} ],
			"fnDrawCallback": function( oSettings ) {
				$('.tooltipped').tooltip("remove");
				$('.tooltipped').tooltip({delay: 10});
				$('select').material_select();
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
        
     
        function edituser(id) {
			$("#uniqUserIdEdit").val(id);
			$("#delgroupform").attr("action", "edituserform");
			$("#delgroupform").submit();
		}
	function deleteuser(id) {
		swal({ title: "Are you sure want to delete?",
            text: "You will not be able to recover this group!",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Yes",   
            closeOnConfirm: false }, 
            function(){ 
            	$("#deletegroupid").val(id);
				$("#deletegroupForm").attr("action", "deletegroupaction");
				$("#deletegroupForm").submit();
         //   swal("Deleted!", "User has been deleted.", "success"); 
            });
		}
	
	function createGrp(){
		
		swal({   title: "Create New Group",   
           // text: "Write something interesting:",   
            type: "input",   showCancelButton: true,   
            closeOnConfirm: false,  
            animation: "slide-from-top",   
            inputPlaceholder: "Group name" }, 
            function(inputValue){   
                if (inputValue === false) return false;      
                if (inputValue === "") {     
                swal.showInputError("Enter your group name");     
                return false;   
                } if(inputValue.length  >= 5 && inputValue.length  <= 50){
                	var letters = /^[0-9a-zA-Z\ ]+$/;  
                	if(inputValue.match(letters)){
                		$("#NewgroupName").val(inputValue);
        				$("#createGrpForm").attr("action", "creategroup");
        				$("#createGrpForm").submit(); 
                	}else{
                		swal.showInputError("Group name should not allow special character.");     
                        return false;	
                	}
                	
                } else{
                	swal.showInputError("Group name minimum 5 to maximum 50 characters.");     
                    return false;
                }   
                
           //swal("Success", "New Group Created Successfully" , "success"); 
       });
	}
		function home(){
					$("#releaseHisFormid").attr("action", "homeusercreation");
					$("#releaseHisFormid").submit();
		}
	
		function editGroupFunct(id,gname){
			swal({   title: "Edit Group",   
                //text: "Create New Group",   
                type: "input",   showCancelButton: true,
                closeOnConfirm: false,   
                animation: "slide-from-top", 
                inputValue: ""+gname , 
                inputPlaceholder: "Group" }, 
                function(inputValue){   
                    if (inputValue === false) return false;      
                    if (inputValue === "") {     
                    	swal.showInputError("Enter your group name");     
                    	return false;  
                    } 
                    if(inputValue.length  >=5 && inputValue.length  <= 50){
                    	var letters = /^[0-9a-zA-Z\ ]+$/;  
                    	if(inputValue.match(letters)){
                    		$("#EditGroupName").val(inputValue);
                    		$("#hiddengroupid").val(id);
    						$("#updateGrpForm").attr("action", "editGroup");
    						$("#updateGrpForm").submit();
              				// swal("Success", "Group updated Successfully ", "success"); 
                    	}else {
                    		swal.showInputError("Group name should not allow special character.");     
                            return false;
                    	}
                    } else {
                    	swal.showInputError("Group name minimum 5 to maximum 50 characters.");     
                        return false;
                    }
           });
			
		}			
    </script>
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
 
</html>