package com.elleined.security_question_api.pre_defined_security_question;

import com.elleined.security_question_api.question.SecurityQuestion;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("pre_defined_security_question")
public record PreDefinedSecurityQuestion(
        @Column("id") @Nullable @Id UUID id,
        @Column("created_at") LocalDateTime createdAt,
        @Column("resource_id") BigDecimal resourceId,
        @Column("answer") String answer,
        @Column("question_id") AggregateReference<SecurityQuestion, UUID> question
) {
}
