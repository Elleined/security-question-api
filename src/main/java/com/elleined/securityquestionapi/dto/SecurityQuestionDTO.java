package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import com.elleined.securityquestionapi.controller.UserController;
import com.elleined.securityquestionapi.controller.question.PreDefinedQuestionController;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class SecurityQuestionDTO extends HateousLinker<SecurityQuestionDTO> {
    private final int id;
    private final LocalDateTime createdAt;
    private final int questionId;
    private final int ownerId;
    private final String answer;

    @Builder
    public SecurityQuestionDTO(int id, LocalDateTime createdAt, int questionId, int ownerId, String answer) {
        this.id = id;
        this.createdAt = createdAt;
        this.questionId = questionId;
        this.ownerId = ownerId;
        this.answer = answer;
        addLinks();
    }

    @Override
    public void addSelfLinks() {
        this.add(
                linkTo(methodOn(SecurityQuestionController.class).getAllByUser(null))
                        .withSelfRel()
                        .withTitle("Get all current user security questions")
                        .withType("GET"),
                linkTo(methodOn(SecurityQuestionController.class).save(null, null, null))
                        .withSelfRel()
                        .withType("POST")
                        .withTitle("Save security question"),
                linkTo(methodOn(SecurityQuestionController.class).isAnswerCorrect(null, id, null))
                        .withSelfRel()
                        .withTitle("Check if security question answer is correct")
                        .withType("GET")
        );
    }

    @Override
    public void addRelatedLinks() {
        this.add(linkTo(methodOn(UserController.class).getById(ownerId)).withRel("owner"));
        this.add(linkTo(methodOn(PreDefinedQuestionController.class).getById(questionId)).withRel("question"));
    }
}
