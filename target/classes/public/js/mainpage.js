window.onload = function(){
    fireChangePollBtn();
    fireAnswerBtn()
};

function fireChangePollBtn() {
    $('.changemypoll-button').click(function(event){
        $(location).attr('href', window.location.href + "/answer");
    })
}

function fireAnswerBtn() {
    $('.answerpolls-button').click(function(event){
        $(location).attr('href', "/answer");
    })
}