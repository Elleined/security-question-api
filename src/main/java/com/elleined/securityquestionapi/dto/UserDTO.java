package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.UserController;
import com.elleined.securityquestionapi.controller.sq.PreDefinedSecurityQuestionController;
import com.elleined.securityquestionapi.controller.sq.UserDefinedSecurityQuestionController;
import com.elleined.securityquestionapi.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class UserDTO extends DTO {
    private String name;

    @Builder
    public UserDTO(int id, LocalDateTime createdAt, String name) {
        super(id, createdAt);
        this.name = name;
    }

    @Override
    public UserDTO addLinks(User currentUser, boolean doInclude) {
        super.addLinks(currentUser, doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(User currentUser, boolean doInclude) {
        return List.of(
                linkTo(methodOn(PreDefinedSecurityQuestionController.class)
                        .save(currentUser.getId(), 0, null, doInclude))
                        .withRel("pre-defined-security-question")
                        .withTitle("Save pre defined security question")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(UserDefinedSecurityQuestionController.class)
                        .save(currentUser.getId(), null, null, doInclude))
                        .withRel("user-defined-security-question")
                        .withTitle("Save user defined security question")
                        .withType(HttpMethod.POST.name())
        );
    }

    @Override
    protected List<Link> getAllSelfLinks(User currentUser, boolean doInclude) {
        return List.of(
                linkTo(methodOn(UserController.class)
                        .save(null, doInclude))
                        .withSelfRel()
                        .withTitle("Save")
                        .withType(HttpMethod.POST.name())
        );
    }
}
