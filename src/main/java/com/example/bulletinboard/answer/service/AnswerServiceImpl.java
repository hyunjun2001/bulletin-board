package com.example.bulletinboard.answer.service;

import com.example.bulletinboard.answer.domain.Answer;
import com.example.bulletinboard.answer.repository.AnswerRepository;
import com.example.bulletinboard.exception.DataNotFoundException;
import com.example.bulletinboard.question.domain.Question;
import com.example.bulletinboard.user.domain.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;


    @Override
    public Answer create(Question question, String context, SiteUser author) {
        Answer answer = new Answer();
        answer.setContext(context);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(author);
        question.addAnswer(answer);
        answerRepository.save(answer);
        return answer;
    }

    @Override
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    @Override
    public void modify(Answer answer, String context) {
        answer.setContext(context);
        answer.setModifyDate(LocalDateTime.now());
        answerRepository.save(answer);
    }

    @Override
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    @Override
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }


}
