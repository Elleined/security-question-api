package com.elleined.securityquestionapi.model;


import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.service.sq.SecurityQuestionService;
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

    @OneToMany(mappedBy = "owner")
    @Setter(AccessLevel.NONE)
    private List<SecurityQuestion> securityQuestions;

    @OneToMany(mappedBy = "owner")
    @Setter(AccessLevel.NONE)
    private List<CustomQuestion> customQuestions;

    public List<Integer> securityQuestionIds() {
        return this.securityQuestions.stream().map(SecurityQuestion::getId).toList();
    }
    public List<Integer> customQuestionIds() {
        return this.customQuestions.stream().map(CustomQuestion::getId).toList();
    }

    public boolean has(SecurityQuestion securityQuestion) {
        return this.getSecurityQuestions().stream().anyMatch(securityQuestion::equals);
    }

    public boolean has(CustomQuestion customQuestion) {
        return this.getCustomQuestions().stream().anyMatch(customQuestion::equals);
    }

    public boolean hasReachedLimitOfSecurityQuestions() {
        return this.getSecurityQuestions().size() == SecurityQuestionService.SECURITY_QUESTION_LIMIT;
    }
}
