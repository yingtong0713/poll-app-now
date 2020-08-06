package com.yingtongding.pollApp.controller;

import com.yingtongding.pollApp.model.Poll;
import com.yingtongding.pollApp.model.PollQuestion;
import com.yingtongding.pollApp.service.PollQuestionService;
import com.yingtongding.pollApp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PollQuestionController {

    @Autowired
    PollQuestionService pollQuestionService;

    @Autowired
    PollService pollService;

    @GetMapping("polls/{pollId}/questions")
    public List<PollQuestion> getAllQuestionsByPollId(@PathVariable Long pollId) {
        return pollQuestionService.getAllPollQuestionsByPollId(pollId);
    }


    @PostMapping("polls/{pollId}/questions")
    public PollQuestion savePollQuestion(@RequestBody PollQuestion question, @PathVariable Long pollId) {
        Poll poll = pollService.getById(pollId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find post with id " + pollId.toString()));
        question.setPoll(poll);
        return pollQuestionService.createQuestion(question);
    }
}
