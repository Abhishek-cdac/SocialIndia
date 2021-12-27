<%@page import="java.net.URLEncoder"%>
<%@page import="com.socialindia.login.EncDecrypt"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type"
	content="<s:text name="meta.contentType"/>">
<meta name="viewport" content="<s:text name="meta.viewport"/>">
<meta http-equiv="X-UA-Compatible"
	content="<s:text name="meta.X-UA-Compatible"/>">
<meta name="msapplication-tap-highlight"
	content="<s:text name="meta.msapplication-tap-highlight"/>">
<meta name="description" content="<s:text name="meta.description"/>">
<meta name="keywords" content="<s:text name="meta.keywords"/>">
<jsp:include page="../common/icodisplyfile.jsp"></jsp:include>
<title><s:text name="Text.Title" /></title>
<jsp:include page="../common/basiccss.jsp"></jsp:include>

</head>

<body onload="getSocyteaList()">
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
					<h5 class="breadcrumbs-title">
						Portal Rights Mgmt
					</h5>
					<ol class="breadcrumbs left">
						<li><a href="loginform"><s:text name="Dashboard" /></a></li>
						<li><a href="#">Admin Mgmt</a></li>
						<li class="active">Portal Rights Mgmt</li>
					</ol>
				</div>
			</div>
		</div>
				</div>
				<!--breadcrumbs end-->
<%-- 				<jsp:include page="../common/Alert.jsp" flush="true" /> --%>

				 <div style="width: 30%;margin-left:2%;display: none;" id="card-alert" class="card success">
					<div class="card-content white-text">
		                 <p id="rights-status"><i class="mdi-navigation-check"></i></p>
		             </div>
					 <button type="button" class="close white-text" data-dismiss="alert" aria-label="Close" style="margin-top:8px;"><span aria-hidden="true">×</span> </button>			
				</div>

				<!--start container-->
				<div class="container">
		<div class="card-panel">
			<s:form role="form" id="appRightsId" name="appRightsId"	method="post">
<!-- 				<input type="checkbox" style="display: none;"/> -->
				<select name="socyteaList" id="socyteaList"></select>

				<div id="cblist">
				</div>


				<div class="row" style="margin-left: 41px;">
					<button type="button" id="userCreateButtonId" onclick="setAccessFlags()" class="<s:text name="button.color.submit"/>">
						<s:text name="Set" />
						<i class="<s:text name="button.icon.submitcard"/>"></i>
					</button>
					<button type="button" id="" class=" <s:text name="button.color.cancel"/>" onclick="home();">
						<s:text name="Text.button.cancel" />
						<i class="<s:text name="button.icon.replycard"/>"></i>
					</button>
				</div>
			</s:form>
			
			<s:form method="post" id="rightsHomeFormid"></s:form>
		</div>
				</div>
				<!--end container-->
			</section>
		</div>
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>

</body>

<% 
String basePath = "\"" + request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/" + "\"" ;

JSONObject obj = new JSONObject();
obj.put("servicecode", "SI0030");       

JSONObject lvrRqstdatajsonobj = new JSONObject();       
lvrRqstdatajsonobj.put("count1", "yes");
lvrRqstdatajsonobj.put("countF1", "yes");
lvrRqstdatajsonobj.put("societystatus", "1");
lvrRqstdatajsonobj.put("srchdtsrch", "");// overalltotal row
lvrRqstdatajsonobj.put("startrow", "0");// starting row
lvrRqstdatajsonobj.put("totalrow", "99");// overalltotal row
lvrRqstdatajsonobj.put("sSoctyId", -1);
obj.put("data", lvrRqstdatajsonobj);

String jsonTextFinal = obj.toString();
jsonTextFinal=EncDecrypt.encrypt(jsonTextFinal); 
String temp="\"ivrparams="+ URLEncoder.encode(jsonTextFinal)+"\"";

%>

<script type="text/javascript">

function home(){
	$("#rightsHomeFormid").attr("action", "Homeform");
	$("#rightsHomeFormid").submit();
}

function chkunchk(chk){
	var chkval = chk.value;
	
	if (chkval == "buysell") {
		if ($(chk).is(':checked')) {
			$("#publishasbuyerorseller").prop('checked', true);
			$("#whatsonstore").prop('checked', true);
		}
		else{
			$("#publishasbuyerorseller").prop('checked', false);
			$("#whatsonstore").prop('checked', false);
		}
	}
	else if (chkval == "publishasbuyerorseller" || chkval == "whatsonstore") {
		if ($(chk).is(':checked')) {
			$("#buysell").prop('checked', true);
		}
	}
	else if (chkval == "sharecare") {
		if ($(chk).is(':checked')) {
			$("#carpooling").prop('checked', true);
			$("#donate").prop('checked', true);
			$("#publishskils").prop('checked', true);
		}
		else{
			$("#carpooling").prop('checked', false);
			$("#donate").prop('checked', false);
			$("#publishskils").prop('checked', false);
		}
	}
	else if (chkval == "carpooling" || chkval == "donate" || chkval == "publishskils") {
		if ($(chk).is(':checked')) {
			$("#sharecare").prop('checked', true);
		}
	}
	else if (chkval == "skillhelp") {
		if ($(chk).is(':checked')) {
			$("#skilldirctory").prop('checked', true);
			$("#myservicebooking").prop('checked', true);
		}
		else{
			$("#skilldirctory").prop('checked', false);
			$("#myservicebooking").prop('checked', false);
		}
	}
	else if (chkval == "skilldirctory" || chkval == "myservicebooking") {
		if ($(chk).is(':checked')) {
			$("#skillhelp").prop('checked', true);
		}
	}
	else if (chkval == "mydocuments") {
		if ($(chk).is(':checked')) {
			$("#knowledgebase").prop('checked', true);
			$("#personalbriefcase").prop('checked', true);
			$("#societyinfo").prop('checked', true);
			$("#searchdocument").prop('checked', true);
		}
		else{
			$("#knowledgebase").prop('checked', false);
			$("#personalbriefcase").prop('checked', false);
			$("#societyinfo").prop('checked', false);
			$("#searchdocument").prop('checked', false);
		}
	}
	else if (chkval == "knowledgebase" || chkval == "personalbriefcase" || chkval == "societyinfo" || chkval == "searchdocument") {
		if ($(chk).is(':checked')) {
			$("#mydocuments").prop('checked', true);
		}
	}
	else if (chkval == "facilitybooking") {
		if ($(chk).is(':checked')) {
			$("#newbooking").prop('checked', true);
		}
		else{
			$("#newbooking").prop('checked', false);
		}
	}
	else if (chkval == "newbooking") {
		if ($(chk).is(':checked')) {
			$("#facilitybooking").prop('checked', true);
		}
	}
	else if (chkval == "personalevents") {
		if ($(chk).is(':checked')) {
			$("#createinvitation").prop('checked', true);
		}
		else{
			$("#createinvitation").prop('checked', false);
		}
	}
	else if (chkval == "createinvitation") {
		if ($(chk).is(':checked')) {
			$("#personalevents").prop('checked', true);
		}
	}
	else if (chkval == "gatepass") {
		if ($(chk).is(':checked')) {
			$("#issuegatepass").prop('checked', true);
		}
		else{
			$("#issuegatepass").prop('checked', false);
		}
	}
	else if (chkval == "issuegatepass") {
		if ($(chk).is(':checked')) {
			$("#gatepass").prop('checked', true);
		}
	}
	else if (chkval == "timelinefeeds") {
		if ($(chk).is(':checked')) {
			$("#feeds").prop('checked', true);
			$("#onlineuser").prop('checked', true);
		}
		else{
			$("#feeds").prop('checked', false);
			$("#onlineuser").prop('checked', false);
		}
	}
	else if (chkval == "feeds" || chkval == "onlineuser") {
		if ($(chk).is(':checked')) {
			$("#timelinefeeds").prop('checked', true);
		}
	}
	else if (chkval == "easypay") {
		if ($(chk).is(':checked')) {
			$("#mypassport").prop('checked', true);
			$("#utilitypayments").prop('checked', true);
		}
		else{
			$("#mypassport").prop('checked', false);
			$("#utilitypayments").prop('checked', false);
		}
	}
	else if (chkval == "mypassport" || chkval == "utilitypayments") {
		if ($(chk).is(':checked')) {
			$("#easypay").prop('checked', true);
		}
	}
	else if (chkval == "complaints") {
		if ($(chk).is(':checked')) {
			$("#postcomplaint").prop('checked', true);
		}
		else{
			$("#postcomplaint").prop('checked', false);
		}
	}
	else if (chkval == "postcomplaint") {
		if ($(chk).is(':checked')) {
			$("#complaints").prop('checked', true);
		}
	}
	else if (chkval == "shophome") {
		if ($(chk).is(':checked')) {
			$("#browsebycategory").prop('checked', true);
			$("#searchwithkeywords").prop('checked', true);
			$("#rateyourexperience").prop('checked', true);
			$("#reportproblem").prop('checked', true);
		}
		else{
			$("#browsebycategory").prop('checked', false);
			$("#searchwithkeywords").prop('checked', false);
			$("#rateyourexperience").prop('checked', false);
			$("#reportproblem").prop('checked', false);
		}
	}
	else if (chkval == "browsebycategory" || chkval == "searchwithkeywords" || chkval == "rateyourexperience" || chkval == "reportproblem") {
		if ($(chk).is(':checked')) {
			$("#shophome").prop('checked', true);
		}
	}
}

function setAccessFlags(){
	swal({   title: "Confirmation",   
    text: "Do you want to Confirm to Assign rights?",   
    type: "info",   showCancelButton: true,   
    closeOnConfirm: true,   
     }, 
    function(){   
    	submitAccessFlags();
	});
}

function submitAccessFlags(){

	if($('#socyteaList').val() == ""){
		alert("Select Socytea");
		return;
	}
	
	var req_data = {
		"society":$('#socyteaList').val(),
		"method":"set",
		"rights":"portal",
		"flag":1,
		"buysell":$("#buysell").prop('checked') == true ? 1 : 0,
		"sharecare":$("#sharecare").prop('checked') == true ? 1 : 0,
		"skillhelp":$("#skillhelp").prop('checked') == true ? 1 : 0,
		"mydocuments":$("#mydocuments").prop('checked') == true ? 1 : 0,				
		"facilitybooking":$("#facilitybooking").prop('checked') == true ? 1 : 0,
		"buysell":$("#buysell").prop('checked') == true ? 1 : 0,
		"personalevents":$("#personalevents").prop('checked') == true ? 1 : 0,
		"gatepass":$("#gatepass").prop('checked') == true ? 1 : 0,
		"profileinfo":$("#profileinfo").prop('checked') == true ? 1 : 0,
		"timelinefeeds":$("#timelinefeeds").prop('checked') == true ? 1 : 0,
		"onlineusers":$("#onlineusers").prop('checked') == true ? 1 : 0,
		"shophome":$("#shophome").prop('checked') == true ? 1 : 0,
		"easypay":$("#easypay").prop('checked') == true ? 1 : 0,
		"complaints":$("#complaints").prop('checked') == true ? 1 : 0,
		"publishasbuyerorseller":$("#publishasbuyerorseller").prop('checked') == true ? 1 : 0,
		"whatsonstore":$("#whatsonstore").prop('checked') == true ? 1 : 0,
		"carpooling":$("#carpooling").prop('checked') == true ? 1 : 0,
		"donate":$("#donate").prop('checked') == true ? 1 : 0,
		"publishskils":$("#publishskils").prop('checked') == true ? 1 : 0,
		"skilldirctory":$("#skilldirctory").prop('checked') == true ? 1 : 0,
		"myservicebooking":$("#myservicebooking").prop('checked') == true ? 1 : 0,
		"knowledgebase":$("#knowledgebase").prop('checked') == true ? 1 : 0,
		"personalbriefcase":$("#personalbriefcase").prop('checked') == true ? 1 : 0,
		"societyinfo":$("#societyinfo").prop('checked') == true ? 1 : 0,
		"searchdocument":$("#searchdocument").prop('checked') == true ? 1 : 0,
		"newbooking":$("#newbooking").prop('checked') == true ? 1 : 0,
		"createinvitation":$("#createinvitation").prop('checked') == true ? 1 : 0,
		"issuegatepass":$("#issuegatepass").prop('checked') == true ? 1 : 0,
		"feeds":$("#feeds").prop('checked') == true ? 1 : 0,
		"onlineuser":$("#onlineuser").prop('checked') == true ? 1 : 0,
		"browsebycategory":$("#browsebycategory").prop('checked') == true ? 1 : 0,
		"searchwithkeywords":$("#searchwithkeywords").prop('checked') == true ? 1 : 0,
		"rateyourexperience":$("#rateyourexperience").prop('checked') == true ? 1 : 0,
		"reportproblem":$("#reportproblem").prop('checked') == true ? 1 : 0,
		"mypassport":$("#mypassport").prop('checked') == true ? 1 : 0,
		"utilitypayments":$("#utilitypayments").prop('checked') == true ? 1 : 0,
		"postcomplaint":$("#postcomplaint").prop('checked') == true ? 1 : 0,
				"postcomplaint":$("#postcomplaint").prop('checked') == true ? 1 : 0		
		};
	
	var req_json = {
			"servicecode":"SI0030",
			"data":req_data
	};

// 	alert(req_data.buysell);
	
	var req_str = JSON.stringify(req_json);

	encDataSet(req_str);
}

function encDataSet(param){
	
	$.ajax({
		url : <%=basePath%>+"/SocialindiaServices-main/encryptData",
		type : "post",
		data : "ivrparams="+param,
		success : function(data) {
// 	 		alert("encData:"+data.message);
	 		updateAccessFlags(data.data.ivrparams);
		},
		error : function(data, status, er) {
			console.log(data + "_" + status + "_" + er);
		},
	});
}


function updateAccessFlags(param){
	
	param = param.replace("ivrparams=", "");
	
	$.ajax({
		url : <%=basePath%>+"/SocialindiaServices-main/appRightsMgmt",
		type : "post",
		data : "ivrparams="+param,
		success : function(data) {
	 		//alert("setAccessFlags:"+data);
// 	 		swal("SUCCESS", 'Rights Assigned Successfully');
	 		$('#rights-status').text('Rights Assigned Successfully');
	 		$('#card-alert').css('background-color', 'green');
			$('#card-alert').css("display", "block");
		},
		error : function(data, status, er) {
// 			swal("ERROR", 'Error Occured Please Contact Support.');
			$('#rights-status').text('Error Occured Please Contact Support.');
			$('#card-alert').css('background-color', 'red');
			$('#card-alert').css("display", "block");			
			console.log(data + "_" + status + "_" + er);
		},
	});
}



$('#socyteaList').on('change',function(){
	
	$('#card-alert').css("display", "none");
	
    var socytea = $(this).val();

    if(socytea == ""){
    	$('#cblist').html('');
    }
    else{
    	
    	$('#cblist').html('');//clear chbox
    	
	    var req_data = {
		"society":socytea,
		"method":"get",
		"flag":1,
		"rights":"portal",
		};
	
		var req_json = {
		"servicecode":"SI0030",
		"data":req_data
		};
	
		var req_str = JSON.stringify(req_json);
	
		encDataGet(req_str);
    }

});

function encDataGet(param){
	
	$.ajax({
		url : <%=basePath%>+"/SocialindiaServices-main/encryptData",
		type : "post",
		data : "ivrparams="+param,
		success : function(data) {
// 	 		alert("encData:"+data.data.ivrparams);
	 		getAccessFlags(data.data.ivrparams);
		},
		error : function(data, status, er) {
			console.log(data + "_" + status + "_" + er);
		},
	});
}

function getAccessFlags(param){
	
	param = param.replace("ivrparams=", "");
	
	$.ajax({
		url : <%=basePath%>+"/SocialindiaServices-main/appRightsMgmt",
		type : "post",
		data : "ivrparams="+param,
		success : function(data) {
			var flags = data.data.onewaycommunication[0];
// 	 		alert("getAccessFlags:"+flags.buysell);
	 		
	 		addCheckbox("buysell", flags.buysell == 1 ? true : false, false);
	 		addCheckbox("publishasbuyerorseller", flags.publishasbuyerorseller == 1 ? true : false, true);
	 		addCheckbox("whatsonstore", flags.whatsonstore == 1 ? true : false, true);
	 		
	 		addCheckbox("sharecare", flags.sharecare == 1 ? true : false, false);
	 		addCheckbox("carpooling", flags.carpooling == 1 ? true : false, true);
	 		addCheckbox("donate", flags.donate == 1 ? true : false, true);
	 		addCheckbox("publishskils", flags.publishskils == 1 ? true : false, true);
	 		
	 		addCheckbox("skillhelp", flags.skillhelp == 1 ? true : false, false);
	 		addCheckbox("skilldirctory", flags.skilldirctory == 1 ? true : false, true);
	 		addCheckbox("myservicebooking", flags.myservicebooking == 1 ? true : false, true);
	 		
	 		addCheckbox("mydocuments", flags.mydocuments == 1 ? true : false, false);
	 		addCheckbox("knowledgebase", flags.knowledgebase == 1 ? true : false, true);
	 		addCheckbox("personalbriefcase", flags.personalbriefcase == 1 ? true : false, true);
	 		addCheckbox("societyinfo", flags.societyinfo == 1 ? true : false, true);
	 		addCheckbox("searchdocument", flags.searchdocument == 1 ? true : false, true);
	 		
	 		addCheckbox("facilitybooking", flags.facilitybooking == 1 ? true : false, false);
	 		addCheckbox("newbooking", flags.newbooking == 1 ? true : false, true);
	 		
	 		addCheckbox("personalevents", flags.personalevents == 1 ? true : false, false);
	 		addCheckbox("createinvitation", flags.createinvitation == 1 ? true : false, true);
	 		
	 		addCheckbox("gatepass", flags.gatepass == 1 ? true : false, false);
	 		addCheckbox("issuegatepass", flags.issuegatepass == 1 ? true : false, true);
	 		
	 		addCheckbox("profileinfo", flags.profileinfo == 1 ? true : false, false);
	 		
	 		addCheckbox("timelinefeeds", flags.timelinefeeds == 1 ? true : false, false);
	 		addCheckbox("feeds", flags.feeds == 1 ? true : false, true);
	 		addCheckbox("onlineuser", flags.onlineuser == 1 ? true : false, true);
	 		
	 		addCheckbox("shophome", flags.shophome == 1 ? true : false, false);
	 		addCheckbox("browsebycategory", flags.browsebycategory == 1 ? true : false, true);
	 		addCheckbox("searchwithkeywords", flags.searchwithkeywords == 1 ? true : false, true);
	 		addCheckbox("rateyourexperience", flags.rateyourexperience == 1 ? true : false, true);
	 		addCheckbox("reportproblem", flags.reportproblem == 1 ? true : false, true);
	 		
	 		addCheckbox("easypay", flags.easypay == 1 ? true : false, false);
	 		addCheckbox("mypassport", flags.mypassport == 1 ? true : false, true);
	 		addCheckbox("utilitypayments", flags.utilitypayments == 1 ? true : false, true);
	 		
	 		addCheckbox("complaints", flags.complaints == 1 ? true : false, false);
	 		addCheckbox("postcomplaint", flags.postcomplaint == 1 ? true : false, true);
	 		
		},
		error : function(data, status, er) {
			console.log(data + "_" + status + "_" + er);
		},
	});
}

function addCheckbox(name, val, space) {
	   var container = $('#cblist');
	   
	   if(space){
	   		$('<input />', { type: 'checkbox', id: name, value: name, checked:val, onclick:"chkunchk(this)"}).appendTo(container);
	   		$('<label />', { 'for': name, text: name, style:"margin-left: 25px;"  }).appendTo(container);
	   }
	   else{
		   $('<input />', { type: 'checkbox', id: name, value: name, checked:val, onclick:"chkunchk(this)" }).appendTo(container);
		   $('<label />', { 'for': name, text: name }).appendTo(container);
	   }
	   
	   $('<br /><br />').appendTo(container);
}

function getSocyteaList(){

$.ajax({
	url : <%=basePath%>+"/SocialindiaServices-main/siSocietymgmt",
	type : "post",
	data : <%= temp %>,
	success : function(data) {
// 		alert(data.data.InitCount);
		var societyMgmt = data.data.societyMgmt;
		
		// Initialize
		$('select').material_select();
		
		// Clear the content
	    $("select").empty().html('');
		
		//$('#socyteaList').empty(); // clear the current elements in select box
		$('select').append($("<option></option>").attr("value","").text("---Select Society---"));//append options
		for(var i=0; i < societyMgmt.length; i++){	
            //$('#socyteaList').append($("<option></option>").attr("value",societyMgmt[i].societyid).text(societyMgmt[i].societyname));//append options
            $('select').append($("<option></option>").attr("value",societyMgmt[i].activationkey).text(societyMgmt[i].societyname));//append options
		}
		
		// Update the content clearing the caret
	    $("select").material_select('update');
		
		//$('.initialized').selectpicker('refresh');//refresh dropdown
		//$('#socyteaList').trigger("chosen:updated");
		//$('#socyteaList').selectmenu("refresh");
		 //$("#socyteaList").trigger('contentChanged');
// 		$(next_id).material_select();
	},
	error : function(data, status, er) {
		console.log(data + "_" + status + "_" + er);
	},
});

}
	
</script>

</html>