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
<script type="text/javascript">
function deleteArticle(n) {
	if (confirm("確定刪除此篇文章 ? ") ) {
		document.forms[0].action="<c:url value='/deleteArticle?artNo=" + n +"' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}

function modifyArticle(n) {
	console.log(n);
	if (confirm("確定修改此篇文章 ? ") ) {
		document.forms[0].action="<c:url value='/articleUpdate/"+n +" ' />" ;
		document.forms[0].method="GET";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
</script>
<style>
img {
	width: 200px;
	height: 200px;
	display: block;
	margin: auto 0px ;
	
}

.article {
	/* 	white-space: pre-line ; */
	
}

body {
	font-family: Microsoft JhengHei;
}


</style>
<body>
	<form></form>
	<!-- 文章列表 -->
	<div class=" container w-75 my-5 bg-light py-5">
		<ul class="list-unstyled">
			<c:if test="${empty articleBean}">
				<h3>尚未有文章</h3>
			</c:if>
			<li><div class="media-body my-auto text-right pb-3">
					<h5 class="mt-0 mb-1">
						<a href="<c:url value="/articleUpdate/0" />">發新文章</a>
					</h5>
				</div></li>
			<c:forEach var="ab" items="${articleBean}">
				<c:set var="string1" value="${ab.sContent}" />
				<c:set var="string2" value="${fn:substring(string1, 0, 100)}" />
				<li class="media"><img
					src="<c:url value="/getArticlePicture/${ab.articleNo}" />"
					class="mr-3 img-fluid img-thumbnail">
					<div class="media-body my-auto article">
						<h5 class="mt-0 mb-1">${ab.title}</h5>
						${string2}...<span><a
							href="<c:url value="/articleDetail/${ab.articleNo}" />"
							class="text--primary">繼續閱讀</a></span>

						<div class="d-flex flex-row-reverse ">
							<div>
								<button type="button" name="modify"
									onclick="modifyArticle(${ab.articleNo})" class="btn btn-link ">編輯</button>
							</div>
							<div>
								<button type="button" name="delete"
									onclick="deleteArticle(${ab.articleNo})" class="btn btn-link ">刪除</button>
							</div>

						</div>
					</div></li>
			</c:forEach>
		</ul>
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