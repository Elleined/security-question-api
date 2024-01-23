package com.elleined.securityquestionapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    private int id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "user")
    @Setter(AccessLevel.NONE)
    private List<SecurityQuestion> securityQuestions;

    public boolean has(SecurityQuestion securityQuestion) {
        return this.getSecurityQuestions().stream().anyMatch(securityQuestion::equals);
    }
}
