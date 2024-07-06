package com.elleined.securityquestionapi.model.sq;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_user_defined_security_question")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserDefinedSecurityQuestion extends SecurityQuestion {

    @Column(
            name = "question",
            nullable = false,
            updatable = false,
            unique = true
    )
    private String question;
}
