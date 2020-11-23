<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-09-25
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body align="center">
<table>
    <tr>
        <td>
            <font size="5">注册成功</font>
            out.println("页面将在3秒后进行跳转......");
            response.setHeader("refresh","3;URL=/commodity");
        </td>
</table>
</body>
</html>
