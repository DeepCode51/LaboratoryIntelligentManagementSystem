<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
      + path ;
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>欢迎登录实验室管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<%= path %>/static/css/login/login.css" rel="stylesheet" />
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="<%= path %>/static/js/lib/jquery-3.3.1.min.js"></script>
    <script src="<%= path %>/static/js/lib/bootstrap.js"></script>
    <script type="text/javascript" src="<%= path %>/static/js/login/login.js"></script>
  </head>
  <body>
    <div class="content">
      <div class="left-img">
        <img src="http://s.ilab.cn/images/bg02.jpg" />
      </div>
      <div class="right-login">
        <div class="login-content">
          <div class="login-header">
            <span><img src="<%= path %>/static/css/images/lab_icon.png" /></span> <span>TYUT实验室智能化管理平台</span>
          </div>
  
          <div class="login-form">
            <form action="<%= path %>/user/login" method="post"
              onsubmit="return checkUser();" id="loginForm">
              <!-- tips -->
              <div class="tip">
                <c:choose>
                  <c:when test="${not empty error }">
                    <div class="errorTips" style="" id="errorTips">
                      <img src="<%= path %>/static/css/images/error-c.png" width="16px" height="16px" />
                      <span class="errorMessage" id="errorMessage">
                        ${error }
                      </span>
                    </div>
                  </c:when>
                  <c:otherwise>
                    <div class="errorTips hidden-important" id="errorTips">
                      <img src="<%= path %>/static/css/images/error-c.png" width="16px" height="16px" />
                      <span class="errorMessage" id="errorMessage">
                        ${error }
                      </span>
                    </div>
                  </c:otherwise>
                </c:choose>
              </div>

              <!-- 账号 -->
              <div class="input-group input-group-lg">
                <span class="input-group-addon" id="sizing-addon1">
                  <span class="glyphicon glyphicon-user"></span>
                </span>
                <input type="text" class="form-control" placeholder="Username"
                  aria-describedby="sizing-addon1" maxlength="15" id="username"
                  name="username" />
              </div>

              <!-- 密码 -->
              <div class="input-group input-group-lg password-div">
                <span class="input-group-addon" id="sizing-addon1"> <span
                  class="glyphicon glyphicon-lock"></span>
                </span> <input type="password" class="form-control"
                  placeholder="Password" aria-describedby="sizing-addon1"
                  maxlength="15" id="password" name="password">
              </div>
              <div class="login-rememRule">
                <label class="rememberUserPassword"> 
                  <input name="rememberMe" type="checkbox" value="1" checked="checked" />&nbsp;记住密码
                </label> 
                <span class="user-operation"> 
                  <a data-toggle="modal" data-dismiss="modal" data-target="#register">注册</a>
                  <a href="./toRestorePassword">忘记密码</a>
                </span>
              </div>
              <input type="submit" id="depost" class="btn btn-info login-btn"
                value="点&nbsp;&nbsp;击&nbsp;&nbsp;登&nbsp;&nbsp;录" />
            </form>
          </div>
          <hr style="height: 1px;" color="#eeeeee" />
          <div class="login-footer">
            ©2018 TYUT lab <br> 晋ICP备17006252号-1
          </div>
        </div>
      </div>
    </div>
  
    <!-- 模态  fade -->
    <!-- 注册 -->
    <div id="register" class="modal fade" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">注册</h1>
          </div>
          <div class="modal-body">
            <form class="form-group" action="<%= path %>/register" method="post">
              <div class="form-group">
                <label for="">用户名</label> <input class="form-control" type="text"
                  placeholder="6-15位字母或数字" name="username">
              </div>
              <div class="form-group">
                <label for="">密码</label> <input class="form-control"
                  type="password" placeholder="至少6位字母或数字" name="password">
              </div>
              <div class="form-group">
                <label for="">再次输入密码</label> <input class="form-control"
                  type="password" placeholder="至少6位字母或数字" name="user_password_2">
              </div>
              <div class="form-group">
                <label for="">邮箱</label> <input class="form-control" type="email"
                  placeholder="例如:123@123.com" name="email">
              </div>
              <div class="text-right">
                <button class="btn btn-primary" type="submit">提交</button>
                <button class="btn btn-danger" data-dismiss="modal">取消</button>
              </div>
              <a href="" data-dismiss="modal">已有账号？点我登录</a>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>