package com.codecool.enterprise.poll.service;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.model.User;
import com.codecool.enterprise.poll.repository.PickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickService {

    @Autowired
    PickRepository pickRepository;

    public void addPick(Pick pick) {
        pickRepository.save(pick);
    }

    public List<Pick> getPicks(Poll poll) {
        return pickRepository.findPicksByPoll(poll);
    }

    public List<Pick> findPicksByAnswer(Answer answer) {
        return pickRepository.findPicksByAnswer(answer);
    }

    public List<Pick> findPicksByUser(User user) {
        return pickRepository.findPicksByUser(user);
    }

    public void removePicks(Poll myPoll) {
        pickRepository.removePicks(myPoll);
    }
}
