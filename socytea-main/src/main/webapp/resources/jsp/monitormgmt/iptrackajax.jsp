<%@ page language="java" import="java.util.Locale" import="java.util.Date" 
import="java.util.TimeZone" import="java.text.SimpleDateFormat" import="com.socialindia.accessInfo.AccessInfo"
errorPage=""%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% 
//data : "clientdatetime=" + dft + " &ip="+ ipadr+ "&clintHost="+ clintHost+
				       //"&clientip="+clientip+"&languagee="+languagee+"&protocol="+protocol+"&methodname="+methodname,
String entryTime=request.getParameter("clientdatetime");
String ip=request.getParameter("ip");
String clintHost=request.getParameter("clintHost");
String clientIPstr=request.getParameter("clientip");
String language=request.getParameter("languagee");
String protocol=request.getParameter("protocol");
String method=request.getParameter("methodname");
String countryName=request.getParameter("countryName");
String gmtTime=AccessInfo.togetGMTDateTime();
String serverTime=AccessInfo.togetServerDateTime();
//System.out.println("entryTime : "+entryTime);
//System.out.println("ip : "+ip);
//System.out.println("clientIP : "+clientIPstr);
//System.out.println("clintHost : "+clintHost);
//System.out.println("language : "+language);
//System.out.println("protocol : "+protocol);
//System.out.println("method : "+method);
//System.out.println("countryName : "+countryName);
if(countryName==null){
	countryName="";
}
int clientIP =0;
if(clientIPstr!=null && !clientIPstr.equalsIgnoreCase("null") && !clientIPstr.equalsIgnoreCase("")){
	clientIP=Integer.parseInt(clientIPstr);
}
String accessinfo = new AccessInfo().accesscontrolip(ip,clintHost,clientIP,language,protocol,method,countryName,serverTime,gmtTime,entryTime);	
session.setAttribute("IPACTION_STATUSFLG", "0");
%>