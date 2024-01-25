package com.elleined.securityquestionapi.dto.question;

import com.elleined.securityquestionapi.dto.HateousLinker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class QuestionDTO extends HateousLinker<QuestionDTO> {
    private int id;
    private String question;
}
