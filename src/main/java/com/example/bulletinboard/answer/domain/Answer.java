package com.example.bulletinboard.answer.domain;

import com.example.bulletinboard.question.domain.Question;
import com.example.bulletinboard.user.domain.SiteUser;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Answer {

    @Id @GeneratedValue
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String context;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    public Answer() {
    }

    public Answer(String context, LocalDateTime createDate) {
        this.context = context;
        this.createDate = createDate;
    }
}
