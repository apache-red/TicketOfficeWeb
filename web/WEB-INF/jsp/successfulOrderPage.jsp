<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Подтвеждение успешности заказа</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.gratitudeForPurchase" var="gratitudeForPurchase" />
<fmt:message bundle="${loc}" key="local.messageAboutReturn1" var="messageAboutReturn1" />
<fmt:message bundle="${loc}" key="local.messageAboutReturn2" var="messageAboutReturn2" />
</head>

<jsp:include page="app/header.jsp" />

<h1><c:out value="${gratitudeForPurchase}" /></h1>

<p>
	${messageAboutReturn1} <a href="index.jsp">${messageAboutReturn2}</a>
</p>

</body>
</html>