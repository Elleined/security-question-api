package com.elleined.securityquestionapi.repository.sq;

import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDefinedSecurityQuestionRepository extends JpaRepository<UserDefinedSecurityQuestion, Integer> {
}