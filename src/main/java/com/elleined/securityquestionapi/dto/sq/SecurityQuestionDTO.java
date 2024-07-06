package com.elleined.securityquestionapi.dto.sq;

import com.elleined.securityquestionapi.dto.DTO;
import com.elleined.securityquestionapi.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class SecurityQuestionDTO extends DTO {
    private UserDTO ownerDTO;

    @Builder
    public SecurityQuestionDTO(int id, LocalDateTime createdAt, UserDTO ownerDTO) {
        super(id, createdAt);
        this.ownerDTO = ownerDTO;
    }
}
