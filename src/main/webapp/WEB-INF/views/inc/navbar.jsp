<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>

<nav
	class="navbar navbar-expand-lg navbar-light bg-light justify-content-end">
	<a class="navbar-brand" href="#">Task List</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<c:set var="role" value="${us.get(0).getUserRole()}" />
			<c:if test="${ not empty us }">
				<li class="nav-item"><a class="nav-link"
					href='<s:url value="/tasklist"></s:url>'>My Task List </a></li>
				<li class="nav-item"><a class="nav-link"
					href='<s:url value="/taskadd"></s:url>'>New Task</a></li>
				<c:if test="${ role == 1 }">
					<li class="nav-item"><a class="nav-link"
						href='<s:url value="/admin/userlist"></s:url>'>User List</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href='<s:url value="/logout"></s:url>'>Logout</a></li>
			</c:if>
			<c:if test="${ empty us }">
				<li class="nav-item"><a class="nav-link"
					href='<s:url value="/"></s:url>'>Sign In</a></li>
				<li class="nav-item"><a class="nav-link"
					href='<s:url value="/register"></s:url>'>Sign Up</a></li>
			</c:if>
		</ul>
	</div>
	<c:if test="${filter == 1}">
	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button"
			id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">Filter</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<a class="dropdown-item" href='<s:url value="/tasklist"></s:url>'>No Filter</a>
			<a class="dropdown-item" href='<s:url value="/tasklist/0"></s:url>'>Active</a>
			<a class="dropdown-item" href='<s:url value="/tasklist/1"></s:url>'>Postponed</a>
			<a class="dropdown-item" href='<s:url value="/tasklist/2"></s:url>'>Canceled</a>
			<a class="dropdown-item" href='<s:url value="/tasklist/3"></s:url>'>Finished</a>
		</div>
	</div>
	</c:if>
		<c:if test="${filter == 2}">
	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button"
			id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">Filter</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<a class="dropdown-item" href='<s:url value="/admin/tasklist/${ uid }"></s:url>'>No Filter</a>
			<a class="dropdown-item" href='<s:url value="/admin/tasklist/${ uid }/0"></s:url>'>Active</a>
			<a class="dropdown-item" href='<s:url value="/admin/tasklist/${ uid }/1"></s:url>'>Postponed</a>
			<a class="dropdown-item" href='<s:url value="/admin/tasklist/${ uid }/2"></s:url>'>Canceled</a>
			<a class="dropdown-item" href='<s:url value="/admin/tasklist/${ uid }/3"></s:url>'>Finished</a>
		</div>
	</div>
	</c:if>
</nav>