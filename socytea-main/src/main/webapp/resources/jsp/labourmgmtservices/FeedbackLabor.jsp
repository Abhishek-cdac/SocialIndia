<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="FeedbackList">
<div class="block-heading">	
<div style="height:170px;margin-right:10px;" id="heightt">
<div style="float: left; width: 155px;">
<s:if test="ivrFEEDBK_PRO_IMG!=null && ivrFEEDBK_PRO_IMG!=''">
<img width="96" height="80" class="circle" src="/templogo/profile/mobile/<s:property value="ivrBnFEEDBK_FOR_USR_TYP"/>/<s:property value="ivrFEEDBK_PRO_IMG"/>" onclick="toViewlargesizeimgwithsrc(this.id,'/templogo/profile/web/<s:property value="ivrBnFEEDBK_FOR_USR_TYP"/>/<s:property value="ivrFEEDBK_PRO_IMG"/>');" id="memberprofimg_<s:property value="ivrBnFEEDBK_ID"/>"  style="margin-top:7px;margin-left: 15px;cursor:pointer;"> 
</s:if>
<s:else>
<img width="96" height="80" class="circle" src="resources/images/social/defaultimg.png" onclick="toViewlargesizeimgwithsrc(this.id,'resources/images/social/defaultimg.png');" id="memberprofimg_<s:property value="ivrBnFEEDBK_ID"/>"   style="margin-top:7px;margin-left: 15px;cursor:pointer;">
</s:else>
<div>
<label style="margin-left:15px;"><b>Name:</b></label><label>&nbsp;<s:property value="ivrFEEDBK_FNAME"/></label><br>	
<label style="margin-left:15px;"><b>Date:</b></label><label>&nbsp;<s:property value="ivrFEEDBK_DATE"/></label><br>
<label style="margin-left:15px;"><b>Rating:</b></label><label>&nbsp;<s:property value="ivrBnRATING"/></label>
</div>
	
</div>
  <div style="width: auto; margin: 0px 0px 0px 16px; min-width: 80%; max-width: 80%;" class="left m"><p><s:property value="ivrBnFEEDBK_TXT"/></p> </div> 
 </div>	
  
</div>	
<div class="clear height5px"></div>
</s:iterator>