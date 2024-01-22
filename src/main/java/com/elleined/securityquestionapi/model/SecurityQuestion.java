package com.elleined.securityquestionapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
            name = "question",
            nullable = false,
            updatable = false,
            unique = true
    )
    private String question;

    @OneToMany(mappedBy = "securityQuestion")
    @Setter(AccessLevel.NONE)
    private List<UserSecurityQuestion> userSecurityQuestions;
}