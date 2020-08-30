package com.yingtongding.pollApp.service;

import com.yingtongding.pollApp.model.Poll;
import com.yingtongding.pollApp.model.PollQuestion;
import com.yingtongding.pollApp.model.Voter;
import com.yingtongding.pollApp.repository.PollQuestionRepository;
import com.yingtongding.pollApp.repository.PollRepository;
import com.yingtongding.pollApp.repository.VoterRepository;
import com.yingtongding.pollApp.utils.HttpReqRespUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollQuestionRepository pollQuestionRepository;

    @Autowired
    VoterRepository voterRepository;

    public Optional<Poll> getById(UUID id) {
        return pollRepository.findById(id);
    }

    public Poll createPoll(Poll poll) {
        List<PollQuestion> questions = poll.getPollQuestions();
        if (questions != null) {
            questions.stream()
                    .forEach(question -> {
                        question.setPoll(poll);
                        pollQuestionRepository.save(question);
                    });
        } else {
            pollQuestionRepository.save(new PollQuestion());
        }
        return pollRepository.save(poll);
    }


    public Poll vote(UUID questionId) throws Exception {
        String IpAddress = HttpReqRespUtils.getClientIpAddressIfServletRequestExist();
        Voter currentVoter = new Voter();
        PollQuestion question = pollQuestionRepository.findById(questionId)
                .orElseThrow(() -> new Exception("Question " + questionId + " not found"));
        Poll poll = question.getPoll();
        if (voterRepository.findAllByPollId(poll.getId()).isEmpty()) {
            question.setNumberOfPeopleSelected(question.getNumberOfPeopleSelected() + 1);
            pollQuestionRepository.save(question);
            currentVoter.setIpAddress(IpAddress);
            currentVoter.setPoll(poll);
            voterRepository.save(currentVoter);
        }
        System.out.println("IP address " + IpAddress + " has voted");
        return poll;
    }

    public String getIpAddress() {
        return HttpReqRespUtils.getClientIpAddressIfServletRequestExist();
    }

}




