<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="../../../js/angular/angular.js"></script>
    <script>
        var app = angular.module("myApp", []);
        app.controller("myCtrl", function($scope) {
            $scope.shops = [{
                id: 80,
                name: "iPhone",
                price: 5400,
                num:10,
                state: false
            }, {
                id: 1200,
                name: "ipad mini",
                price: 2200,
                num:20,
                state: false
            }, {
                id: 500,
                name: "ipad air",
                price: 2340,
                num:30,
                state: false
            }, {
                id: 29,
                name: "ipad",
                price: 1420,
                num:40,
                state: false
            }, {
                id: 910,
                name: "imac",
                price: 6600,
                num:50,
                state: false
            }];

            //下拉菜单排序
            $scope.orderSel = "";

            /*$scope.de=function(index){
             //删除当前项
             $scope.shops.splice(index,1);
            }*/

            $scope.de = function(name, state) {
                if(state) {
                    if(window.confirm("确定要删除" + name + "吗？")) {
                        for(index in $scope.shops) {
                            if(name == $scope.shops[index].name) {
                                $scope.shops.splice(index, 1);
                            }
                        }
                    }
                }else{
                    alert("请先选择");
                }
            }
            //修改价格
            $scope.update = function(shop){
                var newPrice = window.prompt("请输入要修改的"+shop.name+"商品的价格",shop.price);
                if(newPrice == null || newPrice == ""){
                    alert("商品价格不能为空")
                }else if(isNaN(newPrice)){
                    alert("商品价格必须是数字")
                }else{
                    shop.price = parseInt(newPrice);
                }
            }
            //增加数量
            $scope.add = function(shop){
                shop.num++;
            }
            //减少数量
            $scope.less = function(shop){
                if(shop.num>=1){//商品数量大于1
                    shop.num--;
                }else{

                }
            }

            //全选、全不选
            $scope.selectAll = false;
            $scope.selectAllFun = function() {
                if($scope.selectAll) {
                    for(index in $scope.shops) {
                        $scope.shops[index].state = true;
                    }
                } else {
                    for(index in $scope.shops) {
                        $scope.shops[index].state = false;
                    }
                }
            }

            //反选
            $scope.checkSelAll = function() {
                var flag = false;
                for(index in $scope.shops) {
                    if(!$scope.shops[index].state) {
                        //满足条件
                        flag = true;
                    }
                }

                if(flag) {
                    $scope.selectAll = false;
                } else {
                    $scope.selectAll = true;
                }
            }

            //批量删除
            $scope.delSelect = function() {
                var selArr = [];
                for(index in $scope.shops) {
                    if($scope.shops[index].state) {
                        selArr.push($scope.shops[index].name)
                    }
                }

                if(selArr.length <= 0) {
                    alert("请先选择");
                } else {
                    if(window.confirm("确定要删除吗？")) {
                        for(index1 in selArr) {
                            for(index2 in $scope.shops) {
                                if(selArr[index1] == $scope.shops[index2].name) {
                                    $scope.shops.splice(index2, 1);
                                }
                            }
                        }
                    } else {

                    }
                }
            }

            //添加商品
            $scope.isShow = false;
            $scope.isShow2 = false;
            $scope.showForm = function() {
                if($scope.isShow) {
                    $scope.isShow = false;
                } else {
                    $scope.isShow = true;
                }
            }
            //提交按钮
            $scope.submit = function() {
                $scope.errorArr = [];
                //判断id是否为空
                if($scope.newId == null || $scope.newId == "") {
                    $scope.errorArr.push("ID不能为空");
                } else if(isNaN($scope.newId)) {
                    $scope.errorArr.push("ID必须是数字");
                }
                if($scope.newName == null || $scope.newName == "") {
                    $scope.errorArr.push("产品名称不能为空");
                } else {
                    for(index in $scope.shops) {
                        if($scope.shops[index].name == $scope.newName) {
                            $scope.errorArr.push("产品名称不能重复");
                        }
                    }
                }
                if($scope.newPrice == null || $scope.newPrice == "") {
                    $scope.errorArr.push("价格不能为空");
                } else if(isNaN($scope.newPrice)) {
                    $scope.errorArr.push("价格必须是数字");
                }

                if($scope.errorArr.length > 0) {
                    //显示列表
                    $scope.isShow2 = true;
                } else {
                    $scope.isShow2 = false;
                    //添加商品
                    var newShop = {
                        id: parseInt($scope.newId),
                        name: $scope.newName,
                        price: parseInt($scope.newPrice),
                        num:1,
                        state: false
                    };
                    $scope.shops.push(newShop);
                    $scope.isShow = false;
                }
            }
        })
    </script>
</head>

<body ng-app="myApp" ng-controller="myCtrl">
<center>
    <h3>商品列表</h3>
    <input type="text" placeholder="商品名称" ng-model="search" />
    <select ng-model="orderSel">
        <option value="">--请选择--</option>
        <option value="id">--id正序--</option>
        <option value="-id">--id逆序--</option>
        <option value="price">--价格正序--</option>
        <option value="-price">--价格逆序--</option>
    </select>
    <button ng-click="delSelect()">批量删除</button>
    <br /><br />

    <table border="1px solid blue;" cellpadding="10" cellspacing="0">
        <thead>
        <tr>
            <th><input type="checkbox" ng-model="selectAll" ng-click="selectAllFun()" /> </th>
            <th>产品编号</th>
            <th>产品名称</th>
            <th>产品价格</th>
            <th>产品数量</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="shop in shops | filter:{name:search} | orderBy:orderSel">
            <td><input type="checkbox" ng-model="shop.state" ng-click="checkSelAll()" /></td>
            <td>{{shop.id}}</td>
            <td>{{shop.name}}</td>
            <td>{{shop.price}}</td>
            <td>
                <button ng-click="less(shop)">-</button>
                <input type="text" ng-model="shop.num" size="1"/>
                <button ng-click="add(shop)">+</button>
            </td>
            <td width="100px">
                <button ng-click="de(shop.name,shop.state)">删除</button>
                <button ng-click="update(shop)">修改</button>
            </td>
        </tr>
        </tbody>
    </table><br />
    <button ng-click="showForm()">添加商品</button><br /><br />

    <fieldset ng-show="isShow" id="" style="width: 400px;">
        <legend>添加商品</legend><br />
        <form>
            产品编号：<input type="text" ng-model="newId" /><br /><br /> 产品名称：
            <input type="text" ng-model="newName" /><br /><br /> 产品价格：
            <input type="text" ng-model="newPrice" /><br /><br />

            <ul ng-show="isShow2" style="width: 200px; background-color: #f89;">
                <li ng-repeat="error in errorArr">{{error}}</li>
            </ul>

            <input ng-click="submit()" type="button" value="添加" />
        </form>

    </fieldset>

</center>
</body>

</html>