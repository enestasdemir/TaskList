<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login</title>
<c:import url="/css"></c:import>
<c:import url="/js"></c:import>
</head>
<body style="background-color: #d9dddd">
	<div class="container">
		<c:import url="/navbar"></c:import>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4" align="center" style="margin-top: 25px">
				<c:if test="${ not empty error }">
					<div class="alert alert-danger alert-dismissible">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						<h4>
							<i class="icon fa fa-ban"></i>Alert
						</h4>${ error }
					</div>
				</c:if>
				<div class="card" style="width: 18rem;">
					<div class="card-body" align="left">
						<h5 class="card-title">Login</h5>
						<form action='<s:url value="/login"></s:url>' method="post">
							<div class="form-group">
								<label for="user_mail">User Email</label> <input type="email"
									class="form-control" id="user_mail" name="userMail"
									placeholder="Enter email">
							</div>
							<div class="form-group">
								<label for="user_password">Password</label> <input
									type="password" class="form-control" id="user_password"
									name="userPassword" placeholder="Password">
							</div>
							<button type="submit" class="btn btn-primary">Login</button>
						</form>
					</div>
				</div>
				<div style="margin-top: 20px">
					<a href='<s:url value="/register"></s:url>'><button
							type="button" class="btn btn-primary btn-sm">Create a
							New Account</button></a>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</div>
</body>
</html>
