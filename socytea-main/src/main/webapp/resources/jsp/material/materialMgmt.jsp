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
            <!-- Search for small screen -->
            <div class="header-search-wrapper grey hide-on-large-only">
                <i class="mdi-action-search active"></i>
                <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore Materialize">
            </div>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title"><s:text name="Breadcrumb.material" /></h5>
                <ol class="breadcrumbs left">
                  <li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="#"><s:text name="Breadcrumb.utility" /></a></li>
							<li class="active"><s:text name="Breadcrumb.material" /></li>						             
                   </ol>
                   <s:if test="#session.GROUPCODE != 1 && #session.GROUPCODE!=2 && #session.GROUPCODE!=0">
                  <div class="right">
                      <a  href="createMaterialForm"><button type="button" style="width: 235px;" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="<s:text name="Breadcrumb.add.material" />"  ><i class="<s:text name="button.icon.addcard"/>"> </i><s:text name="Breadcrumb.add.material" /></button></a>
                </div> 
                </s:if>                              						
              </div>
              
            </div>
            
          </div>
          
        </div>
        <div class="container">
        </div>
      </section>
    	<jsp:include page="../common/Alert.jsp" flush="true" />
      <div style="clear: both;"></div>
                       <div class="col s12 m4 l3 padding10px">
                <div class="row">
					<s:if test="#session.sSoctyId==-1">
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
						<%-- <div class="input-field col s2">
						<s:select style="width:180px;" id="townshipid"  class="form-control gendervalidate" cssClass="form-control townshipIdvalidate" headerKey=""
													headerValue="Select Township" list="#session.TOWNSHIPLIST"
													listKey="townshipId" listValue="townshipName"
													name="townshipmaster.townshipId"
													value="%{townshipmaster.townshipId}"
													onchange="onchangetownshipId();">
												</s:select>
												</div>
											
											 <div class="input-field col s2">
													<s:select name="societyId" listKey="stateId" id="statediv" cssClass="form-control societyIdvalidate"
														 list="# {'':'Select Society'} " headerValue="Select Society" onchange="onchangesocietyId();"></s:select>	
											</div>  --%>
											</s:if>
											<div class="input-field col s2">
											<label for="slectfield"><s:text name="Text.selectfield.set" /></label>
											<s:textfield name="labRegObj.wrktypname" id="slectfield" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false"/>
											<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id" class="form-control"/>  
											</div>
											<div class="input-field col s2">
											<div class="form-group" id="searchtext">
											<label for="usersearchname"><s:text name="text.search" /></label>									
											<s:textfield name="usersearchname" id="usersearchname" cssClass="form-control input-sm"/>
												</div>
												</div>
												<%-- <div class="input-field col s2">
												<s:select style="width:180px;" name="" class="form-control gendervalidate"
													 id="slectfield_hidd_id"
													cssClass="form-control "
													list="# {'4':'Select Category','':'All','1':'Material Name','2':'Material Category','3':'Material Price'} " onchange="onchangegroupId();">
												</s:select>
											</div>
											<div class="input-field col s2">
									<label for="usersearchname"><s:text name="Search" /></label>
									<s:textfield name="restRegObj.emailId" id="usersearchname"
										cssClass="form-control typeahead tt-query cmpnyRegnovalidate"
										autocomplete="off" />
								</div> --%>
								<div class="input-field col s2">
									<button data-target="#addnewaccount" style="margin-top: 12px;" data-toggle="modal" data-position="right" onclick="filter();" data-delay="10" data-tooltip="Click to search" class="<s:text name="button.color.filter"/>" id="addaccountbuttonid" type="button" onclick="filter();"><i class="mdi-action-search "></i>Filter</button>
								</div>
											
											</div>
											</div>
										
     
     
            <div id="table-datatables" class="padding10px">
              
              <div class="row">
                    <div style="height: 10px;"></div>
                <div class="col s12">
                  <table id="example" class="responsive-table display hoverable" >
                   <thead>
													<tr class="<s:text name="datatable.heading.bgcolor"/>">
													  <th ><s:text name="S.No."></s:text></th>
													<th ><s:text name="Material Category"></s:text></th>
													<th ><s:text name="Material Name"></s:text></th>
													<th ><s:text name="Total Quantity"></s:text></th>
													<th ><s:text name="Used Quantity"></s:text></th>
													<th ><s:text name="Balance Quantity"></s:text></th>
													<th ><s:text name="Material Price"></s:text></th>
													<th>Action</th>
												</tr>
											</thead>
                  </table>
                  	
										<s:form method="post" id="delgroupform">
											<s:hidden name="uniqSocietyId" id="uniqSocietyId"></s:hidden>
											<s:hidden name="materialId" id="materialid"></s:hidden>
										</s:form>
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
			 // {'4':'Select Category','':'All','1':'Material Name','2':'Material Category','3':'Material Price'} "
			 function selectfieldfun(){
					var objects_slectfield;		
					var locval = [{"id":"","label":"All"},{"id":"1","label":"Material Name"},{"id":"2","label":"Material Category"},{"id":"3","label":"Material Price"},{"id":"4","label":"Select Category"}];
					
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
								"ajaxSource" : "materialMgmtTable",						
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
		function filter() {
			var searchWord = $("#usersearchname").val();
			var townshipid = $("#township_hidd_id").val();
			var societyid = $("#societyId_hidd_id").val();
			var slectfield = $("#slectfield_hidd_id").val();
		
			if(societyid!=null && typeof(societyid) != "undefined" && societyid!=''){
				
			}else{
				societyid="";
			}
			var slectfield_hidd_id = $("#slectfield_hidd_id").val();
			 oTable.destroy();				
			 	oTable = $('#example')
					.DataTable({
						"jQueryUI" : false,
						"processing" : true,
						"serverSide" : true,
						"displayLength" : 10,
								"ajaxSource" :"materialMgmtTable?searchWord="
									+ searchWord+"&townShipId="+townshipid+"&societyId="+societyid+"&slectfield_hidd_id="+slectfield_hidd_id,					
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
		
 function deletematerial(id) {
		swal({    title: "Are you sure want to delete?",
         text: "You will not be able to recover this material detail!",   
         type: "warning",   
         showCancelButton: true,   
         confirmButtonColor: "#DD6B55",   
         confirmButtonText: "Yes",   
         closeOnConfirm: false }, 
         function(){ 
         	$("#materialid").val(id);
				$("#delgroupform").attr("action", "deleteMaterial");
				$("#delgroupform").submit();
        // swal("Deleted!", "User has been deleted.", "success"); 
         });
		}
		function viewmaterial(id) {
			//alert(id);
			$("#materialid").val(id);
			$("#delgroupform").attr("action", "materialViewAction");
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
					$("#societyId_hidd_id").val('');
					$("#slectfield_hidd_id").val('');
					$("#usersearchname").val('');
				},
				error : function(d) {
				}
			});
		}

		function editmaterial(id) {
			$("#materialid").val(id);
			$("#delgroupform").attr("action", "materialEditAction");
			$("#delgroupform").submit();
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
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>

