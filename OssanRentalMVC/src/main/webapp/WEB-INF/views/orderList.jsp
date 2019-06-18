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
	<div class="container-fluid mt-5 px-5">
		<c:if test="${empty oib}">
			<h2 class="text-danger text-center">尚無訂單</h2>
		</c:if>
		<c:if test="${!empty oib}">
			<table class="table table-bordered">
				<thead>
					<tr class="text-center">
						<th scope="col">#</th>

						<th scope="col">訂單日期</th>
						<th scope="col">購買時數</th>
						<th scope="col">下單金額</th>
						<th scope="col">訂購姓名</th>
						<th scope="col">聯絡信箱</th>
						<th scope="col">發票地址</th>
						<th scope="col">連絡電話</th>
						<th scope="col">客戶留言</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="oib" items="${oib}">
						<tr>
							<th scope="row">1</th>

							<td width="10%"><fmt:formatDate
									value="${oib.orderBean.orderDate}" pattern="yyyy-MM-dd" /></td>
							<td width="10%">${oib.quantity}</td>
							<td width="10%">${oib.orderBean.totalAmount}</td>
							<td width="10%">${oib.orderBean.invoiceTitle}</td>
							<td width="15%">${oib.orderBean.email}</td>
							<td width="15%">${oib.orderBean.address}</td>
							<td width="10%">${oib.orderBean.phone}</td>
							<td width="20%">${oib.orderBean.comment}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
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