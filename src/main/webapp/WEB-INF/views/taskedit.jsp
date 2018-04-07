<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Edit</title>
<c:import url="/css"></c:import>
<c:import url="/js"></c:import>
</head>
<body style="background-color: #d9dddd">
	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4" align="center" style="margin-top: 25px">
				<div class="card" style="width: 18rem;">
					<div class="card-body" align="left">
						<h5 class="card-title">Task Edit</h5>
						<c:if test="${ not empty ls }">
							<c:forEach items="${ ls }" var="item">
								<form action='<s:url value="/updatetask"></s:url>' method="post">
									<div class="form-group">
										<label for="taskName">Task Name</label> <input type="text"
											class="form-control" id="taskName" name="taskName"
											placeholder="Task Name" value="${item.getTaskName()}">
									</div>
									<div class="form-group">
										<label for="taskDescription">Task Description</label> <input
											type="text" class="form-control" id="taskDescription"
											name="taskDescription" placeholder="Task Description"
											value="${item.getTaskDescription()}">
									</div>

									<div class="form-group">
										<label>Task Start Date</label> <input type="date"
											name="startDate" max="2099-12-31" min="1900-01-01"
											class="form-control" value="${item.getTaskStartDate()}">
									</div>
									<div class="form-group">
										<label>Task Due Date</label> <input type="date" name="dueDate"
											min="1900-01-01" max="2099-12-31" class="form-control"
											value="${item.getTaskDueDate()}">
									</div>
									<div class="form-group">
										<label for="taskStatus">Task Status</label>
										<select class="form-control" id="taskStatus" name="taskStatus">
											<option value="0" <c:if test="${item.getTaskStatus() == 0 }">selected</c:if>>Active</option>
											<option value="1" <c:if test="${item.getTaskStatus() == 1 }">selected</c:if>>Postponed</option>
											<option value="2" <c:if test="${item.getTaskStatus() == 2 }">selected</c:if>>Canceled</option>
											<option value="3" <c:if test="${item.getTaskStatus() == 3 }">selected</c:if>>Finished</option>
										</select>
									</div>
									<button type="submit" class="btn btn-primary">Update</button>
								</form>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</div>

</body>
</html>
