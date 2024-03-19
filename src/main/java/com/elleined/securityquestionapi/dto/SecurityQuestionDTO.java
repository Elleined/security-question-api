package com.elleined.securityquestionapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SecurityQuestionDTO {
    private final int id;
    private final LocalDateTime createdAt;
    private final String answer;

    private final int ownerId;
    private final int preDefinedQuestionId;

    @Builder
    public SecurityQuestionDTO(int id, LocalDateTime createdAt, String answer, int ownerId, int preDefinedQuestionId) {
        this.id = id;
        this.createdAt = createdAt;
        this.answer = answer;
        this.ownerId = ownerId;
        this.preDefinedQuestionId = preDefinedQuestionId;
    }
}
