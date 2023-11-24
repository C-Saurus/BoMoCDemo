<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
    	<h1 align="center">${username} Cart Information</h1>
    	<c:if test="${empty listCart}">
			<div align="center" class=" px-5 pt-1">
				<h2>Chưa có sản phẩm được thêm vào giỏ hàng</h2>
				<button class="btn btn-primary my-4 mx-5">
					<a href="/BoMoCDemo/" class="text-white">Go to Product Page</a>
				</button>
			</div>		
		</c:if>
		<c:if test="${not empty listCart}">
        <div align="center" class=" px-5 pt-1">
            <table class="table table-striped table-hover mx-4 mt-4">
                <th>No</th>
                <th>Title</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
				<c:if test="${not empty listCart}">
				   <c:forEach var="cart" items="${listCart}" varStatus="status">
                <tr> 
                    <td>${status.index + 1}</td>
                    <td>${cart.product_name}</td>
                    <td>
                    	<input type="number" id="quantity${status.index+1}" name="quantity + ${status.index+1}" min="1" value="${cart.quantity}" class="control">
                    </td>
                    <td>${cart.price} vnđ</td>
                    <td>
                        <a href="/BoMoCDemo/detail/${cart.product_id}">Detail</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-warning" onClick="updateQuantity(`${status.index+1}`, `${cart.product_id}`)">Update quantity</button>
                        <button class="btn btn-danger" onClick="removeCart(`${cart.product_id}`)">Remove</button>
                    </td>
                             
                </tr>
                </c:forEach>      
				</c:if>
                       
            </table>
            <h3>Total Price: ${total} vnđ</h3>
            <div class="mx-5 d-flex justify-content-between mt-3">
            	<button class="btn btn-primary">
					<a href="/BoMoCDemo/" class="text-white">Back</a>
				</button>
				<button class="btn btn-success">
					<a href="/BoMoCDemo/" class="text-white">Add Order</a>
				</button>
            </div>
        </div>
        </c:if>
    </body>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
    	function removeCart(id) {
			//const id = document.getElementById("id").value;
			// Hiển thị popup confirm
			var confirmed = confirm("Bạn có muốn thêm xóa sản phẩm khỏi giỏ hàng?");
			if (confirmed) {
				var url = "/BoMoCDemo/removeCart/" + id;
		        var xhr = new XMLHttpRequest();
			    xhr.open("POST", url, true);
			    xhr.onreadystatechange = function() {
			        if (xhr.readyState === 4 && xhr.status === 200) {
			            	var response = xhr.responseText;
			            	alert("Sản phẩm đã được xóa khỏi giỏ hàng.");
			            	window.location.reload(true);
			            }
			        };
			    xhr.send();
			}
		}
		function updateQuantity(index, id) {
			const quantity = document.getElementById("quantity"+index).value;
			// Hiển thị popup confirm
			var confirmed = confirm("Bạn có muốn thêm " + quantity + " sản phẩm vào giỏ hàng?");
			if (confirmed) {
				var url = "/BoMoCDemo/updateCart/" + id + "/" + quantity;
		        var xhr = new XMLHttpRequest();
			    xhr.open("POST", url, true);
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