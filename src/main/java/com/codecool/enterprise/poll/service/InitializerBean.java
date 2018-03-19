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
        userService.addUser(new User("first", "first@email.com", "$2a$10$XWDZyDhV1zY1eRsaY6ysg.k2jhbRXWowEpTyxaQArq1gh5rL2CIVq", 1, 1, 1));
        userService.addUser(new User("second", "second@email.com", "$2a$10$XWDZyDhV1zY1eRsaY6ysg.k2jhbRXWowEpTyxaQArq1gh5rL2CIVq", 1, 1, 1));
        userService.addUser(new User("third", "third@email.com", "$2a$10$XWDZyDhV1zY1eRsaY6ysg.k2jhbRXWowEpTyxaQArq1gh5rL2CIVq", 0, 0, 1));
        userService.addUser(new User("fourth", "fourth@email.com", "$2a$10$XWDZyDhV1zY1eRsaY6ysg.k2jhbRXWowEpTyxaQArq1gh5rL2CIVq", 0, 0, 1));
        userService.addUser(new User("fifth", "fifth@email.com", "$2a$10$XWDZyDhV1zY1eRsaY6ysg.k2jhbRXWowEpTyxaQArq1gh5rL2CIVq", 0, 0, 1));
        userService.addUser(new User("sixth", "sixth@email.com", "$2a$10$XWDZyDhV1zY1eRsaY6ysg.k2jhbRXWowEpTyxaQArq1gh5rL2CIVq", 0, 0, 0));
        pollService.addPoll(new Poll(userService.findUserById(1), "Where shoud I go jogging in Budapest?", Topic.Sport));
        pollService.addPoll(new Poll(userService.findUserById(2), "Which soft drink do you like the most?", Topic.Food));
        pollService.addPoll(new Poll(userService.findUserById(3), "Should I work out every day?", Topic.Sport));
        pollService.addPoll(new Poll(userService.findUserById(4), "Is wine healthy?", Topic.Health));
        pollService.addPoll(new Poll(userService.findUserById(5), "Who is better?", Topic.Music));
        answerService.addAnswer(new Answer(pollService.findPollById(1), "Margaret island", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(1), "Vérmező", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(1), "Stadionok", 1));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Cola", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Rootbeer", 1));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Fanta", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(2), "Sprite", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(3), "Yes", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(3), "No", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(4), "Yes", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(4), "Absolutely", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(5), "Nickelback", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(5), "RHCP", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(5), "Blink-182", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(5), "Britney Spears", 0));
        answerService.addAnswer(new Answer(pollService.findPollById(5), "Beatles", 0));
        pickService.addPick(new Pick(pollService.findPollById(1), answerService.getAnswerById(3), userService.findUserById(2), "Very nice atmosphere."));
        pickService.addPick(new Pick(pollService.findPollById(2), answerService.getAnswerById(5), userService.findUserById(1), "I love A&W rootbeer!"));
    }
}
