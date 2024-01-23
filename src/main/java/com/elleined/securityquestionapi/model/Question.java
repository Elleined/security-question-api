package com.elleined.securityquestionapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {

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

    @OneToMany(mappedBy = "question")
    @Setter(AccessLevel.NONE)
    private List<SecurityQuestion> securityQuestions;
}
