<%@ taglib prefix="s" uri="/struts-tags"%><s:select id="stateId"
	cssClass="form-control statevalidate" headerKey="" headerValue="Select"
	list="#session.stateList" listKey="stateId"
	listValue="stateName"
	name="custRegObj.stateId" value="%{custRegObj.stateId}" onchange="onchangeStatecodefn();">
</s:select>