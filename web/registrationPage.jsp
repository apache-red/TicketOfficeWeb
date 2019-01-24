<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Страница регистрации</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.titleRegistration" var="titleRegistration" />
<fmt:message bundle="${loc}" key="local.errorMessageRegistration1" var="errorMessageRegistration1" />
<fmt:message bundle="${loc}" key="local.errorMessageRegistration2" var="errorMessageRegistration2" />
<fmt:message bundle="${loc}" key="local.errorMessageRegistration3" var="errorMessageRegistration3" />
<fmt:message bundle="${loc}" key="local.errorMessageRegistration4" var="errorMessageRegistration4" />
<fmt:message bundle="${loc}" key="local.signUp" var="signUp" />
	<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.passwordAgain" var="passwordAgain" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.phone" var="phone" />
<fmt:message bundle="${loc}" key="local.address" var="address" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.noteRegistration1" var="noteRegistration1" />
<fmt:message bundle="${loc}" key="local.noteRegistration2" var="noteRegistration2" />
<fmt:message bundle="${loc}" key="local.noteRegistration3" var="noteRegistration3" />
<fmt:message bundle="${loc}" key="local.signUp" var="signUp" />
</head>
<body>
	<jsp:include page="WEB-INF/jsp/app/header.jsp" />

	<h1>${titleRegistration}</h1>

	<c:if test="${requestScope.errorRegistration==1}">
		<font color="#CC0000"> ${errorMessageRegistration1} </font>
	</c:if>

	<c:if test="${requestScope.errorRegistration==2}">
		<font color="#CC0000"> ${errorMessageRegistration2} </font>
	</c:if>

	<c:if test="${requestScope.errorRegistration==3}">
		<font color="#CC0000"> ${errorMessageRegistration3} </font>
	</c:if>

	<c:if test="${requestScope.errorRegistration==4}">
		<font color="#CC0000"> ${errorMessageRegistration4} </font>
	</c:if>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<table class="table2">
			<tr>
				<td>${login}*:</td>
				<td><input type="text" name="login" value="" /></td>
			</tr>
			<tr>
				<td>${password}*:</td>
				<td><input type="text" name="password" value="" /></td>
			</tr>
			<tr>
				<td>${passwordAgain}*:</td>
				<td><input type="text" name="passwordAgain" value="" /></td>
			</tr>
			<tr>
				<td>${surname}*:</td>
				<td><input type="text" name="surname" value="" /></td>
			</tr>
			<tr>
				<td>${name}*:</td>
				<td><input type="text" name="name" value="" /></td>
			</tr>
			<tr>
				<td>${phone}*:</td>
				<td><input type="text" name="phone" value="" /></td>
			</tr>
			<tr>
				<td>${address}:</td>
				<td><input type="text" name="address" value="" /></td>
			</tr>
			<tr>
				<td>${email}:</td>
				<td><input type="text" name="email" value="" /></td>
			</tr>
		</table>
		<small>${noteRegistration1}</small> <br /> ${noteRegistration2} <a
			href="rules.jsp"> ${noteRegistration3} </a> <br /> 
			<input type="submit" value="${signUp}" class="button1"/> <br />
	</form>
</body>
</html>