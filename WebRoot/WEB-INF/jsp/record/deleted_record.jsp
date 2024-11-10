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
    <base href="<%=basePath%>">
    <title>记录查询</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script type="text/javascript" src="<%= path %>/static/js/lib/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%= path %>/static/css/record/request_buy_record.css">
    <script type="text/javascript" src="<%= path %>/static/js/record/buy-record.js"></script>
    <script type="text/javascript">
      var path = "<%= path %>";
      var labs = '${labs}';
    </script>
  </head>

  <body>
     <jsp:include page="../lab/header.jsp"></jsp:include>

    <div class="content">
      <!-- 导航条 -->
      <ol class="breadcrumb">
        <li><a href="<%= path %>/lab/toContent">主页</a></li>
        <li class="active">记录查询</li>
      </ol>

      <div class="record-content">
        <div class="record-left">
          <span class="record-left-title">记录查询</span>
          <ul class="record-left-ul">
            <li class="li-list" onclick="toBuyRecord()"><span>购置记录</span></li>
            <li class="li-list on"><span>删除记录</span></li>
          </ul>
        </div>
        <div class="record-right">
          <div class="record-right-title">
            <span class="title-b pull-left"></span>
            <span class="title-content pull-left">记录</span>
            <form action="<%= path %>/record/delete-record" method="get" id="searchForm">
              <input class="hidden" type="text" value="${pagination.currentPage }" name="page" />
              <input class="hidden" type="text" value="${pagination.orderBy }" name="orderBy" />
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
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>id</th>
                  <th>删除时间</th>
                  <th>工号</th>
                  <th>姓名</th>
                  <th>详情</th>
                </tr>
              </thead>
              <tbody>
                
                <c:forEach items="${pagination.results }" var="record" varStatus="index">
                  <tr>
                    <th scope="row">${index.count }</th>
                    <td>${record.id }</td>
                    <td><fmt:formatDate value="${record.createdAt }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${record.user.id }</td>
                    <td>${record.user.name }</td>
                    <td><img src="<%= path %>/static/css/images/details.svg" class="detail-img" /></td>
                  </tr>
                </c:forEach>

              </tbody>
            </table>

            <nav aria-label="Page navigation" class="pagination-nav text-center">
              <ul class="pagination">
                <c:if test="${pagination.currentPage > 1 }">
                  <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li>
                    <a href="#" aria-label="Previous">
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
                      <li><a href="<%= path %>/buy-record?searchKeyWord=${pagination.searchKeyWord }&page=${pageIndex }&orderBy=${orderBy }">${pageIndex }</a></li>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>

                <c:if test="${pagination.currentPage < pagination.totalPage}">
                  <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&lsaquo;</span>
                    </a>
                  </li>
                </c:if>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
