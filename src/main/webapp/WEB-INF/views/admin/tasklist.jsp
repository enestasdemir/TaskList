<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task List</title>
<c:import url="/css"></c:import>
<c:import url="/js"></c:import>
</head>
<body>
	<div class="container">
		<c:import url="/navbar"></c:import>
		<div class="col-sm-12">
			<div class="box">
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
										<td><c:if test="${ item.getTaskStatus() == 0}"><span class="badge badge-primary">Active</span></c:if>
											<c:if test="${ item.getTaskStatus() == 1}"><span class="badge badge-warning">Postponed</span></c:if> <c:if
												test="${ item.getTaskStatus() == 2}"><span class="badge badge-danger">Canceled</span></c:if> <c:if
												test="${ item.getTaskStatus() == 3}"><span class="badge badge-success">Finished</span></c:if></td>
										<td><a class="btn btn-primary btn-sm"
											href='<s:url value="/admin/taskedit/${ item.getTaskId() }"></s:url>'
											role="button">Edit</a>
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