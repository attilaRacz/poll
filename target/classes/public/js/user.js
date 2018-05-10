window.onload = function(){
    fireLoginButton();
    fireRegisterButton();
};

const errorlist = {
    "allFieldsRequired": "All fields are required!",
    "passwordMismatch": "Passwords do not match!",
    "invalidName": "Your name contains invalid characters!",
    "emailExists": "Your email already exists!",
    "emailInvalid": "That does not look like a valid email!"
};

function fireLoginButton() {
    $('.login_error').hide();
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
                    $(location).attr('href', window.location.href + "/dashboard");
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
}

function fireRegisterButton() {
    $('.register_errors').hide();
    $('.register-button').click(function(event){
        event.preventDefault();
        let data = {
            'email': $('#register_email').val(),
            'username': $('#register_username').val(),
            'password': $('#register_password').val(),
            'passwordcheck': $('#register_password_check').val()
        };
        console.log("yeee! " + data.email, data.username, data.password);
        $.ajax({
            type: 'POST',
            contentType: 'application/JSON',
            url: '/api/register',
            data: JSON.stringify(data),
            success: function (response) {
                console.log(response);
                $('.errors').empty();
                $('#statusMessages').empty();
                if(JSON.parse(response)["valid"] === true){
                    $(location).attr('href', window.location.href + "/dashboard");
                } else {
                    $.each(JSON.parse(response), function(key, value) {
                        if(value === true) {
                            $('.errors').append("<li>" + errorlist[key] + "</li>");
                        }
                        $('.register_errors').show();
                    });
                }
            },
            error: function(response) {
                console.log(response);
            }
        });
    });
}