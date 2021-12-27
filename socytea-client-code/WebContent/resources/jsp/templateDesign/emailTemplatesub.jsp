<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select id="stateId" cssClass="form-control statevalidate" headerKey="" headerValue="Select mail template name" 
	list="emailtemp" listKey="tempid" listValue="subject" name="templateid" onchange="loadSubject()">
</s:select>