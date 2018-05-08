package com.codecool.enterprise.poll.repository;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    //find answer relating stuff

    List<Answer> findAnswersByPoll(Poll poll);

    Answer findAnswerById(long id);

    @Transactional
    @Modifying
    @Query("delete from Answer answer where answer.poll = ?1")
    void removeAnswers(Poll poll);
}
