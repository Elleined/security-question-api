package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.List;

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
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(User currentUser, boolean doInclude) {
        return List.of();
    }
}
