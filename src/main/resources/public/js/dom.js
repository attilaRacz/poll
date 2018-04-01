window.onload = function(){
    $.ajax({
        url: '/getpoll',
        type: 'GET',
        dataType: 'json',
        success: function(poll){
            $("#poll").append(`${poll.question}`
            )
        }
    });

    $.ajax({
        url: '/getanswers',
        type: 'GET',
        dataType: 'json',
        success: function(answers){
            var index = 1;
            $.each(answers, function(i, oneAnswer){
                $("#answer").append(`
                    <label>${answers[i].answer}</label>
                    <input type="radio" name="answer" class="answer" id="${index}" />
                    <br>`)
                    index++;
            })
        }
    });
};
