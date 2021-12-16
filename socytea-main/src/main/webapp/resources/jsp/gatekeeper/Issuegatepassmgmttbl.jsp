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
                <h5 class="breadcrumbs-title"><s:text name="Breadcrumb.manage.gatepass" /></h5>
                <ol class="breadcrumbs left">
                   <li><a href="posthomepage"><s:text name="Text.Home" /></a></li>
				<li><a href="#"><s:text name="Breadcrumb.manage.gatepass"/></a></li>
                    <li class="active"><s:text name="Breadcrumb.manage.issuegatepass" /></li>
                   </ol>
                    <div class="right">
                      <a href="creategatepassform"><button type="button" class="<s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="<s:text name="Text.create.gatepass"/>">
                      <i class="<s:text name="crt.button.icon.event"/>"> </i><s:text name="Text.create.gatepass"/></button></a>
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
          <div id="table-datatables">
              <div class="row">
                    <div style="height: 10px;"></div>
                <div class="col s12">
                  <table id="example" class="responsive-table display" >
                   <thead>
						<tr class="<s:text name="datatable.heading.bgcolor"/>">
							 <th><s:text name="Text.table.sno"></s:text></th>
							<th><s:text name="Text.visitorname"></s:text></th>
							<th><s:text name="Text.visitorno"></s:text></th>
							<th><s:text name="Text.datevisit"></s:text></th>
							<th>Visitor Mobile No.</th>
							<th><s:text name="Text.passtype"></s:text></th>
							<th><s:text name="Text.action"></s:text></th>
						</tr>
					</thead>
                  </table>
                </div>
              </div>
            </div>
            	<s:form method="post" id="delgroupform">
					<s:hidden name="uniqueId" id="uniqgatepassIdEdit"></s:hidden>
					<s:hidden name="uniqueGatepassno" id="uniqgatepassnoEdit"></s:hidden>
			</s:form>
            <div class="divider"></div>
    </div>
  </div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
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
						"sAjaxSource" : "issuegatepassmgmttbl",
						"sorting" : [],
						"paginationType" : "full_numbers",
						"columnDefs" : [ {
							"sortable" : false,
							"targets" : [ 0, 1, 2, 3, 4, 5, 6]
						} ],
					"fnDrawCallback": function( oSettings ) {
						$('.tooltipped').tooltip("remove");
						$('.tooltipped').tooltip({delay: 10});
						$('select').material_select();
						}
					});
				}
			    	function deletegatepassaction(id) {
			    		swal({    title: "Are you sure want to delete?",
			                text: "You will not be able to recover this gatepass detail!",
			                type: "warning",
			                showCancelButton: true,
			                confirmButtonColor: "#DD6B55",
			                confirmButtonText: "Yes",
			                closeOnConfirm: false },
			                function(){
			                	$("#uniqgatepassIdEdit").val(id);
								$("#delgroupform").attr("action", "deletegatepass");
								$("#delgroupform").submit();
			                });
			    		}

			    	function Reusegatepass(id,gatepassno) {
						$("#uniqgatepassIdEdit").val(id);
						$("#uniqgatepassnoEdit").val(gatepassno);
						toShowLoadingImgoverlay();
						$("#delgroupform").attr("action", "reusegatepassaction");
						$("#delgroupform").submit();
					}


			    	function viewgatepassaction(id) {
			    		$("#uniqgatepassnoEdit").val(id);
			    		toShowLoadingImgoverlay();
			    		$("#delgroupform").attr("action", "viewGatepassAction");
			    		$("#delgroupform").submit();
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
		      
		  
		      function printData(id) {
		    		$.ajax({
		    			type : 'post',
		    			datatype : 'html',
		    			url : 'gatepassprintData',
		    			data : "uniqueGatepassno=" + id,
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
		    					BootstrapDialog.alert('The society does not have root user'); 
		    				}
		    			}, error:function(dd){alert("dd")}
		    		});
		    	}
</script>
  <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
    </body></html>

