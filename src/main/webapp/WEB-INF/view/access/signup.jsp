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
<jsp:include page="/WEB-INF/view/menu.jsp"/>
<h2>Registration form</h2>
	<c:if test="alreadyUserError">
		Already exits
	</c:if>
	<form:form action="${pageContext.request.contextPath}/sign-up" method="POST"
	modelAttribute="user">
	
		<form:label path="username">Username:</form:label>
		<form:input path="username"/>
		<form:errors path="username"/>
		<br>
		
		<form:label path="password">Password:</form:label>
		<form:password path="password"/>
		<form:errors path="password"  />
		<br>
		
		<form:label path="confirmPassword">Confirm password:</form:label>
		<input type="password" name="confirmPassword" />
		<form:errors path="confirmPassword" />
		<br>
		<c:if test="${not empty recaptchaError}">reCaptcha error</c:if>
		<div class="g-recaptcha" data-callback="recaptcha_callback" data-expired-callback="recaptchaExpired"
		    data-sitekey="${recapthcaSite}">
		  </div>

		  
		<input id="submit" disabled type="submit" value="Sign up"/>
	</form:form>
<script src='https://www.google.com/recaptcha/api.js'></script>

<script type="text/javascript">
    function recaptcha_callback(){
    	document.getElementById("submit").disabled = false;
    }
    function recaptchaExpired(){
    	document.getElementById("submit").disabled = true;
    }
</script>
</body>
</html>