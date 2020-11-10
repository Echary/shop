<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<input value="用户名搜索" ng-model="uss"/>
<input value="手机号搜索" ng-model="pss"/>
<select  ng-model="citys">
  <option value="">选择城市</option>
  <option>北京</option>
  <option>上海</option>
  <option>广州</option>
  <option>深圳</option>
</select>
<select  ng-model="zhuangtai">
  <option value="">选择状态</option>
  <option>已发货</option>
  <option>发货</option>
  <option>已收货</option>
</select>

<select  ng-model="startM" >
  <option value="">开始月份</option>
  <option ng-repeat="mm in mouth">{{mm}}</option>
</select>

<select  ng-model="endM" >
  <option value="">结束月份</option>
  <option ng-repeat="mm in mouth">{{mm}}</option>
</select>
<input type="button" value="查询" /><br />