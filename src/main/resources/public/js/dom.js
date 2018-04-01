window.onload = function(){
    $.ajax({
        url: '/getpoll',
        type: 'GET',
        dataType: 'json',
        success: function(poll){
            $("#poll").append(`<h2>${poll.question}</h2>`
            )
        }});

    $.ajax({
        url: '/getanswers',
        type: 'GET',
        dataType: 'json',
        success: function(answers){
            $("#poll").append(`<h2>${answers[0].answer}</h2>`
            )
        }});

};
