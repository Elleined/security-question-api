package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import com.elleined.securityquestionapi.controller.UserController;
import com.elleined.securityquestionapi.controller.question.CustomQuestionController;
import com.elleined.securityquestionapi.controller.question.PreDefinedQuestionController;
import lombok.Builder;
import lombok.Getter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Getter
public class UserDTO extends HateousLinker<UserDTO> {
    private final int id;
    private final String name;

    @Builder
    public UserDTO(int id, String name) {
        this.id = id;
        this.name = name;
        addLinks();
    }

    @Override
    public void addSelfLinks() {
        // Only add link if guard is true
        // this.addIf(false, () -> linkTo(methodOn(UserController.class).save(null)).withSelfRel());

        this.add(
                linkTo(methodOn(UserController.class).getById(id))
                        .withSelfRel()
                        .withType("GET")
                        .withTitle("Get by id")
                        .andAffordance(afford(methodOn(UserController.class).save(null)))
        );
    }

    @Override
    public void addRelatedLinks() {
        // Pre Defined Question
        this.add(
                linkTo(methodOn(PreDefinedQuestionController.class).getAll())
                        .withRel("pre-defined-questions")
                        .withTitle("Get all pre defined question")
                        .withType("GET")
        );

        // Pre defined security question
        this.add(
                linkTo(methodOn(SecurityQuestionController.class).getAllByUser(id))
                        .withRel("pre-defined-security-question")
                        .withTitle("Get all current user security questions")
                        .withType("GET"),
                linkTo(methodOn(SecurityQuestionController.class).save(id, null, null))
                        .withRel("pre-defined-security-question")
                        .withType("POST")
                        .withTitle("Save security question"),
                linkTo(methodOn(SecurityQuestionController.class).isAnswerCorrect(id, null, null))
                        .withRel("pre-defined-security-question")
                        .withTitle("Check if security question answer is correct")
                        .withType("GET")
        );

        // Custom security question
        this.add(
                linkTo(methodOn(CustomQuestionController.class).getAll(id))
                        .withRel("custom-security-question")
                        .withTitle("Get all question by owner")
                        .withType("GET"),
                linkTo(methodOn(CustomQuestionController.class).isAnswerCorrect(id, null, null))
                        .withRel("custom-security-question")
                        .withTitle("Check if security question answer is correct")
                        .withType("GET"),
                linkTo(methodOn(CustomQuestionController.class).save(id, null, null))
                        .withRel("custom-security-question")
                        .withTitle("Create security question")
                        .withType("POST")
        );
    }
}
