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
            <jsp:include page="../common/searchexploremob.jsp"></jsp:include>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title">Committee Management</h5>
                <ol class="breadcrumbs left">
                  <li><a href="loginform"><s:text name="Breadcrumb.dashboardhome"/></a></li>
							<li><a href="committeemgmt"><s:text name="Breadcrumb.manage" /></a></li>
								<li class="active"><s:text name="Committee Management" /></li>
             
                   </ol>
                 <div class="right">
				<%-- 	<input type="file"  name="datafile"  class="excelfile" id="uploadfile_id" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" style="display: none;">
                     <button type="button" data-position="bottom" data-delay="10" data-tooltip="Upload Your Excel" class="<s:text name="button.color.new.create1"/> tooltipped" data-toggle="modal" data-target="#addnewaccount" onclick="excelUpload();"><i class="mdi-file-file-upload  left"> </i>Excel Upload</button>
                     --%>
                      <input type="file"  name="datafile"  class="excelfile" id="uploadfile_id" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" style="display: none;">
                     <button type="button" style="width: 228px" data-position="bottom" data-delay="10" data-tooltip="Upload Your Excel" class=" <s:text name="button.color.new.create1"/>" data-toggle="modal" data-target="#addnewaccount" onclick="excelUpload();"><i class="mdi-file-file-upload  left"> </i>Excel Upload</button>
                      <a href="createCommittee"><button type="button" class=" <s:text name="button.color.new.create"/>" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Create Committee"><i class="mdi-social-group-add left"> </i>Create Committee</button></a>
                <div class="textalign" title="Download Sample Document"><a class="atag" href="xlsxdownload">[Sample Excel]</a>
					 </div>
                </div>                               						
              </div>
            </div>
          </div>
        </div>
        <div class="container">
        </div>
      </section>
    	<jsp:include page="../common/Alert.jsp" flush="true" />
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
					<div class="input-field col s2">
					<button data-target="#addnewaccount" style="margin-top: 12px;" data-toggle="modal" data-position="right" onclick="filter();" data-delay="10" data-tooltip="Click to search" class="<s:text name="button.color.filter"/>" id="addaccountbuttonid" type="button" onclick="filter();"><i class="mdi-action-search "></i>Filter</button>
				</div></div>
					</div>
    	<div style="width: 98%;margin-left: 1%;display: none;" id="card-alert" class="card success usrerralert">
			<div class="card-content white-text">
                        <p><i class="mdi-navigation-check"></i> <s:text name="Text.commitee.fileupload"></s:text></p>
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
			<div id="table-datatables" class="padding10px">
				<div class="row">
					<div style="height: 10px;"></div>
					<div class="col s12">
						<table id="example" class="responsive-table display hoverable">
							<thead>

								<tr class="<s:text name="datatable.heading.bgcolor"/>">
									<th><s:text name="Text.table.sno"></s:text></th>
									<!-- <th><s:text name="Text.emailid"></s:text></th> -->
									<th><s:text name="Text.mobileno"></s:text></th>
									<th><s:text name="Text.name"></s:text></th>
									<th><s:text name="Text.adduser.townshipname"></s:text></th>
									<th><s:text name="Text.adduser.societyname"></s:text></th>
									<th><s:text name="Text.accesschannel"></s:text></th>
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
					<s:hidden name="uniqcommitteeId" id="uniqcommitteeId"></s:hidden>
					<s:hidden name="deletestaffid" id="deletestaffidEdit"></s:hidden>
			</s:form>
			<s:form method="post" id="crtnewCmtyShrtctid">
				<s:hidden name="ivrTwsipid" id="ivrTwsipid"></s:hidden>
				<s:hidden name="ivrTwsipname" id="ivrTwsipname"></s:hidden>
				<s:hidden name="ivrSwityid" id="ivrSwityid"></s:hidden>
				<s:hidden name="ivrSwityname" id="ivrSwityname"></s:hidden>
			</s:form>
				<jsp:include page="../common/footer.jsp"></jsp:include>
      	<jsp:include page="../common/dtablepagejs.jsp"></jsp:include> 
      	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/required/bootstrap/bootstraptypehead.min.js?yvvctty"></script>
      	 <script type="text/javascript" src="<s:text name='Resource.path'/>/js/imageupload/jquery.ajaxfileupload.js?cxxxc"></script>
</body>
 <script type="text/javascript">
 $(document).ready(function(){
 	$("#uploadfile_id").click(function(){
 		$('input[type="file"]').ajaxfileupload({
             'action': 'excelfileupload',           
             'onComplete': function(response) {                 	
            	 var obj = JSON.stringify(response); 
            	 var jobj = JSON.parse(obj);
            		                   
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
             'onStart': function() {
                //$('#upload').show();
             	toShowLoadingImgoverlay();
             }
         });
 	});
 	
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
			var locval = [{"id":"","label":"All"},{"id":"1","label":"Mobile No."},{"id":"2","label":"User Name"},{"id":"3","label":"Township Name"},{"id":"4","label":"Society Name"}];
			
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

        /*Show entries on click hide*/
        var oTable;
        $(document).ready(function() {
			createoTable();						
		});
		function createoTable() {
			oTable = $('#example').DataTable(
							{
								"jQueryUI" : false,
								"processing" : true,
								"serverSide" : true,
								"displayLength" : 10,
								"ajaxSource" : "committeeMgmtTable",						
								"sorting" : [],
								"paginationType" : "full_numbers",
								"columnDefs" : [ {									
									"sortable" : false,
									"aTargets" : [ 0, 1, 2, 3 , 4, 5, 6]
									
								} ],
								"drawCallback": function( oSettings ) {
									$('.tooltipped').tooltip("remove");
									 $('.tooltipped').tooltip({delay: 10});
									// $("[name=example_length]").css("display","block");
								    }
							});
			
		}
		function excelUpload(){
			 $(".excelfile").click();
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
        
        function filter() {
			var searchWord = $("#usersearchname").val();
			var townshipid = $("#township_hidd_id").val();
			var societyid = $("#societyId_hidd_id").val();
			var slectfield = $("#slectfield_hidd_id").val();
		
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
								"ajaxSource" :"committeeMgmtTable?searchWord="
									+ searchWord+"&townShipId="+townshipid+"&societyId="+societyid+"&slectfield="+slectfield,					
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
        function editcommittee(id) {
			toShowLoadingImgoverlay();		
			$("#uniqcommitteeId").val(id);
			$("#delgroupform").attr("action", "committeeEditAction");
			$("#delgroupform").submit();
		}
		function viewcommittee(id) {
			toShowLoadingImgoverlay();
			$("#uniqcommitteeId").val(id);
			$("#delgroupform").attr("action", "committeeViewAction");
			$("#delgroupform").submit();
		}		
		function addresident(twnshipidid,twnshipnaname, sociteiid, socityname, commiteeusrid){
			toShowLoadingImgoverlay();			
			$("#ivrTwsipid").val(twnshipidid);
			$("#ivrTwsipname").val(twnshipnaname);
			$("#ivrSwityid").val(sociteiid);
			$("#ivrSwityname").val(socityname);
			$("#crtnewCmtyShrtctid").attr("action", "createNewresidentform");
			$("#crtnewCmtyShrtctid").submit();
		}	
		

    </script>
     <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="<s:text name='Resource.path'/>/js/custom-script.js"></script>
 
</html>