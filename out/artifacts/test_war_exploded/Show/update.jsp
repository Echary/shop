<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-19
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    a.button2 {
        height: 30px;
        line-height: 30px;
        padding: 0 11px;
        background: #fff;
        border: 1px #26bbdb solid;
        border-radius: 3px;
        display: inline-block;
        text-decoration: none;
        font-size: 15px;
        outline: none;
        color: #4f6b72;
    }
</style>

<body>
    <form action="/commodity" method="post">
        商品编号: <input type="text" name="id" value="${commodity.id}" readonly/><br>
        商品名称: <input type="text" name="name" value="${commodity.name}" readonly/><br>
        商品价格: <input type="text" name="price" value="${commodity.price}" readonly/><br>
        商品类别: <input type="text" name="type" value="${commodity.type}"/><br>
        购买数目: <input type="text" name="stock" value="${commodity.stock}"/><br>
        <a href="commodity?method=findCar" class="button2">修改</a>
    </form>
</body>
</html>
