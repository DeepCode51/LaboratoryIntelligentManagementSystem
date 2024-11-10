<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>申请购买设备</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="<%= path %>/static/css/equipment/request_buy_equipment.css" >
    <link rel="stylesheet" href="<%= path %>/static/css/equipment/table.css" >
    <script type="text/javascript" src="<%= path %>/static/js/lib/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript">
      var path = "<%= path %>";
      var labJson = '${labs}';
    </script>
  </head>
  
  <body>
    <jsp:include page="../lab/header.jsp"></jsp:include>
    
    <div class="content">
      <!-- 导航条 -->
      <ol class="breadcrumb">
        <li><a href="<%= path %>/lab/toContent">主页</a></li>
        <li class="active">申请购买设备</li>
      </ol>

      <div class="alert hidden" role="alert"></div>

      <table align="center" class="bordered buy-equipment-table">
        <tr>
          <td colspan="2" bgcolor="#0ccff2" class="text-left"><label style="font-size: 18px">负责人信息</label></td>
        </tr>
        <tr>
          <td bgcolor="#fff">
            <label>负责人工号</label>
          </td>
          <td bgcolor="#fff" class="td-input">
            <input type="text" name="charge_id" class="input-text" value="${USER.id }" disabled="disabled" />
          </td>
        </tr>
        <tr>
          <td bgcolor="#fff"><label>负责人姓名</label></td>
          <td bgcolor="#fff" class="td-input">
            <input type="text" name="charge_name" value="${USER.name }" class="input-text" disabled="disabled" />
          </td>
        </tr>
      </table>

      <table align="center" class="bordered buy-equipment-table">
        <tr>
          <td colspan="2" bgcolor="#0ccff2" class="text-left"><label style="font-size: 18px">申请表信息</label></td>
        </tr>
        <tr>
          <td bgcolor="#fff">
            <label>申请简要(*)</label>
          </td>
          <td bgcolor="#fff" class="td-input">
            <input type="text" class="input-text" id="title" value="" maxlength="20" placeholder="最长20个字符" />
          </td>
        </tr>
      </table>

      <div class="equipment-table">

      </div>

      <input type="text" value="1" class="hidden" id="equipment-index">

      <div class="table-add-div">
        <img src="<%= path %>/static/css/images/add.svg" class="btn-circle">
      </div>

      <div class="commit-div">
        <button class="btn btn-default">取消</button>
        <button class="btn btn-info btn-commit">提交</button>
      </div>
    </div>
    <script type="text/javascript" src="<%= path %>/static/js/equipment/request_buy_equipment.js"></script>
  </body>
</html>
