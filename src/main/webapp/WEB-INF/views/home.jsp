<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login</title>
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
<body style="background-color:#d9dddd">
	<div class="container">
		<div class="row">
		<div class="col-sm-4"></div>
			<div class="col-sm-4" align="center" style="margin-top: 5%">
				<div class="card" style="width: 18rem;">
					<div class="card-body" align="left">
						<h5 class="card-title">User Login</h5>
						<form action='<s:url value="/login"></s:url>' method="post">
							<div class="form-group">
								<label for="user_mail">Username</label> <input
									type="email" class="form-control" id="user_mail" name="userMail"
									placeholder="Enter your username">
							</div>
							<div class="form-group">
								<label for="user_password">Password</label> <input
									type="password" class="form-control" id="user_password" name="userPassword"
									placeholder="Password">
							</div>
							<button type="submit" class="btn btn-primary">Login</button>
						</form>
					</div>
				</div><div style="margin-top: 10%">
				<a href='<s:url value="/register"></s:url>'>Create a New Account</a>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
	</div>
</body>
</html>
