package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.controller.PreDefinedQuestionController;
import com.elleined.securityquestionapi.controller.sq.PreDefinedSecurityQuestionController;
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
public class PreDefinedQuestionDTO extends DTO {
    private String question;

    @Builder
    public PreDefinedQuestionDTO(int id, LocalDateTime createdAt, String question) {
        super(id, createdAt);
        this.question = question;
    }

    @Override
    public PreDefinedQuestionDTO addLinks(User currentUser, boolean doInclude) {
        super.addLinks(currentUser, doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(User currentUser, boolean doInclude) {
        return List.of(
                linkTo(methodOn(PreDefinedSecurityQuestionController.class)
                        .save(currentUser.getId(), this.getId(), null, doInclude))
                        .withRel("pre-defined-security-question")
                        .withTitle("Save pre defined security question")
                        .withType(HttpMethod.POST.name())
        );
    }

    @Override
    protected List<Link> getAllSelfLinks(User currentUser, boolean doInclude) {
        return List.of(
                linkTo(methodOn(PreDefinedQuestionController.class)
                        .getAll(currentUser.getId(), 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name())
        );
    }
}
