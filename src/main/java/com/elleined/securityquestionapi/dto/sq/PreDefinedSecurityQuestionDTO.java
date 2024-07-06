package com.elleined.securityquestionapi.dto.sq;

import com.elleined.securityquestionapi.dto.PreDefinedQuestionDTO;
import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PreDefinedSecurityQuestionDTO extends SecurityQuestionDTO {
    private PreDefinedQuestionDTO preDefinedQuestionDTO;
    private String question;

    @Builder
    public PreDefinedSecurityQuestionDTO(int id,
                                         LocalDateTime createdAt,
                                         UserDTO ownerDTO,
                                         PreDefinedQuestionDTO preDefinedQuestionDTO,
                                         String question) {
        super(id, createdAt, ownerDTO);
        this.preDefinedQuestionDTO = preDefinedQuestionDTO;
        this.question = question;
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
        return List.of();
    }
}
