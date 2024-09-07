package com.elleined.securityquestionapi.dto.sq;

import com.elleined.securityquestionapi.dto.PreDefinedQuestionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PreDefinedSecurityQuestionDTO extends SecurityQuestionDTO {
    private PreDefinedQuestionDTO preDefinedQuestionDTO;
}
