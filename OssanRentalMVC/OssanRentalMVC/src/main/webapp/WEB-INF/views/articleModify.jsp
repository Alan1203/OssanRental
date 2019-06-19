<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章列表</title>
<jsp:include page="top.jsp" />
</head>
<style>
body {
	font-family: Microsoft JhengHei;
}
</style>
<body>

	<div class="container w-50 pt-4">

		<form:form method="POST" modelAttribute="article"
			enctype="multipart/form-data" cssClass="mx-auto my-2">
			<div class="form-group w-75 mx-auto py-2">
				<label class="text-center font-weight-bolder">文章標題</label>
				<form:input type="text" path="title" cssClass="form-control"
					placeholder="標題" required="required" />
			</div>
			<c:if test="${!empty article.articleNo }">
				<form:input type="hidden" path="articleNo" />
			</c:if>
			<div class="form-group w-75 mx-auto font-weight-bolder">
			<label class="text-center">文章內容</label>
			<form:textarea path="sContent" cssClass="form-control"
				placeholder="想說點什麼" required="required" rows="8"  />
			</div>
			<div class="form-group mx-auto w-75">
				 <label class="text-black-50"><small>點選上傳新圖片</small></label>
				<form:input cssClass="form-control-file" type="file"
				path="articleImg" />
			</div>
			<div class="form-group text-center w-75 mx-auto">
			<button type="submit"  style="background: #eee"  class="btn btn-block font-weight-bold border">發文</button>
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