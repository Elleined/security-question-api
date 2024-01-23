package com.elleined.securityquestionapi.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class QuestionDTO extends RepresentationModel<QuestionDTO> {
    private final int id;
    private final String question;

    public QuestionDTO(int id, String question) {
        this.id = id;
        this.question = question;
    }
}
