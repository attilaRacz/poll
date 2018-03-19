package com.codecool.enterprise.poll.controller;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.service.AnswerService;
import com.codecool.enterprise.poll.service.PickService;
import com.codecool.enterprise.poll.service.PollService;
import com.codecool.enterprise.poll.service.UserService;
import com.codecool.enterprise.poll.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private PollService pollService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private PickService pickService;

    @RequestMapping(value = "/")
    public String renderLogin() {
        return "login";
    }

    @RequestMapping(value = "/poll", method = RequestMethod.GET)
    public String renderPoll(Model model) {
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        } else {
            System.out.println(session.getAttribute("id")); //id of user (from session)
            //should find a poll i did not answer
            Poll poll = pollService.getPoll(1);
            List<Answer> answers = answerService.getAnswers(poll);
            List<Pick> picks = pickService.getPicks(poll);
            model.addAttribute("poll", poll);
            model.addAttribute("answers", answers);
            model.addAttribute("picks", picks);
            return "poll";
        }
    }
}
