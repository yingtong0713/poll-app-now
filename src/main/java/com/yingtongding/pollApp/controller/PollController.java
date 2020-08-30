package com.yingtongding.pollApp.controller;

import com.yingtongding.pollApp.model.Poll;
import com.yingtongding.pollApp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    PollService pollService;


    @GetMapping("/{id}")
    public Poll getById(@PathVariable UUID id) {
        return pollService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Poll createPoll(@RequestBody Poll newPoll) {
        return pollService.createPoll(newPoll);
    }


    @PutMapping("/{questionId}/vote")
    public Poll vote(@PathVariable UUID questionId) throws Exception {
        return pollService.vote(questionId);
    }


}
