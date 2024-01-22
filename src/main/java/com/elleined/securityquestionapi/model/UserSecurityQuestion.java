package com.elleined.securityquestionapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_user_security_question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSecurityQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "security_question_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private SecurityQuestion securityQuestion;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private User user;

    @Column(
            name = "answer",
            nullable = false
    )
    private String answer;
}