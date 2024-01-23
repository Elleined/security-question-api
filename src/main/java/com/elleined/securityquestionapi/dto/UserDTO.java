package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.QuestionController;
import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import com.elleined.securityquestionapi.controller.UserController;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpMethod;

import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class UserDTO extends RepresentationModel<UserDTO> implements HATEOUSLinker<UserDTO> {
    private final int id;
    private final String name;

    @Builder
    public UserDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public UserDTO addLinks() {
        return this.addSelfLinks()
                .addRelatedLinks();
    }

    @Override
    public UserDTO addSelfLinks() {
        // Only add link if guard is true
        this.addIf(true, () -> linkTo(methodOn(UserController.class).save(null)).withSelfRel());

        this.add(
                linkTo(methodOn(UserController.class).getById(id))
                        .withSelfRel()
                        .withType("GET")
                        .withTitle("Get by id")
                        .andAffordance(afford(methodOn(UserController.class).save(null)))
        );
        return this;
    }

    @Override
    public UserDTO addRelatedLinks() {
        // Security Question
        this.add(
                linkTo(methodOn(SecurityQuestionController.class).getAllByUser(id))
                        .withRel("security-questions")
                        .withTitle("Get all current user security questions")
                        .withType("GET"),
                linkTo(methodOn(SecurityQuestionController.class).save(id, 0, null))
                        .withRel("security-questions")
                        .withType("POST")
                        .withTitle("Save security question"),
                linkTo(methodOn(SecurityQuestionController.class).isAnswerCorrect(id, 0, null))
                        .withRel("security-questions")
                        .withTitle("Check if security answer is correct")
                        .withType("GET")
        );

        // Question
        this.add(
                linkTo(methodOn(QuestionController.class).save(null))
                        .withRel("questions")
                        .withTitle("Create question")
                        .withType("POST"),
                linkTo(methodOn(QuestionController.class).getAll())
                        .withRel("questions")
                        .withTitle("Get all question")
                        .withType("GET")
        );
        return this;
    }
}
