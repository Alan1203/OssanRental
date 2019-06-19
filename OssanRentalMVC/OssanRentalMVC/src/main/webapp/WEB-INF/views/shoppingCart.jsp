<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車內容</title>
</head>
<style>
body {
	font-family: Microsoft JhengHei;
}
</style>
<body>
    <!-- 購物車內容 -->
    <div class="container mt-5">
        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">大叔名稱</th>
                    <th scope="col">價格</th>
                    <th scope="col">購買時數</th>
                    <th scope="col">刪除</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="cart" items="${cart}">
                <tr>
                    <th scope="row">1</th>
                    <td>${cart.value.name}</td>
                    <td>${cart.value.price}</td>
                    <td><input type="text" value="${cart.value.qty}"></td>
                    <td><a role="button" class="w-50 btn btn-block text-light font-weight-bold border"
                        style="background: #b22222">刪除</a></td>
                </tr>
               </c:forEach>
            </tbody>
        </table>
        <div class="row  bg-light justify-content-end">
            <div class="col-2 bg-light">商品金額總計:</div>
            <div class="col-2 "> NT$100000 </div>
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-2 form-group text-center ">
                <a role="button" class="btn btn-block font-weight-bold border"
                    style="background: #eee">繼續購物</a>
            </div>
            <div class="col-2 form-group text-center ">
                <a role="button" class="btn btn-block font-weight-bold border"
                    style="background: #eee">前往結帳</a>
            </div>
            <div class="col-2 form-group text-center">
                <a role="button" class="btn btn-block font-weight-bold border"
                    style="background: #eee">放棄購物</a>
            </div>
        </div>
    </div>

    <footer class="text-muted py-3 mt-5  bg-light fixed-bottom">
        <div class="container">
            <p class="float-right h3">
                <a href="#">Back to top</a>
            </p>
            <p>Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>

        </div>
    </footer>

</body>
</html>