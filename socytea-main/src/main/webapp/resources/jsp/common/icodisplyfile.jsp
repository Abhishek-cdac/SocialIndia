<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!-- START HEADER -->
 <!-- Favicons <link rel="shortcut icon" href="<s:text name='Resource.path'/>/images/social/48.ico"/>-->
 <!-- For Windows Phone -->   
		<s:if test="((#session.sSoctyIdStr!=null && #session.sSoctyIdStr !='' && #session.sSoctyIdStr!=null && #session.sSoctyIdStr!='-1') && (#session.sSoctyIcoimg!=null && #session.sSoctyIcoimg!='' && #session.sSoctyIcoimg!='null'))">
			<link rel="icon" href="/templogo/society/web/<s:property value="#session.sSoctyId"/>/<s:property value="#session.sSoctyIcoimg"/>">
			<link rel="apple-touch-icon-precomposed" href="/templogo/society/web/<s:property value="#session.sSoctyId"/>/<s:property value="#session.sSoctyIcoimg"/>">
			<meta name="msapplication-TileColor" content="<s:text name="meta.msapplication-TileColor"/>">
			<meta name="msapplication-TileImage" content="/templogo/society/web/<s:property value="#session.sSoctyId"/>/<s:property value="#session.sSoctyIcoimg"/>">
		</s:if>	
		<s:elseif test="((#session.townshipIdStr!=null && #session.townshipIdStr!='null' && #session.townshipIdStr!='-1' && #session.townshipIdStr!='') && (#session.townshipiconame!=null && #session.townshipiconame!='' && #session.townshipiconame!='null'))">
			<link rel="icon" href="/templogo/township/web/<s:property value="#session.townshipId"/>/<s:property value="#session.townshipiconame"/>">
			<link rel="apple-touch-icon-precomposed" href="/templogo/township/web/<s:property value="#session.townshipId"/>/<s:property value="#session.townshipiconame"/>">
			<meta name="msapplication-TileColor" content="<s:text name="meta.msapplication-TileColor"/>">
			<meta name="msapplication-TileImage" content="/templogo/township/web/<s:property value="#session.townshipId"/>/<s:property value="#session.townshipiconame"/>">
		</s:elseif>
		<s:else>
			<!-- Favicons-->
			<link rel="icon" href="<s:text name='Resource.path'/>/images/social/48.ico" sizes="32x32">
			<!-- Favicons-->
			<link rel="apple-touch-icon-precomposed" href="<s:text name='Resource.path'/>/images/social/48.ico">
			<!-- For iPhone -->
			<meta name="msapplication-TileColor" content="<s:text name="meta.msapplication-TileColor"/>">
			<meta name="msapplication-TileImage" content="<s:text name='Resource.path'/>/images/social/48.ico">
		</s:else>
<!-- For iPhone -->