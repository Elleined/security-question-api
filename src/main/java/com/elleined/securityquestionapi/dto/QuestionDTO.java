package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.QuestionController;
import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import lombok.Builder;
import lombok.Getter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class QuestionDTO extends HateousLinker<QuestionDTO> {
    private final int id;
    private final String question;

    @Builder
    public QuestionDTO(int id, String question) {
        this.id = id;
        this.question = question;
        addLinks();
    }

    @Override
    public void addSelfLinks() {
        add(linkTo(methodOn(QuestionController.class).getAll())
                .withSelfRel()
                .withTitle("Get all questions")
                .withType("GET")
        );
        add(linkTo(methodOn(QuestionController.class).save(null))
                .withSelfRel()
                .withTitle("Save question")
                .withType("POST")
        );
    }

    public void addRelatedLinks() {
        add(linkTo(methodOn(SecurityQuestionController.class).save(null, id, null))
                .withRel("security-questions")
                .withTitle("Save user security question with specified question")
                .withType("POST")
        );
    }
}
