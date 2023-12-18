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
        <h2 align="center" class="mt-3">Add Pay Information</h2>
		<div class="px-4 pt-3">
			<form:form action="/BoMoCDemo/addPay" method="post" modelAttribute="pay">
			   <div class="form-group">
			   		<label for="pay_method_id">Hình thức chi trả:</label>
					<select class="form-select form-select-lg mb-3" id="pay_method_id" name="pay_method_id" onchange="updateSelect()">
		                <option value="0" price="0">-- Chọn phương thức thanh toán --</option>
		                <c:forEach items="${listPayMethod}" var="payMethod">
		                    <option value="${payMethod.pay_method_id}" offer="${payMethod.offer_percent}">
		                        ${payMethod.pay_method_name}
		                    </option>
		                </c:forEach>
	            	</select>
			   </div>
			   <form:errors path="pay_method_id" class="text-danger" /><br/>
			   <span id="offer"></span>
			   <form:input type="hidden" class="form-control" path="customer_id" />
			   <form:input type="hidden" class="form-control" path="order_id" />
			   <form:input type="hidden" class="form-control" path="ship_id" />
			   <form:input type="hidden" class="form-control" path="pay_id" />
			   <input type="submit" value="Next" class="btn btn-success w-100 mt-2">
			</form:form>        
			<div class="btn btn-warning w-100 mt-2"><a href="/BoMoCDemo/ship/${pay.ship_id}">Back</a></div>
			<div class="btn btn-danger w-100 mt-2" onclick="handleCancel(`${pay.order_id}`)">Cancel</div>
		</div>
		
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
	    function updateSelect() {
	        var selectElement = document.getElementById("pay_method_id");
	        var selectedOption = selectElement.options[selectElement.selectedIndex];
	        var offer = selectedOption.getAttribute("offer");
	        var offerElement = document.getElementById("offer");
	        offerElement.innerHTML = "Phần trăm hóa đơn được giảm: " + offer + "%";
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