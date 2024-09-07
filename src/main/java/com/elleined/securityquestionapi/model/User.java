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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Cacheable
@org.hibernate.annotations.Cache(region = "sqaUserCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User extends PrimaryKeyIdentity implements UserDetails {

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "password")
    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
