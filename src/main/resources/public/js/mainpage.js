window.onload = function(){
    fireChangePollBtn();
    fireAnswerBtn();
    showPoll();
    showAnswers();
    fireNewAnswerBtn();
};

function fireChangePollBtn() {
    $('.changemypoll-button').click(function(event){
        emptyPollBody();
        showPollEdit();
    })
}

function fireAnswerBtn() {
    $('.answerpolls-button').click(function(event){
        $(location).attr('href', "/answer");
    })
}

function fireNewAnswerBtn() {
    $('.newanswer-button').click(function(event){
        event.preventDefault();
        let data = {
            'answer': $('#new_answer').val()
        };

        if (!data.answer) {
            alert("Write an answer first");
        } else {
            $.ajax({
                type: 'POST',
                url: '/save_new_answer',
                contentType: 'application/JSON',
                data: JSON.stringify(data),
                success: function (response) {
                    console.log("New answer post request sent to server" + data);
                    $(location).attr('href', window.location.href + "/");
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(textStatus + " " + errorThrown)
                }
            });
        }
    });
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

function showPollEdit() {
    $.ajax({
        url: '/getmypoll',
        type: 'GET',
        dataType: 'json',
        success: function(poll){
            if (poll !== null) {
                $("#user").append(`hello <strong>${poll.user.userName}</strong>, edit your question here:`);
                $("#poll").append(`<input id="poll_question" name="poll_question" placeholder="${poll.question}"/>`);
            } else {
                $("#poll").append(`<input id="poll_question" name="poll_question" placeholder="Add a question for your poll"/>`);
            }
            $("#answer").append(`<div><button class="savepoll-button" type="button">Save poll</button></div>`);
            fireSavePollBtn();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " " + errorThrown)
        }
    });
}

function fireSavePollBtn() {
    $('.savepoll-button').click(function(event){
        //todo - send poll data to server
        let data = {
            'question': $('#poll_question').val(),
            //todo - get all answers and organise them into JSON
        };

        if (!data.question) {
            alert("Give a title for your poll!");
        } else {
            $.ajax({
                type: 'POST',
                url: '/edit_poll',
                contentType: 'application/JSON',
                data: JSON.stringify(data),
                success: function (response) {
                    console.log("Poll edit request sent to server" + data);
                    //todo - just simply show my poll with AJAX
                    $(location).attr('href', window.location.href + "/");
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(textStatus + " " + errorThrown)
                }
            });
        }
    })
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

function emptyPollBody() {
    document.getElementById("user").innerHTML = "";
    document.getElementById("poll").innerHTML = "";
    document.getElementById("answer").innerHTML = "";
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