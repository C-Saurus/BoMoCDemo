<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BoMoC Shop</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
    	<h1 align="center">BoMoC Shop</h1>
    	<div class="mx-5 d-flex justify-content-end">
	    	<button class="btn btn-secondary mx-1">
	    		<a class="text-white" href="/BoMoCDemo/logout">Logout</a>
	    	</button>
	    	<button class="btn btn-success mx-1">
	    		<a class="text-white" href="/BoMoCDemo/cart">Cart</a>
	    	</button>
	    	<button class="btn btn-warning  mx-1">
	    		<a class="text-white" href="/BoMoCDemo/profile">Profile</a>
	    	</button>
	    	<button class="btn btn-primary  mx-1">
	    		<a class="text-white" href="/BoMoCDemo/listorder">Order List</a>
	    	</button>
    	</div>
        <div align="center" class="px-5 pt-1">
            <form:form action="search" method="get" class="px-5 mt-5">
	            <div class="input-group mb-3">
				    <input type="text" class="form-control" name="keyword" placeholder="Search product..."/>
				    <button type="submit" class="btn btn-primary">Search</button>
				</div>   
				
	        </form:form>
            <table class="table table-striped table-hover mx-4 mt-4">
                <th>No</th>
                <th>Title</th>
                <th>Category</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Desc</th>
                <th>Action</th>
                 <c:if test="${empty listProduct}">
					<h2>Không tìm thấy sản phầm</h2>
				</c:if>
				<c:if test="${not empty listProduct}">
				   <c:forEach var="product" items="${listProduct}" varStatus="status">
                <tr>
                	
                    <td>${status.index + 1}</td>
                    <td>${product.product_name}</td>
                    <td>${product.category}</td>
                    <td>${product.quantity_in_stock}</td>
                    <td>${product.price} vnđ</td>
                    <td>${product.description}</td>
                    <td>
                        <a href="/BoMoCDemo/detail/${product.product_id}">Detail</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <c:if test="${role == 0}">
							<a href="/BoMoCDemo/addCart/${product.product_id}/1/${product.price}">Add to Cart</a>       
						</c:if>
                        <c:if test="${role == 1}">
							<a href="#">Delete</a>       
						</c:if>
                    </td>
                             
                </tr>
                </c:forEach>      
				</c:if>
                       
            </table>
            <c:if test="${role == 1}">
				<div class="btn btn-primary mt-2 w-100">
					<a href="/BoMoCDemo/add" class="text-white">Add product</a>
				</div>	         
			</c:if>
        </div>
    </body>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</html>