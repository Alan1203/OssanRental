<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購物車內容</title>
</head>
<jsp:include page="top.jsp" />
<style>
body {
	font-family: Microsoft JhengHei;
}
</style>
<script>
function modify(key, qty, index) {
	var x = "newQty" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='/updateItem/" + key + "/" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}
</script>
<body>
<form></form>
    <!-- 購物車內容 -->
    <div class="container mt-5">
    <c:if test="${empty cart}">
    	<div>
    		<h2 class="text-danger text-center">購物車內尚無商品</h2>
    	</div>
    </c:if>
     <c:if test="${!empty cart}">
        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">大叔名稱</th>
                    <th scope="col">價格(NT$/HR)</th>
                    <th scope="col">購買時數</th>
                    <th scope="col">單品金額</th>
                    <th scope="col">刪除</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach varStatus="vs" var="cart" items="${cart.content}">
                <tr>
                    <th scope="row">1</th>
                    <td>${cart.value.name}</td>
                    <td>${cart.value.price}</td>
                    <td><input id="newQty${vs.index}" type="text" onchange="modify(${cart.key},${cart.value.qty},${vs.index})" value="${cart.value.qty}"></td>
                    <td>${cart.value.price * cart.value.qty}</td>
              
                    <td><a role="button" href='<c:url value="/deleteItem/${cart.key}" />' class="w-50 btn btn-block text-light font-weight-bold border"
                        style="background: #b22222">刪除</a></td>
                </tr>
               </c:forEach>
            </tbody>
        </table>
        <div class="row  bg-light justify-content-end">
            <div class="col-2 bg-light">商品金額總計:</div>
            <div class="col-2 "> NT$<fmt:formatNumber value="${cart.subtotal}" pattern="#,###,###" />元 </div>
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-2 form-group text-center ">
                <a href='<c:url value="/" />' role="button" class="btn btn-block font-weight-bold border"
                    style="background: #eee">繼續購物</a>
            </div>
            <div class="col-2 form-group text-center ">
                <a href="<c:url value="/orderConfirm" />" role="button" class="btn btn-block font-weight-bold border"
                    style="background: #eee">前往結帳</a>
            </div>
            <div class="col-2 form-group text-center">
                <a href="<c:url value="/logout" />" role="button" class="btn btn-block font-weight-bold border"
                    style="background: #eee">放棄購物</a>
            </div>
        </div>
        </c:if>
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