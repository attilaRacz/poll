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
        if (pollRepository.findPollsByIdNotInAndUserNot(ids, user).size()>0) {
            //returns the first poll where the owner has credit
            List<Poll> pollsToAnswer = pollRepository.findPollsByIdNotInAndUserNot(ids, user);
            for (Poll poll : pollsToAnswer) {
                if (poll.getUser().getCredit()>0) {
                    return poll;
                }
            }
        }
        return null;
    }

    public Poll findMyPoll(User user) {
        return pollRepository.findPollByUser(user);
    };

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
