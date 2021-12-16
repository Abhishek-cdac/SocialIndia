<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="societyList!=null && societyList.size()>1">
<%-- <label class=" control-label"><s:text name="Text.select.society"/><span class="mandatory">*</span></label> --%>
	<div class="form-group" id="inselectSocietyId" style="margin-left: 45px;width: 77%;">
	
<s:select id="societyId"  cssClass=""
	headerKey="" headerValue="Select Society "
	list="societyList" listKey="societyId" onchange="societycheckerror();"
	listValue="societyName"
	name="selectSocietyId" >
</s:select>
<%-- <small class="help-block" style="display: none;" data-bv-validator="notEmpty" data-bv-for="documenttype" data-bv-result="INVALID"></small> --%>
</div>
</s:if>