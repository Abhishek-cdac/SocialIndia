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
            <jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Breadcrumb.reconresult" /></h5>
                <ol class="breadcrumbs left">
                  <li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
				  <li><a href="#"><s:text name="Breadcrumb.Reconcile" /></a></li>
				  <li class="active"><s:text name="Breadcrumb.reconresult" /></li>						             
                </ol>
             
                 <div class="right">
                     <button type="button" style="" data-position="bottom" data-delay="10" data-tooltip="To Do Reconcile" class=" <s:text name="button.color.new.create1"/>" data-toggle="modal" data-target="#addnewaccount" onclick="tocallreconcile()"><i class="<s:text name="crt.button.icon.reconcile"/>"> </i>To Do Reconcile</button>                                     		
               		 <button type="button" data-position="bottom" data-delay="10" data-tooltip="Refresh" class=" <s:text name="button.color.new.create1"/>" data-toggle="modal" data-target="#addnewaccount" onclick="refreshDatatable()"><i class="<s:text name="crt.button.icon.refresh"/> "> </i>Refresh</button> 
                </div>                                       					
              </div>              
            </div>            
          </div>          
        </div>
        <div class="container">
       
      <jsp:include page="../common/Alert.jsp" flush="true" />
      <iframe id="my_iframe" style="display:none;"></iframe>
      <div style="clear: both;"></div>
      <div class="col s12 m4 l3">
		<div class="row" >
				<s:if test="(#session.sSoctyId!=null && #session.sSoctyId !='' && #session.sSoctyId!='-1')">
					<s:hidden id="societyId_hidd_id" value="%{#session.sSoctyId}"></s:hidden>
					<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control " value="0" />
				</s:if>	
		 		<s:else>
		 		<s:hidden id="societyId_hidd_id" value="%{#session.sSoctyId}"></s:hidden>
					<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control " value="0" />
					<%-- <div class="input-field col s2">
						<label for="township_txt_id"><s:text name="Text.adduser.town.ship.id" /></label>
						<s:textfield id="township_txt_id" name="usercreateObj.townshipName" data-error=".errorTxt1"  cssClass="form-control typeahead tt-query townshipIdvalidate"  autocomplete="off" />
						<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control "  />
					</div>
					<div class="input-field col s2">
						<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /></label>
						<s:textfield name="societyName" id="societyId_txt_id" cssClass="typeahead tt-query  form-control"  autocomplete="off" spellcheck="false" />
						<s:hidden id="societyId_hidd_id" name="societyId" cssClass="form-control"/>
					</div> --%>
				</s:else> 
					<div class="input-field col s2">
						<!-- If have any search filter -->
						<label for="dateFromid">From Date [dd-mm-yyyy]</label>
						<s:textfield name="dateFrom" id="dateFromid" cssClass="dateOfBirth"
							onkeyup="textValidate(this,'20','DT')" />
					</div>
					<div class="input-field col s2">
						<label for="dateToid">To Date [dd-mm-yyyy]</label>
						<s:textfield name="dateTo" id="dateToid" cssClass="dateOfBirth"
							onkeyup="textValidate(this,'20','DT')" />
					</div>
					<div class="input-field col s2">
						<label for="slectfield">Select Fields</label>
						<s:textfield name="slectfield" id="slectfield" cssClass="typeahead tt-query  form-control" onchange="selectField()"/>
						<s:hidden id="slectfield_hidd_id" name="slectfield_hidd_id" cssClass="form-control"/>
					</div>
					<!-- <div class="input-field col s4" id="amounts" style="display: none;"> -->
					<div class="input-field col s2" id="staramount" style="display: none;">
						<label for="startamount">Start Amount</label>
						<s:textfield name="startamount" id="startamount" cssClass="form-control" onkeyup="textValidate(this,'12','MNM')"/>
					</div>
					<div class="input-field col s2" id="enddamount" style="display: none;">
						<label for="endamount">End Amount</label>
						<s:textfield name="endamount" id="endamount" cssClass="form-control" onkeyup="textValidate(this,'12','MNM')"/>
					</div>
					<div class="input-field col s2" id="transactiontypes" style="display: none;">
						<label for="typetrans" >Transaction Type</label>
						<s:textfield name="typetrans" id="typetrans" cssClass="typeahead tt-query  form-control"/>
						<s:hidden id="typetrans_hidd_id" name="typetrans_hidd_id" cssClass="form-control"/>
					</div>
			<!-- </div> -->
					<div class="input-field col s2">
						<button id="addaccountbuttonid"
							class="<s:text name="button.color.filter"/>"
							onclick="filter();" data-toggle="modal"
							data-position="right" data-delay="10"
							data-tooltip="Click to search" style="margin-top: 12px;"
							type="button">
							<i class="mdi-action-search "></i>Filter
						</button>
					</div>


				</div>
			</div>
       <div class="col s12 m4 l3 padding10px">
                
						<div class="right" id="xlspdfdownload">
							<a onclick="getencriptedData('<s:text name="socialindia.reconicileReportXLS"/>');">
							<button id="addaccountbuttonid" class="<s:text name="button.color.new.create"/>"
								style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Export As XLS">
										<i class="mdi-file-cloud-download left"> </i>XLS
									</button>
						</a>
								<a onclick="getencriptedData('<s:text name="socialindia.reconicileReportPDF"/>');">
									<button id="addaccountbuttonid"
										class="<s:text name="button.color.new.create1"/> "
										style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Export As PDF">
										<i class="mdi-file-cloud-download left"> </i>PDF
									</button>
								</a>
								
			 </div>
             </div>										      
        <div id="table-datatables" class="padding10px">              
              <div class="row">
                    <div style="height: 10px;"></div>
                <div class="col s12">
                  <table id="example" class="responsive-table display hoverable" >
                   <thead>
						<tr class="<s:text name="datatable.heading.bgcolor"/>">
						<th ><s:text name="Table.recnhead.sno"></s:text></th>
						<th ><s:text name="Table.recnhead.orderno"></s:text></th>
						<th ><s:text name="Table.recnhead.amount"></s:text></th>
						<th ><s:text name="Transaction Type"></s:text></th>
						<th ><s:text name="Table.recnhead.transaction"></s:text></th>
						<th><s:text name="Table.recnhead.reconicile"></s:text></th>
						<!-- <th ><s:text name="Table.recnhead.marchstatus"></s:text></th>-->
						<th><s:text name="Table.rechhead.reason"></s:text></th>
						</tr>
					</thead>
                  </table>
                  	
				<s:form method="post" id="delgroupform">
					<s:hidden  name="flg" value="paygate" id="uniqSocietyId"></s:hidden>
					<s:hidden name="paygatecyberId" id="materialid"></s:hidden>
				</s:form>
			
                </div>
              </div>
            </div> 
            <br/>
            <div class="divider"></div> 
            <!--DataTables example Row grouping-->
             </div>
      </section>
    </div>
    <!-- END WRAPPER -->
  </div>
  <!-- END MAIN -->	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>  
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/imageupload/jquery.ajaxfileupload.js?cxxxc"></script>
	<script type="text/javascript">

	var oTable;
	$(document).ready(function() {
			createoTable();
			selectfieldfun();
			selecttransaction();
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'gettownshipOnload',
				//data : 'uniqTownShipIdEdit=' + townshipid,
				success : function(data) {
					var spli=data.split("!_!");
					townshipdata(spli[1]);
				}
			});
	});
	function townshipdata(valjson) {
				valjson=valjson.replace(/&quot;/ig,'"');
				var locval = JSON.parse(valjson);	
				var objects_cardtype;
			 $('#township_txt_id').typeahead({
				     	order: "asc",
						hint: true,
							accent: true,
							offset: true,
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
				        $('#township_hidd_id').val(map[item].id);
				        getsociety(map[item].id);
				        return item;
				    }
				    
				});
			 $('#township_txt_id').blur(function(){				
					if (objects_cardtype.indexOf(this.value)!=-1) {					
			        }else{            
			        	  $('#township_txt_id').val('');        	         	 
			        	  $('#township_txt_id').focus();
				          $('#township_hidd_id').val('');
			          }		
			      });
			 
	}
	function getsociety(townid){				
					var temp="";
					$.ajax({
				 		type : 'post',
				 		datatype : 'html',
				 		url : 'townshipgetsociety',
				 		data : 'townshipid=' + townid+"&clfor=autocomp",
				 		success : function(data) { 			
				 			var spl=data.split("!_!"); 			
				 			temp=spl[1]; 	
				 			getsociety_auto(spl[1]);
				 			//return temp;
				 		}
				 	});
	} 
			 
	function getsociety_auto(tempdata){
					tempdata=tempdata.replace(/&quot;/ig,'"');
					var locval = JSON.parse(tempdata);	
					var map_scty;
					 $('#societyId_txt_id').val('');        	         	 		   
				  
				$('#societyId_txt_id').typeahead('refresh');
				    $('#societyId_txt_id').typeahead('destroy');
					$('#societyId_txt_id').typeahead({
				     		order: "asc",
							hint: true,
							accent: true,
							offset: true,
							searchOnFocus: true, 
				        	source: function(query, process) {
				        	objects_scty = [];  
				        	map_scty = {};		      
				        	var data = locval;
				        	$.each(data, function(i, object) {
				        		map_scty[object.label] = object;
				        		objects_scty.push(object.label);
				        	});
				        	process(objects_scty);
				       		$(".dropdown-menu").css("height", "auto");
				       		$(".dropdown-menu").addClass("form-control");
				    },
				    updater: function(item) {
				        $('#societyId_hidd_id').val(map_scty[item].id);
				        return item;
				    }
				  //add dynamical validate - 2
				});		
					$('#societyId_txt_id').typeahead('refresh');

					
						
			$('#societyId_txt_id').blur(function(){				
				if (typeof(objects_scty) != "undefined" && objects_scty.indexOf(this.value)!=-1) {	
			     }else{            
			     	  $('#societyId_txt_id').val('');        	         	 
			     	  $('#societyId_txt_id').focus();
				      $('#societyId_hidd_id').val('');
				      $('#societyId_txt_id').typeahead('destroy');
			       }		
			   });		
			}
	 function selecttransaction(){
			var objects_slectfield;		
			var locval = [{"id":"1","label":"Utility pay"},{"id":"2","label":"Maintenance pay"}];				
			$('#typetrans').typeahead({
				order: "asc",
				hint: true,
				accent: true,
				offset: true,
				searchOnFocus: true, 
				source: function(query, process) {
					    objects_slectfield = [];  
					    map = {};		      
					    var data = locval;
					    $.each(data, function(i, object) {
					        map[object.label] = object;
					        objects_slectfield.push(object.label);
					    });
					    process(objects_slectfield);
					       $(".dropdown-menu").css("height", "auto");
					       $(".dropdown-menu").addClass("form-control");
					    },
					    updater: function(item) {
					        $('#typetrans_hidd_id').val(map[item].id);
					        return item;
					    }
					});
				 $('#typetrans').blur(function(){				
						if (objects_slectfield != undefined && objects_slectfield.indexOf(this.value)!=-1) {			
				        }else{            
				        	  $('#typetrans').val('');        	         	 
				        	  $('#typetrans').focus();
					          $('#typetrans_hidd_id').val('');	          
				          }		
				      });
					}
	
		 function selectfieldfun(){
			var objects_slectfield;		
			var locval = [{"id":"","label":"All"},{"id":"1","label":"Match"},{"id":"2","label":"Un Match"},{"id":"3","label":"Reconcile Success"},{"id":"4","label":"Reconcile Failed"},{"id":"5","label":"Amount"},{"id":"6","label":"Transaction Type"}];				
			$('#slectfield').typeahead({
				order: "asc",
				hint: true,
				accent: true,
				offset: true,
				searchOnFocus: true, 
				source: function(query, process) {
					    objects_slectfield = [];  
					    map = {};		      
					    var data = locval;
					    $.each(data, function(i, object) {
					        map[object.label] = object;
					        objects_slectfield.push(object.label);
					    });
					    process(objects_slectfield);
					       $(".dropdown-menu").css("height", "auto");
					       $(".dropdown-menu").addClass("form-control");
					    },
					    updater: function(item) {
					        $('#slectfield_hidd_id').val(map[item].id);
					        return item;
					    }
					});
				 $('#slectfield').blur(function(){				
						if (objects_slectfield != undefined && objects_slectfield.indexOf(this.value)!=-1) {			
				        }else{            
				        	  $('#slectfield').val('');        	         	 
				        	  $('#slectfield').focus();
					          $('#slectfield_hidd_id').val('');	          
				          }		
				      });
					}

		function createoTable() {
			oTable = $('#example').DataTable({
				"jQueryUI" : false,
				"processing" : true,
				"serverSide" : true,
				"displayLength" : 10,
								"ajaxSource" : "reconicileMgmttbl",						
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3, 4, 5,6]
								} ],
							"fnDrawCallback": function( oSettings ) {
								$('.tooltipped').tooltip("remove");
								$('.tooltipped').tooltip({delay: 10});
								$('select').material_select();
								}
							});
			
		}
		function filter() {
			var startamount;
			var endamount;
			var transtype;
			var fromdate = $("#dateFromid").val();
			var townshipid = $("#township_hidd_id").val();
			var societyid = $("#societyId_hidd_id").val();	
			var todate = $("#dateToid").val();
			var selectId = $("#slectfield_hidd_id").val();
			if(selectId=='5'){
			startamount = $("#startamount").val();		
			endamount = $("#endamount").val();
			}else{
				startamount="";
				endamount="";
			}
			if(selectId=='6'){
				transtype = $("#typetrans_hidd_id").val();		
				}else{
					transtype="";
				}
			
			if(societyid!=null && typeof(societyid) != "undefined" && societyid!=''){
				
			}else{
				societyid="";
			}
			 oTable.destroy();				
			 	oTable = $('#example')
					.DataTable({
						"jQueryUI" : false,
						"processing" : true,
						"serverSide" : true,
						"displayLength" : 10,
								"ajaxSource" :"reconicileMgmttbl?fromdate="+ fromdate+"&townShipId="+townshipid+"&societyId="+societyid+"&todate="+todate+"&selectId="+selectId+"&startamount="+startamount+"&endamount="+endamount+"&transtype="+transtype,					
									"sorting" : [],
									"paginationType" : "full_numbers",
									"columnDefs" : [ {
										"sortable" : false,
										"targets" : [ 0, 1, 2, 3, 4, 5,6]
									} ],
								"fnDrawCallback": function( oSettings ) {
									$('.tooltipped').tooltip("remove");
									$('.tooltipped').tooltip({delay: 10});
									$('select').material_select();
									}
								});
		}
		
 function deletepaygate(id) {
		swal({    title: "Are you sure want to delete?",
         text: "You will not be able to recover this file detail!",   
         type: "warning",   
         showCancelButton: true,   
         confirmButtonColor: "#DD6B55",   
         confirmButtonText: "Yes",   
         closeOnConfirm: false }, 
         function(){ 
         	$("#materialid").val(id);
				$("#delgroupform").attr("action", "deletepaygate");
				$("#delgroupform").submit();
        // swal("Deleted!", "User has been deleted.", "success"); 
         });
		}
		
		function onchangetownshipId() {
			var townshipid = $("#township_hidd_id").val();
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'townshipgetsociety',
				data : 'townshipid=' + townshipid,
				success : function(data) {
					$("#societyId_hidd_id").html(data);
					$('#societyId_hidd_id').material_select();
					$("#societyId_hidd_id").val('');
					$("#slectfield_hidd_id").val('');
					$("#usersearchname").val('');
				},
				error : function(d) {
				}
			});
		}
		function submitgivenform(subval){
			$("#downloadFormval").val(subval);
			$("#downloadFormid").submit();
			
		}

		
		$("#township_txt_id").change(function(){
			$('#societyId_txt_id').val('');
			$('#societyId_hidd_id').val('');
			$('#slectfield').val('');
			$('#slectfield_hidd_id').val('');
			$('#usersearchname').val('');
			});
		
		$("#societyId_txt_id").change(function(){
			$('#slectfield').val('');
			$('#slectfield_hidd_id').val('');
			$('#usersearchname').val('');
			});
		
		$("#slectfield").change(function(){
		$('#usersearchname').val('');
		});
		
		
		function getencriptedData(url){
			//toShowLoadingImgoverlay();
			var fromdate = $("#dateFromid").val();
			var todate = $("#dateToid").val();
			var sTowshipId=$("#township_hidd_id").val();
			var sSoctyId=$("#societyId_hidd_id").val();
			var selectid=$("#slectfield_hidd_id").val();
			var startamount=$("#startamount").val();
			var endamount=$("#endamount").val();
			var transtype=$("#typetrans_hidd_id").val();
			if(sTowshipId==""){
				sTowshipId=-1;
			}
			if(sSoctyId==""){
				sSoctyId=-1;
			}
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'getencryptedReport',
				data : 'fromdate=' + fromdate+'&todate='+todate+"&sSoctyId="+sSoctyId+"&sTowshipId="+sTowshipId+"&selectid="+selectid+"&startamount="+startamount+"&endamount="+endamount+"&transtype="+transtype,
				success : function(data) {
					url=url+"?"+data;
					//e.preventDefault();
					document.getElementById('my_iframe').src = url;
					//window.location.href = url;
					$('#my_iframe').ready(function(){
				    	//tohideLoadingImgoverlay();
					 });
				},
				error : function(d) {
					//tohideLoadingImgoverlay();
				}
			});
			
		}
		function refreshDatatable(){
			oTable.destroy();		
			createoTable();
		}
		function tocallreconcile(){
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'extractfile',// Call Reconcile procedure
				success : function(data) {
					swal("Reconcile!", "Reconcile process will started. Please wait some time. Click refresh button.", "success"); 
				},
				error : function(d) {
					swal("Reconcile!", "Reconcile process not started. Please wait some time. Click refresh button.", "success"); 
				}
			});	
		}
 		function selectField(){
			var selectId=$("#slectfield_hidd_id").val();
			if(selectId=='5'){
				$("#staramount").show();
				$("#enddamount").show();
			
			}else if(selectId=='6'){
				$("#transactiontypes").show();
			
			}
			else{
				$("#staramount").hide();
				$("#enddamount").hide();
			}
			
		 }
		
	</script>
	 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>

