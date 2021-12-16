<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%-- --%>
<!-- START HEADER -->
 <!-- Favicons <link rel="shortcut icon" href="<s:text name='Resource.path'/>/images/social/48.ico"/>-->
 <!-- For Windows Phone -->   
		<s:if test="((#session.sSoctyIdStr!=null && #session.sSoctyIdStr !='' && #session.sSoctyIdStr!=null && #session.sSoctyIdStr!='-1') && (#session.sSoctyIcoimg!=null && #session.sSoctyIcoimg!='' && #session.sSoctyIcoimg!='null'))">
			<link rel="icon" href="/templogo/society/web/<s:property value="#session.sSoctyId"/>/<s:property value="#session.sSoctyIcoimg"/>">
			<link rel="apple-touch-icon-precomposed" href="/templogo/society/web/<s:property value="#session.sSoctyId"/>/<s:property value="#session.sSoctyIcoimg"/>">
			<meta name="msapplication-TileColor" content="<s:text name="meta.msapplication-TileColor"/>">
			<meta name="msapplication-TileImage" content="/templogo/society/web/<s:property value="#session.sSoctyId"/>/<s:property value="#session.sSoctyIcoimg"/>">
		</s:if>	
		<s:elseif test="((#session.townshipIdStr!=null && #session.townshipIdStr!='null' && #session.townshipIdStr!='-1'&& #session.townshipIdStr!=-1) && (#session.townshipiconame!=null && #session.townshipiconame!='' && #session.townshipiconame!='null'))">
			<link rel="icon" href="/templogo/township/web/<s:property value="#session.townshipId"/>/<s:property value="#session.townshipiconame"/>">
			<link rel="apple-touch-icon-precomposed" href="/templogo/township/web/<s:property value="#session.townshipId"/>/<s:property value="#session.townshipiconame"/>">
			<meta name="msapplication-TileColor" content="<s:text name="meta.msapplication-TileColor"/>">
			<meta name="msapplication-TileImage" content="/templogo/township/web/<s:property value="#session.townshipId"/>/<s:property value="#session.townshipiconame"/>">
		</s:elseif>
		<s:else>
			<!-- Favicons-->
			<link rel="icon" href="<s:text name='Resource.path'/>/images/social/48.ico" sizes="32x32">
			<!-- Favicons-->
			<link rel="apple-touch-icon-precomposed" href="<s:text name='Resource.path'/>/images/social/48.ico">
			<!-- For iPhone -->
			<meta name="msapplication-TileColor" content="<s:text name="meta.msapplication-TileColor"/>">
			<meta name="msapplication-TileImage" content="<s:text name='Resource.path'/>/images/social/48.ico">
		</s:else> 
<!-- For iPhone -->
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/plugins/jquery-1.11.2.min.js"></script>    
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/session.warning.js"></script>
<script type="text/javascript" src="<s:text name='Resource.path'/>/js/controlvalidate.js?422"></script>
<%-- <meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage" content="<s:text name='Resource.path'/>/images/social/48.ico"> --%>
<jsp:include page="../common/loading.jsp"></jsp:include>
<jsp:include page="../common/message.jsp"></jsp:include>
<%
	String projName = request.getContextPath();
	String getrd = "";
	String mainmunuact = "";
	String rdec = "";
	try {
		rdec = request.getParameter("rd");
	} catch (Exception ex) {
		rdec = "";
	}	
	if (rdec == null) {
		rdec = "";
	}	
	if (rdec.equalsIgnoreCase("000")) {		
		session.setAttribute("mainmunuact", "");
		session.setAttribute("Rd", "");
	} else if (!rdec.equalsIgnoreCase("")) {
		getrd = request.getParameter("rd");
		String[] mmen = getrd.split("_");
		mainmunuact = mmen[0] + "_";		
		session.setAttribute("mainmunuact", mainmunuact);
		session.setAttribute("Rd", getrd);
	} else {		
	}
%>
<script>

function myFunction1(check){
	 if(check=="add"){
		 $('.imgaddminus').css("display","inline");		 
		 $('.imgaddplus').css("display","none");
	 }else if(check=="sub"){
		 $('.imgaddminus').css("display","none");
		 $('.imgaddplus').css("display","inline");		
	 }
}
 $(document).ready(function() {
	initSessionMonitor();
		 $.ajax({
		type : 'post',
		datatype : 'json',
		url : 'getnotificationaction',
		data : '',
		success : function(data) {
			var ar = data.split("!_!");			
			$("#notifications-dropdown").append(ar[1]);
			$("#countnotification").html(ar[2]);

		}
	});  
	
		 $("#recentactdiv").click(function(){
				window.location.href="auditTrialReportTbl";
			});
		 $("#townshiplistid").click(function(){
				window.location.href="townshipmgmt";
			});

		 	$("#socytealistid").click(function(){
				window.location.href="societymgmt";
			});

		 	$("#commiteelistid").click(function(){
				window.location.href="committeemgmt";
			});

		 	$("#residntlistid").click(function(){
				window.location.href="residentmgmt";
			});
		 	
});
function toviewnotification(url, notifiid){
	$("#notifications-dropdown").html("");
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'notificationreadaction',
			data:'',
			data : 'notificationIdval=' + notifiid,
			success : function(data) {
				window.location.href= ""+url+"";				
			}
		});
	
	
}
 
function readunread(idval)
{
	$("#notifications-dropdown").html("");
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'notificationreadaction',
			data:'',
			data : 'notificationIdval=' + idval,
			success : function(data) {

				$("#notifications-dropdown").append(data);
				//$("#notifications-dropdown").addClass("active");
			}
		});  
}
function recentact() {	
	$("#recentactdiv").html("");
	 $.ajax({
			type : 'post',
			datatype : 'json',
			url : 'recentactivityaction',
			data:'',
			success : function(data) {

				$("#recentactdiv").append(data);
				
			},
			error : function(dt){alert("error");}
		});  
	}
// for clipboard copy  <div id="cpy" onclick="CopyClipboard(this.id)"> Click to Copy</div>
function CopyClipboard (thisid) {
	  // creating new textarea element and giveing it id 't'
	  let t = document.createElement('textarea');
	  t.id = 't';
	  // Optional step to make less noise in the page, if any!
	  t.style.height = 0;
	  // You have to append it to your page somewhere, I chose <body>
	  document.body.appendChild(t);
	  // Copy whatever is in your div to our new textarea
	  t.value = document.getElementById(thisid).innerText;
	  // Now copy whatever inside the textarea to clipboard
	  let selector = document.querySelector('#t');
	  selector.select();
	  document.execCommand('copy');
	  var coppvall = t.value;
	  // Remove the textarea
	  document.body.removeChild(t);
	  if(coppvall!=""){
		  swal({ title:"Clipboard!", text: "Copied Content : "+t.value, type: "success"});
	  } else {
		  swal({ title:"Clipboard!", text: "Not Copied." , type: "error"});
	 }			  
}
function openAutocmp(lvrPid){
	$("#"+lvrPid).click();
	$("#"+lvrPid).focus();
}
/* function sessionexpires(){
	 window.location.href = "<s:text name='Proj.path'/>/sessionExpires";
	
	}   */
</script>
<!--animate css-->
  <link href="<s:text name='Resource.path'/>/js/plugins/animate-css/animate.css" type="text/css" rel="stylesheet" media="screen,projection">
<!-- For Windows Phone -->
  <header id="header" class="page-topbar">
        <!-- start header nav-->
        <div class="navbar-fixed">
        <s:if test="#session.sSoctyIdStr!=null && #session.sSoctyIdStr !='' && #session.sSoctyIdStr!=null && #session.sSoctyIdStr!='-1' && #session.sSoctyIdStr!=-1 && #session.sSoctyColorcode!=null && #session.sSoctyColorcode!='' && #session.sSoctyColorcode!='null'">
			   <nav class="navbar-color" style="background-color:<s:property value="#session.sSoctyColorcode"/>">
			   <input type="hidden" name="ttrrrrr" id ="RPKRAN_if" value="<s:property value="#session.townshipcolourcode"/>"/>
		</s:if>	
		
		<s:elseif test="#session.townshipIdStr!=null && #session.townshipIdStr!='' && #session.townshipIdStr!='null' && #session.townshipId!='-1' && #session.townshipcolourcode!=null && #session.townshipcolourcode!='' && #session.townshipcolourcode!='null'">
			<nav class="navbar-color" style="background-color:<s:property value="#session.townshipcolourcode"/>">
		    <input type="hidden" name="ttrrrrr" id ="RPKRAN_elseif" value="<s:property value="#session.townshipcolourcode"/>"/>
									
		</s:elseif>	
		<s:else>
		 		<nav class="navbar-color <s:text name="full.theme.bgolor"/>">
		 		<input type="hidden" name="ttrrrrr" id ="RPKRAN_else" value="<s:property value="#session.townshipcolourcode"/>_<s:property value="#session.townshipId"/>"/>
		</s:else>		
		 					                       
        <div class="nav-wrapper">                                
            <ul class="left">                                        
            <li><h1 class="logo-wrapper"><a href="loginform" class="brand-logo darken-1" style="padding: 14px 10px;margin-top:-25px;">                                         	
            <table class="logtablecustom " align="center">
							<tr>
							<td>
								<s:if test="#session.sSoctyIdStr!=null && #session.sSoctyIdStr!=-1 && #session.sSoctyIdStr!='' && #session.sSoctyIdStr!='null' && #session.sSoctyIdStr!='-1' && #session.sSoctyLogoimg!=null && #session.sSoctyLogoimg!='' && #session.sSoctyLogoimg!='null'">									
										<img style="width: 50px;margin-top: 5px;" src="/templogo/society/web/<s:property value="#session.sSoctyId"/>/<s:property value="#session.sSoctyLogoimg"/>"  class="rounded responsive-img valign profile-image" />																
								</s:if>
								<s:else>
								<s:if test="#session.townshipIdStr!=null && #session.townshipIdStr!='' && #session.townshipIdStr!='null' && #session.townshipIdStr!='-1' && #session.townshiplogoname!=null && #session.townshiplogoname!='' && #session.townshiplogoname!='null'">							
										<img style="width: 50px;margin-top: 5px;" src="/templogo/township/web/<s:property value="#session.townshipId"/>/<s:property value="#session.townshiplogoname"/>" class="rounded responsive-img valign profile-image" />																	
								</s:if>
								<s:else>							
										<img style="width: 50px;"  src="<s:text name='Resource.path'/>/images/social/social-india-final-logo.png" class="rounded responsive-img valign profile-image" />															
						   		</s:else>
						   		</s:else>
								</td>
								 <td>
						   		 <s:if test="#session.sSoctyimpname!=null && #session.sSoctyimpname !='' && #session.sSoctyimpname !='null' ">
								   
										<div class="imprintNameDiv"><s:property value="#session.sSoctyimpname"/></div>
								
								</s:if>
						   		
							    <s:elseif test="#session.townshipimprintname!=null && #session.townshipimprintname !='' && #session.townshipimprintname !='null' ">
								    <td>
										<div class="imprintNameDiv"><s:property value="#session.townshipimprintname"/></div>
									</td>
									
								</s:elseif>
								
								<s:else>
									<td>
										<div class="imprintNameDiv">SOCYTEA</div>
						    		</td>
						    	</s:else>
						    	</td>
								</tr>
						</table> 
                     	</a><span class="logo-text"></span>
                      </h1></li>
                    </ul>
                     <!-- <div class="header-search-wrapper hide-on-med-and-down">
                        <i class="mdi-action-search"></i>
                        <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore Socytea"/>
                    </div> -->
                    
                    <ul class="right hide-on-med-and-down">
                       <%--  <li><a href="javascript:void(0);" class="waves-effect waves-block waves-light translation-button"  data-activates="translation-dropdown"><img src="<s:text name='Resource.path'/>/images/flag-icons/United-States.png" alt="USA" /></a>
                        </li> --%>
                        <%--<li class="imgaddminus" ><a href="javascript:void(0);" class="waves-effect waves-block waves-light toggle-fullscreen tooltipped" data-tooltip="Maximize" data-delay="10" data-position="bottom"><i class="mdi-action-settings-overscan"></i></a>
                        </li>--%>
                      	<li class="imgaddminus" onclick="myFunction1('sub');"><a href="javascript:void(0);" class="waves-effect waves-block waves-light toggle-fullscreen tooltipped" data-tooltip="Maximize" data-delay="10" data-position="bottom"><i class="mdi-action-settings-overscan"></i></a>
                        </li> 
                        <li class="imgaddplus" style="display:none;" onclick="myFunction1('add');"><a href="javascript:void(0);" class="waves-effect waves-block waves-light toggle-fullscreen tooltipped" data-tooltip="Minimize" data-delay="10" data-position="bottom"><i class="mdi-action-settings-overscan"></i></a>
                        </li>                        
                        <li id="notificationid"><a href="javascript:void(0);" class="waves-effect waves-block waves-light notification-button" data-activates="notifications-dropdown">
                        <i class="mdi-social-notifications" ><small class="notification-badge" id="countnotification" style="display:ruby-base;"></small></i>
                        
                        </a>
                        </li >                        
                        <li id="recentlyactid" onclick="recentact();" data-activates="chat-out" class="waves-effect waves-block waves-light chat-collapse tooltipped" data-tooltip="Recent Activity" data-delay="10" data-position="bottom"><i class="mdi-communication-chat"></i>
                        </li>
                    </ul>
                    <!-- translation-button -->
                    <%-- <ul id="translation-dropdown" class="dropdown-content">
                      <li>
                        <a href="#!"><img src="<s:text name='Resource.path'/>/images/flag-icons/United-States.png" alt="English" />  <span class="language-select">English</span></a>
                      </li>
                      <li>
                        <a href="#!"><img src="<s:text name='Resource.path'/>/images/flag-icons/France.png" alt="French" />  <span class="language-select">French</span></a>
                      </li>
                      <li>
                        <a href="#!"><img src="<s:text name='Resource.path'/>/images/flag-icons/China.png" alt="Chinese" />  <span class="language-select">Chinese</span></a>
                      </li>
                      <li>
                        <a href="#!"><img src="<s:text name='Resource.path'/>/images/flag-icons/Germany.png" alt="German" />  <span class="language-select">German</span></a>
                      </li>
                      
                    </ul> --%>
                    <!-- notifications-dropdown -->
                   <ul id="notifications-dropdown" class="dropdown-content">
                    </ul>
                </div>
            </nav>
        </div>
        <!-- end header nav-->
         <aside id="right-sidebar-nav">
        <ul id="chat-out" class="side-nav rightside-navigation">
            <li class="li-hover">
            <a href="#" data-activates="chat-out" class="chat-close-collapse right"><i class="mdi-navigation-close"></i></a>
             <div id="right-search" class="row">
               <!-- <form class="col s12">
                    <div class="input-field">
                        <i class="mdi-action-search prefix"></i>
                        <input id="icon_prefix" type="text" class="validate">
                        <label for="icon_prefix">Search</label>
                    </div>
                </form>-->
            </div> 
            </li>
            <li class="li-hover">
                <ul class="chat-collapsible" data-collapsible="expandable" >
                <li class="pointer" id="auditreplistid">
                    <div class="collapsible-header teal white-text active"><i class="mdi-social-whatshot"></i>Recent Activity</div>
                    <div class="collapsible-body recent-activity"  id="recentactdiv">
                        <!-- <div class="recent-activity-list chat-out-list row">
                            <div class="col s3 recent-activity-list-icon"><i class="mdi-social-poll small"></i>
                            </div>
                            <div class="col s9 recent-activity-list-text">
                                <a href="#">just now</a>
                                <p>Township created successfully.</p>
                            </div>
                        </div>
                        <div class="recent-activity-list chat-out-list row">
                            <div class="col s3 recent-activity-list-icon"><i class="mdi-social-person-add small"></i>
                            </div>
                            <div class="col s9 recent-activity-list-text">
                                <a href="#">Yesterday</a>
                                <p>Social india user register successfully.</p>
                            </div>
                        </div>
                        <div class="recent-activity-list chat-out-list row">
                            <div class="col s3 recent-activity-list-icon"><i class="mdi-action-settings-voice"></i>
                            </div>
                            <div class="col s9 recent-activity-list-text">
                                <a href="#">5 Days Ago</a>
                                <p>Resident created successfully.</p>
                            </div>
                        </div>
                        <div class="recent-activity-list chat-out-list row">
                            <div class="col s3 recent-activity-list-icon"><i class="mdi-action-store"></i>
                            </div>
                            <div class="col s9 recent-activity-list-text">
                                <a href="#">Last Week</a>
                                <p>Society created successfully.</p>
                            </div>
                        </div>
                        <div class="recent-activity-list chat-out-list row">
                            <div class="col s3 recent-activity-list-icon"><i class="mdi-action-settings-voice"></i>
                            </div>
                            <div class="col s9 recent-activity-list-text">
                                <a href="#">5 Days Ago</a>
                                <p>Merchant created successfully.</p>
                            </div>
                        </div> -->
                    </div>
                </li>
                <li>
                    <div class="collapsible-header light-blue white-text active"><i class="mdi-editor-attach-money"></i>Social India Report</div>
                    <div class="collapsible-body sales-repoart">
                        <div class="sales-repoart-list  chat-out-list row pointer" id="townshiplistid">
                            <div class="col s8"> Township</a></div>
                            <div class="col s4"><span id="sales-line-1"></span>
                            </div>
                        </div>
                        <div class="sales-repoart-list chat-out-list row pointer" id="socytealistid">
                            <div class="col s8">Society</div>
                            <div class="col s4"><span id="sales-bar-1"></span>
                            </div>
                        </div>
                        <div class="sales-repoart-list chat-out-list row pointer" id="commiteelistid">
                            <div class="col s8">Committee</div>
                            <div class="col s4"><span id="sales-line-2"></span>
                            </div>
                        </div>
                        <div class="sales-repoart-list chat-out-list row pointer" id="residntlistid">
                            <div class="col s8">Resident</div>
                            <div class="col s4"><span id="sales-bar-2"></span>
                            </div>
                        </div>
                    </div>
                </li>
               <!--  <li>
                   <div class="collapsible-header red white-text"><i class="mdi-action-stars"></i>Merchant Per login View[Temp]</div> 
                  <div class="collapsible-body favorite-associates">
                        <div class="favorite-associate-list chat-out-list row">
                            <div class="col s4">
                            </div>
                            <div class="col s8">
                            <a href="getProductOrderDetail?orderId=5AmQhfLMg0iyoz00kC6PLA%3D%3D" target="_blank">
                                <p>Restaurant</p></a>
                            </div>
                        </div>
                        <div class="favorite-associate-list chat-out-list row">
                            <div class="col s4">
                            </div>
                            <div class="col s8">
                            <a href="jewellery" target="_blank">
                                <p>Jewellery</p></a>
                            </div>
                        </div>
                        <div class="favorite-associate-list chat-out-list row">
                            <div class="col s4">
                            </div>
                            <div class="col s8">
                               <a href="pharmacy" target="_blank">
                                <p>Pharmacy</p></a>
                            </div>
                        </div>
                        <div class="favorite-associate-list chat-out-list row">
                            <div class="col s4">
                            </div>
                            <div class="col s8">
                              <a href="sports" target="_blank">
                                <p>Sports</p></a>
                            </div>
                        </div>
                        <div class="favorite-associate-list chat-out-list row">
                            <div class="col s4">
                            </div>
                            <div class="col s8">
                                <a href="furniture" target="_blank" >
                                <p>Furniture</p></a>
                            </div>
                        </div>
                    </div> 
                </li> -->
                </ul>
            </li>
        </ul>
      </aside>
      <!-- LEFT RIGHT SIDEBAR NAV-->
  </header>
  <!-- END HEADER -->