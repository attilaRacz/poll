window.onload = function(){
    showPoll();
    showAnswers();
};

function showPoll() {
    $.ajax({
        url: '/getpoll',
        type: 'GET',
        dataType: 'json',
        success: function(poll){
            $("#poll").append(`${poll.question}`
            )
        }
    });
}

function showAnswers() {
    $.ajax({
        url: '/getanswers',
        type: 'GET',
        dataType: 'json',
        success: function(answers){
            $.each(answers, function(i, oneAnswer){
                $("#answer").append(`
                    <label>${answers[i].answer}</label>
                    <input type="radio" name="answer" class="anAnswer" id="${answers[i].id}" />
                    <br>`);
            })
        }
    });

    setTimeout(function showComments() {
        let pollAnswers = document.getElementsByClassName("anAnswer");
        for (let j = 0; j < pollAnswers.length; j++) {
            pollAnswers[j].addEventListener("click", function(event){
                let clickedAnswer = this;
                let clickedAnswerId = parseInt(clickedAnswer.id);
                alert(clickedAnswerId);
                let comments = getCommentsByAnswerId(clickedAnswerId);
                let commentSection = "";
                for (let k = 0; k < comments.length; k++) {
                    commentSection +=
                        `<div>${comments[j].comment}</div>`;
                }
                $("#comments").append(`${commentSection}`);
            });
        }
    }, 1000)
}

function getCommentsByAnswerId(clickedAnswerId) {
    let data = {'id' : clickedAnswerId};
    $.ajax({
        url: '/getcomments',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/JSON',
        success: function(comments){
            return comments;
        },
        error: function() {
            alert('error');
        }
    });
}

