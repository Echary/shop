<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="$fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-19
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script language="JavaScript" type="text/javascript" class="ifr">

    function check(reg){
        var i=str.search(reg);
        return i;
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

                <c:forEach items="${map2}" var="commodity">

                    <c:set var="flag" value="${flag + flag2}"></c:set>

                    <c:choose>

                        <c:when test="${flag%2 == 0}">

                            <c:if test="${$fn:indexOf('commodity.name','') != -1}">

                            </c:if>

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

                </c:forEach>

            </table>

        </td>
    </tr>

</table>

</body>
</html>