package com.codecool.enterprise.poll.service;

import com.codecool.enterprise.poll.model.User;
import com.codecool.enterprise.poll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User findUserById(long id) {
        return userRepository.findOne(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void updateUser(long UserId, Integer questionsAsked) {
        userRepository.updateUser(UserId, questionsAsked);
    }
}