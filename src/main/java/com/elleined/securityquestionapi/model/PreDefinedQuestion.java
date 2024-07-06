package com.elleined.securityquestionapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

// A pre-defined list of questions
@Entity
@Table(name = "tbl_pre_defined_question")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PreDefinedQuestion extends PrimaryKeyIdentity {

    @Column(
            name = "question",
            nullable = false,
            updatable = false,
            unique = true
    )
    private String question;
}
