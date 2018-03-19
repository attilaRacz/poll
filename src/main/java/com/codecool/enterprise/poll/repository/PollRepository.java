package com.codecool.enterprise.poll.repository;

import com.codecool.enterprise.poll.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
        //find poll relating stuff

    //should return a poll i haven't answered
    Poll findPollById(long id);

}
