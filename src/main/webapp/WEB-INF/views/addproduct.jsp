<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
    	<h1 align="center">Chọn loại sản phẩm để thêm</h1>
    	<div class="mx-5 d-flex justify-content-between">
	    	<button class="btn btn-secondary mx-2">
	    		<a class="text-white" href="/BoMoCDemo/add/book">Book</a>
	    	</button>
	    	<button class="btn btn-success mx-2">
	    		<a class="text-white" href="/BoMoCDemo/add/clothes">Clothes</a>
	    	</button>
	    	<button class="btn btn-warning  mx-2">
	    		<a class="text-white" href="/BoMoCDemo/add/mobile">Mobile Phone</a>
	    	</button>
	    	</button>
    	</div>
    </body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</html>