<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>User Information</h2>
        <form:form action="updateuser" method="post" modelAttribute="user">
        	<div class="form-group">
			    <label for="username">Username:</label>
			    <form:input type="text" class="form-control" path="username" disable="true"/>
			    <form:errors path="username" cssClass="text-danger" />
			</div>
        	<div class="form-group">
			    <label for="email">Email:</label>
			    <form:input type="text" class="form-control" path="email" disable="true"/>
			    <form:errors path="email" cssClass="text-danger" />
			</div>
            <div class="form-group">
			    <label for="phone_num">Phone number:</label>
			    <form:input type="text" class="form-control" path="phone_num" disable="true"/>
			    <form:errors path="phone_num" cssClass="text-danger" />
			</div>
			<div class="form-group">
			    <label for="dob">Date of Birth:</label>
			    <form:input type="date" class="form-control" path="dob" disable="true"/>
			    <form:errors path="dob" cssClass="text-danger" />
			</div>
			<div class="form-group">
			    <label for="address">Address:</label>
			    <form:input type="text" class="form-control" path="address" disable="true"/>
			    <form:errors path="address" cssClass="text-danger" />
			</div>          
			<div class="d-flex justify-content-between mt-3">
				<button type="submit" class="btn btn-primary w-100 mr-4" >Save</button>
				<button type="button" class="btn btn-success w-100 ml-4">Edit</button>
			</div>
			<div class="form-group">
			    <form:input type="hidden" class="form-control" path="password" />
			</div>
        </form:form>
        <button type="button" class="btn btn-warning w-100 mt-4">
        	<a href="/BoMoCDemo/">Back</a>
        </button>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>