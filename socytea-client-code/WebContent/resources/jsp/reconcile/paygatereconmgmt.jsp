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
                <h5 class="breadcrumbs-title"><s:text name="Breadcrumb.paygaterecon" /></h5>
                <ol class="breadcrumbs left">
                  <li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li><a href="#"><s:text name="Breadcrumb.Reconcile" /></a></li>
							<li class="active"><s:text name="Breadcrumb.paygaterecon" /></li>						             
                   </ol>
             
                 <div class="right">
                   		<input type="file"  name="payfile"  class="excelfile" id="uploadfile_id" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" style="display: none;">
                     	<button type="button" style="" data-position="bottom" data-delay="10" data-tooltip="Upload Paygate Reconcile File" class=" <s:text name="button.color.new.create1"/>" data-toggle="modal" data-target="#addnewaccount" onclick="excelUpload();"><i class="mdi-file-file-upload  left"> </i>Upload Reconn File</button>                                     		
                </div>                                       					
              </div>              
            </div>            
          </div>          
        </div>
        <div class="container">       
      <jsp:include page="../common/Alert.jsp" flush="true" />
      <div style="width: 98%;margin-left: 1%;display: none;" id="card-alert" class="card success usrerralert">
			<div class="card-content white-text">
            <p><i class="mdi-navigation-check"></i> <s:text name="Text.paygate.fileupload"></s:text></p>
            </div>
			 <button type="button" class="close white-text" data-dismiss="alert" aria-label="Close" style="margin-top:8px;">
              <span aria-hidden="true">×</span>
             </button>
		</div>
		<div style="width: 98%;margin-left: 1%;display: none;" id="card-alert" class="card danger fuplderroralert">
			<div class="card-content white-text">
                        <p><i class="mdi-alert-error"></i> <span id="errormsgalert"></span></p>
            </div>
			 <button type="button" class="close white-text" data-dismiss="alert" aria-label="Close" style="margin-top:8px;">
                        <span aria-hidden="true">×</span>
             </button>
		</div>
      <div style="clear: both;"></div>
       <div class="col s12 m4 l3 padding10px">
                <div class="row">
					<s:if test="#session.sSoctyId==-1">
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
						</div>	 --%>				
					</s:if>
				<%-- 	<div class="input-field col s2">
											<label for="slectfield"><s:text name="Text.selectfield.set" /></label>
											<s:textfield name="labRegObj.wrktypname" id="slectfield" cssClass="form-control typeahead tt-query" autocomplete="off" spellcheck="false"/>
											<s:hidden name="labRegObj.wrktypId" id="slectfield_hidd_id" class="form-control"/>  
					</div> --%>
											<div class="input-field col s4">
											<div class="form-group" id="searchtext">
											<label for="usersearchname"><s:text name="text.search" /></label>									
											<s:textfield name="usersearchname" id="usersearchname" cssClass="form-control input-sm"/>
												</div>
												</div>												
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
						<th ><s:text name="Table.recnhead.sno"></s:text></th>
						<th ><s:text name="Table.recnhead.filename"></s:text></th>
			<%-- 			<th ><s:text name="Table.recnhead.township"></s:text></th>
						<th ><s:text name="Table.recnhead.society"></s:text></th> --%>
						<th ><s:text name="Table.recnhead.uplddate"></s:text></th>
						<th><s:text name="Table.recnhead.action"></s:text></th>
						</tr>
					</thead>
                  </table>
                  	
				<s:form method="post" id="delgroupform">
					<s:hidden  name="flg" value="paygate" id="uniqSocietyId"></s:hidden>
					<s:hidden name="paygatecyberId" id="materialid"></s:hidden>
				</s:form>
				<form id="downloadFormid" method="post" action="downloadfiles">
						<s:hidden id="downloadFormval" name="fileName" value=""></s:hidden>
						<s:hidden id="downloadFormvalpath" name="fileNamePath" value=""></s:hidden>
						</form>
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
	function excelUpload(){
		 $(".excelfile").click();
	}
	var oTable;
	$(document).ready(function() {
			$('input[type="file"]').ajaxfileupload({
            	'action': 'excelpaygeteupload',           
            	'onComplete': function(response) {                 		                    
            		 var obj = JSON.stringify(response); 
                	 var jobj = JSON.parse(obj);	
                	//alert(jobj);     
                	// alert(obj.status);    
                	var lvrsts=jobj["status"];                		 		                 
    	             if (typeof(lvrsts) != "undefined" && (lvrsts == "false" || !lvrsts)) {		            	 
    	            	 tohideLoadingImgoverlay();
    	            	 $("#errormsgalert").html(jobj["message"]);
    	                 $('.fuplderroralert').show();	
    		         }  else {
    		        	 tohideLoadingImgoverlay();
    	                 $('.usrerralert').show();	
    			     } 	                   
           	 },
           	 validate_extensions : true,
             valid_extensions : ['xls','xlsx'],
            'onStart': function() {	              
            	toShowLoadingImgoverlay();
            }
        	});
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
	
		 function selectfieldfun(){
			var objects_slectfield;		
			var locval = [{"id":"","label":"All"},{"id":"1","label":"File Name"},{"id":"2","label":"Society Name"},{"id":"3","label":"Township Name"}];				
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
								"ajaxSource" : "paygatemgmttbl",						
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {
									"sortable" : false,
									"targets" : [ 0, 1, 2, 3]
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
								"ajaxSource" :"paygatemgmttbl?searchWord="+ searchWord+"&townShipId="+townshipid+"&societyId="+societyid+"&slectfield="+slectfield_hidd_id,					
									"sorting" : [],
									"paginationType" : "full_numbers",
									"columnDefs" : [ {
										"sortable" : false,
										"targets" : [ 0, 1, 2, 3]
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
		
	</script>
	 <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
</body>
</html>

