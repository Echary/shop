<%@ page import="entity.Commodity" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-19
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="total" uri="http://total.com" %>
<html>
  <head>
    <title>我的购物车</title>
  </head>
  <body>

  <style>

      td.temp{
          border-right: 0px;
          border-bottom: 0px;
          background: #fff;
          font-size:11px;
          padding: 6px 6px 6px 12px;
          color: #4f6b72;
      }

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
          width: 10px;
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
          text-align: center;
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

  <script language="JavaScript" type="text/javascript">

function Check(){
<c:set var = "map" value = "${map}"></c:set>
<c:choose>
    <c:when test="${empty map}">
        check1();
    </c:when>

    <c:otherwise>
        check2()
    </c:otherwise>

    </c:choose>
}

function check1(){
    alert("购物车为空!")
}

function check2(){
        var cof = window.confirm("确认结算吗?");
        if (cof == true){
            alert("结算成功!")
            list()
        }
    }

function list(){
    window.location.href="commodity?method=clean";
}

  </script>

  <table border=0 cellpadding=0 cellspacing=0 style="width:100% ;height:100%">

      <tr>

          <td style="width:100%;" align="center" valign="middle"  >


              <table id="mytable" cellspacing="0" width="50%" weight="50%">

                  <tr>
                      <th colspan="7" class="nobg" >我的购物车</th>
                  </tr>
                  <tr>
                      <th scope="col" class="nobg">商品编号</th>
                      <th scope="col" >商品名称</th>
                      <th scope="col" >商品类别</th>
                      <th scope="col" >商品价格</th>
                      <th scope="col" >购买数目</th>
                      <th scope="col" >小结</th>
                      <th scope="col" >操作</th>
                  </tr>

                  <c:set var="flag" value="1"></c:set>
                  <c:set var="flag2" value="1"></c:set>
                  <c:set var="total" value="0"></c:set>

                  <c:forEach  items="${map}" var="map">

                      <c:set var="flag" value="${flag + flag2}"></c:set>

                      <c:choose>

                          <c:when test="${flag%2 == 0}">
                              <c:set var="temp" value="0"></c:set>
                              <tr>
                                  <th scope="row" class="specalt">${map.id}</th>
                                  <td class="alt">${map.name}</td>
                                  <td class="alt">${map.type}</td>
                                  <td class="alt">${map.price}</td>
                                  <td class="alt">
                                      <a href="commodity?method=reduce&id=${map.id}" class="button">-</a>
                                          ${map.amount}
                                      <a href="commodity?method=addAmount&id=${map.id}" class="button">+</a>
                                  </td>
                                  <c:set var="temp" value="${map.price * map.amount}"></c:set>
                                  <td scope="col">${temp}</td>
                                  <td class="alt">
                                      <a href="commodity?method=delete&id=${map.id}" class="button2">删除</a>
                                  </td>

                              </tr>
                          </c:when>
                          <c:otherwise>
                              <c:set var="temp" value="0"></c:set>
                              <tr>
                                  <th scope="row" class="spec">${map.id}</th>
                                  <td scope="col">${map.name}</td>
                                  <td scope="col">${map.type}</td>
                                  <td scope="col">${map.price}</td>
                                  <td scope="col">
                                      <a type="button" href="commodity?method=reduce&id=${map.id}" class="button">-</a>
                                          ${map.amount}
                                      <a type="button" href="commodity?method=addAmount&id=${map.id} " class="button" >+</a>
                                  </td>
                                  <c:set var="temp" value="${map.price * map.amount}"></c:set>
                                  <td scope="col">${temp}</td>
                                  <td class="alt">
                                      <a href="commodity?method=delete&id=${map.id}" class="button2">删除</a>
                                  </td>

                              </tr>
                          </c:otherwise>

                      </c:choose>

                  </c:forEach>
                  <tr>
                      <total:consumer/>
                      <%--<th class="nobg" colspan="7">总价:${total}</th>--%>
                  </tr>

              </table>
          <br>
              <a href="/commodity" class="button2">返回</a>
              <a href="/commodity?method=clean" class="button2">清空</a>
              <a class="button2" onclick="return Check()">结算</a>

          </td>
      </tr>

      </table>
  </body>
</html>
