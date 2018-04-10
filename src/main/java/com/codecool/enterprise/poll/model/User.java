package com.codecool.enterprise.poll.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String email;

    private String passWord;

    private int credit;

    private int answersGiven;

    private int questionsAsked;

    public User() {}

    public User(String userName, String email, String passWord, int credit, int answersGiven, int questionsAsked) {
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.credit = credit;
        this.answersGiven = answersGiven;
        this.questionsAsked = questionsAsked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getAnswersGiven() {
        return answersGiven;
    }

    public void setAnswersGiven(int answersGiven) {
        this.answersGiven = answersGiven;
    }

    public int getQuestionsAsked() {
        return questionsAsked;
    }

    public void setQuestionsAsked(int questionsAsked) {
        this.questionsAsked = questionsAsked;
    }

    public void changeCredit(int toAdd) {
        this.credit += toAdd;
    }

}
