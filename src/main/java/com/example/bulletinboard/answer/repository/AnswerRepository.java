package com.example.bulletinboard.answer.repository;

import com.example.bulletinboard.answer.domain.Answer;
import com.example.bulletinboard.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Page<Answer> findByQuestion(Question question, Pageable pageable);
}
