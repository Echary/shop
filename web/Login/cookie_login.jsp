<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-09-24
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/cookieLogin" method="post">        <!--向服务器发出post请求-->
    <table>
        <tr>
            <td>
                用户名:
            </td>
            <td>
                <input type="text" name="username"/>
            </td>
        </tr>
        <tr>
            <td>
                密码:
            </td>
            <td>
                <input type="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" style="width:50px" value="登录">
            </td>
            <td>
                <input type="reset" style="width:50px" value="重置">
                <input type="button" style="width:50px" value="注册" onclick="javascript:location.href='register.jsp'">
            </td>
        </tr>
    </table>

</form>
</body>
</html>
