$(document).ready(function() {
    $(".search-btn").on("click", function() {
        $("#searchForm").submit();
    });

    $(document).keyup(function(event) {
        if(event.keyCode == 13) {
            $("#searchForm").submit();
        }
    });

    $(".record-buy-record").on("click", function() {
        $(location).attr('href', path + '/approvement/r/buy-record');
    });

    $(".approve-buy-record").on("click", function() {
        $(location).attr('href', path + '/approvement/m/buy-record');
    });
});

function toDeletedRecord() {
    document.location.href = path + "/record/delete-record";
}

function toBuyRecord() {
    document.location.href = path + "/record/buy-record";
}
