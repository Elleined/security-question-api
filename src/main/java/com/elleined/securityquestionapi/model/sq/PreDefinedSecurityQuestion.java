package com.elleined.securityquestionapi.model.sq;

import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Cacheable
@org.hibernate.annotations.Cache(region = "preDefinedSecurityQuestionCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(
        name = "tbl_pre_defined_security_question",
        indexes = @Index(name = "created_at_idx", columnList = "created_at")
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PreDefinedSecurityQuestion extends SecurityQuestion {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "pre_defined_question_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private PreDefinedQuestion preDefinedQuestion;
}
