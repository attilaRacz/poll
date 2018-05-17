package com.codecool.enterprise.poll.repository;

import com.codecool.enterprise.poll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User user set user.questionsAsked = ?2 where user.id = ?1")
    void updateUser(long UserId, Integer questionsAsked);
}