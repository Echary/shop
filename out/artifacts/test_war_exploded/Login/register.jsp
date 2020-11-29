<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-09-25
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script language="JavaScript" type="text/javascript">
    <c:set var = "exist" value = "${exist}"></c:set>

    <c:choose>

    <c:when test="${not empty exist}">
    check1();
    </c:when>

    </c:choose>

    function check1(){
        alert("用户已存在!")
    }
</script>

<form action="/register" method="post">
    <table>
    <tr>
        <td>
            用户名：
        </td>
        <td>
        <input type="text" name="userName"/>
        </td>
    </tr>
    <tr>
        <td>
            密码：
        </td>
        <td>
            <input type="password" name="password"/>
        </td>
    </tr>
    <tr>
        <td>
            年 龄：
        </td>
        <td>
            <input type="age" name="age">
        </td>
    </tr>
    <tr>
        <td>
            性 别：
        </td>
        <td>
            <select name="sex">
                <option>男</option>
                <option>女</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            是否同意协议:

        </td>
        <td>
            是<input type="radio" name="treaty" value="yes"checked />
            否<input type="radio" name="treaty" value="no"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" value="注册">
        </td>
    </tr>
    </table>
</form>
</body>
</html>
