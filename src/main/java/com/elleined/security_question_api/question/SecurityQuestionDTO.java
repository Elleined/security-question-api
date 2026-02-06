package com.elleined.security_question_api.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SecurityQuestionDTO(
        @Column("id") @JsonProperty("id") UUID id,
        @Column("created_at") @JsonProperty("created_at") LocalDateTime createdAt,
        @Column("resource_id") @JsonProperty("resource_id") BigDecimal resourceId,
        @Column("question") @JsonProperty("question") String question
) implements Serializable {
}
