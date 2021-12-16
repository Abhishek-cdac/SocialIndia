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
<title><s:text name="Text.Title"/></title>
<style>
#folderView ul{list-style: none;}
#folderView ul li{list-style: none;}
</style>
<!-- CORE CSS-->
<jsp:include page="../common/basiccss.jsp"></jsp:include>	
<style>
#token-input-viewshareusernames { width: 100% ! important; }	
</style>
</head>
<%
String ivrDocid=request.getParameter("dcied");
String ivrShareuserid=request.getParameter("susried");
String calfr=request.getParameter("calfr");
%>
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
							<h5 class="breadcrumbs-title"><s:text name="Text.Document" /></h5>
							<ol class="breadcrumbs left">
								<li><a href="loginform"><s:text name="Breadcrumb.dashboard" /></a></li>
								<li><a href="utititymgmt"><s:text name="Text.Utility.Management" /></a></li>
								<li class="active"><s:text name="Text.Document" /></li>

							</ol>
							<div class="right" id="createdocidform">
					<!-- Top Right side buttons -->
								<form action="utilitynewdocument" method="post">
									<!-- <button style="display: none;" id="addaccountbuttonid" class="<s:text name="button.color.new.create1"/> tooltipped"  data-target="#addnewaccount" data-toggle="modal" type="button" data-position="bottom"
										data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="GENERATE MAINTENANCE BILL" onclick="getpdfgenerate()"> <i class="mdi-av-loop left"> </i>GENERATE MAINTENANCE 
									</button> -->
								<button type="submit" class=" <s:text name="button.color.new.create"/>" 
									data-toggle="modal" data-target="#createMerchant" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" 
									data-tooltip="Create New Document" value="submit"> <i class="<s:text name="button.document.add"/>"> </i>Create Document</button>
								</form>
							</div>
							<div class="right" id="gobackformid" style="display: none;">
										<button id="gobckbtnid" type="button" class="<s:text name="button.color.cancel"/> tooltipped"  data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back">
											Go Back <i class="mdi-content-reply left"></i> </button>
							</div>
						</div>

					</div>

				</div>

			</div>
			<!--breadcrumbs end--> <!--start container-->
			<div class="container"></div>
			<!--end container--> </section>
<div id="card-alert" class="card success" style="width: 98%;margin-left: 1%">
                              <div class="card-content white-text" id="emailupdate"  style="display: none;">
                               <p>
                                 <i class="mdi-navigation-check"></i>
                                     PDF Bill List Generated Successfully.
                                      </p>
                                  </div>
                                 <button class="close white-text" type="button" data-dismiss="alert" aria-label="Close">
                                  <span aria-hidden="true">×</span>
                                   </button>
                                   </div>
			<jsp:include page="../common/Alert.jsp" flush="true" />
			<div style="clear: both;"></div>
			<div id="filterdivid" class="col s12 m4 l3">
				<div class="row" style="margin-left: 12px;">
				<!-- Search filters -->
				<div class="input-field col s8">
				<input type="text" id="searchnaameTextId" class="form-control registerNumber" placeholder="Search by Name/Word" style="float: left;">
				<s:hidden id="searchFlag" value="false"></s:hidden>
				<s:hidden id="searchValue" value=""></s:hidden>
				</div>
				<div class="input-field col s2">
				<button id="addaccountbuttonid" class="<s:text name="button.color.filter"/>" onclick="filter();" data-toggle="modal"
							data-position="right" data-delay="10" data-tooltip="Click to search" style="margin-top: 12px;" type="button">
							<i class="mdi-action-search "></i>Filter</button>
				</div>
				</div>
			</div>
<div id="dataviewcontent">
	<div style="clear:both;height:10px;"></div>
									   <div class="table-responsive">
									   <div style="width:100%;" id="folderviewalert"></div>
									   <div class="col s12">
										<div id="folderView">
										</div>
										</div>
							<div id="verticalline" style="float:left;height:340px; display:none;"></div>
								<div  class="col s12" style="float:left;display:none;" id="shareid">
									          	<div class="row">
 													<div class="form-group" >
											<div class="col-sm-9" style=" margin-left: 0px;">
												<s:textfield name="usernames" id="usernames" cssStyle="height:150px;"  cssClass="form-control typeahead tt-query usernames" placeholder="Enter User Name" autocomplete="off" />
														<input type="hidden"  id="usernames_hidd" class="form-control"/>
														<s:hidden name="documentMng.docShareId" id="sharenames" value=""></s:hidden>
														

											</div>
											<div class="col-sm-3" style ="margin-top:3px; float:left;">
											<button type="submit" class="btn btn-primary btn-sm" id="submitbtn" name="submitbtn"
										value="Close" style="float:right;">Send>></button>
                                       </div>
											</div>

										</div>
										
								</div>
		</div>
		<s:hidden id="rootdirectoryname"></s:hidden>
		</div>
		<div id="generalDocumentdiv"></div>
		<s:form method="post" id="userCancelForm"></s:form>		
		<form id="downloadFormiddocview" method="post" action="downloadfiles">
			<s:hidden id="downloadFormval" name="fileName" value=""></s:hidden>
			<s:hidden id="downloadFormvalpath" name="fileNamePath" value=""></s:hidden>
		</form>					
		<div class="clear height5px"></div>
</div>																		
<!-- END WRAPPER -->
</div>		
<!-- END MAIN -->
	<s:hidden id="fromdate_hidd" value=""></s:hidden>
	<s:hidden id="todate_hidd" value=""></s:hidden>	
	<jsp:include page="../common/footer.jsp"></jsp:include>
    <jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script>
	<script type="text/javascript">	
		 $(document).ready(function() {			
			var lvrClafrm = "<%=calfr%>";
			if (lvrClafrm!=""&&lvrClafrm=="nflg") {
				var lvrdocid = "<%=ivrDocid%>";
				var lvrshrdusrid = "<%=ivrShareuserid%>";
				documentview(lvrdocid,'',lvrshrdusrid);
			} else {					
					createoTable();		
					toLoadAutoComplate();
			}								
				$("#gobckbtnid").click(function(){
					$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
					toShowLoadingImgoverlay();
					$("#userCancelForm").attr("action", "utititymgmt");
					$("#userCancelForm").submit();
				
				});	   
		});			 
		function createoTable() {
		 	$.ajax({
		 		type : 'post',
		 		datatype : 'html',
		 		url : 'getDocumentViewValue',
		 		data : 'docFolder=0&docSubFolder=0&DDT=&docFolderDate=&docFileName=&docShareUsrId=0&docFileName=&searchVal=&searchFlag=false',
		 		success : function(data) {
		 			if(data.trim()=="No record"){
		 				$("#folderView").html('');
		 				$("#folderviewalert").show();
		 				$("#folderviewalert").html("<div style=\"width: 98%;margin-left: 1%\" id=\"card-alert\" class=\"card danger\"> <div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>No Document Found</p> <button type=\"button\" class=\"close white-text\" data-dismiss=\"alert\" aria-label=\"Close\" style=\"margin-top:8px;\"><span aria-hidden=\"true\">×</span> </button></div>");
		 				//$("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card success\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>No record found</p></div><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button></div></div>");
		 			}else{
		 				$("#folderviewalert").hide();
						$("#folderviewalert").html("");
		 				$("#folderView").html(data);
		 				$('.tooltipped').tooltip("remove");
						 $('.tooltipped').tooltip({delay: 10});
		 			}
		 			
		 		},error:function (jqXHR, exception){
		 		      var msg = 'Error In Processing';
		 		     $("#folderviewalert").show();
		 		     $("#folderviewalert").html("<div style=\"width: 98%;margin-left: 1%\" id=\"card-alert\" class=\"card danger\"> <div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>No Document Found</p> <button type=\"button\" class=\"close white-text\" data-dismiss=\"alert\" aria-label=\"Close\" style=\"margin-top:8px;\"><span aria-hidden=\"true\">×</span> </button></div>");
		 			
		 		}
		 	});
		 	
			
		}
		
		function getadditionalfolder(childdata,docFolder,docSubFolder,docShareUsrId,docFolderDate,docFileName,th){
			$("#"+th.id+" .imagehide").hide();
			$("#"+th.id+" .imageshow").show();
			var searchval= $("#searchValue").val();
			var searchflag=$("#searchFlag").val();
			
			$.ajax({
		 		type : 'post',
		 		datatype : 'html',
		 		url : 'getDocumentViewValue',
		 		data : 'docFolder='+docFolder+'&docSubFolder='+docSubFolder+'&docFolderDate='+docFolderDate+'&docShareUsrId='+docShareUsrId+'&docFileName=&searchVal='+searchval+'&searchFlag='+searchflag,
		 		success : function(data) {				 				 	
			 		if(data.trim() != "No record"){
			 			$("#"+childdata).html(data);
			 			$("#folderviewalert").hide();
						$("#folderviewalert").html("");
				 	} else {
					 	
					}
		 			
		 			$('.tooltipped').tooltip("remove");
					 $('.tooltipped').tooltip({delay: 10});
		 			
		 		},error:function (jqXHR, exception){
		 		      var msg = 'Error In Processing';
		 		     $("#folderviewalert").show();
		 		     $("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card danger\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>"+msg+"</p></div><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button></div></div>");
				 			
				 		}
		 	});
			
		}
		function edituser(id) {
			$("#uniqUserIdEdit").val(id);
			$("#delgroupform").attr("action", "edituserform");
			$("#delgroupform").submit();
		}
	function deleteuser(id) {
		
		swal({    title: "Are you sure want to delete?",
            text: "You will not be able to recover this document detail !",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "Yes",   
            closeOnConfirm: false }, 
            function(){
				
				$("#deleteusridEdit").val(id);
				$("#delgroupform").attr("action", "deleteuseraction");
				$("#delgroupform").submit();
		});		
		}
		function home(){
					$("#releaseHisFormid").attr("action", "homeusercreation");
					$("#releaseHisFormid").submit();
		}
		function showfolderimage(th){
			$("#"+th.id+" .imagehide").hide();
			$("#"+th.id+" .imageshow").show();
		}
		function actionshare(th){
			$("#shareid").toggle();
		    $("#verticalline").toggle();

		}
		
	 function toLoadAutoComplate(){
		 $.ajax({
				type : 'post',
				datatype : 'json',
				url : 'getuserdetail',
				data : '',
				success : function(data) {
					data=data.replace(/&quot;/ig,'"');
					data=data.replace(/%27/ig,"'");
					var loc_state_val = JSON.parse(data);
					$("#usernames").tokenInput(loc_state_val, {
	                propertyToSearch: "searchkey",
	                resultsFormatter: function(item){ return "<li>" + "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.fName +" "+item.lName+" "+item.flatNo+ "<br><div  >" + item.mobilNum + "</div></div></div></li>" },
	                tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
	                theme: "facebook"});  
					$("#residentnames").tokenInput(loc_state_val, {
		                propertyToSearch: "searchkey",
		                resultsFormatter: function(item){ return "<li>" + "<img src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.fName +" "+item.lName+" "+item.flatNo+ "<br><div  >" + item.mobilNum + "</div></div></div></li>" },
		                tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
		                theme: "facebook"});  
					// $("div.token-input-dropdown-facebook").removeAttr("css");
	           /*  $(".token-input-dropdown-facebook").css("z-index","260000");
	            $(".token-input-dropdown-facebook").css("width","462px");
	            $("ul .token-input-list-facebook").css("width","462px");
	            $("div.token-input-dropdown-facebook").css("width","462px");
	            $("ul.token-input-list-facebook").css("width","100%");$("ul.token-input-list-facebook").css("min-height","100px");  
				$("ul.token-input-list-facebook").css("max-height","162px"); $("ul.token-input-list-facebook").css("height","auto");  
				$("ul.token-input-list-facebook").css("overflow-y","auto");$("ul.token-input-list-facebook").css(" border-color","#C4C4C4");  */
				}
			}); 
		 
	 }
	 function actionDelete(childdata,docFolder,docSubFolder,docShareUsrId,docFolderDate,docFileName,th,docid,docSubFolder){
		swal({
			title : "Are you sure want to delete?",
			text : "You will not be able to recover this document detail!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes",
			closeOnConfirm : false
			},
					
		function() {
			 toShowLoadingImgoverlay(); 				
		$.ajax({
		type : 'post',
		datatype : 'html',
		url : 'documentDelete',
		data : 'sdocumentId=' + docid+ '&sdocSubFolder=' + docSubFolder+'&docShareUsrId='+docShareUsrId,
		success : function(data) {
			tohideLoadingImgoverlay();
			$("#folderviewalert").hide();
			$("#folderviewalert").html("");
			$("#AlertDivData").html(data);
			//getAdditionalDatafolder(childdata,docFolder, docSubFolder,docShareUsrId, docFolderDate,docFileName);			
			if(docSubFolder=='2') {
				getadditionalfolder("sfd22",docFolder,'0','0','','',this);				
			} else {
				getadditionalfolder("fd2",docFolder,'0','0','','',this);
			}
			
		 	tohideLoadingImgoverlay(); 
			swal("Deleted!", "Successfully Deleted.", "success");
		},
		error : function(jqXHR, exception) {
		var msg = 'Error In Processing';
		$("#folderviewalert").show();
		$("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card danger\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>"
		+ msg+ "</p></div><button class=\"close\" data-dismiss=\"alert\" type=\"button\">×</button></div></div>");
		/* tohideLoadingImgoverlay(); */
		}
		});
		});
		}
		function getAdditionalDatafolder(childdata, docFolder, docSubFolder,docShareUsrId, docFolderDate, docFileName) {

			$.ajax({
						type : 'post',
						datatype : 'html',
						url : 'getDocumentViewValue',
						data : 'docFolder=' + docFolder + '&docSubFolder='
								+ docSubFolder + '&docFolderDate='
								+ docFolderDate + '&docShareUsrId='
								+ docShareUsrId,
						success : function(data) {
							$("#folderviewalert").hide();
							$("#folderviewalert").html("");
							$("#" + childdata).html(data);
							tohideLoadingImgoverlay();
						},
						error : function(jqXHR, exception) {
							tohideLoadingImgoverlay();
							var msg = 'Error In Processing';
							$("#folderviewalert").show();
							$("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card danger\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>" + msg + "</p></div><button style=\"margin-top:8px;\" aria-label=\"Close\" data-dismiss=\"alert\" class=\"close white-text\" type=\"button\"><span aria-hidden=\"true\">×</span> </button></div></div>");

						}
					});
		}
		function documentview(docid, docsubfolder, sdocShareUsrId) {
			toShowLoadingImgoverlay();
			$.ajax({
						type : 'post',
						datatype : 'html',
						url : 'getViewDocumentValue',
						data : 'docId=' + docid + '&sdocShareUsrId='+ sdocShareUsrId,
						success : function(data) {
							$("#folderviewalert").hide();
							$("#folderviewalert").html("");
							$("#dataviewcontent").hide();
							$("#filterdivid").hide();
							$("#createdocidform").hide();
							$("#gobackformid").show();
							tohideLoadingImgoverlay();
							$("#generalDocumentdiv").html(data);
							
						},
						error : function(jqXHR, exception) {
							var msg = 'Error In Processing';
							$("#folderviewalert").show();
							$("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card danger\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>"
													+ msg + "</p></div><button style=\"margin-top:8px;\" aria-label=\"Close\" data-dismiss=\"alert\" class=\"close white-text\" type=\"button\"><span aria-hidden=\"true\">×</span> </button></div></div>");

						}
					});

		}
		function documentedit(docid, docsubfolder, shareDocument) {
			toShowLoadingImgoverlay();
			$
					.ajax({
						type : 'post',
						datatype : 'html',
						url : 'getDocumentEditValue',
						data : 'docId=' + docid + '&shareDocument='
								+ shareDocument,
						success : function(data) {

							$("#dataviewcontent").hide();
							$("#filterdivid").hide();
							$("#createdocidform").hide();
							tohideLoadingImgoverlay();
							//$('.tooltipped').tooltip("remove");
							$("#generalDocumentdiv").html(data);

							//$('.tooltipped').tooltip({delay: 10});

						},
						error : function(jqXHR, exception) {
							tohideLoadingImgoverlay();
							var msg = 'Error In Processing';
							$("#folderviewalert").show();
							$("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card danger\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>"
													+ msg + "</p></div><button style=\"margin-top:8px;\" aria-label=\"Close\" data-dismiss=\"alert\" class=\"close white-text\" type=\"button\"><span aria-hidden=\"true\">×</span> </button></div></div>");

						}
					});

		}
		function filter() {
			var searchval = $("#searchnaameTextId").val();

			if (searchval.length > 0) {
				$("#searchValue").val(searchval);
				$("#searchFlag").val("true");
				searchflag = $("#searchFlag").val();
				toShowLoadingImgoverlay();
				$.ajax({
							type : 'post',
							datatype : 'html',
							url : 'getDocumentViewValue',
							data : 'docFolder=0&docSubFolder=0&DDT=&docFolderDate=&docFileName=&docShareUsrId=0&docFileName=&searchVal='
									+ searchval + '&searchFlag=' + searchflag,
							success : function(data) {
								if (data.trim() == "No record") {
									$("#folderView").html('');
									$("#folderviewalert").show();
									$("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card danger\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>No record found</p></div><button style=\"margin-top:8px;\" aria-label=\"Close\" data-dismiss=\"alert\" class=\"close white-text\" type=\"button\"><span aria-hidden=\"true\">×</span> </button></div></div>");
								} else {
									$("#folderviewalert").hide();
									$("#folderviewalert").html("");
									$("#folderView").html(data);
								}
								tohideLoadingImgoverlay();
							},
							error : function(jqXHR, exception) {
								var msg = 'Error In Processing';
								$("#folderviewalert").show();
								$("#folderviewalert").html("<div id=\"AlertDivData\"><div id=\"card-alert\" class=\"card danger\"><div class=\"card-content white-text\"><p><i class=\"mdi-alert-error\"></i>"+ msg + "</p></div><button style=\"margin-top:8px;\" aria-label=\"Close\" data-dismiss=\"alert\" class=\"close white-text\" type=\"button\"><span aria-hidden=\"true\">×</span> </button></div></div>");
								tohideLoadingImgoverlay();
							}
						});

			} else {
				createoTable();
			}			
		}
		function dwnldfliedocpage(subval){
			$("#downloadFormval").val(subval);
			$("#downloadFormiddocview").submit();	
		}
	</script>
</body>
</html>
