function checkUser() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username == null || username.trim() == "") {
        document.getElementById("errorTips").style.display = "block";
        var message = "用户名不能为空";
        $("#errorMessage").html(message);
        $("#username").focus();

        return false;
    }

    if (password == null || password.trim() == "") {
      document.getElementById("errorTips").style.display = "block";
      var message = "密码不能为空";
      $("#errorMessage").html(message);
      $("#password").focus();

      return false;
    }

    return true;
}