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
<s:form action="login" method="post" validate="false" theme="xhtml">
<s:textfield name="loginName" label="Username"></s:textfield><br/>
<s:password name="password" label="Password"></s:password><br/>
<s:checkbox label="Remember Me" name="rememberMe"></s:checkbox>
<s:submit value="%{'Login'}"></s:submit> 
</s:form>
<a href="./register">Register</a>
</body>
</html>