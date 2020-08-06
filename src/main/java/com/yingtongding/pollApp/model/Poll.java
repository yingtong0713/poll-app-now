package com.yingtongding.pollApp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poll")
public class Poll {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<PollQuestion> pollQuestions=new ArrayList<>();

    public Poll() {
    }

    public Poll(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
