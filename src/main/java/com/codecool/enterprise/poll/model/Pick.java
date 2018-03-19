package com.codecool.enterprise.poll.model;

import javax.persistence.*;

@Entity
public class Pick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //used to be many to one
    @OneToOne
    @JoinColumn(name = "poll")
    private Poll poll;

    @ManyToOne
    private Answer answer;

    @OneToOne
    private User user;

    private String comment;

    public Pick() {}

    public Pick(Poll poll, Answer answer, User user, String comment) {
        this.answer = answer;
        this.user = user;
        this.comment = comment;
        this.poll = poll;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
