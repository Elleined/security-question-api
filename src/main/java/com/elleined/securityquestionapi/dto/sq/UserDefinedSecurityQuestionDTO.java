package com.elleined.securityquestionapi.dto.sq;

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
        return List.of();
    }
}
