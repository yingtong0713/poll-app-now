package com.yingtongding.pollApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingtongding.pollApp.utils.HttpReqRespUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "poll")
public class Poll {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "IPAddress")
    private String ip=HttpReqRespUtils.getClientIpAddressIfServletRequestExist();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poll")
    private List<PollQuestion> pollQuestions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poll")
    private List<Voter> voters;

    public Poll() {
    }

    public Poll(String title, String ip, List<PollQuestion> pollQuestions, List<Voter> voters) {
        this.title = title;
        this.ip = ip;
        this.pollQuestions = pollQuestions;
        this.voters = voters;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIp() {
        return ip;
    }

    public List<PollQuestion> getPollQuestions() {
        return pollQuestions;
    }

    public void setPollQuestions(List<PollQuestion> pollQuestions) {
        this.pollQuestions = pollQuestions;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }
}
