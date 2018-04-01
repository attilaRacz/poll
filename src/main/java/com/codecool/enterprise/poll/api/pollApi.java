package com.codecool.enterprise.poll.api;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.model.User;
import com.codecool.enterprise.poll.service.AnswerService;
import com.codecool.enterprise.poll.service.PickService;
import com.codecool.enterprise.poll.service.PollService;
import com.codecool.enterprise.poll.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codecool.enterprise.poll.session.UserSession;


import java.util.List;

@RestController
public class pollApi { //for dom.js ajax call

    @Autowired
    private UserSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private PollService pollService;

    @Autowired
    private PickService pickService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ObjectMapper mapper;

    @RequestMapping(value = "/getpoll", method = RequestMethod.GET)
    public String getOnePoll() throws JsonProcessingException {
        if (session.getAttribute("id") != null) {
            Long userId = Long.parseLong(session.getAttribute("id"));
            User user = userService.findUserById(userId);
            List<Pick> pickList = pickService.findPicksByUser(user);
            List<Long> answeredPollIds = pollService.findPollsByPicks(pickList);
            Poll poll = (answeredPollIds.size()>0) ?
                    pollService.findNewPoll(answeredPollIds, user) :
                    pollService.findNewPoll(user);
            return mapper.writeValueAsString(poll);
        }
        return null;
    }

    @RequestMapping(value = "/getanswers", method = RequestMethod.GET)
    public String getAnswers() throws JsonProcessingException {
        if (session.getAttribute("id") != null) {
            Long userId = Long.parseLong(session.getAttribute("id"));
            User user = userService.findUserById(userId);
            List<Pick> pickList = pickService.findPicksByUser(user);
            List<Long> answeredPollIds = pollService.findPollsByPicks(pickList);
            Poll poll = (answeredPollIds.size() > 0) ?
                    pollService.findNewPoll(answeredPollIds, user) :
                    pollService.findNewPoll(user);
            if (poll != null) {
                List<Answer> answers = answerService.getAnswers(poll);
                return mapper.writeValueAsString(answers);
            }
        }
        return null;
    }
}
