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

	<!-- 大叔列表 -->
	<div class="container w-80 mt-5">
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
							<a href="<c:url value = '/removeOssan?ossanNo=${Ossan.ossanNo}&pageNo=${pageNo}' />" class="btn btn-dark">刪除</a>
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
						href="<c:url value = '/manageOssan?pageNo=${pageNo - 1}' />"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:if test="${pageNo > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/manageOssan?pageNo=1' />">1</a></li>
				</c:if>
				<c:if test="${pageNo <= totalPage && (pageNo-1) > 1}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/manageOssan?pageNo=${pageNo - 1}' />">${pageNo - 1}</a></li>
				</c:if>
				<li class="page-item"><a class="page-link text-dark"
					href="<c:url value = '/manageOssan?pageNo=${pageNo}' />">${pageNo}</a></li>
				<c:if test="${(pageNo + 1) < totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/manageOssan?pageNo=${pageNo + 1}' />">${pageNo + 1}</a></li>
				</c:if>
				<c:if test="${pageNo != totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/manageOssan?pageNo=${totalPage}' />">${totalPage}</a></li>
				</c:if>
				<c:if test="${pageNo < totalPage}">
					<li class="page-item"><a class="page-link text-dark"
						href="<c:url value = '/manageOssan?pageNo=${pageNo + 1}' />"
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