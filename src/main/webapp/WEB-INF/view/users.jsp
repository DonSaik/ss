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
<jsp:include page="menu.jsp"/>
<h2>Users</h2>
    <form:form action="${pageContext.request.contextPath}/users"
	method="GET" >
		<input type="text" name ="serachUsers" value="${serachUsers}"/>
		<input type="submit" value="Search"/>
	</form:form>
	<c:choose>
		<c:when test="${not empty users}">
		<c:forEach var="user" items="${users}">
			${user.username}
			${user.password}
			${user.enabled}
			<c:forEach var="authority" items="${user.authorities}">
				${authority.name}
			</c:forEach>
			<br>
		</c:forEach>
		</c:when>
		<c:otherwise>No users</c:otherwise>
	</c:choose>
</body>
</html>