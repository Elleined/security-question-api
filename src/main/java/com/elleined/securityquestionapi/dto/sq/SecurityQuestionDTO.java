package com.elleined.securityquestionapi.dto.sq;

import com.elleined.securityquestionapi.dto.DTO;
import com.elleined.securityquestionapi.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class SecurityQuestionDTO extends DTO {
    private UserDTO ownerDTO;

}
