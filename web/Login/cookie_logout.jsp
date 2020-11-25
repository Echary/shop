<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-11-25
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.write("退出成功！");
    out.println("<br>");
    out.println("页面将在3秒后进行跳转......");
    response.setHeader("refresh","3;URL=/commodity");
%>
</body>
</html>
