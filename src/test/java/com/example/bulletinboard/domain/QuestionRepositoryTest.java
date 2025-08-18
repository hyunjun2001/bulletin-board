package com.example.bulletinboard.domain;

import com.example.bulletinboard.question.domain.Question;
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
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    private Integer sample1Id;
    private Integer sample2Id;

    // 추후 testdatainit으로 제거 예정
    @BeforeEach
    void beforeEach() {
        Question sample_1 = questionRepository.save(new Question("스프링이 뭐지?", "스프링이 뭔지 알려줘", LocalDateTime.now()));
        Question sample_2 = questionRepository.save(new Question("스프링은 어려워?", "스프링 난이도 알려줘", LocalDateTime.now()));

        // 저장 후 실제 ID 저장
        sample1Id = sample_1.getId();
        sample2Id = sample_2.getId();
    }

    @Test
    void saveQuestionRepository() {
        Question q1 = new Question();
        q1.setSubject("스프링이 뭐지?");
        q1.setContext("스프링이 뭔지 알려줘");
        q1.setCreateDate(LocalDateTime.now());

        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링은 어려워?");
        q2.setContext("스프링 난이도 알려줘");
        q2.setCreateDate(LocalDateTime.now());

        questionRepository.save(q2);
    }

    @Test
    void searchQuestionRepository() {
        List<Question> questions = questionRepository.findAll();
        assertThat(questions.size()).isEqualTo(2);

        Question q1 = questions.get(0);
        assertThat(q1.getSubject()).isEqualTo("스프링이 뭐지?");
    }

    @Test
    void findByIdQuestionRepository() {
        Optional<Question> question = questionRepository.findById(1);
        if (question.isPresent()) {
            Question q1 = question.get();
            assertThat(q1.getSubject()).isEqualTo("스프링이 뭐지?");
        }
    }
    
    @Test
    void findBySubjectQuestionRepository() {
        Question question = questionRepository.findBySubject("스프링이 뭐지?");
        assertThat(question.getId()).isEqualTo(sample1Id);
    }

    @Test
    void findBySubjectAndContent() {
        Question question = questionRepository
                .findBySubjectAndContext("스프링이 뭐지?", "스프링이 뭔지 알려줘");
        assertThat(question.getId()).isEqualTo(sample1Id);
    }

    @Test
    void findBySubjectLike() {
        List<Question> questions = questionRepository.findBySubjectLike("%스%");
        for (Question question : questions) {
            log.info("question = {}", question);
        }
        Question question = questions.get(0);
        assertThat(question.getId()).isEqualTo(sample1Id);
    }

    // 이후 비즈니스 메서드로 전환 예정
    @Test
    void updateQuestion() {
        Optional<Question> question = questionRepository.findById(1);
        if (question.isPresent()) {
            Question q1 = question.get();
            q1.setSubject("수정된 제목");
            log.info("q1 수정 체크 = {}", q1.getSubject());
            questionRepository.save(q1);
        }
    }

    @Test
    void deleteQuestion() {
        if (questionRepository.findById(1).isPresent()) {
            Optional<Question> question = questionRepository.findById(1);
            Question q1 = question.get();
            questionRepository.delete(q1);
            assertThat(questionRepository.count()).isEqualTo(1);
        }
    }

}
