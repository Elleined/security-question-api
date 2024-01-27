package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.dto.question.PreDefinedQuestionDTO;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
public class SecurityQuestionDTO extends RepresentationModel<SecurityQuestionDTO> {
    private final int id;
    private final LocalDateTime createdAt;
    private final String answer;

    private final UserDTO owner;
    private final PreDefinedQuestionDTO preDefinedQuestionDTO;

    public SecurityQuestionDTO(int id, LocalDateTime createdAt, String answer, UserDTO owner, PreDefinedQuestionDTO preDefinedQuestionDTO) {
        this.id = id;
        this.createdAt = createdAt;
        this.answer = answer;
        this.owner = owner;
        this.preDefinedQuestionDTO = preDefinedQuestionDTO;
    }
}
