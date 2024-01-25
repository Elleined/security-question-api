package com.elleined.securityquestionapi.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomQuestionDTO extends QuestionDTO {
    private int ownerId;

    @Builder(builderMethodName = "customQuestionDTOBuilder")
    public CustomQuestionDTO(int id, String question, int ownerId) {
        super(id, question);
        this.ownerId = ownerId;
    }

}
