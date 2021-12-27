<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<style type="text/css">
.fielderror-spy {
	margin-top: 10px;
	color: #c24237;
	list-style-type: none;
	padding: 0;
}
/* #alertdiv1
{
width : 1200px;
} */
</style>
<head>
<s:if test="alertList!=null">
	<s:iterator value="alertList" status="alertstatus">
		 <div style="width: 98%;margin-left: 1%" id="card-alert" class="card <s:property value="cls" />">
			<div class="card-content white-text">
					<s:if test="cls.equalsIgnoreCase('success')">
                        <p><i class="mdi-navigation-check"></i> <s:property value="msg" /></p>
                        </s:if><s:elseif test="cls.equalsIgnoreCase('info')">
                         <p><i class="mdi-action-info-outline"></i> <s:property value="msg" /></p>
                         </s:elseif>
                        <s:else>
                          <p><i class="mdi-alert-error"></i> 
                          		<s:if test="%{'Number Of Logins Exceeded' == msg}">
                          			Number Of Logins Exceeded  <input type="button" value="Reset" onclick="resetLogin()" style="background-color:green"/>
                          		</s:if>
                          		<s:else>
                          			<s:property value="msg" />
                          		</s:else>
                          </p>
                        </s:else>
                      </div>
			 			<button type="button" class="close white-text" data-dismiss="alert" aria-label="Close" style="margin-top:8px;"><span aria-hidden="true">×</span> </button>			
		</div> 
		<div style="clear: both; height: 5px;"></div>
	</s:iterator>
</s:if>
<script type="text/javascript">
	$(document).ready(function() {
		$(".hideit").click(function() {
			$(this).fadeOut(1000);
		});
		
		
	});
	function hideerrordiv() {
		//  $(this).fadeOut(1000);
		$(".fielderror-sty-pre").fadeOut(1000);
	}
	
	function resetLogin() {
		document.getElementById("resetLogin").value = "true";
		document.getElementById("loginform").submit();
	}
	
</script>
<% session.removeAttribute("alertList"); %>