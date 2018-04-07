<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Task</title>
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
								<label>Task Due Date</label> <input type="date" name="dueDate"
									min="1900-01-01" max="2099-12-31" class="form-control">
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
