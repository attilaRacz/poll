package com.codecool.enterprise.poll.repository;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickRepository extends JpaRepository<Pick, Long> {
    //find pick relating stuff

    List<Pick> findPicksByPoll(Poll poll);

    Pick findPickByAnswer(Answer answer);
}
