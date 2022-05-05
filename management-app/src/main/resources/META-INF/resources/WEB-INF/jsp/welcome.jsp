<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
    <title>Greeting Page</title>
</head>
<body>
<div id="container">
    <div class="row">
        <nav class="center-content navbar navbar-dark bg-primary">
            <h2 class="center" id="title">WELCOME TO PRODUCT CATALOG</h2>
        </nav>
    </div>
    <div class="row justify-content-center">
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/product/list" class="btn btn-primary">LIST OF PRODUCTS</a>
        </div>
        <div class="col-4">
            <a href="${pageContext.request.contextPath}/product/add" class="btn btn-primary">ADD PRODUCT</a>
        </div>
    </div>
</div>
</body>
</html>