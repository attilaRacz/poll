window.onload = function(){
    $.ajax({
        url: '/getpoll',
        type: 'GET',
        dataType: 'json',
        success: function(poll){
        $("#poll").append(`<h2>${poll.question}</h2>`
        )
    }});
};
