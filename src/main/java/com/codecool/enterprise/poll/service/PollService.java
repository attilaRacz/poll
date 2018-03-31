package com.codecool.enterprise.poll.service;

import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.model.User;
import com.codecool.enterprise.poll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public void addPoll(Poll poll) {
        pollRepository.save(poll);
    }

    public Poll findPollById(long id) {
        return pollRepository.findOne(id);
    }

    public Poll getPoll(long id) {
        return pollRepository.findPollById(id);
    }

    public Poll findNewPoll(List<Long> ids, User user) {
        if (pollRepository.findPollsByIdNotInAndUserNot(ids, user)!=null) {
            System.out.println("in service:" + pollRepository.findPollsByIdNotInAndUserNot(ids, user).get(0).getQuestion());
            return pollRepository.findPollsByIdNotInAndUserNot(ids, user).get(0);
        }
        System.out.println("in service: null");
        return null;
    }

    public Poll findNewPoll(User user) {
        return pollRepository.findPollByUserNot(user).get(0);
    }

    public List<Long> findPollsByPicks(List<Pick> picks) {
        List<Long> answeredPollIds = new ArrayList<>();
        for (Pick pick : picks) {
            answeredPollIds.add(pick.getAnswer().getPoll().getId());
        }
        return answeredPollIds;
    }
}
