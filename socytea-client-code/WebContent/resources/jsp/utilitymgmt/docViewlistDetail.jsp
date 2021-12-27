 <%@ taglib prefix="s" uri="/struts-tags"%><s:set var="last" value="listValue" />
<%String last = request.getAttribute("last").toString();out.println(last);%>