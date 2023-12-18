<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
    	<h1 align="center">Order Information</h1>
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
				<c:if test="${not empty listCart}">
				   <c:forEach var="cart" items="${listCart}" varStatus="status">
                <tr> 
                    <td>${status.index + 1}</td>
                    <td>${cart.product_name}</td>
                    <td>${cart.quantity}</td>
                    <td>${cart.price} vnđ</td>
                             
                </tr>
                </c:forEach>      
				</c:if>
                       
            </table>
            <h3>Phí ship: ${shipPrice} vnđ</h3>
            <h3>Giảm giá (phương thức thanh tonas): ${offerPercent}%</h3>
            <h3>Total Price: ${order.total_price} vnđ</h3>
            <div class="mx-5 d-flex justify-content-between mt-3">
            	<button class="btn btn-primary">
					<a href="/BoMoCDemo/pay/${order.pay_id}" class="text-white">Back</a>
				</button>
				<button class="btn btn-success" onclick="handleOrder()">
					Complete Order
				</button>
            </div>
        </div>
        </c:if>
    </body>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
    	function handleOrder() {
			//const id = document.getElementById("id").value;
			// Hiển thị popup confirm
			var confirmed = confirm("Bạn chắc chắn về thông tin của mình?");
			if (confirmed) {
				alert("Cảm ơn bạn đã đặt hàng, sản phẩm sẽ đến tay bạn sớm !");
				window.location.href = "/BoMoCDemo/listorder";
			}
		}
		
		
	</script>
	
</html>