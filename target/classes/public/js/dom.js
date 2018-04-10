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
            $("#user").append(`question by <strong>${poll.user.userName}</strong>`);
            $("#poll").append(`${poll.question}`)
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
                    <input type="radio" name="answer" class="anAnswer" id="${answers[i].id}" />
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

