window.onload = function(){
    $('.login_error').hide();

    var idCount = 1;
    $('.answer').each(function() {
        $(this).attr('id', 'a' + idCount);
        idCount++;
    });

    $('.login-button').click(function(event){
        event.preventDefault();
        let data = {
            'email': $('#login_email').val(),
            'password': $('#login_password').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/login',
            contentType: 'application/JSON',
            data: JSON.stringify(data),
            success: function (response) {
                if(JSON.parse(response)["valid"] === true){
                    $(location).attr('href', window.location.href + "/poll");
                } else {
                    $('.login_error').show();
                }
            },
            error: function(response) {
                $('.login_error').empty();
                $('.login_error').append("<p>Could not connect to the server.</p>");
                $('.login_error').show();
                console.log(response);
            }
        });
    });
};