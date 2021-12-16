<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!--================================================================================
	Item Name: Materialize - Material Design Admin Template
	Version: 3.1
	Author: GeeksLabs
	Author URL: http://www.themeforest.net/user/geekslabs
================================================================================ -->
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
<link href="<s:text name='Resource.path'/>/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
			<section id="content"> <!--breadcrumbs start-->
			<div id="breadcrumbs-wrapper">
				<!-- Search for small screen -->
				<div class="header-search-wrapper grey hide-on-large-only">
					<i class="mdi-action-search active"></i> <input type="text"
						name="Search" class="header-search-input z-depth-2"
						placeholder="Explore Materialize">
				</div>
				<div class="container">
					<div class="row">
						<div class="col s12 m12 l12">
							<h5 class="breadcrumbs-title"><s:text name="Text.report.staffSalary" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Dashboard" /></a></li>
								<li><a href="#"><s:text name="Text.Report.Management" /></a></li>
								<li class="active"><s:text name="Text.report.staffSalary" /></li>

							</ol>
							<div class="right" id="xlspdfdownload">
							<a
							onclick="getencriptedData('<s:text name="socialindia.staffReportXLS"/>');">
							<button id="addaccountbuttonid" class="<s:text name="button.color.new.create"/>"
								style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Export As XLS">
										<i class="mdi-file-cloud-download left"> </i>XLS
									</button>
						</a>
								<a
									onclick="getencriptedData('<s:text name="socialindia.staffReportPDF"/>');">
									<button id="addaccountbuttonid"
										class="<s:text name="button.color.new.create1"/>"
										style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Export As PDF">
										<i class="mdi-file-cloud-download left"> </i>PDF
									</button>
								</a>
								
							</div>
						</div>

					</div>

				</div>

			</div>
			<!--breadcrumbs end--> <!--start container-->
			<iframe id="my_iframe" style="display:none;"></iframe>
			<div class="container"></div>
			<!--end container--> </section>

			<jsp:include page="../common/Alert.jsp" flush="true" />
			<div style="clear: both;"></div>
			<div class="col s12 m4 l3">
				<div class="row" style="margin-left: 12px;">
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
		<s:hidden id="societyId_session" value="%{#session.sSoctyIdStr}"></s:hidden>
		<s:if test="(#session.sSoctyId!=null && #session.sSoctyId !='' && #session.sSoctyId!='-1')">
			<s:hidden id="societyId_hidd_id" value="%{#session.sSoctyId}"></s:hidden>
			<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control " value="0" />
		</s:if>	
		<s:else>
			<div class="input-field col s2">
						<label for="township_txt_id"><s:text name="Text.adduser.town.ship.id" /></label>
					<s:textfield id="township_txt_id" name="usercreateObj.townshipName" data-error=".errorTxt1"  cssClass="form-control typeahead tt-query townshipIdvalidate"  autocomplete="off" />
					<s:hidden name="townshipid" id="township_hidd_id"  cssClass="form-control "  />
					</div>
					<div class="input-field col s2">
						<label for="societyId_txt_id"><s:text name="Text.adduser.societyname" /></label>
					<s:textfield name="societyName" id="societyId_txt_id" cssClass="typeahead tt-query  form-control"  autocomplete="off" spellcheck="false" />
					<s:hidden id="societyId_hidd_id" name="societyId" cssClass="form-control"/>
					</div>
		</s:else>
		
					<div class="input-field col s2">
						<button id="addaccountbuttonid"
							class="<s:text name="button.color.filter"/>"
							onclick="loadDataTableByFilter();" data-toggle="modal"
							data-position="right" data-delay="10"
							data-tooltip="Click to search" style="margin-top: 12px;"
							type="button">
							<i class="mdi-action-search "></i>Filter
						</button>
					</div>


				</div>
			</div>
			<div id="table-datatables" class="padding10px">
				<div class="row">
					<div style="height: 10px;"></div>
					<div class="col s12">
						<table id="example" class="responsive-table display hoverable" cellspacing="0" width="100%">
							<thead>
								<tr class="<s:text name="datatable.heading.bgcolor"/>">
												    <th><s:text name="S.No."></s:text></th>
													<th><s:text name="Text.name"></s:text></th>
													<th><s:text name="Text.Companyname"></s:text></th>
													<th><s:text name="Text.emailid"></s:text></th>
													<th><s:text name="Text.address"></s:text></th>
													<th><s:text name="Text.mobileno"></s:text></th>
													<th><s:text name="Text.entryDate"></s:text></th>
												</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<br>
			<div class="divider"></div>

		</div>
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
	<s:hidden id="fromdate_hidd" value=""></s:hidden>
	<s:hidden id="todate_hidd" value=""></s:hidden>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
		<script type="text/javascript" src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
	<script type="text/javascript">	
	var oTable;
		 $(document).ready(function() {
			 createoTable();
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
		 function townshipdata(valjson){
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
					if (typeof(objects_cardtype) != "undefined" && objects_cardtype.indexOf(this.value)!=-1) {					
			        }else{            
			        	  $('#township_txt_id').val('');        	         	 
			        	  $('#township_txt_id').focus();
				          $('#township_hidd_id').val('');
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
		 function createoTable() {
			 var fromdate = $("#dateFromid").val();
				var todate = $("#dateToid").val();
				var sTowshipId=$("#township_hidd_id").val();
				var sSoctyId=$("#societyId_hidd_id").val();
				if(sTowshipId==""){
					sTowshipId=-1;
				}
				if(sSoctyId==""){
					sSoctyId=-1;
				}
				oTable = $('#example').DataTable(
								{
									"jQueryUI" : false,
									"processing" : true,
									"serverSide" : true,
									"displayLength" : 10,
									"serverMethod": "POST",
									"scrollAutoCss": true,
									"ajaxSource" : "StaffUserReportdatatable?fromdate="
										+ fromdate + "&todate=" + todate+"&sSoctyId="+sSoctyId+"&sTowshipId="+sTowshipId,
									"sorting" : [],
									"paginationType" : "full_numbers",
									"columnDefs" : [ {
										"sortable" : false,
										"targets" : [ 0, 1, 2, 3 , 4,5,6]
									} ],
									"drawCallback": function( oSettings ) {
										if(oSettings._iRecordsTotal==0)
										{
										$("#xlspdfdownload").hide();
										}
										else
										{
										$("#xlspdfdownload").show();
										}
										$('.tooltipped').tooltip("remove");
										 $('.tooltipped').tooltip({delay: 10});
									    }
								});
			}
		 function loadDataTableByFilter() {
			var fromdate = $("#dateFromid").val(); // dd-MM-yyyy
			var todate = $("#dateToid").val(); // dd-MM-yyyy
			if(fromdate!='' && todate!='') {
				var frmDt = new Array(); var toDt = new Array();
				frmDt = fromdate.split("-");
                toDt = todate.split("-");
                var frm = frmDt[2]+"-"+frmDt[1]+"-"+frmDt[0];
                var to_date = toDt[2]+"-"+toDt[1]+"-"+toDt[0];
				var fromdatebj = new Date(frm); //Year-Month-Date
			    var toDateobj = new Date(to_date); //Year-Month-Date	
			    if (fromdatebj  <= toDateobj) {			    	  			    	   
			    	   //alert("To date is greather then From Date.");	
			    	var sTowshipId=$("#township_hidd_id").val();
					var sSoctyId=$("#societyId_hidd_id").val();
					if(sTowshipId==""){
						sTowshipId=-1;
					}
					if(sSoctyId==""){
						sSoctyId=-1;
					}
					oTable.destroy();
					$("#fromdate_hidd").val(fromdate);
					$("#todate_hidd").val(todate);
				 	oTable = $('#example').DataTable({
								"jQueryUI" : false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"serverMethod": "POST",
								"scrollAutoCss": true,
								"ajaxSource" : "StaffUserReportdatatable?fromdate="
									+ fromdate + "&todate=" + todate+"&sSoctyId="+sSoctyId+"&sTowshipId="+sTowshipId,
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3 , 4,5,6]
								} ],
								"drawCallback": function( oSettings ) {
									if(oSettings._iRecordsTotal==0) {
									$("#xlspdfdownload").hide();
									} else {
									$("#xlspdfdownload").show();
									}
									$('.tooltipped').tooltip("remove");
									 $('.tooltipped').tooltip({delay: 10});
									 $('select').material_select();
								    }
							});
			    } else {
			    	swal("From date should less then To date. Change your from date and to date");   
				}				
			} else {
				if(fromdate=='' && todate==''){
					swal("Select From Date And To Date");	
				}else if(fromdate==''){
					swal("Please Select From Date");	
				}else if(todate==''){
					swal("Please Select To Date");	
				}
				oTable.destroy();
				createoTable();
			}
		}
		 
			function getencriptedData(url){
				//toShowLoadingImgoverlay();
				var fromdate = $("#fromdate_hidd").val();
				var todate = $("#todate_hidd").val();
				var sTowshipId=$("#township_hidd_id").val();
				var sSoctyId=$("#societyId_hidd_id").val();
				var sessionSociety = $("#societyId_session").val();
				if(sTowshipId==""){
					sTowshipId=-1;
				}
				if(sSoctyId==""){
					sSoctyId=-1;
				}
				$.ajax({
					type : 'post',
					datatype : 'html',
					url : 'getencryptedData',
					data : 'fromdate=' + fromdate+'&todate='+todate+"&sSoctyId="+sSoctyId+"&sTowshipId="+sTowshipId+"&crntsocietyid="+sessionSociety,
					success : function(data) {
						url=url+"?"+data;
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
		
</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
</body>
</html>