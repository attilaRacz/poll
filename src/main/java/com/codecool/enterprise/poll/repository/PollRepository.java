package com.codecool.enterprise.poll.repository;

import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    //find poll relating stuff

    Poll findPollById(long id);

    List<Poll> findPollByUserNot(User user);

    List<Poll> findPollsByIdNotInAndUserNot(List<Long> ids, User user);

    Poll findPollByUser(User user);
}
