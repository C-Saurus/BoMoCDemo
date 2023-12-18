<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ship</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 align="center" class="mt-3">Add Ship Information</h2>
		<div class="px-4 pt-3">
			<form:form action="/BoMoCDemo/addShip" method="post" modelAttribute="ship">
			   <div class="form-group">
			   		<label for="ship_method_id">Hình thức vận chuyển:</label>
					<select class="form-select form-select-lg mb-3" id="ship_method_id" name="ship_method_id" onchange="updatePrice()">
		                <option value="0" offer="0">-- Chọn phương thức vận chuyển --</option>
		                <c:forEach items="${listShipMethod}" var="shipMethod">
		                    <option value="${shipMethod.ship_method_id}" price="${shipMethod.price}">
		                        ${shipMethod.ship_method_name}
		                    </option>
		                </c:forEach>
	            	</select>
			   </div>
			   <form:errors path="ship_method_id" class="text-danger" /><br/>
			   <span id="price"></span>
			   <div class="form-group">
				   <label for="address">Địa chỉ nhận hàng</label>
				   <form:textarea path="address" class="form-control"/>
			   </div>
			   <form:input type="hidden" class="form-control" path="customer_id" />
			   <form:input type="hidden" class="form-control" path="order_id" />
			   <form:input type="hidden" class="form-control" path="ship_id" />
			   <form:errors path="address" class="text-danger" /><br/>
			   <input type="submit" value="Next" class="btn btn-success w-100 mt-2">
			</form:form>        
			<div class="btn btn-danger w-100 mt-2" onclick="handleCancel(`${ship.order_id}`)">Cancel</div>
		</div>
		
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
	    function updatePrice() {
	        var selectElement = document.getElementById("ship_method_id");
	        var selectedOption = selectElement.options[selectElement.selectedIndex];
	        var price = selectedOption.getAttribute("price");
	        var priceElement = document.getElementById("price");
	        priceElement.innerHTML = "Chi phí vận chuyển: " + price + "vnđ";
	    }
	    function handleCancel(id) {
			//const id = document.getElementById("id").value;
			// Hiển thị popup confirm
			var confirmed = confirm("Bạn có muốn thêm hủy bỏ thao tác đặt hàng?");
			if (confirmed) {
				var url = "/BoMoCDemo/cancelOrder/" + id;
		        var xhr = new XMLHttpRequest();
			    xhr.open("POST", url, true);
			    xhr.onreadystatechange = function() {
			        if (xhr.readyState === 4 && xhr.status === 200) {
			            	return;
			            }
			        };
			    xhr.send();
			}
		}
	</script>
</body>

</html>