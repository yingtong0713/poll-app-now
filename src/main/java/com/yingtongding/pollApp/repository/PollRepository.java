package com.yingtongding.pollApp.repository;

import com.yingtongding.pollApp.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface PollRepository extends JpaRepository<Poll, UUID> {
}
