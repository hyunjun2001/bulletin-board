package com.example.bulletinboard.question.service;

import com.example.bulletinboard.question.domain.Question;
import com.example.bulletinboard.user.domain.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface QuestionService {

    Page<Question> getList(int page, String kw);

    Question getQuestion(Integer id);

    void create(String subject, String context, SiteUser siteUser);

    void modify(Question question, String subject, String content);

    void delete(Question question);

    void vote(Question question, SiteUser siteUser);

    Specification<Question> search(String kw);

}
