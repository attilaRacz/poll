package com.codecool.enterprise.poll.model;

import javax.persistence.*;

@Entity
@Table(name = "poll")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "topic")
    private Topic topic;

    public long getId() {
        return id;
    }

    public Poll() {}

    public Poll(User user, String question, Topic topic) {
        this.user = user;
        this.question = question;
        this.topic = topic;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
