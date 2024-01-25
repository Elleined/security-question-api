package com.elleined.securityquestionapi.model.question;


import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_user_custom_question")
@NoArgsConstructor
@Getter
@Setter
public class CustomQuestion extends Question {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private User owner;

    @Builder(builderMethodName = "customQuestionBuilder")
    public CustomQuestion(int id, String question, List<SecurityQuestion> securityQuestions, User owner) {
        super(id, question, securityQuestions);
        this.owner = owner;
    }
}
