<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單一故事</title>
<jsp:include page="top.jsp" />
</head>
<style>
body {
	font-family: Microsoft JhengHei;
}

.ossan {
	width: 300px;
	height: 300px;
}

.personal {
	margin-top: 250px;
	height: 50px;
}
.article{
 white-space:pre-line
}
</style>
<body>
	<!-- 文章 -->
	<div class="container w-75 mt-5 bg-light py-5">
		<div>
			<div class="row">
				<div class="col-8">
					<img src="<c:url value="/getArticlePicture/${ab.articleNo}" />" class="ossan mr-3 img-fluid img-thumbnail">
				</div>
				<div class="col-4 personal">
					<a class="text-primary h4" href="<c:url value="/personalPage?ossanId=${ab.ossanBean.ossanNo}" />">${ab.ossanBean.name}個人頁面</a>
				</div>
			</div>
			<div class="media-body article">
				<h3 class="my-3 mb-1">${ab.title}</h3>
				${ab.sContent}
			</div>
		</div>

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