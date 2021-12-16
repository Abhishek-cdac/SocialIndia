<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
!_!<s:if test="NotificationList == null ||NotificationList.size()==0">
<%-- <div style="margin-left: 14px;" class="main-text h2">No Notification For <s:property value="laborName_hidd_txt"/></div> --%>
<%-- <li> <h5>NOTIFICATIONS <span class="new badge"><s:property value="NotificationList.size()"/></span></h5> </li> --%>
<li class="divider"></li>
<li>
 <div style="float: left;color: #26a69a"">
      <span class="email-title"> <s:property value="tblrefFlag"/></span>
      <a href="postnotificationpage" style="color: #26a69a"> No Notification </a>
</div>
</li>
</s:if> 
<s:else>
<li> <h5>NOTIFICATIONS <span class="new badge"><s:property value="NotificationList.size()"/></span></h5> </li>
<s:iterator value="NotificationList">		
<li class="divider"></li>
<li>
   <div style="float: left;color: #26a69a">
      <span class="email-title"> <s:property value="tblrefFlag"/></span>
      <a href="<s:property value="postnotificationpage"/>" style="color: #26a69a"> <s:property value="descr"/> </a>
    </div>
      <a style="float: right;">
        <time class="media-meta" datetime="2015-06-12T20:50:48+08:00" style="cursor: default;"><s:property value="NotifyentryDatetime"/></time> 
        <br/>
         <i class="mdi-action-visibility tinysmall cyan-text text-accent-4" onclick="toviewnotification('<s:property value="viewactionurl"/>','<s:property value="notificationId"/>')"></i>
         <i class="mdi-navigation-cancel tinysmall red-text text-accent-2" onclick="readunread(<s:property value="notificationId"/>)"></i>
         <s:hidden name="notificationId"  cssClass="notificationIdcls"></s:hidden>
         </a>
</li>
</s:iterator>
</s:else>!_!<s:property value="lvrNotificnt"/>!_!







