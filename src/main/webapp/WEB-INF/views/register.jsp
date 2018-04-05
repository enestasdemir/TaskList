<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>User Register</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body style="background-color: #d9dddd">
	<div class="container">
		<c:import url="/navbar"></c:import>
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6" align="center" style="margin-top: 50px">
				<div class="card" style="width: 18rem;">
					<div class="card-body" align="left">
						<h5 class="card-title">Register</h5>
						<form action='<s:url value="/register"></s:url>' method="post">
							<div class="form-group">
								<label for="userName">Name</label> <input type="text"
									class="form-control" id="userName" name="userName"
									placeholder="Your name">
							</div>
							<div class="form-group">
								<label for="userSurname">Surname</label> <input type="text"
									class="form-control" id="userSurname" name="userSurname"
									placeholder="Your surname">
							</div>
							<div class="form-group">
								<label for="userMail">E-mail</label> <input type="email"
									class="form-control" id="userMail" name="userMail"
									placeholder="Your e-mail address">
							</div>
							<div class="form-group">
								<label for="userPassword">Password</label> <input
									type="password" class="form-control" id="userPassword"
									name="userPassword" placeholder="Password">
							</div>
							<div class="form-group">
								<label for="conUserPassword">Confirm Password</label> <input
									type="password" class="form-control" id="conUserPassword"
									name="conUserPassword" placeholder="Password">
							</div>
							<button type="submit" class="btn btn-primary">Sign Up</button>
						</form>
					</div>
				</div>
				<div style="margin-top: 20px">
					Already a Member? <a href='<s:url value="/"></s:url>'>Sign In
						Now</a>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
	</div>
</body>
</html>
