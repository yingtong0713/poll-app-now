package com.yingtongding.pollApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "question")
public class PollQuestion {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "question")
    @Type(type = "text")
    @Lob
    private String question;

    @Column(name = "count")
    private int numberOfPeopleSelected=0;


    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    @JsonIgnore
    private Poll poll;

    public PollQuestion() {
    }

    public PollQuestion(String question, int numberOfPeopleSelected, Poll poll) {
        this.question = question;
        this.numberOfPeopleSelected = numberOfPeopleSelected;
        this.poll = poll;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumberOfPeopleSelected() {
        return numberOfPeopleSelected;
    }

    public void setNumberOfPeopleSelected(int numberOfPeopleSelected) {
        this.numberOfPeopleSelected = numberOfPeopleSelected;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
