package com.elleined.securityquestionapi.repository.sq;

import com.elleined.securityquestionapi.model.sq.PreDefinedSecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreDefinedSecurityQuestionRepository extends JpaRepository<PreDefinedSecurityQuestion, Integer> {
}