<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Register</title>
<c:import url="/css"></c:import>
<c:import url="/js"></c:import>
</head>
<body style="background-color: #d9dddd">
	<div class="container">
		<c:import url="/navbar"></c:import>
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6" align="center" style="margin-top: 25px">
				<c:if test="${ not empty error }">
					<div class="alert alert-danger alert-dismissible">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						<h4>
							<i class="icon fa fa-ban"></i>Alert
						</h4>${ error }
					</div>
				</c:if>
				<div class="jumbotron">
					<form action='<s:url value="/register"></s:url>' method="post">
						<div class="form-row">
							<div class="form-group col-sm-6">
								<label for="userName">Name</label> <input type="text"
									class="form-control" id="userName" name="userName"
									placeholder="Your name">
							</div>
							<div class="form-group col-sm-6">
								<label for="userSurname">Surname</label> <input type="text"
									class="form-control" id="userSurname" name="userSurname"
									placeholder="Your surname">
							</div>
							<div class="form-group col-sm-6">
								<label for="userPassword">Password</label> <input
									type="password" class="form-control" id="userPassword"
									name="userPassword" placeholder="Password">
							</div>
							<div class="form-group col-sm-6">
								<label for="conUserPassword">Confirm Password</label> <input
									type="password" class="form-control" id="conUserPassword"
									name="conUserPassword" placeholder="Password">
							</div>
							<div class="form-group col-sm-6">
								<label for="userMail">E-mail</label> <input type="email"
									class="form-control" id="userMail" name="userMail"
									placeholder="Your e-mail address">
							</div>
							<div class="form-group col-sm-6">
								<label for="role">User Role</label> <input type="text"
									class="form-control" id="role" placeholder="Standart User"
									disabled>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">Sign Up</button>
					</form>
				</div>
				<div style="margin-top: 20px">
					<a href='<s:url value="/"></s:url>'><button type="button"
							class="btn btn-primary btn-sm">Already a Member? Sign in
							now!</button></a>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
</body>
</html>
