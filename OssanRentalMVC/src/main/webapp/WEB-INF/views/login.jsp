<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
<jsp:include page="top.jsp" />
</head>
<style>
.login {
	font-family: Microsoft JhengHei;
	margin-top: 100px;
}
</style>
<body>
	<c:if test="${ ! empty sessionScope.timeOut }">
		<!-- 表示使用逾時，重新登入 -->
		<span>${sessionScope.timeOut}</span>
	</c:if>
	<!-- 登入 -->
	<div class="login container w-25">

		<form action="<c:url value="/login" />" method="post">
			<div class="form-group">
				<label for="exampleInputEmail1">電子信箱</label> <input type="email"
					name="email" value="${requestScope.user}"
					class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp"> <span>${errorMsgMap.AccountEmptyError}</span>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">密碼</label> <input type="password"
					name="password" value="${requestScope.password}"
					class="form-control" id="exampleInputPassword1"> <span>${errorMsgMap.PasswordEmptyError}</span>
			</div>
			<div class="form-group form-check">
				<input type="checkbox" name="rememberMe" class="form-check-input"
					<c:if test='${requestScope.rememberMe==true}'>
                  					checked='checked'
               				</c:if>
					id="rememberMe" value="true"> <label class="form-check-label"
					for="exampleCheck1">記住我</label>
					<span>${errorMsgMap.LoginError}</span>    
			</div>
			<button type="submit" class="btn btn-primary">送出</button>
		</form>
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