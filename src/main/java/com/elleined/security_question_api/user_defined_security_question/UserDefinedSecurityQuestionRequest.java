package com.elleined.security_question_api.user_defined_security_question;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDefinedSecurityQuestionRequest(
        @org.hibernate.validator.constraints.UUID UUID id,
        @org.hibernate.validator.constraints.UUID UUID resourceId,
        @NotBlank String answer
) {
}
