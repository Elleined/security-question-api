package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import com.elleined.securityquestionapi.controller.UserController;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

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
        this.add(linkTo(methodOn(UserController.class).getById(id))
                .withSelfRel()
                .andAffordance(afford(methodOn(UserController.class).save(null))));
        return this;
    }

    @Override
    public UserDTO addRelatedLinks() {
        this.add(
                linkTo(methodOn(SecurityQuestionController.class).getAllByUser(id))
                        .withRel("security-questions"),
                linkTo(methodOn(SecurityQuestionController.class).save(id, 0, null))
                        .withRel("security-questions")
                        .withType("POST")
                        .withTitle("Save security question"),
                linkTo(methodOn(SecurityQuestionController.class).isAnswerCorrect(id, 0, null))
                        .withRel("security-questions")
        );
        return this;
    }
}
