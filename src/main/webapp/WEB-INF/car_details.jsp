<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car Details</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="pt-4">
<div class="main-part text-center">
    <div class="float-start">
        <a href="/" class="btn btn-primary"><- Back</a>
    </div>
    <h1 class="px-5 mx-5">Car Details</h1>
    <h3>Plate: <c:out value="${car.plate}"/></h3>
    <h3>Car Brand: <c:out value="${car.brand}"/></h3>
    <div class="service-part mt-5">
        <h3>Add services to this car</h3>
        <%--@elvariable id="service" type=""--%>
        <form:form action="/addRepairService" method="post" modelAttribute="service">
            <input type="hidden" name="plate" value="${car.plate}"/>
            <div class="m-1"><form:errors path="type" class="text-danger"/></div>
            <div class="m-1"><form:errors path="mileage" class="text-danger"/></div>
            <form:input path="type" placeholder="Type of service" class="mx-1"/>
            <form:input path="mileage" type="number" placeholder="Car mileage" class="mx-1"/>
            <button class="btn btn-dark button-font mx-2">Add</button>
        </form:form>
    </div>
    <div class="mt-5 mb-5">
        <c:if test="${car.carRepairServices.size() > 0}">
            <h3 class="text-decoration-underline mt-5">Car services:</h3>
            <ul>
                <c:forEach var="service" items="${car.carRepairServices}">
                    <li class="no-bullet-list">
                        <c:out value="${service.type}"/> --> at <c:out value="${service.formatCreationDate()}"/>
                        with <c:out value="${service.mileage}"/> km
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>
</body>
</html>