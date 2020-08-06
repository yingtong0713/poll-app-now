package com.yingtongding.pollApp.service;

import com.yingtongding.pollApp.model.PollQuestion;
import com.yingtongding.pollApp.repository.PollQuestionRepository;
import com.yingtongding.pollApp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollQuestionService {

    @Autowired
    PollQuestionRepository pollQuestionRepository;


    public List<PollQuestion> getAllPollQuestionsByPollId(Long pollId){
        return  pollQuestionRepository.findAllByPollId(pollId);
    }

    public PollQuestion createQuestion(PollQuestion question) {
        return pollQuestionRepository.save(question);
    }

}
