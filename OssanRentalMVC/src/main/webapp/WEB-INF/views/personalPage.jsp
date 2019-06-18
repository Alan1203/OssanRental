<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>大叔頁面</title>
<jsp:include page="top.jsp" />
</head>
<style>
body {
	font-family: Microsoft JhengHei;
}


.intro {
	border-top: 1px solid #ddd;
	padding: 5px;
}

.article {
	border-top: 1px solid #ddd;
}

.price {
	margin-bottom: 100px;
}

.intro {
	white-space: pre-line
}
</style>
<body>
	<!-- 個人頁面 -->
	<div class="person container mt-5 mb-2">
		<div class="row mt-5">
			<div class="col-sm-6 col-12 h-25">
				<img src='<c:url value="/getPicture/${OssanBean.ossanNo}" />'
					class="img-fluid img-thumbnail">
				<h4 class="mt-5">自我介紹</h4>
				<div class="intro">${OssanBean.sIntro}</div>
			</div>
			<div class="col-sm-6 col-12">
				<div class="name w-75 mx-auto">
					<h3>
						${OssanBean.name} <small class="text-muted">${OssanBean.nickname}</small>
					</h3>
					<p class="h6">${OssanBean.quote}</p>
				</div>
				<div class="price w-75 mx-auto mt-4 ">
					<p class="h4 text-danger font-weight-bold">NT$300/hr</p>
					<form action="<c:url value="/addToCart/${OssanBean.ossanNo}" />">
					<select class="form-control w-50" name="hours" id="exampleFormControlSelect1">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
					</select> <button type="submit"
						class="btn btn-secondary btn-lg btn-block mt-5">加入購物車</button>
					</form>
				</div>
				<h4 class="mt-5">大叔文章</h4>
				<!-- 文章列表 -->
				<div class="article">
					<c:if test="${empty articleBean}">
						<h2>目前尚無文章</h2>
					</c:if>
					<c:if test="${!empty articleBean}">
						<c:forEach var="ab" items="${articleBean}">
							<c:set var="string1" value="${ab.sContent}" />
							<c:set var="string2" value="${fn:substring(string1, 0, 50)}" />
							<div class="media mt-2">
								<img src="<c:url value="/getArticlePicture/${ab.articleNo}" />"
									class="mr-3 img-fluid img-thumbnail" width="150px"
									height="150px">
								<div class="media-body">
									<h5 class="mt-0">${ab.title}</h5>
									${string2}...<span><a
										href="<c:url value="/articleDetail/${ab.articleNo}" />"
										class="text--primary">繼續閱讀</a></span>

								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
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