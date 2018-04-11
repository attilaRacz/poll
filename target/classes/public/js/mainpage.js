window.onload = function(){
    fireChangePollBtn();
    fireAnswerBtn();
    showPoll();
    showAnswers()
};

function fireChangePollBtn() {
    $('.changemypoll-button').click(function(event){
        //to be implemented
        $(location).attr('href', "/answer");
    })
}

function fireAnswerBtn() {
    $('.answerpolls-button').click(function(event){
        $(location).attr('href', "/answer");
    })
}

function showPoll() {
    $.ajax({
        url: '/getmypoll',
        type: 'GET',
        dataType: 'json',
        success: function(poll){
            if (poll !== null) {
                $("#user").append(`hello <strong>${poll.user.userName}</strong>, you asked the following:`);
                $("#poll").append(`${poll.question}`)
            } else {
                $("#mainContainer").hide();
                document.body.innerHTML += '<div class="container"><h2>You do not have an active poll right now.</h2></div>';
            }

        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " " + errorThrown)
        }
    });
}

function showAnswers() {
    $.ajax({
        url: '/getmyanswers',
        type: 'GET',
        dataType: 'json',
        success: function(answers){
            $.each(answers, function(i, oneAnswer){
                $("#answer").append(`
                    <p class="anAnswer" id="${answers[i].id}"><strong>${answers[i].answer}</strong> votes: ${answers[i].score}</p>`);
            });
            showComments();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " " + errorThrown)
        }
    });
}

function showComments() {
    let pollAnswers = document.getElementsByClassName("anAnswer");
    for (let j = 0; j < pollAnswers.length; j++) {
        pollAnswers[j].addEventListener("click", function(event){
            let clickedAnswer = this;
            let clickedAnswerId = parseInt(clickedAnswer.id);
            document.getElementById("comments").innerHTML = "";
            getCommentsByAnswerId(clickedAnswerId);
        });
    }
}

function getCommentsByAnswerId(clickedAnswerId) {
    let data = {'id' : clickedAnswerId};
    $.ajax({
        url: '/getcomments',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/JSON',
        success: function(comments){
            if (comments !== "no comment") {
                let allComments = JSON.parse(comments);
                $.each(allComments, function(i, oneComment) {
                    let commentSection = "";
                    if (oneComment.comment.length>0) {
                        commentSection +=
                            `<li><strong>${allComments[i].user.userName}:</strong> ${allComments[i].comment}</li>`;
                        $("#comments").append(`${commentSection}`);
                    }
                })
            } else {
                $("#comments").append(`<li><i>There is no comment on this answer.</i></li>`);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " " + errorThrown)
        }
    });
}