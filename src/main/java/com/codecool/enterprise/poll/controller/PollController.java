package com.codecool.enterprise.poll.controller;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.service.AnswerService;
import com.codecool.enterprise.poll.service.PickService;
import com.codecool.enterprise.poll.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private PickService pickService;

    @RequestMapping(value = "/")
    public String funFact(Model model) {
        //should return a poll i haven't answered
        Poll poll = pollService.getPoll(1);
        List<Answer> answers = answerService.getAnswers(poll);
        List<Pick> picks = pickService.getPicks(poll);
        model.addAttribute("poll", poll);
        model.addAttribute("answers", answers);
        model.addAttribute("picks", picks);
        return "index";
    }

}