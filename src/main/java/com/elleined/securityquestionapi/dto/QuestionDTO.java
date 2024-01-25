package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.QuestionController;
import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import lombok.Builder;
import lombok.Getter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class QuestionDTO extends HATEOUSLinker<QuestionDTO> {
    private final int id;
    private final String question;

    @Builder
    public QuestionDTO(int id, String question) {
        this.id = id;
        this.question = question;
    }

    @Override
    public QuestionDTO addLinks() {
        return addSelfLinks().addRelatedLinks();
    }

    @Override
    QuestionDTO addSelfLinks() {
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
        return this;
    }

    @Override
    QuestionDTO addRelatedLinks() {
        add(linkTo(SecurityQuestionController.class)
                .withRel("security-questions")
                .withTitle("Save user security question with specified question")
                .withType("POST")
        );
        return this;
    }
}
