package com.codecool.enterprise.poll.repository;

import com.codecool.enterprise.poll.model.Answer;
import com.codecool.enterprise.poll.model.Pick;
import com.codecool.enterprise.poll.model.Poll;
import com.codecool.enterprise.poll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PickRepository extends JpaRepository<Pick, Long> {

    List<Pick> findPicksByPoll(Poll poll);

    List<Pick> findPicksByUser(User user);

    List<Pick> findPicksByAnswer(Answer answer);

    @Transactional
    @Modifying
    @Query("delete from Pick pick where pick.poll = ?1")
    void removePicks(Poll myPoll);
}
