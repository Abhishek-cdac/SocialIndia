<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<iframe id="iframe1" src="twtuserdetail" target="twtuserdetail" style="width:100%;"></iframe>
</body>
<script type="text/javascript" src="<s:text name='Resource.path'/>/assets/js/required/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
window.close();
window.opener.parent.location.href = "twtuserdetail";
//$("#iframe1").click();


</script>
</html>