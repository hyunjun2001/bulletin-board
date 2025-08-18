package com.example.bulletinboard.domain;

import com.example.bulletinboard.answer.domain.Answer;
import com.example.bulletinboard.question.domain.Question;
import com.example.bulletinboard.answer.repository.AnswerRepository;
import com.example.bulletinboard.question.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class AnswerRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private Integer sampleQ1Id;
    private Integer sampleQ2Id;

    private Integer sampleA1Id;
    private Integer sampleA2Id;

    // 추후 testdatainit으로 제거 예정
    @BeforeEach
    void beforeEach() {
        Question sample_Q1 = new Question("스프링이 뭐지?", "스프링이 뭔지 알려줘", LocalDateTime.now());
        Question sample_Q2 = new Question("스프링은 어려워?", "스프링 난이도 알려줘", LocalDateTime.now());

        Answer sample_A1 = new Answer("스프링은 스프링이야", LocalDateTime.now());
        Answer sample_A2 = new Answer("스프링은 어려워", LocalDateTime.now());

        // 편의 메서드를 이용해 양방향 연관관계 설정
        sample_Q1.addAnswer(sample_A1);
        sample_Q2.addAnswer(sample_A2);

        // cascade 덕분에 answer도 같이 저장됨
        questionRepository.save(sample_Q1);
        questionRepository.save(sample_Q2);

        // ID 저장
        sampleQ1Id = sample_Q1.getId();
        sampleQ2Id = sample_Q2.getId();
        sampleA1Id = sample_A1.getId();
        sampleA2Id = sample_A2.getId();

    }

    @Test
    void saveAnswerRepository() {
        Optional<Question> question = questionRepository.findById(sampleQ1Id);
        if (question.isPresent()) {
            Question question1 = question.get();
            Answer answer = new Answer("스프링은 스프링이야", LocalDateTime.now());
            question1.addAnswer(answer);
            answerRepository.save(answer);
            log.info("answer = {}", answer);
        }
    }

    @Test
    void searchAnswerRepository() {
        Optional<Answer> answer = answerRepository.findById(sampleA1Id);
        if (answer.isPresent()) {
            Answer answer1 = answer.get();
            log.info("answer1 = {}", answer1);
        }
    }

    @Test
    void searchQuestions() {
        Optional<Question> question = questionRepository.findById(sampleQ1Id);
        if (question.isPresent()) {
            Question question1 = question.get();
            List<Answer> answerList = question1.getAnswerList();
            assertThat(answerList.size()).isEqualTo(1);
            for (Answer answer : answerList) {
                log.info("답변들 answer = {}", answer);
            }
        }
    }
}
