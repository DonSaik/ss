<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Custom login
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser"
	method="POST">
	<c:if test="param.error !-null">
		<i>Sorry! YOu entered invalid username/password.</i>
	</c:if>
	Username: <input type="text" name="username" />
	Password <input type="password" name="password" />
		<input type="submit" value="Login"/>
	</form:form>
</body>
</html>