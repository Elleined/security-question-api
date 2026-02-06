package com.elleined.security_question_api.question;

import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("security_question")
public record SecurityQuestion(
        @Column("id") @Nullable @Id Long id,
        @Column("created_at") LocalDateTime createdAt,
        @Column("name") String name
) {
}
