package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.question.PreDefinedQuestionController;
import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import com.elleined.securityquestionapi.controller.UserController;
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
        // Security Question
        this.add(
                linkTo(methodOn(SecurityQuestionController.class).getAllByUser(id))
                        .withRel("security-questions")
                        .withTitle("Get all current user security questions")
                        .withType("GET"),
                linkTo(methodOn(SecurityQuestionController.class).save(id, null, null))
                        .withRel("security-questions")
                        .withType("POST")
                        .withTitle("Save security question"),
                linkTo(methodOn(SecurityQuestionController.class).isAnswerCorrect(id, null, null))
                        .withRel("security-questions")
                        .withTitle("Check if security question answer is correct")
                        .withType("GET")
        );

        // Question
        this.add(
                linkTo(methodOn(PreDefinedQuestionController.class).save(null))
                        .withRel("questions")
                        .withTitle("Create custom question")
                        .withType("POST"),
                linkTo(methodOn(PreDefinedQuestionController.class).getAll())
                        .withRel("questions")
                        .withTitle("Get all question")
                        .withType("GET")
        );
    }
}
