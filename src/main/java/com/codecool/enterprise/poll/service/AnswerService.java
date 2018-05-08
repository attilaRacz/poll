package com.codecool.enterprise.poll.service;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public void addAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Answer getAnswerById(long id) {
        return answerRepository.findAnswerById(id);
    }

    public List<Answer> getAnswers(Poll poll) {
        return answerRepository.findAnswersByPoll(poll);
    }

    public void removeAnswers(Poll myPoll) {
        answerRepository.removeAnswers(myPoll);
    }
}