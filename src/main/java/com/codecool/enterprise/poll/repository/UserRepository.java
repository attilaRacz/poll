package com.codecool.enterprise.poll.repository;

import com.codecool.enterprise.poll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

}