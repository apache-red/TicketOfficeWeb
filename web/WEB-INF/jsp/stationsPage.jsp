<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<title>Страница с маршрутами</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.errorAddRoute" var="errorAddRoute" />
<fmt:message bundle="${loc}" key="local.nameOfEditStation" var="nameOfEditStation" />
<fmt:message bundle="${loc}" key="local.nameOfDeleteStation" var="nameOfDeleteStation" />
<fmt:message bundle="${loc}" key="local.nameOfAddNewStation" var="nameOfAddNewStation" />
<fmt:message bundle="${loc}" key="local.addRoute1" var="addRoute1" />
<fmt:message bundle="${loc}" key="local.addRoute2" var="addRoute2" />
</head>
<body>

	<c:if test="${sessionScope.errorAddRoute==1}">
		<font color="#CC0000"> ${errorAddRoute} </font>
		<c:set var="errorAddRoute" value="0" scope="session" />
	</c:if>

	<c:if test="${sessionScope.admin==null}">
		<table width="50%" cellpadding="0">
			<c:forEach items="${applicationScope.allRoutes}" var="route_i">
				<tr>
					<td><h2><c:out value="${route_i.name}"/></h2></td>
					<td><mytag:getstations route="${route_i}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${sessionScope.admin!=null}">
		<table width="50%">
			<c:forEach items="${applicationScope.allRoutes}" var="route_i">
				<tr>
					<td><h2><c:out value="${route_i.name}" /></h2></td>
					<td><mytag:getstationsforadmin route="${route_i}" nameedit="${nameOfEditStation}"
					namedelete="${nameOfDeleteStation}" nameadd="${nameOfAddNewStation}" /></td>
				</tr>
			</c:forEach>
		</table><br/>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="add_new_route" />
			<input type="text" name="name_route" value="" title="${addRoute1}"/>
			<input type="submit" value="${addRoute2}" class="button2"/>
		</form>
	</c:if>

</body>
</html>