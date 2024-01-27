package com.elleined.securityquestionapi.dto.question;

import com.elleined.securityquestionapi.dto.SecurityQuestionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PreDefinedQuestionDTO extends QuestionDTO {

    private List<SecurityQuestionDTO> securityQuestionDTOS;

    public PreDefinedQuestionDTO(int id, String question, List<SecurityQuestionDTO> securityQuestionDTOS) {
        super(id, question);
        this.securityQuestionDTOS = securityQuestionDTOS;
    }
}
