<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>实验室管理系统主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script type="text/javascript" src="<%= path %>/static/js/lib/jquery-3.3.1.min.js"></script>
<script src="<%= path %>/static/js/lib/bootstrap.js"></script>
<link href="<%= path %>/static/css/lab/content.css" rel="stylesheet" />
<style type="text/css">
.carousel-inner>.item>img {
  min-width: 100%;
  height: 100%;
}
</style>
</head>

<body style="background-color: #fff">

  <!-- 顶部标题栏 -->
  <jsp:include page="header.jsp"></jsp:include>

  <!-- content -->
  <div class="container-fluid" style="margin-top: 75px; padding-left: 15%; padding-right: 15%;">

    <!-- 轮播 -->
    <div class="row">
      <div class="col-md-12">
        <!-- 轮播 -->
        <div id="carousel-example-generic" class="carousel slide"
          data-ride="carousel" style="height: 400px;">
          <!-- Indicators -->
          <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0"
              class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
          </ol>

          <!-- Wrapper for slides -->
          <div class="carousel-inner" role="listbox">
            <div class="item active">
              <img src="<%= path %>/static/css/images/h1.jpg" class="img-responsive" alt="...">
              <div class="carousel-caption">计科实验室内景</div>
            </div>
            <div class="item">
              <img src="<%= path %>/static/css/images/h2.jpg" class="img-responsive" alt="...">
              <div class="carousel-caption">化学实验室购置一批新设备</div>
            </div>
            <div class="item">
              <img src="<%= path %>/static/css/images/h3.png" class="img-responsive" alt="...">
              <div class="carousel-caption">生物实验室内景</div>
            </div>
          </div>

          <!-- Controls -->
          <a class="left carousel-control" href="#carousel-example-generic"
            role="button" data-slide="prev"> <span
            class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a> <a class="right carousel-control" href="#carousel-example-generic"
            role="button" data-slide="next"> <span
            class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
    </div>

    <!-- 通知栏 and 业务处理 -->
    <div class="row" style="height: auto;margin-top: 20px;">
      <!-- 通知栏 -->
      <div class="col-md-9">
        <div class="notice">
          <div class="notice-title">
            <div class="notice-board">通知公告：</div>
          </div>
          <hr />
          <div class="bd">
            <ul>

              <li class="intmc"><a href="web_content.jsp"
                title="2016年12月关于实验室设备清点工作"> 2016年12月关于实验室设备清点工作</a></li>
              <li class="intim">2016-12-25</li>
              <li class="intmc"><a href="web_content.jsp"
                title="2016年12月年底关于转发《各实验室管理人员评定工作的通知》">
                  2016年12月年底关于转发《各实验室管理人员评定工作的通知》</a></li>
              <li class="intim">2016-12-24</li>
              <li class="intmc"><a href="web_content.jsp"
                title="2016年12月关于实验室设备清理工作"> 2016年12月关于实验室设备清理工作</a></li>
              <li class="intim">2016-12-01</li>
              <li class="intmc"><a href="http://www.baidu.com"
                title="关于《召开各实验室设备管理维护交流会议通知》"> 关于《召开各实验室设备管理维护交流会议通知》</a></li>
              <li class="intim">2016-11-05</li>
              <li class="intmc"><a href="web_content.jsp"
                title="2016年11月关于实验室设备清点、与维护的通知"> 2016年11月关于实验室设备清点、与维护的通知</a></li>
              <li class="intim">2016-11-01</li>
              <li class="intmc"><a href="web_content.jsp"
                title="2016年11月关于实验室设备清点、与维护的通知"> 2016年11月关于实验室设备清点、与维护的通知</a></li>
              <li class="intim">2016-11-01</li>

            </ul>
          </div>
          <!-- more notice -->
          <div class="notice-more">
            <a href="">&gt;更多公告</a>
          </div>
        </div>
      </div>
      <!-- 业务处理 -->
      <div class="col-md-3" style="">
        <div class="notice">
          <div class="notice-title">
            <div class="notice-board">业务处理：</div>
            <hr />
            <div class="ywcl-btn userinfo">
              <label><a href="<%= path %>/user/toUserInfo">个人信息管理</a></label>
            </div>
            <div class="ywcl-btn">
              <label>实验设备查询</label>
            </div>
            <div class="ywcl-btn userinfo">
              <label><a href="<%= path %>/equipment/toRequestBuyEquip">购置设备申请</a></label>
            </div>
            <div class="ywcl-btn">
              <label>报废设备申请</label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--  footer -->
  <div class="navbar navbar-default navbar-static-bottom" role="navigation"
    style="margin-bottom: 0px; padding: 0px; margin-top: 20px">
    <div class="container" style="padding: 15px; width:100%;">
      <p class="text-center">学生：杨嘉志</p>
      <p class="text-center">© 2018 | 晋ICP备17006252号-1</p>
    </div>
  </div>
  <script src="<%= path %>/static/js/lab/content.js"></script>
</body>
</html>