package com.elleined.securityquestionapi.model;

import com.elleined.securityquestionapi.model.question.Question;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_security_question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SecurityQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private int id;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "answer",
            nullable = false
    )
    private String answer;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "question_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Question question;
}
