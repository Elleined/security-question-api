package com.elleined.securityquestionapi.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PreDefinedQuestionDTO extends QuestionDTO {

    private List<Integer> securityQuestionIds;

    @Builder
    public PreDefinedQuestionDTO(int id, String question, List<Integer> securityQuestionIds) {
        super(id, question);
        this.securityQuestionIds = securityQuestionIds;
    }
}
