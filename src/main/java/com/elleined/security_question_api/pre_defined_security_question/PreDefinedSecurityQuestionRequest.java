package com.elleined.security_question_api.pre_defined_security_question;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PreDefinedSecurityQuestionRequest(
        @JsonProperty("resource_id")
        @org.hibernate.validator.constraints.UUID
        UUID resourceId,

        @JsonProperty("security_question_id")
        @org.hibernate.validator.constraints.UUID(message = "Please provide a valid security question id")
        UUID securityQuestionId,

        @JsonProperty("answer")
        @NotBlank(message = "answer is required")
        String answer
) {
}
