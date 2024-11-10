$(document).ready(function(){

    $('.agree-btn').on('click', function () {
        $.ajax({
            url: path + "/approvement/m/buy-record/approve",
            type: "post",
            data: {
                id: $('.record-id').val().toString(),
                operation: '1'
            },
            success: function (data) {
                if (data.code == '200') {
                    $(".alert").attr("class", "alert alert-success");
                    $(".alert").html("审批成功.");
                    $('html, body').animate({scrollTop: 0},'slow');
                } else {
                    $(".alert").attr("class", "alert alert-danger");
                    $(".alert").html("审批失败.");
                    $('html, body').animate({scrollTop: 0},'slow');
                }
            },
            error: function () {
                $(".alert").attr("class", "alert alert-danger");
                $(".alert").html("输入信息有误，请检查后再提交。");
                $('html, body').animate({scrollTop: 0},'slow');
            }
        });
    });
});