package com.codecool.enterprise.poll.controller;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.model.User;
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

import java.util.ArrayList;
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

    @RequestMapping(value = "/dashboard")
    public String renderDashboard() {
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        return "mypoll";
    }

    @RequestMapping(value = "/answer", method = RequestMethod.GET)
    public String renderPoll(Model model) {
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        } else {
            Long userId = Long.parseLong(session.getAttribute("id"));
            User user = userService.findUserById(userId);

            List<Pick> pickList = pickService.findPicksByUser(user);
            List<Long> answeredPollIds = pollService.findPollsByPicks(pickList);
            Poll poll = (answeredPollIds.size()>0) ?
                    pollService.findNewPoll(answeredPollIds, user) :
                    pollService.findNewPoll(user);
            if (poll!=null) {
                List<Answer> answers = answerService.getAnswers(poll);
                List<Pick> picks = pickService.getPicks(poll);
                model.addAttribute("poll", poll);
                model.addAttribute("answers", answers);
                model.addAttribute("picks", picks);
            }
            return "answer";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        session.clear();
        return "redirect:/";
    }
}
