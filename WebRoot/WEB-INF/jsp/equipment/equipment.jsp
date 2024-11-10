<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%= basePath %>">
    <title>设备管理</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script type="text/javascript" src="<%= path %>/static/js/lib/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript" src="<%= path %>/static/js/equipment/equipment.js"></script>
    <link rel="stylesheet" href="<%= path %>/static/css/equipment/table.css" >
    <link rel="stylesheet" href="<%= path %>/static/css/record/request_buy_record.css">
    <link rel="stylesheet" href="<%= path %>/static/css/equipment/equipment.css">
    <script type="text/javascript">
      var path = "<%= path %>";
      var labs = '${labs}';
    </script>
  </head>

  <body>
     <%@page language="java" import="java.util.*" pageEncoding="UTF-8"%><jsp:include page="../lab/header.jsp"></jsp:include>

    <div class="content">
      <!-- 导航条 -->
      <ol class="breadcrumb">
        <li><a href="<%= path %>/lab/toContent">主页</a></li>
        <li class="active">设备查询</li>
      </ol>

      <c:if test="${not empty tip }">
        <div class="alert alert-success" role="alert">
          ${tip }
        </div>
      </c:if>

      <div class="record-content">
        <div class="record-left">
          <span class="record-left-title">查询</span>
          <ul class="record-left-ul">
            <li class="li-list record-buy-record on"><span>设备</span></li>
          </ul>
          <span class="record-left-title">统计</span>
          <ul class="record-left-ul">
            <c:forEach items="${labs }" var="lab">
              <li class="li-list" onclick="changeUrlById('${lab.id}')"><span>${lab.name }</span></li>
            </c:forEach>
            <c:choose>
              <c:when test="${pagination.extra.labId eq '0' }">
                <li class="li-list" onclick="changeUrlById('${pagination.extra.labId}')"><span>全部</span></li>
              </c:when>
            </c:choose>
          </ul>
        </div>
        
        <div class="record-right">
          <div class="record-right-title">
            <span class="title-b pull-left"></span>
            <span class="title-content pull-left">设备列表</span>
            <form action="<%= path %>/equipment/list" method="get" id="searchForm">
              <input class="hidden" type="text" value="${pagination.currentPage }" name="page" />
              <input class="hidden" type="text" value="${pagination.orderBy }" name="orderBy" />
              <input class="hidden" type="text" value="${pagination.extra.labId }" name="labId" />
              <div class="input-group search-input">
                <input type="text" class="form-control" placeholder="Search key word" name="searchKeyWord" aria-describedby="basic-addon2" value="${pagination.searchKeyWord }">
                <span class="input-group-btn search-btn">
                  <button type="button" class="btn btn-info">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                  </button>
                </span>
              </div>
            </form>
          </div>
          <div class="record-right-body">

          <form method="post" action="<%= path %>/equipment/delete" id="equipmentDeletedForm">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>id</th>
                  <th>名称</th>
                  <th>单价</th>
                  <th>产地</th>
                  <th>创建时间</th>
                  <th>
                    <select class="lab-select" onchange="window.location = this.value">
                      <c:forEach items="${labs }" var="lab">
                        <c:choose>
                          <c:when test="${lab.id eq pagination.extra.labId }">
                           <option selected="selected">
                             ${lab.name }
                           </option>
                          </c:when>
                          <c:otherwise>
                            <option value="<%= path %>/equipment/list?searchKeyWord=${pagination.searchKeyWord }&page=${pagination.totalPage }&orderBy=${orderBy }&labId=${lab.id }">${lab.name }</option>
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>
                      <c:choose>
                        <c:when test="${pagination.extra.labId eq '0' }">
                          <option selected="selected"><span>全部</span></option>
                        </c:when>
                        <c:otherwise>
                          <option value="<%= path %>/equipment/list?searchKeyWord=${pagination.searchKeyWord }&page=${pagination.totalPage }&orderBy=${orderBy }&labId=${lab.id }">全部</option>
                        </c:otherwise>
                      </c:choose>
                    </select>
                  </th>
<!--                   <th>修改</th> -->
                  <th>删除</th>
                </tr>
              </thead>

              <tbody>
                <c:forEach items="${pagination.results }" var="equipment" varStatus="index">
                  <tr>
                    <th scope="row">${index.count }</th>
                    <td>${equipment.id }</td>
                    <td>${equipment.name }</td>
                    <td>${equipment.price }</td>
                    <td>${equipment.origin }</td>
                    <td><fmt:formatDate value="${equipment.createdAt }" pattern="yyyy-MM-dd" /></td>
                    <td>${equipment.lab.name }</td>
<!--                     <td> -->
<%--                       <a href="<%= path %>/equipment/list/${record.id }"> --%>
<%--                         <img src="<%= path %>/static/css/images/details.svg" class="detail-img" /> --%>
<!--                       </a> -->
<!--                     </td> -->
                    <td>
                      <input type="checkbox" value="${equipment.id }" class="hidden" name="deleted-checkboxes" id="checkbox-${equipment.id }" />
                      <label for="checkbox-${equipment.id }">
                        <img src="<%= path %>/static/css/images/check-box-outline-blank.svg" class="delete-img" id="img-${equipment.id }" onclick="checkDeleteBox('img-${equipment.id }', 'checkbox-${equipment.id }')" />
                      </label>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            </form>

	        <div class="form-inline text-right">
	          <c:if test="${fn:length(pagination.results) >= 0}">
                <button class="btn btn-warning delete-btn">删除</button>
              </c:if>
	        </div>

            <nav aria-label="Page navigation" class="pagination-nav text-center">
              <ul class="pagination">
                <c:if test="${pagination.currentPage > 1 }">
                  <li>
                    <a href="<%= path %>/equipment/list?searchKeyWord=${pagination.searchKeyWord }&page=1&orderBy=${orderBy }&labId=${pagination.extra.labId }" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li>
                    <a href="<%= path %>/equipment/list?searchKeyWord=${pagination.searchKeyWord }&page=${pagination.currentPage - 1 }&orderBy=${orderBy }&labId=${pagination.extra.labId }" aria-label="Previous">
                      <span aria-hidden="true">&lsaquo;</span>
                    </a>
                  </li>
                </c:if>

                <c:forEach items="${pagination.pagesList }" varStatus="i" var="pageIndex">
                  <c:choose>
                    <c:when test="${pageIndex eq pagination.currentPage}">
                      <li class="active"><a>${pageIndex }</a></li>
                    </c:when>
                    <c:otherwise>
                      <li><a href="<%= path %>/equipment/list?searchKeyWord=${pagination.searchKeyWord }&page=${pageIndex }&orderBy=${orderBy }&labId=${pagination.extra.labId }">${pageIndex }</a></li>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>

                <c:if test="${pagination.currentPage < pagination.totalPage}">
                  <li>
                    <a href="<%= path %>/equipment/list?searchKeyWord=${pagination.searchKeyWord }&page=${pagination.currentPage + 1 }&orderBy=${orderBy }&labId=${pagination.extra.labId }" aria-label="Previous">
                      <span aria-hidden="true">&rsaquo;</span>
                    </a>
                  </li>
                  <li>
                    <a href="<%= path %>/equipment/list?searchKeyWord=${pagination.searchKeyWord }&page=${pagination.totalPage }&orderBy=${orderBy }&labId=${pagination.extra.labId }" aria-label="Previous">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </c:if>
              </ul>
            </nav>
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
            <span class="modal-title" id="myModalLabel">警告</span>
          </div>
          <div class="modal-body">
            <p>您确定要删除这些设备吗？</p>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
            <button type="button" class="btn btn-primary confirm-delete-btn">确认删除</button>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
