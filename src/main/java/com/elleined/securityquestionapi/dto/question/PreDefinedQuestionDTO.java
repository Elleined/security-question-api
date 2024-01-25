package com.elleined.securityquestionapi.dto.question;

import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import com.elleined.securityquestionapi.controller.question.PreDefinedQuestionController;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@NoArgsConstructor
@Getter
@Setter
public class PreDefinedQuestionDTO extends QuestionDTO {

    @Builder(builderMethodName = "preDefinedQuestionDTOBuilder")
    public PreDefinedQuestionDTO(int id, String question) {
        super(id, question);
    }


    @Override
    public void addSelfLinks() {
        this.add(
                linkTo(methodOn(PreDefinedQuestionController.class).getAll())
                        .withSelfRel()
                        .withTitle("Get all pre defined question")
                        .withType("GET")
        );
    }

    @Override
    public void addRelatedLinks() {
        this.add(
                linkTo(methodOn(SecurityQuestionController.class).save(null, getId(), null))
                        .withRel("security-questions")
                        .withType("POST")
                        .withTitle("Save security question")
        );
    }
}
