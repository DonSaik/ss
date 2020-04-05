<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<ul>
  <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
  <li><a href="${pageContext.request.contextPath}/users">Users</a></li>
  <security:authorize access="hasRole('ADMIN')">
 	<li><a href="${pageContext.request.contextPath}/admin-home">Admin page</a></li>
	</security:authorize>
	     <security:authorize access="isAnonymous()">
  <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
  <li><a href="${pageContext.request.contextPath}/sign-up">Sign up</a></li>
	     </security:authorize>
    <security:authorize access="isAuthenticated()">
    <form:form action="${pageContext.request.contextPath}/logout"
	method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
    </security:authorize>
</ul>