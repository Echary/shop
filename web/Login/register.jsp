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
        history.back();
    }
</script>

<form action="/register" method="post">
    <table>
    <tr>
        <td>
            用户名：
        </td>
        <td>
        <input type="text" name="userName" id="userName"/><span id="msg"></span>
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
<script>
    window.onload=function(){
        //1.获取要判断的文本框
        var nameElenment  = document.getElementById("userName");

        //2.绑定失去焦点事件
        nameElenment.onblur=function(){
            //3.获取用户输入的值
            var username = this.value;

            //4.获取XMLHttprequset
            var xhr =new XMLHttpRequest();

            //5.编写回调函数

            xhr.onreadystatechange=function(){
                //判断是否一切正常
                if(xhr.readyState==4){
                    if(xhr.status==200){
                        var msg = document.getElementById("msg");
                        if(xhr.responseText==1){
                            msg.innerHTML="<font color='red' size='2'> <b> 用户名已存在</b> </font>"
                        }
                        else if(xhr.responseText==2){
                            msg.innerHTML="<font color='green'>  ✔</font>"
                        }
                        else if(xhr.responseText==0){
                            msg.innerHTML="<font></font>"
                        }
                    }
                }
            }
            xhr.open("GET","${pageContext.request.contextPath}/Check_username?username="+username);

            xhr.send();
        }

    }

</script>
</html>
