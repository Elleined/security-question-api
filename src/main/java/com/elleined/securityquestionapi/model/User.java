package com.elleined.securityquestionapi.model;
import com.elleined.securityquestionapi.model.sq.PreDefinedSecurityQuestion;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "owner")
    private Set<PreDefinedSecurityQuestion> preDefinedSecurityQuestions;

    @OneToMany(mappedBy = "owner")
    private List<UserDefinedSecurityQuestion> userDefinedQuestions;

    public boolean has(PreDefinedSecurityQuestion preDefinedSecurityQuestion) {
        return this.getPreDefinedSecurityQuestions().stream().anyMatch(preDefinedSecurityQuestion::equals);
    }

    public boolean has(UserDefinedSecurityQuestion userDefinedQuestion) {
        return this.getUserDefinedQuestions().stream().anyMatch(userDefinedQuestion::equals);
    }
}
