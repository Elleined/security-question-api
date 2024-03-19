package com.elleined.securityquestionapi.dto.question;

import com.elleined.securityquestionapi.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomQuestionDTO extends QuestionDTO {

    private final LocalDateTime createdAt;
    private final String answer;

    private final int ownerId;

    @Builder
    public CustomQuestionDTO(int id, String question, LocalDateTime createdAt, String answer, int ownerId) {
        super(id, question);
        this.createdAt = createdAt;
        this.answer = answer;
        this.ownerId = ownerId;
    }
}
