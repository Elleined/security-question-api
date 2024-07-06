package com.elleined.securityquestionapi.model.sq;

import com.elleined.securityquestionapi.model.PrimaryKeyIdentity;
import com.elleined.securityquestionapi.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
        name = "tbl_security_question",
        indexes = @Index(name = "created_at_idx", columnList = "created_at")
)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class SecurityQuestion extends PrimaryKeyIdentity {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private User owner;

    @Column(
            name = "answer",
            nullable = false,
            updatable = false
    )
    private String answer;
}
