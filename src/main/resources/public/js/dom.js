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
            if (poll !== null) {
                $("#user").append(`question by <strong>${poll.user.userName}</strong>`);
                $("#poll").append(`${poll.question}`)
            } else {
                $("#mainContainer").hide();
                document.body.innerHTML += '<div class="container"><h2>You answered all the polls, thank you! Come back later for more!</h2></div>';
            }

        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " " + errorThrown)
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
                    <input type="radio" name="answer" class="anAnswer" value="${answers[i].id}" id="${answers[i].id}"/>
                    <br>`);
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
    fireButton();
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
                    commentSection +=
                        `<li>${allComments[i].comment}</li>`;
                    $("#comments").append(`${commentSection}`);
                })
            } else {
                $("#comments").append(`<li>There is no comment on this answer.</li>`);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus + " " + errorThrown)
        }
    });
}

function fireButton() {
    $('.pick-button').click(function(event){
        event.preventDefault();
        let data = {
            'id': $('input[name=answer]:checked').val(),
            'comment': $('#user_comment').val()
        };

        $.ajax({
            type: 'POST',
            url: '/save_answer',
            contentType: 'application/JSON',
            data: JSON.stringify(data),
            success: function (response) {
                console.log("Pick post request sent to server" + data)
                $(location).attr('href', window.location.href + "/");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus + " " + errorThrown)
            }
        });
    });
}

