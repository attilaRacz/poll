package com.codecool.enterprise.poll.service;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.model.Topic;
import com.codecool.enterprise.poll.model.User;
import com.codecool.enterprise.poll.model.Pick;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(UserService userService, PollService pollService, AnswerService answerService, PickService pickService) {
        userService.addUser(new User("first", "first@email.com", "pass", 0, 0, 0));
        userService.addUser(new User("second", "second@email.com", "pass", 0, 0, 0));
        pollService.addPoll(new Poll(userService.findUserById(1), "Where shoud I go jogging in Budapest?", Topic.Sport));
        pollService.addPoll(new Poll(userService.findUserById(2), "Which soft drink do you like the most?", Topic.Food));
        answerService.addAnswer(new Answer(pollService.findPollById(1), "Margaret island", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(1), "Vérmező", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(1), "Stadionok", 1));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Cola", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Rootbeer", 1));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Fanta", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Sprite", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Mineral water", 0));
        pickService.addPick(new Pick(pollService.findPollById(1), answerService.getAnswerById(3), userService.findUserById(2), "Very nice atmosphere."));
        pickService.addPick(new Pick(pollService.findPollById(2), answerService.getAnswerById(5), userService.findUserById(1), "I love A&W rootbeer!"));
    }
}
