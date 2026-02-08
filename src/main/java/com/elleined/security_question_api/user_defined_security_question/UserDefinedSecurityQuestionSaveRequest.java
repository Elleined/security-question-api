package com.elleined.security_question_api.user_defined_security_question;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserDefinedSecurityQuestionSaveRequest(
        @JsonProperty("resource_id")
        @NotNull(message = "Resource id is required")
        UUID resourceId,

        @JsonProperty("question")
        @NotBlank(message = "Question is required")
        String question,

        @JsonProperty("answer")
        @NotBlank(message = "Answer is required")
        String answer
) {
}
