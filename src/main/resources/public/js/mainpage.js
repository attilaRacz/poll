window.onload = function(){
    fireChangePollBtn();
    fireAnswerBtn()
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