package com.elleined.securityquestionapi.model.question;

import com.elleined.securityquestionapi.model.SecurityQuestion;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_pre_defined_question")
@NoArgsConstructor
@Getter
@Setter
public class PreDefinedQuestion extends Question {

    @OneToMany(mappedBy = "preDefinedQuestion")
    @Setter(AccessLevel.NONE)
    private List<SecurityQuestion> securityQuestions;

    @Builder(builderMethodName = "preDefinedQuestionBuilder")
    public PreDefinedQuestion(int id, String question, List<SecurityQuestion> securityQuestions) {
        super(id, question);
        this.securityQuestions = securityQuestions;
    }
}
