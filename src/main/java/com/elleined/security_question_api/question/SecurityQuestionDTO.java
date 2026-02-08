package com.elleined.security_question_api.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.util.UUID;

public record SecurityQuestionDTO(
        @Column("id") @JsonProperty("id") UUID id,
        @Column("question") @JsonProperty("question") String question
) implements Serializable {
}
