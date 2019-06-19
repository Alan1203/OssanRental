<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>大叔頁面</title>
<jsp:include page="top.jsp" />
</head>
<body>
	<!-- 個人頁面 -->
	<div class="person container mt-5">
		<form:form method="POST" modelAttribute="OssanBean"
			enctype="multipart/form-data">
			<div class="row mt-5">
				<div class="col-sm-6 col-12 h-auto d-inline-block">

					<img src="<c:url value="/getPicture/${OssanBean.ossanNo}" />"
						class="img-fluid img-thumbnail">
					<div class="form-group">
						<label class="text-black-50"><small>點選上傳新頭貼</small></label>
						<form:input path="ossanImg" type="file" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="ossanNo" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="password" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="birthday" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="uniqueId" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="registerTime" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="fileName" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="privilege" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="twNorth" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="twMiddle" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="twSouth" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
							<form:input path="twOther" type="hidden" class="form-control-file"
							id="exampleFormControlFile1" />
					</div>
					<h4 class="mt-5">格言</h4>
					<div class="quote">
						<form:input type="text" class="w-100" path="quote" />
					</div>
					<h4 class="mt-5">自我介紹</h4>
					<div class="intro">
						<form:textarea path="sIntro" class="w-100" style="height: 500px" />
					</div>

				</div>

				<div class="col-sm-6 col-12">

					<div class="form-group">
						<label for="exampleInputEmail1">電子信箱</label>
						<form:input type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" path="email" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">姓名</label>
						<form:input type="text" class="form-control"
							id="exampleInputPassword1" path="name" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">暱稱</label>
						<form:input type="text" class="form-control"
							id="exampleInputPassword1" path="nickname" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">城市</label>
						<form:input type="text" class="form-control"
							id="exampleInputPassword1" path="city" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">行政區</label>
						<form:input type="text" class="form-control"
							id="exampleInputPassword1" path="district" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">地址</label>
						<form:input type="text" class="form-control"
							id="exampleInputPassword1" path="address" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">手機</label>
						<form:input type="text" class="form-control"
							id="exampleInputPassword1" path="phone" />
					</div>
					<div class="form-group mt-sm-5 mt-0 ">
						<button type="submit"
							class="btn btn-block font-weight-bold border"
							style="background: #eee">確認修改</button>
					</div>

				</div>

			</div>
		</form:form>
	</div>

	<footer class="text-muted py-3  bg-light">
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