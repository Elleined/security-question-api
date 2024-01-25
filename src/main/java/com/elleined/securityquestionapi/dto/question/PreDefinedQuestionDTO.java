package com.elleined.securityquestionapi.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PreDefinedQuestionDTO extends QuestionDTO {

    @Builder(builderMethodName = "preDefinedQuestionDTOBuilder")
    public PreDefinedQuestionDTO(int id, String question) {
        super(id, question);
    }
}
