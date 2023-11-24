<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="mt-4 container">
		<h2>Detail Product Information</h2>
		<c:if test="${not empty product}">
			<p>Mã sản phẩm: ${product.product_id}</p>
	        <p>Tên sản phẩm: ${product.product_name}</p>
			<p>Giá sản phẩm: ${product.price} vnđ</p>
			<p>Danh mục: ${product.category}</p>
			<p>Mô tả sản phẩm: ${product.description}</p>
	        <c:choose>
	            <c:when test="${fn:startsWith(product.product_id, 'BO')}">
	                <p>Tác giả: ${product.author_name}</p>
	                <p>Số trang: ${product.pages}</p>
	                <p>Ngày phát hành: ${product.release_date}</p>
	            </c:when>
	           
	        </c:choose>
	        <c:choose>
	            <c:when test="${fn:startsWith(product.product_id, 'MO')}">
	                <p>Kích thước màn: ${product.screen_size} inch</p>
	                <p>Bộ nhớ: ${product.memory_size} GB</p>
	                <p>Hạn bảo hành: ${product.warranty_date}</p>
	            </c:when>
	           
	        </c:choose>
	        <c:choose>
	            <c:when test="${fn:startsWith(product.product_id, 'C')}">
	                <p>Size: ${product.size}</p>
	                <p>Brand: ${product.brand}</p>
	                <p>Hạn bảo hành: ${product.warranty_date}</p>
	            </c:when>
	           
	        </c:choose>
	    </c:if>
	    <label for="quantity">Số lượng:</label>
	    <input type="number" id="quantity" name="quantity" min="1" value="1" class="control">
	    <input type="hidden" value="${product.price}" id="price"> 
	    <input type="hidden" value="${product.product_id}" id="id"> 
	    <div class="mt-3">
	    	<button class="btn btn-primary" onClick="addToCart()">
	    		Add to Cart
	    	</button>
	    	<button class="btn btn-warning">
	    		<a href="/BoMoCDemo/">Back</a>
	    	</button>
	    </div>
	</div>
</body>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 <script>
	function addToCart() {
		const quantity = document.getElementById("quantity").value;
		const id = document.getElementById("id").value;
		const price = document.getElementById("price").value;
		// Hiển thị popup confirm
		var confirmed = confirm("Bạn có muốn thêm " + quantity + " sản phẩm vào giỏ hàng?");
		if (confirmed) {
			var url = "/BoMoCDemo/addCart/" + id + "/" + quantity + "/" + price;
			console.log(url)
	        var xhr = new XMLHttpRequest();
		    xhr.open("GET", url, true);
		    xhr.onreadystatechange = function() {
		        if (xhr.readyState === 4 && xhr.status === 200) {
		            	var response = xhr.responseText;
		            	alert(quantity + " sản phẩm đã được thêm vào giỏ hàng.");
		            }
		        };
		    xhr.send();
		}
	}
</script>
</html>

