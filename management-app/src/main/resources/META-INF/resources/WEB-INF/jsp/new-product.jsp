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
<h1>Add new Product</h1>
<br>
<c:if test="${successAction}">
    <div id="messageSuccess">Successfully added Product: ${savedProduct.name}</div>
    <br>
</c:if>
<c:url var="add_product_url" value="/product/add"/>
<form:form id="form-product" action="${add_product_url}" method="post" modelAttribute="product">
    <div class="form-group space">
        <form:label path="name" for="nameProduct">Name Product</form:label>
        <form:input path="name" type="text" class="form-control" id="nameProduct" placeholder="Enter name"/>
    </div>
    <br>
    <div class="form-group">
        <form:label path="type" for="typeProduct">Type</form:label>
        <form:input path="type" type="text" class="form-control" id="typeProduct" placeholder="Type"/>
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
<br>
<br>
<a href="/product">Return home</a>
</body>
</html>