package com.elleined.securityquestionapi.dto.question;

import com.elleined.securityquestionapi.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CustomQuestionDTO extends QuestionDTO {

    private LocalDateTime createdAt;
    private String answer;

    private UserDTO owner;

    public CustomQuestionDTO(int id, String question, LocalDateTime createdAt, String answer, UserDTO owner) {
        super(id, question);
        this.createdAt = createdAt;
        this.answer = answer;
        this.owner = owner;
    }
}
