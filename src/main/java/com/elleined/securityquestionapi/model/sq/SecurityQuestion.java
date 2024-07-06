package com.elleined.securityquestionapi.model.sq;

import com.elleined.securityquestionapi.model.PrimaryKeyIdentity;
import com.elleined.securityquestionapi.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
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
