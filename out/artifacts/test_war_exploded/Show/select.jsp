<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-19
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的购物车</title>
</head>
<body>

<style>

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

    .ifr{
        position: relative;
        z-index:1003;
        overflow:auto;
        border-width:0px;
    }

    html>body td{ font-size:15px;}

</style>

<script language="JavaScript" type="text/javascript" class="ifr">
</script>

<table border=0 cellpadding=0 cellspacing=0 style="width:100% ;height:100%">

    <tr>

        <td style="width:100%;" align="center" valign="middle"  >


            <table id="mytable" cellspacing="0" width="50%" weight="50%">
                <td>
                    <input type="text" id="button">
                    <input type="button" value="搜索" onclick="javascript:location.href='finding.jsp'">
                </td>
            </table>
        </td>
    </tr>

</table>

</body>
</html>
