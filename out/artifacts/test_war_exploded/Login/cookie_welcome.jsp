<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-09-24
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("name")){
                    out.write("欢迎回来" + cookie.getValue());
                    out.println("<br>");
                    out.println("页面将在3秒后进行跳转......");
                    response.setHeader("refresh","3;URL=/commodity");
                }
            }
        %>
    <a href="/cookieLogout">退出登录</a>
</body>
</html>
