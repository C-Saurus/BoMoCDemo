<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Order</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
    <h1 align="center">List Order</h1>
    
    <div align="center" class="px-5 pt-1">
        
        <table class="table table-striped table-hover mx-4 mt-4">
            <th>No</th>
            <th>Order_ID</th>
            <th>Status</th>
            <th>Date</th>
            <th>Price</th>
            <th>Action</th>
            <c:if test="${empty listOrder}">
                <h2>Không tìm thấy đơn hàng</h2>
            </c:if>
            <c:if test="${not empty listOrder}">
                <c:forEach var="order" items="${listOrder}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${order.order_id}</td>
                        <td>${order.order_status}</td>
                        <td>${order.order_date}</td>
                        <td>${order.total_price} vnđ</td>
                        <c:if test="${role == 1}">
				          <td>
				            <div class="btn btn-primary mt-2" onclick="handleChangeStatus(`${order.order_id}`)">
				            	Finish
				            </div>
				          </td>
			        	</c:if>
                        <c:if test="${role == 0}">
				          <td>
				            <div class="btn btn-danger mt-2" onclick="handleCancel(`${order.order_id}`)">
				            	Cancel
				            </div>
				          </td>
			        	</c:if>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        
        <div class="btn btn-warning w-100 mt-2">
            <a href="/BoMoCDemo/">Home</a>
        </div>
        
        
    </div>
</body>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
	    function handleChangeStatus(id) {
			var confirmed = confirm("Bạn có muốn thêm cập xác nhận đơn hàng đã được giao ?");
			if (confirmed) {
				var url = "/BoMoCDemo/updateStatus/" + id;
				console.log(url);
		        var xhr = new XMLHttpRequest();
			    xhr.open("POST", url, true);
			    xhr.onreadystatechange = function() {
			        if (xhr.readyState === 4 && xhr.status === 200) {
			            	var response = xhr.responseText;
			            	alert("Cập nhật thành công");
			            	window.location.reload(true);
			            }
			        };
			    xhr.send();
			}
		}
	    function handleCancel(id) {
			var confirmed = confirm("Bạn có chắc chắn muốn hủy đơn hàng ?");
			if (confirmed) {
				var url = "/BoMoCDemo/cancelOrder/" + id;
				console.log(url);
		        var xhr = new XMLHttpRequest();
			    xhr.open("POST", url, true);
			    xhr.onreadystatechange = function() {
			        if (xhr.readyState === 4 && xhr.status === 200) {
			            	var response = xhr.responseText;
			            	alert("Đã hủy thành công");
			            	window.location.reload(true);
			            }
			        };
			    xhr.send();
			}
		}
    </script>
</html>