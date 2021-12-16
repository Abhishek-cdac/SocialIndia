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
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
<!-- For Windows Phone -->

<jsp:include page="../common/basiccss.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/drop_uploader.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input.css" />
		<link type="text/css" rel="stylesheet" href="<s:text name='Resource.path'/>/css/token-input-facebook.css?ee" />
</head>
<style>#token-input-invitefriend{width: 100% ! important; border-bottom : 1px solid #9e9e9e !important; }</style>
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
							<div class=" col s12 m12 l12">
								<h5 class="breadcrumbs-title"><s:text name="Text.Utility.Management.Details" /></h5>
								<ol class="breadcrumbs left">
							<li><a href="posthomepage"><s:text name="Breadcrumb.dashboard" /></a></li>
							<li class="active"><a href="eventMgmt"><s:text name="Text.Utility.Management"></s:text></a></li>
							<li><a href="eventMgmt"><s:text name="Text.Utility.Management.event"/></a></li>
							<li class="active"><s:text name="Text.Utility.Management.Details" /></li>
						</ol>
						<div class="right">
									<button id="gobckbtnid"
											class="<s:text name="button.color.cancel" />" data-toggle="modal" data-target="#addnewaccount" data-position="bottom" data-delay="<s:text name="material.tooltip.delay"/>" data-tooltip="Go Back" type="submit" name="action">
											Go Back <i class="<s:text name="button.icon.replycard"/>"></i>
										</button>
								</div>
					</div>

							</div>
						</div>
					</div>
				<div class="container">
				<div id="alertmsgsuccess" class="card success" style="display: none;width: 98%;margin-left: 1%;" onclick="hideFunction();">
				<div class="card-content white-text">
                        <p><i class="mdi-navigation-check"></i> Event Shared Successfully.</p>
                      </div></div>
				<div id="alertmsgerror" class="card error" style="display: none;width: 98%;margin-left: 1%;" onclick="hideFunction();">
  									<div class="card-content white-text">
                        <p><i class="mdi-navigation-check"></i> Shared Error.</p>
                      </div>
					</div>
					
					<div class="card-panel">
						<div class="clear" style="height: 5px;"></div>
						<form method="post" id="registerformid" name="registerformid" action="">
							<div class="row">
								<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5"> <s:text name="Text.event.function.for" /> </div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="eventobj.eventfuname" /></span>
										</div>
									</div>
								</div>

								<div class="input-field col s6">
									<div class="form-group" id="divage">
										<div class="input-field col m5">
											Event Image
										</div>
										<div class="input-field col m7">
											<div style="float: left"> : &nbsp;</div>
											<div style="float: left">
												<s:if test="#session.eveimg!=null && #session.eveimg!=''">
													  <img class="hoverable pointer" id="imgeventid" style="width: 40px; max-width: 40px; max-height: 40px; display: block;" src="/templogo/event/mobile/<s:property value="#session.EVENTID"/>/<s:property value="#session.eveimg"/>" onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/event/web/<s:property value="#session.EVENTID"/>/<s:property value="#session.eveimg"/>')">
												</s:if>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">

								<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5"> Title </div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"> <s:property value="eventobj.evetitle" /></span>
										</div>
									</div>
								</div>

								<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5">
										Event Template
										</div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"><s:property value="eventobj.eventfuntempatetext" /></span>
										</div>
									</div>
								</div>


							</div>
							<div class="row">
								<div class="input-field col s6">
									<div class="form-group" id="divage">
										<div class="input-field col m5">
											Start Date
										</div>
										<div class="input-field col m7">:
										<span class="<s:text name="view.fontvalue.color" />"> <s:property value="eventobj.evestartdate" /></span>
										</div>
									</div>
								</div>

							<div class="input-field col s6">
								<div class="form-group" id="divage">
									<div class="input-field col m5">End Date</div>
									<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"><s:property
											value="eventobj.eveenddate" /></span>
											</div>
								</div>
							</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<div class="form-group" id="divage">
								<div class="input-field col m5">Start Time</div>
								<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property
										value="eventobj.evestarttime" /></span>
										</div>

							</div>
						</div>
						<div class="input-field col s6">
							<div class="form-group" id="divage">
								<div class="input-field col m5">End Time</div>
								<div class="input-field col m7"> : <span class="<s:text name="view.fontvalue.color" />"> <s:property
										value="eventobj.eveendtime" /></span>
										</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
									<div class="form-group">
										<div class="input-field col m5">
										Facility
										</div>
										<div class="input-field col m7">
											: <span class="<s:text name="view.fontvalue.color" />"><s:property value="lvrEvntfacilitytext" /></span>
										</div>
									</div>
						</div>
						<div class="input-field col s6">
								<div class="input-field col m5">Event Location</div>
								<div class="input-field col m7"> :<span class="<s:text name="view.fontvalue.color" />"> <s:property
										value="eventobj.evelocation" /></span>
										</div>
						</div>
					</div>

				<div class="row">
				<div class="input-field col s6">
										<div class="input-field col m5">Event Type</div>
										<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">
										<s:property value="eventtype" /></span>
							</div>
						</div>
				</div>
					<!-- <div class="row">
					<div class="input-field col s12">
								<div class="input-field col" style="width: 20.7%">Video Path</div>
								<div class="input-field col m9"> : <span class="<s:text name="view.fontvalue.color" />"> <a target="_blank"
									href="<s:property value="eventobj.evevideopath"/>"><span class="videohover <s:text name="view.fontvalue.color" />">
										<s:property value="eventobj.evevideopath" /></span></a></span>
							</div>
						</div>
					</div> -->

					<div class="row">
						<div class="input-field col s12">
								<div class="input-field col" style="width: 20.7%">Short Description</div>
								<div class="input-field col m9 breakword">
								: <span class="<s:text name="view.fontvalue.color" />"><s:property value="eventobj.eveshdesc" />
									</span>
								</div>
						</div>

					</div>
					<div class="row">
				<div class="input-field col s6" style="display:none">
										<div class="input-field col m5">Event Rsvp</div>
										<div class="input-field col m7">: <span class="<s:text name="view.fontvalue.color" />">
										<s:property value="eventrsvpview" /></span>
							</div>
						</div>
				</div>
					<div class="row">
						<div class="input-field col s12" style="">
								<div class="input-field col " style="width: 20.7%" >Description</div>
								<div class="input-field col m9 breakword">
								: <span class="<s:text name="view.fontvalue.color" />"><s:property value="eventobj.evedesc"/> </span>
									</div>
						</div>

					</div>
					<div class="row">

						<div class="input-field col s12" style="display: block;" id="shareid">
						<div class="input-field col " style="width: 20.7%" ><s:text name="Text.Utility.Management.modifyeventResident" /></div>
								<div class="input-field col m5">
								<s:textfield name="eventobj.inviteName" id="invitefriend" cssClass="form-control typeahead tt-query" />
								<div id="sltrsdit" class="red-text text-darken-1" style="display:none;"> </div>
								<s:hidden name="eventobj.inviteid" id="inviteid"></s:hidden>
								</div>
								<div class="input-field col s2">
								<button type="button" class="<s:text name="button.color.submit"/>"  id="submitbtn" name="submitbtn"  style="float:right;" onclick="userdetailFun()"> Send <i class="<s:text name="button.icon.submitcard"/>"></i> </button>
						<%-- <button type="submit" id="userEditButtonId" class="<s:text name="button.color.submit"/>" >Send<i class="<s:text name="button.icon.submitcard"/>"></i></button> --%>
						</div>
						</div>

					</div>
					</form>


				</div>
		</div>
		</section>
	</div>
	<s:form method="post" id="userCancelForm"></s:form>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<jsp:include page="../common/clorbox.jsp"></jsp:include>
	<jsp:include page="../common/allbasicjs.jsp"></jsp:include>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/drop_uploader.js"></script>
	<script type="text/javascript" src="<s:text name='Resource.path'/>/js/jquery.tokeninput.js?ytty"></script>
	<script>
$(document).ready(function(){
    $('input[type=file]').drop_uploader({
        uploader_text: 'Again Drop files to upload, or',
        browse_text: 'Browse',
        browse_css_class: 'button button-primary',
        browse_css_selector: 'file_browse',
        uploader_icon: '<i class="pe-7s-cloud-upload"></i>',
        file_icon: '<i class="pe-7s-file"></i>',
        time_show_errors: 5,
        layout: 'thumbnails'
    });
   
});
function toLoadAutoComplate(){
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'getuserfbdetail',
			data : '',
			success : function(data) {
				data=data.replace(/&quot;/ig,'"');
				data=data.replace(/%27/ig,"'");
				var loc_state_val = JSON.parse(data);
				$("#invitefriend").tokenInput(loc_state_val, {
            	propertyToSearch: "searchkey",
            	 resultsFormatter: function(item){return "<li style='height:51px'>" + "<img class='grptypimage' src='"+item.imageNameMobile+"' title='" + item.searchkey + "' style='margin:-20px 0 0 0;'  height='25px' width='25px' />" + "<div style='display: inline-block; padding-left: 5px; margin:0 0 0 0;height:30px;'><div class='full_name'>" + item.fName +" "+item.lName+" "+item.flatNo+ "<br><div>" + item.mobilNum + "</div></div></div></li>" },
		            tokenFormatter: function(item) { return "<li><p id='"+item.searchvalue+"'>" + item.searchkey + "</p></li>" },
		            theme: "facebook"});
				$(".token-input-dropdown-facebook").css("z-index","260000");
	        	//$("ul.token-input-list-facebook").css("max-width","465px");
	        	$("ul.token-input-list-facebook").css("width","100%");
	        	//$("div.token-input-dropdown-facebook").css("max-width","465px");
	        	$("div.token-input-dropdown-facebook").css("width",$('ul.token-input-list-facebook').width());
	       		$("ul.token-input-list-facebook").css("min-height","0px");
				$("ul.token-input-list-facebook").css("max-height","162px");
				$("ul.token-input-list-facebook").css("height","auto");
				$("ul.token-input-list-facebook").css("overflow-y","auto");
				$("ul.token-input-list-facebook").css("border-color","#C4C4C4");
				$("#token-input-invitefriend").css("border-bottom","1px solid #9e9e9e");
				$("ul.token-input-list-facebook li input").css("-webkit-appearance","none");
				
				$("ul.token-input-list-facebook").addClass("form-control");				 
			}
		});
}
$(document).ready(function() {
	 toLoadAutoComplate();
});

function togetId(){var idname="";
     $(".token-input-token-facebook p").each(function(x){// new autocomplete
         idname+=$(this).attr("id")+",";
     });
     return idname;
}
function userdetailFun(){
	$("#sltrsdit").hide();
	var idnam = togetId();
	if(idnam!=''){
		$("#sltrsdit").hide();
		 toShowLoadingImgoverlay();
		$.ajax({
			type : 'post',
			datatype : 'json',
			url : 'inviteeventaction',
			data : 'eventShareids='+idnam,
			success : function(data) {
				var str=data;
				toremoveFBAuto();
             $("#invitefriend").val("");
			if((str.trim())=='00' || (str.trim())=='0'){
				$('#alertmsgsuccess').show();

			} else if((str.trim())=='01' || (str.trim())=='1'){
				$('#alertmsgerror').show();
			}else{

			}
			tohideLoadingImgoverlay()
			}
		});
	}else{
		swal("Warning!", "Select minimum one resident.", "info"); 
		//$("#sltrsdit").html("Select minimum one resident.");
		//$("#sltrsdit").show();
	}
}
function toremoveFBAuto(){
    $(".token-input-token-facebook").each(function(x){// new autocomplete
        $(this).remove();
    });
}
function hideFunction(){$('#alertmsgsuccess').hide();$('#alertmsgerror').hide();}
function eventMgmtFun(){
	 toShowLoadingImgoverlay();
	$("#registerformid").attr("action","MgmtFun");
	$("#registerformid").submit();
}
$(document).ready(function(){
	$("#gobckbtnid").click(function(){
		$('#gobckbtnid').addClass('<s:text name="goback.button.animated"/>');
		toShowLoadingImgoverlay();
		$("#userCancelForm").attr("action", "eventMgmt");
		$("#userCancelForm").submit();
	});
});
</script>
</body>

</html>
