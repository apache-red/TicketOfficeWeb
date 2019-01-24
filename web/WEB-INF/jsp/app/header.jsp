<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" media="all">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.logOut" var="logOut" />
<fmt:message bundle="${loc}" key="local.greeting" var="greeting" />
<fmt:message bundle="${loc}" key="local.myShoppingCart" var="myShoppingCart" />
<fmt:message bundle="${loc}" key="local.authorization" var="authorization" />
<fmt:message bundle="${loc}" key="local.isRegistered" var="isRegistered" />
<fmt:message bundle="${loc}" key="local.signUp" var="signUp" />
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.admin!=null}">
			<table width="100%">
				<tr>
					<td
						style="font-size: 80px; font-family: fantasy; font-style: italic; max-height: 15px">
						<a href="controller?command=go_to_admin_page"
						style="text-decoration: none; color: silver;" title="На главную">Железнодорожная касса</a>
					</td>
					<td><jsp:include page="localization.jsp" />
						<p align="right">${greeting}
						<c:out value="${sessionScope.admin.login}!"></c:out>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="log_out" />
							<p align="right">
								<input type="submit" value="${logOut}" class="button2" />
							</p>
						</form>
					</td>
				</tr>
			</table>
		</c:when>
		<c:when test="${sessionScope.client!=null}">
			<table width="100%">
				<tr>
					<td	style="font-size: 80px; font-family: fantasy; font-style: italic; max-height: 15px">
						<a href="index.jsp" style="text-decoration: none; color: #880000;"
						title="На главную">Железнодорожная касса</a>
					</td>
					<td><jsp:include page="localization.jsp" />
						<p align="right">${greeting}
						<c:out value="${sessionScope.client.login}!"></c:out><br /> 
						<a href="controller?command=show_orders&id_client=${client.id}">
							${myShoppingCart} 
						</a> <br />
						<form action="controller" method="post">
							<input type="hidden" name="command" value="log_out" />
							<p align="right">
								<input type="submit" value="${logOut}" class="button2" />
							</p>
						</form>
					</td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<table width="100%">
				<tr>
					<td style="font-size: 80px; font-family: fantasy; text-align: center; font-style: oblique; max-height: 15px">
                        <%--color of the main inscription--%>
						<a href="index.jsp" style="text-decoration-line: none; color: #f67977; " title="На главную страницу">
							Железнодорожная касса
						</a>
					</td>
					<td>
						<jsp:include page="localization.jsp" />
						<p align="right">
							<a href="authorizationPage.jsp"> ${authorization} </a>
						</p>
						<p align="right">${isRegistered}</p>
						<p align="right">
							<a href="registrationPage.jsp"> ${signUp} </a>
						</p>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

</body>
</html>