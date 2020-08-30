package com.yingtongding.pollApp.repository;

import com.yingtongding.pollApp.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VoterRepository extends JpaRepository<Voter, UUID> {
    List<Voter> findAllByPollId(UUID pollId);
}
