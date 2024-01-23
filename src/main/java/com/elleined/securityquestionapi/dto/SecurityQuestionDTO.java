package com.elleined.securityquestionapi.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
public class SecurityQuestionDTO extends RepresentationModel<SecurityQuestionDTO> {
    private final int id;
    private final LocalDateTime createdAt;
    private final int questionId;
    private final int ownerId;
    private final String answer;

    @Builder
    public SecurityQuestionDTO(int id, LocalDateTime createdAt, int questionId, int ownerId, String answer) {
        this.id = id;
        this.createdAt = createdAt;
        this.questionId = questionId;
        this.ownerId = ownerId;
        this.answer = answer;
    }
}
