<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task List</title>
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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="<s:url value="../../resources/js/functions.js"></s:url>"></script>
</head>
<body>
	<div class="container">
		<div class="col-sm-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Task List</h3>
					<div align="right">
						<a href='<s:url value="/taskadd"></s:url>'>New Task</a> <a
							href='<s:url value="/logout"></s:url>'>Logout</a>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover">
						<tbody>
							<tr id="rows">
								<th style="width: 40px">Name</th>
								<th>Description</th>
								<th>Start Date</th>
								<th>Due Date</th>
								<th>Status</th>
								<th style="width: 130px;">Transactions</th>
							</tr>

							<c:if test="${ not empty ls }">

								<c:forEach items="${ ls }" var="item">
									<tr id="${ item.getTaskId() }" role="delete">
										<td>${ item.getTaskName() }</td>
										<td>${ item.getTaskDescription() }</td>
										<td>${ item.getTaskStartDate()}</td>
										<td>${ item.getTaskDueDate()}</td>
										<td>
										<c:if test="${ item.getTaskStatus() == 0}">Active</c:if>
										<c:if test="${ item.getTaskStatus() == 1}">Postponed</c:if>
										<c:if test="${ item.getTaskStatus() == 2}">Canceled</c:if>
										<c:if test="${ item.getTaskStatus() == 3}">Finished</c:if>
										</td>
										<td>
											<a class="btn btn-primary btn-sm" href='<s:url value="/admin/taskedit/${ item.getTaskId() }"></s:url>' role="button">Edit</a>
											<button
												onclick="fncDelete(${ item.getTaskId() }, 'task_id','tasks')"
												type="button" class="btn btn-danger btn-sm">Delete</button>
										</td>
									</tr>
								</c:forEach>

							</c:if>

						</tbody>
					</table>
				</div>
				<!-- /.box-body -->

				<div class="box-footer clearfix">
					<ul class="pagination pagination-sm no-margin pull-right"
						id="pages">

					</ul>
				</div>

				<c:if test="${ empty ls }">
					<div style="text-align: center; padding: 10px;">There is no
						data!</div>
				</c:if>
			</div>
			<!-- /.box -->
		</div>
	</div>
</body>
</html>