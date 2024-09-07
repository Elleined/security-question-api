package com.elleined.securityquestionapi.dto.sq;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserDefinedSecurityQuestionDTO extends SecurityQuestionDTO {
    private String question;
}
