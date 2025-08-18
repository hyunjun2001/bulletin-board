package com.example.bulletinboard.question.domain;

import com.example.bulletinboard.answer.domain.Answer;
import com.example.bulletinboard.user.domain.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String context;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Answer> answerList = new ArrayList<>();

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    public Question() {
    }

    public Question(String subject, String context, LocalDateTime createDate) {
        this.subject = subject;
        this.context = context;
        this.createDate = createDate;
    }

    public void addAnswer(Answer answer) {
        answerList.add(answer);
        answer.setQuestion(this);
    }
}
