package com.elleined.securityquestionapi.dto.sq;

import com.elleined.securityquestionapi.controller.sq.UserDefinedSecurityQuestionController;
import com.elleined.securityquestionapi.dto.UserDTO;
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
public class UserDefinedSecurityQuestionDTO extends SecurityQuestionDTO {
    private String question;

    @Builder
    public UserDefinedSecurityQuestionDTO(int id, LocalDateTime createdAt, UserDTO ownerDTO, String question) {
        super(id, createdAt, ownerDTO);
        this.question = question;
    }

    @Override
    public UserDefinedSecurityQuestionDTO addLinks(User currentUser, boolean doInclude) {
        super.addLinks(currentUser, doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(User currentUser, boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(User currentUser, boolean doInclude) {
        return List.of(
                linkTo(methodOn(UserDefinedSecurityQuestionController.class)
                        .save(currentUser.getId(), null, null, doInclude))
                        .withRel("user-defined-security-question")
                        .withTitle("Save user defined security question")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(UserDefinedSecurityQuestionController.class)
                        .getAll(currentUser.getId(), 0, 0, null, null, doInclude))
                        .withRel("user-defined-security-question")
                        .withTitle("Get all user defined security questions")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(UserDefinedSecurityQuestionController.class)
                        .isAnswerCorrect(currentUser.getId(), 0, null))
                        .withRel("user-defined-security-question")
                        .withTitle("Check answer")
                        .withType(HttpMethod.GET.name())
        );
    }
}
