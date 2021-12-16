<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<script type="text/javascript" src="/uambootstrap/resources/js/jquery-1.8.2.min.js"></script>
<s:hidden id="decrptdata" name="decrptdata" value="%{paymentDecrptdata}"></s:hidden>
<script>
	$(document).ready(function() {
		var decrptval = $("#decrptdata").val();
		window.location.href = "http://192.168.1.41:8089/paygatewayapis/PayinitActionFrm?payvalue="+decrptval;
	});
</script>