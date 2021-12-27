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
					<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">
									<s:text name="Text.complaint.Management" />								
								</h5>
						<ol class="breadcrumbs left">
									<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
									<li><a href="#"><s:text name="Breadcrumb.utility"/></a></li>
									<li class="active"><s:text name="Breadcrumb.manage.complaintmgmt" /></li>  
								</ol>
								<div class="right">
								<form id="searchflt" action="" method="post">
								<s:if test="(#session.sSoctyId!=null && #session.sSoctyId !='' && #session.sSoctyId!='-1')">
								<!--  <button id="createGrpbtn" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#createsmallmodal"  data-backdrop="static"/>New</button>-->
								 <button id="createGrpbtn" type="button"  onclick="newComplaintCreation();" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Add New Complaint"><i class="<s:text name="crt.button.icon.complaints"/>"> </i><s:text name="text.newcomplaint"/></button>		
								 </s:if>						
								</form></div>
							</div>
						</div>
					</div>
				</div>
			<!--breadcrumbs end--> <!--start container-->
			<div class="container"></div>
			<!--end container--> </section>

			<jsp:include page="../common/Alert.jsp" flush="true" />
			<div style="clear: both;"></div>
			<div class="col s12 m4 l3 padding10px">
				<div class="row">
					<s:if test="(#session.sSoctyId!=null && #session.sSoctyId !='' && #session.sSoctyId!='-1')">
						<div class="input-field col s2">
						<label for="slectfield"><s:text name="Text.selectfield.set" /></label>
					<s:textfield name="labRegObj.wrktypname" id="slectfield" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false"/>
					<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id" class="form-control"/>  
					</div>
					 <div class="input-field col s2">
						<label for="usersearchname"><s:text name="text.search" /></label>
					<s:textfield name="usersearchname" id="usersearchname" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false" />
					</div>
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
					<div class="input-field col s2">
					<label for="slectfield"><s:text name="Text.selectfield.set" /></label>
					<s:textfield name="labRegObj.wrktypname" id="slectfield" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false" />
					<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id" class="form-control"/>  
					</div>
					 <div class="input-field col s2">
					<label for="usersearchname"><s:text name="text.search" /></label>
					<s:textfield name="usersearchname" id="usersearchname" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false" />
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
													<th><s:text name="Text.id"></s:text></th>
												  	 <th><s:text name="Text.adduser.townshipname"></s:text></th>
													<th><s:text name="Text.adduser.societyname"></s:text></th>
													<th><s:text name="Text.name"></s:text></th>
													<th><s:text name="Text.complainttitle"></s:text></th>
													<th><s:text name="Text.complaintto"></s:text></th>
													<th><s:text name="Text.ticketid"></s:text></th>
													<th><s:text name="Text.status"></s:text></th>
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
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
		<s:form method="post" id="delgroupform">
		<s:hidden name="closereasonval" id="closereasonid"></s:hidden>
					<s:hidden name="complaintuniqueId" id="uniqcomplaintIdEdit"></s:hidden>
					<s:hidden name="complaintName_hidd_txt" id="complaintName_hidd_id"></s:hidden>
					<s:hidden name="deletecomplaintid" id="deletecomplaintidEdit"></s:hidden>
					<s:hidden name="deletecomplaintserviceid" id="deletecomplaintserv_id"></s:hidden>
					<s:hidden name="hiddencmpltidval" id="hiddencmpltid" />
			</s:form>
	
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
<script type="text/javascript">
var oTable;
	$(document).ready(function() {
		createoTable();
		selectfieldfun();
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
			if (objects_cardtype.indexOf(this.value)!=-1) {					
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
	 
	 function selectfieldfun(){
			var objects_slectfield;		
			var locval = [{"id":"1","label":"All"},{"id":"2","label":"Resident"},{"id":"3","label":"Complaint Title"},{"id":"4","label":"To Committee"}];
			
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
		
		oTable = $('#example')
		.dataTable(
				{
					//"sScrollX": "100%",
				    //"sScrollXInner": "110%",				   
				    //"bScrollInfinite": true,
				    //"bScrollCollapse": true,
				    
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,		    
					"ajaxSource" : "Complaintsmgmtdatatable",
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [ {
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3 , 4, 5, 6, 7, 8]
					} ],
					"drawCallback": function( oSettings ) {
						$('.tooltipped').tooltip("remove");
						 $('.tooltipped').tooltip({delay: 10});
					    }
					//"sDom": 'T<"clear">lfrtip',			       
					//"oTableTools": {
			            //"sSwfPath": "/swf/copy_csv_xls_pdf.swf"
			       // }													
				});
		$(window).bind('resize', function () {
		    oTable.fnAdjustColumnSizing();
		  } );
		tohideLoadingImgoverlay(); 
	}

	function loadDataTableByFilter() {
		 var township=$('#township_hidd_id').val();
			var society=$('#societyId_hidd_id').val();
			var slectfield=$('#slectfield_hidd_id').val();
			var searchname=$('#usersearchname').val();
			
			if (oTable != undefined) {
				oTable.fnDestroy();				
			}else{
				oTable.fnDestroy();				
			} 
		oTable = $('#example')
				.dataTable(
						{
							"jQueryUI" : false,
							"processing" : true,
							"serverSide" : true,
							"displayLength" : 10,
							"ajaxSource" : "Complaintsmgmtdatatable?townshipid="+ township+"&society="+society+"&slectfield="+slectfield+"&searchname="+searchname+"&searchval=1",
							"sorting" : [],
							"paginationType" : "full_numbers",
							"columnDefs" : [ {
								"sortable" : false,
								"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7, 8]
							} ],
							"drawCallback": function( oSettings ) {
								$('.tooltipped').tooltip("remove");
								 $('.tooltipped').tooltip({delay: 10});
							    }
						});
		
	}
	function deletecomplaintaction(id){		
		swal({    title: "Are you sure want to delete?",
            text: "You will not be able to recover this complaint detail !",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Yes",   
            closeOnConfirm: false }, 
            function(){ 
            	$("#deletecomplaintidEdit").val(id);
				$("#delgroupform").attr("action", "deletecomplaint");
				$("#delgroupform").submit();
            });
	}
	/* function deactivecomplaint(id) {
		$("#hiddencmpltid").val(id);
		swal({   title: "Close Reason",   
	        //text: "Create New Group",   
	        type: "input",   showCancelButton: true,
	        closeOnConfirm: false,   
	        
	        animation: "slide-from-top", 
	        inputValue: "" , 
	        inputPlaceholder: "Enter the reason" }, 
	        function(inputValue){  
	        	
	            if (inputValue === false) return false;      
	            if (inputValue === "") {     
	            swal.showInputError("Enter the reason");     
	            return false   
	            } 
	            $("#closereasonid").val(inputValue);
	            $("#deletecomplaintidEdit").val(id);
				$("#delgroupform").attr("action", "Reasonaction");
				$("#delgroupform").submit();
	      // swal("Success", "Reason updated Successfully ", "success"); 
	   });
		
		
	
		 
	} */
	function createclosereason(id) {
		$("#hiddencmpltid").val(id);
		$("#delgroupform").attr("action", "Reasonactionpage");
		$("#delgroupform").submit();
		
	}
	/* function showreasonFunct(id,clsreason,dateformat){
		$("#viewclsreasonid").val(clsreason);
		$("#hiddenreasonid").val(id);
		 swal({   
	         title: "Closed Reason",   
	         text:clsreason, 
	        
	    });
		
		
	} */
	
	 function showreasonFunct(id,clsreason,dateformat) {
			//var townshipid = $("#township_hidd_id").val();
			  swal({   
				  	 title: "<div class='<s:text name='alert.titleh2.color'/>'>Closed Reason</div>",   			         
			         text: "<div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Reason <span style=\"margin-left:12px;\">:</span></div> <div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp; "+clsreason+"&nbsp; </div></div> <div class='clear height10px'></div> <div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Resolved Date <span style=\"margin-left:24px;\">:</span> </div><div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp;&nbsp;"+dateformat+"&nbsp;</div></div></div>" ,
			         //text: "<div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Township Name <span style=\"margin-left:12px;\">:</span> </div> <div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp; "+townshipname+"&nbsp; </div></div> <div class='clear height10px'></div> <div class=''><div class='col s4 <s:text name='alert.label.color'/>'> Activation Key <span style=\"margin-left:24px;\">:</span></div> <div class='col s1 <s:text name='alert.value.color'/>'>&nbsp;&nbsp;&nbsp;"+activekey+"&nbsp;</div></div></div>" ,
			         html: true 
			    });
		} 
	function viewcomplaint(id) {
		$("#deletecomplaintidEdit").val(id);
		$("#delgroupform").attr("action", "viewcomplaintform");
		$("#delgroupform").submit();
	}
	function emailFunction(id,emailid,groupcode) {
		
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'Labor_MercEmailFunction',
			data : "uniqcmpltid=" + id+"&complaintfrmemailid="+emailid+"&complaintfrmgroupcode="+groupcode,
			success : function(data) {
			/* 	document.write(data);
				window.print(); */
				
				var spli = data.split("!_!");
				if(spli[1]=="success"){
					//BootstrapDialog.alert('Email was sent sccessfully'); 
					swal("Email was sent sccessfully");
				}else{
					//BootstrapDialog.alert('The society does not have root user');
					swal("The society does not have root user");
				}
			}
		});
	}
function smsFunction(id,mobno,groupcode) {
		
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'Labor_MercSMSFunction',
			data : "uniqcmpltid=" + id+"&complaintfrmmobno="+mobno+"&complaintfrmgroupcode="+groupcode,
			success : function(data) {
			/* 	document.write(data);
				window.print(); */
				
				var spli = data.split("!_!");
				if(spli[1]=="success"){
					//BootstrapDialog.alert('Email was sent sccessfully'); 
					swal("SMS was sent sccessfully");
				}else{
					//BootstrapDialog.alert('The society does not have root user');
					swal("The society does not have root user");
				}
			}
		});
	}
	function onchangetownshipId() {
		var townshipid = $("#townshipid").val();
		$.ajax({
			type : 'post',
			datatype : 'html',
			url : 'townshipgetsociety',
			data : 'townshipid=' + townshipid,
			success : function(data) {
				$("#statediv").html(data);
			},
			error : function(d) {
			}
		});
	}
	function newComplaintCreation(){
		toShowLoadingImgoverlay();
		$("#searchflt").attr("action", "createComplaint");
		$("#searchflt").submit();
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
</script>		
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/plugins.js"></script>
	<!--custom-script.js - Add your own theme custom JS-->
	<script type="text/javascript"
		src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
</body>
</html>
