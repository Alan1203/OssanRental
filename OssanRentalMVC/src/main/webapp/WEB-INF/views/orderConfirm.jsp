<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結帳</title>
</head>
<jsp:include page="top.jsp" />
<style>
body {
	font-family: Microsoft JhengHei;
}
</style>
<body>
	<div class="container-fluid mt-5">
		<div class="row">
			<div class="col-5 mt-5 mx-auto">
				<table class="table table-hover">
					<thead class="thead-light">
						<tr>
							<th scope="col">#</th>
							<th scope="col">大叔名稱</th>
							<th scope="col">價格(NT$/HR)</th>
							<th scope="col">購買時數</th>
							<th scope="col">單品金額</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach varStatus="vs" var="cart" items="${cart.content}">
							<tr>
								<th scope="row">1</th>
								<td>${cart.value.name}</td>
								<td>${cart.value.price}</td>
								<td>${cart.value.qty}</td>
								<td>${cart.value.price * cart.value.qty}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="row  bg-light justify-content-end">
					<div class="col-3 bg-light">商品金額總計:</div>
					<div class="col-3 ">
						NT$
						<fmt:formatNumber value="${cart.subtotal}" pattern="#,###,###" />
						元
					</div>
				</div>
			</div>
			<div class="col-5 mx-auto bg-light py-5">
				<form:form modelAttribute="orderBean" method="post">
					<div class="form-group">
						<form:input type="text" class="form-control w-50 mx-auto" path="invoiceTitle"
							id="exampleInputEmail1" placeholder="發票抬頭(姓名)" />
					</div>
					<div class="form-group">
						<form:input type="tel" class="form-control w-50 mx-auto" path="phone"
							id="exampleInputEmail1" placeholder="連絡電話(手機)" />
					</div>
					<div class="form-group">
						<form:input type="email" class="form-control w-50 mx-auto" path="email"
							id="exampleInputEmail1" placeholder="電子郵件" />
					</div>
					<div class="form-group">
						<form:input type="text" class="form-control w-50 mx-auto" path="bno"
							id="exampleInputEmail1" placeholder="統編(不需要可跳過)" />
					</div>
					<div class="form-group">
						<form:input type="text" class="form-control w-50 mx-auto"
							id="exampleInputEmail1" path="address"
							placeholder="發票地址" />
					</div>
					<div class="form-group">
						<form:textarea class="form-control w-50 mx-auto" path="comment"
							id="exampleInputEmail1" placeholder="留言給大叔" rows="3" />
					</div>
					<div class="row w-50 mx-auto">
						<div class="col-6">
							<button type="submit"
								class="btn w-100 text-light font-weight-bold border"
								style="background: #b22222">送出訂單</button>
						</div>
						<div class="col-6">
							<a href="<c:url value="/" />" role="button"
								class="btn w-100 text-light font-weight-bold border"
								style="background: #b22222">取消訂單</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


	<footer class="text-muted py-3 mt-5  bg-light ">
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