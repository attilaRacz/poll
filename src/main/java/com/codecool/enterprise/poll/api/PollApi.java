package com.codecool.enterprise.poll.api;

import com.codecool.enterprise.poll.model.*;
import com.codecool.enterprise.poll.service.AnswerService;
import com.codecool.enterprise.poll.service.PickService;
import com.codecool.enterprise.poll.service.PollService;
import com.codecool.enterprise.poll.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codecool.enterprise.poll.session.UserSession;


import java.util.List;

@RestController
public class PollApi { //for answer_dom.js ajax call

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

    @RequestMapping(value = "/getmypoll", method = RequestMethod.GET)
    public String getMyPoll() throws JsonProcessingException {
        if (session.getAttribute("id") != null) {
            Long userId = Long.parseLong(session.getAttribute("id"));
            User user = userService.findUserById(userId);
            Poll poll = pollService.findMyPoll(user);
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

    @RequestMapping(value = "/getmyanswers", method = RequestMethod.GET)
    public String getMyAnswers() throws JsonProcessingException {
        if (session.getAttribute("id") != null) {
            Long userId = Long.parseLong(session.getAttribute("id"));
            User user = userService.findUserById(userId);
            Poll poll = pollService.findMyPoll(user);;
            if (poll != null) {
                List<Answer> answers = answerService.getAnswers(poll);
                return mapper.writeValueAsString(answers);
            }
        }
        return null;
    }

    @RequestMapping(value = "/getcomments", method = RequestMethod.POST)
    public String getComments(@RequestBody IdJSON idJSON) throws JsonProcessingException {
        if (session.getAttribute("id") != null) {
            Answer answer = answerService.getAnswerById(idJSON.getId());
            List<Pick> picks = pickService.findPicksByAnswer(answer);
            if (picks.size() > 0) {
                return mapper.writeValueAsString(picks);
            }
        }
        return "no comment";
    }

    @PostMapping(value = "/save_answer")
    public String login(@RequestBody AnswerJSON answerData) {
        //update the credit of the user
        Long userId = Long.parseLong(session.getAttribute("id"));
        User user = userService.findUserById(userId);
        user.changeCredit(1);
        //update the score of the answer
        Answer answer = answerService.getAnswerById(answerData.getId());
        answer.changeScore(1);
        //update the credit of the owner of the poll
        User pollOwner = answer.getPoll().getUser();
        pollOwner.changeCredit(-1);
        //persist the pick
        Poll poll = answer.getPoll();
        Pick pick = new Pick(poll, answer, user, answerData.getComment());
        pickService.addPick(pick);
        return "ok";
    }

    @PostMapping(value = "/save_new_answer")
    public String saveNewAnswer(@RequestBody AnswerJSON answerData) {
        Long userId = Long.parseLong(session.getAttribute("id"));
        User user = userService.findUserById(userId);
        Poll myPoll = pollService.findMyPoll(user);
        Answer newAnswer = new Answer(myPoll, answerData.getAnswer(), 0);
        answerService.addAnswer(newAnswer);
        return "ok";
    }

    @PostMapping(value = "/edit_poll")
    public String editPoll(@RequestBody PollJSON pollData) {
        Long userId = Long.parseLong(session.getAttribute("id"));
        pollService.updatePoll(userId, pollData.question);
        User user = userService.findUserById(userId);
        Poll myPoll = pollService.findMyPoll(user);
        pickService.removePicks(myPoll);
        answerService.removeAnswers(myPoll);
        return "ok";
    }
}
