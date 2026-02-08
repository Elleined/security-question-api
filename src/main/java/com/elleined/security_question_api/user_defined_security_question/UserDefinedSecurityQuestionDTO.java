package com.elleined.security_question_api.user_defined_security_question;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

public record UserDefinedSecurityQuestionDTO(
        @JsonProperty("id") @Column("id") UUID id,
        @JsonProperty("resource_id") @Column("resource_id") UUID resourceId,
        @JsonProperty("security_question") @Column("security_question") String securityQuestion
) {
}
