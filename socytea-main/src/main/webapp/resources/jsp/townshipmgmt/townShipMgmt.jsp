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
                <h5 class="breadcrumbs-title"><s:text name="Township Management" /></h5>
                <ol class="breadcrumbs left">
                  	<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
					<li><a href="townshipmgmt"><s:text name="Breadcrumb.manage" /></a></li>
                    <li class="active"><s:text name="Breadcrumb.manage.townshipmgmt" /></li>             
                   </ol>
                  <div class="right">
                      <a href="createtownshipform"><button type="button" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Create Township"><i class="mdi-communication-business left"> </i>Create Township</button></a>
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
                  <table id="example" class="responsive-table display hoverable">
                   <thead>
						<tr class="<s:text name="datatable.heading.bgcolor"/>">
							<th><s:text name="Text.table.sno"></s:text></th>
							<th><s:text name="Text.adduser.town.ship.id"></s:text></th>
							<th><s:text name="Township Id"></s:text></th>
							<th><s:text name="Text.customerreg.activation.key"></s:text></th>
							<th><s:text name="Text.country"></s:text></th>
							<th><s:text name="Text.state"></s:text></th>
							<th><s:text name="Text.city"></s:text></th>
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
		<s:hidden name="uniqTownShipIdEdit" id="uniqTownShipIdEdit"></s:hidden>
		<s:hidden name="deletestaffid" id="deletestaffidEdit"></s:hidden>
	</s:form>
	<s:form method="post" id="crtnewStysShrtctid">
		<s:hidden name="ivrTwsipid" id="ivrTwsipid"></s:hidden>
		<s:hidden name="ivrTwsipname" id="ivrTwsipname"></s:hidden>
	</s:form>
			<jsp:include page="../common/footer.jsp"></jsp:include>
			  <jsp:include page="../common/clorbox.jsp"></jsp:include>
      	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include> 
	<script type="text/javascript">	
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
						"sAjaxSource" : "townShipMgmtTable",						
						"sorting" : [],
						"paginationType" : "full_numbers",
						"columnDefs" : [ {
							"sortable" : false,
							"targets" : [ 0, 1, 2, 3, 4, 5, 6 ]
						} ],
					"fnDrawCallback": function( oSettings ) {
						$('.tooltipped').tooltip("remove");
						$('.tooltipped').tooltip({delay: 10});
						$('select').material_select();
						}
					});
					
				}
		function edittownship(id) {
			$("#uniqTownShipIdEdit").val(id);
			$("#delgroupform").attr("action", "edittownshipform");
			$("#delgroupform").submit();
		}
		function viewtownship(id) {
			$("#uniqTownShipIdEdit").val(id);
			$("#delgroupform").attr("action", "viewtownshipform");
			$("#delgroupform").submit();
		}
		
		 function generatekey(id,activekey,townshipname) {
			var townshipid = $("#township_hidd_id").val();
			  swal({   
			         title: "<div class='<s:text name='alert.titleh2.color'/>'>Activation Key</div>",   			         
			         text: "<div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Township Name <span style=\"margin-left:12px;\">:</span> </div> <div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp; "+townshipname+"&nbsp; </div></div> <div class='clear height10px'></div> <div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Activation Key <span style=\"margin-left:24px;\">:</span></div> <div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp;&nbsp;"+activekey+"&nbsp;</div></div></div>" ,
			         html: true 
			    });
		} 
		function addsociety(id, twnshipnaname) {
			$("#ivrTwsipid").val(id);
			$("#ivrTwsipname").val(twnshipnaname);
			$("#crtnewStysShrtctid").attr("action", "createsocietyform");
			$("#crtnewStysShrtctid").submit();
		}			 		
</script>
  <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
    </body></html>	 
