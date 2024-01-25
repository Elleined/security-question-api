package com.elleined.securityquestionapi.model.question;

import com.elleined.securityquestionapi.model.SecurityQuestion;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_pre_defined_question")
@NoArgsConstructor
@Getter
@Setter
public class PreDefinedQuestion extends Question {

    @Builder(builderMethodName = "preDefinedQuestionBuilder")
    public PreDefinedQuestion(int id, String question, List<SecurityQuestion> securityQuestions) {
        super(id, question, securityQuestions);
    }
}
