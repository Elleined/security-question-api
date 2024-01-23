package com.elleined.securityquestionapi.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
public class SecurityQuestionDTO extends RepresentationModel<SecurityQuestionDTO> {
    private int id;
    private LocalDateTime createdAt;
    private int questionId;
    private int ownerId;
    private String answer;

    @Builder
    public SecurityQuestionDTO(int id, LocalDateTime createdAt, int questionId, int ownerId, String answer) {
        this.id = id;
        this.createdAt = createdAt;
        this.questionId = questionId;
        this.ownerId = ownerId;
        this.answer = answer;
    }
}
