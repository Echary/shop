<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-09-25
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="regDo">
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
            <input type="button" value="注册" onclick="javascript:location.href='regDo.jsp'">
        </td>
    </tr>
    </table>
</form>
</body>
</html>
