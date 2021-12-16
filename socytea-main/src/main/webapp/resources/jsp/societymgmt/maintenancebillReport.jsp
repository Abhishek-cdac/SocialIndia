	<!-- START MAIN -->
	<div id="main2">
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
							<h5 class="breadcrumbs-title"><s:text name="Text.no.of.maintenancebill" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Dashboard" /></a></li>
								<li><a href="#"><s:text name="Text.Report.Management" /></a></li>
								<li class="active"><s:text name="Text.no.of.maintenancebill" /></li>

							</ol>
							<div class="right" id="xlspdfdownload">
							<a onclick="getencriptedData('<s:text name="socialindia.maintenancebillReportXLS"/>');">
							<button id="addaccountbuttonid" class="<s:text name="button.color.new.create"/>"
								style="width: 200px" data-target="#addnewaccount"
										data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>"
										data-tooltip="Export As XLS">
										<i class="mdi-file-cloud-download left"> </i>XLS
									</button>
						</a>
								<a onclick="getencriptedData('<s:text name="socialindia.maintenancebillReportPDF"/>');">
									<button id="addaccountbuttonid" style="width: 200px"
										class="<s:text name="button.color.new.create1"/>"
										data-target="#addnewaccount"
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
			<iframe id="my_iframe" style="display:none;"></iframe>
			<div class="container"></div>
			<!--end container--> </section>
			<jsp:include page="../common/Alert.jsp" flush="true" />
			<s:hidden id="societyId_session" value="%{#session.sSoctyIdStr}"></s:hidden>			
			<div style="clear: both;"></div>
			<div class="col s12 m4 l3">
				<div class="row">
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
							<s:fielderror cssClass="error" name="fromerror"/>
					</div>
					<div class="input-field col s2">
						<button id="addaccountbuttonid" class="<s:text name="button.color.filter"/>"
							onclick="loadDataTableByFilter();" data-toggle="modal"
							data-position="right" data-delay="10"
							data-tooltip="Click to search" style="margin-top: 12px;"
							type="button">
							<i class="mdi-action-search "></i>Filter
						</button>
					</div>

				</div>
			</div>
			<div id="table-datatables">
				<div class="row">
					<div class=" col s12">
						<table id="example" class="responsive-table display hoverable" cellspacing="0" width="100%">
							<thead>
								<tr class="<s:text name="datatable.heading.bgcolor"/>">
									<th><s:text name="Text.table.sno" /></th>
									<th><s:text name="Text.Form.FileName" /></th>
									<th><s:text name="Text.no.of.record" /></th>
									<th><s:text name="Text.no.of.grandtotal" /></th>
									<th><s:text name="Text.adduser.society.id" /></th>
									<th><s:text name="Text.status" /></th>
									<th><s:text name="Text.Utility.feedback.Management.EntryDate"/></th>
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


	<s:hidden id="fromdate_hidd" value=""></s:hidden>
	<s:hidden id="todate_hidd" value=""></s:hidden>
	<form id="downloadFormid" method="post" action="downloadfiles">
		<s:hidden id="downloadFormval" name="fileName" value=""></s:hidden>
		<s:hidden id="downloadFormvalpath" name="fileNamePath" value=""></s:hidden>
	</form>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<jsp:include page="../common/loading.jsp"></jsp:include>
	<script type="text/javascript">
	 var oTable;
		$(document).ready(function() {							
			createoMNTNCTable();
		});
		function createoMNTNCTable() {
			var fromdate = $("#dateFromid").val();
			var todate = $("#dateToid").val();
			oTable = $('#example').DataTable({
							"jQueryUI" : false,
							"processing" : true,
							"serverSide" : true,
							"displayLength" : 10,
							"serverMethod": "POST",
							"scrollAutoCss": true,			
							"ajaxSource" : "maintenancebillReportTbl?fromdate="
								+ fromdate + "&todate=" + todate,
							"sorting" : [],
							"paginationType" : "full_numbers",
							"columnDefs" : [ {
								"sortable" : false,
								"targets" : [ 0, 1, 2, 3, 4, 5, 6 ]
							} ],
							"drawCallback": function( oSettings ) {
								 $('select').material_select();
								if(oSettings._iRecordsTotal==0) {
								} else {
									$("#xlspdfdownload").show();
								}
									$('.tooltipped').tooltip("remove");
								 	$('.tooltipped').tooltip({delay: 10});
							    }
						});							  										
		}
		function loadDataTableByFilter() {
			var fromdate = $("#dateFromid").val(); // dd-MM-yyyy
			var todate = $("#dateToid").val();// dd-MM-yyyy
			if(fromdate!='' && todate!=''){
				var frmDt = new Array(); var toDt = new Array();
				frmDt = fromdate.split("-");
	            toDt = todate.split("-");
	            var frm = frmDt[2]+"-"+frmDt[1]+"-"+frmDt[0];
	            var to_date = toDt[2]+"-"+toDt[1]+"-"+toDt[0];
				var fromdatebj = new Date(frm); //Year-Month-Date
			    var toDateobj = new Date(to_date); //Year-Month-Date	
			    if (fromdatebj  <= toDateobj) {			    	  			    	   
			    	   //alert("To date is greather then From Date.");
			    	$("#fromdate_hidd").val(fromdate);
					$("#todate_hidd").val(todate);
					oTable.destroy();
					oTable = $('#example').DataTable({
								"jQueryUI" : false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"ajaxSource" : "maintenancebillReportTbl?fromdate="
										+ fromdate + "&todate=" + todate,
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3, 4, 5, 6]
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
				
			}else{
				if(fromdate=='' && todate==''){
					 swal("Select From Date And To Date");	
				}else if(fromdate==''){
					 swal("Please Select From Date");	
				}else if(todate==''){
					 swal("Please Select To Date");	
				}
				
			}
			
		}
		function getencriptedData(url) {
			//toShowLoadingImgoverlay();
			var fromdate = $("#fromdate_hidd").val();
			var todate = $("#todate_hidd").val();
			var sessionSociety = $("#societyId_session").val();
			$.ajax({
				type : 'post',
				datatype : 'html',
				url : 'getencryptedData',
				data : 'fromdate=' + fromdate + '&todate=' + todate+"&crntsocietyid="+sessionSociety,
				success : function(data) {
					url = url + "?" + data;					
					document.getElementById('my_iframe').src = url;
					//window.location.href = url;
					$('#my_iframe').ready(function(){
					 });
											
				},
				error : function(d) {
					//tohideLoadingImgoverlay();
				}
				
			});
			
		}  
		function submitgivenform(subval){
			$("#downloadFormval").val(subval);
			$("#downloadFormid").submit();
			
		}
		
	</script>
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
	</div>
	<!-- END MAIN -->