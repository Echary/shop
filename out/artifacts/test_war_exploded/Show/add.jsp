<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-19
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/commodity" method="post">
        商品编号: <input type="text" name="id"><br>
        商品名称: <input type="text" name="name"><br>
        商品价格: <input type="text" name="price"><br>
        商品库存: <input type="text" name="stock"><br>
        商品类别: <input type="text" name="type"><br>
        <input type="submit" value="添加">
    </form>
</body>
</html>
