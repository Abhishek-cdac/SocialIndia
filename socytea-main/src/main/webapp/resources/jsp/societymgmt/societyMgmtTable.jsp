<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><s:text name='Text.project.title' /></title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="<s:text name='meta.contentType' />">
<meta name="viewport" content="<s:text name='meta.viewport' />">
<meta http-equiv="X-UA-Compatible" content="<s:text name='meta.X-UA-Compatible' />">
<meta name="msapplication-tap-highlight" content="<s:text name='meta.msapplication-tap-highlight' />">
<meta name="description" content="<s:text name='meta.description' />">
<meta name="keywords" content="<s:text name='meta.keywords' />">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
  <jsp:include page="../common/basiccss.jsp"></jsp:include>
</head>

<body>
<%-- <script> --%>
<%-- var mainmunuact = '<%= session.getAttribute("mainmunuact") %>'; --%>
<%-- var Rd = '<%= session.getAttribute("Rd") %>'; --%>
<%-- </script> --%>

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
           <!-- Search for small screen -->
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Society Management" /></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Breadcrumb.dashboardhome"/></a></li>
					<li><a href="#"><s:text name="Breadcrumb.societymgmt"/></a></li>
                    <li class="active"><s:text name="Breadcrumb.manage.society" /></li>
             
                   </ol>
                  <div class="right">
                      <a href="createsocietyform"><button type="button" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Create Society"><i class="mdi-social-location-city left"> </i>Create Society</button></a>
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
          <div id="table-datatables" class="padding10px">
              <div class="row">
                    <div style="height: 10px;"></div>
                <div class="col s12">
                  <table id="societytable" class="responsive-table display hoverable" >
                   <thead>
						<tr class="<s:text name="datatable.heading.bgcolor"/>">
							<th><s:text name="S.No."></s:text></th>
							<th><s:text name="Society Name"></s:text></th>
							<th><s:text name="Society Id"></s:text></th>
							<th><s:text name="Township Name"></s:text></th>
							<th><s:text name="Activation Key"></s:text></th>
							<th><s:text name="Country"></s:text></th>
							<th><s:text name="State"></s:text></th>
							<th><s:text name="City"></s:text></th>
							<th><s:text name="Text.action"></s:text></th>
						</tr> 
					</thead>
                  </table>
                </div>
              </div>
            </div> 
            <br>
            <div class="divider"></div> 
    </div>
  </div>
  			<s:form method="post" id="delgroupform">
					<s:hidden name="uniqSocietyId" id="uniqSocietyId"></s:hidden>
			</s:form>
			<s:form method="post" id="crtnewCmtyShrtctid">
				<s:hidden name="ivrTwsipid" id="ivrTwsipid"></s:hidden>
				<s:hidden name="ivrTwsipname" id="ivrTwsipname"></s:hidden>
				<s:hidden name="ivrSwityid" id="ivrSwityid"></s:hidden>
				<s:hidden name="ivrSwityname" id="ivrSwityname"></s:hidden>
			</s:form>
			<jsp:include page="../common/footer.jsp"></jsp:include>
    	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include> 
	<script type="text/javascript">	
	var oTable;
			 $(document).ready(function() {
					createoTable();						
				});
				function createoTable() {
					oTable = $('#societytable').DataTable({
						"jQueryUI" : false,
						"processing" : true,
						"serverSide" : true,
						"displayLength" : 10,
						"sAjaxSource" : "societyMgmtTable",						
						"sorting" : [],
						"paginationType" : "full_numbers",
						"columnDefs" : [ {
							"sortable" : false,
							"targets" : [ 0, 1, 2, 3, 4, 5, 6 ,7]
						} ],
					"fnDrawCallback": function( oSettings ) {
						$('.tooltipped').tooltip("remove");
						$('.tooltipped').tooltip({delay: 10});
						$('select').material_select();
						}
					});
					
				}
				
		 function generatekey(id,activekey,townshipname) {
			//var townshipid = $("#township_hidd_id").val();
			  swal({   
				  	 title: "<div class='<s:text name='alert.titleh2.color'/>'>Activation Key</div>",   			         
			         text: "<div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Society Name <span style=\"margin-left:12px;\">:</span></div> <div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp; "+townshipname+"&nbsp; </div></div> <div class='clear height10px'></div> <div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Activation Key <span style=\"margin-left:7px;\">:</span> </div><div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp;&nbsp;"+activekey+"&nbsp;</div></div></div>" ,
			        // title: "Activation Key",   
			        // text: "<div class='fontweight'>Society Name : "+townshipname+"</div><div class='clear height10px'></div><div class='fontweight'> Activation Key : "+activekey+"</div></div>" ,
			         html: true 
			    });
		} 
/* 		function addsociety(id) {
			$("#delgroupform").attr("action", "createsocietyform");
			$("#delgroupform").submit();
		} */	
		function addcommittemember(twnshipidid,twnshipnaname, sociteiid, socityname) {			
			$("#ivrTwsipid").val(twnshipidid);
			$("#ivrTwsipname").val(twnshipnaname);
			$("#ivrSwityid").val(sociteiid);
			$("#ivrSwityname").val(socityname);
			$("#crtnewCmtyShrtctid").attr("action", "createCommittee");
			$("#crtnewCmtyShrtctid").submit();
		}
		
		function deletesocyteaction(id) {
    		swal({    title: "Are you sure want to delete?",
                text: "You will not be able to recover this socytea detail!",   
                type: "warning",   
                showCancelButton: true,   
                confirmButtonColor: "#DD6B55",   
                confirmButtonText: "Yes",   
                closeOnConfirm: false }, 
                function(){ 
//                 	alert(id);
                	$("#uniqSocietyId").val(id);
        			$("#delgroupform").attr("action", "societyDeleteAction");
        			$("#delgroupform").submit();
                });
    		}
		
		function editsociety(id) {
			//alert(id);
			$("#uniqSocietyId").val(id);
			$("#delgroupform").attr("action", "societyEditAction");
			$("#delgroupform").submit();
		}
		function viewsociety(id) {			
			$("#uniqSocietyId").val(id);
			$("#delgroupform").attr("action", "societyViewAction");
			$("#delgroupform").submit();
		}
		function printData(id,groupcode) {
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'printSocietyData',
				data : "uniqSocietyId=" + id+"&groupcode="+groupcode,
				success : function(data) {
				/* 	document.write(data);
					window.print(); */
					var spli = data.split("!_!");
					if(spli[1]=="success"){
					 var mywindow = window.open('', 'my div', 'height=600,width=800,toolbar=0,scrollbars=0,status=0');
				        mywindow.document.write('<html><head><title></title>');
				        /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
				        mywindow.document.write('</head><body ><div id="header" style="background-color:White;"></div>');
				        mywindow.document.write(spli[0]);
				        mywindow.document.write('<div id="footer" style="background-color:White;"></div></body></html>');
				        mywindow.print();
				        mywindow.close();
					}else{
						//BootstrapDialog.alert('The society does not have root user'); 
					}
				}
			});
		}
function emailFunction(id,groupcode) {
			
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'societyEmailFunction',
				data : "uniqSocietyId=" + id+"&groupcode="+groupcode,
				success : function(data) {
				/* 	document.write(data);
					window.print(); */
					var spli = data.split("!_!");
					if(spli[1]=="success"){
						swal("An email has been sent successfully.");						
					}else{
						swal("The society does not have root user."); 						
					}
				}
			});
		}
</script>
  <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
    </body></html>	

