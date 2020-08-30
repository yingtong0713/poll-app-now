package com.yingtongding.pollApp.repository;

import com.yingtongding.pollApp.model.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface PollQuestionRepository extends JpaRepository<PollQuestion, UUID> {

    List<PollQuestion> findAllByPollId(UUID pollId);
}
