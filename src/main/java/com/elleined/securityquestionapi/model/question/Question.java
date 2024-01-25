package com.elleined.securityquestionapi.model.question;

import com.elleined.securityquestionapi.model.SecurityQuestion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_question")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Question {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "questionAutoIncrement"
    )
    @SequenceGenerator(
            allocationSize = 1,
            name = "questionAutoIncrement",
            sequenceName = "questionAutoIncrement"
    )
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
