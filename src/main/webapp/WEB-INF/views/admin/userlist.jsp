<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
<c:import url="/css"></c:import>
<c:import url="/js"></c:import>
<title>List</title>
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
								<th>Surname</th>
								<th>E-mail</th>
								<th>Role</th>
								<th style="width: 130px;">Transactions</th>
							</tr>

							<c:if test="${ not empty uls }">

								<c:forEach items="${ uls }" var="item">
									<tr id="${ item.getUserId() }" role="delete">
										<td>${ item.getUserName() }</td>
										<td>${ item.getUserSurname() }</td>
										<td>${ item.getUserMail()}</td>
										<td><c:if test="${ item.getUserRole() == 0}">Standart</c:if>
											<c:if test="${ item.getUserRole() == 1}">Admin</c:if></td>
										<td><a class="btn btn-primary btn-sm"
											href='<s:url value="/admin/tasklist/${ item.getUserId() }"></s:url>'
											role="button">Tasks</a></td>
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

				<c:if test="${ empty uls }">
					<div style="text-align: center; padding: 10px;">There is no
						data!</div>
				</c:if>
			</div>
			<!-- /.box -->
		</div>
	</div>
</body>
</html>