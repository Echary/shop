<%@ page import="java.net.URLDecoder" %><%--
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
            boolean check = Boolean.parseBoolean(request.getParameter("check"));

            String cookie_username = request.getParameter("username");
            String cookie_password = request.getParameter("password");
            Cookie[] cookies = request.getCookies();

            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    cookie_username = cookie.getValue();
                    cookie_username = URLDecoder.decode(cookie_username, "UTF-8");
                }
                if ("userPassword".equals(cookie.getName())) {
                    cookie_password = cookie.getValue();
                }
            }

            if (check == true){
                session.setAttribute("loginUser", cookie_username);
                session.setAttribute("userPassword",cookie_password);

            } else{
                Cookie userCookie = new Cookie("username",cookie_username);
                Cookie passwordCookie = new Cookie("userPassword",cookie_password);

                session = request.getSession();
                session.setAttribute("loginUser", cookie_username);
                session.setAttribute("userPassword", cookie_password);

                userCookie.setPath(request.getContextPath());
                passwordCookie.setPath(request.getContextPath());

                response.addCookie(userCookie);
                response.addCookie(passwordCookie);
            }

            out.write("欢迎回来" + cookie_username);
            out.println("<br>");
            out.println("页面将在3秒后进行跳转......");
            response.setHeader("refresh", "3;URL=/commodity");
        %>

    <a href="/cookieLogout">退出登录</a>
</body>
</html>
