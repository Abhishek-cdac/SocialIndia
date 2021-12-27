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
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div id="main">
		<div class="wrapper">
			<jsp:include page="../common/menuBar.jsp"></jsp:include>
			<section id="content"> <!--breadcrumbs start-->
			<div id="breadcrumbs-wrapper">
				<!-- Search for small screen -->
				<jsp:include page="../common/searchexploremob.jsp"></jsp:include>
				<div class="container">
					<div class="row">
						<div class="col s12 m12 l12">
							<h5 class="breadcrumbs-title"><s:text name="Text.Utility.Management.event" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="#"><s:text name="Text.Utility.Management" /></a></li>
								<li class="active"><s:text name="Text.Utility.Management.event" /></li>

							</ol>
							<div class="right">
									<button type="button" onclick="newEventCreation();" class=" <s:text name="button.color.new.create"/>" 
									data-toggle="modal" data-target="#createMerchant" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" 
									data-tooltip="<s:text name="Text.Create.New.Event"/>" value="submit"> <i class="<s:text name="crt.button.icon.event"/>"> </i><s:text name="Text.Create.Event"/></button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--breadcrumbs end--> <!--start container-->
			<div class="container"></div>
			<!--end container--> </section>

			<jsp:include page="../common/Alert.jsp" flush="true" />
			<div class="col s12 m4 l3 padding10px">
				<!-- Search inputs -->
				<div class="row">
				<form id="searchflt" action="" method="post">
				<s:if test="(#session.sSoctyId==null || #session.sSoctyId =='' || #session.sSoctyId =='-1')">
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
					<s:textfield name="labRegObj.wrktypname" id="slectfield" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false" onchange="checkdatepicker();"/>
					<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id" class="form-control" onchange="checkdatepicker();"/>  
					</div>
					<div class="input-field col s2">
					<div class="form-group" id="searchtext">
						<label for="usersearchname"><s:text name="text.search" /></label>									
					<s:textfield name="usersearchname" id="usersearchname" cssClass="form-control input-sm"/>
					</div>
									<div class="form-group" id="searchdate" style="display:none;" >		
									<input type="text" class="form-control enddate datepicker" name="usersearchname" id="usersearchnamea"/>							
									<div class="input-group input-append date" id="datePicker">
                					<%-- <s:textfield cssClass="form-control startdate" name="usersearchname" id="usersearchnamea" Placeholder="Select Search Date"/> --%>
               						<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
           							</div>
									</div>
									</div>
									</s:if>
									<s:else>
									<div class="input-field col s2">
					<label for="slectfield"><s:text name="Text.selectfield.set" /></label>
					<s:textfield name="labRegObj.wrktypname" id="slectfield" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false" onchange="checkdatepicker();"/>
					<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id" class="form-control" onchange="checkdatepicker();"/>  
					</div>
									<div class="input-field col s2">
									<div class="form-group" id="searchtext">
									<label for="usersearchname"><s:text name="text.search" /></label>									
									  <s:textfield name="usersearchname" id="usersearchname" cssClass="form-control input-sm"/>
									</div>
									<div class="form-group" id="searchdate" style="display:none;" >	
									<input type="text" class="form-control enddate datepicker" name="usersearchname" id="usersearchnamea"/>									
									 <div class="input-group input-append date" id="datePicker">
                					<%-- <s:textfield cssClass="form-control startdate" name="usersearchname" id="usersearchnamea" Placeholder="Select Search Date"/> --%>
               						<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
           							</div> 
									</div>
									</div>
									</s:else>
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
					<div class="clear"></div>								
					</form>
									
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
													<th><s:text name="Text.event.function.for"></s:text></th>
												  	<th><s:text name="Text.title"></s:text></th>
													<th><s:text name="Text.utlity.startdate"></s:text></th>
													<th><s:text name="Text.utlity.enddate"></s:text></th>
													<th><s:text name="Text.table.action"/></th>
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
					<s:hidden name="laboruniqueId" id="uniqlaborIdEdit"></s:hidden>
					<s:hidden name="laborName_hidd_txt" id="laborName_hidd_id"></s:hidden>
					<s:hidden name="uniqueId" id="deleteleventidEdit"></s:hidden>
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
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
		
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
			var locval = [{"id":"1","label":"All"},{"id":"2","label":"Title"},{"id":"3","label":"Start Date"},{"id":"4","label":"End Date"}];
			
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
		.DataTable(
				{
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,
					"serverMethod": "POST",
					"scrollAutoCss": true,			
					"ajaxSource" : "eventMgmtform",
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [ {
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3, 4, 5 ]
					} ],
					"fnDrawCallback": function( oSettings ) {
						 $('.tooltipped').tooltip({delay: 10});
					      //alert( 'DataTables has redrawn the table' );
					    }
				});
	}
	function vieweventaction(id) {
		$("#deleteleventidEdit").val(id);
		toShowLoadingImgoverlay();
		$("#delgroupform").attr("action", "viewEventAction");
		$("#delgroupform").submit();
	}
	function editeventaction(id) {
		$("#deleteleventidEdit").val(id);
		toShowLoadingImgoverlay();
		$("#delgroupform").attr("action", "modifyEventAction");
		$("#delgroupform").submit();
	}
	function inviteeventaction(id) {
		$("#deleteleventidEdit").val(id);
		$("#delgroupform").attr("action", "inviteeventaction");
		toShowLoadingImgoverlay();
		$("#delgroupform").submit();
	}
	function deleteeventaction(id){		
		swal({    title: "Are you sure want to delete?",
            text: "You will not be able to recover this event detail !",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Yes",   
            closeOnConfirm: false }, 
            function(){
				toShowLoadingImgoverlay();
				$("#deleteleventidEdit").val(id);
				$("#delgroupform").attr("action", "deleteEvent");
				$("#delgroupform").submit();
		});
	}function newEventCreation(){
		toShowLoadingImgoverlay();
		$("#searchflt").attr("action", "createEvent");
		$("#searchflt").submit();
	}
	
	function filter(){
		 var township=$('#township_hidd_id').val();
		 var searchfield=$('#slectfield_hidd_id').val();
		 var societyid=$('#societyId_hidd_id').val();
		 
		 if(societyid!=null && societyid!='' && societyid!="undefined"){
			 societyid=societyid;
		 }else{
			 societyid=""; 
		 }
		 var usersearchname=$('#usersearchname').val();	
		 var selectid=document.getElementById('usersearchnamea').value;
		 if(selectid!=''){
			usersearchname=selectid;	
		 }
			oTable.destroy();				
	 	oTable = $('#example')
		.DataTable(
				{
					"jQueryUI" : false,
					"processing" : true,
					"serverSide" : true,
					"displayLength" : 10,
					"serverMethod": "POST",
					"scrollAutoCss": true,	
					"ajaxSource" :"eventMgmtform?searchfield="+ searchfield+"&usersearchname="+usersearchname+"&societyid="+societyid+"&sdtSearchff=1",					
					"sorting" : [],
					"paginationType" : "full_numbers",
					"columnDefs" : [{						
						"sortable" : false,
						"targets" : [ 0, 1, 2, 3, 4, 5]
					}],
					"fnDrawCallback": function( oSettings ) {
						 $('.tooltipped').tooltip({delay: 10});
						 $('select').material_select();
					      //alert( 'DataTables has redrawn the table' );
					    }
				});
		
	}
	
	function checkdatepicker(){
		var selectid=document.getElementById('slectfield_hidd_id').value;
		if(selectid=='3' || selectid=='4'){
			$('#usersearchnamea').val('');
			$('#usersearchname').val('');
			$('#searchdate').show();
			$('#searchtext').hide();
			
		}else if(selectid=='1'){
			$('#usersearchnamea').val('');
			$('#usersearchname').val('');			
			$('#searchdate').hide();
			$('#searchtext').show();
			filter();
		}else{
			$('#usersearchnamea').val('');
			$('#usersearchname').val('');
			$('#searchdate').hide();
			$('#searchtext').show();
		}
		
	}
	
	$("#township_txt_id").change(function(){
		$('#societyId_txt_id').val('');
		$('#societyId_hidd_id').val('');
		$('#slectfield').val('');
		$('#slectfield_hidd_id').val('');
		$('#usersearchname').val('');
		$('#usersearchnamea').val('');
		});

	$("#societyId_txt_id").change(function(){
		$('#slectfield').val('');
		$('#slectfield_hidd_id').val('');
		$('#usersearchname').val('');
		$('#usersearchnamea').val('');
		});

	$("#slectfield").change(function(){
	$('#usersearchname').val('');
	$('#usersearchnamea').val('');
	});
		
						</script>
	
	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>
