package com.elleined.securityquestionapi.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class QuestionDTO {
    private int id;
    private String question;
}
