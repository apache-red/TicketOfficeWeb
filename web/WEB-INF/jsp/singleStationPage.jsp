<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/main.css" media="all">
    <title>Страница станции</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.idNumberOfStation" var="idNumberOfStation" />
    <fmt:message bundle="${loc}" key="local.orderMessage" var="orderMessage" />
</head>
<body>
<jsp:include page="app/header.jsp" />

<jsp:useBean id="station" class="by.htp.ticketoffice.entity.Station" scope="request" />
<jsp:setProperty property="*" name="station" />

<h1><jsp:getProperty property="name" name="station" /></h1>

<table class="table2">
    <tr>
        <td>${idNumberOfStation}:</td>
        <td><jsp:getProperty property="id" name="station" /></td>
    </tr>
    <tr>
        <td>
            <c:if test="${sessionScope.client != null}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="order" />
                    <input type="hidden" name="id_client" value="${client.id}" />
                    <input type="hidden" name="id_station" value="${station.id}" />
                    <input type="submit" value="${orderMessage}" />
                </form>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>