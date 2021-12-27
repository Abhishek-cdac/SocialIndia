<%@ taglib prefix="s" uri="/struts-tags"%><s:select id="isdCodeid"
	cssClass="form-control isdvalidate" headerKey="" headerValue="Select"
	list="#session.isdList" listKey="isdCodeid"
	listValue="isdCodevalue"
	name="custRegObj.isdCodeid" value="%{isdCodeid}">
</s:select>