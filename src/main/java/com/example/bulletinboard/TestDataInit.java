package com.example.bulletinboard;

import com.example.bulletinboard.answer.domain.Answer;
import com.example.bulletinboard.answer.repository.AnswerRepository;
import com.example.bulletinboard.question.domain.Question;
import com.example.bulletinboard.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 테스트 요건이 복잡해질 경우 추후 클래스 단위로 분리 및 @profile을 통한 제어 예정
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class TestDataInit {
    

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {

        if (questionRepository.count() > 0) {
            log.info("기존 데이터 있어서 추가하지 않습니다.");
            return;
        }

//        initSmallTestData();
        initLargeTestData();
    }

    private void initSmallTestData() {
        log.info("소규모 테스트 데이터 삽입");

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
        answerRepository.save(sample_A1);
        answerRepository.save(sample_A2);
    }

    private void initLargeTestData() {
        log.info("대규모 질문 테스트 데이터 삽입");

        for (int i = 0; i <= 100; i++) {
            String subject = String.format("테스트 데이터입니다.[%03d]", i);
            String context = "서비스 운영 환경을 점검하기 위한 테스트 데이터입니다.";
            questionRepository.save(new Question(subject, context, LocalDateTime.now()));
        }

    }
}
