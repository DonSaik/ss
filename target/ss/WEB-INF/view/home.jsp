<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
This is home page
<security:authorize access="hasRole('ADMIN')">
 <a href= "${pageContext.request.contextPath}/admin">Admin page</a>

</security:authorize>
User: <security:authentication property="principal.username"/>
User: <security:authentication property="principal.authorities"/>
<form:form action="${pageContext.request.contextPath}/logout"
	method="POST">
	<input type="submit" value="Logout"/>
</form:form>
</body>
</html>