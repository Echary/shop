<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-19
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>搜索结果</title>
</head>
<body>

<style>

    a.button2 {
        height: 30px;
        line-height: 30px;
        padding: 0 11px;
        background: #fff;
        border: 1px #26bbdb solid;
        border-radius: 3px;
        display: inline-block;
        text-decoration: none;
        font-size: 15px;
        outline: none;
        color: #4f6b72;
    }

    a.button {
        height: 21px;
        line-height: 21px;
        padding: 0 11px;
        background: #fff;
        border: 1px #26bbdb solid;
        border-radius: 3px;
        display: inline-block;
        text-decoration: none;
        font-size: 14px;
        outline: none;
        color: #4f6b72;
    }

    table{
        text-align: center;
    }

    body {
        font: normal 11px auto;
        color: #4f6b72;
        background: #E6EAE9;
    }

    a {
        color: #c75f3e;
    }

    #mytable {
        width: 700px;
        padding: 0;
        margin: 0;
    }

    caption {
        padding: 0 0 5px 0;
        width: 700px;
        font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        text-align: right;
    }

    th {
        font: bold 15px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #4f6b72;
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        border-top: 1px solid #C1DAD7;
        letter-spacing: 2px;
        text-transform: uppercase;
        text-align: center;
        padding: 6px 6px 6px 12px;
        background: #CAE8EA no-repeat;
    }

    th.nobg {
        border-top: 1px solid #C1DAD7;
        border-left: 1px solid #C1DAD7;
        border-right: 1px solid #C1DAD7;
        background: none;
        padding: 6px 6px 6px 12px;
        border-bottom: 1px solid #C1DAD7;
    }

    td {
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        background: #fff;
        font-size:11px;
        padding: 6px 6px 6px 12px;
        color: #4f6b72;
    }


    td.alt {
        background: #F5FAFA;
        color: #797268;
    }

    th.spec {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #fff no-repeat;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
    }

    th.specalt {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #f5fafa no-repeat;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #797268;
    }

    html>body td{ font-size:15px;}

</style>

<script language="JavaScript" type="text/javascript" class="ifr">
    function check(){
        <c:choose>

        <c:when test="${not empty sessionScope.loginUser }">
        check1()
        </c:when>

        <c:otherwise>
        check2();
        </c:otherwise>

        </c:choose>
    }

    function checking(){
        <c:set var="login" value="${login}"></c:set>

        <c:choose>

        <c:when test="${empty sessionScope.loginUser }">
        check2();
        </c:when>

        </c:choose>
    }

    function check1(){
        alert("添加成功")
    }

    function check2(){
        alert("请登录!")
    }
</script>


<table border=0 cellpadding=0 cellspacing=0 style="width:100% ;height:100%">

    <tr>

        <td style="width:100%;" align="center" valign="middle"  >


            <table id="mytable" cellspacing="0" width="50%" weight="50%">


                <tr>
                    <th colspan="6" class="nobg" >商品列表</th>
                </tr>
                <tr>
                    <th scope="col" class="nobg">商品编号</th>
                    <th scope="col" >商品名称</th>
                    <th scope="col" >商品价格</th>
                    <th scope="col" >商品库存</th>
                    <th scope="col" >商品类别</th>
                    <th scope="col" >操作</th>
                </tr>

                <c:set var="flag" value="1"></c:set>
                <c:set var="flag2" value="1"></c:set>
                <c:set var="key" value="${keySearch}"></c:set>


                <c:forEach items="${map2}" var="commodity">
                    <c:if test="${fn:indexOf(commodity.name,key) != -1 }">
                        <c:set var="flag" value="${flag + flag2}"></c:set>
                        <c:choose>
                            <c:when test="${flag%2 == 0}">
                                <tr>
                                    <th scope="row" class="specalt">${commodity.id}</th>
                                    <td class="alt">${commodity.name}</td>
                                    <td class="alt">${commodity.price}</td>
                                    <td class="alt">${commodity.stock}</td>
                                    <td class="alt">${commodity.type}</td>
                                    <td>
                                        <a href="commodity?method=add&id=${commodity.id}" class="button" onclick="return check()">加入购物车</a>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <th scope="row" class="spec">${commodity.id}</th>
                                    <td scope="col">${commodity.name}</td>
                                    <td scope="col">${commodity.price}</td>
                                    <td scope="col">${commodity.stock}</td>
                                    <td scope="col">${commodity.type}</td>
                                    <td>
                                        <a href="commodity?method=add&id=${commodity.id}" class="button" onclick="return check()">加入购物车</a>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </table>
            <br>
            <a href="/commodity" class="button2" style="width: 60px">返回</a>
            <a href="Show/select.jsp" class="button2">继续搜索</a>
            <a href="/commodity?method=findCar" class="button2" style="width: 60px" onclick="return checking()">购物车</a>
        </td>
    </tr>
</table>

</body>
</html>