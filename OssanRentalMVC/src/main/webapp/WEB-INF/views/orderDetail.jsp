<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單列表</title>
</head>
<jsp:include page="top.jsp" />
<style>
body {
	font-family: Microsoft JhengHei;
}
</style>
<body>
	<div class="container-fluid mt-5 px-5 w-75">
		
			<table class="table table-bordered">
				<thead>
					<tr class="text-center">
						<th scope="col">#</th>

						<th scope="col">大叔編號</th>
						<th scope="col">大叔姓名</th>
						<th scope="col">大叔暱稱</th>
						<th scope="col">購買時數</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${list}">
						<tr>
							<th scope="row">${list.ossanBean.ossanNo}</th>

							<td>${list.ossanBean.name}</td>
							<td>${list.ossanBean.nickname}</td>
							<td>${list.quantity}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<footer class="text-muted py-3 mt-5  bg-light fixed-bottom">
		<div class="container">
			<p class="float-right h3">
				<a href="#">Back to top</a>
			</p>
			<p>Album example is &copy; Bootstrap, but please download and
				customize it for yourself!</p>

		</div>
	</footer>
</body>
</html>