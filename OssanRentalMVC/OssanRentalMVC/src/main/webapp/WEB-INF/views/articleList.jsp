<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章列表</title>
<jsp:include page="top.jsp" />
</head>
<style>
img {
	width: 200px;
	height: 200px;
	display: block;
	margin: auto 0px ;
	
}

.article{
 white-space:pre-line
}
</style>
<body>
	<!-- 文章列表 -->
	<div class=" container w-75 mt-5 bg-light py-5">
		<ul class="list-unstyled">
			<c:forEach var="ab" items="${articleBean}">
				<c:set var="string1" value="${ab.sContent}" />
				<c:set var="string2" value="${fn:substring(string1, 0, 100)}" />
				<li class="media"><img src="<c:url value="/getArticlePicture/${ab.articleNo}" />"
					class="mr-3 img-fluid img-thumbnail">
					<div class="media-body my-auto article">
						<h5 class="mt-0 mb-1">${ab.title}</h5>
						${string2}...<span><a href="<c:url value="/articleDetail/${ab.articleNo}" />" class="text--primary">繼續閱讀</a></span>
						<footer class="blockquote-footer">出自--${ab.ossanBean.nickname} <cite title="Source Title"> ${ab.ossanBean.name}</cite></footer>
					</div></li>
			</c:forEach>
		</ul>
	</div>

	<!-- 分頁 -->
	<div class="container-fluid bg-light mt-5">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center ">
				<c:if test="${pageNo > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/articleList?pageNo=${pageNo - 1}' />"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:if test="${pageNo > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/articleList?pageNo=1' />">1</a></li>
				</c:if>
				<c:if test="${pageNo <= totalPage && (pageNo-1) > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/articleList?pageNo=${pageNo - 1}' />">${pageNo - 1}</a></li>
				</c:if>
				<li class="page-item"><a class="page-link text-dark"
					href="<c:url value = '/articleList?pageNo=${pageNo}' />">${pageNo}</a></li>
				<c:if test="${(pageNo + 1) < totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/articleList?pageNo=${pageNo + 1}' />">${pageNo + 1}</a></li>
				</c:if>
				<c:if test="${pageNo != totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/articleList?pageNo=${totalPage}' />">${totalPage}</a></li>
				</c:if>
				<c:if test="${pageNo < totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/articleList?pageNo=${pageNo + 1}' />"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</nav>
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