package com.example.bulletinboard.answer.service;

import com.example.bulletinboard.answer.domain.Answer;
import com.example.bulletinboard.question.domain.Question;
import com.example.bulletinboard.user.domain.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerService {

    Answer create(Question question, String context, SiteUser user);

    Answer getAnswer(Integer id);

    void modify(Answer answer, String content);

    void delete(Answer answer);

    void vote(Answer answer, SiteUser siteUser);

    Page<Answer> getPagedAnswersByQuestion(Question question, int page);
}
