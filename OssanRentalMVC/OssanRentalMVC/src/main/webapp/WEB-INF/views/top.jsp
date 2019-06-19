<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

</head>

<style>
body {
	font-family: Microsoft JhengHei;
}

.box {
	border: 1px red solid;
}

.bg-cover {
	background-size: cover;
	background-position: center center;
}
</style>


<body>
	<c:if test="${empty LoginOK && empty AdminLoginOK}">
		<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top"
			style="border-bottom: 2px rgba(26, 8, 8, 0.699) solid">
			<div class="container-fluid">
				<a class="navbar-brand" href='<c:url value="/" />'>首頁</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNav" aria-controls="navbarNav"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav mr-auto ">
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="#">網站理念</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="#">聯絡我們</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/articleList" />">大叔故事</a></li>
					</ul>
					<ul class="navbar-nav ml-auto ">
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/register" />">大叔註冊 </a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/login" />">大叔登入 </a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href='<c:url value="/cartContent" />'><img
								src="${pageContext.request.contextPath}/images/shopping-cart.png"></a></li>
					</ul>
				</div>
			</div>
		</nav>
	</c:if>

	<c:if test="${!empty LoginOK}">
		<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top"
			style="border-bottom: 2px rgba(26, 8, 8, 0.699) solid">
			<div class="container-fluid ">
				<a class="navbar-brand" href='<c:url value="/" />'>首頁</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNav" aria-controls="navbarNav"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav mr-auto ">
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="#">網站理念</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="#">聯絡我們</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/articleList" />">大叔故事</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/articleConfirm" />">個人故事管理</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/personalUpdate" />">個人頁面</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="">個人訂單紀錄</a></li>
						
					</ul>
					<ul class="navbar-nav ml-auto ">
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/logout" />">大叔登出</a></li>
						<li class="nav-item px-2 nav-link text-dark">${LoginOK.name}
							您好!</li>
						
					</ul>
				</div>
			</div>
		</nav>
	</c:if>

	<c:if test="${!empty AdminLoginOK}">
		<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top"
			style="border-bottom: 2px rgba(26, 8, 8, 0.699) solid">
			<div class="container-fluid ">
				<a class="navbar-brand" href='<c:url value="/" />'>首頁</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNav" aria-controls="navbarNav"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav mr-auto ">
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="#">全部故事管理</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="#">全部大叔管理</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="/articleList" />">大叔故事</a></li>
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="#">全部訂單紀錄</a></li>
					</ul>
					<ul class="navbar-nav ml-auto ">
						<li class="nav-item px-2"><a class="nav-link text-dark"
							href="<c:url value="logout" />">管理員登出</a></li>
						<li class="nav-item px-2 nav-link text-dark">管理員 您好!</li>
					</ul>
				</div>
			</div>
		</nav>
	</c:if>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>


