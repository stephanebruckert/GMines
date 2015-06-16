function refresh() {
    $.ajax({
        url:link,
        dataType: 'text',
        success: function(data) {
            if (parseInt(data) != actionCount) {
                location.reload();
            }
        },
        error: function(request, status, error) {

        },
        complete: function() {

        }
    });
}