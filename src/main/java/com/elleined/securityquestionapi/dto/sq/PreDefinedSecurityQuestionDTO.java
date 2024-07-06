package com.elleined.securityquestionapi.dto.sq;

import com.elleined.securityquestionapi.controller.sq.PreDefinedSecurityQuestionController;
import com.elleined.securityquestionapi.controller.sq.UserDefinedSecurityQuestionController;
import com.elleined.securityquestionapi.dto.PreDefinedQuestionDTO;
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
public class PreDefinedSecurityQuestionDTO extends SecurityQuestionDTO {
    private PreDefinedQuestionDTO preDefinedQuestionDTO;

    @Builder
    public PreDefinedSecurityQuestionDTO(int id,
                                         LocalDateTime createdAt,
                                         UserDTO ownerDTO,
                                         PreDefinedQuestionDTO preDefinedQuestionDTO) {
        super(id, createdAt, ownerDTO);
        this.preDefinedQuestionDTO = preDefinedQuestionDTO;
    }

    @Override
    public PreDefinedSecurityQuestionDTO addLinks(User currentUser, boolean doInclude) {
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
                linkTo(methodOn(PreDefinedSecurityQuestionController.class)
                        .save(currentUser.getId(), 0, null, doInclude))
                        .withRel("pre-defined-security-question")
                        .withTitle("Save pre defined security question")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(PreDefinedSecurityQuestionController.class)
                        .getAll(currentUser.getId(), 0, 0, null, null, doInclude))
                        .withRel("pre-defined-security-question")
                        .withTitle("Get all pre defined security questions")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(UserDefinedSecurityQuestionController.class)
                        .isAnswerCorrect(currentUser.getId(), 0, null))
                        .withRel("pre-defined-security-question")
                        .withTitle("Check answer")
                        .withType(HttpMethod.GET.name())
        );
    }
}
