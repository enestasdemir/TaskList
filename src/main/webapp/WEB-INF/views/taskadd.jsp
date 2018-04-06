<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Task</title>
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
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4" align="center" style="margin-top: 25px">
				<div class="card" style="width: 18rem;">
					<div class="card-body" align="left">
						<h5 class="card-title">Task Add</h5>
						<form action='<s:url value="/addtask"></s:url>' method="post">
							<div class="form-group">
								<label for="taskName">Task Name</label> <input type="text"
									class="form-control" id="taskName" name="taskName"
									placeholder="Task Name">
							</div>
							<div class="form-group">
								<label for="taskDescription">Task Description</label> <input
									type="text" class="form-control" id="taskDescription"
									name="taskDescription" placeholder="Task Description">
							</div>

							<div class="form-group">
								<label>Task Start Date</label> <input type="date"
									name="startDate" max="2099-12-31" min="1900-01-01"
									class="form-control">
							</div>
							<div class="form-group">
								<label>Task Due Date</label> <input type="date"
									name="dueDate" min="1900-01-01" max="2099-12-31"
									class="form-control">
							</div>
							<button type="submit" class="btn btn-primary">Add</button>
						</form>
					</div>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</div>

</body>
</html>
