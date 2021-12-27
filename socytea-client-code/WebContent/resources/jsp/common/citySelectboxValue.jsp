<%@ taglib prefix="s" uri="/struts-tags"%><s:select id="cityId"
	cssClass="form-control cityvalidate" headerKey="" headerValue="Select"
	list="#session.cityList" listKey="cityId"
	listValue="cityName"
	name="custRegObj.cityId" value="%{cityId}">
</s:select>