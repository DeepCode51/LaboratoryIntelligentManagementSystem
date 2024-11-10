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
    <title>实验室信息</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script type="text/javascript" src="<%= path %>/static/js/lib/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript" src="<%= path %>/static/js/lib/echarts.simple.min.js"></script>
    <link rel="stylesheet" href="<%= path %>/static/css/equipment/table.css" >
    <link rel="stylesheet" href="<%= path %>/static/css/record/request_buy_record.css">
    <link rel="stylesheet" href="<%= path %>/static/css/lab/lab_info.css">
    <script type="text/javascript">
      var path = "<%= path %>";
      var labs = '${labInfo.labs }';
      var labEquipNames = "${labInfo.names }";
      var labEquipCounts = "${labInfo.counts }";
      var currentLabId = '${labInfo.labId}';
    </script>
    <script type="text/javascript" src="<%= path %>/static/js/lab/lab_info.js"></script>
  </head>

  <body>
     <jsp:include page="../lab/header.jsp"></jsp:include>

    <div class="content">
      <!-- 导航条 -->
      <ol class="breadcrumb">
        <li><a href="<%= path %>/lab/toContent">主页</a></li>
        <li class="active">设备查询</li>
      </ol>

      <div class="record-content">
        <div class="record-left">
          <span class="record-left-title">查询</span>
          <ul class="record-left-ul">
            <li class="li-list record-buy-record" onclick="toEquipmentUrl()"><span>设备</span></li>
          </ul>
          <span class="record-left-title">统计</span>
          <ul class="record-left-ul">
            <c:forEach items="${labInfo.labs }" var="labIndex">
              <c:choose>
                <c:when test="${labIndex.id eq labInfo.labId }">
                  <li class="li-list on"><span>${labIndex.name }</span></li>
                </c:when>
                <c:otherwise>
                  <li class="li-list" onclick="changeUrlById('${labIndex.id}')">
                    <span>${labIndex.name }</span>
                  </li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
              <c:when test="${labInfo.labId eq '0' }">
                <li class="li-list on"><span>全部</span></li>
              </c:when>
              <c:otherwise>
                <li class="li-list" onclick="changeUrlById('${labIndex.id}')">
                 <span>全部</span>
                </li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div>
        
        <div class="record-right">
          <div class="record-right-title">
            <span class="title-b pull-left"></span>
            <span class="title-content pull-left">设备列表</span>
          </div>
          <div class="record-right-body">
            <c:if test="${labInfo.labId ne '0' }">
              <div class="lab-info">
	            <div class="lab-info-title">
                  <div class="info rm-margin-top">实验室信息</div>
                </div>
                <div class="lab-info-body">
                  <div class="lab-name">
                    <span class="dt">名称:</span>
                    <span class="da">${labInfo.lab.name }</span>
                  </div>
                  <div class="lab-user-info">
                    <span class="dt">负责人工号:</span>
                    <span class="da">${labInfo.lab.manager.id }</span>
                    <span class="dt mt">负责人姓名:</span>
                    <span class="da">${labInfo.lab.manager.name }</span>
                  </div>
                  <div class="lab-brief">
                    <span class="dt">实验室简介:</span>
                    <span class="db">${labInfo.lab.brief }</span>
                  </div>
                </div>
              </div>
            </c:if>

            <div class="lab-date-info">
              <div class="lab-info-title">
                <div class="info">设备 <span class="da num">${labInfo.totalCount }</span></div>
                <div class="lab-date-body" id="lab-date-body">

                </div>
              </div>
            </div>
          </div>

        </div>

      </div>
    </div>
      
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <span class="title-b pull-left"></span>
            <span class="modal-title" id="myModalLabel">Modal title</span>
          </div>
          <div class="modal-body">
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
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
