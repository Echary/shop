<%@ page import="java.net.URLDecoder" %>
<%@ page import="service.superDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-12-09
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    boolean check = Boolean.parseBoolean(request.getParameter("check"));

    String superName = request.getParameter("superName");
    String superPassword = request.getParameter("superPassword");
    Cookie[] cookies = request.getCookies();

    for (Cookie cookie : cookies) {
        if ("superName".equals(cookie.getName())) {
            superName = cookie.getValue();
            superName = URLDecoder.decode(superName, "UTF-8");
        }
        if ("superPassword".equals(cookie.getName())) {
            superPassword = cookie.getValue();
        }
    }

    if (check == true){
        session.setAttribute("superUser", superName);
        session.setAttribute("superPassword", superPassword);

    } else{

        Cookie userCookie = new Cookie("superName",superName);
        Cookie passwordCookie = new Cookie("superPassword",superPassword);

        session = request.getSession();
        session.setAttribute("superUser", superName);
        session.setAttribute("superPassword", superPassword);

        userCookie.setPath(request.getContextPath());
        passwordCookie.setPath(request.getContextPath());

        response.addCookie(userCookie);
        response.addCookie(passwordCookie);
    }

    out.write("欢迎回来" + superName);
    out.println("<br>");
    out.println("页面将在3秒后进行跳转......");
    response.setHeader("refresh", "3;URL=/super");
%>
<a href="/superLogout">退出登录</a>
</body>
</html>
