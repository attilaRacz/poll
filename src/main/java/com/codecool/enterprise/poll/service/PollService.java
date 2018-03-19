package com.codecool.enterprise.poll.service;

import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
