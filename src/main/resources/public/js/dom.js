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
            let index = 1;
            $.each(answers, function(i, oneAnswer){
                $("#answer").append(`
                    <label>${answers[i].answer}</label>
                    <input type="radio" name="answer" class="answer" id="${index}" />
                    <br>`);
                    index++;
            })
        }
    });

    let pollAnswers = document.getElementsByClassName("answer");
    for (let j = 0; j < pollAnswers.length; j++) {
        pollAnswers[j].addEventListener("click", function(event){
            let clickedAnswer = this;
            let clickedAnswerId = parseInt(clickedAnswer.dataset['id']);
            let comments = getCommentsByAnswerId(clickedAnswerId); //megcsinálni a methodot
            let commentSection = "";
            for (let k = 0; k < comments.length; k++) {
                commentSection +=
                    `<div>${comments[j].comment}</div>`;
            }
            $("#comments").append(`${commentSection}`);
        });
    }
};

function getCommentsByAnswerId(clickedAnswerId) {
    $.ajax({
        url: '/getcomments',
        type: 'GET',
        dataType: 'json',
        success: function(comments){
            return comments;
        }
    });
}