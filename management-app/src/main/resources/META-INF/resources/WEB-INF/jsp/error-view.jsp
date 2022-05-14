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
<div class="container">
    <h1>An Error has ocurred</h1>
    <ul class="list-group">
            <li class="list-group-item list-group-item-warning">
                <div class="alert alert-danger">
                    <strong>An error has occurred when loading the page, try it later</strong>
                </div>
            </li>
    </ul>
</div>
<br>
<a href="/product">Return home</a>
</body>
</html>