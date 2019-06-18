<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顯示大叔資訊</title>
<jsp:include page="top.jsp" />
</head>
<body>
	<!-- 輪播圖 -->
	<div id="carouselExampleIndicators"
		class="carousel slide carousel-fade" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="${pageContext.request.contextPath}/images/6.jpg"
					class="d-block w-100" alt="...">
				<div class="carousel-caption py-5" style="bottom: 100px">
					<h1>出 租 ・ 大 叔</h1>
					<span></span>
					<p>偶爾，我們其實只需要一種</p>
					<p>沉穩的陪伴與傾聽</p>
					
				</div>
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/images/4.jpg"
					class="d-block w-100" alt="...">
				<div class="carousel-caption py-5" style="bottom: 100px">
					<h1>出 租 ・ 大 叔</h1>
					<span></span>
					<p>彷彿一切都在他的眼神裡</p>
					<p>所以，我也可以勇敢的往前走</p>
					
				</div>
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/images/9.jpg"
					class="d-block w-100" alt="...">
				<div class="carousel-caption py-5" style="bottom: 100px">
					<h1>出 租 ・ 大 叔</h1>
					<span></span>
					<p>情緒在角落時，我知道</p>
					<p>有一個聲音會無條件的支持我</p>
					
				</div>
			</div>
		</div>
	</div>

	<!-- 區域按鈕 -->
	<div class=" my-4 bg-light ">
		<div class="d-flex justify-content-between mx-auto" style="width:400px">
		<a class="btn btn-secondary text-light" href="<c:url value = '/?requestArea=twAll' />" role="button">回到列表</a>
		<a class="btn btn-secondary text-light" href="<c:url value = '/?requestArea=twNorth' />" role="button">北部</a>
		<a class="btn btn-secondary text-light" href="<c:url value = '/?requestArea=twMiddle' />" role="button">中部</a>
		<a class="btn btn-secondary text-light" href="<c:url value = '/?requestArea=twSouth' />" role="button">南部</a>
		<a class="btn btn-secondary text-light" href="<c:url value = '/?requestArea=twOther' />" role="button">其他</a>
		</div>
	</div>

	<!-- 大叔列表 -->
	<div class="container w-80">
		<div class="row ">
			<c:forEach var="Ossan" items="${OssanBean}">
				<div class="col-12 col-sm-4">
					<div class="card shadow rounded">
						<div class="bg-cover rounded"
							style="height: 50vh ;background-image: url('<c:url value="/getPicture/${Ossan.ossanNo}"/>')"></div>
						<div class="card-body">
							<h3 class="card-title">${Ossan.name}
								<small class="h6">${Ossan.nickname }</small>
							</h3>
							<p class="card-text">${Ossan.quote}</p>
							<a href="<c:url value = '/personalPage?ossanId=${Ossan.ossanNo}' />" class="btn btn-dark">個人頁面</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- 分頁 -->
	<div class="container-fluid bg-light mt-5">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center ">
				<c:if test="${pageNo > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/?pageNo=${pageNo - 1}' />"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:if test="${pageNo > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/?pageNo=1' />">1</a></li>
				</c:if>
				<c:if test="${pageNo <= totalPage && (pageNo-1) > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/?pageNo=${pageNo - 1}' />">${pageNo - 1}</a></li>
				</c:if>
				<li class="page-item"><a class="page-link text-dark"
					href="<c:url value = '/?pageNo=${pageNo}' />">${pageNo}</a></li>
				<c:if test="${(pageNo + 1) < totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/?pageNo=${pageNo + 1}' />">${pageNo + 1}</a></li>
				</c:if>
				<c:if test="${pageNo != totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/?pageNo=${totalPage}' />">${totalPage}</a></li>
				</c:if>
				<c:if test="${pageNo < totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/?pageNo=${pageNo + 1}' />"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
	
	<!-- 底部 -->
    <footer class="text-muted py-3">
        <div class="container">
            <p class="float-right h3">
                <a href="#">Back to top</a>
            </p>
            <p>Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>

        </div>
    </footer>
</body>
</html>