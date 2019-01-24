<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Подтверждение успешности регистрации</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.congratulation1" var="congratulation1" />
<fmt:message bundle="${loc}" key="local.messageAboutReturn3" var="messageAboutReturn3" />
<fmt:message bundle="${loc}" key="local.messageAboutReturn4" var="messageAboutReturn4" />
</head>
<body>
	<jsp:include page="app/header.jsp" />

	<font size="+3"><c:out value="${congratulation1}" /></font><br/>

	${messageAboutReturn3} 
	<a href="authorizationPage.jsp"> ${messageAboutReturn4} </a>
</body>
</html>