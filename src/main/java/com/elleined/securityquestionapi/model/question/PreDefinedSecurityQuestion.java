package com.elleined.securityquestionapi.model.question;

import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_pre_defined_security_question")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PreDefinedSecurityQuestion extends SecurityQuestion {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "pre_defined_question_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private PreDefinedQuestion preDefinedQuestion;
}
