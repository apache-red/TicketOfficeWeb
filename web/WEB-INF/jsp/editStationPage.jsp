<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/main.css" media="all">
    <title>Страница редактирования данных о станции</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="by.htp.ticketoffice.localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.errorAddOrEditStation1" var="errorAddOrEditStation1" />
    <fmt:message bundle="${loc}" key="local.idNumberOfRoute" var="idNumberOfRoute" />
    <fmt:message bundle="${loc}" key="local.idNumberOfStation" var="idNumberOfStation" />
    <fmt:message bundle="${loc}" key="local.nameStation" var="nameStation" />
    <fmt:message bundle="${loc}" key="local.nameOfOperation4" var="nameOfOperation4" />
</head>
<body>

<jsp:useBean id="station" class="by.htp.ticketoffice.entity.Station" scope="request" />

<jsp:include page="app/header.jsp" />

<c:if test="${sessionScope.errorAddOrEditStation==1}">
    <font color="#CC0000"> ${errorAddOrEditStation1} </font>
    <c:set var="errorAddOrEditStation" value="0" scope="session" />
</c:if>


<form action="controller" method="post">
    <input type="hidden" name="command" value="edit_station" />
    <table class="table2">
        <tr>
            <td>${idNumberOfRoute}:</td>
            <td><input type="text" readonly="readonly" name="id_route" value="${requestScope.station.idRoute}" /></td>
        </tr>
        <tr>
            <td>${idNumberOfStation}:</td>
            <td><input type="text" readonly="readonly" name="id_station" value="${requestScope.station.id}" /></td>
        </tr>
        <tr>
            <td>${nameStation}:</td>
            <td><input type="text" name="name_station" value="${requestScope.station.name}" /></td>
        </tr>
        <tr>
            <td></td>
            <td align="center"><input type="submit"
                                      value="${nameOfOperation4}" /></td>
        </tr>
    </table>
</form>
</body>
</html>