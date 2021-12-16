<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.apache.struts2.ServletActionContext" %>
   <%
        ServletContext context = ServletActionContext.getServletContext();
      request.setAttribute("MENUTYPE", context.getAttribute("MENUTYP"));
      //request.setAttribute("MMAIMMENULIST", context.getAttribute("MAINMENULIST"));
      %>
