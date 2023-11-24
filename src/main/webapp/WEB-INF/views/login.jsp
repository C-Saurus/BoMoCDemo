<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form:form action="authentication" method="post" modelAttribute="data">
            <div class="form-group">
			    <label for="email">Email:</label>
			    <form:input type="text" class="form-control" path="email" />
			    <form:errors path="email" cssClass="text-danger" />
			</div>
			<div class="form-group">
			    <label for="password">Password:</label>
			    <form:input type="password" class="form-control" path="password" />
			    <form:errors path="password" cssClass="text-danger" />
			</div>         
			 
			<button type="submit" class="btn btn-primary w-100">Login</button>
        </form:form>
        <c:if test="${param.error == '1'}">
		    <div class="text-warning my-2">
		        <h3 align="center">Đăng nhập để truy cập vào trang này bạn êyy.</h3>
		    </div>
		</c:if>
        <button class="btn btn-warning w-100 mt-2">
        	<a href="/BoMoCDemo/register">Register</a>
        </button>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>