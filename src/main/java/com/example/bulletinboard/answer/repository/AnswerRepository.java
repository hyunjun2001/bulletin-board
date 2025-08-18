package com.example.bulletinboard.answer.repository;

import com.example.bulletinboard.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
