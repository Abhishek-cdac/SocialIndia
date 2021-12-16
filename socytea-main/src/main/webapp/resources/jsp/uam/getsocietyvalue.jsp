 <%@ taglib prefix="s" uri="/struts-tags"%>
 <s:select id="stateid"
	cssClass="form-control societyIdvalidate" headerKey="" headerValue="Select Society"
	list="#session.societylist"
	listKey="societyId"
	listValue="societyName"
	name="stateId" value="%{stateId}">
</s:select> 
