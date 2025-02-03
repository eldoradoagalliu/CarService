<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car Service</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<h1 class="text-decoration-underline mb-3">My Service</h1>
<div class="main-part">
    <c:if test="${searchEnabled}">
        <div class="float-start">
            <a href="/car/my/service" class="btn btn-primary"><- Back</a>
        </div>
    </c:if>
    <div class="search-part mt-2 mx-5 px-5">
        <form:form action="/car/search" method="post">
            <label><input name="inputtedPlate" placeholder="Input car plate" required="required"/></label>
            <button class="btn btn-success button-font mx-2">Search</button>
        </form:form>
    </div>
    <c:if test="${searchEnabled}">
        <div>
            <c:choose>
                <c:when test="${carPlate != null}">
                    Car with plate: <a href="/car/${carPlate}" class="link">${carPlate}</a>
                </c:when>
                <c:otherwise>
                    <c:out value="There was no car with this plate!"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="extra-margin"></div>
    </c:if>
    <c:if test="${showAddCarForm}">
        <h3 class="mt-5">Add new car to service</h3>
        <div class="new-car-part mb-5">
            <%--@elvariable id="car" type=""--%>
            <form:form action="/car" method="post" modelAttribute="car">
                <c:if test="${carExistsMessage != null}">
                    <div class="text-danger">${carExistsMessage}</div>
                </c:if>
                <div class="m-1"><form:errors path="plate" class="text-danger"/></div>
                <div class="m-1"><form:errors path="brand" class="text-danger"/></div>
                <div class="m-2"><form:input path="plate" placeholder="Plate"/></div>
                <div class="m-1"><form:input path="brand" placeholder="Brand"/></div>
                <div class="m-2 pr d-flex justify-content-end">
                    <button class="btn btn-primary button-font">Add</button>
                </div>
            </form:form>
        </div>
    </c:if>
    <div>
        <c:if test="${!cars.isEmpty()}">
            <c:if test="${showAllCars}"><h3>Total number of cars: <c:out value="${cars.size()}"/></h3></c:if>
            <h3 class="mt-3">Cars added to this service:</h3>
            <c:choose>
                <c:when test="${showAllCars == null}">
                    <ul>
                        <c:forEach var="car" items="${cars}" end="${9}">
                            <li class="no-bullet-list m-2 d-inline-block">
                                <a href="/car/${car.plate}" class="link text-decoration-none">
                                    <c:out value="${car.plate}"/>
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${cars.size() > 10}">
                            <li class="no-bullet-list m-2 d-inline-block"><a href="/show/all">See more...</a></li>
                        </c:if>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ol class="center-ol">
                        <c:forEach var="car" items="${cars}">
                            <li class="m-2">
                                <a href="/car/${car.plate}" class="link text-decoration-none">
                                    <c:out value="${car.plate}"/>
                                </a>
                            </li>
                        </c:forEach>
                    </ol>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</div>
</body>
</html>