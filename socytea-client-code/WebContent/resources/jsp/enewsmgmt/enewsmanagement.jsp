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
                <h5 class="breadcrumbs-title">eNews</h5>
                <ol class="breadcrumbs left">
                    <li><a href="loginform">Dashboard</a></li>
                    <li class="active"><a href="eNewsmgmt"><s:text name="Text.Utility.Management" /></a></li>
                    <li class="active">eNews</li>
                </ol>
                <s:if test="#session.GROUPCODE != 1 && #session.GROUPCODE!=2 && #session.GROUPCODE!=0">
                 <div class="right">
                      <button type="button" style="width: 228px;" data-position="bottom" data-delay="10" data-tooltip=" Post eNews" class=" <s:text name="button.color.new.create"/>" onclick="neweNewsCreation();" ><i class="mdi-communication-comment left"> </i>Post eNews</button>
                </div>
                </s:if>
              </div>
            </div>
          </div>
          
        </div>
        <!--breadcrumbs end-->
        <!--start container-->
        <div class="container">
        
        </div>
        <!--end container-->
      </section>
     
    	<jsp:include page="../common/Alert.jsp" flush="true" />
      <div style="clear: both;"></div>
		<form id="searchflt" action="" method="post">
		<div class="col s12 m4 l3 padding10px">
          <div class="row">
          <div class="">
		  <s:if test="#session.sSoctyId==-1 ||#session.sSoctyId.equalsIgnoreCase('-1')">
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
									</s:if>
									<div class="input-field col s2">
											<label for="slectfield"><s:text name="Text.selectfield.set" /></label>
											<s:textfield name="labRegObj.wrktypname" id="slectfield" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false"/>
											<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id" class="form-control"/>  
											</div>
						
									<div class="input-field col s2">
									<label for="usersearchname"><s:text name="Search" /></label>

									<s:textfield name="restRegObj.emailId" id="usersearchname"
										cssClass="form-control typeahead tt-query cmpnyRegnovalidate"
										autocomplete="off" />
								</div>
									 <div class="input-field col s2">
									<button data-target="#addnewaccount" style="margin-top: 12px;" data-toggle="modal" data-position="right"  data-delay="10" data-tooltip="Click to search" class="<s:text name="button.color.filter"/>" id="addaccountbuttonid" type="button" onclick="filter();"><i class="mdi-action-search "></i>Filter</button>
								</div>
								</div>							
										</div>
										</div>
									<div class="clear"></div>	
									</form>
       
     
            <div id="table-datatables" class="padding10px">
              
              <div class="row">
                    <div style="height: 10px;"></div>
                <div class="col s12">
                  <table id="example" class="responsive-table display hoverable" >
                   <thead>
													<tr class="<s:text name="datatable.heading.bgcolor"/>">
													 <th><s:text name="Text.id"></s:text></th>
													<th><s:text name="Text.adduser.societyname"></s:text></th>
													<th><s:text name="Text.title"></s:text></th>
													<th><s:text name="text.images"></s:text></th>
													<th>Action</th>
												</tr>
											</thead>
                  </table>
                  	<s:form method="post" id="delgroupform">
					<s:hidden name="enewuniqueId" id="uniqenewIdEdit"></s:hidden>
					<s:hidden name="enewName_hidd_txt" id="enewName_hidd_id"></s:hidden>
					<s:hidden name="uniqueId" id="deleteenewidEdit"></s:hidden>
					<s:hidden name="deleteenewserviceid" id="deleteenewserv_id"></s:hidden>
			</s:form>

			
			<s:form method="post" name="lockunlockaccountformid" id="lockunlockaccountformid"  >
				 <s:hidden id="lockuniqidHidn" name="custAccontDetailObj.uniqId" value="%{custAccontDetailObj.uniqId}"></s:hidden>
				 <s:hidden id="lockunlockflgHidn" name="custAccontDetailObj.isLocked" value="%{custAccontDetailObj.isLocked}"></s:hidden>
			</s:form>
			
			<s:hidden id="validateEnterValHidn" name="validateEnterValHidn" value="%{validateEnterVal}"></s:hidden>
			<s:hidden id="validateAccntfrmtHidn" name="validateAccntfrmtHidn" value="%{validateAccntfrmt}"></s:hidden>
			
                </div>
              </div>
            </div> 
            <br>
            <div class="divider"></div> 
            <!--DataTables example Row grouping-->
      

    </div>
    <!-- END WRAPPER -->

  </div>
  <!-- END MAIN -->
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include>  
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
	 <jsp:include page="../common/clorbox.jsp"></jsp:include> 
	 
	<script type="text/javascript">
	$(document).ready(function() {
		//toShowLoadingImgoverlay();
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
	 //{'4':'Select All','':'All','1':'Title'}
	 function selectfieldfun(){
			var objects_slectfield_hidd_id;		
			var locval = [{"id":"1","label":"All"},{"id":"2","label":"Title"}];
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
							"ajaxSource" : "eNewsmgmtdatatable",						
							"sorting" : [],
							"paginationType" : "full_numbers",
							"columnDefs" : [ {
								"sortable" : false,
								"targets" : [ 0, 1, 2, 3, 4]
							} ],
						"fnDrawCallback": function( oSettings ) {
							$('.tooltipped').tooltip("remove");
							$('.tooltipped').tooltip({delay: 10});
							$('select').material_select();
							}
						});
		
	}
	
	function filter() {
		 var township=$('#township_hidd_id').val();
			var society=$('#societyId_hidd_id').val();
			var slectfield=$('#slectfield_hidd_id').val();
			var searchname=$('#usersearchname').val();
			 oTable.destroy();	
			oTable = $('#example').DataTable({
				"jQueryUI" : false,
				"processing" : true,
				"serverSide" : true,
				"displayLength" : 10,		
							"sAjaxSource" : "eNewsmgmtdatatable?townshipid="+ township+"&society="+society+"&slectfield="+slectfield+"&usersearchname="+searchname+"&searchval=1",
							"sorting" : [],
							"paginationType" : "full_numbers",
							"columnDefs" : [ {
								"sortable" : false,
								"targets" : [ 0, 1, 2, 3, 4]
							} ],
						"fnDrawCallback": function( oSettings ) {
							$('.tooltipped').tooltip("remove");
							$('.tooltipped').tooltip({delay: 10});
							$('select').material_select();
							}
						});
			
	}

	function deleteeNewsaction(id) {
		swal({    title: "Are you sure want to delete?",
         text: "You will not be able to recover this enews detail!",   
         type: "warning",   
         showCancelButton: true,   
         confirmButtonColor: "#DD6B55",   
         confirmButtonText: "Yes",   
         closeOnConfirm: false }, 
         function(){ 
         	$("#deleteenewidEdit").val(id);
				$("#delgroupform").attr("action", "deleteEnews");
				$("#delgroupform").submit();
        // swal("Deleted!", "User has been deleted.", "success"); 
         });
		}
	
	function editenewaction(id)
	{
		$("#deleteenewidEdit").val(id);
		$("#delgroupform").attr("action", "editenewform");
		$("#delgroupform").submit();
	}
	function viewenewaction(id) {
		$("#deleteenewidEdit").val(id);
		$("#delgroupform").attr("action", "viewEnewsform");
		$("#delgroupform").submit();
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
			},
			error : function(d) {
			}
		});
	}
	function neweNewsCreation(){
		$("#searchflt").attr("action", "createeNews");
		$("#searchflt").submit();
	}$("#township_txt_id").change(function(){
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
	
	/* function onchangesocietyId(){
		$("#slectfield_hidd_id").val('');
		$("#usersearchname").val('');
	}
	function onchangeselectId(){
		$("#usersearchname").val('');
	} */
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body></html>

	