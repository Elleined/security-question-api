package com.elleined.securityquestionapi.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class QuestionDTO extends RepresentationModel<QuestionDTO> {
    private int id;
    private String question;
}
