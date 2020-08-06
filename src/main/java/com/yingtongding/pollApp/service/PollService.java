package com.yingtongding.pollApp.service;

import com.yingtongding.pollApp.model.Poll;
import com.yingtongding.pollApp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public Optional<Poll> getById(Long id){
        return pollRepository.findById(id);
    }
}