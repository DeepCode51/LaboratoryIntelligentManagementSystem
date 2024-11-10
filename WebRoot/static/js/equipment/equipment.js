$(document).ready(function() {
    $(".search-btn").on("click", function() {
        $("#searchForm").submit();
    });

    $(document).keyup(function(event) {
        if(event.keyCode == 13) {
            $("#searchForm").submit();
        }
    });

    $(".delete-btn").on("click", function() {
        if ($("input[type='checkbox']:checked").length > 0) {
            $("#myModal").modal('show');
        }
    });

    $(".confirm-delete-btn").on("click", function() {
        if ($("input[type='checkbox']:checked").length > 0) {
            $('#equipmentDeletedForm').submit();
            $("#myModal").modal('hide');
        }
    });
});

function changeUrlById(id) {
    document.location.href = path + "/lab/info?labId=" + id;
}

function checkDeleteBox(imgId, checkBoxId) {
    if ($('#' + checkBoxId).is(":checked")) {
        $('#' + imgId).attr('src', path + '/static/css/images/check-box-outline-blank.svg');
    } else {
        $('#' + imgId).attr('src', path + '/static/css/images/check-box.svg');
    }
}
