<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Wallet-Login</title>
</head>
<body>
<h2>Login</h2> 
<s:actionmessage/>
<s:actionerror/>
<s:form action="register" method="post" validate="false" theme="xhtml">
<s:textfield name="loginName" label="Username"></s:textfield><br/>
<s:password name="password" label="Password"></s:password><br/>
<s:password name="confirmPassword" label="Confirm Password"></s:password><br/>
<s:submit value="%{'Register'}"></s:submit> 
</s:form>
</body>
</html>