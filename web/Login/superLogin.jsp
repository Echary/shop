<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-19
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="number" uri="http://number.com" %>
<html>
<head>
    <title>商品列表</title>
</head>
<body>

<style>
    input.button2 {
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
        border-top: 2px solid #C1DAD7;
        border-left: 2px solid #C1DAD7;
        border-right: 2px solid #C1DAD7;
        background: none;
        padding: 6px 6px 6px 12px;
        border-bottom: 2px solid #C1DAD7;
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

    td.temp{
        border-top: 0px solid;
        border-left: 0px solid ;
        border-right: 0px solid ;
        background: none;
        padding: 6px 6px 6px 12px;
        border-bottom: 0px solid ;
    }

    .ifr{
        position: relative;
        z-index:1003;
        overflow:auto;
        border-width:0px;
    }

    html>body td{ font-size:15px;}

</style>

<table border=0 cellpadding=0 cellspacing=0 style="width:100% ;height:100%">
    <number:count/>
    <tr>

        <td style="width:100%;" align="center" valign="middle"  >


            <table id="mytable" cellspacing="0" width="50%" weight="50%">
                <form action="/superLogin" method="get">        <!--向服务器发出post请求-->
                    <table>

                        <tr>
                            <th scope="col">
                                管理员:
                            </th>

                            <td class="nobg">
                                <input type="text" name="superName" />
                            </td>
                        </tr>

                        <tr>
                            <th scope="col">
                                密码:
                            </th>

                            <td class="nobg">
                                <input type="password" name="superPassword"/>
                            </td>
                        </tr>

                        <br>

                        <tr>
                            <td class="ifr" colspan="2">
                                <input type="submit" style="width:50px" value="登录" class="button2">
                            </td>


                        </tr>



                    </table>
                </form>
            </table>

        </td>
    </tr>

</table>

</body>
</html>
