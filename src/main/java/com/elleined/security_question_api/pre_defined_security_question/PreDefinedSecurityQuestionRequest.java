package com.elleined.security_question_api.pre_defined_security_question;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PreDefinedSecurityQuestionRequest(
        @JsonProperty("resource_id")
        @NotNull(message = "Resource id is required")
        UUID resourceId,

        @JsonProperty("security_question_id")
        @NotNull(message = "Security question id is required")
        UUID securityQuestionId,

        @JsonProperty("answer")
        @NotBlank(message = "answer is required")
        String answer
) {
}
