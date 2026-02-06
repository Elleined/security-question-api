package com.elleined.security_question_api.user_defined_security_question;

import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("user_defined_security_question")
public record UserDefinedSecurityQuestion(
        @Column("id") @Nullable @Id UUID id,
        @Column("created_at") LocalDateTime createdAt,
        @Column("resource_id") BigDecimal resourceId,
        @Column("answer") String answer,
        @Column("question") String question
) {
}
