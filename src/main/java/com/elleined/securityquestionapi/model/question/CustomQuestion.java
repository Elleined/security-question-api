package com.elleined.securityquestionapi.model.question;


import com.elleined.securityquestionapi.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user_custom_question")
@NoArgsConstructor
@Getter
@Setter
public class CustomQuestion extends Question {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private User owner;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "answer",
            nullable = false,
            updatable = false
    )
    private String answer;

    @Builder(builderMethodName = "customQuestionBuilder")
    public CustomQuestion(int id, String question, User owner, LocalDateTime createdAt, String answer) {
        super(id, question);
        this.owner = owner;
        this.createdAt = createdAt;
        this.answer = answer;
    }
}
