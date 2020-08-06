package com.yingtongding.pollApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class PollQuestion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question")
    @Type(type = "text")
    @Lob
    private String question;

    @ManyToOne
    @JsonIgnore
    private Poll poll;

    public PollQuestion() {
    }

    public PollQuestion(String question, Poll poll) {
        this.question = question;
        this.poll = poll;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
