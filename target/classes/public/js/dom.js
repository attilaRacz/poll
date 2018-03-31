window.onload = function(){
    $.ajax({url: '/getpoll', type: 'GET',
        success: function(poll){
        $("#poll").append('<h2>`${poll.getQuestion()}`</h2>'
        )
    }});
};
