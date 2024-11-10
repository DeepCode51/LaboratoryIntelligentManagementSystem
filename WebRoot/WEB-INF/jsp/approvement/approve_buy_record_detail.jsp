<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%= basePath %>">
    <title>记录审批</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script type="text/javascript" src="<%= path %>/static/js/lib/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%= path %>/static/css/approvement/request_buy_record_detail.css" >
    <link rel="stylesheet" href="<%= path %>/static/css/equipment/table.css" >
    <script type="text/javascript">
      var path = "<%= path %>";
    </script>
  </head>

  <body>
     <jsp:include page="../lab/header.jsp"></jsp:include>

    <div class="content">
      <!-- 导航条 -->
      <ol class="breadcrumb">
        <li><a href="<%= path %>/lab/toContent">主页</a></li>
        <li><a href="<%= path %>/approvement/m/buy-record">审批管理</a></li>
        <li><a href="<%= path %>/approvement/m/buy-record">购置审批</a></li>
        <li class="active">${record.id }</li>
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
            <span class="input-text">${record.requestedUser.id }</span>
          </td>
        </tr>
        <tr>
          <td bgcolor="#fff"><label>负责人姓名</label></td>
          <td bgcolor="#fff" class="td-input">
            <span class="input-text">${record.requestedUser.name }</span>
          </td>
        </tr>
      </table>

      <table align="center" class="bordered buy-equipment-table">
        <tr>
          <td colspan="2" bgcolor="#0ccff2" class="text-left"><label style="font-size: 18px">申请表相关信息</label></td>
        </tr>
        <tr>
          <td bgcolor="#fff">
            <label>申请日期</label>
          </td>
          <td bgcolor="#fff" class="td-input">
            <span class="input-text"><fmt:formatDate value="${record.createAt }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
            <input type="text" class="hidden record-id" value="${record.id }">
          </td>
        </tr>
        <tr>
          <td bgcolor="#fff"><label>申请简要</label></td>
          <td bgcolor="#fff" class="td-input">
            <span class="input-text">${record.title }</span>
          </td>
        </tr>
        <tr>
          <td bgcolor="#fff"><label>设备花费</label></td>
          <td bgcolor="#fff" class="td-input">
            <span class="input-text">${record.cost }</span>
          </td>
        </tr>
      </table>

      <c:forEach items="${record.equipments }" varStatus="pageIndex" var="equipment">

	      <table align="center" class="bordered buy-equipment-table">
	        <tr>
	          <td colspan="2" bgcolor="#0ccff2" class="text-left"><label style="font-size: 18px">${pageIndex.count }. ${equipment.name }</label></td>
	        </tr>
	        <tr>
	          <td bgcolor="#fff"><label>设备名称</label></td>
	          <td bgcolor="#fff" class="td-input">
	            <span class="input-text">${equipment.name }</span>
	          </td>
	        </tr>
	        <tr>
	          <td bgcolor="#fff"><label>设备数量</label></td>
	          <td bgcolor="#fff" class="td-input">
	            <span class="input-text">${equipment.count }</span>
	          </td>
	        </tr>
	        <tr>
	          <td bgcolor="#fff"><label>设备价格</label></td>
	          <td bgcolor="#fff" class="td-input">
	            <span class="input-text">${equipment.price }</span>
	          </td>
	        </tr>
	        <tr>
	          <td bgcolor="#fff"><label>设备所属实验室</label></td>
	          <td bgcolor="#fff" class="td-input">
	            <span class="input-text">${equipment.lab.name }</span>
	          </td>
	        </tr>
	      </table>

      </c:forEach>

      <div class="opeartion-btns text-center">
        <a class="btn btn-default btn-lg" href="<%= path %>/approvement/buy-record">返回</a>
        <button class="btn btn-warning btn-lg ml">拒绝</button>
        <button class="btn btn-success btn-lg ml agree-btn">同意</button>
      </div>
    </div>
    <script type="text/javascript" src="<%= path %>/static/js/approvement/approve_buy_record_detail.js"></script>
  </body>
</html>
