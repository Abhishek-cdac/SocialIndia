<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<style>
ul.side-nav.leftside-navigation li.activeMenu {
	background-color: rgba(0, 0, 0, 0.04);
}

ul.side-nav.leftside-navigation li.activeMenu>a {
	color: #00bcd4;
}
</style>
<!-- START LEFT SIDEBAR NAV-->
<aside id="left-sidebar-nav">
	<ul id="slide-out" class="side-nav fixed leftside-navigation mainnav">
		<li class="user-details cyan darken-2">
			<div class="row">
				<div class="col col s4 m4 l4 pointer">
					<s:if
						test="#session.userProfileImage!=null && #session.userProfileImage!=''">
						<img id="dsbpfimgid"
							class="circle responsive-img valign profile-image"
							src="/templogo/profile/mobile/<s:property value="#session.USERID"/>/<s:property value="#session.userProfileImage"/>"
							onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/profile/web/<s:property value="#session.USERID"/>/<s:property value="#session.userProfileImage"/>')" />
					</s:if>
					<s:else>
						<img
							src="<s:text name='Resource.path'/>/images/social/profile-default-male.png"
							alt="" class="circle responsive-img valign profile-image"
							onclick="toViewlargesizeimgwithsrc(this.id,'<s:text name="Resource.path"/>/images/social/profile-default-male.png')">
					</s:else>
					<%--  <img src="<s:text name='Resource.path'/>/images/social/profile-default-male.png" alt="" class="circle responsive-img valign profile-image"> --%>
				</div>
				<div class="col col s8 m8 l8">
					<ul id="profile-dropdown" class="dropdown-content">
						<li>
							<!-- <a href="profile"><i class="mdi-action-face-unlock"></i> Profile</a> -->
							<s:if
								test="#session.GROUPCODE==1 || #session.GROUPCODE=='1' ||#session.GROUPCODE== 2 || #session.GROUPCODE=='2'  && #session.GROUPCODE!='null'&& #session.GROUPCODE!=null">
								<a href="adminprofile"><i
									class="mdi-action-face-unlock <s:text name='profile.icon.color'/>"></i>Profile</a>
							</s:if> <s:else>
								<a href="userProfile"><i
									class="mdi-action-face-unlock <s:text name='profile.icon.color'/>"></i>Profile
								</a>
							</s:else>
						</li>
						<!--  <li><a href="#"><i class="mdi-action-settings"></i> Settings</a>
                        </li>
                        <li><a href="#"><i class="mdi-communication-live-help"></i> Help</a>
                        </li> -->
						<li class="divider"></li>
						<li><a href="socchgpswd"><i
								class="mdi-action-lock-outline <s:text name='profilepaswd.icon.color'/>"></i>
								Password</a></li>
						<li class="divider"></li>

						<li><a href="doLogOut"><i
								class="mdi-hardware-keyboard-tab <s:text name='profilelgout.icon.color'/>"></i>
								Logout</a></li>
					</ul>

					<s:if
						test="#session.sDisplyname!=null && #session.sDisplyname!='' && #session.sDisplyname!='null'">
						<a
							class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
							href="#" data-activates="profile-dropdown"> <s:property
								value="#session.sDisplyname" /><i
							class="mdi-navigation-arrow-drop-down right"
							style="margin-left: 1px;"></i></a>
					</s:if>
					<s:elseif
						test="#session.FirstName!=null && #session.FirstName!='' && #session.FirstName!='null'">
						<a
							class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
							href="#" data-activates="profile-dropdown"> <s:property
								value="#session.FirstName" /><i
							class="mdi-navigation-arrow-drop-down right"
							style="margin-left: 1px;"></i></a>
					</s:elseif>
					<s:elseif
						test="#session.sSoctyimpname!=null && #session.sSoctyimpname!='' && #session.sSoctyimpname!='null'">
						<a
							class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
							href="#" data-activates="profile-dropdown"> <s:property
								value="#session.sSoctyimpname" /><i
							class="mdi-navigation-arrow-drop-down right"
							style="margin-left: 1px;"></i></a>
					</s:elseif>
					<s:elseif
						test="#session.MobileNo!=null && #session.MobileNo!='' && #session.MobileNo!='null'">
						<a
							class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
							href="#" data-activates="profile-dropdown"> <s:property
								value="#session.MobileNo" /><i
							class="mdi-navigation-arrow-drop-down right"
							style="margin-left: 1px;"></i></a>
					</s:elseif>
					<s:else>
						<a
							class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
							href="#" data-activates="profile-dropdown"> <s:property
								value="#session.MobileNo" /><i
							class="mdi-navigation-arrow-drop-down right"
							style="margin-left: 1px;"></i></a>
					</s:else>

				</div>
				<span style="color: white"><b><s:property
							value="#session.usersocietyname" /></b></span>
			</div>
		</li>

		<li id="menu001" class="bold"><a href="Homeform"
			class="waves-effect waves-cyan"><i
				class="mdi-action-dashboard teal-text text-accent-4"></i> Dashboard</a>
		</li>
		<li id="menu002" class="bold"><a href="postnotificationpage"
			class=" waves-effect waves-cyan" data-position="bottom"
			data-delay="10" data-tooltip="Notifications"><i
				class="mdi-social-notifications pink-text text-accent-3"></i>Notifications</a>
		</li>
		<li class="no-padding">
			<ul class="collapsible collapsible-accordion">
				<%
					int isfirst = 1;
					int isfirst1 = 0;
				%>
				<s:iterator value="#session.RIGHTSLST" status="menurightslist">
					<s:if test="menuType.equalsIgnoreCase('M')">
						<%
							if (isfirst1 == 1) {
						%>
					
						</ul> <%}
								 		isfirst1 = 0;
								 		if (isfirst == 1) {
								 			isfirst++;
								 		} else {
							  %>
					
						</ul>
						<%
									}
						%>
						<s:if test="rootDesc.equalsIgnoreCase(#session.mainmunuact)">
							<li class="bold activeMenu" id='<s:property value="rootDesc"/>'>
								<s:set var="isHilight" value="true"></s:set>
						</s:if>
						<s:else>
							<s:set var="isHilight" value="false"></s:set>
							<li class="bold" id='<s:property value="rootDesc"/>'>
								<%-- <li class="dump" id='<s:property value="menuId.rootDesc"/>'> --%>
						</s:else>
						<a href="#" class="collapsible-header waves-effect waves-cyan"
							data-position="bottom" data-delay="10"
							data-tooltip='<s:property value="menuDesc"/>'> <i
							class="<s:property value="menuClass" />"></i> <s:property
								value="menuDesc" />
						</a>
						<div class="collapsible-body">
						<ul>
				</s:if>
				<!-- else -->
				<s:else>
					<s:if test="rootDesc.equalsIgnoreCase(#session.Rd)">
						<s:set name="currentSubmenu" value="menuDesc" />
					</s:if>
					<s:if test="(typeOfMenu.equalsIgnoreCase('S') || typeOfMenu.equalsIgnoreCase('SS')) && (menuPath==null || rootDesc.length()==20)">
						<s:if test="menuType.equalsIgnoreCase('S')">
							<%
								if (isfirst1 == 1) {
							%>
						
								</ul>
							</div>
							<%
											}
															isfirst1 = 0;
										%>
							<li class="menu-item-top" selected><a href="#" class="top"> <i
									class="<s:property value="menuClass" />"></i> <s:property
										value="menuDesc" />
							</a>
								<ul>
						</s:if>
						<s:else>
							<%
								isfirst1 = 1;
							%>
							<s:if test="rootDesc.equalsIgnoreCase(#session.Rd)">
								<li id='<s:property value="rootDesc"/>'>
							</s:if>
							<s:else>
								<li id='<s:property value="rootDesc"/>'>
							</s:else>
							<a href="${pageContext.request.contextPath}/<s:property value="menuPath"/>?rd=<s:property value="rootDesc"/>"><s:property value="menuDesc" /></a></li>
						</s:else>
				</s:if>
				<s:else>
						<%
								if (isfirst1 == 1) {
						%>
					</ul>
						<%
								}
										isfirst1 = 0;
						%>
					<s:if test="rootDesc.equalsIgnoreCase(#session.Rd)">
						<li class="active" id='<s:property value="rootDesc"/>'>
							<%-- <li id='<s:property value="menuId.rootDesc"/>' class="activeSubmenu"> --%>
					</s:if>
					<s:else>
						<li id='<s:property value="rootDesc"/>'>
					</s:else>
<%-- 					<s:if test="menuPath.equalsIgnoreCase('societymgmt')"> --%>
<!-- 							<ul class="collapsible collapsible-accordion">					 -->
<!-- 								<li class="bold active" id="000001_"> -->
<!-- 									<a href="#" class="collapsible-header waves-effect waves-cyan" data-position="bottom" data-delay="10" data-tooltip="Society Mgmt">  -->
<!-- 										Society Mgmt -->
<!-- 									</a> -->
<!-- 									<div class="collapsible-body"> -->
<!-- 									<ul> -->
<%-- 										<s:if test="#session.GROUPCODE == 1 || #session.GROUPCODE == 2 || #session.GROUPCODE == 5 "> --%>
<!-- 											<li id="000003_000002_"> -->
<!-- 												<a href="/socytea/societymgmt?rd=000003_000002_" style="color: blue">Demographic Data</a> -->
<!-- 											</li> -->
<%-- 										</s:if> --%>
<%-- 										<s:if test="#session.GROUPCODE == 1 || #session.GROUPCODE == 2"> --%>
<!-- 											<li id="000012_000001_"> -->
<!-- 												<a href="/socytea/biometricdatabasemenu?rd=000012_000001_" style="color: blue">Biometric Database</a> -->
<!-- 											</li> -->
<%-- 										</s:if> --%>
<%-- 										<s:if test="#session.GROUPCODE == 1 || #session.GROUPCODE == 2"> --%>
<!-- 											<li id="000012_000002_"> -->
<!-- 												<a href="/socytea/biometricdatamenu?rd=000012_000002_" style="color: blue">Biometric Data</a> -->
<!-- 											</li> -->
<%-- 										</s:if> --%>
<%-- 										<s:if test="#session.GROUPCODE == 1 || #session.GROUPCODE == 2"> --%>
<!-- 											<li id="#"> -->
<!-- 												<a href="#" style="color: blue">Fees and charges</a> -->
<!-- 											</li> -->
<%-- 										</s:if> --%>
<!-- 									</ul> -->
<!-- 									</div> -->
<!-- 								</li> -->
<!-- 						</ul>	 -->
<%-- 					</s:if> --%>
<%-- 					<s:else> --%>
						<a href="${pageContext.request.contextPath}/<s:property value="menuPath"/>?rd=<s:property value="rootDesc"/>"> <s:property value="menuDesc" /></a>
<%-- 					</s:else>	 --%>
					</li>
				</s:else>
			</s:else>
	</s:iterator>
	</ul>
	</ul>
	</li>
	<li>
		<div style="clear: both; height: 64px; width: 240px;"></div>
	</li>
	</ul>
	<a href="#" data-activates="slide-out"
		class="<s:text name="sidebar-collapse.mobile.bgcolor"/> tooltipped"
		data-position="bottom" data-delay="10" data-tooltip="Show Menu"><i
		class="mdi-navigation-menu"></i></a>
</aside>
<!-- END LEFT SIDEBAR NAV-->
<script>
      $(document).ready(function(){
    	  $(".activeMenu> a").addClass("active");
    	  $(".activeMenu> .collapsible-body").show();
    	  $(".activeMenu> .collapsible-body a").focus();
    	  $(".activeMenu> .collapsible-body a").blur();
      });
      
      var userid = '<%=session.getAttribute("USERID")%>';
      var myVar = setInterval(myTimer, 30000);

      var sessiond8tm = "";
      var currentd8tm = "";
      function myTimer() {
    	  
    	  $.ajax({
    			type : 'post',
    			datatype : 'json',
    			async : 'true',
    			url : '/socytea/getresetdatetime?userid='+userid,
    			success : function(data) {
    				
    				sessiond8tm = '<%=session.getAttribute("resetDatetime")%>';

				currentd8tm = data.data.resetDatetime;

				if (sessiond8tm != currentd8tm) {
					window.location = "/socytea/sessionExpires?flag=true";
				}
			}
		});

	}
</script>
<jsp:include page="../common/clorbox.jsp"></jsp:include>


