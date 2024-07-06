package com.elleined.securityquestionapi.model.sq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Cacheable
@org.hibernate.annotations.Cache(region = "userDefinedSecurityQuestionCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(
        name = "tbl_user_defined_security_question",
        indexes = @Index(name = "created_at_idx", columnList = "created_at")
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserDefinedSecurityQuestion extends SecurityQuestion {

    @Column(
            name = "question",
            nullable = false,
            updatable = false,
            unique = true
    )
    private String question;
}
