<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${errorOccurred}">
    <div class="container">
        <h1>An Error has ocurred</h1>
        <ul class="list-group">
            <c:forEach var="error" items="${errors}">
                <li class="list-group-item list-group-item-warning">T
                    <div class="alert alert-danger">
                        <strong><c:out value="${error}"/>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<c:if test="${!errorOccurred}">
<h1>List of products</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${product}" var="item">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.type}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<br>
<a href="/product">Return home</a>
</body>
</html>