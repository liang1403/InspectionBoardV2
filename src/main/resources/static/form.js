$(function() {
    $('form').submit( function(e) {
        e.preventDefault();
        $.ajax({
            url: e.action,
            type: e.method,
            contentType: "application/json",
            data: $(this).serialize()
        });

    } );
});