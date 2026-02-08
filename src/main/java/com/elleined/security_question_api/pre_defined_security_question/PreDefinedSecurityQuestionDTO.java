package com.elleined.security_question_api.pre_defined_security_question;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.util.UUID;

public record PreDefinedSecurityQuestionDTO(
        @JsonProperty("resource_id") @Column("resource_id") UUID resourceId,
        @JsonProperty("security_question_id") @Column("security_question_id") UUID securityQuestionId,
        @JsonProperty("security_question") @Column("security_question") String securityQuestion
) implements Serializable {
}
