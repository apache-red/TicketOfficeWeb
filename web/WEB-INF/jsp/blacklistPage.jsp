<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<link rel="stylesheet" type="text/css" href="css/forpulsatingbuttons.css" media="all">
<title>Черный список</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.blacklist" var="blacklist" />
<fmt:message bundle="${loc}" key="local.idOfClient" var="idOfClient" />
<fmt:message bundle="${loc}" key="local.loginOfCLient" var="loginOfClient" />
<fmt:message bundle="${loc}" key="local.removeFromBlacklist1" var="removeFromBlacklist1" />
<fmt:message bundle="${loc}" key="local.removeFromBlacklist2" var="removeFromBlacklist2" />
<fmt:message bundle="${loc}" key="local.emptyBlacklist" var="emptyBlacklist" />
</head>
<body>

	<jsp:include page="app/header.jsp" />

	<h1>${blacklist}</h1>

	<c:if test="${sessionScope.clients != null}">
		<table class="table1">
			<thead>
				<tr>
					<td>${idOfClient}</td>
					<td>${loginOfClient}</td>
					<td>${removeFromBlacklist1}</td>
				</tr>
			</thead>
			<c:forEach items="${sessionScope.clients}" var="client">
				<tr>
					<td class="grey"><c:out value="${client.id}" /></td>
					<td class="grey"><c:out value="${client.login}" /></td>
					<td class="grey" align="center">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="remove_from_blacklist" />
							<input type="hidden" name="id_client" value="${client.id}" /> 
							<input type="submit" value="${removeFromBlacklist2}!" class="button3" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${sessionScope.clients == null}">
		${emptyBlacklist}
	</c:if>
</body>
</html>