<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>大叔註冊</title>
<jsp:include page="top.jsp" />
</head>
<style>
.register {
	font-family: Microsoft JhengHei;
}
</style>
<body>
	<!-- 註冊表單 -->
	<div class="register container w-50">
		<form:form method="POST" modelAttribute="ossanBean">
			<div class="row">
				<div class="col-sm-6 col-12">
					<div class="form-group">
						<label for="exampleInputEmail1">電子信箱</label>
						<form:input path="email" type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"
							placeholder="Enter email" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密碼</label>
						<form:input path="password" type="password" class="form-control"
							id="exampleInputPassword1" placeholder="Password" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密碼確認</label>
						<form:input path="passwordConfirm" type="password"
							class="form-control" id="exampleInputPassword1" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">姓名</label>
						<form:input path="name" type="text" class="form-control"
							id="exampleInputPassword1" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">暱稱</label>
						<form:input path="nickname" type="text" class="form-control"
							id="exampleInputPassword1" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">身分證字號</label>
						<form:input path="uniqueId" type="text" class="form-control"
							id="exampleInputPassword1" placeholder="A123456789" />
					</div>
				</div>


				<div class="col-sm-6 col-12">
					<div class="form-group">
						<label for="exampleInputPassword1">城市</label>
						<form:input path="city" type="text" class="form-control"
							id="exampleInputPassword1" placeholder="新北市" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">行政區</label>
						<form:input path="district" type="text" class="form-control"
							id="exampleInputPassword1" placeholder="新店區" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">地址</label>
						<form:input path="address" type="text" class="form-control"
							id="exampleInputPassword1" placeholder="北新路200號3樓" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">手機</label>
						<form:input path="phone" type="text" class="form-control"
							id="exampleInputPassword1" placeholder="0919388588" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">生日</label>
						<form:input path="birthday" type="date" class="form-control"
							id="exampleInputPassword1" />
					</div>
					<div class="form-group mt-sm-5 mt-0 ">
						<button type="submit"
							class="btn btn-block font-weight-bold border"
							style="background: #eee">Submit</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>

	<footer class="text-muted py-3 mt-5  bg-light">
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